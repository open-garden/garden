package com.zipc.garden.webplatform.client.command;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.zipc.garden.core.ZGAddCommand;
import com.zipc.garden.core.ZGRemoveCommand;
import com.zipc.garden.core.ZGSetCommand;
import com.zipc.garden.model.cb.CBFile;
import com.zipc.garden.model.cb.CBLogic;
import com.zipc.garden.model.cb.CBPackage;
import com.zipc.garden.model.cb.CBRoot;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.core.Reference;

/**
 * This class manages the commands operated by CBEditor.
 */
public final class CBEditorCommandProvider {

    /**
     * private constructor to block the instantiation from other class.
     */
    private CBEditorCommandProvider() {
    }

    /**
     * method to get the instance
     * @return This class
     */
    public static CBEditorCommandProvider getInstance() {
        return CBEditorCommandProvideryHolder.PROVIDER;
    }

    /**
     * class to hold the instance as final
     */
    private static class CBEditorCommandProvideryHolder {
        private static final CBEditorCommandProvider PROVIDER = new CBEditorCommandProvider();
    }

    /**
     * Create a command to associate FPS and BPS files with SCSS.
     * @param root
     * @param cbFiles
     * @return Created command
     */
    public final synchronized CompoundCommand addFiles(CBRoot root, List<CBFile> cbFiles) {

        CompoundCommand cmd = new CompoundCommand();

        root.getRefs().forEach(ref -> {
            ZGRemoveCommand command1 = new ZGRemoveCommand(root, COREPackage.Literals.ABSTRACT_ROOT_ELEMENT__REFS, ref);
            cmd.append(command1);
        });

        HashSet<String> uuids = new HashSet<String>();
        AtomicInteger idx = new AtomicInteger(root.getLogic().getFile().size());
        cbFiles.forEach(file -> {
            ZGAddCommand command2 = new ZGAddCommand(root.getLogic(), CBPackage.Literals.CB_LOGIC__FILE, file, idx.getAndIncrement());
            cmd.append(command2);
            uuids.add(file.getUuid());
        });
        root.getLogic().getFile().forEach(file -> {
            uuids.add(file.getUuid());
        });
        AtomicInteger idx2 = new AtomicInteger(0);
        uuids.forEach(uuid -> {
            Reference ref = COREFactory.eINSTANCE.createReference();
            ref.setRefid(uuid);
            ZGAddCommand command3 = new ZGAddCommand(root, COREPackage.Literals.ABSTRACT_ROOT_ELEMENT__REFS, ref, idx2.getAndIncrement());
            cmd.append(command3);
        });

        return cmd;
    }

    /**
     * Create a command to disassociate FPS and BPS files from SCSS.
     * @param logic
     * @param cbFiles
     * @return Created command
     */
    public final synchronized CompoundCommand removeFiles(CBLogic logic, List<CBFile> cbFiles) {
        CBRoot root = (CBRoot) EcoreUtil.getRootContainer(logic);
        CompoundCommand cmd = new CompoundCommand();
        cbFiles.forEach(file -> {
            ZGRemoveCommand command = new ZGRemoveCommand(logic, CBPackage.Literals.CB_LOGIC__FILE, file);
            cmd.append(command);
            Optional<Reference> refOpt = root.getRefs().stream().filter(r -> r.getRefid().equals(file.getUuid())).findFirst();
            if (refOpt.isPresent()) {
                ZGRemoveCommand rmRefCommand = new ZGRemoveCommand(root, COREPackage.Literals.ABSTRACT_ROOT_ELEMENT__REFS, refOpt.get());
                cmd.append(rmRefCommand);
            }

        });
        return cmd;
    }

    /**
     * Create a command to change the pattern number.
     * @param cbFile
     * @param newPattern
     * @return Created command
     */
    public final synchronized CompoundCommand setPattern(CBFile cbFile, String newPattern) {
        CompoundCommand cmd = new CompoundCommand();
        ZGSetCommand command = new ZGSetCommand(cbFile, CBPackage.Literals.CB_FILE__PATTERN, newPattern);
        cmd.append(command);
        return cmd;
    }
}
