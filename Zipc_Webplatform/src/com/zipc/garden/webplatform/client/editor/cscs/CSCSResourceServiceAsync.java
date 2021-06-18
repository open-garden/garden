package com.zipc.garden.webplatform.client.editor.cscs;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.CSCSResourceServiceImpl;

/**
 * Asynchronous interface for CSCSEditor original processing
 */
public interface CSCSResourceServiceAsync {

    /**
     * {@link CSCSResourceServiceImpl#save(String, long, byte[])}
     */
    void save(long fileId, byte[] CSCPatternBinary, AsyncCallback<Void> callback) throws IllegalArgumentException;
}
