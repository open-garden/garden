package com.zipc.garden.webplatform.client.editor.tps;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Defines an interface derived from RemoteService.<br>
 * Lists all RPC methods for managing TPSEditor's originals processing.
 */
@RemoteServiceRelativePath("tpsResource")
public interface TPSResourceService extends RemoteService {

    /**
     * Creates and gets a feature pattern setting Root based on an FM or TC EMF model.
     * @param fileId FM or TC or FMC file ID
     * @param root Feature pattern setting root before reloading
     * @return feature pattern setting Root
     * @throws IllegalArgumentException
     */
    byte[] getFileContent(long fileId, byte[] root) throws IllegalArgumentException;
}
