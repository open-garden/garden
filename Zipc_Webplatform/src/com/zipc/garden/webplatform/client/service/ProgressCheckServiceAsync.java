package com.zipc.garden.webplatform.client.service;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.ProgressCheckServiceImpl;

/**
 * Asynchronous interface that monitors and cancels the status of running jobs
 */
public interface ProgressCheckServiceAsync {

    /**
     * {@link ProgressCheckServiceImpl#setCancelStatus(List)}
     */
    void setCancelStatus(List<String> cancelJobsList, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link ProgressCheckServiceImpl#getAllJobByProjId(long)}
     */
    void getAllJobByProjId(long projectId, AsyncCallback<List<Map<String, String>>> callback) throws IllegalArgumentException;
}
