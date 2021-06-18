package com.zipc.garden.core;

import org.eclipse.emf.common.command.AbstractCommand;

/**
 * Manages the functions needed to undo/redo the EMF model.
 */
public class ZGCommand extends AbstractCommand {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean prepare() {
        return true;
    }

    /**
     * Undo the operation you performed.<br>
     * {@inheritDoc}
     */
    @Override
    public void undo() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redo() {
        execute();
    }
}
