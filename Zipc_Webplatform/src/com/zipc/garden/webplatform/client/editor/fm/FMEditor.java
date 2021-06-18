package com.zipc.garden.webplatform.client.editor.fm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.KnobType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawLabel;
import com.smartgwt.client.widgets.drawing.DrawLine;
import com.smartgwt.client.widgets.drawing.DrawOval;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.drawing.DrawRect;
import com.smartgwt.client.widgets.drawing.DrawTriangle;
import com.smartgwt.client.widgets.drawing.Point;
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
import com.smartgwt.client.widgets.tab.Tab;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.AbstractStyle;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMProperty;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.EditorMemo;
import com.zipc.garden.webplatform.client.editor.EditorOpener;
import com.zipc.garden.webplatform.client.editor.EditorScroll;
import com.zipc.garden.webplatform.client.editor.EditorScroll.ItemSelection;
import com.zipc.garden.webplatform.client.editor.NodeArrangeInterface;
import com.zipc.garden.webplatform.client.editor.RefreshEndListener;
import com.zipc.garden.webplatform.client.editor.fm.layout.LayoutMode;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * Class for editors that set feature models.
 */
public class FMEditor extends Editor implements EditorOpener, NodeArrangeInterface {

    /** Class that manages the commands operated by FMEditor */
    private final EditManager manager = EditManager.createInstance();

    /** Class that manages the processing related to DrawRect created by the feature-model editor. */
    public final FMNodeManager nodeManager = new FMNodeManager();

    /** Class that manages the processing related to DrawLine created by the feature model editor. */
    protected final FMLineManager lineManager = new FMLineManager();

    /** Manages events for items and lines on the canvas */
    protected final FMEditorEventHolder eventHolder = new FMEditorEventHolder();

    /** Manage key input events on canvas */
    protected final FMEditorKeyEventHolder keyEventHolder = new FMEditorKeyEventHolder();

    /** Overall layout of FMEditor */
    final HLayout hLayout = new HLayout();

    /** Canvas layout */
    final Layout layout = new Layout();

    /** feature model editor canvas. */
    final DrawPane drawPane = new DrawPane();

    /** Root model of FM editor. */
    FMRoot root;

    /** The project ID in which this FM is managed. */
    private long projectId;

    /** FM file ID */
    private Long fileId;

    /** File ID of selected drawrect */
    private long openFileId = -1L;

    /** Asynchronous interface to get and save feature model information */
    private EditResourceServiceAsync service;

    /** Declaration of a class with canvas scroll functionality and object selection functionality */
    private EditorScroll scroll;

    /** Declare class to manage canvas memo */
    private EditorMemo memo;

    /** Class which manages the method issued at the end of the editor repaint */
    private RefreshEndListener listener;

    /**
     * <pre>
     * Whether properties can be displayed.
     * If true, the property value is displayed in drawRect.
     * If false, a red triangle will appear in the upper right of drawRect if the property value is set.
     * </pre>
     */
    private boolean isPropertyDisplay = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRefreshEndListener(RefreshEndListener listener) {
        this.listener = listener;
    }

    /**
     * <pre>
     * A Map that associates the DrawRect existing in the Canvas with the EMF model.
     * ・Integer -> HashCode for DrawRect
     * ・FMDrawNode -> Single FMNode and drawing information
     * ※ DrawLine does not exist in RootNode
     * </pre>
     */
    Map<Integer, FMDrawNode> drawItems = new HashMap<>();

    /** True if the cursor is on the DrawRect */
    private boolean mouseMoveFlag = false;

    /** True if the cursor is on the DrawLine */
    private boolean mouseMoveFlag_line = false;

    /** Context menu for saving the FM editor */
    public MenuItem saveItem = new MenuItem("Save");

    /** Context menu for opening ReferenceNode files */
    MenuItem referenceOpenItem = new MenuItem("Open");

    /** Automatic layout control */
    private FMNodeAutoLayout autoLayout;

    /** Context menu for creating a new FMModel associated with an end node */
    MenuItem childModelItem = new MenuItem("Create child model");

    /**
     * constructor
     * @param root Root model of FM editor
     * @param projectId The project ID in which this FM is managed
     * @param fileId FM file ID
     * @param service Asynchronous interface to get and save feature model information
     */
    public FMEditor(FMRoot root, Long projectId, Long fileId, EditResourceServiceAsync service) {
        this.root = root;
        this.projectId = projectId;
        this.fileId = fileId;
        this.service = service;
        this.autoLayout = new FMNodeAutoLayout();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(ModelingProjectView modelingProjectView, Editor editor, Tab tab, PostProcessHandler handler) {
        Set<String> fmFileIds = new HashSet<String>();
        FMRoot fmRoot = FMEditor.this.root;
        List<FMNode[]> nodes = new ArrayList<>();
        FMEditor.this.nodeManager.getPairList(fmRoot.getNode(), nodes);
        nodes.forEach(node -> {
            if (node[1].getRefuuid() != null && node[1].getRefuuid().length() > 0) {
                fmFileIds.add(node[1].getRefuuid());
            }
        });
        fmRoot.getRefs().clear();
        fmFileIds.forEach(fileId -> {
            Reference ref = COREFactory.eINSTANCE.createReference();
            ref.setRefid(fileId);
            fmRoot.getRefs().add(ref);
        });
        super.save(modelingProjectView, editor, tab, handler);
    }

    /**
     * Get the root model of the FM editor.
     * @return root model
     */
    public FMRoot getFMRoot() {
        return root;
    }

    /**
     * Gets the project ID in which this FM editor is managed.
     * @return project Id
     */
    protected long getProjectId() {
        return projectId;
    }

    /**
     * Gets FM file ID
     * @return FM file id
     */
    protected long getFileId() {
        return fileId;
    }

    /**
     * Gets Asynchronous interface to get and save feature model information
     * @return Asynchronous interface
     */
    protected EditResourceServiceAsync getService() {
        return service;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Layout getLayout() {
        return hLayout;
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
     * Get a class that has canvas scroll function and object selection function
     * @return
     */
    public EditorScroll getEditorScroll() {
        return scroll;
    }

    /**
     * Get the class that manages canvas memos
     * @return Class that manages memo created on the canvas
     */
    protected EditorMemo getEditorMemo() {
        return memo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getOpenFileId() {
        List<DrawRect> drawRects = nodeManager.getSelectDrawRect();
        if (drawRects.size() > 1) {
            return -1L;
        }
        FMNode node = this.getDrawRectMap().get(drawRects.get(0).hashCode()).getFmNode();
        if (node.getChildren().size() > 0) {
            return -2L;
        }

        return openFileId;
    }

    /**
     * Set the file ID of the selected drawrect
     * @param openFileId the file ID of the selected drawrect
     */
    public void setOpenFileId(long openFileId) {
        this.openFileId = openFileId;
    }

    /**
     * Get the map with the relationship between DrawRect and FMNode
     * @return the map with the relationship between DrawRect and FMNode
     */
    public Map<Integer, FMDrawNode> getDrawRectMap() {
        return drawItems;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create() {
        // FMEditorのフィールド変数を設定する
        nodeManager.setFieldData(root, drawItems);

        layout.setWidth100();
        layout.setHeight100();
        scroll = new EditorScroll(drawPane, layout, root);
        scroll.addItemSelection(new ItemSelection() {
            @Override
            public void selectedRange(DrawRect selection) {
                drawItems.values().forEach(v -> {
                    if (selection.isPointInPath(v.getFmNode().getLeft(), v.getFmNode().getTop())
                            && selection.isPointInPath(v.getFmNode().getLeft() + v.getFmNode().getWidth(), v.getFmNode().getTop() + v.getFmNode().getHeight())) {
                        FMEditor.this.nodeManager.setSelectDrawRect(v.getFmNode());
                    }
                });
                // TODO:線の付け替えがマージされたら線の選択も実装
            }
        });
        memo = new EditorMemo(manager, root, drawPane);
        drawPane.setCanFocus(true);

        refresh();

        if (root.getNode() != null) {
            FMNode parentNode = root.getNode();
            List<FMNode[]> pairList = new ArrayList<FMNode[]>();
            nodeManager.getPairList(parentNode, pairList);
            for (FMNode[] pair : pairList) {
                if (pair == null) {
                    break;
                }
            }
        }
        // Leftクリックイベント作成（キャンバス上）
        addLeftClickEvent();

        // Rightクリックイベント作成（キャンバス上）
        addRightClickEvent();

        // 円弧を描画
        addDrawArcEvent();

        // ショートカットキーイベント
        addShortcutKeyEvent();

        hLayout.addMember(layout);

        Scheduler.get().scheduleDeferred(() -> {
            scroll.setPanelPosition(root.getPositionX(), root.getPositionY());
        });
    }

    /**
     * The FM information saved in the model is reflected on the canvas.
     */
    protected void refresh() {
        // rootのlabel退避用
        List<Integer> labels = new ArrayList<>();
        List<FMNode[]> pairList = new ArrayList<FMNode[]>();

        if (root.getNode() != null) {
            FMNode parentNode = root.getNode();
            nodeManager.getPairList(parentNode, pairList);
        }
        // Model(現在)でループ処理
        for (FMNode[] pair : pairList) {
            if (pair == null)
                break;
            // labelが存在しない場合
            if (nodeManager.getDrawRect(pair[0]) == null) {
                makeNewLabel(pair[0]);
            }
            if (nodeManager.getDrawRect(pair[1]) == null) {
                makeNewLabel(pair[1]);
            }
            // rootのlabelを退避
            DrawRect drawRect1 = nodeManager.getDrawRect(pair[0]);
            DrawRect drawRect2 = nodeManager.getDrawRect(pair[1]);
            if (!labels.contains(drawRect1.hashCode())) {
                labels.add(drawRect1.hashCode());
            }
            if (!labels.contains(drawRect2.hashCode())) {
                labels.add(drawRect2.hashCode());
            }

        }

        adjustLayout();

        // DrawRect(現在)でループ処理
        List<FMDrawNode> delLabelList = new ArrayList<>();
        for (Map.Entry<Integer, FMDrawNode> entry : drawItems.entrySet()) {
            FMDrawNode drawNode = entry.getValue();
            FMNode fmNode = drawNode.getFmNode();
            DrawRect drawRect = drawNode.getDrawRect();
            DrawLabel cardinality = drawNode.getCardinality();
            DrawTriangle triangle = drawNode.getDrawTriangle();
            // Modelに対応するDrawRectが存在しない場合
            if (!labels.contains(entry.getKey())) {
                // DrawRect(削除対象)を退避
                delLabelList.add(drawNode);
                continue;
            } else {
                String title = "";
                if (fmNode.getRefInfo() != null) {
                    title = fmNode.getName() + ":" + fmNode.getRefName() + "\n(" + fmNode.getRefInfo() + ")";
                } else {
                    title = fmNode.getName();
                }

                // Propertyの表示設定
                if (!fmNode.getProperties().isEmpty()) {
                    StringBuilder propSb = new StringBuilder();
                    for (FMProperty fmProperty : fmNode.getProperties()) {
                        if (!"".equals(fmProperty.getKey())) {
                            propSb.append(fmProperty.getKey() + " : " + fmProperty.getValue());
                            propSb.append(isPropertyDisplay ? "\n" : "<br>");
                        }
                    }
                    if (isPropertyDisplay) {
                        title += "\n" + propSb.toString();
                        drawRect.setShowHover(false);
                    } else {
                        drawRect.setShowHover(true);
                        drawRect.setPrompt(propSb.toString());
                    }

                }

                drawRect.setTitle(title);
                drawRect.setTop(fmNode.getTop());
                drawRect.setLeft(fmNode.getLeft());
                drawRect.setHeight(fmNode.getHeight());
                drawRect.setWidth(fmNode.getWidth());
                if (fmNode.getChildType().equals(ChildType.XOR) && fmNode.getMin() != -1) {
                    if (cardinality == null) {
                        makeNewCardinality(drawNode);
                        cardinality = drawNode.getCardinality();
                    }

                    int[] defaultPosition = getCurrentLayoutMode().getDefaultCardinalityPosition(fmNode);
                    cardinality.moveTo(defaultPosition[0] + fmNode.getX(), defaultPosition[1] + fmNode.getY());
                    String max = Integer.toString(fmNode.getMax());
                    if (fmNode.getMax() == -1) {
                        max = "*";
                    }
                    cardinality.setContents(fmNode.getMin() + ".." + max);
                } else {
                    if (cardinality != null) {
                        cardinality.erase();
                        cardinality = null;
                        drawNode.setCardinality(cardinality);
                    }
                }
                dragMoveOval(entry.getValue());

                if (!fmNode.getProperties().isEmpty() && !isPropertyDisplay) {
                    if (triangle == null) {
                        makeNewDrawTriangle(drawNode);
                    }
                } else {
                    if (triangle != null) {
                        triangle.erase();
                        triangle = null;
                        drawNode.setDrawTriangle(triangle);
                    }
                }
                dragMoveTriangle(entry.getValue());
                lineManager.redrawLine(FMEditor.this, drawRect);
            }
        }
        // DrawRectの削除
        for (FMDrawNode drawNode : delLabelList) {
            drawItems.remove(drawNode.getDrawRect().hashCode());
            drawNode.getDrawRect().erase();
            if (drawNode.getDrawLine() != null) {
                drawNode.getDrawLine().erase();
            }
            if (drawNode.getDrawLine_Transparent() != null) {
                drawNode.getDrawLine_Transparent().erase();
            }
            if (drawNode.getDrawOval() != null) {
                drawNode.getDrawOval().erase();
            }
            if (drawNode.getCardinality() != null) {
                drawNode.getCardinality().erase();
            }
            if (drawNode.getDrawTriangle() != null) {
                drawNode.getDrawTriangle().erase();
            }
        }
        memo.refresh();

        if (this.listener != null) {
            this.listener.onRefreshEnd();
        }
    }

    /**
     * Delete the node and redraw the canvas.
     * @param selectNodes Selected Node
     * @param childNodes Child node of the selected Node
     */
    protected void onDelete(List<FMNode> selectNodes, List<FMNode[]> childNodes) {
        nodeManager.onDelete(selectNodes, childNodes, FMEditor.this);
        refresh();
    }

    /**
     * Create a DrawRect for the FM node.
     * @param node FMNode with information of DrawRect to create
     */
    private void makeNewLabel(FMNode node) {
        DrawRect drawRect = nodeManager.makeNewLabel(node, drawPane);
        addMouseEvent(drawRect);
        addDragResizeEvent(drawRect);
        addDragAndDropEvent(drawRect);
        addRightClickEvent(drawRect, node);
        addLeftClickEvent(drawRect);

        FMDrawNode drawNode = new FMDrawNode();
        drawItems.put(drawRect.hashCode(), drawNode);
        drawNode.setFmNode(node);
        drawNode.setDrawRect(drawRect);

        makeNewCardinality(drawNode);
        makeNewDrawOval(drawNode);
        makeNewDrawTriangle(drawNode);

        lineManager.makeNewDrawLine(FMEditor.this, drawNode);
        if (drawNode.getDrawLine() != null) {
            lineManager.createDrawLineTransparent(FMEditor.this, drawNode);
            addMouseEvent(drawNode.getDrawLine(), drawNode.getDrawLine_Transparent());
            addDragResizeEvent(drawNode.getDrawLine());
            addRightClickEvent(drawNode.getDrawLine(), drawNode.getDrawLine_Transparent());
            addLeftClickEvent(drawNode.getDrawLine(), drawNode.getDrawLine_Transparent());
        }
    }

    /**
     * Create a DrawLabel for cardinality.
     * @param drawNode Class with information on DrawRect, DrawLine, FMNode
     */
    private void makeNewCardinality(FMDrawNode drawNode) {
        if (drawNode.getFmNode().getChildType().equals(ChildType.XOR) && drawNode.getFmNode().getMin() != -1) {
            DrawLabel cardinality = nodeManager.makeNewCardinality(drawNode.getFmNode(), drawPane, getCurrentLayoutMode());
            addCardinalityDragAndDropEvent(cardinality, drawNode.getDrawRect());
            drawNode.setCardinality(cardinality);
        }

    }

    /**
     * Create a DrawOval to display on the line
     * @param drawNode Class with information on DrawRect, DrawLine, FMNode
     */
    private void makeNewDrawOval(FMDrawNode drawNode) {
        DrawRect drawRect = drawNode.getDrawRect();
        if (drawRect == null || "".equals(drawRect.getAttributeAsString("drawRect"))) {
            return;
        }
        FMNode parentNode = nodeManager.getParentNode(drawNode.getFmNode());
        if (parentNode == null) {
            return;
        }
        DrawOval oval = new DrawOval();
        oval.setDrawPane(drawPane);
        oval.setRadius(FMEditorConstants.OVAL_SIZE);
        oval.setFillOpacity(1);
        oval.setLineColor("black");
        oval.setLineWidth(1);
        oval.draw();
        if (!drawNode.getFmNode().isOptional()) {
            oval.setFillColor("black");
        } else {
            oval.setFillColor("white");
        }

        drawNode.setDrawOval(oval);
    }

    /**
     * Create a DrawTriangle to display on the DrawRect
     * @param drawNode Class with information on DrawRect, DrawLine, FMNode
     */
    private void makeNewDrawTriangle(FMDrawNode drawNode) {
        DrawRect drawRect = drawNode.getDrawRect();
        if (drawRect == null || "".equals(drawRect.getAttributeAsString("drawRect"))) {
            return;
        }
        FMNode node = drawNode.getFmNode();
        if (node.getProperties().isEmpty()) {
            return;
        }
        DrawTriangle triangle = new DrawTriangle();
        triangle.setDrawPane(drawPane);
        triangle.setFillColor("red");
        triangle.setLineWidth(1);
        triangle.setPoints(new Point(FMEditorConstants.TRIANGLE_SIZE, 0), new Point(FMEditorConstants.TRIANGLE_SIZE, FMEditorConstants.TRIANGLE_SIZE), new Point(0, 0));
        triangle.draw();
        drawNode.setDrawTriangle(triangle);
    }

    /**
     * Create resize event for DrawRect
     * @param drawRect DrawRect associated with FMNode
     */
    protected void addDragResizeEvent(DrawRect drawRect) {
        drawRect.addDragResizeStartHandler(eventHolder.createDragResizeStartHandler(this, drawRect));
        drawRect.addDragResizeMoveHandler(eventHolder.createDragResizeMoveHandler(this, drawRect));
        drawRect.addDragResizeStopHandler(eventHolder.createDragResizeStopHandler(this, drawRect));
    }

    /**
     * Create a drag and drop event for DrawRect.
     * @param drawRect DrawRect associated with FMNode
     */
    protected void addDragAndDropEvent(DrawRect drawRect) {
        drawRect.addDragStartHandler(eventHolder.createDragStartHandler(this, drawRect));
        drawRect.addDragMoveHandler(eventHolder.createDragMoveHandler(this, drawRect));
        drawRect.addDragStopHandler(eventHolder.createDragStopHandler(this));
    }

    /**
     * Create a drag and drop event for the cardinality DrawLabel.
     * @param cardinality Cardinality Draw Label
     * @param drawRect DrawRect with cardinality
     */
    protected void addCardinalityDragAndDropEvent(DrawLabel cardinality, DrawRect drawRect) {
        cardinality.addDragStopHandler(eventHolder.createCardinalityDragStopHandler(FMEditor.this, drawRect, cardinality));
    }

    /**
     * Create a drag and drop event for DrawLine.
     * @param drawLine DrawLine associated with FMNode
     */
    protected void addDragResizeEvent(DrawLine drawLine) {
        drawLine.addResizedHandler(eventHolder.createResizedHandler(FMEditor.this, drawLine));
    }

    /**
     * Create MouseOver and MouseOut events for DrawRect.
     * @param drawRect DrawRect associated with FMNode
     */
    protected void addMouseEvent(DrawRect drawRect) {
        drawRect.addMouseOverHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                mouseMoveFlag = true;
            }
        });
        drawRect.addMouseOutHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                mouseMoveFlag = false;
            }
        });
    }

    /**
     * Create MouseOver and MouseOut events for DrawLine.
     * @param drawLine DrawLine associated with FMNode
     * @param drawLineTransparent Transparent line on drawLine
     */
    protected void addMouseEvent(DrawLine drawLine, DrawLine drawLineTransparent) {
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
        drawLineTransparent.addMouseOverHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                mouseMoveFlag_line = true;
            }
        });
        drawLineTransparent.addMouseOutHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                mouseMoveFlag_line = false;
            }
        });
    }

    /**
     * Create a canvas right-click event handler
     */
    private void addRightClickEvent() {
        drawPane.addShowContextMenuHandler(new ShowContextMenuHandler() {
            @Override
            public void onShowContextMenu(ShowContextMenuEvent event) {
                // 右クリックした座標を取得
                int x = event.getX();
                int y = event.getY();
                drawPane.setContextMenu(FMMenuItemCreator.createCanvasRightClickMenuItem(FMEditor.this, x, y));
            }
        });
    }

    /**
     * Create DrawRect right-click event handler
     * @param drawRect DrawRect associated with FMNode
     * @param node FM node related to DrawRect
     */
    protected void addRightClickEvent(DrawRect drawRect, FMNode node) {
        drawRect.addShowContextMenuHandler(new com.smartgwt.client.widgets.drawing.events.ShowContextMenuHandler() {
            @Override
            public void onShowContextMenu(com.smartgwt.client.widgets.drawing.events.ShowContextMenuEvent event) {
                // 選択状態の解除、設定
                if (drawRect.getFillColor().equals(node.getFillColor())) {
                    drawRect.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
                    drawRect.setFillColor("yellow");
                    drawRect.showKnobs(KnobType.RESIZE);
                }

                drawRect.setContextMenu(FMMenuItemCreator.createDrawRectRightClickMenuItem(FMEditor.this, drawRect));
                drawPane.focus();

                String uuid = getDrawRectMap().get(drawRect.hashCode()).getFmNode().getRefuuid();
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
     * Create DrawLine right-click event handler
     * @param drawLine DrawLine associated with FMNode
     * @param drawLineTransparent Transparent line on drawLine
     */
    protected void addRightClickEvent(DrawLine drawLine, DrawLine drawLineTransparent) {
        drawLine.addShowContextMenuHandler(new com.smartgwt.client.widgets.drawing.events.ShowContextMenuHandler() {
            @Override
            public void onShowContextMenu(com.smartgwt.client.widgets.drawing.events.ShowContextMenuEvent event) {
                if (!lineManager.getSelectFlag(drawLine)) {
                    drawLine.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
                }
            };
        });
        drawLineTransparent.addShowContextMenuHandler(new com.smartgwt.client.widgets.drawing.events.ShowContextMenuHandler() {
            @Override
            public void onShowContextMenu(com.smartgwt.client.widgets.drawing.events.ShowContextMenuEvent event) {
                if (!lineManager.getSelectFlag(drawLine)) {
                    drawLine.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
                }
            }
        });
    }

    /**
     * Create a process for the left click event of Canvas
     */
    private void addLeftClickEvent() {
        drawPane.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // CTRLキーが押下されていない かつ、DrawRect上にマウスポインタが存在しない
                if (!event.isCtrlKeyDown() && !mouseMoveFlag && !mouseMoveFlag_line && !memo.getMouseMoveFlag()) {
                    for (Map.Entry<Integer, FMDrawNode> entry : drawItems.entrySet()) {
                        DrawRect drawRect = entry.getValue().getDrawRect();
                        drawRect.setFillColor(entry.getValue().getFmNode().getFillColor());
                        drawRect.hideAllKnobs();
                    }
                    lineManager.selectDrawLineAll(FMEditor.this, false);
                    memo.selectMemoAll(false);
                }
            }
        });

        drawPane.addDragStopHandler(new DragStopHandler() {

            @Override
            public void onDragStop(DragStopEvent event) {
                DrawLine drawLine = lineManager.resizeDrawLine;
                if (drawLine != null) {
                    FMDrawNode fmDrawNode = lineManager.getFMDrawNode(FMEditor.this, drawLine);
                    lineManager.resizeDrawLine(FMEditor.this, fmDrawNode.getFmNode(), drawLine);
                    lineManager.resizeDrawLine = null;
                    refresh();
                }
            }
        });
    }

    /**
     * Create DrawRect Left Click Event
     * @param drawRect DrawRect associated with FMNode
     */
    protected void addLeftClickEvent(DrawRect drawRect) {
        drawRect.addHandler(eventHolder.createLeftClickOnLabelHandler(this, drawItems, drawRect), ClickEvent.getType());
        drawRect.addClickHandler(new com.smartgwt.client.widgets.drawing.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.drawing.events.ClickEvent event) {
                drawRect.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
            }
        });
    }

    /**
     * Create DrawLine Left Click Event
     * @param drawLine DrawLine associated with FMNode
     * @param drawLineTransparent Transparent line on drawLine
     */
    protected void addLeftClickEvent(DrawLine drawLine, DrawLine drawLineTransparent) {
        drawLine.addHandler(eventHolder.createLeftClickOnDrawLineHandler(FMEditor.this, drawLine), ClickEvent.getType());
        drawLine.addClickHandler(new com.smartgwt.client.widgets.drawing.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.drawing.events.ClickEvent event) {
                drawLine.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
            }
        });
        drawLineTransparent.addHandler(eventHolder.createLeftClickOnDrawLineHandler(FMEditor.this, drawLine), ClickEvent.getType());
        drawLineTransparent.addClickHandler(new com.smartgwt.client.widgets.drawing.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.drawing.events.ClickEvent event) {
                drawLine.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
            }
        });
    }

    /**
     * Create canvas key input event.
     */
    private void addShortcutKeyEvent() {
        drawPane.addKeyPressHandler(keyEventHolder.createShortcutKeyEvent(FMEditor.this));
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
    public void addOpenFileHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        referenceOpenItem.addClickHandler(handler);
    }

    /**
     * Create a click event for the "Create child model" menu.
     * @param handler MenuItem click handler
     */
    public void addCreateChildModelHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        childModelItem.addClickHandler(handler);
    }

    /**
     * Method to identify the drawing position of DrawOval based on DrawRect and draw
     * @param drawNode Class with information on DrawRect, DrawLine, FMNode
     */
    protected void dragMoveOval(FMDrawNode drawNode) {
        DrawOval oval = drawNode.getDrawOval();
        if (oval == null) {
            return;
        }
        DrawRect drawRect = drawNode.getDrawRect();
        if (drawRect == null || "".equals(drawRect.getAttributeAsString("drawRect"))) {
            return;
        }
        FMNode parentNode = nodeManager.getParentNode(drawNode.getFmNode());
        if (parentNode == null) {
            return;
        }
        if (!drawNode.getFmNode().isOptional()) {
            oval.setFillColor("black");
        } else {
            oval.setFillColor("white");
        }

        DrawRect label = drawNode.getDrawRect();
        int[] coord = getCurrentLayoutMode().getOvalPosition(label);
        oval.moveTo(coord[0], coord[1]);
        oval.bringToFront();
    }

    /**
     * Method to identify the drawing position of DrawTriangle based on DrawRect and draw
     * @param drawNode Class with information on DrawRect, DrawLine, FMNode
     */
    protected void dragMoveTriangle(FMDrawNode drawNode) {
        DrawTriangle triangle = drawNode.getDrawTriangle();
        if (triangle == null) {
            return;
        }
        DrawRect label = drawNode.getDrawRect();
        int[] coord = getCurrentLayoutMode().getTrianglePosition(label);
        triangle.moveTo(coord[0], coord[1]);
        triangle.bringToFront();
    }

    /**
     * Event that draws an arc on all DrawRects
     */
    private void addDrawArcEvent() {
        drawPane.addDrawStartHandler(eventHolder.createDrawStartHandler(this, nodeManager));
    }

    /**
     * Get all layout modes
     * @return All layout modes
     */
    public List<LayoutMode> getLayoutModes() {
        return this.autoLayout.getModes();
    }

    /**
     * Get the currently active layout mode
     * @return layout mode
     */
    public LayoutMode getCurrentLayoutMode() {
        return this.autoLayout.getMode(root.getAutoLayout());
    }

    /**
     * Activates the specified layout mode and redraws the canvas.
     * @param key The specified layout mode
     */
    public void setCurrentLayoutMode(int key) {
        root.setAutoLayout(key);
        refresh();
    }

    /**
     * Process to check the current layout mode
     * @return True if the current layout mode is Horizontal
     */
    public boolean isHorizontalLayout() {
        return getCurrentLayoutMode().isHorizontal();
    }

    /**
     * Applies the current layout mode to the canvas.
     */
    public void adjustLayout() {
        if (!Objects.isNull(root.getNode())) {
            getCurrentLayoutMode().execute(root.getNode());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditManager getEditManager() {
        return this.manager;
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
    public Map<AbstractStyle, DrawItem> getArrangeStyleNode() {
        Map<AbstractStyle, DrawItem> styleMap = new HashMap<>();
        for (FMDrawNode fmDrawNode : this.drawItems.values()) {
            styleMap.put(fmDrawNode.getFmNode(), fmDrawNode.getDrawRect());
        }
        return styleMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<AbstractStyle, DrawItem> getSelectedArrangeStyleNode() {
        Map<AbstractStyle, DrawItem> styleMap = new HashMap<>();
        for (FMDrawNode fmDrawNode : nodeManager.getSelectFMDrawNode()) {
            styleMap.put(fmDrawNode.getFmNode(), fmDrawNode.getDrawRect());
        }
        return styleMap;
    }

    /** a menu to change the style of a node */
    MenuItem menuItem;

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuItem getNodeArrangeItem() {
        return menuItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNodeArrangeItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doRefresh() {
        refresh();
    }

    /**
     * Gets the display flag of the property.
     * @return If true, the property value is displayed in drawRect.<br>
     *         If false, a red triangle will appear in the upper right of drawRect if the property value is set.
     */
    public boolean isPropertyDisplay() {
        return isPropertyDisplay;
    }

    /**
     * Sets the display flag of the property.
     * @param isPropertyDisplay
     */
    public void setPropertyDisplay(boolean isPropertyDisplay) {
        this.isPropertyDisplay = isPropertyDisplay;
    }

}
