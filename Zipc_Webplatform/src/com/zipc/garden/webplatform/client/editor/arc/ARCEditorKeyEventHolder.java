package com.zipc.garden.webplatform.client.editor.arc;

import java.util.Comparator;
import java.util.Map;

import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.model.arc.ARCLine;
import com.zipc.garden.model.arc.ARCState;
import com.zipc.garden.webplatform.client.editor.EditorDrawLine;

/**
 * Class which manages shortcut key events of architecture editor.
 */
public class ARCEditorKeyEventHolder {

    /**
     * Event when the keyboard is operated. <br>
     * Occurs when the canvas has focus.
     * @param editor Main class of architecture editor
     * @return Key press handler
     */
    protected KeyPressHandler createShortcutKeyEvent(ARCEditor editor) {
        return new KeyPressHandler() {

            @Override
            public void onKeyPress(KeyPressEvent event) {
                // Escape
                if (KeyNames.ESC.equals(event.getKeyName())) {
                    editor.lineManager.addLineDrawItems = new DrawItem[2];
                    editor.stateManager.selectDrawItemAll(false);
                    editor.lineManager.selectDrawLineAll(false);
                }
                // F2 Key Press
                else if ("f2".equals(event.getKeyName())) {
                    Map<ARCState, DrawItem> drawItems = editor.stateManager.getSelectedDrawItems();
                    Map<ARCLine, EditorDrawLine> drawLines = editor.lineManager.getSelectedDrawLines();
                    if (drawItems.size() == 1 && drawLines.size() == 0) {
                        editor.stateManager.onRename(drawItems.values().stream().findFirst().get());
                    }
                }
                // CTRL Key + A
                else if (event.isCtrlKeyDown() && "A".equals(event.getKeyName())) {
                    event.cancel();
                    editor.stateManager.selectDrawItemAll(true);
                }
                // CTRL Key + S
                else if (event.isCtrlKeyDown() && "S".equals(event.getKeyName())) {
                    event.cancel();
                    editor.getSaveItem().fireEvent(new MenuItemClickEvent(editor.getSaveItem().getJsObj()));
                }
                // CTRL Key + Z
                else if (event.isCtrlKeyDown() && "Z".equals(event.getKeyName())) {
                    event.cancel();
                    editor.manager.undo();
                    editor.refresh();
                }
                // CTRL Key + Y
                else if (event.isCtrlKeyDown() && "Y".equals(event.getKeyName())) {
                    event.cancel();
                    editor.manager.redo();
                    editor.refresh();
                }
                // CTRL Key + C
                else if (event.isCtrlKeyDown() && "C".equals(event.getKeyName())) {
                    event.cancel();
                    editor.stateManager.copyStateMachine();
                }
                // CTRL Key + V
                else if (event.isCtrlKeyDown() && "V".equals(event.getKeyName())) {
                    event.cancel();
                    Map<ARCState, DrawItem> drawItems = editor.stateManager.getSelectedDrawItems();
                    if (drawItems != null && drawItems.isEmpty()) {
                        editor.stateManager.pasteStateMachine(0, 0);
                    } else {
                        int minTop = drawItems.keySet().stream().min(Comparator.comparing(x -> x.getTop())).get().getTop();
                        int minLeft = drawItems.keySet().stream().min(Comparator.comparing(x -> x.getLeft())).get().getLeft();
                        editor.stateManager.pasteStateMachine(minTop, minLeft);
                    }
                }
                // DELETE Key
                else if (KeyNames.DEL.equals(event.getKeyName())) {
                    event.cancel();
                    editor.stateManager.deleteStateAndLine();

                }
            }
        };
    }
}
