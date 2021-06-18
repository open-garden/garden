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
 * Feature Pattern Ontology
 */
public class FPO extends GBO {

    /** Feature pattern extension name on the URI */
    public static final String FP_NAME = "FP";

    /** Used in URI to identify pattern. */
    public static final String PATTERN_NAME = "Pattern";

    /**
     * Creates an feature pattern ontology based on the specified URI.
     * @param local specified URI
     * @return Feature pattern ontology
     */
    protected static final Ontology fpOntology(String local) {
        return ModelFactory.createOntologyModel().createOntology(local);
    }

    /**
     * Create an OntClass based on the FP namespace and arguments.
     * @param local String after the FP namespace
     * @return OntClass
     */
    protected static final OntClass fpClazz(String local) {
        return ModelFactory.createOntologyModel().createClass(GDNNS.FP.NS + local);
    }

    /**
     * Create an Resource based on the FP namespace and arguments.
     * @param local String after the FP namespace
     * @return Resource
     */
    protected static final Resource fpResource(String local) {
        return ModelFactory.createOntologyModel().createOntResource(GDNNS.FP.NS + local);
    }

    /**
     * Create an DatatypeProperty based on the FP namespace and arguments.
     * @param local String after the FP namespace
     * @return DatatypeProperty
     */
    protected static final Property fpProperty(String local) {
        return ModelFactory.createOntologyModel().createDatatypeProperty(GDNNS.FP.NS + local);
    }

    /**
     * Create an ObjectProperty based on the FP namespace and arguments.
     * @param local String after the FP namespace
     * @return ObjectProperty
     */
    protected static final Property fpObjProperty(String local) {
        return ModelFactory.createOntologyModel().createObjectProperty(GDNNS.FP.NS + local);
    }

    /** Ontology of feature pattern. */
    public static final Ontology fpOntology = Init.fpOntology();

    /** Means a set of feature patterns. */
    public static final OntClass FeaturePatternSet = Init.FeaturePatternSet();

    /** Means a feature pattern. */
    public static final OntClass FeaturePattern = Init.FeaturePattern();

    /** Means an element of a feature pattern. */
    public static final OntClass PatternElement = Init.PatternElement();

    /**
     * A property that has a pattern number. <br>
     * This must be possessed by an instance of FeaturePattern.
     */
    public static final Property hasPatternNo = Init.hasPatternNo();

    /**
     * A property that has a pattern name. <br>
     * This must be possessed by an instance of FeaturePattern.
     */
    public static final Property hasPatternName = Init.hasPatternName();

    /**
     * A property that has element name for the feature pattern. <br>
     * This must be possessed by an instance of PatternElement.
     */
    public static final Property hasPatternElementName = Init.hasPatternElementName();

    /**
     * A property that has element value ​​for the feature pattern. <br>
     * This must be possessed by an instance of PatternElement.
     */
    public static final Property hasPatternElementValue = Init.hasPatternElementValue();

    /**
     * A property that represents a relationship to the feature pattern generation settings.<br>
     * This must be possessed by an instance of FeaturePatternSet.
     */
    public static final Property refSettingFile = Init.refSettingFile();

    /**
     * A property that represents a relationship to the feature pattern.<br>
     * This must be possessed by an instance of FeaturePatternSet.
     */
    public static final Property hasPattern = Init.hasPattern();

    /**
     * A property that represents a relationship of nodes in a feature model.<br>
     * This must be possessed by an instance of FeaturePattern.
     */
    public static final Property refNode = Init.refNode();

    /**
     * A property that represents a relationship of elements in a feature pattern.<br>
     * This must be possessed by an instance of FeaturePattern.
     */
    public static final Property hasPatternElement = Init.hasPatternElement();

    /**
     * A class that summarizes the initial processing methods of Ontology, OntClass, and Property.
     */
    public static class Init {

        /**
         * Create / Get ontology of feature pattern.
         * @return Ontology of feature pattern.
         */
        public static Ontology fpOntology() {
            return FPO.fpOntology(GDNNS.FM.URI);
        }

        /**
         * Create / get a class that means a set of feature patterns.
         * @return A class that means a set of feature patterns.
         */
        public static OntClass FeaturePatternSet() {
            return fpClazz("FeaturePatternSet");
        }

        /**
         * Create / get a class that means a feature pattern.
         * @return A class that means a feature pattern.
         */
        public static OntClass FeaturePattern() {
            return fpClazz("FeaturePattern");
        }

        /**
         * Create / Get a class that means an element of a feature pattern.
         * @return A class that means an element of a feature pattern.
         */
        public static OntClass PatternElement() {
            return fpClazz("PatternElement");
        }

        /**
         * Create / Get a property that has a pattern number.
         * @return A property that has a pattern number.
         */
        public static Property hasPatternNo() {
            Property p = fpProperty("hasPatternNo");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that has a pattern name.
         * @return A property that has a pattern name.
         */
        public static Property hasPatternName() {
            Property p = fpProperty("hasPatternName");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that has element name ​​for the feature pattern.
         * @return A property that has element name ​​for the feature pattern.
         */
        public static Property hasPatternElementName() {
            Property p = fpProperty("hasPatternElementName");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that has element value ​​for the feature pattern.
         * @return A property that has element value ​​for the feature pattern.
         */
        public static Property hasPatternElementValue() {
            Property p = fpProperty("hasPatternElementValue");
            p.addProperty(RDF.type, OWL2.DatatypeProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that represents a relationship to the feature pattern generation settings.
         * @return A property that represents a relationship to the feature pattern generation settings.
         */
        public static Property refSettingFile() {
            Property p = fpProperty("refSettingFile");
            p.addProperty(RDF.type, OWL2.ObjectProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return p;
        }

        /**
         * Create / Get a property that represents a relationship to the feature pattern.
         * @return A property that represents a relationship to the feature pattern.
         */
        public static Property hasPattern() {
            Property p = fpProperty("hasPattern");
            p.addProperty(RDF.type, OWL2.ObjectProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return p;
        }

        /**
         * Create / Get a property that represents a relationship of nodes in a feature model.
         * @return A property that represents a relationship of nodes in a feature model.
         */
        public static Property refNode() {
            Property p = fpProperty("refNode");
            p.addProperty(RDF.type, OWL2.ObjectProperty);
            p.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return p;
        }

        /**
         * Create / Get a property that represents a relationship of elements in a feature pattern.
         * @return A property that represents a relationship of elements in a feature pattern.
         */
        public static Property hasPatternElement() {
            Property p = fpProperty("hasPatternElement");
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

        /** Node of Ontology created by {@link Init#fpOntology()}. */
        public static final Node fpOntology = Init.fpOntology().asNode();

        /** Node of OntClass created by {@link Init#FeaturePatternSet()}. */
        public static final Node FeaturePatternSet = Init.FeaturePatternSet().asNode();

        /** Node of OntClass created by {@link Init#FeaturePattern()}. */
        public static final Node FeaturePattern = Init.FeaturePattern().asNode();

        /** Node of OntClass created by {@link Init#PatternElement()}. */
        public static final Node PatternElement = Init.PatternElement().asNode();

        /** Node of Property created by {@link Init#hasPatternNo()}. */
        public static final Node hasPatternNo = Init.hasPatternNo().asNode();

        /** Node of Property created by {@link Init#hasPatternName()}. */
        public static final Node hasPatternName = Init.hasPatternName().asNode();

        /** Node of Property created by {@link Init#hasPatternElementName()}. */
        public static final Node hasPatternElementName = Init.hasPatternElementName().asNode();

        /** Node of Property created by {@link Init#hasPatternElementValue()}. */
        public static final Node hasPatternElementValue = Init.hasPatternElementValue().asNode();

        /** Node of Property created by {@link Init#refSettingFile()}. */
        public static final Node refSettingFile = Init.refSettingFile().asNode();

        /** Node of Property created by {@link Init#hasPattern()}. */
        public static final Node hasPattern = Init.hasPattern().asNode();

        /** Node of Property created by {@link Init#refNode()}. */
        public static final Node refNode = Init.refNode().asNode();

        /** Node of Property created by {@link Init#hasPatternElement()}. */
        public static final Node hasPatternElement = Init.hasPatternElement().asNode();
    }
}
