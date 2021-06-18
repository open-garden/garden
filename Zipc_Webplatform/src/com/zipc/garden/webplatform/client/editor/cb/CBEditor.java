package com.zipc.garden.webplatform.client.editor.cb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.SortSpecifier;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.events.ShowContextMenuEvent;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.BodyKeyPressEvent;
import com.smartgwt.client.widgets.grid.events.BodyKeyPressHandler;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.cb.CBFactory;
import com.zipc.garden.model.cb.CBFile;
import com.zipc.garden.model.cb.CBLogic;
import com.zipc.garden.model.cb.CBRoot;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.webplatform.client.command.CBEditorCommandProvider;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.EditorUtil;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.service.GenerateResourceService;
import com.zipc.garden.webplatform.client.service.GenerateResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.FileTreeNode;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * It is an editor that creates the scenarioset-setting model
 */
public class CBEditor extends Editor {

    /** Manages the operation commands of the scenarioset-setting editor. */
    private final EditManager manager = EditManager.createInstance();

    /** Context menu for saving the SCSS editor */
    private MenuItem saveItem = new MenuItem("Save");

    /** Root model of SCSS editor. */
    private CBRoot root;

    /** Class that manages the contents of the project */
    private ModelingProjectView modelingProjectView;

    /** ID of the project in which the SCSS file is managed */
    private long projectId;

    /** Asynchronous interface for saving and retrieving SCSS contents */
    private EditResourceServiceAsync editResourceService;

    /** Asynchronous interface for registering Job that generates scenarioset */
    private GenerateResourceServiceAsync genResourceService = GWT.create(GenerateResourceService.class);

    /** Overall layout of SCSSEditor */
    private final Layout vLayout = new VLayout();

    /** ListGrid that displays the file list */
    private final ListGrid listGrid = new ListGrid();

    /** MAP that associates Record added to ListGrid with EMF model */
    private final Map<ListGridRecord, CBFile> cbFiles = new HashMap<>();

    /** SCSS file Id */
    private long fileId;

    /** SCSS Directory ID */
    private long cbParentId;

    /** It becomes true when generate button is pressed. */
    private boolean generateFlag = false;

    /** It becomes true when generate and create button is pressed. */
    private boolean isCreateFile = false;

    /** Layout to display execution button and execution status of Job */
    private HLayout statusLayout;

    /**
     * constructor
     * @param root Root model of SCSS editor
     * @param modelingProjectView Class that manages the contents of the project
     * @param projectId The project ID in which this SCSS is managed
     * @param fileId Information of SCSS file to be displayed
     * @param editResourceService Asynchronous interface for saving and retrieving SCSS contents
     */
    public CBEditor(CBRoot root, ModelingProjectView modelingProjectView, long projectId, long fileId, EditResourceServiceAsync editResourceService) {
        this.root = root;
        this.modelingProjectView = modelingProjectView;
        this.projectId = projectId;
        this.fileId = fileId;
        this.editResourceService = editResourceService;
        this.editResourceService.getDirId(fileId, new AsyncCallback<Long>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(Long result) {
                cbParentId = Long.parseLong(result.toString());
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditManager getEditManager() {
        return manager;
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
    public AbstractRootElement getRoot() {
        return root;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChanged() {
        return manager.getSavedPosition() != manager.getStackCoount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSavedPosition() {
        manager.setSavedPosition(manager.getStackCoount());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Layout getLayout() {
        return vLayout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(ModelingProjectView modelingProjectView, Editor editor, Tab tab, PostProcessHandler handler) {
        super.save(modelingProjectView, editor, tab, new PostProcessHandler() {
            @Override
            public void execute() {
                if (handler != null) {
                    handler.execute();
                }
                if (generateFlag) {
                    genResourceService.generateScenarioSet(fileId, isCreateFile, new AsyncCallback<VMFile>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }

                        @Override
                        public void onSuccess(VMFile resultFile) {
                            if (isCreateFile) {
                                if (null == resultFile) {
                                    SC.warn("generate fail");
                                } else {
                                    String fileFullName = resultFile.getName() + "." + resultFile.getExtensionStr();
                                    SC.say(fileFullName + " is generated.\nPlease wait a moment as the process is in progress.");
                                    generateCallBackFile(modelingProjectView.getFileTreeNodeFactory().findTreeNode(modelingProjectView.getTree(), cbParentId), resultFile,
                                            resultFile.getId());
                                }
                            } else {
                                SC.say("Please wait a moment as the process is in progress.");
                            }
                            EditorUtil.getInstance().setGenerationStatus(fileId, projectId, genResourceService, statusLayout);
                        }

                    });
                    generateFlag = false;
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public void create() {
        if (root.getLogic() == null) {
            CBLogic logic = CBFactory.eINSTANCE.createCBLogic();
            logic.setType(CBFactory.eINSTANCE.createCBAllCombination());
            root.setLogic(logic);
            root.getRefs().forEach(ref -> {
                CBFile cbFile = CBFactory.eINSTANCE.createCBFile();
                cbFile.setUuid(ref.getRefid());
                logic.getFile().add(cbFile);
            });
            Scheduler.get().scheduleDeferred(() -> {
                saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
            });
        }

        vLayout.setWidth100();
        vLayout.setHeight100();
        vLayout.setOverflow(Overflow.HIDDEN);
        vLayout.draw();
        statusLayout = EditorUtil.getInstance().createGenerationStatusLayout(fileId, projectId, genResourceService);
        vLayout.addMember(statusLayout);
        createButtonArea();

        createListGrid();
        refresh();
        keyPressEvent();
        rightClickEvent();
    }

    /**
     * SCSS information saved in the model is reflected on the canvas.
     */
    protected void refresh() {
        AtomicInteger idx = new AtomicInteger(1);

        // Update Record based on Model
        root.getLogic().getFile().forEach(file -> {
            editResourceService.getVMFile(file.getUuid(), projectId, new AsyncCallback<VMFile>() {
                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }

                @Override
                public void onSuccess(VMFile result) {
                    Optional<Entry<ListGridRecord, CBFile>> optCBFile = cbFiles.entrySet().stream().filter(entry -> {
                        return entry.getValue().equals(file);
                    }).findFirst();
                    ListGridRecord record;
                    if (optCBFile.isPresent()) {
                        record = optCBFile.get().getKey();
                    } else {
                        record = new ListGridRecord();
                        cbFiles.put(record, file);
                        listGrid.addData(record);
                    }
                    record.setAttribute("fileName", result == null ? "file not found" : result.getName() + "." + result.getExtensionStr());
                    record.setAttribute("pattern", file.getPattern());

                    if (root.getLogic().getFile().size() == idx.getAndIncrement()) {
                        // To be deleted if it exists only in the record
                        List<ListGridRecord> delRecord = new ArrayList<>();
                        cbFiles.forEach((listGridRecord, cbFile) -> {
                            Optional<CBFile> optCbFile = root.getLogic().getFile().stream().filter(modelFile -> cbFile.equals(modelFile)).findFirst();
                            if (!optCbFile.isPresent()) {
                                delRecord.add(listGridRecord);
                            }
                        });
                        delRecord.forEach(dr -> {
                            listGrid.removeData(dr);
                            cbFiles.remove(dr);
                        });
                        SortSpecifier sortSpecifier = new SortSpecifier("fileName", SortDirection.ASCENDING);
                        SortSpecifier[] sortSpecifiers = { sortSpecifier };
                        listGrid.setSort(sortSpecifiers);
                    }
                }
            });
        });

        if (root.getLogic().getFile().size() == 0) {
            Arrays.asList(listGrid.getRecords()).forEach(listGridRecord -> listGrid.removeData(listGridRecord));
            cbFiles.clear();
        }
    }

    /**
     * Create a form that displays the SCS generation button and the add file button.
     */
    private void createButtonArea() {

        final DynamicForm generateBtnForm = new DynamicForm();
        generateBtnForm.setWidth100();
        generateBtnForm.setMargin(10);
        generateBtnForm.setNumCols(5);

        generateBtnForm.setColWidths("*", 220, 150, 150, "*");
        SpacerItem spacer = new SpacerItem();
        spacer.setWidth("*");
        spacer.setEndRow(false);

        ButtonItem generateAndCreateBtn = new ButtonItem();
        generateAndCreateBtn.setTitle("Generate and Create");
        generateAndCreateBtn.setStartRow(false);
        generateAndCreateBtn.setWidth(150);

        ButtonItem generateBtn = new ButtonItem("Generate");
        generateBtn.setStartRow(false);
        generateBtn.setEndRow(false);
        generateBtn.setAlign(Alignment.CENTER);
        generateBtn.setWidth(150);

        generateBtnForm.setItems(spacer, generateBtn, generateAndCreateBtn);

        DynamicForm btnAreaForm = new DynamicForm();
        btnAreaForm.setWidth100();
        ButtonItem addFileBtn = new ButtonItem("AddFile");
        addFileBtn.setWidth(80);
        addFileBtn.setAlign(Alignment.LEFT);
        addFileBtn.setEndRow(false);
        addFileBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onClickAddFile();
            }
        });

        generateBtn.addClickHandler(e -> {
            generate(false);
        });

        generateAndCreateBtn.addClickHandler(e -> {
            generate(true);
        });
        Scheduler.get().scheduleDeferred(() -> btnAreaForm.setItems(addFileBtn));
        vLayout.addMembers(generateBtnForm, btnAreaForm);
    }

    /**
     * Get the latest FPS and BPS hashcodes and update the model before generating the SCS.<br>
     * Running this process creates a job for SCS generation.
     * @param isCreateBtn True to create SCS file
     */
    private void generate(boolean isCreateBtn) {
        if (cbFiles.isEmpty()) {
            return;
        }
        AtomicInteger i = new AtomicInteger(0);
        root.getLogic().getFile().forEach(f -> {
            editResourceService.getFileContent(f.getUuid(), projectId, new AsyncCallback<byte[]>() {
                @Override
                public void onSuccess(byte[] result) {
                    BinaryResourceImpl r = new BinaryResourceImpl();
                    ByteArrayInputStream bi = new ByteArrayInputStream(result);
                    AbstractRootElement root = null;
                    try {
                        r.load(bi, EditOptions.getDefaultLoadOptions());
                        root = (AbstractRootElement) r.getContents().get(0);
                    } catch (IOException e) {
                        SC.warn(e.getMessage());
                    }

                    f.setAbstractRoot(root);
                    editResourceService.getHash(f.getUuid(), projectId, new AsyncCallback<String>() {

                        @Override
                        public void onSuccess(String result) {
                            f.setHash(result);
                            if (CBEditor.this.root.getLogic().getFile().size() == i.incrementAndGet()) {
                                generateFlag = true;
                                isCreateFile = isCreateBtn;
                                saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
                            }
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }
                    });

                }

                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }
            });
        });
    }

    /**
     * Create a ListGrid that displays the added FPS and BPS files
     */
    private void createListGrid() {
        listGrid.setWidth100();
        listGrid.setHeight100();
        listGrid.setCellHeight(30);
        listGrid.setCanSort(true);
        listGrid.setEditByCell(true);
        listGrid.setEditEvent(ListGridEditEvent.DOUBLECLICK);
        listGrid.setLeaveScrollbarGap(false);
        listGrid.setShowHeaderContextMenu(false);
        listGrid.setWrapCells(true);

        ListGridField fileNameField = new ListGridField("fileName");
        fileNameField.setWidth("50%");
        fileNameField.setCanEdit(false);

        ListGridField patternField = new ListGridField("pattern");
        patternField.setWidth("50%");
        patternField.setCanEdit(true);
        patternField.addCellSavedHandler(new CellSavedHandler() {
            @Override
            public void onCellSaved(CellSavedEvent event) {
                String newValue = event.getNewValue() == null ? null : event.getNewValue().toString();
                CompoundCommand cmd = CBEditorCommandProvider.getInstance().setPattern(cbFiles.get(event.getRecord()), newValue);
                manager.execute(cmd.unwrap());
            }
        });

        listGrid.setFields(new ListGridField[] { fileNameField, patternField });
        vLayout.addMember(listGrid);
    }

    /**
     * Create a Key Press Event.
     */
    private void keyPressEvent() {
        vLayout.addKeyPressHandler(CBEditorKeyEventHolder.createShortcutKeyEvent(CBEditor.this));
    }

    /**
     * Create a Right Click Event.
     */
    private void rightClickEvent() {
        listGrid.addShowContextMenuHandler(new ShowContextMenuHandler() {
            @Override
            public void onShowContextMenu(ShowContextMenuEvent event) {
                listGrid.setContextMenu(CBMenuItemCreator.createRightClickMenuItem(CBEditor.this, event.getX(), event.getY()));
            }
        });
    }

    /**
     * Delete the record (BPS or TPS) added to ListGird.<br>
     * Only the selected records will be deleted.
     */
    protected void onDelete() {
        if (listGrid.getSelectedRecords().length > 0) {
            List<CBFile> removeCBFiles = new ArrayList<>();
            for (Record record : listGrid.getSelectedRecords()) {
                removeCBFiles.add(cbFiles.get(record));
                listGrid.removeData(record);
                cbFiles.remove(record);
            }
            CompoundCommand cmd = CBEditorCommandProvider.getInstance().removeFiles(root.getLogic(), removeCBFiles);
            manager.execute(cmd.unwrap());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSaveHandler(ClickHandler handler) {
        saveItem.addClickHandler(handler);
    }

    /**
     * The file addition screen is displayed.<br>
     * The selected TPS or BPS file is added to ListGrid.
     */
    private void onClickAddFile() {
        final Window winModal = new Window();
        winModal.setTitle("Add File");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.setShowFooter(true);
        winModal.setShowResizer(true);
        winModal.setKeepInParentRect(true);
        winModal.setAutoCenter(true);
        winModal.setBackgroundColor("white");
        winModal.setCanDragResize(true);
        winModal.setWidth(600);
        winModal.setHeight(400);
        winModal.addCloseClickHandler(e -> winModal.markForDestroy());

        VLayout formlayout = new VLayout();
        formlayout.setWidth100();
        formlayout.setHeight100();

        DynamicForm searchForm = new DynamicForm();
        searchForm.setNumCols(2);
        searchForm.setBackgroundColor("white");
        searchForm.setMargin(3);
        TextItem searchItem = new TextItem("Search");
        searchForm.setFields(searchItem);

        ListGrid dependentFile = new ListGrid();
        dependentFile.setBackgroundColor("white");
        dependentFile.setWidth100();
        dependentFile.setHeight100();
        dependentFile.setShowHeaderContextMenu(false);
        dependentFile.setShowHeaderMenuButton(false);
        dependentFile.setCanResizeFields(true);
        dependentFile.setCanReorderFields(false);
        dependentFile.setLeaveScrollbarGap(false);
        dependentFile.setMargin(5);

        ListGridField idField = new ListGridField("id", "");
        idField.setHidden(true);
        ListGridField fileNameField = new ListGridField("fileName", "");
        fileNameField.setType(ListGridFieldType.TEXT);
        ListGridField fullPathField = new ListGridField("fullPath", "");
        fullPathField.setType(ListGridFieldType.TEXT);
        dependentFile.setFields(idField, fileNameField, fullPathField);

        HLayout hlayout = new HLayout();
        hlayout.setHeight(30);
        hlayout.setWidth100();
        hlayout.setLayoutLeftMargin(20);
        IButton okBtn = new IButton("OK");
        okBtn.setHeight100();
        okBtn.setWidth100();
        okBtn.setMargin(5);
        IButton cancelBtn = new IButton("CANCEL");
        cancelBtn.setHeight100();
        cancelBtn.setWidth100();
        cancelBtn.setMargin(5);
        LayoutSpacer hspacer = new LayoutSpacer(3, "100%");
        hspacer.setBackgroundColor("white");

        hlayout.addMembers(hspacer, okBtn, hspacer, cancelBtn, hspacer);

        dependentFile.addCellDoubleClickHandler(new CellDoubleClickHandler() {
            @Override
            public void onCellDoubleClick(CellDoubleClickEvent event) {
                dependentFile.selectRecord(event.getRecord());
                okBtn.fireEvent(new com.smartgwt.client.widgets.events.ClickEvent(okBtn.getJsObj()));
            }
        });

        searchItem.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent event) {
                String searchVal = event.getValue() != null ? event.getValue().toString() : "";
                Criteria criteria = new Criteria();
                criteria.addCriteria("fullPath", searchVal);
                dependentFile.filterData(criteria);
            }
        });

        okBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                if (dependentFile.getSelectedRecord() == null) {
                    event.cancel();
                    return;
                }
                List<CBFile> newFiles = new ArrayList<>();
                for (ListGridRecord record : dependentFile.getSelectedRecords()) {
                    String uuid = record.getAttributeAsString("uuid");

                    CBFile file = CBFactory.eINSTANCE.createCBFile();
                    file.setUuid(uuid);
                    file.setPattern("");

                    newFiles.add(file);
                }
                CompoundCommand cmd = CBEditorCommandProvider.getInstance().addFiles(root, newFiles);
                manager.execute(cmd.unwrap());
                refresh();

                winModal.markForDestroy();
            }
        });

        cancelBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                winModal.markForDestroy();
            }
        });

        dependentFile.addBodyKeyPressHandler(new BodyKeyPressHandler() {
            @Override
            public void onBodyKeyPress(BodyKeyPressEvent event) {
                if (KeyNames.ENTER.equals(EventHandler.getKey())) {
                    okBtn.fireEvent(new com.smartgwt.client.widgets.events.ClickEvent(okBtn.getJsObj()));
                }
            }
        });

        formlayout.addMembers(searchForm, dependentFile);
        winModal.addMember(formlayout);

        List<String> extensions = Arrays.asList(new String[] { Extension.FPS.getValue(), Extension.BPS.getValue() });
        createListGridData_AddFile(editResourceService, projectId, extensions, dependentFile);
        winModal.show();
        winModal.getFooter().addMember(hlayout, 0);
        dependentFile.getHeader().setWidth100();

        winModal.addResizedHandler(new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent event) {
                formlayout.setWidth100();
            }
        });
    }

    /**
     * The project explorer is redrawn. <br>
     * (The .scs file created from the "Generate and Create" button will be displayed in Explorer)
     * @param selectedNode TreeNode of the directory where the SCSS file is located
     * @param resource Class with information of created SCS file
     * @param fileId ID of the created SCS file
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

    /**
     * Create ListGrid to be displayed on the file addition screen.
     * @param editResourceService
     * @param projectId
     * @param extensions
     * @param dependentFile
     */
    private void createListGridData_AddFile(EditResourceServiceAsync editResourceService, long projectId, List<String> extensions, ListGrid dependentFile) {
        editResourceService.getRefTargetFiles(projectId, extensions, new AsyncCallback<List<VMFile>>() {

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(List<VMFile> result) {

                ListGridRecord[] retData = new ListGridRecord[result.size()];
                for (int i = 0; i < result.size(); i++) {
                    retData[i] = new ListGridRecord();
                    retData[i].setAttribute("fullPath", result.get(i).getFullPath());
                    retData[i].setAttribute("id", result.get(i).getId());
                    retData[i].setAttribute("fileName", result.get(i).getName() + "." + result.get(i).getExtensionStr());
                    retData[i].setAttribute("uuid", result.get(i).getUuid());
                }

                DataSource ds = DependentFileDS.getInstance();
                ds.setTestData(retData);
                dependentFile.setDataSource(ds);
                dependentFile.sort();
                dependentFile.fetchData();
                Scheduler.get().scheduleDeferred(() -> {
                    dependentFile.selectSingleRecord(0);
                    dependentFile.focus();
                });
            }
        });
    }

    /**
     * Data source of the file list displayed on the file addition screen
     */
    private static class DependentFileDS extends DataSource {

        private static DependentFileDS instance = null;

        public static DependentFileDS getInstance() {
            if (instance == null) {
                instance = new DependentFileDS("dependentFileDS");
            }
            return instance;
        }

        public DependentFileDS(String id) {
            setID(id);

            DataSourceTextField dsId = new DataSourceTextField("id", "ID");
            dsId.setHidden(true);
            DataSourceTextField dsFileName = new DataSourceTextField("fileName", "Name");
            DataSourceTextField dsFullPath = new DataSourceTextField("fullPath", "FullPath");
            setFields(dsId, dsFileName, dsFullPath);

            setClientOnly(true);
        }
    }
}
