package com.zipc.garden.webplatform.client.editor.tc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.grid.events.SelectionUpdatedEvent;
import com.smartgwt.client.widgets.grid.events.SelectionUpdatedHandler;
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
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCNodeState;
import com.zipc.garden.model.tc.TCProperty;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.webplatform.client.command.TCEditorCommandProvider;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;
import com.zipc.garden.webplatform.client.view.part.DependentFileViewPart;
import com.zipc.garden.webplatform.shared.Extension;

/**
 * Class for editors that set test-condition.
 */
public class TCEditor extends Editor {

    /** Class that manages the commands operated by TCEditor */
    private final EditManager manager = EditManager.createInstance();

    /** The project ID in which this TC is managed. */
    private long projectId;

    /** Root model of TC editor. */
    private TCRoot root;

    /**
     * Map holding unique TCNode information.<br>
     * key : Unique path <br>
     * value : TCNode managed by TCRoot
     */
    private Map<String, TCNode> parseRoot = new HashMap<String, TCNode>();

    /** TreeGrid displaying the data of TCNode */
    private TreeGrid treeGrid;

    /** The Tree that will be displayed as a row for this {@link TCEditor#treeGrid} */
    private Tree tree;

    /** Visible title for the tree column (field). */
    private final String gridName;

    /** Overall layout of TCEditor */
    private final Layout layout = new VLayout();

    /** A class that displays a selection view of .fm, .tc files */
    private DependentFileViewPart fileView;

    /** TC editor operation commands */
    private CompoundCommand cmd = new CompoundCommand();

    /** Context menu for saving the TC editor */
    protected MenuItem saveItem = new MenuItem("Save");

    /** True if the TC editor has changed */
    private boolean changedFlg = false;

    /** An asynchronous interface to parse and marge the current TCRoot and the newly loaded TCRoot */
    private final TCResourceServiceAsync tcResourceService = GWT.create(TCResourceService.class);

    /** Asynchronous interface to get list information of files you want to select in TCEditor */
    private EditResourceServiceAsync editResourceService;

    /**
     * constructor
     * @param root {@link TCEditor#root}
     * @param projectId {@link TCEditor#projectId}
     * @param fileName {@link TCEditor#gridName}
     * @param editResourceService {@link TCEditor#editResourceService}
     */
    public TCEditor(TCRoot root, long projectId, String fileName, EditResourceServiceAsync editResourceService) {
        this.projectId = projectId;
        this.root = root;
        this.gridName = fileName;
        this.editResourceService = editResourceService;
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
    public AbstractRootElement getRoot() {
        return root;
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
    public boolean isChanged() {
        return (manager.getSavedPosition() != manager.getStackCoount() || changedFlg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSavedPosition() {
        manager.setSavedPosition(manager.getStackCoount());
    }

    /**
     * Get the data to be displayed in Tree of TC Editor.
     * @return the data to be displayed in Tree
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
        layout.setOverflow(Overflow.HIDDEN);
        layout.draw();

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
        treeGrid.setTreeFieldTitle(gridName);
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

        ListGridField name = new ListGridField("Name", gridName);
        ListGridField pkey = new ListGridField("pk");
        pkey.setHidden(true);
        ListGridField parent = new ListGridField("parentId");
        parent.setHidden(true);
        ListGridField checkbox = new ListGridField("checkbox");
        checkbox.setHidden(true);
        ListGridField tcNode = new ListGridField("TCNode");
        tcNode.setHidden(true);
        ListGridField property = new ListGridField("property", "Property");
        property.setCanEdit(false);
        treeGrid.setFields(pkey, parent, checkbox, name, tcNode, property);

        List<String> extensions = Arrays.asList(new String[] { Extension.FM.getValue(), Extension.TC.getValue() });
        fileView = new DependentFileViewPart("Dependent file", root, projectId, layout.getWidth(), layout.getHeight(), extensions, editResourceService);

        Scheduler.get().scheduleDeferred(() -> {
            layout.setMembers(fileView.getDependentFileView(), treeGrid);
        });

        bind();

        refresh();
    }

    /**
     * Creates and gets a row in the TreeGrid.<br>
     * Tree has no visual display.
     * @return Tree
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
     * {@inheritDoc} <br>
     * If you click the "Update and Save" button in the save dialog, the differences will be merged. <br>
     * If you click the "Save" button, the differences will be saved without being merged. <br>
     * If you click the "Cancel" button, the saving will be canceled.
     */
    @Override
    public void save(ModelingProjectView modelingProjectView, Editor editor, Tab tab, PostProcessHandler handler) {
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
            dialog.addCloseClickHandler(e -> dialog.markForDestroy());
            Button dialogUpdateAndSave = new Button("Update and Save");
            Button dialogSave = new Button("Save");
            Button dialogCancel = new Button("Cancel");
            dialogUpdateAndSave.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
                @Override
                public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                    updateAndSaveAction();
                    refresh();
                    modelingProjectView.executeSaveAction(editor, tab, handler);
                    dialog.markForDestroy();
                }
            });
            dialogSave.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
                @Override
                public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                    refresh();
                    modelingProjectView.executeSaveAction(editor, tab, handler);
                    dialog.markForDestroy();
                }
            });
            dialogCancel.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
                @Override
                public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                    dialog.markForDestroy();
                }
            });
            dialog.setButtons(dialogUpdateAndSave, dialogSave, dialogCancel);
            dialog.draw();
        } else {
            modelingProjectView.executeSaveAction(editor, tab, handler);
        }
        changedFlg = false;
    }

    /**
     * It will create various handlers for the TC editor.
     */
    private void bind() {
        layout.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.isCtrlKeyDown() && "Z".equals(event.getKeyName())) {
                    manager.undo();
                    refresh();
                } else if (event.isCtrlKeyDown() && "Y".equals(event.getKeyName())) {
                    manager.redo();
                    refresh();
                }
                if (event.isCtrlKeyDown() && "S".equals(event.getKeyName())) {
                    event.cancel();
                    saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
                }
            }
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
        fileView.addReloadClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                TextItem selectItem = fileView.getSelectItem();
                if (selectItem.getValueMap() == null && selectItem.getValueMap().isEmpty()) {
                    SC.say("Please Select Dependent File.");
                    return;
                }
                @SuppressWarnings("unchecked")
                Map<String, String> valueMap = selectItem.getValueMap();
                String fileId = valueMap.keySet().iterator().next();
                BinaryResourceImpl re = new BinaryResourceImpl();
                re.getContents().add(root);
                final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                try {
                    re.save(outputStream, null);
                } catch (IOException e1) {
                    SC.warn(e1.getMessage());
                }
                tcResourceService.getFileContent(Long.valueOf(fileId), outputStream.toByteArray(), new AsyncCallback<byte[]>() {

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
                            root = (TCRoot) r.getContents().get(0);
                        } catch (IOException e) {
                            SC.warn(e.getMessage());
                        }
                        changedFlg = true;
                        refresh();
                    }
                });
            }
        }, fileView.getReload());
        fileView.addSelectClickHandler(fileView.getSelect());
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
        deletedList.forEach(x -> ((TCRoot) x.eContainer()).getRootNodes().remove(x));
        root.getActiveRootNode().setState(TCNodeState.UNCHANGED);
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
     * {@inheritDoc}
     */
    @Override
    public void addSaveHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        saveItem.addClickHandler(handler);
    }

    /**
     * A class that creates a TreeNode to be displayed in the TC editor.
     */
    private class TCEditorTreeNode extends TreeNode {

        /**
         * constructor.<br>
         * Create the data to be displayed in Tree of TC Editor.
         * @param pk Primary key
         * @param name TCNode name
         * @param type TCNode child type (AND or XOR)
         * @param tcNode TCNode information
         * @param isCheck TreeNode selection flag
         * @param isOpen Whether the child elements of TreeNode are collapsed
         */
        public TCEditorTreeNode(String pk, String name, ChildType type, TCNode tcNode, boolean isCheck, boolean isOpen) {
            setAttribute("pk", pk);
            String parent = pk.substring(0, pk.lastIndexOf(name));
            setAttribute("parentId", parent.substring(0, parent.lastIndexOf("/")));
            String showName = name;
            if (type != null) {
                showName += "(" + type.getName() + ")";
            }
            if (tcNode.isOptional()) {
                showName += "(Optional)";
            }
            // add cardinality
            if (tcNode.getChildType().equals(ChildType.XOR) && tcNode.getMin() != -1) {
                String max = Integer.toString(tcNode.getMax());
                if (tcNode.getMax() == -1) {
                    max = "*";
                }
                showName += "[" + tcNode.getMin() + ".." + max + "]";
            }

            String tcPropertyStr = "";
            for (TCProperty tcProperty : tcNode.getProperties()) {
                if (!"".equals(tcProperty.getKey())) {
                    if (!tcPropertyStr.equals("")) {
                        tcPropertyStr += ", ";
                    }
                    tcPropertyStr += tcProperty.getKey() + " : " + tcProperty.getValue();
                }
            }

            setAttribute("Name", showName);
            setAttribute("TCNode", tcNode);
            setAttribute("checkbox", isCheck);
            setAttribute("isOpen", isOpen);
            setAttribute("property", tcPropertyStr);
            set_baseStyle("treeCell_TCEditor");
        }
    }

    /**
     * The TC information saved in the model is reflected on the canvas.
     */
    private void refresh() {
        parseRoot = new TCModelParser().getParseTCRootResult(root);
        fileView.setRoot(root);
        fileView.setFileName(null);

        tree.removeChildren(tree.getRoot());
        createTCEditorData().forEach(record -> tree.add(record));
        tree.openAll();
        Scheduler.get().scheduleDeferred(() -> {
            treeGrid.redraw();
        });
    }
}
