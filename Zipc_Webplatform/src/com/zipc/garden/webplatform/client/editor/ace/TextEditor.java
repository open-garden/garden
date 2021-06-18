package com.zipc.garden.webplatform.client.editor.ace;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * Use with editors not managed by EMF.<br>
 * It is expressed in text format.
 */
public class TextEditor extends com.zipc.garden.webplatform.client.editor.Editor {

    /** Ace.js text editor */
    protected Editor editor;

    /** Ace text editor tabs */
    protected Tab tab;

    /** Contains information on the displayed text file */
    protected VMFile file;

    /** Asynchronous interface for getting text information */
    protected EditResourceServiceAsync service;

    /** Manage input history of text editor */
    public UndoManager manager;

    /** Text editor layout */
    protected Layout layout;

    /** Contents of the text editor when it was saved */
    protected String savedContents;

    /** Context menu for save processing */
    protected MenuItem saveItem;

    /**
     * constructor
     * @param tab text editor tabs
     * @param file text file
     * @param service Asynchronous interface for getting text information
     */
    public TextEditor(Tab tab, VMFile file, EditResourceServiceAsync service) {
        this.tab = tab;
        this.file = file;
        this.service = service;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSaveHandler(ClickHandler clickHandler) {
        saveItem.addClickHandler(clickHandler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChanged() {
        return savedContents != null && !savedContents.equals(editor.getValue());
    }

    /**
     * Holds the text when saved.
     */
    @Override
    public void setSavedPosition() {
        savedContents = editor.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create() {
        saveItem = new MenuItem("save");

        layout = new Layout();
        layout.setWidth("100%");
        layout.setHeight("100%");
        Menu menu = new Menu();
        menu.addItem(saveItem);
        layout.setContextMenu(menu);
        HTML html = new HTML();
        html.setWidth("100%");
        html.setHeight("100%");
        html.getElement().setId(tab.getID() + "textEditor");
        layout.addMember(html);

        scheduleDeferred(html.getElement().getId());
    }

    /**
     * Generate Ace Text Editor, Set the text contents stored in DB
     * @param parentId Html element number of text editor
     */
    protected void scheduleDeferred(String parentId) {
        Scheduler.get().scheduleDeferred(() -> {
            service.getTextFileContent(file.getId(), new AsyncCallback<String>() {

                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }

                @Override
                public void onSuccess(String result) {

                    editor = Ace.edit(parentId);
                    editor.setTheme("ace/theme/chrome");
                    editor.setFontSize(14);
                    EditSession session = editor.getSession();
                    session.setMode(AceMode.getByExtension(file.getExtensionStr()));
                    session.setUseWrapMode(true);
                    session.setTabSize(2);
                    manager = session.getUndoManager();
                    editor.blockScrolling = "Infinity";
                    editor.setOption("enableBasicAutocompletion", "true");
                    editor.setOption("enableSnippets", "true");
                    editor.setOption("enableLiveAutocompletion", "false");
                    editor.setValue(result);
                    manager.reset();
                    savedContents = editor.getValue();
                    session.on("change", (event) -> {
                        String tabName = tab.getAttributeAsString("TabName");
                        if (isChanged()) {
                            tabName = "*" + tabName;
                        }
                        tab.setTitle(ModelingProjectView.getTabImgTitle(tabName, file.getExtension()));
                    });

                    /** This is like Native Javascript **/
                    JsObject command = new JsObject();
                    JsObject bindKey = new JsObject();
                    bindKey.win = "Ctrl-S";
                    bindKey.mac = "Cmd-S";
                    command.name = "save";
                    command.bindKey = bindKey;
                    command.exec = (event) -> {
                        saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
                    };
                    /***********************************/

                    editor.commands.addCommand(command);
                }
            });
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuItem getSaveItem() {
        return this.saveItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Layout getLayout() {
        return layout;
    }

    /**
     * Get input value of text editor
     * @return input value
     */
    public String getValue() {
        return editor.getValue();
    }

    /**
     * This method manages EMF operation commands. Therefore, it is not used by text editors.
     * @deprecated
     */
    @Override
    public EditManager getEditManager() {
        return null;
    }

    /**
     * This method gets the EMF root model. Therefore, it is not used in text editors.
     * @deprecated
     */
    @Override
    public AbstractRootElement getRoot() {
        return null;
    }
}
