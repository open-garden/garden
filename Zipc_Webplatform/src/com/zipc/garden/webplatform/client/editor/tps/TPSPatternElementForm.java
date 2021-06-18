package com.zipc.garden.webplatform.client.editor.tps;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tps.NodeVariable;
import com.zipc.garden.model.tps.TPSFactory;
import com.zipc.garden.model.tps.TPSPatternElement;
import com.zipc.garden.webplatform.client.service.ProjectServiceAsync;
import com.zipc.garden.webplatform.shared.PatternElementInfo;

/**
 * This screen manages the risk importance calculation method created with feature-model.
 */
public class TPSPatternElementForm {

    /** Main class of feature-pattern-setting editor */
    private TPSEditor editor;

    /** ID of the project in which the FPS file is managed */
    private long projectId;

    /** Asynchronous interface for acquiring pattern element information registered in DB */
    private ProjectServiceAsync projectService;

    /** Form consisting of TextItem of PatternElement and Edit button */
    private DynamicForm form;

    /** ListGrid displaying risk importance calculation formula and name */
    private ListGrid listGrid;

    /**
     * TextItem that displays the editing status of PatternElement.<br>
     * The value of {@link TPSPatternElementForm#PATTERN_ELEMENT_DEFAULT} or
     * {@link TPSPatternElementForm#PATTERN_ELEMENT_EDITING} is displayed.
     */
    private TextItem textItem;

    /** Edit button of PatternElement */
    private ButtonItem edit;

    /** Button for resetting the edited contents of PatternElement */
    private ButtonItem reset;

    /** MenuItem for deleting {@link #listGrid} records */
    private final MenuItem deleteItem = new MenuItem();

    /** Value displayed in TextItem when PatternElement is not edited */
    private final String PATTERN_ELEMENT_DEFAULT = "Default";

    /** Value displayed in TextItem when PatternElement is edited */
    private final String PATTERN_ELEMENT_EDITING = "Editing";

    /**
     * constructor.<br>
     * The contents of the form are initialized.
     * @param editor {@link #editor}
     * @param projectId {@link #projectId}
     * @param projectService {@link #projectService}
     */
    public TPSPatternElementForm(TPSEditor editor, long projectId, ProjectServiceAsync projectService) {
        this.editor = editor;
        this.projectId = projectId;
        this.projectService = projectService;

        form = new DynamicForm();
        form.setWidth100();
        form.setMargin(10);
        form.setNumCols(4);

        textItem = new TextItem("featurePatternElement", "Feature Pattern Element");
        textItem.setWidth("*");
        textItem.setEndRow(false);
        textItem.setCanEdit(false);
        textItem.setRequired(true);
        textItem.setEditorProperties(new StaticTextItem());

        edit = new ButtonItem("Edit");
        edit.setWidth(150);
        edit.setStartRow(false);
        edit.setEndRow(false);

        reset = new ButtonItem("Reset");
        reset.setWidth(150);
        reset.setStartRow(false);
        reset.setEndRow(false);

        form.setItems(textItem, edit, reset);
        checkDefaultData();
    }

    /**
     * The click handler is created.<br>
     * The edit screen of PatternElement is displayed.
     * @param edit Edit button of PatternElement
     * @return click handler
     */
    public HandlerRegistration addEditClickHandler(ButtonItem edit) {
        return edit.addClickHandler(event -> {
            final Window winModal = new Window();
            winModal.setTitle("Feature Pattern Element");
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
            winModal.addCloseClickHandler(e -> winModal.markForDestroy());

            VLayout formlayout = new VLayout();
            formlayout.setWidth100();
            formlayout.setHeight100();

            final ListGrid variableListGrid = new ListGrid();

            variableListGrid.setEditEvent(ListGridEditEvent.DOUBLECLICK);
            variableListGrid.setEditByCell(true);
            variableListGrid.setCanSort(false);
            variableListGrid.setCanResizeFields(true);
            variableListGrid.setCanReorderFields(false);
            variableListGrid.setShowSortArrow(SortArrow.NONE);
            variableListGrid.setShowHeaderContextMenu(false);
            variableListGrid.setShowHeaderMenuButton(false);
            variableListGrid.setLeaveScrollbarGap(false);
            variableListGrid.setWidth100();
            variableListGrid.setHeight100();
            variableListGrid.setMargin(5);

            ListGridField parentField = new ListGridField("ParentNode");
            parentField.setHidden(true);

            ListGridField parentNameField = new ListGridField("ParentNodeName", "Parent Node");
            parentNameField.setType(ListGridFieldType.TEXT);
            parentNameField.setCanEdit(false);

            ListGridField variableNameField = new ListGridField("VariableName", "Variable Name");
            variableNameField.setType(ListGridFieldType.TEXT);
            variableNameField.setCanEdit(true);

            variableListGrid.setFields(parentField, parentNameField, variableNameField);

            listGrid = new ListGrid();
            listGrid.setEditEvent(ListGridEditEvent.DOUBLECLICK);
            listGrid.setEditByCell(true);
            listGrid.setCanSort(false);
            listGrid.setCanResizeFields(true);
            listGrid.setCanReorderFields(false);
            listGrid.setShowSortArrow(SortArrow.NONE);
            listGrid.setShowHeaderContextMenu(false);
            listGrid.setShowHeaderMenuButton(false);
            listGrid.setLeaveScrollbarGap(false);
            listGrid.setWidth100();
            listGrid.setHeight100();
            listGrid.setMargin(5);
            listGrid.addKeyPressHandler(addKeyPressHandler());
            listGrid.setContextMenu(addRightClickMenu());

            ListGridField nameField = new ListGridField("name", "");
            nameField.setType(ListGridFieldType.TEXT);
            nameField.setCanEdit(true);
            nameField.addCellSavedHandler(e -> {
                if (lastLineInputCheck()) {
                    createNewLine();
                }
            });
            ListGridField valueField = new ListGridField("value", "");
            TextAreaItem textAreaItem = new TextAreaItem();
            textAreaItem.setHeight(70);
            valueField.setEditorProperties(textAreaItem);
            valueField.setCanEdit(true);
            valueField.addCellSavedHandler(e -> {
                if (lastLineInputCheck()) {
                    createNewLine();
                }
            });
            listGrid.setFields(nameField, valueField);

            HLayout hlayout = new HLayout();
            hlayout.setHeight(30);
            hlayout.setWidth100();
            hlayout.setLayoutLeftMargin(20);
            IButton okBtn = new IButton("OK");
            okBtn.setHeight100();
            okBtn.setWidth100();
            okBtn.setMargin(5);
            okBtn.addClickHandler(e -> {

                Arrays.stream(variableListGrid.getRecords()).forEach(record -> {
                    TCNode tcNode = (TCNode) record.getAttributeAsObject("ParentNode");
                    String variableName = record.getAttribute("VariableName");
                    Optional<NodeVariable> opt = editor.getRoot().getNodeVariables().stream().filter(n -> n.getTcNode() == tcNode).findFirst();
                    if (opt.isPresent()) {
                        opt.get().setVariableName(variableName);
                    }
                });

                editor.getRoot().getPatternElements().clear();
                Arrays.stream(listGrid.getRecords()).filter(record -> {
                    // Excludes the last line because it is a new input line.
                    return listGrid.getRecords().length != listGrid.getRowNum(record) + 1;
                }).forEach(record -> {
                    TPSPatternElement el = TPSFactory.eINSTANCE.createTPSPatternElement();
                    el.setName(record.getAttributeAsString("name"));
                    el.setValue(record.getAttributeAsString("value"));
                    editor.getRoot().getPatternElements().add(el);
                });
                checkDefaultData();
                winModal.markForDestroy();
            });

            IButton cancelBtn = new IButton("CANCEL");
            cancelBtn.setHeight100();
            cancelBtn.setWidth100();
            cancelBtn.setMargin(5);
            cancelBtn.addClickHandler(e -> winModal.markForDestroy());

            LayoutSpacer hspacer = new LayoutSpacer(3, "100%");
            hspacer.setBackgroundColor("white");

            hlayout.addMembers(hspacer, okBtn, hspacer, cancelBtn, hspacer);

            formlayout.addMembers(variableListGrid, listGrid);
            winModal.addMember(formlayout);
            createPatternElementData();
            createVariabletData(variableListGrid);
            winModal.show();
            winModal.getFooter().addMember(hlayout, 0);

            winModal.addResizedHandler(e -> formlayout.setWidth100());

        });
    }

    /**
     * The click handler is created.<br>
     * Editing of Pattern Element is reset.
     * @param reset Reset button of PatternElement
     * @return click handler
     */
    public HandlerRegistration addResetClickHandler(ButtonItem reset) {
        return reset.addClickHandler(event -> {
            projectService.getPatternElementInfoList(projectId, new AsyncCallback<List<PatternElementInfo>>() {
                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }

                @Override
                public void onSuccess(List<PatternElementInfo> result) {
                    editor.getRoot().getPatternElements().clear();
                    result.forEach(info -> {
                        TPSPatternElement el = TPSFactory.eINSTANCE.createTPSPatternElement();
                        el.setName(info.getName());
                        el.setValue(info.getValue());
                        editor.getRoot().getPatternElements().add(el);
                    });
                    textItem.setValue(PATTERN_ELEMENT_DEFAULT);
                }
            });
        });
    }

    /**
     * Check the input value in the last row of {@link #listGrid}.
     * @return If true, it has been entered. If false, there is no input.
     */
    private boolean lastLineInputCheck() {
        ListGridRecord[] records = listGrid.getRecords();
        if (records.length > 0) {
            String name = records[records.length - 1].getAttributeAsString("name");
            String value = records[records.length - 1].getAttributeAsString("value");
            if ("".equals(name.trim()) && "".equals(value.trim())) {
                return false;
            }
        }
        return true;
    }

    /**
     * A new input row is added to the last row of {@link #listGrid}.
     */
    private void createNewLine() {
        ListGridRecord record = new ListGridRecord();
        record.setAttribute("name", "");
        record.setAttribute("value", "");
        listGrid.addData(record);
    }

    /**
     * Display the contents of PatternElement of EMF model in {@link #listGrid}.
     */
    private void createPatternElementData() {
        ListGridRecord[] retData = new ListGridRecord[editor.getRoot().getPatternElements().size() + 1];
        for (int i = 0; i < editor.getRoot().getPatternElements().size(); i++) {
            retData[i] = new ListGridRecord();
            retData[i].setAttribute("name", editor.getRoot().getPatternElements().get(i).getName());
            retData[i].setAttribute("value", editor.getRoot().getPatternElements().get(i).getValue());
            listGrid.addData(retData[i]);
        }
        createNewLine();
    }

    /**
     * Display the contents of NodeVariable of EMF model in the argument: listGrid.
     * @param listGrid NodeVariable ListGrid
     */
    private void createVariabletData(ListGrid listGrid) {
        ListGridRecord[] retData = new ListGridRecord[editor.getRoot().getNodeVariables().size()];
        for (int i = 0; i < editor.getRoot().getNodeVariables().size(); i++) {
            retData[i] = new ListGridRecord();
            retData[i].setAttribute("ParentNode", editor.getRoot().getNodeVariables().get(i).getTcNode());
            retData[i].setAttribute("ParentNodeName", editor.getRoot().getNodeVariables().get(i).getTcNode().getName());
            retData[i].setAttribute("VariableName", editor.getRoot().getNodeVariables().get(i).getVariableName());

            listGrid.addData(retData[i]);
        }
    }

    /**
     * Check if the PatternElement has been edited.<br>
     * If unedited, the value of {@link #PATTERN_ELEMENT_DEFAULT} will be displayed in the {@link #textItem}.<br>
     * If it is being edited, the value of {@link #PATTERN_ELEMENT_EDITING} will be displayed in the {@link #textItem}.
     */
    private void checkDefaultData() {
        projectService.getPatternElementInfoList(projectId, new AsyncCallback<List<PatternElementInfo>>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(List<PatternElementInfo> result) {
                String itemValue = PATTERN_ELEMENT_DEFAULT;
                if (result.size() == editor.getRoot().getPatternElements().size()) {
                    for (int i = 0; i < result.size(); i++) {
                        if (!result.get(i).getName().equals(editor.getRoot().getPatternElements().get(i).getName())
                                | !result.get(i).getValue().equals(editor.getRoot().getPatternElements().get(i).getValue())) {
                            itemValue = PATTERN_ELEMENT_EDITING;
                            break;
                        }
                    }
                } else {
                    itemValue = PATTERN_ELEMENT_EDITING;
                }
                textItem.setValue(itemValue);
            }
        });
    }

    /**
     * Method that creates a shortcut key event.
     * @return key press handler
     */
    private KeyPressHandler addKeyPressHandler() {
        return event -> {
            if (KeyNames.DEL.equals(event.getKeyName())) {
                deleteItem.fireEvent(new MenuItemClickEvent(event.getFiringCanvas().getJsObj()));
            }
        };
    }

    /**
     * Create and get the right-click menu for {@link #listGrid}.
     * @return Menu
     */
    private Menu addRightClickMenu() {
        Menu menu = new Menu();
        deleteItem.setTitle("Delete");
        deleteItem.setKeyTitle("Delete");
        deleteItem.addClickHandler(event -> {
            if (listGrid.getSelectedRecords().length > 0) {
                for (Record record : listGrid.getSelectedRecords()) {
                    // last line delete is skip
                    if (listGrid.getRecordIndex(record) == listGrid.getRecords().length - 1) {
                        continue;
                    }
                    listGrid.removeData(record);
                }
            }
        });
        menu.addItem(deleteItem);
        return menu;
    }

    /**
     * Gets the form consisting of TextItem of PatternElement and edit button.
     * @return form
     */
    public DynamicForm getDynamicForm() {
        return form;
    }

    /**
     * Gets the TextItem that displays the edit status of the PatternElement.
     * @return textItem
     */
    public TextItem getTextItem() {
        return textItem;
    }

    /**
     * Gets the edit button of PatternElement.
     * @return edit button
     */
    public ButtonItem getEdit() {
        return edit;
    }

    /**
     * Gets the button for resetting the edit contents of the PatternElement.
     * @return reset button
     */
    public ButtonItem getReset() {
        return reset;
    }
}
