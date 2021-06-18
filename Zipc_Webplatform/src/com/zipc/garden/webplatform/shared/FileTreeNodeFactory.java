package com.zipc.garden.webplatform.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.zipc.garden.webplatform.shared.resource.VMDirectory;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * A class that summarizes the processing related to TreeNode of the file displayed in Project Explorer
 */
public class FileTreeNodeFactory {

    /** Project root directory information */
    private VMDirectory rootVMDirectory;

    /**
     * Refreshes the specified tree and directory information related to the tree.
     * @param editorTabSet List of tabs displayed in the editor
     * @param tree Tree in Project Explorer to refresh
     * @param tabRegs A NativePreviewHandler that receives all events for the tab. key: tab id / value: event
     */
    public void refresh(TabSet editorTabSet, Tree tree, Map<String, HandlerRegistration> tabRegs) {
        refreshTreeView(editorTabSet, tree, tabRegs);
        refreshModelView(tree);
    }

    /**
     * If the TreeNode belonging to the argument tree has been deleted from the directory, the TreeNode will be excluded from
     * the tree.<br>
     * If it has not been deleted, but the TreeNode name has been changed, the TreeNode will be recreated.
     * @param editorTabSet List of tabs displayed in the editor
     * @param tree Tree in Project Explorer to refresh
     * @param tabRegs A NativePreviewHandler that receives all events for the tab. key: tab id / value: event
     */
    public void refreshTreeView(TabSet editorTabSet, Tree tree, Map<String, HandlerRegistration> tabRegs) {
        // Treeから確認
        for (TreeNode node : tree.getAllNodes()) {
            FileTreeNode fnode = (FileTreeNode) node;
            VMResource target = rootVMDirectory.getResource(rootVMDirectory, fnode.getResource().getId());
            if (target == null) {
                TreeNode removeNode = removeCallbackFile(editorTabSet, tree, tabRegs, node);
                if (removeNode != null) {
                    tree.remove(removeNode);
                }
                continue;
            } else if (!target.getName().equals(fnode.getResource().getName())) {
                TreeNode parent = tree.getParent(node);
                TreeNode renameNode = removeCallbackFile(editorTabSet, tree, tabRegs, node);
                tree.add(renameNode, parent);
            }
        }
    }

    /**
     * Refresh the tree information to the latest information.
     * @param tree Tree in Project Explorer to refresh
     */
    public void refreshModelView(Tree tree) {
        // Modelから確認
        List<Long> nodeIds = new ArrayList<Long>();
        correctAllTreeNodeIds(tree, tree.getRoot(), nodeIds);
        checkViewModel(tree, nodeIds, rootVMDirectory);
    }

    /**
     * Set all resource IDs of TreeNode associated with Tree to argument nodeIds.
     * @param tree Project Explorer File Tree
     * @param rootNode The node whose children you want to fetch.
     * @param nodeIds Resource ID setting destination
     */
    private void correctAllTreeNodeIds(Tree tree, TreeNode rootNode, List<Long> nodeIds) {
        for (TreeNode treeNode : tree.getChildren(rootNode)) {
            VMResource fnode = ((FileTreeNode) treeNode).getResource();
            nodeIds.add(fnode.getId());
            correctAllTreeNodeIds(tree, treeNode, nodeIds);
        }
    }

    /**
     * Reconfigure the resources in the specified directory and set the resource ID in the argument nodeIds.
     * @param tree Project Explorer File Tree
     * @param nodeIds Addition destination of resource ID
     * @param parent specified directory
     */
    private void checkViewModel(Tree tree, List<Long> nodeIds, VMDirectory parent) {
        for (VMResource resource : parent.getResources()) {
            if (resource instanceof VMDirectory) {
                checkViewModel(tree, nodeIds, (VMDirectory) resource);
            }
            if (nodeIds.contains(resource.getId())) {
                continue;
            }
            this.getFileTreeNode(tree, parent.getId(), resource);
            nodeIds.add(resource.getId());
        }
    }

    /**
     * Finds the resource that matches the specified targetId and gets the TreeNode associated with the resource.
     * @param tree Search target
     * @param targetId Resource ID to search
     * @return search results. Null if there is no corresponding data.
     */
    public TreeNode findTreeNode(Tree tree, long targetId) {
        if (rootVMDirectory.getId() == targetId) {
            return tree.find("/");
        }
        for (TreeNode treeNode : tree.getAllNodes()) {
            if (((FileTreeNode) treeNode).getResource().getId() == targetId) {
                return treeNode;
            }
        }
        return null;
    }

    /**
     * Removes the tab associated with the specified TreeNode.<br>
     * Also, tab events are excluded from monitoring.
     * @param editorTabSet List of tabs displayed in the editor
     * @param tree Project Explorer File Tree
     * @param tabRegs A NativePreviewHandler that receives all events for the tab. key: tab id / value: event
     * @param record Target of deletion
     * @return The TreeNode associated with the excluded tab
     */
    private TreeNode removeCallbackFile(TabSet editorTabSet, Tree tree, Map<String, HandlerRegistration> tabRegs, TreeNode record) {
        if (record == null)
            return null;
        String tabId = record.getAttribute("UniqueId");
        FileTreeNode target = (FileTreeNode) record;
        VMResource vmResource = target.getResource();
        if (vmResource instanceof VMFile) {
            if (tabId != null && tabRegs.get(tabId) != null) {
                tabRegs.get(tabId).removeHandler();
            }
        }
        editorTabSet.removeTab(tabId);
        return findTreeNode(tree, vmResource.getId());
    }

    /**
     * Set the root directory resource
     * @param root directory resource
     */
    public void setRootDirectory(VMDirectory rootDir) {
        rootVMDirectory = rootDir;
    }

    /**
     * FileTreeNode with specified resource will be created and retrieved.<br>
     * The created FileTreeNode will be added to the specified directory.
     * @param parentId specified directory id
     * @param resources specified resource.
     * @return FileTreeNode with specified resource.
     */
    public TreeNode[] getFileTreeNodes(long parentId, List<VMResource> resources) {
        List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
        VMDirectory parent = (VMDirectory) rootVMDirectory.getResource(rootVMDirectory, parentId);
        if (parent == null)
            return null;
        for (VMResource resource : resources) {
            FileTreeNode fileTreeNode = new FileTreeNode();
            fileTreeNode.setResource(resource);
            if (resource instanceof VMFile && ((VMFile) resource).getExtension() != null) {
                fileTreeNode.setIcon(((VMFile) resource).getExtension().getImgPath());
            }
            if (parent.getResource(rootVMDirectory, resource.getId()) == null) {
                parent.addResource(resource);
            }
            treeNodeList.add(fileTreeNode);
        }
        return treeNodeList.toArray(new TreeNode[treeNodeList.size()]);
    }

    /**
     * Get the resource associated with the FileTreeNode.
     * @param node FileTreeNode
     * @return resource associated with the FileTreeNode.
     */
    public VMResource getResource(TreeNode node) {
        VMResource resource = ((FileTreeNode) node).getResource();
        return resource;
    }

    /**
     * Adds the specified resource to the directory that matches the parentId.<br>
     * If the resource specified in tree has not been created, a TreeNode with the specified resource will be added to tree.<br>
     * It has been created, but if the resource name is different, it will be reconfigured.
     * @param tree Project Explorer File Tree
     * @param parentId ID of the directory to add the resource to
     * @param resource Resources to add
     * @return TreeNode with added resources
     */
    public TreeNode getFileTreeNode(Tree tree, long parentId, VMResource resource) {
        FileTreeNode fileTreeNode = new FileTreeNode();
        fileTreeNode.setResource(resource);
        if (resource instanceof VMFile && ((VMFile) resource).getExtension() != null) {
            fileTreeNode.setIcon(((VMFile) resource).getExtension().getImgPath());
        }
        VMDirectory parent = (VMDirectory) rootVMDirectory.getResource(rootVMDirectory, parentId);
        if (parent == null)
            return null;
        FileTreeNode node = (FileTreeNode) findTreeNode(tree, resource.getId());
        if (parent.getResource(rootVMDirectory, resource.getId()) == null) {
            parent.addResource(resource);
            if (parent.getId() == rootVMDirectory.getId()) {
                tree.add(fileTreeNode, tree.find("/"));
            } else {
                tree.add(fileTreeNode, findTreeNode(tree, parent.getId()));
            }
        } else if (node != null && !resource.getName().equals(tree.getName(node))) {
            parent.removeResource(rootVMDirectory, resource.getId());
            parent.addResource(resource);
            node.setResource(resource);
        }
        return fileTreeNode;
    }

    /**
     * Delete the resource that matches the targetId.
     * @param targetId The ID of the resource to delete.
     */
    public void removeVMResource(long targetId) {
        rootVMDirectory.removeResource(rootVMDirectory, targetId);
    }

    /**
     * The resource (targetId) will be moved to the specified directory (parentId).
     * @param parentId The ID of the directory to move to.
     * @param targetId The ID of the resource to move.
     */
    public void moveVMResource(long parentId, long targetId) {
        VMResource target = rootVMDirectory.removeResource(rootVMDirectory, targetId);
        VMDirectory parent = (VMDirectory) rootVMDirectory.getResource(rootVMDirectory, parentId);
        parent.getResources().add(target);
    }

    /**
     * Gets the ID of the parent directory of the resource that matches the specified targetId.
     * @param targetId The specified targetId
     * @return ID of parent directory
     */
    public long getParentDirId(Long targetId) {
        return rootVMDirectory.getParent(rootVMDirectory, targetId).getId();
    }
}
