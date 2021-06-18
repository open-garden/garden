package com.zipc.garden.webplatform.server.dao.accessor;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.zipc.garden.webplatform.dao.Directory;
import com.zipc.garden.webplatform.dao.PatternElement;
import com.zipc.garden.webplatform.dao.Project;
import com.zipc.garden.webplatform.dao.ProjectUsers;
import com.zipc.garden.webplatform.dao.ProjectUsers.ProjectUsersPK;
import com.zipc.garden.webplatform.dao.UserMaster;
import com.zipc.garden.webplatform.dao.UserProperty;
import com.zipc.garden.webplatform.shared.CharaCode;
import com.zipc.garden.webplatform.shared.ProjectInfo;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * Manages the process of accessing project related databases.
 */
public class ProjectAccessor {

    /**
     * Gets all project information available to the specified user.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param userId Login user ID
     * @return ProjectInfo List
     * @throws IllegalArgumentException
     */
    public static List<ProjectInfo> getProjects(Session session, Long userId) throws IllegalArgumentException {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<ProjectInfo> projectInfoQuery = builder.createQuery(ProjectInfo.class);
        Root<ProjectUsers> projectUsersTbl = projectInfoQuery.from(ProjectUsers.class);
        Root<Project> projectTbl = projectInfoQuery.from(Project.class);
        projectInfoQuery.select(builder.construct(ProjectInfo.class, projectTbl.get("id"), projectTbl.get("name"), projectTbl.get("description"), projectTbl.get("image"),
                projectTbl.get("directory").get("id"), projectTbl.get("imageName"), projectTbl.get("imageWidth"), projectTbl.get("imageHeight"), projectTbl.get("encodingType")))
                .where(builder.equal(projectUsersTbl.get("pk").get("project"), projectTbl.get("id")), builder.equal(projectUsersTbl.get("pk").get("userMaster"), userId));
        List<ProjectInfo> projectList = session.createQuery(projectInfoQuery).getResultList();
        changeImageData(projectList);
        return projectList;
    }

    /**
     * Get the project information based on the specified project ID.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param projectId specified project ID.
     * @return Project information acquired based on the specified project ID
     * @throws IllegalArgumentException
     */
    public static ProjectInfo getProject(Session session, Long projectId) throws IllegalArgumentException {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<ProjectInfo> projectInfoQuery = builder.createQuery(ProjectInfo.class);
        Root<Project> projectTbl = projectInfoQuery.from(Project.class);
        projectInfoQuery.select(builder.construct(ProjectInfo.class, projectTbl.get("id"), projectTbl.get("name"), projectTbl.get("description"), projectTbl.get("image"),
                projectTbl.get("directory").get("id"), projectTbl.get("imageName"), projectTbl.get("imageWidth"), projectTbl.get("imageHeight"), projectTbl.get("encodingType")))
                .where(builder.equal(projectTbl.get("id"), projectId));
        ProjectInfo projectInfo = session.createQuery(projectInfoQuery).getSingleResult();
        changeImageData(projectInfo);
        return projectInfo;
    }

    /**
     * Create the URL of the image to display.
     * @param projectList Multiple projects info
     */
    private static void changeImageData(List<ProjectInfo> projectList) {
        for (ProjectInfo projectInfo : projectList) {
            changeImageData(projectInfo);
        }
    }

    /**
     * Create the URL of the image to display.
     * @param projectInfo Single project info
     */
    private static void changeImageData(ProjectInfo projectInfo) {
        Byte[] imageR = projectInfo.getImage();
        if (imageR != null) {

            byte[] imageP = new byte[imageR.length];

            for (int i = 0; i < imageR.length; i++) {
                imageP[i] = imageR[i];
            }

            projectInfo.setImageData(projectInfo.getImageType().getMimeType() + Base64.getEncoder().encodeToString(imageP));
        }
    }

    /**
     * A new project is created.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param projectName Project name to be created
     * @param userId Login user ID
     * @throws IllegalArgumentException
     */
    public static void createProject(Session session, String projectName, Long userId) throws IllegalArgumentException {
        Directory dir = new Directory();
        dir.setName("root");
        session.persist(dir);

        Project project = new Project();
        project.setName(projectName);
        project.setDirectory(dir);
        project.setEncodingType(CharaCode.UTF8.getName());
        session.persist(project);

        dir.setProjectid(project.getId());
        session.save(dir);

        ProjectUsers pUsers = new ProjectUsers();
        pUsers.setPk(new ProjectUsersPK(new UserMaster(userId), project));
        session.persist(pUsers);
    }

    /**
     * The project that matches the argument is deleted.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param projectIdList List of specified project IDs
     * @throws IllegalArgumentException
     */
    public static void removeProject(Session session, List<Long> projectIdList) throws IllegalArgumentException {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaDelete<Project> delQuery = builder.createCriteriaDelete(Project.class);
        Root<Project> root = delQuery.from(Project.class);
        delQuery.where(root.get("id").in(projectIdList));
        session.createQuery(delQuery).executeUpdate();

        String delDirSql = "update Directory dir set dir.deleteFlg = true where dir.projectid in :projectids";
        session.createQuery(delDirSql).setParameterList("projectids", projectIdList).executeUpdate();

        String delFileSql = "update File file set file.deleteFlg = true where file.projectid in :projectids";
        session.createQuery(delFileSql).setParameterList("projectids", projectIdList).executeUpdate();
    }

    /**
     * The project that matches the specified project ID is renamed.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param projectId specified project ID.
     * @param newName New name
     * @throws IllegalArgumentException
     */
    public static void renameProject(Session session, Long projectId, String newName) throws IllegalArgumentException {
        Project project = session.byId(Project.class).load(projectId);
        project.setName(newName);
        session.save(project);
    }

    /**
     * Updates the "all variable names" for projects that match the specified project ID.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param projectId specified project ID.
     * @param allVariablesName New name
     */
    public static void updateAllVariablesName(Session session, Long projectId, String allVariablesName) {
        Project project = session.byId(Project.class).load(projectId);
        project.setAllVariablesName(allVariablesName);
        session.save(project);
    }

    /**
     * Get the "All Variables Name" of the projects that match the specified project ID.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param projectId specified project ID.
     * @return "All variable names" obtained based on the specified project ID
     */
    public static String getAllVariablesName(Session session, Long projectId) {
        Project project = session.byId(Project.class).load(projectId);
        return project.getAllVariablesName();
    }

    /**
     * Acquires the user property information related to the specified project ID.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param projectId specified project ID.
     * @return user property information
     */
    public static List<UserProperty> getUserPropertyList(Session session, Long projectId) {
        Project project = session.byId(Project.class).load(projectId);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserProperty> userPropertyQuery = builder.createQuery(UserProperty.class);
        Root<UserProperty> userPropertyTbl = userPropertyQuery.from(UserProperty.class);
        userPropertyQuery.where(builder.equal(userPropertyTbl.get("project"), project));
        return session.createQuery(userPropertyQuery).getResultList();
    }

    /**
     * Create a new record in the user property table.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param projectId ID of the project whose user properties you want to edit
     * @param userInfo Information of logged-in user
     * @return Created user property information
     */
    public static UserProperty createUserProperty(Session session, Long projectId, UserInfo userInfo) {
        UserProperty prop = new UserProperty();
        UserMaster user = session.byId(UserMaster.class).load(userInfo.getUserId());
        Project project = session.byId(Project.class).load(projectId);
        prop.setCreateUser(user);
        prop.setCreateTime(new Timestamp(System.currentTimeMillis()));
        prop.setProject(project);
        session.save(prop);
        return prop;
    }

    /**
     * Updates records that match the specified userProperty Id.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param userPropertyId ID of the user property to be updated
     * @param userProperty The new userProperty
     * @param initialValue The new initialValue
     * @param userInfo Information of logged-in user
     */
    public static void updateUserProperty(Session session, Long userPropertyId, String userProperty, String initialValue, UserInfo userInfo) {
        UserProperty prop = session.byId(UserProperty.class).load(userPropertyId);
        UserMaster user = session.byId(UserMaster.class).load(userInfo.getUserId());
        prop.setUpdateUser(user);
        prop.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        prop.setUserProperty(userProperty);
        prop.setInitialValue(initialValue);
        session.save(prop);
    }

    /**
     * The records that match the specified userProperty Id are deleted.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param userPropertyId Specified user property ID
     */
    public static void removeUserProperty(Session session, Long userPropertyId) {
        UserProperty prop = session.byId(UserProperty.class).load(userPropertyId);
        session.remove(prop);
    }

    /**
     * Gets the PatternElement list based on the specified project ID.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param projectId ID of the specified project
     * @return PatternElement list
     */
    public static List<PatternElement> getPatternElementList(Session session, Long projectId) {
        Project project = session.byId(Project.class).load(projectId);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PatternElement> patternElementQuery = builder.createQuery(PatternElement.class);
        Root<PatternElement> patternElementTbl = patternElementQuery.from(PatternElement.class);
        patternElementQuery.where(builder.equal(patternElementTbl.get("project"), project));
        return session.createQuery(patternElementQuery).getResultList();
    }

    /**
     * Create a new record in the PatternElement table.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param projectId ID of the specified project
     * @param userInfo Information of logged-in user
     * @return Information of created PatternElement
     */
    public static PatternElement createPatternElement(Session session, Long projectId, UserInfo userInfo) {
        PatternElement ptnElem = new PatternElement();
        UserMaster user = session.byId(UserMaster.class).load(userInfo.getUserId());
        Project project = session.byId(Project.class).load(projectId);
        ptnElem.setCreateUser(user);
        ptnElem.setCreateTime(new Timestamp(System.currentTimeMillis()));
        ptnElem.setProject(project);
        session.save(ptnElem);
        return ptnElem;
    }

    /**
     * The records that match the ID of the specified PatternElement will be updated.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param patternElementId ID of PatternElement to be updated
     * @param name The name of the new PatternElement
     * @param value The value of the new PatternElement
     * @param userInfo Information of logged-in user
     */
    public static void updatePatternElement(Session session, Long patternElementId, String name, String value, UserInfo userInfo) {
        PatternElement ptnElem = session.byId(PatternElement.class).load(patternElementId);
        UserMaster user = session.byId(UserMaster.class).load(userInfo.getUserId());
        ptnElem.setUpdateUser(user);
        ptnElem.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        ptnElem.setName(name);
        ptnElem.setValue(value);
        session.save(ptnElem);
    }

    /**
     * The record that matches the specified PatternElement ID is deleted from the PatternElement table.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param PatternElementId specified PatternElement ID
     */
    public static void removePatternElement(Session session, Long PatternElementId) {
        PatternElement ptnElem = session.byId(PatternElement.class).load(PatternElementId);
        session.remove(ptnElem);
    }

}
