package com.zipc.garden.webplatform.client.editor.fm.layout;

import com.zipc.garden.model.fm.FMNode;

/**
 * Used in horizontal auto layout.<br>
 * Get the coordinates for aligning nodes from left to right
 */
public class LeftToRightStrategy extends TreeLayoutStrategy {

    /**
     * constructor
     * @param originTop The drawing start position (Y coordinate) of the node.
     * @param originLeft The drawing start position (X coordinate) of the node.
     * @param gutterWidth Width between nodes during AutoLayout.
     * @param gutterHeight Height between nodes during AutoLayout.
     */
    public LeftToRightStrategy(int originTop, int originLeft, int gutterWidth, int gutterHeight) {
        super(originTop, originLeft, gutterWidth, gutterHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setVerticalOffset(FMNode node, int offset) {
        node.setLeft(offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setHorizontalOffset(FMNode node, int offset) {
        node.setTop(offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getVerticalSizeIncludingGutter(FMNode node) {
        return node.getWidth() + this.gutterWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getHorizontalSizeIncludingGutter(FMNode node) {
        return node.getHeight() + this.gutterHeight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getVerticalOrigin() {
        return this.originLeft;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getHorizontalOrigin() {
        return this.originTop;
    }
}
