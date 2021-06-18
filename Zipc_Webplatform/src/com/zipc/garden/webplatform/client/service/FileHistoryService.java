package com.zipc.garden.webplatform.client.service;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.zipc.garden.webplatform.shared.FileHistoryInfo;

/**
 * Defines an interface derived from RemoteService.<br>
 * List all RPC methods for manages the operation of records in the file history management table
 */
@RemoteServiceRelativePath("fileHistory")
public interface FileHistoryService extends RemoteService {

    /**
     * Acquires the file change history based on the argument "fileId".
     * @param fileId Specified file ID
     * @return history list of file
     * @throws IllegalArgumentException
     */
    List<FileHistoryInfo> getFileHistoryInfo(long fileId) throws IllegalArgumentException;

    /**
     * The current file status returns to the status obtained from the file history.
     * @param fileId Specified file ID
     * @param hash Specified hash
     * @Timestamp updateTime Specified updateTime
     * @throws IllegalArgumentException
     */
    void updateFileContent(long fileId, String hash, Date updateTime) throws IllegalArgumentException;

    /**
     * Based on the argument "fileId, hash", the current file status is added to the file history.
     * @param fileId Specified file ID
     * @param hash Specified hash
     * @throws IllegalArgumentException
     */
    void saveFileToHistory(long fileId, String hash) throws IllegalArgumentException;

}
