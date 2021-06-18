package com.zipc.garden.webplatform.client.editor.scs;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Defines an interface derived from RemoteService.<br>
 * Lists all RPC methods for managing SCSEditor's originals processing.
 */
@RemoteServiceRelativePath("scsResource")
public interface SCSResourceService extends RemoteService {

    /**
     * Reflect LSC information in SCSPatternDAO table.
     * @param fileId SCSS file id
     * @param SCSPatternBinary Binaryized SCSPattern containing LSC information
     * @throws IllegalArgumentException
     */
    void saveLSC(long fileId, byte[] SCSPatternBinary) throws IllegalArgumentException;
}
