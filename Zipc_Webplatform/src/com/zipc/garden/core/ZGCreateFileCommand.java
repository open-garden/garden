package com.zipc.garden.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

public class ZGCreateFileCommand extends ZGResourceCommand {

    /** ID of the parent directory of the file to be created */
    private long parentId;

    /** File information to be created */
    private VMFile file;

    /** Reference ID list of created file */
    private List<Long> refIdList;

    /** Created file ID */
    private Long fileId;

    /**
     * constructor
     * @param editResourceService
     * @param viewHandler
     * @param parentId
     * @param file
     * @param refIdList
     */
    public ZGCreateFileCommand(EditResourceServiceAsync editResourceService, List<HandlerRegistration> viewHandler, long parentId, VMFile file, List<Long> refIdList) {
        super(editResourceService, viewHandler);
        this.parentId = parentId;
        this.file = file;
        this.refIdList = refIdList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean prepare() {
        if (!super.prepare()) {
            return false;
        }
        if (parentId == 0 || file == null) {
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
        editResourceService.createFile(parentId, file, refIdList, new AsyncCallback<Long>() {
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
        resources.add(file);
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
     * Get the ID of the file to create
     * @return
     */
    public Long getFileId() {
        return this.fileId;
    }

    /**
     * Get information about the file to be created
     * @return
     */
    public VMFile getFile() {
        return this.file;
    }

}
