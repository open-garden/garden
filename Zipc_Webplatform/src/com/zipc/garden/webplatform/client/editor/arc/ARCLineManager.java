package com.zipc.garden.webplatform.client.editor.arc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawLine;
import com.smartgwt.client.widgets.drawing.Point;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
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
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.model.fsm.FSMPackage;
import com.zipc.garden.webplatform.client.command.ARCEditorCommandProvider;
import com.zipc.garden.webplatform.client.command.EditorCommandProvider;
import com.zipc.garden.webplatform.client.editor.EditorDrawLine;
import com.zipc.garden.webplatform.client.editor.EditorLineUtil;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * This class manages the lines drawn in the architecture editor.
 */
public class ARCLineManager {

    /** DrawItem associated with the line to draw */
    protected DrawItem[] addLineDrawItems = new DrawItem[2];

    /** The type of line to draw */
    protected LineType addLineType;

    /** Holds the drawing line before resizing. */
    protected List<DrawLine> resizeDrawLine = new ArrayList<>();

    /** Holds the start and end coordinates of the drawing line before resizing. */
    protected Point[] resizePoints = new Point[2];

    /** Main class of architecture editor */
    private final ARCEditor editor;

    /**
     * constructor
     * @param editor Main class of architecture editor
     */
    public ARCLineManager(ARCEditor editor) {
        this.editor = editor;
    }

    /**
     * Executed when the connection destination of the transition line is specified.<br>
     * Performs the process of opening "Additional modal window of line" with the information of connection source and
     * connection destination and the line information of the newly created EMF model.
     */
    protected void onAdd() {
        Optional<ARCState> source = editor.stateManager.getKey(editor.getDrawItems(), addLineDrawItems[0]);
        Optional<ARCState> target = editor.stateManager.getKey(editor.getDrawItems(), addLineDrawItems[1]);
        if (!source.isPresent() || !target.isPresent()) {
            return;
        }

        ARCLine line = ARCFactory.eINSTANCE.createARCLine();
        line.setType(addLineType);
        List<double[]> mPoints = new ArrayList<double[]>();
        if (addLineType != null && addLineType.equals(LineType.MANHATTAN)) {
            mPoints = EditorLineUtil.getManhattanPoint(line, addLineDrawItems[0], addLineDrawItems[1], null, null);
        }
        addLineDrawItems = new DrawItem[2];
        addLine(source.get(), target.get(), 400, 400, mPoints, line);
    }

    /**
     * Get the transition line label text from the EMF model and create the label.
     * @param line EMF model of transition line
     * @param drawLine The line being drawn
     */
    protected void makeNewDrawLabel(ARCLine line, EditorDrawLine drawLine) {
        drawLine.getDrawLabel().erase();

        StringBuilder contents = new StringBuilder();
        if (line.getVariableName() != null && !"".equals(line.getVariableName())) {
            contents.append(line.getVariableName());
        }
        if ("".equals(contents.toString())) {
            return;
        }
        EditorLineUtil.drawNewLabel(editor.getDrawPane(), drawLine, contents.toString(), line.getX(), line.getY());
    }

    /**
     * Fired at the drag stop event of the DrawLine.<br>
     * Set the Item that connects the DrawLine and reflect it in the EMF model.
     * @param drawLines Resized DrawLine
     */
    protected void resizeDrawLine(List<DrawLine> drawLines) {
        DrawLine startLine = drawLines.get(0);
        DrawLine endLine = drawLines.get(drawLines.size() - 1);
        ARCLine line = getKey(editor.getEditorDrawLines(), startLine);

        CompoundCommand cmd = new CompoundCommand();
        Point oldStartPoint = editor.lineManager.resizePoints[0];
        if (startLine.getStartLeft() != oldStartPoint.getX() || startLine.getStartTop() != oldStartPoint.getY()) {
            Map<ARCState, DrawItem> mapDrawItem = editor.stateManager.getDrawItemIsBelowThePointer(startLine.getStartLeft(), startLine.getStartTop());
            if (mapDrawItem.size() > 0) {
                ARCState state = mapDrawItem.keySet().stream().findFirst().get();
                DrawItem drawItem = mapDrawItem.values().stream().findFirst().get();
                double[] anchor = EditorLineUtil.getCalculationResultOfAnchor(drawItem, startLine.getStartLeft(), startLine.getStartTop());

                cmd = ARCEditorCommandProvider.getInstance().resizeOutgoingLine(cmd, line, state, anchor[0], anchor[1]);

                if (line.getType() != null && line.getType().equals(LineType.MANHATTAN)) {
                    DrawItem target = editor.getDrawItems().get(line.getTarget());
                    List<double[]> mPoints = EditorLineUtil.getManhattanPoint(line, drawItem, target, anchor, null);
                    List<AbstractPoint> points = EditorLineUtil.getConnectionPoint(mPoints, editor);
                    cmd = EditorCommandProvider.getInstance().addPoint(cmd, line, points);
                }
                editor.manager.execute(cmd.unwrap());

                editor.getEditorDrawLines().get(line).getDrawItems()[0] = drawItem;
            }

        } else {
            Map<ARCState, DrawItem> mapDrawItem = editor.stateManager.getDrawItemIsBelowThePointer(endLine.getEndLeft(), endLine.getEndTop());
            if (mapDrawItem.size() > 0) {
                DrawItem drawItem = mapDrawItem.values().stream().findFirst().get();
                double[] anchor = EditorLineUtil.getCalculationResultOfAnchor(drawItem, endLine.getEndLeft(), endLine.getEndTop());
                List<double[]> mPoints = new ArrayList<double[]>();
                if (line.getType() != null && line.getType().equals(LineType.MANHATTAN)) {
                    DrawItem source = editor.getDrawItems().get(line.getSource());
                    mPoints = EditorLineUtil.getManhattanPoint(line, source, drawItem, null, anchor);
                }
                ARCState state = mapDrawItem.keySet().stream().findFirst().get();
                if (!line.getTarget().equals(state)) {
                    ARCLine newLine = ARCFactory.eINSTANCE.createARCLine();
                    newLine.setType(line.getType());
                    addLine(line.getSource(), state, 400, 400, mPoints, newLine, line);
                } else {
                    cmd = ARCEditorCommandProvider.getInstance().resizeIncomingLine(cmd, line, state, anchor[0], anchor[1]);

                    if (line.getType() != null && line.getType().equals(LineType.MANHATTAN)) {
                        List<AbstractPoint> points = EditorLineUtil.getConnectionPoint(mPoints, editor);
                        cmd = EditorCommandProvider.getInstance().addPoint(cmd, line, points);
                    }
                    editor.manager.execute(cmd.unwrap());

                    editor.getEditorDrawLines().get(line).getDrawItems()[1] = drawItem;
                }
            }
        }
        editor.refresh();

        if (line.getType() != null && line.getType().equals(LineType.MANHATTAN)) {

            Map<Integer, DrawLine> newLines = EditorLineUtil.reDesignManhattan(editor.getEditorDrawLines().get(line).getDrawLines(), editor.getDrawPane());
            newLines.forEach((i, drawLine) -> {
                EditorDrawLine editorDrawLine = editor.getEditorDrawLines().get(line);
                editorDrawLine.getDotLines().add(drawLine);
                editor.addManhattanLineDragStopEvent(editor, line, drawLine, i);
            });
        }
        EditorDrawLine editorDrawLine = editor.getEditorDrawLines().get(line);
        EditorLineUtil.selectDrawLine(editorDrawLine.getDrawLabel(), editorDrawLine.getDrawLines(), editorDrawLine.getDotLines(), true);
    }

    /**
     * Get all the lines connected to the argument DrawItem and return the EMF model of the line.
     * @param map Map that associates the drawn line with the EMF model line class
     * @param value DrawItem to search
     * @return List of Transition line of EMF model
     */
    protected List<ARCLine> getKey(Map<ARCLine, EditorDrawLine> map, DrawItem value) {
        Map<ARCLine, EditorDrawLine> lines = map.entrySet().stream().filter(entry -> {
            return value.equals(entry.getValue().getDrawItems()[0]) || value.equals(entry.getValue().getDrawItems()[1]);
        }).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        return new ArrayList<ARCLine>(lines.keySet());
    }

    /**
     * Gets the EMF model associated with the drawn line
     * @param map Map that associates the drawn line with the EMF model line class
     * @param value DrawLine to search
     * @return Transition line of EMF model
     */
    protected ARCLine getKey(Map<ARCLine, EditorDrawLine> map, DrawLine value) {
        Map<ARCLine, EditorDrawLine> lines = map.entrySet().stream().filter(drawLines -> {
            return drawLines.getValue().getDrawLines().stream().filter(drawLine -> value.equals(drawLine)).findFirst().isPresent();
        }).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        return lines.keySet().stream().findFirst().get();
    }

    /**
     * Get all selected lines.
     * @return selected lines
     */
    protected Map<ARCLine, EditorDrawLine> getSelectedDrawLines() {
        return editor.getEditorDrawLines().entrySet().stream().filter(entry -> {
            return entry.getValue().getDrawLines().stream().filter(drawLine -> {
                return EditorLineUtil.getSelectFlag(drawLine);
            }).findFirst().isPresent();
        }).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    }

    /**
     * Get the transition line based on the information of the connection source and connection destination.
     * @param source Source EMF model
     * @param target Target EMF model
     * @return Transition line of EMF model
     */
    protected Map<ARCLine, EditorDrawLine> getMap(ARCState source, ARCState target) {
        Map<ARCLine, EditorDrawLine> lines = editor.getEditorDrawLines().entrySet().stream().filter(entry -> {
            return source.equals(entry.getKey().getSource()) || target.equals(entry.getKey().getTarget());
        }).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        return lines;
    }

    /**
     * Select all the drawn Lines.
     * @param selectFlag If true, select
     */
    protected void selectDrawLineAll(boolean selectFlag) {
        editor.getEditorDrawLines().entrySet().forEach(drawLines -> EditorLineUtil.selectDrawLine(drawLines.getValue().getDrawLabel(), drawLines.getValue().getDrawLines(),
                drawLines.getValue().getDotLines(), selectFlag));
    }

    /**
     * Redraws the transition line connected to the argument DrawItem.
     * @param drawItem DrawItem to reconnect
     * @param createFlag True to delete the transition line and recreate it
     */
    protected void redrawLine(DrawItem drawItem, boolean createFlag) {
        List<ARCLine> lines = (List<ARCLine>) getKey(editor.getEditorDrawLines(), drawItem);
        lines.forEach(line -> {
            if (line.getSource() == null || line.getTarget() == null) {
                return;
            }
            boolean selfLineFlag = line.getSource().equals(line.getTarget());
            if (createFlag) {
                removeDrawLines(line);
                editor.makeNewDrawLine(line, selfLineFlag);
            }
            DrawItem source = editor.getDrawItems().get(line.getSource());
            DrawItem target = editor.getDrawItems().get(line.getTarget());
            EditorDrawLine editorDrawLine = editor.getEditorDrawLines().get(line);
            Point mPoint = editorDrawLine.getMiddlePoint();
            if (line.getType() != null && line.getType().equals(LineType.SIMPLE)) {
                if (selfLineFlag) {
                    editorDrawLine.repositionSelfLine(editorDrawLine.getDrawLines(), line.getSourceAnchorX(), line.getSourceAnchorY(), line.getTargetAnchorX(),
                            line.getTargetAnchorY(), editor.getSnapGap());
                    editorDrawLine.repositionSelfLine(editorDrawLine.getDrawLinesTransparent(), line.getSourceAnchorX(), line.getSourceAnchorY(), line.getTargetAnchorX(),
                            line.getTargetAnchorY(), editor.getSnapGap());
                } else {
                    Point point = EditorLineUtil.lineIntersection(line, source, target, source, false);
                    Point point2 = EditorLineUtil.lineIntersection(line, source, target, target, false);
                    DrawLine drawLine = editor.getEditorDrawLines().get(line).getDrawLines().stream().findFirst().get();
                    drawLine.setStartPoint(point);
                    drawLine.setEndPoint(point2);
                    DrawLine drawLineT = editor.getEditorDrawLines().get(line).getDrawLinesTransparent().stream().findFirst().get();
                    drawLineT.setStartPoint(point);
                    drawLineT.setEndPoint(point2);
                }
                editorDrawLine.getDrawLabel().moveTo(line.getX() + mPoint.getX(), line.getY() + mPoint.getY());
            } else {
                if (!createFlag) {
                    List<double[]> mPoints = EditorLineUtil.getManhattanPoint(line, source, target, null, null);
                    List<DrawLine> drawlines = editor.getEditorDrawLines().get(line).getDrawLines();
                    EditorLineUtil.redrawManhattan(drawlines, mPoints, editorDrawLine);
                    editor.getEditorDrawLines().get(line).getDotLines().forEach(dotLine -> dotLine.erase());
                    if (!(line.getVariableName() == null && line.getVariableType() == null)) {
                        if ("".equals(line.getVariableName()) && "".equals(line.getVariableType())) {
                            editorDrawLine.getDrawLabel().erase();
                        } else {
                            editorDrawLine.getDrawLabel().setDrawPane(editor.getDrawPane());
                            editorDrawLine.getDrawLabel().draw();
                            if (editorDrawLine.getDrawLabel().getContents() != null) {
                                editorDrawLine.getDrawLabel().moveBy(line.getX() + mPoint.getX() - editorDrawLine.getDrawLabel().getLeft(),
                                        line.getY() + mPoint.getY() - editorDrawLine.getDrawLabel().getTop());
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Perform the process to open "Add line modal window".<br>
     * Executed when drawing a new line.
     * @param source EMF model of connection source
     * @param target EMF model of connection target
     * @param width Modal window width
     * @param height Modal window height
     * @param manhattanPoints Bending position in Manhattan
     * @param newLine EMF model line to be created
     */
    public void addLine(ARCState source, ARCState target, int width, int height, List<double[]> manhattanPoints, ARCLine newLine) {
        addLine(source, target, width, height, manhattanPoints, newLine, null);
    }

    /**
     * Perform the process to open "Add line modal window".<br>
     * @param source EMF model of connection source
     * @param target EMF model of connection target
     * @param width Modal window width
     * @param height Modal window height
     * @param mPoints Bending position in Manhattan
     * @param newLine EMF model line to be created
     * @param oldLine EMF model line to remove. Null to create a new one.
     */
    public void addLine(ARCState source, ARCState target, int width, int height, List<double[]> mPoints, ARCLine newLine, ARCLine oldLine) {
        final Window winModal = new Window();
        winModal.setTitle("Add Line");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.setShowFooter(true);
        winModal.setShowResizer(true);
        winModal.setKeepInParentRect(true);
        winModal.setAutoCenter(true);
        winModal.setBackgroundColor("white");
        winModal.setCanDragResize(true);
        winModal.setWidth(width);
        winModal.setHeight(height);
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
        ListGridField nameField = new ListGridField("name", "");
        nameField.setType(ListGridFieldType.TEXT);
        ListGridField typeField = new ListGridField("type", "");
        typeField.setType(ListGridFieldType.TEXT);
        dependentFile.setFields(idfield, nameField, typeField);

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
                criteria.addCriteria("name", searchVal);
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
                String name = record.getAttributeAsString("name");
                String type = record.getAttributeAsString("type");

                CompoundCommand cmd = ARCEditorCommandProvider.getInstance().addLine(newLine, editor.getARCRoot(), source, target, oldLine, name, type);
                if (newLine.getType() != null && newLine.getType().equals(LineType.MANHATTAN)) {
                    List<AbstractPoint> points = EditorLineUtil.getConnectionPoint(mPoints, editor);
                    List<double[]> pos = EditorLineUtil.getAnchor(mPoints, source.getLeft(), source.getWidth(), source.getTop(), source.getHeight(), target.getLeft(),
                            target.getWidth(), target.getTop(), target.getHeight());
                    cmd = EditorCommandProvider.getInstance().addPoint(cmd, newLine, points);
                    cmd = ARCEditorCommandProvider.getInstance().resizeOutgoingLine(cmd, newLine, source, pos.get(0)[0], pos.get(0)[1]);
                    cmd = ARCEditorCommandProvider.getInstance().resizeIncomingLine(cmd, newLine, target, pos.get(1)[0], pos.get(1)[1]);
                }

                editor.manager.execute(cmd.unwrap());
                editor.refresh();

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
        createListGridData(editor.getService(), target.getFileId(), dependentFile);
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
     * Create a ListGrid by reading the variable list registered with the FSM editor.
     * @param editResourceService Asynchronous interface
     * @param fileId fsm file id
     * @param dependentFile List Grid
     */
    private void createListGridData(EditResourceServiceAsync editResourceService, String fileId, ListGrid dependentFile) {
        editResourceService.getVMFile(fileId, editor.getProjectId(), new AsyncCallback<VMFile>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(VMFile result) {
                if (result == null) {
                    return;
                }
                editResourceService.getFileContent(result.getId(), new AsyncCallback<byte[]>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(byte[] result) {
                        BinaryResourceImpl r = new BinaryResourceImpl();
                        ByteArrayInputStream bi = new ByteArrayInputStream(result);
                        EPackage.Registry.INSTANCE.put(FSMPackage.eNS_URI, FSMPackage.eINSTANCE);

                        FSMDStateMachine machine = null;
                        try {
                            r.load(bi, null);
                            machine = (FSMDStateMachine) r.getContents().get(0);
                        } catch (IOException e) {
                            SC.warn(e.getMessage());
                        }

                        int[] index = { 0 };
                        ListGridRecord[] retData = new ListGridRecord[machine.getVariables().size()];
                        machine.getVariables().forEach(variable -> {
                            retData[index[0]] = new ListGridRecord();
                            retData[index[0]].setAttribute("name", variable.getName());
                            retData[index[0]].setAttribute("type", variable.getType());
                            index[0]++;

                            DataSource ds = DependentFileDS.getInstance();
                            ds.setTestData(retData);
                            dependentFile.setDataSource(ds);
                            dependentFile.sort();
                            dependentFile.fetchData();
                        });

                        Scheduler.get().scheduleDeferred(() -> {
                            dependentFile.selectSingleRecord(0);
                            dependentFile.focus();
                        });
                    }
                });
            }
        });
    }

    /**
     * Data source of variable list displayed on Add screen of transition line
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

            DataSourceTextField dsName = new DataSourceTextField("name", "Name");
            DataSourceTextField dsType = new DataSourceTextField("type", "Type");
            setFields(dsName, dsType);

            setClientOnly(true);
        }
    }

    /**
     * The transition line type is changed and redrawn.
     * @param lineType Type of transition line after change
     */
    protected void redrawChangedTransitionType(LineType lineType) {
        CompoundCommand cmd = new CompoundCommand();
        Map<ARCLine, EditorDrawLine> lines = getSelectedDrawLines();
        for (ARCLine line : lines.keySet()) {
            if (line.getType() != null && !line.getType().equals(lineType)) {
                cmd = changeTransitionType(cmd, line, lineType);
            }
        }
        editor.manager.execute(cmd);
        lines.entrySet().forEach(line -> {
            removeDrawLines(line.getKey());
        });
        editor.refresh();
    }

    /**
     * A command is created to change the type of transition line.
     * @param cmd command
     * @param line Transition line to be changed
     * @param lineType Type of transition line after change
     * @return Created command
     */
    protected CompoundCommand changeTransitionType(CompoundCommand cmd, ARCLine line, LineType lineType) {
        double[] sAnchor = { 0.5, 0.5 };
        double[] tAnchor = { 0.5, 0.5 };
        if (lineType.equals(LineType.MANHATTAN)) {
            DrawItem source = editor.getDrawItems().get(line.getSource());
            DrawItem target = editor.getDrawItems().get(line.getTarget());
            List<double[]> mPoints = EditorLineUtil.getManhattanPoint(line, source, target, null, null);
            List<AbstractPoint> points = EditorLineUtil.getConnectionPoint(mPoints, editor);
            sAnchor = EditorLineUtil.getCalculationResultOfAnchor(source, (int) mPoints.get(0)[0], (int) mPoints.get(0)[1]);
            tAnchor = EditorLineUtil.getCalculationResultOfAnchor(target, (int) mPoints.get(mPoints.size() - 1)[0], (int) mPoints.get(mPoints.size() - 1)[1]);
            cmd = EditorCommandProvider.getInstance().addPoint(cmd, line, points);
        }
        cmd = EditorCommandProvider.getInstance().changeLine(cmd, line, lineType);
        cmd = ARCEditorCommandProvider.getInstance().resizeOutgoingLine(cmd, line, line.getSource(), sAnchor[0], sAnchor[1]);
        cmd = ARCEditorCommandProvider.getInstance().resizeIncomingLine(cmd, line, line.getTarget(), tAnchor[0], tAnchor[1]);
        return cmd;
    }

    /**
     * Checks the selected line type and returns True if it matches that of the argument.
     * @param lineType Line type to check
     * @return Check result
     */
    protected boolean checkSelectedLineRouting(LineType lineType) {
        Map<ARCLine, EditorDrawLine> lines = getSelectedDrawLines();
        for (ARCLine line : lines.keySet()) {
            if (line.getType() != lineType) {
                return false;
            }
        }
        return true;
    }

    /**
     * The edited Manhattan line is confirmed and redrawn.
     * @param line EMF model related to Manhattan lines
     * @param newLine Edited manhattan line
     * @param ind Manhattan line index numbers
     */
    protected void reDrawEditedManhattan(ARCLine line, DrawLine newLine, int ind) {
        CompoundCommand cmd = new CompoundCommand();
        cmd = EditorCommandProvider.getInstance().addPoint(cmd, line, EditorLineUtil.getNewManhattanPoint(line, newLine, ind));
        editor.manager.execute(cmd);
        removeDrawLines(line);
        editor.makeNewDrawLine(line, false);
        List<DrawLine> drawLines = editor.getEditorDrawLines().get(line).getDrawLines();
        EditorLineUtil.selectDrawLine(editor.getEditorDrawLines().get(line).getDrawLabel(), drawLines, editor.getEditorDrawLines().get(line).getDotLines(), true);
        EditorDrawLine editorDrawLine = editor.getEditorDrawLines().get(line);

        Map<Integer, DrawLine> newLines = EditorLineUtil.reDesignManhattan(drawLines, editor.getDrawPane());
        newLines.forEach((i, drawLine) -> {
            editorDrawLine.getDotLines().add(drawLine);
            editor.addManhattanLineDragStopEvent(editor, line, drawLine, i);
        });
    }

    /**
     * Deletes the drawn line that matches the argument.
     * @param line EMF model related to Manhattan lines
     */
    protected void removeDrawLines(ARCLine line) {
        editor.getEditorDrawLines().get(line).getDrawLinesTransparent().forEach(l -> l.erase());
        editor.getEditorDrawLines().get(line).getDrawLines().forEach(l -> l.erase());
        editor.getEditorDrawLines().get(line).getDotLines().forEach(dotLine -> dotLine.erase());
        editor.getEditorDrawLines().get(line).getDrawLabel().erase();
        editor.getEditorDrawLines().remove(line);
    }
}
