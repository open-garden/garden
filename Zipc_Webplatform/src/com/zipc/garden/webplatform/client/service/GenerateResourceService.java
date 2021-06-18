package com.zipc.garden.webplatform.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.zipc.garden.webplatform.shared.JobStatusInfo;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * <pre>
 * Defines an interface derived from RemoteService.<br>
 * List all RPC methods for generating the following and acquiring the generated data.
 * 
 *   feature-pattern
 *   behavior-pattern
 *   scenario-set
 *   concrete-scenario-set
 * </pre>
 */
@RemoteServiceRelativePath("generateResource")
public interface GenerateResourceService extends RemoteService {

    /**
     * Generate empty FP file and create job.
     * @param settingFileId fps file id
     * @param isCreateFile True to create an empty FP file
     * @return Returns {@code null}, if generating file or job fails
     * @throws IllegalArgumentException
     */
    VMFile generateTSDPattern(long settingFileId, boolean isCreateFile) throws IllegalArgumentException;

    /**
     * Generate empty BP file and create job.
     * @param settingFileId bps file id
     * @param isCreateFile True to create an empty BP file
     * @return Returns {@code null}, if generating file or job fails
     * @throws IllegalArgumentException
     */
    VMFile generateBehaviorPattern(long settingFileId, boolean isCreateFile) throws IllegalArgumentException;

    /**
     * Generate empty SCS file and create job.
     * @param settingFileId scss file id
     * @param isCreateFile True to create an empty SCS file
     * @return Returns {@code null}, if generating file or job fails
     * @throws IllegalArgumentException
     */
    VMFile generateScenarioSet(long settingFileId, boolean isCreateFile) throws IllegalArgumentException;

    /**
     * Generate empty CSCS file and create job.
     * @param settingFileId scss file id
     * @param pattern Binary data of SCSPattern class
     * @return Returns {@code null}, if generating file or job fails
     * @throws IllegalArgumentException
     */
    VMFile generateConcreteScenarioSet(long settingFileId, byte[] pattern) throws IllegalArgumentException;

    /**
     * Based on the specified argument, get one of TPRoot, BPRoot, SCSRoot, CSCSRoot.
     * @param projectId Specified project Id
     * @param fileId Specified file Id
     * @param startRecordOffset Acquisition start position
     * @param recordCount Number of cases to acquire
     * @return Binary information of TPRoot or BPRoot.<br>
     *         Returns {@code null}, if getting file content fails
     * @throws IllegalArgumentException
     */
    byte[] getPartOfFileContent(long projectId, long fileId, long startRecordOffset, long recordCount) throws IllegalArgumentException;

    /**
     * Gets a TPRoot or BPRoot based on the given arguments. Acquires one pattern.
     * @param projectId Specified project Id
     * @param fileId Specified file Id
     * @param patternId Specified pattern Id
     * @param generationHash Hash value of fps or bps file at the time of scs generation.
     * @return Binary information of TPRoot or BPRoot.<br>
     *         Returns {@code null}, if getting file content fails
     * @throws IllegalArgumentException
     */
    byte[] getPartOfFileContent(long projectId, long fileId, long patternId, String generationHash) throws IllegalArgumentException;

    /**
     * Get setting file job status.
     * @param fileId Specified file Id
     * @param projectId Specified project Id
     * @return job status info
     * @throws IllegalArgumentException
     */
    List<JobStatusInfo> getSettingFileJobStatusList(long fileId, long projectId) throws IllegalArgumentException;

}
