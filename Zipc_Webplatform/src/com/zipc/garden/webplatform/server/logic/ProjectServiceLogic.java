package com.zipc.garden.webplatform.server.logic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.Directory;
import com.zipc.garden.webplatform.dao.PatternElement;
import com.zipc.garden.webplatform.dao.UserProperty;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.server.dao.DaoCopyUtil;
import com.zipc.garden.webplatform.server.dao.accessor.ProjectAccessor;
import com.zipc.garden.webplatform.server.service.EditResourceServiceImpl;
import com.zipc.garden.webplatform.shared.CharaCode;
import com.zipc.garden.webplatform.shared.PatternElementInfo;
import com.zipc.garden.webplatform.shared.ProjectInfo;
import com.zipc.garden.webplatform.shared.UserInfo;
import com.zipc.garden.webplatform.shared.UserPropertyInfo;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * The actual logic of project-related asynchronous processing is managed.
 */
public class ProjectServiceLogic {

    /**
     * Name of property file that holds project information. <br>
     * Used when downloading and uploading the project.
     */
    private static final String PROPERTIES_NAME = "Project.properties";

    /** Directory delimiter */
    private static final String DIR_SEPARATOR = "/";

    /**
     * Gets all project information available to the specified user.
     * @param userId Login user ID
     * @return ProjectInfo List
     * @throws IllegalArgumentException
     */
    public static List<ProjectInfo> getProjects(Long userId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<ProjectInfo> projectList = ProjectAccessor.getProjects(session, userId);
                tx.commit();
                return projectList;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * Get the project information based on the specified project ID.
     * @param projectId specified project ID.
     * @return Project information acquired based on the specified project ID
     * @throws IllegalArgumentException
     */
    public static ProjectInfo getProject(Long projectId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                ProjectInfo projectInfo = ProjectAccessor.getProject(session, projectId);
                tx.commit();
                return projectInfo;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * A new project is created.
     * @param projectName Project name to be created
     * @param userId Login user ID
     * @throws IllegalArgumentException
     */
    public static void createProject(String projectName, Long userId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                ProjectAccessor.createProject(session, projectName, userId);
                tx.commit();
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * The project that matches the argument is deleted.
     * @param projectIdList List of specified project IDs
     * @throws IllegalArgumentException
     */
    public static void removeProject(List<Long> projectIdList) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                ProjectAccessor.removeProject(session, projectIdList);
                tx.commit();
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                System.err.println(e.getMessage());
                throw e;
            }
        }
    }

    /**
     * The project that matches the specified project ID is renamed.
     * @param projectId specified project ID.
     * @param newName New name
     * @throws IllegalArgumentException
     */
    public static void renameProject(Long projectId, String newName) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                ProjectAccessor.renameProject(session, projectId, newName);
                tx.commit();
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                System.err.println(e.getMessage());
                throw e;
            }
        }
    }

    /**
     * Updates the "all variable names" for projects that match the specified project ID.
     * @param projectId specified project ID.
     * @param allVariablesName New name
     * @throws IllegalArgumentException
     */
    public static void updateAllVariablesName(Long projectId, String allVariablesName) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                ProjectAccessor.updateAllVariablesName(session, projectId, allVariablesName);
                tx.commit();
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                System.err.println(e.getMessage());
                throw e;
            }
        }
    }

    /**
     * Get the "All Variables Name" of the projects that match the specified project ID.
     * @param projectId specified project ID.
     * @return "All variable names" obtained based on the specified project ID
     * @throws IllegalArgumentException
     */
    public static String getAllVariablesName(Long projectId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String allVariablesName = ProjectAccessor.getAllVariablesName(session, projectId);
                tx.commit();
                return allVariablesName;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * Create a project based on the specified folder list and file list.
     * @param folderPaths Dragged and dropped folder(fullPath)
     * @param byteFiles List of files in folder(key : fullPath, value : byte file)
     * @param userInfo Information of logged-in user
     * @return true : success / false : some projects failed to upload.
     * @throws IllegalArgumentException
     */
    public static boolean uploadProjects(List<String> folderPaths, Map<String, byte[]> byteFiles, UserInfo userInfo) throws IllegalArgumentException {

        int result = 0, zipResult = 0, projectFolderCnt = 0, unZipProjectFolderCnt = 0;

        // ******************************
        // When there is a property file directly under the folder where you dragged and dropped.
        // ******************************
        Map<String, byte[]> properties = byteFiles.entrySet().stream().filter(entry -> {
            return entry.getKey().split(DIR_SEPARATOR).length == 3;
        }).filter(entry -> {
            return entry.getKey().endsWith(PROPERTIES_NAME);
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        result = createProjects(properties, folderPaths, byteFiles, userInfo);
        projectFolderCnt = (int) folderPaths.stream().filter(folderPath -> {
            return folderPath.split(DIR_SEPARATOR).length == 2;
        }).count();

        // ******************************
        // When you drag and drop the zip file.
        // ******************************
        Map<String, byte[]> zips = byteFiles.entrySet().stream().filter(entry -> {
            return entry.getKey().split(DIR_SEPARATOR).length == 2;
        }).filter(entry -> {
            return entry.getKey().endsWith(".zip");
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for (Entry<String, byte[]> zip : zips.entrySet()) {

            Map<String, byte[]> unZipProperties = new HashMap<>();
            List<String> unZipFolderPaths = new ArrayList<>();
            Map<String, byte[]> unZipByteFiles = new HashMap<>();

            ZipEntry entry;
            try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(zip.getValue()), Charset.forName("MS932"))) {
                while ((entry = zis.getNextEntry()) != null) {
                    List<String> dirs = Arrays.asList(entry.getName().split(DIR_SEPARATOR));
                    if (!entry.isDirectory()) {
                        dirs = dirs.subList(0, dirs.size() - 1);
                    }
                    StringBuilder sb = new StringBuilder();
                    dirs.forEach(dir -> {
                        sb.append(DIR_SEPARATOR).append(dir);
                        if (!unZipFolderPaths.contains(sb.toString())) {
                            unZipFolderPaths.add(sb.toString());
                        }
                    });

                    if (!entry.isDirectory()) {
                        int read;
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        while ((read = zis.read(buffer)) != -1) {
                            baos.write(buffer, 0, read);
                        }
                        if (entry.getName().split(DIR_SEPARATOR).length == 2 && entry.getName().endsWith(PROPERTIES_NAME)) {
                            unZipProperties.put(DIR_SEPARATOR + entry.getName(), baos.toByteArray());
                        } else {
                            unZipByteFiles.put(DIR_SEPARATOR + entry.getName(), baos.toByteArray());
                        }
                        baos.flush();
                        baos.close();
                    }
                    zis.closeEntry();
                }
                zis.close();

                zipResult += createProjects(unZipProperties, unZipFolderPaths, unZipByteFiles, userInfo);
                unZipProjectFolderCnt += (int) unZipFolderPaths.stream().filter(folderPath -> {
                    return folderPath.split(DIR_SEPARATOR).length == 2;
                }).count();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }

        List<String> notZipFiles = byteFiles.keySet().stream().filter(filePath -> {
            return filePath.split(DIR_SEPARATOR).length == 2;
        }).filter(filePath -> {
            return !filePath.endsWith(".zip");
        }).collect(Collectors.toList());

        return result == projectFolderCnt && zipResult == unZipProjectFolderCnt && notZipFiles.size() == 0;
    }

    /**
     * Create a project based on the specified property file, folder list, and file list.
     * @param properties List of properties files in folder(key : fullPath, value : byte file)
     * @param folderPaths Dragged and dropped folder(fullPath)
     * @param byteFiles List of files in folder(key : fullPath, value : byte file)
     * @param userInfo Information of logged-in user
     * @return Number of created projects
     */
    private static int createProjects(Map<String, byte[]> properties, List<String> folderPaths, Map<String, byte[]> byteFiles, UserInfo userInfo) {
        int result = 0;
        for (Entry<String, byte[]> propertyFile : properties.entrySet()) {
            Map<String, Long> folderMap = new HashMap<>();
            String dirName = propertyFile.getKey().replace(PROPERTIES_NAME, "");
            String projectFolder = dirName + "Project" + DIR_SEPARATOR;

            // ******************************
            // create project
            // ******************************
            Properties p = new Properties();
            try (Reader reader = new InputStreamReader(new ByteArrayInputStream(propertyFile.getValue()), "UTF-8")) {
                p.load(reader);
                reader.close();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            String description = p.getProperty("description", null);
            String encodingType = p.getProperty("encodingtype", CharaCode.UTF8.getName());
            int imageHeight = Integer.parseInt(p.getProperty("imageheight", "0"));
            int imageWidth = Integer.parseInt(p.getProperty("imagewidth", "0"));
            String name = p.getProperty("name", null);
            if (name == null || name.isEmpty()) {
                continue;
            }

            Optional<Entry<String, byte[]>> imageFile = byteFiles.entrySet().stream().filter(entry -> {
                return entry.getKey().replace(dirName, "").split(DIR_SEPARATOR).length == 1;
            }).filter(entry -> {
                return ProjectServiceLogic.isImageFileByImageIO(new ByteArrayInputStream(entry.getValue()));
            }).findFirst();

            String imageName = null;
            Byte[] image = null;
            if (imageFile.isPresent()) {
                imageName = imageFile.get().getKey().replace(dirName, "");
                image = ArrayUtils.toObject(imageFile.get().getValue());
            }

            Directory directory = EditResourceUtil.INSTANCE.createProject(name, userInfo.getUserId(), description, encodingType, image, imageName, imageHeight, imageWidth);

            // ******************************
            // create folder
            // ******************************
            List<String> targetFolders = folderPaths.stream().filter(path -> {
                return path.startsWith(projectFolder);
            }).collect(Collectors.toList());
            Collections.sort(targetFolders);
            EditResourceServiceImpl service = new EditResourceServiceImpl();
            for (String targetFolder : targetFolders) {
                String targetFolderPath = targetFolder.replace(projectFolder, "");
                if (targetFolderPath.split(DIR_SEPARATOR).length == 1) {
                    Long dirId = service.createDir(directory.getId(), targetFolderPath);
                    folderMap.put(targetFolderPath, dirId);
                } else {
                    String parentPath = targetFolderPath.substring(0, targetFolderPath.lastIndexOf(DIR_SEPARATOR));
                    String childDirName = targetFolderPath.substring(targetFolderPath.lastIndexOf(DIR_SEPARATOR) + 1);
                    Long parentId = folderMap.get(parentPath);
                    Long dirId = service.createDir(parentId, childDirName);
                    folderMap.put(targetFolderPath, dirId);
                }
            }

            // ******************************
            // create file
            // ******************************
            Map<String, byte[]> targetFiles = byteFiles.entrySet().stream().filter(entry -> {
                return entry.getKey().startsWith(projectFolder);
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            for (Entry<String, byte[]> targetFile : targetFiles.entrySet()) {
                String targetFilePath = targetFile.getKey().replace(projectFolder, "");
                VMFile vmFile = new VMFile();
                if (targetFilePath.split(DIR_SEPARATOR).length == 1) {
                    int index = targetFilePath.lastIndexOf(".") == 0 ? targetFilePath.length() : targetFilePath.lastIndexOf(".") + 1;
                    vmFile.setName(targetFilePath.substring(0, index - 1));
                    vmFile.setExtensionStr(targetFilePath.substring(index));
                    EditResourceUtil.INSTANCE.uploadFile(directory.getId(), vmFile, targetFile.getValue(), userInfo);
                } else {
                    String parentPath = targetFilePath.substring(0, targetFilePath.lastIndexOf(DIR_SEPARATOR));
                    String fileName = targetFilePath.substring(targetFilePath.lastIndexOf(DIR_SEPARATOR) + 1);
                    Long parentId = folderMap.get(parentPath);

                    int index = fileName.lastIndexOf(".") == 0 ? fileName.length() : fileName.lastIndexOf(".") + 1;
                    vmFile.setName(fileName.substring(0, index - 1));
                    vmFile.setExtensionStr(fileName.substring(index));
                    EditResourceUtil.INSTANCE.uploadFile(parentId, vmFile, targetFile.getValue(), userInfo);
                }
            }
            result++;
        }
        return result;
    }

    /**
     * Acquires the user property information related to the specified project ID.
     * @param projectId specified project ID.
     * @return user property information
     * @throws IllegalArgumentException
     */
    public static List<UserPropertyInfo> getUserPropertyInfoList(Long projectId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<UserProperty> propList = ProjectAccessor.getUserPropertyList(session, projectId);
                tx.commit();
                List<UserPropertyInfo> propInfoList = DaoCopyUtil.copy(propList, UserPropertyInfo.class);
                return propInfoList;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * Create a new record in the user property table.
     * @param projectId ID of the project whose user properties you want to edit
     * @param userInfo Information of logged-in user
     * @return Created user property information
     * @throws IllegalArgumentException
     */
    public static UserPropertyInfo createUserProperty(Long projectId, UserInfo userInfo) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                UserProperty prop = ProjectAccessor.createUserProperty(session, projectId, userInfo);
                tx.commit();
                UserPropertyInfo propInfo = new UserPropertyInfo();
                DaoCopyUtil.copy(prop, propInfo);
                return propInfo;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * Updates records that match the specified userProperty Id.
     * @param userPropertyId ID of the user property to be updated
     * @param userProperty The new userProperty
     * @param initialValue The new initialValue
     * @param userInfo Information of logged-in user
     * @throws IllegalArgumentException
     */
    public static void updateUserProperty(Long userPropertyId, String userProperty, String initialValue, UserInfo userInfo) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                ProjectAccessor.updateUserProperty(session, userPropertyId, userProperty, initialValue, userInfo);
                tx.commit();
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * The records that match the specified userProperty Id are deleted.
     * @param userPropertyId Specified user property ID
     * @throws IllegalArgumentException
     */
    public static void removeUserProperty(Long userPropertyId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                ProjectAccessor.removeUserProperty(session, userPropertyId);
                tx.commit();
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * Gets the PatternElement list based on the specified project ID.
     * @param projectId ID of the specified project
     * @return PatternElement list
     * @throws IllegalArgumentException
     */
    public static List<PatternElementInfo> getPatternElementInfoList(Long projectId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<PatternElement> ptnElemList = ProjectAccessor.getPatternElementList(session, projectId);
                tx.commit();
                List<PatternElementInfo> ptnElemInfoList = DaoCopyUtil.copy(ptnElemList, PatternElementInfo.class);
                return ptnElemInfoList;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * Create a new record in the PatternElement table.
     * @param projectId ID of the specified project
     * @param userInfo Information of logged-in user
     * @return PatternElementInfo Information of created PatternElement
     * @throws IllegalArgumentException
     */
    public static PatternElementInfo createPatternElement(Long projectId, UserInfo userInfo) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                PatternElement ptnElem = ProjectAccessor.createPatternElement(session, projectId, userInfo);
                tx.commit();
                PatternElementInfo ptnElemInfo = new PatternElementInfo();
                DaoCopyUtil.copy(ptnElem, ptnElemInfo);
                return ptnElemInfo;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * The records that match the ID of the specified PatternElement will be updated.
     * @param patternElementId ID of PatternElement to be updated
     * @param name The name of the new PatternElement
     * @param value The value of the new PatternElement
     * @param userInfo Information of logged-in user
     * @throws IllegalArgumentException
     */
    public static void updatePatternElement(Long patternElementId, String name, String value, UserInfo userInfo) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                ProjectAccessor.updatePatternElement(session, patternElementId, name, value, userInfo);
                tx.commit();
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * The record that matches the specified PatternElement ID is deleted from the PatternElement table.
     * @param patternElementId ID of the selected PatternElement
     * @throws IllegalArgumentException
     */
    public static void removePatternElement(Long PatternElementId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                ProjectAccessor.removePatternElement(session, PatternElementId);
                tx.commit();
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * It is checked whether the specified binary data is an image file.
     * @param inputStream InputStream with binary information
     * @return In case of image data, True is returned.
     */
    private static boolean isImageFileByImageIO(InputStream inputStream) {
        try {
            BufferedImage bi = ImageIO.read(inputStream);
            if (bi != null) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

}
