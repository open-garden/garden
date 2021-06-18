from rdflib import Graph, Literal, RDF, URIRef, RDFS, OWL
from rdflib.term import Node
from rdflib.plugins.stores.sparqlstore import SPARQLUpdateStore

from datas.scene_type import *
from datas.scenario_schema import ScenarioSchema, GRAPH_ID, BASE, SCENARIO
from utils.constants import *


class RdfGraphCreator(object):

    def __init__(self, database, measurement):
        """
        initialize
        :param database:
        :param measurement:
        """
        self.database = database
        self.measurement = measurement
        self.store = SPARQLUpdateStore(queryEndpoint="{}:{}/{}/query".format(JF_HOST, JF_PORT, JF_DATASET),
                                       update_endpoint="{}:{}/{}/update".format(JF_HOST, JF_PORT, JF_DATASET))
        self.graph = Graph(identifier=URIRef(GRAPH_ID),
                           store=self.store)
        self.__init_graph()

    def __init_graph(self):
        """
        initialize graph
        :return:
        """
        # class
        ScenarioSchema.raw_data_class(self.graph)
        ScenarioSchema.action_class(self.graph)
        ScenarioSchema.actor_class(self.graph)
        ScenarioSchema.scene_type_class(self.graph)
        ScenarioSchema.scene_class(self.graph)
        ScenarioSchema.time_class(self.graph)
        ScenarioSchema.tag_class(self.graph)

        # property
        ScenarioSchema.actor_property(self.graph)
        ScenarioSchema.target_property(self.graph)
        ScenarioSchema.scene_type_property(self.graph)
        ScenarioSchema.initial_property(self.graph)
        ScenarioSchema.next_property(self.graph)
        ScenarioSchema.start_property(self.graph)
        ScenarioSchema.end_property(self.graph)
        ScenarioSchema.time_property(self.graph)
        ScenarioSchema.tag_property(self.graph)

    def create_raw_data(self):
        """
        :return:
        """
        raw_data = URIRef(BASE['{}_{}_scenario'.format(self.database, self.measurement)])
        self.graph.add((raw_data, RDF.type, ScenarioSchema.raw_data()))
        self.graph.add((raw_data, RDFS.label, Literal(self.measurement)))
        self.graph.add((raw_data, URIRef(OWL['namespace']), Literal(self.database)))
        return raw_data

    def create_actor(self, vehicle):
        """
        :param vehicle: vehicle name => ego, obs1, obs2, ...
        :return:
        """
        actor = URIRef(BASE['{}_{}_Actor_{}'.format(self.database, self.measurement, vehicle)])
        self.graph.add((actor, RDF.type, ScenarioSchema.actor()))
        self.graph.add((actor, RDFS.label, Literal(vehicle)))
        return actor

    def create_tag(self, value):
        """
        :param value: SAKURA32 name => SAKURA32-No1, SAKURA32-No2, ...
        :return:
        """
        tag = URIRef(BASE['{}_{}_Tag_{}'.format(self.database, self.measurement, value)])
        self.graph.add((tag, RDF.type, ScenarioSchema.tag()))
        self.graph.add((tag, RDFS.label, Literal(value)))
        return tag

    def create_time(self, value):
        """
        :param value: time value => 2021-02-16T19:03:16.516000Z
        :return:
        """
        time = URIRef(BASE['{}_{}_Time_{}'.format(self.database, self.measurement, value)])
        self.graph.add((time, RDF.type, ScenarioSchema.time()))
        self.graph.add((time, RDFS.label, Literal(value)))
        return time

    def create_scene_type(self, vehicle, _type):
        """

        :param vehicle: vehicle name => ego, obs1, obs2, ...
        :param _type: scene_type(LANE, DIRECTION, BEHAVIOR(LaneMotion, VehicleMotion), ROADGEOMETRY)
        :return:
        """
        scene_type = URIRef(BASE['{}_{}_{}_{}'.format(self.database, self.measurement, vehicle, _type.value)])
        self.graph.add((scene_type, RDF.type, ScenarioSchema.scene_type()))
        self.graph.add((scene_type, RDFS.label, Literal(_type.value)))
        return scene_type

    def create_scene(self, vehicle, scene_type, scene, num):
        """
        :param vehicle: vehicle name => ego, obs1, obs2, ...
        :param scene_type: scene_type(LANE, DIRECTION, BEHAVIOR, ROADGEOMETRY)
        :param scene: behaivor_type or direction_type
                behaivor_type(LANE_CHANGE, LANE_KEEP, ACCEL, DECEL, SYNC)
                direction_type(FRONT_FRONT, FRONT, RIGHT_FRONT, LEFT_FRONT, LEFT, RIGHT, BACK, LEFT_BACK, RIGHT_BACK)
        :param num: data count
        :return:
        """
        uri_ref = URIRef(BASE['{}_{}_{}_{}_{}_{}'.format(self.database, self.measurement,
                                                         vehicle,
                                                         scene_type.value,
                                                         scene.value, num
                                                         )])
        self.graph.add((uri_ref, RDF.type, ScenarioSchema.scene()))
        self.graph.add((uri_ref, RDFS.label, Literal(scene.value)))
        return uri_ref

    def add_top_object_property(self, subject, _object):
        """

        :param subject:
        :param _object:
        :return:
        """
        assert isinstance(subject, Node), "Subject %s must be an rdflib term" % (subject,)
        assert isinstance(_object, Node), "Object %s must be an rdflib term" % (_object,)
        self.graph.add((subject, URIRef(OWL['topObjectProperty']), _object))

    def add_graph(self, subject, predicate, _object):
        """

        :param subject:
        :param predicate:
        :param _object:
        :return:
        """
        assert isinstance(subject, Node), "Subject %s must be an rdflib term" % (subject,)
        assert isinstance(predicate, Node), "Predicate %s must be an rdflib term" % (predicate,)
        assert isinstance(_object, Node), "Object %s must be an rdflib term" % (_object,)
        self.add_top_object_property(subject, _object)
        self.graph.add((subject, predicate, _object))

    def build_behavior(self, data_dict, scene_type, target=None):
        self.__build(data_dict, target, scene_type)

    def build_direction(self, data_dict, target=None):
        self.__build(data_dict, target, SceneType.DIRECTION)

    def build_road_geometry(self, data_dict, target=None):
        self.__build(data_dict, target, SceneType.ROADGEOMETRY)

    def build_surrounding_vehicle_motion(self, data_dict, target=None):
        self.__build(data_dict, target, SceneType.SURROUNDING_VEHICLE_MOTION)

    def __build(self, data_dict, target, _type):
        raw_data = self.create_raw_data()

        ego_actor = None
        if target is not None:
            ego_actor = self.create_actor("ego")
            self.add_graph(raw_data, URIRef(SCENARIO['actor']), ego_actor)

        for vehicle, datas in data_dict.items():
            actor = self.create_actor(vehicle)
            self.add_graph(raw_data, URIRef(SCENARIO['actor']), actor)

            prev = None
            for num in range(len(datas)):
                data = datas[num]

                scene = self.create_scene(vehicle, _type, data.get_type(), num)
                start = self.create_time(data.get_start())
                self.add_graph(raw_data, URIRef(SCENARIO['time']), start)
                self.add_graph(scene, URIRef(SCENARIO['start']), start)
                end = self.create_time(data.get_end())
                self.add_graph(raw_data, URIRef(SCENARIO['time']), end)
                self.add_graph(scene, URIRef(SCENARIO['end']), end)

                if num == 0:
                    scene_type = self.create_scene_type(vehicle, _type)
                    self.add_graph(scene_type, URIRef(SCENARIO['actor']), actor)
                    if ego_actor is not None:
                        self.add_graph(scene_type, URIRef(SCENARIO['target']), ego_actor)
                    self.add_graph(scene_type, URIRef(SCENARIO['initial']), scene)

                if prev is not None:
                    self.add_graph(prev, URIRef(SCENARIO['next']), scene)
                prev = scene

        self.commit()

    def build_tag(self, data_list):
        raw_data = self.create_raw_data()

        for data in data_list:
            tag = self.create_tag(data.get_type())
            start = self.create_time(data.get_start())
            self.add_graph(raw_data, URIRef(SCENARIO['time']), start)
            self.add_graph(tag, URIRef(SCENARIO['start']), start)
            end = self.create_time(data.get_end())
            self.add_graph(raw_data, URIRef(SCENARIO['time']), end)
            self.add_graph(tag, URIRef(SCENARIO['end']), end)
            self.add_graph(raw_data, URIRef(SCENARIO['tag']), tag)

        self.commit()

    def write(self, output="output.ttl"):
        self.graph.serialize(destination=output, format='turtle')

    def commit(self):
        self.graph.commit()
