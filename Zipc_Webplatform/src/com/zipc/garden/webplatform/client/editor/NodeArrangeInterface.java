package com.zipc.garden.webplatform.client.editor;

import java.util.Map;

import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.model.core.AbstractStyle;

/**
 * Interface that summarizes the methods for changing the style of the node
 */
public interface NodeArrangeInterface {

    /**
     * Get command history of editor
     * @return command history of the editor
     */
    public EditManager getEditManager();

    /**
     * Gets the MAP that represents the relationship between the EMF model class and node elements.
     * @return map
     */
    public Map<AbstractStyle, DrawItem> getArrangeStyleNode();

    /**
     * Gets the MAP that represents the relationship between the EMF model class and node elements. Only the selected ones will
     * be retrieved.
     * @return map
     */
    public Map<AbstractStyle, DrawItem> getSelectedArrangeStyleNode();

    /**
     * Get a menu to change the style of a node
     * @return menu item
     */
    public MenuItem getNodeArrangeItem();

    /**
     * Sets the menu for changing the style of the node.
     * @param menuItem
     */
    public void setNodeArrangeItem(MenuItem menuItem);

    /**
     * This method is executed when you want to reflect the created command on the canvas.
     */
    public void doRefresh();

    /**
     * Set the class that manages the method to be issued after refresh.
     * @param listener
     */
    public void setRefreshEndListener(RefreshEndListener listener);
}
