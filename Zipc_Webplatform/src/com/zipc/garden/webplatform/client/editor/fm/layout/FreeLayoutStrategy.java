package com.zipc.garden.webplatform.client.editor.fm.layout;

import com.zipc.garden.model.fm.FMNode;

/**
 * Used in manual layout.<br>
 * Manages the processing when manual layout is selected.
 */
public class FreeLayoutStrategy implements LayoutStrategy {

    /**
     * {@inheritDoc} <br>
     * Since it is a manual process, nothing happens when it is executed.
     */
    @Override
    public void execute(FMNode rootNode) {
    }
}
