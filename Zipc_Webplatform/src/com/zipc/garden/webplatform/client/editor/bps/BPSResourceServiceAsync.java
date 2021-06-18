package com.zipc.garden.webplatform.client.editor.bps;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.BPSResourceServiceImpl;

/**
 * Asynchronous interface for BPSEditor original processing
 */
public interface BPSResourceServiceAsync {

    /**
     * {@link BPSResourceServiceImpl#getFileContent(long, byte[])}
     */
    void getFileContent(long fileId, byte[] root, AsyncCallback<byte[]> callback) throws IllegalArgumentException;
}
