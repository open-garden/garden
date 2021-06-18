package com.zipc.garden.webplatform.client.command;

import org.eclipse.emf.common.command.CompoundCommand;

import com.zipc.garden.core.ZGSetCommand;
import com.zipc.garden.model.bps.BPSEnablement;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;
import com.zipc.garden.model.bps.BPSPackage;

/**
 * This class manages the commands operated by BPSEditor.
 */
public final class BPSEditorCommandProvider {

    /**
     * private constructor to block the instantiation from other class.
     */
    private BPSEditorCommandProvider() {
    }

    /**
     * method to get the instance
     * @return This class
     */
    public static BPSEditorCommandProvider getInstance() {
        return BPSEditorCommandProviderHolder.PROVIDER;
    }

    /**
     * class to hold the instance as final
     */
    private static class BPSEditorCommandProviderHolder {
        private static final BPSEditorCommandProvider PROVIDER = new BPSEditorCommandProvider();
    }

    /**
     * Create a command that changes the selection status of checked records.
     * @param element
     * @param newValue
     * @param cmd
     */
    public final synchronized void checkNode(BPSEnablement element, boolean newValue, CompoundCommand cmd) {
        ZGSetCommand command = new ZGSetCommand(element, BPSPackage.Literals.BPS_ENABLEMENT__ENABLED, newValue);
        cmd.append(command);
    }

    /**
     * Create a command to change the priority of BPSInstanceStateMachine.
     * @param bpsStm
     * @param newValue
     * @param cmd
     */
    public final synchronized void setEvalPriority(BPSInstanceStateMachine bpsStm, int newValue, CompoundCommand cmd) {
        ZGSetCommand command = new ZGSetCommand(bpsStm, BPSPackage.Literals.BPS_INSTANCE_STATE_MACHINE__EVAL_PRIORITY, newValue);
        cmd.append(command);
    }
}
