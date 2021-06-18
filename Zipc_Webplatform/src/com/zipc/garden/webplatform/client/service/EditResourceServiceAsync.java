package com.zipc.garden.webplatform.client.service;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.EditResourceServiceImpl;
import com.zipc.garden.webplatform.shared.JobStatusInfo;
import com.zipc.garden.webplatform.shared.SearchResult;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * Asynchronous interface that manages the process of accessing the DB of Project Explorer
 */
public interface EditResourceServiceAsync {

    /**
     * {@link EditResourceServiceImpl#getResources(long)}
     */
    void getResources(long parentDirId, AsyncCallback<List<VMResource>> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#renameResource(long, String)}
     */
    void renameResource(long resourceId, String newName, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#saveFile(long, byte[])}
     */
    void saveFile(long fileId, byte[] data, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#saveTextFile(long, String, long)}
     */
    void saveTextFile(long fileId, String data, long projectId, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#createDir(long, String)}
     */
    void createDir(long parentId, String childDirName, AsyncCallback<Long> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#createFile(long, VMFile, List)}
     */
    void createFile(long parentId, VMFile file, List<Long> refIdList, AsyncCallback<Long> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#uploadFile(long, VMFile, byte[])}
     */
    void uploadFile(long parentId, VMFile file, byte[] data, AsyncCallback<Long> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getFileContent(long)}
     */
    void getFileContent(long fileId, AsyncCallback<byte[]> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getFileContent(String, long)}
     */
    void getFileContent(String fileUuid, long projectId, AsyncCallback<byte[]> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getTextFileContent(long)}
     */
    void getTextFileContent(long fileId, AsyncCallback<String> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#removeResources(List)}
     */
    void removeResources(List<VMResource> resources, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getHash(String, long)}
     */
    void getHash(String uuId, long projectId, AsyncCallback<String> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getVMFile(long)}
     */
    void getVMFile(long fileId, AsyncCallback<VMFile> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getVMFile(String, long)}
     */
    void getVMFile(String fileId, long projectId, AsyncCallback<VMFile> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getVMFile(String, long, Boolean)}
     */
    void getVMFile(String fileId, long projectId, Boolean deleteFlg, AsyncCallback<VMFile> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getFileMap(long, String)}
     */
    void getFileMap(long projectId, String extension, AsyncCallback<Map<String, String>> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getFileMap(long, List, String)}
     */
    void getFileMap(long projectId, List<Long> refId, String extension, AsyncCallback<Map<String, String>> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getFileUuidMap(long)}
     */
    void getFileUuidMap(long projectId, AsyncCallback<Map<String, Long>> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#moveParents(List)}
     */
    void moveParents(List<Map<Long, VMResource>> targets, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#changeDeleteFlgs(List, boolean)}
     */
    void changeDeleteFlgs(List<Long> targetIds, boolean flg, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getDirId(long)}
     */
    void getDirId(long fileId, AsyncCallback<Long> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getRefTargetFiles(long, List)}
     */
    void getRefTargetFiles(long projectId, List<String> extensions, AsyncCallback<List<VMFile>> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getActiveDirOrFileMap(long, List)}
     */
    void getActiveDirOrFileMap(long projectId, List<Long> targetIds, AsyncCallback<List<Map<Long, VMResource>>> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#searchModel(long, List, String)}
     */
    void searchModel(long projectId, List<String> extensions, String keyWord, AsyncCallback<List<SearchResult>> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#searchFile(long, String)}
     */
    void searchFile(long projectId, String keyWord, AsyncCallback<List<VMFile>> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getTargetToRootDirIds(long)}
     */
    void getTargetToRootDirIds(long targetId, AsyncCallback<List<Long>> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getFileDependencies(long)}
     */
    void getFileDependencies(long projectId, AsyncCallback<List<VMFile[]>> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getSCSRootWithLSCDefined(long, String, String)}
     */
    void getSCSRootWithLSCDefined(long projectId, String scsUuid, String scssUuid, AsyncCallback<Map<String, String>> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getCSCSFileMap(long, String, long)}
     */
    void getCSCSFileMap(long projectId, String uuid, long patternId, AsyncCallback<Map<String, String>> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getJobStatusInfo(long,String)}
     */
    void getJobStatusInfo(long fileId, String hash, AsyncCallback<JobStatusInfo> callback) throws IllegalArgumentException;

    /**
     * {@link EditResourceServiceImpl#getFileContentIsSetting(long)}
     */
    void getFileContentIsSetting(long fileId, AsyncCallback<byte[]> callback) throws IllegalArgumentException;
}
