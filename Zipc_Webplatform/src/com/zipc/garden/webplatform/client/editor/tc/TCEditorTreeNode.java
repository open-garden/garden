package com.zipc.garden.webplatform.client.editor.tc;

import com.smartgwt.client.widgets.tree.TreeNode;
import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.tc.TCNode;

/**
 * A class that creates a TreeNode to be displayed in the TPS editor.
 */
public class TCEditorTreeNode extends TreeNode {

    /**
     * constructor.<br>
     * Create the data to be displayed in Tree of TPS Editor.
     * @param pk Primary key
     * @param name TCNode name
     * @param type TCNode child type (AND or XOR)
     * @param tcNode TCNode information
     * @param isCheck TreeNode selection flag
     * @param isOpen Whether the child elements of TreeNode are collapsed
     */
    public TCEditorTreeNode(String pk, String name, ChildType type, TCNode tcNode, boolean isCheck, boolean isOpen) {
        setAttribute("pk", pk);
        String parent = pk.substring(0, pk.lastIndexOf(name));
        setAttribute("parentId", parent.substring(0, parent.lastIndexOf("/")));
        String showName = name;
        if (type != null) {
            showName += "(" + type.getName() + ")";
        } else if (tcNode.isOptional()) {
            showName += "(Optional)";
        }
        // add cardinality
        if (tcNode.getChildType().equals(ChildType.XOR) && tcNode.getMin() != -1) {
            String max = Integer.toString(tcNode.getMax());
            if (tcNode.getMax() == -1) {
                max = "*";
            }
            showName += "[" + tcNode.getMin() + ".." + max + "]";
        }
        setAttribute("Name", showName);
        setAttribute("TCNode", tcNode);
        setAttribute("checkbox", isCheck);
        setAttribute("isOpen", isOpen);
        set_baseStyle("treeCell_TCEditor");
    }
}
