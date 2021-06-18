package com.zipc.garden.job.ontology.register;

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
import com.zipc.garden.job.ontology.register.IRDFRegister.Result;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.tp.TPElement;
import com.zipc.garden.model.tp.TPFactory;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tp.TPTSDPattern;
import com.zipc.garden.model.tps.TPSRoot;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.server.logic.ProjectServiceLogic;
import com.zipc.garden.webplatform.server.logic.UserServiceLogic;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FPRegisterTest {

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
    public void test01_RegistFeaturePattern() {
        VMFile vmFile = getVMFile("TSD.fps");
        long fileId = vmFile.getId();
        File file = EditResourceUtil.INSTANCE.getFile(fileId);
        byte[] data = EditResourceUtil.INSTANCE.getFileContent(fileId);
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        assertTrue(root instanceof TPSRoot);

        TPRoot tpRoot = TPFactory.eINSTANCE.createTPRoot();

        TPSetting setting = TPFactory.eINSTANCE.createTPSetting();
        tpRoot.getSettings().add(setting);

        TPElement elementFine = TPFactory.eINSTANCE.createTPElement();
        elementFine.setFullPath("RootNode.Weather.Fine");
        setting.getElements().add(elementFine);
        TPElement elementRain = TPFactory.eINSTANCE.createTPElement();
        elementRain.setFullPath("RootNode.Weather.Rain");
        setting.getElements().add(elementRain);
        TPElement elementHighway = TPFactory.eINSTANCE.createTPElement();
        elementHighway.setFullPath("RootNode.Road.Highway");
        setting.getElements().add(elementHighway);
        TPElement elementUrban = TPFactory.eINSTANCE.createTPElement();
        elementUrban.setFullPath("RootNode.Road.Urban");
        setting.getElements().add(elementUrban);

        TPTSDPattern pattern1 = TPFactory.eINSTANCE.createTPTSDPattern();
        pattern1.setId("1");
        pattern1.getElements().add(elementFine);
        pattern1.getElements().add(elementHighway);
        setting.getPatterns().add(pattern1);
        TPTSDPattern pattern2 = TPFactory.eINSTANCE.createTPTSDPattern();
        pattern2.setId("2");
        pattern2.getElements().add(elementFine);
        pattern2.getElements().add(elementUrban);
        setting.getPatterns().add(pattern2);
        TPTSDPattern pattern3 = TPFactory.eINSTANCE.createTPTSDPattern();
        pattern3.setId("3");
        pattern3.getElements().add(elementRain);
        pattern3.getElements().add(elementHighway);
        setting.getPatterns().add(pattern3);
        TPTSDPattern pattern4 = TPFactory.eINSTANCE.createTPTSDPattern();
        pattern4.setId("4");
        pattern4.getElements().add(elementRain);
        pattern4.getElements().add(elementUrban);
        setting.getPatterns().add(pattern4);

        IRDFRegister register = OntologyUtils.createRegister(tpRoot, file);
        Result result = register.execute();
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
