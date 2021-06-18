package com.zipc.garden.webplatform.client.editor.scs;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.SCSResourceServiceImpl;

/**
 * Asynchronous interface for SCSEditor original processing
 */
public interface SCSResourceServiceAsync {

    /**
     * {@link SCSResourceServiceImpl#saveLSC(String, long, byte[])}
     */
    void saveLSC(long fileId, byte[] SCSPatternBinary, AsyncCallback<Void> callback) throws IllegalArgumentException;
}
