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
 * Feature Pattern Setting Ontology
 */
public class FPSO extends GBO {

    /** Feature pattern setting extension name on the URI */
    public static final String FPS_NAME = "FPS";

    /** Used in URI to identify node. */
    public static final String NODE_NAME = "Node";

    /**
     * Creates an feature pattern setting ontology based on the specified URI.
     * @param local specified URI
     * @return Feature pattern setting ontology
     */
    protected static final Ontology fpsOntology(String local) {
        return ModelFactory.createOntologyModel().createOntology(local);
    }

    /**
     * Create an OntClass based on the FPS namespace and arguments.
     * @param local String after the FPS namespace
     * @return OntClass
     */
    protected static final OntClass fpsClazz(String local) {
        return ModelFactory.createOntologyModel().createClass(GDNNS.FPS.NS + local);
    }

    /**
     * Create an Resource based on the FPS namespace and arguments.
     * @param local String after the FPS namespace
     * @return Resource
     */
    protected static final Resource fpsResource(String local) {
        return ModelFactory.createOntologyModel().createOntResource(GDNNS.FPS.NS + local);
    }

    /**
     * Create an DatatypeProperty based on the FPS namespace and arguments.
     * @param local String after the FPS namespace
     * @return DatatypeProperty
     */
    protected static final Property fpsProperty(String local) {
        return ModelFactory.createOntologyModel().createDatatypeProperty(GDNNS.FPS.NS + local);
    }

    /**
     * Create an ObjectProperty based on the FPS namespace and arguments.
     * @param local String after the FPS namespace
     * @return ObjectProperty
     */
    protected static final Property fpsObjProperty(String local) {
        return ModelFactory.createOntologyModel().createObjectProperty(GDNNS.FPS.NS + local);
    }

    /** Ontology of feature pattern setting. */
    public static final Ontology fpsOntology = Init.fpsOntology();

    /** Means feature pattern generation settings. A subclass of File. */
    public static final OntClass FeaturePatternSetting = Init.FeaturePatternSetting();

    /** Means one node in the feature model. */
    public static final OntClass Node = Init.Node();

    /** Means the user property that a node has. */
    public static final OntClass UserProperty = Init.UserProperty();

    /** Means the type of child node that a node has. */
    public static final OntClass ChildType = Init.ChildType();

    /** It means that the type of child node that the node has is AND. */
    public static final OntClass ChildTypeAND = Init.ChildTypeAND();

    /** It means that the type of child node that the node has is XOR. */
    public static final OntClass ChildTypeXOR = Init.ChildTypeXOR();

    /**
     * A property that has a node name. <br>
     * This must be possessed by an instance of Node.
     */
    public static final Property hasNodeName = Init.hasNodeName();

    /**
     * A property that has the full path of the node. <br>
     * This must be possessed by an instance of Node.
     */
    public static final Property hasFullPath = Init.hasFullPath();

    /**
     * A property that has the simple path of the node. <br>
     * This must be possessed by an instance of Node.
     */
    public static final Property hasSimplePath = Init.hasSimplePath();

    /**
     * A property that has optional enable / disable. <br>
     * This must be possessed by an instance of Node.
     */
    public static final Property isOptional = Init.isOptional();

    /**
     * A property that has the min value of cardinality. <br>
     * This must be possessed by an instance of Node.
     */
    public static final Property hasMin = Init.hasMin();

    /**
     * A property that has the max value of cardinality. <br>
     * This must be possessed by an instance of Node.
     */
    public static final Property hasMax = Init.hasMax();

    /**
     * A property that has validity / invalidity as a pattern element. <br>
     * This must be possessed by an instance of end Node.
     */
    public static final Property isChecked = Init.isChecked();

    /**
     * A property that represents a relationship to the root node. <br>
     * This must be possessed by an instance of FeatureModel.
     */
    public static final Property hasRootNode = Init.hasRootNode();

    /**
     * A property that represents a relationship to the child node. <br>
     * This must be possessed by an instance of Node.
     */
    public static final Property hasChildNode = Init.hasChildNode();

    /**
     * A property that represents the relationship the inclusion types of child nodes. <br>
     * This must be possessed by an instance of Node.<br>
     * However, it must have one or more associations with child nodes.
     */
    public static final Property hasChildType = Init.hasChildType();

    /**
     * A property that represents a relationship to a user property. <br>
     * This must be possessed by an instance of Node.
     */
    public static final Property hasUserProperty = Init.hasUserProperty();

    /**
     * A property that has a user property key. <br>
     * This must be possessed by an instance of UserProperty.
     */
    public static final Property hasKey = Init.hasKey();

    /**
     * A property that has a user property value. <br>
     * This must be possessed by an instance of UserProperty.
     */
    public static final Property hasValue = Init.hasValue();

    /**
     * A class that summarizes the initial processing methods of Ontology, OntClass, and Property.
     */
    public static class Init {

        /**
         * Create / Get ontology of feature pattern setting.
         * @return Ontology of feature pattern setting.
         */
        public static Ontology fpsOntology() {
            return FPSO.fpsOntology(GDNNS.FM.URI);
        }

        /**
         * Create / get a class that means feature pattern generation settings.
         * @return A class that means feature pattern generation settings.
         */
        public static OntClass FeaturePatternSetting() {
            OntClass fps = fpsClazz("FeaturePatternSetting");
            fps.addProperty(RDFS.subClassOf, GBO.File);
            return fps;
        }

        /**
         * Create / get a class that means one node in the feature model.
         * @return A class that means one node in the feature model.
         */
        public static OntClass Node() {
            return fpsClazz("Node");
        }

        /**
         * Create / get a class that means the user property that the node has.
         * @return A class that means the user property that the node has.
         */
        public static OntClass UserProperty() {
            return fpsClazz("UserProperty");
        }

        /**
         * Create / get a class that means the type of child node that the node has.
         * @return A class that means the type of child node that the node has.
         */
        public static OntClass ChildType() {
            return fpsClazz("ChildType");
        }

        /**
         * Create / Get a class that means that the type of child node that the node has is AND.
         * @return A class that means that the type of child node that the node has is AND.
         */
        public static OntClass ChildTypeAND() {
            OntClass and = fpsClazz("ChildTypeAND");
            and.addProperty(RDFS.subClassOf, FPSO.ChildType);
            return and;
        }

        /**
         * Create / Get a class that means that the type of child node that the node has is XOR.
         * @return A class that means that the type of child node that the node has is XOR.
         */
        public static OntClass ChildTypeXOR() {
            OntClass and = fpsClazz("ChildTypeXOR");
            and.addProperty(RDFS.subClassOf, FPSO.ChildType);
            return and;
        }

        /**
         * Create / Get a property that has a node name.
         * @return A property that has a node name.
         */
        public static Property hasNodeName() {
            Property p = fpsProperty("hasNodeName");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that has the full path of the node.
         * @return A property that has the full path of the node.
         */
        public static Property hasFullPath() {
            Property p = fpsProperty("hasFullPath");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that has the simple path of the node.
         * @return A property that has the simple path of the node.
         */
        public static Property hasSimplePath() {
            Property p = fpsProperty("hasSimplePath");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that has optional enable / disable.
         * @return A property that has optional enable / disable.
         */
        public static Property isOptional() {
            Property p = fpsProperty("isOptional");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that has the min value of cardinality.
         * @return A property that has the min value of cardinality.
         */
        public static Property hasMin() {
            Property p = fpsProperty("hasMin");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that has the max value of cardinality.
         * @return A property that has the max value of cardinality.
         */
        public static Property hasMax() {
            Property p = fpsProperty("hasMax");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that has validity / invalidity as a pattern element.
         * @return A property that has validity / invalidity as a pattern element.
         */
        public static Property isChecked() {
            Property p = fpsProperty("isChecked");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that represents a relationship to the root node.
         * @return A property that represents a relationship to the root node.
         */
        public static Property hasRootNode() {
            Property p = fpsProperty("hasRootNode");
            p.addProperty(RDF.type, OWL2.ObjectProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return p;
        }

        /**
         * Create / Get a property that represents a relationship to the child node.
         * @return A property that represents a relationship to the child node.
         */
        public static Property hasChildNode() {
            Property p = fpsProperty("hasChildNode");
            p.addProperty(RDF.type, OWL2.ObjectProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return p;
        }

        /**
         * Create / Get a property that represents the relationship the inclusion types of child nodes.
         * @return A property that represents the relationship the inclusion types of child nodes.
         */
        public static Property hasChildType() {
            Property p = fpsProperty("hasChildType");
            p.addProperty(RDF.type, OWL2.ObjectProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return p;
        }

        /**
         * Create / Get a property that represents a relationship to a user property.
         * @return A property that represents a relationship to a user property.
         */
        public static Property hasUserProperty() {
            Property p = fpsProperty("hasUserProperty");
            p.addProperty(RDF.type, OWL2.ObjectProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return p;
        }

        /**
         * Create / Get a property that has a user property key.
         * @return A property that has a user property key.
         */
        public static Property hasKey() {
            Property p = fpsProperty("hasKey");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that has a user property value.
         * @return A property that has a user property value.
         */
        public static Property hasValue() {
            Property p = fpsProperty("hasValue");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }
    }

    /**
     * The RDFS vocabulary, expressed for the SPI layer in terms of .graph Nodes.
     */
    @SuppressWarnings("hiding")
    public static class Nodes {

        /** Node of Ontology created by {@link Init#fpsOntology()}. */
        public static final Node fpsOntology = Init.fpsOntology().asNode();

        /** Node of OntClass created by {@link Init#FeaturePatternSetting()}. */
        public static final Node FeaturePatternSetting = Init.FeaturePatternSetting().asNode();

        /** Node of OntClass created by {@link Init#Node()}. */
        public static final Node Node = Init.Node().asNode();

        /** Node of OntClass created by {@link Init#UserProperty()}. */
        public static final Node UserProperty = Init.UserProperty().asNode();

        /** Node of OntClass created by {@link Init#ChildType()}. */
        public static final Node ChildType = Init.ChildType().asNode();

        /** Node of OntClass created by {@link Init#ChildTypeAND()}. */
        public static final Node ChildTypeAND = Init.ChildTypeAND().asNode();

        /** Node of OntClass created by {@link Init#ChildTypeXOR()}. */
        public static final Node ChildTypeXOR = Init.ChildTypeXOR().asNode();

        /** Node of Property created by {@link Init#hasNodeName()}. */
        public static final Node hasNodeName = Init.hasNodeName().asNode();

        /** Node of Property created by {@link Init#hasFullPath()}. */
        public static final Node hasFullPath = Init.hasFullPath().asNode();

        /** Node of Property created by {@link Init#hasSimplePath()}. */
        public static final Node hasSimplePath = Init.hasSimplePath().asNode();

        /** Node of Property created by {@link Init#isOptional()}. */
        public static final Node isOptional = Init.isOptional().asNode();

        /** Node of Property created by {@link Init#hasMin()}. */
        public static final Node hasMin = Init.hasMin().asNode();

        /** Node of Property created by {@link Init#hasMax()}. */
        public static final Node hasMax = Init.hasMax().asNode();

        /** Node of Property created by {@link Init#isChecked()}. */
        public static final Node isChecked = Init.isChecked().asNode();

        /** Node of Property created by {@link Init#hasRootNode()}. */
        public static final Node hasRootNode = Init.hasRootNode().asNode();

        /** Node of Property created by {@link Init#hasChildNode()}. */
        public static final Node hasChildNode = Init.hasChildNode().asNode();

        /** Node of Property created by {@link Init#hasChildType()}. */
        public static final Node hasChildType = Init.hasChildType().asNode();

        /** Node of Property created by {@link Init#isOptional()}. */
        public static final Node hasp = Init.isOptional().asNode();

        /** Node of Property created by {@link Init#hasUserProperty()}. */
        public static final Node hasUserProperty = Init.hasUserProperty().asNode();

        /** Node of Property created by {@link Init#hasKey()}. */
        public static final Node hasKey = Init.hasKey().asNode();

        /** Node of Property created by {@link Init#hasValue()}. */
        public static final Node hasValue = Init.hasValue().asNode();
    }
}
