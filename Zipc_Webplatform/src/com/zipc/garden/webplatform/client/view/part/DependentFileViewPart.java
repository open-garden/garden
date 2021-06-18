package com.zipc.garden.webplatform.client.view.part;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.BodyKeyPressEvent;
import com.smartgwt.client.widgets.grid.events.BodyKeyPressHandler;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * <pre>
 * A class that creates a file selection form.
 * You can perform the following operations.
 * ・View files registered in the project.
 * ・File selection.
 * ・Reload the selected file.
 * ・Deselect files.
 * 
 * For reloading, it is necessary to create a process at the caller.
 * </pre>
 */
public class DependentFileViewPart {

    /** Caller's EMFRoot model */
    private AbstractRootElement root;

    /** project ID */
    private long projectId;

    /** Used for the height and width of the modal window screen that is launched when a file is selected. */
    private int width, height;

    /** File selection form */
    private DynamicForm dependentFileView;

    /** ListGrid displayed in the file selection modal window */
    private ListGrid dependentFile;

    /** TextItem to display the selected file */
    private TextItem selectItem;

    /** File selection button */
    private ButtonItem select;

    /** File reload button */
    private ButtonItem reload;

    /** File deselect button */
    private ButtonItem clear;

    /** Files with the extension specified here are displayed in the selection list. */
    private List<String> extensions;

    /** Asynchronous interface for getting file information */
    private final EditResourceServiceAsync editResourceService;

    /**
     * constructor. <br>
     * Create a file selection form. (No clear button, no post-processing)
     * @param title File selection form title
     * @param root {@link #root}.
     * @param projectId {@link #projectId}.
     * @param width {@link #width}.
     * @param height {@link #height}.
     * @param extensions {@link #extensions}.
     * @param editResourceService {@link #editResourceService}.
     */
    public DependentFileViewPart(String title, AbstractRootElement root, long projectId, int width, int height, List<String> extensions,
            EditResourceServiceAsync editResourceService) {
        this(title, root, projectId, width, height, extensions, editResourceService, false, null);
    }

    /**
     * constructor. <br>
     * Create a file selection form. (Clear button and post-processing can be used depending on the argument)
     * @param title File selection form title
     * @param root {@link #root}.
     * @param projectId {@link #projectId}.
     * @param width {@link #width}.
     * @param height {@link #height}.
     * @param extensions {@link #extensions}.
     * @param editResourceService {@link #editResourceService}.
     * @param showClear If True, a clear button will be displayed
     * @param handler Post-processing
     */
    public DependentFileViewPart(String title, AbstractRootElement root, long projectId, int width, int height, List<String> extensions,
            EditResourceServiceAsync editResourceService, boolean showClear, PostProcessHandler handler) {
        this.root = root;
        this.projectId = projectId;
        this.width = width;
        this.height = height;
        this.extensions = extensions;
        this.editResourceService = editResourceService;
        dependentFileView = new DynamicForm();
        dependentFileView.setWidth100();
        dependentFileView.setMargin(10);
        dependentFileView.setNumCols(4);
        selectItem = new TextItem();
        selectItem.setWidth("*");
        selectItem.setTitle(title);
        selectItem.setEndRow(false);
        selectItem.setCanEdit(false);
        selectItem.setRequired(true);

        select = new ButtonItem("select");
        select.setWidth(150);
        select.setStartRow(false);
        select.setEndRow(false);
        reload = new ButtonItem("reload");
        reload.setWidth(150);
        reload.setStartRow(false);
        reload.setEndRow(false);
        if (showClear == true) {
            clear = new ButtonItem("clear");
            clear.setWidth(150);
            clear.setStartRow(false);
            clear.setEndRow(false);
            dependentFileView.setItems(selectItem, select, reload, clear);
        } else {
            dependentFileView.setItems(selectItem, select, reload);
        }
        setFileName(handler);
    }

    /**
     * Sets the specified content to an item on the file selection form.
     * @param valueMap specified content
     */
    public void setValueMap(Map<String, String> valueMap) {
        this.selectItem.setValueMap(valueMap);
    }

    /**
     * Register the process for the Reload button click event
     * @param handler Process to be executed
     * @param reload Button to register handler
     * @return HandlerRegistration
     */
    public HandlerRegistration addReloadClickHandler(ClickHandler handler, ButtonItem reload) {
        return reload.addClickHandler(handler);
    }

    /**
     * Register the default process for the Select button click event
     * @param select Button to register handler
     * @return HandlerRegistration
     */
    public HandlerRegistration addSelectClickHandler(ButtonItem select) {
        return addSelectClickHandler(select, null);
    }

    /**
     * Register the default process for the Clear button click event
     * @param clear Button to register handler
     * @return HandlerRegistration
     */
    public HandlerRegistration addClearClickHandler(ButtonItem clear) {
        return addClearClickHandler(clear, null);
    }

    /**
     * Register the process for the clear button click event. (With post-processing.)
     * @param clear Button to register handler
     * @param postProcessHandler Processing after clearing
     * @return HandlerRegistration
     */
    public HandlerRegistration addClearClickHandler(ButtonItem clear, PostProcessHandler postProcessHandler) {
        return clear.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                root.getRefs().removeIf(ref -> extensions.contains(ref.getRefExtension()));
                Map<String, String> clearValueMap = new HashMap<String, String>();
                clearValueMap.put("clear", "");
                selectItem.setValueMap(clearValueMap);
                selectItem.setValue("clear");
                if (null != postProcessHandler) {
                    postProcessHandler.execute();
                }
            }
        });
    }

    /**
     * A click event for the select button is created.
     * @param select Clicked button
     * @param postProcessHandler Processing after select button press
     * @return HandlerRegistration
     */
    public HandlerRegistration addSelectClickHandler(ButtonItem select, PostProcessHandler postProcessHandler) {
        return select.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final Window winModal = new Window();
                winModal.setTitle("Select dependent file");
                winModal.setShowMinimizeButton(false);
                winModal.setIsModal(true);
                winModal.setShowModalMask(true);
                winModal.setShowFooter(true);
                winModal.setShowResizer(true);
                winModal.setKeepInParentRect(true);
                winModal.setAutoCenter(true);
                winModal.setBackgroundColor("white");
                winModal.setCanDragResize(true);
                winModal.setWidth((int) (width * 0.8));
                winModal.setHeight((int) (height * 0.6));
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

                dependentFile = new ListGrid();
                dependentFile.setBackgroundColor("white");
                dependentFile.setWidth100();
                dependentFile.setHeight100();
                dependentFile.setShowHeaderContextMenu(false);
                dependentFile.setShowHeaderMenuButton(false);
                dependentFile.setCanResizeFields(true);
                dependentFile.setCanReorderFields(false);
                dependentFile.setLeaveScrollbarGap(false);
                dependentFile.setMargin(5);

                ListGridField idfield = new ListGridField("id", "");
                idfield.setHidden(true);
                ListGridField fileName = new ListGridField("fileName", "");
                fileName.setType(ListGridFieldType.TEXT);
                ListGridField fullPath = new ListGridField("fullPath", "");
                fullPath.setType(ListGridFieldType.TEXT);
                ListGridField extension = new ListGridField("extension", "");
                extension.setHidden(true);
                ListGridField uuid = new ListGridField("uuid", "");
                uuid.setHidden(true);
                dependentFile.setFields(idfield, fileName, fullPath, extension, uuid);

                HLayout hlayout = new HLayout();
                hlayout.setHeight(30);
                hlayout.setWidth100();
                hlayout.setLayoutLeftMargin(20);
                IButton fileBtn = new IButton("OK");
                fileBtn.setHeight100();
                fileBtn.setWidth100();
                fileBtn.setMargin(5);
                IButton cancelBtn = new IButton("CANCEL");
                cancelBtn.setHeight100();
                cancelBtn.setWidth100();
                cancelBtn.setMargin(5);
                LayoutSpacer hspacer = new LayoutSpacer(3, "100%");
                hspacer.setBackgroundColor("white");

                hlayout.addMembers(hspacer, fileBtn, hspacer, cancelBtn, hspacer);

                dependentFile.addCellDoubleClickHandler(new CellDoubleClickHandler() {

                    @Override
                    public void onCellDoubleClick(CellDoubleClickEvent event) {
                        dependentFile.selectRecord(event.getRecord());
                        fileBtn.fireEvent(new com.smartgwt.client.widgets.events.ClickEvent(fileBtn.getJsObj()));
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

                fileBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

                    @Override
                    public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                        Record record = dependentFile.getSelectedRecord();
                        if (record == null) {
                            event.cancel();
                            return;
                        }
                        root.getRefs().removeIf(ref -> extensions.contains(ref.getRefExtension()));
                        Reference ref = COREFactory.eINSTANCE.createReference();
                        ref.setRefid(record.getAttributeAsString("uuid"));
                        ref.setRefExtension(record.getAttributeAsString("extension"));
                        root.getRefs().add(ref);
                        Map<String, String> selectValueMap = new HashMap<String, String>();
                        selectValueMap.put(record.getAttributeAsString("id"), record.getAttributeAsString("fileName"));
                        selectItem.setValueMap(selectValueMap);
                        selectItem.setValue(record.getAttributeAsString("id"));
                        selectItem.fireEvent(new ChangedEvent(selectItem.getJsObj()));
                        winModal.markForDestroy();
                        if (postProcessHandler != null) {
                            postProcessHandler.execute();
                        }
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
                            fileBtn.fireEvent(new com.smartgwt.client.widgets.events.ClickEvent(fileBtn.getJsObj()));
                        }
                    }
                });

                formlayout.addMembers(searchForm, dependentFile);
                winModal.addMember(formlayout);
                createDependentFileData(extensions);
                winModal.show();
                winModal.getFooter().addMember(hlayout, 0);

                winModal.addResizedHandler(new ResizedHandler() {
                    @Override
                    public void onResized(ResizedEvent event) {
                        formlayout.setWidth100();
                    }
                });
            }
        });
    }

    /**
     * Gets the file that matches the specified file extension from the table and displays it in {@link #dependentFileView}.
     * @param extensions specified file extension
     */
    private void createDependentFileData(List<String> extensions) {
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
                    retData[i].setAttribute("extension", result.get(i).getExtensionStr());
                    retData[i].setAttribute("uuid", result.get(i).getUuid());
                }
                setDatas(retData);
                Scheduler.get().scheduleDeferred(() -> {
                    dependentFile.selectSingleRecord(0);
                    dependentFile.focus();
                });
            }
        });
    }

    /**
     * Get and set the reference file registered in the Root model.
     * @param handler If there is no processing to execute, null can be specified
     */
    public void setFileName(PostProcessHandler handler) {
        if (root.getRefs().size() <= 0) {
            return;
        }
        Reference ref = root.getRefs().stream().filter(x -> extensions.contains(x.getRefExtension())).findFirst().orElse(null);
        if (ref != null && ref.getRefid() != null) {
            editResourceService.getVMFile(ref.getRefid(), projectId, new AsyncCallback<VMFile>() {
                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }

                @Override
                public void onSuccess(VMFile result) {
                    Map<Long, String> fileItem = new HashMap<>();
                    fileItem.put(result.getId(), result.getName() + "." + result.getExtensionStr());
                    DependentFileViewPart.this.getSelectItem().setValueMap(fileItem);
                    DependentFileViewPart.this.getSelectItem().setValue(result.getId());
                    if (handler != null) {
                        handler.execute();
                    }

                }
            });
        }
    }

    /**
     * Get the file selection form.
     * @return {@link #dependentFileView}
     */
    public DynamicForm getDependentFileView() {
        return dependentFileView;
    }

    /**
     * Set the file selection form.
     * @param dependentFileView {@link #dependentFileView}
     */
    public void setDependentFileView(DynamicForm dependentFileView) {
        this.dependentFileView = dependentFileView;
    }

    /**
     * Get the TextItem of the file selection form.
     * @return {@link #selectItem}
     */
    public TextItem getSelectItem() {
        return selectItem;
    }

    /**
     * The TextItem of the file selection form is set.
     * @param selectItem {@link #selectItem}
     */
    public void setSelectItem(TextItem selectItem) {
        this.selectItem = selectItem;
    }

    /**
     * The record of {@link #dependentFile} is fetched.
     * @return record of dependentFile
     */
    public ListGridRecord[] getDatas() {
        return dependentFile.getRecords();
    }

    /**
     * The specified record is set in {@link #dependentFile}.
     * @param datas specified record
     */
    public void setDatas(ListGridRecord[] datas) {
        DataSource ds = DependentFileDS.getInstance();
        ds.setTestData(datas);
        this.dependentFile.setDataSource(ds);
        this.dependentFile.sort();
        this.dependentFile.fetchData();
    }

    /**
     * Get the file selection button.
     * @return {@link #select}
     */
    public ButtonItem getSelect() {
        return select;
    }

    /**
     * Set the select button.
     * @param select {@link #select}
     */
    public void setSelect(ButtonItem select) {
        this.select = select;
    }

    /**
     * Get the file reload button.
     * @return {@link #reload}
     */
    public ButtonItem getReload() {
        return reload;
    }

    /**
     * Set the reload button.
     * @param reload {@link #reload}
     */
    public void setReload(ButtonItem reload) {
        this.reload = reload;
    }

    /**
     * Get the file deselect button.
     * @return {@link #clear}
     */
    public ButtonItem getClear() {
        return clear;
    }

    /**
     * Set the clear button.
     * @param clear {@link #clear}
     */
    public void setClear(ButtonItem clear) {
        this.clear = clear;
    }

    /**
     * Data source class related to {@link #dependentFile}.
     */
    private static class DependentFileDS extends DataSource {

        /** DependentFileDS instance */
        private static DependentFileDS instance = null;

        /**
         * You will get an instance of DependentFileDS.<br>
         * If the instance has not been created, it will be created and retrieved.
         * @return DependentFileDS instance
         */
        public static DependentFileDS getInstance() {
            if (instance == null) {
                instance = new DependentFileDS("dependentFileDS");
            }
            return instance;
        }

        /**
         * constructor. <br>
         * The data source field is set.
         * @param id Unique ID specified when creating the instance
         */
        public DependentFileDS(String id) {
            setID(id);

            DataSourceTextField dsId = new DataSourceTextField("id", "ID");
            dsId.setHidden(true);
            DataSourceTextField dsFileName = new DataSourceTextField("fileName", "Name");
            DataSourceTextField dsFullPath = new DataSourceTextField("fullPath", "FullPath");
            DataSourceTextField extension = new DataSourceTextField("extension", "Extension");
            extension.setHidden(true);
            DataSourceTextField uuid = new DataSourceTextField("uuid", "Uuid");
            uuid.setHidden(true);
            setFields(dsId, dsFileName, dsFullPath, extension, uuid);

            setClientOnly(true);
        }
    }

    /**
     * Set the EMFRoot model.
     * @param root {@link #root}
     */
    public void setRoot(AbstractRootElement root) {
        this.root = root;
    }
}
