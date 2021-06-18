package com.zipc.garden.fsm.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.ECollections;

import com.zipc.garden.model.bps.BPSDataflow;
import com.zipc.garden.model.bps.BPSInstanceArc;
import com.zipc.garden.model.bps.BPSInstanceState;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;
import com.zipc.garden.model.bps.BPSInstanceTransition;

/**
 * A class that summarizes the simulator's generation logic.
 */
public class FSMSimulator implements IFSMSimulator {

    /** An instance of the ARC file loaded in BPS. */
    BPSInstanceArc root;

    /** State Machine environment for simulators. */
    List<SIMStateMachine> fsms = new ArrayList<>();

    /**
     * <pre>
     * Represents the association with other FSM variables that reference the FSM.
     *  key   : State Machine environment for simulators.
     *  value : Other FSM variables in the simulator.
     * </pre>
     */
    Map<SIMStateMachine, List<SIMVariable>> stateVarListMap = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean configure(BPSInstanceArc arc) {
        this.root = arc;

        // ARCに定義されているFSMインスタンスを生成
        List<BPSInstanceStateMachine> states = root.getStateMachines();
        fsms.clear();
        for (BPSInstanceStateMachine bpsStm : states) {
            // FSMDStateが持つ遷移情報をイベント判定高速化のため優先度順にソートする
            List<BPSInstanceState> bpsStates = bpsStm.getStates();
            bpsStates.forEach(bpsState -> {
                ECollections.sort(bpsState.getTransitions(), new Comparator<BPSInstanceTransition>() {
                    @Override
                    public int compare(BPSInstanceTransition o1, BPSInstanceTransition o2) {
                        Integer priority1 = o1.getPriority();
                        Integer priority2 = o2.getPriority();
                        return priority1.compareTo(priority2);
                    }
                });
            });
            SIMStateMachine fsm = new SIMStateMachine();
            if (!fsm.configure(bpsStm, bpsStm.getOriginalName())) {
                return false;
            }
            fsm.setPriority(bpsStm.getEvalPriority());
            fsms.add(fsm);
        }

        // FSMの状態を参照する他FSMの変数との関連付け
        stateVarListMap.clear();
        fsms.forEach(fsm -> stateVarListMap.put(fsm, new ArrayList<>()));
        for (BPSDataflow dataflow : root.getDataflows()) {
            SIMStateMachine srcFsm = getFsm(dataflow.getSource().getOriginalName());
            SIMStateMachine dstFsm = getFsm(((BPSInstanceStateMachine) dataflow.getTarget().eContainer()).getOriginalName());
            SIMVariable dstFsmVar = dstFsm.getVariable(dataflow.getTarget().getName());
            stateVarListMap.get(srcFsm).add(dstFsmVar);
        }

        // FSMの現在状態を他FSMの変数に反映
        notifyAllFsmStateToVariables();

        // FSMリストを優先度順にソート
        sortFsmsByPriority();
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setFsmPriority(Map<String, Integer> fsmPriorityMap) {
        for (Entry<String, Integer> entry : fsmPriorityMap.entrySet()) {
            SIMStateMachine targetFsm = getFsm(entry.getKey());
            if (null == targetFsm) {
                return false;
            }
            targetFsm.setPriority(entry.getValue());
        }
        // FSMリストを優先度順にソート
        sortFsmsByPriority();
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setFsmEvent(String fsmInstanceName, String event) {
        SIMStateMachine fsm = getFsm(fsmInstanceName);
        if (null == fsm) {
            return false;
        }
        return fsm.setEvent(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() {
        for (SIMStateMachine fsm : fsms) {
            if (!fsm.execute()) {
                return false;
            }
            notifyFsmStateToVariables(fsm);
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearAllFsmEvent() {
        fsms.forEach(fsm -> fsm.clearAllEvent());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clearFsmEvent(String fsmInstanceName) {
        SIMStateMachine fsm = getFsm(fsmInstanceName);
        if (null == fsm) {
            return false;
        }
        fsm.clearAllEvent();
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFsmCurrentState(String fsmInstanceName) {
        SIMStateMachine fsm = getFsm(fsmInstanceName);
        if (null == fsm) {
            return null;
        }
        return fsm.getCurrentState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFsmOccurredEvent(String fsmInstanceName) {
        SIMStateMachine fsm = getFsm(fsmInstanceName);
        if (null == fsm) {
            return null;
        }
        return fsm.getOccurredEvent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFsmVarCurrentValue(String fsmInstanceName, String variableName) {
        SIMStateMachine fsm = getFsm(fsmInstanceName);
        if (null == fsm) {
            return null;
        }
        return fsm.getVarCurrentValue(variableName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        fsms.forEach(fsm -> fsm.reset());

        // FSMの現在状態を他FSMの変数に反映
        notifyAllFsmStateToVariables();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSelfTransitionSuccess(String fsmInstanceName) {
        SIMStateMachine fsm = getFsm(fsmInstanceName);
        if (fsm.isSelfTransition) {
            return fsm.isSelfTransitionSuccess;
        }
        return true;
    }

    /******************/
    /* Private Method */
    /******************/
    /**
     * Gets the simulator state machine that matches the specified state machine name.
     * @param fsmInstanceName specified state machine name
     * @return State Machine environment for simulators.
     */
    private SIMStateMachine getFsm(String fsmInstanceName) {
        return fsms.stream().filter(fsm -> fsmInstanceName.equals(fsm.getName())).findFirst().get();
    }

    /**
     * Sort {@link #fsms} in order of priority. <br>
     * FSMリストを優先度順にソートします。
     */
    private void sortFsmsByPriority() {
        Collections.sort(fsms, new Comparator<SIMStateMachine>() {
            @Override
            public int compare(SIMStateMachine o1, SIMStateMachine o2) {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        });
    }

    /**
     * Reflects the current state of all FSMs in the variables of other FSMs. <br>
     * 全FSMの現在状態を他FSMの変数に反映する
     */
    private void notifyAllFsmStateToVariables() {
        stateVarListMap.entrySet().forEach(entry -> {
            notifyFsmStateToVariables(entry.getKey());
        });
    }

    /**
     * Reflects the current state of FSM in variables of other FSM. <br>
     * FSMの現在状態を他FSMの変数に反映する
     * @param fsm Specified FSM
     */
    private void notifyFsmStateToVariables(SIMStateMachine fsm) {
        List<SIMVariable> variables = stateVarListMap.get(fsm);
        variables.forEach(variable -> {
            if (variable instanceof SIMStringVariable) {
                ((SIMStringVariable) variable).setValue(fsm.getCurrentState());
            }
        });
    }
}
