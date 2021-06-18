package com.zipc.garden.webplatform.server.service;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.webplatform.client.service.ProjectService;
import com.zipc.garden.webplatform.server.logic.ProjectServiceLogic;
import com.zipc.garden.webplatform.shared.PatternElementInfo;
import com.zipc.garden.webplatform.shared.ProjectInfo;
import com.zipc.garden.webplatform.shared.UserInfo;
import com.zipc.garden.webplatform.shared.UserPropertyInfo;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the ProjectService interface.
 */
@SuppressWarnings("serial")
public class ProjectServiceImpl extends RemoteServiceServlet implements ProjectService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProjectInfo> getProjects() throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        Long userId = userInfo.getUserId();
        return ProjectServiceLogic.getProjects(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectInfo getProject(Long projectId) throws IllegalArgumentException {
        return ProjectServiceLogic.getProject(projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createProject(String projectName) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        Long userId = userInfo.getUserId();
        ProjectServiceLogic.createProject(projectName, userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeProject(List<Long> projectIdList) throws IllegalArgumentException {
        ProjectServiceLogic.removeProject(projectIdList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renameProject(Long projectId, String newName) throws IllegalArgumentException {
        ProjectServiceLogic.renameProject(projectId, newName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAllVariablesName(Long projectId, String allVariablesName) throws IllegalArgumentException {
        ProjectServiceLogic.updateAllVariablesName(projectId, allVariablesName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAllVariablesName(Long projectId) throws IllegalArgumentException {
        return ProjectServiceLogic.getAllVariablesName(projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean uploadProjects(List<String> folderPaths, Map<String, byte[]> files) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        return ProjectServiceLogic.uploadProjects(folderPaths, files, userInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserPropertyInfo> getUserPropertyInfoList(Long projectId) throws IllegalArgumentException {
        return ProjectServiceLogic.getUserPropertyInfoList(projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserPropertyInfo createUserProperty(Long projectId) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        return ProjectServiceLogic.createUserProperty(projectId, userInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUserProperty(Long userPropertyId, String userProperty, String initialValue) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        ProjectServiceLogic.updateUserProperty(userPropertyId, userProperty, initialValue, userInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUserProperty(Long userPropertyId) throws IllegalArgumentException {
        ProjectServiceLogic.removeUserProperty(userPropertyId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PatternElementInfo> getPatternElementInfoList(Long projectId) throws IllegalArgumentException {
        return ProjectServiceLogic.getPatternElementInfoList(projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PatternElementInfo createPatternElement(Long projectId) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        return ProjectServiceLogic.createPatternElement(projectId, userInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePatternElement(Long patternElementId, String name, String value) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        ProjectServiceLogic.updatePatternElement(patternElementId, name, value, userInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePatternElement(Long PatternElementId) throws IllegalArgumentException {
        ProjectServiceLogic.removePatternElement(PatternElementId);
    }

}
