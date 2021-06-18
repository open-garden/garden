package com.zipc.garden.webplatform.dao.rdf.ontology;

import org.apache.jena.graph.Node;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

/**
 * Behavior Pattern Ontology
 */
public class BPO extends GBO {

    /** Behavior pattern extension name on the URI */
    public static final String BP_NAME = "BP";

    /**
     * Creates an behavior pattern ontology based on the specified URI.
     * @param local specified URI
     * @return Behavior pattern Ontology
     */
    protected static final Ontology bpOntology(String local) {
        return ModelFactory.createOntologyModel().createOntology(local);
    }

    /**
     * Create an OntClass based on the BP namespace and arguments.
     * @param local String after the BP namespace
     * @return OntClass
     */
    protected static final OntClass bpClazz(String local) {
        return ModelFactory.createOntologyModel().createClass(GDNNS.BP.NS + local);
    }

    /**
     * Create an DatatypeProperty based on the BP namespace and arguments.
     * @param local String after the BP namespace
     * @return A DatatypeProperty resource.
     */
    protected static final Property bpProperty(String local) {
        return ModelFactory.createOntologyModel().createDatatypeProperty(GDNNS.BP.NS + local);
    }

    /** Ontology of behavior pattern. */
    public static final Ontology bpOntology = Init.bpOntology();

    /** OntClass which means behavior pattern file. */
    public static final OntClass BehaviorPatternSet = Init.BehaviorPatternSet();

    /** A property that represents the association with the behavior pattern generation settings. */
    public static final Property refSettingFile = Init.refSettingFile();

    /** A property with a behavior pattern. */
    public static final Property hasPattern = Init.hasPattern();

    /** OntClass which means the behavior pattern. */
    public static final OntClass BehaviorPattern = Init.BehaviorPattern();

    /** Property that has the pattern number. */
    public static final Property hasPatternNo = Init.hasPatternNo();

    /** Property that has a behavior. */
    public static final Property hasBehavior = Init.hasBehavior();

    /** OntClass that represents a behavior model, including behavior pattern. */
    public static final OntClass Behavior = Init.Behavior();

    /** A reference property to the behavior model instance of the architecture on which the behavior is based. */
    public static final Property stateMachineInstance = Init.stateMachineInstance();

    /** Property that has step. */
    public static final Property hasStep = Init.hasStep();

    /** This OntClass means the occurrence and transition of events. */
    public static final OntClass Step = Init.Step();

    /** Property that has step number. */
    public static final Property hasStepNo = Init.hasStepNo();

    /** Property that has a state. */
    public static final Property hasState = Init.hasState();

    /** Property that has a transition. Get information about the event from the transition. */
    public static final Property hasTransition = Init.hasTransition();

    /** Property that has the next step. */
    public static final Property hasNext = Init.hasNext();

    /**
     * A class that summarizes the initial processing methods of Ontology, OntClass, and Property.
     */
    public static class Init {

        /**
         * Create / Get Ontology of behavior pattern.
         * @return Ontology of behavior pattern.
         */
        public static Ontology bpOntology() {
            return BPO.bpOntology(GDNNS.BP.URI);
        }

        /**
         * Create / get OntClass which means behavior pattern file.
         * @return OntClass which means behavior pattern file.
         */
        public static OntClass BehaviorPatternSet() {
            OntClass bpSet = bpClazz("BehaviorPatternSet");
            return bpSet;
        }

        /**
         * Create / Get a property that represents the association with the behavior pattern generation settings.
         * @return A property that represents the association with the behavior pattern generation settings.
         */
        public static Property refSettingFile() {
            Property prop = bpProperty("refSettingFile");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get a property with a behavior pattern.
         * @return A property with a behavior pattern.
         */
        public static Property hasPattern() {
            Property prop = bpProperty("hasPattern");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get OntClass which means the behavior pattern.
         * @return OntClass which means the behavior pattern.
         */
        public static OntClass BehaviorPattern() {
            OntClass bp = bpClazz("BehaviorPattern");
            return bp;
        }

        /**
         * Create / Get a property that has the pattern number.
         * @return Property that has the pattern number.
         */
        public static Property hasPatternNo() {
            Property prop = bpProperty("hasPatternNo");
            prop.addProperty(RDF.type, OWL2.DatatypeProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return prop;
        }

        /**
         * Create / Get a property that has a behavior.
         * @return Property that has a behavior.
         */
        public static Property hasBehavior() {
            Property prop = bpProperty("hasBehavior");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get a OntClass that represents a behavior model, including behavior pattern.
         * @return OntClass that represents a behavior model, including behavior pattern.
         */
        public static OntClass Behavior() {
            OntClass behavior = bpClazz("Behavior");
            return behavior;
        }

        /**
         * Create / Get a reference property to the behavior model instance of the architecture on which the behavior is based.
         * @return A reference property to the behavior model instance of the architecture on which the behavior is based.
         */
        public static Property stateMachineInstance() {
            Property prop = bpProperty("stateMachineInstance");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get a property that has step.
         * @return Property that has step.
         */
        public static Property hasStep() {
            Property prop = bpProperty("hasStep");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get OntClass that means the occurrence and transition of events.
         * @return OntClass that means the occurrence and transition of events.
         */
        public static OntClass Step() {
            OntClass step = bpClazz("Step");
            return step;
        }

        /**
         * Create / Get a property that has step number.
         * @return Property that has step number
         */
        public static Property hasStepNo() {
            Property prop = bpProperty("hasStepNo");
            prop.addProperty(RDF.type, OWL2.DatatypeProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return prop;
        }

        /**
         * Create / Get a property that has a state.
         * @return Property that has a state.
         */
        public static Property hasState() {
            Property prop = bpProperty("hasState");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get a property that has a transition.
         * @return Property that has a transition.
         */
        public static Property hasTransition() {
            Property prop = bpProperty("hasTransition");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get a property that has the next step.
         * @return Property that has the next step.
         */
        public static Property hasNext() {
            Property prop = bpProperty("hasNext");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }
    }

    /**
     * The RDFS vocabulary, expressed for the SPI layer in terms of .graph Nodes.
     */
    public static class Nodes {

        /** Node of Ontology created by {@link Init#bpOntology()}. */
        public static final Node bpOntology = Init.bpOntology().asNode();

        /** Node of OntClass created by {@link Init#BehaviorPatternSet()}. */
        public static final Node BehaviorPatternSet = Init.BehaviorPatternSet().asNode();

        /** Node of property created by {@link Init#refSettingFile()}. */
        public static final Node refSettingFile = Init.refSettingFile().asNode();

        /** Node of property created by {@link Init#hasPattern()}. */
        public static final Node hasPattern = Init.hasPattern().asNode();

        /** Node of OntClass created by {@link Init#BehaviorPattern()}. */
        public static final Node BehaviorPattern = Init.BehaviorPattern().asNode();

        /** Node of OntClass created by {@link Init#Behavior()}. */
        public static final Node Behavior = Init.Behavior().asNode();

        /** Node of property created by {@link Init#stateMachineInstance()}. */
        public static final Node stateMachineInstance = Init.stateMachineInstance().asNode();

        /** Node of property created by {@link Init#hasStep()}. */
        public static final Node hasStep = Init.hasStep().asNode();

        /** Node of OntClass created by {@link Init#Step()}. */
        public static final Node Step = Init.Step().asNode();

        /** Node of property created by {@link Init#hasStepNo()}. */
        public static final Node hasStepNo = Init.hasStepNo().asNode();

        /** Node of property created by {@link Init#hasState()}. */
        public static final Node hasState = Init.hasState().asNode();

        /** Node of property created by {@link Init#hasTransition()}. */
        public static final Node hasTransition = Init.hasTransition().asNode();

        /** Node of property created by {@link Init#hasNext()}. */
        public static final Node hasNext = Init.hasNext().asNode();

    }
}
