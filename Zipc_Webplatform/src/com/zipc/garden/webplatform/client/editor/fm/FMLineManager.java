package com.zipc.garden.webplatform.client.editor.fm;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;

import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.types.KnobType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.drawing.DrawLine;
import com.smartgwt.client.widgets.drawing.DrawRect;
import com.smartgwt.client.widgets.drawing.Point;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.webplatform.client.command.FMEditorCommandProvider;

/**
 * Class that manages the processing related to DrawLine created by the feature model editor.
 */
public class FMLineManager {

    /** Variable that holds the DrawLine being resized */
    protected DrawLine resizeDrawLine = null;

    /**
     * Enum that manages the type of FMNode
     */
    private enum FMNodeType {
        PARENT, CHILD;
    }

    /**
     * Draw a DrawLine and connect it to the parent node.
     * @param editor Main class of feature-model editor
     * @param fmDrawNode Single FMNode and drawing information
     */
    protected void makeNewDrawLine(FMEditor editor, FMDrawNode fmDrawNode) {
        FMNode parentNode = editor.nodeManager.getParentNode(fmDrawNode.getFmNode());
        if (parentNode == null) {
            return;
        }

        DrawLine drawLine = new DrawLine();
        drawLine.setDrawPane(editor.drawPane);
        drawLine.draw();
        drawLine.setStartPoint(lineIntersection(editor, FMNodeType.PARENT, parentNode));
        drawLine.setEndPoint(lineIntersection(editor, FMNodeType.CHILD, fmDrawNode.getFmNode()));
        drawLine.setLineWidth(2);
        drawLine.setCursor(Cursor.MOVE);

        fmDrawNode.setDrawLine(drawLine);
    }

    /**
     * The node to which the DrawLine is connected changes.
     * @param editor Main class of feature-model editor
     * @param node DrawLine's connected child nodes
     * @param drawLine Resized DrawLine
     */
    protected void resizeDrawLine(FMEditor editor, FMNode node, DrawLine drawLine) {
        Map<Integer, FMDrawNode> drawRects = editor.nodeManager.getDrawRectIsBelowThePointer(editor, drawLine.getStartLeft(), drawLine.getStartTop());
        if (drawRects.size() > 0) {
            FMDrawNode fmDrawNode = drawRects.values().stream().findFirst().get();
            FMNode parentNode = fmDrawNode.getFmNode();
            if (!checkLineConnection(parentNode, node)) {
                return;
            }
            CompoundCommand cmd = FMEditorCommandProvider.getInstance().resizeDrawLine(parentNode, node);
            editor.getEditManager().execute(cmd.unwrap());
        }
    }

    /**
     * Check if the DrawLine is a connectable node.
     * @param parentNode Reconnect destination
     * @param node Connection source
     * @return True if you can connect
     */
    private boolean checkLineConnection(FMNode parentNode, FMNode node) {
        // 自身の場合接続不可
        if (parentNode.equals(node)) {
            SC.warn("Parent node could not be same node.");
            return false;
        }
        // 自身の子孫の場合接続不可
        else if (!checkParentNode(parentNode, node)) {
            SC.warn("Couldn't change parent node, because ancestor this node.");
            return false;
        }
        // リファレンスノードの場合接続不可
        else if (parentNode.getRefuuid() != null) {
            SC.warn("A reference node cannot be a parent node.");
            return false;
        }

        return true;
    }

    /**
     * Recursively search the parent node of the reconnection destination.
     * @param parentNode Ancestor node
     * @param node Connection source
     * @return If there is a connection source node in the ancestor node of the reconnection destination, return false
     */
    private boolean checkParentNode(FMNode parentNode, FMNode node) {
        EObject obj = parentNode.eContainer();
        if (obj instanceof FMNode) {
            if (obj.equals(node)) {
                return false;
            } else {
                return checkParentNode((FMNode) obj, node);
            }
        } else {
            return true;
        }
    }

    /**
     * Controls the selected state of DrawLine.
     * @param drawLine Selection target
     * @param selectFlag Select if True.
     */
    protected void selectDrawLine(DrawLine drawLine, boolean selectFlag) {
        if (selectFlag) {
            drawLine.setLineColor("blue");
            drawLine.bringToFront();
            drawLine.showKnobs(KnobType.STARTPOINT);
        } else {
            drawLine.setLineColor("gray");
            drawLine.hideKnobs(KnobType.STARTPOINT);
        }
    }

    /**
     * Gets the selected state of DrawLine.
     * @param drawLine DrawLine that gets the selected state
     * @return selectFlag Select if True.
     */
    protected boolean getSelectFlag(DrawLine drawLine) {
        return "blue".equals(drawLine.getLineColor());
    }

    /**
     * Controls the selection status of all DrawLines.
     * @param editor Main class of feature-model editor
     * @param selectFlag Select if True.
     */
    protected void selectDrawLineAll(FMEditor editor, boolean selectFlag) {
        editor.getDrawRectMap().entrySet().forEach(entry -> {
            if (editor.getFMRoot().getNode().equals(entry.getValue().getFmNode())) {
                return;
            } else {
                selectDrawLine(entry.getValue().getDrawLine(), selectFlag);
            }
        });
    }

    /**
     * Redraws the DrawLine based on the currently active layout mode.
     * @param editor Main class of feature-model editor
     * @param drawRect The DrawLine connected to the parent node from this child node will be redrawn.
     */
    protected void redrawLine(FMEditor editor, DrawRect drawRect) {
        FMDrawNode fmDrawNode = editor.getDrawRectMap().get(drawRect.hashCode());
        if (fmDrawNode.getDrawLine() != null) {
            // TODO
            // int x = drawRect.getLeft();
            // int y = drawRect.getTop() + (drawRect.getHeight() / 2);
            int[] coord = editor.getCurrentLayoutMode().getConnectionPointAsChild(drawRect);
            fmDrawNode.getDrawLine().setEndPoint(new Point(coord[0], coord[1]));
            fmDrawNode.getDrawLine_Transparent().setEndPoint(new Point(coord[0], coord[1]));
        }

        fmDrawNode.getFmNode().getChildren().forEach(childNode -> {
            DrawRect childDrawRect = editor.nodeManager.getDrawRect(childNode);
            FMDrawNode childFMDrawNode = editor.getDrawRectMap().get(childDrawRect.hashCode());
            // TODO
            // int x = drawRect.getLeft() + drawRect.getWidth();
            // int y = drawRect.getTop() + (drawRect.getHeight() / 2);
            int[] coord = editor.getCurrentLayoutMode().getConnectionPointAsParent(drawRect);
            childFMDrawNode.getDrawLine().setStartPoint(new Point(coord[0], coord[1]));
            childFMDrawNode.getDrawLine_Transparent().setStartPoint(new Point(coord[0], coord[1]));
        });
    }

    /**
     * Gets the Point at which the node and DrawLine are connected.
     * @param editor Main class of feature-model editor
     * @param nodeType Node type (parent or child)
     * @param node parent or child node
     * @return Coordinate point of connection source or connection destination
     */
    protected Point lineIntersection(FMEditor editor, FMNodeType nodeType, FMNode node) {
        // int x, y;
        int[] coord;
        if (FMNodeType.PARENT.equals(nodeType)) {
            // x = node.getLeft() + node.getWidth();
            coord = editor.getCurrentLayoutMode().getConnectionPointAsParent(node);
        } else {
            // x = node.getLeft();
            coord = editor.getCurrentLayoutMode().getConnectionPointAsChild(node);
        }
        // y = node.getTop() + (node.getHeight() / 2);

        return new Point(coord[0], coord[1]);
    }

    /**
     * Create a transparent line corresponding to the created DrawLine.
     * @param editor Main class of feature-model editor
     * @param fmDrawNode Single FMNode and drawing information
     */
    protected void createDrawLineTransparent(FMEditor editor, FMDrawNode fmDrawNode) {
        DrawLine drawLine = new DrawLine();
        drawLine.setDrawPane(editor.drawPane);
        drawLine.draw();
        drawLine.setStartPoint(fmDrawNode.getDrawLine().getStartPoint());
        drawLine.setEndPoint(fmDrawNode.getDrawLine().getEndPoint());
        drawLine.setLineWidth(10);
        drawLine.setLineColor(null);// If null, transparent
        drawLine.setCursor(Cursor.MOVE);
        fmDrawNode.setDrawLine_Transparent(drawLine);
    }

    /**
     * Get the child nodes connected to DrawLine.
     * @param editor Main class of feature-model editor
     * @param drawLine DrawLine to search
     * @return Single FMNode and drawing information
     */
    protected FMDrawNode getFMDrawNode(FMEditor editor, DrawLine drawLine) {
        Optional<Entry<Integer, FMDrawNode>> optDrawNode = editor.getDrawRectMap().entrySet().stream().filter(entry -> {
            if (editor.getFMRoot().getNode().equals(entry.getValue().getFmNode())) {
                return false;
            } else {
                return entry.getValue().getDrawLine().equals(drawLine);
            }
        }).findFirst();
        if (optDrawNode.isPresent()) {
            return optDrawNode.get().getValue();
        } else {
            return null;
        }
    }
}
