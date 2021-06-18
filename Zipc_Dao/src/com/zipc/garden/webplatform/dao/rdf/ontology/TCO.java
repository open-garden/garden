package com.zipc.garden.webplatform.dao.rdf.ontology;

import org.apache.jena.graph.Node;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.RDFS;

/**
 * Test Condition Ontology
 */
public class TCO extends GBO {

    /** Test condition extension name on the URI */
    public static final String TC_NAME = "TC";

    /**
     * Creates an test condition ontology based on the specified URI.
     * @param local specified URI
     * @return Test condition ontology
     */
    protected static final Ontology tcOntology(String local) {
        return ModelFactory.createOntologyModel().createOntology(local);
    }

    /**
     * Create an OntClass based on the TC namespace and arguments.
     * @param local String after the TC namespace
     * @return OntClass
     */
    protected static final OntClass tcClazz(String local) {
        return ModelFactory.createOntologyModel().createClass(GDNNS.TC.NS + local);
    }

    /** Ontology of test condition. */
    public static final Ontology tcOntology = Init.tcOntology();

    /** OntClass which means a test condition file. A subclass of File. */
    public static final OntClass TestCondition = Init.TestCondition();

    /**
     * A class that summarizes the initial processing methods of Ontology and OntClass.
     */
    public static class Init {

        /**
         * Create / Get ontology of test condition.
         * @return Ontology of test condition.
         */
        public static Ontology tcOntology() {
            return TCO.tcOntology(GDNNS.TC.URI);
        }

        /**
         * Create / get OntClass which means a test condition.
         * @return OntClass which means a test condition.
         */
        public static OntClass TestCondition() {
            OntClass tc = tcClazz("TestCondition");
            tc.addProperty(RDFS.subClassOf, GBO.File);
            return tc;
        }
    }

    /**
     * The RDFS vocabulary, expressed for the SPI layer in terms of .graph Nodes.
     */
    @SuppressWarnings("hiding")
    public static class Nodes {

        /** Node of Ontology created by {@link Init#tcOntology()}. */
        public static final Node tcOntology = Init.tcOntology().asNode();

        /** Node of OntClass created by {@link Init#TestCondition()}. */
        public static final Node TestCondition = Init.TestCondition().asNode();

    }
}
