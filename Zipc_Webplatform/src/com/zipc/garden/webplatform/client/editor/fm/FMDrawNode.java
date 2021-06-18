package com.zipc.garden.webplatform.client.editor.fm;

import com.smartgwt.client.widgets.drawing.DrawLabel;
import com.smartgwt.client.widgets.drawing.DrawLine;
import com.smartgwt.client.widgets.drawing.DrawOval;
import com.smartgwt.client.widgets.drawing.DrawRect;
import com.smartgwt.client.widgets.drawing.DrawTriangle;
import com.zipc.garden.model.fm.FMNode;

/**
 * This class manages the nodes and edges drawn in the feature-model Editor.
 */
public class FMDrawNode {

    /** Connection line to parent */
    private DrawLine drawLine;

    /** A transparent line that works in conjunction with drawLine */
    private DrawLine drawLine_Transparent;

    /** DrawRect to draw the contents of the FM Node model */
    private DrawRect drawRect;

    /** Label that displays the cardinality of the node */
    private DrawLabel cardinality;

    /**
     * A DrawOval that displays on the line connecting the parent and child nodes.<br>
     * If it is an Optional node, it is displayed as a white circle, otherwise it is displayed as a black circle.
     */
    private DrawOval drawOval;

    /** If the property is set, it will be displayed in the upper right corner of draw rect. */
    private DrawTriangle drawTriangle;

    /**
     * FMNode model associated with drawRect
     */
    private FMNode fmNode;

    /**
     * Get the connection line to the parent.
     * @return drawLine
     */
    public DrawLine getDrawLine() {
        return drawLine;
    }

    /**
     * Set the information of the line connecting to the parent node.
     * @param drawLine Connection line to parent
     */
    public void setDrawLine(DrawLine drawLine) {
        this.drawLine = drawLine;
    }

    /**
     * Gets a transparent line that works in conjunction with drawLine.
     * @return transparent drawline
     */
    public DrawLine getDrawLine_Transparent() {
        return drawLine_Transparent;
    }

    /**
     * Sets a transparent line that works in conjunction with drawLine.
     * @param drawLine_Transparent transparent drawline
     */
    public void setDrawLine_Transparent(DrawLine drawLine_Transparent) {
        this.drawLine_Transparent = drawLine_Transparent;
    }

    /**
     * Set the DrawRect associated with the FM node.
     * @param drawRect DrawRect associated with the FM node
     */
    public void setDrawRect(DrawRect drawRect) {
        this.drawRect = drawRect;
    }

    /**
     * Get the DrawRect associated with the FM node.
     * @return DrawRect associated with the FM node
     */
    public DrawRect getDrawRect() {
        return this.drawRect;
    }

    /**
     * Gets the FMNode model associated with drawRect.
     * @return FMNode model associated with drawRect
     */
    public FMNode getFmNode() {
        return fmNode;
    }

    /**
     * Sets the FMNode model associated with drawRect.
     * @param fmNode FMNode model associated with drawRect
     */
    public void setFmNode(FMNode fmNode) {
        this.fmNode = fmNode;
    }

    /**
     * Get the label that displays the cardinality
     * @return
     */
    public DrawLabel getCardinality() {
        return cardinality;
    }

    /**
     * Set the label that displays the cardinality
     * @param cardinality
     */
    public void setCardinality(DrawLabel cardinality) {
        this.cardinality = cardinality;
    }

    /**
     * Gets the DrawOval displayed on the line.
     * @return DrawOval displayed on the line.
     */
    public DrawOval getDrawOval() {
        return drawOval;
    }

    /**
     * Sets the DrawOval displayed on the line.
     * @param drawOval DrawOval displayed on the line.
     */
    public void setDrawOval(DrawOval drawOval) {
        this.drawOval = drawOval;
    }

    /**
     * Gets the triangle displayed in the upper right corner of drawRect
     * @return triangle displayed in the upper right corner of drawRect
     */
    public DrawTriangle getDrawTriangle() {
        return drawTriangle;
    }

    /**
     * Sets the triangle displayed in the upper right corner of drawRect
     * @param drawTriangle triangle displayed in the upper right corner of drawRect
     */
    public void setDrawTriangle(DrawTriangle drawTriangle) {
        this.drawTriangle = drawTriangle;
    }
}
