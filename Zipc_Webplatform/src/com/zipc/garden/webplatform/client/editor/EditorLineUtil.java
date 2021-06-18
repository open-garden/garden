package com.zipc.garden.webplatform.client.editor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;

import com.smartgwt.client.types.ArrowStyle;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.types.KnobType;
import com.smartgwt.client.types.LinePattern;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawLabel;
import com.smartgwt.client.widgets.drawing.DrawLine;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.drawing.Point;
import com.smartgwt.client.widgets.drawing.events.DragMove;
import com.smartgwt.client.widgets.drawing.events.DragMoveHandler;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.zipc.garden.model.arc.ARCFactory;
import com.zipc.garden.model.arc.ARCState;
import com.zipc.garden.model.core.AbstractLine;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.fsm.FSMFactory;
import com.zipc.garden.webplatform.client.editor.fsm.FSMEditor;

/**
 * A class that summarizes the processing related to the transition line drawn on the editor.
 */
public final class EditorLineUtil {

    /** Color used for line or label text (BLUE) */
    private static final String BLUE = "blue";

    /** Color used for line or label text (BLACK) */
    private static final String BLACK = "black";

    /** Color used for line or label text (GRAY) */
    private static final String GRAY = "gray";

    /**
     * private constructor to block the instantiation from other class.
     */
    private EditorLineUtil() {
    }

    /**
     * Method to execute when selecting the transition line.<br>
     * The color of the transition lines and text changes.
     * @param drawLabel Label on transition line
     * @param drawLines Transition line
     * @param dotLines Manhattan line resized dotted line
     * @param selectFlag Transition line selection flag
     */
    public static void selectDrawLine(DrawLabel drawLabel, List<DrawLine> drawLines, List<DrawLine> dotLines, boolean selectFlag) {
        if (selectFlag) {
            drawLabel.setLineColor(BLUE);
        } else {
            drawLabel.setLineColor(BLACK);
        }
        for (int i = 0; i < drawLines.size(); i++) {
            DrawLine drawLine = drawLines.get(i);
            if (selectFlag) {
                drawLine.setLineColor(BLUE);
                drawLine.bringToFront();
                if (i == 0) {
                    drawLine.showKnobs(KnobType.STARTPOINT);
                }
                if (i == drawLines.size() - 1) {
                    drawLine.showKnobs(KnobType.ENDPOINT);
                }
            } else {
                drawLine.setLineColor(GRAY);
                if (i == 0) {
                    drawLine.hideKnobs(KnobType.STARTPOINT);
                }
                if (i == drawLines.size() - 1) {
                    drawLine.hideKnobs(KnobType.ENDPOINT);
                }
                dotLines.forEach(dotLine -> dotLine.erase());
            }
        }
    }

    /**
     * Get the selected state of the transition line.
     * @param drawLine Transition line to check the selection state
     * @return True if selected
     */
    public static boolean getSelectFlag(DrawLine drawLine) {
        return BLUE.equals(drawLine.getLineColor());
    }

    /**
     * The Manhattan line is redrawn.
     * @param drawlines Transition line information
     * @param manhattanPoints Bending position of the Manhattan line
     * @param editorDrawLine Transition line and connection destination information
     */
    public static void redrawManhattan(List<DrawLine> drawlines, List<double[]> manhattanPoints, EditorDrawLine editorDrawLine) {
        for (int i = 0; i < drawlines.size(); i++) {
            DrawLine dl = drawlines.get(i);
            Point startPoint = new Point(manhattanPoints.get(i)[0], manhattanPoints.get(i)[1]);
            dl.setStartPoint(startPoint);
            Point endPoint = new Point(manhattanPoints.get(i + 1)[0], manhattanPoints.get(i + 1)[1]);
            dl.setEndPoint(endPoint);
            editorDrawLine.getDrawLinesTransparent().get(i).setStartPoint(startPoint);
            editorDrawLine.getDrawLinesTransparent().get(i).setEndPoint(endPoint);
        }
    }

    /**
     * This is a process to draw a new transition line on the canvas.
     * @param drawPane Canvas for drawing transition line
     * @param line Transition line information stored in the model.
     * @param source The connection source Item being drawn
     * @param target The connection target Item being drawn
     * @param sourceState True if the State type is Choice(rhombus)
     * @param targetState True if the State type is Choice(rhombus)
     * @return {@link EditorDrawLine}
     */
    public static EditorDrawLine makeNewDrawLine(DrawPane drawPane, AbstractLine line, DrawItem source, DrawItem target, boolean sourceState, boolean targetState) {
        DrawLine drawLine = new DrawLine();
        drawLine.setDrawPane(drawPane);
        drawLine.draw();
        drawLine.setStartPoint(lineIntersection(line, source, target, source, sourceState));
        drawLine.setEndPoint(lineIntersection(line, source, target, target, targetState));
        drawLine.setEndArrow(ArrowStyle.OPEN);
        drawLine.setLineWidth(2);
        drawLine.setCursor(Cursor.MOVE);

        EditorDrawLine editorDrawLine = new EditorDrawLine();
        editorDrawLine.getDrawLines().add(drawLine);
        editorDrawLine.getDrawItems()[0] = source;
        editorDrawLine.getDrawItems()[1] = target;

        return editorDrawLine;
    }

    /**
     * This is a process to draw a new transition line on the canvas.<br>
     * A transition line to self is created.
     * @param drawPane Canvas for drawing transition line
     * @param source The connection source Item being drawn
     * @param target The connection target Item being drawn
     * @return {@link EditorDrawLine}
     */
    public static EditorDrawLine makeNewDrawLine_Self(DrawPane drawPane, DrawItem source, DrawItem target) {

        DrawLine firstLine = new DrawLine();
        firstLine.setDrawPane(drawPane);
        firstLine.draw();
        firstLine.setLineWidth(2);
        firstLine.setCursor(Cursor.MOVE);

        DrawLine secondLine = new DrawLine();
        secondLine.setDrawPane(drawPane);
        secondLine.draw();
        secondLine.setLineWidth(2);
        secondLine.setCursor(Cursor.MOVE);

        DrawLine thirdLine = new DrawLine();
        thirdLine.setDrawPane(drawPane);
        thirdLine.draw();
        thirdLine.setLineWidth(2);
        thirdLine.setCursor(Cursor.MOVE);

        DrawLine fourthLine = new DrawLine();
        fourthLine.setDrawPane(drawPane);
        fourthLine.draw();
        fourthLine.setEndArrow(ArrowStyle.OPEN);
        fourthLine.setLineWidth(2);
        fourthLine.setCursor(Cursor.MOVE);

        EditorDrawLine editorDrawLine = new EditorDrawLine();
        editorDrawLine.getDrawLines().add(firstLine);
        editorDrawLine.getDrawLines().add(secondLine);
        editorDrawLine.getDrawLines().add(thirdLine);
        editorDrawLine.getDrawLines().add(fourthLine);

        editorDrawLine.getDrawItems()[0] = source;
        editorDrawLine.getDrawItems()[1] = target;

        return editorDrawLine;
    }

    /**
     * This is a process to draw a new transition line on the canvas.<br>
     * A Manhattan line is created.
     * @param line Transition line information stored in the model.
     * @param drawPane Canvas for drawing transition line
     * @param source The connection source Item being drawn
     * @param target The connection target Item being drawn
     * @return {@link EditorDrawLine}
     */
    public static EditorDrawLine makeDrawLine_Manhattan(AbstractLine line, DrawPane drawPane, DrawItem source, DrawItem target) {
        EditorDrawLine editorDrawLine = new EditorDrawLine();

        DrawLine drawLine = null;
        Iterator<AbstractPoint> itr = line.getConnectionPoint().iterator();
        while (itr.hasNext()) {
            AbstractPoint arcPoint = itr.next();
            Point point = new Point(arcPoint.getX(), arcPoint.getY());
            if (drawLine != null) {
                drawLine.setEndPoint(point);
                editorDrawLine.getDrawLines().add(drawLine);
            }
            if (itr.hasNext()) {
                drawLine = new DrawLine();
                drawLine.setDrawPane(drawPane);
                drawLine.draw();
                drawLine.setLineWidth(2);
                drawLine.setCursor(Cursor.MOVE);
                drawLine.setStartPoint(point);
            } else {
                drawLine.setEndArrow(ArrowStyle.OPEN);
            }
        }
        editorDrawLine.getDrawItems()[0] = source;
        editorDrawLine.getDrawItems()[1] = target;

        return editorDrawLine;
    }

    /**
     * This is the process of drawing a new label on the transition line.
     * @param drawPane Canvas for drawing labels
     * @param drawLine Associated transition line
     * @param contents Label content
     * @param x The x coordinate of the label to draw
     * @param y The y coordinate of the label to draw
     */
    public static void drawNewLabel(DrawPane drawPane, EditorDrawLine drawLine, String contents, int x, int y) {
        Point mPoint = drawLine.getMiddlePoint();

        DrawLabel drawLabel = new DrawLabel();
        drawLabel.setDrawPane(drawPane);
        drawLabel.setCanDrag(true);
        drawLabel.setContents(contents.toString());
        drawLabel.setFontSize(13);
        drawLabel.setLineColor(BLACK);
        drawLabel.setFontWeight("normal");
        drawLabel.setTop(y + mPoint.getY());
        drawLabel.setLeft(x + mPoint.getX());
        drawLabel.bringToFront();
        drawLabel.draw();
        drawLabel.addClickHandler(new com.smartgwt.client.widgets.drawing.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.drawing.events.ClickEvent event) {
                drawLine.getDrawLines().get(0).fireEvent(new ClickEvent(drawLine.getDrawLines().get(0).getJsObj()));
            }
        });

        drawLine.setDrawLabel(drawLabel);
    }

    /**
     * The process of creating transparent lines that work in conjunction with drawLines.
     * @param editorDrawLine {@link EditorDrawLine}
     * @param drawPane Canvas for drawing transparent lines
     */
    public static void createDrawLinesTransparent(EditorDrawLine editorDrawLine, DrawPane drawPane) {
        editorDrawLine.getDrawLinesTransparent().forEach(line -> line.erase());
        editorDrawLine.getDrawLinesTransparent().clear();
        editorDrawLine.getDrawLines().forEach(line -> {
            DrawLine drawLine = new DrawLine();
            drawLine.setDrawPane(drawPane);
            drawLine.draw();
            drawLine.setStartPoint(line.getStartPoint());
            drawLine.setEndPoint(line.getEndPoint());
            drawLine.setLineWidth(10);
            drawLine.setLineColor(null);// If null, transparent
            drawLine.setCursor(Cursor.MOVE);
            editorDrawLine.getDrawLinesTransparent().add(drawLine);
        });
    }

    /**
     * Calculates and gets the coordinate position where the transition line is connected.
     * @param drawItem Element with transition line connected
     * @param x X-coordinate of transition line
     * @param y Y-coordinate of transition line
     * @return Calculation result of connection position
     */
    public static double[] getCalculationResultOfAnchor(DrawItem drawItem, int x, int y) {
        double top = drawItem.getAttributeAsInt("top");
        double left = drawItem.getAttributeAsInt("left");
        double bottom = drawItem.getAttributeAsInt("top") + drawItem.getAttributeAsInt("height");
        double right = drawItem.getAttributeAsInt("left") + drawItem.getAttributeAsInt("width");
        double anchorX = (x - left) / (right - left);
        double anchorY = (y - top) / (bottom - top);

        anchorX = anchorX < 0 ? 0 : anchorX > 1 ? 1 : anchorX;
        anchorY = anchorY < 0 ? 0 : anchorY > 1 ? 1 : anchorY;

        return new double[] { anchorX, anchorY };
    }

    /**
     * Calculates and gets the coordinate position where the transition line is connected.<br>
     * Coordinate positions of the connection source and the connection destination are acquired.
     * @param mPoints Coordinate position of the Manhattan line
     * @param sLeft X-coordinate of connection source
     * @param sWidth The width of the source element
     * @param sTop Y-coordinate of connection source
     * @param sHeight The height of the source element
     * @param tLeft X-coordinate of connection target
     * @param tWidth The width of the target element
     * @param tTop Y-coordinate of connection target
     * @param tHeight The height of the target element
     * @return Coordinate position where the transition line is connected
     */
    public static List<double[]> getAnchor(List<double[]> mPoints, int sLeft, int sWidth, int sTop, int sHeight, int tLeft, int tWidth, int tTop, int tHeight) {
        double sAnchorX = (mPoints.get(0)[0] - sLeft) / sWidth;
        double sAnchorY = (mPoints.get(0)[1] - sTop) / sHeight;
        double tAnchorX = (mPoints.get(mPoints.size() - 1)[0] - tLeft) / tWidth;
        double tAnchorY = (mPoints.get(mPoints.size() - 1)[1] - tTop) / tHeight;
        List<double[]> pos = new ArrayList<double[]>();
        double[] source = { sAnchorX, sAnchorY };
        double[] target = { tAnchorX, tAnchorY };
        pos.add(source);
        pos.add(target);
        return pos;
    }

    /**
     * Gets the coordinate position where the transition line intersects with the element.
     * @param line Transition line information
     * @param source Source element
     * @param target Target element
     * @param drawItem Specify source or target. The connection position of the element specified here is acquired.
     * @param isChoice True if the element type is Choice
     * @return Element connection position
     */
    public static Point lineIntersection(AbstractLine line, DrawItem source, DrawItem target, DrawItem drawItem, boolean isChoice) {
        Point ret = null;

        double ax = source.getAttributeAsInt("left") + source.getAttributeAsInt("width") * line.getSourceAnchorX();
        double ay = source.getAttributeAsInt("top") + source.getAttributeAsInt("height") * line.getSourceAnchorY();
        double bx = target.getAttributeAsInt("left") + target.getAttributeAsInt("width") * line.getTargetAnchorX();
        double by = target.getAttributeAsInt("top") + target.getAttributeAsInt("height") * line.getTargetAnchorY();

        int height = drawItem.getAttributeAsInt("height");
        int width = drawItem.getAttributeAsInt("width");
        int top = drawItem.getAttributeAsInt("top");
        int left = drawItem.getAttributeAsInt("left");
        int right = left + width;
        int bottom = top + height;

        double[] startX;
        double[] startY;
        double[] endX;
        double[] endY;

        if (isChoice) {
            startX = new double[] { left, width / 2 + left, right, width / 2 + left };
            startY = new double[] { height / 2 + top, top, height / 2 + top, bottom };
            endX = new double[] { width / 2 + left, right, width / 2 + left, left };
            endY = new double[] { top, height / 2 + top, bottom, height / 2 + top };
        } else {
            startX = new double[] { left, right, right, left };
            startY = new double[] { top, top, bottom, bottom };
            endX = new double[] { right, right, left, left };
            endY = new double[] { top, bottom, bottom, top };
        }

        for (int i = 0; i < startX.length; i++) {

            // denominator
            double d = (bx - ax) * (endY[i] - startY[i]) - (by - ay) * (endX[i] - startX[i]);

            // line1 … Ratio of inside to the intersection point
            double u = ((startX[i] - ax) * (endY[i] - startY[i]) - (startY[i] - ay) * (endX[i] - startX[i])) / d;

            // line2 … Ratio of inside to the intersection point
            double v = ((startX[i] - ax) * (by - ay) - (startY[i] - ay) * (bx - ax)) / d;

            if (u < 0 || u > 1 || v < 0 || v > 1) {
                continue;
            }

            double x = ax + u * (bx - ax);
            double y = ay + u * (by - ay);
            ret = new Point(x, y);
            break;
        }
        return ret;
    }

    /**
     * Gets the coordinate position where the Manhattan line intersects the element.
     * @param sourceAnchorPoint Start position of transition line
     * @param targetAnchorPoint End position of transition line
     * @param sourceState Variable with the width (=[0]) and height (=[1]) of the connection source
     * @param targetState Variable with the width (=[0]) and height (=[1]) of the connection target
     * @return Coordinate position where the Manhattan line intersects the element
     */
    protected static List<double[]> manhattanIntersection(double[] sourceAnchorPoint, double[] targetAnchorPoint, double[] sourceState, double[] targetState) {

        double ax = sourceAnchorPoint[0];
        double ay = sourceAnchorPoint[1];
        double bx = targetAnchorPoint[0];
        double by = targetAnchorPoint[1];
        // StateA,Bの幅、高さ
        double aWidth = sourceState[0];
        double aHeight = sourceState[1];
        double bWidth = targetState[0];
        double bHeight = targetState[1];
        // StateA,BのLeft、Top
        double aLeft = sourceState[2];
        double aTop = sourceState[3];
        double bLeft = targetState[2];
        double bTop = targetState[3];

        // leftとの距離
        double xLeftA = ax - aLeft;
        double xLeftB = bx - bLeft;
        // rightとの距離
        double xRightA = aWidth - xLeftA;
        double xRightB = bWidth - xLeftB;
        // topとの距離
        double yTopA = ay - aTop;
        double yTopB = by - bTop;
        // bottomとの距離
        double yBottomA = aHeight - yTopA;
        double yBottomB = bHeight - yTopB;

        double x1 = 0;
        double y1 = 0;
        double x6 = 0;
        double y6 = 0;

        if (xRightA <= yBottomA && xRightA <= xLeftA && xRightA <= yTopA) {
            x1 = aLeft + aWidth;
            y1 = ay;
        } else if (yBottomA <= xLeftA && yBottomA <= yTopA && yBottomA < xRightA) {
            x1 = ax;
            y1 = aTop + aHeight;
        } else if (xLeftA <= yTopA && xLeftA < xRightA && xLeftA < yBottomA) {
            x1 = aLeft;
            y1 = ay;
        } else if (yTopA < xRightA && yTopA < yBottomA && yTopA < xLeftA) {
            x1 = ax;
            y1 = aTop;
        }

        if (xRightB <= yBottomB && xRightB <= xLeftB && xRightB <= yTopB) {
            x6 = bLeft + bWidth;
            y6 = by;
        } else if (yBottomB <= xLeftB && yBottomB <= yTopB && yBottomB < xRightB) {
            x6 = bx;
            y6 = bTop + bHeight;
        } else if (xLeftB <= yTopB && xLeftB < xRightB && xLeftB < yBottomB) {
            x6 = bLeft;
            y6 = by;
        } else if (yTopB < xRightB && yTopB < yBottomB && yTopB < xLeftB) {
            x6 = bx;
            y6 = bTop;
        }

        List<double[]> ret = new ArrayList<double[]>();
        double p1[] = { x1, y1 };
        double p6[] = { x6, y6 };
        ret.add(p1);
        ret.add(p6);
        return ret;
    }

    /**
     * Converts the coordinate position of the Manhattan line to AbstractPoint and acquires it.
     * @param manhattanPoints Coordinate position of the Manhattan line.
     * @param editor The Editor drawing the transition line
     * @return List of AbstractPoint
     */
    public static List<AbstractPoint> getConnectionPoint(List<double[]> manhattanPoints, Editor editor) {
        List<AbstractPoint> points = new ArrayList<AbstractPoint>();

        AbstractPoint point;
        if (editor instanceof FSMEditor) {
            point = FSMFactory.eINSTANCE.createFSMDPoint();
        } else {
            point = ARCFactory.eINSTANCE.createARCPoint();
        }
        manhattanPoints.forEach(p -> {
            AbstractPoint tmpPoint = EcoreUtil.copy(point);
            tmpPoint.setX((int) p[0]);
            tmpPoint.setY((int) p[1]);
            points.add(tmpPoint);
        });

        return points;
    }

    /**
     * The coordinate position of the Manhattan line is obtained based on the drawn DrawItem.
     * @param line Transition line information
     * @param source Connection source information
     * @param target Connection target information
     * @param startPoint Start position of transition line
     * @param endPoint End position of transition line
     * @return Coordinate position of the Manhattan line
     */
    public static List<double[]> getManhattanPoint(AbstractLine line, DrawItem source, DrawItem target, double[] startPoint, double[] endPoint) {
        List<double[]> anchorPoint = new ArrayList<double[]>();
        double sourceAnchor[] = { line.getSourceAnchorX(), line.getSourceAnchorY() };
        double targetAnchor[] = { line.getTargetAnchorX(), line.getTargetAnchorY() };
        anchorPoint.add(sourceAnchor);
        anchorPoint.add(targetAnchor);
        List<double[]> manhattanPoints = getCalculationManhattanPoint(anchorPoint, source, target, startPoint, endPoint);
        return manhattanPoints;
    }

    /**
     * The coordinate position of the Manhattan line is obtained based on the ARCState of the EMF model.
     * @param line Transition line information
     * @param source Connection source information
     * @param target Connection target information
     * @param startPoint Start position of transition line
     * @param endPoint End position of transition line
     * @return Coordinate position of the Manhattan line
     */
    public static List<double[]> getManhattanPoint(AbstractLine line, ARCState source, ARCState target, double[] startPoint, double[] endPoint) {
        List<double[]> anchorPoint = new ArrayList<double[]>();
        double sourceAnchor[] = { line.getSourceAnchorX(), line.getSourceAnchorY() };
        double targetAnchor[] = { line.getTargetAnchorX(), line.getTargetAnchorY() };
        anchorPoint.add(sourceAnchor);
        anchorPoint.add(targetAnchor);
        List<double[]> manhattanPoints = getCalculationManhattanPoint(anchorPoint, source, target, startPoint, endPoint);
        return manhattanPoints;
    }

    /**
     * Gets the calculation result of the coordinate position of the Manhattan line.<br>
     * Calculated based on ARCState.
     * @param anchor Coordinate position where the transition line is connected
     * @param source Connection source information
     * @param target Connection target information
     * @param startPoint Start position of transition line
     * @param endPoint End position of transition line
     * @return Coordinate position of the Manhattan line
     */
    public static List<double[]> getCalculationManhattanPoint(List<double[]> anchor, ARCState source, ARCState target, double[] startPoint, double[] endPoint) {
        // StateA,Bの幅、高さ
        double widthS = source.getWidth();
        double heightS = source.getHeight();
        double widthT = target.getWidth();
        double heightT = target.getHeight();
        // StateA,BのLeft、Top
        double leftS = source.getLeft();
        double topS = source.getTop();
        double leftT = target.getLeft();
        double topT = target.getTop();
        return getCalculationManhattanPoint(anchor, widthS, heightS, widthT, heightT, leftS, topS, leftT, topT, startPoint, endPoint);
    }

    /**
     * Gets the calculation result of the coordinate position of the Manhattan line.<br>
     * Calculated based on DrawItem.
     * @param anchor Coordinate position where the transition line is connected
     * @param source Connection source information
     * @param target Connection target information
     * @param startPoint Start position of transition line
     * @param endPoint End position of transition line
     * @return Coordinate position of the Manhattan line
     */
    public static List<double[]> getCalculationManhattanPoint(List<double[]> anchor, DrawItem source, DrawItem target, double[] startPoint, double[] endPoint) {
        // StateSource,targetの幅、高さ
        double widthS = source.getAttributeAsInt("width");
        double heightS = source.getAttributeAsInt("height");
        double widthT = target.getAttributeAsInt("width");
        double heightT = target.getAttributeAsInt("height");
        // StateSource,targetのLeft、Top
        double leftS = source.getAttributeAsInt("left");
        double topS = source.getAttributeAsInt("top");
        double leftT = target.getAttributeAsInt("left");
        double topT = target.getAttributeAsInt("top");
        return getCalculationManhattanPoint(anchor, widthS, heightS, widthT, heightT, leftS, topS, leftT, topT, startPoint, endPoint);

    }

    /**
     * This is the process of calculating the coordinate position of the Manhattan line.
     * @param anchor Coordinate position where the transition line is connected
     * @param widthS The width of the source element
     * @param heightS The height of the source element
     * @param widthT The width of the target element
     * @param heightT The height of the target element
     * @param leftS X-coordinate of connection source
     * @param topS Y-coordinate of connection source
     * @param leftT X-coordinate of connection target
     * @param topT Y-coordinate of connection target
     * @param startPoint Start position of transition line
     * @param endPoint End position of transition line
     * @return Coordinate position of the Manhattan line
     */
    private static List<double[]> getCalculationManhattanPoint(List<double[]> anchor, double widthS, double heightS, double widthT, double heightT, double leftS, double topS,
            double leftT, double topT, double[] startPoint, double[] endPoint) {
        double[] stateS = { widthS, heightS, leftS, topS };
        double[] stateT = { widthT, heightT, leftT, topT };
        // StateSource,targetの中心
        double anchorSX = leftS + widthS / 2;
        double anchorSY = topS + heightS / 2;
        double anchorTX = leftT + widthT / 2;
        double anchorTY = topT + heightT / 2;
        // スタート、エンド
        double x1 = 0;
        double y1 = 0;
        double x6 = 0;
        double y6 = 0;
        double[] sourceAnchorPoint = { widthS * anchor.get(0)[0] + leftS, heightS * anchor.get(0)[1] + topS };
        double[] targetAnchorPoint = { widthT * anchor.get(1)[0] + leftT, heightT * anchor.get(1)[1] + topT };

        // StateA,Bそれぞれの線との接点を決める
        if (startPoint != null) {
            // move start point
            double[] sourcePointMove = { widthS * startPoint[0] + leftS, heightS * startPoint[1] + topS };
            List<double[]> intersections = manhattanIntersection(sourcePointMove, targetAnchorPoint, stateS, stateT);
            x1 = intersections.get(0)[0];
            y1 = intersections.get(0)[1];
            x6 = intersections.get(1)[0];
            y6 = intersections.get(1)[1];
        } else if (endPoint != null) {
            // move end point
            double[] targetPointMove = { widthT * endPoint[0] + leftT, heightT * endPoint[1] + topT };
            List<double[]> intersections = manhattanIntersection(sourceAnchorPoint, targetPointMove, stateS, stateT);
            x1 = intersections.get(0)[0];
            y1 = intersections.get(0)[1];
            x6 = intersections.get(1)[0];
            y6 = intersections.get(1)[1];
        } else if (startPoint == null && endPoint == null
                && !(anchorSX == sourceAnchorPoint[0] && anchorSY == sourceAnchorPoint[1] && anchorTX == targetAnchorPoint[0] && anchorTY == targetAnchorPoint[1])) {
            // redraw
            List<double[]> intersections = manhattanIntersection(sourceAnchorPoint, targetAnchorPoint, stateS, stateT);
            x1 = intersections.get(0)[0];
            y1 = intersections.get(0)[1];
            x6 = intersections.get(1)[0];
            y6 = intersections.get(1)[1];
        } else {
            // AddLineの時
            if (Math.abs(anchorTX - anchorSX) - Math.abs(anchorTY - anchorSY) >= 0) {
                // x軸方向に離れている時

                if (anchorTX >= anchorSX) {
                    // StateBの方が右にある時
                    x1 = anchorSX + widthS / 2;
                    y1 = anchorSY;
                    x6 = anchorTX - widthT / 2;
                    y6 = anchorTY;
                } else {
                    // StateAの方が右にある時
                    x1 = anchorSX - widthS / 2;
                    y1 = anchorSY;
                    x6 = anchorTX + widthT / 2;
                    y6 = anchorTY;
                }

            } else {
                // y軸方向に離れている時

                if (anchorTY >= anchorSY) {
                    // StateBの方が上にある時
                    x1 = anchorSX;
                    y1 = anchorSY + heightS / 2;
                    x6 = anchorTX;
                    y6 = anchorTY - heightT / 2;

                } else {
                    // StateAの方が上にある時
                    x1 = anchorSX;
                    y1 = anchorSY - heightS / 2;
                    x6 = anchorTX;
                    y6 = anchorTY + heightT / 2;
                }
            }
        }
        double x2 = 0;
        double y2 = 0;
        double x5 = 0;
        double y5 = 0;
        // StateA,Bそれぞれの一つ目のポイントまでの長さ
        double l = 20;
        // StateA側の2つ目のポイント
        if (x1 == leftS) {
            x2 = x1 - l;
            y2 = y1;
        } else if (x1 == leftS + widthS) {
            x2 = x1 + l;
            y2 = y1;
        } else {
            if (y1 == topS) {
                x2 = x1;
                y2 = y1 - l;
            } else {
                x2 = x1;
                y2 = y1 + l;
            }
        }
        // StateB側の2つ目のポイント
        if (x6 == leftT) {
            x5 = x6 - l;
            y5 = y6;
        } else if (x6 == leftT + widthT) {
            x5 = x6 + l;
            y5 = y6;
        } else {
            if (y6 == topT) {
                x5 = x6;
                y5 = y6 - l;
            } else {
                x5 = x6;
                y5 = y6 + l;
            }
        }
        // 3つ目のポイント
        double x3 = 0;
        double y3 = 0;
        // 4つ目のポイント
        double x4 = 0;
        double y4 = 0;

        // 平行か垂直か判定する
        if ((x1 == x2 && x5 == x6) || (y1 == y2 && y5 == y6)) {
            // 平行の時

            if (x1 == x2 && x5 == x6) {
                // y軸方向に平行の時

                if (Math.abs(y6 - y1) > Math.abs(y5 - y2)) {
                    // 近づく時（接点同士が向き合っている時）
                    if (topS == y1) {
                        if (y1 - y6 < 2 * l) {
                            x3 = (x2 + x5) / 2;
                            y3 = y2;
                            x4 = (x2 + x5) / 2;
                            y4 = y5;
                        } else {
                            x3 = x2;
                            y3 = y5;
                            x4 = x3;
                            y4 = y3;
                        }
                    } else {
                        if (y6 - y1 < 2 * l) {
                            x3 = (x2 + x5) / 2;
                            y3 = y2;
                            x4 = (x2 + x5) / 2;
                            y4 = y5;
                        } else {
                            x3 = x2;
                            y3 = y5;
                            x4 = x3;
                            y4 = y3;
                        }
                    }

                } else if (Math.abs(y6 - y1) == Math.abs(y5 - y2)) {
                    // 距離が変わらない時

                    if (y1 == y5 && y2 == y6) {
                        // 向き合っている状態で距離が変わらない時
                        y3 = y2;
                        y4 = y5;
                        if (anchorTX >= anchorSX) {
                            x3 = (anchorTX - widthT / 2 + anchorSX + widthS / 2) / 2;
                            x4 = (anchorTX - widthT / 2 + anchorSX + widthS / 2) / 2;
                        } else {
                            x3 = (anchorTX + widthT / 2 + anchorSX - widthS / 2) / 2;
                            x4 = (anchorTX + widthT / 2 + anchorSX - widthS / 2) / 2;
                        }
                    } else {
                        // 接点同士が同じ方向の時
                        if (Math.abs(anchorTX - anchorSX) >= Math.abs((widthS + widthT) / 2)) {
                            // State同士がx軸方向に十分離れている時

                            if (y1 == topS) {
                                // topAに線が接している時
                                if (topT >= topS) {
                                    x3 = x5;
                                    y3 = y2;
                                    x4 = x3;
                                    y4 = y3;
                                } else {
                                    x3 = x2;
                                    y3 = y5;
                                    x4 = x3;
                                    y4 = y3;
                                }
                            } else {
                                // bottomAに線が接している時
                                if (topT + heightT >= topS + heightS) {
                                    x3 = x2;
                                    y3 = y5;
                                    x4 = x3;
                                    y4 = y3;
                                } else {
                                    x3 = x5;
                                    y3 = y2;
                                    x4 = x3;
                                    y4 = y3;
                                }
                            }
                        } else {
                            // State同士がx軸方向に近い時

                            if (y1 == topS) {
                                // topAに線が接している時
                                if (anchorTY >= anchorSY) {
                                    if (x6 < leftS + widthS + l && anchorTX >= anchorSX) {
                                        x3 = leftS + widthS + l;
                                        y3 = y2;
                                        x4 = leftS + widthS + l;
                                        y4 = y5;
                                    } else if (anchorTX < anchorSX && leftS - l > x6) {
                                        x3 = leftS - l;
                                        y3 = y2;
                                        x4 = leftS - l;
                                        y4 = y5;
                                    } else {
                                        x3 = leftS - l;
                                        y3 = y2;
                                        x4 = leftS - l;
                                        y4 = y5;
                                    }
                                } else {
                                    if (leftT - l < x1 && anchorTX >= anchorSX) {
                                        x3 = leftT - l;
                                        y3 = y2;
                                        x4 = leftT - l;
                                        y4 = y5;
                                    } else if (anchorTX < anchorSX && leftT + widthT + l > x1) {
                                        x3 = leftT + widthT + l;
                                        y3 = y2;
                                        x4 = leftT + widthT + l;
                                        y4 = y5;
                                    } else {
                                        x3 = x2;
                                        y3 = y5;
                                        x4 = x3;
                                        y4 = y3;
                                    }
                                }
                            } else {
                                if (anchorTY >= anchorSY) {
                                    if (leftT - l < x1 && anchorTX >= anchorSX) {
                                        x3 = leftT - l;
                                        y3 = y2;
                                        x4 = leftT - l;
                                        y4 = y5;
                                    } else if (leftT + widthT + l > x1 && anchorTX < anchorSX) {
                                        x3 = leftT + widthT + l;
                                        y3 = y2;
                                        x4 = leftT + widthT + l;
                                        y4 = y5;
                                    } else {
                                        x3 = x2;
                                        y3 = y5;
                                        x4 = x3;
                                        y4 = y3;
                                    }
                                } else {
                                    if (x6 < leftS + widthS + l && anchorTX > anchorSX) {
                                        x3 = leftS + widthS + l;
                                        y3 = y2;
                                        x4 = leftS + widthS + l;
                                        y4 = y5;
                                    } else if (anchorTX <= anchorSX && x6 >= leftS - l) {
                                        x3 = leftS - l;
                                        y3 = y2;
                                        x4 = leftS - l;
                                        y4 = y5;
                                    } else {
                                        x3 = x5;
                                        y3 = y2;
                                        x4 = x3;
                                        y4 = y3;
                                    }
                                }
                            }
                        }
                    }

                } else {
                    // 距離が遠くなる時（接点同士が反対方向の時）

                    if (Math.abs(anchorTX - anchorSX) > Math.abs((widthS + widthT) / 2)) {
                        // State同士がx軸方向に十分離れている時
                        y3 = y2;
                        y4 = y5;
                        if (anchorTX >= anchorSX) {
                            x3 = (anchorTX - widthT / 2 + anchorSX + widthS / 2) / 2;
                            x4 = (anchorTX - widthT / 2 + anchorSX + widthS / 2) / 2;
                        } else {
                            x3 = (anchorTX + widthT / 2 + anchorSX - widthS / 2) / 2;
                            x4 = (anchorTX + widthT / 2 + anchorSX - widthS / 2) / 2;
                        }

                    } else {
                        // State同士がx軸方向に近い時（重なっている時）

                        if (leftS + widthS >= leftT + widthT) {
                            // StateAの方が右側にある
                            x3 = leftS + widthS + l;
                            y3 = y2;
                            x4 = leftS + widthS + l;
                            y4 = y5;

                        } else {
                            // StateBの方が右側にある
                            x3 = leftT + widthT + l;
                            y3 = y2;
                            x4 = leftT + widthT + l;
                            y4 = y5;
                        }
                    }
                }
            } else {
                // x軸方向に平行の時

                if (Math.abs(x6 - x1) > Math.abs(x5 - x2)) {
                    // 近づく時（接点同士が向き合っている時）
                    if (leftS == x1) {
                        if (leftS - (leftT + widthT) < 2 * l) {
                            x3 = x2;
                            y3 = (y2 + y5) / 2;
                            x4 = x5;
                            y4 = (y2 + y5) / 2;
                        } else {
                            x3 = x5;
                            y3 = y2;
                            x4 = x3;
                            y4 = y3;
                        }
                    } else {
                        if (leftT - (leftS + widthS) < 2 * l) {
                            x3 = x2;
                            y3 = (y2 + y5) / 2;
                            x4 = x5;
                            y4 = (y2 + y5) / 2;
                        } else {
                            x3 = x5;
                            y3 = y2;
                            x4 = x3;
                            y4 = y3;
                        }
                    }
                } else if (Math.abs(x6 - x1) == Math.abs(x5 - x2)) {
                    // 距離が変わらない時

                    if (x1 == x5 && x2 == x6) {
                        // 向き合っている状態で距離が変わらない時
                        x3 = x2;
                        x4 = x5;
                        if (anchorTY >= anchorSY) {
                            y3 = (anchorTY - heightT / 2 + anchorSY + heightS / 2) / 2;
                            y4 = (anchorTY - heightT / 2 + anchorSY + heightS / 2) / 2;
                        } else {
                            y3 = (anchorTY + heightT / 2 + anchorSY - heightS / 2) / 2;
                            y4 = (anchorTY + heightT / 2 + anchorSY - heightS / 2) / 2;
                        }
                    } else {
                        // 接点同士が同じ方向の時
                        if (Math.abs(anchorTY - anchorSY) > Math.abs((heightS + heightT) / 2)) {
                            // State同士がy軸方向に十分離れている時
                            if (leftS + widthS == x1) {
                                if (leftS + widthS + l <= x5) {
                                    x3 = x5;
                                    y3 = y2;
                                    x4 = x3;
                                    y4 = y3;
                                } else {
                                    x3 = x2;
                                    y3 = y5;
                                    x4 = x3;
                                    y4 = y3;
                                }
                            } else {
                                if (leftS - l >= x5) {
                                    x3 = x5;
                                    y3 = y2;
                                    x4 = x3;
                                    y4 = y3;
                                } else {
                                    x3 = x2;
                                    y3 = y5;
                                    x4 = x3;
                                    y4 = y3;
                                }
                            }
                        } else {
                            if (x1 == leftS) {
                                if (anchorTX >= anchorSX) {
                                    if (y6 < topS + heightS + l && anchorSY <= anchorTY) {
                                        x3 = x2;
                                        y3 = topS + heightS + l;
                                        x4 = x5;
                                        y4 = topS + heightS + l;
                                    } else if (anchorSY > anchorTY && y6 > topS - l) {
                                        x3 = x2;
                                        y3 = topS - l;
                                        x4 = x5;
                                        y4 = topS - l;
                                    } else {
                                        x3 = x5;
                                        y3 = y2;
                                        x4 = x3;
                                        y4 = y3;
                                    }
                                } else {
                                    if (y1 < topT + heightT + l && anchorTY <= anchorSY) {
                                        x3 = x2;
                                        y3 = topT + heightT + l;
                                        x4 = x5;
                                        y4 = topT + heightT + l;
                                    } else if (anchorTY > anchorSY && y1 > topT - l) {
                                        x3 = x2;
                                        y3 = topT - l;
                                        x4 = x5;
                                        y4 = topT - l;
                                    } else {
                                        x3 = x2;
                                        y3 = y5;
                                        x4 = x3;
                                        y4 = y3;
                                    }
                                }
                            } else {
                                if (anchorSX >= anchorTX) {
                                    if (y6 < topS + heightS + l && anchorSY <= anchorTY) {
                                        x3 = x2;
                                        y3 = topS + heightS + l;
                                        x4 = x5;
                                        y4 = topS + heightS + l;
                                    } else if (anchorSY > anchorTY && y6 > topS - l) {
                                        x3 = x2;
                                        y3 = topS - l;
                                        x4 = x5;
                                        y4 = topS - l;
                                    } else {
                                        x3 = x2;
                                        y3 = y5;
                                        x4 = x3;
                                        y4 = y3;
                                    }
                                } else {
                                    if (y1 < topT + heightT + l && anchorTY <= anchorSY) {
                                        x3 = x2;
                                        y3 = topT + heightT + l;
                                        x4 = x5;
                                        y4 = topT + heightT + l;
                                    } else if (anchorTY > anchorSY && y1 > topT - l) {
                                        x3 = x2;
                                        y3 = topT - l;
                                        x4 = x5;
                                        y4 = topT - l;
                                    } else {
                                        x3 = x5;
                                        y3 = y2;
                                        x4 = x3;
                                        y4 = y3;
                                    }
                                }
                            }
                        }
                    }

                } else {
                    // 距離が遠くなる時（接点同士が反対方向の時）

                    if (Math.abs(anchorTY - anchorSY) > Math.abs((heightS + heightT) / 2)) {
                        // State同士がy軸方向に十分離れている時
                        x3 = x2;
                        x4 = x5;
                        if (anchorTY >= anchorSY) {
                            y3 = (anchorTY - heightT / 2 + anchorSY + heightS / 2) / 2;
                            y4 = (anchorTY - heightT / 2 + anchorSY + heightS / 2) / 2;
                        } else {
                            y3 = (anchorTY + heightT / 2 + anchorSY - heightS / 2) / 2;
                            y4 = (anchorTY + heightT / 2 + anchorSY - heightS / 2) / 2;
                        }

                    } else {
                        // State同士がy軸方向に近い時（重なっている時）

                        if (topS + heightS >= topT + heightT) {
                            // StateAの方が下側にある
                            x3 = x2;
                            y3 = topS + heightS + l;
                            x4 = x5;
                            y4 = topS + heightS + l;

                        } else {
                            // StateBの方が下側にある
                            x3 = x2;
                            y3 = topT + heightT + l;
                            x4 = x5;
                            y4 = topT + heightT + l;
                        }
                    }
                }
            }

        } else {
            // 垂直の時
            if (y1 == topS) {
                if (x6 == leftT + widthT) {
                    if (leftT + widthT + l > x1) {
                        x3 = x5;
                        y3 = y2;
                        x4 = x3;
                        y4 = y3;
                    } else {
                        if (topS - l >= y6) {
                            x3 = x2;
                            y3 = y5;
                            x4 = x3;
                            y4 = y3;
                        } else {
                            x3 = x5;
                            y3 = y2;
                            x4 = x3;
                            y4 = y3;
                        }
                    }
                } else {
                    if (leftT - l > x1) {
                        if (topS - l >= y6) {
                            x3 = x2;
                            y3 = y5;
                            x4 = x3;
                            y4 = y3;
                        } else {
                            x3 = x5;
                            y3 = y2;
                            x4 = x3;
                            y4 = y3;
                        }
                    } else {
                        x3 = x5;
                        y3 = y2;
                        x4 = x3;
                        y4 = y3;
                    }
                }

            } else if (x1 == leftS + widthS) {
                if (y6 == topT + heightT) {
                    if (topT + heightT + l > y1) {
                        x3 = x2;
                        y3 = y5;
                        x4 = x3;
                        y4 = y3;
                    } else {
                        if (leftS + widthS + l >= x6) {
                            x3 = x2;
                            y3 = y5;
                            x4 = x3;
                            y4 = y3;
                        } else {
                            x3 = x5;
                            y3 = y2;
                            x4 = x3;
                            y4 = y3;
                        }
                    }
                } else {
                    if (topT - l > y1) {
                        if (leftS + widthS + l >= x6) {
                            x3 = x2;
                            y3 = y5;
                            x4 = x3;
                            y4 = y3;
                        } else {
                            x3 = x5;
                            y3 = y2;
                            x4 = x3;
                            y4 = y3;
                        }
                    } else {
                        x3 = x2;
                        y3 = y5;
                        x4 = x3;
                        y4 = y3;
                    }
                }
            } else if (y1 == topS + heightS) {
                if (x6 == leftT + widthT) {
                    if (leftT + widthT + l > x1) {
                        x3 = x5;
                        y3 = y2;
                        x4 = x3;
                        y4 = y3;
                    } else {
                        if (topS + heightS + l >= y6) {
                            x3 = x5;
                            y3 = y2;
                            x4 = x3;
                            y4 = y3;
                        } else {
                            x3 = x2;
                            y3 = y5;
                            x4 = x3;
                            y4 = y3;
                        }
                    }
                } else {
                    if (leftT - l > x1) {
                        if (topS + heightS + l >= y6) {
                            x3 = x5;
                            y3 = y2;
                            x4 = x3;
                            y4 = y3;
                        } else {
                            x3 = x2;
                            y3 = y5;
                            x4 = x3;
                            y4 = y3;
                        }
                    } else {
                        x3 = x5;
                        y3 = y2;
                        x4 = x3;
                        y4 = y3;
                    }
                }
            } else {
                if (y6 == topT + heightT) {
                    if (topT + heightT + l > y1) {
                        x3 = x2;
                        y3 = y5;
                        x4 = x3;
                        y4 = y3;
                    } else {
                        if (leftS - l <= x6) {
                            x3 = x2;
                            y3 = y5;
                            x4 = x3;
                            y4 = y3;
                        } else {
                            x3 = x5;
                            y3 = y2;
                            x4 = x3;
                            y4 = y3;
                        }
                    }
                } else {
                    if (topT - l > y1) {
                        if (leftS - l <= x6) {
                            x3 = x2;
                            y3 = y5;
                            x4 = x3;
                            y4 = y3;
                        } else {
                            x3 = x5;
                            y3 = y2;
                            x4 = x3;
                            y4 = y3;
                        }
                    } else {
                        x3 = x2;
                        y3 = y5;
                        x4 = x3;
                        y4 = y3;
                    }
                }
            }
        }

        List<double[]> ret = new ArrayList<double[]>();

        double p1[] = { x1, y1 };

        double p2[] = { x2, y2 };

        double p3[] = { x3, y3 };

        double p4[] = { x4, y4 };

        double p5[] = { x5, y5 };

        double p6[] = { x6, y6 };
        ret.add(p1);
        ret.add(p2);
        ret.add(p3);
        ret.add(p4);
        ret.add(p5);
        ret.add(p6);
        return ret;
    }

    /**
     * A dotted line is drawn.<br>
     * You can change the position of the Manhattan line.
     * @param drawLines Manhattan line
     * @param drawPane Canvas to draw dotted lines
     * @return dotted line
     */
    public static Map<Integer, DrawLine> reDesignManhattan(List<DrawLine> drawLines, DrawPane drawPane) {
        Map<Integer, DrawLine> newLines = new LinkedHashMap<Integer, DrawLine>();
        for (int i = 0; i < drawLines.size(); i++) {
            if (i == 1
                    && (drawLines.get(i - 1).getStartPoint().getX() != drawLines.get(i).getEndPoint().getX()
                            && drawLines.get(i - 1).getStartPoint().getY() != drawLines.get(i).getEndPoint().getY())
                    || i == 2 && !(drawLines.get(i).getStartPoint().getX() == drawLines.get(i).getEndPoint().getX()
                            && drawLines.get(i).getStartPoint().getY() == drawLines.get(i).getEndPoint().getY())
                    || i == 3 && (drawLines.get(i).getStartPoint().getX() != drawLines.get(i + 1).getEndPoint().getX()
                            && drawLines.get(i).getStartPoint().getY() != drawLines.get(i + 1).getEndPoint().getY())) {
                DrawLine newLine = new DrawLine();
                newLine.setTitle("●");
                newLine.setDrawPane(drawPane);
                newLine.setLineWidth(2);
                newLine.setLineColor(BLUE);
                newLine.setStartTop(drawLines.get(i).getStartTop());
                newLine.setStartLeft(drawLines.get(i).getStartLeft());
                newLine.setEndTop(drawLines.get(i).getEndTop());
                newLine.setEndLeft(drawLines.get(i).getEndLeft());
                newLine.setCanDrag(true);
                newLine.setShowTitleLabelBackground(true);
                newLine.setLinePattern(LinePattern.SHORTDOT);
                newLine.addDragMoveHandler(new DragMoveHandler() {

                    @Override
                    public void onDragMove(DragMove event) {
                        event.cancel();
                        if (newLine.getStartTop() == newLine.getEndTop()) {
                            newLine.moveBy(0, event.getY() - newLine.getStartTop());
                        }
                        if (newLine.getStartLeft() == newLine.getEndLeft()) {
                            newLine.moveBy(event.getX() - newLine.getStartLeft(), 0);
                        }
                    }
                });
                newLine.draw();
                newLines.put(i, newLine);

            }
        }
        return newLines;
    }

    /**
     * Gets the coordinate position of the newly created Manhattan line.<br>
     * It works by resizing the Manhattan line.
     * @param line Manhattan line before resizing
     * @param newLine Manhattan line after resizing
     * @param ind Manhattan line index numbers
     * @return The coordinate position of the newly created Manhattan line.
     */
    public static List<AbstractPoint> getNewManhattanPoint(AbstractLine line, DrawLine newLine, int ind) {
        List<AbstractPoint> oldP = line.getConnectionPoint();
        List<AbstractPoint> newP = new ArrayList<AbstractPoint>();
        oldP.forEach(p -> {
            newP.add(EcoreUtil.copy(p));
        });
        int startX = newLine.getStartPoint().getX();
        int startY = newLine.getStartPoint().getY();
        int endX = newLine.getEndPoint().getX();
        int endY = newLine.getEndPoint().getY();

        if ((newP.get(ind).getX() == newP.get(ind - 1).getX()) && (newP.get(ind).getY() == newP.get(ind - 1).getY())) {
            if ((newP.get(ind).getX() == newP.get(ind - 3).getX()) || (newP.get(ind).getY() == newP.get(ind - 3).getY())) {
                if ((newP.get(ind + 1).getX() != newP.get(ind - 3).getX()) && (newP.get(ind + 1).getY() != newP.get(ind - 3).getY())) {
                    newP.get(ind - 2).setX(startX);
                    newP.get(ind - 2).setY(startY);
                }
            }
            newP.get(ind - 1).setX(startX);
            newP.get(ind - 1).setY(startY);
        } else if ((newP.get(ind + 1).getX() == newP.get(ind + 2).getX()) && (newP.get(ind + 1).getY() == newP.get(ind + 2).getY())) {
            newP.get(ind + 2).setX(endX);
            newP.get(ind + 2).setY(endY);
            if ((newP.get(ind + 1).getX() == newP.get(ind + 4).getX()) || (newP.get(ind + 1).getY() == newP.get(ind + 4).getY())) {
                if ((newP.get(ind).getX() != newP.get(ind + 4).getX()) && (newP.get(ind).getY() != newP.get(ind + 4).getY())) {
                    newP.get(ind + 3).setX(endX);
                    newP.get(ind + 3).setY(endY);
                }
            }
        }
        newP.get(ind).setX(startX);
        newP.get(ind).setY(startY);
        newP.get(ind + 1).setX(endX);
        newP.get(ind + 1).setY(endY);

        return newP;
    }
}
