package com.zipc.garden.webplatform.client.command;

import java.util.List;
import java.util.Optional;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.zipc.garden.core.ZGAddCommand;
import com.zipc.garden.core.ZGRemoveCommand;
import com.zipc.garden.core.ZGSetCommand;
import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMPackage;
import com.zipc.garden.model.fm.FMProperty;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.webplatform.client.editor.fm.FMEditor;

/**
 * This class manages the commands operated by FMEditor.
 */
public final class FMEditorCommandProvider {

    /**
     * private constructor to block the instantiation from other class.
     */
    private FMEditorCommandProvider() {
    }

    /**
     * method to get the instance
     * @return This class
     */
    public static FMEditorCommandProvider getInstance() {
        return FMEditorCommandProvideryHolder.PROVIDER;
    }

    /**
     * class to hold the instance as final
     */
    private static class FMEditorCommandProvideryHolder {
        private static final FMEditorCommandProvider PROVIDER = new FMEditorCommandProvider();
    }

    /**
     * Create a command to change the coordinate position of the rectangle of FMNode.
     * @param nodeList
     * @param moveY
     * @param moveX
     * @return Created command
     */
    public final synchronized CompoundCommand moveNodes(List<FMNode> nodeList, int moveY, int moveX) {
        CompoundCommand cmd = new CompoundCommand();
        for (FMNode node : nodeList) {
            ZGSetCommand command1 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__TOP, node.getTop() + moveY);
            ZGSetCommand command2 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__LEFT, node.getLeft() + moveX);
            cmd.append(command1);
            cmd.append(command2);
        }
        return cmd;
    }

    /**
     * Create a command to resize the FMNode rectangle.
     * @param nodeList
     * @param resizeY
     * @param resizeX
     * @param resizeW
     * @param resizeH
     * @return Created command
     */
    public final synchronized CompoundCommand resizeNodes(List<FMNode> nodeList, int resizeY, int resizeX, int resizeW, int resizeH) {
        CompoundCommand cmd = new CompoundCommand();
        for (FMNode node : nodeList) {
            ZGSetCommand command1 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__TOP, node.getTop() + resizeY);
            ZGSetCommand command2 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__LEFT, node.getLeft() + resizeX);
            ZGSetCommand command3 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__WIDTH, node.getWidth() + resizeW);
            ZGSetCommand command4 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__HEIGHT, node.getHeight() + resizeH);
            cmd.append(command1);
            cmd.append(command2);
            cmd.append(command3);
            cmd.append(command4);
        }
        return cmd;
    }

    /**
     * Create a command to add a new FMNode rectangle.
     * @param root
     * @param parentNode
     * @param childNode
     * @param addPosition
     * @return Created command
     */
    public final synchronized CompoundCommand addNode(FMRoot root, FMNode parentNode, FMNode childNode, int addPosition) {
        CompoundCommand cmd = new CompoundCommand();

        if (root.getNode() == null) {
            ZGSetCommand command = new ZGSetCommand(root, FMPackage.Literals.FM_ROOT__NODE, parentNode);
            cmd.append(command);
        }

        ZGAddCommand command = new ZGAddCommand(parentNode, FMPackage.Literals.FM_NODE__CHILDREN, childNode, addPosition);
        cmd.append(command);

        return cmd;
    }

    /**
     * Create a rename command for FMNode.
     * @param node
     * @param newValue
     * @return Created command
     */
    public final synchronized CompoundCommand renameNode(FMNode node, String newValue) {
        CompoundCommand cmd = new CompoundCommand();

        ZGSetCommand command = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__NAME, newValue);
        cmd.append(command);

        return cmd;
    }

    /**
     * Create a command to change the FMNode type (AND/XOR).
     * @param node
     * @param newValue
     * @return Created command
     */
    public final synchronized CompoundCommand changeChildType(FMNode node, ChildType newValue) {
        CompoundCommand cmd = new CompoundCommand();

        ZGSetCommand command = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__CHILD_TYPE, newValue);
        cmd.append(command);

        return cmd;
    }

    /**
     * Create a command to delete the FMNode.
     * @param root
     * @param pairList
     * @param selectNodes
     * @param childNodes
     * @return Created command
     */
    public final synchronized CompoundCommand removeNodes(FMRoot root, List<FMNode[]> pairList, List<FMNode> selectNodes, List<FMNode[]> childNodes) {
        CompoundCommand cmd = new CompoundCommand();
        int rootNodeChildDelCnt = 0;

        for (FMNode[] node : childNodes) {
            selectNodes.remove(node[1]);

            ZGRemoveCommand command = new ZGRemoveCommand(node[0], FMPackage.Literals.FM_NODE__CHILDREN, node[1]);
            cmd.append(command);
        }
        for (FMNode selNode : selectNodes) {
            Optional<FMNode[]> parentNode = pairList.stream().filter(nodes -> nodes[1].hashCode() == selNode.hashCode()).findFirst();
            if (!parentNode.isPresent()) {
                ZGSetCommand command = new ZGSetCommand(root, FMPackage.Literals.FM_ROOT__NODE, null);
                cmd.append(command);
            } else {
                ZGRemoveCommand command = new ZGRemoveCommand(parentNode.get()[0], FMPackage.Literals.FM_NODE__CHILDREN, selNode);
                cmd.append(command);
                if (parentNode.get()[0].hashCode() == root.getNode().hashCode()) {
                    rootNodeChildDelCnt++;
                }
            }
        }
        if (root.getNode() != null) {
            if (root.getNode().getChildren().size() == rootNodeChildDelCnt) {
                ZGSetCommand command = new ZGSetCommand(root, FMPackage.Literals.FM_ROOT__NODE, null);
                cmd.append(command);
            }
        }

        return cmd;
    }

    /**
     * Create a command to change the position of the FMNode's elements.
     * @param parentNode
     * @param nowPos
     * @param position
     * @return Created command
     */
    public final synchronized CompoundCommand moveNodePosition(FMNode parentNode, int nowPos, int position) {
        CompoundCommand cmd = new CompoundCommand();

        FMNode nowNode = parentNode.getChildren().get(nowPos);

        ZGRemoveCommand command1 = new ZGRemoveCommand(parentNode, FMPackage.Literals.FM_NODE__CHILDREN, nowNode);
        cmd.append(command1);

        ZGAddCommand command2 = new ZGAddCommand(parentNode, FMPackage.Literals.FM_NODE__CHILDREN, nowNode, nowPos + position);
        cmd.append(command2);

        return cmd;
    }

    /**
     * Create a command to connect the transition line of FMNode to another FMNode.
     * @param parentNode
     * @param childNode
     * @return Created command
     */
    public final synchronized CompoundCommand resizeDrawLine(FMNode parentNode, FMNode childNode) {
        CompoundCommand cmd = new CompoundCommand();

        ZGRemoveCommand command = new ZGRemoveCommand(childNode.eContainer(), FMPackage.Literals.FM_NODE__CHILDREN, childNode);
        cmd.append(command);

        ZGAddCommand command2 = new ZGAddCommand(parentNode, FMPackage.Literals.FM_NODE__CHILDREN, childNode, parentNode.getChildren().size());
        cmd.append(command2);

        return cmd;
    }

    /**
     * Create a command that reflects the editing of the FMNode's cardinality.
     * @param editor
     * @param node
     * @param min
     * @param max
     * @param x
     * @param y
     * @return Created command
     */
    public final synchronized CompoundCommand editCardinality(FMEditor editor, FMNode node, int min, int max, int x, int y) {
        CompoundCommand cmd = new CompoundCommand();
        cmd = editCardinality(editor, node, min, max, x, y, cmd);
        return cmd;
    }

    /**
     * Create a command that reflects the editing of the FMNode's cardinality.<br>
     * The created command will be added to the command received as an argument.
     * @param editor
     * @param node
     * @param min
     * @param max
     * @param x
     * @param y
     * @param cmd
     * @return Created command
     */
    public final synchronized CompoundCommand editCardinality(FMEditor editor, FMNode node, int min, int max, int x, int y, CompoundCommand cmd) {
        ZGSetCommand command1 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__MIN, min);
        ZGSetCommand command2 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__MAX, max);
        ZGSetCommand command3 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__X, x);
        ZGSetCommand command4 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__Y, y);

        cmd.append(command1);
        cmd.append(command2);
        cmd.append(command3);
        cmd.append(command4);
        return cmd;
    }

    /**
     * Create a command to change the display position of the cardinality.
     * @param editor
     * @param node
     * @param position
     * @return Created command
     */
    public final synchronized CompoundCommand moveCardinality(FMEditor editor, FMNode node, int[] position) {
        CompoundCommand cmd = new CompoundCommand();
        ZGSetCommand command1 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__X, position[0]);
        ZGSetCommand command2 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__Y, position[1]);

        cmd.append(command1);
        cmd.append(command2);
        return cmd;
    }

    /**
     * Create a command that reflects the Optional settings of FMNode.
     * @param node
     * @param newValue
     * @return Created command
     */
    public final synchronized CompoundCommand changeOptional(FMNode node, boolean newValue) {
        CompoundCommand cmd = new CompoundCommand();
        ZGSetCommand command = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__OPTIONAL, newValue);
        cmd.append(command);
        return cmd;
    }

    /**
     * Create a command that reflects the property settings of FMNode.
     * @param cmd
     * @param fMProperty
     * @param feature
     * @param oldValue
     * @param newValue
     */
    public final synchronized void setProperty(CompoundCommand cmd, FMProperty fMProperty, EStructuralFeature feature, Object oldValue, Object newValue) {
        ZGSetCommand command = new ZGSetCommand(fMProperty, feature, newValue);
        cmd.append(command);
    }

    /**
     * Create a command to add a new FMRoot property.
     * @param cmd
     * @param root
     * @param fMProperty
     * @param addPosition
     */
    public final synchronized void addProperty(CompoundCommand cmd, FMRoot root, FMProperty fMProperty, int addPosition) {
        ZGAddCommand command = new ZGAddCommand(root, FMPackage.Literals.FM_ROOT__PROPERTIES, fMProperty, addPosition);
        cmd.append(command);
    }

    /**
     * Create a command to add a new FMNode property.
     * @param cmd
     * @param node
     * @param fMProperty
     * @param addPosition
     */
    public final synchronized void addProperty(CompoundCommand cmd, FMNode node, FMProperty fMProperty, int addPosition) {
        ZGAddCommand command = new ZGAddCommand(node, FMPackage.Literals.FM_NODE__PROPERTIES, fMProperty, addPosition);
        cmd.append(command);
    }

    /**
     * Create a command that removes the properties of FMRoot.
     * @param cmd
     * @param root
     * @param fMProperties
     */
    public final synchronized void removeProperties(CompoundCommand cmd, FMRoot root, List<FMProperty> fMProperties) {
        fMProperties.forEach(fMProperty -> {
            ZGRemoveCommand command = new ZGRemoveCommand(root, FMPackage.Literals.FM_ROOT__PROPERTIES, fMProperty);
            cmd.append(command);
        });
    }

    /**
     * Create a command that removes the properties of FMNode.
     * @param cmd
     * @param node
     * @param fMProperties
     */
    public final synchronized void removeProperties(CompoundCommand cmd, FMNode node, List<FMProperty> fMProperties) {
        fMProperties.forEach(fMProperty -> {
            ZGRemoveCommand command = new ZGRemoveCommand(node, FMPackage.Literals.FM_NODE__PROPERTIES, fMProperty);
            cmd.append(command);
        });
    }

    /**
     * Create a command to create the reference node information.
     * @param node
     * @param ref
     * @param refName
     * @param refInfo
     * @param refUuid
     * @return Created command
     */
    public final synchronized CompoundCommand setReferenceNode(FMNode node, long ref, String refName, String refInfo, String refUuid) {
        CompoundCommand cmd = new CompoundCommand();

        ZGSetCommand command1 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__REF, ref);
        ZGSetCommand command2 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__REF_NAME, refName);
        ZGSetCommand command3 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__REF_INFO, refInfo);
        ZGSetCommand command4 = new ZGSetCommand(node, FMPackage.Literals.FM_NODE__REFUUID, refUuid);

        cmd.append(command1);
        cmd.append(command2);
        cmd.append(command3);
        cmd.append(command4);

        return cmd;
    }
}
