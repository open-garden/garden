package com.zipc.garden.webplatform.client.service;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.ProjectServiceImpl;
import com.zipc.garden.webplatform.shared.PatternElementInfo;
import com.zipc.garden.webplatform.shared.ProjectInfo;
import com.zipc.garden.webplatform.shared.UserPropertyInfo;

/**
 * Asynchronous interface for editing and fetching project settings
 */
public interface ProjectServiceAsync {

    /**
     * {@link ProjectServiceImpl#getProjects()}
     */
    void getProjects(AsyncCallback<List<ProjectInfo>> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#getProject(Long)}
     */
    void getProject(Long projectId, AsyncCallback<ProjectInfo> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#createProject(String)}
     */
    void createProject(String projectName, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#removeProject(List)}
     */
    void removeProject(List<Long> projectIdList, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#updateAllVariablesName(Long, String)}
     */
    void updateAllVariablesName(Long projectId, String allVariablesName, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#getAllVariablesName(Long)}
     */
    void getAllVariablesName(Long projectId, AsyncCallback<String> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#renameProject(Long, String)}
     */
    void renameProject(Long projectId, String newName, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#uploadProjects(List, Map)}
     */
    void uploadProjects(List<String> folderPaths, Map<String, byte[]> files, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#getUserPropertyInfoList(Long)}
     */
    void getUserPropertyInfoList(Long projectId, AsyncCallback<List<UserPropertyInfo>> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#createUserProperty(Long)}
     */
    void createUserProperty(Long projectId, AsyncCallback<UserPropertyInfo> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#updateUserProperty(Long, String, String)}
     */
    void updateUserProperty(Long userPropertyId, String userProperty, String initialValue, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#removeUserProperty(Long)}
     */
    void removeUserProperty(Long userPropertyId, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#getPatternElementInfoList(Long)}
     */
    void getPatternElementInfoList(Long projectId, AsyncCallback<List<PatternElementInfo>> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#createPatternElement(Long)}
     */
    void createPatternElement(Long projectId, AsyncCallback<PatternElementInfo> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#updatePatternElement(Long, String, String)}
     */
    void updatePatternElement(Long patternElementId, String name, String value, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link ProjectServiceImpl#removePatternElement(Long)}
     */
    void removePatternElement(Long patternElementId, AsyncCallback<Void> callback) throws IllegalArgumentException;
}
