from utils.rdf_graph_creator import *

class SampleGraphCreator(RdfGraphCreator):

    def __init__(self, database, measurement):
        super().__init__(database, measurement)

    def create(self, datas):
        # raw data
        raw_data = URIRef(BASE['{}_{}_scenario'.format(self.database, self.measurement)])
        self.graph.add((raw_data, RDF.type, ScenarioSchema.raw_data()))
        self.graph.add((raw_data, RDFS.label, Literal(self.measurement)))
        self.graph.add((raw_data, URIRef(OWL['namespace']), Literal(self.database)))

        # ego
        ego = URIRef(BASE['{}_{}_Actor_{}'.format(self.database, self.measurement, "ego")])
        self.graph.add((ego, RDF.type, ScenarioSchema.actor()))
        self.graph.add((ego, RDFS.label, Literal("ego")))

        # scene type
        scene_type = URIRef(BASE['{}_{}_{}_{}'.format(self.database, self.measurement, "ego", "Behavior")])
        self.graph.add((scene_type, RDF.type, ScenarioSchema.scene_type()))
        self.graph.add((scene_type, RDFS.label, Literal("Behavior")))

        # scene
        scene1 = URIRef(BASE['{}_{}_{}_{}'.format(self.database, self.measurement, "ego", "Behavior1")])
        self.graph.add((scene1, RDF.type, ScenarioSchema.scene()))
        self.graph.add((scene1, RDFS.label, Literal("LaneKeep")))

        # scene
        scene2 = URIRef(BASE['{}_{}_{}_{}'.format(self.database, self.measurement, "ego", "Behavior2")])
        self.graph.add((scene2, RDF.type, ScenarioSchema.scene()))
        self.graph.add((scene2, RDFS.label, Literal("LaneChange")))

        # time start
        start1 = URIRef(BASE['{}_{}_Time_{}'.format(self.database, self.measurement, "10.0")])
        self.graph.add((start1, RDF.type, ScenarioSchema.time()))
        self.graph.add((start1, RDFS.label, Literal("10.0")))

        # time end
        end1 = URIRef(BASE['{}_{}_Time_{}'.format(self.database, self.measurement, "20.0")])
        self.graph.add((end1, RDF.type, ScenarioSchema.time()))
        self.graph.add((end1, RDFS.label, Literal("20.0")))

        # time start
        start2 = URIRef(BASE['{}_{}_Time_{}'.format(self.database, self.measurement, "30.0")])
        self.graph.add((start2, RDF.type, ScenarioSchema.time()))
        self.graph.add((start2, RDFS.label, Literal("30.0")))

        # time end
        end2 = URIRef(BASE['{}_{}_Time_{}'.format(self.database, self.measurement, "40.0")])
        self.graph.add((end2, RDF.type, ScenarioSchema.time()))
        self.graph.add((end2, RDFS.label, Literal("40.0")))

        # dependency
        # raw data =>
        self.graph.add((raw_data, URIRef(OWL['topObjectProperty']), ego))
        self.graph.add((raw_data, URIRef(OWL['topObjectProperty']), start1))
        self.graph.add((raw_data, URIRef(OWL['topObjectProperty']), end1))
        self.graph.add((raw_data, URIRef(OWL['topObjectProperty']), start2))
        self.graph.add((raw_data, URIRef(OWL['topObjectProperty']), end2))

        self.graph.add((raw_data, URIRef(SCENARIONS['actor']), ego))
        self.graph.add((raw_data, URIRef(SCENARIONS['time']), start1))
        self.graph.add((raw_data, URIRef(SCENARIONS['time']), end1))
        self.graph.add((raw_data, URIRef(SCENARIONS['time']), start2))
        self.graph.add((raw_data, URIRef(SCENARIONS['time']), end2))

        # behavior
        self.graph.add((scene_type, URIRef(OWL['topObjectProperty']), ego))
        self.graph.add((scene_type, URIRef(OWL['topObjectProperty']), scene1))

        self.graph.add((scene_type, URIRef(SCENARIONS['actor']), ego))
        self.graph.add((scene_type, URIRef(SCENARIONS['initial']), scene1))

        # scene1
        self.graph.add((scene1, URIRef(OWL['topObjectProperty']), start1))
        self.graph.add((scene1, URIRef(OWL['topObjectProperty']), end1))
        self.graph.add((scene1, URIRef(OWL['topObjectProperty']), scene2))

        self.graph.add((scene1, URIRef(SCENARIONS['start']), start1))
        self.graph.add((scene1, URIRef(SCENARIONS['end']), end1))
        self.graph.add((scene1, URIRef(SCENARIONS['next']), scene2))

        # scene2
        self.graph.add((scene2, URIRef(OWL['topObjectProperty']), start2))
        self.graph.add((scene2, URIRef(OWL['topObjectProperty']), end2))

        self.graph.add((scene2, URIRef(SCENARIONS['start']), start2))
        self.graph.add((scene2, URIRef(SCENARIONS['end']), end2))


if __name__ == '__main__':
    creator = SampleGraphCreator("garden_ts", "hoge")
    creator.create(None)
    creator.commit()
