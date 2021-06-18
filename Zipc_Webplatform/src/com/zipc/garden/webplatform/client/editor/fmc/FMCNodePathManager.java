package com.zipc.garden.webplatform.client.editor.fmc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.model.fmc.FMCFactory;
import com.zipc.garden.model.fmc.FMCNodePath;
import com.zipc.garden.model.fmc.FMCRoot;
import com.zipc.garden.webplatform.client.command.FMCEditorCommandProvider;
import com.zipc.garden.webplatform.shared.NodeUtil;

/**
 * This class works when I reload the FM file in the FMC editor.<br>
 * Create the full path and simple path of the node of the selected FM file.
 */
public class FMCNodePathManager {

    /** Node path separator */
    private final String DOT = ".";

    /**
     * Reloads the FM file information.<br>
     * Create the full path and simple path of the node.
     * @param editor Main class of feature-model-constraint editor
     * @param valueMap Information about the file selected when reloading<br>
     *            key: id<br>
     *            value: name
     * @param projectId The project ID in which this FMC is managed.
     */
    protected void reload(FMCEditor editor, Map<String, String> valueMap, long projectId) {
        String fileUuid = editor.getFMCRoot().getRefs().stream().findFirst().get().getRefid();
        editor.getService().getFMFileContent(fileUuid, projectId, new AsyncCallback<Map<String, byte[]>>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(Map<String, byte[]> result) {

                BinaryResourceImpl r = new BinaryResourceImpl();
                ByteArrayInputStream bi = new ByteArrayInputStream(result.get(fileUuid));

                try {
                    r.load(bi, EditOptions.getDefaultLoadOptions());
                } catch (IOException e) {
                    SC.warn(e.getMessage());
                }
                FMRoot root = (FMRoot) r.getContents().get(0);
                FMNode rootNode = root.getNode();
                if (rootNode != null) {
                    FMCRoot fmcRoot = FMCFactory.eINSTANCE.createFMCRoot();
                    setFullPath(fmcRoot, rootNode, FMCFactory.eINSTANCE.createFMCNodePath(), result);
                    setSimplePath(fmcRoot);

                    CompoundCommand cmd = FMCEditorCommandProvider.getInstance().reload(editor.getFMCRoot(), fmcRoot.getNodepaths(), valueMap);
                    editor.getEditManager().execute(cmd.unwrap());
                }
            }
        });
    }

    /**
     * Get and set the full path of the node.
     * @param root FMC Root Model
     * @param node Node to get the full path
     * @param nodePath Class with node path information
     * @param fMRoots FM model of the file set in the reference node<br>
     *            key: uuid<br>
     *            value: Binary (FM Root Model)
     */
    private void setFullPath(FMCRoot root, FMNode node, FMCNodePath nodePath, Map<String, byte[]> fMRoots) {
        if (root.getNodepaths().size() == 0) {
            nodePath.setFullpath(NodeUtil.getInstance().getEscapedNodeName(node.getName()));
            nodePath.setNodeName(node.getName());
            root.getNodepaths().add(nodePath);
        }
        node.getChildren().forEach(fMNode -> {
            FMCNodePath fMCNodePath = FMCFactory.eINSTANCE.createFMCNodePath();
            fMCNodePath.setFullpath(nodePath.getFullpath() + DOT + NodeUtil.getInstance().getEscapedNodeName(fMNode.getName()));
            fMCNodePath.setNodeName(fMNode.getName());
            root.getNodepaths().add(fMCNodePath);
            setFullPath(root, fMNode, fMCNodePath, fMRoots);
        });
        if (node.getRefuuid() != null) {
            BinaryResourceImpl r = new BinaryResourceImpl();
            ByteArrayInputStream bi = new ByteArrayInputStream(fMRoots.get(node.getRefuuid()));
            try {
                r.load(bi, null);
            } catch (IOException e) {
                SC.warn(e.getMessage());
            }
            FMRoot refRoot = (FMRoot) r.getContents().get(0);
            FMNode refRootNode = refRoot.getNode();
            if (refRootNode != null) {
                setFullPath(root, refRootNode, nodePath, fMRoots);
            }
        }
    }

    /**
     * Create and set a simple path based on the full path.
     * @param root FMC Root Model
     */
    private void setSimplePath(FMCRoot root) {
        root.getNodepaths().forEach(nodePath -> {

            // Get a full pass other than yourself.
            List<String> fullPaths = new ArrayList<>();
            root.getNodepaths().stream().filter(nodePath2 -> !nodePath.equals(nodePath2)).forEach(nodePath2 -> fullPaths.add(nodePath2.getFullpath()));

            // Create a "simplePath" pattern.
            List<String> names = NodeUtil.getInstance().splitNode(nodePath.getFullpath());
            Collections.reverse(names);
            List<String> simplePaths = new ArrayList<>();
            names.forEach(name -> {
                name = NodeUtil.getInstance().getEscapedNodeName(name);
                if (simplePaths.size() > 0) {
                    simplePaths.add(name + DOT + simplePaths.get(simplePaths.size() - 1));
                } else {
                    simplePaths.add(name);
                }
            });

            // Make sure that the end of "FullPath" does not match "SimplePath".
            Optional<String> optSimplePath = simplePaths.stream().filter(str -> {
                Optional<String> optFullPath = fullPaths.stream().filter(fullPath -> fullPath.endsWith(str)).findFirst();
                return !optFullPath.isPresent();
            }).findFirst();

            // If "SimplePath" can not be obtained, set "FullPath".
            if (optSimplePath.isPresent()) {
                nodePath.setSimplePath(optSimplePath.get());
            } else {
                nodePath.setSimplePath(nodePath.getFullpath());
            }
        });
    }
}
