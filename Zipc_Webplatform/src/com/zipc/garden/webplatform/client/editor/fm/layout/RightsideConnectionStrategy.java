package com.zipc.garden.webplatform.client.editor.fm.layout;

import java.util.Comparator;

import com.smartgwt.client.widgets.drawing.DrawRect;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.webplatform.client.editor.fm.FMEditorConstants;

/**
 * Class for calculating the drawing position of each element related to the DrawRect when horizontal is selected in layout mode
 */
public class RightsideConnectionStrategy implements ConnectionStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getConnectionPointAsParent(DrawRect rect) {
        int[] coord = { rect.getLeft() + rect.getWidth(), rect.getTop() + (rect.getHeight() / 2) };
        return coord;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getConnectionPointAsParent(FMNode node) {
        int[] coord = { node.getLeft() + node.getWidth(), node.getTop() + (node.getHeight() / 2) };
        return coord;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getConnectionPointAsChild(DrawRect rect) {
        int[] coord = { rect.getLeft(), rect.getTop() + (rect.getHeight() / 2) };
        return coord;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getConnectionPointAsChild(FMNode node) {
        int[] coord = { node.getLeft(), node.getTop() + (node.getHeight() / 2) };
        return coord;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getOvalPosition(DrawRect rect) {
        int[] connectionPoint = getConnectionPointAsChild(rect);
        int[] coord = { connectionPoint[0] - FMEditorConstants.OVAL_SIZE, connectionPoint[1] - FMEditorConstants.OVAL_SIZE };
        return coord;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getTrianglePosition(DrawRect rect) {
        int[] coord = { rect.getLeft() + rect.getWidth() - FMEditorConstants.TRIANGLE_SIZE, rect.getTop() };
        return coord;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getDefaultCardinalityPosition(DrawRect rect) {
        int[] coord = { rect.getLeft() + rect.getWidth() + FMEditorConstants.LeftToRightStrategy.CARDINALITY_OFFSET_X,
                rect.getTop() + (rect.getHeight() / 2) + FMEditorConstants.LeftToRightStrategy.CARDINALITY_OFFSET_Y };
        return coord;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] getDefaultCardinalityPosition(FMNode node) {
        int[] coord = { node.getLeft() + node.getWidth() + FMEditorConstants.LeftToRightStrategy.CARDINALITY_OFFSET_X,
                node.getTop() + (node.getHeight() / 2) + FMEditorConstants.LeftToRightStrategy.CARDINALITY_OFFSET_Y };
        return coord;
    }

    /**
     * {@inheritDoc}
     * @return "horizontal"
     */
    @Override
    public String getDirection() {
        return "horizontal";
    }

    /**
     * {@inheritDoc} <br>
     * It is sorted in ascending order of the Top value of DrawRect and ascending order of hashCode.
     */
    @Override
    public Comparator<DrawRect> getNodeChildrenComparator() {
        return Comparator.comparing(DrawRect::getTop).thenComparing(DrawRect::hashCode);
    }
}
