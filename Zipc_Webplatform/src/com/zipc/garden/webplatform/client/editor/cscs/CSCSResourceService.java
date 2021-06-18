package com.zipc.garden.webplatform.client.editor.cscs;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Defines an interface derived from RemoteService.<br>
 * Lists all RPC methods for managing CSCSEditor's originals processing.
 */
@RemoteServiceRelativePath("cscsResource")
public interface CSCSResourceService extends RemoteService {

    /**
     * Method to perform registration process to CSCSPatternDao table.
     * @param fileId ID of CSCS file
     * @param CSCPatternBinary Binary of CSCSPattern class
     * @throws IllegalArgumentException
     */
    void save(long fileId, byte[] CSCPatternBinary) throws IllegalArgumentException;
}
