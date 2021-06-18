package com.zipc.garden.webplatform.client.editor.tp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
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
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.tp.PathType;
import com.zipc.garden.model.tp.TPElement;
import com.zipc.garden.model.tp.TPPatternElement;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tp.TPTSDPattern;
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
import com.zipc.garden.webplatform.shared.NodeUtil;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * It is a Viewer that displays the created feature-pattern.
 */
public class TPViewer extends Editor implements SearchViewerInterface {

    /** ID of the project in which the FP file is managed */
    private long projectId;

    /** EMF model that summarizes the contents of feature-pattern Viewer */
    private TPRoot root;

    /** TPRoot at the time of saving */
    private TPRoot savedRoot;

    /** Overall layout of feature-pattern Viewer */
    private final Layout layout = new VLayout();

    /** Layout of the bottom of the feature-pattern Viewer screen. (List display part) */
    private VLayout tableContainer = new VLayout();

    /** Asynchronous interface to get the setting information of feature pattern viewer */
    private EditResourceServiceAsync editResourceService;

    /** Asynchronous interface to get the created feature-pattern */
    private final GenerateResourceServiceAsync genResourceService = GWT.create(GenerateResourceService.class);

    /** Class that summarizes the contents of the FP file */
    private VMFile file;

    /** Button to return to the previous page */
    private ButtonItem prevBtn = new ButtonItem("<");

    /** Text item that displays the page number */
    private StaticTextItem pageText = new StaticTextItem();

    /** Button to go to the next page */
    private ButtonItem nextBtn = new ButtonItem(">");

    /** Menu for saving the contents of feature pattern viewer */
    protected MenuItem saveItem = new MenuItem("Save");

    /** Button to apply input value and reload */
    private ButtonItem applyBtn = new ButtonItem("Apply");

    /** Maximum number of rows displayed on one page */
    private IntegerItem maxRow = new IntegerItem("max");

    /**
     * constructor.<br>
     * This is used in the scenario set editor to display a single piece of data for a functional pattern.
     */
    public TPViewer() {
        super();
    }

    /**
     * constructor.<br>
     * Executed when you open an FP file from Project Explorer.
     * @param root {@link TPViewer#root}
     * @param projectId {@link TPViewer#projectId}
     * @param file {@link TPViewer#file}
     * @param editResourceService {@link TPViewer#editResourceService}
     */
    public TPViewer(TPRoot root, long projectId, VMFile file, EditResourceServiceAsync editResourceService) {
        this.root = root;
        this.file = file;
        this.projectId = projectId;
        this.editResourceService = editResourceService;
        setSavedPosition();
    }

    /**
     * Not used.
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
                pageTransition(root.getBegin(), root.getMax());
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
    @Override
    public void create() {
        layout.setWidth100();
        layout.setHeight100();
        layout.draw();
        layout.setOverflow(Overflow.HIDDEN);

        final DynamicForm settingArea = new DynamicForm();
        settingArea.setNumCols(5);
        settingArea.setWidth100();
        settingArea.setHeight(55);
        settingArea.setColWidths(150, 50, 100, 50, "100%");

        applyBtn.setEndRow(false);
        applyBtn.setWidth("100%");

        maxRow.setStartRow(false);
        maxRow.setEndRow(false);
        maxRow.setWidth("100%");
        maxRow.setBrowserInputType("tel");
        maxRow.setKeyPressFilter("[0-9]");
        maxRow.setRequired(true);
        IntegerRangeValidator validator = new IntegerRangeValidator();
        validator.setMax(1000);
        validator.setMin(100);
        maxRow.setValidators(validator);
        maxRow.setValue(root.getMax());

        DynamicForm btnArea = new DynamicForm();
        btnArea.setNumCols(5);
        btnArea.setColWidths(30, 120, 30, 80, 80);

        prevBtn.setAlign(Alignment.CENTER);
        prevBtn.setEndRow(false);

        pageText.setAlign(Alignment.CENTER);
        pageText.setShowTitle(false);
        pageText.setValue("0 - 0 of 0");

        nextBtn.setAlign(Alignment.CENTER);
        nextBtn.setStartRow(false);
        nextBtn.setEndRow(false);

        ButtonItem settingBtn = new ButtonItem("Setting");
        settingBtn.setWidth("100%");

        Scheduler.get().scheduleDeferred(() -> {
            settingArea.setItems(settingBtn, applyBtn, maxRow);
            btnArea.setItems(prevBtn, pageText, nextBtn);
            layout.addMember(settingArea);
            layout.addMember(btnArea);
            layout.addMember(tableContainer);
            pageTransition(root.getBegin(), root.getMax());
            setDisabledToButton();
        });

        /** 以下ハンドラーの設定 */
        applyBtn.addClickHandler(e -> {
            root.setMax(maxRow.getValueAsInteger());
            root.setAll(0);
            root.setBegin(0);
            root.setEnd(0);
            root.getSettings().forEach(s -> {
                TPSetting tpSetting = (TPSetting) s;
                tpSetting.getPatterns().clear();
                tpSetting.getElements().clear();
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

        settingBtn.addClickHandler(e -> {
            ViewSettingWindow<TPRoot> viewSettingWindow = new ViewSettingWindow<TPRoot>(root, editResourceService, projectId);
            viewSettingWindow.create(tmpRoot -> {
                this.root = tmpRoot;
                doApply();
            });
        });
    }

    /**
     * Create JSON format data from TPRoot
     * @param allNum The setting value of No. If null, only single data will be displayed.
     * @param num Detail No
     * @param tpSetting Pattern data for one record
     * @param tableContainer Canvas that displays the feature-pattern data
     */
    public static void fillData(AtomicInteger allNum, int num, TPSetting tpSetting, HTMLPane tableContainer) {
        // fieldsにheaderを設定
        JSONObject jsonRoot = new JSONObject();
        JSONArray fields = new JSONArray();
        jsonRoot.put("fields", fields);
        // elementsに内容を設定
        JSONArray elements = new JSONArray();
        jsonRoot.put("elements", elements);
        List<String> pathHeader = new ArrayList<>();
        List<String> fullPathHeader = new ArrayList<>();
        for (String fullPath : tpSetting.getHeader()) {
            fullPathHeader.add(NodeUtil.getInstance().removeEscape(fullPath));
        }
        if (tpSetting.getTitle() == PathType.FULL_PATH) {
            pathHeader.addAll(fullPathHeader);
        } else {
            for (String shortestPath : NodeUtil.getInstance().getUniqueShortestPaths(tpSetting.getHeader())) {
                pathHeader.add(NodeUtil.getInstance().removeEscape(shortestPath));
            }
        }

        // 選択されているPatternElementを取得
        List<TPPatternElement> shows = tpSetting.getPatternElements().stream().filter(TPPatternElement::isChecked).collect(Collectors.toList());

        // fieldに幅や右寄せを設定
        List<JSONObject> fieldList = new ArrayList<JSONObject>();
        JSONObject noObj = new JSONObject();
        noObj.put("key", new JSONString("No"));
        noObj.put("thStyle", new JSONString("width:50px"));
        noObj.put("tdClass", new JSONString("text-right"));
        fieldList.add(noObj);
        if (allNum != null) {
            JSONObject detailNoObj = new JSONObject();
            detailNoObj.put("key", new JSONString("DetailNo"));
            detailNoObj.put("thStyle", new JSONString("width:50px"));
            detailNoObj.put("tdClass", new JSONString("text-right"));
            fieldList.add(detailNoObj);
        }
        fieldList.addAll(shows.stream().map(show -> {
            JSONObject showObj = new JSONObject();
            showObj.put("key", new JSONString(show.getName()));
            showObj.put("thStyle", new JSONString("width:50px"));
            return showObj;
        }).collect(Collectors.toList()));
        fieldList.addAll(pathHeader.stream().map(p -> {
            JSONObject jsObj = new JSONObject();
            jsObj.put("key", new JSONString(p));
            return jsObj;
        }).collect(Collectors.toList()));
        for (int i = 0; i < fieldList.size(); i++) {
            fields.set(i, fieldList.get(i));
        }

        // 内容の設定
        for (int j = 0; j < tpSetting.getPatterns().size(); j++) {
            JSONObject tpElement = new JSONObject();
            TPTSDPattern pattern = tpSetting.getPatterns().get(j);
            if (allNum != null) {
                tpElement.put("No", new JSONNumber(allNum.incrementAndGet()));
                tpElement.put("DetailNo", new JSONString(num + "-" + pattern.getId()));
            } else {
                tpElement.put("No", new JSONString(pattern.getId()));
            }

            shows.forEach(show -> tpElement.put(show.getName(), new JSONString("")));
            pattern.getPatternElements().forEach(el -> tpElement.put(el.getName(), new JSONString(el.getValue())));

            pathHeader.forEach(val -> tpElement.put(val, new JSONString("")));
            for (TPElement tp : pattern.getElements()) {
                List<String> fullPath = NodeUtil.getInstance().splitNode(tp.getFullPath());
                List<String> simpPath = NodeUtil.getInstance().splitNode(tp.getSimplePath());
                String parameter = String.join(".", fullPath.subList(0, fullPath.size() - 1));
                if (tpSetting.getTitle() == PathType.SIMPLE_PATH) {
                    int indexOf = fullPathHeader.indexOf(parameter);
                    if (indexOf != -1) {
                        parameter = pathHeader.get(indexOf);
                    }
                }
                String value = simpPath.get(simpPath.size() - 1);
                if (tpElement.containsKey(parameter)) {
                    String tpElementVal = tpElement.get(parameter).isString().stringValue();
                    value = "".equals(tpElementVal) ? value : tpElementVal + ", " + value;
                }
                tpElement.put(parameter, new JSONString(value));
            }
            pathHeader.stream().filter(col -> tpElement.get(col) == null).forEach(col -> tpElement.put(col, new JSONString("")));
            elements.set(j, tpElement);
        }

        /*
         * BootstrapVue の テーブルをビューに入れる b-table の使い方は下記のURLを参照してください： https://bootstrap-vue.js.org/docs/components/table/
         */
        StringBuilder innerHtml = new StringBuilder();
        innerHtml.append("<b-table style=\"margin-bottom: -1px;\"");
        innerHtml.append("    :striped=\"striped\"");
        innerHtml.append("    :bordered=\"bordered\"");
        innerHtml.append("    :small=\"small\"");
        innerHtml.append("    :hover=\"hover\"");
        innerHtml.append("    :no-border-collapse=\"noCollapse\"");
        innerHtml.append("    :fields=\"fields\"");
        innerHtml.append("    :items=\"items\"");
        innerHtml.append("></b-table>");

        Element elm = Document.get().getElementById(tableContainer.getDOM().getId());

        elm.setInnerHTML(innerHtml.toString());
        drawTable(jsonRoot, tableContainer);
    }

    /**
     * Process to create an instance of Vue.js and display a table.
     * @param jsonRoot feature pattern data in JSON format
     * @param tableContainer Canvas that displays the feature-pattern data
     * @see <a href="http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsJSNI.html">GWT JSNI</a>
     */
    private static void drawTable(JSONObject jsonRoot, HTMLPane tableContainer) {
        JSONObject options = new JSONObject();
        options.put("el", new JSONString("#" + tableContainer.getDOM().getId()));
        JSONObject data = new JSONObject();
        data.put("striped", JSONBoolean.getInstance(true));
        data.put("bordered", JSONBoolean.getInstance(true));
        data.put("small", JSONBoolean.getInstance(true));
        data.put("hover", JSONBoolean.getInstance(true));
        data.put("noCollapse", JSONBoolean.getInstance(true));
        data.put("fields", jsonRoot.get("fields").isArray());
        data.put("items", jsonRoot.get("elements").isArray());
        options.put("data", data);
        new Vue(options.getJavaScriptObject());
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
                if (result == null) {
                    SC.warn("The result could not be obtained.");
                    return;
                }
                BinaryResourceImpl r = new BinaryResourceImpl();
                ByteArrayInputStream bi = new ByteArrayInputStream(result);
                final TPRoot tpRoot;
                try {
                    r.load(bi, EditOptions.getDefaultLoadOptions());
                    tpRoot = (TPRoot) r.getContents().get(0);
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
                                return;
                            }
                        }
                        showContents(tpRoot, argRecordCount);
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
     * The acquired TPRoot contents are displayed in a list.
     * @param tpRoot TPRoot information obtained from RDF
     * @param argRecordCount Maximum number of rows displayed on one page
     */
    void showContents(TPRoot tpRoot, int argRecordCount) {
        root = tpRoot;
        root.setMax(argRecordCount);
        AtomicInteger allNum = new AtomicInteger(root.getBegin());
        tableContainer.markForDestroy();
        tableContainer = new VLayout();
        layout.addMember(tableContainer);
        tableContainer.setHeight("*");
        tableContainer.setOverflow(Overflow.AUTO);
        tableContainer.draw();
        for (int num = 0; num < tpRoot.getSettings().size(); num++) {
            TPSetting setting = (TPSetting) tpRoot.getSettings().get(num);
            if (setting.getPatterns().size() <= 0) {
                continue;
            }
            HTMLPane table = new HTMLPane();
            table.setPosition(Positioning.RELATIVE);
            tableContainer.addMember(table);
            table.draw();

            fillData(allNum, num + 1, setting, table);

        }

        double num = root.getBegin() + root.getMax();
        num = num > root.getAll() ? root.getAll() : num;
        pageText.setValue((root.getBegin() + 1) + " - " + num + " of " + root.getAll());
        setDisabledToButton();
    }

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
