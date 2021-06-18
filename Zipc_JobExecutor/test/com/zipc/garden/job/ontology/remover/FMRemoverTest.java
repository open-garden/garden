package com.zipc.garden.job.ontology.remover;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import com.zipc.garden.fsm.simulator.DBUtil;
import com.zipc.garden.job.ontology.OntologyUtils;
import com.zipc.garden.job.ontology.register.IRDFRegister;
import com.zipc.garden.job.ontology.remover.IRDFRemover.Result;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.server.logic.ProjectServiceLogic;
import com.zipc.garden.webplatform.server.logic.UserServiceLogic;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FMRemoverTest {

    private static Path userFilePath = Paths.get("test/resources/com/zipc/garden/job/ontology/register/environment/user.txt");

    private static Path projectFilePath = Paths.get("test/resources/com/zipc/garden/job/ontology/register/environment/project.txt");

    private static Path contentsFolderPath = Paths.get("test/resources/com/zipc/garden/job/ontology/register/content/");

    /**
     * Add user, project, content(arc, fsm)
     */
    @BeforeClass
    public static void setup() {
        java.io.File userFile = userFilePath.toFile();
        DBUtil.INSTANCE.createUser(userFile.getAbsolutePath());
        Long userId = DBUtil.INSTANCE.getUserId(userFile.getAbsolutePath());

        java.io.File projectFile = projectFilePath.toFile();
        DBUtil.INSTANCE.createProject(projectFile.getAbsolutePath(), userId);
        Long projectId = DBUtil.INSTANCE.getProjectId(projectFile.getAbsolutePath(), userId);

        List<java.io.File> contentFiles = getAllContents();
        for (java.io.File contentFile : contentFiles) {
            Long fileId = DBUtil.INSTANCE.uploadFile(contentFile.getAbsolutePath(), userId, projectId);
            System.out.println(contentFile.getAbsolutePath() + " fileId:" + fileId);
        }
    }

    @Test
    public void test01_RegistFeatureModel() {
        VMFile vmFile = getVMFile("OD.fm");
        long fileId = vmFile.getId();
        File file = EditResourceUtil.INSTANCE.getFile(fileId);
        byte[] data = EditResourceUtil.INSTANCE.getFileContent(fileId);
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        assertTrue(root instanceof FMRoot);
        IRDFRegister register = OntologyUtils.createRegister(root, file);
        IRDFRegister.Result result = register.execute();
        assertEquals(result, IRDFRegister.Result.SUCCESS);
    }

    @Test
    public void test02_RemoveFeatureModel() {
        VMFile vmFile = getVMFile("OD.fm");
        long fileId = vmFile.getId();
        File file = EditResourceUtil.INSTANCE.getFile(fileId);
        IRDFRemover remover = OntologyUtils.createRemover(file);
        Result result = remover.execute();
        assertEquals(result, Result.SUCCESS);
    }

    /**
     * Remove user, project, content(fm, fmc, tc, fps)
     */
    @AfterClass
    public static void teardown() {
        Long projectId = getProjectId();
        List<VMFile> vmFiles = EditResourceUtil.INSTANCE.getVMFiles(projectId);
        List<VMResource> vmResources = new ArrayList<>();
        for (VMFile vmFile : vmFiles) {
            vmResources.add((VMResource) vmFile);
        }
        vmFiles.forEach(vmFile -> {
            EditResourceUtil.INSTANCE.removeResources(vmResources, null);
        });

        Long userId = getUserId();
        List<Long> projectIds = new ArrayList<>();
        projectIds.add(projectId);
        ProjectServiceLogic.removeProject(projectIds);

        List<Long> userIds = new ArrayList<>();
        userIds.add(userId);
        UserServiceLogic.removeUsers(userIds);
    }

    private static Long getUserId() {
        java.io.File userFile = userFilePath.toFile();
        Long userId = DBUtil.INSTANCE.getUserId(userFile.getAbsolutePath());
        return userId;
    }

    private static Long getProjectId() {
        java.io.File projectFile = projectFilePath.toFile();
        Long projectId = DBUtil.INSTANCE.getProjectId(projectFile.getAbsolutePath(), getUserId());
        return projectId;
    }

    private static List<java.io.File> getAllContents() {
        return getContents(contentsFolderPath.toFile());
    }

    private static List<java.io.File> getContents(java.io.File file) {
        List<java.io.File> contents = new ArrayList<>();
        for (java.io.File childFile : Arrays.asList(file.listFiles())) {
            if (childFile.isDirectory()) {
                contents.addAll(getContents(childFile));
            } else {
                contents.add(childFile);
            }
        }
        return contents;
    }

    private VMFile getVMFile(String file) {
        Long projectId = getProjectId();
        int point = file.lastIndexOf(".");
        String fileName = file.substring(0, point);
        String fileExtension = file.substring(point + 1);
        List<VMFile> vmFiles = EditResourceUtil.INSTANCE.getVMFiles(projectId);
        for (VMFile vmFile : vmFiles) {
            if (!vmFile.getName().equals(fileName)) {
                continue;
            }
            if (!vmFile.getExtension().getValue().equals(fileExtension)) {
                continue;
            }
            return vmFile;
        }
        return null;
    }
}
