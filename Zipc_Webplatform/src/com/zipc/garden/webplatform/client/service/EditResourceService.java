package com.zipc.garden.webplatform.client.service;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.SettingInterface;
import com.zipc.garden.webplatform.shared.JobStatusInfo;
import com.zipc.garden.webplatform.shared.SearchResult;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * Defines an interface derived from RemoteService.<br>
 * List all RPC methods for manages the process of accessing the DB of Project Explorer
 */
@RemoteServiceRelativePath("editResource")
public interface EditResourceService extends RemoteService {

    /**
     * Refers to the file table and directory table based on the argument, and returns the result.
     * @param parentDirId
     * @return VMResource List.
     * @throws IllegalArgumentException
     */
    List<VMResource> getResources(long parentDirId) throws IllegalArgumentException;

    /**
     * Rename the folder or file that matches the argument "resourceId".
     * @param resourceId
     * @param newName
     * @throws IllegalArgumentException
     */
    void renameResource(long resourceId, String newName) throws IllegalArgumentException;

    /**
     * Update the file information that matches the argument "fileId".
     * @param fileId
     * @param data
     * @throws IllegalArgumentException
     */
    void saveFile(long fileId, byte[] data) throws IllegalArgumentException;

    /**
     * Update the text file information that matches the argument "fileId".
     * @param fileId
     * @param data
     * @param projectId
     * @throws IllegalArgumentException
     */
    void saveTextFile(long fileId, String data, long projectId) throws IllegalArgumentException;

    /**
     * Create a new folder.
     * @param parentId
     * @param childDirName
     * @return child directory id
     * @throws IllegalArgumentException
     */
    Long createDir(long parentId, String childDirName) throws IllegalArgumentException;

    /**
     * Create a new file.
     * @param parentId
     * @param file
     * @param refIdList -> May be {@code null}, in which case ref information is not saved.
     * @return file id
     * @throws IllegalArgumentException
     */
    Long createFile(long parentId, VMFile file, List<Long> refIdList) throws IllegalArgumentException;

    /**
     * The file is uploaded.
     * @param parentId
     * @param file
     * @param data
     * @return file id
     * @throws IllegalArgumentException
     */
    Long uploadFile(long parentId, VMFile file, byte[] data) throws IllegalArgumentException;

    /**
     * Get the model information of the editor in binary format.
     * @param fileId
     * @return Model binary data
     * @throws IllegalArgumentException
     */
    byte[] getFileContent(long fileId) throws IllegalArgumentException;

    /**
     * Get the model information of the editor in binary format.
     * @param fileUuid
     * @param projectId
     * @return Model binary data
     * @throws IllegalArgumentException
     */
    byte[] getFileContent(String fileUuid, long projectId) throws IllegalArgumentException;

    /**
     * The contents of the text file are acquired.
     * @param fileId
     * @return text file content
     * @throws IllegalArgumentException
     */
    String getTextFileContent(long fileId) throws IllegalArgumentException;

    /**
     * The folder or file is deleted.
     * @param resources
     * @throws IllegalArgumentException
     */
    void removeResources(List<VMResource> resources) throws IllegalArgumentException;

    /**
     * The Hash value of the file that matches the argument "uuid" and "projectId" are obtained.
     * @param fileId
     * @return String
     * @throws IllegalArgumentException
     */
    String getHash(String uuid, long projectId) throws IllegalArgumentException;

    /**
     * Information about the file that matches the argument "fileid" is acquired.
     * @param fileId
     * @return VMFile
     * @throws IllegalArgumentException
     */
    VMFile getVMFile(long fileId) throws IllegalArgumentException;

    /**
     * Information about the file that matches the arguments "fileuuid" and "projectId" is acquired.
     * @param fileId
     * @param projectId
     * @return VMFile
     * @throws IllegalArgumentException
     */
    VMFile getVMFile(String fileId, long projectId) throws IllegalArgumentException;

    /**
     * Information about the file that matches the arguments "fileuuid" ,"projectId" and "deleteFlg" is acquired.
     * @param fileId
     * @param projectId
     * @param deleteFlg If the delete flag is null, the search condition of the delete flag is not set
     * @return VMFile
     * @throws IllegalArgumentException
     */
    VMFile getVMFile(String fileId, long projectId, Boolean deleteFlg) throws IllegalArgumentException;

    /**
     * The UUID and file name of the file that matches the argument "projectId, extension" will be acquired.
     * @param projectId
     * @param extension
     * @throws IllegalArgumentException
     */
    Map<String, String> getFileMap(long projectId, String extension) throws IllegalArgumentException;

    /**
     * The UUID and file name of the file that matches the argument "projectId, refIdList, extension" are obtained.<br>
     * If refIdList is empty, the return value will be empty.
     * @param projectId
     * @param refIdList
     * @param extension
     * @throws IllegalArgumentException
     */
    Map<String, String> getFileMap(long projectId, List<Long> refId, String extension) throws IllegalArgumentException;

    /**
     * The UUID and file id of the file that matches the argument "projectId" are obtained.
     * @param projectId
     * @return file uuid and file id
     * @throws IllegalArgumentException
     */
    Map<String, Long> getFileUuidMap(long projectId) throws IllegalArgumentException;

    /**
     * The folder or file storage location is changed.
     * @param targets
     * @throws IllegalArgumentException
     */
    void moveParents(List<Map<Long, VMResource>> targets) throws IllegalArgumentException;

    /**
     * The folder or file deletion flag is updated.
     * @param targetIds
     * @param flg
     * @throws IllegalArgumentException
     */
    void changeDeleteFlgs(List<Long> targetIds, boolean flg) throws IllegalArgumentException;

    /**
     * Based on the argument “fileId”, get the ID of the directory where the file is located.
     * @param fileId
     * @return directory id
     * @throws IllegalArgumentException
     */
    long getDirId(long fileId) throws IllegalArgumentException;

    /**
     * Acquires the information of the file that matches the argument "projectId, extensions".
     * @param projectId
     * @param extensions
     * @return VMFile List
     * @throws IllegalArgumentException
     */
    List<VMFile> getRefTargetFiles(long projectId, List<String> extensions) throws IllegalArgumentException;

    /**
     * Based on the argument, the file or folder information is acquired.<br>
     * If the argument "targetId" is a folder, information on the folder and file to which it belongs is obtained.
     * @param projectId
     * @param targetIds -> ID of the selected folder or file.
     * @return directory or file
     * @throws IllegalArgumentException
     */
    List<Map<Long, VMResource>> getActiveDirOrFileMap(long projectId, List<Long> targetIds) throws IllegalArgumentException;

    /**
     * Files are searched based on their arguments. A list of files matching "projectId, extensions, keyWord" is retrieved.
     * @param projectId
     * @param extensions
     * @param keyWord
     * @return search results
     * @throws IllegalArgumentException
     */
    List<SearchResult> searchModel(long projectId, List<String> extensions, String keyWord) throws IllegalArgumentException;

    /**
     * Files are searched based on their arguments. A list of files matching "projectId, keyWord" is retrieved.
     * @param projectId
     * @param keyWord
     * @return search results
     * @throws IllegalArgumentException
     */
    List<VMFile> searchFile(long projectId, String keyWord) throws IllegalArgumentException;

    /**
     * ID's are root to targetFile directory.
     * @param targetId
     * @return root to targetFile directory ID's from DB
     * @throws IllegalArgumentException
     */
    List<Long> getTargetToRootDirIds(long targetId) throws IllegalArgumentException;

    /**
     * Get File Dependencies : List<{@link VMFile}[0] is source, {@link VMFile}[1] is target>
     * @param projectId
     * @return
     */
    List<VMFile[]> getFileDependencies(long projectId);

    /**
     * Get Logical Scenario pull-down menu.<br>
     * Only data for which LSC is defined will be obtained.
     * @param projectId
     * @param scsUuid
     * @param scssUuid
     * @return
     */
    Map<String, String> getSCSRootWithLSCDefined(long projectId, String scsUuid, String scssUuid);

    /**
     * Get the CSCS file related to SCS file.<br>
     * The one that matches patternId is obtained.
     * @param projectId
     * @param scsUuid
     * @param patternId
     * @return
     */
    Map<String, String> getCSCSFileMap(long projectId, String scsUuid, long patternId);

    /**
     * Get the JobStatusInfo by fileid.<br>
     * @param fileId
     * @return
     */
    JobStatusInfo getJobStatusInfo(long fileId, String hash);

    /**
     * Get AbstractRoot containing AbstractSetting data.
     * @param fileId
     * @return
     */
    <T extends AbstractRootElement & SettingInterface> byte[] getFileContentIsSetting(long fileId);
}
