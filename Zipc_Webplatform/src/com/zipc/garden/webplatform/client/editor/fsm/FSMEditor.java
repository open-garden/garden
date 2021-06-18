package com.zipc.garden.webplatform.client.editor.fsm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gwt.core.client.Scheduler;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawLabel;
import com.smartgwt.client.widgets.drawing.DrawLine;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.drawing.DrawRect;
import com.smartgwt.client.widgets.drawing.events.MouseOutEvent;
import com.smartgwt.client.widgets.drawing.events.MouseOutHandler;
import com.smartgwt.client.widgets.drawing.events.MouseOverEvent;
import com.smartgwt.client.widgets.drawing.events.MouseOverHandler;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DragStopEvent;
import com.smartgwt.client.widgets.events.DragStopHandler;
import com.smartgwt.client.widgets.events.ShowContextMenuEvent;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.LineType;
import com.zipc.garden.model.fsm.FSMDPseudoStateType;
import com.zipc.garden.model.fsm.FSMDRegion;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.model.fsm.FSMDTransition;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.EditorDrawLine;
import com.zipc.garden.webplatform.client.editor.EditorLineUtil;
import com.zipc.garden.webplatform.client.editor.EditorMemo;
import com.zipc.garden.webplatform.client.editor.EditorScroll;
import com.zipc.garden.webplatform.client.editor.EditorScroll.ItemSelection;

/**
 * Class for editors that set behavior-model.
 */
public class FSMEditor extends Editor {

    /** Class that manages the commands operated by FSMEditor */
    protected final EditManager manager = EditManager.createInstance();

    /** EMF root model of FSM editor. */
    private FSMDStateMachine machine;

    /** FSM file ID */
    private long fileId;

    /** Class that manages the processing related to the created DrawItem. */
    public final FSMStateManager stateManager = new FSMStateManager();

    /** Class that manages the processing related to the created DrawLine. */
    protected final FSMTransitionManager transManager = new FSMTransitionManager();

    /** Manages events for items and lines on the canvas */
    protected final FSMEditorEventHolder eventHolder = new FSMEditorEventHolder();

    /** Manage key input events on canvas */
    protected final FSMEditorKeyEventHolder keyEventHolder = new FSMEditorKeyEventHolder();

    /** Overall layout of FSMEditor */
    private final HLayout hLayout = new HLayout();

    /** Canvas layout */
    private final Layout layout = new Layout();

    /** editor canvas. */
    private final DrawPane drawPane = new DrawPane();

    /** A map that associates DrawItems existing in Canvas with EMF models. */
    private final Map<FSMDState, DrawItem> drawItems = new HashMap<>();

    /** A map that associates DrawLines existing in Canvas with EMF models. */
    private final Map<FSMDTransition, EditorDrawLine> editorDrawLines = new HashMap<>();

    /** True if the cursor is on the DrawItem */
    private boolean mouseMoveFlag = false;

    /** True if the cursor is on the DrawLine */
    private boolean mouseMoveFlag_line = false;

    /** Context menu for saving the FSM editor */
    protected MenuItem saveItem = new MenuItem("Save");

    /** Declaration of a class with canvas scroll functionality and object selection functionality */
    private EditorScroll scroll;

    /** Declare class to manage canvas memo */
    private EditorMemo memo;

    /**
     * constructor
     * @param machine {@link FSMEditor#machine}
     * @param fileId {@link FSMEditor#fileId}
     */
    public FSMEditor(FSMDStateMachine machine, long fileId) {
        this.machine = machine;
        this.fileId = fileId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditManager getEditManager() {
        return manager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractRootElement getRoot() {
        return machine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChanged() {
        return manager.getSavedPosition() != manager.getStackCoount();
    }

    /**
     * Get the model of the FSM editor.
     * @return {@link FSMEditor#machine}
     */
    public FSMDStateMachine getFSMDStateMachine() {
        return machine;
    }

    /**
     * Gets FSM file ID
     * @return {@link FSMEditor#fileId}
     */
    protected long getFileId() {
        return fileId;
    }

    /**
     * {@inheritDoc}
     */
    public Layout getLayout() {
        return hLayout;
    }

    /**
     * Gets the FSM Editor canvas.
     * @return {@link FSMEditor#drawPane}
     */
    protected DrawPane getDrawPane() {
        return drawPane;
    }

    /**
     * Gets the map that associates the DrawItem with the EMF model.
     * @return {@link FSMEditor#drawItems}
     */
    protected Map<FSMDState, DrawItem> getDrawItems() {
        return drawItems;
    }

    /**
     * Gets the map that associates the DrawLine with the EMF model.
     * @return {@link FSMEditor#editorDrawLines}
     */
    protected Map<FSMDTransition, EditorDrawLine> getEditorDrawLines() {
        return editorDrawLines;
    }

    /**
     * Get a class that has canvas scroll function and object selection function
     * @return {@link FSMEditor#scroll}
     */
    public EditorScroll getEditorScroll() {
        return scroll;
    }

    /**
     * Get the class that manages canvas memos
     * @return {@link FSMEditor#memo}
     */
    protected EditorMemo getEditorMemo() {
        return memo;
    }

    /**
     * The line drawing mode of Manhattan is confirmed.
     * @return if true, Manhattan line<br>
     *         if false, a straight line
     */
    public boolean isManhattanMode() {
        return machine.getManhattanMode() > -1;
    }

    /**
     * The type of line to draw is switched and the canvas is refreshed.
     */
    public void switchManhattanMode() {
        if (isManhattanMode()) {
            machine.setManhattanMode(-1);
        } else {
            machine.setManhattanMode(0);
        }
        refresh();
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
    public void setSavedPosition() {
        manager.setSavedPosition(manager.getStackCoount());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create() {
        stateManager.setFieldData(machine);
        transManager.setFieldData(machine);

        layout.setWidth100();
        layout.setHeight100();

        scroll = new EditorScroll(drawPane, layout, machine);
        scroll.addItemSelection(new ItemSelection() {
            @Override
            public void selectedRange(DrawRect selection) {
                drawItems.forEach((k, v) -> {
                    if (selection.isPointInPath(k.getLeft(), k.getTop()) && selection.isPointInPath(k.getLeft() + k.getWidth(), k.getTop() + k.getHeight())) {
                        FSMEditor.this.stateManager.selectDrawItem(v, true);
                    }
                });
                editorDrawLines.forEach((k, v) -> {
                    boolean isNotSelected = v.getDrawLines().stream()
                            .filter(l -> (!selection.isPointInPath(l.getStartLeft(), l.getStartTop()) || !selection.isPointInPath(l.getEndLeft(), l.getEndTop()))).findFirst()
                            .isPresent();
                    if (!isNotSelected) {
                        EditorLineUtil.selectDrawLine(v.getDrawLabel(), v.getDrawLines(), v.getDotLines(), true);
                    }
                });
            }
        });

        memo = new EditorMemo(manager, machine, drawPane);

        drawPane.setCanFocus(true);

        refresh();

        addLeftClickEvent();

        addRightClickEvent();

        addShortcutKeyEvent();

        hLayout.addMember(layout);

        Scheduler.get().scheduleDeferred(() -> {
            scroll.setPanelPosition(machine.getPositionX(), machine.getPositionY());
        });
    }

    /**
     * The FSM information saved in the model is reflected on the canvas.
     */
    protected void refresh() {
        FSMDRegion region = machine.getRegions().get(0);

        // Loop Model State
        region.getStates().forEach(state -> {
            if (!drawItems.containsKey(state)) {
                makeNewDrawItem(state);
            }
        });

        // Loop Canvas State
        List<FSMDState> delStates = new ArrayList<>();
        drawItems.forEach((state, drawItem) -> {
            Optional<FSMDState> optState = region.getStates().stream().filter(modelState -> state.equals(modelState)).findFirst();
            if (!optState.isPresent()) {
                delStates.add(state);
            } else {
                drawItem.setTitle(state.getName());
                drawItem.setAttribute("top", state.getTop(), true);
                drawItem.setAttribute("left", state.getLeft(), true);
                drawItem.setAttribute("width", state.getWidth(), true);
                drawItem.setAttribute("height", state.getHeight(), true);
                transManager.redrawLine(FSMEditor.this, drawItem, true);
            }
        });

        // Loop Model Line
        machine.getTransitions().stream().filter(x -> x != null).forEach(trans -> {
            if (!editorDrawLines.containsKey(trans)) {
                makeNewDrawLine(trans, trans.getSource().equals(trans.getTarget()));
            }
        });

        // Loop Canvas Line
        List<FSMDTransition> delTrans = new ArrayList<>();
        editorDrawLines.forEach((trans, drawLine) -> {
            Optional<FSMDTransition> optTrans = machine.getTransitions().stream().filter(modelTrans -> trans.equals(modelTrans)).findFirst();
            if (!optTrans.isPresent()) {
                delTrans.add(trans);
            }
        });

        // Canvas State Delete
        delStates.forEach(state -> {
            drawItems.get(state).erase();
            drawItems.remove(state);
        });

        // Canvas Line Delete
        delTrans.forEach(trans -> {
            editorDrawLines.get(trans).getDrawLines().forEach(drawLine -> drawLine.erase());
            editorDrawLines.get(trans).getDrawLinesTransparent().forEach(drawLine -> drawLine.erase());
            editorDrawLines.get(trans).getDrawLabel().erase();
            editorDrawLines.remove(trans);
        });

        memo.refresh();
    }

    /**
     * Create a DrawItem for the FSMDState.
     * @param state FSMDState with information of DrawItem to create
     */
    protected void makeNewDrawItem(FSMDState state) {
        DrawItem drawItem = stateManager.makeNewDrawItem(FSMEditor.this, state);
        drawItems.put(state, drawItem);

        addMouseEvent(drawItem);
        addDragResizeEvent(drawItem);
        addDragAndDropEvent(drawItem);
        addRightClickEvent(drawItem);
        addLeftClickEvent(drawItem);
    }

    /**
     * Create a DrawLine for the FSMDTransition.
     * @param transition FSMDTransition with information of DrawLine to create
     * @param selfTransitionFlag Transition line creation flag to own state
     */
    protected void makeNewDrawLine(FSMDTransition transition, boolean selfTransitionFlag) {
        EditorDrawLine editorDrawLine = new EditorDrawLine();
        DrawItem source = getDrawItems().get(transition.getSource());
        DrawItem target = getDrawItems().get(transition.getTarget());
        if (transition.getType() != null && transition.getType().equals(LineType.MANHATTAN)) {
            editorDrawLine = EditorLineUtil.makeDrawLine_Manhattan(transition, drawPane, source, target);
        } else {
            if (selfTransitionFlag) {
                editorDrawLine = EditorLineUtil.makeNewDrawLine_Self(drawPane, source, target);
                editorDrawLine.repositionSelfLine(editorDrawLine.getDrawLines(), transition.getSourceAnchorX(), transition.getSourceAnchorY(), transition.getTargetAnchorX(),
                        transition.getTargetAnchorY(), getFSMDStateMachine().getGridSize());
            } else {
                editorDrawLine = EditorLineUtil.makeNewDrawLine(drawPane, transition, source, target, FSMDPseudoStateType.CHOICE.equals(transition.getSource().getType()),
                        FSMDPseudoStateType.CHOICE.equals(transition.getTarget().getType()));
            }
        }
        transManager.makeNewDrawLabel(FSMEditor.this, transition, editorDrawLine);
        editorDrawLines.put(transition, editorDrawLine);
        EditorLineUtil.createDrawLinesTransparent(editorDrawLine, drawPane);

        DrawLabel drawLabel = editorDrawLine.getDrawLabel();
        List<DrawLine> listDrawLine = editorDrawLine.getDrawLines();
        List<DrawLine> listDrawLineTransparent = editorDrawLine.getDrawLinesTransparent();
        addMouseEvent(drawLabel, listDrawLine, listDrawLineTransparent);
        addDragResizeEvent(listDrawLine);
        addRightClickEvent(drawLabel, listDrawLine, editorDrawLine.getDotLines(), listDrawLineTransparent);
        addLeftClickEvent(drawLabel, listDrawLine, editorDrawLine.getDotLines(), listDrawLineTransparent);
        addDragStopEvent(transition, editorDrawLine);
    }

    /**
     * Create a process for the left click event of Canvas
     */
    private void addLeftClickEvent() {
        drawPane.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!event.isCtrlKeyDown() && !mouseMoveFlag && !mouseMoveFlag_line && !memo.getMouseMoveFlag()) {
                    stateManager.selectDrawItemAll(FSMEditor.this, false);
                    transManager.selectDrawLineAll(FSMEditor.this, false);
                    memo.selectMemoAll(false);
                }
            }
        });

        drawPane.addDragStopHandler(new DragStopHandler() {

            @Override
            public void onDragStop(DragStopEvent event) {
                List<DrawLine> drawLines = transManager.resizeDrawLine;
                if (drawLines.size() > 0) {
                    transManager.resizeDrawLine(FSMEditor.this, drawLines);
                    transManager.resizeDrawLine = new ArrayList<>();
                }
            }
        });
    }

    /**
     * Create resize event for DrawItem
     * @param drawItem Event creation target
     */
    protected void addDragResizeEvent(DrawItem drawItem) {
        drawItem.addDragResizeStartHandler(eventHolder.createDragResizeStartHandler(FSMEditor.this, drawItem));
        drawItem.addDragResizeMoveHandler(eventHolder.createDragResizeMoveHandler(FSMEditor.this, drawItem));
        drawItem.addDragResizeStopHandler(eventHolder.createDragResizeStopHandler(FSMEditor.this, drawItem));
    }

    /**
     * Create a drag and drop event for DrawItem.
     * @param drawItem Event creation target
     */
    protected void addDragAndDropEvent(DrawItem drawItem) {
        drawItem.addDragStartHandler(eventHolder.createDragStartHandler(FSMEditor.this, drawItem));
        drawItem.addDragMoveHandler(eventHolder.createDragMoveHandler(FSMEditor.this, drawItem));
        drawItem.addDragStopHandler(eventHolder.createDragStopHandler(FSMEditor.this));
    }

    /**
     * Creates a resize event for DrawLine.
     * @param drawLines Event creation target
     */
    protected void addDragResizeEvent(List<DrawLine> drawLines) {
        drawLines.forEach(drawLine -> {
            drawLine.addResizedHandler(eventHolder.createResizedHandler(FSMEditor.this, drawLines));
        });
    }

    /**
     * Create MouseOver and MouseOut events for DrawItem.
     * @param drawItem Event creation target
     */
    protected void addMouseEvent(DrawItem drawItem) {
        drawItem.addMouseOverHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                mouseMoveFlag = true;
            }
        });
        drawItem.addMouseOutHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                mouseMoveFlag = false;
            }
        });
    }

    /**
     * Create MouseOver and MouseOut events for DrawLine and DrawLabel.
     * @param drawLabel Event creation target. Information of the label set on the transition line.
     * @param drawLines Event creation target. Transition line.
     * @param drawLineTransparent Event creation target. Transparency lines associated with transition lines.
     */
    protected void addMouseEvent(DrawLabel drawLabel, List<DrawLine> drawLines, List<DrawLine> drawLineTransparent) {
        drawLabel.addMouseOverHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                mouseMoveFlag_line = true;
            }
        });
        drawLabel.addMouseOutHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                mouseMoveFlag_line = false;
            }
        });
        drawLines.forEach(drawLine -> {
            drawLine.addMouseOverHandler(new MouseOverHandler() {
                @Override
                public void onMouseOver(MouseOverEvent event) {
                    mouseMoveFlag_line = true;
                }
            });
            drawLine.addMouseOutHandler(new MouseOutHandler() {
                @Override
                public void onMouseOut(MouseOutEvent event) {
                    mouseMoveFlag_line = false;
                }
            });
        });
        drawLineTransparent.forEach(drawLine -> {
            drawLine.addMouseOverHandler(new MouseOverHandler() {
                @Override
                public void onMouseOver(MouseOverEvent event) {
                    mouseMoveFlag_line = true;
                }
            });
            drawLine.addMouseOutHandler(new MouseOutHandler() {
                @Override
                public void onMouseOut(MouseOutEvent event) {
                    mouseMoveFlag_line = false;
                }
            });
        });
    }

    /**
     * Create a canvas right-click event
     */
    private void addRightClickEvent() {
        drawPane.addShowContextMenuHandler(new ShowContextMenuHandler() {
            @Override
            public void onShowContextMenu(ShowContextMenuEvent event) {
                drawPane.setContextMenu(FSMMenuItemCreator.createCanvasRightClickMenuItem(FSMEditor.this, event.getX(), event.getY()));
                drawPane.focus();
            }
        });
    }

    /**
     * Create DrawItem right-click event handler
     * @param drawItem Event creation target.
     */
    protected void addRightClickEvent(DrawItem drawItem) {
        drawItem.addShowContextMenuHandler(new com.smartgwt.client.widgets.drawing.events.ShowContextMenuHandler() {
            @Override
            public void onShowContextMenu(com.smartgwt.client.widgets.drawing.events.ShowContextMenuEvent event) {
                if (!stateManager.getSelectFlag(drawItem)) {
                    drawItem.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
                    stateManager.selectDrawItem(drawItem, true);
                }

                drawItem.setContextMenu(FSMMenuItemCreator.createDrawItemRightClickMenuItem(FSMEditor.this, drawItem));
                drawPane.focus();
            }
        });
    }

    /**
     * Creating right-click event for DrawLabel and DrawLine
     * @param drawLabel Event creation target. Information of the label set on the transition line.
     * @param drawLines Event creation target. Transition line.
     * @param dotLines Manhattan resize line
     * @param drawLinesTransparent Event creation target. Transparency lines associated with transition lines.
     */
    protected void addRightClickEvent(DrawLabel drawLabel, List<DrawLine> drawLines, List<DrawLine> dotLines, List<DrawLine> drawLinesTransparent) {
        drawLines.forEach(drawLine -> {
            drawLine.addShowContextMenuHandler(new com.smartgwt.client.widgets.drawing.events.ShowContextMenuHandler() {
                @Override
                public void onShowContextMenu(com.smartgwt.client.widgets.drawing.events.ShowContextMenuEvent event) {
                    if (!EditorLineUtil.getSelectFlag(drawLine)) {
                        drawLine.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
                        EditorLineUtil.selectDrawLine(drawLabel, drawLines, dotLines, true);
                    }
                    drawLine.setContextMenu(FSMMenuItemCreator.createDrawLineRightClickMenuItem(FSMEditor.this, drawLine));
                    drawPane.focus();
                }
            });
        });
        drawLinesTransparent.forEach(drawLineT -> {
            drawLineT.addShowContextMenuHandler(new com.smartgwt.client.widgets.drawing.events.ShowContextMenuHandler() {
                @Override
                public void onShowContextMenu(com.smartgwt.client.widgets.drawing.events.ShowContextMenuEvent event) {
                    DrawLine drawLine = drawLines.get(0);
                    if (!EditorLineUtil.getSelectFlag(drawLine)) {
                        drawLine.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
                        EditorLineUtil.selectDrawLine(drawLabel, drawLines, dotLines, true);
                    }
                    drawLineT.setContextMenu(FSMMenuItemCreator.createDrawLineRightClickMenuItem(FSMEditor.this, drawLine));
                    drawPane.focus();
                }
            });
        });
        drawLabel.addShowContextMenuHandler(new com.smartgwt.client.widgets.drawing.events.ShowContextMenuHandler() {
            @Override
            public void onShowContextMenu(com.smartgwt.client.widgets.drawing.events.ShowContextMenuEvent event) {
                DrawLine drawLine = drawLines.get(0);
                if (!EditorLineUtil.getSelectFlag(drawLine)) {
                    drawLine.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
                    EditorLineUtil.selectDrawLine(drawLabel, drawLines, dotLines, true);
                }
                drawLabel.setContextMenu(FSMMenuItemCreator.createDrawLineRightClickMenuItem(FSMEditor.this, drawLine));
                drawPane.focus();
            }
        });
    }

    /**
     * Create DrawItem Left Click Event
     * @param drawItem Event creation target.
     */
    protected void addLeftClickEvent(DrawItem drawItem) {
        drawItem.addHandler(eventHolder.createLeftClickOnLabelHandler(this, drawItem), ClickEvent.getType());
        drawItem.addClickHandler(new com.smartgwt.client.widgets.drawing.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.drawing.events.ClickEvent event) {
                drawItem.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
            }
        });
    }

    /**
     * Creating DrawLabel and DrawLine Left Click Events
     * @param drawLabel Event creation target. Information of the label set on the transition line.
     * @param drawLines Event creation target. Transition line.
     * @param dotLines Manhattan resize line
     * @param drawLinesTransparent Event creation target. Transparency lines associated with transition lines.
     */
    protected void addLeftClickEvent(DrawLabel drawLabel, List<DrawLine> drawLines, List<DrawLine> dotLines, List<DrawLine> drawLinesTransparent) {
        drawLines.forEach(drawLine -> {
            drawLine.addHandler(eventHolder.createLeftClickOnDrawLineHandler(this, drawLabel, drawLines, dotLines), ClickEvent.getType());
            drawLine.addClickHandler(new com.smartgwt.client.widgets.drawing.events.ClickHandler() {
                @Override
                public void onClick(com.smartgwt.client.widgets.drawing.events.ClickEvent event) {
                    drawLine.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
                }
            });
        });
        drawLinesTransparent.forEach(drawLine -> {
            drawLine.addHandler(eventHolder.createLeftClickOnDrawLineHandler(this, drawLabel, drawLines, dotLines), ClickEvent.getType());
            drawLine.addClickHandler(new com.smartgwt.client.widgets.drawing.events.ClickHandler() {
                @Override
                public void onClick(com.smartgwt.client.widgets.drawing.events.ClickEvent event) {
                    drawLines.get(0).fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
                }
            });
        });
    }

    /**
     * Create DrawLabel drag stop event
     * @param transition FSM model transition line information
     * @param drawLine Drawing information of DrawLine and DrawLabel
     */
    protected void addDragStopEvent(FSMDTransition transition, EditorDrawLine drawLine) {
        drawLine.getDrawLabel().addDragStopHandler(eventHolder.createDragStopHandler(FSMEditor.this, transition, drawLine));
    }

    /**
     * Create a drag stop event for the resize line for the Manhattan line.
     * @param editor Main class of behavior-model editor
     * @param transition FSM model transition line information
     * @param newLine Manhattan line after resizing
     * @param ind Manhattan line index numbers
     */
    protected void addManhattanLineDragStopEvent(FSMEditor editor, FSMDTransition transition, DrawLine newLine, int ind) {
        newLine.addDragStopHandler(eventHolder.createManhattanLineDragStopHandler(editor, transition, newLine, ind));
    }

    /**
     * Create a shortcut key event for the canvas.
     */
    private void addShortcutKeyEvent() {
        drawPane.addKeyPressHandler(keyEventHolder.createShortcutKeyEvent(FSMEditor.this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSaveHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        saveItem.addClickHandler(handler);
    }

}
