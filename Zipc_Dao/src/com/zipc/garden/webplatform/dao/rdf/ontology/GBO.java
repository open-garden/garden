package com.zipc.garden.webplatform.dao.rdf.ontology;

import org.apache.jena.graph.Node;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

/**
 * Garden Base Ontology
 */
public class GBO {

    /** "File". Used to create a resource. */
    public static final String FILE_NAME = "File";

    /**
     * Creates an ontology for the specified URI.
     * @param local specified URI
     * @return Ontology
     */
    protected static final Ontology garden(String local) {
        return ModelFactory.createOntologyModel().createOntology(local);
    }

    /**
     * Create an OntClass based on the GARDEN namespace and arguments.
     * @param local String after the GARDEN namespace
     * @return OntClass
     */
    protected static final OntClass clazz(String local) {
        return ModelFactory.createOntologyModel().createClass(GDNNS.GARDEN.NS + local);
    }

    /**
     * Create an OntResource based on the GARDEN namespace and arguments.
     * @param local String after the GARDEN namespace
     * @return An OntResource with the given URI
     */
    protected static final Resource resource(String local) {
        return ModelFactory.createOntologyModel().createOntResource(GDNNS.GARDEN.NS + local);
    }

    /**
     * Create an DatatypeProperty based on the GARDEN namespace and arguments.
     * @param local String after the GARDEN namespace
     * @return A DatatypeProperty resource.
     */
    protected static final Property property(String local) {
        return ModelFactory.createOntologyModel().createDatatypeProperty(GDNNS.GARDEN.NS + local);
    }

    /**
     * Create an ObjectProperty based on the GARDEN namespace and arguments.
     * @param local String after the GARDEN namespace
     * @return An ObjectProperty resource.
     */
    protected static final Property objProperty(String local) {
        return ModelFactory.createOntologyModel().createObjectProperty(GDNNS.GARDEN.NS + local);
    }

    /** Ontology common to all models. */
    public static final Ontology garden = Init.garden();

    /** OntClass of the file. */
    public static final OntClass File = Init.File();

    /** DatatypeProperty that has the FileID. */
    public static final Property hasFileId = Init.hasFileId();

    /** DatatypeProperty that has the FileName. */
    public static final Property hasFileName = Init.hasFileName();

    /** DatatypeProperty that has the ProjectId. */
    public static final Property hasProjectId = Init.hasProjectId();

    /** DatatypeProperty that has the hash value of the file. */
    public static final Property hasHash = Init.hasHash();

    /** ObjectProperty that represent the relationships of other files. */
    public static final Property refFile = Init.refFile();

    /**
     * A class that summarizes the initial processing methods of Ontology, OntClass, and Property.
     */
    public static class Init {

        /**
         * Create / get an ontology common to all models.
         * @return An ontology common to all models.
         */
        public static Ontology garden() {
            return GBO.garden(GDNNS.GARDEN.URI);
        }

        /**
         * Create / get OntClass of the file.
         * @return OntClass of the file.
         */
        public static OntClass File() {
            return clazz("File");
        }

        /**
         * Create / get a DatatypeProperty that has the FileID..
         * @return DatatypeProperty that has the FileID.
         */
        public static Property hasFileId() {
            Property p = property("hasFileId");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / get a DatatypeProperty that has the FileName.
         * @return DatatypeProperty that has the FileName.
         */
        public static Property hasFileName() {
            Property p = property("hasFileName");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / get a DatatypeProperty that has the ProjectId.
         * @return DatatypeProperty that has the ProjectId.
         */
        public static Property hasProjectId() {
            Property p = property("hasProjectId");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / get a DatatypeProperty that has the hash value of the file.
         * @return DatatypeProperty that has the hash value of the file.
         */
        public static Property hasHash() {
            Property p = property("hasHash");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / get ObjectProperty that represent the relationships of other files.
         * @return ObjectProperty that represent the relationships of other files.
         */
        public static Property refFile() {
            Property p = objProperty("refFile");
            p.addProperty(RDF.type, OWL2.ObjectProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return p;
        }
    }

    /**
     * The RDFS vocabulary, expressed for the SPI layer in terms of .graph Nodes.
     */
    @SuppressWarnings("hiding")
    public static class Nodes {

        /** Node of Ontology created by {@link Init#garden()}. */
        public static final Node garden = Init.garden().asNode();

        /** Node of OntClass created by {@link Init#File()}. */
        public static final Node File = Init.File().asNode();

        /** Node of Property created by {@link Init#hasFileId()}. */
        public static final Node hasFileId = Init.hasFileId().asNode();

        /** Node of Property created by {@link Init#hasFileName()}. */
        public static final Node hasFileName = Init.hasFileName().asNode();

        /** Node of Property created by {@link Init#hasProjectId()}. */
        public static final Node hasProjectId = Init.hasProjectId().asNode();

        /** Node of Property created by {@link Init#hasHash()}. */
        public static final Node hasHash = Init.hasHash().asNode();

        /** Node of Property created by {@link Init#refFile()}. */
        public static final Node refFile = Init.refFile().asNode();
    }
}
