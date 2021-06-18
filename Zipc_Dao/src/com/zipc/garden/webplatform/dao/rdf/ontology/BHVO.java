package com.zipc.garden.webplatform.dao.rdf.ontology;

import org.apache.jena.graph.Node;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.vocabulary.RDFS;

/**
 * Behavior Model Ontology
 */
public class BHVO extends GBO {

    /** Behavior model extension name on the URI */
    public static final String BHV_NAME = "BHV";

    /**
     * Creates an behavior model ontology based on the specified URI.
     * @param local specified URI
     * @return Behavior model Ontology
     */
    protected static final Ontology bhvOntology(String local) {
        return ModelFactory.createOntologyModel().createOntology(local);
    }

    /**
     * Create an OntClass based on the BM namespace and arguments.
     * @param local String after the BM namespace
     * @return OntClass
     */
    protected static final OntClass bhvClazz(String local) {
        return ModelFactory.createOntologyModel().createClass(GDNNS.BM.NS + local);
    }

    /**
     * Create an DatatypeProperty based on the BM namespace and arguments.
     * @param local String after the BM namespace
     * @return A DatatypeProperty resource.
     */
    protected static final Property bhvProperty(String local) {
        return ModelFactory.createOntologyModel().createDatatypeProperty(GDNNS.BM.NS + local);
    }

    /** Ontology of behavior model. */
    public static final Ontology bhvOntology = Init.bhvOntology();

    /** OntClass of the behavior model. */
    public static final OntClass BehaviorModel = Init.BehaviorModel();

    /**
     * A class that summarizes the initial processing methods of Ontology, and OntClass.
     */
    public static class Init {

        /**
         * Create / get an behavior model ontology.
         * @return behavior model ontology.
         */
        public static Ontology bhvOntology() {
            return BHVO.bhvOntology(GDNNS.BM.URI);
        }

        /**
         * Create / get OntClass of the behavior model.
         * @return OntClass of the behavior model.
         */
        public static OntClass BehaviorModel() {
            OntClass bhv = bhvClazz("BehaviorModel");
            bhv.addProperty(RDFS.subClassOf, GBO.File);
            return bhv;
        }
    }

    /**
     * The RDFS vocabulary, expressed for the SPI layer in terms of .graph Nodes.
     */
    public static class Nodes {

        /** Node of Ontology created by {@link Init#bhvOntology()}. */
        public static final Node bhvOntology = Init.bhvOntology().asNode();

        /** Node of OntClass created by {@link Init#BehaviorModel()}. */
        public static final Node BehaviorModel = Init.BehaviorModel().asNode();

    }
}
