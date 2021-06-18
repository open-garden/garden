package com.zipc.garden.webplatform.server.service;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.webplatform.client.service.GenerateResourceService;
import com.zipc.garden.webplatform.server.generator.GenerateBehaviorPatternAcceptor;
import com.zipc.garden.webplatform.server.generator.GenerateConcreteScenarioSetAcceptor;
import com.zipc.garden.webplatform.server.generator.GenerateScenarioSetSettingAcceptor;
import com.zipc.garden.webplatform.server.generator.GenerateTSDPatternAcceptor;
import com.zipc.garden.webplatform.server.generator.HeavyTaskContentGetter;
import com.zipc.garden.webplatform.shared.JobStatusInfo;
import com.zipc.garden.webplatform.shared.UserInfo;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the GenerateResourceService
 * interface.
 */
@SuppressWarnings("serial")
public class GenerateResourceServiceImpl extends RemoteServiceServlet implements GenerateResourceService {

    /**
     * {@inheritDoc}
     */
    @Override
    public VMFile generateTSDPattern(long settingFileId, boolean isCreateFile) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        GenerateTSDPatternAcceptor acceptor = new GenerateTSDPatternAcceptor();
        VMFile tpVMFile = acceptor.execute(settingFileId, userInfo, isCreateFile);
        return tpVMFile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VMFile generateBehaviorPattern(long settingFileId, boolean isCreateFile) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        GenerateBehaviorPatternAcceptor acceptor = new GenerateBehaviorPatternAcceptor();
        VMFile bpVMFile = acceptor.execute(settingFileId, userInfo, isCreateFile);
        return bpVMFile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VMFile generateScenarioSet(long settingFileId, boolean isCreateFile) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        GenerateScenarioSetSettingAcceptor acceptor = new GenerateScenarioSetSettingAcceptor();
        VMFile scsVMFile = acceptor.execute(settingFileId, userInfo, isCreateFile);
        return scsVMFile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VMFile generateConcreteScenarioSet(long settingFileId, byte[] pattern) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        GenerateConcreteScenarioSetAcceptor acceptor = new GenerateConcreteScenarioSetAcceptor();
        VMFile cscsVMFile = acceptor.execute(settingFileId, pattern, userInfo);
        return cscsVMFile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getPartOfFileContent(long projectId, long fileId, long startRecordOffset, long recordCount) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        HeavyTaskContentGetter<?> getter = new HeavyTaskContentGetter<>();
        return getter.getPartOfFileContent(projectId, fileId, startRecordOffset, recordCount, userInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getPartOfFileContent(long projectId, long fileId, long patternId, String generationHash) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        HeavyTaskContentGetter<?> getter = new HeavyTaskContentGetter<>();
        return getter.getPartOfFileContent(projectId, fileId, patternId, userInfo, generationHash);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<JobStatusInfo> getSettingFileJobStatusList(long fileId, long projectId) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        HeavyTaskContentGetter<?> getter = new HeavyTaskContentGetter<>();
        return getter.getJobStatusInfo(projectId, fileId, userInfo);
    }

}
