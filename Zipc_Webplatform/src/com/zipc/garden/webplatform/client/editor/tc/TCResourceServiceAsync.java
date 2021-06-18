package com.zipc.garden.webplatform.client.editor.tc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.TCResourceServiceImpl;

/**
 * Asynchronous interface for TCEditor original processing
 */
public interface TCResourceServiceAsync {

    /**
     * {@link TCResourceServiceImpl#getFileContent(long, byte[])}
     */
    void getFileContent(long fileId, byte[] root, AsyncCallback<byte[]> callback) throws IllegalArgumentException;
}
