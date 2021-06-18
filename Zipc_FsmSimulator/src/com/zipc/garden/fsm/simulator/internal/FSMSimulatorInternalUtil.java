package com.zipc.garden.fsm.simulator.internal;

import com.zipc.garden.fsm.simulator.SIMStringVariable;
import com.zipc.garden.fsm.simulator.SIMVariable;
import com.zipc.garden.model.bps.BPSVariable;

public class FSMSimulatorInternalUtil {

    /**
     * Create a variable class for the simulator.
     * @param variable BPS variable information
     * @return a variable class for the simulator.
     */
    public static SIMVariable createVariable(BPSVariable variable) {
        // TODO ここでBPSVariableのtypeに基づき具象クラスを生成するがtype未実装なのでStringのみ
        SIMStringVariable var = new SIMStringVariable();
        if (var.configure(variable)) {
            return var;
        }
        return null;
    }
}
