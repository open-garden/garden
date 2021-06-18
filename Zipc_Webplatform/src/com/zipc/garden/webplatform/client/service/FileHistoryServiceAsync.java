package com.zipc.garden.webplatform.client.service;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.FileHistoryServiceImpl;
import com.zipc.garden.webplatform.shared.FileHistoryInfo;

/**
 * Asynchronous interface that manages the operation of records in the file history management table
 */
public interface FileHistoryServiceAsync {

    /**
     * {@link FileHistoryServiceImpl#getFileHistoryInfo(long)}
     */
    void getFileHistoryInfo(long fileId, AsyncCallback<List<FileHistoryInfo>> callback) throws IllegalArgumentException;

    /**
     * {@link FileHistoryServiceImpl#updateFileContent(long, String, Date)}
     */
    void updateFileContent(long fileId, String hash, Date updateTime, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link FileHistoryServiceImpl#saveFileToHistory(long, String)}
     */
    void saveFileToHistory(long fileId, String hash, AsyncCallback<Void> callback) throws IllegalArgumentException;
}
