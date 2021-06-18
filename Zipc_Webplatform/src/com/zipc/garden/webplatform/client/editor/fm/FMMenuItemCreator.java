package com.zipc.garden.webplatform.client.editor.fm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.drawing.DrawRect;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemIfFunction;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.webplatform.client.editor.fm.FMProperty.ProcessType;
import com.zipc.garden.webplatform.client.editor.fm.layout.LayoutMode;

/**
 * This class manages the context menu used in the feature-model editor.
 */
public class FMMenuItemCreator {

    /**
     * Create the menu that is displayed when you right-click in DrawRect.
     * @param editor Main class of feature-model editor
     * @param drawRect Right-clicked DrawRect
     * @return Menu
     */
    public static Menu createDrawRectRightClickMenuItem(FMEditor editor, DrawRect drawRect) {
        // 右クリックイベントの作成
        Menu menu = new Menu();
        menu.setWidth(150);
        MenuItem addItem = new MenuItem("Add");
        addItem.setKeyTitle("Insert");
        addItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                FMNode node = editor.drawItems.get(drawRect.hashCode()).getFmNode();
                if (node.getRefuuid() != null) {
                    SC.warn("Can not add because the reference node is set.");
                    return;
                }
                editor.nodeManager.addNode(node.getChildren().size(), drawRect, editor);
            }
        });
        MenuItem renameItem = new MenuItem("Rename");
        renameItem.setKeyTitle("F2");
        renameItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.nodeManager.renameNode(drawRect, editor);
            }
        });
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setKeyTitle("Delete");
        deleteItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                List<FMNode> selecteds = editor.nodeManager.getSelectFMNodes();
                List<FMNode[]> nodeArrays = editor.nodeManager.getNodeChildren(selecteds);
                editor.onDelete(selecteds, nodeArrays);
            }
        });
        MenuItem moveUpNode = new MenuItem("Move to previous");
        moveUpNode.setKeyTitle(editor.isHorizontalLayout() ? "Ctrl + ↑" : "Ctrl + ←");
        moveUpNode.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                FMNode node = editor.drawItems.get(drawRect.hashCode()).getFmNode();
                editor.nodeManager.moveNodePosition(editor, node, -1);
                editor.refresh();
            }
        });
        MenuItem moveDownNode = new MenuItem("Move to next");
        moveDownNode.setKeyTitle(editor.isHorizontalLayout() ? "Ctrl + ↓" : "Ctrl + →");
        moveDownNode.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                FMNode node = editor.drawItems.get(drawRect.hashCode()).getFmNode();
                editor.nodeManager.moveNodePosition(editor, node, 1);
                editor.refresh();
            }
        });
        Menu edgeMenu = new Menu();
        edgeMenu.setWidth(150);
        FMNode node = editor.drawItems.get(drawRect.hashCode()).getFmNode();
        FMNode parentNode = editor.nodeManager.getParentNode(node);
        String childType = node.getChildType().getName();

        MenuItem andItem = new MenuItem("AND");
        andItem.setChecked("AND".equals(childType));
        andItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.nodeManager.changeEdgeOfSelectedNode(editor, ChildType.AND);
            }
        });
        MenuItem xorItem = new MenuItem("XOR");
        xorItem.setChecked("XOR".equals(childType));
        xorItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.nodeManager.changeEdgeOfSelectedNode(editor, ChildType.XOR);
            }
        });
        edgeMenu.setItems(andItem, xorItem);
        MenuItem edgeItem = new MenuItem("Edge");
        edgeItem.setSubmenu(edgeMenu);

        editor.referenceOpenItem.setKeyTitle("Ctrl + O");

        MenuItem refSelectItem = new MenuItem("Select");
        refSelectItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.nodeManager.selectReferenceNode(editor);
            }
        });
        MenuItem refUnselectItem = new MenuItem("Unselect");
        refUnselectItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.nodeManager.unselectReferenceNode(editor, node);
            }
        });

        Menu referenceMenu = new Menu();
        referenceMenu.setItems(editor.referenceOpenItem, refSelectItem, refUnselectItem);
        MenuItem referenceItem = new MenuItem("Reference Node");
        referenceItem.setSubmenu(referenceMenu);

        MenuItem propItem = new MenuItem("Edit property");
        propItem.addClickHandler(new FMProperty().getPropClickMenuHandler(editor, ProcessType.Node, node));

        if (node.getChildren().size() == 0)
            menu.setItems(addItem, renameItem, deleteItem, moveUpNode, moveDownNode, edgeItem, referenceItem, propItem, editor.childModelItem);
        else
            menu.setItems(addItem, renameItem, deleteItem, moveUpNode, moveDownNode, edgeItem, referenceItem, propItem);

        if (node.getChildType().equals(ChildType.XOR)) {

            MenuItem cardinalityEditItem = new MenuItem("edit");
            cardinalityEditItem.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(MenuItemClickEvent event) {
                    editor.nodeManager.openCardinalityWindow(editor, node);
                }
            });

            MenuItem cardinalityDeleteItem = new MenuItem("delete");
            cardinalityDeleteItem.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(MenuItemClickEvent event) {
                    editor.nodeManager.deleteCardinality(editor, node);
                }
            });

            Menu cardinalityMenu = new Menu();
            cardinalityMenu.setItems(cardinalityEditItem, cardinalityDeleteItem);
            MenuItem cardinalityItem = new MenuItem("Cardinality");
            cardinalityItem.setSubmenu(cardinalityMenu);

            menu.addItem(cardinalityItem);

        }
        Menu optMenu = new Menu();
        optMenu.setWidth(150);
        MenuItem onItem = new MenuItem("ON");
        onItem.setChecked(node.isOptional());
        onItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.nodeManager.changeOptionalOfSelectedNode(editor, true);
            }
        });
        MenuItem offItem = new MenuItem("OFF");
        offItem.setChecked(!node.isOptional());
        offItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.nodeManager.changeOptionalOfSelectedNode(editor, false);
            }
        });
        optMenu.setItems(onItem, offItem);
        MenuItem optItem = new MenuItem("Optional");
        optItem.setSubmenu(optMenu);
        menu.addItem(optItem);

        menu.addItem(editor.getNodeArrangeItem());

        return menu;
    }

    /**
     * Create the menu that is displayed when you right-click in Canvas.
     * @param editor Main class of feature-model editor
     * @param x Right clicked coordinate position(X coordinate)
     * @param y Right clicked coordinate position(Y coordinate)
     * @return Menu
     */
    public static Menu createCanvasRightClickMenuItem(FMEditor editor, int x, int y) {
        int snapGap = editor.getFMRoot().getGridSize();
        int clickX = x - editor.drawPane.getPageLeft();
        int clickY = y - editor.drawPane.getPageTop();
        int left = Math.round(clickX / snapGap) * snapGap;
        int top = Math.round(clickY / snapGap) * snapGap;

        Menu menu = new Menu();
        menu.setWidth(150);
        // 要素の追加
        MenuItem addItem = new MenuItem("Add");
        addItem.setKeyTitle("Insert");
        addItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                // NODEが存在する場合、選択チェック
                if (editor.root.getNode() != null) {
                    List<DrawRect> drawRects = editor.nodeManager.getSelectDrawRect();
                    if (drawRects.size() != 1) {
                        SC.warn("Please select one node.");
                        return;
                    }
                    FMNode node = editor.drawItems.get(drawRects.get(0).hashCode()).getFmNode();
                    if (node.getRefuuid() != null) {
                        SC.warn("Can not add because the reference node is set.");
                        return;
                    }
                    editor.nodeManager.addNode(node.getChildren().size(), drawRects.get(0), editor);
                } else {
                    editor.nodeManager.addNode(0, editor);
                }
            }
        });
        // 要素の削除
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                List<FMNode> selecteds = new ArrayList<>();
                for (Map.Entry<Integer, FMDrawNode> entry : editor.drawItems.entrySet()) {
                    DrawRect drawRect = entry.getValue().getDrawRect();
                    // 選択されている要素を取得
                    if (drawRect.getFillColor().equals("yellow")) {
                        FMNode node = editor.drawItems.get(drawRect.hashCode()).getFmNode();
                        selecteds.add(node);
                    }
                }
                // 未選択
                if (selecteds.size() == 0) {
                    SC.warn("Please select node.");
                    return;
                } else {
                    List<FMNode[]> nodeArrays = editor.nodeManager.getNodeChildren(selecteds);
                    editor.onDelete(selecteds, nodeArrays);
                }
            }
        });
        // Canvasの画像保存機能のメニュー
        MenuItem printItem = new MenuItem("Print Preview");
        printItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                Canvas.showPrintPreview(editor.drawPane);
            }
        });

        // Propertyのダイアログ表示のメニュー
        MenuItem propItem = new MenuItem("Edit property");
        propItem.addClickHandler(new FMProperty().getPropClickMenuHandler(editor, ProcessType.Canvas, null));

        MenuItem propDispItem = new MenuItem("Property display");
        propDispItem.setCheckIfCondition(new MenuItemIfFunction() {
            @Override
            public boolean execute(Canvas target, Menu menu, MenuItem item) {
                return editor.isPropertyDisplay();
            }
        });
        propDispItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.setPropertyDisplay(!editor.isPropertyDisplay());
                editor.refresh();
            }
        });

        MenuItem undoItem = new MenuItem("Undo");
        undoItem.setKeyTitle("Ctrl + Z");
        undoItem.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.getEditManager().undo();
                editor.refresh();
                editor.drawPane.refreshNow();
            }
        });
        MenuItem redoItem = new MenuItem("Redo");
        redoItem.setKeyTitle("Ctrl + Y");
        redoItem.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.getEditManager().redo();
                editor.refresh();
                editor.drawPane.refreshNow();
            }
        });

        editor.saveItem.setKeyTitle("Ctrl + S");

        menu.setItems(addItem, deleteItem, editor.saveItem, printItem, propItem, propDispItem, undoItem, redoItem);

        menu.addItem(createAutoLayoutModeMenuItem(editor));

        menu.addItem(editor.getEditorScroll().getGridItem());

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
     * Gets the context menu for automatic layout mode.
     * @param editor Main class of feature-model editor
     * @return Menu Item
     */
    private static MenuItem createAutoLayoutModeMenuItem(FMEditor editor) {
        Menu items = new Menu();
        List<LayoutMode> modes = editor.getLayoutModes();
        LayoutMode currentMode = editor.getCurrentLayoutMode();
        for (int i = 0; i < modes.size(); i++) {
            final int key = i;
            LayoutMode mode = modes.get(i);
            MenuItem item = new MenuItem(mode.getName());
            item.setCheckIfCondition(new MenuItemIfFunction() {
                @Override
                public boolean execute(Canvas target, Menu menu, MenuItem item) {
                    return currentMode == mode;
                }
            });
            item.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(MenuItemClickEvent event) {
                    editor.setCurrentLayoutMode(key);
                }
            });
            items.addItem(item);
        }
        MenuItem menuItem = new MenuItem("Layout");
        menuItem.setSubmenu(items);
        return menuItem;
    }
}
