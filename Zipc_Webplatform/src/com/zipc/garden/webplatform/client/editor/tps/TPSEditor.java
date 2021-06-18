package com.zipc.garden.webplatform.client.editor.tps;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.ButtonClickEvent;
import com.smartgwt.client.widgets.events.ButtonClickHandler;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellContextClickEvent;
import com.smartgwt.client.widgets.grid.events.CellContextClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.grid.events.SelectionUpdatedEvent;
import com.smartgwt.client.widgets.grid.events.SelectionUpdatedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCNodeState;
import com.zipc.garden.model.tc.TCProperty;
import com.zipc.garden.model.tps.TPSRoot;
import com.zipc.garden.webplatform.client.command.TCEditorCommandProvider;
import com.zipc.garden.webplatform.client.command.TPSEditorCommandProvider;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.EditorUtil;
import com.zipc.garden.webplatform.client.editor.tc.TCEditorTreeNode;
import com.zipc.garden.webplatform.client.editor.tc.TCModelParser;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.service.GenerateResourceService;
import com.zipc.garden.webplatform.client.service.GenerateResourceServiceAsync;
import com.zipc.garden.webplatform.client.service.ProjectService;
import com.zipc.garden.webplatform.client.service.ProjectServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;
import com.zipc.garden.webplatform.client.view.part.DependentFileViewPart;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.FileTreeNode;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * It is a class that setting feature-pattern-setting.
 */
public class TPSEditor extends Editor {

    /** Class that manages the commands operated on the FPS screen */
    private final EditManager manager = EditManager.createInstance();

    /** ID of the project in which the FPS file is managed */
    private long projectId;

    /** FPS file ID */
    private long fileId;

    /** EMF model that summarizes the contents of FPSEditor */
    private TPSRoot root;

    /** FPSRoot at the time of saving */
    private TPSRoot oldRoot;

    /**
     * Map holding unique TCNode information.<br>
     * key : Unique path <br>
     * value : TCNode managed by TCRoot or TPSRoot
     */
    private Map<String, TCNode> parseRoot = new HashMap<String, TCNode>();

    /** Overall layout of FPSEditor */
    private final Layout layout = new VLayout();

    /** Menu for saving the contents of FPSEditor */
    protected MenuItem saveItem = new MenuItem("Save");

    /** Common class for listing FM file and TC file */
    private DependentFileViewPart fileViewFmTc;

    /** Common class for listing FMC file */
    private DependentFileViewPart fileViewFmc;

    /** Class that manages the calculation method for risk importance created by the feature model */
    private TPSPatternElementForm patternElementForm;

    /** feature-pattern generation button */
    private ButtonItem generate;

    /** feature-pattern generation button (FP file is also created) */
    private ButtonItem generateAndCreate;

    /** TreeGrid displaying the contents of an FM file or TC file */
    private TreeGrid treeGrid;

    /** Class that manages data properties of TreeGrid */
    private Tree tree;

    /** TreeGrid data change flag */
    private boolean changedFlg = false;

    /**
     * Handler for post-processing. <br>
     * At the time of refresh processing, the file name being read is reflected in TreeGrid.
     */
    private PostProcessHandler handler;

    /** FPS editor operation commands */
    private CompoundCommand cmd = new CompoundCommand();

    /** Asynchronous interface to get a list of FM and TC files or a list of FMC files */
    private EditResourceServiceAsync editResourceService;

    /** Asynchronous interface for registering Job that generates feature-pattern */
    private GenerateResourceServiceAsync genResourceService = GWT.create(GenerateResourceService.class);

    /** Asynchronous interface that creates FPS based on selected FM or TC */
    private TPSResourceServiceAsync tpsResourceService = GWT.create(TPSResourceService.class);

    /** Asynchronous interface for acquiring pattern element information registered in DB */
    private ProjectServiceAsync projectService = GWT.create(ProjectService.class);

    /** ID of the directory where the TPS file is located */
    private long tpParentId;

    /** Class that manages the contents of the project */
    private ModelingProjectView modelingProjectView;

    /** Layout to display execution button and execution status of Job */
    private HLayout statusLayout;

    /** It becomes true when generate button is pressed. */
    private boolean generateFlag = false;

    /** It becomes true when generate and create button is pressed. */
    private boolean isCreateFile = false;

    /**
     * constructor
     * @param root {@link #root}
     * @param modelingProjectView {@link #modelingProjectView}
     * @param projectId {@link #projectId}
     * @param fileId {@link #fileId}
     * @param editResourceService {@link #editResourceService}
     */
    public TPSEditor(TPSRoot root, ModelingProjectView modelingProjectView, long projectId, long fileId, EditResourceServiceAsync editResourceService) {
        this.root = root;
        this.oldRoot = EcoreUtil.copy(root);
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
                tpParentId = Long.parseLong(result.toString());
            }
        });
        setSavedPosition();
    }

    /**
     * {@inheritDoc}
     */
    public TPSRoot getRoot() {
        return root;
    }

    /**
     * {@inheritDoc}
     */
    public Layout getLayout() {
        return layout;
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
    public void save(ModelingProjectView modelingProjectView, Editor editor, Tab tab, PostProcessHandler handler) {
        super.save(modelingProjectView, editor, tab, new PostProcessHandler() {
            @Override
            public void execute() {
                if (handler != null) {
                    handler.execute();
                }
                if (generateFlag) {
                    genResourceService.generateTSDPattern(fileId, isCreateFile, new AsyncCallback<VMFile>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }

                        @Override
                        public void onSuccess(VMFile resultFile) {
                            EditorUtil.getInstance().setGenerationStatus(fileId, projectId, genResourceService, statusLayout);
                            if (isCreateFile) {
                                if (null == resultFile) {
                                    SC.warn("return null");
                                } else {
                                    String fileFullName = resultFile.getName() + "." + resultFile.getExtensionStr();
                                    SC.say(fileFullName + " is generated.\nPlease wait a moment as the process is in progress.");
                                    generateCallBackFile(modelingProjectView.getFileTreeNodeFactory().findTreeNode(modelingProjectView.getTree(), tpParentId), resultFile,
                                            resultFile.getId());
                                }
                            } else {
                                SC.say("Please wait a moment as the process is in progress.");
                            }
                        }
                    });
                    generateFlag = false;
                }
                oldRoot = EcoreUtil.copy(root);
            }
        });
    }

    /**
     * Create data to be displayed in TreeGrid based on {@link #parseRoot}.
     * @return Data displayed in TreeGrid
     */
    private List<TreeNode> createTCEditorData() {
        List<TreeNode> ret = new ArrayList<TreeNode>();
        Iterator<Map.Entry<String, TCNode>> it = parseRoot.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, TCNode> entry = it.next();
            TreeNode temp = null;
            if (entry.getValue().getChildren() != null && entry.getValue().getChildren().size() > 0) {
                temp = new TCEditorTreeNode(entry.getKey(), entry.getValue().getName(), entry.getValue().getChildType(), entry.getValue(), false, false);
            } else {
                temp = new TCEditorTreeNode(entry.getKey(), entry.getValue().getName(), null, entry.getValue(), entry.getValue().isChecked(), false);
            }
            ret.add(temp);
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    public void create() {
        layout.setBorder("1px solid blue");
        layout.setWidth100();
        layout.setHeight100();
        layout.draw();
        List<String> extensionsFmTc = Arrays.asList(new String[] { Extension.FM.getValue(), Extension.TC.getValue() });
        fileViewFmTc = new DependentFileViewPart("Dependent .fm or .tc file", root, projectId, layout.getWidth(), layout.getHeight(), extensionsFmTc, editResourceService);
        List<String> extensionsFmc = Arrays.asList(new String[] { Extension.FMC.getValue() });
        fileViewFmc = new DependentFileViewPart("Dependent .fmc file", root, projectId, layout.getWidth(), layout.getHeight(), extensionsFmc, editResourceService, true, null);
        patternElementForm = new TPSPatternElementForm(TPSEditor.this, projectId, projectService);

        DynamicForm formViewTc = fileViewFmTc.getDependentFileView();
        formViewTc.setNumCols(5);
        formViewTc.setColWidths("*", 220, 150, 150, "*");
        formViewTc.setItems(fileViewFmTc.getSelectItem(), fileViewFmTc.getSelect(), fileViewFmTc.getReload(), new SpacerItem());

        DynamicForm formViewFmc = fileViewFmc.getDependentFileView();
        formViewFmc.setNumCols(5);
        formViewFmc.setColWidths("*", 220, 150, 150, "*");
        formViewFmc.setItems(fileViewFmc.getSelectItem(), fileViewFmc.getSelect(), fileViewFmc.getReload(), fileViewFmc.getClear());

        DynamicForm patternElementForm = this.patternElementForm.getDynamicForm();
        patternElementForm.setNumCols(5);
        patternElementForm.setColWidths("*", 220, 150, 150, "*");
        patternElementForm.setItems(this.patternElementForm.getTextItem(), this.patternElementForm.getEdit(), this.patternElementForm.getReset(), new SpacerItem());

        statusLayout = EditorUtil.getInstance().createGenerationStatusLayout(fileId, projectId, genResourceService);

        DynamicForm generateBtnForm = new DynamicForm();
        generateBtnForm.setWidth100();
        generateBtnForm.setMargin(10);
        generateBtnForm.setNumCols(5);
        generateBtnForm.setColWidths("*", 220, 150, 150, "*");
        SpacerItem spacer = new SpacerItem();
        spacer.setWidth("*");
        spacer.setEndRow(false);
        generate = new ButtonItem("Generate");
        generate.setStartRow(false);
        generate.setEndRow(false);
        generate.setAlign(Alignment.CENTER);
        generate.setWidth(150);

        generateAndCreate = new ButtonItem();
        generateAndCreate.setTitle("Generate and Create");
        generateAndCreate.setStartRow(false);
        generateAndCreate.setWidth(150);

        generateBtnForm.setItems(spacer, generate, generateAndCreate);

        treeGrid = new TreeGrid();
        treeGrid.setCellFormatter(new CellFormatter() {

            @Override
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
                if (((TCNode) record.getAttributeAsObject("TCNode")).getState().equals(TCNodeState.NEW)) {
                    record.set_baseStyle("treeCell_TCEditorNEW");
                } else if (((TCNode) record.getAttributeAsObject("TCNode")).getState().equals(TCNodeState.DELETED)) {
                    record.set_baseStyle("treeCell_TCEditorDEL");
                } else if (((TCNode) record.getAttributeAsObject("TCNode")).isInherited()) {
                    record.set_baseStyle("treeCell_TCEditorInherited");
                } else if (((TCNode) record.getAttributeAsObject("TCNode")).getState().equals(TCNodeState.UNCHANGED)) {
                    record.set_baseStyle("treeCell_TCEditor");
                } else {
                    record.set_baseStyle("treeCell_TCEditor");
                }
                if (treeGrid.isSelected(record)) {
                    record.set_baseStyle("treeCell_TCEditorChecked " + record.get_baseStyle());
                } else {
                    if (record.get_baseStyle().contains("treeCell_TCEditorChecked")) {
                        record.set_baseStyle(record.get_baseStyle().replace("treeCell_TCEditorChecked ", ""));
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
        treeGrid.setCanResizeFields(false);
        treeGrid.setCanReorderFields(false);
        treeGrid.setCanSort(false);
        treeGrid.setLeaveScrollbarGap(false);
        treeGrid.setData(createTreeData());
        treeGrid.setMargin(10);

        ListGridField name = new ListGridField("Name");

        handler = new PostProcessHandler() {
            @Override
            public void execute() {
                name.setHeaderTitle(fileViewFmTc.getSelectItem().getValueMap() != null ? (String) fileViewFmTc.getSelectItem().getValueMap().values().iterator().next() : "");
                treeGrid.redrawHeader(true);
            }
        };

        ListGridField pkey = new ListGridField("pk");
        pkey.setHidden(true);
        ListGridField parent = new ListGridField("parentId");
        parent.setHidden(true);
        ListGridField checkbox = new ListGridField("checkbox");
        checkbox.setHidden(true);
        ListGridField tcNode = new ListGridField("TCNode");
        tcNode.setHidden(true);
        ListGridField nWise = new ListGridField("nwise", "N-wise");
        ListGridField property = new ListGridField("property", "Property");
        TextItem ti = new TextItem();
        ti.setBrowserInputType("tel");
        ti.setKeyPressFilter("[0-9]");
        nWise.setEditorProperties(ti);
        nWise.setType(ListGridFieldType.TEXT);
        nWise.setCanEdit(false);
        treeGrid.setFields(pkey, parent, checkbox, name, tcNode, nWise, property);

        fileViewFmTc.getSelectItem().setRequired(true);
        fileViewFmc.getSelectItem().setRequired(true);
        this.patternElementForm.getTextItem().setRequired(true);

        Scheduler.get().scheduleDeferred(() -> {
            layout.setMembers(formViewTc, formViewFmc, patternElementForm, statusLayout, generateBtnForm, treeGrid);
        });

        bind();

        refresh();
    }

    /**
     * Create a Tree to be set in the TreeGrid that displays the contents of the FM file or TC file.
     * @return tree
     */
    private Tree createTreeData() {
        tree = new Tree();
        tree.setModelType(TreeModelType.PARENT);
        tree.setRootValue("");
        tree.setIdField("pk");
        tree.setParentIdField("parentId");
        tree.setNameProperty("Name");
        tree.setOpenProperty("isOpen");
        tree.setData(new TreeNode[] {});
        return tree;
    }

    /**
     * Create event handlers for various functions of the FPS editor.
     */
    protected void bind() {

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
                    changedFlg = false;
                    for (ListGridRecord record : treeGrid.getRecords()) {
                        if (!((TCNode) record.getAttributeAsObject("TCNode")).getState().equals(TCNodeState.UNCHANGED)) {
                            changedFlg = true;
                            break;
                        }
                    }
                    if (changedFlg) {
                        final Dialog dialog = new Dialog();
                        dialog.setIsModal(true);
                        dialog.setShowModalMask(true);
                        dialog.setCanDrag(true);
                        dialog.setCanDragReposition(true);
                        dialog.setTitle("Save menu");
                        dialog.setMessage("This file has new or deleted state item. Please select action.");
                        dialog.setButtons(new Button("Update and Save"), new Button("Save"), new Button("Cancel"));
                        dialog.addCloseClickHandler(e -> dialog.markForDestroy());
                        dialog.addButtonClickHandler(new ButtonClickHandler() {
                            public void onButtonClick(ButtonClickEvent event) {
                                String title = event.getTargetCanvas().getTitle();
                                if ("Update and Save".equals(title)) {
                                    updateAndSaveAction();
                                    refresh();
                                    saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
                                } else if ("Save".equals(title)) {
                                    refresh();
                                    saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
                                }
                                dialog.markForDestroy();
                            }
                        });
                        dialog.draw();
                    } else {
                        saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
                    }
                    changedFlg = false;
                }
            }
        });
        fileViewFmTc.addReloadClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                TextItem selectItem = fileViewFmTc.getSelectItem();
                if (selectItem.getValueMap() == null || selectItem.getValueMap().isEmpty()) {
                    SC.say("Please Select Dependent .fm or .tc File.");
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

                tpsResourceService.getFileContent(targetFileId, outputStream.toByteArray(), new AsyncCallback<byte[]>() {

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
                            root = (TPSRoot) r.getContents().get(0);
                        } catch (IOException e) {
                            SC.warn(e.getMessage());
                        }
                        saveDependentFiles();
                        refresh();
                    }
                });
            }
        }, fileViewFmTc.getReload());

        fileViewFmc.addReloadClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                TextItem selectItem = fileViewFmc.getSelectItem();
                if (selectItem.getValueMap() == null || selectItem.getValueMap().isEmpty()) {
                    SC.say("Please Select Dependent .fmc File.");
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

                tpsResourceService.getFileContent(targetFileId, outputStream.toByteArray(), new AsyncCallback<byte[]>() {

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
                            root = (TPSRoot) r.getContents().get(0);
                        } catch (IOException e) {
                            SC.warn(e.getMessage());
                        }
                        saveDependentFiles();
                        refresh();
                    }
                });
            }
        }, fileViewFmc.getReload());

        fileViewFmTc.addSelectClickHandler(fileViewFmTc.getSelect());
        fileViewFmc.addSelectClickHandler(fileViewFmc.getSelect());
        patternElementForm.addEditClickHandler(patternElementForm.getEdit());
        patternElementForm.addResetClickHandler(patternElementForm.getReset());

        fileViewFmc.addClearClickHandler(fileViewFmc.getClear(), new PostProcessHandler() {
            @Override
            public void execute() {
                // FMCの関連削除時、TPSRootのReferenceからFMCを削除する
                List<Reference> fmcRefs = root.getRefs().stream().filter(ref -> Extension.FMC.equals(Extension.getByCode(ref.getRefExtension()))).collect(Collectors.toList());
                fmcRefs.forEach(ref -> root.getRefs().remove(ref));

                // FMCの関連削除時はFMCRootを削除する
                if (root.getFmcRoot() != null) {
                    root.setFmcRoot(null);
                    saveDependentFiles();
                    refresh();
                }
            }
        });

        generate.addClickHandler(e -> {
            if (!fileViewFmTc.getDependentFileView().validate()) {
                SC.say("please select .tc file.");
                return;
            }
            saveDependentFiles();
            isCreateFile = false;
            generateFlag = true;
            saveItem.fireEvent(new com.smartgwt.client.widgets.events.ClickEvent(saveItem.getJsObj()));
        });

        generateAndCreate.addClickHandler(e -> {
            if (!fileViewFmTc.getDependentFileView().validate()) {
                SC.say("please select .tc file.");
                return;
            }
            saveDependentFiles();
            isCreateFile = true;
            generateFlag = true;
            saveItem.fireEvent(new com.smartgwt.client.widgets.events.ClickEvent(saveItem.getJsObj()));
        });

        treeGrid.addSelectionChangedHandler(createSelectionChangedHandler());
        treeGrid.addSelectionUpdatedHandler(new SelectionUpdatedHandler() {

            @Override
            public void onSelectionUpdated(SelectionUpdatedEvent event) {
                if (!cmd.isEmpty()) {
                    Arrays.asList(tree.getAllNodes()).forEach(node -> {
                        TCNode tcNode = (TCNode) node.getAttributeAsObject("TCNode");
                        if (tree.isFolder(node) && tcNode.isChecked() != treeGrid.isSelected(node)) {
                            if (treeGrid.isSelected(node)) {
                                TCEditorCommandProvider.getInstance().checkNode(tcNode, true, cmd);
                            } else {
                                TCEditorCommandProvider.getInstance().checkNode(tcNode, false, cmd);
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
                TCNode target = (TCNode) event.getRecord().getAttributeAsObject("TCNode");
                if (target != null) {
                    String nwise = String.valueOf(target.getNWiseCombination());
                    if ("-1".equals(nwise)) {
                        nwise = "";
                    }
                    final Dialog dialog = new Dialog();
                    dialog.setAutoCenter(true);
                    dialog.setWidth(300);
                    dialog.setHeight(85);
                    dialog.setTitle("Please input for N-wise number.");
                    dialog.setBackgroundColor("white");
                    dialog.setShowModalMask(true);
                    dialog.setIsModal(true);
                    dialog.setCanDragReposition(true);
                    dialog.addCloseClickHandler(e -> dialog.markForDestroy());
                    DynamicForm nWiseForm = new DynamicForm();
                    nWiseForm.setWidth("100%");
                    nWiseForm.setHeight("100%");
                    nWiseForm.setNumCols(2);
                    nWiseForm.setColWidths("50%", "50%");

                    TextItem nWiseText = new TextItem("nWise", "Please enter a Value");
                    nWiseText.setValue(nwise);
                    nWiseText.setTitleColSpan(1);
                    nWiseText.setColSpan(1);
                    nWiseText.setWidth("100%");
                    nWiseText.setSelectOnFocus(true);
                    ButtonItem okBtn = new ButtonItem("OK");
                    okBtn.setColSpan(1);
                    okBtn.setEndRow(false);
                    okBtn.setWidth("100%");
                    okBtn.setTextAlign(Alignment.CENTER);
                    ButtonItem cancelBtn = new ButtonItem("Cancel");
                    cancelBtn.setColSpan(1);
                    cancelBtn.setStartRow(false);
                    cancelBtn.setWidth("100%");
                    cancelBtn.setTextAlign(Alignment.CENTER);

                    nWiseForm.setFields(nWiseText, okBtn, cancelBtn);
                    dialog.setMembers(nWiseForm);

                    okBtn.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent event) {
                            if (nWiseText.getValueAsString() == null) {
                                dialog.markForDestroy();
                                return;
                            }
                            if ("".equals(nWiseText.getValueAsString()) || !isDigit(nWiseText.getValueAsString())) {
                                TPSEditorCommandProvider.getInstance().setNwise(target, -1, cmd);
                                manager.execute(cmd.unwrap());
                                cmd = new CompoundCommand();
                                refresh();
                            } else {
                                TPSEditorCommandProvider.getInstance().setNwise(target, Integer.valueOf(nWiseText.getValueAsString()), cmd);
                                manager.execute(cmd.unwrap());
                                cmd = new CompoundCommand();
                                refresh();
                            }
                            dialog.markForDestroy();
                        }
                    });

                    cancelBtn.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent event) {
                            dialog.markForDestroy();
                        }
                    });
                    dialog.draw();
                }
            }
        });

    }

    /**
     * Create an event that changes the checked state of TCNode.
     * @return Selection Changed Handler
     */
    private SelectionChangedHandler createSelectionChangedHandler() {
        return new SelectionChangedHandler() {
            @Override
            public void onSelectionChanged(SelectionEvent event) {
                ListGridRecord record = event.getRecord();
                TCNode tcNode = ((TCNode) record.getAttributeAsObject("TCNode"));
                if (tcNode == null)
                    return;
                if (tcNode.isChecked() != treeGrid.isSelected(record) && !tree.isFolder((TreeNode) record)) {
                    TCEditorCommandProvider.getInstance().checkNode(tcNode, treeGrid.isSelected(record), cmd);
                }
            }
        };
    }

    /**
     * Nodes with a status of DELETED are deleted from the TC model.<br>
     * Node statuses other than the above are updated to UNCHANGED.
     */
    private void updateAndSaveAction() {
        List<TCNode> deletedList = new ArrayList<TCNode>();
        root.getRootNodes().forEach(x -> {
            for (TCNode delNode : x.getChildren()) {
                removeNodes(delNode, deletedList);
            }
        });
        deletedList.forEach(x -> ((TCNode) x.eContainer()).getChildren().remove(x));
        deletedList.clear();
        for (TCNode delNode : root.getRootNodes()) {
            if (delNode.getState().equals(TCNodeState.DELETED)) {
                deletedList.add(delNode);
            }
        }
        deletedList.forEach(x -> ((TPSRoot) x.eContainer()).getRootNodes().remove(x));
        root.getRootNodes().get(0).setState(TCNodeState.UNCHANGED);
    }

    /**
     * TCNode to be deleted will be added to the argument: deletedList.<br>
     * It will be processed recursively until a node that has no child nodes is checked.
     * @param childNode Node to check
     * @param deletedList Target node to be deleted
     */
    private void removeNodes(TCNode childNode, List<TCNode> deletedList) {
        List<TCNode> children = childNode.getChildren();
        if (!children.isEmpty()) {
            for (TCNode target : children) {
                removeNodes(target, deletedList);
            }
        }
        if (childNode.getState().equals(TCNodeState.DELETED)) {
            deletedList.add(childNode);
        } else {
            childNode.setState(TCNodeState.UNCHANGED);
        }
    }

    /**
     * The project explorer is redrawn. <br>
     * (The .fp file created from the "Generate and Create" button will be displayed in Explorer)
     * @param selectedNode TreeNode of the directory where the FPS file is located
     * @param resource Class with information of created FP file
     * @param fileId ID of the created FP file
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
     * FPS information saved in the model is reflected on the layout.
     */
    protected void refresh() {
        parseRoot = new TCModelParser().getParseTPSRootResult(root);
        fileViewFmTc.setFileName(handler);

        tree.removeChildren(tree.getRoot());
        List<TreeNode> newTreeNode = createTCEditorData();
        newTreeNode.forEach(record -> {
            TCNode tcNode = (TCNode) record.getAttributeAsObject("TCNode");
            String nwise = tcNode.getNWiseCombination() + "";
            if (nwise == null || Integer.valueOf(nwise) == -1) {
                nwise = "";
            }
            record.setAttribute("nwise", nwise);

            String tcPropertyStr = "";
            for (TCProperty tcProperty : tcNode.getProperties()) {
                if (!"".equals(tcProperty.getKey())) {
                    if (!tcPropertyStr.equals("")) {
                        tcPropertyStr += ", ";
                    }
                    tcPropertyStr += tcProperty.getKey() + " : " + tcProperty.getValue();
                }
            }

            record.setAttribute("property", tcPropertyStr);

            tree.add(record);
        });

        tree.openAll();
        Scheduler.get().scheduleDeferred(() -> {
            treeGrid.redraw();
        });
    }

    /**
     * {@inheritDoc}
     */
    public void addSaveHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        saveItem.addClickHandler(handler);
    }

    /**
     * The dependent file inputs are saved.
     */
    private void saveDependentFiles() {
        if (!fileViewFmTc.getDependentFileView().validate()) {
            return;
        }
        saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
        refresh();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChanged() {
        if (root.getNodeVariables().size() != oldRoot.getNodeVariables().size()) {
            return true;
        } else {
            for (int i = 0; i < root.getNodeVariables().size(); i++) {
                if (!root.getNodeVariables().get(i).getVariableName().equals(oldRoot.getNodeVariables().get(i).getVariableName())) {
                    return true;
                }
            }
        }
        if (root.getPatternElements().size() != oldRoot.getPatternElements().size()) {
            return true;
        } else {
            for (int i = 0; i < root.getPatternElements().size(); i++) {
                if (!root.getPatternElements().get(i).getName().equals(oldRoot.getPatternElements().get(i).getName())) {
                    return true;
                } else if (!root.getPatternElements().get(i).getValue().equals(oldRoot.getPatternElements().get(i).getValue())) {
                    return true;
                }
            }
        }
        return (manager.getSavedPosition() != manager.getStackCoount());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSavedPosition() {
        manager.setSavedPosition(manager.getStackCoount());
    }

    /**
     * Checks if the argument string is a number.
     * @param str String to check
     * @return If true, a number
     */
    private boolean isDigit(String str) {
        if (str == null) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
