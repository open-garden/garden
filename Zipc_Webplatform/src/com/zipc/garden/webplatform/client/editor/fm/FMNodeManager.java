package com.zipc.garden.webplatform.client.editor.fm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.KnobType;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.drawing.DrawLabel;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.drawing.DrawRect;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
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
import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.fm.FMFactory;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMPackage;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.webplatform.client.command.FMEditorCommandProvider;
import com.zipc.garden.webplatform.client.editor.fm.layout.LayoutMode;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * Class that manages the processing related to DrawRect created by the feature model editor.
 */
public class FMNodeManager {

    /** {@link FMEditor#root} */
    FMRoot root;

    /** {@link FMEditor#drawItems} */
    Map<Integer, FMDrawNode> drawItems;

    /** Default name of Node */
    private final String DEFAULT_NAME = "Node";

    /**
     * Set the field variable of FM editor.
     * @param root {@link FMEditor#root}
     * @param drawItems {@link FMEditor#drawItems}
     */
    public void setFieldData(FMRoot root, Map<Integer, FMDrawNode> drawItems) {
        this.root = root;
        this.drawItems = drawItems;
    }

    /**
     * The node information is reflected in the EMF model and the canvas is refreshed.
     * @param childType Line type connecting nodes (AND or XOR)
     * @param addPosition Insertion position of child node to List
     * @param drawRect DrawRect information of parent node
     * @param editor Main class of feature-model editor
     */
    private void onAdd(String childType, int addPosition, DrawRect drawRect, FMEditor editor) {

        // MapのNode値をリストに変換
        List<FMNode> nodeList = new ArrayList<FMNode>();
        drawItems.values().stream().forEach(action -> nodeList.add(action.getFmNode()));

        int top = 0;
        int left = 180;

        FMNode parentNode;
        if (drawRect == null) {
            String nodeName = getNewName(nodeList, DEFAULT_NAME);
            parentNode = FMFactory.eINSTANCE.createFMNode();
            parentNode.setName(nodeName);
            parentNode.setTop(0);
            parentNode.setLeft(0);
            parentNode.setHeight(40);
            parentNode.setWidth(80);

            nodeList.add(parentNode);
        } else {
            parentNode = drawItems.get(drawRect.hashCode()).getFmNode();

            top = drawRect.getTop();
            left = drawRect.getLeft() + 180;
        }
        parentNode.setChildType(ChildType.get(childType));

        // 選択されているNodeが子Nodeかチェックする
        List<DrawRect> drawRects = editor.nodeManager.getSelectDrawRect();
        if (drawRects.size() == 1) {
            FMDrawNode drawNode = drawItems.get(drawRects.get(0).hashCode());
            for (int i = 0; i < parentNode.getChildren().size(); i++) {
                FMNode childNode = parentNode.getChildren().get(i);
                if (drawNode.getFmNode().hashCode() == childNode.hashCode()) {

                    // 兄Nodeの場合
                    if (addPosition == i) {
                        // 選択したNodeの上の位置を取得
                        top = drawNode.getDrawRect().getTop() - drawNode.getDrawRect().getHeight();
                    }
                    // 子Nodeの場合
                    else {
                        // 選択したNodeの下の位置を取得
                        top = drawNode.getDrawRect().getTop() + drawNode.getDrawRect().getHeight();
                    }
                    left = drawNode.getDrawRect().getLeft();
                    break;
                }
            }
        }

        // Canvasの枠外に出ないように調整する
        if (top < 0)
            top = 0;
        if (top + 40 > editor.drawPane.getHeight())
            top = editor.drawPane.getHeight() - 40;
        if (left < 0)
            left = 0;
        if (left + 80 > editor.drawPane.getWidth())
            left = editor.drawPane.getWidth() - 80;

        String nodeName = getNewName(nodeList, DEFAULT_NAME);
        FMNode childNode = FMFactory.eINSTANCE.createFMNode();
        childNode.setName(nodeName);
        childNode.setTop(top);
        childNode.setLeft(left);
        childNode.setHeight(40);
        childNode.setWidth(80);

        CompoundCommand cmd = FMEditorCommandProvider.getInstance().addNode(root, parentNode, childNode, addPosition);

        editor.getEditManager().execute(cmd.unwrap());
        editor.refresh();
    }

    /**
     * The node name is reflected in the EMF model and the canvas is refreshed.
     * @param editor Main class of feature-model editor
     * @param drawRect DrawRect with the node name changed
     * @param newNodeName The name of the new node
     */
    public void setNodeName(FMEditor editor, DrawRect drawRect, String newNodeName) {
        FMNode node = drawItems.get(drawRect.hashCode()).getFmNode();
        CompoundCommand cmd = FMEditorCommandProvider.getInstance().renameNode(node, newNodeName);
        editor.getEditManager().execute(cmd.unwrap());
        editor.refresh();
    }

    /**
     * The Child Type is reflected in the EMF model and the canvas is updated.
     * @param editor Main class of feature-model editor
     * @param node Node to change
     * @param newChildType Line type connecting nodes (AND or XOR)
     */
    public void setChildType(FMEditor editor, FMNode node, ChildType newChildType) {
        CompoundCommand cmd = FMEditorCommandProvider.getInstance().changeChildType(node, newChildType);
        if (newChildType.equals(ChildType.AND)) {
            cmd = FMEditorCommandProvider.getInstance().editCardinality(editor, node, -1, -1, 0, 0, cmd);
        }
        editor.getEditManager().execute(cmd.unwrap());
        editor.drawPane.refreshNow();

        editor.refresh();
    }

    /**
     * The child types of all selected nodes are reflected in the EMF model and the canvas is updated.
     * @param editor Main class of feature-model editor
     * @param childType Line type connecting nodes (AND or XOR)
     */
    protected void changeEdgeOfSelectedNode(FMEditor editor, ChildType childType) {
        List<DrawRect> drawRects = getSelectDrawRect();
        if (drawRects.size() > 0) {
            for (DrawRect drawRect : drawRects) {
                FMNode node = drawItems.get(drawRect.hashCode()).getFmNode();
                setChildType(editor, node, childType);
            }
        }
    }

    /**
     * The cardinality of the specified node is reflected in the EMF model and the canvas is refresh.
     * @param editor Main class of feature-model editor
     * @param node Node to change
     * @param min Minimum cardinality
     * @param max Maximum cardinality
     */
    public void setCardinality(FMEditor editor, FMNode node, int min, int max) {
        setCardinality(editor, node, min, max, node.getX(), node.getY());
    }

    /**
     * The cardinality of the specified node is reflected in the EMF model and the canvas is refresh.
     * @param editor Main class of feature-model editor
     * @param node Node to change
     * @param min Minimum cardinality. Specify -1 to delete.
     * @param max Maximum cardinality. Specify -1 to delete.
     * @param x X coordinate of cardinality label. Specify 0 to delete.
     * @param y Y coordinate of cardinality label. Specify 0 to delete.
     */
    public void setCardinality(FMEditor editor, FMNode node, int min, int max, int x, int y) {
        CompoundCommand cmd = FMEditorCommandProvider.getInstance().editCardinality(editor, node, min, max, x, y);
        editor.getEditManager().execute(cmd.unwrap());
        editor.refresh();
    }

    /**
     * Reflect the optional flag of the node in the EMF model and refresh the canvas.
     * @param editor Main class of feature-model editor
     * @param node Node to change
     * @param optional For true, OptionalNode
     */
    protected void changeOptional(FMEditor editor, FMNode node, boolean optional) {
        CompoundCommand cmd = FMEditorCommandProvider.getInstance().changeOptional(node, optional);
        editor.getEditManager().execute(cmd);
        editor.refresh();
    }

    /**
     * The optional flags of all selected nodes are reflected in the EMF model and the canvas is refreshed.
     * @param editor Main class of feature-model editor
     * @param isOptional For true, OptionalNode
     */
    protected void changeOptionalOfSelectedNode(FMEditor editor, boolean isOptional) {
        List<DrawRect> drawRects = getSelectDrawRect();
        if (drawRects.size() > 0) {
            for (DrawRect drawRect : drawRects) {
                FMNode node = drawItems.get(drawRect.hashCode()).getFmNode();
                changeOptional(editor, node, isOptional);
            }
        }
    }

    /**
     * Delete the selected node and the related lower layer node from the EMF model.
     * @param selectNodes Selected node
     * @param childNodes Nodes that belong to the selected node
     * @param editor Main class of feature-model editor
     */
    public void onDelete(List<FMNode> selectNodes, List<FMNode[]> childNodes, FMEditor editor) {
        List<FMNode[]> pairList = new ArrayList<>();
        getPairList(root.getNode(), pairList);
        CompoundCommand cmd = FMEditorCommandProvider.getInstance().removeNodes(root, pairList, selectNodes, childNodes);
        editor.getEditManager().execute(cmd.unwrap());
    }

    /**
     * Root node addition processing
     * @param addPosition 0
     * @param editor Main class of feature-model editor
     */
    protected void addNode(int addPosition, FMEditor editor) {
        addNode(addPosition, null, editor);
    }

    /**
     * Node addition processing
     * @param addPosition Child Node addition position
     * @param drawRect Parent Node
     * @param editor Main class of feature-model editor
     */
    protected void addNode(int addPosition, DrawRect drawRect, FMEditor editor) {
        String childType = "AND";
        if (drawRect != null) {
            childType = drawItems.get(drawRect.hashCode()).getFmNode().getChildType().getName();
        }
        onAdd(childType, addPosition, drawRect, editor);
    }

    /**
     * Creates a new node in the same hierarchy as the selected node.
     * @param position When 0: The node to be created is inserted at the index position of the selected node. <br>
     *            When 1: Inserts the node to be created at the index +1 of the selected node.
     * @param editor Main class of feature-model editor
     */
    protected void addNodeBrother(int position, FMEditor editor) {
        List<DrawRect> drawRects = getSelectDrawRect();
        if (drawRects.size() == 1) {
            int addPosition = 0;
            DrawRect drawRect = drawRects.get(0);
            FMNode childNode = drawItems.get(drawRect.hashCode()).getFmNode();
            FMNode parentNode = getParentNode(childNode);
            DrawRect parentDrawRect = getDrawRect(parentNode);
            for (int i = 0; i < parentNode.getChildren().size(); i++) {
                if (childNode.hashCode() == parentNode.getChildren().get(i).hashCode()) {
                    addPosition = i + position;
                    break;
                }
            }
            addNode(addPosition, parentDrawRect, editor);
        }
    }

    /**
     * Displays a modal window for changing the node name.
     * @param drawRect DrawRect to rename
     * @param editor Main class of feature-model editor
     */
    protected void renameNode(DrawRect drawRect, FMEditor editor) {
        // ModalWindow作成
        final Window winModal = new Window();
        winModal.setTop(drawRect.getPageTop() + drawRect.getHeight());
        winModal.setLeft(drawRect.getPageLeft() + drawRect.getWidth());
        winModal.setHeight(100);
        winModal.setWidth(310);
        winModal.setTitle("Rename Node");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.setKeepInParentRect(true);
        winModal.addCloseClickHandler(e -> winModal.markForDestroy());

        // ModalWindowがページからはみ出る場合
        if (winModal.getTop() + winModal.getHeight() > editor.drawPane.getPageBottom()) {
            winModal.setTop(editor.drawPane.getPageBottom() - winModal.getHeight());
        }
        if (winModal.getLeft() + winModal.getWidth() > editor.drawPane.getPageRight()) {
            winModal.setLeft(editor.drawPane.getPageRight() - winModal.getWidth());
        }

        DynamicForm form = new DynamicForm();
        form.setHeight100();
        form.setWidth100();
        form.setPadding(5);
        form.setNumCols(5);
        form.setColWidths(70, 70, 5, 70, 70);
        form.setLayoutAlign(VerticalAlignment.BOTTOM);
        form.setAutoFocus(true);

        TextItem nodeText = new TextItem();
        nodeText.setTitle("Node");
        nodeText.setTitleColSpan(1);
        nodeText.setColSpan(4);
        String nodeName = drawItems.get(drawRect.hashCode()).getFmNode().getName();
        nodeText.setValue(nodeName);
        nodeText.setSelectOnFocus(true);

        SubmitItem okButton = new SubmitItem();
        okButton.setTitle("OK");
        okButton.setColSpan(2);
        okButton.setWidth(80);
        okButton.setAlign(Alignment.RIGHT);
        okButton.setStartRow(false);
        okButton.setEndRow(false);
        okButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                // 未入力チェック
                if (nodeText.getValue() == null || "".equals(nodeText.getValue().toString().trim())) {
                    SC.warn("Please input node.");
                    return;
                }
                // 同名チェック。変更前後で名称が変わらない場合処理しない
                if (!nodeName.equals(nodeText.getValue().toString())) {

                    setNodeName(editor, drawRect, nodeText.getValue().toString());
                }
                winModal.markForDestroy();
            }
        });
        ButtonItem canselButton = new ButtonItem();
        canselButton.setTitle("Cancel");
        canselButton.setColSpan(2);
        canselButton.setWidth(80);
        canselButton.setStartRow(false);
        canselButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                winModal.markForDestroy();
            }
        });
        SpacerItem space = new SpacerItem();
        space.setWidth(5);
        form.setFields(nodeText, okButton, space, canselButton);
        form.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.ENTER.equals(event.getKeyName())) {
                    okButton.fireEvent(new com.smartgwt.client.widgets.form.fields.events.ClickEvent(okButton.getJsObj()));
                }
            }
        });
        winModal.addItem(form);
        winModal.show();
    }

    /**
     * A new DrawRect will be created.<br>
     * Draw the FMNode model content on the canvas.
     * @param node FMNode corresponding to DrawRect
     * @param drawPane Canvas to draw DrawRect
     * @return The created DrawRect
     */
    public DrawRect makeNewLabel(FMNode node, DrawPane drawPane) {
        DrawRect drawRect = new DrawRect();
        drawRect.setDrawPane(drawPane);
        drawRect.draw();
        drawRect.setWidth(node.getWidth());
        drawRect.setHeight(node.getHeight());
        drawRect.setTop(node.getTop());
        drawRect.setLeft(node.getLeft());
        drawRect.setTitle(node.getName());
        drawRect.setCanDrag(true);
        drawRect.setFillOpacity(1);
        drawRect.setFillColor(node.getFillColor());
        drawRect.setKeepInParentRect(true);

        return drawRect;
    }

    /**
     * Create a DrawLabel that displays the cardinality and draw it on the canvas.
     * @param node FMNode corresponding to DrawLabel
     * @param drawPane Canvas to draw DrawLabel
     * @param layoutMode The currently active layout mode
     * @return The created DrawLabel
     */
    public DrawLabel makeNewCardinality(FMNode node, DrawPane drawPane, LayoutMode layoutMode) {

        DrawLabel cardinality = new DrawLabel();
        cardinality.setDrawPane(drawPane);
        int[] position = layoutMode.getDefaultCardinalityPosition(node);
        cardinality.setTop(node.getY() - position[1]);
        cardinality.setLeft(node.getX() - position[0]);
        cardinality.setFontSize(13);
        cardinality.setLineColor("#000000");
        cardinality.setFontWeight("normal");
        cardinality.setCanDrag(true);
        String max = Integer.toString(node.getMax());
        if (node.getMax() == -1) {
            max = "*";
        }
        cardinality.setContents(node.getMin() + ".." + max);
        cardinality.setKeepInParentRect(true);
        cardinality.draw();

        return cardinality;
    }

    /**
     * Get all the nodes under the argument parentNode and create a list with parentNode and childNode.
     * @param parentNode Parent node
     * @param pairList List with parent and child nodes
     */
    public void getPairList(FMNode parentNode, List<FMNode[]> pairList) {
        if (parentNode != null) {
            for (FMNode node : parentNode.getChildren()) {
                if (node != null) {
                    if (!node.getChildren().isEmpty()) {
                        getPairList(node, pairList);
                    }
                    FMNode[] pair = new FMNode[2];
                    pair[0] = parentNode;
                    pair[1] = node;
                    pairList.add(pair);
                }
            }
        }
    }

    /**
     * Get parent node
     * @param childNode Child node
     * @return Parent node. Null if not present
     */
    protected FMNode getParentNode(FMNode childNode) {
        EObject parentNode = childNode.eContainer();
        if (parentNode instanceof FMNode) {
            return (FMNode) parentNode;
        } else {
            return null;
        }
    }

    /**
     * Change the storage location (display order) of the node and reflect it in the EMF model
     * @param editor Main class of feature-model editor
     * @param targetNode Node that changes order
     * @param position -1: Move the order of the target node to the previous one.<br>
     *            1: Move the order of the target node to the next one.
     */
    protected void moveNodePosition(FMEditor editor, FMNode targetNode, int position) {
        FMNode parentNode = getParentNode(targetNode);
        int nowPos = parentNode.getChildren().indexOf(targetNode);
        if (nowPos < 0) {
            return;
        } else if (nowPos + position < 0) {
            return;
        } else if (nowPos + position >= parentNode.getChildren().size()) {
            return;
        }
        CompoundCommand cmd = FMEditorCommandProvider.getInstance().moveNodePosition(parentNode, nowPos, position);
        editor.getEditManager().execute(cmd);
    }

    /**
     * Get lower node than target node
     * @param parentNodes Target node
     * @return Subordinate node list
     */
    protected List<FMNode[]> getNodeChildren(List<FMNode> parentNodes) {
        Map<Integer, FMNode[]> result = new HashMap<>();
        List<FMNode[]> pairList = new ArrayList<>();

        for (FMNode node : parentNodes) {
            getPairList(node, pairList);
        }
        for (int i = 0; i < pairList.size(); i++) {
            result.put(pairList.get(i)[1].hashCode(), pairList.get(i));
        }
        return new ArrayList<>(result.values());
    }

    /**
     * Get the DrawRect associated with the FMNode model
     * @param node Node to search
     * @return search result
     */
    public DrawRect getDrawRect(FMNode node) {
        DrawRect drawRect = null;
        for (Map.Entry<Integer, FMDrawNode> entry : drawItems.entrySet()) {
            if (entry.getValue().getFmNode().hashCode() == node.hashCode()) {
                drawRect = entry.getValue().getDrawRect();
            }
        }
        return drawRect;
    }

    /**
     * Get the selected Node
     * @return DrawRect list
     */
    public List<DrawRect> getSelectDrawRect() {
        List<DrawRect> selecteds = new ArrayList<>();
        for (FMDrawNode FMDrawNode : getSelectFMDrawNode()) {
            selecteds.add(FMDrawNode.getDrawRect());
        }
        return selecteds;
    }

    /**
     * Get FMDrawNode based on the selected drawRect
     * @return FMDrawNode list
     */
    public List<FMDrawNode> getSelectFMDrawNode() {
        List<FMDrawNode> selecteds = new ArrayList<>();
        for (Map.Entry<Integer, FMDrawNode> entry : drawItems.entrySet()) {
            DrawRect drawRect = entry.getValue().getDrawRect();

            // 選択されている要素を取得
            if (getSelectFlag(drawRect)) {
                selecteds.add(entry.getValue());
            }
        }
        return selecteds;
    }

    /**
     * Get the selected Node
     * @return FMNode list
     */
    public List<FMNode> getSelectFMNodes() {
        List<FMNode> selecteds = new ArrayList<>();
        for (Map.Entry<Integer, FMDrawNode> entry : drawItems.entrySet()) {
            DrawRect drawRect = entry.getValue().getDrawRect();
            // 選択されている要素を取得
            if (drawRect.getKnobs() != null && drawRect.getKnobs().length != 0) {
                FMNode node = drawItems.get(drawRect.hashCode()).getFmNode();
                selecteds.add(node);
            }
        }
        return selecteds;
    }

    /**
     * Select a node.
     * @param Node to select
     */
    public void setSelectDrawRect(FMNode node) {
        DrawRect drawRect = getDrawRect(node);
        drawRect.setFillColor("yellow");
        drawRect.showKnobs(KnobType.RESIZE);
    }

    /**
     * Set the selection status of all nodes.
     * @param selectFlag true: Select all<br>
     *            false: Deselect all
     */
    public void setSelectDrawRectAll(boolean selectFlag) {
        if (selectFlag) {
            for (Map.Entry<Integer, FMDrawNode> entry : drawItems.entrySet()) {
                DrawRect drawRect = entry.getValue().getDrawRect();
                drawRect.setFillColor("yellow");
                drawRect.showKnobs(KnobType.RESIZE);
            }
        } else {
            for (Map.Entry<Integer, FMDrawNode> entry : drawItems.entrySet()) {
                DrawRect drawRect = entry.getValue().getDrawRect();
                drawRect.setFillColor(entry.getValue().getFmNode().getFillColor());
                drawRect.hideAllKnobs();
            }
        }
    }

    /**
     * Deselect all nodes and select only the root node.
     * @param editor Main class of feature-model editor
     */
    public void selectRootNode(FMEditor editor) {
        if (root.getNode() != null) {
            setSelectDrawRectAll(false);
            setSelectDrawRect(root.getNode());
        }
        editor.drawPane.focus();
    }

    /**
     * Sort the Node list. Sort order is given as an argument
     * @param nodes List of nodes to sort
     * @param comparator Sort order. Sorting is performed based on the node coordinate position.
     * @return FMNodes are returned in the sorted order.
     */
    protected Map<Integer, FMNode> sortNodeChildren(List<FMNode> nodes, Comparator<DrawRect> comparator) {
        List<DrawRect> drawRects = new ArrayList<>();
        for (FMNode node : nodes) {
            drawRects.add(getDrawRect(node));
        }
        drawRects.sort(comparator);

        Map<Integer, FMNode> nodeMap = new HashMap<>();
        int i = 0;
        for (DrawRect drawRect : drawRects) {
            i++;
            nodeMap.put(i, drawItems.get(drawRect.hashCode()).getFmNode());
        }
        return nodeMap;
    }

    /**
     * Gets the DrawRect that exists at the specified coordinates.
     * @param editor Main class of feature-model editor
     * @param x X coordinate
     * @param y Y coordinate
     * @return The DrawRect that exists at the specified coordinates
     */
    protected Map<Integer, FMDrawNode> getDrawRectIsBelowThePointer(FMEditor editor, int x, int y) {
        return editor.getDrawRectMap().entrySet().stream().filter(map -> map.getValue().getDrawRect().isPointInPath(x, y))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
    }

    /**
     * Display a modal window for editing Cardinality
     * @param editor Main class of feature-model editor
     * @param node Node to be edited for cardinality
     */
    public void openCardinalityWindow(FMEditor editor, FMNode node) {
        final Window window = new Window();
        window.setAutoSize(true);
        window.setTitle("Cardinality");
        window.setShowMinimizeButton(false);
        window.setIsModal(true);
        window.setShowModalMask(true);
        window.centerInPage();
        window.addCloseClickHandler(e -> window.markForDestroy());
        DynamicForm form = new DynamicForm();
        form.setNumCols(3);
        form.setTitleSuffix("");
        form.setColWidths("70", "10", "70");
        form.setAutoFocus(true);
        IntegerRangeValidator minValidator = new IntegerRangeValidator();
        IntegerItem min = new IntegerItem();
        min.setBrowserInputType("tel");
        min.setKeyPressFilter("[0-9]");
        min.setShowTitle(false);
        min.setWidth("*");
        min.setAlign(Alignment.RIGHT);
        min.setRequired(true);
        min.setValidators(minValidator);
        IntegerItem max = new IntegerItem();
        max.setBrowserInputType("tel");
        max.setKeyPressFilter("[0-9]");
        max.setWidth("*");
        max.setTitle("<font size=\"5\">..</font>");
        ButtonItem okBtn = new ButtonItem("okBtn", "O K");
        okBtn.setAlign(Alignment.CENTER);
        okBtn.setColSpan(3);
        okBtn.setWidth("*");
        form.setItems(min, max, okBtn);
        window.addItem(form);
        window.show();
        if (node.getMin() != -1) {
            min.setValue(node.getMin());
        }
        if (node.getMax() != -1) {
            max.setValue(node.getMax());
        }
        okBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                int maxInt = -1;
                if (max.getValue() != null) {
                    maxInt = max.getValueAsInteger();
                }
                if (maxInt != -1) {
                    minValidator.setMax(max.getValueAsInteger());
                } else {
                    minValidator.setMax(Integer.MAX_VALUE);
                }

                if (!form.validate()) {
                    return;
                }

                setCardinality(editor, node, min.getValueAsInteger(), maxInt);
                window.markForDestroy();
            }
        });
        form.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.ENTER.equals(event.getKeyName())) {
                    okBtn.fireEvent(new ClickEvent(okBtn.getJsObj()));
                }
            }
        });
    }

    /**
     * The cardinality is deleted.
     * @param editor Main class of feature-model editor
     * @param node Node from which cardinality is removed
     */
    public void deleteCardinality(FMEditor editor, FMNode node) {
        SC.ask("Cardinality", "Are you sure you want to delete this cardinality ?", new BooleanCallback() {
            @Override
            public void execute(Boolean value) {
                if (value == null) {
                    return;
                }
                if (value) {
                    setCardinality(editor, node, -1, -1, 0, 0);
                }
            }
        });
    }

    /**
     * Get the default name of the node + n. <br>
     * n is numbered until there are no duplicate nodes.
     * @param states Node list to search
     * @param defaultName Node name to search
     * @return New node name
     */
    private String getNewName(List<? extends FMNode> states, String defaultName) {
        int counter = 0;
        String name = defaultName;
        while (hasSameName(name, states)) {
            counter++;
            name = defaultName + counter;
        }

        return name;
    }

    /**
     * Check if the same node name exists
     * @param name Node name to search
     * @param identifiables Node list to search
     * @return True if the same node name exists
     */
    private boolean hasSameName(String name, List<? extends FMNode> identifiables) {
        if (identifiables == null) {
            return false;
        }
        for (FMNode identifiable : identifiables) {
            if (name.equals(identifiable.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Displays a modal window for selecting the FM file to associate with the end node.
     * @param editor Main class of feature-model editor
     */
    protected void selectReferenceNode(FMEditor editor) {
        List<DrawRect> drawRects = editor.nodeManager.getSelectDrawRect();
        if (drawRects.size() > 1) {
            SC.warn("Please select one node.");
            return;
        }
        FMDrawNode drawNode = editor.getDrawRectMap().get(drawRects.get(0).hashCode());
        FMNode node = drawNode.getFmNode();
        if (node.getChildren().size() > 0) {
            SC.warn("Please select end node.");
            return;
        }

        final Window winModal = new Window();
        winModal.setTitle("Select Reference Node");
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

        ListGridField uuidField = new ListGridField("uuid", "");
        uuidField.setHidden(true);
        ListGridField idField = new ListGridField("id", "");
        idField.setHidden(true);
        ListGridField fileNameField = new ListGridField("fileName", "");
        fileNameField.setType(ListGridFieldType.TEXT);
        ListGridField fullPathField = new ListGridField("fullPath", "");
        fullPathField.setType(ListGridFieldType.TEXT);
        dependentFile.setFields(uuidField, idField, fileNameField, fullPathField);

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
                Record record = dependentFile.getSelectedRecord();
                if (record == null) {
                    event.cancel();
                    return;
                }
                long id = record.getAttributeAsLong("id");
                String uuid = record.getAttributeAsString("uuid");
                String fileName = record.getAttributeAsString("fileName");

                editor.getService().getFileContent(id, new AsyncCallback<byte[]>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(byte[] result) {
                        BinaryResourceImpl r = new BinaryResourceImpl();
                        ByteArrayInputStream bi = new ByteArrayInputStream(result);
                        EPackage.Registry.INSTANCE.put(FMPackage.eNS_URI, FMPackage.eINSTANCE);

                        FMRoot root = null;
                        try {
                            r.load(bi, null);
                            root = (FMRoot) r.getContents().get(0);
                        } catch (IOException e) {
                            SC.warn(e.getMessage());
                        }

                        CompoundCommand cmd = FMEditorCommandProvider.getInstance().setReferenceNode(drawNode.getFmNode(), id, root.getNode().getName(), fileName, uuid);
                        editor.getEditManager().execute(cmd.unwrap());
                        drawNode.getDrawRect().setTitle(drawNode.getFmNode().getName() + ":" + root.getNode().getName() + "\n(" + fileName + ")");
                        editor.setOpenFileId(id);
                    }
                });

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

        List<String> extensions = Arrays.asList(new String[] { Extension.FM.getValue() });
        createListGridData(editor.getService(), editor.getProjectId(), extensions, dependentFile);
        winModal.show();
        searchItem.focusInItem();
        winModal.getFooter().addMember(hlayout, 0);

        winModal.addResizedHandler(new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent event) {
                formlayout.setWidth100();
            }
        });
    }

    /**
     * Disassociate the FM file of the reference node.
     * @param editor Main class of feature-model editor
     * @param node reference node
     */
    protected void unselectReferenceNode(FMEditor editor, FMNode node) {
        CompoundCommand cmd = FMEditorCommandProvider.getInstance().setReferenceNode(node, 0, null, null, null);
        editor.getEditManager().execute(cmd.unwrap());
        editor.refresh();
    }

    /**
     * Create a ListGrid that displays the file path and file name.
     * @param editResourceService Asynchronous interface to get file information
     * @param projectId ID of the project
     * @param extensions File extension of acquisition target file
     * @param dependentFile Display destination of acquired file
     */
    private void createListGridData(EditResourceServiceAsync editResourceService, long projectId, List<String> extensions, ListGrid dependentFile) {
        editResourceService.getRefTargetFiles(projectId, extensions, new AsyncCallback<List<VMFile>>() {

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(List<VMFile> result) {
                result.sort(Comparator.comparing(VMFile::getName, String.CASE_INSENSITIVE_ORDER));

                ListGridRecord[] retData = new ListGridRecord[result.size()];
                Arrays.asList(dependentFile.getRecords()).forEach(record -> dependentFile.removeData(record));
                for (int i = 0; i < result.size(); i++) {
                    retData[i] = new ListGridRecord();
                    retData[i].setAttribute("fullPath", result.get(i).getFullPath());
                    retData[i].setAttribute("id", result.get(i).getId());
                    retData[i].setAttribute("fileName", result.get(i).getName() + "." + result.get(i).getExtensionStr());
                    retData[i].setAttribute("uuid", result.get(i).getUuid());
                    checkReferenceNodeCirculation(editResourceService, projectId, result.get(i).getUuid(), dependentFile, retData[i]);
                    dependentFile.addData(retData[i]);
                }
                dependentFile.sort();
                dependentFile.redraw();
                Scheduler.get().scheduleDeferred(() -> {
                    dependentFile.selectSingleRecord(0);
                    dependentFile.focus();
                });
            }
        });
    }

    /**
     * If an FM file is assigned to the reference node and it becomes a circular reference, the target record cannot be selected
     * from ListGrid.
     * @param editResourceService Asynchronous interface to get file information
     * @param projectId project id
     * @param uuid File UUID
     * @param dependentFile Display destination of acquired file
     * @param retData Controls whether this record can be selected.
     */
    private void checkReferenceNodeCirculation(EditResourceServiceAsync editResourceService, long projectId, String uuid, ListGrid dependentFile, ListGridRecord retData) {
        // Cannot select own file
        if (root.getId().equals(uuid)) {
            retData.setEnabled(false);
            dependentFile.redraw();
            return;
        }
        // You cannot select files that circulate.
        editResourceService.getFileContent(uuid, projectId, new AsyncCallback<byte[]>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(byte[] result) {
                BinaryResourceImpl r = new BinaryResourceImpl();
                ByteArrayInputStream bi = new ByteArrayInputStream(result);
                EPackage.Registry.INSTANCE.put(FMPackage.eNS_URI, FMPackage.eINSTANCE);
                FMRoot fmRoot = null;
                try {
                    r.load(bi, null);
                    fmRoot = (FMRoot) r.getContents().get(0);
                } catch (IOException e) {
                    SC.warn(e.getMessage());
                }
                fmRoot.getRefs().forEach(ref -> {
                    if (root.getId().equals(ref.getRefid())) {
                        retData.setEnabled(false);
                        dependentFile.redraw();
                        return;
                    } else {
                        checkReferenceNodeCirculation(editResourceService, projectId, ref.getRefid(), dependentFile, retData);
                    }
                });
            }
        });
    }

    /**
     * Get all selected nodes.
     * @param editor Main class of feature-model editor
     * @return Information about the selected node
     */
    protected Map<Integer, FMDrawNode> getSelectedDrawItems(FMEditor editor) {
        return editor.getDrawRectMap().entrySet().stream().filter(map -> getSelectFlag(map.getValue().getDrawRect())).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    }

    /**
     * Get the selection status of the target node.
     * @param drawRect Node to check the selection status
     * @return True if selected
     */
    protected boolean getSelectFlag(DrawRect drawRect) {
        if (drawRect.getKnobs() == null)
            return false;
        else
            return Arrays.asList(drawRect.getKnobs()).stream().filter(type -> type.equals(KnobType.RESIZE)).findFirst().isPresent();
    }
}
