package com.zipc.garden.webplatform.dao.rdf.ontology;

import org.apache.jena.graph.Node;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.vocabulary.RDFS;

/**
 * Architecture Model Ontology
 */
public class ARCO extends GBO {

    /** Architecture model extension name on the URI */
    public static final String ARC_NAME = "ARC";

    /**
     * Creates an architecture model ontology based on the specified URI.
     * @param local specified URI
     * @return Architecture model Ontology
     */
    protected static final Ontology arcOntology(String local) {
        return ModelFactory.createOntologyModel().createOntology(local);
    }

    /**
     * Create an OntClass based on the ARC namespace and arguments.
     * @param local String after the ARC namespace
     * @return OntClass
     */
    protected static final OntClass arcClazz(String local) {
        return ModelFactory.createOntologyModel().createClass(GDNNS.ARC.NS + local);
    }

    /**
     * Create an DatatypeProperty based on the ARC namespace and arguments.
     * @param local String after the ARC namespace
     * @return A DatatypeProperty resource.
     */
    protected static final Property arcProperty(String local) {
        return ModelFactory.createOntologyModel().createDatatypeProperty(GDNNS.ARC.NS + local);
    }

    /** Ontology of architecture model. */
    public static final Ontology arcOntology = Init.arcOntology();

    /** OntClass of the architecture model. */
    public static final OntClass ArchitectureModel = Init.ArchitectureModel();

    /**
     * A class that summarizes the initial processing methods of Ontology, and OntClass.
     */
    public static class Init {

        /**
         * Create / get an architecture model ontology.
         * @return architecture model ontology.
         */
        public static Ontology arcOntology() {
            return ARCO.arcOntology(GDNNS.ARC.URI);
        }

        /**
         * Create / get OntClass of the architecture model.
         * @return OntClass of the architecture model.
         */
        public static OntClass ArchitectureModel() {
            OntClass arc = arcClazz("ArchitectureModel");
            arc.addProperty(RDFS.subClassOf, GBO.File);
            return arc;
        }
    }

    /**
     * The RDFS vocabulary, expressed for the SPI layer in terms of .graph Nodes.
     */
    public static class Nodes {

        /** Node of Ontology created by {@link Init#arcOntology()}. */
        public static final Node arcOntology = Init.arcOntology().asNode();

        /** Node of OntClass created by {@link Init#ArchitectureModel()}. */
        public static final Node ArchitectureModel = Init.ArchitectureModel().asNode();

    }
}
