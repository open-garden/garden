package com.zipc.garden.webplatform.client.editor.fsm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.command.CompoundCommand;

import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawLabel;
import com.smartgwt.client.widgets.drawing.DrawLine;
import com.smartgwt.client.widgets.drawing.Point;
import com.smartgwt.client.widgets.drawing.events.DragMove;
import com.smartgwt.client.widgets.drawing.events.DragMoveHandler;
import com.smartgwt.client.widgets.drawing.events.DragResizeMoveEvent;
import com.smartgwt.client.widgets.drawing.events.DragResizeMoveHandler;
import com.smartgwt.client.widgets.drawing.events.DragResizeStartEvent;
import com.smartgwt.client.widgets.drawing.events.DragResizeStartHandler;
import com.smartgwt.client.widgets.drawing.events.DragResizeStopEvent;
import com.smartgwt.client.widgets.drawing.events.DragResizeStopHandler;
import com.smartgwt.client.widgets.drawing.events.DragStart;
import com.smartgwt.client.widgets.drawing.events.DragStartHandler;
import com.smartgwt.client.widgets.drawing.events.DragStop;
import com.smartgwt.client.widgets.drawing.events.DragStopHandler;
import com.smartgwt.client.widgets.drawing.events.ResizedEvent;
import com.smartgwt.client.widgets.drawing.events.ResizedHandler;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.LineType;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMDTransition;
import com.zipc.garden.webplatform.client.command.FSMEditorCommandProvider;
import com.zipc.garden.webplatform.client.editor.EditorDrawLine;
import com.zipc.garden.webplatform.client.editor.EditorLineUtil;

/**
 * Manages events for items and lines on the canvas
 */
public class FSMEditorEventHolder {

    /**
     * Value obtained by subtracting the click position (Y coordinate) within DrawItem and the Top position (Y coordinate) of
     * DrawItem
     */
    private int dragTopDiff = 0;

    /**
     * Value obtained by subtracting the click position (X coordinate) within DrawItem and the Left position (X coordinate) of
     * DrawItem
     */
    private int dragLeftDiff = 0;

    /**
     * Value (Y coordinate) after subtracting the value before and after resizing of DrawItem
     */
    private int resizeTopDiff = 0;

    /**
     * Value (X coordinate) after subtracting the value before and after resizing of DrawItem
     */
    private int resizeLeftDiff = 0;

    /** Value (height) after subtracting the value before and after resizing of DrawItem */
    private int resizeHeightDiff = 0;

    /** Value (width) after subtracting the value before and after resizing of DrawItem */
    private int resizeWidthDiff = 0;

    /**
     * Controls the selection status of DrawItem.
     * @param editor Main class of behavior-model editor
     * @param drawItem clicked DrawItem
     * @return Click handler
     */
    protected ClickHandler createLeftClickOnLabelHandler(FSMEditor editor, DrawItem drawItem) {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (editor.transManager.addLineDrawItems[0] != null) {
                    editor.transManager.addLineDrawItems[1] = drawItem;
                    editor.transManager.onAdd(editor);
                }
                if (event.isCtrlKeyDown()) {
                    editor.stateManager.selectDrawItem(drawItem, !editor.stateManager.getSelectFlag(drawItem));
                } else {
                    editor.stateManager.selectDrawItemAll(editor, false);
                    editor.transManager.selectDrawLineAll(editor, false);
                    editor.stateManager.selectDrawItem(drawItem, true);
                }
            }
        };
    }

    /**
     * Event which is fired when dragging of DrawItem is started. <br>
     * DrawItems are moved at grid intervals.
     * @param editor Main class of behavior-model editor
     * @param drawItem DrawItem to drag and drop
     * @return Drag start event
     */
    protected DragStartHandler createDragStartHandler(FSMEditor editor, DrawItem drawItem) {
        return new DragStartHandler() {
            @Override
            public void onDragStart(DragStart event) {
                int snapGap = editor.getFSMDStateMachine().getGridSize();
                // click位置とDrawItem位置の差分
                dragLeftDiff = (event.getX() - drawItem.getAttributeAsInt("left")) / snapGap * snapGap;
                dragTopDiff = (event.getY() - drawItem.getAttributeAsInt("top")) / snapGap * snapGap;

                if (!editor.stateManager.getSelectFlag(drawItem)) {
                    // click時のイベントを発火
                    drawItem.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
                }
            }
        };
    }

    /**
     * Event when the DrawItem is being dragged. <br>
     * DrawItems are controlled to be dragged with grid spacing.
     * @param editor Main class of behavior-model editor
     * @param drawItem DrawItem to drag and drop
     * @return Drag Move Handler
     */
    protected DragMoveHandler createDragMoveHandler(FSMEditor editor, DrawItem drawItem) {
        return new DragMoveHandler() {
            @Override
            public void onDragMove(DragMove event) {
                event.cancel();
                Map<FSMDState, DrawItem> map = editor.stateManager.getSelectedDrawItems(editor);
                FSMDState state = editor.stateManager.getKey(map, drawItem).get();

                int snapGap = editor.getFSMDStateMachine().getGridSize();
                int x = event.getX() / snapGap * snapGap - dragLeftDiff;
                int y = event.getY() / snapGap * snapGap - dragTopDiff;
                int dragMoveTop = y - state.getTop();
                int dragMoveLeft = x - state.getLeft();

                map.entrySet().forEach(entry -> {
                    int top = entry.getKey().getTop() + dragMoveTop;
                    int left = entry.getKey().getLeft() + dragMoveLeft;

                    if (top >= 0 && top + entry.getKey().getHeight() <= editor.getFSMDStateMachine().getScrollY()) {
                        entry.getValue().setAttribute("top", top, true);
                    }

                    if (left >= 0 && left + entry.getKey().getWidth() <= editor.getFSMDStateMachine().getScrollX()) {
                        entry.getValue().setAttribute("left", left, true);
                    }
                    editor.transManager.redrawLine(editor, entry.getValue(), false);
                });
            }
        };
    }

    /**
     * DrawItem drag stop event.<br>
     * The coordinates of the DrawItem are reflected in the EMF model.
     * @param editor Main class of behavior-model editor
     * @return Drag stop handler
     */
    protected DragStopHandler createDragStopHandler(FSMEditor editor) {
        return new DragStopHandler() {
            @Override
            public void onDragStop(DragStop event) {
                Map<FSMDState, DrawItem> drawItems = editor.stateManager.getSelectedDrawItems(editor);
                Entry<FSMDState, DrawItem> entry = drawItems.entrySet().stream().findFirst().get();

                int moveY = entry.getValue().getAttributeAsInt("top") - entry.getKey().getTop();
                int moveX = entry.getValue().getAttributeAsInt("left") - entry.getKey().getLeft();

                List<FSMDState> states = new ArrayList<>(drawItems.keySet());
                Map<FSMDTransition, List<AbstractPoint>> trans = new HashMap<>();
                drawItems.values().forEach(drawItem -> {
                    editor.transManager.getKey(editor.getEditorDrawLines(), drawItem).forEach(moveTransition -> {
                        DrawItem source = editor.getDrawItems().get(moveTransition.getSource());
                        DrawItem target = editor.getDrawItems().get(moveTransition.getTarget());

                        List<double[]> mPoints = EditorLineUtil.getManhattanPoint(moveTransition, source, target, null, null);
                        List<AbstractPoint> points = EditorLineUtil.getConnectionPoint(mPoints, editor);
                        trans.put(moveTransition, points);
                    });
                });
                CompoundCommand cmd = FSMEditorCommandProvider.getInstance().moveStates(states, trans, moveY, moveX);
                editor.manager.execute(cmd.unwrap());
            }
        };
    }

    /**
     * DrawLabel drag stop event.<br>
     * The coordinates of the DrawLabel are reflected in the EMF model.
     * @param transition FSM model transition line information
     * @param drawLine Drawing information of DrawLine and DrawLabel
     * @return Drag stop handler
     */
    protected DragStopHandler createDragStopHandler(FSMEditor editor, FSMDTransition transition, EditorDrawLine drawLine) {
        return new DragStopHandler() {
            @Override
            public void onDragStop(DragStop event) {
                Point mPoint = drawLine.getMiddlePoint();
                int newX = drawLine.getDrawLabel().getLeft() - mPoint.getX();
                int newY = drawLine.getDrawLabel().getTop() - mPoint.getY();

                CompoundCommand cmd = FSMEditorCommandProvider.getInstance().moveTransitionLabel(transition, newX, newY);
                editor.manager.execute(cmd.unwrap());
            }
        };
    }

    /**
     * Manhattan DrawLine drag stop event.<br>
     * The coordinates of the DrawLine are reflected in the EMF model.
     * @param editor Main class of behavior-model editor
     * @param transition FSM model transition line information
     * @param newLine Manhattan line after resizing
     * @param ind Manhattan line index numbers
     * @return Drag stop handler
     */
    protected DragStopHandler createManhattanLineDragStopHandler(FSMEditor editor, FSMDTransition transition, DrawLine newLine, int ind) {
        return new DragStopHandler() {
            @Override
            public void onDragStop(DragStop event) {
                editor.transManager.reDrawEditedManhattan(editor, transition, newLine, ind);
            }
        };
    }

    /**
     * DrawItem resize start event.<br>
     * The size of the DrawItem at the start of resizing should be the same as the contents of the EMF model.
     * @param editor Main class of behavior-model editor
     * @param drawItem DrawItem to resize
     * @return Resize start handler
     */
    protected DragResizeStartHandler createDragResizeStartHandler(FSMEditor editor, DrawItem drawItem) {
        return new DragResizeStartHandler() {
            @Override
            public void onDragResizeStart(DragResizeStartEvent event) {
                FSMDState state = editor.stateManager.getKey(editor.getDrawItems(), drawItem).get();
                drawItem.setAttribute("top", state.getTop(), true);
                drawItem.setAttribute("left", state.getLeft(), true);
                drawItem.setAttribute("width", state.getWidth(), true);
                drawItem.setAttribute("height", state.getHeight(), true);
            }
        };
    }

    /**
     * DrawItem resize event.<br>
     * DrawItems are controlled to resize with grid spacing.
     * @param editor Main class of behavior-model editor
     * @param drawItem DrawItem to resize
     * @return Handler during resizing
     */
    protected DragResizeMoveHandler createDragResizeMoveHandler(FSMEditor editor, DrawItem drawItem) {
        return new DragResizeMoveHandler() {
            @Override
            public void onDragResizeMove(DragResizeMoveEvent event) {
                event.cancel();
                FSMDState state = editor.stateManager.getKey(editor.getDrawItems(), drawItem).get();
                int snapGap = editor.getFSMDStateMachine().getGridSize();

                int newY = Math.round(event.getNewY());
                int newX = Math.round(event.getNewX());
                int newW = Math.round(event.getNewWidth());
                int newH = Math.round(event.getNewHeight());

                resizeTopDiff = newY / snapGap * snapGap - state.getTop();
                resizeLeftDiff = newX / snapGap * snapGap - state.getLeft();
                resizeWidthDiff = newW / snapGap * snapGap - state.getWidth();
                resizeHeightDiff = newH / snapGap * snapGap - state.getHeight();

                if (newY % snapGap != 0 && newH % snapGap != 0) {
                    int oldBottom = state.getTop() + state.getHeight();
                    int newBottom = newY + newH;
                    resizeHeightDiff = resizeTopDiff * -1 + (newBottom - oldBottom);
                }
                if (newX % snapGap != 0 && newW % snapGap != 0) {
                    int oldRight = state.getLeft() + state.getWidth();
                    int newRight = newX + newW;
                    resizeWidthDiff = resizeLeftDiff * -1 + (newRight - oldRight);
                }

                editor.stateManager.getSelectedDrawItems(editor).entrySet().forEach(action -> {
                    action.getValue().setAttribute("top", action.getKey().getTop() + resizeTopDiff, true);
                    action.getValue().setAttribute("left", action.getKey().getLeft() + resizeLeftDiff, true);
                    action.getValue().setAttribute("width", action.getKey().getWidth() + resizeWidthDiff, true);
                    action.getValue().setAttribute("height", action.getKey().getHeight() + resizeHeightDiff, true);
                    editor.transManager.redrawLine(editor, action.getValue(), false);
                });
            }
        };
    }

    /**
     * DrawItem resize stop event.<br>
     * The changed DrawItem size is reflected in the model.
     * @param editor Main class of behavior-model editor
     * @param drawItem DrawItem to resize
     * @return Resize stop handler
     */
    protected DragResizeStopHandler createDragResizeStopHandler(FSMEditor editor, DrawItem drawItem) {
        return new DragResizeStopHandler() {
            @Override
            public void onDragResizeStop(DragResizeStopEvent event) {
                FSMDState state = editor.stateManager.getKey(editor.getDrawItems(), drawItem).get();
                drawItem.setAttribute("top", state.getTop() + resizeTopDiff, true);
                drawItem.setAttribute("left", state.getLeft() + resizeLeftDiff, true);
                drawItem.setAttribute("width", state.getWidth() + resizeWidthDiff, true);
                drawItem.setAttribute("height", state.getHeight() + resizeHeightDiff, true);

                Map<FSMDState, DrawItem> drawItems = editor.stateManager.getSelectedDrawItems(editor);
                List<FSMDState> states = new ArrayList<>(drawItems.keySet());

                // リサイズに伴って遷移線も移動する
                Map<FSMDTransition, List<AbstractPoint>> trans = new HashMap<>();
                drawItems.values().forEach(drawItem -> {
                    editor.transManager.getKey(editor.getEditorDrawLines(), drawItem).forEach(moveTransition -> {
                        DrawItem source = editor.getDrawItems().get(moveTransition.getSource());
                        DrawItem target = editor.getDrawItems().get(moveTransition.getTarget());

                        List<double[]> mPoints = EditorLineUtil.getManhattanPoint(moveTransition, source, target, null, null);
                        List<AbstractPoint> points = EditorLineUtil.getConnectionPoint(mPoints, editor);
                        trans.put(moveTransition, points);
                    });
                });
                CompoundCommand cmd = FSMEditorCommandProvider.getInstance().resizeStates(states, trans, resizeTopDiff, resizeLeftDiff, resizeWidthDiff, resizeHeightDiff);
                editor.manager.execute(cmd.unwrap());
            }
        };
    }

    /**
     * Click event of DrawLine
     * @param editor Main class of behavior-model editor
     * @param drawLabel Label with line information
     * @param drawLines DrawLine to click
     * @param dotLines Transparent line associated with DrawLine
     * @return click handler
     */
    protected ClickHandler createLeftClickOnDrawLineHandler(FSMEditor editor, DrawLabel drawLabel, List<DrawLine> drawLines, List<DrawLine> dotLines) {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (event.isCtrlKeyDown()) {
                    EditorLineUtil.selectDrawLine(drawLabel, drawLines, dotLines, !EditorLineUtil.getSelectFlag(drawLines.get(0)));
                } else {
                    editor.stateManager.selectDrawItemAll(editor, false);
                    editor.transManager.selectDrawLineAll(editor, false);
                    EditorLineUtil.selectDrawLine(drawLabel, drawLines, dotLines, true);
                    if (editor.transManager.getKey(editor.getEditorDrawLines(), drawLines.get(0)).getType() != null
                            && editor.transManager.getKey(editor.getEditorDrawLines(), drawLines.get(0)).getType().equals(LineType.MANHATTAN)) {
                        FSMDTransition transition = editor.transManager.getKey(editor.getEditorDrawLines(), drawLines.get(0));
                        EditorDrawLine editorDrawLine = editor.getEditorDrawLines().get(transition);

                        Map<Integer, DrawLine> newLines = EditorLineUtil.reDesignManhattan(drawLines, editor.getDrawPane());
                        newLines.forEach((i, drawLine) -> {
                            editorDrawLine.getDotLines().add(drawLine);
                            editor.addManhattanLineDragStopEvent(editor, transition, drawLine, i);
                        });
                    }
                }
            }
        };
    }

    /**
     * DrawLine resize event.<br>
     * The resized DrawLine is held in a variable.
     * @param editor Main class of behavior-model editor
     * @param drawLines DrawLine to resized
     * @return Resized Handler
     */
    protected ResizedHandler createResizedHandler(FSMEditor editor, List<DrawLine> drawLines) {
        return new ResizedHandler() {

            @Override
            public void onResized(ResizedEvent event) {
                if (editor.transManager.resizeDrawLine.size() == 0) {
                    DrawLine startLine = drawLines.get(0);
                    DrawLine endLine = drawLines.get(drawLines.size() - 1);

                    editor.transManager.resizeDrawLine = drawLines;
                    editor.transManager.resizePoints[0] = new Point(startLine.getStartLeft(), startLine.getStartTop());
                    editor.transManager.resizePoints[1] = new Point(endLine.getEndLeft(), endLine.getEndTop());
                    FSMDTransition trans = editor.transManager.getKey(editor.getEditorDrawLines(), drawLines.get(0));
                    EditorDrawLine editorDrawLine = editor.getEditorDrawLines().get(trans);
                    editorDrawLine.getDotLines().forEach(dotLine -> dotLine.erase());
                }
            }
        };
    }
}
