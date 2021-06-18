package com.zipc.garden.fsm.simulator;

import java.util.Map;

import com.zipc.garden.model.bps.BPSInstanceArc;

/**
 * A interface that summarizes the simulator's generation logic.
 */
public interface IFSMSimulator {

    /**
     * <pre>
     * Build a simulator environment based on the BPSInstanceArc created by loading the ARC file.
     * ARCファイルを元にシミュレータ環境を構築する
     * </pre>
     * 
     * @param arc BPSInstanceArc created based on the ARC file.
     * @return True if successful. False if unsuccessful.
     */
    boolean configure(BPSInstanceArc arc);

    /**
     * <pre>
     * Sets the FSM priority based on the specified arguments. The smaller the value, the higher the priority.
     * FSMの優先度をつける。小さい値ほど優先度が高い
     * </pre>
     * 
     * @param fsmPriorityMap A Map that has the FSM name and FSM priority. <br>
     *            key: FSM state machine name, value: priority
     * @return True if successful. False if the priority setting fails.
     */
    boolean setFsmPriority(Map<String, Integer> fsmPriorityMap);

    /**
     * <pre>
     * Issue the specified event to the FSM. The corresponding event is turned on.
     * FSMに対してイベントを発行する。該当イベントはONになる
     * </pre>
     * 
     * @param fsmInstanceName The name of the FSM where the event will be issued
     * @param event Event to issue
     * @return True if successful. False if event issuing fails.
     */
    boolean setFsmEvent(String fsmInstanceName, String event);

    /**
     * <pre>
     * Run all FSM simulations in order of priority.
     * 優先度順に全FSMのシミュレーションを実行する
     * </pre>
     * 
     * @return true/false
     */
    boolean execute();

    /**
     * <pre>
     * Turn off all FSM events.
     * 全FSMのイベントをOFFにする
     * </pre>
     */
    void clearAllFsmEvent();

    /**
     * <pre>
     * Turns off all events in the state machine that match the specified state machine name.
     * FSMのイベントをOFFにする
     * </pre>
     * 
     * @param fsmInstanceName FSM state machine name
     * @return true/false
     */
    boolean clearFsmEvent(String fsmInstanceName);

    /**
     * <pre>
     * Gets the current state of the state machine that matches the specified state machine name.
     * FSMの状態を取得する
     * </pre>
     * 
     * @param fsmInstanceName FSM state machine name
     * @return String state of fsmInstanceName. or null
     */
    String getFsmCurrentState(String fsmInstanceName);

    /**
     * <pre>
     * Gets the occurred event of a state machine that matches the specified state machine name.
     * FSMの発火したイベントを取得する
     * </pre>
     * 
     * @param fsmInstanceName FSM state machine name
     * @return String event of fsmInstanceName. or null
     */
    String getFsmOccurredEvent(String fsmInstanceName);

    /**
     * <pre>
     * Gets the variable value based on the specified state machine and variable name.
     * FSMの変数値を取得する
     * </pre>
     * 
     * @param fsmInstanceName FSM state machine name
     * @param variableName The name of the variable to get.
     * @return String value of variableName. or null
     */
    String getFsmVarCurrentValue(String fsmInstanceName, String variableName);

    /**
     * <pre>
     * Returns the simulator environment to the initial state.
     * シミュレータ環境を初期状態に戻す
     * </pre>
     */
    void reset();

    /**
     * <pre>
     * Whether the FSM event is self-transition and the event was successful.
     * FSMのイベントが自遷移でそのイベントが発火しているか
     * </pre>
     * 
     * @param fsmInstanceName FSM state machine name
     * @return True if the event fires successfully. Or True if it is not a self-transition. <br>
     *         False if it fails due to a condition mismatch.
     */
    boolean isSelfTransitionSuccess(String fsmInstanceName);
}
