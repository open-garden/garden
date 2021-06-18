package com.zipc.garden.webplatform.client.editor.fmc;

import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Defines an interface derived from RemoteService.<br>
 * Lists all RPC methods for managing FMCEditor's originals processing.
 */
@RemoteServiceRelativePath("fmcResource")
public interface FMCResourceService extends RemoteService {

    /**
     * Get all the FM models used in the reference node.
     * @param fileUuid UUID of FM file
     * @param projectId project Id
     * @return FM model of the file set in the reference node<br>
     *         key: uuid<br>
     *         value: Binary (FM Root Model)
     * @throws IllegalArgumentException
     */
    Map<String, byte[]> getFMFileContent(String fileUuid, long projectId) throws IllegalArgumentException;
}
