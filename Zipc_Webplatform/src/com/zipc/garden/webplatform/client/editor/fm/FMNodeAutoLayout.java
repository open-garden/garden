package com.zipc.garden.webplatform.client.editor.fm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zipc.garden.webplatform.client.editor.fm.layout.BottomsideConnectionStrategy;
import com.zipc.garden.webplatform.client.editor.fm.layout.ConnectionStrategy;
import com.zipc.garden.webplatform.client.editor.fm.layout.FreeLayoutStrategy;
import com.zipc.garden.webplatform.client.editor.fm.layout.LayoutMode;
import com.zipc.garden.webplatform.client.editor.fm.layout.LayoutStrategy;
import com.zipc.garden.webplatform.client.editor.fm.layout.LeftToRightStrategy;
import com.zipc.garden.webplatform.client.editor.fm.layout.RightsideConnectionStrategy;
import com.zipc.garden.webplatform.client.editor.fm.layout.TopToBottomStrategy;

/**
 * Class that manages the type of automatic layout mode
 */
public class FMNodeAutoLayout {

    /** Manage layout mode as a list. */
    private final List<LayoutMode> modes;
    {
        modes = new ArrayList<>();
        LayoutStrategy free = new FreeLayoutStrategy();
        ConnectionStrategy rightside = new RightsideConnectionStrategy();
        ConnectionStrategy bottomside = new BottomsideConnectionStrategy();
        modes.add(new LayoutMode("Auto Horizontal", new LeftToRightStrategy(FMEditorConstants.LeftToRightStrategy.PADDING_TOP, FMEditorConstants.LeftToRightStrategy.PADDING_LEFT,
                FMEditorConstants.LeftToRightStrategy.GUTTER_WIDTH, FMEditorConstants.LeftToRightStrategy.GUTTER_HEIGHT), rightside));
        modes.add(new LayoutMode("Auto Vertical", new TopToBottomStrategy(FMEditorConstants.TopToBottomStrategy.PADDING_TOP, FMEditorConstants.TopToBottomStrategy.PADDING_LEFT,
                FMEditorConstants.TopToBottomStrategy.GUTTER_WIDTH, FMEditorConstants.TopToBottomStrategy.GUTTER_HEIGHT), bottomside));
        modes.add(new LayoutMode("Manual Horizontal", free, rightside));
        modes.add(new LayoutMode("Manual Vertical", free, bottomside));
    }

    /**
     * Gets the layout mode list.
     * @return layout mode list.
     */
    public List<LayoutMode> getModes() {
        return Collections.unmodifiableList(this.modes);
    }

    /**
     * Gets the layout mode according to the argument.
     * @param key The specified position in the layout mode list
     * @return layout mode
     */
    public LayoutMode getMode(int key) {
        int index = (key >= 0 && key < this.modes.size()) ? key : 0;
        return this.modes.get(index);
    }
}
