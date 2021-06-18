package com.zipc.garden.fsm.simulator;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.zipc.garden.fsm.simulator.internal.FSMSimulatorInternalUtil;
import com.zipc.garden.model.bps.BPSInstanceState;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;
import com.zipc.garden.model.bps.BPSInstanceTransition;
import com.zipc.garden.model.bps.BPSVariable;
import com.zipc.garden.parser.fsm.condition.ASTFSMCondition;
import com.zipc.garden.parser.fsm.condition.FSMConditionParser;
import com.zipc.garden.parser.fsm.condition.ParseException;

/**
 * A state machine environment for the simulator.
 */
public class SIMStateMachine {

    /** name of state machine */
    String name;

    /** priority of state machine */
    Integer priority;

    /** State machine created based on ARC file */
    BPSInstanceStateMachine bpsStm;

    /** The initial state of the state machine. */
    BPSInstanceState initialState;

    /** The current state of the state machine. */
    BPSInstanceState currentState;

    /** Occurred event name */
    String occurredEvent;

    /** A class that parses FSM conditional expressions. */
    SIMFSMConditionParserVisitor visitor;

    /** Event transition information and transition conditions are managed. */
    Map<BPSInstanceTransition, ASTFSMCondition> conditionMap = new HashMap<>();

    /** Variable names and variable values ​​are managed. */
    Map<BPSVariable, SIMVariable> variableMap = new HashMap<>();

    /** The firing status of the event is managed. */
    Map<String, Boolean> eventMap = new HashMap<>();

    /** Events and event firing conditions are managed. */
    Map<String, ASTFSMCondition> triggerMap = new HashMap<>();

    /**
     * <pre>
     * Self-transition flag.
     * True for self-transition.
     * False if not self-transition.
     * </pre>
     */
    boolean isSelfTransition = false;

    /**
     * <pre>
     * Flags for success or failure of self-transition events.
     * True if successful or not self-transition.
     * False if unsuccessful.
     * </pre>
     */
    boolean isSelfTransitionSuccess = true;

    /**
     * <pre>
     * Gets the current state of the state machine.
     * 現在の状態を取得する
     * </pre>
     * 
     * @return String current state name
     */
    public String getCurrentState() {
        if (null == currentState) {
            return null;
        }
        return currentState.getOriginalName();
    }

    /**
     * <pre>
     * Get the occurred event.
     * 発火したイベントを取得する
     * </pre>
     * 
     * @return String Occurred event name
     */
    public String getOccurredEvent() {
        if (null == occurredEvent) {
            return currentState.getOriginalName() + "->null";
        }
        return occurredEvent;
    }

    /**
     * <pre>
     * Gets the current variable value for the specified variable name.
     * 現在の変数値を取得する
     * </pre>
     * 
     * @param variableName specified variable name.
     * @return String value of variableName
     */
    public String getVarCurrentValue(String variableName) {
        if (null == variableName) {
            return null;
        }
        BPSVariable variable = bpsStm.getVariables().stream().filter(var -> variableName.equals(var.getName())).findFirst().orElse(null);
        if (null == variable) {
            return null;
        }
        SIMVariable simVariable = variableMap.get(variable);
        if (simVariable instanceof SIMStringVariable) {
            return ((SIMStringVariable) simVariable).getValue();
        } else {
            return null;
        }
    }

    /**
     * <pre>
     * Build a StateMachine environment for simulators from BPSInstanceStateMachine.
     * BPSInstanceStateMachineからシミュレータ向けStateMachineの環境を構築する
     * </pre>
     * 
     * @param bpsStm State machine created based on ARC file
     * @param instanceName name of state machine
     * @return True if built successfully. False if unsuccessful.
     */
    public boolean configure(BPSInstanceStateMachine bpsStm, String instanceName) {
        boolean result = false;
        this.bpsStm = bpsStm;
        name = instanceName;
        priority = 0;
        initialState = getInitialState();
        currentState = initialState;
        occurredEvent = currentState.getOriginalName() + "->null";
        visitor = new SIMFSMConditionParserVisitor();
        visitor.setFsm(this);
        if (!createEventMap()) {
            return false;
        }
        result = createVariableMap();
        return result;
    }

    /**
     * <pre>
     * Issue the specified event. The corresponding event turns ON.
     * イベントをONにする
     * </pre>
     * 
     * @param event Event to Issue.
     * @return True if successful. False if the specified event does not exist.
     */
    public boolean setEvent(String event) {
        String eventName = currentState.getOriginalName() + "->" + event;
        if (null == eventMap.get(eventName)) {
            return false;
        }
        eventMap.put(eventName, true);
        return true;
    }

    /**
     * <pre>
     * Run the simulation. The state transitions based on the set event.
     * シミュレーションを実行する。設定済のイベントに基づき状態遷移する。
     * </pre>
     * 
     * @return true
     */
    public boolean execute() {
        // シミュレーションを実行する前、トリガーがあるイベントに発火できるかどうかを評価する
        for (Map.Entry<String, ASTFSMCondition> entry : triggerMap.entrySet()) {
            String event = entry.getKey();
            ASTFSMCondition trigger = entry.getValue();
            Boolean result = false;
            if (null != trigger) { // トリガーがある場合は確認
                result = (Boolean) trigger.jjtAccept(visitor, null);
                eventMap.put(event, result);
            }
        }
        // シミュレーションを実行する
        String tempEvent;
        occurredEvent = currentState.getOriginalName() + "->null";
        for (BPSInstanceTransition transition : currentState.getTransitions().stream().sorted((t1, t2) -> t1.getPriority() - t2.getPriority()).collect(Collectors.toList())) {
            tempEvent = transition.getSource().getOriginalName() + "->" + transition.getEvent();
            if (eventMap.get(tempEvent)) {
                occurredEvent = tempEvent;
                ASTFSMCondition condition = conditionMap.get(transition);
                Boolean result = true;
                if (null != condition) { // 条件がある場合は確認
                    result = (Boolean) condition.jjtAccept(visitor, null);
                }
                // 自遷移の場合
                isSelfTransition = currentState.equals(transition.getTarget());
                if (isSelfTransition) {
                    isSelfTransitionSuccess = result;
                }
                if (result) {
                    currentState = transition.getTarget();
                    return true;
                }
            }
        }

        return true;
    }

    /**
     * <pre>
     * Turn off all events.
     * イベントを全てOFFにする
     * </pre>
     */
    public void clearAllEvent() {
        eventMap.entrySet().forEach(entry -> eventMap.put(entry.getKey(), false));
    }

    /**
     * <pre>
     * Turns off the specified event.
     * イベントをOFFにする
     * </pre>
     * 
     * @param event Events to turn off
     * @return True if successful. False if the specified event does not exist.
     */
    public boolean clearEvent(String event) {
        if (null == eventMap.get(event)) {
            return false;
        }
        eventMap.put(event, false);
        return true;
    }

    /**
     * <pre>
     * Reset the state machine to its initial state.
     * ステートマシンを初期状態に戻す
     * </pre>
     */
    public void reset() {
        currentState = initialState;
        occurredEvent = currentState.getOriginalName() + "->null";
        variableMap.entrySet().forEach(entry -> entry.getValue().reset());
        isSelfTransition = false;
        isSelfTransitionSuccess = true;
    }

    /**
     * <pre>
     * Get the state machine name.
     * ステートマシン名を取得する
     * </pre>
     * 
     * @return String name of state machine
     */
    public String getName() {
        return name;
    }

    /**
     * <pre>
     * Gets a variable value instance with the specified name.
     * 指定した名前の変数値インスタンスを取得する
     * </pre>
     * 
     * @param name Specified variable name
     * @return variable value instance
     */
    public SIMVariable getVariable(String name) {
        SIMVariable variable = variableMap.entrySet().stream().filter(e -> name.equals(e.getKey().getName())).map(e -> e.getValue()).findFirst().orElse(null);
        return variable;
    }

    /**
     * <pre>
     * Set the priority of the state machine.
     * 優先度を設定する
     * </pre>
     * 
     * @param priority priority of state machine
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * <pre>
     * Get the priority of the state machine.
     * 優先度を取得する
     * </pre>
     * 
     * @return {@link #priority}
     */
    public Integer getPriority() {
        return priority;
    }

    /******************/
    /* Private Method */
    /******************/

    /**
     * <pre>
     * Get the initial state from {@link #bpsStm BPSInstanceStateMachine}.
     * BPSInstanceStateMachineから初期状態を取得する
     * </pre>
     * 
     * @return The initial state of the state machine.
     */
    private BPSInstanceState getInitialState() {
        BPSInstanceState initialState = bpsStm.getInitialState();
        if (null == initialState) { // 初期状態が無い
            initialState = bpsStm.getStates().get(0);
        } else {
            if (initialState.getTransitions().isEmpty()) { // 初期状態がどの状態にも遷移しない
                initialState = bpsStm.getStates().get(0);
            } else { // TODO 今は初期状態の遷移先が一つの前提
                initialState = initialState.getTransitions().stream().sorted((t1, t2) -> t1.getPriority() - t2.getPriority()).collect(Collectors.toList()).get(0).getTarget();
            }
        }
        return initialState;
    }

    /**
     * <pre>
     * Get the event from {@link #bpsStm BPSInstanceStateMachine}, generate the condition judgment logic using the parser, and register it in the condition map.
     * The condition map to be registered is as follows.
     *  {@link #triggerMap}
     *  {@link #conditionMap}
     *  {@link #eventMap}
     * 
     * BPSInstanceStateMachineからイベントを取得しパーサを使って条件判定ロジックを生成し、条件マップに登録する
     * </pre>
     * 
     * @return true
     */
    private boolean createEventMap() {
        triggerMap.clear();
        conditionMap.clear();
        eventMap.clear();
        List<BPSInstanceTransition> transitions = bpsStm.getStates().stream().flatMap(bpsState -> bpsState.getTransitions().stream()).collect(Collectors.toList());
        for (BPSInstanceTransition transition : transitions) {
            String source = transition.getSource().getOriginalName();
            String event = transition.getEvent();
            if (null == event) { // イベントが無いなら無視
                continue;
            } else if (event.isEmpty()) { // イベントの中身が空なら無視
                continue;
            }
            ASTFSMCondition astfsmTrigger = createASTFSMCondition(transition.getTrigger());
            ASTFSMCondition astfsmCondition = createASTFSMCondition(transition.getCondition());

            triggerMap.put(source + "->" + event, astfsmTrigger);
            conditionMap.put(transition, astfsmCondition);
            eventMap.put(source + "->" + event, false);
        }
        return true;
    }

    /**
     * <pre>
     * Get a variable from {@link #bpsStm BPSInstanceStateMachine}, generate a variable value class, and register it in {@link #variableMap variable map}.
     * 
     * BPSInstanceStateMachineから変数を取得し、変数値クラスを生成して、変数マップに登録する
     * </pre>
     * 
     * @return true
     */
    private boolean createVariableMap() {
        variableMap.clear();
        for (BPSVariable fsmVariable : bpsStm.getVariables()) {
            SIMVariable variable = FSMSimulatorInternalUtil.createVariable(fsmVariable);
            if (null == variable) {
                return false;
            }
            variableMap.put(fsmVariable, variable);
        }
        return true;
    }

    /**
     * <pre>
     * Generate condition judgment logic from the conditional expression character string.
     * 条件式の文字列から条件判定ロジックを生成する
     * </pre>
     * 
     * @param conditionText the conditional expression character string.
     * @return ASTFSMCondition (condition judgment logic)
     */
    private ASTFSMCondition createASTFSMCondition(String conditionText) {
        if (null != conditionText) { // 条件がある場合のみパーサを準備
            if (!conditionText.isEmpty()) { // 中身が空でない
                try (StringReader in = new StringReader(conditionText)) {
                    FSMConditionParser parser = new FSMConditionParser(in);
                    return parser.parse();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
