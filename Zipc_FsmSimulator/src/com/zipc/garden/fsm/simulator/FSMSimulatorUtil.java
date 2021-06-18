package com.zipc.garden.fsm.simulator;

/**
 * A class that summarizes the common methods used by Fsm Simulator.
 */
public class FSMSimulatorUtil {

    /**
     * Generate a fsm simulator. <br>
     * シミュレータを生成する
     * @return instance of that implements IFSMSimulator
     */
    public static IFSMSimulator createSimulator() {
        return new FSMSimulator();
    }
}
