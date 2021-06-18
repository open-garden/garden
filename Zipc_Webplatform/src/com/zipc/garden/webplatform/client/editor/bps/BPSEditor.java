package com.zipc.garden.webplatform.client.editor.bps;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.SortSpecifier;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.BodyKeyPressEvent;
import com.smartgwt.client.widgets.grid.events.BodyKeyPressHandler;
import com.smartgwt.client.widgets.grid.events.CellContextClickEvent;
import com.smartgwt.client.widgets.grid.events.CellContextClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.grid.events.SelectionUpdatedEvent;
import com.smartgwt.client.widgets.grid.events.SelectionUpdatedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.bps.BPSEnablement;
import com.zipc.garden.model.bps.BPSInstancePseudoStateType;
import com.zipc.garden.model.bps.BPSInstanceState;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;
import com.zipc.garden.model.bps.BPSInstanceTransition;
import com.zipc.garden.model.bps.BPSOption;
import com.zipc.garden.model.bps.BPSPathCombinationOption;
import com.zipc.garden.model.bps.BPSRoot;
import com.zipc.garden.model.bps.BPSStateCombinationOption;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.webplatform.client.command.BPSEditorCommandProvider;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.EditorUtil;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.service.GenerateResourceService;
import com.zipc.garden.webplatform.client.service.GenerateResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;
import com.zipc.garden.webplatform.client.view.part.DependentFileViewPart;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.FileTreeNode;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * It is a class that setting behavior-pattern.
 */
public class BPSEditor extends Editor {

    /** Initial number of transitions */
    private final static int DEFAULT_PATH_LENGTH = 3;

    /** Asynchronous interface to get a list of ARC files */
    private final EditResourceServiceAsync editResourceService;

    /** Asynchronous interface for registering Job that generates behavior-pattern */
    private GenerateResourceServiceAsync genResourceService = GWT.create(GenerateResourceService.class);

    /** Asynchronous interface that creates BPS based on selected ARC */
    private BPSResourceServiceAsync bpsResourceService = GWT.create(BPSResourceService.class);

    /** Class that manages the commands operated on the BPS screen */
    protected final EditManager manager = EditManager.createInstance();

    /** Menu for saving the contents of BPSEditor */
    protected MenuItem saveItem = new MenuItem("Save");

    /** Overall layout of BPSEditor */
    private final VLayout layout = new VLayout();

    /** behavior-pattern generation button */
    private ButtonItem generateBtn;

    /** behavior-pattern generation button (BP file is also created) */
    private ButtonItem generateAndCreateBtn;

    /** TreeGrid displaying the contents of an ARC file */
    private TreeGrid treeGrid;

    /** Class that manages data properties of TreeGrid */
    private Tree tree;

    /** BPS editor operation commands */
    private CompoundCommand cmd = new CompoundCommand();

    /** Common class for listing ARC files */
    private DependentFileViewPart fileView;

    /** Specify the extension of the file displayed by DependentFileViewPart */
    private List<String> extensions;

    /** EMF model that summarizes the contents of BPSEditor */
    private BPSRoot root;

    /** BPSRoot at the time of saving */
    private BPSRoot savedRoot;

    /** ID of the project in which the BPS file is managed */
    private Long projectId;

    /** BPS file ID */
    private long fileId;

    /** ID of the directory where the BPS file is located */
    private long bpParentId;

    /** Layout to display execution button and execution status of Job */
    private HLayout statusLayout;

    /**
     * Flag whether Editor is redisplayed <br>
     * When re-displaying the created Editor: true <br>
     * When creating a new and displaying it for the first time: false <br>
     * Used to determine the selection state of TargetStateMachine at Editor Open
     */
    private boolean isReDrawEditor;

    /** Class that manages the contents of the project */
    private ModelingProjectView modelingProjectView;

    /** Input items for the number of State combinations (the number of steps) */
    private IntegerItem numOfTransitions;

    /** It becomes true when generate button is pressed. */
    private boolean generateFlag = false;

    /** It becomes true when generate and create button is pressed. */
    private boolean isCreateFile = false;

    /**
     * constructor
     * @param root Root model of BPS editor
     * @param modelingProjectView Class that manages the contents of the project
     * @param projectId The project ID in which this BPS is managed
     * @param fileId Information of BPS file to be displayed
     * @param editResourceService Asynchronous interface to get a list of ARC files
     */
    public BPSEditor(BPSRoot root, ModelingProjectView modelingProjectView, Long projectId, long fileId, EditResourceServiceAsync editResourceService) {
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
                bpParentId = Long.parseLong(result.toString());
            }
        });
        setSavedPosition();
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
    public boolean isChanged() {
        if (savedRoot.getRefs() != null && root.getRefs() != null) {
            if (savedRoot.getRefs().size() != root.getRefs().size()) {
                return true;
            }
        }
        for (Reference savedRef : savedRoot.getRefs()) {
            for (Reference ref : root.getRefs()) {
                if (savedRef.getRefExtension().equals(ref.getRefExtension())) {
                    if (!savedRef.getRefid().equals(ref.getRefid())) {
                        return true;
                    }
                }
            }
        }
        if (getActiveOptionValue(root) != null) {
            if (!getActiveOptionValue(root).equals(getActiveOptionValue(savedRoot))) {
                return true;
            }
        } else {
            if (getActiveOptionValue(savedRoot) != null) {
                return true;
            }
        }
        return (manager.getSavedPosition() != manager.getStackCoount());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSavedPosition() {
        savedRoot = EcoreUtil.copy(root);
        manager.setSavedPosition(manager.getStackCoount());
    }

    /**
     * Gets the refresh flag of the editor.
     * @param isReDrawEditor When re-displaying the created Editor: true <br>
     *            When creating a new and displaying it for the first time: false<br>
     */
    public void setIsReDrawEditor(boolean isReDrawEditor) {
        this.isReDrawEditor = isReDrawEditor;
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
                    genResourceService.generateBehaviorPattern(fileId, isCreateFile, new AsyncCallback<VMFile>() {
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
                                    generateCallBackFile(modelingProjectView.getFileTreeNodeFactory().findTreeNode(modelingProjectView.getTree(), bpParentId), resultFile,
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
        layout.setWidth100();
        layout.setHeight100();
        Menu menu = new Menu();
        menu.setItems(saveItem);
        layout.setContextMenu(menu);

        saveItem.setKeyTitle("Ctrl + S");

        numOfTransitions = new IntegerItem();
        numOfTransitions.setTitle("Number of Transitions");
        numOfTransitions.setEndRow(false);
        numOfTransitions.setWidth(220);
        numOfTransitions.setCanEdit(true);
        numOfTransitions.setBrowserInputType("tel");
        numOfTransitions.setRequired(true);
        IntegerRangeValidator validator = new IntegerRangeValidator();
        validator.setMin(0);
        numOfTransitions.setValidators(validator);
        numOfTransitions.setKeyPressFilter("[0-9]");

        statusLayout = EditorUtil.getInstance().createGenerationStatusLayout(fileId, projectId, genResourceService);

        final DynamicForm generateBtnForm = new DynamicForm();
        generateBtnForm.setWidth100();
        generateBtnForm.setMargin(10);
        generateBtnForm.setNumCols(5);

        generateBtnForm.setColWidths("*", 220, 150, 150, "*");
        SpacerItem spacer = new SpacerItem();
        spacer.setWidth("*");
        spacer.setEndRow(false);

        generateBtn = new ButtonItem("Generate");
        generateBtn.setStartRow(false);
        generateBtn.setEndRow(false);
        generateBtn.setAlign(Alignment.CENTER);
        generateBtn.setWidth(150);

        generateAndCreateBtn = new ButtonItem();
        generateAndCreateBtn.setTitle("Generate and Create");
        generateAndCreateBtn.setStartRow(false);
        generateAndCreateBtn.setWidth(150);

        generateBtnForm.setItems(spacer, generateBtn, generateAndCreateBtn);

        treeGrid = new TreeGrid();
        treeGrid.setCellFormatter(new CellFormatter() {
            @Override
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                BPSEnablement enablement = (BPSEnablement) record.getAttributeAsObject("Enablement");
                if (enablement instanceof BPSInstanceStateMachine) {
                    record.set_baseStyle("treeCell_BPSEditorLight " + record.get_baseStyle());
                }
                if (treeGrid.isSelected(record)) {
                    record.set_baseStyle("treeCell_BPSEditorChecked " + record.get_baseStyle());
                } else {
                    if (record.get_baseStyle().contains("treeCell_BPSEditorChecked")) {
                        record.set_baseStyle(record.get_baseStyle().replace("treeCell_BPSEditorChecked ", ""));
                    }
                }
                return (String) value;
            }
        });
        treeGrid.setWidth100();
        treeGrid.setHeight100();
        treeGrid.setFolderIcon(null);
        treeGrid.setNodeIcon(null);
        treeGrid.setClosedIconSuffix("");
        treeGrid.setCascadeSelection(true);
        treeGrid.setShowPartialSelection(true);
        treeGrid.setShowSelectedStyle(false);
        treeGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        treeGrid.setSelectionProperty("checkbox");
        treeGrid.setShowConnectors(true);
        treeGrid.setShowHeaderContextMenu(false);
        treeGrid.setShowHeaderMenuButton(false);
        treeGrid.setCanResizeFields(true);
        treeGrid.setCanReorderFields(false);
        treeGrid.setCanSort(false);
        treeGrid.setLeaveScrollbarGap(false);
        treeGrid.setData(createTreeData());
        treeGrid.setMargin(10);

        ListGridField idField = new ListGridField("id");
        idField.setHidden(true);
        ListGridField parentId = new ListGridField("parentId");
        parentId.setHidden(true);
        ListGridField checkbox = new ListGridField("checkbox");
        checkbox.setHidden(true);
        ListGridField enablement = new ListGridField("Enablement");
        enablement.setHidden(true);
        ListGridField name = new ListGridField("Name");
        ListGridField triggerField = new ListGridField("Trigger");
        ListGridField eventField = new ListGridField("Event");
        ListGridField conditionField = new ListGridField("Condition");
        ListGridField sourceField = new ListGridField("Source", "Source State");
        ListGridField targetField = new ListGridField("Target", "Target State");

        treeGrid.setFields(idField, parentId, checkbox, name, triggerField, eventField, conditionField, sourceField, targetField, enablement);

        extensions = Arrays.asList(new String[] { Extension.ARC.getValue() });
        fileView = new DependentFileViewPart("Dependent .arc file", root, projectId, 500, 300, extensions, editResourceService, false, new PostProcessHandler() {
            @Override
            public void execute() {
                if (isReDrawEditor) {
                    initLoad();
                } else {
                    setActiveOptionValue(DEFAULT_PATH_LENGTH);
                }
            }
        });
        List<FormItem> itemList = new ArrayList<FormItem>();
        for (FormItem item : fileView.getDependentFileView().getFields()) {
            itemList.add(item);
        }
        itemList.add(numOfTransitions);

        fileView.getDependentFileView().setFields(itemList.toArray(new FormItem[itemList.size()]));
        fileView.getDependentFileView().setNumCols(5);
        fileView.getDependentFileView().setColWidths("*", 220, 150, 150, "*");
        layout.addMembers(fileView.getDependentFileView(), statusLayout, generateBtnForm, treeGrid);
        bind();

        isReDrawEditor = true;

        refresh();
    }

    /**
     * Create a Tree to be set in the TreeGrid that displays the contents of the ARC file.
     * @return tree
     */
    private Tree createTreeData() {
        tree = new Tree();
        tree.setModelType(TreeModelType.PARENT);
        tree.setRootValue("");
        tree.setIdField("id");
        tree.setParentIdField("parentId");
        tree.setNameProperty("Name");
        tree.setOpenProperty("isOpen");
        tree.setData(new TreeNode[] {});
        return tree;
    }

    /**
     * Create event handlers for various functions of the BPS editor.
     */
    private void bind() {
        fileView.addSelectClickHandler(fileView.getSelect(), new PostProcessHandler() {
            @Override
            public void execute() {
                if (fileView.getSelectItem().getValue() != null) {
                    setActiveOptionValue(DEFAULT_PATH_LENGTH);
                }
            }
        });
        fileView.addReloadClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                TextItem selectItem = fileView.getSelectItem();
                if (selectItem.getValueMap() == null && selectItem.getValueMap().isEmpty()) {
                    SC.say("Please Select Dependent .arc File.");
                    return;
                }
                String fileIdStr = selectItem.getValueAsString();
                if (fileIdStr == null || fileIdStr.isEmpty()) {
                    return;
                }
                Long targetFileId = Long.valueOf(fileIdStr);

                BinaryResourceImpl re = new BinaryResourceImpl();
                re.getContents().add(root);
                final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                try {
                    re.save(outputStream, null);
                } catch (IOException e) {
                    SC.warn(e.getMessage());
                }
                bpsResourceService.getFileContent(targetFileId, outputStream.toByteArray(), new AsyncCallback<byte[]>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(byte[] result) {
                        BinaryResourceImpl r = new BinaryResourceImpl();
                        ByteArrayInputStream bi = new ByteArrayInputStream(result);
                        try {
                            r.load(bi, EditOptions.getDefaultLoadOptions());
                            root = (BPSRoot) r.getContents().get(0);
                        } catch (IOException e) {
                            SC.warn(e.getMessage());
                        }
                        saveDependentFiles();
                        refresh();
                        judgeGenerateBtnCanExecute();
                    }

                });
            }
        }, fileView.getReload());
        numOfTransitions.addKeyUpHandler(new KeyUpHandler() {

            @Override
            public void onKeyUp(KeyUpEvent event) {
                judgeGenerateBtnCanExecute();
                setActiveOptionValue(numOfTransitions.getValueAsInteger());

            }
        });

        generateBtn.addClickHandler(e -> {
            if (!fileView.getDependentFileView().validate() && !numOfTransitions.validate()) {
                return;
            }
            generateFlag = true;
            isCreateFile = false;
            saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
        });

        generateAndCreateBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!fileView.getDependentFileView().validate() && !numOfTransitions.validate()) {
                    return;
                }
                generateFlag = true;
                isCreateFile = true;
                saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
            }
        });

        treeGrid.addSelectionChangedHandler(new SelectionChangedHandler() {
            @Override
            public void onSelectionChanged(SelectionEvent event) {
                ListGridRecord record = event.getRecord();
                BPSEnablement enablement = (BPSEnablement) record.getAttributeAsObject("Enablement");
                if (enablement != null && enablement.isEnabled() != treeGrid.isSelected(record) && !tree.isFolder((TreeNode) record)) {
                    BPSEditorCommandProvider.getInstance().checkNode(enablement, treeGrid.isSelected(record), cmd);
                }
            }
        });
        treeGrid.addSelectionUpdatedHandler(new SelectionUpdatedHandler() {
            @Override
            public void onSelectionUpdated(SelectionUpdatedEvent event) {
                if (!cmd.isEmpty()) {
                    Arrays.asList(tree.getAllNodes()).forEach(node -> {
                        BPSEnablement enablement = (BPSEnablement) node.getAttributeAsObject("Enablement");
                        if (enablement != null && tree.isFolder(node) && enablement.isEnabled() != treeGrid.isSelected(node)) {
                            if (treeGrid.isSelected(node)) {
                                BPSEditorCommandProvider.getInstance().checkNode(enablement, true, cmd);
                            } else {
                                BPSEditorCommandProvider.getInstance().checkNode(enablement, false, cmd);
                            }
                        }
                    });
                    manager.execute(cmd.unwrap());
                    cmd = new CompoundCommand();
                    refresh();
                }
            }
        });
        treeGrid.addCellContextClickHandler(new CellContextClickHandler() {
            @Override
            public void onCellContextClick(CellContextClickEvent event) {
                event.cancel();
                BPSEnablement enablement = (BPSEnablement) event.getRecord().getAttributeAsObject("Enablement");
                if (enablement instanceof BPSInstanceStateMachine) {
                    openPrioritySettingDialog();
                }
            }
        });

        layout.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.isCtrlKeyDown() && "Z".equals(event.getKeyName())) {
                    manager.undo();
                    refresh();
                }
                if (event.isCtrlKeyDown() && "Y".equals(event.getKeyName())) {
                    manager.redo();
                    refresh();
                }
                if (event.isCtrlKeyDown() && "S".equals(event.getKeyName())) {
                    event.cancel();
                    saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
                }
            }
        });
    }

    /**
     * The number of combinations of states is reflected in the EMF model.
     * @param value Input value of the number of state combinations (number of steps)
     */
    private void setActiveOptionValue(Object value) {
        BPSOption option = root.getActiveOption();
        if (option instanceof BPSStateCombinationOption) {
            BPSStateCombinationOption bpsOption = (BPSStateCombinationOption) option;
            // TODO: Not yet installed
        } else if (option instanceof BPSPathCombinationOption) {
            BPSPathCombinationOption bpsOption = (BPSPathCombinationOption) option;
            int intVal = 0;
            if (null != value && value instanceof Integer) {
                intVal = (int) value;
            }
            bpsOption.setPathLength(intVal + 1);
            numOfTransitions.setValue(intVal);
        }
    }

    /**
     * Gets the number of state combinations set in the EMF model.
     * @param root BPSRoot Model
     * @return Number of state combinations
     */
    private Object getActiveOptionValue(BPSRoot root) {
        Object ret = null;
        BPSOption option = root.getActiveOption();
        if (option instanceof BPSStateCombinationOption) {
            BPSStateCombinationOption bpsOption = (BPSStateCombinationOption) option;
            // TODO: Not yet installed
        } else if (option instanceof BPSPathCombinationOption) {
            BPSPathCombinationOption bpsOption = (BPSPathCombinationOption) option;
            ret = bpsOption.getPathLength() - 1;
        }
        return ret;
    }

    /**
     * The project explorer is redrawn. <br>
     * (The .bp file created from the "Generate and Create" button will be displayed in Explorer)
     * @param selectedNode TreeNode of the directory where the BPS file is located
     * @param resource Class with information of created BP file
     * @param fileId ID of the created BP file
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
     * The number of registered transitions is displayed.
     */
    private void initLoad() {

        if (root.getRefs().size() > 0) {
            Object value = getActiveOptionValue(root);
            setActiveOptionValue(value);
            judgeGenerateBtnCanExecute();
            isReDrawEditor = false;
        }

    }

    /**
     * If you make a mistake in entering the Number of Transitions, the Generate button will be disabled.
     */
    private void judgeGenerateBtnCanExecute() {
        boolean disable = true;
        if (numOfTransitions.validate()) {
            disable = false;
        }
        generateBtn.setDisabled(disable);
        generateAndCreateBtn.setDisabled(disable);
    }

    /**
     * The dependent file inputs are saved.
     */
    private void saveDependentFiles() {
        if (!fileView.getDependentFileView().validate()) {
            return;
        }
        saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
    }

    /**
     * BPS information saved in the model is reflected on the layout.
     */
    protected void refresh() {
        // インスタンス情報のTreeView を 更新する
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        tree.removeChildren(tree.getRoot());
        int idx = 1;
        if (root.getInstanceArc() != null) {
            for (BPSInstanceStateMachine bpsStm : root.getInstanceArc().getStateMachines().stream().sorted((o1, o2) -> o1.getEvalPriority() - o2.getEvalPriority())
                    .collect(Collectors.toList())) {
                String orgUuid = bpsStm.getOriginalUuid() + "_" + idx++;
                treeNodes.add(new BPSEditorTreeNode(orgUuid, "", bpsStm.getOriginalName(), bpsStm, bpsStm.getStates().isEmpty() ? bpsStm.isEnabled() : false, false));

                if (bpsStm.getInitialState() != null && BPSInstancePseudoStateType.INITIAL == bpsStm.getInitialState().getType()) {
                    Optional<BPSInstanceTransition> initialNode = bpsStm.getInitialState().getTransitions().stream().findFirst();
                    if (initialNode.isPresent()) {
                        BPSInstanceTransition bpsTransition = initialNode.get();
                        treeNodes.add(new BPSEditorTreeNode(orgUuid + "_BPSInstancePseudoStateType_INITIAL", orgUuid, "\u26AB ->", bpsTransition.getTrigger(),
                                bpsTransition.getEvent(), bpsTransition.getCondition(), bpsTransition.getSource().getOriginalName(), bpsTransition.getTarget().getOriginalName(),
                                bpsTransition, bpsStm.isEnabled(), false, true, true));
                    }
                }

                BPSInstanceState bpsState;
                for (int i = 0; i < bpsStm.getStates().size(); i++) {
                    bpsState = bpsStm.getStates().get(i);
                    if (BPSInstancePseudoStateType.NONE == bpsState.getType()) {
                        for (BPSInstanceTransition bpsTransition : bpsState.getTransitions().stream().sorted((t1, t2) -> t1.getPriority() - t2.getPriority())
                                .collect(Collectors.toList())) {
                            String bpsTransitionName = createBpsTransitionLabelContents(bpsTransition);
                            treeNodes.add(new BPSEditorTreeNode(orgUuid + "_" + bpsTransitionName, orgUuid, bpsTransitionName, bpsTransition.getTrigger(), bpsTransition.getEvent(),
                                    bpsTransition.getCondition(), bpsTransition.getSource().getOriginalName(), bpsTransition.getTarget().getOriginalName(), bpsTransition,
                                    bpsTransition.isEnabled(), false, true, false));
                        }
                    }
                }
            }
        }
        for (TreeNode node : treeNodes) {
            tree.add(node);
        }
        for (TreeNode node : treeNodes) {
            BPSEnablement enablement = (BPSEnablement) node.getAttributeAsObject("Enablement");
            if ((enablement instanceof BPSInstanceStateMachine || enablement instanceof BPSInstanceState) && enablement.isEnabled()) {
                tree.openFolder(node);
            }
        }
        Scheduler.get().scheduleDeferred(() -> {
            treeGrid.redraw();
        });
    }

    /**
     * Launches a screen where you can specify the priority order of FSMState.
     */
    protected void openPrioritySettingDialog() {
        final Window winModal = new Window();
        winModal.setTitle("Priority Setting");
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

        ListGrid bpsStmList = new ListGrid();
        bpsStmList.setBackgroundColor("white");
        bpsStmList.setWidth100();
        bpsStmList.setHeight100();
        bpsStmList.setShowHeaderContextMenu(false);
        bpsStmList.setShowHeaderMenuButton(false);
        bpsStmList.setCanResizeFields(false);
        bpsStmList.setCanReorderFields(false);
        bpsStmList.setLeaveScrollbarGap(false);
        bpsStmList.setCanReorderRecords(true);
        bpsStmList.setMargin(5);

        ListGridField priorityField = new ListGridField("Priority", "");
        priorityField.setHidden(true);
        ListGridField stateName = new ListGridField("Name", "");
        stateName.setType(ListGridFieldType.TEXT);
        ListGridField stateField = new ListGridField("Enablement", "");
        stateField.setHidden(true);
        bpsStmList.setFields(priorityField, stateName, stateField);
        bpsStmList.setSort(new SortSpecifier("Priority", SortDirection.ASCENDING));

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

        okBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                int priority = 1;
                for (ListGridRecord record : bpsStmList.getRecords()) {
                    BPSInstanceStateMachine bpsStm = (BPSInstanceStateMachine) record.getAttributeAsObject("Enablement");
                    if (bpsStm.getEvalPriority() == priority) {
                        priority++;
                        continue;
                    } else {
                        BPSEditorCommandProvider.getInstance().setEvalPriority(bpsStm, priority++, cmd);
                    }
                }
                if (!cmd.isEmpty()) {
                    manager.execute(cmd.unwrap());
                    cmd = new CompoundCommand();
                    refresh();
                }
                winModal.markForDestroy();
            }
        });

        cancelBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                winModal.markForDestroy();
            }
        });

        bpsStmList.addBodyKeyPressHandler(new BodyKeyPressHandler() {
            @Override
            public void onBodyKeyPress(BodyKeyPressEvent event) {
                if (KeyNames.ENTER.equals(EventHandler.getKey())) {
                    okBtn.fireEvent(new com.smartgwt.client.widgets.events.ClickEvent(okBtn.getJsObj()));
                }
            }
        });

        formlayout.addMembers(bpsStmList);
        winModal.addMember(formlayout);

        createBpsStmList(bpsStmList);
        winModal.show();
        winModal.getFooter().addMember(hlayout, 0);

        winModal.addResizedHandler(new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent event) {
                formlayout.setWidth100();
            }
        });
    }

    /**
     * Display the priority of FSMState in ListGrid.
     * @param listGrid ListGrid displaying the priority of FSMState
     */
    private void createBpsStmList(ListGrid listGrid) {
        if (root.getInstanceArc() != null) {
            for (BPSInstanceStateMachine bpsStm : root.getInstanceArc().getStateMachines().stream().sorted((o1, o2) -> o1.getEvalPriority() - o2.getEvalPriority())
                    .collect(Collectors.toList())) {
                listGrid.addData(new BPSEditorListGridRecord(bpsStm.getOriginalName(), bpsStm.getEvalPriority(), bpsStm));
            }
        }
        listGrid.sort();
        Scheduler.get().scheduleDeferred(() -> {
            listGrid.selectSingleRecord(0);
            listGrid.focus();
        });
    }

    /**
     * Create the transition line label content.
     * @param bpsTransition Class with transition line information
     * @return Contents of created transition line label
     */
    private String createBpsTransitionLabelContents(BPSInstanceTransition bpsTransition) {
        StringBuilder contents = new StringBuilder();
        contents.append('[').append(bpsTransition.getSource().getOriginalName()).append(']').append(": ");
        if (bpsTransition.getPriority() > 0) {
            contents.append(bpsTransition.getPriority()).append(".");
        }
        if (bpsTransition.getEvent() != null && !"".equals(bpsTransition.getEvent())) {
            contents.append(bpsTransition.getEvent());
        }
        if (bpsTransition.getCondition() != null && !"".equals(bpsTransition.getCondition())) {
            contents.append("[").append(bpsTransition.getCondition()).append("]");
        }
        if (bpsTransition.getAction() != null && !"".equals(bpsTransition.getAction())) {
            contents.append("/").append(bpsTransition.getAction());
        }

        return contents.toString();
    }

    /**
     * TreeNode that displays the contents of the read ARC
     */
    class BPSEditorTreeNode extends TreeNode {
        public BPSEditorTreeNode(String id, String parentId, String name, BPSEnablement element, boolean isCheck, boolean isOpen) {
            this(id, parentId, name, null, null, null, null, null, element, isCheck, isOpen, true, false);
        }

        public BPSEditorTreeNode(String id, String parentId, String name, String trigger, String event, String condition, String source, String target, BPSEnablement element,
                boolean isCheck, boolean isOpen, boolean enabled, boolean isHidden) {
            setAttribute("id", id);
            setAttribute("parentId", parentId);
            setAttribute("Name", name);
            setAttribute("Trigger", trigger);
            setAttribute("Event", event);
            setAttribute("Condition", condition);
            setAttribute("Source", source);
            setAttribute("Target", target);
            setAttribute("Enablement", element);
            setAttribute("checkbox", isCheck);
            setAttribute("isOpen", isOpen);
            setAttribute("enabled", enabled);
            if (isHidden) {
                set_baseStyle("treeCell_BPSEditor treeCell_BPSEditorHidden");
            } else {
                set_baseStyle("treeCell_BPSEditor");
            }
        }
    }

    /**
     * ListGridRecord used in the screen for specifying the priority of FSMState
     */
    class BPSEditorListGridRecord extends ListGridRecord {
        public BPSEditorListGridRecord(String name, int priority, BPSEnablement element) {
            setAttribute("Name", name);
            setAttribute("Priority", priority);
            setAttribute("Enablement", element);
        }
    }

}
