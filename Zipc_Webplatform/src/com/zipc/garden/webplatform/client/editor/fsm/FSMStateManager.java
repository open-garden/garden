package com.zipc.garden.webplatform.client.editor.fsm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.CompoundCommand;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.KnobType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.drawing.DrawDiamond;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawOval;
import com.smartgwt.client.widgets.drawing.DrawRect;
import com.smartgwt.client.widgets.drawing.events.DrawStartEvent;
import com.smartgwt.client.widgets.drawing.events.DrawStartHandler;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.zipc.garden.model.fsm.FSMDPseudoStateType;
import com.zipc.garden.model.fsm.FSMDRegion;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.model.fsm.FSMDTransition;
import com.zipc.garden.model.fsm.FSMFactory;
import com.zipc.garden.webplatform.client.command.FSMEditorCommandProvider;
import com.zipc.garden.webplatform.client.editor.EditorDrawLine;

/**
 * Class that manages the processing related to DrawItem created by the behavior model editor.
 */
public class FSMStateManager {

    /** EMF root model of FSM editor. */
    private FSMDStateMachine machine;

    /** Default name of State */
    private final String DEFAULT_STATE_NAME = "State";

    /**
     * Set the field variable of FSM editor.
     * @param machine EMF root model of FSM editor.
     */
    protected void setFieldData(FSMDStateMachine machine) {
        this.machine = machine;
    }

    /**
     * The state information is reflected in the EMF model and the canvas is refreshed.
     * @param editor Main class of behavior-model editor
     * @param left X coordinate of state
     * @param top Y coordinate of state
     * @param type Type of state to create
     */
    protected void onAdd(FSMEditor editor, int left, int top, FSMDPseudoStateType type) {
        AtomicInteger i = new AtomicInteger();
        Optional<FSMDState> fsmdState = Optional.empty();
        StringBuilder newName = new StringBuilder();
        int width = 40;
        int height = 40;
        switch (type) {
        case NONE:
            width = 80;
            do {
                newName.setLength(0);
                newName.append(DEFAULT_STATE_NAME);
                newName.append(i.incrementAndGet());
                fsmdState = machine.getRegions().get(0).getStates().stream().filter(state -> newName.toString().equals(state.getName())).findFirst();
            } while (fsmdState.isPresent());
            break;
        case SHALLOW_HISTORY:
            newName.append("H");
            break;
        case DEEP_HISTORY:
            newName.append("H*");
            break;
        default:
            break;
        }

        FSMDState state = FSMFactory.eINSTANCE.createFSMDState();
        state.setName(newName.toString());
        state.setLeft(left);
        state.setTop(top);
        state.setWidth(width);
        state.setHeight(height);
        state.setType(type);

        FSMDRegion region = machine.getRegions().get(0);
        CompoundCommand cmd = FSMEditorCommandProvider.getInstance().addState(region, state, region.getStates().size());
        editor.manager.execute(cmd.unwrap());
        editor.refresh();
    }

    /**
     * Displays a modal window for changing the state name.
     * @param editor Main class of behavior-model editor
     * @param drawItem DrawItem to rename
     */
    protected void onRename(FSMEditor editor, DrawItem drawItem) {

        FSMDState state = getKey(editor.getDrawItems(), drawItem).get();

        final Window winModal = new Window();
        winModal.setTop(drawItem.getPageTop() + drawItem.getAttributeAsInt("height"));
        winModal.setLeft(drawItem.getPageLeft() + drawItem.getAttributeAsInt("width"));
        winModal.setHeight(100);
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

        DynamicForm textForm = new DynamicForm();
        textForm.setAutoFocus(true);
        textForm.setMargin(5);

        HLayout buttonLayout = new HLayout();
        buttonLayout.setHeight100();
        buttonLayout.setWidth100();
        buttonLayout.setAlign(Alignment.CENTER);

        TextItem stateText = new TextItem();
        stateText.setTitle("State");
        stateText.setValue(state.getName());
        stateText.setSelectOnFocus(true);

        IButton okButton = new IButton();
        okButton.setTitle("OK");
        okButton.setWidth(80);
        okButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                // input check
                if (stateText.getValue() == null || "".equals(stateText.getValue().toString().trim())) {
                    SC.warn("Please input state.");
                    return;
                }
                // Same name check
                if (!state.getName().equals(stateText.getValue().toString())) {
                    CompoundCommand cmd = FSMEditorCommandProvider.getInstance().renameState(state, stateText.getValue().toString());
                    editor.manager.execute(cmd.unwrap());
                    editor.refresh();
                }
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
        textForm.setFields(stateText);
        buttonLayout.addMembers(okButton, new LayoutSpacer(15, 0), cancelButton);
        renameLayout.addMembers(textForm, buttonLayout);
        renameLayout.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.ENTER.equals(event.getKeyName())) {
                    okButton.fireEvent(new com.smartgwt.client.widgets.events.ClickEvent(okButton.getJsObj()));
                }
            }
        });
        winModal.addItem(renameLayout);
        winModal.show();
    }

    /**
     * Delete the selected state and the selected transition line from the EMF model.
     * @param editor Main class of behavior-model editor
     * @param drawItems Selected state
     * @param drawLines Selected line
     */
    protected void onDelete(FSMEditor editor, Map<FSMDState, DrawItem> drawItems, Map<FSMDTransition, EditorDrawLine> drawLines) {
        drawLines.keySet().forEach(transition -> {
            editor.getEditorDrawLines().get(transition).getDotLines().forEach(dotLine -> dotLine.erase());
        });
        List<FSMDState> delStates = new ArrayList<FSMDState>(drawItems.keySet());
        List<FSMDTransition> delTrans = new ArrayList<>(drawLines.keySet());
        drawItems.entrySet().forEach(action -> {
            List<FSMDTransition> trans = editor.transManager.getKey(editor.getEditorDrawLines(), action.getValue());
            trans.forEach(list -> delTrans.add(list));
        });
        List<FSMDTransition> delTransDistinct = delTrans.stream().distinct().collect(Collectors.toList());

        CompoundCommand cmd = FSMEditorCommandProvider.getInstance().removeStates(machine, delStates, delTransDistinct);
        editor.manager.execute(cmd.unwrap());
        editor.refresh();
    }

    /**
     * A new DrawItem will be created.<br>
     * Draw the FSMDState model content on the canvas.
     * @param editor Main class of behavior-model editor
     * @param state FSMDState corresponding to DrawItem
     * @return The created DrawItem
     */
    protected DrawItem makeNewDrawItem(FSMEditor editor, FSMDState state) {
        DrawItem drawItem = new DrawItem();

        switch (state.getType()) {
        case INITIAL:
            DrawOval ovalInit = new DrawOval();
            ovalInit.setDrawPane(editor.getDrawPane());
            ovalInit.draw();
            ovalInit.setWidth(state.getWidth());
            ovalInit.setHeight(state.getHeight());
            ovalInit.setTop(state.getTop());
            ovalInit.setLeft(state.getLeft());
            ovalInit.setCanDrag(true);
            ovalInit.setFillColor("gray");
            ovalInit.setLineColor("gray");
            drawItem = ovalInit;
            break;

        case CHOICE:
            DrawDiamond diamondChoice = new DrawDiamond();
            diamondChoice.setDrawPane(editor.getDrawPane());
            diamondChoice.draw();
            diamondChoice.setWidth(state.getWidth());
            diamondChoice.setHeight(state.getHeight());
            diamondChoice.setTop(state.getTop());
            diamondChoice.setLeft(state.getLeft());
            diamondChoice.setCanDrag(true);
            diamondChoice.setFillColor("white");
            diamondChoice.setLineColor("gray");
            drawItem = diamondChoice;
            break;
        case FINAL:
            DrawOval ovalFinal = new DrawOval();
            ovalFinal.setDrawPane(editor.getDrawPane());
            ovalFinal.draw();
            ovalFinal.setWidth(state.getWidth());
            ovalFinal.setHeight(state.getHeight());
            ovalFinal.setTop(state.getTop());
            ovalFinal.setLeft(state.getLeft());
            ovalFinal.setCanDrag(true);
            ovalFinal.setLineColor("gray");
            drawItem = ovalFinal;

            ovalFinal.addDrawStartHandler(new DrawStartHandler() {
                @Override
                public void onDrawStart(DrawStartEvent event) {
                    int width = ovalFinal.getWidth(), height = ovalFinal.getHeight();
                    int x = ovalFinal.getLeft() + (width / 2);
                    int y = ovalFinal.getTop() + (height / 2);
                    int radius = width > height ? height / 3 : width / 3;

                    Context2d context = ovalFinal.getUnderlyingGWTCanvas().getContext2d();
                    context.setFillStyle("gray");
                    context.beginPath();
                    context.arc(x, y, radius, 0, Math.PI * 2);
                    context.stroke();
                    context.fill();
                }
            });

            break;
        case SHALLOW_HISTORY:
        case DEEP_HISTORY:
            DrawOval ovalHistory = new DrawOval();
            ovalHistory.setDrawPane(editor.getDrawPane());
            ovalHistory.draw();
            ovalHistory.setTitle(state.getName());
            ovalHistory.setWidth(state.getWidth());
            ovalHistory.setHeight(state.getHeight());
            ovalHistory.setTop(state.getTop());
            ovalHistory.setLeft(state.getLeft());
            ovalHistory.setCanDrag(true);
            ovalHistory.setFillColor("white");
            ovalHistory.setLineColor("gray");
            drawItem = ovalHistory;
            Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                @Override
                public void execute() {
                    ovalHistory.getTitleLabel().setFontSize(18);
                }
            });
            break;
        default:
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
            drawRect.setLineOpacity(0);
            drawItem = drawRect;

            // set round corners.
            drawRect.addDrawStartHandler(new DrawStartHandler() {
                @Override
                public void onDrawStart(DrawStartEvent event) {
                    int radiusW = drawRect.getWidth() / 8;
                    int radiusH = drawRect.getHeight() / 4;
                    int radius = radiusW > radiusH ? radiusH : radiusW;

                    Context2d context = drawRect.getUnderlyingGWTCanvas().getContext2d();
                    context.setLineWidth(4);
                    context.setFillStyle(getSelectFlag(drawRect) ? "yellow" : "white");
                    context.setStrokeStyle("rgba(128,128,128,1)");
                    int top = drawRect.getTop();
                    int left = drawRect.getLeft();
                    int right = drawRect.getLeft() + drawRect.getWidth();
                    int bottom = drawRect.getTop() + drawRect.getHeight();
                    double angle0 = 0, angle90 = Math.PI * 0.5, angle180 = Math.PI, angle270 = Math.PI * 1.5;
                    context.beginPath();
                    context.moveTo(left + radius, top);
                    context.lineTo(right - radius, top);
                    context.arc(right - radius, top + radius, radius, angle270, angle0);
                    context.lineTo(right, bottom - radius);
                    context.arc(right - radius, bottom - radius, radius, angle0, angle90);
                    context.lineTo(left + radius, bottom);
                    context.arc(left + radius, bottom - radius, radius, angle90, angle180);
                    context.lineTo(left, top + radius);
                    context.arc(left + radius, top + radius, radius, angle180, angle270);
                    context.stroke();
                    context.fill();
                }
            });
            break;
        }

        return drawItem;
    }

    /**
     * Get related FSMDState based on DrawItem
     * @param map Search target
     * @param value Search value
     * @return search result
     */
    protected Optional<FSMDState> getKey(Map<FSMDState, DrawItem> map, DrawItem value) {
        return map.entrySet().stream().filter(entry -> value.equals(entry.getValue())).map(Map.Entry::getKey).findFirst();
    }

    /**
     * Gets the DrawItem that exists at the coordinates specified by the argument.
     * @param editor Main class of behavior-model editor
     * @param x X coordinate
     * @param y Y coordinate
     * @return DrawItem and associated FSMDState
     */
    protected Map<FSMDState, DrawItem> getDrawItemIsBelowThePointer(FSMEditor editor, int x, int y) {
        return editor.getDrawItems().entrySet().stream().filter(map -> map.getValue().isPointInPath(x, y)).collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
    }

    /**
     * Get all the DrawItems currently selected on the canvas.
     * @param editor Main class of behavior-model editor
     * @return The selected DrawItem
     */
    protected Map<FSMDState, DrawItem> getSelectedDrawItems(FSMEditor editor) {
        return editor.getDrawItems().entrySet().stream().filter(map -> getSelectFlag(map.getValue())).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    }

    /**
     * Select DrawItem based on FSMDState and scroll to the coordinates of DrawItem.
     * @param editor Main class of behavior-model editor
     * @param state FSMDState to select/search
     */
    public void selectDrawItem(FSMEditor editor, FSMDState state) {
        Optional<Entry<FSMDState, DrawItem>> optEntry = editor.getDrawItems().entrySet().stream().filter(map -> map.getKey().getName().equals(state.getName())).findFirst();
        if (optEntry.isPresent()) {
            DrawItem drawItem = optEntry.get().getValue();
            selectDrawItem(optEntry.get().getValue(), true);
            editor.getEditorScroll().moveLayoutPosition(drawItem.getAttributeAsInt("left"), drawItem.getAttributeAsInt("top"));
        }
    }

    /**
     * Set the selection status of FSMDstate.
     * @param drawItem FSMDstate to select
     * @param selectFlag true: Select <br>
     *            false: Deselect
     */
    protected void selectDrawItem(DrawItem drawItem, boolean selectFlag) {
        if (drawItem instanceof DrawRect) {
            if (selectFlag) {
                drawItem.showKnobs(KnobType.RESIZE);
            } else {
                drawItem.hideAllKnobs();
            }
        } else if (drawItem instanceof DrawDiamond) {
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
     * Set the selection status of all FSMDstate.
     * @param editor Main class of behavior-model editor
     * @param selectFlag true: Select all<br>
     *            false: Deselect all
     */
    public void selectDrawItemAll(FSMEditor editor, boolean selectFlag) {
        editor.getDrawItems().entrySet().forEach(map -> {
            selectDrawItem(map.getValue(), selectFlag);
        });
    }

    /**
     * Get the selection status of the target state.
     * @param drawItem DrawItem to check the selection status
     * @return True if selected
     */
    protected boolean getSelectFlag(DrawItem drawItem) {
        if (drawItem.getKnobs() == null)
            return false;
        else
            return Arrays.asList(drawItem.getKnobs()).stream().filter(type -> type.equals(KnobType.RESIZE)).findFirst().isPresent() || "blue".equals(drawItem.getLineColor());
    }

    /**
     * Delete the selected state and the selected transition line.
     * @param editor Main class of behavior-model editor
     */
    protected void deleteItem(FSMEditor editor) {
        Map<FSMDState, DrawItem> drawItems = editor.stateManager.getSelectedDrawItems(editor);
        Map<FSMDTransition, EditorDrawLine> drawLines = editor.transManager.getSelectedDrawLines(editor);
        if (drawItems.size() == 0 && drawLines.size() == 0) {
            SC.warn("Please select node or line.");
            return;
        } else {
            editor.stateManager.onDelete(editor, drawItems, drawLines);
        }

    }
}
