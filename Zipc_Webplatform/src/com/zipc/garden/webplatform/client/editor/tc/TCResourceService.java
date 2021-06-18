package com.zipc.garden.webplatform.client.editor.tc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Defines an interface derived from RemoteService.<br>
 * Lists all RPC methods for managing TCEditor's originals processing.
 */
@RemoteServiceRelativePath("tcResource")
public interface TCResourceService extends RemoteService {

    /**
     * The current TCRoot and the newly loaded TCRoot are parsed and a TCNode diff is created.
     * @param fileId fm file id
     * @param root TCRoot at reload
     * @return TCRoot of parse result
     * @throws IllegalArgumentException
     */
    byte[] getFileContent(long fileId, byte[] root) throws IllegalArgumentException;
}
