package com.zipc.garden.webplatform.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.GenerateResourceServiceImpl;
import com.zipc.garden.webplatform.shared.JobStatusInfo;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * <pre>
 * Asynchronous interface for generating the following and acquiring the generated data
 * 
 *  feature-pattern
 *  behavior-pattern
 *  scenario-set
 *  concrete-scenario-set
 * </pre>
 */
public interface GenerateResourceServiceAsync {
    /**
     * {@link GenerateResourceServiceImpl#generateTSDPattern(long,boolean)}
     */
    void generateTSDPattern(long settingFileId, boolean isCreateFile, AsyncCallback<VMFile> callback) throws IllegalArgumentException;

    /**
     * {@link GenerateResourceServiceImpl#generateBehaviorPattern(long,boolean)}
     */
    void generateBehaviorPattern(long settingFileId, boolean isCreateFile, AsyncCallback<VMFile> callback) throws IllegalArgumentException;

    /**
     * {@link GenerateResourceServiceImpl#generateScenarioSet(long, boolean)}
     */
    void generateScenarioSet(long settingFileId, boolean isCreateFile, AsyncCallback<VMFile> callback) throws IllegalArgumentException;

    /**
     * {@link GenerateResourceServiceImpl#generateConcreteScenarioSet(long, byte[])}
     */
    void generateConcreteScenarioSet(long settingFileId, byte[] pattern, AsyncCallback<VMFile> callback) throws IllegalArgumentException;

    /**
     * {@link GenerateResourceServiceImpl#getPartOfFileContent(long, long, long, long)}
     */
    void getPartOfFileContent(long projectId, long fileId, long startRecordOffset, long recordCount, AsyncCallback<byte[]> callback) throws IllegalArgumentException;

    /**
     * {@link GenerateResourceServiceImpl#getPartOfFileContent(long, long, long, String)}
     */
    void getPartOfFileContent(long projectId, long fileId, long patternId, String generationHash, AsyncCallback<byte[]> callback) throws IllegalArgumentException;

    /**
     * {@link GenerateResourceServiceImpl#getSettingFileJobStatusList(long, long)}
     */
    void getSettingFileJobStatusList(long fileId, long projectId, AsyncCallback<List<JobStatusInfo>> callback) throws IllegalArgumentException;

}
