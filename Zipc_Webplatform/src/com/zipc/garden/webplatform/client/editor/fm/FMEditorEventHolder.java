package com.zipc.garden.webplatform.client.editor.fm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.CompoundCommand;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.smartgwt.client.types.KnobType;
import com.smartgwt.client.widgets.drawing.DrawLabel;
import com.smartgwt.client.widgets.drawing.DrawLine;
import com.smartgwt.client.widgets.drawing.DrawRect;
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
import com.smartgwt.client.widgets.drawing.events.DrawStartEvent;
import com.smartgwt.client.widgets.drawing.events.DrawStartHandler;
import com.smartgwt.client.widgets.drawing.events.ResizedEvent;
import com.smartgwt.client.widgets.drawing.events.ResizedHandler;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.webplatform.client.command.FMEditorCommandProvider;
import com.zipc.garden.webplatform.client.editor.fm.FMProperty.ProcessType;
import com.zipc.garden.webplatform.client.editor.fm.layout.LayoutMode;

/**
 * Manages events for items and lines on the canvas
 */
public class FMEditorEventHolder {

    /**
     * Value obtained by subtracting the click position (Y coordinate) within DrawRect and the Top position (Y coordinate) of
     * DrawRect
     */
    int dragTopDiff = 0;

    /**
     * Value obtained by subtracting the click position (X coordinate) within DrawRect and the Left position (X coordinate) of
     * DrawRect
     */
    int dragLeftDiff = 0;

    /** Value (Y coordinate) after subtracting the value before and after resizing of DrawRect */
    int resizeTopDiff = 0;

    /** Value (X coordinate) after subtracting the value before and after resizing of DrawRect */
    int resizeLeftDiff = 0;

    /** Value (height) after subtracting the value before and after resizing of DrawRect */
    int resizeHeightDiff = 0;

    /** Value (width) after subtracting the value before and after resizing of DrawRect */
    int resizeWidthDiff = 0;

    /**
     * Controls the selection status of DrawRect.
     * @param editor Main class of feature-model editor
     * @param drawItems A Map that associates the DrawRect existing in the Canvas with the EMF model.
     * @param drawRect clicked DrawRect
     * @return Click handler
     */
    protected ClickHandler createLeftClickOnLabelHandler(FMEditor editor, Map<Integer, FMDrawNode> drawItems, DrawRect drawRect) {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                List<DrawRect> drawRectNum = editor.nodeManager.getSelectDrawRect();
                if (event.isCtrlKeyDown()) {
                    if (drawRect.getKnobs() != null && drawRect.getKnobs().length != 0) {
                        drawRect.setFillColor(drawItems.get(drawRect.hashCode()).getFmNode().getFillColor());
                        drawRect.hideAllKnobs();
                    } else {
                        drawRect.setFillColor("yellow");
                        drawRect.showKnobs(KnobType.RESIZE);
                    }
                } else {
                    FMNode node = null;
                    for (Map.Entry<Integer, FMDrawNode> entry : drawItems.entrySet()) {
                        if (drawRect.hashCode() == entry.getKey()) {
                            node = entry.getValue().getFmNode();
                            continue;
                        }
                        DrawRect drawRect = entry.getValue().getDrawRect();
                        drawRect.setFillColor(drawItems.get(drawRect.hashCode()).getFmNode().getFillColor());
                        drawRect.hideAllKnobs();
                    }
                    if (drawRect.getKnobs() != null && drawRect.getKnobs().length != 0) {
                        if (drawRectNum.size() == 1) {
                            FMNode propertyNode = node;
                            Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                                @Override
                                public void execute() {
                                    new FMProperty().openProperty(editor, ProcessType.Node, propertyNode);
                                }
                            });
                        }
                    } else {
                        drawRect.setFillColor("yellow");
                        drawRect.showKnobs(KnobType.RESIZE);
                    }
                }
            }
        };
    }

    /**
     * Event which is fired when dragging of DrawRect is started. <br>
     * DrawRects are moved at grid intervals.
     * @param editor Main class of feature-model editor
     * @param label DrawRect to drag and drop
     * @return Drag start event
     */
    protected DragStartHandler createDragStartHandler(FMEditor editor, DrawRect label) {
        return new DragStartHandler() {
            @Override
            public void onDragStart(DragStart event) {
                int snapGap = editor.getFMRoot().getGridSize();
                // click位置とlabel位置の差分
                dragLeftDiff = (event.getX() - label.getLeft()) / snapGap * snapGap;
                dragTopDiff = (event.getY() - label.getTop()) / snapGap * snapGap;

                if (!editor.nodeManager.getSelectFlag(label)) {
                    // click時のイベントを発火
                    label.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
                }
            }
        };
    }

    /**
     * Event when the DrawRect is being dragged. <br>
     * DrawRects are controlled to be dragged with grid spacing.
     * @param editor Main class of feature-model editor
     * @param drawRect DrawRect to drag and drop
     * @return Drag Move Handler
     */
    protected DragMoveHandler createDragMoveHandler(FMEditor editor, DrawRect drawRect) {
        return new DragMoveHandler() {
            @Override
            public void onDragMove(DragMove event) {
                event.cancel();
                Map<Integer, FMDrawNode> map = editor.nodeManager.getSelectedDrawItems(editor);
                FMNode node = map.get(drawRect.hashCode()).getFmNode();

                int snapGap = editor.getFMRoot().getGridSize();
                int x = event.getX() / snapGap * snapGap - dragLeftDiff;
                int y = event.getY() / snapGap * snapGap - dragTopDiff;
                int dragMoveTop = y - node.getTop();
                int dragMoveLeft = x - node.getLeft();

                LayoutMode mode = editor.getCurrentLayoutMode();
                map.entrySet().forEach(entry -> {
                    FMNode selNode = entry.getValue().getFmNode();
                    DrawLabel cardinality = entry.getValue().getCardinality();
                    DrawRect selDrawRect = entry.getValue().getDrawRect();
                    int top = selNode.getTop() + dragMoveTop;
                    int left = selNode.getLeft() + dragMoveLeft;

                    if (cardinality != null) {
                        int[] position = mode.getDefaultCardinalityPosition(selDrawRect);
                        cardinality.moveTo(position[0] + node.getX(), position[1] + node.getY());
                    }
                    if (top >= 0 && top + selNode.getHeight() <= editor.getFMRoot().getScrollY()) {
                        selDrawRect.setTop(top);
                    }

                    if (left >= 0 && left + selNode.getWidth() <= editor.getFMRoot().getScrollX()) {
                        selDrawRect.setLeft(left);
                    }
                    editor.lineManager.redrawLine(editor, selDrawRect);
                    editor.dragMoveOval(entry.getValue());
                    editor.dragMoveTriangle(entry.getValue());
                });
            }
        };

    }

    /**
     * DrawRect drag stop event.<br>
     * The coordinates of the DrawRect are reflected in the EMF model.
     * @param editor Main class of feature-model editor
     * @return Drag stop handler
     */
    protected DragStopHandler createDragStopHandler(FMEditor editor) {
        return new DragStopHandler() {

            @Override
            public void onDragStop(DragStop event) {

                List<FMNode> nodeList = new ArrayList<>();
                List<DrawRect> drawRects = editor.nodeManager.getSelectDrawRect();
                drawRects.stream().forEach(x -> {
                    FMNode node = editor.getDrawRectMap().get(x.hashCode()).getFmNode();
                    nodeList.add(node);
                });
                int moveY = drawRects.get(0).getTop() - nodeList.get(0).getTop();
                int moveX = drawRects.get(0).getLeft() - nodeList.get(0).getLeft();

                CompoundCommand cmd = FMEditorCommandProvider.getInstance().moveNodes(nodeList, moveY, moveX);

                editor.getEditManager().execute(cmd.unwrap());
                editor.refresh();
            }
        };
    }

    /**
     * Cardinality DrawLabel drag stop event.<br>
     * The coordinates of the DrawLabel are reflected in the EMF model.
     * @param editor Main class of feature-model editor
     * @param drawRect DrawRect related to cardinality
     * @param cardinality DrawLabel to drag and drop
     * @return Drag stop handler
     */
    protected DragStopHandler createCardinalityDragStopHandler(FMEditor editor, DrawRect drawRect, DrawLabel cardinality) {
        return new DragStopHandler() {

            @Override
            public void onDragStop(DragStop event) {
                FMNode node = editor.getDrawRectMap().get(drawRect.hashCode()).getFmNode();
                LayoutMode mode = editor.getCurrentLayoutMode();
                int[] defaultPosition = mode.getDefaultCardinalityPosition(node);
                int[] position = { cardinality.getLeft() - defaultPosition[0], cardinality.getTop() - defaultPosition[1] };
                CompoundCommand cmd = FMEditorCommandProvider.getInstance().moveCardinality(editor, node, position);
                editor.getEditManager().execute(cmd.unwrap());
            }
        };
    }

    /**
     * DrawRect resize start event.<br>
     * The size of the DrawRect at the start of resizing should be the same as the contents of the EMF model.
     * @param editor Main class of feature-model editor
     * @param drawRect DrawRect to resize
     * @return Resize start handler
     */
    protected DragResizeStartHandler createDragResizeStartHandler(FMEditor editor, DrawRect drawRect) {
        return new DragResizeStartHandler() {
            @Override
            public void onDragResizeStart(DragResizeStartEvent event) {
                FMNode node = editor.getDrawRectMap().get(drawRect.hashCode()).getFmNode();
                drawRect.setTop(node.getTop());
                drawRect.setLeft(node.getLeft());
                drawRect.setWidth(node.getWidth());
                drawRect.setHeight(node.getHeight());
            }
        };
    }

    /**
     * DrawRect resize event.<br>
     * DrawRects are controlled to resize with grid spacing.
     * @param editor Main class of feature-model editor
     * @param drawRect DrawRect to resize
     * @return Handler during resizing
     */
    protected DragResizeMoveHandler createDragResizeMoveHandler(FMEditor editor, DrawRect drawRect) {
        return new DragResizeMoveHandler() {
            @Override
            public void onDragResizeMove(DragResizeMoveEvent event) {
                event.cancel();
                FMNode node = editor.getDrawRectMap().get(drawRect.hashCode()).getFmNode();
                int snapGap = editor.getFMRoot().getGridSize();

                int newY = Math.round(event.getNewY());
                int newX = Math.round(event.getNewX());
                int newW = Math.round(event.getNewWidth());
                int newH = Math.round(event.getNewHeight());

                resizeTopDiff = newY / snapGap * snapGap - node.getTop();
                resizeLeftDiff = newX / snapGap * snapGap - node.getLeft();
                resizeWidthDiff = newW / snapGap * snapGap - node.getWidth();
                resizeHeightDiff = newH / snapGap * snapGap - node.getHeight();

                if (newY % snapGap != 0 && newH % snapGap != 0) {
                    int oldBottom = node.getTop() + node.getHeight();
                    int newBottom = newY + newH;
                    resizeHeightDiff = resizeTopDiff * -1 + (newBottom - oldBottom);
                }
                if (newX % snapGap != 0 && newW % snapGap != 0) {
                    int oldRight = node.getLeft() + node.getWidth();
                    int newRight = newX + newW;
                    resizeWidthDiff = resizeLeftDiff * -1 + (newRight - oldRight);
                }

                editor.nodeManager.getSelectedDrawItems(editor).entrySet().forEach(entry -> {
                    FMNode selNode = entry.getValue().getFmNode();
                    DrawRect selDrawRect = entry.getValue().getDrawRect();
                    DrawLabel cardinality = entry.getValue().getCardinality();
                    LayoutMode mode = editor.getCurrentLayoutMode();

                    if (cardinality != null) {
                        int[] position = mode.getDefaultCardinalityPosition(selDrawRect);
                        cardinality.moveTo(position[0] + node.getX(), position[1] + node.getY());
                    }
                    selDrawRect.setTop(selNode.getTop() + resizeTopDiff);
                    selDrawRect.setLeft(selNode.getLeft() + resizeLeftDiff);
                    selDrawRect.setWidth(selNode.getWidth() + resizeWidthDiff);
                    selDrawRect.setHeight(selNode.getHeight() + resizeHeightDiff);

                    editor.lineManager.redrawLine(editor, selDrawRect);
                    editor.dragMoveOval(entry.getValue());
                    editor.dragMoveTriangle(entry.getValue());
                });
            }
        };
    }

    /**
     * DrawRect resize stop event.<br>
     * The changed DrawRect size is reflected in the model.
     * @param editor Main class of feature-model editor
     * @param drawRect DrawRect to resize
     * @return Resize stop handler
     */
    protected DragResizeStopHandler createDragResizeStopHandler(FMEditor editor, DrawRect drawRect) {
        return new DragResizeStopHandler() {
            @Override
            public void onDragResizeStop(DragResizeStopEvent event) {
                FMNode selNode = editor.drawItems.get(drawRect.hashCode()).getFmNode();

                drawRect.setTop(selNode.getTop() + resizeTopDiff);
                drawRect.setLeft(selNode.getLeft() + resizeLeftDiff);
                drawRect.setWidth(selNode.getWidth() + resizeWidthDiff);
                drawRect.setHeight(selNode.getHeight() + resizeHeightDiff);

                List<FMNode> nodeList = new ArrayList<FMNode>();
                editor.nodeManager.getSelectDrawRect().forEach(item -> {
                    FMNode node = editor.getDrawRectMap().get(item.hashCode()).getFmNode();
                    nodeList.add(node);
                });

                CompoundCommand cmd = FMEditorCommandProvider.getInstance().resizeNodes(nodeList, resizeTopDiff, resizeLeftDiff, resizeWidthDiff, resizeHeightDiff);

                editor.getEditManager().execute(cmd.unwrap());
                editor.refresh();
            }
        };
    }

    /**
     * Canvas drawing start event.<br>
     * Create an arc on the line connecting the parent and child nodes.
     * @param editor Main class of feature-model editor
     * @param nodeManager Class that manages Node
     * @return Draw Start Handler
     */
    protected DrawStartHandler createDrawStartHandler(FMEditor editor, FMNodeManager nodeManager) {
        return new DrawStartHandler() {

            @Override
            public void onDrawStart(DrawStartEvent event) {
                if (nodeManager.root.getNode() == null) {
                    return;
                }

                LayoutMode layoutMode = editor.getCurrentLayoutMode();
                // MAP<主語、目的語(複数)>を宣言
                Map<DrawRect, List<DrawRect>> map = new HashMap<>();
                // MAPを作成
                FMNode parentNode = nodeManager.root.getNode();
                List<FMNode[]> pairList = new ArrayList<FMNode[]>();
                nodeManager.getPairList(parentNode, pairList);
                for (FMNode[] pair : pairList) {
                    if (pair == null) {
                        break;
                    }
                    DrawRect subject = nodeManager.getDrawRect(pair[0]);
                    DrawRect object = nodeManager.getDrawRect(pair[1]);
                    if (!pair[1].isOptional()) {
                        if (!map.containsKey(subject) && pair[0].getChildType().equals(ChildType.XOR)) {
                            List<DrawRect> list = new ArrayList<DrawRect>();
                            list.add(object);
                            map.put(subject, list);
                        } else if (pair[0].getChildType().equals(ChildType.XOR)) {
                            List<DrawRect> list = map.get(subject);
                            list.add(object);
                        }
                    }
                }

                // MAP分、反復処理
                for (Map.Entry<DrawRect, List<DrawRect>> entry : map.entrySet()) {
                    DrawRect subject = entry.getKey();
                    DrawRect object1 = new DrawRect();
                    DrawRect object2 = new DrawRect();
                    int idx = 0;
                    int[] subjectCoords = layoutMode.getConnectionPointAsParent(subject);
                    // List分、反復処理
                    for (DrawRect object : entry.getValue()) {
                        idx++;
                        // 1件目の場合
                        if (idx == 1) {
                            object1 = object;
                            object2 = object;
                            continue;
                        }

                        // 目的語の座標を取得し、主語との角度を求める
                        int[] objectCoords = layoutMode.getConnectionPointAsChild(object);
                        double objectAngle = getAngleBetweenTwoPoints(subjectCoords, objectCoords);
                        // 退避した目的語1の座標を取得し、主語との角度を求める
                        int[] object1Coords = layoutMode.getConnectionPointAsChild(object1);
                        double object1Angle = getAngleBetweenTwoPoints(subjectCoords, object1Coords);
                        // 退避した目的語2の座標を取得し、主語との角度を求める
                        int[] object2Coords = layoutMode.getConnectionPointAsChild(object2);
                        double object2Angle = getAngleBetweenTwoPoints(subjectCoords, object2Coords);

                        // 退避したラベルより角度が小さい
                        if (object1Angle >= objectAngle) {
                            object1 = object;
                        }
                        // 退避したラベルより角度が大きい
                        else if (object2Angle <= objectAngle) {
                            object2 = object;
                        }
                    }
                    // 主語、目的語1、目的語2の間で円弧を描画する
                    addDrawArcEvent(layoutMode, subject, object1, object2);
                }
            }
        };
    }

    /**
     * Create an arc on the line connecting the parent and child nodes.
     * @param layoutMode Active layout mode
     * @param subject Parent node
     * @param object1 Child node with the lowest Y coordinate position
     * @param object2 Child node with the highest Y coordinate position
     */
    private void addDrawArcEvent(LayoutMode layoutMode, DrawRect subject, DrawRect object1, DrawRect object2) {
        // 主語の座標を取得
        int[] subjectCoords = layoutMode.getConnectionPointAsParent(subject);
        // 目的語1の座標を取得
        int[] object1Coords = layoutMode.getConnectionPointAsChild(object1);
        // 目的語2の座標を取得
        int[] object2Coords = layoutMode.getConnectionPointAsChild(object2);
        // 主語と目的語1の座標を元に、角度を取得する
        double angle1 = getAngleBetweenTwoPoints(subjectCoords, object1Coords);
        // 主語と目的語2の座標を元に、角度を取得する
        double angle2 = getAngleBetweenTwoPoints(subjectCoords, object2Coords);

        // 円弧の描画
        Context2d context = subject.getUnderlyingGWTCanvas().getContext2d();
        context.setLineWidth(2);
        String rgba = "rgba(125, 125, 125, 1)";
        context.setStrokeStyle(CssColor.make(rgba));
        context.beginPath(); // 現在のパスをリセットします
        context.arc(subjectCoords[0] // 円の中心のX座標
                , subjectCoords[1] // 円の中心のY座標
                , 40 // 半径
                , (angle1 - 10) * Math.PI / 180 // 描画開始位置（角度からラジアンに変換）
                , (angle2 + 10) * Math.PI / 180 // 描画終了位置（角度からラジアンに変換）
        );
        context.stroke(); // 現在のパスを描画します
    }

    /**
     * Find the angle based on the coordinates of two points
     * @param x1 -> X coordinate of parent node
     * @param y1 -> Y coordinate of parent node
     * @param x2 -> X coordinate of child node
     * @param y2 -> Y coordinate of child node
     * @return Angle based on the coordinates of two points
     */
    protected double getAngleBetweenTwoPoints(double x1, double y1, double x2, double y2) {
        // ラジアン取得
        double radian = Math.atan2(y2 - y1, x2 - x1);
        // ラジアンから角度に変換
        double angle = radian * 180 / Math.PI;
        return angle;
    }

    /**
     * Find the angle based on the coordinates of two points
     * @param coords1 The coordinates of the parent node
     * @param coords2 The coordinates of the child node
     * @return Angle based on the coordinates of two points
     */
    protected double getAngleBetweenTwoPoints(int[] coords1, int[] coords2) {
        return getAngleBetweenTwoPoints(coords1[0], coords1[1], coords2[0], coords2[1]);
    }

    /**
     * Click event of DrawLine
     * @param editor Main class of feature-model editor
     * @param drawLine DrawLine to click
     * @return click handler
     */
    protected ClickHandler createLeftClickOnDrawLineHandler(FMEditor editor, DrawLine drawLine) {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (event.isCtrlKeyDown()) {
                    editor.lineManager.selectDrawLine(drawLine, !editor.lineManager.getSelectFlag(drawLine));
                } else {
                    editor.lineManager.selectDrawLineAll(editor, false);
                    editor.lineManager.selectDrawLine(drawLine, true);
                }
            }
        };
    }

    /**
     * DrawLine resize event.<br>
     * The resized DrawLine is held in a variable.
     * @param editor Main class of feature-model editor
     * @param drawLine DrawLine to resized
     * @return Resized Handler
     */
    protected ResizedHandler createResizedHandler(FMEditor editor, DrawLine drawLine) {
        return new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent event) {
                if (editor.lineManager.resizeDrawLine == null) {
                    editor.lineManager.resizeDrawLine = drawLine;
                }
            }
        };
    }
}
