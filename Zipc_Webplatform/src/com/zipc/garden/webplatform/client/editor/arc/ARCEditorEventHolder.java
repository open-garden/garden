package com.zipc.garden.webplatform.client.editor.arc;

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
import com.zipc.garden.model.arc.ARCLine;
import com.zipc.garden.model.arc.ARCState;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.LineType;
import com.zipc.garden.webplatform.client.command.ARCEditorCommandProvider;
import com.zipc.garden.webplatform.client.editor.EditorDrawLine;
import com.zipc.garden.webplatform.client.editor.EditorLineUtil;

/**
 * Manages events for items and lines on the canvas
 */
public class ARCEditorEventHolder {

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

    /** Value (Y coordinate) after subtracting the value before and after resizing of DrawItem */
    private int resizeTopDiff = 0;

    /** Value (X coordinate) after subtracting the value before and after resizing of DrawItem */
    private int resizeLeftDiff = 0;

    /** Value (height) after subtracting the value before and after resizing of DrawItem */
    private int resizeHeightDiff = 0;

    /** Value (width) after subtracting the value before and after resizing of DrawItem */
    private int resizeWidthDiff = 0;

    /**
     * Controls the selection status of Item and Line.<br>
     * Also, the connection destination of the transition line is set.
     * @param editor Main class of architecture editor
     * @param drawItem DrawItem to connect to
     * @return Click handler
     */
    protected ClickHandler createLeftClickOnLabelHandler(ARCEditor editor, DrawItem drawItem) {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (editor.lineManager.addLineDrawItems[0] != null) {
                    editor.lineManager.addLineDrawItems[1] = drawItem;
                    editor.lineManager.onAdd();
                }
                if (event.isCtrlKeyDown()) {
                    editor.stateManager.selectDrawItem(drawItem, !editor.stateManager.getSelectFlag(drawItem));
                } else {
                    editor.stateManager.selectDrawItemAll(false);
                    editor.lineManager.selectDrawLineAll(false);
                    editor.stateManager.selectDrawItem(drawItem, true);
                }
            }
        };
    }

    /**
     * Event which is fired when dragging of DrawItem is started. <br>
     * DrawItems are moved at grid intervals.
     * @param editor Main class of architecture editor
     * @param drawItem DrawItem to drag and drop
     * @return Drag start event
     */
    protected DragStartHandler createDragStartHandler(ARCEditor editor, DrawItem drawItem) {
        return new DragStartHandler() {
            @Override
            public void onDragStart(DragStart event) {
                int snapGap = editor.getARCRoot().getGridSize();
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
     * @param editor Main class of architecture editor
     * @param drawItem DrawItem to drag and drop
     * @return Drag Move Handler
     */
    protected DragMoveHandler createDragMoveHandler(ARCEditor editor, DrawItem drawItem) {
        return new DragMoveHandler() {
            @Override
            public void onDragMove(DragMove event) {
                event.cancel();
                Map<ARCState, DrawItem> map = editor.stateManager.getSelectedDrawItems();
                ARCState state = editor.stateManager.getKey(map, drawItem).get();

                int snapGap = editor.getARCRoot().getGridSize();
                int x = event.getX() / snapGap * snapGap - dragLeftDiff;
                int y = event.getY() / snapGap * snapGap - dragTopDiff;
                int dragMoveTop = y - state.getTop();
                int dragMoveLeft = x - state.getLeft();

                map.entrySet().forEach(entry -> {
                    int top = entry.getKey().getTop() + dragMoveTop;
                    int left = entry.getKey().getLeft() + dragMoveLeft;

                    if (top >= 0 && top + entry.getKey().getHeight() <= editor.getARCRoot().getScrollY()) {
                        entry.getValue().setAttribute("top", top, true);
                    }

                    if (left >= 0 && left + entry.getKey().getWidth() <= editor.getARCRoot().getScrollX()) {
                        entry.getValue().setAttribute("left", left, true);
                    }
                    editor.lineManager.redrawLine(entry.getValue(), false);
                });
            }
        };
    }

    /**
     * DrawItem drag stop event.<br>
     * The coordinates of the DrawItem are reflected in the EMF model.
     * @param editor Main class of architecture editor
     * @return Drag stop handler
     */
    protected DragStopHandler createDragStopHandler(ARCEditor editor) {
        return new DragStopHandler() {
            @Override
            public void onDragStop(DragStop event) {
                Map<ARCState, DrawItem> drawItems = editor.stateManager.getSelectedDrawItems();
                Entry<ARCState, DrawItem> entry = drawItems.entrySet().stream().findFirst().get();

                int moveY = entry.getValue().getAttributeAsInt("top") - entry.getKey().getTop();
                int moveX = entry.getValue().getAttributeAsInt("left") - entry.getKey().getLeft();

                List<ARCState> states = new ArrayList<>(drawItems.keySet());
                Map<ARCLine, List<AbstractPoint>> lines = new HashMap<>();
                drawItems.values().forEach(drawItem -> {
                    editor.lineManager.getKey(editor.getEditorDrawLines(), drawItem).forEach(moveLine -> {
                        DrawItem source = editor.getDrawItems().get(moveLine.getSource());
                        DrawItem target = editor.getDrawItems().get(moveLine.getTarget());

                        List<double[]> mPoints = EditorLineUtil.getManhattanPoint(moveLine, source, target, null, null);
                        List<AbstractPoint> points = EditorLineUtil.getConnectionPoint(mPoints, editor);
                        lines.put(moveLine, points);
                    });
                });
                CompoundCommand cmd = ARCEditorCommandProvider.getInstance().moveStates(states, lines, moveY, moveX);
                editor.manager.execute(cmd.unwrap());
            }
        };
    }

    /**
     * DrawLabel drag stop event related to DrawLine.<br>
     * The coordinates of DrawLabel are reflected in the EMF model.
     * @param editor Main class of architecture editor
     * @param line EMF model associated with the line being drawn
     * @param drawLine DrawLine to drag and drop
     * @return Drag stop handler
     */
    protected DragStopHandler createDragStopHandler(ARCEditor editor, ARCLine line, EditorDrawLine drawLine) {
        return new DragStopHandler() {
            @Override
            public void onDragStop(DragStop event) {
                Point mPoint = drawLine.getMiddlePoint();
                int newX = drawLine.getDrawLabel().getLeft() - mPoint.getX();
                int newY = drawLine.getDrawLabel().getTop() - mPoint.getY();

                CompoundCommand cmd = ARCEditorCommandProvider.getInstance().moveLineLabel(line, newX, newY);
                editor.manager.execute(cmd.unwrap());
            }
        };
    }

    /**
     * Manhattan line drag stop event.
     * @param editor Main class of architecture editor
     * @param line EMF model associated with the line being drawn
     * @param newLine Manhattan DrawLine to drag and drop
     * @param ind Manhattan line index numbers
     * @return Drag stop handler
     */
    protected DragStopHandler createManhattanLineDragStopHandler(ARCEditor editor, ARCLine line, DrawLine newLine, int ind) {
        return new DragStopHandler() {
            @Override
            public void onDragStop(DragStop event) {
                editor.lineManager.reDrawEditedManhattan(line, newLine, ind);
            }
        };
    }

    /**
     * DrawItem resize start event.<br>
     * The size of the DrawItem at the start of resizing should be the same as the contents of the EMF model.
     * @param editor Main class of architecture editor
     * @param drawItem DrawItem to resize
     * @return Resize start handler
     */
    protected DragResizeStartHandler createDragResizeStartHandler(ARCEditor editor, DrawItem drawItem) {
        return new DragResizeStartHandler() {
            @Override
            public void onDragResizeStart(DragResizeStartEvent event) {
                ARCState state = editor.stateManager.getKey(editor.getDrawItems(), drawItem).get();
                drawItem.setAttribute("top", state.getTop(), true);
                drawItem.setAttribute("left", state.getLeft(), true);
                drawItem.setAttribute("width", state.getWidth(), true);
                drawItem.setAttribute("height", state.getHeight(), true);
            }
        };
    }

    /**
     * DrawItem resize event.<br>
     * Items are controlled to resize with grid spacing.
     * @param editor Main class of architecture editor
     * @param drawItem DrawItem to resize
     * @return Handler during resizing
     */
    protected DragResizeMoveHandler createDragResizeMoveHandler(ARCEditor editor, DrawItem drawItem) {
        return new DragResizeMoveHandler() {
            @Override
            public void onDragResizeMove(DragResizeMoveEvent event) {
                event.cancel();
                ARCState state = editor.stateManager.getKey(editor.getDrawItems(), drawItem).get();
                int snapGap = editor.getARCRoot().getGridSize();

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

                editor.stateManager.getSelectedDrawItems().entrySet().forEach(action -> {
                    action.getValue().setAttribute("top", action.getKey().getTop() + resizeTopDiff, true);
                    action.getValue().setAttribute("left", action.getKey().getLeft() + resizeLeftDiff, true);
                    action.getValue().setAttribute("width", action.getKey().getWidth() + resizeWidthDiff, true);
                    action.getValue().setAttribute("height", action.getKey().getHeight() + resizeHeightDiff, true);
                    editor.lineManager.redrawLine(action.getValue(), false);
                });
            }
        };
    }

    /**
     * DrawItem resize stop event.<br>
     * The changed DrawItem size is reflected in the model.
     * @param editor Main class of architecture editor
     * @param drawItem DrawItem to resize
     * @return Resize stop handler
     */
    protected DragResizeStopHandler createDragResizeStopHandler(ARCEditor editor, DrawItem drawItem) {
        return new DragResizeStopHandler() {
            @Override
            public void onDragResizeStop(DragResizeStopEvent event) {
                ARCState state = editor.stateManager.getKey(editor.getDrawItems(), drawItem).get();
                drawItem.setAttribute("top", state.getTop() + resizeTopDiff, true);
                drawItem.setAttribute("left", state.getLeft() + resizeLeftDiff, true);
                drawItem.setAttribute("width", state.getWidth() + resizeWidthDiff, true);
                drawItem.setAttribute("height", state.getHeight() + resizeHeightDiff, true);

                Map<ARCState, DrawItem> drawItems = editor.stateManager.getSelectedDrawItems();
                List<ARCState> states = new ArrayList<ARCState>(drawItems.keySet());

                // リサイズに伴って遷移線も移動する
                Map<ARCLine, List<AbstractPoint>> lines = new HashMap<>();
                drawItems.values().forEach(drawItem -> {
                    editor.lineManager.getKey(editor.getEditorDrawLines(), drawItem).forEach(moveLine -> {
                        DrawItem source = editor.getDrawItems().get(moveLine.getSource());
                        DrawItem target = editor.getDrawItems().get(moveLine.getTarget());

                        List<double[]> mPoints = EditorLineUtil.getManhattanPoint(moveLine, source, target, null, null);
                        List<AbstractPoint> points = EditorLineUtil.getConnectionPoint(mPoints, editor);
                        lines.put(moveLine, points);
                    });
                });

                CompoundCommand cmd = ARCEditorCommandProvider.getInstance().resizeStates(states, lines, resizeTopDiff, resizeLeftDiff, resizeWidthDiff, resizeHeightDiff);
                editor.manager.execute(cmd.unwrap());
            }
        };
    }

    /**
     * Click event for DrawLine.<br>
     * Controls the selection state of the DrawLine and the DrawLabel associated with it.<br>
     * If you click on the Manhattan line, the resize line will appear.
     * @param editor Main class of architecture editor
     * @param drawLabel DrawLabel related to DrawLine
     * @param drawLines Clicked DrawLine
     * @param dotLines Line for changing the size of the Manhattan line
     * @return DrawLine click handler
     */
    protected ClickHandler createLeftClickOnDrawLineHandler(ARCEditor editor, DrawLabel drawLabel, List<DrawLine> drawLines, List<DrawLine> dotLines) {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (event.isCtrlKeyDown()) {
                    EditorLineUtil.selectDrawLine(drawLabel, drawLines, dotLines, !EditorLineUtil.getSelectFlag(drawLines.get(0)));
                } else {
                    editor.stateManager.selectDrawItemAll(false);
                    editor.lineManager.selectDrawLineAll(false);
                    EditorLineUtil.selectDrawLine(drawLabel, drawLines, dotLines, true);
                    if (editor.lineManager.getKey(editor.getEditorDrawLines(), drawLines.get(0)).getType() != null
                            && editor.lineManager.getKey(editor.getEditorDrawLines(), drawLines.get(0)).getType().equals(LineType.MANHATTAN)) {
                        ARCLine arcLine = editor.lineManager.getKey(editor.getEditorDrawLines(), drawLines.get(0));
                        EditorDrawLine editorDrawLine = editor.getEditorDrawLines().get(arcLine);

                        Map<Integer, DrawLine> newLines = EditorLineUtil.reDesignManhattan(drawLines, editor.getDrawPane());
                        newLines.forEach((i, drawLine) -> {
                            editorDrawLine.getDotLines().add(drawLine);
                            editor.addManhattanLineDragStopEvent(editor, arcLine, drawLine, i);
                        });
                    }
                }
            }
        };
    }

    /**
     * DrawLine resize event.<br>
     * Holds information about the resized DrawLine.
     * @param editor Main class of architecture editor
     * @param drawLines DrawLine to be resized
     * @return Resize handler
     */
    protected ResizedHandler createResizedHandler(ARCEditor editor, List<DrawLine> drawLines) {
        return new ResizedHandler() {

            @Override
            public void onResized(ResizedEvent event) {
                if (editor.lineManager.resizeDrawLine.size() == 0) {
                    DrawLine startLine = drawLines.get(0);
                    DrawLine endLine = drawLines.get(drawLines.size() - 1);

                    editor.lineManager.resizeDrawLine = drawLines;
                    editor.lineManager.resizePoints[0] = new Point(startLine.getStartLeft(), startLine.getStartTop());
                    editor.lineManager.resizePoints[1] = new Point(endLine.getEndLeft(), endLine.getEndTop());
                    ARCLine lines = editor.lineManager.getKey(editor.getEditorDrawLines(), drawLines.get(0));
                    EditorDrawLine editorDrawLine = editor.getEditorDrawLines().get(lines);
                    editorDrawLine.getDotLines().forEach(dotLine -> dotLine.erase());
                }
            }
        };
    }
}
