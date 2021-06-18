package com.zipc.garden.webplatform.client.command;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.CompoundCommand;

import com.zipc.garden.core.ZGAddCommand;
import com.zipc.garden.core.ZGRemoveCommand;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.model.fmc.FMCNodePath;
import com.zipc.garden.model.fmc.FMCPackage;
import com.zipc.garden.model.fmc.FMCRoot;

/**
 * This class manages the commands operated by FMCEditor.
 */
public final class FMCEditorCommandProvider {

    /**
     * private constructor to block the instantiation from other class.
     */
    private FMCEditorCommandProvider() {
    }

    /**
     * method to get the instance
     * @return This class
     */
    public static FMCEditorCommandProvider getInstance() {
        return FMCEditorCommandProvideryHolder.PROVIDER;
    }

    /**
     * class to hold the instance as final
     */
    private static class FMCEditorCommandProvideryHolder {
        private static final FMCEditorCommandProvider PROVIDER = new FMCEditorCommandProvider();
    }

    /**
     * When reloading, create and return a command that reflects the information of FullPath and SimplePath of FM file in
     * FMCRoot.
     * @param root
     * @param fMCNodePaths
     * @param valueMap
     * @return Created command
     */
    public final synchronized CompoundCommand reload(FMCRoot root, List<FMCNodePath> fMCNodePaths, Map<String, String> valueMap) {
        CompoundCommand cmd = new CompoundCommand();

        if (root.getRefs().size() > 0) {
            ZGRemoveCommand command1 = new ZGRemoveCommand(root, COREPackage.Literals.ABSTRACT_ROOT_ELEMENT__REFS, root.getRefs().get(0));
            cmd.append(command1);
        }

        Reference ref = COREFactory.eINSTANCE.createReference();
        ref.setRefid(root.getRefs().stream().findFirst().get().getRefid());

        String name = valueMap.values().iterator().next();
        int index = name.lastIndexOf(".") == 0 ? name.length() : name.lastIndexOf(".") + 1;
        ref.setRefName(name.substring(0, index - 1));
        ref.setRefExtension(name.substring(index, name.length()));

        ZGAddCommand command2 = new ZGAddCommand(root, COREPackage.Literals.ABSTRACT_ROOT_ELEMENT__REFS, ref, 0);
        cmd.append(command2);

        root.getNodepaths().forEach(nodePath -> {
            ZGRemoveCommand command3 = new ZGRemoveCommand(root, FMCPackage.Literals.FMC_ROOT__NODEPATHS, nodePath);
            cmd.append(command3);
        });

        fMCNodePaths.forEach(nodePath -> {
            ZGAddCommand command4 = new ZGAddCommand(root, FMCPackage.Literals.FMC_ROOT__NODEPATHS, nodePath, fMCNodePaths.indexOf(nodePath));
            cmd.append(command4);
        });

        return cmd;
    }
}
