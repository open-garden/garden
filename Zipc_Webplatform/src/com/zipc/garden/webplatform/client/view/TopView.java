package com.zipc.garden.webplatform.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragLeaveHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceImageField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.util.ValueCallback;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClearEvent;
import com.smartgwt.client.widgets.events.ClearHandler;
import com.smartgwt.client.widgets.events.KeyDownEvent;
import com.smartgwt.client.widgets.events.KeyDownHandler;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellContextClickEvent;
import com.smartgwt.client.widgets.grid.events.CellContextClickHandler;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemSeparator;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.webplatform.client.core.screentransition.FrameBase;
import com.zipc.garden.webplatform.client.core.screentransition.FrameManager;
import com.zipc.garden.webplatform.client.core.screentransition.ViewDefine;
import com.zipc.garden.webplatform.client.service.ProjectService;
import com.zipc.garden.webplatform.client.service.ProjectServiceAsync;
import com.zipc.garden.webplatform.client.view.part.HeaderViewPart;
import com.zipc.garden.webplatform.client.view.part.ViewPartBase;
import com.zipc.garden.webplatform.client.view.viz.Promise;
import com.zipc.garden.webplatform.shared.ProjectInfo;

/**
 * <pre>
 * This class is a class that summarizes the processing of the top screen. 
 * A list of projects available to the logged-in user is displayed.
 * You can add / delete projects, edit names, etc.
 * </pre>
 */
public class TopView extends FrameBase {

    /** Layout to display a list of projects */
    private Layout body;

    /** When pressed, a modal dialog for creating a new project will be displayed. */
    private LinkItem createProjectLink;

    /** The available projects are displayed in this list grid. */
    private ListGrid projectGrid;

    /** Used for fuzzy search of project list grid. */
    private TextItem searchItem;

    /** The ID field of the project list grid. */
    private ListGridField idField;

    /** The name field of the project list grid. */
    private ListGridField nameField;

    /** Data source for {@link #projectGrid} */
    private DataSource ds;

    /** Context menu for cells displayed in {@link #projectGrid} */
    private Menu menu;

    /** Menu that transitions to the project explorer screen */
    private MenuItem openItem;

    /** Menu to delete the specified project */
    private MenuItem deleteItem;

    /** Menu for renaming the specified project */
    private MenuItem renameItem;

    /** Menu for transitioning to the screen for editing the information of the specified project \ */
    private MenuItem settingsItem;

    /** Menu to download the specified project and the folders and files created in the project */
    private MenuItem downloadItem;

    /** The image field of the project list grid. */
    private ListGridField imgField;

    /** Asynchronous interface to get a list of projects available to logged-in users */
    private ProjectServiceAsync projectService;

    /**
     * A variable that holds the created DomHandler. <br>
     * Used for drag and drop. <br>
     * The handler will be deleted when this screen becomes inactive.
     */
    private List<HandlerRegistration> rootPanelHandlers = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void entry() {
        projectService = GWT.create(ProjectService.class);
    }

    /**
     * <pre>
     * {@inheritDoc}
     *  
     * VLayout will be created and returned.
     * </pre>
     */
    @Override
    public Layout createLayout() {
        return new VLayout();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Canvas[] createCanvases() {
        getLayout().setHeight100();
        getLayout().setWidth100();

        body = new VLayout();
        body.setWidth100();
        body.setHeight100();
        body.setDefaultLayoutAlign(Alignment.CENTER);

        ds = ProjectDS.getInstance();

        HLayout hl = new HLayout();
        hl.setHeight("*");
        hl.setWidth("98%");
        DynamicForm projectForm = new DynamicForm();
        projectForm.setNumCols(1);

        createProjectLink = new LinkItem();
        createProjectLink.setShowTitle(false);
        createProjectLink.setLinkTitle("Create New Project");

        projectForm.setFields(createProjectLink);

        DynamicForm searchForm = new DynamicForm();
        searchForm.setNumCols(2);
        searchItem = new TextItem("Search");
        searchItem.setAlign(Alignment.RIGHT);

        searchForm.setFields(searchItem);

        hl.addMembers(projectForm, new LayoutSpacer(), searchForm);

        projectGrid = new ListGrid();
        projectGrid.setWidth("98%");
        projectGrid.setHeight100();
        projectGrid.setCanEdit(false);
        projectGrid.setShowSortArrow(SortArrow.FIELD);
        projectGrid.setShowHeaderContextMenu(false);
        projectGrid.setShowHeaderMenuButton(false);
        projectGrid.setCanResizeFields(false);
        projectGrid.setCanReorderFields(false);
        projectGrid.setShowAllRecords(true);
        projectGrid.setLeaveScrollbarGap(false);
        projectGrid.setEnforceVClipping(true);
        projectGrid.setWrapCells(true);
        projectGrid.setFixedRecordHeights(false);

        idField = new ListGridField("projectId", "ID");
        idField.setType(ListGridFieldType.INTEGER);
        idField.setHidden(true);
        nameField = new ListGridField("projectName", "Name");
        nameField.setType(ListGridFieldType.TEXT);

        ListGridField descField = new ListGridField("projectDesc", "Description");
        descField.setType(ListGridFieldType.TEXT);
        imgField = new ListGridField("projectImg", "Image");
        imgField.setType(ListGridFieldType.IMAGE);

        projectGrid.setFields(new ListGridField[] { idField, nameField, descField, imgField });

        body.addMembers(hl, projectGrid, new LayoutSpacer("100%", 10));

        body.addClearHandler(new ClearHandler() {

            @Override
            public void onClear(ClearEvent event) {
                rootPanelHandlers.forEach(h -> h.removeHandler());
            }
        });

        menu = new Menu();
        openItem = new MenuItem("Open");
        deleteItem = new MenuItem("Delete");
        renameItem = new MenuItem("Rename");
        settingsItem = new MenuItem("Settings");
        downloadItem = new MenuItem("Download");

        ViewPartBase header = new HeaderViewPart(getParam());

        return new Canvas[] { header.getLayout(), body };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {

        searchItem.addKeyUpHandler(new KeyUpHandler() {

            @Override
            public void onKeyUp(KeyUpEvent event) {
                String key = event.getKeyName();
                if (key.equals(KeyNames.BACKSPACE) || key.equals(KeyNames.DEL)) {
                    searchItem.fireEvent(new ChangedEvent(searchItem.getJsObj()));
                }
            }
        });
        searchItem.addChangedHandler(new ChangedHandler() {

            @Override
            public void onChanged(ChangedEvent event) {
                String searchVal = event.getValue() != null ? event.getValue().toString() : "";
                Criteria criteria = new Criteria();
                criteria.addCriteria("projectName", searchVal);
                projectGrid.filterData(criteria);
            }
        });

        createProjectLink.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                SC.askforValue("New Project", "Please enter the new project name.", new ValueCallback() {
                    @Override
                    public void execute(String value) {
                        if (value == null) {
                            return;
                        } else if ("".equals(value)) {
                            onClick(event);
                            SC.warn("<br>Name field is empty");
                            return;
                        }
                        projectService.createProject(value, new AsyncCallback<Void>() {
                            @Override
                            public void onSuccess(Void result) {
                                loadProjects();
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

        projectGrid.addCellContextClickHandler(new CellContextClickHandler() {
            @Override
            public void onCellContextClick(CellContextClickEvent event) {
                event.cancel();
                if (projectGrid == null || projectGrid.getSelectedRecords().length <= 0) {
                    return;
                } else if (projectGrid.getSelectedRecords().length > 1) {
                    menu.setItems(deleteItem, downloadItem);
                } else {
                    // menu.setItems(openItem, deleteItem, renameItem, new MenuItemSeparator(), settingsItem, new
                    // MenuItemSeparator(), newModelingItem);
                    menu.setItems(openItem, deleteItem, renameItem, new MenuItemSeparator(), settingsItem, downloadItem);
                }

                menu.moveTo(event.getX(), event.getY());
                menu.show();
            }
        });

        openItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                projectGrid.fireEvent(new CellDoubleClickEvent(projectGrid.getJsObj()));
            }
        });

        deleteItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                String msg;
                ListGridRecord[] records = projectGrid.getSelectedRecords();
                if (records.length <= 1) {
                    msg = "Are you sure you want to delete project <strong>'" + projectGrid.getSelectedRecord().getAttribute("projectName") + "'</strong>?";
                } else {
                    msg = "Are you sure you want to delete  <strong> " + records.length + " projects </strong>?";
                }

                SC.confirm("Delete", msg, new BooleanCallback() {
                    @Override
                    public void execute(Boolean value) {
                        if (value == null || !value) {
                            return;
                        }
                        List<Long> projectIdList = new ArrayList<Long>();
                        for (ListGridRecord listGridRecord : records) {
                            projectIdList.add(listGridRecord.getAttributeAsLong("projectId"));
                        }

                        projectService.removeProject(projectIdList, new AsyncCallback<Void>() {

                            @Override
                            public void onSuccess(Void result) {
                                loadProjects();
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                SC.warn(caught.getMessage());
                            }
                        });
                    };
                });
            }
        });

        renameItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                final Window winModal = new Window();
                winModal.setHeight(100);
                winModal.setWidth(310);
                winModal.setTitle("Rename Project");
                winModal.setShowMinimizeButton(false);
                winModal.setIsModal(true);
                winModal.setShowModalMask(true);
                winModal.setKeepInParentRect(true);
                winModal.setAutoCenter(true);
                winModal.addCloseClickHandler(e -> winModal.markForDestroy());

                DynamicForm form = new DynamicForm();
                form.setHeight100();
                form.setWidth100();
                form.setPadding(5);
                form.setNumCols(5);
                form.setColWidths(70, 70, 5, 70, 70);
                form.setLayoutAlign(VerticalAlignment.BOTTOM);
                form.setAutoFocus(true);

                TextItem renameText = new TextItem();
                renameText.setTitle("New Name");
                renameText.setTitleColSpan(1);
                renameText.setColSpan(4);
                ListGridRecord record = projectGrid.getSelectedRecord();
                String projectName = record.getAttribute("projectName");
                renameText.setValue(projectName);
                renameText.setSelectOnFocus(true);

                ButtonItem okButton = new ButtonItem();
                okButton.setTitle("OK");
                okButton.setColSpan(2);
                okButton.setWidth(80);
                okButton.setAlign(Alignment.RIGHT);
                okButton.setStartRow(false);
                okButton.setEndRow(false);
                okButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
                    @Override
                    public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                        if (renameText.getValueAsString() == null || "".equals(renameText.getValueAsString())) {
                            SC.warn("Name field is empty");
                            return;
                        }
                        Long projectId = projectGrid.getSelectedRecord().getAttributeAsLong("projectId");
                        projectService.renameProject(projectId, renameText.getValueAsString(), new AsyncCallback<Void>() {

                            @Override
                            public void onSuccess(Void result) {
                                loadProjects();
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                SC.warn(caught.getMessage());
                            }
                        });
                        winModal.markForDestroy();
                    }
                });
                ButtonItem canselButton = new ButtonItem();
                canselButton.setTitle("Cancel");
                canselButton.setColSpan(2);
                canselButton.setWidth(80);
                canselButton.setStartRow(false);
                canselButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

                    @Override
                    public void onClick(ClickEvent event) {
                        winModal.markForDestroy();
                    }
                });
                SpacerItem space = new SpacerItem();
                space.setWidth(5);
                form.setFields(renameText, okButton, space, canselButton);
                form.addKeyPressHandler(new KeyPressHandler() {
                    @Override
                    public void onKeyPress(KeyPressEvent event) {
                        if (KeyNames.ENTER.equals(event.getKeyName())) {
                            okButton.fireEvent(new com.smartgwt.client.widgets.form.fields.events.ClickEvent(okButton.getJsObj()));
                        }
                    }
                });
                winModal.addItem(form);
                winModal.show();
            }
        });

        settingsItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                String projectName = projectGrid.getSelectedRecord().getAttributeAsString("projectName");
                Long projectId = projectGrid.getSelectedRecord().getAttributeAsLong("projectId");
                Long rootId = projectGrid.getSelectedRecord().getAttributeAsLong("rootDirId");

                Map<String, String> map = new HashMap<String, String>();
                map.put("id", projectId.toString());
                map.put("name", projectName);
                map.put("rootId", rootId.toString());
                map.put("setting", "Settings");
                FrameManager.getInstance().transitionTo(ViewDefine.PROJECT, map);
            }
        });

        downloadItem.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                String projectIds = Arrays.stream(projectGrid.getSelectedRecords()).map(record -> record.getAttributeAsString("projectId")).collect(Collectors.joining(","));
                if (projectIds == null || projectIds.isEmpty()) {
                    SC.warn("No project has been selected.");
                    return;
                }
                String downloadFileName = "Project";
                String fileDownloadURL = GWT.getModuleBaseURL() + "download?name=" + downloadFileName + "&ids=" + projectIds + "&type=project";
                com.google.gwt.user.client.Window.open(fileDownloadURL, "", "");
            }
        });

        projectGrid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
            @Override
            public void onCellDoubleClick(CellDoubleClickEvent event) {
                cellDoubleClick();
            }
        });

        projectGrid.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (EventHandler.getKey().equalsIgnoreCase("Enter")) {
                    cellDoubleClick();
                }
            }
        });

        RootPanel rootPanel = RootPanel.get();
        rootPanelHandlers.add(rootPanel.addDomHandler(new DragOverHandler() {
            @Override
            public void onDragOver(DragOverEvent event) {
                if (!"yellow".equals(projectGrid.getBackgroundColor())) {
                    projectGrid.setBackgroundColor("yellow");
                }
            }
        }, DragOverEvent.getType()));
        rootPanelHandlers.add(rootPanel.addDomHandler(new DragLeaveHandler() {
            @Override
            public void onDragLeave(DragLeaveEvent event) {
                if ("yellow".equals(projectGrid.getBackgroundColor())) {
                    projectGrid.setBackgroundColor("white");
                }
            }
        }, DragLeaveEvent.getType()));
        rootPanelHandlers.add(rootPanel.addDomHandler(new com.google.gwt.event.dom.client.DropHandler() {

            @Override
            public void onDrop(com.google.gwt.event.dom.client.DropEvent event) {
                event.stopPropagation();
                event.preventDefault();

                FileUtil fileUtil = new FileUtil();
                Promise promise = fileUtil.convertToByte(event.getDataTransfer());
                promise.then(fn -> {
                    List<String> folders = new ArrayList<>(Arrays.asList(fileUtil.folderPaths));

                    // javascript からの戻りが0～255の範囲なので-128～127の範囲に補完する
                    Map<String, byte[]> files = new HashMap<>();
                    for (int i = 0; i < fileUtil.fileBytes.length; i++) {
                        byte[] datas = new byte[fileUtil.fileBytes[i].length];
                        for (int j = 0; j < fileUtil.fileBytes[i].length; j++) {
                            datas[j] = (byte) (fileUtil.fileBytes[i][j] & 0xFF);
                        }
                        files.put(fileUtil.filePaths[i], datas);
                    }
                    projectService.uploadProjects(folders, files, new AsyncCallback<Boolean>() {
                        @Override
                        public void onSuccess(Boolean result) {
                            if (result.booleanValue()) {
                                SC.say("Your project is uploaded.");
                            } else {
                                SC.warn("Some projects failed to upload.");
                            }
                            loadProjects();
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }
                    });
                });
                projectGrid.setBackgroundColor("white");
                projectGrid.redraw();
            }
        }, com.google.gwt.event.dom.client.DropEvent.getType()));
    }

    /**
     * Fired when you double-click (or press Enter) a list grid record in your project. <br>
     * Set the project information in the GET parameter and move to the project explorer screen.
     */
    void cellDoubleClick() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", projectGrid.getSelectedRecord().getAttributeAsString("rootDirId"));
        map.put("name", projectGrid.getSelectedRecord().getAttributeAsString("projectName"));
        map.put("projectId", projectGrid.getSelectedRecord().getAttributeAsString("projectId"));
        FrameManager.getInstance().transitionTo(ViewDefine.MODEL, map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialized() {
        loadProjects();
    }

    /**
     * A record will be created and returned based on the specified project information.
     * @param projectList specified project information.
     * @return Record created based on project information
     */
    private ListGridRecord[] getRecords(List<ProjectInfo> projectList) {
        ArrayList<ListGridRecord> records = new ArrayList<ListGridRecord>();
        for (ProjectInfo projectInfo : projectList) {
            ListGridRecord record = new ListGridRecord();
            record.setAttribute("projectId", projectInfo.getId());
            record.setAttribute("projectName", projectInfo.getName());
            record.setAttribute("projectDesc", projectInfo.getDescription());
            record.setAttribute("rootDirId", projectInfo.getRootDirId());
            if (projectInfo.getImageData() != null) {
                Image img = new Image(projectInfo.getImageData());
                img.getElement().setAttribute("width", String.valueOf(projectInfo.getImageWidth()));
                img.getElement().setAttribute("height", String.valueOf(projectInfo.getImageHeight()));
                record.setAttribute("projectImg", img.getElement());
            }

            records.add(record);
        }

        return records.toArray(new ListGridRecord[records.size()]);
    }

    /**
     * Data source class related to the list grid displaying the project
     */
    private static class ProjectDS extends DataSource {

        /** instance */
        private static ProjectDS instance = null;

        /**
         * Get an instance. <br>
         * If the instance has not been created, create one and get it.
         * @return {@link #instance}
         */
        public static ProjectDS getInstance() {
            if (instance == null) {
                instance = new ProjectDS("projectDS");
            }
            return instance;
        }

        /**
         * The fields of the data source are set.
         * @param id Unique ID specified when creating the instance
         */
        public ProjectDS(String id) {
            setID(id);

            DataSourceTextField dsfId = new DataSourceTextField("projectId", "ID");
            dsfId.setHidden(true);
            DataSourceTextField dsfName = new DataSourceTextField("projectName", "Name");
            DataSourceTextField dsfDescription = new DataSourceTextField("projectDesc", "Description");
            DataSourceImageField dsfImage = new DataSourceImageField("projectImg", "Image");
            setFields(dsfId, dsfName, dsfDescription, dsfImage);

            setClientOnly(true);
        }
    }

    /**
     * Load the available projects from the project table and display them in the list grid.
     */
    public void loadProjects() {
        projectService.getProjects(new AsyncCallback<List<ProjectInfo>>() {
            @Override
            public void onSuccess(List<ProjectInfo> result) {
                ds.setTestData(getRecords(result));
                projectGrid.setDataSource(ds);
                projectGrid.fetchData();
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }
        });
    }
}
