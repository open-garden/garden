package com.zipc.garden.webplatform.client.editor.cb;

import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * Manage SCSS editor shortcut key events
 */
public class CBEditorKeyEventHolder {

    /**
     * Event when the keyboard is operated. <br>
     * Occurs when the canvas has focus.
     * @param editor Main class of "scenarioset-setting editor"
     * @return Key Press Handler
     */
    protected static KeyPressHandler createShortcutKeyEvent(CBEditor editor) {
        return new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                // delete key
                if (KeyNames.DEL.equals(event.getKeyName())) {
                    editor.onDelete();
                }
                // Ctrl + S
                else if (event.isCtrlKeyDown() && "S".equals(event.getKeyName())) {
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
