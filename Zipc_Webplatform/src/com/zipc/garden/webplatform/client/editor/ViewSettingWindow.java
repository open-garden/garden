package com.zipc.garden.webplatform.client.editor;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridComponent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.core.SettingInterface;
import com.zipc.garden.model.tp.PathType;
import com.zipc.garden.model.tp.TPFactory;
import com.zipc.garden.model.tp.TPPatternElement;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tps.TPSRoot;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler2;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.shared.JobStatusInfo;
import com.zipc.garden.webplatform.shared.NodeUtil;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * This is a class that summarizes the contents of the setting screen for the FP, BP, and SCS windows.
 */
public class ViewSettingWindow<T extends AbstractRootElement & SettingInterface> {

    /** Variable to temporarily save the contents of TPRoot, BPRoot, SCSRoot */
    private T root;

    /** Asynchronous interface to get job status, file information, etc. */
    private final EditResourceServiceAsync editResourceService;

    /** Active project ID */
    private final long projectId;

    /** Message displayed when RDF generation is not completed */
    private static final String MSG_NOT_GENERATED = "<font color=\"red\"> (Not Generated!) </font>";

    /** Default value of background color */
    private static final String BG_COLOR_DEFAULT = "#F0F0F0";

    /** Background color of deleted files */
    private static final String BG_COLOR_DELETED = "#BBBBBB";

    /** Background color when the hash value of FPS, BPS, SCSS file is changed */
    private static final String BG_COLOR_CHG_HASH = "#FFB084";

    /**
     * constructor
     * @param root One of TPRoot, BPRoot, SCSRoot
     * @param editResourceService Asynchronous interface
     * @param projectId Active project ID
     */
    public ViewSettingWindow(T root, EditResourceServiceAsync editResourceService, long projectId) {
        this.root = EcoreUtil.copy(root);
        this.editResourceService = editResourceService;
        this.projectId = projectId;
    }

    /**
     * The process of creating and starting the setting screen.
     * @param copyRootHandler Handler used for post-processing
     */
    public void create(PostProcessHandler2<T> copyRootHandler) {
        // View Setting window作成
        final Window winModal = new Window();
        winModal.setTitle("View Setting");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.setShowFooter(true);
        winModal.setShowResizer(true);
        winModal.setKeepInParentRect(true);
        winModal.setAutoCenter(true);
        winModal.setBackgroundColor("white");
        winModal.setCanDragResize(true);
        winModal.setWidth(800);
        winModal.setHeight(600);

        // sectionの大枠作成
        final SectionStack sectionStack = new SectionStack();
        // sectionを複数開ける設定
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
        sectionStack.setHeaderHeight(25);
        sectionStack.setCanReorderSections(true);
        sectionStack.setWidth100();
        sectionStack.setHeight100();
        // sectionのアコーディオン時のアニメーションON
        sectionStack.setAnimateSections(true);
        sectionStack.setOverflow(Overflow.AUTO);

        // AddFileボタン用のレイアウト
        HLayout btnLayout = new HLayout();
        btnLayout.setWidth100();
        btnLayout.setPadding(5);
        final IButton addBtn = new IButton("AddFile");

        btnLayout.addMember(addBtn);
        addBtn.setEdgeMarginSize(10);

        // OKボタンとCancelボタンのForm
        final HLayout btnForm = new HLayout();
        btnForm.setHeight(30);
        btnForm.setWidth100();
        btnForm.setLayoutLeftMargin(5);

        final IButton okBtn = new IButton("OK");
        okBtn.setHeight100();
        okBtn.setWidth100();
        okBtn.setMargin(5);

        final IButton cancelBtn = new IButton("Cancel");
        cancelBtn.setHeight100();
        cancelBtn.setWidth100();
        cancelBtn.setMargin(5);

        final LayoutSpacer hspacer = new LayoutSpacer(3, "100%");
        hspacer.setBackgroundColor("white");

        btnForm.addMembers(hspacer, okBtn, hspacer, cancelBtn, hspacer);

        winModal.addMembers(btnLayout, sectionStack);
        winModal.show();
        winModal.getFooter().addMember(btnForm, 0);

        // windowを表示した後でsectionを追加
        root.getSettings().forEach(setting -> {
            addSection(sectionStack, setting);
        });

        winModal.addCloseClickHandler(e -> winModal.markForDestroy());
        winModal.addResizedHandler(e -> winModal.getFooter().setWidth100());

        addBtn.addClickHandler(e -> {
            AddFileWindow addFileWindow = new AddFileWindow(root, editResourceService, projectId);
            addFileWindow.create(setting -> {
                addSection(sectionStack, setting);
            });
        });

        okBtn.addClickHandler(e -> {
            // OKの場合は一時保存をrootに戻す
            copyRootHandler.execute(root);
            // doApply();
            winModal.markForDestroy();
        });
        cancelBtn.addClickHandler(e -> {
            winModal.markForDestroy();
        });
    }

    /**
     * Controls the order of settings and the associated inactivity of the up and down buttons
     * @param sectionStack accordion
     */
    private void moveBtnSetDisabled(SectionStack sectionStack) {
        SectionStackSection[] sections = sectionStack.getSections();
        for (SectionStackSection section : sections) {
            int pos = sectionStack.getSectionNumber(section.getName());
            AbstractSetting setting = (AbstractSetting) section.getAttributeAsObject("setting");
            root.getSettings().remove(setting);
            root.getSettings().add(pos, setting);
            for (Canvas c : section.getControls()) {
                switch (c.getName()) {
                case "upBtn":
                    c.setDisabled(pos == 0);
                    break;
                case "downBtn":
                    c.setDisabled(pos == (sections.length - 1));
                    break;
                }
            }
        }
    }

    /**
     * When you click the reload button, the header color of the section is colored according to the existence of the hash and
     * the file, and the nodeList is updated.
     * @param setting EMF model that manages set values
     * @param section 1 section of accordion
     * @param showSelectItem Item for which "Risk importance" can be selected to be displayed in the view of TPRoot
     * @param ds Data source set in SelectItem
     * @param nodeList ListGrid that sets the order of header
     */
    private void reloadFile(AbstractSetting setting, final SectionStackSection section, SelectItem showSelectItem, DataSource ds, ListGrid nodeList) {
        editResourceService.getHash(setting.getAbstractRoot().getId(), projectId, new AsyncCallback<String>() {
            @Override
            public void onSuccess(String result) {
                setting.setSettingHash(result);
                checkFile(setting, section);
                if (ds != null && nodeList != null) {
                    editResourceService.getFileContent(setting.getUuid(), projectId, new AsyncCallback<byte[]>() {
                        @Override
                        public void onSuccess(byte[] result) {
                            AbstractRootElement root = EditorUtil.getInstance().convertToRootElement(result);
                            if (root instanceof TPSRoot) {
                                NodeUtil.getInstance().patternElementUpdate((TPSRoot) root, (TPSetting) setting);
                                setShowListGrid(showSelectItem, ds, (TPSetting) setting);
                                NodeUtil.getInstance().headerUpdate((TPSRoot) root, (TPSetting) setting);
                                setHeaderListGrid(nodeList, (TPSetting) setting);
                            }
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }
        });

    }

    /**
     * When the ViewSetting is displayed, the color of the section header is colored according to whether hash or a file exists.
     * @param setting EMF model that manages set values
     * @param section 1 section of accordion
     */
    private void checkFile(AbstractSetting setting, final SectionStackSection section) {
        editResourceService.getVMFile(setting.getAbstractRoot().getId(), projectId, null, new AsyncCallback<VMFile>() {
            @Override
            public void onSuccess(VMFile vmFile) {
                editResourceService.getJobStatusInfo(vmFile.getId(), setting.getSettingHash(), new AsyncCallback<JobStatusInfo>() {
                    @Override
                    public void onSuccess(JobStatusInfo jobStatusInfo) {
                        String title = vmFile.getName() + "." + vmFile.getExtensionStr();
                        if (jobStatusInfo == null || jobStatusInfo.getStatusNum() != Job.STATUS_COMPLETE) {
                            title += MSG_NOT_GENERATED;
                        }
                        section.setTitle(title);
                        if (vmFile.getDeleteFlg()) {
                            section.getSectionHeader().setBackgroundColor(BG_COLOR_DELETED);
                        } else {
                            editResourceService.getHash(setting.getAbstractRoot().getId(), projectId, new AsyncCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    if (!setting.getSettingHash().equals(result)) {
                                        section.getSectionHeader().setBackgroundColor(BG_COLOR_CHG_HASH);
                                    } else {
                                        section.getSectionHeader().setBackgroundColor(BG_COLOR_DEFAULT);
                                    }
                                }

                                @Override
                                public void onFailure(Throwable caught) {
                                    SC.warn(caught.getMessage());
                                }
                            });
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
    }

    /**
     * The process of adding sections to the accordion
     * @param sectionStack accordion
     * @param setting EMF model that manages set values
     */
    private void addSection(final SectionStack sectionStack, AbstractSetting setting) {
        // 新しいsection
        final SectionStackSection section = new SectionStackSection();
        // 順序変更時に使用するためsettingを保持
        section.setAttribute("setting", setting);
        // sectionの初期表示が閉じた状態
        section.setExpanded(false);
        // sectionのHeader色付け
        checkFile(setting, section);

        // sectionのHeaderの×ボタン(section削除のボタン)
        final ImgButton removeBtn = new ImgButton();
        removeBtn.setSrc("[SKIN]TransferIcons/delete.png");
        removeBtn.setShowDown(false);
        removeBtn.setSize(16);

        // sectionのHeaderの▲ボタン(sectionを上に移動させるボタン)
        final ImgButton upBtn = new ImgButton();
        upBtn.setSrc("[SKIN]TransferIcons/up.png");
        upBtn.setName("upBtn");
        upBtn.setShowDown(false);
        upBtn.setSize(16);

        // sectionのHeaderの▼ボタン(sectionを下に移動させるボタン)
        final ImgButton downBtn = new ImgButton();
        downBtn.setSrc("[SKIN]TransferIcons/down.png");
        downBtn.setName("downBtn");
        downBtn.setShowDown(false);
        downBtn.setSize(16);

        section.setControls(upBtn, downBtn, removeBtn);
        sectionStack.addSection(section);

        upBtn.addClickHandler(e -> {
            int pos = sectionStack.getSectionNumber(section.getName());
            sectionStack.moveSection(section.getID(), pos - 1);
            moveBtnSetDisabled(sectionStack);
        });

        downBtn.addClickHandler(e -> {
            int pos = sectionStack.getSectionNumber(section.getName());
            sectionStack.moveSection(section.getID(), pos + 1);
            moveBtnSetDisabled(sectionStack);
        });

        sectionStack.addDropHandler(e -> {
            Scheduler.get().scheduleDeferred(() -> moveBtnSetDisabled(sectionStack));
        });

        removeBtn.addClickHandler(e -> {
            root.getSettings().removeIf(s -> s == setting);
            root.getRefs().removeIf(ref -> ref.getRefid().equals(setting.getUuid()));
            sectionStack.removeSection(section.getID());
        });

        moveBtnSetDisabled(sectionStack);

        // sectionの中のレイアウト
        final VLayout sectionLayout = new VLayout();
        sectionLayout.setWidth100();
        sectionLayout.setHeight100();
        sectionLayout.setShowEdges(true);
        sectionLayout.setEdgeSize(1);

        // sectionの中を設定
        final DynamicForm ptnForm = new DynamicForm();
        ptnForm.setMargin(5);
        ptnForm.setNumCols(2);
        ptnForm.setColWidths(100, "100%");
        // パターンのtextbox
        final TextItem ptnItem = new TextItem("pattern", "pattern");
        ptnItem.setWrapTitle(false);
        ptnItem.setWidth("100%");
        ptnItem.setDefaultValue(setting.getPatternNos());
        // 入力値のチェック
        RegExpValidator regExpValidator = new RegExpValidator("^(|([0-9])+(,[0-9]+)*(\\r\\n|\\n|\\r|))$");
        ptnItem.setValidators(regExpValidator);
        ptnItem.addChangedHandler(e -> {
            if (ptnItem.validate()) {
                setting.setPatternNos(ptnItem.getValueAsString());
            }
        });

        // reloadボタン
        ButtonItem reloadBtn = new ButtonItem("Reload", "Reload");
        reloadBtn.setAlign(Alignment.RIGHT);
        reloadBtn.setWidth(100);
        reloadBtn.setStartRow(false);

        if (root instanceof TPRoot) {
            TPSetting tpSetting = (TPSetting) setting;
            // pathのheaderのラジオボタン
            final RadioGroupItem pathRGItem = new RadioGroupItem();
            LinkedHashMap<Integer, String> pathType = new LinkedHashMap<Integer, String>();
            pathType.put(PathType.FULL_PATH_VALUE, "FullPath");
            pathType.put(PathType.SIMPLE_PATH_VALUE, "SimplePath");
            pathRGItem.setTitle("title");
            pathRGItem.setVertical(false);
            pathRGItem.setColSpan("*");
            pathRGItem.setValueMap(pathType);
            pathRGItem.addChangedHandler(e -> {
                tpSetting.setTitle(PathType.get((int) pathRGItem.getValue()));
            });
            pathRGItem.setDefaultValue(tpSetting.getTitle().getValue());

            PatternElementDS ds = new PatternElementDS(section.getID());

            ListGrid showListGrid = new ListGrid();
            showListGrid.setBackgroundColor("white");
            showListGrid.setWidth100();
            showListGrid.setHeight100();
            showListGrid.setShowHeaderContextMenu(false);
            showListGrid.setShowHeaderMenuButton(false);
            showListGrid.setCanResizeFields(true);
            showListGrid.setCanReorderFields(false);
            showListGrid.setLeaveScrollbarGap(false);
            showListGrid.setGridComponents(ListGridComponent.HEADER, ListGridComponent.BODY);

            SelectItem showSelectItem = new SelectItem("show", "Show Columns");
            showSelectItem.setWidth("100%");
            showSelectItem.setOptionDataSource(ds);
            showSelectItem.setDisplayField("name");
            showSelectItem.setValueField("no");
            showSelectItem.setMultiple(true);
            showSelectItem.setPickListProperties(showListGrid);
            showSelectItem.addChangedHandler(e -> {
                List<String> values = Arrays.asList(showSelectItem.getValues());
                tpSetting.getPatternElements().clear();
                Arrays.stream(ds.getCacheData()).forEach(record -> {
                    TPPatternElement patternElement = TPFactory.eINSTANCE.createTPPatternElement();
                    patternElement.setChecked(values.contains(record.getAttributeAsString("no")));
                    patternElement.setName(record.getAttributeAsString("name"));
                    patternElement.setValue(record.getAttributeAsString("value"));
                    tpSetting.getPatternElements().add(patternElement);
                });
            });
            if (tpSetting.getPatternElements().isEmpty()) {
                NodeUtil.getInstance().patternElementUpdate((TPSRoot) tpSetting.getAbstractRoot(), tpSetting);
            }
            setShowListGrid(showSelectItem, ds, tpSetting);

            // pathheaderの順序を設定するListGrid
            final ListGrid nodeList = new ListGrid();
            nodeList.setBackgroundColor("white");
            nodeList.setWidth100();
            nodeList.setHeight100();
            nodeList.setShowHeaderContextMenu(false);
            nodeList.setShowHeaderMenuButton(false);
            nodeList.setCanResizeFields(false);
            nodeList.setCanReorderFields(false);
            nodeList.setLeaveScrollbarGap(false);
            nodeList.setCanReorderRecords(true);
            nodeList.setMargin(5);
            ListGridField fullPath = new ListGridField("fullPath", "");
            fullPath.setType(ListGridFieldType.TEXT);
            ListGridField simplePath = new ListGridField("simplePath", "");
            simplePath.setType(ListGridFieldType.TEXT);
            nodeList.setFields(fullPath, simplePath);
            setHeaderListGrid(nodeList, tpSetting);
            nodeList.addSortChangedHandler(e -> {
                tpSetting.getHeader().clear();
                Arrays.stream(nodeList.getRecords()).forEach(record -> tpSetting.getHeader().add(record.getAttributeAsString("escapeFullPath")));
            });
            nodeList.addDropCompleteHandler(e -> {
                tpSetting.getHeader().clear();
                Arrays.stream(nodeList.getRecords()).forEach(record -> tpSetting.getHeader().add(record.getAttributeAsString("escapeFullPath")));
            });
            ptnForm.setItems(ptnItem, pathRGItem, showSelectItem, new SpacerItem(), reloadBtn);
            sectionLayout.addMembers(ptnForm, nodeList);

            reloadBtn.addClickHandler(e -> {
                reloadFile(setting, section, showSelectItem, ds, nodeList);
            });
        } else {
            ptnForm.setItems(ptnItem, new SpacerItem(), reloadBtn);
            sectionLayout.addMembers(ptnForm);

            reloadBtn.addClickHandler(e -> {
                reloadFile(setting, section, null, null, null);
            });

        }

        section.addItem(sectionLayout);
    }

    /**
     * Set the ListGrid that can select the "Risk importance" to be displayed in the header.
     * @param showItem Selection items for "Risk importance"
     * @param ds Data source set in SelectItem
     * @param setting EMF model that manages set values
     */
    private void setShowListGrid(SelectItem showItem, DataSource ds, TPSetting setting) {
        Arrays.stream(ds.getCacheData()).forEach(record -> ds.removeData(record));
        List<String> values = new LinkedList<>();
        ListGridRecord[] retData = new ListGridRecord[setting.getPatternElements().size()];
        for (int i = 0; i < setting.getPatternElements().size(); i++) {
            retData[i] = new ListGridRecord();
            retData[i].setAttribute("no", i);
            retData[i].setAttribute("name", setting.getPatternElements().get(i).getName());
            retData[i].setAttribute("value", setting.getPatternElements().get(i).getValue());
            if (setting.getPatternElements().get(i).isChecked()) {
                values.add(String.valueOf(i));
            }
            ds.addData(retData[i]);
        }
        showItem.setValues(values.toArray(new String[values.size()]));
    }

    /**
     * Set ListGrid that sets the order of headers.
     * @param nodeList Will be added to this list grid
     * @param setting EMF model that manages set values
     */
    private void setHeaderListGrid(ListGrid nodeList, TPSetting setting) {
        Arrays.stream(nodeList.getRecords()).forEach(record -> nodeList.removeData(record));
        List<String> shortestPaths = NodeUtil.getInstance().getUniqueShortestPaths(setting.getHeader());
        for (int i = 0; i < setting.getHeader().size(); i++) {
            ListGridRecord retData = new ListGridRecord();
            retData.setAttribute("escapeFullPath", setting.getHeader().get(i));
            retData.setAttribute("fullPath", NodeUtil.getInstance().removeEscape(setting.getHeader().get(i)));
            retData.setAttribute("simplePath", NodeUtil.getInstance().removeEscape(shortestPaths.get(i)));
            nodeList.addData(retData);
        }
    }

    /**
     * "Risk Importance" Data Source Class
     */
    private class PatternElementDS extends DataSource {
        public PatternElementDS(String id) {
            setID("patternElementDS_" + id);

            DataSourceIntegerField no = new DataSourceIntegerField("no");
            no.setHidden(true);
            no.setPrimaryKey(true);

            DataSourceTextField name = new DataSourceTextField("name", "Name");

            DataSourceTextField value = new DataSourceTextField("value", "Value");
            value.setHidden(true);

            setFields(no, name, value);
            setClientOnly(true);
        }
    }
}
