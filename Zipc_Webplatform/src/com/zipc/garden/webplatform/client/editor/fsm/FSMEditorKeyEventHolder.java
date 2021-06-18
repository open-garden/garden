package com.zipc.garden.webplatform.client.editor.fsm;

import java.util.Map;

import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.model.fsm.FSMDPseudoStateType;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMDTransition;
import com.zipc.garden.webplatform.client.editor.EditorDrawLine;

/**
 * Class which manages shortcut key events of behavior-model editor.
 */
public class FSMEditorKeyEventHolder {

    /**
     * Event when the keyboard is operated. <br>
     * Occurs when the canvas has focus.
     * @param editor Main class of behavior-model editor
     * @return Key press handler
     */
    protected KeyPressHandler createShortcutKeyEvent(FSMEditor editor) {
        return new KeyPressHandler() {

            @Override
            public void onKeyPress(KeyPressEvent event) {
                // Escape
                if (KeyNames.ESC.equals(event.getKeyName())) {
                    editor.transManager.addLineDrawItems = new DrawItem[2];
                    editor.stateManager.selectDrawItemAll(editor, false);
                    editor.transManager.selectDrawLineAll(editor, false);
                }
                // F2 Key Press
                else if ("f2".equals(event.getKeyName())) {
                    Map<FSMDState, DrawItem> drawItems = editor.stateManager.getSelectedDrawItems(editor);
                    Map<FSMDTransition, EditorDrawLine> drawLines = editor.transManager.getSelectedDrawLines(editor);
                    if (drawItems.size() == 1 && drawLines.size() == 0) {
                        FSMDState state = drawItems.keySet().stream().findFirst().get();
                        if (FSMDPseudoStateType.NONE.equals(state.getType())) {
                            editor.stateManager.onRename(editor, drawItems.values().stream().findFirst().get());
                        }
                    } else if (drawItems.size() == 0 && drawLines.size() == 1) {
                        editor.transManager.onEditTransition(editor, drawLines.values().stream().findFirst().get().getDrawLines().get(0));
                    }
                }
                // CTRL Key + A
                else if (event.isCtrlKeyDown() && "A".equals(event.getKeyName())) {
                    event.cancel();
                    editor.stateManager.selectDrawItemAll(editor, true);
                }
                // CTRL Key + S
                else if (event.isCtrlKeyDown() && "S".equals(event.getKeyName())) {
                    event.cancel();
                    editor.saveItem.fireEvent(new MenuItemClickEvent(editor.saveItem.getJsObj()));
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
                // DELETE Key Press
                else if (KeyNames.DEL.equals(event.getKeyName())) {
                    editor.stateManager.deleteItem(editor);
                }
            }
        };
    }
}
