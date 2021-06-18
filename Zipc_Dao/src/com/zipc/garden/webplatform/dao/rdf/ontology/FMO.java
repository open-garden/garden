package com.zipc.garden.webplatform.dao.rdf.ontology;

import org.apache.jena.graph.Node;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.RDFS;

/**
 * Feature Model Ontology
 */
public class FMO extends GBO {

    /** Feature model extension name on the URI */
    public static final String FM_NAME = "FM";

    /**
     * Creates an feature model ontology based on the specified URI.
     * @param local specified URI
     * @return Feature model ontology
     */
    protected static final Ontology fmOntology(String local) {
        return ModelFactory.createOntologyModel().createOntology(local);
    }

    /**
     * Create an OntClass based on the FM namespace and arguments.
     * @param local String after the FM namespace
     * @return OntClass
     */
    protected static final OntClass fmClazz(String local) {
        return ModelFactory.createOntologyModel().createClass(GDNNS.FM.NS + local);
    }

    /** Ontology of feature model. */
    public static final Ontology fmOntology = Init.fmOntology();

    /** OntClass which means a feature model. A subclass of File. */
    public static final OntClass FeatureModel = Init.FeatureModel();

    /**
     * A class that summarizes the initial processing methods of Ontology and OntClass.
     */
    public static class Init {

        /**
         * Create / Get ontology of feature model.
         * @return Ontology of feature model.
         */
        public static Ontology fmOntology() {
            return FMO.fmOntology(GDNNS.FM.URI);
        }

        /**
         * Create / get OntClass which means a feature model.
         * @return OntClass which means a feature model.
         */
        public static OntClass FeatureModel() {
            OntClass fm = fmClazz("FeatureModel");
            fm.addProperty(RDFS.subClassOf, GBO.File);
            return fm;
        }
    }

    /**
     * The RDFS vocabulary, expressed for the SPI layer in terms of .graph Nodes.
     */
    @SuppressWarnings("hiding")
    public static class Nodes {

        /** Node of Ontology created by {@link Init#fmOntology()}. */
        public static final Node fmOntology = Init.fmOntology().asNode();

        /** Node of OntClass created by {@link Init#FeatureModel()}. */
        public static final Node FeatureModel = Init.FeatureModel().asNode();

    }
}
