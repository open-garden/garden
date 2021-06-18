package com.zipc.garden.webplatform.client.editor;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.zipc.garden.model.bp.BPFactory;
import com.zipc.garden.model.bp.BPRoot;
import com.zipc.garden.model.bps.BPSRoot;
import com.zipc.garden.model.cb.CBRoot;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.model.scs.SCSFactory;
import com.zipc.garden.model.scs.SCSRoot;
import com.zipc.garden.model.tp.TPFactory;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tps.TPSRoot;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler2;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.NodeUtil;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * A class that manages the process when the AddFile button is pressed.<br>
 * Applies to TPViewer, BPViewer, SCSViewer
 */
public class AddFileWindow {

    /** Stores the active TPRoot or BPRoot or SCSRoot. */
    private final AbstractRootElement root;

    /** Class for asynchronous call from client */
    private final EditResourceServiceAsync editResourceService;

    /** Active project ID */
    private final long projectId;

    /**
     * constructor
     * @param root
     * @param editResourceService
     * @param projectId
     */
    public AddFileWindow(AbstractRootElement root, EditResourceServiceAsync editResourceService, long projectId) {
        this.root = root;
        this.editResourceService = editResourceService;
        this.projectId = projectId;
    }

    /**
     * Create a window when the FP or BP or SCS AddFile button is pressed
     * @param root
     * @param editResourceService
     * @param projectId
     * @param handler
     */
    public void create(PostProcessHandler2<AbstractSetting> handler) {
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

        final VLayout formlayout = new VLayout();
        formlayout.setWidth100();
        formlayout.setHeight100();

        final DynamicForm searchForm = new DynamicForm();
        searchForm.setNumCols(2);
        searchForm.setBackgroundColor("white");
        searchForm.setMargin(3);

        // 検索textbox
        final TextItem searchItem = new TextItem("Search");
        searchForm.setFields(searchItem);

        // 依存ファイル一覧のListGrid
        final ListGrid dependentFile = new ListGrid();
        dependentFile.setBackgroundColor("white");
        dependentFile.setWidth100();
        dependentFile.setHeight100();
        dependentFile.setShowHeaderContextMenu(false);
        dependentFile.setShowHeaderMenuButton(false);
        dependentFile.setCanResizeFields(true);
        dependentFile.setCanReorderFields(false);
        dependentFile.setLeaveScrollbarGap(false);
        dependentFile.setMargin(5);

        // ListGridのFieldを作成
        final ListGridField idField = new ListGridField("id", "");
        idField.setHidden(true);
        ListGridField fileNameField = new ListGridField("fileName", "");
        fileNameField.setType(ListGridFieldType.TEXT);
        ListGridField fullPathField = new ListGridField("fullPath", "");
        fullPathField.setType(ListGridFieldType.TEXT);
        dependentFile.setFields(idField, fileNameField, fullPathField);

        // OKボタンとキャンセルボタンのレイアウト
        final HLayout hlayout = new HLayout();
        hlayout.setHeight(30);
        hlayout.setWidth100();
        hlayout.setLayoutLeftMargin(20);
        final IButton okBtn = new IButton("OK");
        okBtn.setHeight100();
        okBtn.setWidth100();
        okBtn.setMargin(5);
        final IButton cancelBtn = new IButton("CANCEL");
        cancelBtn.setHeight100();
        cancelBtn.setWidth100();
        cancelBtn.setMargin(5);
        final LayoutSpacer hspacer = new LayoutSpacer(3, "100%");
        hspacer.setBackgroundColor("white");
        hlayout.addMembers(hspacer, okBtn, hspacer, cancelBtn, hspacer);
        formlayout.addMembers(searchForm, dependentFile);
        winModal.addMember(formlayout);

        List<String> extensions;
        // 依存ファイルのListGritへのデータ設定
        if (root instanceof TPRoot) {
            extensions = Arrays.asList(new String[] { Extension.FPS.getValue() });
        } else if (root instanceof BPRoot) {
            extensions = Arrays.asList(new String[] { Extension.BPS.getValue() });
        } else if (root instanceof SCSRoot) {
            extensions = Arrays.asList(new String[] { Extension.SCSS.getValue() });
        } else {
            extensions = null;
        }

        createListGridDataForAddFile(extensions, dependentFile);

        // window表示
        winModal.show();
        winModal.getFooter().addMember(hlayout, 0);
        dependentFile.getHeader().setWidth100();

        /** 以下 ハンドラーの設定 */
        winModal.addCloseClickHandler(e -> winModal.markForDestroy());

        dependentFile.addCellDoubleClickHandler(e -> {
            dependentFile.selectRecord(e.getRecord());
            okBtn.fireEvent(new ClickEvent(okBtn.getJsObj()));
        });

        searchItem.addChangedHandler(e -> {
            String searchVal = e.getValue() != null ? e.getValue().toString() : "";
            Criteria criteria = new Criteria();
            criteria.addCriteria("fullPath", searchVal);
            dependentFile.filterData(criteria);
        });

        okBtn.addClickHandler(e -> {
            if (dependentFile.getSelectedRecord() == null) {
                e.cancel();
                return;
            }
            winModal.markForDestroy();
            // 選択状態のレコードを依存ファイルに設定する
            setSelectedRecords(dependentFile.getSelectedRecords(), handler);
        });

        cancelBtn.addClickHandler(e -> {
            winModal.markForDestroy();
        });

        dependentFile.addBodyKeyPressHandler(e -> {
            if (KeyNames.ENTER.equals(EventHandler.getKey())) {
                okBtn.fireEvent(new ClickEvent(okBtn.getJsObj()));
            }
        });

        winModal.addResizedHandler(e -> {
            formlayout.setWidth100();
        });
    }

    /**
     * Set the selected record in the dependent file
     * @param selectedRecords
     * @param handler
     */
    private void setSelectedRecords(ListGridRecord[] selectedRecords, PostProcessHandler2<AbstractSetting> handler) {
        for (ListGridRecord record : selectedRecords) {
            String uuid = record.getAttributeAsString("uuid");
            String fileNameExt = record.getAttributeAsString("fileName");
            String fileName = fileNameExt.substring(0, fileNameExt.lastIndexOf("."));
            String extension = record.getAttributeAsString("extension");

            if (handler != null) {
                editResourceService.getHash(uuid, projectId, new AsyncCallback<String>() {
                    public void onSuccess(String hash) {
                        // 選択したファイルを参照ファイルに設定する
                        Reference ref = COREFactory.eINSTANCE.createReference();
                        ref.setHash(hash);
                        ref.setRefid(uuid);
                        ref.setRefName(fileName);
                        ref.setRefExtension(extension);
                        root.getRefs().add(ref);

                        // 選択したファイルの実体を設定する
                        editResourceService.getFileContent(uuid, projectId, new AsyncCallback<byte[]>() {
                            @Override
                            public void onSuccess(byte[] result) {
                                AbstractRootElement root = EditorUtil.getInstance().convertToRootElement(result);
                                AbstractSetting setting = null;
                                if (root instanceof TPSRoot) {
                                    setting = TPFactory.eINSTANCE.createTPSetting();
                                    NodeUtil.getInstance().headerUpdate((TPSRoot) root, (TPSetting) setting);
                                } else if (root instanceof BPSRoot) {
                                    setting = BPFactory.eINSTANCE.createBPSetting();
                                } else if (root instanceof CBRoot) {
                                    setting = SCSFactory.eINSTANCE.createSCSSetting();
                                }
                                setting.setUuid(uuid);
                                setting.setSettingHash(hash);
                                setting.setAbstractRoot(root);
                                handler.execute(setting);
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
            }
        }
    }

    /**
     * Data setting to ListGrit of dependent file
     * @param extensions
     * @param dependentFile
     */
    private void createListGridDataForAddFile(List<String> extensions, ListGrid dependentFile) {
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
                    retData[i].setAttribute("extension", result.get(i).getExtensionStr());
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
     * Dependent file data source
     */
    private static class DependentFileDS extends DataSource {

        /** Dependent file data source */
        private static DependentFileDS instance = null;

        /**
         * Get dependent file data source.
         * @return data source
         */
        public static DependentFileDS getInstance() {
            if (instance == null) {
                instance = new DependentFileDS("dependentFileDS");
            }
            return instance;
        }

        /**
         * constructor<br>
         * Create the information of Field of the dependent file data source.
         * @param id
         */
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
