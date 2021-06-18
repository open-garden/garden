package com.zipc.garden.webplatform.client.editor.bps;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Defines an interface derived from RemoteService.<br>
 * Lists all RPC methods for managing BPSEditor's originals processing.
 */
@RemoteServiceRelativePath("bpsResource")
public interface BPSResourceService extends RemoteService {

    /**
     * Generates and acquires BPS based on the ARC EMF model.
     * @param fileId arc file id
     * @param root bps root
     * @return bps root
     * @throws IllegalArgumentException
     */
    byte[] getFileContent(long fileId, byte[] root) throws IllegalArgumentException;
}
