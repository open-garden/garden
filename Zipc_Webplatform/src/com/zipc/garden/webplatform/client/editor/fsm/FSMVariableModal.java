package com.zipc.garden.webplatform.client.editor.fsm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.grid.events.HeaderClickEvent;
import com.smartgwt.client.widgets.grid.events.HeaderClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.model.fsm.FSMDVariable;
import com.zipc.garden.model.fsm.FSMFactory;
import com.zipc.garden.model.fsm.FSMPackage;
import com.zipc.garden.webplatform.client.command.FSMEditorCommandProvider;

/**
 * This class manages information about the variable of the behavior-model editor.
 */
public class FSMVariableModal {

    /** EMF root model of FSM editor. */
    private FSMEditor editor;

    /** ListGrid displaying variables */
    private final ListGrid listGrid = new ListGrid();

    /** Map with the relationship between ListGridRecord and Variable model */
    private final Map<Record, FSMDVariable> fsmVariables = new HashMap<>();

    /** A command that summarizes the edited contents. The command is issued when you save the variable. */
    private final CompoundCommand cmd = new CompoundCommand();

    /** Menu for deleting records */
    private final MenuItem deleteItem = new MenuItem();

    /**
     * It is incremented when the name header is pressed.<br>
     * If it is even, it will be sorted in ascending order.<br>
     * If it is odd, it will be sorted in descending order.
     */
    private int nameCount = 0;

    /**
     * It is incremented when the type header is pressed.<br>
     * If it is even, it will be sorted in ascending order.<br>
     * If it is odd, it will be sorted in descending order.
     */
    private int typeCount = 0;

    /**
     * ENUM that summarizes the types of variables
     */
    private enum VariableType {
        STRING("String"), INTEGER("Integer"), DOUBLE("Double"), KEY("key"), MIN("min"), MAX("max");
        private final String type;

        private VariableType(String type) {
            this.type = type;
        }

        private String getType() {
            return type;
        }

        public static String[] getValues() {
            return Arrays.stream(values()).map(v -> v.getType()).collect(Collectors.toList()).toArray(new String[values().length]);
        }
    }

    /**
     * This event triggers the edit screen for the variable.
     * @param editor Main class of behavior-model editor
     * @return menu click handler
     */
    protected com.smartgwt.client.widgets.menu.events.ClickHandler getPropClickMenuHandler(FSMEditor editor) {
        return new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                createListGrid(editor);
                initListGridRecord();
                createNewLine();
                listGrid.selectSingleRecord(listGrid.getRecords().length - 1);
                listGrid.rowDoubleClick(listGrid.getSelectedRecord(), listGrid.getRecords().length - 1, 0);
            }
        };
    }

    /**
     * Display the variable information of EMF model in ListGrid.
     */
    private void initListGridRecord() {
        sortVariables(editor.getFSMDStateMachine().getVariables(), 0);
        editor.getFSMDStateMachine().getVariables().forEach(action -> {
            ListGridRecord record = new ListGridRecord();
            record.setAttribute("name", action.getName());
            record.setAttribute("type", action.getType());
            listGrid.addData(record);
            fsmVariables.put(record, action);
        });
    }

    /**
     * Display the variable edit modal window and create a ListGrid.
     * @param editor Main class of behavior-model editor
     */
    private void createListGrid(FSMEditor editor) {
        this.editor = editor;

        final Window winModal = new Window();
        winModal.setTop(editor.getLayout().getHeight() / 4);
        winModal.setLeft(editor.getLayout().getWidth() / 4);
        winModal.setHeight(editor.getLayout().getHeight() / 2);
        winModal.setWidth(editor.getLayout().getWidth() / 2);
        winModal.setTitle("Edit Variable");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.setShowFooter(true);
        winModal.setShowResizer(true);
        winModal.setKeepInParentRect(true);
        winModal.setAutoCenter(true);
        winModal.setBackgroundColor("white");
        winModal.setCanDragResize(true);
        winModal.addCloseClickHandler(e -> winModal.markForDestroy());

        // ModalWindowがページからはみ出る場合
        if (winModal.getTop() + winModal.getHeight() > editor.getDrawPane().getPageBottom()) {
            winModal.setTop(editor.getDrawPane().getPageBottom() - winModal.getHeight());
        }
        if (winModal.getLeft() + winModal.getWidth() > editor.getDrawPane().getPageRight()) {
            winModal.setLeft(editor.getDrawPane().getPageRight() - winModal.getWidth());
        }

        VLayout form = new VLayout();
        form.setHeight100();
        form.setWidth100();

        listGrid.setWidth100();
        listGrid.setHeight100();
        listGrid.setAutoFetchData(true);
        listGrid.setEditEvent(ListGridEditEvent.DOUBLECLICK);
        listGrid.setEditByCell(true);
        listGrid.setCanSort(false);
        listGrid.setCanResizeFields(true);
        listGrid.setShowSortArrow(SortArrow.NONE);
        listGrid.setShowHeaderContextMenu(false);
        listGrid.setShowHeaderMenuButton(false);
        listGrid.setCanReorderFields(false);
        listGrid.setLeaveScrollbarGap(false);
        listGrid.addKeyPressHandler(addKeyPressHandler());
        listGrid.setContextMenu(addRightClickMenu());
        listGrid.setMargin(5);

        ListGridField nameField = new ListGridField("name");
        nameField.setType(ListGridFieldType.TEXT);
        nameField.setCanEdit(true);
        nameField.addCellSavedHandler(addCellSavedHandler(FSMPackage.Literals.FSM_DVARIABLE__NAME));

        ComboBoxItem cmbType = new ComboBoxItem();
        cmbType.setType("comboBox");
        cmbType.setValueMap(VariableType.getValues());

        ListGridField typeField = new ListGridField("type");
        typeField.setType(ListGridFieldType.TEXT);
        typeField.setCanEdit(true);
        typeField.setEditorProperties(cmbType);
        typeField.addCellSavedHandler(addCellSavedHandler(FSMPackage.Literals.FSM_DVARIABLE__TYPE));

        listGrid.setFields(nameField, typeField);

        IButton saveButton = new IButton("OK");
        saveButton.setHeight100();
        saveButton.setWidth100();
        saveButton.setMargin(5);
        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editor.getEditManager().execute(cmd.unwrap());
                winModal.markForDestroy();
            }
        });

        IButton cancelButton = new IButton("Cancel");
        cancelButton.setHeight100();
        cancelButton.setWidth100();
        cancelButton.setMargin(5);
        cancelButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                winModal.markForDestroy();
            }
        });
        LayoutSpacer hspacer = new LayoutSpacer(3, "100%");
        hspacer.setBackgroundColor("white");

        HLayout buttons = new HLayout();
        buttons.setHeight(30);
        buttons.setWidth100();
        buttons.setLayoutLeftMargin(20);
        buttons.addMembers(hspacer, saveButton, hspacer, cancelButton, hspacer);

        form.addMembers(listGrid);
        winModal.addMembers(form);
        winModal.show();
        winModal.getFooter().addMember(buttons, 0);

        winModal.addResizedHandler(new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent event) {
                form.setWidth100();
            }
        });

        listGrid.addHeaderClickHandler(new HeaderClickHandler() {

            @Override
            public void onHeaderClick(HeaderClickEvent event) {
                EList<com.zipc.garden.model.fsm.FSMDVariable> v = FSMFactory.eINSTANCE.createFSMDStateMachine().getVariables();
                List<com.zipc.garden.model.fsm.FSMDVariable> temp = Arrays.asList(listGrid.getRecords()).stream().map(m -> {
                    com.zipc.garden.model.fsm.FSMDVariable variable = FSMFactory.eINSTANCE.createFSMDVariable();
                    variable.setName(m.getAttributeAsString("name"));
                    variable.setType(m.getAttributeAsString("type"));
                    listGrid.removeData(m);
                    return variable;
                }).collect(Collectors.toList());
                ECollections.setEList(v, temp);
                sortVariables(v, event.getFieldNum());

                for (com.zipc.garden.model.fsm.FSMDVariable variable : v) {
                    ListGridRecord record = new ListGridRecord();
                    record.setAttribute("name", variable.getName());
                    record.setAttribute("type", variable.getType());
                    listGrid.addData(record);
                }

            }
        });
    }

    /**
     * Input value check for the last record of ListGrid
     * @return true -> input<br>
     *         false -> un input
     */
    private boolean lastLineInputCheck() {
        ListGridRecord[] records = listGrid.getRecords();

        if (records.length > 0) {
            String name = records[records.length - 1].getAttributeAsString("name");
            String type = records[records.length - 1].getAttributeAsString("type");
            if ("".equals(name.trim()) && "".equals(type.trim())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Create a new input row in ListGrid.
     */
    private void createNewLine() {
        ListGridRecord record = new ListGridRecord();
        record.setAttribute("name", "");
        record.setAttribute("type", "");
        listGrid.addData(record);

        FSMDVariable fsmVariable = FSMFactory.eINSTANCE.createFSMDVariable();
        fsmVariable.setName("");
        fsmVariable.setType("");
        fsmVariables.put(record, fsmVariable);
    }

    /**
     * Create an event that fires when the input to the cell is saved.<br>
     * A command to apply the input values ​​to the EMF model is created.
     * @param feature Structural feature name or type
     * @return Cell Saved Event Handler
     */
    private CellSavedHandler addCellSavedHandler(EStructuralFeature feature) {
        return new CellSavedHandler() {
            @Override
            public void onCellSaved(CellSavedEvent event) {
                boolean lastLineFlag = false;
                if (event.getRecord().equals(listGrid.getRecords()[listGrid.getRecords().length - 1])) {
                    lastLineFlag = true;
                }
                FSMEditorCommandProvider.getInstance().cellSavedVariable(cmd, editor.getFSMDStateMachine(), fsmVariables.get(event.getRecord()), feature, event.getNewValue(),
                        listGrid.getRecords().length - 1, lastLineFlag);

                if (lastLineInputCheck()) {
                    createNewLine();
                }
            }
        };
    }

    /**
     * ListGrid key press event is created.
     * @return key press handler
     */
    private KeyPressHandler addKeyPressHandler() {
        return new KeyPressHandler() {

            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.DEL.equals(event.getKeyName())) {
                    deleteItem.fireEvent(new MenuItemClickEvent(event.getFiringCanvas().getJsObj()));
                }
            }
        };
    }

    /**
     * Create a context menu for ListGrid.
     * @return menu
     */
    private Menu addRightClickMenu() {
        Menu menu = new Menu();
        deleteItem.setTitle("Delete");
        deleteItem.setKeyTitle("Delete");
        deleteItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                if (listGrid.getSelectedRecords().length > 0) {
                    List<FSMDVariable> removeFSMDVariables = new ArrayList<>();
                    for (Record record : listGrid.getSelectedRecords()) {

                        // last line delete is skip
                        if (listGrid.getRecordIndex(record) == listGrid.getRecords().length - 1) {
                            continue;
                        }
                        removeFSMDVariables.add(fsmVariables.get(record));

                        listGrid.removeData(record);
                        fsmVariables.remove(record);
                    }
                    FSMEditorCommandProvider.getInstance().removeVariable(cmd, editor.getFSMDStateMachine(), removeFSMDVariables);
                }
            }
        });
        menu.addItem(deleteItem);
        return menu;
    }

    /**
     * The FSMDVariable list is sorted.
     * @param properties FSMDVariable model to sort
     * @param fieldNum If it is 0, it will be sorted based on the name column. <br>
     *            If it is 1, it will be sorted based on the type column.
     */
    private void sortVariables(EList<com.zipc.garden.model.fsm.FSMDVariable> properties, int fieldNum) {
        Comparator<com.zipc.garden.model.fsm.FSMDVariable> comparator = createVariableComparetor(fieldNum);
        ECollections.sort(properties, comparator);
    }

    /**
     * The sort condition is acquired.
     * @param fieldNum If 0, get sort condition of name column. In case of 1, get sort condition of type column.
     * @return Sort conditions
     */
    private Comparator<com.zipc.garden.model.fsm.FSMDVariable> createVariableComparetor(int fieldNum) {
        if (fieldNum == 0) {
            return nameCount % 2 == 0 ? nameComparator(true) : nameComparator(false);
        } else {
            return typeCount % 2 == 0 ? typeComparator(true) : typeComparator(false);
        }
    }

    /**
     * It is sorted based on the values ​​in the name column.<br>
     * The value that has not been entered is placed at the end.
     * @param order If true, ascending order. If false, descending order.
     * @return Sort conditions
     */
    private Comparator<com.zipc.garden.model.fsm.FSMDVariable> nameComparator(boolean order) {
        nameCount++;
        return new Comparator<com.zipc.garden.model.fsm.FSMDVariable>() {

            @Override
            public int compare(com.zipc.garden.model.fsm.FSMDVariable o1, com.zipc.garden.model.fsm.FSMDVariable o2) {

                if ("".equals(o1.getName())) {
                    return 1;
                } else if ("".equals(o2.getName())) {
                    return -1;
                } else {
                    return order == true ? o1.getName().compareTo(o2.getName()) : o2.getName().compareTo(o1.getName());
                }
            }
        };
    }

    /**
     * It is sorted based on the values ​​in the type column.<br>
     * The value that has not been entered is placed at the end.
     * @param order If true, ascending order. If false, descending order.
     * @return Sort conditions
     */
    private Comparator<com.zipc.garden.model.fsm.FSMDVariable> typeComparator(boolean order) {
        typeCount++;
        return new Comparator<com.zipc.garden.model.fsm.FSMDVariable>() {

            @Override
            public int compare(com.zipc.garden.model.fsm.FSMDVariable o1, com.zipc.garden.model.fsm.FSMDVariable o2) {

                if ("".equals(o1.getType())) {
                    return 1;
                } else if ("".equals(o2.getType())) {
                    return -1;
                } else {
                    return order == true ? o1.getType().compareTo(o2.getType()) : o2.getType().compareTo(o1.getType());
                }
            }
        };
    }
}
