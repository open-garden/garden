package com.zipc.garden.webplatform.client.editor;

import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;

/**
 * Interface used to launch the editor<br>
 * Implementation is required when one editor launches another.
 */
public interface EditorOpener {

    /**
     * Get the ID of the file to be launched
     * @return file id
     */
    public long getOpenFileId();

    /**
     * The file set in OpenFileId is started.<br>
     * It is implemented in the link below.<br>
     * {@link ModelingProjectView#clickOpenFile(EditorOpener)}<br>
     * The editor implementation simply specifies the menu that calls the ClickHandler argument.
     * @param handler Context menu click event handler
     */
    public void addOpenFileHandler(ClickHandler handler);
}
