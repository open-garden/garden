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
 * Behavior Pattern Setting Ontology
 */
public class BPSO extends GBO {

    /** Behavior pattern setting extension name on the URI */
    public static final String BPS_NAME = "BPS";

    /**
     * Creates an behavior pattern setting ontology based on the specified URI.
     * @param local specified URI
     * @return Behavior pattern setting Ontology
     */
    protected static final Ontology bpsOntology(String local) {
        return ModelFactory.createOntologyModel().createOntology(local);
    }

    /**
     * Create an OntClass based on the BPS namespace and arguments.
     * @param local String after the BPS namespace
     * @return OntClass
     */
    protected static final OntClass bpsClazz(String local) {
        return ModelFactory.createOntologyModel().createClass(GDNNS.BPS.NS + local);
    }

    /**
     * Create an DatatypeProperty based on the BPS namespace and arguments.
     * @param local String after the BPS namespace
     * @return A DatatypeProperty resource.
     */
    protected static final Property bpsProperty(String local) {
        return ModelFactory.createOntologyModel().createDatatypeProperty(GDNNS.BPS.NS + local);
    }

    /** Ontology of behavior pattern setting. */
    public static final Ontology bpsOntology = Init.bpsOntology();

    /** A property that has validity / invalidity as a pattern element. */
    public static final Property isEnabled = Init.isEnabled();

    /** A property that has an instance ID. */
    public static final Property hasOriginalUuid = Init.hasOriginalUuid();

    /** A property that has an instance name. */
    public static final Property hasOriginalName = Init.hasOriginalName();

    /** A property that has a transition source. */
    public static final Property hasSource = Init.hasSource();

    /** A property that has a transition target. */
    public static final Property hasTarget = Init.hasTarget();

    /** OntClass of the behavior pattern generation setting file. */
    public static final OntClass BehaviorPatternSetting = Init.BehaviorPatternSetting();

    /** A property that has an architecture model instance. */
    public static final Property hasArc = Init.hasArc();

    /** OntClass of architecture model instance. */
    public static final OntClass ArcInstance = Init.ArcInstance();

    /** A property that has a behavior model instance. */
    public static final Property hasBehavior = Init.hasBehavior();

    /** A property that has a data flow. */
    public static final Property hasDataflow = Init.hasDataflow();

    /** OntClass of behavior model instance. */
    public static final OntClass BehaviorInstance = Init.BehaviorInstance();

    /** A property with a priority value. */
    public static final Property hasEvalPriority = Init.hasEvalPriority();

    /** A property that references the initial state. */
    public static final Property hasInitialState = Init.hasInitialState();

    /** A property that has a state. */
    public static final Property hasState = Init.hasState();

    /** A property that has a variable. */
    public static final Property hasVariable = Init.hasVariable();

    /** OntClass which means one state model in the behavior model. */
    public static final OntClass State = Init.State();

    /** A property that has a state that represents the type of state. */
    public static final Property hasStateType = Init.hasStateType();

    /** A property that has a transition. */
    public static final Property hasTransition = Init.hasTransition();

    /** OntClass which means the type of state. */
    public static final OntClass StateType = Init.StateType();

    /** OntClass which means the type of state is None. */
    public static final OntClass StateTypeNone = Init.StateTypeNone();

    /** OntClass which means the type of state is Join. */
    public static final OntClass StateTypeJoin = Init.StateTypeJoin();

    /** OntClass which means the type of state is Fork. */
    public static final OntClass StateTypeFork = Init.StateTypeFork();

    /** OntClass which means the type of state is Junction. */
    public static final OntClass StateTypeJunction = Init.StateTypeJunction();

    /** OntClass which means the type of state is Choice. */
    public static final OntClass StateTypeChoice = Init.StateTypeChoice();

    /** OntClass which means the type of state is EntryPoint. */
    public static final OntClass StateTypeEntryPoint = Init.StateTypeEntryPoint();

    /** OntClass which means the type of state is ExitPoint. */
    public static final OntClass StateTypeExitPoint = Init.StateTypeExitPoint();

    /** OntClass which means the type of state is Terminate . */
    public static final OntClass StateTypeTerminate = Init.StateTypeTerminate();

    /** OntClass which means the type of state is Initial. */
    public static final OntClass StateTypeInitial = Init.StateTypeInitial();

    /** OntClass which means the type of state is Final. */
    public static final OntClass StateTypeFinal = Init.StateTypeFinal();

    /** OntClass which means the type of state is DeepHistory. */
    public static final OntClass StateTypeDeepHistory = Init.StateTypeDeepHistory();

    /** OntClass which means the type of state is ShallowHistory. */
    public static final OntClass StateTypeShallowHistory = Init.StateTypeShallowHistory();

    /** OntClass which means one transition model in the state model. */
    public static final OntClass Transition = Init.Transition();

    /** A property that has a priority of transition. */
    public static final Property hasPriority = Init.hasPriority();

    /** A property that has a trigger condition of transition event. */
    public static final Property hasTrigger = Init.hasTrigger();

    /** A property that has a string of transition event. */
    public static final Property hasEvent = Init.hasEvent();

    /** A property that has a string of transition condition. */
    public static final Property hasCondition = Init.hasCondition();

    /** A property that has a string of transition action. */
    public static final Property hasAction = Init.hasAction();

    /** OntClass which means one variable model in the behavior model. */
    public static final OntClass Variable = Init.Variable();

    /** A property with a variable name. */
    public static final Property hasName = Init.hasName();

    /** A property that has a variable type. */
    public static final Property hasType = Init.hasType();

    /** OntClass which means the one transition model in the architectural model. */
    public static final OntClass Dataflow = Init.Dataflow();

    /**
     * A class that summarizes the initial processing methods of Ontology, OntClass, and Property.
     */
    public static class Init {

        /**
         * Create / Get Ontology of behavior pattern setting.
         * @return Ontology of behavior pattern setting.
         */
        public static Ontology bpsOntology() {
            return BPSO.bpsOntology(GDNNS.BPS.URI);
        }

        /**
         * Create / Get a property that has validity / invalidity as a pattern element.
         * @return A property that has validity / invalidity as a pattern element.
         */
        public static Property isEnabled() {
            Property prop = bpsProperty("isEnabled");
            prop.addProperty(RDF.type, OWL2.DatatypeProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return prop;
        }

        /**
         * Craete / Get a property that has an instance ID.
         * @return A property that has an instance ID.
         */
        public static Property hasOriginalUuid() {
            Property prop = bpsProperty("hasOriginalUuid");
            prop.addProperty(RDF.type, OWL2.DatatypeProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return prop;
        }

        /**
         * Craete / Get a property that has an instance name.
         * @return A property that has an instance name.
         */
        public static Property hasOriginalName() {
            Property prop = bpsProperty("hasOriginalName");
            prop.addProperty(RDF.type, OWL2.DatatypeProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return prop;
        }

        /**
         * Create / Get a property that has a transition source.
         * @return A property that has a transition source.
         */
        public static Property hasSource() {
            Property prop = bpsProperty("hasSource");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get a property that has a transition target.
         * @return A property that has a transition target.
         */
        public static Property hasTarget() {
            Property prop = bpsProperty("hasTarget");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get OntClass of the behavior pattern generation setting file.
         * @return OntClass of the behavior pattern generation setting file.
         */
        public static OntClass BehaviorPatternSetting() {
            OntClass bps = bpsClazz("BehaviorPatternSetting");
            bps.addProperty(RDFS.subClassOf, GBO.File);
            return bps;
        }

        /**
         * Create / Get a property that has an architecture model instance.
         * @return A property that has an architecture model instance.
         */
        public static Property hasArc() {
            Property prop = bpsProperty("hasArc");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get OntClass of architecture model instance.
         * @return OntClass of architecture model instance.
         */
        public static OntClass ArcInstance() {
            OntClass arc = bpsClazz("ArcInstance");
            return arc;
        }

        /**
         * Create / Get a property that has a behavior model instance.
         * @return A property that has a behavior model instance.
         */
        public static Property hasBehavior() {
            Property prop = bpsProperty("hasBehavior");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get a property that has a data flow.
         * @return A property that has a data flow.
         */
        public static Property hasDataflow() {
            Property prop = bpsProperty("hasDataflow");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get OntClass of behavior model instance.
         * @return OntClass of behavior model instance.
         */
        public static OntClass BehaviorInstance() {
            OntClass behavior = bpsClazz("BehaviorInstance");
            return behavior;
        }

        /**
         * Create / Get a property with a priority value.
         * @return A property with a priority value.
         */
        public static Property hasEvalPriority() {
            Property prop = bpsProperty("hasEvalPriority");
            prop.addProperty(RDF.type, OWL2.DatatypeProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return prop;
        }

        /**
         * Create / Get a property that references the initial state.
         * @return A property that references the initial state.
         */
        public static Property hasInitialState() {
            Property prop = bpsProperty("hasInitialState");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get a property that has a state.
         * @return A property that has a state.
         */
        public static Property hasState() {
            Property prop = bpsProperty("hasState");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get a property that has a variable.
         * @return A property that has a variable.
         */
        public static Property hasVariable() {
            Property prop = bpsProperty("hasVariable");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get OntClass which means one state model in the behavior model.
         * @return OntClass which means one state model in the behavior model.
         */
        public static OntClass State() {
            OntClass state = bpsClazz("State");
            return state;
        }

        /**
         * Create / Get a property that has a state that represents the type of state.
         * @return A property that has a state that represents the type of state.
         */
        public static Property hasStateType() {
            Property prop = bpsProperty("hasStateType");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / get a property with a transition.
         * @return A property that has a transition.
         */
        public static Property hasTransition() {
            Property prop = bpsProperty("hasTransition");
            prop.addProperty(RDF.type, OWL2.ObjectProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return prop;
        }

        /**
         * Create / Get OntClass which means the type of state.
         * @return OntClass which means the type of state.
         */
        public static OntClass StateType() {
            OntClass stateType = bpsClazz("PseudoStateType");
            return stateType;
        }

        /**
         * Create / Get OntClass which means the type of state is None.
         * @return OntClass which means the type of state is None.
         */
        public static OntClass StateTypeNone() {
            OntClass stateTypeNone = bpsClazz("PseudoStateTypeNone");
            stateTypeNone.addProperty(RDFS.subClassOf, BPSO.StateType);
            return stateTypeNone;
        }

        /**
         * Create / Get OntClass which means the type of state is Join.
         * @return OntClass which means the type of state is Join.
         */
        public static OntClass StateTypeJoin() {
            OntClass stateTypeJoin = bpsClazz("PseudoStateTypeJoin");
            stateTypeJoin.addProperty(RDFS.subClassOf, BPSO.StateType);
            return stateTypeJoin;
        }

        /**
         * Create / Get OntClass which means the type of state is Fork.
         * @return OntClass which means the type of state is Fork.
         */
        public static OntClass StateTypeFork() {
            OntClass stateTypeFork = bpsClazz("PseudoStateTypeFork");
            stateTypeFork.addProperty(RDFS.subClassOf, BPSO.StateType);
            return stateTypeFork;
        }

        /**
         * Create / Get OntClass which means the type of state is Junction.
         * @return OntClass which means the type of state is Junction.
         */
        public static OntClass StateTypeJunction() {
            OntClass stateTypeJunction = bpsClazz("PseudoStateTypeJunction");
            stateTypeJunction.addProperty(RDFS.subClassOf, BPSO.StateType);
            return stateTypeJunction;
        }

        /**
         * Create / Get OntClass which means the type of state is Choice.
         * @return OntClass which means the type of state is Choice.
         */
        public static OntClass StateTypeChoice() {
            OntClass stateTypeChoice = bpsClazz("PseudoStateTypeChoice");
            stateTypeChoice.addProperty(RDFS.subClassOf, BPSO.StateType);
            return stateTypeChoice;
        }

        /**
         * Create / Get OntClass which means the type of state is EntryPoint.
         * @return OntClass which means the type of state is EntryPoint.
         */
        public static OntClass StateTypeEntryPoint() {
            OntClass stateTypeEntryPoint = bpsClazz("PseudoStateTypeEntryPoint");
            stateTypeEntryPoint.addProperty(RDFS.subClassOf, BPSO.StateType);
            return stateTypeEntryPoint;
        }

        /**
         * Create / Get OntClass which means the type of state is ExitPoint.
         * @return OntClass which means the type of state is ExitPoint.
         */
        public static OntClass StateTypeExitPoint() {
            OntClass stateTypeExitPoint = bpsClazz("PseudoStateTypeExitPoint");
            stateTypeExitPoint.addProperty(RDFS.subClassOf, BPSO.StateType);
            return stateTypeExitPoint;
        }

        /**
         * Create / Get OntClass which means the type of state is Terminate.
         * @return OntClass which means the type of state is Terminate.
         */
        public static OntClass StateTypeTerminate() {
            OntClass stateTypeTerminate = bpsClazz("PseudoStateTypeTerminate");
            stateTypeTerminate.addProperty(RDFS.subClassOf, BPSO.StateType);
            return stateTypeTerminate;
        }

        /**
         * Create / Get OntClass which means the type of state is Initial.
         * @return OntClass which means the type of state is Initial.
         */
        public static OntClass StateTypeInitial() {
            OntClass stateTypeInitial = bpsClazz("PseudoStateTypeInitial");
            stateTypeInitial.addProperty(RDFS.subClassOf, BPSO.StateType);
            return stateTypeInitial;
        }

        /**
         * Create / Get OntClass which means the type of state is Final.
         * @return OntClass which means the type of state is Final.
         */
        public static OntClass StateTypeFinal() {
            OntClass stateTypeFinal = bpsClazz("PseudoStateTypeFinal");
            stateTypeFinal.addProperty(RDFS.subClassOf, BPSO.StateType);
            return stateTypeFinal;
        }

        /**
         * Create / Get OntClass which means the type of state is DeepHistory.
         * @return OntClass which means the type of state is DeepHistory.
         */
        public static OntClass StateTypeDeepHistory() {
            OntClass stateTypeDeepHistory = bpsClazz("PseudoStateTypeDeepHistory");
            stateTypeDeepHistory.addProperty(RDFS.subClassOf, BPSO.StateType);
            return stateTypeDeepHistory;
        }

        /**
         * Create / Get OntClass which means the type of state is ShallowHistory.
         * @return OntClass which means the type of state is ShallowHistory.
         */
        public static OntClass StateTypeShallowHistory() {
            OntClass stateTypeShallowHistory = bpsClazz("PseudoStateTypeShallowHistory");
            stateTypeShallowHistory.addProperty(RDFS.subClassOf, BPSO.StateType);
            return stateTypeShallowHistory;
        }

        /**
         * Create / Get OntClass which means one transition model in the state model.
         * @return OntClass which means one transition model in the state model.
         */
        public static OntClass Transition() {
            OntClass transition = bpsClazz("Transition");
            return transition;
        }

        /**
         * Create / Get a property that has a priority of transition.
         * @return A property that has a priority of transition.
         */
        public static Property hasPriority() {
            Property prop = bpsProperty("hasPriority");
            prop.addProperty(RDF.type, OWL2.DatatypeProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return prop;
        }

        /**
         * Create / Get a property that has a trigger condition of transition event.
         * @return A property that has a trigger condition of transition event.
         */
        public static Property hasTrigger() {
            Property prop = bpsProperty("hasTrigger");
            prop.addProperty(RDF.type, OWL2.DatatypeProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return prop;
        }

        /**
         * Create / get a property that has a string of transition event.
         * @return A property that has a string of transition event.
         */
        public static Property hasEvent() {
            Property prop = bpsProperty("hasEvent");
            prop.addProperty(RDF.type, OWL2.DatatypeProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return prop;
        }

        /**
         * Create / Get a property that has a string of transition condition.
         * @return A property that has a string of transition condition.
         */
        public static Property hasCondition() {
            Property prop = bpsProperty("hasCondition");
            prop.addProperty(RDF.type, OWL2.DatatypeProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return prop;
        }

        /**
         * Create / Get a property that has a string of transition action.
         * @return A property that has a string of transition action.
         */
        public static Property hasAction() {
            Property prop = bpsProperty("hasAction");
            prop.addProperty(RDF.type, OWL2.DatatypeProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return prop;
        }

        /**
         * Create / Get OntClass which means one variable model in the behavior model.
         * @return OntClass which means one variable model in the behavior model.
         */
        public static OntClass Variable() {
            OntClass variable = bpsClazz("Variable");
            return variable;
        }

        /**
         * Create / Get A property with a variable name.
         * @return A property with a variable name.
         */
        public static Property hasName() {
            Property prop = bpsProperty("hasName");
            prop.addProperty(RDF.type, OWL2.DatatypeProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return prop;
        }

        /**
         * Create / Get a property that has a variable type.
         * @return A property that has a variable type.
         */
        public static Property hasType() {
            Property prop = bpsProperty("hasType");
            prop.addProperty(RDF.type, OWL2.DatatypeProperty);
            prop.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return prop;
        }

        /**
         * Create and get OntClass, which means one transition model of the architectural model.
         * @return OntClass which means the one transition model in the architectural model.
         */
        public static OntClass Dataflow() {
            OntClass dataflow = bpsClazz("Dataflow");
            return dataflow;
        }
    }

    /**
     * The RDFS vocabulary, expressed for the SPI layer in terms of .graph Nodes.
     */
    public static class Nodes {

        /** Node of Ontology created by {@link Init#bpsOntology()}. */
        public static final Node bpsOntology = Init.bpsOntology().asNode();

        /** Node of property created by {@link Init#isEnabled()}. */
        public static final Node isEnabled = Init.isEnabled().asNode();

        /** Node of property created by {@link Init#hasOriginalUuid()}. */
        public static final Node hasOriginalUuid = Init.hasOriginalUuid().asNode();

        /** Node of property created by {@link Init#hasOriginalName()}. */
        public static final Node hasOriginalName = Init.hasOriginalName().asNode();

        /** Node of property created by {@link Init#hasSource()}. */
        public static final Node hasSource = Init.hasSource().asNode();

        /** Node of property created by {@link Init#hasTarget()}. */
        public static final Node hasTarget = Init.hasTarget().asNode();

        /** Node of OntClass created by {@link Init#BehaviorPatternSetting()}. */
        public static final Node BehaviorPatternSetting = Init.BehaviorPatternSetting().asNode();

        /** Node of property created by {@link Init#hasArc()}. */
        public static final Node hasArc = Init.hasArc().asNode();

        /** Node of OntClass created by {@link Init#ArcInstance()}. */
        public static final Node ArcInstance = Init.ArcInstance().asNode();

        /** Node of property created by {@link Init#hasBehavior()}. */
        public static final Node hasBehavior = Init.hasBehavior().asNode();

        /** Node of property created by {@link Init#hasDataflow()}. */
        public static final Node hasDataflow = Init.hasDataflow().asNode();

        /** Node of OntClass created by {@link Init#BehaviorInstance()}. */
        public static final Node BehaviorInstance = Init.BehaviorInstance().asNode();

        /** Node of property created by {@link Init#hasEvalPriority()}. */
        public static final Node hasEvalPriority = Init.hasEvalPriority().asNode();

        /** Node of property created by {@link Init#hasInitialState()}. */
        public static final Node hasInitialState = Init.hasInitialState().asNode();

        /** Node of property created by {@link Init#hasState()}. */
        public static final Node hasState = Init.hasState().asNode();

        /** Node of property created by {@link Init#hasVariable()}. */
        public static final Node hasVariable = Init.hasVariable().asNode();

        /** Node of OntClass created by {@link Init#State()}. */
        public static final Node State = Init.State().asNode();

        /** Node of property created by {@link Init#hasStateType()}. */
        public static final Node hasStateType = Init.hasStateType().asNode();

        /** Node of property created by {@link Init#hasTransition()}. */
        public static final Node hasTransition = Init.hasTransition().asNode();

        /** Node of OntClass created by {@link Init#StateType()}. */
        public static final Node StateType = Init.StateType().asNode();

        /** Node of OntClass created by {@link Init#StateTypeNone()}. */
        public static final Node StateTypeNone = Init.StateTypeNone().asNode();

        /** Node of OntClass created by {@link Init#StateTypeJoin()}. */
        public static final Node StateTypeJoin = Init.StateTypeJoin().asNode();

        /** Node of OntClass created by {@link Init#StateTypeFork()}. */
        public static final Node StateTypeFork = Init.StateTypeFork().asNode();

        /** Node of OntClass created by {@link Init#StateTypeJunction()}. */
        public static final Node StateTypeJunction = Init.StateTypeJunction().asNode();

        /** Node of OntClass created by {@link Init#StateTypeChoice()}. */
        public static final Node StateTypeChoice = Init.StateTypeChoice().asNode();

        /** Node of OntClass created by {@link Init#StateTypeEntryPoint()}. */
        public static final Node StateTypeEntryPoint = Init.StateTypeEntryPoint().asNode();

        /** Node of OntClass created by {@link Init#StateTypeExitPoint()}. */
        public static final Node StateTypeExitPoint = Init.StateTypeExitPoint().asNode();

        /** Node of OntClass created by {@link Init#StateTypeTerminate()}. */
        public static final Node StateTypeTerminate = Init.StateTypeTerminate().asNode();

        /** Node of OntClass created by {@link Init#StateTypeInitial()}. */
        public static final Node StateTypeInitial = Init.StateTypeInitial().asNode();

        /** Node of OntClass created by {@link Init#StateTypeFinal()}. */
        public static final Node StateTypeFinal = Init.StateTypeFinal().asNode();

        /** Node of OntClass created by {@link Init#StateTypeDeepHistory()}. */
        public static final Node StateTypeDeepHistory = Init.StateTypeDeepHistory().asNode();

        /** Node of OntClass created by {@link Init#StateTypeShallowHistory()}. */
        public static final Node StateTypeShallowHistory = Init.StateTypeShallowHistory().asNode();

        /** Node of OntClass created by {@link Init#Transition()}. */
        public static final Node Transition = Init.Transition().asNode();

        /** Node of property created by {@link Init#hasPriority()}. */
        public static final Node hasPriority = Init.hasPriority().asNode();

        /** Node of property created by {@link Init#hasTrigger()}. */
        public static final Node hasTrigger = Init.hasTrigger().asNode();

        /** Node of property created by {@link Init#hasEvent()}. */
        public static final Node hasEvent = Init.hasEvent().asNode();

        /** Node of property created by {@link Init#hasCondition().} */
        public static final Node hasCondition = Init.hasCondition().asNode();

        /** Node of property created by {@link Init#hasAction()}. */
        public static final Node hasAction = Init.hasAction().asNode();

        /** Node of OntClass created by {@link Init#Variable()}. */
        public static final Node Variable = Init.Variable().asNode();

        /** Node of property created by {@link Init#hasName()}. */
        public static final Node hasName = Init.hasName().asNode();

        /** Node of property created by {@link Init#hasType()}. */
        public static final Node hasType = Init.hasType().asNode();

        /** Node of OntClass created by {@link Init#Dataflow()}. */
        public static final Node Dataflow = Init.Dataflow().asNode();

    }
}
