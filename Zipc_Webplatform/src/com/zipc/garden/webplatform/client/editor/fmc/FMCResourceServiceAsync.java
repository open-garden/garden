package com.zipc.garden.webplatform.client.editor.fmc;

import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.FMCResourceServiceImpl;

/**
 * Asynchronous interface for FMCEditor original processing
 */
public interface FMCResourceServiceAsync {

    /**
     * {@link FMCResourceServiceImpl#getFMFileContent(String, long)}
     */
    void getFMFileContent(String fileUuid, long projectId, AsyncCallback<Map<String, byte[]>> callback) throws IllegalArgumentException;
}
