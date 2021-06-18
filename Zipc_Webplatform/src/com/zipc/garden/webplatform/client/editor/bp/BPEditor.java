package com.zipc.garden.webplatform.client.editor.bp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.bp.BPBehavior;
import com.zipc.garden.model.bp.BPBehaviorPattern;
import com.zipc.garden.model.bp.BPRoot;
import com.zipc.garden.model.bp.BPSetting;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.SearchViewerInterface;
import com.zipc.garden.webplatform.client.editor.ViewSettingWindow;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.service.GenerateResourceService;
import com.zipc.garden.webplatform.client.service.GenerateResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.shared.JobStatusInfo;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * It is a Viewer that displays the created behavior-pattern.
 */
public class BPEditor extends Editor implements SearchViewerInterface {

    /** Asynchronous interface to get the setting information of BPEditor */
    private final EditResourceServiceAsync editResourceService;

    /** Menu for saving the contents of BPEditor */
    protected MenuItem saveItem = new MenuItem("Save");

    /** Overall layout of BPEditor */
    private final VLayout layout = new VLayout();

    /** Layout at the top of the BPEditor screen. (Setting button, Apply button, etc.) */
    private final VLayout allArea = new VLayout();

    /** Layout of the bottom of the BPEditor screen. (List display part) */
    private VLayout dataArea;

    /** EMF model that summarizes the contents of BPEditor */
    private BPRoot root;

    /** BPRoot at the time of saving */
    private BPRoot savedRoot;

    /** ID of the project in which the BP file is managed */
    private Long projectId;

    /** Class that summarizes the contents of the BP file */
    private VMFile file;

    /** Text item that displays the page number */
    private StaticTextItem pageText;

    /** Button to apply input value and reload */
    private ButtonItem applyBtn;

    /** Button to return to the previous page */
    private ButtonItem prevBtn;

    /** Button to go to the next page */
    private ButtonItem nextBtn;

    /** Maximum number of rows displayed on one page */
    private IntegerItem maxRow;

    /** Step column title */
    public static final String STEP = "step";

    /** Asynchronous interface to get the created behavior-pattern */
    private final GenerateResourceServiceAsync genResourceService = GWT.create(GenerateResourceService.class);

    /**
     * constructor
     * @param root Root model of BP editor
     * @param projectId The project ID in which this BP is managed
     * @param file Information of BP file to be displayed
     * @param editResourceService Asynchronous interface to get the setting information of BPEditor
     */
    public BPEditor(BPRoot root, Long projectId, VMFile file, EditResourceServiceAsync editResourceService) {
        this.root = root;
        this.projectId = projectId;
        this.file = file;
        this.editResourceService = editResourceService;
        setSavedPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditManager getEditManager() {
        return null;
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
    public void save(ModelingProjectView modelingProjectView, Editor editor, Tab tab, PostProcessHandler handler) {

        if (!maxRow.validate()) {
            return;
        }
        super.save(modelingProjectView, editor, tab, new PostProcessHandler() {

            @Override
            public void execute() {
                if (handler != null) {
                    handler.execute();
                }
                pageTransition(0, root.getMax());
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doApply() {
        applyBtn.fireEvent(new ClickEvent(applyBtn.getJsObj()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowIds(List<String> patternNos, List<String> uuids) {
        if (this.root.getSettings().size() == patternNos.size()) {
            for (int i = 0; i < patternNos.size(); i++) {
                AbstractSetting setting = this.root.getSettings().get(i);
                if (setting.getUuid().equals(uuids.get(i))) {
                    setting.setPatternNos(patternNos.get(i));
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void create() {
        layout.setWidth100();
        layout.setHeight100();
        layout.draw();
        layout.setOverflow(Overflow.HIDDEN);

        allArea.setWidth100();
        allArea.setHeight100();

        DynamicForm settingArea = new DynamicForm();
        settingArea.setNumCols(5);
        settingArea.setWidth100();
        settingArea.setHeight(55);
        settingArea.setColWidths(150, 50, 100, 50, "100%");

        ButtonItem settingBtn = new ButtonItem("Setting");
        settingBtn.setWidth("100%");

        applyBtn = new ButtonItem("Apply");
        applyBtn.setEndRow(false);
        applyBtn.setWidth("100%");

        maxRow = new IntegerItem("max");
        maxRow.setStartRow(false);
        maxRow.setEndRow(false);
        maxRow.setWidth("100%");
        maxRow.setBrowserInputType("tel");
        maxRow.setKeyPressFilter("[0-9]");
        maxRow.setRequired(true);
        IntegerRangeValidator validator = new IntegerRangeValidator();
        validator.setMax(1000);
        validator.setMin(1);
        maxRow.setValidators(validator);
        maxRow.setValue(root.getMax());

        DynamicForm btnArea = new DynamicForm();
        btnArea.setNumCols(5);
        btnArea.setColWidths(30, 120, 30, 80, 80);

        prevBtn = new ButtonItem("<");
        prevBtn.setEndRow(false);
        prevBtn.setAlign(Alignment.CENTER);

        pageText = new StaticTextItem();
        pageText.setAlign(Alignment.CENTER);
        pageText.setShowTitle(false);
        pageText.setValue("0 - 0 of 0");

        nextBtn = new ButtonItem(">");
        nextBtn.setStartRow(false);
        nextBtn.setEndRow(false);
        nextBtn.setAlign(Alignment.CENTER);

        Scheduler.get().scheduleDeferred(() -> {
            settingArea.setItems(settingBtn, applyBtn, maxRow);
            btnArea.setItems(prevBtn, pageText, nextBtn);
            btnArea.setHeight(prevBtn.getHeight());
        });
        allArea.addMembers(settingArea, btnArea);

        dataArea = new VLayout();
        allArea.addMember(dataArea);
        layout.addChild(allArea);

        pageTransition(root.getBegin(), root.getMax());

        /** 以下ハンドラーの設定 */

        settingBtn.addClickHandler(e -> {
            ViewSettingWindow<BPRoot> viewSettingWindow = new ViewSettingWindow<BPRoot>(root, editResourceService, projectId);
            viewSettingWindow.create(tmpRoot -> {
                this.root = tmpRoot;
                doApply();
            });
        });

        applyBtn.addClickHandler(e -> {
            root.setMax(maxRow.getValueAsInteger());
            root.setBegin(0);
            root.setEnd(0);
            root.setAll(0);
            root.getSettings().forEach(s -> {
                ((BPSetting) s).getPattern().clear();
                ((BPSetting) s).getStateMachines().clear();
            });
            saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
        });

        maxRow.addChangedHandler(e -> {
            if (switchApplyState()) {
                return;
            }
            root.setMax(maxRow.getValueAsInteger());
        });

        prevBtn.addClickHandler(e -> {
            pageTransition(root.getBegin() - root.getMax(), root.getMax());
        });

        nextBtn.addClickHandler(e -> {
            pageTransition(root.getBegin() + root.getMax(), root.getMax());
        });

    }

    /**
     * The data acquired from RDF is displayed.
     * @param allNum First record number of page break
     * @param fileNo Pattern number
     * @param bpSetting Contents set in BP Editor
     * @param tableContainer Container that displays the acquired RDF data
     */
    private void fillData(AtomicInteger allNum, int fileNo, BPSetting bpSetting, HTMLPane tableContainer) {

        int max = getMaxBPStepColumn(bpSetting);
        List<ListGridField> fields = new ArrayList<ListGridField>();

        ListGridField patternIDField = new ListGridField("patternID");
        patternIDField.setCanSort(false);
        fields.add(patternIDField);

        ListGridField stateMachineField = new ListGridField("stateMachine");
        stateMachineField.setCanSort(false);
        fields.add(stateMachineField);

        for (int i = 0; i < max; i++) {
            int count = i + 1;
            String attributeName = STEP + count;
            ListGridField stepField = new ListGridField(attributeName, attributeName);
            stepField.setCanSort(false);
            fields.add(stepField);
        }

        Scheduler.get().scheduleDeferred(() -> {
            Element elm = Document.get().getElementById(tableContainer.getDOM().getId());
            elm.getStyle().clearHeight();
            elm.getStyle().setTop(0, Unit.PX);
            elm.setInnerHTML("<bptable :items=\"records\" :columns=\"column\"></bptable>");

            int num = root.getBegin() + root.getMax();
            num = num > root.getAll() ? root.getAll() : num;
            pageText.setValue((root.getBegin() + 1) + " - " + num + " of " + root.getAll());
            setDisabledToButton();

            // show vue table
            JSONObject jsonRoot = BPModelData.getRecordsAsJsonString(allNum, fileNo, bpSetting);
            updateBPTable(jsonRoot.getJavaScriptObject(), tableContainer.getDOM().getId(), false);
        });

    }

    /**
     * get the max length of BPStep
     * @param bpSetting BPSetting obtained by searching RDF
     * @return the max length of BPStep
     */
    public static int getMaxBPStepColumn(BPSetting bpSetting) {
        if (bpSetting.getPattern().isEmpty()) {
            return 0;
        }
        List<Integer> lst = new ArrayList<Integer>();
        for (BPBehaviorPattern bpPattern : bpSetting.getPattern()) {
            for (BPBehavior bpBehavior : bpPattern.getBehaviors()) {
                lst.add(bpBehavior.getSteps().size());
            }
        }
        int max = Collections.max(lst);
        return max;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChanged() {
        if (savedRoot.getMax() != root.getMax()) {
            return true;
        }
        if (savedRoot.getRowIds() != root.getRowIds()) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSavedPosition() {
        savedRoot = EcoreUtil.copy(root);
    }

    /**
     * The record registered in RDF is acquired and displayed.
     * @param argStartRecordOffset Record reading start offset
     * @param argRecordCount Maximum number of rows displayed on one page
     */
    private void pageTransition(int argStartRecordOffset, int argRecordCount) {
        genResourceService.getPartOfFileContent(projectId, file.getId(), argStartRecordOffset, argRecordCount, new AsyncCallback<byte[]>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(byte[] result) {
                setDisabledToButton();
                if (result == null) {
                    SC.warn("The result could not be obtained.");
                    return;
                }
                BinaryResourceImpl r = new BinaryResourceImpl();
                ByteArrayInputStream bi = new ByteArrayInputStream(result);
                final BPRoot bpRoot;
                try {
                    r.load(bi, EditOptions.getDefaultLoadOptions());
                    bpRoot = (BPRoot) r.getContents().get(0);
                } catch (IOException e) {
                    SC.warn(e.getMessage());
                    return;
                }
                genResourceService.getSettingFileJobStatusList(file.getId(), projectId, new AsyncCallback<List<JobStatusInfo>>() {
                    @Override
                    public void onSuccess(List<JobStatusInfo> result) {
                        for (JobStatusInfo jobStatusInfo : result) {
                            switch (jobStatusInfo.getStatusNum()) {
                            case Job.STATUS_NOEXEC:
                                SC.say("Cannot be acquired because it has not been executed.");
                                return;
                            case Job.STATUS_EXECUTING:
                                SC.say("Cannot be acquired because it is being processed.");
                                return;
                            case Job.STATUS_COMPLETE:
                                continue;
                            case Job.STATUS_CANCEL:
                                SC.warn("Canceled. Show only generated.");
                                break;
                            default:
                                pageText.setValue("0 - 0 of " + BigDecimal.valueOf(root.getAll()).toPlainString());
                                SC.warn(jobStatusInfo.getInfomation());
                                break;
                            }
                        }
                        showContents(bpRoot, argRecordCount);
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }
                });
            }
        });
    }

    /**
     * The acquired BPRoot contents are displayed in a list.
     * @param bpRoot BPRoot information obtained from RDF
     * @param argRecordCount Maximum number of rows displayed on one page
     */
    void showContents(BPRoot bpRoot, int argRecordCount) {
        root = bpRoot;
        root.setMax(argRecordCount);
        dataArea.markForDestroy();
        dataArea = new VLayout();
        allArea.addMember(dataArea);
        dataArea.setHeight("*");
        dataArea.setOverflow(Overflow.AUTO);
        dataArea.markForRedraw();

        AtomicInteger allNum = new AtomicInteger(root.getBegin());

        for (int i = 0; i < root.getSettings().size(); i++) {
            BPSetting setting = (BPSetting) root.getSettings().get(i);
            if (setting.getPattern().size() <= 0) {
                continue;
            }
            HTMLPane tableContainer = new HTMLPane();
            tableContainer.setStyleName("bpeditorWrapper");
            tableContainer.setPosition(Positioning.RELATIVE);
            dataArea.addMember(tableContainer);
            tableContainer.markForRedraw();
            fillData(allNum, i + 1, setting, tableContainer);
        }
    }

    /**
     * Pass the process to vue-bptable.js.
     * @param jsonRoot
     * @param appId
     * @param isCalledFromSCS
     */
    public static native void updateBPTable(JavaScriptObject jsonRoot, String appId, boolean isCalledFromSCS) /*-{
		$wnd.Vue.config.silent = true;
		$wnd._UseBPTable(jsonRoot, appId, isCalledFromSCS);
    }-*/;

    /**
     * Controls whether the page break button is disabled.
     */
    private void setDisabledToButton() {
        int num = root.getBegin() + root.getMax();
        prevBtn.setDisabled(root.getBegin() == 0);
        nextBtn.setDisabled(num >= root.getAll());
    }

    /**
     * If you enter the maximum number of lines displayed on one page incorrectly, the Apply button will be disabled.
     * @return If true, disabled
     */
    private boolean switchApplyState() {
        if (!maxRow.validate()) {
            applyBtn.setDisabled(true);
            return true;
        } else {
            applyBtn.setDisabled(false);
            return false;
        }
    }
}
