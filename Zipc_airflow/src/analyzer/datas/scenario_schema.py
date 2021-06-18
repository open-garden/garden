from rdflib.extras.infixowl import Property, Class
from rdflib import URIRef, OWL, Namespace

GRAPH_ID = "http://www.zipc.com/model/scenario"
# GRAPH_ID = "http://www.zipc.com/model/hoge"
BASE = Namespace("http://www.zipc.com/model/scenario#")
SCENARIO = Namespace("http://www.zipc.com/schema/scenario-schema#")


class ScenarioSchema(object):

    @staticmethod
    def raw_data():
        return URIRef(SCENARIO['RawData'])

    @staticmethod
    def actor():
        return URIRef(SCENARIO['Actor'])

    @staticmethod
    def tag():
        return URIRef(SCENARIO['Tag'])

    @staticmethod
    def scene():
        return URIRef(SCENARIO['Scene'])

    @staticmethod
    def scene_type():
        return URIRef(SCENARIO['SceneType'])

    @staticmethod
    def time():
        return URIRef(SCENARIO['Time'])

    @staticmethod
    def raw_data_class(graph):
        return Class(identifier=SCENARIO['RawData'], graph=graph)

    @staticmethod
    def actor_class(graph):
        return Class(identifier=SCENARIO['Actor'], graph=graph)

    @staticmethod
    def action_class(graph):
        return Class(identifier=SCENARIO['Action'], graph=graph)

    @staticmethod
    def scene_type_class(graph):
        return Class(identifier=SCENARIO['SceneType'], graph=graph)

    @staticmethod
    def scene_class(graph):
        return Class(identifier=SCENARIO['Scene'], graph=graph)

    @staticmethod
    def time_class(graph):
        return Class(identifier=SCENARIO['Time'], graph=graph)

    @staticmethod
    def tag_class(graph):
        return Class(identifier=SCENARIO['Tag'], graph=graph)

    @staticmethod
    def actor_property(graph):
        return Property(identifier=URIRef(SCENARIO['actor']),
                        subPropertyOf=[OWL['topObjectProperty'],
                                       SCENARIO['actor']],
                        graph=graph)

    @staticmethod
    def target_property(graph):
        return Property(identifier=URIRef(SCENARIO['target']),
                        subPropertyOf=[OWL['topObjectProperty'],
                                       SCENARIO['time']],
                        graph=graph)

    @staticmethod
    def scene_type_property(graph):
        return Property(identifier=URIRef(SCENARIO['scene_type']),
                        subPropertyOf=[OWL['topObjectProperty'],
                                       SCENARIO['scene_type']],
                        graph=graph)

    @staticmethod
    def initial_property(graph):
        return Property(identifier=URIRef(SCENARIO['initial']),
                        subPropertyOf=[OWL['topObjectProperty'],
                                       SCENARIO['initial']],
                        graph=graph)

    @staticmethod
    def next_property(graph):
        return Property(identifier=URIRef(SCENARIO['next']),
                        subPropertyOf=[OWL['topObjectProperty'],
                                       SCENARIO['next']],
                        graph=graph)

    @staticmethod
    def start_property(graph):
        return Property(identifier=URIRef(SCENARIO['start']),
                        subPropertyOf=[OWL['topObjectProperty'],
                                       SCENARIO['start']],
                        graph=graph)

    @staticmethod
    def end_property(graph):
        return Property(identifier=URIRef(SCENARIO['end']),
                        subPropertyOf=[OWL['topObjectProperty'],
                                       SCENARIO['end']],
                        graph=graph)

    @staticmethod
    def time_property(graph):
        return Property(identifier=URIRef(SCENARIO['time']),
                        subPropertyOf=[OWL['topObjectProperty'],
                                       SCENARIO['time']],
                        graph=graph)

    @staticmethod
    def namespace_property(graph):
        return Property(identifier=URIRef(SCENARIO['time']),
                        baseType=OWL['AnnotationProperty'],
                        graph=graph)
