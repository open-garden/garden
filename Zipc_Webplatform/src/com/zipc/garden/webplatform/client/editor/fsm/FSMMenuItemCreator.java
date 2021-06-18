package com.zipc.garden.webplatform.client.editor.fsm;

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
import com.zipc.garden.model.core.LineType;
import com.zipc.garden.model.fsm.FSMDPseudoStateType;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMDTransition;
import com.zipc.garden.webplatform.client.editor.EditorDrawLine;

/**
 * This class manages the context menu used in the behavior-model editor.
 */
public class FSMMenuItemCreator {

    /**
     * Create the menu that is displayed when you right-click in DrawItem.
     * @param editor Main class of behavior-model editor
     * @param drawItem Right-clicked DrawItem
     * @return Menu
     */
    public static Menu createDrawItemRightClickMenuItem(FSMEditor editor, DrawItem drawItem) {
        FSMDState state = editor.stateManager.getKey(editor.getDrawItems(), drawItem).get();

        // 右クリックイベントの作成
        Menu menu = new Menu();
        menu.setWidth(150);
        Menu addLineMenu = new Menu();
        addLineMenu.setWidth(150);

        MenuItem addLineItem = new MenuItem("Add Line");
        addLineItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.transManager.addLineDrawItems[0] = drawItem;
                if (editor.isManhattanMode()) {
                    editor.transManager.addLineType = LineType.MANHATTAN;
                } else {
                    editor.transManager.addLineType = LineType.SIMPLE;
                }
            }
        });
        MenuItem renameItem = new MenuItem("Rename");
        renameItem.setKeyTitle("F2");
        renameItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.onRename(editor, drawItem);
            }
        });
        renameItem.setEnabled(FSMDPseudoStateType.NONE.equals(state.getType()));
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setKeyTitle("Delete");
        deleteItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.deleteItem(editor);
            }
        });

        menu.setItems(addLineItem, renameItem, deleteItem);
        return menu;
    }

    /**
     * Create the menu that is displayed when you right-click in Canvas.
     * @param editor Main class of behavior-model editor
     * @param x Right clicked coordinate position(X coordinate)
     * @param y Right clicked coordinate position(Y coordinate)
     * @return Menu
     */
    public static Menu createCanvasRightClickMenuItem(FSMEditor editor, int x, int y) {
        int snapGap = editor.getFSMDStateMachine().getGridSize();
        int clickX = x - editor.getDrawPane().getPageLeft();
        int clickY = y - editor.getDrawPane().getPageTop();
        int left = Math.round(clickX / snapGap) * snapGap;
        int top = Math.round(clickY / snapGap) * snapGap;

        Menu menu = new Menu();
        menu.setWidth(150);
        MenuItem addItem = new MenuItem("Add");
        Menu addMenu = new Menu();
        MenuItem addNoneItem = new MenuItem("State");
        addNoneItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.onAdd(editor, left, top, FSMDPseudoStateType.NONE);
            }
        });
        MenuItem addInitialItem = new MenuItem("Initial");
        addInitialItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.onAdd(editor, left, top, FSMDPseudoStateType.INITIAL);
            }
        });
        MenuItem addChoiceItem = new MenuItem("Choice");
        addChoiceItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.onAdd(editor, left, top, FSMDPseudoStateType.CHOICE);
            }
        });
        MenuItem addFinalItem = new MenuItem("Final");
        addFinalItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.onAdd(editor, left, top, FSMDPseudoStateType.FINAL);
            }
        });
        MenuItem addShallowItem = new MenuItem("Shallow History");
        addShallowItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.onAdd(editor, left, top, FSMDPseudoStateType.SHALLOW_HISTORY);
            }
        });
        MenuItem addDeepItem = new MenuItem("Deep History");
        addDeepItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.stateManager.onAdd(editor, left, top, FSMDPseudoStateType.DEEP_HISTORY);
            }
        });

        MenuItemSeparator sep1 = new MenuItemSeparator();

        MenuItem addMemoItem = new MenuItem("Memo");
        addMemoItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.getEditorMemo().onAdd("", left, top);
                editor.getEditorMemo().getEditItem().fireEvent(new MenuItemClickEvent(event.getItem().getJsObj()));
            }
        });

        addMenu.setItems(addNoneItem, addInitialItem, addChoiceItem, addFinalItem, addShallowItem, addDeepItem, sep1, addMemoItem);
        addItem.setSubmenu(addMenu);

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                Map<FSMDState, DrawItem> drawItems = editor.stateManager.getSelectedDrawItems(editor);
                Map<FSMDTransition, EditorDrawLine> drawLines = editor.transManager.getSelectedDrawLines(editor);
                if (drawItems.size() == 0 && drawLines.size() == 0) {
                    SC.warn("Please select node or line.");
                    return;
                } else {
                    editor.stateManager.onDelete(editor, drawItems, drawLines);
                }
            }
        });

        MenuItem varItem = new MenuItem("Edit Variable");
        varItem.addClickHandler(new FSMVariableModal().getPropClickMenuHandler(editor));

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

        MenuItemSeparator sep2 = new MenuItemSeparator();

        editor.saveItem.setKeyTitle("Ctrl + S");

        menu.setItems(addItem, deleteItem, varItem, editor.saveItem, undoItem, redoItem, sep2);

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

        MenuItem printItem = new MenuItem("Print Preview");
        printItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                Canvas.showPrintPreview(editor.getDrawPane());
            }
        });
        menu.addItem(printItem);

        return menu;
    }

    /**
     * Create the menu that is displayed when you right-click in DrawLine.
     * @param editor Main class of behavior-model editor
     * @param drawLine Right-clicked DrawLine
     * @return Menu
     */
    public static Menu createDrawLineRightClickMenuItem(FSMEditor editor, DrawLine drawLine) {
        Menu menu = new Menu();
        menu.setWidth(150);

        MenuItem routItem = new MenuItem("Line routing");
        Menu routMenu = new Menu();
        MenuItem manhattanItem = new MenuItem("Manhattan routing");
        manhattanItem.setCheckIfCondition(new MenuItemIfFunction() {
            @Override
            public boolean execute(Canvas target, Menu menu, MenuItem item) {
                return editor.transManager.checkSelectedLineRouting(editor, LineType.MANHATTAN);
            }
        });

        manhattanItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.transManager.redrawChangedTransitionType(editor, LineType.MANHATTAN);
            }
        });
        MenuItem linearItem = new MenuItem("Linear routing");
        linearItem.setCheckIfCondition(new MenuItemIfFunction() {
            @Override
            public boolean execute(Canvas target, Menu menu, MenuItem item) {
                return editor.transManager.checkSelectedLineRouting(editor, LineType.SIMPLE);
            }
        });
        linearItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.transManager.redrawChangedTransitionType(editor, LineType.SIMPLE);
            }
        });

        routMenu.setItems(manhattanItem, linearItem);
        routItem.setSubmenu(routMenu);

        MenuItem editItem = new MenuItem("Edit Transition");
        editItem.setKeyTitle("F2");
        editItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.transManager.onEditTransition(editor, drawLine);
            }
        });

        MenuItem clearItem = new MenuItem("Clear Transition");
        clearItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.transManager.onClearTransition(editor, drawLine);
            }
        });

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                Map<FSMDTransition, EditorDrawLine> drawLines = editor.transManager.getSelectedDrawLines(editor);
                if (drawLines.size() == 0) {
                    SC.warn("Please select line.");
                    return;
                } else {
                    editor.stateManager.onDelete(editor, new HashMap<>(), drawLines);
                }
            }
        });

        menu.setItems(routItem, editItem, clearItem, deleteItem);
        return menu;
    }
}
