package com.zipc.garden.webplatform.client.editor;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawLabel;
import com.smartgwt.client.widgets.drawing.DrawLine;
import com.smartgwt.client.widgets.drawing.Point;

/**
 * A class that manages transition lines that associate elements with each other.
 */
public class EditorDrawLine {

    /**
     * Information about the transition line being drawn.<br>
     * For the Manhattan line, four lines are stored.
     */
    private List<DrawLine> drawLines = new ArrayList<>();

    /** A transparent line that works in conjunction with drawLines. */
    private List<DrawLine> drawLines_Transparent = new ArrayList<>();

    /** The connection source and destination of the transition line are stored. */
    private DrawItem[] drawItems = new DrawItem[2];

    /** Information of the label set on the transition line. */
    private DrawLabel drawLabel = new DrawLabel();

    /** The line that appears when you resize the Manhattan line. */
    private List<DrawLine> dotLines = new ArrayList<>();

    /**
     * The transition line is acquired.
     * @return Drawn line
     */
    public List<DrawLine> getDrawLines() {
        return drawLines;
    }

    /**
     * Gets the transparent line associated with the transition line.
     * @return Transparent line
     */
    public List<DrawLine> getDrawLinesTransparent() {
        return drawLines_Transparent;
    }

    /**
     * Gets the connection source and connection destination of the transition line.
     * @return Drawn item
     */
    public DrawItem[] getDrawItems() {
        return drawItems;
    }

    /**
     * Get information about the label set on the transition line.
     * @return Transition line label information
     */
    public DrawLabel getDrawLabel() {
        return drawLabel;
    }

    /**
     * Set the label information of the transition line.
     * @param drawLabel
     */
    public void setDrawLabel(DrawLabel drawLabel) {
        this.drawLabel = drawLabel;
    }

    /**
     * Gets the line that appears when you resize a Manhattan line.
     * @return dotted line
     */
    public List<DrawLine> getDotLines() {
        return dotLines;
    }

    /**
     * Adjust the connection position of the transition line that connects to the same element.
     * @param drawLines
     * @param sourceAnchorX Source X coordinate
     * @param sourceAnchorY Source Y coordinate
     * @param targetAnchorX Target X coordinate
     * @param targetAnchorY Target Y coordinate
     * @param snapGap Dot spacing on Canvas
     */
    public void repositionSelfLine(List<DrawLine> drawLines, double sourceAnchorX, double sourceAnchorY, double targetAnchorX, double targetAnchorY, int snapGap) {
        if (drawLines.size() != 4) {
            return;
        }

        DrawItem drawItem = drawItems[0];
        Point startPoint = new Point();
        Point firstPoint = new Point();
        Point secondPoint = new Point();
        Point thirdPoint = new Point();
        Point endPoint = new Point();

        int top = drawItem.getAttributeAsInt("top");
        int left = drawItem.getAttributeAsInt("left");
        int height = drawItem.getAttributeAsInt("height");
        int width = drawItem.getAttributeAsInt("width");

        if (sourceAnchorX >= 0.5) {
            int x = left + width;
            int y = top + (int) (height * sourceAnchorY);
            startPoint = new Point(x, y);
            firstPoint = new Point(x + snapGap, y);
        } else {
            int x = left;
            int y = top + (int) (height * sourceAnchorY);
            startPoint = new Point(x, y);
            firstPoint = new Point(x - snapGap, y);
        }
        if (targetAnchorY >= 0.5) {
            int x = firstPoint.getX();
            int y = top + height + snapGap;
            secondPoint = new Point(x, y);
        } else {
            int x = firstPoint.getX();
            int y = top - snapGap;
            secondPoint = new Point(x, y);
        }

        int thirdX = left + (int) (width * targetAnchorX);
        int thirdY = secondPoint.getY();
        thirdPoint = new Point(thirdX, thirdY);

        if (targetAnchorY >= 0.5) {
            int x = thirdPoint.getX();
            int y = top + height;
            endPoint = new Point(x, y);
        } else {
            int x = thirdPoint.getX();
            int y = top;
            endPoint = new Point(x, y);
        }

        drawLines.get(0).setStartPoint(startPoint);
        drawLines.get(0).setEndPoint(firstPoint);
        drawLines.get(1).setStartPoint(firstPoint);
        drawLines.get(1).setEndPoint(secondPoint);
        drawLines.get(2).setStartPoint(secondPoint);
        drawLines.get(2).setEndPoint(thirdPoint);
        drawLines.get(3).setStartPoint(thirdPoint);
        drawLines.get(3).setEndPoint(endPoint);
    }

    /**
     * Get middle coordinates of transition line
     * @return middle point
     */
    public Point getMiddlePoint() {
        int startX = 0, startY = 0, endX = 0, endY = 0;
        DrawLine drawLine = null;
        if (drawLines.size() < 5) {
            drawLine = drawLines.get(0);
        } else {
            drawLine = drawLines.get(1);
        }
        try { // If there is no length of DrawLine, null is entered.
            startX = drawLine.getStartLeft();
            startY = drawLine.getStartTop();
            endX = drawLine.getEndLeft();
            endY = drawLine.getEndTop();
        } catch (Exception e) {
            startX = drawItems[0].getAttributeAsInt("left");
            startY = drawItems[0].getAttributeAsInt("top");
            endX = startX;
            endY = startY;
        }
        int middleX = (startX + endX) / 2;
        int middleY = (startY + endY) / 2;
        return new Point(middleX, middleY);
    }
}
