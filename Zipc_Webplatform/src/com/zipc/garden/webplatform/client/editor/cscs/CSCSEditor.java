package com.zipc.garden.webplatform.client.editor.cscs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.AdvancedCriteria;
import com.smartgwt.client.data.Criterion;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.OperatorId;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.events.ShowContextMenuEvent;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellContextClickEvent;
import com.smartgwt.client.widgets.grid.events.CellContextClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.cscs.CSCSPattern;
import com.zipc.garden.model.cscs.CSCSRoot;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.ace.EditSession;
import com.zipc.garden.webplatform.client.editor.ace.JsObject;
import com.zipc.garden.webplatform.client.editor.ace.UndoManager;
import com.zipc.garden.webplatform.client.editor.xtext.XtextAce;
import com.zipc.garden.webplatform.client.service.GenerateResourceService;
import com.zipc.garden.webplatform.client.service.GenerateResourceServiceAsync;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * It is a class that setting concrete-scenario-set.
 */
public class CSCSEditor extends Editor {

    /** Menu for saving the contents of CSCSEditor */
    protected MenuItem saveItem = new MenuItem("Save");

    /** Overall layout of CSCSEditor */
    private final VLayout layout = new VLayout();

    /** Layout to display the data part of CSCS editor */
    private Layout dataArea;

    /** EMF model that summarizes the contents of CSCSEditor */
    private CSCSRoot root;

    /** ID of the project in which the CSCS file is managed */
    private Long projectId;

    /** Class that summarizes the contents of the CSCS file */
    private VMFile file;

    /** Context menu */
    private Menu mainMenu;

    /** Context menu for edit concrete-scenario */
    private MenuItem editMenuItem;

    /** Context menu for show concrete-scenario */
    private MenuItem showMenuItem;

    /** Text item for filtering the data part */
    private TextItem searchItem;

    /** Text item that displays the page number */
    private StaticTextItem pageText;

    /** Button to return to the previous page */
    private ButtonItem prevBtn;

    /** Button to go to the next page */
    private ButtonItem nextBtn;

    /** Button to reload CSCS editor */
    private ButtonItem updateBtn;

    /** ListGrid displaying the contents of an CSCS file */
    private ListGrid listGrid;

    /** Maximum number of rows displayed on one page */
    private int recordCount = 100;

    /** updateBtn title */
    private static final String UPDATE = "Update";

    /** Asynchronous interface to get the created concrete-scenario-set */
    private final GenerateResourceServiceAsync genResourceService = GWT.create(GenerateResourceService.class);

    /** Asynchronous interface for registering to CSCSPatternDAO table */
    private final CSCSResourceServiceAsync cscsResourceService = GWT.create(CSCSResourceService.class);

    /** Selected row's CSS Pattern */
    private CSCSPattern selectedPattern;

    /** True if the ListGrid displaying CSCS data was right clicked */
    private boolean cellContextClickFlag = false;

    /**
     * constructor
     * @param root Root model of CSCS editor
     * @param projectId The project ID in which this CSCS is managed
     * @param file Information of CSCS file to be displayed
     */
    public CSCSEditor(CSCSRoot root, Long projectId, VMFile file) {
        this.root = root;
        this.projectId = projectId;
        this.file = file;
    }

    /**
     * Not used
     */
    @Override
    public EditManager getEditManager() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Layout getLayout() {
        return layout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuItem getSaveItem() {
        return saveItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSaveHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        saveItem.addClickHandler(handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractRootElement getRoot() {
        return root;
    }

    /**
     * {@inheritDoc}<br>
     * In this editor, only false is returned
     */
    @Override
    public boolean isChanged() {
        return false;
    }

    /**
     * Not used
     */
    @Override
    public void setSavedPosition() {
    }

    /**
     * {@inheritDoc}
     */
    public void create() {
        layout.setWidth100();
        layout.setHeight100();
        layout.setOverflow(Overflow.HIDDEN);
        layout.draw();

        VLayout allArea = new VLayout();
        allArea.setWidth100();
        allArea.setHeight100();

        DynamicForm searchArea = new DynamicForm();
        searchArea.setNumCols(2);
        searchArea.setWidth100();
        searchArea.setHeight(50);
        searchArea.setColWidths(50, "*");
        searchArea.setPadding(10);

        searchItem = new TextItem("Search");
        searchItem.setAlign(Alignment.LEFT);
        searchItem.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent event) {
                String searchVal = "";
                if (event.getItem() != null) {
                    searchVal = event.getValue() != null ? event.getValue().toString() : "";
                }
                if ("".equals(searchVal)) {
                    searchVal = searchItem.getValueAsString() != null ? searchItem.getValueAsString() : "";
                }
                AdvancedCriteria criteria = new AdvancedCriteria(OperatorId.OR, new Criterion[] { new Criterion("pattern", OperatorId.ICONTAINS, searchVal) });
                listGrid.filterData(criteria);
            }
        });

        searchArea.setFields(searchItem);

        DynamicForm btnArea = new DynamicForm();
        btnArea.setNumCols(4);
        btnArea.setWidth100();
        btnArea.setColWidths(30, 120, 30, 80);

        prevBtn = new ButtonItem("<");
        prevBtn.setEndRow(false);
        prevBtn.setAlign(Alignment.CENTER);
        prevBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                pageTransition(root.getBegin() - recordCount, recordCount);
            }
        });

        pageText = new StaticTextItem();
        pageText.setAlign(Alignment.CENTER);
        pageText.setShowTitle(false);
        pageText.setValue("0 - 0 of 0");

        nextBtn = new ButtonItem(">");
        nextBtn.setStartRow(false);
        nextBtn.setEndRow(false);
        nextBtn.setAlign(Alignment.CENTER);
        nextBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                pageTransition(root.getBegin() + recordCount, recordCount);
            }
        });

        updateBtn = new ButtonItem(UPDATE);
        updateBtn.setAlign(Alignment.CENTER);
        updateBtn.setStartRow(false);
        updateBtn.setWidth(150);
        updateBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                pageTransition(0, recordCount);
            }
        });

        Scheduler.get().scheduleDeferred(() -> {
            allArea.addMember(searchArea);
            allArea.addMember(btnArea);
            allArea.addMember(dataArea);
            btnArea.setItems(prevBtn, pageText, nextBtn, updateBtn);
            btnArea.setHeight(prevBtn.getHeight());
            pageTransition(root.getBegin(), recordCount);
            setDisabledToButton();
        });

        mainMenu = new Menu();
        editMenuItem = new MenuItem("Edit Concrete Scenario");
        showMenuItem = new MenuItem("Show Concrete Scenario");

        listGrid = new ListGrid();

        listGrid.setWidth100();
        listGrid.setHeight100();
        listGrid.setCellHeight(25);
        listGrid.setLeaveScrollbarGap(false);
        listGrid.setAlternateFieldStyles(false);
        listGrid.setSelectionType(SelectionStyle.NONE);
        listGrid.setContextMenu(mainMenu);
        listGrid.setShowHeaderContextMenu(false);
        listGrid.setCanReorderFields(false);
        listGrid.setCanReorderRecords(false);
        listGrid.setCanResizeFields(false);
        listGrid.setCanSort(true);
        listGrid.setCanEdit(false);

        listGrid.addShowContextMenuHandler(new ShowContextMenuHandler() {
            @Override
            public void onShowContextMenu(ShowContextMenuEvent event) {
                if (!cellContextClickFlag) {
                    event.cancel();
                }
                cellContextClickFlag = false;
            }
        });

        listGrid.addCellContextClickHandler(new CellContextClickHandler() {
            @Override
            public void onCellContextClick(CellContextClickEvent event) {
                selectedPattern = (CSCSPattern) event.getRecord().getAttributeAsObject("cscsPattern");
                mainMenu.setItems(editMenuItem, showMenuItem);
                cellContextClickFlag = true;
            }
        });
        editMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editConcreteScenarioWindow(selectedPattern);
            }
        });
        showMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                showConcreteScenarioWindow();
            }
        });

        dataArea = new Layout();
        dataArea.setWidth100();
        dataArea.setHeight100();

        dataArea.addMember(listGrid);
        layout.addChild(allArea);
    }

    /**
     * The data acquired from CSCSPatternDAO is displayed.
     */
    private void fillData() {
        List<ListGridRecord> recordList = new ArrayList<>();
        for (CSCSPattern pattern : root.getPatterns()) {
            ListGridRecord record = new ListGridRecord();
            record.setAttribute("no", Integer.parseInt(pattern.getId()));
            record.setAttribute("pattern", pattern.getPatternValue());
            record.setAttribute("cscsPattern", pattern);
            recordList.add(record);
        }
        ListGridRecord[] gridRecords = new ListGridRecord[recordList.size()];
        gridRecords = recordList.toArray(gridRecords);

        DataSource ds = ConcreteScenarioSetDS.getInstance();
        ds.setTestData(gridRecords);
        listGrid.setDataSource(ds);
        listGrid.sort();
        listGrid.fetchData();
        searchItem.fireEvent(new ChangedEvent(searchItem.getJsObj()));

        int num = root.getBegin() + recordCount;
        num = (int) (num > root.getAll() ? root.getAll() : num);
        pageText.setValue((root.getBegin() + 1) + " - " + num + " of " + root.getAll());
        Scheduler.get().scheduleDeferred(() -> layout.redraw());
    }

    /**
     * The record registered in CSCSPatternDAO is acquired and displayed.
     * @param argStartRecordOffset Record reading start offset
     * @param argRecordCount Maximum number of rows displayed on one page
     */
    private void pageTransition(int argStartRecordOffset, int argRecordCount) {
        genResourceService.getPartOfFileContent(projectId, file.getId(), argStartRecordOffset, argRecordCount, new AsyncCallback<byte[]>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(byte[] result) {
                if (result == null) {
                    SC.warn("The result could not be obtained.");
                    return;
                }
                BinaryResourceImpl r = new BinaryResourceImpl();
                ByteArrayInputStream bi = new ByteArrayInputStream(result);
                CSCSRoot cscsRoot = null;
                try {
                    if (file.getExtension() != Extension.TXT) {
                        r.load(bi, null);
                        cscsRoot = (CSCSRoot) r.getContents().get(0);
                    }
                } catch (IOException e) {
                    SC.warn(e.getMessage());
                    return;
                }
                switch (cscsRoot.getStatus()) {
                case Job.STATUS_NOEXEC:
                    SC.say("Cannot be acquired because it has not been executed.");
                    break;
                case Job.STATUS_EXECUTING:
                    SC.say("Cannot be acquired because it is being processed.");
                    break;
                case Job.STATUS_COMPLETE:
                    root = cscsRoot;
                    recordCount = argRecordCount;
                    fillData();
                    break;
                case Job.STATUS_CANCEL:
                    root = cscsRoot;
                    recordCount = argRecordCount;
                    fillData();
                    SC.warn("Canceled. Show only generated.");
                    break;
                default:
                    pageText.setValue("0 - 0 of " + BigDecimal.valueOf(root.getAll()).toPlainString());
                    SC.warn(cscsRoot.getMessage());
                    break;
                }
                setDisabledToButton();
            }
        });
    }

    /**
     * Controls whether the page break button is disabled.
     */
    private void setDisabledToButton() {
        int num = root.getBegin() + recordCount;
        prevBtn.setDisabled(root.getBegin() == 0);
        nextBtn.setDisabled(num >= root.getAll());
    }

    /**
     * A modal window for editing concrete-scenario is displayed.
     * @param pattern Selected row's CSCS Pattern
     */
    private void editConcreteScenarioWindow(CSCSPattern pattern) {
        Window cscModal = new Window();
        cscModal.setTitle("CSC Editor");
        cscModal.setShowMaximizeButton(true);
        cscModal.setIsModal(true);
        cscModal.setShowModalMask(true);
        cscModal.setKeepInParentRect(true);
        cscModal.setAutoCenter(true);
        cscModal.setBackgroundColor("white");
        cscModal.setCanDragResize(true);
        cscModal.setWidth(600);
        cscModal.setHeight(400);
        cscModal.show();

        HLayout cscEditorArea = new HLayout();
        cscEditorArea.setWidth("100%");
        cscEditorArea.setHeight("100%");
        cscEditorArea.setEdgeSize(1);
        cscEditorArea.setShowEdges(true);

        DynamicForm form = new DynamicForm();
        form.setNumCols(3);
        form.setColWidths("70%", "15%", "15%");
        ButtonItem saveBtn = new ButtonItem("save", "Save");
        saveBtn.setWidth("100%");
        saveBtn.setStartRow(false);
        saveBtn.setEndRow(false);
        ButtonItem closeBtn = new ButtonItem("close", "Close");
        closeBtn.setWidth("100%");
        closeBtn.setStartRow(false);
        form.setItems(new SpacerItem(), saveBtn, closeBtn);
        cscModal.addMembers(cscEditorArea, form);

        Dialog dialog = new Dialog();
        dialog.setTitle("Save CSC");
        dialog.setMessage("Save change in CSC ?");
        dialog.setWidth("*");
        dialog.setHeight("*");
        dialog.setIsModal(true);
        dialog.setShowModalMask(true);
        dialog.addCloseClickHandler(e -> dialog.markForDestroy());
        Button dialogSave = new Button("Save");
        Button dialogDontSave = new Button("Don't Save");
        Button dialogCancel = new Button("Cancel");
        dialogSave.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                saveBtn.fireEvent(new ClickEvent(saveBtn.getJsObj()));
                dialog.markForDestroy();
                cscModal.markForDestroy();
            }
        });
        dialogDontSave.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                dialog.markForDestroy();
                cscModal.markForDestroy();
            }
        });
        dialogCancel.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                dialog.markForDestroy();
            }
        });
        dialog.setButtons(dialogSave, dialogDontSave, dialogCancel);

        Scheduler.get().scheduleDeferred(() -> {
            JSONObject options = new JSONObject();
            options.put("parent", new JSONString(cscEditorArea.getDOM().getId()));
            options.put("sendFullText", JSONBoolean.getInstance(true));
            options.put("loadFromServer", JSONBoolean.getInstance(false));
            options.put("resourceId", new JSONString(file.getId() + ".sc"));
            options.put("syntaxDefinition", new JSONString("xtext/resource/sc"));
            options.put("baseUrl", new JSONString("/com.zipc.garden.webplatform.dsl.sc.web"));
            com.zipc.garden.webplatform.client.editor.ace.Editor editor = XtextAce.createEditor(options.getJavaScriptObject());
            String style = cscEditorArea.getDOM().getAttribute("style");
            cscEditorArea.getDOM().setAttribute("style", style + " height:100%;");
            editor.setTheme("ace/theme/eclipse");
            editor.setFontSize(14);
            editor.setShowPrintMargin(false);
            EditSession session = editor.getSession();
            session.setUseWrapMode(false);
            session.setTabSize(2);
            UndoManager manager = session.getUndoManager();
            if (pattern.getCsc() != null) {
                editor.setValue(pattern.getCsc());
            } else {
                editor.setValue("");
            }
            editor.focus();
            editor.gotoLine(0);
            manager.reset();

            /** This is like Native Javascript **/
            JsObject command = new JsObject();
            JsObject bindKey = new JsObject();
            bindKey.win = "Ctrl-Space|Ctrl-Shift-Space|Alt-Space";
            bindKey.mac = "Cmd-Space";
            command.name = "startCommandOriginal";
            command.bindKey = bindKey;
            command.exec = (event) -> {
                if (event.completer == null) {
                    event.commands.commands.startAutocomplete.exec.function(event);
                    event.completer.detach();
                }
                event.completer.getPopup().container.getStyle().setZIndex(cscModal.getZIndex() + 1);
                event.completer.showPopup(event);
                event.completer.cancelContextMenu();
            };
            editor.commands.addCommand(command);
            /***********************************/

            saveBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    pattern.setCsc(editor.getValue());
                    BinaryResourceImpl r = new BinaryResourceImpl();
                    r.getContents().add(pattern);
                    ByteArrayOutputStream bo = new ByteArrayOutputStream();
                    try {
                        r.save(bo, null);
                    } catch (IOException e) {
                        SC.warn(e.getMessage());
                    }
                    cscsResourceService.save(file.getId(), bo.toByteArray(), new AsyncCallback<Void>() {
                        @Override
                        public void onSuccess(Void result) {
                            pageTransition(root.getBegin(), recordCount);
                            SC.say("Your change was successful.");
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }
                    });
                }
            });

            closeBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    if (pattern.getCsc() != null && !editor.getValue().equals(pattern.getCsc())) {
                        dialog.show();
                    } else {
                        cscModal.markForDestroy();
                    }

                }
            });

            cscModal.addCloseClickHandler(new CloseClickHandler() {
                @Override
                public void onCloseClick(CloseClickEvent event) {
                    event.cancel();
                    closeBtn.fireEvent(new ClickEvent(closeBtn.getJsObj()));
                }
            });

            cscModal.addResizedHandler(new ResizedHandler() {
                @Override
                public void onResized(ResizedEvent event) {

                    // cscEditorArea is depend on ACEEditor,so you should arrange ACEEditor's style.
                    String style = cscEditorArea.getDOM().getAttribute("style");
                    cscEditorArea.getDOM().setAttribute("style", style + " height:100%;");

                    // you have to arrange smartGWT width because button is hided.
                    cscEditorArea.setWidth100();
                    editor.resize();
                }
            });
        });

    }

    /**
     * A modal window for displaying the concrete scenario is displayed.
     */
    private void showConcreteScenarioWindow() {
        Window showCscModal = new Window();
        showCscModal.setTitle("CSC Editor");
        showCscModal.setShowMaximizeButton(true);
        showCscModal.setIsModal(true);
        showCscModal.setShowModalMask(true);
        showCscModal.setKeepInParentRect(true);
        showCscModal.setAutoCenter(true);
        showCscModal.setBackgroundColor("white");
        showCscModal.setCanDragResize(true);
        showCscModal.setWidth(600);
        showCscModal.setHeight(400);
        showCscModal.show();
        showCscModal.setCanFocus(true);
        showCscModal.focus();

        DynamicForm form = new DynamicForm();
        form.setNumCols(2);
        form.setColWidths("85%", "15%");

        ButtonItem closeBtn = new ButtonItem("close", "Close");
        closeBtn.setWidth("100%");
        closeBtn.setStartRow(false);
        form.setItems(new SpacerItem(), closeBtn);

        showCscModal.addMembers(form);

        closeBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                showCscModal.markForDestroy();
            }
        });

        showCscModal.addCloseClickHandler(new CloseClickHandler() {
            @Override
            public void onCloseClick(CloseClickEvent event) {
                event.cancel();
                closeBtn.fireEvent(new ClickEvent(closeBtn.getJsObj()));
            }
        });
    }

    /**
     * Data source of concrete-scenario-set displayed in the data part
     */
    private static class ConcreteScenarioSetDS extends DataSource {

        private static ConcreteScenarioSetDS instance = null;

        public static ConcreteScenarioSetDS getInstance() {
            if (instance == null) {
                instance = new ConcreteScenarioSetDS("concreteScenarioSetDS");
            }
            return instance;
        }

        public ConcreteScenarioSetDS(String id) {
            setID(id);

            DataSourceTextField dsNo = new DataSourceTextField("no", "No");
            dsNo.setAttribute("width", 80);
            dsNo.setAttribute("align", Alignment.RIGHT);
            DataSourceTextField dsPattern = new DataSourceTextField("pattern", "Pattern");
            DataSourceTextField dsCscsPattern = new DataSourceTextField("cscsPattern", "CscsPattern");
            dsCscsPattern.setHidden(true);
            setFields(dsNo, dsPattern, dsCscsPattern);

            setClientOnly(true);
        }
    }
}
