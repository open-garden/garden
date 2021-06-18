package com.zipc.garden.webplatform.client.editor.fsm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.CompoundCommand;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawLabel;
import com.smartgwt.client.widgets.drawing.DrawLine;
import com.smartgwt.client.widgets.drawing.Point;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.LineType;
import com.zipc.garden.model.fsm.FSMDEvent;
import com.zipc.garden.model.fsm.FSMDPseudoStateType;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.model.fsm.FSMDTransition;
import com.zipc.garden.model.fsm.FSMFactory;
import com.zipc.garden.webplatform.client.command.EditorCommandProvider;
import com.zipc.garden.webplatform.client.command.FSMEditorCommandProvider;
import com.zipc.garden.webplatform.client.editor.EditorDrawLine;
import com.zipc.garden.webplatform.client.editor.EditorLineUtil;

/**
 * Class that manages the processing related to DrawLine created by the behavior model editor.
 */
public class FSMTransitionManager {

    /** EMF root model of FSM editor. */
    private FSMDStateMachine machine;

    /** Variable that holds information of connection source and connection destination */
    protected DrawItem[] addLineDrawItems = new DrawItem[2];

    /** Line type of transition line */
    protected LineType addLineType;

    /** Variable that holds the resized transition line */
    protected List<DrawLine> resizeDrawLine = new ArrayList<>();

    /** Variable that holds the coordinates of the transition line before resizing */
    protected Point[] resizePoints = new Point[2];

    /** Default event name for transition lines */
    private final String DEFAULT_EVENT_NAME = "Event";

    /**
     * Set the field variable of FSM editor.
     * @param machine EMF root model of FSM editor.
     */
    protected void setFieldData(FSMDStateMachine machine) {
        this.machine = machine;
    }

    /**
     * The transition line information is reflected in the EMF model and the canvas is refreshed.
     * @param editor Main class of behavior-model editor
     */
    protected void onAdd(FSMEditor editor) {
        Optional<FSMDState> source = editor.stateManager.getKey(editor.getDrawItems(), addLineDrawItems[0]);
        Optional<FSMDState> target = editor.stateManager.getKey(editor.getDrawItems(), addLineDrawItems[1]);
        if (!source.isPresent() || !target.isPresent()) {
            return;
        }

        FSMDTransition transition = FSMFactory.eINSTANCE.createFSMDTransition();
        transition.setType(addLineType);

        AtomicInteger i = new AtomicInteger();
        Optional<FSMDEvent> optEvent = Optional.empty();
        StringBuilder newEventName = new StringBuilder();
        do {
            newEventName.setLength(0);
            newEventName.append(DEFAULT_EVENT_NAME);
            newEventName.append(i.incrementAndGet());
            optEvent = machine.getFmsdevent().stream().filter(event -> newEventName.toString().equals(event.getName())).findFirst();
        } while (optEvent.isPresent());
        transition.setEvent(newEventName.toString());

        CompoundCommand cmd = FSMEditorCommandProvider.getInstance().addTransition(transition, machine, source.get(), target.get());

        if (addLineType != null && addLineType.equals(LineType.MANHATTAN)) {

            List<double[]> mPoints = EditorLineUtil.getManhattanPoint(transition, addLineDrawItems[0], addLineDrawItems[1], null, null);
            List<AbstractPoint> points = EditorLineUtil.getConnectionPoint(mPoints, editor);
            List<double[]> pos = EditorLineUtil.getAnchor(mPoints, source.get().getLeft(), source.get().getWidth(), source.get().getTop(), source.get().getHeight(),
                    target.get().getLeft(), target.get().getWidth(), target.get().getTop(), target.get().getHeight());
            cmd = EditorCommandProvider.getInstance().addPoint(cmd, transition, points);
            cmd = FSMEditorCommandProvider.getInstance().resizeOutgoingTransition(cmd, transition, source.get(), pos.get(0)[0], pos.get(0)[1]);
            cmd = FSMEditorCommandProvider.getInstance().resizeIncomingTransition(cmd, transition, target.get(), pos.get(1)[0], pos.get(1)[1]);
        }
        addLineDrawItems = new DrawItem[2];
        editor.manager.execute(cmd.unwrap());
        editor.refresh();
    }

    /**
     * Display a modal window to edit the transition line information. <br>
     * If you edit, it will be reflected in the EMF model and the canvas will be updated.
     * @param editor Main class of behavior-model editor
     * @param drawLine The transition line to be edited
     */
    protected void onEditTransition(FSMEditor editor, DrawLine drawLine) {

        FSMDTransition trans = getKey(editor.getEditorDrawLines(), drawLine);
        Point mPoint = editor.getEditorDrawLines().get(trans).getMiddlePoint();

        final Window winModal = new Window();
        winModal.setTop(editor.getDrawPane().getPageTop() + mPoint.getY());
        winModal.setLeft(editor.getDrawPane().getPageLeft() + mPoint.getX());
        winModal.setHeight(220);
        winModal.setWidth(310);
        winModal.setTitle("Edit Transition");
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

        DynamicForm form = new DynamicForm();
        form.setHeight100();
        form.setWidth100();
        form.setPadding(5);
        form.setNumCols(5);
        form.setColWidths(70, 70, 5, 70, 70);
        form.setLayoutAlign(VerticalAlignment.BOTTOM);
        form.setAutoFocus(true);

        TextItem priorityText = new TextItem();
        priorityText.setBrowserInputType("tel");
        priorityText.setKeyPressFilter("[0-9]");
        priorityText.setTitle("Priority");
        priorityText.setValue(trans.getPriority() > 0 ? trans.getPriority() : "");
        priorityText.setColSpan(4);

        TextItem triggerText = new TextItem();
        triggerText.setTitle("Trigger");
        triggerText.setValue(trans.getTrigger());
        triggerText.setColSpan(4);

        TextItem eventText = new TextItem();
        eventText.setTitle("Event");
        eventText.setValue(trans.getEvent());
        eventText.setColSpan(4);

        TextItem conditionText = new TextItem();
        conditionText.setTitle("Condition");
        conditionText.setValue(trans.getCondition());
        conditionText.setColSpan(4);

        TextItem actionText = new TextItem();
        actionText.setTitle("Action");
        actionText.setValue(trans.getAction());
        actionText.setColSpan(4);

        SubmitItem okButton = new SubmitItem();
        okButton.setTitle("OK");
        okButton.setWidth(80);
        okButton.setColSpan(2);
        okButton.setAlign(Alignment.RIGHT);
        okButton.setStartRow(false);
        okButton.setEndRow(false);
        okButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                event.cancel();
                int priority = 0;
                if (priorityText.getValue() != null && !"".equals(priorityText.getValue().toString())) {
                    priority = Integer.parseInt(priorityText.getValue().toString());
                }
                triggerText.setValue(triggerText.getValue() == null ? "" : triggerText.getValue());
                eventText.setValue(eventText.getValue() == null ? "" : eventText.getValue());
                conditionText.setValue(conditionText.getValue() == null ? "" : conditionText.getValue());
                actionText.setValue(actionText.getValue() == null ? "" : actionText.getValue());

                CompoundCommand cmd = FSMEditorCommandProvider.getInstance().editTransitionLabel(editor.getFSMDStateMachine(), trans, priority, triggerText.getValue().toString(),
                        eventText.getValue().toString(), conditionText.getValue().toString(), actionText.getValue().toString());
                editor.manager.execute(cmd.unwrap());
                if (trans.getType() != null && trans.getType().equals(LineType.MANHATTAN)) {
                    EditorDrawLine editorDrawLine = editor.getEditorDrawLines().get(trans);
                    makeNewDrawLabel(editor, trans, editorDrawLine);
                    DrawLabel drawLabel = editorDrawLine.getDrawLabel();
                    List<DrawLine> listDrawLine = editorDrawLine.getDrawLines();
                    List<DrawLine> listDrawLineTransparent = editorDrawLine.getDrawLinesTransparent();
                    editor.addMouseEvent(drawLabel, listDrawLine, listDrawLineTransparent);
                    editor.addDragResizeEvent(listDrawLine);
                    editor.addRightClickEvent(drawLabel, listDrawLine, editorDrawLine.getDotLines(), listDrawLineTransparent);
                    editor.addLeftClickEvent(drawLabel, listDrawLine, editorDrawLine.getDotLines(), listDrawLineTransparent);
                    editor.addDragStopEvent(trans, editorDrawLine);
                }
                editor.refresh();
                winModal.markForDestroy();
            }
        });

        form.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.ENTER.equals(event.getKeyName())) {
                    okButton.fireEvent(new com.smartgwt.client.widgets.form.fields.events.ClickEvent(okButton.getJsObj()));
                }
            }
        });

        ButtonItem cancelButton = new ButtonItem();
        cancelButton.setTitle("Cancel");
        cancelButton.setWidth(80);
        cancelButton.setColSpan(2);
        cancelButton.setStartRow(false);
        cancelButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                event.cancel();
                winModal.markForDestroy();
            }
        });
        SpacerItem space = new SpacerItem();
        space.setWidth(5);
        form.setFields(priorityText, triggerText, eventText, conditionText, actionText, okButton, space, cancelButton);
        winModal.addItem(form);
        winModal.show();
    }

    /**
     * The edited contents of the transition line are cleared and reflected in the EMF model.
     * @param editor Main class of behavior-model editor
     * @param drawLine Transition line that clears the edited contents
     */
    protected void onClearTransition(FSMEditor editor, DrawLine drawLine) {
        FSMDTransition trans = getKey(editor.getEditorDrawLines(), drawLine);
        CompoundCommand cmd = FSMEditorCommandProvider.getInstance().editTransitionLabel(editor.getFSMDStateMachine(), trans, 0, "", "", "", "");
        editor.manager.execute(cmd.unwrap());
        editor.refresh();
    }

    /**
     * Acquires the transition line information summarized in one line.
     * @param transition transition line information
     * @return Label display data
     */
    private String createLabelContents(FSMDTransition transition) {
        StringBuilder contents = new StringBuilder();
        if (transition.getPriority() > 0) {
            contents.append(transition.getPriority()).append(".");
        }
        if (transition.getTrigger() != null && !"".equals(transition.getTrigger())) {
            contents.append("[").append(transition.getTrigger()).append("]");
        }
        if (transition.getEvent() != null && !"".equals(transition.getEvent())) {
            contents.append(transition.getEvent());
        }
        if (transition.getCondition() != null && !"".equals(transition.getCondition())) {
            contents.append("[").append(transition.getCondition()).append("]");
        }
        if (transition.getAction() != null && !"".equals(transition.getAction())) {
            contents.append("/").append(transition.getAction());
        }

        return contents.toString();
    }

    /**
     * Create DrawLabel based on the information of transition line.
     * @param editor Main class of behavior-model editor
     * @param transition Transition line information
     * @param drawLine Class with draw line and label association
     */
    protected void makeNewDrawLabel(FSMEditor editor, FSMDTransition transition, EditorDrawLine drawLine) {
        drawLine.getDrawLabel().erase();

        String contents = createLabelContents(transition);
        if ("".equals(contents.toString())) {
            return;
        }
        EditorLineUtil.drawNewLabel(editor.getDrawPane(), drawLine, contents, transition.getX(), transition.getY());
    }

    /**
     * The size of the drawing line is changed and reflected in the EMF model.
     * @param editor Main class of behavior-model editor
     * @param drawLines line to resize
     */
    protected void resizeDrawLine(FSMEditor editor, List<DrawLine> drawLines) {
        DrawLine startLine = drawLines.get(0);
        DrawLine endLine = drawLines.get(drawLines.size() - 1);
        FSMDTransition trans = getKey(editor.getEditorDrawLines(), startLine);

        CompoundCommand cmd = new CompoundCommand();
        Point oldStartPoint = editor.transManager.resizePoints[0];
        DrawItem source = editor.getDrawItems().get(trans.getSource());
        DrawItem target = editor.getDrawItems().get(trans.getTarget());
        if (startLine.getStartLeft() != oldStartPoint.getX() || startLine.getStartTop() != oldStartPoint.getY()) {
            Map<FSMDState, DrawItem> mapDrawItem = editor.stateManager.getDrawItemIsBelowThePointer(editor, startLine.getStartLeft(), startLine.getStartTop());
            if (mapDrawItem.size() > 0) {
                FSMDState state = mapDrawItem.keySet().stream().findFirst().get();
                DrawItem drawItem = mapDrawItem.values().stream().findFirst().get();
                double[] anchor = EditorLineUtil.getCalculationResultOfAnchor(drawItem, startLine.getStartLeft(), startLine.getStartTop());
                cmd = FSMEditorCommandProvider.getInstance().resizeOutgoingTransition(cmd, trans, state, anchor[0], anchor[1]);

                if (trans.getType() != null && trans.getType().equals(LineType.MANHATTAN)) {
                    List<double[]> mPoints = EditorLineUtil.getManhattanPoint(trans, drawItem, target, anchor, null);
                    List<AbstractPoint> points = EditorLineUtil.getConnectionPoint(mPoints, editor);
                    cmd = EditorCommandProvider.getInstance().addPoint(cmd, trans, points);
                }
                editor.manager.execute(cmd.unwrap());

                editor.getEditorDrawLines().get(trans).getDrawItems()[0] = drawItem;
            }

        } else {
            Map<FSMDState, DrawItem> mapDrawItem = editor.stateManager.getDrawItemIsBelowThePointer(editor, endLine.getEndLeft(), endLine.getEndTop());
            if (mapDrawItem.size() > 0) {
                FSMDState state = mapDrawItem.keySet().stream().findFirst().get();
                DrawItem drawItem = mapDrawItem.values().stream().findFirst().get();
                double[] anchor = EditorLineUtil.getCalculationResultOfAnchor(drawItem, endLine.getEndLeft(), endLine.getEndTop());
                cmd = FSMEditorCommandProvider.getInstance().resizeIncomingTransition(cmd, trans, state, anchor[0], anchor[1]);

                if (trans.getType() != null && trans.getType().equals(LineType.MANHATTAN)) {
                    List<double[]> mPoints = EditorLineUtil.getManhattanPoint(trans, source, drawItem, null, anchor);
                    List<AbstractPoint> points = EditorLineUtil.getConnectionPoint(mPoints, editor);
                    cmd = EditorCommandProvider.getInstance().addPoint(cmd, trans, points);
                }
                editor.manager.execute(cmd.unwrap());

                editor.getEditorDrawLines().get(trans).getDrawItems()[1] = drawItem;
            }
        }
        removeDrawLines(editor, trans);

        if (trans.getType() != null && trans.getType().equals(LineType.SIMPLE)) {
            if (trans.getSource().equals(trans.getTarget())) {
                editor.makeNewDrawLine(trans, true);
            } else {
                editor.makeNewDrawLine(trans, false);
                startLine.setStartPoint(EditorLineUtil.lineIntersection(trans, source, target, source, FSMDPseudoStateType.CHOICE.equals(trans.getSource().getType())));
                endLine.setEndPoint(EditorLineUtil.lineIntersection(trans, source, target, target, FSMDPseudoStateType.CHOICE.equals(trans.getTarget().getType())));
            }
        } else {
            editor.makeNewDrawLine(trans, false);

            Map<Integer, DrawLine> newLines = EditorLineUtil.reDesignManhattan(editor.getEditorDrawLines().get(trans).getDrawLines(), editor.getDrawPane());
            newLines.forEach((i, drawLine) -> {
                EditorDrawLine editorDrawLine = editor.getEditorDrawLines().get(trans);
                editorDrawLine.getDotLines().add(drawLine);
                editor.addManhattanLineDragStopEvent(editor, trans, drawLine, i);
            });
        }
        EditorDrawLine editorDrawLine = editor.getEditorDrawLines().get(trans);
        EditorLineUtil.selectDrawLine(editorDrawLine.getDrawLabel(), editorDrawLine.getDrawLines(), editorDrawLine.getDotLines(), true);
    }

    /**
     * Get all transition lines connected to a specific DrawItem
     * @param map Map that associates DrawLine and EMF model
     * @param value DrawItem to search
     * @return Transition line connected to DrawItem
     */
    protected List<FSMDTransition> getKey(Map<FSMDTransition, EditorDrawLine> map, DrawItem value) {
        Map<FSMDTransition, EditorDrawLine> trans = map.entrySet().stream().filter(entry -> {
            return value.equals(entry.getValue().getDrawItems()[0]) || value.equals(entry.getValue().getDrawItems()[1]);
        }).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        return new ArrayList<FSMDTransition>(trans.keySet());
    }

    /**
     * Get the transition line model that manages a specific DrawLine
     * @param map Map that associates DrawLine and EMF model
     * @param value DrawLine to search
     * @return EMF model of transition line
     */
    protected FSMDTransition getKey(Map<FSMDTransition, EditorDrawLine> map, DrawLine value) {
        Map<FSMDTransition, EditorDrawLine> trans = map.entrySet().stream().filter(drawLines -> {
            return drawLines.getValue().getDrawLines().stream().filter(drawLine -> value.equals(drawLine)).findFirst().isPresent();
        }).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        return trans.keySet().stream().findFirst().get();
    }

    /**
     * Get all selected transition lines.
     * @param editor Main class of behavior-model editor
     * @return Map that associates DrawLine and EMF model
     */
    protected Map<FSMDTransition, EditorDrawLine> getSelectedDrawLines(FSMEditor editor) {
        return editor.getEditorDrawLines().entrySet().stream().filter(entry -> {
            return entry.getValue().getDrawLines().stream().filter(drawLine -> {
                return EditorLineUtil.getSelectFlag(drawLine);
            }).findFirst().isPresent();
        }).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    }

    /**
     * Controls the selection state of all transition lines.
     * @param editor Main class of behavior-model editor
     * @param selectFlag Selection is True, Deselection is False
     */
    protected void selectDrawLineAll(FSMEditor editor, boolean selectFlag) {
        editor.getEditorDrawLines().entrySet().forEach(drawLines -> EditorLineUtil.selectDrawLine(drawLines.getValue().getDrawLabel(), drawLines.getValue().getDrawLines(),
                drawLines.getValue().getDotLines(), selectFlag));
    }

    /**
     * Reconnect the transition line to the specified DrawItem.
     * @param editor Main class of behavior-model editor
     * @param drawItem The resized or repositioned DrawItem
     * @param isRefresh True if executed in refresh process
     */
    protected void redrawLine(FSMEditor editor, DrawItem drawItem, boolean isRefresh) {
        List<FSMDTransition> transitions = getKey(editor.getEditorDrawLines(), drawItem);
        transitions.forEach(trans -> {
            if (trans.getSource() == null || trans.getTarget() == null) {
                return;
            }
            DrawItem source = editor.getDrawItems().get(trans.getSource());
            DrawItem target = editor.getDrawItems().get(trans.getTarget());
            if (trans.getType() != null && trans.getType().equals(LineType.SIMPLE)) {
                removeDrawLines(editor, trans);
                if (trans.getSource().equals(trans.getTarget())) {
                    editor.makeNewDrawLine(trans, true);
                } else {
                    editor.makeNewDrawLine(trans, false);
                    editor.getEditorDrawLines().get(trans).getDrawLines().forEach(drawLine -> {

                        Point point1 = EditorLineUtil.lineIntersection(trans, source, target, source, FSMDPseudoStateType.CHOICE.equals(trans.getSource().getType()));
                        drawLine.setStartPoint(point1);
                        Point point2 = EditorLineUtil.lineIntersection(trans, source, target, target, FSMDPseudoStateType.CHOICE.equals(trans.getTarget().getType()));
                        drawLine.setEndPoint(point2);
                    });
                }
            } else {
                if (isRefresh) {
                    editor.getEditorDrawLines().get(trans).getDotLines().forEach(dotLine -> dotLine.erase());
                    removeDrawLines(editor, trans);
                    editor.makeNewDrawLine(trans, false);
                } else {
                    EditorDrawLine editorDrawLine = editor.getEditorDrawLines().get(trans);
                    List<double[]> mPoints = EditorLineUtil.getManhattanPoint(trans, source, target, null, null);
                    List<DrawLine> lines = editor.getEditorDrawLines().get(trans).getDrawLines();
                    EditorLineUtil.redrawManhattan(lines, mPoints, editorDrawLine);
                    editor.getEditorDrawLines().get(trans).getDotLines().forEach(dotLine -> dotLine.erase());
                    if (!(trans.getPriority() == 0 && trans.getEvent() == null && trans.getCondition() == null && trans.getAction() == null)) {
                        if (trans.getPriority() == 0 && "".equals(trans.getEvent()) && "".equals(trans.getCondition()) && "".equals(trans.getAction())) {
                            editorDrawLine.getDrawLabel().erase();
                        } else {
                            editorDrawLine.getDrawLabel().setDrawPane(editor.getDrawPane());
                            editorDrawLine.getDrawLabel().draw();
                            if (editorDrawLine.getDrawLabel().getContents() != null) {
                                Point mPoint = editorDrawLine.getMiddlePoint();
                                editorDrawLine.getDrawLabel().moveBy(trans.getX() + mPoint.getX() - editorDrawLine.getDrawLabel().getLeft(),
                                        trans.getY() + mPoint.getY() - editorDrawLine.getDrawLabel().getTop());
                                editorDrawLine.getDrawLabel().setContents(createLabelContents(trans));
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Change the transition line type and refresh the canvas.
     * @param editor Main class of behavior-model editor
     * @param lineType transition line type
     */
    protected void redrawChangedTransitionType(FSMEditor editor, LineType lineType) {
        CompoundCommand cmd = new CompoundCommand();
        Map<FSMDTransition, EditorDrawLine> transitions = getSelectedDrawLines(editor);
        for (FSMDTransition transition : transitions.keySet()) {
            if (transition.getType() != null && !transition.getType().equals(lineType)) {
                cmd = changeTransitionType(cmd, editor, transition, lineType);
            }
        }
        editor.manager.execute(cmd);
        transitions.entrySet().forEach(transition -> {
            editor.getEditorDrawLines().get(transition.getKey()).getDotLines().forEach(dotLine -> dotLine.erase());
            removeDrawLines(editor, transition.getKey());
        });
        editor.refresh();
    }

    /**
     * Create and get a command to change the transition line type.
     * @param cmd Created command
     * @param editor Main class of behavior-model editor
     * @param trans transition line
     * @param lineType transition line type
     * @return Created command
     */
    protected CompoundCommand changeTransitionType(CompoundCommand cmd, FSMEditor editor, FSMDTransition trans, LineType lineType) {
        double[] sAnchor = { 0.5, 0.5 };
        double[] tAnchor = { 0.5, 0.5 };
        if (lineType.equals(LineType.MANHATTAN)) {
            DrawItem source = editor.getDrawItems().get(trans.getSource());
            DrawItem target = editor.getDrawItems().get(trans.getTarget());
            List<double[]> mPoints = EditorLineUtil.getManhattanPoint(trans, source, target, null, null);
            List<AbstractPoint> points = EditorLineUtil.getConnectionPoint(mPoints, editor);
            sAnchor = EditorLineUtil.getCalculationResultOfAnchor(source, (int) mPoints.get(0)[0], (int) mPoints.get(0)[1]);
            tAnchor = EditorLineUtil.getCalculationResultOfAnchor(target, (int) mPoints.get(mPoints.size() - 1)[0], (int) mPoints.get(mPoints.size() - 1)[1]);
            cmd = EditorCommandProvider.getInstance().addPoint(cmd, trans, points);
        }
        cmd = EditorCommandProvider.getInstance().changeLine(cmd, trans, lineType);
        cmd = FSMEditorCommandProvider.getInstance().resizeOutgoingTransition(cmd, trans, trans.getSource(), sAnchor[0], sAnchor[1]);
        cmd = FSMEditorCommandProvider.getInstance().resizeIncomingTransition(cmd, trans, trans.getTarget(), tAnchor[0], tAnchor[1]);
        return cmd;
    }

    /**
     * The line type of the selected drawing line is checked.<br>
     * True is returned if all are the same line type.
     * @param editor Main class of behavior-model editor
     * @param lineType line type
     * @return True if all line types are the same
     */
    protected boolean checkSelectedLineRouting(FSMEditor editor, LineType lineType) {
        Map<FSMDTransition, EditorDrawLine> transitions = getSelectedDrawLines(editor);
        for (FSMDTransition transition : transitions.keySet()) {
            if (transition.getType() != lineType) {
                return false;
            }
        }
        return true;
    }

    /**
     * Resize the Manhattan line to reflect in the EMF model.
     * @param editor Main class of behavior-model editor
     * @param transition FSM model transition line information
     * @param newLine Manhattan line after resizing
     * @param ind Manhattan line index numbers
     */
    protected void reDrawEditedManhattan(FSMEditor editor, FSMDTransition transition, DrawLine newLine, int ind) {
        CompoundCommand cmd = new CompoundCommand();
        cmd = EditorCommandProvider.getInstance().addPoint(cmd, transition, EditorLineUtil.getNewManhattanPoint(transition, newLine, ind));
        editor.manager.execute(cmd);
        editor.getEditorDrawLines().get(transition).getDotLines().forEach(dotLine -> dotLine.erase());
        removeDrawLines(editor, transition);
        editor.makeNewDrawLine(transition, false);
        List<DrawLine> drawLines = editor.getEditorDrawLines().get(transition).getDrawLines();
        EditorLineUtil.selectDrawLine(editor.getEditorDrawLines().get(transition).getDrawLabel(), drawLines, editor.getEditorDrawLines().get(transition).getDotLines(), true);
        EditorDrawLine editorDrawLine = editor.getEditorDrawLines().get(transition);

        Map<Integer, DrawLine> newLines = EditorLineUtil.reDesignManhattan(drawLines, editor.getDrawPane());
        newLines.forEach((i, drawLine) -> {
            editorDrawLine.getDotLines().add(drawLine);
            editor.addManhattanLineDragStopEvent(editor, transition, drawLine, i);
        });
    }

    /**
     * Deletes all line drawing information related to transition line information.
     * @param editor Main class of behavior-model editor
     * @param transition FSM model transition line information
     */
    protected void removeDrawLines(FSMEditor editor, FSMDTransition transition) {
        editor.getEditorDrawLines().get(transition).getDrawLinesTransparent().forEach(l -> l.erase());
        editor.getEditorDrawLines().get(transition).getDrawLines().forEach(l -> l.erase());
        editor.getEditorDrawLines().get(transition).getDotLines().forEach(dotLine -> dotLine.erase());
        editor.getEditorDrawLines().get(transition).getDrawLabel().erase();
        editor.getEditorDrawLines().remove(transition);
    }
}
