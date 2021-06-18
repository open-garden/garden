package com.zipc.garden.job.ontology.register;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import com.zipc.garden.model.bps.BPSDataflow;
import com.zipc.garden.model.bps.BPSInstanceArc;
import com.zipc.garden.model.bps.BPSInstanceState;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;
import com.zipc.garden.model.bps.BPSInstanceTransition;
import com.zipc.garden.model.bps.BPSRoot;
import com.zipc.garden.model.bps.BPSVariable;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.rdf.ontology.BPSO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GBO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;

/**
 * A class that registers the ontology model of the behavior pattern setting.
 */
public class BPSRegister extends AbstractRegister {

    /**
     * constructor.
     * @param root bps Root
     * @param file bps File
     */
    public BPSRegister(AbstractRootElement root, File file) {
        super(root, file);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String generateFileInstanceURI() {
        File file = getFile();
        return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + BPSO.BPS_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected OntModel generateOntology() {
        OntModel ontModel = ModelFactory.createOntologyModel();

        // NameSpace
        ontModel.setNsPrefix("garden", GDNNS.GARDEN.NS);
        ontModel.setNsPrefix("bps", GDNNS.BPS.NS);
        ontModel.setNsPrefix("arc", GDNNS.ARC.NS);
        ontModel.setNsPrefix("gdata", GDNNS.UD.NS);

        createClassOntology(ontModel);

        // Ontology
        Ontology bpsOntology = ontModel.createOntology(GDNNS.BPS.URI);
        bpsOntology.addProperty(OWL2.imports, GBO.garden);

        // Class
        Resource bps = ontModel.createClass(BPSO.BehaviorPatternSetting.getURI());
        bps.addProperty(RDF.type, OWL2.Class);
        bps.addProperty(RDFS.subClassOf, GBO.File);

        // UserDataOntology
        Ontology ontology = ontModel.createOntology(GDNNS.UD.URI);
        ontology.addProperty(OWL2.imports, GBO.garden);

        // Instance
        Resource instance = ontModel.createOntResource(generateFileInstanceURI());
        setFileInstanceInfo(ontModel, instance, BPSO.BehaviorPatternSetting);

        setExtensionInfo(ontModel, instance);

        return ontModel;
    }

    /**
     * Create an ontology definition for BPS.
     * @param m BPS ontology model
     */
    private void createClassOntology(OntModel m) {
        // Ontology
        Ontology bpsOntology = m.createOntology(GDNNS.BPS.URI);
        bpsOntology.addProperty(OWL2.imports, GBO.garden);

        // Class
        Resource isEnabled = m.createProperty(BPSO.isEnabled.getURI());
        isEnabled.addProperty(RDF.type, OWL2.DatatypeProperty);
        isEnabled.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasOriginalUuid = m.createProperty(BPSO.hasOriginalUuid.getURI());
        hasOriginalUuid.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasOriginalUuid.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasOriginalName = m.createProperty(BPSO.hasOriginalName.getURI());
        hasOriginalName.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasOriginalName.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasSource = m.createProperty(BPSO.hasSource.getURI());
        hasSource.addProperty(RDF.type, OWL2.ObjectProperty);
        hasSource.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource hasTarget = m.createProperty(BPSO.hasTarget.getURI());
        hasTarget.addProperty(RDF.type, OWL2.ObjectProperty);
        hasTarget.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource bps = m.createClass(BPSO.BehaviorPatternSetting.getURI());
        bps.addProperty(RDF.type, OWL2.Class);
        Resource hasArc = m.createProperty(BPSO.hasArc.getURI());
        hasArc.addProperty(RDF.type, OWL2.ObjectProperty);
        hasArc.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource arc = m.createClass(BPSO.ArcInstance.getURI());
        arc.addProperty(RDF.type, OWL2.Class);
        Resource hasBehavior = m.createProperty(BPSO.hasBehavior.getURI());
        hasBehavior.addProperty(RDF.type, OWL2.ObjectProperty);
        hasBehavior.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource hasDataflow = m.createProperty(BPSO.hasDataflow.getURI());
        hasDataflow.addProperty(RDF.type, OWL2.ObjectProperty);
        hasDataflow.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource behavior = m.createClass(BPSO.BehaviorInstance.getURI());
        behavior.addProperty(RDF.type, OWL2.Class);
        Resource hasEvalPriority = m.createProperty(BPSO.hasEvalPriority.getURI());
        hasEvalPriority.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasEvalPriority.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasInitialState = m.createProperty(BPSO.hasInitialState.getURI());
        hasInitialState.addProperty(RDF.type, OWL2.ObjectProperty);
        hasInitialState.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource hasState = m.createProperty(BPSO.hasState.getURI());
        hasState.addProperty(RDF.type, OWL2.ObjectProperty);
        hasState.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource hasVariable = m.createProperty(BPSO.hasVariable.getURI());
        hasVariable.addProperty(RDF.type, OWL2.ObjectProperty);
        hasVariable.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource state = m.createClass(BPSO.State.getURI());
        state.addProperty(RDF.type, OWL2.Class);
        Resource hasStateType = m.createProperty(BPSO.hasStateType.getURI());
        hasStateType.addProperty(RDF.type, OWL2.ObjectProperty);
        hasStateType.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource hasTransition = m.createProperty(BPSO.hasTransition.getURI());
        hasTransition.addProperty(RDF.type, OWL2.ObjectProperty);
        hasTransition.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource stateType = m.createClass(BPSO.StateType.getURI());
        stateType.addProperty(RDF.type, OWL2.Class);
        Resource stateTypeNone = m.createClass(BPSO.StateTypeNone.getURI());
        stateTypeNone.addProperty(RDF.type, OWL2.Class);
        stateTypeNone.addProperty(RDFS.subClassOf, BPSO.StateType);
        Resource stateTypeJoin = m.createClass(BPSO.StateTypeJoin.getURI());
        stateTypeJoin.addProperty(RDF.type, OWL2.Class);
        stateTypeJoin.addProperty(RDFS.subClassOf, BPSO.StateType);
        Resource stateTypeFork = m.createClass(BPSO.StateTypeFork.getURI());
        stateTypeFork.addProperty(RDF.type, OWL2.Class);
        stateTypeFork.addProperty(RDFS.subClassOf, BPSO.StateType);
        Resource stateTypeJunction = m.createClass(BPSO.StateTypeJunction.getURI());
        stateTypeJunction.addProperty(RDF.type, OWL2.Class);
        stateTypeJunction.addProperty(RDFS.subClassOf, BPSO.StateType);
        Resource stateTypeChoice = m.createClass(BPSO.StateTypeChoice.getURI());
        stateTypeChoice.addProperty(RDF.type, OWL2.Class);
        stateTypeChoice.addProperty(RDFS.subClassOf, BPSO.StateType);
        Resource stateTypeEntryPoint = m.createClass(BPSO.StateTypeEntryPoint.getURI());
        stateTypeEntryPoint.addProperty(RDF.type, OWL2.Class);
        stateTypeEntryPoint.addProperty(RDFS.subClassOf, BPSO.StateType);
        Resource stateTypeExitPoint = m.createClass(BPSO.StateTypeExitPoint.getURI());
        stateTypeExitPoint.addProperty(RDF.type, OWL2.Class);
        stateTypeExitPoint.addProperty(RDFS.subClassOf, BPSO.StateType);
        Resource stateTypeTerminate = m.createClass(BPSO.StateTypeTerminate.getURI());
        stateTypeTerminate.addProperty(RDF.type, OWL2.Class);
        stateTypeTerminate.addProperty(RDFS.subClassOf, BPSO.StateType);
        Resource stateTypeInitial = m.createClass(BPSO.StateTypeInitial.getURI());
        stateTypeInitial.addProperty(RDF.type, OWL2.Class);
        stateTypeInitial.addProperty(RDFS.subClassOf, BPSO.StateType);
        Resource stateTypeFinal = m.createClass(BPSO.StateTypeFinal.getURI());
        stateTypeFinal.addProperty(RDF.type, OWL2.Class);
        stateTypeFinal.addProperty(RDFS.subClassOf, BPSO.StateType);
        Resource stateTypeDeepHistory = m.createClass(BPSO.StateTypeDeepHistory.getURI());
        stateTypeDeepHistory.addProperty(RDF.type, OWL2.Class);
        stateTypeDeepHistory.addProperty(RDFS.subClassOf, BPSO.StateType);
        Resource stateTypeShallowHistory = m.createClass(BPSO.StateTypeShallowHistory.getURI());
        stateTypeShallowHistory.addProperty(RDF.type, OWL2.Class);
        stateTypeShallowHistory.addProperty(RDFS.subClassOf, BPSO.StateType);
        Resource transition = m.createClass(BPSO.Transition.getURI());
        transition.addProperty(RDF.type, OWL2.Class);
        Resource hasPriority = m.createProperty(BPSO.hasPriority.getURI());
        hasPriority.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasPriority.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasTrigger = m.createProperty(BPSO.hasTrigger.getURI());
        hasTrigger.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasTrigger.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasEvent = m.createProperty(BPSO.hasEvent.getURI());
        hasEvent.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasEvent.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasCondition = m.createProperty(BPSO.hasCondition.getURI());
        hasCondition.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasCondition.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasAction = m.createProperty(BPSO.hasAction.getURI());
        hasAction.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasAction.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource variable = m.createClass(BPSO.Variable.getURI());
        variable.addProperty(RDF.type, OWL2.Class);
        Resource hasName = m.createProperty(BPSO.hasName.getURI());
        hasName.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasName.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasType = m.createProperty(BPSO.hasType.getURI());
        hasType.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasType.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource dataflow = m.createClass(BPSO.Dataflow.getURI());
        dataflow.addProperty(RDF.type, OWL2.Class);

    }

    /**
     * Create the contents of BPSRoot and set it in the ontology model.
     * @param ontModel BPS ontology model
     * @param instance The created instance is set.
     */
    private void setExtensionInfo(OntModel ontModel, Resource instance) {
        BPSRoot bpsRoot = (BPSRoot) getRoot();

        // --> Start bps:ArcInstance
        BPSInstanceArc bpsArc = bpsRoot.getInstanceArc();
        if (bpsArc != null) {
            Resource iArc = ontModel.createOntResource(instance.getURI() + SEPARATOR + "Arc");
            iArc.addProperty(RDF.type, OWL2.NamedIndividual);
            iArc.addProperty(RDF.type, BPSO.ArcInstance);
            iArc.addLiteral(BPSO.hasOriginalUuid, bpsArc.getOriginalUuid());

            // --> Start bps:BehaviorInstance
            Resource iStm;
            for (BPSInstanceStateMachine bpsStm : bpsArc.getStateMachines()) {
                iStm = ontModel.createOntResource(instance.getURI() + SEPARATOR + "BehaviorInstance" + SEPARATOR + bpsStm.getOriginalName());
                iStm.addProperty(RDF.type, OWL2.NamedIndividual);
                iStm.addProperty(RDF.type, BPSO.BehaviorInstance);
                iStm.addLiteral(BPSO.isEnabled, bpsStm.isEnabled());
                iStm.addLiteral(BPSO.hasOriginalUuid, bpsStm.getOriginalUuid());
                iStm.addLiteral(BPSO.hasOriginalName, bpsStm.getOriginalName());
                iStm.addLiteral(BPSO.hasEvalPriority, bpsStm.getEvalPriority());

                iStm.addProperty(BPSO.hasInitialState, ontModel.createResource(iStm.getURI() + SEPARATOR + "State" + SEPARATOR + bpsStm.getInitialState().getOriginalName()));

                // --> Start bps:State
                Resource iState;
                for (BPSInstanceState bpsState : bpsStm.getStates()) {
                    iState = ontModel.createOntResource(iStm.getURI() + SEPARATOR + "State" + SEPARATOR + bpsState.getOriginalName());
                    iState.addProperty(RDF.type, OWL2.NamedIndividual);
                    iState.addProperty(RDF.type, BPSO.State);
                    iState.addLiteral(BPSO.isEnabled, bpsState.isEnabled());
                    iState.addLiteral(BPSO.hasName, bpsState.getOriginalName()); // Variableと同じくhasNameとする
                    switch (bpsState.getType()) {
                    case NONE:
                        iState.addProperty(BPSO.hasStateType, BPSO.StateTypeNone);
                        break;
                    case JOIN:
                        iState.addProperty(BPSO.hasStateType, BPSO.StateTypeJoin);
                        break;
                    case FORK:
                        iState.addProperty(BPSO.hasStateType, BPSO.StateTypeFork);
                        break;
                    case JUNCTION:
                        iState.addProperty(BPSO.hasStateType, BPSO.StateTypeJunction);
                        break;
                    case CHOICE:
                        iState.addProperty(BPSO.hasStateType, BPSO.StateTypeChoice);
                        break;
                    case ENTRY_POINT:
                        iState.addProperty(BPSO.hasStateType, BPSO.StateTypeEntryPoint);
                        break;
                    case EXIT_POINT:
                        iState.addProperty(BPSO.hasStateType, BPSO.StateTypeExitPoint);
                        break;
                    case TERMINATE:
                        iState.addProperty(BPSO.hasStateType, BPSO.StateTypeTerminate);
                        break;
                    case INITIAL:
                        iState.addProperty(BPSO.hasStateType, BPSO.StateTypeInitial);
                        break;
                    case FINAL:
                        iState.addProperty(BPSO.hasStateType, BPSO.StateTypeFinal);
                        break;
                    case DEEP_HISTORY:
                        iState.addProperty(BPSO.hasStateType, BPSO.StateTypeDeepHistory);
                        break;
                    case SHALLOW_HISTORY:
                        iState.addProperty(BPSO.hasStateType, BPSO.StateTypeShallowHistory);
                        break;
                    }

                    // --> Start bps:Transition
                    Resource iTransition;
                    for (BPSInstanceTransition bpsTransition : bpsState.getTransitions()) {
                        iTransition = ontModel.createOntResource(iState.getURI() + SEPARATOR + "Transition" + SEPARATOR + bpsTransition.getEvent());
                        iTransition.addProperty(RDF.type, OWL2.NamedIndividual);
                        iTransition.addProperty(RDF.type, BPSO.Transition);
                        iTransition.addLiteral(BPSO.isEnabled, bpsTransition.isEnabled());
                        iTransition.addLiteral(BPSO.hasPriority, bpsTransition.getPriority());
                        if (bpsTransition.getTrigger() != null && !bpsTransition.getTrigger().isEmpty())
                            iTransition.addLiteral(BPSO.hasTrigger, bpsTransition.getTrigger());
                        if (bpsTransition.getEvent() != null && !bpsTransition.getEvent().isEmpty())
                            iTransition.addLiteral(BPSO.hasEvent, bpsTransition.getEvent());
                        if (bpsTransition.getCondition() != null && !bpsTransition.getCondition().isEmpty())
                            iTransition.addLiteral(BPSO.hasCondition, bpsTransition.getCondition());
                        if (bpsTransition.getAction() != null && !bpsTransition.getAction().isEmpty())
                            iTransition.addLiteral(BPSO.hasAction, bpsTransition.getAction());
                        iTransition.addProperty(BPSO.hasSource, iState);
                        iTransition.addProperty(BPSO.hasTarget,
                                ontModel.createResource(iStm.getURI() + SEPARATOR + "State" + SEPARATOR + bpsTransition.getTarget().getOriginalName()));

                        iState.addProperty(BPSO.hasTransition, iTransition);
                    } // <-- End bps:Transition

                    iStm.addProperty(BPSO.hasState, iState);
                } // <-- End bps:State

                // --> Start bps:Variable
                Resource iVariable;
                for (BPSVariable bpsVariable : bpsStm.getVariables()) {
                    iVariable = ontModel.createOntResource(iStm.getURI() + SEPARATOR + "Variable" + SEPARATOR + bpsVariable.getName());
                    iVariable.addProperty(RDF.type, OWL2.NamedIndividual);
                    iVariable.addProperty(RDF.type, BPSO.Variable);
                    iVariable.addLiteral(BPSO.hasName, bpsVariable.getName());
                    iVariable.addLiteral(BPSO.hasType, bpsVariable.getType());

                    iStm.addProperty(BPSO.hasVariable, iVariable);
                } // <-- End bps:Variable

                iArc.addProperty(BPSO.hasBehavior, iStm);
            } // <-- End bps:BehaviorInstance

            // --> Start bps:Dataflow
            Resource iDataflow;
            BPSDataflow bpsDataflow;
            for (int i = 0; i < bpsArc.getDataflows().size(); i++) {
                bpsDataflow = bpsArc.getDataflows().get(i);
                iDataflow = ontModel.createOntResource(instance.getURI() + SEPARATOR + "Dataflow" + SEPARATOR + i);
                iDataflow.addProperty(RDF.type, OWL2.NamedIndividual);
                iDataflow.addProperty(RDF.type, BPSO.Dataflow);
                iDataflow.addProperty(BPSO.hasSource,
                        ontModel.createResource(instance.getURI() + SEPARATOR + "BehaviorInstance" + SEPARATOR + bpsDataflow.getSource().getOriginalName()));
                iDataflow.addProperty(BPSO.hasTarget,
                        ontModel.createResource(
                                instance.getURI() + SEPARATOR + "BehaviorInstance" + SEPARATOR + ((BPSInstanceStateMachine) bpsDataflow.getTarget().eContainer()).getOriginalName()
                                        + SEPARATOR + "Variable" + SEPARATOR + bpsDataflow.getTarget().getName()));

                iArc.addProperty(BPSO.hasDataflow, iDataflow);
            } // <-- End bps:Dataflow

            instance.addProperty(BPSO.hasArc, iArc); // <-- End bps:ArcInstance
        }
    }

}
