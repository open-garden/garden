package com.zipc.garden.webplatform.client.editor.tps;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.TPSResourceServiceImpl;

/**
 * Asynchronous interface for TPSEditor original processing
 */
public interface TPSResourceServiceAsync {

    /**
     * {@link TPSResourceServiceImpl#getFileContent(long, byte[])}
     */
    void getFileContent(long fileId, byte[] root, AsyncCallback<byte[]> callback) throws IllegalArgumentException;
}
