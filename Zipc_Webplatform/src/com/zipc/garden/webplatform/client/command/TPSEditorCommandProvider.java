package com.zipc.garden.webplatform.client.command;

import org.eclipse.emf.common.command.CompoundCommand;

import com.zipc.garden.core.ZGSetCommand;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCPackage;

/**
 * This class manages the commands operated by TPSEditor.
 */
public final class TPSEditorCommandProvider {

    /**
     * private constructor to block the instantiation from other class.
     */
    private TPSEditorCommandProvider() {
    }

    /**
     * method to get the instance
     * @return This class
     */
    public static TPSEditorCommandProvider getInstance() {
        return TPSEditorCommandProviderHolder.PROVIDER;
    }

    /**
     * class to hold the instance as final
     */
    private static class TPSEditorCommandProviderHolder {
        private static final TPSEditorCommandProvider PROVIDER = new TPSEditorCommandProvider();
    }

    /**
     * Create a command to update n-wise input values.
     * @param node
     * @param newValue
     * @param cmd
     */
    public final synchronized void setNwise(TCNode node, int newValue, CompoundCommand cmd) {
        ZGSetCommand command = new ZGSetCommand(node, TCPackage.Literals.TC_NODE__NWISE_COMBINATION, newValue);
        cmd.append(command);
    }
}
