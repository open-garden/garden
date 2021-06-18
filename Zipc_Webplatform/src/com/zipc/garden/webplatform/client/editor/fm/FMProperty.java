package com.zipc.garden.webplatform.client.editor.fm;

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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.util.SC;
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
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.model.fm.FMFactory;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMPackage;
import com.zipc.garden.webplatform.client.command.FMEditorCommandProvider;
import com.zipc.garden.webplatform.client.service.ProjectService;
import com.zipc.garden.webplatform.client.service.ProjectServiceAsync;
import com.zipc.garden.webplatform.shared.UserPropertyInfo;

/**
 * This class manages information about the properties of the feature-model editor.
 */
public class FMProperty {

    /** Asynchronous interface to get the properties set in the project */
    private final ProjectServiceAsync projectService = GWT.create(ProjectService.class);

    /**
     * It is incremented when the key header is pressed.<br>
     * If it is even, it will be sorted in ascending order.<br>
     * If it is odd, it will be sorted in descending order.
     */
    private int keyCount = 0;

    /**
     * It is incremented when the value header is pressed.<br>
     * If it is even, it will be sorted in ascending order.<br>
     * If it is odd, it will be sorted in descending order.
     */
    private int valueCount = 0;

    /**
     * ENUM that manages the type of property edit screen that is displayed
     */
    protected enum ProcessType {
        Canvas, Node;
    }

    /**
     * Create an event when you click the property menu.
     * @param editor Main class of feature-model editor
     * @param type Property creation target
     * @param node Node that creates the property. Null for canvas properties.
     * @return Created property menu click event
     */
    protected com.smartgwt.client.widgets.menu.events.ClickHandler getPropClickMenuHandler(FMEditor editor, ProcessType type, FMNode node) {
        return new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                openProperty(editor, type, node);
            }
        };
    }

    /**
     * Gets the properties set in the project. <br>
     * Display the property edit modal window with the acquired property as the initial value of the key combo box.
     * @param editor Main class of feature-model editor
     * @param type Property creation target
     * @param node Node that creates the property. Null for canvas properties.
     */
    protected void openProperty(FMEditor editor, ProcessType type, FMNode node) {
        projectService.getUserPropertyInfoList(editor.getProjectId(), new AsyncCallback<List<UserPropertyInfo>>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(List<UserPropertyInfo> result) {
                List<String> values = new ArrayList<>();
                result.forEach(info -> {
                    values.add(info.getUserProperty());
                });
                openProperty(editor, type, node, values.toArray(new String[values.size()]));
            }
        });
    }

    /**
     * Displays the property edit modal window.
     * @param editor Main class of feature-model editor
     * @param type Property creation target
     * @param node Node that creates the property. Null for canvas properties.
     * @param cmbKeys Default value of combo box displayed in key
     */
    protected void openProperty(FMEditor editor, ProcessType type, FMNode node, String[] cmbKeys) {
        CompoundCommand cmd = new CompoundCommand();
        Map<Integer, com.zipc.garden.model.fm.FMProperty> props = new HashMap<>();

        final Window winModal = new Window();
        winModal.setTop(editor.layout.getHeight() / 4);
        winModal.setLeft(editor.layout.getWidth() / 4);
        winModal.setHeight(editor.layout.getHeight() / 2);
        winModal.setWidth(editor.layout.getWidth() / 2);
        winModal.setTitle("Preferences");
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
        if (winModal.getTop() + winModal.getHeight() > editor.drawPane.getPageBottom()) {
            winModal.setTop(editor.drawPane.getPageBottom() - winModal.getHeight());
        }
        if (winModal.getLeft() + winModal.getWidth() > editor.drawPane.getPageRight()) {
            winModal.setLeft(editor.drawPane.getPageRight() - winModal.getWidth());
        }

        VLayout form = new VLayout();
        form.setHeight100();
        form.setWidth100();

        PropertyDS propDS = PropertyDS.getInstance(String.valueOf(editor.getFileId()));
        ListGrid propertyList = new ListGrid();
        propertyList.setWidth100();
        propertyList.setHeight100();
        propertyList.setAutoFetchData(true);
        propertyList.setEditEvent(ListGridEditEvent.DOUBLECLICK);
        propertyList.setEditByCell(true);
        propertyList.setCanSort(false);
        propertyList.setCanResizeFields(true);
        propertyList.setShowSortArrow(SortArrow.NONE);
        propertyList.setShowHeaderContextMenu(false);
        propertyList.setShowHeaderMenuButton(false);
        propertyList.setCanReorderFields(false);
        propertyList.setLeaveScrollbarGap(false);
        propertyList.setMargin(5);

        ComboBoxItem cmbKey = new ComboBoxItem();
        cmbKey.setType("comboBox");
        cmbKey.setValueMap(cmbKeys);

        ListGridField keyField = new ListGridField("key");
        keyField.setType(ListGridFieldType.TEXT);
        keyField.setCanEdit(true);
        keyField.setEditorProperties(cmbKey);
        keyField.addCellSavedHandler(new CellSavedHandler() {
            @Override
            public void onCellSaved(CellSavedEvent event) {
                FMEditorCommandProvider.getInstance().setProperty(cmd, props.get(event.getRecord().hashCode()), FMPackage.Literals.FM_PROPERTY__KEY, event.getOldValue(),
                        event.getNewValue());
            }
        });

        ListGridField valueField = new ListGridField("value");
        valueField.setType(ListGridFieldType.TEXT);
        valueField.setCanEdit(true);
        valueField.addCellSavedHandler(new CellSavedHandler() {

            @Override
            public void onCellSaved(CellSavedEvent event) {
                FMEditorCommandProvider.getInstance().setProperty(cmd, props.get(event.getRecord().hashCode()), FMPackage.Literals.FM_PROPERTY__VALUE, event.getOldValue(),
                        event.getNewValue());
                if (props.size() == event.getRowNum() + 1) {
                    addEditingArea(editor, type, node, cmd, props, propertyList);
                }
            }
        });

        propertyList.setFields(keyField, valueField);

        propertyList.addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.DEL.equals(event.getKeyName())) {
                    List<com.zipc.garden.model.fm.FMProperty> fMProperties = new ArrayList<>();
                    for (Record target : propertyList.getSelectedRecords()) {
                        fMProperties.add(props.get(target.hashCode()));
                        propertyList.removeData(target);
                        props.remove(target.hashCode());
                    }
                    if (ProcessType.Canvas == type) {
                        FMEditorCommandProvider.getInstance().removeProperties(cmd, editor.getFMRoot(), fMProperties);
                    } else if (ProcessType.Node == type) {
                        FMEditorCommandProvider.getInstance().removeProperties(cmd, node, fMProperties);
                    }
                    propertyList.fetchData();
                }
            }
        });

        winModal.addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.ESC.equals(event.getKeyName())) {
                    winModal.markForDestroy();
                }
            }
        });

        IButton saveButton = new IButton("OK");
        saveButton.setHeight100();
        saveButton.setWidth100();
        saveButton.setMargin(5);
        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                editor.getEditManager().execute(cmd.unwrap());
                editor.refresh();
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

        // load properties
        EList<com.zipc.garden.model.fm.FMProperty> properties = ECollections.emptyEList();
        if (ProcessType.Canvas == type) {
            properties = editor.getFMRoot().getProperties();
        } else if (ProcessType.Node == type) {
            properties = node.getProperties();
        }
        sortProperty(properties, 0);

        for (com.zipc.garden.model.fm.FMProperty prop : properties) {
            ListGridRecord record = new ListGridRecord();
            record.setAttribute("id", propDS.getNextId());
            record.setAttribute("key", prop.getKey());
            record.setAttribute("value", prop.getValue());
            propertyList.addData(record);
            props.put(record.hashCode(), prop);
        }
        propertyList.addHeaderClickHandler(new HeaderClickHandler() {

            @Override
            public void onHeaderClick(HeaderClickEvent event) {
                EList<com.zipc.garden.model.fm.FMProperty> p = FMFactory.eINSTANCE.createFMRoot().getProperties();
                List<com.zipc.garden.model.fm.FMProperty> temp = Arrays.asList(propertyList.getRecords()).stream().map(m -> {
                    com.zipc.garden.model.fm.FMProperty prop = FMFactory.eINSTANCE.createFMProperty();
                    prop.setKey(m.getAttributeAsString("key"));
                    prop.setValue(m.getAttributeAsString("value"));
                    propertyList.removeData(m);
                    return prop;
                }).collect(Collectors.toList());
                ECollections.setEList(p, temp);
                sortProperty(p, event.getFieldNum());

                for (com.zipc.garden.model.fm.FMProperty prop : p) {
                    ListGridRecord record = new ListGridRecord();
                    record.setAttribute("id", propDS.getNextId());
                    record.setAttribute("key", prop.getKey());
                    record.setAttribute("value", prop.getValue());
                    propertyList.addData(record);
                }
            }

        });

        if (props.size() == 0) {
            addEditingArea(editor, type, node, cmd, props, propertyList);
        }
        LayoutSpacer hspacer = new LayoutSpacer(3, "100%");
        hspacer.setBackgroundColor("white");

        HLayout buttons = new HLayout();
        buttons.setWidth100();
        buttons.setHeight(30);
        buttons.setLayoutLeftMargin(20);
        buttons.addMembers(hspacer, saveButton, hspacer, cancelButton, hspacer);

        form.addMembers(propertyList);
        winModal.addMember(form);
        winModal.show();
        winModal.getFooter().addMember(buttons, 0);

        propertyList.selectSingleRecord(propertyList.getRecords().length - 1);
        propertyList.rowDoubleClick(propertyList.getSelectedRecord(), propertyList.getRecords().length - 1, 0);

        winModal.addResizedHandler(new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent event) {
                form.setWidth100();
            }
        });
    }

    /**
     * Create a new property input line.
     * @param editor Main class of feature-model editor
     * @param type Property creation target
     * @param node Node that creates the property
     * @param cmd Property editing commands
     * @param props Map that associates the drawn record with the EMF model
     * @param propertyList ListGrid displaying properties
     */
    protected void addEditingArea(FMEditor editor, ProcessType type, FMNode node, CompoundCommand cmd, Map<Integer, com.zipc.garden.model.fm.FMProperty> props,
            ListGrid propertyList) {
        PropertyDS propDS = PropertyDS.getInstance(String.valueOf(editor.getFileId()));
        ListGridRecord record = new ListGridRecord();
        record.setAttribute("id", propDS.getNextId());
        record.setAttribute("key", "");
        record.setAttribute("value", "");
        propertyList.addData(record);

        com.zipc.garden.model.fm.FMProperty prop = FMFactory.eINSTANCE.createFMProperty();
        prop.setKey("");
        prop.setValue("");
        props.put(record.hashCode(), prop);
        if (ProcessType.Canvas == type) {
            FMEditorCommandProvider.getInstance().addProperty(cmd, editor.getFMRoot(), prop, props.size() - 1);
        } else if (ProcessType.Node == type) {
            FMEditorCommandProvider.getInstance().addProperty(cmd, node, prop, props.size() - 1);
        }
    }

    /**
     * Data source class related to ListGrid that displays properties
     */
    private static class PropertyDS extends DataSource {
        private static List<PropertyDS> instances = new ArrayList<PropertyDS>();

        private int id = 0;

        public int getNextId() {
            return id++;
        }

        public PropertyDS(String id) {
            setID("propertyDS" + id);
            DataSourceIntegerField idField = new DataSourceIntegerField("id");
            idField.setPrimaryKey(true);
            idField.setHidden(true);
            DataSourceTextField keyField = new DataSourceTextField("key");
            DataSourceTextField valueField = new DataSourceTextField("value");
            setFields(idField, keyField, valueField);
            setClientOnly(true);
        }

        public static PropertyDS getInstance(String id) {
            for (PropertyDS instance : instances) {
                if (instance.getID().equals("propertyDS" + id))
                    return instance;
            }
            PropertyDS prop = new PropertyDS(id);
            instances.add(prop);
            return prop;
        }
    }

    /**
     * The FMProperty list is sorted.
     * @param properties FMProperty model to sort
     * @param fieldNum If it is 0, it will be sorted based on the key column. <br>
     *            If it is 1, it will be sorted based on the value column.
     */
    private void sortProperty(EList<com.zipc.garden.model.fm.FMProperty> properties, int fieldNum) {
        Comparator<com.zipc.garden.model.fm.FMProperty> comparator = createPropertyComparetor(fieldNum);
        ECollections.sort(properties, comparator);
    }

    /**
     * The sort condition is acquired.
     * @param fieldNum If 0, get sort condition of key column. In case of 1, get sort condition of value column.
     * @return Sort conditions
     */
    private Comparator<com.zipc.garden.model.fm.FMProperty> createPropertyComparetor(int fieldNum) {
        if (fieldNum == 0) {
            return keyCount % 2 == 0 ? keyComparator(true) : keyComparator(false);
        } else {
            return valueCount % 2 == 0 ? valueComparator(true) : valueComparator(false);
        }
    }

    /**
     * It is sorted based on the values ​​in the key column.<br>
     * The value that has not been entered is placed at the end.
     * @param order If true, ascending order. If false, descending order.
     * @return Sort conditions
     */
    private Comparator<com.zipc.garden.model.fm.FMProperty> keyComparator(boolean order) {
        keyCount++;
        return new Comparator<com.zipc.garden.model.fm.FMProperty>() {

            @Override
            public int compare(com.zipc.garden.model.fm.FMProperty o1, com.zipc.garden.model.fm.FMProperty o2) {

                if ("".equals(o1.getKey())) {
                    return 1;
                } else if ("".equals(o2.getKey())) {
                    return -1;
                } else {
                    return order == true ? o1.getKey().compareTo(o2.getKey()) : o2.getKey().compareTo(o1.getKey());
                }
            }
        };
    }

    /**
     * It is sorted based on the values ​​in the value column.<br>
     * The value that has not been entered is placed at the end.
     * @param order If true, ascending order. If false, descending order.
     * @return Sort conditions
     */
    private Comparator<com.zipc.garden.model.fm.FMProperty> valueComparator(boolean order) {
        valueCount++;
        return new Comparator<com.zipc.garden.model.fm.FMProperty>() {

            @Override
            public int compare(com.zipc.garden.model.fm.FMProperty o1, com.zipc.garden.model.fm.FMProperty o2) {

                if ("".equals(o1.getValue())) {
                    return 1;
                } else if ("".equals(o2.getValue())) {
                    return -1;
                } else {

                    return order == true ? o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue());
                }
            }
        };
    }

}
