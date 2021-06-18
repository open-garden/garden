package com.zipc.garden.webplatform.client.view.part;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.zipc.garden.webplatform.client.service.ProjectService;
import com.zipc.garden.webplatform.client.service.ProjectServiceAsync;
import com.zipc.garden.webplatform.shared.PatternElementInfo;
import com.zipc.garden.webplatform.shared.UserPropertyInfo;

/**
 * <pre>
 * This class manages the following items.
 * ・ The name of the array that holds all the variables
 * ・ FM Editor property choices
 * ・ Formula to calculate the risk importance of FP Viewer
 * </pre>
 */
public class ProjectKeyWordViewPart extends ViewPartBase {

    /** Element name of the record to be added to {@link #allVariablesGrid} */
    private static final String ALL_VARIABLES_NAME = "allVariablesName";

    /** Header title of record added to {@link #allVariablesGrid} */
    private static final String ALL_VARIABLES_TITLE = "All Variables Name";

    /** Element name of the record to be added to {@link #propGrid} */
    private static final String USER_PROP_ID_NAME = "propId";

    /** Element name of the record to be added to {@link #propGrid} */
    private static final String USER_PROP_NAME = "userProp";

    /** Header title of record added to {@link #propGrid} */
    private static final String USER_PROP_TITLE = "User Property";

    /** Element name of the record to be added to {@link #propGrid} */
    private static final String INITIAL_VALUE_NAME = "initialValue";

    /** Header title of record added to {@link #propGrid} */
    private static final String INITIAL_VALUE_TITLE = "Initial Value";

    /** Element name of the record to be added to {@link #ptnElemGrid} */
    private static final String PATTERN_ELEMENT_ID_NAME = "ptnElementfId";

    /** Element name of the record to be added to {@link #ptnElemGrid} */
    private static final String PATTERN_ELEMENT_NAME = "ptnElement";

    /** Header title of record added to {@link #ptnElemGrid} */
    private static final String PATTERN_ELEMENT_TITLE = "Pattern Element";

    /** Element name of the record to be added to {@link #ptnElemGrid} */
    private static final String PATTERN_ELEMENT_VALUE_NAME = "ptnValue";

    /** Header title of record added to {@link #ptnElemGrid} */
    private static final String PATTERN_ELEMENT_VALUE_TITLE = "Pattern Element Value";

    /** Asynchronous interface for reflecting the edited contents in the table */
    private ProjectServiceAsync projectService;

    /** ID of the project being edited */
    private Long projectId;

    /** ListGrid where you can set the name of the array that holds all the variables */
    private ListGrid allVariablesGrid;

    /** ListGrid that can set the formula to calculate the risk importance of FP Viewer */
    private ListGrid propGrid;

    /** Button to add new record to {@link #propGrid} */
    private ImgButton propAddButton;

    /** Button to delete selected records from {@link #propGrid} */
    private ImgButton propRemoveButton;

    /** ListGrid that displays the formula to calculate the risk importance of FP Viewer */
    private ListGrid ptnElemGrid;

    /** Button to add new record to {@link #ptnElemGrid} */
    private ImgButton ptnElemAddButton;

    /** Button to delete selected records from {@link #ptnElemGrid} */
    private ImgButton ptnElemRemoveButton;

    /**
     * constructor
     * @param map GET parameter
     */
    public ProjectKeyWordViewPart(Map<String, String> map) {
        super(map);
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
    public void entry() {
        Map<String, String> map = getParam();
        projectId = Long.valueOf(map.get("id"));
        projectService = GWT.create(ProjectService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Canvas[] createCanvases() {
        getLayout().setWidth100();
        getLayout().setHeight100();

        allVariablesGrid = new ListGrid();
        allVariablesGrid.setCanEdit(true);
        allVariablesGrid.setShowHeaderContextMenu(false);
        allVariablesGrid.setShowHeaderMenuButton(false);
        allVariablesGrid.setCanResizeFields(false);
        allVariablesGrid.setCanReorderFields(false);
        allVariablesGrid.setLeaveScrollbarGap(false);
        allVariablesGrid.setEditEvent(ListGridEditEvent.CLICK);
        allVariablesGrid.setFields(new ListGridField(ALL_VARIABLES_NAME, ALL_VARIABLES_TITLE));

        propGrid = new ListGrid();
        propGrid.setCanEdit(true);
        propGrid.setShowHeaderContextMenu(false);
        propGrid.setShowHeaderMenuButton(false);
        propGrid.setCanResizeFields(false);
        propGrid.setCanReorderFields(false);
        propGrid.setLeaveScrollbarGap(false);
        propGrid.setEditEvent(ListGridEditEvent.CLICK);
        propGrid.setFields(new ListGridField(USER_PROP_NAME, USER_PROP_TITLE), new ListGridField(INITIAL_VALUE_NAME, INITIAL_VALUE_TITLE));

        propAddButton = new ImgButton();
        propAddButton.setSrc("[SKIN]actions/add.png");
        propAddButton.setSize(16);
        propAddButton.setShowFocused(false);
        propAddButton.setShowRollOver(false);
        propAddButton.setShowDown(false);

        propRemoveButton = new ImgButton();
        propRemoveButton.setSrc("[SKIN]actions/remove.png");
        propRemoveButton.setSize(16);
        propRemoveButton.setShowFocused(false);
        propRemoveButton.setShowRollOver(false);
        propRemoveButton.setShowDown(false);

        SectionStackSection propSection = new SectionStackSection();
        propSection.setTitle(".fm");
        propSection.setItems(propGrid);
        propSection.setControls(propAddButton, propRemoveButton);
        propSection.setExpanded(true);
        propSection.setCanCollapse(false);

        ptnElemGrid = new ListGrid();
        ptnElemGrid.setCanEdit(true);
        ptnElemGrid.setShowHeaderContextMenu(false);
        ptnElemGrid.setShowHeaderMenuButton(false);
        ptnElemGrid.setCanResizeFields(false);
        ptnElemGrid.setCanReorderFields(false);
        ptnElemGrid.setLeaveScrollbarGap(false);
        ptnElemGrid.setEditEvent(ListGridEditEvent.CLICK);

        ListGridField valueField = new ListGridField(PATTERN_ELEMENT_VALUE_NAME, PATTERN_ELEMENT_VALUE_TITLE);
        TextAreaItem textAreaItem = new TextAreaItem();
        textAreaItem.setHeight(100);
        valueField.setEditorProperties(textAreaItem);
        ptnElemGrid.setFields(new ListGridField(PATTERN_ELEMENT_NAME, PATTERN_ELEMENT_TITLE), valueField);

        ptnElemAddButton = new ImgButton();
        ptnElemAddButton.setSrc("[SKIN]actions/add.png");
        ptnElemAddButton.setSize(16);
        ptnElemAddButton.setShowFocused(false);
        ptnElemAddButton.setShowRollOver(false);
        ptnElemAddButton.setShowDown(false);

        ptnElemRemoveButton = new ImgButton();
        ptnElemRemoveButton.setSrc("[SKIN]actions/remove.png");
        ptnElemRemoveButton.setSize(16);
        ptnElemRemoveButton.setShowFocused(false);
        ptnElemRemoveButton.setShowRollOver(false);
        ptnElemRemoveButton.setShowDown(false);

        SectionStackSection ptnElemSection = new SectionStackSection();
        ptnElemSection.setTitle(".fp");
        ptnElemSection.setItems(ptnElemGrid);
        ptnElemSection.setControls(ptnElemAddButton, ptnElemRemoveButton);
        ptnElemSection.setExpanded(true);
        ptnElemSection.setCanCollapse(false);

        SectionStack sectionStack = new SectionStack();
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
        sectionStack.setSections(propSection, ptnElemSection);
        sectionStack.setWidth100();
        sectionStack.setHeight100();
        sectionStack.setOverflow(Overflow.AUTO);

        return new Canvas[] { allVariablesGrid, sectionStack };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {

        allVariablesGrid.addEditCompleteHandler(e -> {
            ListGridRecord record = allVariablesGrid.getRecord(e.getRowNum());
            String allVariablesName = record.getAttribute(ALL_VARIABLES_NAME);
            projectService.updateAllVariablesName(projectId, allVariablesName, new AsyncCallback<Void>() {
                @Override
                public void onSuccess(Void result) {
                    return;
                }

                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }
            });
        });

        propGrid.addEditCompleteHandler(e -> {
            ListGridRecord record = propGrid.getRecord(e.getRowNum());
            Long userPropertyId = record.getAttributeAsLong(USER_PROP_ID_NAME);
            String userProperty = record.getAttribute(USER_PROP_NAME);
            String initinalValue = record.getAttribute(INITIAL_VALUE_NAME);
            projectService.updateUserProperty(userPropertyId, userProperty, initinalValue, new AsyncCallback<Void>() {
                @Override
                public void onSuccess(Void result) {
                    return;
                }

                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }
            });
        });

        propAddButton.addClickHandler(e -> {
            projectService.createUserProperty(projectId, new AsyncCallback<UserPropertyInfo>() {
                @Override
                public void onSuccess(UserPropertyInfo result) {
                    Record record = new ListGridRecord();
                    record.setAttribute(USER_PROP_ID_NAME, result.getId());
                    propGrid.startEditingNew(record);
                }

                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }
            });
            e.cancel();
        });

        propRemoveButton.addClickHandler(e -> {
            Record record = propGrid.getSelectedRecord();
            Long userPropertyId = record.getAttributeAsLong(USER_PROP_ID_NAME);
            projectService.removeUserProperty(userPropertyId, new AsyncCallback<Void>() {
                @Override
                public void onSuccess(Void result) {
                    propGrid.removeSelectedData();
                }

                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }
            });
            e.cancel();
        });

        ptnElemGrid.addEditCompleteHandler(e -> {
            ListGridRecord record = ptnElemGrid.getRecord(e.getRowNum());
            Long id = record.getAttributeAsLong(PATTERN_ELEMENT_ID_NAME);
            String name = record.getAttribute(PATTERN_ELEMENT_NAME);
            String value = record.getAttribute(PATTERN_ELEMENT_VALUE_NAME);
            projectService.updatePatternElement(id, name, value, new AsyncCallback<Void>() {
                @Override
                public void onSuccess(Void result) {
                    return;
                }

                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }
            });
        });

        ptnElemAddButton.addClickHandler(e -> {
            projectService.createPatternElement(projectId, new AsyncCallback<PatternElementInfo>() {
                @Override
                public void onSuccess(PatternElementInfo result) {
                    Record record = new ListGridRecord();
                    record.setAttribute(PATTERN_ELEMENT_ID_NAME, result.getId());
                    ptnElemGrid.startEditingNew(record);
                }

                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }
            });
            e.cancel();
        });

        ptnElemRemoveButton.addClickHandler(e -> {
            Record record = ptnElemGrid.getSelectedRecord();
            Long PatternElementId = record.getAttributeAsLong(PATTERN_ELEMENT_ID_NAME);
            projectService.removePatternElement(PatternElementId, new AsyncCallback<Void>() {
                @Override
                public void onSuccess(Void result) {
                    ptnElemGrid.removeSelectedData();
                }

                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }
            });
            e.cancel();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialized() {
        projectService.getAllVariablesName(projectId, new AsyncCallback<String>() {

            @Override
            public void onSuccess(String result) {
                List<ListGridRecord> listGridRecordList = new ArrayList<ListGridRecord>();
                ListGridRecord record = new ListGridRecord();
                record.setAttribute(ALL_VARIABLES_NAME, result);
                listGridRecordList.add(record);
                allVariablesGrid.setRecords(listGridRecordList.toArray(new ListGridRecord[listGridRecordList.size()]));
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }
        });
        projectService.getUserPropertyInfoList(projectId, new AsyncCallback<List<UserPropertyInfo>>() {
            @Override
            public void onSuccess(List<UserPropertyInfo> result) {
                List<ListGridRecord> listGridRecordList = new ArrayList<ListGridRecord>();
                result.forEach(prop -> {
                    ListGridRecord record = new ListGridRecord();
                    record.setAttribute(USER_PROP_ID_NAME, prop.getId());
                    record.setAttribute(USER_PROP_NAME, prop.getUserProperty());
                    record.setAttribute(INITIAL_VALUE_NAME, prop.getInitialValue());
                    listGridRecordList.add(record);
                });
                propGrid.setRecords(listGridRecordList.toArray(new ListGridRecord[listGridRecordList.size()]));
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }
        });

        projectService.getPatternElementInfoList(projectId, new AsyncCallback<List<PatternElementInfo>>() {
            @Override
            public void onSuccess(List<PatternElementInfo> result) {
                List<ListGridRecord> listGridRecordList = new ArrayList<ListGridRecord>();
                result.forEach(ptnElem -> {
                    ListGridRecord record = new ListGridRecord();
                    record.setAttribute(PATTERN_ELEMENT_ID_NAME, ptnElem.getId());
                    record.setAttribute(PATTERN_ELEMENT_NAME, ptnElem.getName());
                    record.setAttribute(PATTERN_ELEMENT_VALUE_NAME, ptnElem.getValue());
                    listGridRecordList.add(record);
                });
                ptnElemGrid.setRecords(listGridRecordList.toArray(new ListGridRecord[listGridRecordList.size()]));
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }
        });
    }

}
