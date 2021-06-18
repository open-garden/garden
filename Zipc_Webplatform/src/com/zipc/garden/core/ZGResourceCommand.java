package com.zipc.garden.core;

import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;

public class ZGResourceCommand extends ZGCommand {

    /** Interface for linking UNDO/REDO features of GWT and EMF models */
    protected CommandListener listener;

    /** "NativePreviewHandler" that receives all events is set */
    private HandlerRegistration eventCanceler;

    /** The registration object that is returned when the event handler is bound and is used to unregister. */
    private List<HandlerRegistration> viewHandler;

    /** Class for asynchronous call from client */
    protected EditResourceServiceAsync editResourceService;

    /**
     * constructor
     * @param editResourceService
     * @param viewHandler
     */
    public ZGResourceCommand(EditResourceServiceAsync editResourceService, List<HandlerRegistration> viewHandler) {
        this.editResourceService = editResourceService;
        this.viewHandler = viewHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean prepare() {
        if (editResourceService == null || viewHandler == null) {
            return false;
        }
        return true;
    }

    /**
     * Set the CommandListener class.
     * @param listener
     */
    public void addCommandListener(CommandListener listener) {
        this.listener = listener;
    }

    /**
     * If eventFlag is true, cancel all running javascript events and remove the registered handler from the held
     * viewHandler.<br>
     * If eventFlag is false, execute bindEvent method for CommandListener instance and re-attach the event handler.
     * @param eventFlag
     */
    protected void eventCancel(boolean eventFlag) {
        if (eventFlag) {
            eventCanceler = Event.addNativePreviewHandler(new NativePreviewHandler() {
                @Override
                public void onPreviewNativeEvent(NativePreviewEvent event) {
                    // 全てのイベントを停止する
                    event.cancel();
                }
            });
            for (HandlerRegistration handler : viewHandler) {
                handler.removeHandler();
            }
        } else {
            if (eventCanceler != null) {
                eventCanceler.removeHandler();
            }
            listener.bindEvent();
        }
    }
}
