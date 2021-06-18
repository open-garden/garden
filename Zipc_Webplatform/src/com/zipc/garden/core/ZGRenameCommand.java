
package com.zipc.garden.core;

import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;

/**
 * This class manages file or directory rename commands.
 */
public class ZGRenameCommand extends ZGResourceCommand {

    /** Target resource ID */
    private long targetId;

    /** New file name or directory name */
    private String newName;

    /** Old file name or directory name */
    private String oldName;

    /**
     * constructor
     * @param editResourceService
     * @param viewHandler
     * @param targetId
     * @param newName
     * @param oldName
     */
    public ZGRenameCommand(EditResourceServiceAsync editResourceService, List<HandlerRegistration> viewHandler, long targetId, String newName, String oldName) {
        super(editResourceService, viewHandler);
        this.targetId = targetId;
        this.newName = newName;
        this.oldName = oldName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean prepare() {
        if (!super.prepare()) {
            return false;
        }
        if (targetId == 0 || newName == null || oldName == null) {
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
        editResourceService.renameResource(targetId, newName, new AsyncCallback<Void>() {
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
        editResourceService.renameResource(targetId, oldName, new AsyncCallback<Void>() {
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
     * Gets the new file or directory name.
     * @return new name
     */
    public String getNewName() {
        return this.newName;
    }

    /**
     * Get the old file or directory name.
     * @return old name
     */
    public String getOldName() {
        return this.oldName;
    }
}
