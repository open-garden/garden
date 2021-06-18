
package com.zipc.garden.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * This class manages file or directory delete commands.
 */
public class ZGResourceRemoveCommand extends ZGResourceCommand {

    /** Resource to be deleted */
    private List<Map<Long, VMResource>> targets;

    /**
     * constructor
     * @param editResourceService
     * @param viewHandler
     * @param targets
     */
    public ZGResourceRemoveCommand(EditResourceServiceAsync editResourceService, List<HandlerRegistration> viewHandler, List<Map<Long, VMResource>> targets) {
        super(editResourceService, viewHandler);
        this.targets = targets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean prepare() {
        if (!super.prepare()) {
            return false;
        }
        if (targets == null) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        eventCancel(true);
        List<VMResource> removeTarget = new ArrayList<VMResource>();
        for (Map<Long, VMResource> target : targets) {
            for (Entry<Long, VMResource> entry : target.entrySet()) {
                removeTarget.add(entry.getValue());
            }
        }
        editResourceService.removeResources(removeTarget, new AsyncCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                listener.executeEvent();
                eventCancel(false);
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
                eventCancel(false);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void undo() {
        eventCancel(true);
        List<Long> undoTargets = new ArrayList<Long>();
        for (Map<Long, VMResource> target : targets) {
            for (Entry<Long, VMResource> entry : target.entrySet()) {
                undoTargets.add(entry.getValue().getId());
            }
        }
        editResourceService.changeDeleteFlgs(undoTargets, false, new AsyncCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                listener.undoEvent();
                eventCancel(false);
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
                eventCancel(false);
            }
        });
    }

    /**
     * Get the resource to be deleted.
     * @return Resource to be deleted
     */
    public List<Map<Long, VMResource>> getTargets() {
        return targets;
    }

}
