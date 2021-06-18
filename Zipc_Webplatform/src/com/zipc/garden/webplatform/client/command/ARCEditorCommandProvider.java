package com.zipc.garden.webplatform.client.command;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.common.command.CompoundCommand;

import com.zipc.garden.core.ZGAddCommand;
import com.zipc.garden.core.ZGRemoveCommand;
import com.zipc.garden.core.ZGSetCommand;
import com.zipc.garden.model.arc.ARCLine;
import com.zipc.garden.model.arc.ARCPackage;
import com.zipc.garden.model.arc.ARCRoot;
import com.zipc.garden.model.arc.ARCState;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.COREPackage;

/**
 * This class manages the commands operated by ARCEditor.
 */
public final class ARCEditorCommandProvider {

    /**
     * private constructor to block the instantiation from other class.
     */
    private ARCEditorCommandProvider() {
    }

    /**
     * method to get the instance
     * @return This class
     */
    public static ARCEditorCommandProvider getInstance() {
        return ARCEditorCommandProvideryHolder.PROVIDER;
    }

    /**
     * class to hold the instance as final
     */
    private static class ARCEditorCommandProvideryHolder {
        private static final ARCEditorCommandProvider PROVIDER = new ARCEditorCommandProvider();
    }

    /**
     * Create a command to change the coordinates of the ARCState rectangle.
     * @param states
     * @param lines
     * @param moveY
     * @param moveX
     * @return Created command
     */
    public final synchronized CompoundCommand moveStates(List<ARCState> states, Map<ARCLine, List<AbstractPoint>> lines, int moveY, int moveX) {
        CompoundCommand cmd = new CompoundCommand();
        states.forEach(state -> {
            ZGSetCommand command1 = new ZGSetCommand(state, ARCPackage.Literals.ARC_VERTEX__TOP, state.getTop() + moveY);
            ZGSetCommand command2 = new ZGSetCommand(state, ARCPackage.Literals.ARC_VERTEX__LEFT, state.getLeft() + moveX);
            cmd.append(command1);
            cmd.append(command2);
        });
        lines.entrySet().forEach(entry -> {
            entry.getKey().getConnectionPoint().forEach(point -> {
                ZGRemoveCommand command = new ZGRemoveCommand(entry.getKey(), COREPackage.Literals.ABSTRACT_LINE__CONNECTION_POINT, point);
                cmd.append(command);
            });
            AtomicInteger count = new AtomicInteger(0);
            entry.getValue().forEach(point -> {
                ZGAddCommand command = new ZGAddCommand(entry.getKey(), COREPackage.Literals.ABSTRACT_LINE__CONNECTION_POINT, point, count.getAndIncrement());
                cmd.append(command);
            });
        });
        return cmd;
    }

    /**
     * Create a command to resize the ARCState rectangle.
     * @param states
     * @param trans
     * @param resizeY
     * @param resizeX
     * @param resizeW
     * @param resizeH
     * @return Created command
     */
    public final synchronized CompoundCommand resizeStates(List<ARCState> states, Map<ARCLine, List<AbstractPoint>> lines, int resizeY, int resizeX, int resizeW, int resizeH) {
        CompoundCommand cmd = new CompoundCommand();
        states.forEach(state -> {
            ZGSetCommand command1 = new ZGSetCommand(state, ARCPackage.Literals.ARC_VERTEX__TOP, state.getTop() + resizeY);
            ZGSetCommand command2 = new ZGSetCommand(state, ARCPackage.Literals.ARC_VERTEX__LEFT, state.getLeft() + resizeX);
            ZGSetCommand command3 = new ZGSetCommand(state, ARCPackage.Literals.ARC_VERTEX__WIDTH, state.getWidth() + resizeW);
            ZGSetCommand command4 = new ZGSetCommand(state, ARCPackage.Literals.ARC_VERTEX__HEIGHT, state.getHeight() + resizeH);
            cmd.append(command1);
            cmd.append(command2);
            cmd.append(command3);
            cmd.append(command4);
        });
        lines.entrySet().forEach(entry -> {
            entry.getKey().getConnectionPoint().forEach(point -> {
                ZGRemoveCommand command = new ZGRemoveCommand(entry.getKey(), COREPackage.Literals.ABSTRACT_LINE__CONNECTION_POINT, point);
                cmd.append(command);
            });
            AtomicInteger count = new AtomicInteger(0);
            entry.getValue().forEach(point -> {
                ZGAddCommand command = new ZGAddCommand(entry.getKey(), COREPackage.Literals.ABSTRACT_LINE__CONNECTION_POINT, point, count.getAndIncrement());
                cmd.append(command);
            });
        });
        return cmd;
    }

    /**
     * Create a command to add a single new ARCState.
     * @param root
     * @param state
     * @param addPosition
     * @return Created command
     */
    public final synchronized CompoundCommand addState(ARCRoot root, ARCState state, int addPosition) {
        CompoundCommand cmd = new CompoundCommand();
        ZGAddCommand command = new ZGAddCommand(root, ARCPackage.Literals.ARC_ROOT__STATES, state, addPosition);
        cmd.append(command);
        return cmd;
    }

    /**
     * Create a command to add multiple new ARCStates.
     * @param root
     * @param states
     * @return Created command
     */
    public final synchronized CompoundCommand addState(ARCRoot root, List<ARCState> states) {
        CompoundCommand cmd = new CompoundCommand();
        AtomicInteger addPosition = new AtomicInteger(root.getStates().size());
        states.forEach(state -> {
            ZGAddCommand command = new ZGAddCommand(root, ARCPackage.Literals.ARC_ROOT__STATES, state, addPosition.getAndIncrement());
            cmd.append(command);
        });
        return cmd;
    }

    /**
     * Create a command to change the priority of ARCState.
     * @param cmd
     * @param state
     * @param priority
     */
    public final synchronized void savePriorityState(CompoundCommand cmd, ARCState state, int priority) {
        ZGSetCommand command = new ZGSetCommand(state, ARCPackage.Literals.ARC_STATE__EVAL_PRIORITY, priority);
        cmd.append(command);
    }

    /**
     * Create a command to rename ARCState.
     * @param state
     * @param newValue
     * @return Created command
     */
    public final synchronized CompoundCommand renameState(ARCState state, String newValue) {
        CompoundCommand cmd = new CompoundCommand();
        ZGSetCommand command = new ZGSetCommand(state, ARCPackage.Literals.ARC_STATE__NAME, newValue);
        cmd.append(command);
        return cmd;
    }

    /**
     * Create a command to remove ARCState.
     * @param root
     * @param states
     * @param lines
     * @return Created command
     */
    public final synchronized CompoundCommand removeStates(ARCRoot root, List<ARCState> states, List<ARCLine> lines) {
        CompoundCommand cmd = new CompoundCommand();
        states.forEach(state -> {
            ZGRemoveCommand command = new ZGRemoveCommand(root, ARCPackage.Literals.ARC_ROOT__STATES, state);
            cmd.append(command);
        });
        lines.forEach(line -> {
            ZGRemoveCommand command = new ZGRemoveCommand(root, ARCPackage.Literals.ARC_ROOT__LINES, line);
            cmd.append(command);
            ZGRemoveCommand command2 = new ZGRemoveCommand(line.getSource(), ARCPackage.Literals.ARC_STATE__OUTGOING_LINE, line);
            cmd.append(command2);
            ZGRemoveCommand command3 = new ZGRemoveCommand(line.getTarget(), ARCPackage.Literals.ARC_STATE__INCOMING_LINE, line);
            cmd.append(command3);
        });
        return cmd;
    }

    /**
     * Create a command to create a transition line connecting ARCState.
     * @param line
     * @param root
     * @param source
     * @param target
     * @param oldLine
     * @param name
     * @param type
     * @return Created command
     */
    public final synchronized CompoundCommand addLine(ARCLine line, ARCRoot root, ARCState source, ARCState target, ARCLine oldLine, String name, String type) {
        CompoundCommand cmd = new CompoundCommand();

        line.setVariableName(name);
        line.setVariableType(type);

        ZGAddCommand command = new ZGAddCommand(source, ARCPackage.Literals.ARC_STATE__OUTGOING_LINE, line, source.getOutgoingLine().size());
        cmd.append(command);

        ZGAddCommand command2 = new ZGAddCommand(target, ARCPackage.Literals.ARC_STATE__INCOMING_LINE, line, target.getIncomingLine().size());
        cmd.append(command2);

        ZGAddCommand command3 = new ZGAddCommand(root, ARCPackage.Literals.ARC_ROOT__LINES, line, root.getLines().size());
        cmd.append(command3);

        if (oldLine != null) {
            ZGRemoveCommand command4 = new ZGRemoveCommand(root, ARCPackage.Literals.ARC_ROOT__LINES, oldLine);
            cmd.append(command4);
        }

        return cmd;
    }

    /**
     * Create a command to delete the information of the transition line of ARCLine.
     * @param root
     * @param lines
     * @return Created command
     */
    public final synchronized CompoundCommand removeLines(ARCRoot root, List<ARCLine> lines) {
        CompoundCommand cmd = new CompoundCommand();
        lines.forEach(line -> {
            ZGRemoveCommand command = new ZGRemoveCommand(root, ARCPackage.Literals.ARC_ROOT__LINES, line);
            cmd.append(command);
        });
        return cmd;
    }

    /**
     * Create a command to change the connection source position of the transition line.
     * @param cmd
     * @param line
     * @param newSource
     * @param anchorX
     * @param anchorY
     * @return Created command
     */
    public final synchronized CompoundCommand resizeOutgoingLine(CompoundCommand cmd, ARCLine line, ARCState newSource, double anchorX, double anchorY) {
        ZGSetCommand command = new ZGSetCommand(line, ARCPackage.Literals.ARC_LINE__SOURCE, newSource);
        cmd.append(command);

        ZGSetCommand command2 = new ZGSetCommand(line, COREPackage.Literals.ABSTRACT_LINE__SOURCE_ANCHOR_X, anchorX);
        cmd.append(command2);

        ZGSetCommand command3 = new ZGSetCommand(line, COREPackage.Literals.ABSTRACT_LINE__SOURCE_ANCHOR_Y, anchorY);
        cmd.append(command3);

        return cmd;
    }

    /**
     * Create a command to change the connection target position of the transition line .
     * @param cmd
     * @param line
     * @param newTarget
     * @param anchorX
     * @param anchorY
     * @return Created command
     */
    public final synchronized CompoundCommand resizeIncomingLine(CompoundCommand cmd, ARCLine line, ARCState newTarget, double anchorX, double anchorY) {
        ZGSetCommand command = new ZGSetCommand(line, ARCPackage.Literals.ARC_LINE__TARGET, newTarget);
        cmd.append(command);

        ZGSetCommand command2 = new ZGSetCommand(line, COREPackage.Literals.ABSTRACT_LINE__TARGET_ANCHOR_X, anchorX);
        cmd.append(command2);

        ZGSetCommand command3 = new ZGSetCommand(line, COREPackage.Literals.ABSTRACT_LINE__TARGET_ANCHOR_Y, anchorY);
        cmd.append(command3);

        return cmd;
    }

    /**
     * Create a command to change the information of the transition line label.
     * @param root
     * @param line
     * @param newName
     * @param newType
     * @return Created command
     */
    public final synchronized CompoundCommand editTransitionLabel(ARCRoot root, ARCLine line, String newName, String newType) {
        CompoundCommand cmd = new CompoundCommand();

        ZGSetCommand command = new ZGSetCommand(line, ARCPackage.Literals.ARC_LINE__VARIABLE_NAME, newName);
        cmd.append(command);

        ZGSetCommand command2 = new ZGSetCommand(line, ARCPackage.Literals.ARC_LINE__VARIABLE_TYPE, newType);
        cmd.append(command2);

        if ("".equals(newName) && "".equals(newType)) {
            ZGSetCommand command3 = new ZGSetCommand(line, ARCPackage.Literals.ARC_LINE__X, 0);
            cmd.append(command3);

            ZGSetCommand command4 = new ZGSetCommand(line, ARCPackage.Literals.ARC_LINE__Y, 0);
            cmd.append(command4);
        }

        return cmd;
    }

    /**
     * Create a command to change the position information of the transition line label.
     * @param line
     * @param newX
     * @param newY
     * @return Created command
     */
    public final synchronized CompoundCommand moveLineLabel(ARCLine line, int newX, int newY) {
        CompoundCommand cmd = new CompoundCommand();

        ZGSetCommand command = new ZGSetCommand(line, ARCPackage.Literals.ARC_LINE__X, newX);
        cmd.append(command);

        ZGSetCommand command2 = new ZGSetCommand(line, ARCPackage.Literals.ARC_LINE__Y, newY);
        cmd.append(command2);

        return cmd;
    }
}
