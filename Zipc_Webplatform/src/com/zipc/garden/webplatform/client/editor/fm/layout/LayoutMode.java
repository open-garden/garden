package com.zipc.garden.webplatform.client.editor.fm.layout;

import java.util.Comparator;

import com.smartgwt.client.widgets.drawing.DrawRect;
import com.zipc.garden.model.fm.FMNode;

/**
 * Class that manages the process when layout mode is selected
 */
public class LayoutMode {

    /** Layout mode name */
    private final String name;

    /** The process when the layout mode is selected is managed */
    private final LayoutStrategy layout;

    /** Class that calculates the coordinate position of each element associated with DrawRect */
    private final ConnectionStrategy connection;

    /**
     * constructor
     * @param name {@link LayoutMode#name}
     * @param layout {@link LayoutMode#layout}
     * @param connection {@link LayoutMode#connection}
     */
    public LayoutMode(String name, LayoutStrategy layout, ConnectionStrategy connection) {
        this.name = name;
        this.layout = layout;
        this.connection = connection;
    }

    /**
     * {@link LayoutStrategy#execute(FMNode)}
     */
    public void execute(FMNode rootNode) {
        this.layout.execute(rootNode);
    }

    /**
     * Gets the layout mode name.
     * @return Layout mode name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checks if the layout mode is horizontal.
     * @return True if horizontal
     */
    public boolean isHorizontal() {
        return "horizontal".equals(this.connection.getDirection());
    }

    /**
     * {@link ConnectionStrategy#getConnectionPointAsParent(DrawRect)}
     */
    public int[] getConnectionPointAsParent(DrawRect rect) {
        return this.connection.getConnectionPointAsParent(rect);
    }

    /**
     * {@link ConnectionStrategy#getConnectionPointAsParent(FMNode)}
     */
    public int[] getConnectionPointAsParent(FMNode node) {
        return this.connection.getConnectionPointAsParent(node);
    }

    /**
     * {@link ConnectionStrategy#getConnectionPointAsChild(DrawRect)}
     */
    public int[] getConnectionPointAsChild(DrawRect rect) {
        return this.connection.getConnectionPointAsChild(rect);
    }

    /**
     * {@link ConnectionStrategy#getConnectionPointAsChild(FMNode)}
     */
    public int[] getConnectionPointAsChild(FMNode node) {
        return this.connection.getConnectionPointAsChild(node);
    }

    /**
     * {@link ConnectionStrategy#getOvalPosition(DrawRect)}
     */
    public int[] getOvalPosition(DrawRect rect) {
        return this.connection.getOvalPosition(rect);
    }

    /**
     * {@link ConnectionStrategy#getTrianglePosition(DrawRect)}
     */
    public int[] getTrianglePosition(DrawRect rect) {
        return this.connection.getTrianglePosition(rect);
    }

    /**
     * {@link ConnectionStrategy#getDefaultCardinalityPosition(DrawRect)}
     */
    public int[] getDefaultCardinalityPosition(DrawRect rect) {
        return this.connection.getDefaultCardinalityPosition(rect);
    }

    /**
     * {@link ConnectionStrategy#getDefaultCardinalityPosition(FMNode)}
     */
    public int[] getDefaultCardinalityPosition(FMNode node) {
        return this.connection.getDefaultCardinalityPosition(node);
    }

    /**
     * {@link ConnectionStrategy#getNodeChildrenComparator()}
     */
    public Comparator<DrawRect> getNodeChildrenComparator() {
        return this.connection.getNodeChildrenComparator();
    }
}
