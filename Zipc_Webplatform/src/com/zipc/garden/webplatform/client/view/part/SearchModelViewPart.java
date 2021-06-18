package com.zipc.garden.webplatform.client.view.part;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.DrawEvent;
import com.smartgwt.client.widgets.events.DrawHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.KeyDownEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyDownHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.fm.FMEditor;
import com.zipc.garden.webplatform.client.editor.fsm.FSMEditor;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.SearchResult;

/**
 * This class implements the ability to search for FMNode and FSMDState.
 */
public class SearchModelViewPart {

    /** search window */
    private Window winModal;

    /** Variable to save the record selected from ListGrid */
    private ListGridRecord selectRecord;

    /** TextItem used for fuzzy search of FMNode name, FSMDState name */
    private TextItem searchItem = new TextItem("Search");

    /**
     * A search window for FMNode and FSMDState is created.
     * @param modelingProjectView Project explorer
     * @param editResourceService Asynchronous interface for searching FM or FSM models
     * @param projectId project id
     */
    public void openSearchWindow(ModelingProjectView modelingProjectView, EditResourceServiceAsync editResourceService, long projectId) {
        winModal = new Window();
        winModal.setTitle("Search Model");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(false);
        winModal.setShowFooter(true);
        winModal.setShowResizer(true);
        winModal.setKeepInParentRect(true);
        winModal.setAutoCenter(true);
        winModal.setBackgroundColor("white");
        winModal.setCanDragResize(true);
        winModal.setWidth(600);
        winModal.setHeight(400);

        VLayout formlayout = new VLayout();
        formlayout.setWidth100();
        formlayout.setHeight100();

        DynamicForm searchForm = new DynamicForm();
        searchForm.setNumCols(5);
        searchForm.setBackgroundColor("white");
        searchForm.setMargin(3);
        searchForm.setColWidths(120, 120, 120, 120, 120);
        searchItem.setEndRow(false);
        ButtonItem searchBtn = new ButtonItem("Search");
        searchBtn.setStartRow(false);
        searchBtn.setEndRow(false);

        DynamicForm checkBoxes = new DynamicForm();
        checkBoxes.setNumCols(50);
        checkBoxes.setBackgroundColor("white");
        checkBoxes.setMargin(3);
        CheckboxItem fmCheck = new CheckboxItem(".fm");
        fmCheck.setWidth(30);
        fmCheck.setStartRow(false);
        fmCheck.setEndRow(false);
        fmCheck.setValue(true);
        CheckboxItem fsmCheck = new CheckboxItem(".fsm");
        fsmCheck.setWidth(30);
        fsmCheck.setStartRow(false);
        fsmCheck.setEndRow(false);
        fsmCheck.setValue(true);

        searchForm.setFields(searchItem, searchBtn);
        checkBoxes.setFields(fmCheck, fsmCheck);

        ListGrid dependentFile = new ListGrid();
        dependentFile.setBackgroundColor("white");
        dependentFile.setWidth100();
        dependentFile.setHeight100();
        dependentFile.setShowHeaderContextMenu(false);
        dependentFile.setShowHeaderMenuButton(false);
        dependentFile.setCanResizeFields(true);
        dependentFile.setCanReorderFields(false);
        dependentFile.setLeaveScrollbarGap(false);
        dependentFile.setCanEdit(false);
        dependentFile.setSelectionType(SelectionStyle.SINGLE);
        dependentFile.setMargin(5);

        ListGridField idfield = new ListGridField("id", "");
        idfield.setType(ListGridFieldType.TEXT);
        idfield.setHidden(true);
        ListGridField fileNameField = new ListGridField("Name", "");
        fileNameField.setType(ListGridFieldType.TEXT);
        ListGridField fullPathField = new ListGridField("fullPath", "");
        fullPathField.setType(ListGridFieldType.TEXT);
        ListGridField extension = new ListGridField("extension", "");
        extension.setType(ListGridFieldType.TEXT);
        extension.setHidden(true);
        ListGridField node = new ListGridField("Node", "");
        node.setHidden(true);

        dependentFile.setFields(idfield, fileNameField, fullPathField, extension, node);

        searchItem.addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (KeyNames.ENTER.equals(event.getKeyName())) {
                    searchBtn.fireEvent(new com.smartgwt.client.widgets.form.fields.events.ClickEvent(searchBtn.getJsObj()));
                }
            }
        });

        searchBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                List<String> extensions = new ArrayList<String>();
                if (fmCheck.getValueAsBoolean()) {
                    extensions.add(Extension.FM.getValue());
                }
                if (fsmCheck.getValueAsBoolean()) {
                    extensions.add(Extension.FSM.getValue());
                }
                String keyWord = searchItem.getValue() != null ? searchItem.getValue().toString() : "";
                if ("".equals(keyWord)) {
                    DataSource ds = SearchModelListDS.getInstance();
                    dependentFile.setDataSource(ds);
                    ds.setTestData(new ListGridRecord[] {});
                    dependentFile.fetchData();
                    return;
                }
                createListGridData(editResourceService, projectId, extensions, dependentFile, keyWord);
            }
        });

        dependentFile.addCellDoubleClickHandler(new CellDoubleClickHandler() {
            @Override
            public void onCellDoubleClick(CellDoubleClickEvent event) {
                selectRecord = event.getRecord();
                modelingProjectView.createOpenEditorTab(selectRecord.getAttributeAsLong("id"), true);
            }
        });

        formlayout.addMembers(searchForm, checkBoxes, dependentFile);
        winModal.addMember(formlayout);

        winModal.addCloseClickHandler(new CloseClickHandler() {

            @Override
            public void onCloseClick(CloseClickEvent event) {
                event.cancel();
                winModal.hide();
            }
        });

        winModal.addResizedHandler(new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent event) {
                formlayout.setWidth100();
            }
        });
    }

    /**
     * Gets the editor associated with the specified tab and searches and selects FMNode or FSMDState.
     * @param selectedTab specified tab
     */
    public void selectSearchResultModel(Tab selectedTab) {
        if (selectedTab != null) {
            Editor editor = (Editor) selectedTab.getAttributeAsObject("Editor");
            editor.getLayout().addDrawHandler(new DrawHandler() {
                @Override
                public void onDraw(DrawEvent event) {
                    findAndSelectNode(editor);
                }
            });
            if (editor.getLayout().isDrawn()) {
                findAndSelectNode(editor);
            }
        }
    }

    /**
     * FMNode or FSMDState in the specified editor will be searched and selected.
     * @param editor FM Editor or FSM Editor
     */
    private void findAndSelectNode(Editor editor) {
        if (editor instanceof FMEditor) {
            FMEditor fmEditor = (FMEditor) editor;
            FMNode selectNode = findEditorFMNode(fmEditor.getFMRoot(), selectRecord.getAttributeAsString("Name"));
            fmEditor.getEditorScroll().moveLayoutPosition(selectNode.getLeft() + selectNode.getWidth() / 2, selectNode.getTop() + selectNode.getHeight() / 2);
            fmEditor.nodeManager.setSelectDrawRectAll(false);
            fmEditor.nodeManager.setSelectDrawRect(selectNode);
        } else if (editor instanceof FSMEditor) {
            FSMEditor fsmEditor = (FSMEditor) editor;
            FSMDState selectNode = (FSMDState) selectRecord.getAttributeAsObject("Node");
            fsmEditor.getEditorScroll().moveLayoutPosition(selectNode.getLeft() + selectNode.getWidth() / 2, selectNode.getTop() + selectNode.getHeight() / 2);
            fsmEditor.stateManager.selectDrawItemAll(fsmEditor, false);
            fsmEditor.stateManager.selectDrawItem(fsmEditor, (FSMDState) selectRecord.getAttributeAsObject("Node"));
        }
    }

    /**
     * Create a ListGrid that displays the search results for FM and FSM models.
     * @param editResourceService Asynchronous interface for searching FM or FSM models
     * @param projectId project id
     * @param extensions File extension to search (FM, FSM)
     * @param dependentFile Where to display search results
     * @param keyWord Keywords to search
     */
    private void createListGridData(EditResourceServiceAsync editResourceService, long projectId, List<String> extensions, ListGrid dependentFile, String keyWord) {
        editResourceService.searchModel(projectId, extensions, keyWord, new AsyncCallback<List<SearchResult>>() {

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(List<SearchResult> results) {
                List<ListGridRecord> retData = new ArrayList<ListGridRecord>();
                for (SearchResult result : results) {
                    ListGridRecord record = new ListGridRecord();
                    record.setAttribute("id", result.getId());
                    record.setAttribute("Name", result.getName());
                    record.setAttribute("fullPath", result.getFullPath());
                    record.setAttribute("extension", result.getExtension());
                    record.setAttribute("Node", convertToRootElement(result.getNode()));
                    retData.add(record);
                }

                DataSource ds = SearchModelListDS.getInstance();
                ds.setTestData(retData.toArray(new ListGridRecord[retData.size()]));
                dependentFile.setDataSource(ds);
                dependentFile.sort();
                dependentFile.fetchData();
            }
        });
    }

    /**
     * The specified full path is searched for in fmRoot. <br>
     * If there is a match, returns FMNode.
     * @param fmRoot Search target
     * @param fullPath It will be searched by this Full Path.
     * @return search results
     */
    private FMNode findEditorFMNode(FMRoot fmRoot, String fullPath) {
        return searchFMModel(fmRoot, fullPath);
    }

    /**
     * The specified path is searched for in fmRoot. <br>
     * If there is a match, returns FMNode.
     * @param fmRoot Search target
     * @param path It will be searched by this path.
     * @return search results
     */
    private FMNode searchFMModel(FMRoot fmRoot, String path) {
        FMNode ret = null;
        if (fmRoot != null && fmRoot.getNode() != null) {
            if (fmRoot.getNode().getName().indexOf(path) != -1) {
                return fmRoot.getNode();
            }
            if (fmRoot.getNode().getChildren() != null && !fmRoot.getNode().getChildren().isEmpty()) {
                for (FMNode child : fmRoot.getNode().getChildren()) {
                    ret = parseFMNode(child, path, fmRoot.getNode().getName());
                    if (ret != null) {
                        return ret;
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Recursively searches for FM Nodes that match the specified path.
     * @param childNode Node to search
     * @param path specified path
     * @param makePath Full path of parent node
     * @return search results
     */
    private FMNode parseFMNode(FMNode childNode, String path, String makePath) {
        FMNode ret = null;
        makePath += "." + childNode.getName();
        final String fullPath = makePath;
        if (fullPath.indexOf(path) != -1) {
            return childNode;
        }
        if (childNode.getChildren() != null && !childNode.getChildren().isEmpty()) {
            for (FMNode gchild : childNode.getChildren()) {
                ret = parseFMNode(gchild, path, fullPath);
                if (ret != null) {
                    return ret;
                }
            }
        }
        return ret;
    }

    /**
     * Converts the specified binary data to EObject.
     * @param data specified binary data
     * @return Transformed EObject
     */
    public EObject convertToRootElement(byte[] data) {
        BinaryResourceImpl r = new BinaryResourceImpl();
        ByteArrayInputStream bi = new ByteArrayInputStream(data);
        try {
            r.load(bi, EditOptions.getDefaultLoadOptions());
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
        return (EObject) r.getContents().get(0);
    }

    /**
     * A data source associated with a ListGrid that displays search results for FM and FSM models.
     */
    private static class SearchModelListDS extends DataSource {

        /** Initialize the instance with null. */
        private static SearchModelListDS instance = null;

        /**
         * Get an instance. <br>
         * If not created, an instance will be created.
         * @return instance
         */
        public static SearchModelListDS getInstance() {
            if (instance == null) {
                instance = new SearchModelListDS("SearchModelListDS");
            }
            return instance;
        }

        /**
         * Issued by {@link #getInstance()}. <br>
         * The data source field is created.
         * @param id
         */
        public SearchModelListDS(String id) {
            setID(id);

            DataSourceTextField dsId = new DataSourceTextField("id", "ID");
            dsId.setHidden(true);
            DataSourceTextField dsFileName = new DataSourceTextField("Name", "Name");
            DataSourceTextField dsFullPath = new DataSourceTextField("fullPath", "FullPath");
            DataSourceTextField dsExtension = new DataSourceTextField("extension", "extension");
            dsExtension.setHidden(true);
            DataSourceField dsNode = new DataSourceField("Node", FieldType.BINARY);
            dsNode.setHidden(true);
            setFields(dsId, dsFileName, dsFullPath, dsExtension, dsNode);

            setClientOnly(true);
        }
    }

    /**
     * show {@link #winModal}.
     */
    public void showWindow() {
        winModal.show();
        searchItem.focusInItem();
    }

    /**
     * {@link #winModal} is discarded.
     */
    public void destroy() {
        winModal.markForDestroy();
    }
}
