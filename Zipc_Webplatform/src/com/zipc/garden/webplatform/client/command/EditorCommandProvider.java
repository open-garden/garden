package com.zipc.garden.webplatform.client.command;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.common.command.CompoundCommand;

import com.zipc.garden.core.ZGAddCommand;
import com.zipc.garden.core.ZGRemoveCommand;
import com.zipc.garden.core.ZGSetCommand;
import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractLine;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.AbstractStyle;
import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.core.LineType;
import com.zipc.garden.model.core.Memo;

/**
 * This class manages commands for common operations in the editor.
 */
public final class EditorCommandProvider {

    /**
     * private constructor to block the instantiation from other class.
     */
    private EditorCommandProvider() {
    }

    /**
     * method to get the instance
     * @return This class
     */
    public static EditorCommandProvider getInstance() {
        return EditorCommandProvideryHolder.PROVIDER;
    }

    /**
     * class to hold the instance as final
     */
    private static class EditorCommandProvideryHolder {
        private static final EditorCommandProvider PROVIDER = new EditorCommandProvider();
    }

    /**
     * Create a command to add a note to the Editor.
     * @param root
     * @param memo
     * @return Created command
     */
    public final synchronized CompoundCommand addMemo(AbstractDiagram root, Memo memo) {
        CompoundCommand cmd = new CompoundCommand();
        ZGAddCommand command = new ZGAddCommand(root, COREPackage.Literals.ABSTRACT_DIAGRAM__NODES, memo, root.getNodes().size());
        cmd.append(command);
        return cmd;
    }

    /**
     * Create a command to edit the memo in the editor.
     * @param memo
     * @param newValue
     * @return Created command
     */
    public final synchronized CompoundCommand editMemo(Memo memo, String newValue) {
        CompoundCommand cmd = new CompoundCommand();
        ZGSetCommand command = new ZGSetCommand(memo, COREPackage.Literals.MEMO__TEXT, newValue);
        cmd.append(command);
        return cmd;
    }

    /**
     * Create a command to delete a note on the editor.
     * @param root
     * @param memo
     * @return Created command
     */
    public final synchronized CompoundCommand deleteMemo(AbstractDiagram root, Memo memo) {
        CompoundCommand cmd = new CompoundCommand();
        ZGRemoveCommand command = new ZGRemoveCommand(root, COREPackage.Literals.ABSTRACT_DIAGRAM__NODES, memo);
        cmd.append(command);
        return cmd;
    }

    /**
     * Create a command to resize the memo rectangle.
     * @param memo
     * @param left
     * @param top
     * @param width
     * @param height
     * @return Created command
     */
    public final synchronized CompoundCommand resizeMemo(Memo memo, int left, int top, int width, int height) {
        CompoundCommand cmd = new CompoundCommand();
        ZGSetCommand command1 = new ZGSetCommand(memo, COREPackage.Literals.ABSTRACT_NODE__LEFT, left);
        ZGSetCommand command2 = new ZGSetCommand(memo, COREPackage.Literals.ABSTRACT_NODE__TOP, top);
        ZGSetCommand command3 = new ZGSetCommand(memo, COREPackage.Literals.ABSTRACT_NODE__WIDTH, width);
        ZGSetCommand command4 = new ZGSetCommand(memo, COREPackage.Literals.ABSTRACT_NODE__HEIGHT, height);
        cmd.append(command1);
        cmd.append(command2);
        cmd.append(command3);
        cmd.append(command4);
        return cmd;
    }

    /**
     * Create a command to change the bend position of the Manhattan line.
     * @param cmd
     * @param line
     * @param points
     * @return Created command
     */
    public final synchronized CompoundCommand addPoint(CompoundCommand cmd, AbstractLine line, List<AbstractPoint> points) {
        line.getConnectionPoint().forEach(point -> {
            ZGRemoveCommand command = new ZGRemoveCommand(line, COREPackage.Literals.ABSTRACT_LINE__CONNECTION_POINT, point);
            cmd.append(command);
        });
        AtomicInteger count = new AtomicInteger(0);
        points.forEach(point -> {
            ZGAddCommand command = new ZGAddCommand(line, COREPackage.Literals.ABSTRACT_LINE__CONNECTION_POINT, point, count.getAndIncrement());
            cmd.append(command);
        });
        return cmd;
    }

    /**
     * Create a command to change the transition line type.
     * @param cmd
     * @param line
     * @param lineType
     * @return Created command
     */
    public final synchronized CompoundCommand changeLine(CompoundCommand cmd, AbstractLine line, LineType lineType) {
        ZGSetCommand command = new ZGSetCommand(line, COREPackage.Literals.ABSTRACT_LINE__TYPE, lineType);
        cmd.append(command);

        return cmd;
    }

    /**
     * Create a command to change the font size of the node.
     * @param cmd
     * @param node
     * @param fontSize
     * @return Created command
     */
    public final synchronized CompoundCommand setNodeFontSize(CompoundCommand cmd, AbstractStyle node, int fontSize) {
        ZGSetCommand command = new ZGSetCommand(node, COREPackage.Literals.ABSTRACT_STYLE__FONT_SIZE, fontSize);
        cmd.append(command);

        return cmd;
    }

    /**
     * Create a command to change the fill color of the node.
     * @param cmd
     * @param node
     * @param color
     * @return Created command
     */
    public final synchronized CompoundCommand setNodeFillColor(CompoundCommand cmd, AbstractStyle node, String color) {
        ZGSetCommand command = new ZGSetCommand(node, COREPackage.Literals.ABSTRACT_STYLE__FILL_COLOR, color);
        cmd.append(command);

        return cmd;
    }

    /**
     * Create a command to change the node font color.
     * @param cmd
     * @param node
     * @param color
     * @return Created command
     */
    public final synchronized CompoundCommand setNodeFontColor(CompoundCommand cmd, AbstractStyle node, String color) {
        ZGSetCommand command = new ZGSetCommand(node, COREPackage.Literals.ABSTRACT_STYLE__FONT_COLOR, color);
        cmd.append(command);

        return cmd;
    }
}
