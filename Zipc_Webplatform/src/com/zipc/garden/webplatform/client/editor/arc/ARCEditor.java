package com.zipc.garden.webplatform.client.editor.arc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
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
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.tab.Tab;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.model.arc.ARCLine;
import com.zipc.garden.model.arc.ARCRoot;
import com.zipc.garden.model.arc.ARCState;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.LineType;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.EditorDrawLine;
import com.zipc.garden.webplatform.client.editor.EditorLineUtil;
import com.zipc.garden.webplatform.client.editor.EditorMemo;
import com.zipc.garden.webplatform.client.editor.EditorOpener;
import com.zipc.garden.webplatform.client.editor.EditorScroll;
import com.zipc.garden.webplatform.client.editor.EditorScroll.ItemSelection;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * It is an editor that creates the architecture model
 */
public class ARCEditor extends Editor implements EditorOpener {

    /** Manages the operation commands of the architecture editor. */
    protected final EditManager manager = EditManager.createInstance();

    /** Root model of ARC editor. */
    private ARCRoot root;

    /** The project ID in which this ARC is managed. */
    private long projectId;

    /** File ID of selected Item */
    private long openFileId = -1L;

    /** Asynchronous interface to get and save architectural information */
    private EditResourceServiceAsync service;

    /** Class that manages items drawn on the canvas */
    protected final ARCStateManager stateManager = new ARCStateManager(this);

    /** Class that manages lines drawn on the canvas */
    protected final ARCLineManager lineManager = new ARCLineManager(this);

    /** Manages events for items and lines on the canvas */
    protected final ARCEditorEventHolder eventHolder = new ARCEditorEventHolder();

    /** Manage key input events on canvas */
    protected final ARCEditorKeyEventHolder keyEventHolder = new ARCEditorKeyEventHolder();

    /** Canvas layout */
    private final Layout layout = new Layout();

    /** Architecture editor canvas. */
    private final DrawPane drawPane = new DrawPane();

    /** Map that associates the drawn item with the EMF model state class */
    private final Map<ARCState, DrawItem> drawItems = new HashMap<>();

    /** Map that associates the drawn line with the EMF model line class */
    private final Map<ARCLine, EditorDrawLine> editorDrawLines = new HashMap<>();

    /** Used to set points on the Manhattan line */
    private final int snapGap = 20;

    /** True if the mouse cursor is over the Item */
    private boolean mouseMoveFlag = false;

    /** True if the mouse cursor is over the line */
    private boolean mouseMoveFlag_line = false;

    /** Context menu for saving the ARC editor */
    private MenuItem saveItem = new MenuItem("Save");

    /** Menu to create a new figure related to FSM file */
    private MenuItem newStateItem = new MenuItem("New State Machine");

    /** Menu for opening the FSM file associated with the selected Item */
    private MenuItem openFileItem = new MenuItem("Open File");

    /** The coordinates of the newly drawn Item */
    private int addStateTop = 0, addStateLeft = 0;

    /** Declaration of a class with canvas scroll functionality and object selection functionality */
    private EditorScroll scroll;

    /** Declare class to manage canvas memo */
    private EditorMemo memo;

    /**
     * constructor
     * @param root Root model of ARC editor
     * @param projectId The project ID in which this ARC is managed
     * @param service Asynchronous interface to get and save architectural information
     */
    public ARCEditor(ARCRoot root, long projectId, EditResourceServiceAsync service) {
        this.root = root;
        this.projectId = projectId;
        this.service = service;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(ModelingProjectView modelingProjectView, Editor editor, Tab tab, PostProcessHandler handler) {
        Set<String> fsmFileIds = new HashSet<String>();
        ARCRoot arcRoot = ARCEditor.this.root;
        arcRoot.getStates().forEach(state -> {
            fsmFileIds.add(state.getFileId());
        });
        arcRoot.getRefs().clear();
        fsmFileIds.forEach(fileId -> {
            Reference ref = COREFactory.eINSTANCE.createReference();
            ref.setRefid(fileId);
            arcRoot.getRefs().add(ref);
        });
        super.save(modelingProjectView, editor, tab, handler);
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
        return root;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChanged() {
        return manager.getSavedPosition() != manager.getStackCoount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSavedPosition() {
        manager.setSavedPosition(manager.getStackCoount());
    }

    /**
     * Get ARC root model
     * @return ARC Root Model
     */
    public ARCRoot getARCRoot() {
        return root;
    }

    /**
     * Get project id
     * @return project id
     */
    protected long getProjectId() {
        return projectId;
    }

    /**
     * get asynchronous interface
     * @return asynchronous interface
     */
    protected EditResourceServiceAsync getService() {
        return service;
    }

    /**
     * Gets the class that manages the item
     * @return class that manages the item
     */
    public ARCStateManager getARCStateManager() {
        return stateManager;
    }

    /**
     * get canvas layout
     * @return canvas layout
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * get Architecture editor canvas.
     * @return canvas
     */
    protected DrawPane getDrawPane() {
        return drawPane;
    }

    /**
     * Gets a map that associates the drawn item with the EMF model state class
     * @return map
     */
    protected Map<ARCState, DrawItem> getDrawItems() {
        return drawItems;
    }

    /**
     * Gets a map that associates the drawn line with the EMF model line class
     * @return map
     */
    protected Map<ARCLine, EditorDrawLine> getEditorDrawLines() {
        return editorDrawLines;
    }

    /**
     * Used at the beginning and end of the Manhattan line
     * @return 20
     */
    protected int getSnapGap() {
        return snapGap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuItem getSaveItem() {
        return saveItem;
    }

    /**
     * Get a menu to create a new FSM file and state.
     * @return menu
     */
    public MenuItem getNewStateItem() {
        return newStateItem;
    }

    /**
     * Get the FSM file ID of the selected state.
     * @return file id
     */
    public MenuItem getOpenFileItem() {
        return openFileItem;
    }

    /**
     * Get the Y coordinate of the newly drawn item
     * @return Y coordinate
     */
    public int getStateTop() {
        return addStateTop;
    }

    /**
     * Get the X coordinate of the newly drawn item
     * @return X coordinate
     */
    public int getStateLeft() {
        return addStateLeft;
    }

    /**
     * Get a class that has canvas scroll function and object selection function.
     * @return EditorScroll class
     */
    public EditorScroll getEditorScroll() {
        return scroll;
    }

    /**
     * Get the class that manages the notes created on the canvas.
     * @return EditorMemo class
     */
    protected EditorMemo getEditorMemo() {
        return memo;
    }

    /**
     * Returns True if the line being drawn is Manhattan.
     * @return Manhattan drawing flag
     */
    public boolean isManhattanMode() {
        return root.getLineMode() > -1;
    }

    /**
     * If a straight line is drawn, change it to the Manhattan line.<br>
     * If a Manhattan line is drawn, change it to a straight line.
     */
    public void switchManhattanMode() {
        if (isManhattanMode()) {
            root.setLineMode(-1);
        } else {
            root.setLineMode(0);
        }
        refresh();
    }

    /**
     * Gets the maximum priority value.
     * @return Maximum priority value
     */
    public int getMaxEvalPriority() {
        return drawItems.entrySet().stream().max((l, r) -> l.getKey().getEvalPriority() - r.getKey().getEvalPriority()).map(m -> m.getKey().getEvalPriority()).orElse(0) + 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getOpenFileId() {
        Map<ARCState, DrawItem> drawItems = this.getARCStateManager().getSelectedDrawItems();
        if (drawItems.size() != 1) {
            return -1L;
        }
        return openFileId;
    }

    /**
     * {@inheritDoc}
     */
    public void create() {

        layout.setWidth100();
        layout.setHeight100();

        scroll = new EditorScroll(drawPane, layout, root);
        scroll.addItemSelection(new ItemSelection() {
            @Override
            public void selectedRange(DrawRect selection) {
                drawItems.forEach((k, v) -> {
                    if (selection.isPointInPath(k.getLeft(), k.getTop()) && selection.isPointInPath(k.getLeft() + k.getWidth(), k.getTop() + k.getHeight())) {
                        ARCEditor.this.stateManager.selectDrawItem(v, true);
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

        memo = new EditorMemo(manager, root, drawPane);

        drawPane.setCanFocus(true);

        refresh();

        addLeftClickEvent();

        addRightClickEvent();

        addShortcutKeyEvent();

        Scheduler.get().scheduleDeferred(() -> {
            scroll.setPanelPosition(root.getPositionX(), root.getPositionY());
        });
    }

    /**
     * ARC information saved in the model is reflected on the canvas.
     */
    public void refresh() {

        // Loop Model State
        getARCRoot().getStates().forEach(state -> {
            if (!drawItems.containsKey(state)) {
                makeNewDrawItem(state);
            }
        });

        // Loop Canvas State
        List<ARCState> delStates = new ArrayList<>();
        drawItems.forEach((state, drawItem) -> {
            Optional<ARCState> optState = getARCRoot().getStates().stream().filter(modelState -> state.equals(modelState)).findFirst();
            if (!optState.isPresent()) {
                delStates.add(state);
            } else {
                drawItem.setAttribute("top", state.getTop(), true);
                drawItem.setAttribute("left", state.getLeft(), true);
                drawItem.setAttribute("width", state.getWidth(), true);
                drawItem.setAttribute("height", state.getHeight(), true);
                lineManager.redrawLine(drawItem, true);
            }
        });

        // Loop Model Line
        getARCRoot().getLines().stream().filter(x -> x != null).forEach(trans -> {
            if (!editorDrawLines.containsKey(trans)) {
                makeNewDrawLine(trans, trans.getSource().equals(trans.getTarget()));
            }
        });

        // Loop Canvas Line
        List<ARCLine> delLines = new ArrayList<>();
        editorDrawLines.forEach((line, drawLine) -> {
            Optional<ARCLine> optTrans = getARCRoot().getLines().stream().filter(modelLine -> line.equals(modelLine)).findFirst();
            if (!optTrans.isPresent()) {
                delLines.add(line);
            }
        });

        // Canvas State Delete
        delStates.forEach(state -> {
            drawItems.get(state).erase();
            drawItems.remove(state);
        });

        // Canvas Line Delete
        delLines.forEach(line -> {
            editorDrawLines.get(line).getDrawLines().forEach(drawLine -> drawLine.erase());
            editorDrawLines.get(line).getDrawLinesTransparent().forEach(drawLine -> drawLine.erase());
            editorDrawLines.get(line).getDrawLabel().erase();
            editorDrawLines.remove(line);
        });

        stateManager.reacquireStateMachine();

        memo.refresh();
    }

    /**
     * The contents of ARCState of the EMF model are drawn on the canvas.<br>
     * Also, an event handler for the drawn Item is created.
     * @param state The contents of this ARCState will be reflected on the canvas
     */
    protected void makeNewDrawItem(ARCState state) {
        DrawItem drawItem = stateManager.makeNewDrawItem(state);
        drawItems.put(state, drawItem);

        addMouseEvent(drawItem);
        addDragResizeEvent(drawItem);
        addDragAndDropEvent(drawItem);
        addRightClickEvent(drawItem);
        addLeftClickEvent(drawItem);
    }

    /**
     * The contents of the ARCLine of the EMF model are drawn on the canvas. <br>
     * It also creates an event handler for the drawn line.
     * @param line The contents of this ARCLine will be reflected on the canvas
     * @param selfLineFlag True if self transition
     */
    protected void makeNewDrawLine(ARCLine line, boolean selfLineFlag) {
        EditorDrawLine editorDrawLine = new EditorDrawLine();
        DrawItem source = getDrawItems().get(line.getSource());
        DrawItem target = getDrawItems().get(line.getTarget());
        if (line.getType() != null && line.getType().equals(LineType.MANHATTAN)) {
            editorDrawLine = EditorLineUtil.makeDrawLine_Manhattan(line, drawPane, source, target);
        } else {
            if (selfLineFlag) {
                editorDrawLine = EditorLineUtil.makeNewDrawLine_Self(drawPane, source, target);
                editorDrawLine.repositionSelfLine(editorDrawLine.getDrawLines(), line.getSourceAnchorX(), line.getSourceAnchorY(), line.getTargetAnchorX(), line.getTargetAnchorY(),
                        getSnapGap());
            } else {
                editorDrawLine = EditorLineUtil.makeNewDrawLine(drawPane, line, source, target, false, false);
            }
        }
        lineManager.makeNewDrawLabel(line, editorDrawLine);
        editorDrawLines.put(line, editorDrawLine);
        EditorLineUtil.createDrawLinesTransparent(editorDrawLine, drawPane);

        DrawLabel drawLabel = editorDrawLine.getDrawLabel();
        List<DrawLine> listDrawLine = editorDrawLine.getDrawLines();
        List<DrawLine> listDrawLineTransparent = editorDrawLine.getDrawLinesTransparent();
        addMouseEvent(drawLabel, listDrawLine, listDrawLineTransparent);
        addDragResizeEvent(listDrawLine);
        addRightClickEvent(drawLabel, listDrawLine, editorDrawLine.getDotLines(), listDrawLineTransparent);
        addLeftClickEvent(drawLabel, listDrawLine, editorDrawLine.getDotLines(), listDrawLineTransparent);
        addDragStopEvent(line, editorDrawLine);
    }

    /**
     * Create an event handler that fires when you click the left mouse button on the canvas.
     */
    private void addLeftClickEvent() {
        drawPane.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!event.isCtrlKeyDown() && !mouseMoveFlag && !mouseMoveFlag_line && !memo.getMouseMoveFlag()) {
                    stateManager.selectDrawItemAll(false);
                    lineManager.selectDrawLineAll(false);
                    memo.selectMemoAll(false);
                }
            }
        });

        drawPane.addDragStopHandler(new DragStopHandler() {

            @Override
            public void onDragStop(DragStopEvent event) {
                List<DrawLine> drawLines = lineManager.resizeDrawLine;
                if (drawLines.size() > 0) {
                    lineManager.resizeDrawLine(drawLines);
                    lineManager.resizeDrawLine = new ArrayList<>();
                }
            }
        });
    }

    /**
     * Create an event handler that fires when the DrawItem to draw is resized.
     * @param drawItem The DrawItem that creates the event
     */
    protected void addDragResizeEvent(DrawItem drawItem) {
        drawItem.addDragResizeStartHandler(eventHolder.createDragResizeStartHandler(ARCEditor.this, drawItem));
        drawItem.addDragResizeMoveHandler(eventHolder.createDragResizeMoveHandler(ARCEditor.this, drawItem));
        drawItem.addDragResizeStopHandler(eventHolder.createDragResizeStopHandler(ARCEditor.this, drawItem));
    }

    /**
     * Create an event handler that fires when the DrawItem to draw is dragged and dropped.
     * @param drawItem The DrawItem that creates the event
     */
    protected void addDragAndDropEvent(DrawItem drawItem) {
        drawItem.addDragStartHandler(eventHolder.createDragStartHandler(ARCEditor.this, drawItem));
        drawItem.addDragMoveHandler(eventHolder.createDragMoveHandler(ARCEditor.this, drawItem));
        drawItem.addDragStopHandler(eventHolder.createDragStopHandler(ARCEditor.this));
    }

    /**
     * Create an event handler that fires when the DrawLine to draw is reallocated.
     * @param drawLines The DrawLine that creates the event
     */
    protected void addDragResizeEvent(List<DrawLine> drawLines) {
        drawLines.forEach(drawLine -> {
            drawLine.addResizedHandler(eventHolder.createResizedHandler(ARCEditor.this, drawLines));
        });
    }

    /**
     * Create an event that fires when the mouse is over the DrawItem to draw.
     * @param drawItem The DrawItem that creates the event
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
     * Create an event that fires when the mouse is over the DrawLine to draw.
     * @param drawLabel The DrawLabel that creates the event
     * @param drawLines The DrawLine that creates the event
     * @param drawLineTransparent The DrawLine that creates an event (transparent line)
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
     * Create a right-click context menu for the canvas.
     */
    private void addRightClickEvent() {
        drawPane.addShowContextMenuHandler(new ShowContextMenuHandler() {
            @Override
            public void onShowContextMenu(ShowContextMenuEvent event) {
                int clickX = event.getX() - getDrawPane().getPageLeft();
                int clickY = event.getY() - getDrawPane().getPageTop();
                addStateLeft = Math.round(clickX / getSnapGap()) * getSnapGap();
                addStateTop = Math.round(clickY / getSnapGap()) * getSnapGap();
                drawPane.setContextMenu(ARCMenuItemCreator.createCanvasRightClickMenuItem(ARCEditor.this, addStateLeft, addStateTop));
                drawPane.focus();
            }
        });
    }

    /**
     * Create a right-click context menu for the DrawItem
     * @param drawItem The DrawItem that creates the event
     */
    protected void addRightClickEvent(DrawItem drawItem) {
        drawItem.addShowContextMenuHandler(new com.smartgwt.client.widgets.drawing.events.ShowContextMenuHandler() {
            @Override
            public void onShowContextMenu(com.smartgwt.client.widgets.drawing.events.ShowContextMenuEvent event) {
                if (!stateManager.getSelectFlag(drawItem)) {
                    drawItem.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
                    stateManager.selectDrawItem(drawItem, true);
                }

                drawItem.setContextMenu(ARCMenuItemCreator.createDrawItemRightClickMenuItem(ARCEditor.this, drawItem));
                drawPane.focus();

                String uuid = stateManager.getKey(getDrawItems(), drawItem).get().getFileId();
                if (null != uuid && !uuid.isEmpty()) {

                    service.getVMFile(uuid, projectId, new AsyncCallback<VMFile>() {
                        @Override
                        public void onSuccess(VMFile result) {
                            if (result == null) {
                                openFileId = -3L;
                                return;
                            }
                            openFileId = result.getId();
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }
                    });
                } else {
                    openFileId = -3L;
                }
            }
        });
    }

    /**
     * Create a right-click context menu for the DrawLine
     * @param drawLabel The DrawLabel that creates the event
     * @param drawLines The DrawLine that creates the event
     * @param dotLines The DrawLine that creates an event (Manhattan resize line)
     * @param drawLinesTransparent The DrawLine that creates an event (transparent line)
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
                    drawLine.setContextMenu(ARCMenuItemCreator.createDrawLineRightClickMenuItem(ARCEditor.this, drawLine));
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
                    drawLineT.setContextMenu(ARCMenuItemCreator.createDrawLineRightClickMenuItem(ARCEditor.this, drawLine));
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
                drawLabel.setContextMenu(ARCMenuItemCreator.createDrawLineRightClickMenuItem(ARCEditor.this, drawLine));
                drawPane.focus();
            }
        });
    }

    /**
     * Creates an event that is raised when the left mouse button is pressed over a DrawItem.
     * @param drawItem The DrawItem that creates the event
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
     * Creates an event that is raised when the left mouse button is pressed over a DrawLine.
     * @param drawLabel The DrawLabel that creates the event
     * @param drawLines The DrawLine that creates the event
     * @param dotLines The DrawLine that creates an event (Manhattan resize line)
     * @param drawLinesTransparent The DrawLine that creates an event (transparent line)
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
     * Create an event that occurs when a DrawLabel related to DrawLine is dragged and dropped.
     * @param line EMF model associated with the line being drawn
     * @param drawLine A class that manages a transition line that correlates elements.
     */
    protected void addDragStopEvent(ARCLine line, EditorDrawLine drawLine) {
        drawLine.getDrawLabel().addDragStopHandler(eventHolder.createDragStopHandler(ARCEditor.this, line, drawLine));
    }

    /**
     * Create an event that fires when you drag and drop the DrawLine of Manhattan line.
     * @param editor This class
     * @param line EMF model associated with the Manhattan line
     * @param newLine Resized Manhattan line
     * @param ind Manhattan line index numbers
     */
    public void addManhattanLineDragStopEvent(ARCEditor editor, ARCLine line, DrawLine newLine, int ind) {
        newLine.addDragStopHandler(eventHolder.createManhattanLineDragStopHandler(editor, line, newLine, ind));
    }

    /**
     * Creates an event that occurs when the keyboard is typed. <br>
     * Fired when the canvas has focus.
     */
    private void addShortcutKeyEvent() {
        drawPane.addKeyPressHandler(keyEventHolder.createShortcutKeyEvent(ARCEditor.this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSaveHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        saveItem.addClickHandler(handler);
    }

    /**
     * Create an event that fires when a "New State Machine" is selected from the right-click menu on the canvas.
     * {@link ModelingProjectView#clickNewStateMachine(ARCEditor, VMFile)}
     * @param handler "New State Machine" menu click handler
     */
    public void addNewStateMachineHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        newStateItem.addClickHandler(handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addOpenFileHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        openFileItem.addClickHandler(handler);
    }
}
