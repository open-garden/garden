package com.zipc.garden.webplatform.client.editor.arc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.SortSpecifier;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.KnobType;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawRect;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
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
import com.zipc.garden.model.arc.ARCFactory;
import com.zipc.garden.model.arc.ARCLine;
import com.zipc.garden.model.arc.ARCState;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.LineType;
import com.zipc.garden.webplatform.client.command.ARCEditorCommandProvider;
import com.zipc.garden.webplatform.client.command.EditorCommandProvider;
import com.zipc.garden.webplatform.client.editor.EditorDrawLine;
import com.zipc.garden.webplatform.client.editor.EditorLineUtil;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * This class manages the Item drawn in the architecture editor.
 */
public class ARCStateManager {

    /** A map of the copied DrawItem. */
    private Map<ARCState, DrawItem> copyDrawItemMap;

    /** A map of the copied DrawLine. */
    private Map<ARCLine, EditorDrawLine> copyEditorDrawLineMap;

    /** Main class of architecture editor */
    private final ARCEditor editor;

    /**
     * constructor
     * @param editor Main class of architecture editor
     */
    public ARCStateManager(ARCEditor editor) {
        this.editor = editor;
    }

    /**
     * Displays the rename modal window for DrawItem.
     * @param drawItem DrawItem to rename
     */
    protected void onRename(DrawItem drawItem) {

        ARCState state = getKey(editor.getDrawItems(), drawItem).get();

        final Window winModal = new Window();
        winModal.setTop(drawItem.getPageTop() + drawItem.getAttributeAsInt("height"));
        winModal.setLeft(drawItem.getPageLeft() + drawItem.getAttributeAsInt("width"));
        winModal.setHeight(140);
        winModal.setWidth(300);
        winModal.setTitle("Rename State");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.setKeepInParentRect(true);
        winModal.addCloseClickHandler(e -> winModal.markForDestroy());

        if (winModal.getTop() + winModal.getHeight() > editor.getDrawPane().getPageBottom()) {
            winModal.setTop(editor.getDrawPane().getPageBottom() - winModal.getHeight());
        }
        if (winModal.getLeft() + winModal.getWidth() > editor.getDrawPane().getPageRight()) {
            winModal.setLeft(editor.getDrawPane().getPageRight() - winModal.getWidth());
        }

        VLayout renameLayout = new VLayout();
        renameLayout.setHeight100();
        renameLayout.setWidth100();
        renameLayout.setPadding(3);
        renameLayout.setOverflow(Overflow.HIDDEN);

        DynamicForm textForm = new DynamicForm();
        textForm.setAutoFocus(true);
        textForm.setMargin(5);
        textForm.setNumCols(2);
        textForm.setColWidths(80, 220);

        HLayout buttonLayout = new HLayout();
        buttonLayout.setHeight100();
        buttonLayout.setWidth100();
        buttonLayout.setAlign(Alignment.CENTER);

        TextItem stateText = new TextItem();
        stateText.setTitle("State");
        stateText.setValue(state.getName());
        stateText.setSelectOnFocus(true);

        StaticTextItem fileNameItem = new StaticTextItem();
        fileNameItem.setHeight(30);
        fileNameItem.setTitle("File&nbsp;Name");
        fileNameItem.setWrap(false);
        editor.getService().getVMFile(state.getFileId(), editor.getProjectId(), new AsyncCallback<VMFile>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(VMFile result) {
                if (result == null) {
                    fileNameItem.setValue("File Not Found.");
                } else {
                    fileNameItem.setValue(result.getName());
                }
            }
        });

        IButton okButton = new IButton();
        okButton.setTitle("OK");
        okButton.setWidth(80);
        okButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                String newValue;
                if (stateText.getValue() == null || "".equals(stateText.getValue().toString().trim())) {
                    newValue = null;
                } else {
                    newValue = stateText.getValue().toString();
                }
                CompoundCommand cmd = ARCEditorCommandProvider.getInstance().renameState(state, newValue);
                editor.manager.execute(cmd.unwrap());
                editor.refresh();
                winModal.markForDestroy();
            }
        });

        IButton cancelButton = new IButton();
        cancelButton.setTitle("Cancel");
        cancelButton.setWidth(80);
        cancelButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                winModal.markForDestroy();
            }
        });
        renameLayout.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.ENTER.equals(event.getKeyName())) {
                    okButton.fireEvent(new com.smartgwt.client.widgets.events.ClickEvent(okButton.getJsObj()));
                }
            }
        });
        textForm.setFields(stateText, fileNameItem);
        buttonLayout.addMembers(okButton, new LayoutSpacer(15, 0), cancelButton);
        renameLayout.addMembers(textForm, buttonLayout);
        renameLayout.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.ENTER.equals(event.getKeyName())) {
                    okButton.fireEvent(new com.smartgwt.client.widgets.form.fields.events.ClickEvent(okButton.getJsObj()));
                }
            }
        });
        winModal.addItem(renameLayout);
        winModal.show();
    }

    /**
     * Delete the DrawItem and DrawLine that match the argument.<br>
     * The associated EMF model is also deleted.
     * @param drawItems The DrawItem to remove
     * @param drawLines The DrawLine to remove
     */
    protected void onDelete(Map<ARCState, DrawItem> drawItems, Map<ARCLine, EditorDrawLine> drawLines) {
        drawLines.keySet().forEach(line -> {
            editor.getEditorDrawLines().get(line).getDotLines().forEach(dotLine -> dotLine.erase());
        });
        List<ARCState> delStates = new ArrayList<ARCState>(drawItems.keySet());
        List<ARCLine> delLines = new ArrayList<>(drawLines.keySet());
        drawItems.entrySet().forEach(action -> {
            List<ARCLine> lines = editor.lineManager.getKey(editor.getEditorDrawLines(), action.getValue());
            lines.forEach(list -> delLines.add(list));
        });
        List<ARCLine> delLinesDistinct = delLines.stream().distinct().collect(Collectors.toList());

        CompoundCommand cmd = ARCEditorCommandProvider.getInstance().removeStates(editor.getARCRoot(), delStates, delLinesDistinct);
        AtomicInteger priority = new AtomicInteger(1);
        editor.getDrawItems().entrySet().stream().map(m -> m.getKey()).filter(delState -> !delStates.stream().filter(r -> EcoreUtil.equals(delState, r)).findFirst().isPresent())
                .sorted((l, r) -> l.getEvalPriority() - r.getEvalPriority())
                .forEach(t -> ARCEditorCommandProvider.getInstance().savePriorityState(cmd, t, priority.getAndIncrement()));
        editor.manager.execute(cmd.unwrap());
        editor.refresh();
    }

    /**
     * Get the element to be deleted and call the deletion process
     */
    protected void deleteStateAndLine() {
        Map<ARCState, DrawItem> drawItems = getSelectedDrawItems();
        Map<ARCLine, EditorDrawLine> drawLines = editor.lineManager.getSelectedDrawLines();
        if (drawItems.size() == 0 && drawLines.size() == 0) {
            SC.warn("Please select node or line.");
            return;
        } else {
            onDelete(drawItems, drawLines);
        }
    }

    /**
     * Create a new DrawItem on Canvas based on the EMF model.
     * @param state ARC state of EMF model
     * @return new DrawItem
     */
    protected DrawItem makeNewDrawItem(ARCState state) {

        DrawRect drawRect = new DrawRect();
        drawRect.setDrawPane(editor.getDrawPane());
        drawRect.draw();
        drawRect.setWidth(state.getWidth());
        drawRect.setHeight(state.getHeight());
        drawRect.setTop(state.getTop());
        drawRect.setLeft(state.getLeft());
        drawRect.setTitle(state.getName());
        drawRect.setCanDrag(true);
        drawRect.setCanHover(true);
        drawRect.setKeepInParentRect(true);
        drawRect.bringToFront();
        drawRect.setLineOpacity(1);
        drawRect.setLineWidth(2);
        drawRect.setFillOpacity(1);
        drawRect.setFillColor("white");

        return drawRect;
    }

    /**
     * Gets the EMF model associated with the DrawItem.
     * @param map Map in which DrawItem and EMF model are related
     * @param value DrawItem to search
     * @return EMF model. Empty if unable to get
     */
    protected Optional<ARCState> getKey(Map<ARCState, DrawItem> map, DrawItem value) {
        return map.entrySet().stream().filter(entry -> value.equals(entry.getValue())).map(Map.Entry::getKey).findFirst();
    }

    /**
     * Finds the DrawItem at the specified coordinate position.<br>
     * Gets the EMF model associated with the DrawItem.
     * @param x The specified X coordinate
     * @param y The specified Y coordinate
     * @return Map in which DrawItem and EMF model are related
     */
    protected Map<ARCState, DrawItem> getDrawItemIsBelowThePointer(int x, int y) {
        return editor.getDrawItems().entrySet().stream().filter(map -> map.getValue().isPointInPath(x, y)).collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
    }

    /**
     * Get all the selected DrawItems.
     * @return Map in which DrawItem and EMF model are related
     */
    public Map<ARCState, DrawItem> getSelectedDrawItems() {
        return editor.getDrawItems().entrySet().stream().filter(map -> getSelectFlag(map.getValue())).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    }

    /**
     * Controls the selection state of the argument DrawItem.
     * @param drawItem The target DrawItem
     * @param selectFlag If true, selected.
     */
    protected void selectDrawItem(DrawItem drawItem, boolean selectFlag) {
        if (drawItem instanceof DrawRect) {
            if (selectFlag) {
                drawItem.setFillColor("yellow");
                drawItem.showKnobs(KnobType.RESIZE);
            } else {
                drawItem.setFillColor("white");
                drawItem.hideAllKnobs();
            }
        } else {
            if (selectFlag) {
                drawItem.setLineColor("blue");
                drawItem.showKnobs(KnobType.RESIZE);
            } else {
                drawItem.setLineColor("gray");
                drawItem.hideAllKnobs();
            }
        }
    }

    /**
     * Controls the selection state of all DrawItems.
     * @param selectFlag If true, selected.
     */
    protected void selectDrawItemAll(boolean selectFlag) {
        editor.getDrawItems().entrySet().forEach(map -> {
            selectDrawItem(map.getValue(), selectFlag);
        });
    }

    /**
     * Gets the selection state of the argument DrawItem.
     * @param drawItem The target DrawItem
     * @return If true, it is selected.
     */
    protected boolean getSelectFlag(DrawItem drawItem) {
        if (drawItem.getKnobs() == null)
            return false;
        else
            return Arrays.asList(drawItem.getKnobs()).stream().filter(type -> type.equals(KnobType.RESIZE)).findFirst().isPresent() || "blue".equals(drawItem.getLineColor());
    }

    /**
     * Display the "Add State Machine" modal window.<br>
     * The FSMs created in the project are listed.
     * @param stateLeft An item will be created at this X coordinate
     * @param stateTop An item will be created at this Y coordinate
     */
    protected void addStateMachine(int stateLeft, int stateTop) {
        final Window winModal = new Window();
        winModal.setTitle("Add State Machine");
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

        DynamicForm searchForm = new DynamicForm();
        searchForm.setNumCols(2);
        searchForm.setBackgroundColor("white");
        searchForm.setMargin(3);
        TextItem searchItem = new TextItem("Search");
        searchForm.setFields(searchItem);

        ListGrid dependentFile = new ListGrid();
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
        ListGridField fileNameField = new ListGridField("fileName", "");
        fileNameField.setType(ListGridFieldType.TEXT);
        ListGridField fullPathField = new ListGridField("fullPath", "");
        fullPathField.setType(ListGridFieldType.TEXT);
        dependentFile.setFields(idfield, fileNameField, fullPathField);

        HLayout hlayout = new HLayout();
        hlayout.setHeight(30);
        hlayout.setWidth100();
        hlayout.setLayoutLeftMargin(20);
        IButton okBtn = new IButton("OK");
        okBtn.setHeight100();
        okBtn.setWidth100();
        okBtn.setMargin(5);
        IButton cancelBtn = new IButton("CANCEL");
        cancelBtn.setHeight100();
        cancelBtn.setWidth100();
        cancelBtn.setMargin(5);
        LayoutSpacer hspacer = new LayoutSpacer(3, "100%");
        hspacer.setBackgroundColor("white");

        hlayout.addMembers(hspacer, okBtn, hspacer, cancelBtn, hspacer);

        dependentFile.addCellDoubleClickHandler(new CellDoubleClickHandler() {
            @Override
            public void onCellDoubleClick(CellDoubleClickEvent event) {
                dependentFile.selectRecord(event.getRecord());
                okBtn.fireEvent(new com.smartgwt.client.widgets.events.ClickEvent(okBtn.getJsObj()));
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

        okBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                if (dependentFile.getSelectedRecord() == null) {
                    event.cancel();
                    return;
                }
                List<String> uuids = new ArrayList<String>();
                for (ListGridRecord record : dependentFile.getSelectedRecords()) {
                    String uuid = record.getAttributeAsString("uuid");
                    uuids.add(uuid);
                }
                addARCStates(stateLeft, stateTop, uuids);

                winModal.markForDestroy();
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
                    okBtn.fireEvent(new com.smartgwt.client.widgets.events.ClickEvent(okBtn.getJsObj()));
                }
            }
        });

        formlayout.addMembers(searchForm, dependentFile);
        winModal.addMember(formlayout);

        List<String> extensions = Arrays.asList(new String[] { Extension.FSM.getValue() });
        createListGridData(editor.getService(), editor.getProjectId(), extensions, dependentFile);
        winModal.show();
        winModal.getFooter().addMember(hlayout, 0);

        winModal.addResizedHandler(new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent event) {
                formlayout.setWidth100();
            }
        });
    }

    /**
     * A modal window for setting the ARCState priority appears.
     */
    protected void prioritySetting() {
        final Window winModal = new Window();
        winModal.setTitle("Priority Setting");
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

        ListGrid arcStateList = new ListGrid();
        arcStateList.setBackgroundColor("white");
        arcStateList.setWidth100();
        arcStateList.setHeight100();
        arcStateList.setShowHeaderContextMenu(false);
        arcStateList.setShowHeaderMenuButton(false);
        arcStateList.setCanResizeFields(false);
        arcStateList.setCanReorderFields(false);
        arcStateList.setLeaveScrollbarGap(false);
        arcStateList.setCanReorderRecords(true);
        arcStateList.setMargin(5);

        ListGridField priorityField = new ListGridField("priority", "");
        priorityField.setHidden(true);
        ListGridField stateName = new ListGridField("stateName", "");
        stateName.setType(ListGridFieldType.TEXT);
        ListGridField stateField = new ListGridField("state", "");
        stateField.setHidden(true);
        arcStateList.setFields(priorityField, stateName, stateField);
        arcStateList.setSort(new SortSpecifier("priority", SortDirection.ASCENDING));

        HLayout hlayout = new HLayout();
        hlayout.setHeight(30);
        hlayout.setWidth100();
        hlayout.setLayoutLeftMargin(20);
        IButton okBtn = new IButton("OK");
        okBtn.setHeight100();
        okBtn.setWidth100();
        okBtn.setMargin(5);
        IButton cancelBtn = new IButton("CANCEL");
        cancelBtn.setHeight100();
        cancelBtn.setWidth100();
        cancelBtn.setMargin(5);
        LayoutSpacer hspacer = new LayoutSpacer(3, "100%");
        hspacer.setBackgroundColor("white");

        hlayout.addMembers(hspacer, okBtn, hspacer, cancelBtn, hspacer);

        okBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                CompoundCommand cmd = new CompoundCommand();
                int priority = 1;
                for (ListGridRecord record : arcStateList.getRecords()) {
                    ARCState arcState = (ARCState) record.getAttributeAsObject("state");
                    if (arcState.getEvalPriority() == priority) {
                        priority++;
                        continue;
                    } else {
                        ARCEditorCommandProvider.getInstance().savePriorityState(cmd, arcState, priority++);
                    }
                }
                if (!cmd.isEmpty()) {
                    editor.getEditManager().execute(cmd.unwrap());
                    editor.refresh();
                }
                winModal.markForDestroy();
            }
        });

        cancelBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                winModal.markForDestroy();
            }
        });

        arcStateList.addBodyKeyPressHandler(new BodyKeyPressHandler() {
            @Override
            public void onBodyKeyPress(BodyKeyPressEvent event) {
                if (KeyNames.ENTER.equals(EventHandler.getKey())) {
                    okBtn.fireEvent(new com.smartgwt.client.widgets.events.ClickEvent(okBtn.getJsObj()));
                }
            }
        });

        formlayout.addMembers(arcStateList);
        winModal.addMember(formlayout);

        createArcStateList(arcStateList);
        winModal.show();
        winModal.getFooter().addMember(hlayout, 0);

        winModal.addResizedHandler(new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent event) {
                formlayout.setWidth100();
            }
        });
    }

    /**
     * ARCState is added based on the content of the arguments. <br>
     * The canvas is redrawn.
     * @param stateLeft The position to add the item to the canvas(X coordinate)
     * @param stateTop The position to add the item to the canvas(Y coordinate)
     * @param uuids File UUID selected in FSM list
     */
    public void addARCStates(int stateLeft, int stateTop, List<String> uuids) {
        int left = stateLeft;
        int top = stateTop;
        List<ARCState> newStates = new ArrayList<>();
        int priority = editor.getMaxEvalPriority();

        for (String uuid : uuids) {

            ARCState state = ARCFactory.eINSTANCE.createARCState();
            state.setFileId(uuid);
            state.setLeft(left);
            state.setTop(top);
            state.setHeight(40);
            state.setWidth(80);
            state.setEvalPriority(priority++);

            left += 20;
            top += 20;

            newStates.add(state);
        }
        CompoundCommand cmd = ARCEditorCommandProvider.getInstance().addState(editor.getARCRoot(), newStates);
        editor.getEditManager().execute(cmd.unwrap());
        editor.refresh();
    }

    /**
     * It is a method that retains the copied DrwaItem and DrawLine.
     */
    protected void copyStateMachine() {
        Map<ARCState, DrawItem> selectedMap = getSelectedDrawItems();
        if (selectedMap == null || selectedMap.isEmpty()) {
            return;
        }
        copyDrawItemMap = selectedMap;
        copyEditorDrawLineMap = editor.lineManager.getSelectedDrawLines();
    }

    /**
     * Get the coordinates of the selected DrwaItem and paste the copied DrawItem.
     * @param posItem The selected DrawItem
     */
    protected void pasteStateMachine(DrawItem posItem) {
        ARCState posState = editor.stateManager.getKey(editor.getDrawItems(), posItem).get();
        pasteStateMachine(posState.getTop(), posState.getLeft());
    }

    /**
     * Paste the copied DrawItem at the specified coordinates.
     * @param top Coordinates (Y coordinate) to paste DrawItem
     * @param left Coordinates (X coordinate) to paste DrawItem
     */
    protected void pasteStateMachine(int top, int left) {
        if (!existsCopyState()) {
            return;
        }
        CompoundCommand cmd = new CompoundCommand();
        int minTop = copyDrawItemMap.keySet().stream().min(Comparator.comparing(x -> x.getTop())).get().getTop();
        int minLeft = copyDrawItemMap.keySet().stream().min(Comparator.comparing(x -> x.getLeft())).get().getLeft();
        int priority = editor.getMaxEvalPriority();

        Map<ARCState, ARCState> createdStateMap = new HashMap<ARCState, ARCState>();
        List<ARCState> createdStateList = new ArrayList<ARCState>();
        Map<ARCState, ARCState> srcTgtMap = new HashMap<ARCState, ARCState>();
        for (ARCState state : copyDrawItemMap.keySet()) {
            ARCState newState = ARCFactory.eINSTANCE.createARCState();
            newState.setFileId(state.getFileId());
            newState.setName(state.getName());
            newState.setTop(state.getTop() - minTop + top + editor.getSnapGap());
            newState.setLeft(state.getLeft() - minLeft + left + +editor.getSnapGap());
            newState.setHeight(state.getHeight());
            newState.setWidth(state.getWidth());
            newState.setEvalPriority(priority++);
            cmd.append(ARCEditorCommandProvider.getInstance().addState(editor.getARCRoot(), newState, editor.getARCRoot().getStates().size()));
            createdStateMap.put(state, newState);
            createdStateList.add(newState);
        }
        Map<ARCLine, EditorDrawLine> lineMap = copyEditorDrawLineMap.entrySet().stream().filter(entry -> {
            return copyDrawItemMap.containsValue(entry.getValue().getDrawItems()[0]) && copyDrawItemMap.containsValue(entry.getValue().getDrawItems()[1]);
        }).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        for (ARCLine k : lineMap.keySet()) {
            ARCState newSourceState = createdStateMap.get(k.getSource());
            ARCState newTargetState = createdStateMap.get(k.getTarget());
            ARCLine newLine = ARCFactory.eINSTANCE.createARCLine();
            cmd.append(
                    ARCEditorCommandProvider.getInstance().addLine(newLine, editor.getARCRoot(), newSourceState, newTargetState, null, k.getVariableName(), k.getVariableType()));
            if (k.getType() != null && k.getType().equals(LineType.MANHATTAN)) {
                newLine.setType(k.getType());
                List<double[]> mPoints = EditorLineUtil.getManhattanPoint(newLine, newSourceState, newTargetState, null, null);
                List<AbstractPoint> points = EditorLineUtil.getConnectionPoint(mPoints, editor);
                cmd = EditorCommandProvider.getInstance().addPoint(cmd, newLine, points);
                cmd = ARCEditorCommandProvider.getInstance().resizeOutgoingLine(cmd, newLine, newSourceState, k.getSourceAnchorX(), k.getSourceAnchorY());
                cmd = ARCEditorCommandProvider.getInstance().resizeIncomingLine(cmd, newLine, newTargetState, k.getTargetAnchorX(), k.getTargetAnchorY());
            }
            srcTgtMap.put(newSourceState, newTargetState);
        }

        editor.getEditManager().execute(cmd.unwrap());
        editor.refresh();
        selectDrawItemAll(false);
        srcTgtMap.forEach((s, t) -> {
            Map<ARCLine, EditorDrawLine> newLines = editor.lineManager.getMap(s, t);
            newLines.values().stream().forEach(v -> {
                EditorLineUtil.selectDrawLine(v.getDrawLabel(), v.getDrawLines(), v.getDotLines(), true);
            });
        });

        createdStateList.forEach(x -> {
            editor.stateManager.selectDrawItem(editor.getDrawItems().get(x), true);
        });
    }

    /**
     * Method to check if DrawItem is copied
     * @return True if copied
     */
    protected boolean existsCopyState() {
        return copyDrawItemMap != null && !copyDrawItemMap.isEmpty();
    }

    /**
     * Check if there is a selected DrawItem
     * @return True if there is a selected DrawItem
     */
    protected boolean isSelectedStates() {
        return getSelectedDrawItems() != null && !getSelectedDrawItems().isEmpty();
    }

    /**
     * A list of FSM created in the project is created.
     * @param editResourceService Asynchronous interface
     * @param projectId The project ID in which this ARC is managed.
     * @param extensions File extension FSM
     * @param dependentFile ListGrid displaying a list of FSM
     */
    private void createListGridData(EditResourceServiceAsync editResourceService, long projectId, List<String> extensions, ListGrid dependentFile) {
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
     * Create a record in the ListGrid that displays the ARCState priority.
     * @param arcStateList ListGrid displaying ARCState priority
     */
    private void createArcStateList(ListGrid arcStateList) {
        for (Entry<ARCState, DrawItem> entry : editor.getDrawItems().entrySet()) {
            ListGridRecord retData = new ListGridRecord();
            retData.setAttribute("priority", entry.getKey().getEvalPriority());
            retData.setAttribute("stateName", entry.getValue().getTitle());
            retData.setAttribute("state", entry.getKey());
            arcStateList.addData(retData);
        }
        arcStateList.sort();
        Scheduler.get().scheduleDeferred(() -> {
            arcStateList.selectSingleRecord(0);
            arcStateList.focus();
        });
    }

    /**
     * This is a data source related to FSM ListGrid.
     */
    private static class DependentFileDS extends DataSource {

        private static DependentFileDS instance = null;

        public static DependentFileDS getInstance() {
            if (instance == null) {
                instance = new DependentFileDS("dependentFileDS");
            }
            return instance;
        }

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

    /**
     * Check if the FSM file related to the DrawItem (ARCState) drawn on the canvas exists.<br>
     * If it does not exist, "File Not Found" is displayed in red in DrawItem.
     */
    protected void reacquireStateMachine() {
        Scheduler.get().scheduleDeferred(() -> {
            editor.getDrawItems().forEach((state, drawItem) -> {
                editor.getService().getVMFile(state.getFileId(), editor.getProjectId(), new AsyncCallback<VMFile>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(VMFile result) {
                        if (result == null) {
                            drawItem.setTitle(state.getName() != null && !"".equals(state.getName().trim()) ? state.getName() : "File Not Found");
                            drawItem.getTitleLabel().setLineColor("#FF0000");
                        } else {
                            drawItem.setTitle(state.getName() != null && !"".equals(state.getName().trim()) ? state.getName() : result.getName());
                            drawItem.getTitleLabel().setLineColor("#808080");
                        }
                    }
                });
            });
        });
    }
}
