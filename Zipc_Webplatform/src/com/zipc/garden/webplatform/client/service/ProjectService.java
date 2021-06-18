package com.zipc.garden.webplatform.client.service;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.zipc.garden.webplatform.shared.PatternElementInfo;
import com.zipc.garden.webplatform.shared.ProjectInfo;
import com.zipc.garden.webplatform.shared.UserPropertyInfo;

/**
 * Defines an interface derived from RemoteService.<br>
 * Lists all the RPC methods needed to edit and get project settings.
 */
@RemoteServiceRelativePath("project")
public interface ProjectService extends RemoteService {

    /**
     * Get all project information available to logged in users.
     * @return ProjectInfo List
     * @throws IllegalArgumentException
     */
    List<ProjectInfo> getProjects() throws IllegalArgumentException;

    /**
     * Get the project information based on the argument "projectId".
     * @param projectId specified project ID.
     * @return Project information acquired based on the specified project ID
     * @throws IllegalArgumentException
     */
    ProjectInfo getProject(Long projectId) throws IllegalArgumentException;

    /**
     * A new project is created.
     * @param projectName Project name to be created
     * @throws IllegalArgumentException
     */
    void createProject(String projectName) throws IllegalArgumentException;

    /**
     * The project that matches the argument is deleted.
     * @param projectIdList List of specified project IDs
     * @throws IllegalArgumentException
     */
    void removeProject(List<Long> projectIdList) throws IllegalArgumentException;

    /**
     * The project that matches the argument will be renamed.
     * @param projectId specified project ID.
     * @param newName New name
     * @throws IllegalArgumentException
     */
    void renameProject(Long projectId, String newName) throws IllegalArgumentException;

    /**
     * Update the "all variable names" of the project that matches the argument.
     * @param projectId specified project ID.
     * @param allVariablesName New name
     * @throws IllegalArgumentException
     */
    void updateAllVariablesName(Long projectId, String allVariablesName) throws IllegalArgumentException;

    /**
     * Get the "All Variables Name" of the project with the argument "projectId".
     * @param projectId specified project ID.
     * @return "All variable names" obtained based on the specified project ID
     * @throws IllegalArgumentException
     */
    String getAllVariablesName(Long projectId) throws IllegalArgumentException;

    /**
     * Register the dragged and dropped folder in the project.
     * @param folderPaths Dragged and dropped folder(fullPath)
     * @param files List of files in folder(key : fullPath, value : byte file)
     * @return true : success / false : some projects failed to upload.
     * @throws IllegalArgumentException
     */
    boolean uploadProjects(List<String> folderPaths, Map<String, byte[]> files) throws IllegalArgumentException;

    /**
     * Acquires the user property information related to the specified project ID.
     * @param projectId specified project ID.
     * @return user property information
     * @throws IllegalArgumentException
     */
    List<UserPropertyInfo> getUserPropertyInfoList(Long projectId) throws IllegalArgumentException;

    /**
     * Create user property when clicking plus button.
     * @param projectId ID of the project whose user properties you want to edit
     * @return Created user property information
     * @throws IllegalArgumentException
     */
    UserPropertyInfo createUserProperty(Long projectId) throws IllegalArgumentException;

    /**
     * Update user property when editing complete.
     * @param userPropertyId ID of the user property to be updated
     * @param userProperty The new userProperty
     * @param initialValue The new initialValue
     * @throws IllegalArgumentException
     */
    void updateUserProperty(Long userPropertyId, String userProperty, String initialValue) throws IllegalArgumentException;

    /**
     * Remove selected user property when clicking minus button.
     * @param userPropertyId Specified user property ID
     * @throws IllegalArgumentException
     */
    void removeUserProperty(Long userPropertyId) throws IllegalArgumentException;

    /**
     * Gets the PatternElement list based on the specified project ID.
     * @param projectId ID of the specified project
     * @return PatternElement list
     * @throws IllegalArgumentException
     */
    List<PatternElementInfo> getPatternElementInfoList(Long projectId) throws IllegalArgumentException;

    /**
     * Create user PatternElement when clicking plus button.
     * @param projectId ID of the specified project
     * @return PatternElementInfo Information of created PatternElement
     * @throws IllegalArgumentException
     */
    PatternElementInfo createPatternElement(Long projectId) throws IllegalArgumentException;

    /**
     * Update user PatternElement when editing complete.
     * @param patternElementId ID of PatternElement to be updated
     * @param name The name of the new PatternElement
     * @param value The value of the new PatternElement
     * @throws IllegalArgumentException
     */
    void updatePatternElement(Long patternElementId, String name, String value) throws IllegalArgumentException;

    /**
     * Remove selected PatternElement when clicking minus button.
     * @param patternElementId ID of the selected PatternElement
     * @throws IllegalArgumentException
     */
    void removePatternElement(Long patternElementId) throws IllegalArgumentException;

}
