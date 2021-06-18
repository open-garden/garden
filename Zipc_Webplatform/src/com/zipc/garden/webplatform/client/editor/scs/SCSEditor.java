package com.zipc.garden.webplatform.client.editor.scs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.events.ShowContextMenuEvent;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellContextClickEvent;
import com.smartgwt.client.widgets.grid.events.CellContextClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.bp.BPRoot;
import com.zipc.garden.model.bp.BPSetting;
import com.zipc.garden.model.cb.CBRoot;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.scs.SCSPattern;
import com.zipc.garden.model.scs.SCSRoot;
import com.zipc.garden.model.scs.SCSSetting;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.EditorUtil;
import com.zipc.garden.webplatform.client.editor.SearchViewerInterface;
import com.zipc.garden.webplatform.client.editor.ViewSettingWindow;
import com.zipc.garden.webplatform.client.editor.ace.EditSession;
import com.zipc.garden.webplatform.client.editor.ace.JsObject;
import com.zipc.garden.webplatform.client.editor.ace.UndoManager;
import com.zipc.garden.webplatform.client.editor.bp.BPEditor;
import com.zipc.garden.webplatform.client.editor.bp.BPModelData;
import com.zipc.garden.webplatform.client.editor.tp.TPViewer;
import com.zipc.garden.webplatform.client.editor.xtext.XtextAce;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.service.GenerateResourceService;
import com.zipc.garden.webplatform.client.service.GenerateResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.shared.FileTreeNode;
import com.zipc.garden.webplatform.shared.JobStatusInfo;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * A class that displays a list of created scenario set.
 */
public class SCSEditor extends Editor implements SearchViewerInterface {

    /** MenuItem for saving the SCS editor */
    protected MenuItem saveItem = new MenuItem("Save");

    /** Overall layout of SCSEditor */
    private final VLayout layout = new VLayout();

    /** List grid displaying combination data */
    private Layout dataArea;

    /** EMF root model of SCS editor. */
    private SCSRoot root;

    /** Class that manages the contents of the project */
    private ModelingProjectView modelingProjectView;

    /** ID of the project in which the SCS file is managed */
    private Long projectId;

    /** Class that summarizes the contents of the SCS file */
    private VMFile file;

    /** ID of the directory in which the SCS file is managed */
    private long scsParentId;

    /** ListGrid context menu */
    private Menu mainMenu;

    /** MenuItem for editing Logical Scenario */
    private MenuItem editorMenuItem;

    /** MenuItem for generating Concrete Scenario based on the edited Logical Scenario */
    private MenuItem genCscMenuItem;

    /** MenuItem for displaying the created combination of feature-pattern and behavior-pattern */
    private MenuItem viewMenuItem;

    /** Text item that displays the page number */
    private StaticTextItem pageText;

    /** Button to apply input value and reload */
    private ButtonItem applyBtn;

    /** Button to return to the previous page */
    private ButtonItem prevBtn;

    /** Button to go to the next page */
    private ButtonItem nextBtn;

    /** Maximum number of rows displayed on one page */
    private IntegerItem maxRow;

    /** ListGrid displaying the created combinations of feature and behavior patterns */
    private ListGrid listGrid;

    /** Modal window for editing logical scenario */
    private Window lscEditorWinModal;

    /** Character string displayed when logical scenario is defined */
    private static final String DEFINED = "defined";

    /** Character string displayed when logical scenario is undefined */
    private static final String UNDEFINED = "undefined";

    /** It is an asynchronous interface to get the settings of the file. */
    private EditResourceServiceAsync editResourceService;

    /** Get the scenario set data. It is also an asynchronous interface for generating concrete scenario sets. */
    private final GenerateResourceServiceAsync genResourceService = GWT.create(GenerateResourceService.class);

    /** Asynchronous interface for saving the entered logical scenario */
    private final SCSResourceServiceAsync scsResourceService = GWT.create(SCSResourceService.class);

    /** A variable that holds the SCSPattern of the selected record */
    private SCSPattern selectedPattern;

    /** A variable that holds the ScssUUID of the selected record */
    private String selectedScssUuid;

    /** True if the cell was right-clicked. It is used to not react the right click of the header. */
    private boolean cellContextClickFlag = false;

    /**
     * constructor
     * @param root {@link SCSEditor#root}
     * @param modelingProjectView {@link SCSEditor#modelingProjectView}
     * @param projectId {@link SCSEditor#projectId}
     * @param file {@link SCSEditor#file}
     * @param editResourceService {@link SCSEditor#editResourceService}
     */
    public SCSEditor(SCSRoot root, ModelingProjectView modelingProjectView, Long projectId, VMFile file, EditResourceServiceAsync editResourceService) {
        this.root = root;
        this.modelingProjectView = modelingProjectView;
        this.projectId = projectId;
        this.file = file;
        this.editResourceService = editResourceService;
        this.editResourceService.getDirId(file.getId(), new AsyncCallback<Long>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(Long result) {
                scsParentId = Long.parseLong(result.toString());
            }
        });
        setSavedPosition();
    }

    /**
     * Not used.
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
     * {@inheritDoc}
     */
    @Override
    public void save(ModelingProjectView modelingProjectView, Editor editor, Tab tab, PostProcessHandler handler) {

        if (!maxRow.validate()) {
            return;
        }
        super.save(modelingProjectView, editor, tab, new PostProcessHandler() {

            @Override
            public void execute() {
                if (handler != null) {
                    handler.execute();
                }
                pageTransition(0, root.getMax());
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doApply() {
        applyBtn.fireEvent(new ClickEvent(applyBtn.getJsObj()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowIds(List<String> patternNos, List<String> uuids) {
        if (this.root.getSettings().size() == patternNos.size()) {
            for (int i = 0; i < patternNos.size(); i++) {
                AbstractSetting setting = this.root.getSettings().get(i);
                if (setting.getUuid().equals(uuids.get(i))) {
                    setting.setPatternNos(patternNos.get(i));
                }
            }
        }
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

        DynamicForm settingArea = new DynamicForm();
        settingArea.setNumCols(5);
        settingArea.setWidth100();
        settingArea.setColWidths(150, 50, 100, 50, "100%");

        applyBtn = new ButtonItem("Apply");
        applyBtn.setEndRow(false);
        applyBtn.setWidth("100%");

        applyBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                root.setMax(maxRow.getValueAsInteger());
                root.setAll(0);
                root.setBegin(0);
                root.setEnd(0);
                root.getSettings().forEach(s -> {
                    SCSSetting tpSetting = (SCSSetting) s;
                    tpSetting.getPatterns().clear();
                });
                saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
            }
        });

        maxRow = new IntegerItem("max");
        maxRow.setStartRow(false);
        maxRow.setEndRow(false);
        maxRow.setWidth("100%");
        maxRow.setBrowserInputType("tel");
        maxRow.setKeyPressFilter("[0-9]");
        maxRow.setRequired(true);
        IntegerRangeValidator validator = new IntegerRangeValidator();
        validator.setMax(1000);
        validator.setMin(100);
        maxRow.setValidators(validator);
        maxRow.setValue(root.getMax());
        maxRow.addChangedHandler(new ChangedHandler() {

            @Override
            public void onChanged(ChangedEvent event) {
                if (switchApplyState()) {
                    return;
                }
                root.setMax(maxRow.getValueAsInteger());
            }
        });

        DynamicForm btnArea = new DynamicForm();
        btnArea.setNumCols(5);
        btnArea.setColWidths(30, 120, 30, 80, 80);

        prevBtn = new ButtonItem("<");
        prevBtn.setEndRow(false);
        prevBtn.setAlign(Alignment.CENTER);
        prevBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                pageTransition(root.getBegin() - root.getMax(), root.getMax());
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
                pageTransition(root.getBegin() + root.getMax(), root.getMax());
            }
        });

        ButtonItem settingBtn = new ButtonItem("Setting");
        settingBtn.setWidth("100%");

        settingBtn.addClickHandler(e -> {
            ViewSettingWindow<SCSRoot> viewSettingWindow = new ViewSettingWindow<SCSRoot>(root, editResourceService, projectId);
            viewSettingWindow.create(tmpRoot -> {
                this.root = tmpRoot;
                doApply();
            });
        });

        Scheduler.get().scheduleDeferred(() -> {
            settingArea.setItems(settingBtn, applyBtn, maxRow);
            btnArea.setItems(prevBtn, pageText, nextBtn);
            allArea.addMembers(settingArea, btnArea, dataArea);
            setDisabledToButton();
        });

        mainMenu = new Menu();
        editorMenuItem = new MenuItem("Edit Logical Scenario");
        genCscMenuItem = new MenuItem("Generate Concrete Scenario");
        viewMenuItem = new MenuItem("View");

        listGrid = new ListGrid();

        listGrid.setWidth100();
        listGrid.setHeight100();
        listGrid.setCellHeight(25);
        listGrid.setShowAllRecords(true);
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

        ListGridField noField = new ListGridField("no", "No", 80);
        noField.setAlign(Alignment.RIGHT);

        ListGridField detailNoField = new ListGridField("detailNo", "DetailNo", 80);
        detailNoField.setAlign(Alignment.RIGHT);

        ListGridField lscField = new ListGridField("lsc", "lsc", 120);
        lscField.setAlign(Alignment.LEFT);

        ListGridField cscField = new ListGridField("csc", "csc", 120);
        cscField.setAlign(Alignment.LEFT);

        ListGridField patternField = new ListGridField("pattern", "Pattern");
        patternField.setWidth("*");
        patternField.setAlign(Alignment.LEFT);

        listGrid.setFields(noField, detailNoField, lscField, cscField, patternField);

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
                selectedScssUuid = event.getRecord().getAttributeAsString("scssUuid");
                selectedPattern = (SCSPattern) event.getRecord().getAttributeAsObject("scsPattern");
                if (selectedPattern.getLsc() == null || "".equals(selectedPattern.getLsc())) {
                    genCscMenuItem.setEnabled(false);
                } else {
                    genCscMenuItem.setEnabled(true);
                }
                mainMenu.setItems(editorMenuItem, genCscMenuItem, viewMenuItem);
                cellContextClickFlag = true;
            }
        });
        editorMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                openLSCEditorWindow(selectedPattern);
            }
        });
        genCscMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                generateConcreteScenario(selectedPattern);
            }
        });
        viewMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                onClickPatternView(selectedPattern);
            }
        });

        dataArea = new Layout();
        dataArea.setWidth100();
        dataArea.setHeight100();

        dataArea.addMember(listGrid);
        layout.addChild(allArea);

        pageTransition(root.getBegin(), root.getMax());
        setDisabledToButton();
    }

    /**
     * Create a record of combination of behavior-pattern and feature-pattern in ListGrid.
     */
    private void fillData() {
        List<ListGridRecord> recordList = new ArrayList<ListGridRecord>();
        int allNum = root.getBegin() + 1;
        for (int i = 0; i < root.getSettings().size(); i++) {
            SCSSetting scsSetting = (SCSSetting) root.getSettings().get(i);
            for (SCSPattern pattern : scsSetting.getPatterns()) {
                ListGridRecord record = new ListGridRecord();
                record.setAttribute("no", allNum++);
                record.setAttribute("detailNo", (i + 1) + "_" + pattern.getId());
                if (pattern.getLsc() == null || "".equals(pattern.getLsc())) {
                    record.setAttribute("lsc", UNDEFINED);
                } else {
                    record.setAttribute("lsc", DEFINED);
                }
                if (pattern.getCscFileName() == null || "".contentEquals(pattern.getCscFileName())) {
                    record.setAttribute("csc", "");
                } else {
                    record.setAttribute("csc", pattern.getCscFileName());
                }
                record.setAttribute("pattern", pattern.getPatternValue());
                record.setAttribute("scsPattern", pattern);
                record.setAttribute("scssUuid", scsSetting.getUuid());
                recordList.add(record);
            }
        }
        ListGridRecord[] gridRecords = new ListGridRecord[recordList.size()];
        gridRecords = recordList.toArray(gridRecords);
        listGrid.setData(gridRecords);
        Scheduler.get().scheduleDeferred(() -> layout.redraw());
    }

    /**
     * Not used.
     */
    @Override
    public boolean isChanged() {
        return false;
    }

    /**
     * Not used.
     */
    @Override
    public void setSavedPosition() {
    }

    /**
     * Used for page transition of ListGrid.
     * @param argStartRecordOffset First line number
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
                final SCSRoot scsRoot;
                try {
                    r.load(bi, EditOptions.getDefaultLoadOptions());
                    scsRoot = (SCSRoot) r.getContents().get(0);
                } catch (IOException e) {
                    SC.warn(e.getMessage());
                    return;
                }
                genResourceService.getSettingFileJobStatusList(file.getId(), projectId, new AsyncCallback<List<JobStatusInfo>>() {
                    @Override
                    public void onSuccess(List<JobStatusInfo> result) {
                        for (JobStatusInfo jobStatusInfo : result) {
                            switch (jobStatusInfo.getStatusNum()) {
                            case Job.STATUS_NOEXEC:
                                SC.say("Cannot be acquired because it has not been executed.");
                                return;
                            case Job.STATUS_EXECUTING:
                                SC.say("Cannot be acquired because it is being processed.");
                                return;
                            case Job.STATUS_COMPLETE:
                                continue;
                            case Job.STATUS_CANCEL:
                                SC.warn("Canceled. Show only generated.");
                                break;
                            default:
                                pageText.setValue("0 - 0 of " + BigDecimal.valueOf(root.getAll()).toPlainString());
                                SC.warn(jobStatusInfo.getInfomation());
                                break;
                            }
                        }
                        showContents(scsRoot, argRecordCount);
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }

                });
            }
        });
    }

    /**
     * The data acquired in the page transition process is reflected in ListGrid and page text.
     * @param scsRoot
     * @param argRecordCount
     */
    void showContents(SCSRoot scsRoot, int argRecordCount) {
        root = scsRoot;
        root.setMax(argRecordCount);
        fillData();

        double num = root.getBegin() + root.getMax();
        num = num > root.getAll() ? root.getAll() : num;
        pageText.setValue((root.getBegin() + 1) + " - " + num + " of " + root.getAll());
        setDisabledToButton();
    }

    /**
     * Controls the page break button of ListGrid. Whether the page break button can be pressed is controlled based on the
     * current page information.
     */
    private void setDisabledToButton() {
        int num = root.getBegin() + root.getMax();
        prevBtn.setDisabled(root.getBegin() == 0);
        nextBtn.setDisabled(num >= root.getAll());
    }

    /**
     * A modal window for editing the logical scenario is created and displayed.
     * @param pattern SCS pattern related to the record selected from ListGrid
     */
    private void openLSCEditorWindow(SCSPattern pattern) {
        lscEditorWinModal = new Window();
        lscEditorWinModal.setTitle("LSC Editor");
        lscEditorWinModal.setShowMaximizeButton(true);
        lscEditorWinModal.setIsModal(true);
        lscEditorWinModal.setShowModalMask(true);
        lscEditorWinModal.setShowBody(true);
        lscEditorWinModal.setShowFooter(true);
        lscEditorWinModal.setShowResizer(true);
        lscEditorWinModal.setKeepInParentRect(true);
        lscEditorWinModal.setAutoCenter(true);
        lscEditorWinModal.setBackgroundColor("white");
        lscEditorWinModal.setCanDragResize(true);
        lscEditorWinModal.setWidth(600);
        lscEditorWinModal.setHeight(400);
        lscEditorWinModal.show();

        HLayout lscEditorArea = new HLayout();
        lscEditorArea.setWidth("100%");
        lscEditorArea.setHeight("100%");
        lscEditorArea.setEdgeSize(1);
        lscEditorArea.setShowEdges(true);

        HLayout hlayout = new HLayout();
        hlayout.setHeight(30);
        hlayout.setWidth100();
        hlayout.setLayoutLeftMargin(20);
        IButton saveBtn = new IButton("Save");
        saveBtn.setHeight100();
        saveBtn.setWidth100();
        saveBtn.setMargin(5);
        IButton closeBtn = new IButton("Close");
        closeBtn.setHeight100();
        closeBtn.setWidth100();
        closeBtn.setMargin(5);
        LayoutSpacer hspacer = new LayoutSpacer(3, "100%");
        hspacer.setBackgroundColor("white");
        hlayout.addMembers(hspacer, saveBtn, hspacer, closeBtn, hspacer);

        lscEditorWinModal.getBody().addChild(lscEditorArea);
        lscEditorWinModal.getFooter().addMember(hlayout, 0);

        Dialog dialog = new Dialog();
        dialog.setTitle("Save LSC");
        dialog.setMessage("Save change in LSC ?");
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
                lscEditorWinModal.markForDestroy();
            }
        });
        dialogDontSave.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                dialog.markForDestroy();
                lscEditorWinModal.markForDestroy();
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
            options.put("parent", new JSONString(lscEditorArea.getDOM().getId()));
            options.put("sendFullText", JSONBoolean.getInstance(true));
            options.put("loadFromServer", JSONBoolean.getInstance(false));
            options.put("resourceId", new JSONString(file.getId() + ".sc"));
            options.put("syntaxDefinition", new JSONString("xtext/resource/sc"));
            options.put("baseUrl", new JSONString("/com.zipc.garden.webplatform.dsl.sc.web"));
            com.zipc.garden.webplatform.client.editor.ace.Editor editor = XtextAce.createEditor(options.getJavaScriptObject());
            String style = lscEditorArea.getDOM().getAttribute("style");
            lscEditorArea.getDOM().setAttribute("style", style + " height:100%;");
            editor.setTheme("ace/theme/eclipse");
            editor.setFontSize(14);
            editor.setShowPrintMargin(false);
            EditSession session = editor.getSession();
            session.setUseWrapMode(false);
            session.setTabSize(2);
            UndoManager manager = session.getUndoManager();
            if (pattern.getLsc() != null) {
                editor.setValue(pattern.getLsc());
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
                event.completer.getPopup().container.getStyle().setZIndex(lscEditorWinModal.getZIndex() + 1);
                event.completer.showPopup(event);
                event.completer.cancelContextMenu();
            };
            editor.commands.addCommand(command);
            /***********************************/

            saveBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

                @Override
                public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                    pattern.setLsc(editor.getValue());
                    BinaryResourceImpl r = new BinaryResourceImpl();
                    r.getContents().add(pattern);
                    ByteArrayOutputStream bo = new ByteArrayOutputStream();
                    try {
                        r.save(bo, null);
                    } catch (IOException e) {
                        SC.warn(e.getMessage());
                    }
                    editResourceService.getVMFile(selectedScssUuid, projectId, new AsyncCallback<VMFile>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }

                        @Override
                        public void onSuccess(VMFile result) {
                            scsResourceService.saveLSC(result.getId(), bo.toByteArray(), new AsyncCallback<Void>() {
                                @Override
                                public void onSuccess(Void result) {
                                    pageTransition(root.getBegin(), root.getMax());
                                    SC.say("Your change was successful.");
                                }

                                @Override
                                public void onFailure(Throwable caught) {
                                    SC.warn(caught.getMessage());
                                }
                            });
                        }
                    });
                }
            });

            closeBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

                @Override
                public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                    if (pattern.getLsc() != null && !editor.getValue().equals(pattern.getLsc())) {
                        dialog.show();
                    } else {
                        lscEditorWinModal.markForDestroy();
                    }

                }
            });

            lscEditorWinModal.addCloseClickHandler(new CloseClickHandler() {
                @Override
                public void onCloseClick(CloseClickEvent event) {
                    event.cancel();
                    closeBtn.fireEvent(new com.smartgwt.client.widgets.events.ClickEvent(closeBtn.getJsObj()));
                }
            });

            lscEditorWinModal.addResizedHandler(new ResizedHandler() {

                @Override
                public void onResized(ResizedEvent event) {
                    // lscEditorArea is depend on ACEEditor,so you should arrange ACEEditor's style.
                    String style = lscEditorArea.getDOM().getAttribute("style");
                    lscEditorArea.getDOM().setAttribute("style", style + " height:100%;");
                    // you have to arrange smartGWT width because button is hided.
                    lscEditorArea.setWidth100();
                    editor.resize();
                    lscEditorWinModal.getBody().setWidth100();
                    lscEditorWinModal.getFooter().setWidth100();
                }
            });

        });

    }

    /**
     * Concrete scenario is generated and reflected in ListGrid.
     * @param pattern SCS pattern related to the record selected from ListGrid
     */
    private void generateConcreteScenario(SCSPattern pattern) {
        BinaryResourceImpl r = new BinaryResourceImpl();
        r.getContents().add(pattern);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
            r.save(bo, null);
        } catch (IOException e) {
            SC.warn(e.getMessage());
        }
        editResourceService.getVMFile(selectedScssUuid, projectId, new AsyncCallback<VMFile>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(VMFile result) {
                genResourceService.generateConcreteScenarioSet(result.getId(), bo.toByteArray(), new AsyncCallback<VMFile>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(VMFile resultFile) {
                        if (null == resultFile) {
                            SC.warn("return null");
                        } else {
                            generateCallBackFile(modelingProjectView.getFileTreeNodeFactory().findTreeNode(modelingProjectView.getTree(), scsParentId), resultFile,
                                    resultFile.getId());

                            pageTransition(root.getBegin(), root.getMax());
                            String fileName = resultFile.getName() + "." + resultFile.getExtensionStr();
                            SC.say(fileName + " is generated.\nPlease wait a moment as the process is in progress.");
                        }
                    }
                });
            }
        });
    }

    /** A variable that holds the record position of the combination of the displayed function pattern and operation pattern */
    private int dispRecordNum = -1;

    /**
     * Create a modal window to display a combination of feature pattern and behavior pattern.
     * @param pattern SCS pattern related to the record selected from ListGrid
     */
    private void onClickPatternView(SCSPattern pattern) {
        final Window winModal = new Window();

        // set jump out react window
        winModal.setTitle("Pattern View");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.setShowFooter(true);
        winModal.setShowResizer(true);
        winModal.setKeepInParentRect(true);
        winModal.setAutoCenter(true);
        winModal.setBackgroundColor("white");
        winModal.setWidth(800);
        winModal.setHeight(600);
        winModal.setShowMaximizeButton(true);
        winModal.setCanDragResize(true);
        winModal.setFooterHeight(10);
        winModal.addCloseClickHandler(e -> winModal.markForDestroy());
        winModal.setAutoSize(true);

        VLayout viewLayout = new VLayout();
        viewLayout.setWidth100();
        viewLayout.setHeight100();
        viewLayout.setOverflow(Overflow.AUTO);

        DynamicForm scenarioNoForm = new DynamicForm();
        scenarioNoForm.setNumCols(4);
        scenarioNoForm.setWidth100();
        scenarioNoForm.setColWidths(30, "*", 30, "100%");
        StaticTextItem scenarioNo = new StaticTextItem();
        scenarioNo.setAlign(Alignment.CENTER);
        scenarioNo.setTextBoxStyle("font-weight-bold");
        scenarioNo.setShowTitle(false);

        ButtonItem winPrevBtn = new ButtonItem("<");
        winPrevBtn.setEndRow(false);
        winPrevBtn.setAlign(Alignment.CENTER);
        ButtonItem winNextBtn = new ButtonItem(">");
        winNextBtn.setStartRow(false);
        winNextBtn.setEndRow(false);
        winNextBtn.setAlign(Alignment.CENTER);

        Optional<ListGridRecord> dispRecord = Arrays.stream(listGrid.getRecords()).filter(r -> r.getAttributeAsObject("scsPattern").equals(pattern)).findFirst();
        if (dispRecord.isPresent()) {
            dispRecordNum = listGrid.getRowNum(dispRecord.get());
        }

        scenarioNoForm.setItems(winPrevBtn, scenarioNo, winNextBtn);
        viewLayout.addMember(scenarioNoForm);

        // Chaneg Bproot File show way into Vue Show methods

        winModal.addMember(viewLayout);
        winModal.show();
        winModal.focus();

        VLayout patternView = new VLayout();
        patternView.setWidth100();
        patternView.setHeight100();
        patternView.setMargin(20);
        viewLayout.addMember(patternView);

        winPrevBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ListGridRecord record = listGrid.getRecord(--dispRecordNum);
                SCSPattern selectPattern = (SCSPattern) record.getAttributeAsObject("scsPattern");
                changeBtnDisable(winPrevBtn, winNextBtn);
                redrawPattanView(selectPattern, scenarioNo, patternView, winModal);
            }
        });

        winNextBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ListGridRecord record = listGrid.getRecord(++dispRecordNum);
                SCSPattern selectPattern = (SCSPattern) record.getAttributeAsObject("scsPattern");
                changeBtnDisable(winPrevBtn, winNextBtn);
                redrawPattanView(selectPattern, scenarioNo, patternView, winModal);
            }
        });

        redrawPattanView(pattern, scenarioNo, patternView, winModal);
        changeBtnDisable(winPrevBtn, winNextBtn);

        winModal.addResizedHandler(new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent event) {
                viewLayout.setWidth100();
                winModal.getFooter().setWidth100();
            }
        });
    }

    /**
     * Controls the page break button of PatternView. <br>
     * Whether the page break button can be pressed is controlled based on the current page information.
     * @param winPrevBtn Prev button
     * @param winNextBtn Next button
     */
    private void changeBtnDisable(ButtonItem winPrevBtn, ButtonItem winNextBtn) {
        winPrevBtn.setDisabled(dispRecordNum < 1);
        winNextBtn.setDisabled(dispRecordNum >= listGrid.getRecords().length - 1);
    }

    /**
     * Read and display feature pattern and behavior pattern from RDF.
     * @param pattern Combination of feature-pattern and behavior-pattern
     * @param scenarioNo A TextItem that displays the current scenario number
     * @param patternView Layout to display feature-pattern and behavior-pattern
     * @param winModal Modal window displaying combination pattern
     */
    private void redrawPattanView(SCSPattern pattern, StaticTextItem scenarioNo, VLayout patternView, Window winModal) {
        Map<TPRoot, String> tpRoots = new LinkedHashMap<>();
        Map<BPRoot, String> bpRoots = new LinkedHashMap<>();
        AtomicInteger cnt = new AtomicInteger(1);
        ListGridRecord record = listGrid.getRecord(dispRecordNum);
        String detailNo = record.getAttributeAsString("detailNo");
        String scssUuid = record.getAttributeAsString("scssUuid");

        editResourceService.getFileContent(scssUuid, projectId, new AsyncCallback<byte[]>() {

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(byte[] result) {
                CBRoot cbRoot = (CBRoot) EditorUtil.getInstance().convertToRootElement(result);
                pattern.getPatternreferences().forEach(ref -> {
                    editResourceService.getVMFile(ref.getFileId(), new AsyncCallback<VMFile>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }

                        @Override
                        public void onSuccess(VMFile result) {
                            String fileName = result.getName() + "." + result.getExtension().getValue();
                            String generationHash = cbRoot.getLogic().getFile().stream().filter(f -> f.getUuid().equals(result.getUuid())).findFirst().get().getHash();
                            genResourceService.getPartOfFileContent(projectId, ref.getFileId(), ref.getPatternId(), generationHash, new AsyncCallback<byte[]>() {
                                @Override
                                public void onFailure(Throwable caught) {
                                    SC.say(caught.getMessage());
                                }

                                @Override
                                public void onSuccess(byte[] result) {
                                    BinaryResourceImpl r = new BinaryResourceImpl();
                                    ByteArrayInputStream bi = new ByteArrayInputStream(result);
                                    try {
                                        r.load(bi, EditOptions.getDefaultLoadOptions());
                                    } catch (IOException e) {
                                        SC.warn(e.getMessage());
                                    }
                                    AbstractRootElement root = (AbstractRootElement) r.getContents().get(0);
                                    if (root instanceof TPRoot) {
                                        tpRoots.put((TPRoot) root, fileName);
                                    } else {
                                        bpRoots.put((BPRoot) root, fileName);
                                    }
                                    if (pattern.getPatternreferences().size() == cnt.getAndIncrement()) {
                                        scenarioNo.setValue("DetailNo:" + detailNo);
                                        Arrays.stream(patternView.getMembers()).forEach(v -> v.markForDestroy());
                                        tpRoots.entrySet().stream().sorted(Entry.comparingByValue()).forEach(entry -> {
                                            String fpFileName = entry.getValue();
                                            final VLayout tpView = new VLayout();
                                            tpView.setHeight(110);
                                            final DynamicForm fpForm = new DynamicForm();
                                            final StaticTextItem fpFileNameLabel = new StaticTextItem();
                                            fpFileNameLabel.setValue(fpFileName);
                                            fpFileNameLabel.setShowTitle(false);
                                            fpForm.setItems(fpFileNameLabel);
                                            final HTMLPane tableContainer = new HTMLPane();
                                            tpView.addMembers(fpForm, tableContainer);
                                            patternView.addMembers(tpView);
                                            Scheduler.get().scheduleFinally(() -> {
                                                try {
                                                    TPViewer.fillData(null, -1, (TPSetting) entry.getKey().getSettings().get(0), tableContainer);
                                                    return false;
                                                } catch (Exception e) {
                                                    return true;
                                                }
                                            });
                                        });

                                        bpRoots.entrySet().stream().sorted(Entry.comparingByValue()).forEach(entry -> {
                                            // get the Root Content in Json format
                                            String bpFileName = entry.getValue();
                                            final DynamicForm bpForm = new DynamicForm();
                                            final StaticTextItem bpFileNameLabel = new StaticTextItem();
                                            bpFileNameLabel.setValue(bpFileName);
                                            bpFileNameLabel.setShowTitle(false);
                                            bpForm.setItems(bpFileNameLabel);
                                            // all HTML Tag Id should Begin with Character not Number
                                            final HTMLPane tableContainer = new HTMLPane();
                                            tableContainer.setStyleName("bpeditorWrapper");

                                            final VLayout bpView = new VLayout();
                                            // bpView.setPosition(Positioning.RELATIVE);

                                            bpView.addMembers(bpForm, tableContainer);
                                            patternView.addMembers(bpView);

                                            Scheduler.get().scheduleFinally(() -> {
                                                try {
                                                    Element elm = Document.get().getElementById(tableContainer.getDOM().getId());
                                                    elm.setInnerHTML("<bptable :items=\"records\" :columns=\"column\"></bptable>");
                                                    BPSetting bpSetting = (BPSetting) entry.getKey().getSettings().get(0);
                                                    JSONObject jsonRoot = BPModelData.getRecordsAsJsonString(new AtomicInteger(1), 1, bpSetting);
                                                    BPEditor.updateBPTable(jsonRoot.getJavaScriptObject(), tableContainer.getDOM().getId(), true);
                                                    return false;
                                                } catch (Exception e) {
                                                    return true;
                                                }
                                            });
                                        });
                                        Arrays.stream(patternView.getMembers()).forEach(v -> v.markForRedraw());
                                    }
                                }
                            });
                        }
                    });
                });
            }

        });
    }

    /**
     * Validate the {@link SCSEditor#maxRow} input value and disable {@link SCSEditor#applyBtn} if there is a problem.
     * @return True if disabled. False if enabled.
     */
    private boolean switchApplyState() {
        if (!maxRow.validate()) {
            applyBtn.setDisabled(true);
            return true;
        } else {
            applyBtn.setDisabled(false);
            return false;
        }
    }

    /**
     * Redraw the Project Explorer and display the created Concrete-Scenario-Set file.
     * @param selectedNode TreeNode related to the directory where SCS files are stored
     * @param resource File information for concrete-scenario-set
     * @param fileId File ID of concrete-scenario-set
     */
    private void generateCallBackFile(TreeNode selectedNode, VMResource resource, Long fileId) {
        resource.setId(fileId);
        long parentId;
        if (selectedNode == null ? true : "/".equals(modelingProjectView.getTree().getPath(selectedNode))) {
            modelingProjectView.getFileTreeNodeFactory().getFileTreeNode(modelingProjectView.getTree(), modelingProjectView.getRootId(), resource);
            parentId = modelingProjectView.getRootId();
        } else {
            parentId = ((FileTreeNode) selectedNode).getResource().getId();
            modelingProjectView.getFileTreeNodeFactory().getFileTreeNode(modelingProjectView.getTree(), parentId, resource);
        }
        modelingProjectView.getFileTreeNodeFactory().refresh(modelingProjectView.getEditorTabSet(), modelingProjectView.getTree(), modelingProjectView.getTabRegs());
        modelingProjectView.getTreeGrid().sort();
        modelingProjectView.getTreeGrid().redraw();
        modelingProjectView.getTreeGrid().deselectAllRecords();
    }
}
