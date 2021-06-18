
package com.zipc.garden.core;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.shared.FileTreeNodeFactory;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * Class that manages commands for changing directory paths
 */
public class ZGMoveCommand extends ZGResourceCommand {

    /** Resource after changing directory */
    private List<Map<Long, VMResource>> targets;

    /** Resource before changing directory */
    private List<Map<Long, VMResource>> oldTargets;

    /** Class that manages the project explorer */
    private FileTreeNodeFactory fileTreeNodeFactory;

    /**
     * constructor
     * @param editResourceService
     * @param viewHandler
     * @param targets
     * @param oldTargets
     * @param fileTreeNodeFactory
     */
    public ZGMoveCommand(EditResourceServiceAsync editResourceService, List<HandlerRegistration> viewHandler, List<Map<Long, VMResource>> targets,
            List<Map<Long, VMResource>> oldTargets, FileTreeNodeFactory fileTreeNodeFactory) {
        super(editResourceService, viewHandler);
        this.targets = targets;
        this.oldTargets = oldTargets;
        this.fileTreeNodeFactory = fileTreeNodeFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean prepare() {
        if (!super.prepare()) {
            return false;
        }
        if (targets == null || oldTargets == null || fileTreeNodeFactory == null) {
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
        editResourceService.moveParents(targets, new AsyncCallback<Void>() {
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
        for (Map<Long, VMResource> target : oldTargets) {
            for (Entry<Long, VMResource> entry : target.entrySet()) {
                fileTreeNodeFactory.moveVMResource(entry.getKey(), entry.getValue().getId());
            }
        }
        editResourceService.moveParents(oldTargets, new AsyncCallback<Void>() {
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
     * The resource after changing the directory is acquired.
     * @return
     */
    public List<Map<Long, VMResource>> getTargets() {
        return targets;
    }

    /**
     * The resource before changing the directory is acquired.
     * @return
     */
    public List<Map<Long, VMResource>> getOldTargets() {
        return oldTargets;
    }
}
