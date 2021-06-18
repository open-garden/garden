package com.zipc.garden.webplatform.client.editor.fm.layout;

import java.util.Comparator;

import com.smartgwt.client.widgets.drawing.DrawRect;
import com.zipc.garden.model.fm.FMNode;

/**
 * Interface that summarizes the process of calculating the coordinate position of each element associated with a DrawRect
 */
public interface ConnectionStrategy {

    /**
     * Get connection point to parent node
     * @param rect parent DrawRect
     * @return Connection point (X,Y coordinates)
     */
    int[] getConnectionPointAsParent(DrawRect rect);

    /**
     * Get connection point to parent node
     * @param node parent FMNode
     * @return Connection point (X,Y coordinates)
     */
    int[] getConnectionPointAsParent(FMNode node);

    /**
     * Get connection point to child node
     * @param rect child DrawRect
     * @return Connection point (X,Y coordinates)
     */
    int[] getConnectionPointAsChild(DrawRect rect);

    /**
     * Get connection point to child node
     * @param node child FMNode
     * @return Connection point (X,Y coordinates)
     */
    int[] getConnectionPointAsChild(FMNode node);

    /**
     * Get the coordinate position of Oval based on the connection point of the child node.
     * @param rect child DrawRect
     * @return Oval coordinates (X,Y coordinates)
     */
    int[] getOvalPosition(DrawRect rect);

    /**
     * Get the coordinates of the red triangle on the upper right of the node
     * @param rect Node for which the property has been created
     * @return Red triangle coordinates displayed at the upper right of the node
     */
    int[] getTrianglePosition(DrawRect rect);

    /**
     * Gets the display position of the multiplicity label.
     * @param rect DrawRect with multiplicity set
     * @return X,Y coordinates
     */
    int[] getDefaultCardinalityPosition(DrawRect rect);

    /**
     * Gets the display position of the multiplicity label.
     * @param node FMNode with multiplicity set
     * @return X,Y coordinates
     */
    int[] getDefaultCardinalityPosition(FMNode node);

    /**
     * Gets the layout type.
     * @return Layout type
     */
    String getDirection();

    /**
     * Gets the sort condition of DrawRect.
     * @return sort condition
     */
    Comparator<DrawRect> getNodeChildrenComparator();
}
