package com.zipc.garden.webplatform.client.editor.cb;

import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * This class manages the context menu used in the SCSS editor.
 */
public class CBMenuItemCreator {

    /**
     * Create the menu that is displayed when you right-click in ListGrid.
     * @param editor Main class of scenarioset-setting editor
     * @param x Right clicked coordinate position(X coordinate)
     * @param y Right clicked coordinate position(Y coordinate)
     * @return Menu
     */
    protected static Menu createRightClickMenuItem(CBEditor editor, int x, int y) {
        Menu menu = new Menu();
        menu.setWidth(150);

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setKeyTitle("Del");
        deleteItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.onDelete();
            }
        });

        editor.getSaveItem().setKeyTitle("Ctrl + S");

        MenuItem undoItem = new MenuItem("Undo");
        undoItem.setKeyTitle("Ctrl + Z");
        undoItem.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.getEditManager().undo();
                editor.refresh();
            }
        });

        MenuItem redoItem = new MenuItem("Redo");
        redoItem.setKeyTitle("Ctrl + Y");
        redoItem.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.getEditManager().redo();
                editor.refresh();
            }
        });

        menu.setItems(deleteItem, editor.getSaveItem(), undoItem, redoItem);

        return menu;
    }
}
