package com.zipc.garden.webplatform.client.editor.fm.layout;

import com.zipc.garden.model.fm.FMNode;

/**
 * Interface that manages processing when layout mode is selected
 */
public interface LayoutStrategy {

    /**
     * Places the nodes based on the layout mode you select.
     * @param rootNode Top layer node (root node)
     */
    void execute(FMNode rootNode);
}
