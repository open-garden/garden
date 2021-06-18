package com.zipc.garden.webplatform.client.view.part;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.KeyDownEvent;
import com.smartgwt.client.widgets.events.KeyDownHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;
import com.zipc.garden.webplatform.shared.FileTreeNode;
import com.zipc.garden.webplatform.shared.FileTreeNodeFactory;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * This class implements the ability to find all filenames that belong to a project.
 */
public class SearchFileViewPart {

    /** search window */
    private Window winModal;

    /** Variable to save the record selected from ListGrid */
    private ListGridRecord selectRecord;

    /**
     * A file search window is created.
     * @param modelingProjectView Project explorer
     * @param editResourceService Asynchronous interface for retrieving files
     * @param projectId project id
     */
    public void createSearchWindow(ModelingProjectView modelingProjectView, EditResourceServiceAsync editResourceService, long projectId) {
        winModal = new Window();
        winModal.setTitle("Search File");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
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
        searchForm.setNumCols(3);
        searchForm.setBackgroundColor("white");
        searchForm.setMargin(3);
        searchForm.setColWidths("*", 200, 200);
        TextItem searchItem = new TextItem("Search");
        searchItem.setEndRow(false);
        ButtonItem searchBtn = new ButtonItem("Search");
        searchBtn.setStartRow(false);
        searchBtn.setEndRow(false);

        searchForm.setFields(searchItem, searchBtn);

        ListGrid searchResultFileList = new ListGrid();
        searchResultFileList.setBackgroundColor("white");
        searchResultFileList.setWidth100();
        searchResultFileList.setHeight100();
        searchResultFileList.setShowHeaderContextMenu(false);
        searchResultFileList.setShowHeaderMenuButton(false);
        searchResultFileList.setCanResizeFields(false);
        searchResultFileList.setCanReorderFields(false);
        searchResultFileList.setLeaveScrollbarGap(false);
        searchResultFileList.setCanEdit(false);
        searchResultFileList.setSelectionType(SelectionStyle.SINGLE);
        searchResultFileList.setMargin(5);

        ListGridField idfield = new ListGridField("id", "");
        idfield.setType(ListGridFieldType.TEXT);
        idfield.setHidden(true);
        ListGridField fileNameField = new ListGridField("Name", "");
        fileNameField.setType(ListGridFieldType.TEXT);
        ListGridField fullPathField = new ListGridField("fullPath", "");
        fullPathField.setType(ListGridFieldType.TEXT);

        searchResultFileList.setFields(idfield, fileNameField, fullPathField);

        final int DELAYED_TIME = 200;

        Timer delayKeyTypeEventTimer = new Timer() {
            @Override
            public void run() {
                searchBtn.fireEvent(new com.smartgwt.client.widgets.form.fields.events.ClickEvent(searchBtn.getJsObj()));
            }
        };

        searchItem.addKeyUpHandler(new KeyUpHandler() {

            @Override
            public void onKeyUp(KeyUpEvent event) {
                delayKeyTypeEventTimer.cancel();
                delayKeyTypeEventTimer.schedule(DELAYED_TIME);
            }
        });

        searchItem.addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.getKeyName().equals(KeyNames.ARROW_DOWN)) {
                    event.cancel();
                    if (searchResultFileList.getTotalRows() > 0) {
                        searchResultFileList.focusInRow(searchResultFileList.getRowNumberStart() - 1);
                        searchResultFileList.selectRecord(searchResultFileList.getRowNumberStart() - 1);
                    }
                }
            }
        });

        searchBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                String keyWord = checkKeyWord(searchItem, searchResultFileList);
                createListGridData(editResourceService, projectId, searchResultFileList, keyWord);
            }
        });

        searchResultFileList.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (EventHandler.getKey().equalsIgnoreCase("Enter")) {
                    searchResultFileList.fireEvent(new CellDoubleClickEvent(searchResultFileList.getJsObj()));
                }
            }
        });

        searchResultFileList.addCellDoubleClickHandler(new CellDoubleClickHandler() {
            @Override
            public void onCellDoubleClick(CellDoubleClickEvent event) {
                if (event.getRecord() == null) {
                    selectRecord = searchResultFileList.getSelectedRecord();
                    if (selectRecord == null) {
                        return;
                    }
                } else {
                    selectRecord = event.getRecord();
                }
                modelingProjectView.getTreeGrid().deselectAllRecords();
                long targetId = selectRecord.getAttributeAsLong("id");
                Tree tree = modelingProjectView.getTree();
                FileTreeNodeFactory fileTreeNodeFactory = modelingProjectView.getFileTreeNodeFactory();
                editResourceService.getTargetToRootDirIds(targetId, new AsyncCallback<List<Long>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(List<Long> result) {
                        openParentFolder(modelingProjectView, result, new PostProcessHandler() {
                            @Override
                            public void execute() {
                                selectTargetNode(modelingProjectView, targetId, tree, fileTreeNodeFactory);
                            }
                        });
                        selectTargetNode(modelingProjectView, targetId, tree, fileTreeNodeFactory);
                    }
                });
                winModal.hide();
            }
        });

        formlayout.addMembers(searchForm, searchResultFileList);
        winModal.addMember(formlayout);

        winModal.addCloseClickHandler(new CloseClickHandler() {

            @Override
            public void onCloseClick(CloseClickEvent event) {
                event.cancel();
                winModal.hide();
            }
        });
    }

    /**
     * Activates the file associated with the specified targetId.
     * @param modelingProjectView Project explorer
     * @param targetId TargetId associated with TreeNode in Project Explorer
     * @param tree Tree information in project explorer
     * @param fileTreeNodeFactory A class that summarizes the processing related to TreeNode of the file
     */
    public static void selectTargetNode(ModelingProjectView modelingProjectView, long targetId, Tree tree, FileTreeNodeFactory fileTreeNodeFactory) {
        TreeGrid treeGrid = modelingProjectView.getTreeGrid();
        List<Long> nodeIds = Arrays.asList(treeGrid.getRecords()).stream().map(r -> ((FileTreeNode) r).getResource().getId()).collect(Collectors.toList());
        if (nodeIds.contains(targetId)) {
            TreeNode targetNode = fileTreeNodeFactory.findTreeNode(tree, targetId);
            treeGrid.deselectAllRecords();
            treeGrid.selectRecord(targetNode);
            treeGrid.scrollToRow(treeGrid.getRowNum(targetNode));
        }
    }

    /**
     * Open the parent folder of the searched file.
     * @param modelingProjectView Project explorer
     * @param result Parent directory ID
     * @param afterOpenFolderHandler A handler that can be used for post-processing.
     */
    public static void openParentFolder(ModelingProjectView modelingProjectView, List<Long> result, PostProcessHandler afterOpenFolderHandler) {
        TreeGrid treeGrid = modelingProjectView.getTreeGrid();
        ListGridRecord[] targets = treeGrid.getRecords();
        for (ListGridRecord record : targets) {
            FileTreeNode targetNode = (FileTreeNode) record;
            if (result.contains(targetNode.getResource().getId())) {
                modelingProjectView.folderOpendAction(targetNode, modelingProjectView.getTree(), modelingProjectView.getFileTreeNodeFactory(), new PostProcessHandler() {

                    @Override
                    public void execute() {
                        // check modelingProjectView's TreeNode is open action is finished.
                        modelingProjectView.getTree().openFolder(targetNode);
                        openParentFolder(modelingProjectView, result, afterOpenFolderHandler);
                        if (afterOpenFolderHandler != null) {
                            afterOpenFolderHandler.execute();
                        }
                    }
                });

                result.remove(targetNode.getResource().getId());
                break;
            }
        }
    }

    /**
     * The input value of the search keyword is checked. <br>
     * If it is empty, the ListGrid data will be cleared and the empty string will be returned. <br>
     * If it is not empty, the entered keyword will be returned.
     * @param searchItem Search keyword
     * @param dependentFile Where to display search results
     * @return keyword
     */
    private String checkKeyWord(TextItem searchItem, ListGrid dependentFile) {
        String keyWord = searchItem.getValue() != null ? searchItem.getValue().toString() : "";
        if ("".equals(keyWord)) {
            DataSource ds = SearchFileListDS.getInstance();
            dependentFile.setDataSource(ds);
            ds.setTestData(new ListGridRecord[] {});
            dependentFile.fetchData();
            return "";
        }
        return keyWord;
    }

    /**
     * Create a ListGrid that displays the search results for file.
     * @param editResourceService Asynchronous interface for searching file
     * @param projectId project id
     * @param searchResultFileList Where to display search results
     * @param keyWord Keywords to search
     */
    private void createListGridData(EditResourceServiceAsync editResourceService, long projectId, ListGrid searchResultFileList, String keyWord) {
        editResourceService.searchFile(projectId, keyWord, new AsyncCallback<List<VMFile>>() {

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(List<VMFile> results) {
                List<ListGridRecord> retData = new ArrayList<ListGridRecord>();
                for (VMFile result : results) {
                    ListGridRecord record = new ListGridRecord();
                    record.setAttribute("id", result.getId());
                    record.setAttribute("Name", result.getName());
                    record.setAttribute("fullPath", result.getFullPath());
                    retData.add(record);
                }

                DataSource ds = SearchFileListDS.getInstance();
                ds.setTestData(retData.toArray(new ListGridRecord[retData.size()]));
                searchResultFileList.setDataSource(ds);
                searchResultFileList.sort();
                searchResultFileList.fetchData();
            }
        });
    }

    /**
     * A data source associated with a ListGrid that displays file search results.
     */
    private static class SearchFileListDS extends DataSource {

        /** Initialize the instance with null. */
        private static SearchFileListDS instance = null;

        /**
         * Get an instance. <br>
         * If not created, an instance will be created.
         * @return instance
         */
        public static SearchFileListDS getInstance() {
            if (instance == null) {
                instance = new SearchFileListDS("SearchFileListDS");
            }
            return instance;
        }

        /**
         * Issued by {@link #getInstance()}. <br>
         * The data source field is created.
         * @param id
         */
        public SearchFileListDS(String id) {
            setID(id);

            DataSourceTextField dsId = new DataSourceTextField("id", "ID");
            dsId.setHidden(true);
            DataSourceTextField dsFileName = new DataSourceTextField("Name", "Name");
            DataSourceTextField dsFullPath = new DataSourceTextField("fullPath", "FullPath");
            setFields(dsId, dsFileName, dsFullPath);

            setClientOnly(true);
        }
    }

    /**
     * show {@link #winModal}.
     */
    public void showWindow() {
        winModal.show();
        winModal.focusInNextTabElement();
    }

    /**
     * {@link #winModal} is discarded.
     */
    public void destroy() {
        winModal.markForDestroy();
    }
}
