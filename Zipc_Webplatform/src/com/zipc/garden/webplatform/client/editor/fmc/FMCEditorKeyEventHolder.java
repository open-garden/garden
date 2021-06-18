package com.zipc.garden.webplatform.client.editor.fmc;

import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * Class which manages key input event of feature-model-constraint editor
 */
public class FMCEditorKeyEventHolder {

    /**
     * Create shortcut key event for FMC editor
     * @param editor Main class of feature-model-constraint editor
     * @return KeyPressHandler
     */
    protected static KeyPressHandler createShortcutKeyEvent(FMCEditor editor) {
        return new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                // Ctrl + S
                if (event.isCtrlKeyDown() && "S".equals(event.getKeyName())) {
                    event.cancel();
                    editor.getSaveItem().fireEvent(new MenuItemClickEvent(editor.getSaveItem().getJsObj()));
                }
                // Ctrl + Z
                else if (event.isCtrlKeyDown() && "Z".equals(event.getKeyName())) {
                    event.cancel();
                    editor.getEditManager().undo();
                    editor.refresh();
                }
                // Ctrl + Y
                else if (event.isCtrlKeyDown() && "Y".equals(event.getKeyName())) {
                    event.cancel();
                    editor.getEditManager().redo();
                    editor.refresh();
                }
            }
        };
    }
}
