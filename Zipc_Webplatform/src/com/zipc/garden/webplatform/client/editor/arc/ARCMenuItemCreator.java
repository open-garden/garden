package com.zipc.garden.webplatform.client.editor.arc;

import java.util.HashMap;
import java.util.Map;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawLine;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemIfFunction;
import com.smartgwt.client.widgets.menu.MenuItemSeparator;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.model.arc.ARCLine;
import com.zipc.garden.model.arc.ARCState;
import com.zipc.garden.model.core.LineType;
import com.zipc.garden.webplatform.client.editor.EditorDrawLine;

/**
 * This class manages the context menu used in the ARC editor.
 */
public class ARCMenuItemCreator {

    /**
     * Create the menu that is displayed when you right-click in DrawItem.
     * @param editor Main class of architecture editor
     * @param drawItem Right-clicked DrawItem
     * @return Menu
     */
    public static Menu createDrawItemRightClickMenuItem(ARCEditor editor, DrawItem drawItem) {

        Menu menu = new Menu();
        menu.setWidth(150);
        MenuItem addLineItem = new MenuItem("Add Line");
        addLineItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.lineManager.addLineDrawItems[0] = drawItem;
                if (editor.isManhattanMode()) {
                    editor.lineManager.addLineType = LineType.MANHATTAN;
                } else {
                    editor.lineManager.addLineType = LineType.SIMPLE;
                }
            }
        });
        MenuItem renameItem = new MenuItem("Rename");
        renameItem.setKeyTitle("F2");
        renameItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.onRename(drawItem);
            }
        });
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setKeyTitle("Delete");
        deleteItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.deleteStateAndLine();
            }
        });

        MenuItem copyItem = new MenuItem("Copy");
        MenuItemIfFunction canCopyCond = new MenuItemIfFunction() {
            @Override
            public boolean execute(Canvas target, Menu menu, MenuItem item) {
                return editor.stateManager.isSelectedStates();
            }
        };
        copyItem.setEnableIfCondition(canCopyCond);
        copyItem.setKeyTitle("Ctrl + C");
        copyItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.copyStateMachine();
            }
        });

        MenuItem pasteItem = new MenuItem("Paste");
        MenuItemIfFunction canPasteCond = new MenuItemIfFunction() {
            @Override
            public boolean execute(Canvas target, Menu menu, MenuItem item) {
                return editor.stateManager.existsCopyState();
            }
        };
        pasteItem.setEnableIfCondition(canPasteCond);
        pasteItem.setKeyTitle("Ctrl + V");
        pasteItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.pasteStateMachine(drawItem);
            }
        });

        menu.setItems(copyItem, pasteItem, new MenuItemSeparator(), addLineItem, editor.getOpenFileItem(), renameItem, deleteItem);
        return menu;
    }

    /**
     * Create the menu that is displayed when you right-click in Canvas.
     * @param editor Main class of architecture editor
     * @param x Right clicked coordinate position(X coordinate)
     * @param y Right clicked coordinate position(Y coordinate)
     * @return Menu
     */
    public static Menu createCanvasRightClickMenuItem(ARCEditor editor, int left, int top) {
        Menu menu = new Menu();
        menu.setWidth(150);

        MenuItem addStateItem = new MenuItem("Add State Machine");
        addStateItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.addStateMachine(left, top);
            }
        });
        MenuItem prioritySettingItem = new MenuItem("Priority Setting");
        prioritySettingItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.prioritySetting();
            }
        });
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                Map<ARCState, DrawItem> drawItems = editor.stateManager.getSelectedDrawItems();
                Map<ARCLine, EditorDrawLine> drawLines = editor.lineManager.getSelectedDrawLines();
                if (drawItems.size() == 0 && drawLines.size() == 0) {
                    SC.warn("Please select node or line.");
                    return;
                } else {
                    editor.stateManager.onDelete(drawItems, drawLines);
                }
            }
        });
        MenuItem printItem = new MenuItem("Print Preview");
        printItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                Canvas.showPrintPreview(editor.getDrawPane());
            }
        });

        MenuItem undoItem = new MenuItem("Undo");
        undoItem.setKeyTitle("Ctrl + Z");
        undoItem.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.manager.undo();
                editor.refresh();
            }
        });
        MenuItem redoItem = new MenuItem("Redo");
        redoItem.setKeyTitle("Ctrl + Y");
        redoItem.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.manager.redo();
                editor.refresh();
            }
        });

        editor.getSaveItem().setKeyTitle("Ctrl + S");

        MenuItem copyItem = new MenuItem("Copy");
        MenuItemIfFunction canCopyCond = new MenuItemIfFunction() {
            @Override
            public boolean execute(Canvas target, Menu menu, MenuItem item) {
                return editor.stateManager.isSelectedStates();
            }
        };
        copyItem.setEnableIfCondition(canCopyCond);
        copyItem.setKeyTitle("Ctrl + C");
        copyItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.copyStateMachine();
            }
        });

        MenuItem pasteItem = new MenuItem("Paste");
        MenuItemIfFunction canPasteCond = new MenuItemIfFunction() {
            @Override
            public boolean execute(Canvas target, Menu menu, MenuItem item) {
                return editor.stateManager.existsCopyState();
            }
        };
        pasteItem.setEnableIfCondition(canPasteCond);
        pasteItem.setKeyTitle("Ctrl + V");
        pasteItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.pasteStateMachine(top, left);
            }
        });

        menu.setItems(copyItem, pasteItem, new MenuItemSeparator(), editor.getNewStateItem(), addStateItem, prioritySettingItem, deleteItem, editor.getSaveItem(), printItem,
                undoItem, redoItem);

        menu.addItem(editor.getEditorScroll().getGridItem());

        MenuItem routItem = new MenuItem("Line routing");
        Menu routMenu = new Menu();
        MenuItem manhattanItem = new MenuItem("Manhattan routing");
        manhattanItem.setCheckIfCondition(new MenuItemIfFunction() {
            @Override
            public boolean execute(Canvas target, Menu menu, MenuItem item) {
                return editor.isManhattanMode();
            }
        });
        manhattanItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                if (!editor.isManhattanMode()) {
                    editor.switchManhattanMode();
                }
            }
        });

        MenuItem linearItem = new MenuItem("Linear routing");
        linearItem.setCheckIfCondition(new MenuItemIfFunction() {
            @Override
            public boolean execute(Canvas target, Menu menu, MenuItem item) {
                return !editor.isManhattanMode();
            }
        });
        linearItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                if (editor.isManhattanMode()) {
                    editor.switchManhattanMode();
                }
            }
        });

        routMenu.setItems(manhattanItem, linearItem);
        routItem.setSubmenu(routMenu);
        menu.addItem(routItem);

        menu.addItem(editor.getEditorScroll().getPanelSizeItem());

        MenuItem addMemoItem = new MenuItem("Add Memo");
        addMemoItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.getEditorMemo().onAdd("", left, top);
                editor.getEditorMemo().getEditItem().fireEvent(new MenuItemClickEvent(event.getItem().getJsObj()));
            }
        });
        menu.addItem(addMemoItem);

        return menu;
    }

    /**
     * Create the menu that is displayed when you right-click in DrawLine.
     * @param editor Main class of architecture editor
     * @param drawLine Right-clicked DrawLine
     * @return Menu
     */
    public static Menu createDrawLineRightClickMenuItem(ARCEditor editor, DrawLine drawLine) {
        Menu menu = new Menu();
        menu.setWidth(150);

        MenuItem routItem = new MenuItem("Line routing");
        Menu routMenu = new Menu();
        MenuItem manhattanItem = new MenuItem("Manhattan routing");
        manhattanItem.setCheckIfCondition(new MenuItemIfFunction() {
            @Override
            public boolean execute(Canvas target, Menu menu, MenuItem item) {
                return editor.lineManager.checkSelectedLineRouting(LineType.MANHATTAN);
            }
        });

        manhattanItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.lineManager.redrawChangedTransitionType(LineType.MANHATTAN);
            }
        });
        MenuItem linearItem = new MenuItem("Linear routing");
        linearItem.setCheckIfCondition(new MenuItemIfFunction() {
            @Override
            public boolean execute(Canvas target, Menu menu, MenuItem item) {
                return editor.lineManager.checkSelectedLineRouting(LineType.SIMPLE);
            }
        });
        linearItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.lineManager.redrawChangedTransitionType(LineType.SIMPLE);
            }
        });

        routMenu.setItems(manhattanItem, linearItem);
        routItem.setSubmenu(routMenu);

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                Map<ARCLine, EditorDrawLine> drawLines = editor.lineManager.getSelectedDrawLines();
                if (drawLines.size() == 0) {
                    SC.warn("Please select line.");
                    return;
                } else {
                    editor.stateManager.onDelete(new HashMap<>(), drawLines);
                }
            }
        });

        menu.setItems(routItem, deleteItem);
        return menu;
    }
}
