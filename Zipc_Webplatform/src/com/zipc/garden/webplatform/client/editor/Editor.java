package com.zipc.garden.webplatform.client.editor;

import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.tab.Tab;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;

/**
 * It is a class that summarizes the methods used in various Editors.<br>
 * If you create a new Editor class, you need to inherit it.
 */
public abstract class Editor {

    /**
     * The history of commands operated on the screen is managed.
     * @return EditManager that manages the operations performed on the editor
     */
    public abstract EditManager getEditManager();

    /**
     * This menu saves the commands operated on the screen.
     * @return MenuItem for saving
     */
    public abstract MenuItem getSaveItem();

    /**
     * The method that is initially loaded when the editor or view is launched.
     */
    public abstract void create();

    /**
     * The layout of the editor or view.
     * @return Editor or view layout
     */
    public abstract Layout getLayout();

    /**
     * The model of the launched editor or view.
     * @return Editor or view model
     */
    public abstract AbstractRootElement getRoot();

    /**
     * Event handler when saveItem is pressed
     * @param clickHandler
     */
    public abstract void addSaveHandler(ClickHandler clickHandler);

    /**
     * If the editor is editing, true is stored.
     * @return
     */
    public abstract boolean isChanged();

    /**
     * If saved in the editor, the saved command index is saved.
     */
    public abstract void setSavedPosition();

    /**
     * This method will be executed when saved in the editor.
     * @param modelingProjectView Class that manages the contents of the project
     * @param editor Active editor
     * @param tab Active tab
     * @param handler A handler that can be used after the save process is completed.
     */
    public void save(ModelingProjectView modelingProjectView, Editor editor, Tab tab, PostProcessHandler handler) {
        modelingProjectView.executeSaveAction(editor, tab, handler);
    }
}
