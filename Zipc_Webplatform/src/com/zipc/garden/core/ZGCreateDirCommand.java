package com.zipc.garden.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.shared.resource.VMDirectory;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * Manages directory creation in Explorer.
 */
public class ZGCreateDirCommand extends ZGResourceCommand {

    /** ID of the parent directory of the directory to be created */
    private long parentId;

    /** Created directory ID */
    private long fileId;

    /** Directory information to be created */
    private VMDirectory targetDir;

    /**
     * constructor
     * @param editResourceService
     * @param viewHandler
     * @param parentId
     * @param targetDir
     */
    public ZGCreateDirCommand(EditResourceServiceAsync editResourceService, List<HandlerRegistration> viewHandler, long parentId, VMDirectory targetDir) {
        super(editResourceService, viewHandler);
        this.parentId = parentId;
        this.targetDir = targetDir;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean prepare() {
        if (!super.prepare()) {
            return false;
        }
        if (parentId == 0 || targetDir == null) {
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
        editResourceService.createDir(parentId, targetDir.getName(), new AsyncCallback<Long>() {

            @Override
            public void onSuccess(Long result) {
                fileId = result;
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
        List<VMResource> resources = new ArrayList<VMResource>();
        resources.add(targetDir);
        editResourceService.removeResources(resources, new AsyncCallback<Void>() {

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
     * {@inheritDoc}
     */
    @Override
    public void redo() {
        eventCancel(true);
        editResourceService.changeDeleteFlgs(Arrays.asList(this.fileId), false, new AsyncCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                listener.redoEvent();
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
     * Get parent directory ID
     * @return
     */
    public Long getParentId() {
        return this.parentId;
    }

    /**
     * Get the ID of the directory to create
     * @return
     */
    public Long getFileId() {
        return this.fileId;
    }

    /**
     * Get information about the directory to be created
     * @return
     */
    public VMDirectory getDirectory() {
        return this.targetDir;
    }
}
