package com.zipc.garden.webplatform.client.command;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.zipc.garden.core.ZGAddCommand;
import com.zipc.garden.core.ZGRemoveCommand;
import com.zipc.garden.core.ZGSetCommand;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.fsm.FSMDAction;
import com.zipc.garden.model.fsm.FSMDEvent;
import com.zipc.garden.model.fsm.FSMDRegion;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.model.fsm.FSMDTransition;
import com.zipc.garden.model.fsm.FSMDVariable;
import com.zipc.garden.model.fsm.FSMFactory;
import com.zipc.garden.model.fsm.FSMPackage;

/**
 * This class manages the commands operated by FSMEditor.
 */
public final class FSMEditorCommandProvider {

    /**
     * private constructor to block the instantiation from other class.
     */
    private FSMEditorCommandProvider() {
    }

    /**
     * method to get the instance
     * @return This class
     */
    public static FSMEditorCommandProvider getInstance() {
        return FMEditorCommandProvideryHolder.PROVIDER;
    }

    /**
     * class to hold the instance as final
     */
    private static class FMEditorCommandProvideryHolder {
        private static final FSMEditorCommandProvider PROVIDER = new FSMEditorCommandProvider();
    }

    /**
     * Create a command to change the coordinate position of FSMDState.
     * @param states
     * @param trans
     * @param moveY
     * @param moveX
     * @return Created command
     */
    public final synchronized CompoundCommand moveStates(List<FSMDState> states, Map<FSMDTransition, List<AbstractPoint>> trans, int moveY, int moveX) {
        CompoundCommand cmd = new CompoundCommand();
        states.forEach(state -> {
            ZGSetCommand command1 = new ZGSetCommand(state, FSMPackage.Literals.FSM_DVERTEX__TOP, state.getTop() + moveY);
            ZGSetCommand command2 = new ZGSetCommand(state, FSMPackage.Literals.FSM_DVERTEX__LEFT, state.getLeft() + moveX);
            cmd.append(command1);
            cmd.append(command2);
        });
        trans.entrySet().forEach(entry -> {
            entry.getKey().getConnectionPoint().forEach(point -> {
                ZGRemoveCommand command = new ZGRemoveCommand(entry.getKey(), COREPackage.Literals.ABSTRACT_LINE__CONNECTION_POINT, point);
                cmd.append(command);
            });
            AtomicInteger count = new AtomicInteger(0);
            entry.getValue().forEach(point -> {
                ZGAddCommand command = new ZGAddCommand(entry.getKey(), COREPackage.Literals.ABSTRACT_LINE__CONNECTION_POINT, point, count.getAndIncrement());
                cmd.append(command);
            });
        });
        return cmd;
    }

    /**
     * Create a command to resize FSMDState.
     * @param states
     * @param trans
     * @param resizeY
     * @param resizeX
     * @param resizeW
     * @param resizeH
     * @return Created command
     */
    public final synchronized CompoundCommand resizeStates(List<FSMDState> states, Map<FSMDTransition, List<AbstractPoint>> trans, int resizeY, int resizeX, int resizeW,
            int resizeH) {
        CompoundCommand cmd = new CompoundCommand();
        states.forEach(state -> {
            ZGSetCommand command1 = new ZGSetCommand(state, FSMPackage.Literals.FSM_DVERTEX__TOP, state.getTop() + resizeY);
            ZGSetCommand command2 = new ZGSetCommand(state, FSMPackage.Literals.FSM_DVERTEX__LEFT, state.getLeft() + resizeX);
            ZGSetCommand command3 = new ZGSetCommand(state, FSMPackage.Literals.FSM_DVERTEX__WIDTH, state.getWidth() + resizeW);
            ZGSetCommand command4 = new ZGSetCommand(state, FSMPackage.Literals.FSM_DVERTEX__HEIGHT, state.getHeight() + resizeH);
            cmd.append(command1);
            cmd.append(command2);
            cmd.append(command3);
            cmd.append(command4);
        });
        trans.entrySet().forEach(entry -> {
            entry.getKey().getConnectionPoint().forEach(point -> {
                ZGRemoveCommand command = new ZGRemoveCommand(entry.getKey(), COREPackage.Literals.ABSTRACT_LINE__CONNECTION_POINT, point);
                cmd.append(command);
            });
            AtomicInteger count = new AtomicInteger(0);
            entry.getValue().forEach(point -> {
                ZGAddCommand command = new ZGAddCommand(entry.getKey(), COREPackage.Literals.ABSTRACT_LINE__CONNECTION_POINT, point, count.getAndIncrement());
                cmd.append(command);
            });
        });
        return cmd;
    }

    /**
     * Create a command to add a new FSMDState.
     * @param region
     * @param state
     * @param addPosition
     * @return Created command
     */
    public final synchronized CompoundCommand addState(FSMDRegion region, FSMDState state, int addPosition) {
        CompoundCommand cmd = new CompoundCommand();
        ZGAddCommand command = new ZGAddCommand(region, FSMPackage.Literals.FSM_DREGION__STATES, state, addPosition);
        cmd.append(command);
        return cmd;
    }

    /**
     * Create a command to rename FSMDState.
     * @param state
     * @param newValue
     * @return Created command
     */
    public final synchronized CompoundCommand renameState(FSMDState state, String newValue) {
        CompoundCommand cmd = new CompoundCommand();
        ZGSetCommand command = new ZGSetCommand(state, FSMPackage.Literals.FSM_DSTATE__NAME, newValue);
        cmd.append(command);
        return cmd;
    }

    /**
     * Create a command to delete FSMDState.
     * @param machine
     * @param states
     * @param transitions
     * @return Created command
     */
    public final synchronized CompoundCommand removeStates(FSMDStateMachine machine, List<FSMDState> states, List<FSMDTransition> transitions) {
        CompoundCommand cmd = new CompoundCommand();
        states.forEach(state -> {
            ZGRemoveCommand command = new ZGRemoveCommand(machine.getRegions().get(0), FSMPackage.Literals.FSM_DREGION__STATES, state);
            cmd.append(command);
        });
        transitions.forEach(transition -> {
            ZGRemoveCommand command = new ZGRemoveCommand(machine, FSMPackage.Literals.FSM_DSTATE_MACHINE__TRANSITIONS, transition);
            cmd.append(command);
            ZGRemoveCommand command2 = new ZGRemoveCommand(transition.getSource(), FSMPackage.Literals.FSM_DSTATE__OUTGOING_TRANSITION, transition);
            cmd.append(command2);
            ZGRemoveCommand command3 = new ZGRemoveCommand(transition.getTarget(), FSMPackage.Literals.FSM_DSTATE__INCOMING_TRANSITION, transition);
            cmd.append(command3);
        });

        // ****************************************
        // Evacuate canvas information.
        // ****************************************
        HashSet<String> canvasEvents = new HashSet<>();
        HashSet<String> canvasActions = new HashSet<>();
        machine.getTransitions().forEach(trans -> {
            if (transitions.contains(trans)) {
                return;
            }
            if (trans.getEvent() != null && !"".equals(trans.getEvent())) {
                canvasEvents.add(trans.getEvent());
            }
            if (trans.getAction() != null && !"".equals(trans.getAction())) {
                canvasActions.add(trans.getAction());
            }
        });
        evacuateEventAndAction(cmd, machine, canvasEvents, canvasActions);

        return cmd;
    }

    /**
     * Create a command that creates a transition line that connects FSMDState and FSMDState.
     * @param transition
     * @param machine
     * @param source
     * @param target
     * @return Created command
     */
    public final synchronized CompoundCommand addTransition(FSMDTransition transition, FSMDStateMachine machine, FSMDState source, FSMDState target) {
        CompoundCommand cmd = new CompoundCommand();

        ZGAddCommand command = new ZGAddCommand(source, FSMPackage.Literals.FSM_DSTATE__OUTGOING_TRANSITION, transition, source.getOutgoingTransition().size());
        cmd.append(command);

        ZGAddCommand command2 = new ZGAddCommand(target, FSMPackage.Literals.FSM_DSTATE__INCOMING_TRANSITION, transition, target.getIncomingTransition().size());
        cmd.append(command2);

        ZGAddCommand command3 = new ZGAddCommand(machine, FSMPackage.Literals.FSM_DSTATE_MACHINE__TRANSITIONS, transition, machine.getTransitions().size());
        cmd.append(command3);

        // ****************************************
        // Evacuate canvas information.
        // ****************************************
        HashSet<String> canvasEvents = new HashSet<>();
        HashSet<String> canvasActions = new HashSet<>();
        machine.getTransitions().forEach(trans -> {
            if (trans.getEvent() != null && !"".equals(trans.getEvent())) {
                canvasEvents.add(trans.getEvent());
            }
            if (trans.getAction() != null && !"".equals(trans.getAction())) {
                canvasActions.add(trans.getAction());
            }
        });
        canvasEvents.add(Objects.toString(transition.getEvent(), ""));
        evacuateEventAndAction(cmd, machine, canvasEvents, canvasActions);

        return cmd;
    }

    /**
     * Create a command to change the connection source position of the transition line .
     * @param cmd
     * @param transition
     * @param newSource
     * @param anchorX
     * @param anchorY
     * @return
     */
    public final synchronized CompoundCommand resizeOutgoingTransition(CompoundCommand cmd, FSMDTransition transition, FSMDState newSource, double anchorX, double anchorY) {
        ZGSetCommand command = new ZGSetCommand(transition, FSMPackage.Literals.FSM_DTRANSITION__SOURCE, newSource);
        cmd.append(command);

        ZGSetCommand command2 = new ZGSetCommand(transition, COREPackage.Literals.ABSTRACT_LINE__SOURCE_ANCHOR_X, anchorX);
        cmd.append(command2);

        ZGSetCommand command3 = new ZGSetCommand(transition, COREPackage.Literals.ABSTRACT_LINE__SOURCE_ANCHOR_Y, anchorY);
        cmd.append(command3);

        return cmd;
    }

    /**
     * Create a command to change the connection target position of the transition line .
     * @param cmd
     * @param transition
     * @param newTarget
     * @param anchorX
     * @param anchorY
     * @return Created command
     */
    public final synchronized CompoundCommand resizeIncomingTransition(CompoundCommand cmd, FSMDTransition transition, FSMDState newTarget, double anchorX, double anchorY) {
        ZGSetCommand command = new ZGSetCommand(transition, FSMPackage.Literals.FSM_DTRANSITION__TARGET, newTarget);
        cmd.append(command);

        ZGSetCommand command2 = new ZGSetCommand(transition, COREPackage.Literals.ABSTRACT_LINE__TARGET_ANCHOR_X, anchorX);
        cmd.append(command2);

        ZGSetCommand command3 = new ZGSetCommand(transition, COREPackage.Literals.ABSTRACT_LINE__TARGET_ANCHOR_Y, anchorY);
        cmd.append(command3);

        return cmd;
    }

    /**
     * Create an event that changes the information of the label on the transition line.
     * @param machine
     * @param transition
     * @param newPriority
     * @param newTrigger
     * @param newEvent
     * @param newCondition
     * @param newAction
     * @return Created command
     */
    public final synchronized CompoundCommand editTransitionLabel(FSMDStateMachine machine, FSMDTransition transition, int newPriority, String newTrigger, String newEvent,
            String newCondition, String newAction) {
        CompoundCommand cmd = new CompoundCommand();

        ZGSetCommand command1 = new ZGSetCommand(transition, FSMPackage.Literals.FSM_DTRANSITION__PRIORITY, newPriority);
        cmd.append(command1);

        ZGSetCommand command2 = new ZGSetCommand(transition, FSMPackage.Literals.FSM_DTRANSITION__TRIGGER, newTrigger);
        cmd.append(command2);

        ZGSetCommand command3 = new ZGSetCommand(transition, FSMPackage.Literals.FSM_DTRANSITION__EVENT, newEvent);
        cmd.append(command3);

        ZGSetCommand command4 = new ZGSetCommand(transition, FSMPackage.Literals.FSM_DTRANSITION__CONDITION, newCondition);
        cmd.append(command4);

        ZGSetCommand command5 = new ZGSetCommand(transition, FSMPackage.Literals.FSM_DTRANSITION__ACTION, newAction);
        cmd.append(command5);

        if (newPriority == 0 && "".equals(newTrigger) && "".equals(newEvent) && "".equals(newCondition) && "".equals(newAction)) {
            ZGSetCommand command6 = new ZGSetCommand(transition, FSMPackage.Literals.FSM_DTRANSITION__X, 0);
            cmd.append(command6);

            ZGSetCommand command7 = new ZGSetCommand(transition, FSMPackage.Literals.FSM_DTRANSITION__Y, 0);
            cmd.append(command7);
        }

        // ****************************************
        // Evacuate canvas information.
        // ****************************************
        HashSet<String> canvasEvents = new HashSet<>();
        HashSet<String> canvasActions = new HashSet<>();
        machine.getTransitions().forEach(trans -> {
            if (!trans.equals(transition)) {
                if (trans.getEvent() != null && !"".equals(trans.getEvent())) {
                    canvasEvents.add(trans.getEvent());
                }
                if (trans.getAction() != null && !"".equals(trans.getAction())) {
                    canvasActions.add(trans.getAction());
                }
            }
        });
        if (!"".equals(newEvent)) {
            canvasEvents.add(newEvent);
        }
        if (!"".equals(newAction)) {
            canvasActions.add(newAction);
        }

        evacuateEventAndAction(cmd, machine, canvasEvents, canvasActions);

        return cmd;
    }

    /**
     * Create an event that changes the coordinate position of the label on the transition line.
     * @param transition
     * @param newX
     * @param newY
     * @return Created command
     */
    public final synchronized CompoundCommand moveTransitionLabel(FSMDTransition transition, int newX, int newY) {
        CompoundCommand cmd = new CompoundCommand();

        ZGSetCommand command = new ZGSetCommand(transition, FSMPackage.Literals.FSM_DTRANSITION__X, newX);
        cmd.append(command);

        ZGSetCommand command2 = new ZGSetCommand(transition, FSMPackage.Literals.FSM_DTRANSITION__Y, newY);
        cmd.append(command2);

        return cmd;
    }

    /**
     * Create an event that registers the variable set by FSMDStateMachine.
     * @param cmd
     * @param machine
     * @param variable
     * @param feature
     * @param newValue
     * @param position
     * @param lastLineFlag
     */
    public final synchronized void cellSavedVariable(CompoundCommand cmd, FSMDStateMachine machine, FSMDVariable variable, EStructuralFeature feature, Object newValue,
            int position, boolean lastLineFlag) {
        if (lastLineFlag) {
            ZGAddCommand command = new ZGAddCommand(machine, FSMPackage.Literals.FSM_DSTATE_MACHINE__VARIABLES, variable, position);
            cmd.append(command);
        }
        ZGSetCommand command = new ZGSetCommand(variable, feature, newValue);
        cmd.append(command);
    }

    /**
     * Creates an event that deletes the Variable set in FSMDStateMachine.
     * @param cmd
     * @param machine
     * @param variables
     */
    public final synchronized void removeVariable(CompoundCommand cmd, FSMDStateMachine machine, List<FSMDVariable> variables) {
        variables.forEach(variable -> {
            ZGRemoveCommand command = new ZGRemoveCommand(machine, FSMPackage.Literals.FSM_DSTATE_MACHINE__VARIABLES, variable);
            cmd.append(command);
        });
    }

    /**
     * Event and Action of FSMDStateMachine are organized and become equal to Event and Action existing on the canvas.
     * @param cmd
     * @param machine
     * @param canvasEvents
     * @param canvasActions
     */
    private void evacuateEventAndAction(CompoundCommand cmd, FSMDStateMachine machine, HashSet<String> canvasEvents, HashSet<String> canvasActions) {

        // ****************************************
        // If it is not displayed on the canvas, delete it.
        // ****************************************
        machine.getFmsdevent().forEach(event -> {
            if (!canvasEvents.contains(event.getName())) {
                ZGRemoveCommand command = new ZGRemoveCommand(machine, FSMPackage.Literals.FSM_DSTATE_MACHINE__FMSDEVENT, event);
                cmd.append(command);
            }
        });
        machine.getActions().forEach(action -> {
            if (!canvasActions.contains(action.getText())) {
                ZGRemoveCommand command = new ZGRemoveCommand(machine, FSMPackage.Literals.FSM_DSTATE_MACHINE__ACTIONS, action);
                cmd.append(command);
            }
        });

        // ****************************************
        // If it is only displayed on the canvas, add it.
        // ****************************************
        canvasEvents.forEach(name -> {
            Optional<FSMDEvent> optEvent = machine.getFmsdevent().stream().filter(event -> event.getName().equals(name)).findFirst();
            if (!optEvent.isPresent()) {
                FSMDEvent event = FSMFactory.eINSTANCE.createFSMDEvent();
                event.setName(name);
                ZGAddCommand command = new ZGAddCommand(machine, FSMPackage.Literals.FSM_DSTATE_MACHINE__FMSDEVENT, event, 0);
                cmd.append(command);
            }
        });
        canvasActions.forEach(text -> {
            Optional<FSMDAction> optAction = machine.getActions().stream().filter(action -> action.getText().equals(text)).findFirst();
            if (!optAction.isPresent()) {
                FSMDAction action = FSMFactory.eINSTANCE.createFSMDAction();
                action.setText(text);
                ZGAddCommand command = new ZGAddCommand(machine, FSMPackage.Literals.FSM_DSTATE_MACHINE__ACTIONS, action, 0);
                cmd.append(command);
            }
        });
    }
}
