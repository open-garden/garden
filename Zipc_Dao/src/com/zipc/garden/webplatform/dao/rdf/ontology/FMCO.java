package com.zipc.garden.webplatform.dao.rdf.ontology;

import org.apache.jena.graph.Node;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.RDFS;

/**
 * Feature Model Constraint Ontology
 */
public class FMCO extends GBO {

    /** Feature model constraint extension name on the URI */
    public static final String FMC_NAME = "FMC";

    /**
     * Creates an feature model constraint ontology based on the specified URI.
     * @param local specified URI
     * @return Feature model constraint ontology
     */
    protected static final Ontology fmcOntology(String local) {
        return ModelFactory.createOntologyModel().createOntology(local);
    }

    /**
     * Create an OntClass based on the FMC namespace and arguments.
     * @param local String after the FMC namespace
     * @return OntClass
     */
    protected static final OntClass fmcClazz(String local) {
        return ModelFactory.createOntologyModel().createClass(GDNNS.FMC.NS + local);
    }

    /** Ontology of feature model constraint. */
    public static final Ontology fmcOntology = Init.fmcOntology();

    /** OntClass which means a feature model constraint. A subclass of File. */
    public static final OntClass FMConstraint = Init.FMConstraint();

    /**
     * A class that summarizes the initial processing methods of Ontology and OntClass.
     */
    public static class Init {

        /**
         * Create / Get Ontology of feature model constraint.
         * @return Ontology of feature model constraint.
         */
        public static Ontology fmcOntology() {
            return FMCO.fmcOntology(GDNNS.FMC.URI);
        }

        /**
         * Create / get OntClass which means a feature model constraint.
         * @return OntClass which means a feature model constraint.
         */
        public static OntClass FMConstraint() {
            OntClass fmc = fmcClazz("FMConstraint");
            fmc.addProperty(RDFS.subClassOf, GBO.File);
            return fmc;
        }
    }

    /**
     * The RDFS vocabulary, expressed for the SPI layer in terms of .graph Nodes.
     */
    @SuppressWarnings("hiding")
    public static class Nodes {

        /** Node of Ontology created by {@link Init#fmcOntology()}. */
        public static final Node fmcOntology = Init.fmcOntology().asNode();

        /** Node of OntClass created by {@link Init#FMConstraint()}. */
        public static final Node FMConstraint = Init.FMConstraint().asNode();

    }
}
