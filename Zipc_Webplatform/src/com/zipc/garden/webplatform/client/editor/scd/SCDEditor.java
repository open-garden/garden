package com.zipc.garden.webplatform.client.editor.scd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.validator.IsOneOfValidator;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.scd.SCDRoot;
import com.zipc.garden.model.scs.SCSRoot;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.EditorOpener;
import com.zipc.garden.webplatform.client.editor.EditorUtil;
import com.zipc.garden.webplatform.client.editor.SearchViewerInterface;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.shared.Extension;

/**
 * Class for editor that set scenario design.<br>
 * The phases up to scenario generation are summarized.
 */
public class SCDEditor extends Editor implements EditorOpener {

    /** Attribute name. It is tied to whether the SelectItem is a mandatory selection. */
    private final String REQUIRED = "Required";

    /** Constant used for the unique field name of each Create button. */
    private final String CREATE_BTN_NAME = "CreateBtn";

    /** Constant used for the unique field name of each Open button. */
    private final String OPEN_BTN_NAME = "OpenBtn";

    /** Constant used for the unique field name of each SelectItem. */
    private final String SELECT_BOX_NAME = "SelBox";

    /** Attribute name. It is used to give a particular form the information for the next form. */
    private final String NEXT_FORMS = "NextForms";

    /** Attribute name. It is used to give a particular form the information for the prev form. */
    private final String PREV_FORMS = "PrevForms";

    /** Attribute name. It is used to give a create button the information for the file extension. */
    private final String EXTENSION = "Extension";

    /** Attribute name. It is used to give a SelectItem the file information. */
    private final String TITLE_TYPE = "TitleType";

    /** Root model of SCD editor. */
    private SCDRoot root;

    /** Root model of SCD editor when saved. */
    private SCDRoot savedRoot;

    /** The project ID in which this SCD is managed. */
    private final long projectId;

    /** Asynchronous interface used for getting file relationships and creating new files etc. */
    private final EditResourceServiceAsync editResourceService;

    /** Overall layout of SCDEditor */
    private Layout mainLayout;

    /** This is the menu for saving the SCD editor. */
    private MenuItem saveItem;

    /** It is a menu that validates the input value of the form and executes savaItem. */
    private MenuItem dummySaveItem;

    /** Class that manages the contents of the project */
    private ModelingProjectView modelingProjectView;

    /** List of selection forms */
    private List<DynamicForm> formList;

    /** Open the file selected by SelectItem */
    private MenuItem dummyOpenItem;

    /** When the Open button is pressed, the ID of the file selected by SelectItem is stored */
    private long openFileId = -1L;

    /** A variable that holds the IDs of all the files in the project that manages this SCD */
    private Map<String, Long> uuidMap;

    /** SCD editor tabs */
    private Tab tab;

    /**
     * @deprecated
     */
    @Override
    public EditManager getEditManager() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuItem getSaveItem() {
        return this.saveItem;
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
    public void addSaveHandler(ClickHandler clickHandler) {
        saveItem.addClickHandler(clickHandler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChanged() {
        for (DynamicForm form : formList) {
            SelectItem selectBox = (SelectItem) form.getField(form.getName() + SELECT_BOX_NAME);
            TitleType titleType = (TitleType) selectBox.getAttributeAsObject(TITLE_TYPE);
            String uuid = selectBox.getValueAsString();
            if (uuid != null) {
                if (!uuid.equals(TitleType.getUuid(titleType, savedRoot))) {
                    return true;
                }
            }
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
     * {@inheritDoc}
     */
    @Override
    public long getOpenFileId() {
        return openFileId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addOpenFileHandler(ClickHandler handler) {
        dummyOpenItem.addClickHandler(handler);
    }

    /**
     * constructor.<br>
     * The field variables are set.
     * @param root {@link SCDEditor#root}
     * @param tab {@link SCDEditor#tab}
     * @param projectId {@link SCDEditor#projectId}
     * @param modelingProjectView {@link SCDEditor#modelingProjectView}
     * @param editResourceService {@link SCDEditor#editResourceService}
     */
    public SCDEditor(SCDRoot root, Tab tab, long projectId, ModelingProjectView modelingProjectView, EditResourceServiceAsync editResourceService) {
        this.root = root;
        this.tab = tab;
        this.savedRoot = EcoreUtil.copy(root);
        this.modelingProjectView = modelingProjectView;
        this.editResourceService = editResourceService;
        this.formList = new ArrayList<DynamicForm>();
        this.projectId = projectId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create() {

        dummyOpenItem = new MenuItem();

        saveItem = new MenuItem("save");
        dummySaveItem = new MenuItem("save");
        mainLayout = new Layout();
        mainLayout.setMinWidth(650);

        Menu menu = new Menu();
        menu.addItem(dummySaveItem);
        mainLayout.setContextMenu(menu);

        VLayout layout = new VLayout();
        layout.setWidth100();
        layout.setOverflow(Overflow.AUTO);
        layout.setHeight100();

        mainLayout.addMember(layout);

        editResourceService.getFileUuidMap(projectId, new AsyncCallback<Map<String, Long>>() {

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(Map<String, Long> result) {
                uuidMap = result;
                // 1.ODD Design & 2.Behavior Designs
                HLayout subLayout = new HLayout();

                // 1.ODD Design
                VLayout oddLayout = new VLayout();
                oddLayout.setBorder("1px solid #808080");
                Label oddLabel = new Label("1.ODD Design");
                oddLabel.setHeight("20");
                DynamicForm odForm = createBaseForm(TitleType.OD, false);
                DynamicForm odConstForm = createBaseForm(TitleType.ODC, true, true, false, odForm);
                DynamicForm oddForm = createBaseForm(TitleType.ODD, odForm);
                oddLayout.addMembers(oddLabel, odForm, createArrow(), odConstForm, createArrow(), oddForm, createArrow());

                // 2.Behavior Designs
                VLayout behaviorLayout = new VLayout();

                behaviorLayout.setBorder("1px solid #808080");
                Label behaviorLabel = new Label("2.Behavior Design");
                behaviorLabel.setHeight("20");
                DynamicForm behaviorForm = createBaseForm(TitleType.BA, false);
                behaviorLayout.addMembers(behaviorLabel, behaviorForm, createArrow(), createArrow());

                subLayout.setMembersMargin(10);
                subLayout.addMembers(oddLayout, behaviorLayout);

                // 3.Scenario Generation
                VLayout scenarioLayout = new VLayout();
                scenarioLayout.setBorder("1px solid #808080");
                HLayout scenarioLayoutUp = new HLayout();
                Label scenarioLabel = new Label("3.Scenario Generation");
                scenarioLabel.setHeight("20");

                VLayout tsdLayout = new VLayout();
                DynamicForm tsdPatternSettingForm = createBaseForm(TitleType.FPS, odConstForm, oddForm, odForm);
                DynamicForm tsdPatternForm = createBaseForm(TitleType.FP, false, true, true, tsdPatternSettingForm);
                Label sArrow = createArrow();
                tsdLayout.addMembers(scenarioLabel, sArrow, tsdPatternSettingForm, createArrow(), tsdPatternForm, createArrow());

                VLayout behaviorGenLayout = new VLayout();
                Label spacerArrow = createArrow();
                DynamicForm behaviorPatternSettingForm = createBaseForm(TitleType.BPS, behaviorForm);
                DynamicForm behaviorPatternForm = createBaseForm(TitleType.BP, false, true, true, behaviorPatternSettingForm);

                behaviorGenLayout.addMembers(spacerArrow, behaviorPatternSettingForm, createArrow(), behaviorPatternForm, createArrow());
                scenarioLayoutUp.addMembers(tsdLayout, behaviorGenLayout);

                DynamicForm combinationSettingForm = createBaseForm(TitleType.SCSS, tsdPatternSettingForm, behaviorPatternSettingForm);
                DynamicForm scenarioSetForm = createBaseForm(TitleType.SCS, false, true, true, combinationSettingForm);
                DynamicForm logicalScenarioForm = createBaseForm(TitleType.LSC, false, true, true, scenarioSetForm);
                DynamicForm concreateScenarioForm = createBaseForm(TitleType.CSCS, false, true, true, logicalScenarioForm);

                scenarioLayout.addMembers(scenarioLayoutUp, combinationSettingForm, createArrow(), scenarioSetForm, createArrow(), logicalScenarioForm, createArrow(),
                        concreateScenarioForm);

                layout.setMembersMargin(10);
                layout.addMembers(subLayout, scenarioLayout);

                bind();

                Scheduler.get().scheduleDeferred(() -> {
                    behaviorLayout.setHeight(oddLayout.getVisibleHeight());
                    spacerArrow.setHeight(scenarioLabel.getVisibleHeight() + sArrow.getVisibleHeight());
                });

                editResourceService.getFileMap(projectId, Extension.FM.getValue(), new AsyncCallback<Map<String, String>>() {
                    @Override
                    public void onSuccess(Map<String, String> result) {
                        SelectItem item = (SelectItem) odForm.getField(odForm.getName() + SELECT_BOX_NAME);
                        item.setValueMap(result);
                        String uuid = TitleType.getUuid(TitleType.OD, root);
                        if (uuid != null && !uuid.isEmpty()) {
                            if (result.containsKey(uuid)) {
                                item.setValue(uuid);
                                Scheduler.get().scheduleDeferred(() -> {
                                    ButtonItem open = (ButtonItem) odForm.getField(odForm.getName() + OPEN_BTN_NAME);
                                    open.setDisabled(false);
                                });
                            } else {
                                item.setValue(TitleType.getFileName(TitleType.OD, root));
                            }
                        }

                        odForm.validate();
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }
                });

                editResourceService.getFileMap(projectId, Extension.ARC.getValue(), new AsyncCallback<Map<String, String>>() {
                    @Override
                    public void onSuccess(Map<String, String> result) {
                        SelectItem item = (SelectItem) behaviorForm.getField(behaviorForm.getName() + SELECT_BOX_NAME);
                        item.setValueMap(result);
                        String uuid = TitleType.getUuid(TitleType.BA, root);
                        if (uuid != null && !uuid.isEmpty()) {
                            if (result.containsKey(uuid)) {
                                item.setValue(uuid);
                                Scheduler.get().scheduleDeferred(() -> {
                                    ButtonItem open = (ButtonItem) behaviorForm.getField(behaviorForm.getName() + OPEN_BTN_NAME);
                                    open.setDisabled(false);
                                });
                            } else {
                                item.setValue(TitleType.getFileName(TitleType.BA, root));
                            }
                        }
                        behaviorForm.validate();
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
     * It is a method that creates an event when SelectItem is selected and an event when various buttons are pressed.
     */
    private void bind() {
        mainLayout.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.isCtrlKeyDown() && "S".equals(event.getKeyName())) {
                    dummySaveItem.fireEvent(new MenuItemClickEvent(dummySaveItem.getJsObj()));
                    event.cancel();
                }
            }
        });

        dummySaveItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                formList.forEach(form -> {
                    if (!form.validate()) {
                        SelectItem selectBox = (SelectItem) form.getField(form.getName() + SELECT_BOX_NAME);
                        TitleType titleType = (TitleType) selectBox.getAttributeAsObject(TITLE_TYPE);
                        SC.warn(titleType.getTitle() + " is not a valid option.");
                        return;
                    }
                });
                saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
            }
        });

        formList.forEach(form -> {
            String formName = form.getName();
            ButtonItem createBtn = (ButtonItem) form.getField(formName + CREATE_BTN_NAME);
            ButtonItem openBtn = (ButtonItem) form.getField(formName + OPEN_BTN_NAME);
            SelectItem selectBox = (SelectItem) form.getField(formName + SELECT_BOX_NAME);

            selectBox.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    JavaScriptObject obj = selectBox.getAttributeAsJavaScriptObject(PREV_FORMS);
                    @SuppressWarnings("unchecked")
                    List<DynamicForm> prevFormList = JSOHelper.convertToList(obj);
                    String extension = ((Extension) createBtn.getAttributeAsObject(EXTENSION)).getValue();

                    if (prevFormList != null && prevFormList.size() > 0) {
                        // True is stored when TC select box is selected.
                        boolean isPrevTcSelected = isPulldownSelected(prevFormList, TitleType.ODD);

                        List<Long> refIdList = new ArrayList<>();
                        for (DynamicForm prevForm : prevFormList) {
                            SelectItem prevSelectBox = (SelectItem) prevForm.getField(prevForm.getName() + SELECT_BOX_NAME);
                            TitleType prevType = (TitleType) prevForm.getField(prevForm.getName() + SELECT_BOX_NAME).getAttributeAsObject(TITLE_TYPE);
                            if (prevSelectBox.getValueAsString() != null && !prevSelectBox.getValueAsString().isEmpty()) {
                                switch (prevType) {
                                case OD:
                                    if (!isPrevTcSelected) {
                                        refIdList.add(uuidMap.get(prevSelectBox.getValueAsString()));
                                    }
                                    break;
                                case LSC:
                                    break;
                                default:
                                    refIdList.add(uuidMap.get(prevSelectBox.getValueAsString()));
                                }
                            }
                        }
                        DynamicForm prevForm = prevFormList.get(0);
                        SelectItem prevSelectBox = (SelectItem) prevForm.getField(prevForm.getName() + SELECT_BOX_NAME);
                        switch (Extension.getByCode(extension)) {
                        case LSC:
                            JavaScriptObject scssObj = prevSelectBox.getAttributeAsJavaScriptObject(PREV_FORMS);
                            DynamicForm scssForm = (DynamicForm) JSOHelper.convertToList(scssObj).get(0);
                            SelectItem scssSelectBox = (SelectItem) scssForm.getField(scssForm.getName() + SELECT_BOX_NAME);
                            String scssUuid = scssSelectBox.getValueAsString();
                            String scsUuid = prevSelectBox.getValueAsString();
                            editResourceService.getSCSRootWithLSCDefined(projectId, scsUuid, scssUuid, new AsyncCallback<Map<String, String>>() {
                                @Override
                                public void onSuccess(Map<String, String> result) {
                                    selectBox.setValueMap(result);
                                    selectBox.showPicker();
                                }

                                @Override
                                public void onFailure(Throwable caught) {
                                    SC.warn(caught.getMessage());
                                }
                            });
                            break;
                        case CSCS:
                            JavaScriptObject scsObj = prevSelectBox.getAttributeAsJavaScriptObject(PREV_FORMS);
                            DynamicForm scsForm = (DynamicForm) JSOHelper.convertToList(scsObj).get(0);
                            SelectItem scsSelectBox = (SelectItem) scsForm.getField(scsForm.getName() + SELECT_BOX_NAME);
                            long patternId = Long.parseLong(prevSelectBox.getValueAsString());

                            JavaScriptObject scssObj2 = scsSelectBox.getAttributeAsJavaScriptObject(PREV_FORMS);
                            DynamicForm scssForm2 = (DynamicForm) JSOHelper.convertToList(scssObj2).get(0);
                            SelectItem scssSelectBox2 = (SelectItem) scssForm2.getField(scssForm2.getName() + SELECT_BOX_NAME);
                            String scssUuid2 = scssSelectBox2.getValueAsString();

                            editResourceService.getCSCSFileMap(projectId, scssUuid2, patternId, new AsyncCallback<Map<String, String>>() {
                                @Override
                                public void onSuccess(Map<String, String> result) {
                                    selectBox.setValueMap(result);
                                    selectBox.showPicker();
                                }

                                @Override
                                public void onFailure(Throwable caught) {
                                    SC.warn(caught.getMessage());
                                }
                            });
                            break;
                        default:
                            editResourceService.getFileMap(projectId, refIdList, extension, new AsyncCallback<Map<String, String>>() {
                                @Override
                                public void onSuccess(Map<String, String> result) {
                                    selectBox.setValueMap(result);
                                    selectBox.showPicker();
                                }

                                @Override
                                public void onFailure(Throwable caught) {
                                    SC.warn(caught.getMessage());
                                }
                            });
                        }
                    } else {
                        editResourceService.getFileMap(projectId, extension, new AsyncCallback<Map<String, String>>() {

                            @Override
                            public void onSuccess(Map<String, String> result) {
                                selectBox.setValueMap(result);
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                SC.warn(caught.getMessage());
                            }
                        });
                    }
                    editResourceService.getFileUuidMap(projectId, new AsyncCallback<Map<String, Long>>() {
                        @Override
                        public void onSuccess(Map<String, Long> result) {
                            uuidMap = result;
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }
                    });
                }
            });
            selectBox.addChangedHandler(new ChangedHandler() {

                @Override
                public void onChanged(ChangedEvent event) {
                    ButtonItem openBtn = (ButtonItem) form.getField(form.getName() + OPEN_BTN_NAME);
                    form.validate();

                    String uuid = "";
                    if (selectBox.getValueAsString() != null && !selectBox.getValueAsString().isEmpty()) {
                        uuid = selectBox.getValueAsString();
                    }
                    TitleType titleType = (TitleType) selectBox.getAttributeAsObject(TITLE_TYPE);
                    long fileId;
                    if (titleType.equals(TitleType.LSC)) {
                        fileId = uuid.isEmpty() ? -1 : Long.parseLong(uuid);
                    } else {
                        fileId = uuidMap.get(uuid);
                    }
                    @SuppressWarnings("unchecked")
                    Map<String, String> map = selectBox.getValueMap();
                    String fileName = "";
                    if (map != null && !map.isEmpty()) {
                        fileName = map.get(uuid);
                    }
                    if (fileName == null) {
                        fileName = "";
                    }
                    TitleType.setId(titleType, root, fileId, uuid, fileName);

                    if (selectBox.getValueAsString() != null && !selectBox.getValueAsString().isEmpty()) {
                        openBtn.setDisabled(false);
                    } else {
                        openBtn.setDisabled(true);
                    }

                    JavaScriptObject nextObj = selectBox.getAttributeAsJavaScriptObject(NEXT_FORMS);

                    if (nextObj == null) {
                        return;
                    }
                    @SuppressWarnings("unchecked")
                    List<DynamicForm> nextOtherFormList = JSOHelper.convertToList(nextObj);
                    for (DynamicForm nextForm : nextOtherFormList) {
                        String nextFormName = nextForm.getName();
                        ButtonItem nextCreateBtn = (ButtonItem) nextForm.getField(nextFormName + CREATE_BTN_NAME);
                        ButtonItem nextOpenBtn = (ButtonItem) nextForm.getField(nextFormName + OPEN_BTN_NAME);
                        SelectItem nextSelectBox = (SelectItem) nextForm.getField(nextFormName + SELECT_BOX_NAME);
                        TitleType nextType = (TitleType) nextSelectBox.getAttributeAsObject(TITLE_TYPE);

                        JavaScriptObject prevOtherObj = nextSelectBox.getAttributeAsJavaScriptObject(PREV_FORMS);
                        @SuppressWarnings("unchecked")
                        List<DynamicForm> prevOtherFormList = JSOHelper.convertToList(prevOtherObj);
                        boolean prevFmFormFlag = prevOtherFormList.stream().filter(prevForm -> {
                            TitleType prevType = (TitleType) prevForm.getField(prevForm.getName() + SELECT_BOX_NAME).getAttributeAsObject(TITLE_TYPE);
                            return prevType.equals(TitleType.OD);
                        }).collect(Collectors.toList()).size() > 0;

                        boolean nextFormDisable = false;

                        // FMに関連するファイルの場合、FMが未入力の場合は使用不可にする
                        if (prevFmFormFlag) {
                            if (TitleType.OD.equals(titleType) && selectBox.getAttributeAsBoolean(REQUIRED)
                                    && (selectBox.getValueAsString() == null || selectBox.getValueAsString().isEmpty())) {
                                nextFormDisable = true;
                            }
                        }
                        // プルダウンメニューが未選択の場合、次のFormを使用不可にする
                        else if (selectBox.getAttributeAsBoolean(REQUIRED) && (selectBox.getValueAsString() == null || selectBox.getValueAsString().isEmpty())) {
                            nextFormDisable = true;
                        }
                        // プルダウンメニューが選択された場合
                        else {
                            // 次のFormのRefに当たるFormに、未入力のものがある場合、次のFormを使用不可にする
                            if (prevOtherFormList != null && prevOtherFormList.size() > 0) {
                                for (DynamicForm otherForm : prevOtherFormList) {
                                    if (otherForm != form) {
                                        SelectItem tmpSelectBox = (SelectItem) otherForm.getField(otherForm.getName() + SELECT_BOX_NAME);
                                        if (tmpSelectBox.getAttributeAsBoolean(REQUIRED)
                                                && (tmpSelectBox.getValueAsString() == null || tmpSelectBox.getValueAsString().isEmpty())) {
                                            nextFormDisable = true;
                                        }
                                    }
                                }
                            }
                        }
                        nextSelectBox.clearValue();

                        if (selectBox.getValueAsString() != null && !selectBox.getValueAsString().isEmpty()) {
                            List<Long> refIds = new ArrayList<>();
                            switch (titleType) {
                            case FPS:
                                refIds.add(root.getTSDgsFileId());
                            case BPS:
                                refIds.add(root.getBgsFileId());
                            case SCSS:
                                refIds.add(root.getCBFileId());
                                editResourceService.getFileMap(projectId, Arrays.asList(refIds.get(0)), nextType.getExtension().getValue(),
                                        new AsyncCallback<Map<String, String>>() {
                                            @Override
                                            public void onSuccess(Map<String, String> result) {
                                                if (result.size() > 1) {
                                                    result.remove("");
                                                    nextSelectBox.setValueMap(result);
                                                    nextSelectBox.setValue(result.keySet().stream().findFirst().get());
                                                    nextOpenBtn.setDisabled(false);
                                                    nextSelectBox.setDisabled(false);
                                                    nextSelectBox.fireEvent(new ChangedEvent(nextSelectBox.getJsObj()));
                                                }
                                            }

                                            @Override
                                            public void onFailure(Throwable caught) {
                                                SC.warn(caught.getMessage());
                                            }
                                        });
                                break;
                            case LSC:
                                long patternId = Long.valueOf(selectBox.getValueAsString());
                                editResourceService.getCSCSFileMap(projectId, root.getCB(), patternId, new AsyncCallback<Map<String, String>>() {
                                    @Override
                                    public void onSuccess(Map<String, String> result) {
                                        if (result.size() > 1) {
                                            result.remove("");
                                            nextSelectBox.setValueMap(result);
                                            nextSelectBox.setValue(result.keySet().stream().findFirst().get());
                                            nextOpenBtn.setDisabled(false);
                                            nextSelectBox.setDisabled(false);
                                            nextSelectBox.fireEvent(new ChangedEvent(nextSelectBox.getJsObj()));
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable caught) {
                                        SC.warn(caught.getMessage());
                                    }
                                });
                                break;
                            default:
                            }
                        }

                        if (nextFormDisable) {
                            nextCreateBtn.setDisabled(true);
                            nextOpenBtn.setDisabled(true);
                            nextSelectBox.setDisabled(true);
                            nextSelectBox.fireEvent(new ChangedEvent(nextSelectBox.getJsObj()));
                            continue;
                        }

                        nextCreateBtn.setDisabled(false);
                        nextSelectBox.setDisabled(false);
                        nextSelectBox.fireEvent(new ChangedEvent(nextSelectBox.getJsObj()));
                    }
                }
            });
            createBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    JavaScriptObject obj = selectBox.getAttributeAsJavaScriptObject(PREV_FORMS);
                    @SuppressWarnings("unchecked")
                    List<DynamicForm> prevFormList = JSOHelper.convertToList(obj);
                    if (prevFormList != null) {
                        boolean isPrevTcSelected = isPulldownSelected(prevFormList, TitleType.ODD);

                        List<Long> refIdList = new ArrayList<>();
                        for (DynamicForm prevForm : prevFormList) {
                            if (!prevForm.validate()) {
                                SC.warn("Reference file does not exist.");
                                return;
                            }
                            SelectItem prevSelectBox = (SelectItem) prevForm.getField(prevForm.getName() + SELECT_BOX_NAME);
                            TitleType prevType = (TitleType) prevForm.getField(prevForm.getName() + SELECT_BOX_NAME).getAttributeAsObject(TITLE_TYPE);
                            if (prevSelectBox.getValueAsString() != null && !prevSelectBox.getValueAsString().isEmpty()) {
                                if (prevType.equals(TitleType.OD)) {
                                    if (!isPrevTcSelected) {
                                        refIdList.add(uuidMap.get(prevSelectBox.getValueAsString()));
                                    }
                                } else {
                                    refIdList.add(uuidMap.get(prevSelectBox.getValueAsString()));
                                }
                            }
                        }
                        modelingProjectView.clickCreateDirOrFile((Extension) createBtn.getAttributeAsObject(EXTENSION), refIdList);
                    } else {
                        modelingProjectView.clickCreateDirOrFile((Extension) createBtn.getAttributeAsObject(EXTENSION));
                    }
                }
            });
            openBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    if (!form.validate()) {
                        SC.warn("This file can not open.");
                        return;
                    }
                    TitleType titleType = (TitleType) selectBox.getAttributeAsObject(TITLE_TYPE);
                    if (TitleType.LSC.equals(titleType)) {
                        openFileId = root.getSSFileId();
                        editResourceService.getFileContent(openFileId, new AsyncCallback<byte[]>() {
                            @Override
                            public void onSuccess(byte[] result) {
                                BinaryResourceImpl r = new BinaryResourceImpl();
                                ByteArrayInputStream bi = new ByteArrayInputStream(result);
                                SCSRoot scsRoot = null;
                                try {
                                    r.load(bi, EditOptions.getDefaultLoadOptions());
                                    scsRoot = (SCSRoot) r.getContents().get(0);
                                } catch (IOException e) {
                                    SC.warn(e.getMessage());
                                }

                                List<String> patternNos = new ArrayList<>();
                                List<String> uuids = new ArrayList<>();
                                scsRoot.getSettings().forEach(setting -> {
                                    if (setting.getUuid().equals(root.getCB())) {
                                        setting.setPatternNos(root.getLS());
                                    }
                                    patternNos.add(setting.getPatternNos());
                                    uuids.add(setting.getUuid());
                                });

                                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                try {
                                    r.save(outputStream, null);
                                } catch (IOException e) {
                                    throw new IllegalArgumentException(e);
                                }
                                editResourceService.saveFile(openFileId, outputStream.toByteArray(), new AsyncCallback<Void>() {
                                    @Override
                                    public void onSuccess(Void result) {
                                        SearchViewerInterface openedEditor = EditorUtil.getInstance().findAndSelectEditor(tab, openFileId);
                                        if (openedEditor != null) {
                                            openedEditor.setRowIds(patternNos, uuids);
                                            openedEditor.doApply();
                                        } else {
                                            dummyOpenItem.fireEvent(new MenuItemClickEvent(dummyOpenItem.getJsObj()));
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
                    } else {
                        openFileId = uuidMap.get(selectBox.getValueAsString());
                        dummyOpenItem.fireEvent(new MenuItemClickEvent(dummyOpenItem.getJsObj()));
                    }
                }
            });

        });

    }

    /**
     * The down arrow label is created and retrieved.
     * @return Created label
     */
    private Label createArrow() {
        Label arrow = new Label("↓");
        arrow.setHeight("*");
        arrow.setWidth100();
        arrow.setAlign(Alignment.CENTER);
        return arrow;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Layout getLayout() {
        return mainLayout;
    }

    /**
     * <pre>
     * Overloaded method for the next link process.
     * {@link SCDEditor#createBaseForm(TitleType, boolean, DynamicForm...)}
     * 
     * It is executed with the arguments isDefaultDisabled as True.
     * For the other arguments, the one passed to this method is used.
     * </pre>
     * 
     * @param titleType Information about the file to be created
     * @param prevForms Form before the form to be created
     * @return The created form.
     */
    private DynamicForm createBaseForm(TitleType titleType, DynamicForm... prevForms) {
        return createBaseForm(titleType, true, prevForms);
    }

    /**
     * <pre>
     * Overloaded method for the next link process.
     * {@link SCDEditor#createBaseForm(TitleType, boolean, boolean, boolean, DynamicForm...)}
     * 
     * It is executed with the arguments createOrNot and isRequired as True.
     * For the other arguments, the one passed to this method is used.
     * </pre>
     * 
     * @param titleType Information about the file to be created
     * @param isDefaultDisabled Whether the input form can be used. True if it should be disabled.
     * @param prevForms Form before the form to be created
     * @return The created form.
     */
    private DynamicForm createBaseForm(TitleType titleType, boolean isDefaultDisabled, DynamicForm... prevForms) {
        return createBaseForm(titleType, true, isDefaultDisabled, true, prevForms);
    }

    /**
     * A file selection form is created based on the arguments.
     * @param titleType Information about the file to be created
     * @param createOrNot Whether to display the create button. If true, it is displayed.
     * @param isDefaultDisabled Whether the input form can be used. True if it should be disabled.
     * @param isRequired True if this file is mandatory for scenario generation
     * @param prevForms Form before the form to be created
     * @return The created form.
     */
    private DynamicForm createBaseForm(TitleType titleType, boolean createOrNot, boolean isDefaultDisabled, boolean isRequired, DynamicForm... prevForms) {
        String uuid = TitleType.getUuid(titleType, root);
        if (uuid != null && !uuid.isEmpty()) {
            isDefaultDisabled = false;
        } else {
            uuid = "";
        }
        Long defaultId = uuidMap.get(uuid);
        if (defaultId == null || defaultId.equals(0L)) {
            defaultId = -1L;
        }
        String fileName = TitleType.getFileName(titleType, root);
        if (fileName == null) {
            fileName = "";
        }
        TitleType.setId(titleType, root, defaultId, uuid, fileName);
        String displayName = "";
        if (titleType.getExtension().equals(Extension.LSC)) {
            displayName = Canvas.imgHTML("pieces/16/" + titleType.getExtension().getImgPath()) + "&nbsp;" + titleType.getTitle();
        } else {
            displayName = Canvas.imgHTML("pieces/16/" + titleType.getExtension().getImgPath()) + "&nbsp;" + titleType.getTitle() + " (." + titleType.getExtension().getValue()
                    + ")";
        }
        String name = titleType.getTitle().replaceAll("\\s", "");
        DynamicForm form = new DynamicForm();
        form.setWidth100();
        form.setMargin(20);
        form.setNumCols(4);
        form.setName(name);
        form.setColWidths("*", "10", "70", "70");
        form.setTitleSuffix("");
        ButtonItem create = new ButtonItem(name + CREATE_BTN_NAME, "Create");
        create.setAttribute(EXTENSION, titleType.getExtension());
        create.setStartRow(false);
        create.setEndRow(false);
        create.setAlign(Alignment.RIGHT);
        create.setWidth("*");
        create.setDisabled(isDefaultDisabled);
        ButtonItem open = new ButtonItem(name + OPEN_BTN_NAME, "Open");
        open.setStartRow(false);
        open.setEndRow(false);
        open.setAlign(Alignment.RIGHT);
        open.setWidth("*");
        open.setDisabled(true);
        StaticTextItem selectBoxTitle = new StaticTextItem(name + "Title", displayName);
        selectBoxTitle.setTitleAlign(Alignment.LEFT);
        selectBoxTitle.setTitleVAlign(VerticalAlignment.BOTTOM);
        if (!createOrNot) {
            create.setVisible(false);
            selectBoxTitle.setColSpan(2);
        }
        SelectItem select = new SelectItem(name + SELECT_BOX_NAME);
        select.setShowTitle(false);
        select.setColSpan(4);
        select.setWidth("*");
        select.setDisabled(isDefaultDisabled);
        select.setAttribute(PREV_FORMS, Arrays.asList(prevForms));
        select.setAttribute(REQUIRED, isRequired);
        select.setValidators(new IsOneOfValidator());
        form.setItems(selectBoxTitle, create, open, select);
        List<Long> refFileIdList = new ArrayList<Long>();
        boolean disableFlag = false;
        if (prevForms != null) {
            List<DynamicForm> nextFormList = new ArrayList<DynamicForm>();
            for (DynamicForm prevForm : prevForms) {
                JavaScriptObject obj = prevForm.getField(prevForm.getName() + SELECT_BOX_NAME).getAttributeAsJavaScriptObject(NEXT_FORMS);
                if (obj != null) {
                    if (JSOHelper.convertToList(obj) != null) {
                        nextFormList = JSOHelper.convertToList(obj);
                    }
                }
                nextFormList.add(form);
                prevForm.getField(prevForm.getName() + SELECT_BOX_NAME).setAttribute(NEXT_FORMS, nextFormList);

                TitleType prevType = (TitleType) prevForm.getField(prevForm.getName() + SELECT_BOX_NAME).getAttributeAsObject(TITLE_TYPE);
                String tmpUuid = TitleType.getUuid(prevType, root);

                if (TitleType.OD.equals(prevType) && TitleType.FPS.equals(titleType)) {
                    // ODDが選択されている場合、ODはrefFileIdListに含まない。
                    if (root.getODDFileId() > 0) {
                        continue;
                    }
                }

                if (tmpUuid != null && !tmpUuid.isEmpty()) {
                    Long refId = uuidMap.get(tmpUuid);
                    refFileIdList.add(refId);
                }

                if (prevType != TitleType.ODC && prevType != TitleType.ODD) {
                    if (tmpUuid == null || tmpUuid.isEmpty()) {
                        disableFlag = true;
                    }
                }
            }
            select.setAttribute(PREV_FORMS, Arrays.asList(prevForms));
        }
        select.setDisabled(disableFlag);
        create.setDisabled(disableFlag);
        if (refFileIdList.size() > 0) {
            String extension = ((Extension) create.getAttributeAsObject(EXTENSION)).getValue();
            String scsUuid = TitleType.getUuid(TitleType.SCS, root);
            String scssUuid = TitleType.getUuid(TitleType.SCSS, root);
            switch (Extension.getByCode(extension)) {
            case LSC:
                editResourceService.getSCSRootWithLSCDefined(projectId, scsUuid, scssUuid, new AsyncCallback<Map<String, String>>() {
                    @Override
                    public void onSuccess(Map<String, String> result) {
                        select.setValueMap(result);
                        String patternId = TitleType.getUuid(titleType, root);
                        if (patternId != null && !patternId.isEmpty()) {
                            if (result.containsKey(patternId)) {
                                select.setValue(patternId);
                                open.setDisabled(false);
                            } else {
                                select.setValue(TitleType.getFileName(titleType, root));
                            }
                        }
                        form.validate();
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }
                });
                break;
            case CSCS:
                long patternId = Long.valueOf(TitleType.getUuid(TitleType.LSC, root));
                editResourceService.getCSCSFileMap(projectId, scssUuid, patternId, new AsyncCallback<Map<String, String>>() {
                    @Override
                    public void onSuccess(Map<String, String> result) {
                        select.setValueMap(result);
                        String uuid = TitleType.getUuid(titleType, root);
                        if (uuid != null && !uuid.isEmpty()) {
                            if (result.containsKey(uuid)) {
                                select.setValue(uuid);
                                open.setDisabled(false);
                            } else {
                                select.setValue(TitleType.getFileName(titleType, root));
                            }
                        }
                        form.validate();
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }
                });
                break;
            default:
                editResourceService.getFileMap(projectId, refFileIdList, extension, new AsyncCallback<Map<String, String>>() {
                    @Override
                    public void onSuccess(Map<String, String> result) {
                        String uuid = TitleType.getUuid(titleType, root);
                        select.setValueMap(result);
                        if (uuid != null && !uuid.isEmpty()) {
                            if (result.containsKey(uuid)) {
                                select.setValue(uuid);
                                open.setDisabled(false);
                            } else {
                                select.setValue(TitleType.getFileName(titleType, root));
                            }
                        }
                        form.validate();
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }
                });
            }
        }

        select.setAttribute(TITLE_TYPE, titleType);
        formList.add(form);
        return form;
    }

    /**
     * True is stored when Argument:ext select box is selected.
     * @param forms Select item list of SelectItem
     * @param titleType File to be checked
     * @return Selected state of SelectItem
     */
    private boolean isPulldownSelected(List<DynamicForm> forms, TitleType titleType) {
        return forms.stream().filter(form -> {
            SelectItem selectBox = (SelectItem) form.getField(form.getName() + SELECT_BOX_NAME);
            TitleType type = (TitleType) form.getField(form.getName() + SELECT_BOX_NAME).getAttributeAsObject(TITLE_TYPE);
            if (type.equals(titleType)) {
                if (selectBox.getValueAsString() != null && !selectBox.getValueAsString().isEmpty()) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList()).size() > 0;
    }

    /**
     * ENUM that summarizes the file information of the form
     */
    private enum TitleType {
        OD("OD", Extension.FM), ODC("OD constraint", Extension.FMC), ODD("ODD", Extension.TC), BA("Behavior Architecture", Extension.ARC), FPS("Feature pattern setting",
                Extension.FPS), FP("Feature pattern", Extension.FP), BPS("Behavior pattern setting", Extension.BPS), BP("Behavior pattern", Extension.BP), SCSS(
                        "Scenario set setting",
                        Extension.SCSS), SCS("Scenario Set", Extension.SCS), LSC("Logical Scenario", Extension.LSC), CSCS("Concrete Scenario Set", Extension.CSCS);

        private final String title;

        private final Extension ext;

        private TitleType(String title, Extension ext) {
            this.title = title;
            this.ext = ext;
        }

        public String getTitle() {
            return title;
        }

        public Extension getExtension() {
            return ext;
        }

        public static void setId(TitleType titleType, SCDRoot root, Long id, String uuid, String fileName) {
            switch (titleType) {
            case OD:
                root.setODFileId(id);
                root.setOD(uuid);
                root.setODFileName(fileName);
                break;
            case ODC:
                root.setODcFileId(id);
                root.setODC(uuid);
                root.setODCFileName(fileName);
                break;
            case ODD:
                root.setODDFileId(id);
                root.setODD(uuid);
                root.setODDFileName(fileName);
                break;
            case BA:
                root.setBAFileId(id);
                root.setBA(uuid);
                root.setBAFileName(fileName);
                break;
            case FPS:
                root.setTSDgsFileId(id);
                root.setTSDGS(uuid);
                root.setTSDGSFileName(fileName);
                break;
            case FP:
                root.setTSDpFileId(id);
                root.setTSDP(uuid);
                root.setTSDPFileName(fileName);
                break;
            case BPS:
                root.setBgsFileId(id);
                root.setBGS(uuid);
                root.setBGSFileName(fileName);
                break;
            case BP:
                root.setBpFileId(id);
                root.setBP(uuid);
                root.setBPFileName(fileName);
                break;
            case SCSS:
                root.setCBFileId(id);
                root.setCB(uuid);
                root.setCBFileName(fileName);
                break;
            case SCS:
                root.setSSFileId(id);
                root.setSS(uuid);
                root.setSSFileName(fileName);
                break;
            case LSC:
                root.setLSFileId(id);
                root.setLS(uuid);
                root.setLSFileName(fileName);
                break;
            case CSCS:
                root.setCSFileId(id);
                root.setCS(uuid);
                root.setCSFileName(fileName);
                break;
            default:
                break;
            }
        }

        public static String getUuid(TitleType titleType, SCDRoot root) {
            switch (titleType) {
            case OD:
                return root.getOD();
            case ODC:
                return root.getODC();
            case ODD:
                return root.getODD();
            case BA:
                return root.getBA();
            case FPS:
                return root.getTSDGS();
            case FP:
                return root.getTSDP();
            case BPS:
                return root.getBGS();
            case BP:
                return root.getBP();
            case SCSS:
                return root.getCB();
            case SCS:
                return root.getSS();
            case LSC:
                return root.getLS();
            case CSCS:
                return root.getCS();
            default:
                return "";
            }
        }

        public static String getFileName(TitleType titleType, SCDRoot root) {
            switch (titleType) {
            case OD:
                return root.getODFileName();
            case ODC:
                return root.getODCFileName();
            case ODD:
                return root.getODDFileName();
            case BA:
                return root.getBAFileName();
            case FPS:
                return root.getTSDGSFileName();
            case FP:
                return root.getTSDPFileName();
            case BPS:
                return root.getBGSFileName();
            case BP:
                return root.getBPFileName();
            case SCSS:
                return root.getCBFileName();
            case SCS:
                return root.getSSFileName();
            case LSC:
                return root.getLSFileName();
            case CSCS:
                return root.getCSFileName();
            default:
                return "";
            }
        }
    }
}
