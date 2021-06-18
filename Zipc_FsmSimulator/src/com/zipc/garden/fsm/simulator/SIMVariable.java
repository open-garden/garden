package com.zipc.garden.fsm.simulator;

import com.zipc.garden.model.bps.BPSVariable;

/**
 * An abstract class that summarizes the methods used for the variable class for the simulator.
 */
public abstract class SIMVariable {

    /**
     * <pre>
     * Build variable information based on BPSVariable.
     * BPSVariableを元に変数情報を構築する
     * </pre>
     * 
     * @param variable BPS variable information
     * @return true/false
     */
    public abstract boolean configure(BPSVariable variable);

    /**
     * <pre>
     * Initialize the variable.
     * 変数を初期化する
     * </pre>
     */
    public abstract void reset();
}
