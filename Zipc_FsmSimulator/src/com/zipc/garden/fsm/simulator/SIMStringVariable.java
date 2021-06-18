package com.zipc.garden.fsm.simulator;

import com.zipc.garden.model.bps.BPSVariable;

/**
 * A class that manages variable strings for simulators.
 */
public class SIMStringVariable extends SIMVariable {

    /** The initial value of the variable. */
    private String initialValue;

    /** The string value of the variable. */
    private String value;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean configure(BPSVariable variable) {
        // TODO 初期値を設定。元モデルにないので空を設定
        initialValue = "";
        reset();
        return true;
    }

    /**
     * Gets the value of a variable.
     * @return {@link #value}
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value of the variable.
     * @param value The string value of the variable.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        setValue(initialValue);
    }
}
