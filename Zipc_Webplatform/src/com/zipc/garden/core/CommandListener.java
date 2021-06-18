package com.zipc.garden.core;

/**
 * Interface for linking the UNDO/REDO function of GWT with the UNDO/REDO function of the EMF model
 */
public interface CommandListener {

    /** This event is fired when a command is executed. */
    public void executeEvent();

    /** It is an event that undoes the operation performed. */
    public void undoEvent();

    /** It is an event to redo the operation once canceled with the cancel function */
    public void redoEvent();

    /**
     * It use eventCancel method, When need rebind handler's event.
     * {@link com.zipc.garden.core.ZGResourceCommand#eventCancel(boolean) }
     */
    public void bindEvent();
}
