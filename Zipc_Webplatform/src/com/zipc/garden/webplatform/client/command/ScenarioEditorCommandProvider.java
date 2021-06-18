package com.zipc.garden.webplatform.client.command;

import org.eclipse.emf.common.command.CompoundCommand;

import com.zipc.garden.core.ZGAddCommand;
import com.zipc.garden.core.ZGCommand;
import com.zipc.garden.core.ZGRemoveCommand;
import com.zipc.garden.core.ZGSetCommand;
import com.zipc.garden.model.scenario.Actor;
import com.zipc.garden.model.scenario.ActorSetting;
import com.zipc.garden.model.scenario.OtherVehicle;
import com.zipc.garden.model.scenario.Phase;
import com.zipc.garden.model.scenario.PhaseAction;
import com.zipc.garden.model.scenario.PhaseCondition;
import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scenario.VehicleAction;
import com.zipc.garden.model.scenario.VehicleCondition;

/**
 * This class manages the commands operated by ScenarioEditor.
 */
public final class ScenarioEditorCommandProvider {

    /**
     * private constructor to block the instantiation from other class.
     */
    private ScenarioEditorCommandProvider() {
    }

    /**
     * method to get the instance
     * @return This class
     */
    public static ScenarioEditorCommandProvider getInstance() {
        return ScenarioEditorCommandProviderHolder.PROVIDER;
    }

    /**
     * class to hold the instance as final
     */
    private static class ScenarioEditorCommandProviderHolder {
        private static final ScenarioEditorCommandProvider PROVIDER = new ScenarioEditorCommandProvider();
    }

    /**
     * Create a command to add a new actor.
     * @param actorSetting
     * @param other
     * @param cmd
     */
    public final synchronized void addNewActor(ActorSetting actorSetting, OtherVehicle other, CompoundCommand cmd) {
        ZGAddCommand command = new ZGAddCommand(actorSetting, ScenarioPackage.Literals.ACTOR_SETTING__OTHERS, other, actorSetting.getOthers().size());
        cmd.append(command);
    }

    /**
     * Create a command to remove the actor.
     * @param actorSetting
     * @param other
     * @param cmd
     */
    public final synchronized void deleteActor(ActorSetting actorSetting, OtherVehicle other, CompoundCommand cmd) {
        ZGRemoveCommand command = new ZGRemoveCommand(actorSetting, ScenarioPackage.Literals.ACTOR_SETTING__OTHERS, other);
        cmd.append(command);
    }

    /**
     * Create a command to add a new phase.
     * @param phase
     * @param nextPhase
     * @param cmd
     */
    public final synchronized void addNewPhase(Phase phase, Phase nextPhase, CompoundCommand cmd) {
        ZGAddCommand command = new ZGAddCommand(phase, ScenarioPackage.Literals.PHASE__NEXT_PHASES, nextPhase, phase.getNextPhases().size());
        cmd.append(command);
    }

    /**
     * Create a command to remove the phase.
     * @param prevPhase
     * @param phase
     * @param cmd
     */
    public final synchronized void deletePhase(Phase prevPhase, Phase phase, CompoundCommand cmd) {
        ZGRemoveCommand command = new ZGRemoveCommand(prevPhase, ScenarioPackage.Literals.PHASE__NEXT_PHASES, phase);
        cmd.append(command);
    }

    /**
     * Create a command to set the condition of the phase.
     * @param phase
     * @param phaseCondition
     * @param cmd
     */
    public final synchronized void setPhaseCondition(Phase phase, PhaseCondition phaseCondition, CompoundCommand cmd) {
        ZGCommand command = new ZGSetCommand(phase, ScenarioPackage.Literals.PHASE__CONDITION, phaseCondition);
        cmd.append(command);
    }

    /**
     * Create a command to set the action of the phase.
     * @param phase
     * @param phaseAction
     * @param cmd
     */
    public final synchronized void setPhaseAction(Phase phase, PhaseAction phaseAction, CompoundCommand cmd) {
        ZGCommand command = new ZGSetCommand(phase, ScenarioPackage.Literals.PHASE__ACTION, phaseAction);
        cmd.append(command);
    }

    /**
     * Create a command to add a new vehicle condition.
     * @param phase
     * @param vCondition
     * @param cmd
     */
    public final synchronized void addVehicleCondition(Phase phase, VehicleCondition vCondition, CompoundCommand cmd) {
        ZGCommand command = new ZGSetCommand(phase, ScenarioPackage.Literals.PHASE_CONDITION__CONDITIONS, vCondition);
        cmd.append(command);
    }

    /**
     * Create a command to add a new vehicle action.
     * @param phase
     * @param vAction
     * @param cmd
     */
    public final synchronized void addVehicleAction(Phase phase, VehicleAction vAction, CompoundCommand cmd) {
        ZGCommand command = new ZGSetCommand(phase, ScenarioPackage.Literals.PHASE_ACTION__ACTIONS, vAction);
        cmd.append(command);
    }

    /**
     * Create a command to update the phase name.
     * @param phase
     * @param newName
     * @param cmd
     */
    public final synchronized void setPhaseName(Phase phase, String newName, CompoundCommand cmd) {
        ZGCommand command = new ZGSetCommand(phase, ScenarioPackage.Literals.PHASE__NAME, newName);
        cmd.append(command);
    }

    /**
     * Create a command to update the actor name.
     * @param actor
     * @param newName
     * @param cmd
     */
    public final synchronized void setActorName(Actor actor, String newName, CompoundCommand cmd) {
        ZGCommand command = new ZGSetCommand(actor, ScenarioPackage.Literals.ACTOR__NAME, newName);
        cmd.append(command);
    }
}
