package com.zipc.garden.webplatform.client.command;

import org.eclipse.emf.common.command.CompoundCommand;

import com.zipc.garden.core.ZGSetCommand;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCPackage;

/**
 * This class manages the commands operated by TCEditor.
 */
public final class TCEditorCommandProvider {

    /**
     * private constructor to block the instantiation from other class.
     */
    private TCEditorCommandProvider() {
    }

    /**
     * method to get the instance
     * @return This class
     */
    public static TCEditorCommandProvider getInstance() {
        return TCEditorCommandProviderHolder.PROVIDER;
    }

    /**
     * class to hold the instance as final
     */
    private static class TCEditorCommandProviderHolder {
        private static final TCEditorCommandProvider PROVIDER = new TCEditorCommandProvider();
    }

    /**
     * Create an event that updates the selection state of TCNode.
     * @param node
     * @param newValue
     * @param cmd
     */
    public final synchronized void checkNode(TCNode node, boolean newValue, CompoundCommand cmd) {
        ZGSetCommand command = new ZGSetCommand(node, TCPackage.Literals.TC_NODE__CHECKED, newValue);
        cmd.append(command);
    }
}
