package com.zipc.garden.webplatform.client.editor.fm;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.drawing.DrawRect;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.webplatform.client.editor.fm.FMProperty.ProcessType;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * Class which manages shortcut key events of feature-model editor.
 */
public class FMEditorKeyEventHolder {

    /**
     * Event when the keyboard is operated. <br>
     * Occurs when the canvas has focus.
     * @param editor Main class of feature-model editor
     * @return Key press handler
     */
    protected KeyPressHandler createShortcutKeyEvent(FMEditor editor) {
        return new KeyPressHandler() {

            @Override
            public void onKeyPress(KeyPressEvent event) {
                // Insert Key Press
                if (KeyNames.INSERT.equals(event.getKeyName())) {
                    List<DrawRect> drawRects = editor.nodeManager.getSelectDrawRect();
                    if (drawRects.size() == 1) {
                        DrawRect drawRect = drawRects.get(0);
                        FMNode node = editor.drawItems.get(drawRect.hashCode()).getFmNode();
                        if (node.getRefuuid() != null) {
                            SC.warn("Can not add because the reference node is set.");
                            return;
                        }
                        editor.nodeManager.addNode(node.getChildren().size(), drawRect, editor);
                    } else {
                        if (editor.drawItems.size() == 0) {
                            editor.nodeManager.addNode(0, editor);
                        }
                    }
                }
                // Esc Key Press
                else if (KeyNames.ESC.equals(event.getKeyName())) {
                    editor.nodeManager.selectRootNode(editor);
                }
                // F2 Key Press
                else if ("f2".equals(event.getKeyName())) {
                    List<DrawRect> drawRects = editor.nodeManager.getSelectDrawRect();
                    if (drawRects.size() == 1) {
                        editor.nodeManager.renameNode(drawRects.get(0), editor);
                    }
                }
                // CTRL Key + ↑ Key Press
                else if (event.isCtrlKeyDown() && (KeyNames.ARROW_UP.equals(event.getKeyName()) || KeyNames.ARROW_LEFT.equals(event.getKeyName()))) {
                    List<DrawRect> drawRects = editor.nodeManager.getSelectDrawRect();
                    if (drawRects.size() == 1) {
                        DrawRect drawRect = drawRects.get(0);
                        FMNode node = editor.drawItems.get(drawRect.hashCode()).getFmNode();
                        editor.nodeManager.moveNodePosition(editor, node, -1);
                        editor.refresh();
                    }
                }
                // CTRL Key + ↓ Key Press
                else if (event.isCtrlKeyDown() && (KeyNames.ARROW_DOWN.equals(event.getKeyName()) || KeyNames.ARROW_RIGHT.equals(event.getKeyName()))) {
                    List<DrawRect> drawRects = editor.nodeManager.getSelectDrawRect();
                    if (drawRects.size() == 1) {
                        DrawRect drawRect = drawRects.get(0);
                        FMNode node = editor.drawItems.get(drawRect.hashCode()).getFmNode();
                        editor.nodeManager.moveNodePosition(editor, node, 1);
                        editor.refresh();
                    }
                }
                // ↑ Key Press
                else if (KeyNames.ARROW_UP.equals(event.getKeyName())) {
                    if (editor.getCurrentLayoutMode().isHorizontal()) {
                        FMEditorKeyEventHolder.this.selectSiblingNode(editor, -1);
                    } else {
                        FMEditorKeyEventHolder.this.selectParentNode(editor);
                    }
                }
                // ↓ Key Press
                else if (KeyNames.ARROW_DOWN.equals(event.getKeyName())) {
                    if (editor.getCurrentLayoutMode().isHorizontal()) {
                        FMEditorKeyEventHolder.this.selectSiblingNode(editor, +1);
                    } else {
                        FMEditorKeyEventHolder.this.selectChildNode(editor);
                    }
                }
                // ← Key Press
                else if (KeyNames.ARROW_LEFT.equals(event.getKeyName())) {
                    if (editor.getCurrentLayoutMode().isHorizontal()) {
                        FMEditorKeyEventHolder.this.selectParentNode(editor);
                    } else {
                        FMEditorKeyEventHolder.this.selectSiblingNode(editor, -1);
                    }
                }
                // → Key Press
                else if (KeyNames.ARROW_RIGHT.equals(event.getKeyName())) {
                    if (editor.getCurrentLayoutMode().isHorizontal()) {
                        FMEditorKeyEventHolder.this.selectChildNode(editor);
                    } else {
                        FMEditorKeyEventHolder.this.selectSiblingNode(editor, +1);
                    }
                }
                // Shift Key + Enter Key Press
                else if (EventHandler.shiftKeyDown() && KeyNames.ENTER.equals(event.getKeyName())) {
                    // 兄NODE作成
                    editor.nodeManager.addNodeBrother(0, editor);
                }
                // CTRL Key + Enter Key Press
                else if (event.isCtrlKeyDown() && KeyNames.ENTER.equals(event.getKeyName())) {
                    // 弟NODE作成
                    editor.nodeManager.addNodeBrother(1, editor);
                }
                // ENTER Key Press
                else if (KeyNames.ENTER.equals(event.getKeyName())) {
                    List<DrawRect> drawRects = editor.nodeManager.getSelectDrawRect();
                    if (drawRects.size() == 1) {
                        DrawRect drawRect = drawRects.get(0);
                        FMNode node = editor.drawItems.get(drawRect.hashCode()).getFmNode();
                        new FMProperty().openProperty(editor, ProcessType.Node, node);

                    }
                }
                // CTRL Key + A
                else if (event.isCtrlKeyDown() && "A".equals(event.getKeyName())) {
                    event.cancel();
                    editor.nodeManager.setSelectDrawRectAll(true);
                }
                // CTRL Key + S
                else if (event.isCtrlKeyDown() && "S".equals(event.getKeyName())) {
                    event.cancel();
                    editor.saveItem.fireEvent(new MenuItemClickEvent(editor.saveItem.getJsObj()));
                }
                // CTRL Key + Z
                else if (event.isCtrlKeyDown() && "Z".equals(event.getKeyName())) {
                    event.cancel();
                    editor.getEditManager().undo();
                    editor.refresh();
                    editor.drawPane.refreshNow();
                }
                // CTRL Key + Y
                else if (event.isCtrlKeyDown() && "Y".equals(event.getKeyName())) {
                    event.cancel();
                    editor.getEditManager().redo();
                    editor.refresh();
                    editor.drawPane.refreshNow();
                }
                // CTRL + O
                else if (event.isCtrlKeyDown() && "O".equals(event.getKeyName())) {
                    event.cancel();
                    List<DrawRect> drawRects = editor.nodeManager.getSelectDrawRect();
                    if (drawRects.size() == 1) {
                        DrawRect drawRect = drawRects.get(0);
                        FMNode node = editor.drawItems.get(drawRect.hashCode()).getFmNode();
                        String uuid = node.getRefuuid();
                        if (null != uuid && !uuid.isEmpty()) {

                            editor.getService().getVMFile(uuid, editor.getProjectId(), new AsyncCallback<VMFile>() {
                                @Override
                                public void onSuccess(VMFile result) {
                                    if (result == null) {
                                        editor.setOpenFileId(-3L);
                                    } else {
                                        editor.setOpenFileId(result.getId());
                                    }
                                    editor.referenceOpenItem.fireEvent(new MenuItemClickEvent(editor.referenceOpenItem.getJsObj()));
                                }

                                @Override
                                public void onFailure(Throwable caught) {
                                    SC.warn(caught.getMessage());
                                }
                            });
                        } else {
                            editor.setOpenFileId(-3L);
                            editor.referenceOpenItem.fireEvent(new MenuItemClickEvent(editor.referenceOpenItem.getJsObj()));
                        }
                    } else {
                        editor.referenceOpenItem.fireEvent(new MenuItemClickEvent(editor.referenceOpenItem.getJsObj()));
                    }
                }
                // DELETE Key Press
                else if (KeyNames.DEL.equals(event.getKeyName())) {
                    List<FMNode> selecteds = editor.nodeManager.getSelectFMNodes();
                    List<FMNode[]> nodeArrays = editor.nodeManager.getNodeChildren(selecteds);
                    editor.onDelete(selecteds, nodeArrays);
                }
                // CTRL Key + SHIFT + I
                else if (event.isCtrlKeyDown() && EventHandler.shiftKeyDown() && "I".equals(event.getKeyName())) {
                    event.cancel();
                    editor.nodeManager.changeOptionalOfSelectedNode(editor, false);
                }
                // CTRL Key + I
                else if (event.isCtrlKeyDown() && "I".equals(event.getKeyName())) {
                    event.cancel();
                    editor.nodeManager.changeOptionalOfSelectedNode(editor, true);
                }
                // CTRL Key + SHIFT + E
                else if (event.isCtrlKeyDown() && EventHandler.shiftKeyDown() && "E".equals(event.getKeyName())) {
                    event.cancel();
                    editor.nodeManager.changeEdgeOfSelectedNode(editor, ChildType.XOR);
                }
                // CTRL Key + E
                else if (event.isCtrlKeyDown() && "E".equals(event.getKeyName())) {
                    event.cancel();
                    editor.nodeManager.changeEdgeOfSelectedNode(editor, ChildType.AND);
                }
            }
        };
    }

    /**
     * Method to select sibling nodes.
     * @param editor Main class of feature-model editor
     * @param diff If it is -1, select the older brother node.<br>
     *            If it is +1, select the younger brother node.
     */
    private void selectSiblingNode(FMEditor editor, int diff) {
        List<DrawRect> drawRects = editor.nodeManager.getSelectDrawRect();
        if (drawRects.size() == 1) {
            DrawRect drawRect = drawRects.get(0);
            FMNode node = editor.drawItems.get(drawRect.hashCode()).getFmNode();
            FMNode parentNode = editor.nodeManager.getParentNode(node);
            if (parentNode == null)
                return;
            if (parentNode.getChildren().size() > 1) {
                Comparator<DrawRect> comparator = editor.getCurrentLayoutMode().getNodeChildrenComparator();
                Map<Integer, FMNode> map = editor.nodeManager.sortNodeChildren(parentNode.getChildren(), comparator);
                int key = 0;
                for (Map.Entry<Integer, FMNode> entry : map.entrySet()) {
                    if (entry.getValue().hashCode() == node.hashCode()) {
                        key = entry.getKey();
                        if (map.get(key + diff) != null) {
                            key += diff;
                            break;
                        }
                    }
                }
                editor.nodeManager.setSelectDrawRectAll(false);
                editor.nodeManager.setSelectDrawRect(map.get(key));
            }
        }
    }

    /**
     * Method to select parent node.
     * @param editor Main class of feature-model editor
     */
    private void selectParentNode(FMEditor editor) {
        List<DrawRect> drawRects = editor.nodeManager.getSelectDrawRect();
        if (drawRects.size() == 1) {
            DrawRect drawRect = drawRects.get(0);
            FMNode node = editor.drawItems.get(drawRect.hashCode()).getFmNode();
            FMNode parentNode = editor.nodeManager.getParentNode(node);
            if (parentNode != null) {
                editor.nodeManager.setSelectDrawRectAll(false);
                editor.nodeManager.setSelectDrawRect(parentNode);
            }
        }
    }

    /**
     * Method to select child nodes.
     * @param editor Main class of feature-model editor
     */
    private void selectChildNode(FMEditor editor) {
        List<DrawRect> drawRects = editor.nodeManager.getSelectDrawRect();
        if (drawRects.size() == 1) {
            DrawRect drawRect = drawRects.get(0);
            FMNode node = editor.drawItems.get(drawRect.hashCode()).getFmNode();
            if (node.getChildren().size() > 0) {
                Comparator<DrawRect> comparator = editor.getCurrentLayoutMode().getNodeChildrenComparator();
                Map<Integer, FMNode> map = editor.nodeManager.sortNodeChildren(node.getChildren(), comparator);
                editor.nodeManager.setSelectDrawRectAll(false);
                editor.nodeManager.setSelectDrawRect(map.get(1));
            }
        }
    }
}
