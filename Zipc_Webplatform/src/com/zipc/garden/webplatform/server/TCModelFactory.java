package com.zipc.garden.webplatform.server;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;

import com.zipc.garden.model.tc.TCFactory;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCNodeState;
import com.zipc.garden.model.tc.TCRoot;

/**
 * This class creates a TCRoot that summarizes the differences between the old TCRoot and the new TCRoot.
 */
public class TCModelFactory {

    /**
     * Creates a diff between the old TCRoot and the newly created TCRoot.
     * @param oldRoot old TCRoot
     * @param newRoot newly created TCRoot
     * @return TCRoot that summarizes the difference between oldRoot and newRoot
     */
    public TCRoot createTCRoot(TCRoot oldRoot, TCRoot newRoot) {
        TCRoot mergedRoot = TCFactory.eINSTANCE.createTCRoot();
        EList<TCNode> mergedRootNodes = createTCNodes(oldRoot.getRootNodes(), newRoot);
        mergedRoot.getRootNodes().addAll(mergedRootNodes);
        return mergedRoot;
    }

    /**
     * Creates the diff of the specified argument.
     * @param rootNodes Root node of old TCRoot
     * @param newRoot newly created TCRoot
     * @return TCNode after merging
     */
    public EList<TCNode> createTCNodes(EList<TCNode> rootNodes, TCRoot newRoot) {
        TCDiffNode newDiffRoot = new TCDiffNode();
        TCDiffNode oldDiffRoot = new TCDiffNode();
        newRoot.getRootNodes().forEach(n -> {
            new TCDiffNode(newDiffRoot, n);
        });
        rootNodes.forEach(n -> {
            new TCDiffNode(oldDiffRoot, n);
        });
        newDiffRoot.merge(oldDiffRoot);
        TCNode mergedNode = newDiffRoot.createTCNode();
        return mergedNode.getChildren();
    }
}

/**
 * This class manages the differences of TCNode.
 */
class TCDiffNode {

    /** TCNode subject to difference management */
    private TCNode node;

    /** Unique node string */
    private String name;

    /** Difference information of child nodes */
    private Map<String, TCDiffNode> children = new LinkedHashMap<>();

    /** TCDiffNode, the parent of {@link #node} */
    private TCDiffNode parent;

    /** True for newly added nodes */
    private boolean added;

    /** True for deleted nodes */
    private boolean deleted;

    /** True if the node is checked */
    private boolean isChecked;

    /**
     * constructor.<br>
     * Used when instantiating this class.
     */
    public TCDiffNode() {

    }

    /**
     * Create a new TCNode based on the current difference information of this class.
     * @return Created TCNode
     */
    public TCNode createTCNode() {
        TCNode createdNode = TCFactory.eINSTANCE.createTCNode();
        if (node != null) {
            createdNode.setName(node.getName());
            createdNode.setOptional(node.isOptional());
            createdNode.setChecked(node.isChecked());
            createdNode.setInherited(node.isInherited());
            createdNode.setChildType(node.getChildType());
            createdNode.setMin(node.getMin());
            createdNode.setMax(node.getMax());
            createdNode.setNWiseCombination(node.getNWiseCombination());
            createdNode.getProperties().addAll(node.getProperties());
        }
        if (added) {
            createdNode.setState(TCNodeState.NEW);
        } else if (deleted) {
            createdNode.setState(TCNodeState.DELETED);
        } else {
            createdNode.setState(TCNodeState.UNCHANGED);
        }
        if (isChecked) {
            createdNode.setChecked(isChecked());
        }
        children.forEach((name, child) -> {
            TCNode node = child.createTCNode();
            createdNode.getChildren().add(node);
        });

        return createdNode;
    }

    /**
     * Gets the parent TCDiffNode.
     * @return {@link #parent}
     */
    public TCDiffNode getParent() {
        return parent;
    }

    /**
     * Gets a flag indicating whether a node is added.
     * @return {@link #added}
     */
    public boolean isAdded() {
        return added;
    }

    /**
     * Gets a flag indicating whether a node is deleted.
     * @return {@link #deleted}
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Gets a flag indicating whether a node is selected.
     * @return {@link #isChecked}
     */
    public boolean isChecked() {
        return isChecked;
    }

    /**
     * Merges the TCDiffNode managed by this class with the specified TCDiffNode.
     * @param oldDiff specified TCDiffNode
     */
    public void merge(TCDiffNode oldDiff) {
        children.forEach((name, child) -> {
            if (oldDiff.has(name)) {
                // exists both side
                child.setIsChecked(oldDiff.get(name).isChecked());
                child.merge(oldDiff.get(name));
            } else {
                // added.
                child.setAdded(true);
            }
        });
        oldDiff.getChildren().forEach((name, child) -> {
            if (!has(name)) {
                // deleted.
                TCDiffNode copied = child.copy();
                copied.setDeleted(true);
                copied.setParent(TCDiffNode.this);
                addChild(name, copied);
            }
        });

    }

    /**
     * Set the parent TCDiffNode to {@link #parent}.
     * @param parent parent TCDiffNode.
     */
    private void setParent(TCDiffNode parent) {
        this.parent = parent;
    }

    /**
     * Sets a flag to determine if a child node is a deleted node.
     * @param deleted If True, it is a deleted node.
     */
    private void setDeleted(boolean deleted) {
        this.deleted = deleted;
        children.forEach((name, child) -> {
            child.setDeleted(deleted);
        });
    }

    /**
     * The node check flag is set.
     * @param isChecked If True, the node is checked.
     */
    private void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    /**
     * A copy of {@link #node} is made.<br>
     * It is not associated with the parent node.
     * @return Copyed TCDiffNode
     */
    private TCDiffNode copy() {
        TCDiffNode copied = new TCDiffNode(null, node);
        return copied;
    }

    /**
     * Acquires the difference information of the child node.
     * @return {@link #children}
     */
    public Map<String, TCDiffNode> getChildren() {
        return children;
    }

    /**
     * Sets a flag to determine if a child node is a newly added node.
     * @param added If True, it is a newly added node.
     */
    private void setAdded(boolean added) {
        this.added = added;
        children.forEach((name, child) -> {
            child.setAdded(added);
        });
    }

    /**
     * Check if the specified unique node string is added to {@link #children}.
     * @param key Unique node string
     * @return True if present.
     */
    private boolean has(String key) {
        return children.containsKey(key);
    }

    /**
     * constructor.<br>
     * A child node is added to the specified TCDiffNode.<br>
     * The differences between the added child nodes are also managed.<br>
     * @param parent Parent TCDiffNode
     * @param node Child TCNode
     */
    public TCDiffNode(TCDiffNode parent, TCNode node) {
        this.node = node;
        this.name = node.getName() + node.getChildType().getName() + (node.isOptional() ? "Optional" : "") + node.getMin() + node.getMax();
        this.isChecked = node.isChecked();
        setParent(parent);
        if (parent != null) {
            parent.addChild(name, this);
        }
        node.getChildren().forEach(n -> {
            new TCDiffNode(TCDiffNode.this, n);
        });
    }

    /**
     * Set the specified content to {@link #children}.
     * @param childName Unique node string
     * @param diff TCDiffNode associated with a unique node string
     */
    private void addChild(String childName, TCDiffNode diff) {
        if (children.containsKey(childName)) {
            throw new RuntimeException("duplicated name:" + childName);
        }
        children.put(childName, diff);
    }

    /**
     * Gets the TCDiffNode associated with the specified unique node string.
     * @param name Unique node string
     * @return TCDiffNode
     */
    public TCDiffNode get(String name) {
        return children.get(name);
    }

}
