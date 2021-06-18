package com.zipc.garden.webplatform.server.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.zipc.garden.webplatform.shared.PatternElementInfo;
import com.zipc.garden.webplatform.shared.ProjectInfo;
import com.zipc.garden.webplatform.shared.UserInfo;
import com.zipc.garden.webplatform.shared.UserPropertyInfo;

public class ProjectServiceLogicTest {

    private static UserInfo userInfo;

    private static ProjectInfo projectInfo;

    @BeforeClass
    public static void setup() {
        String userName = "testName";
        String dispName = "testDisplayName";
        String mail = "testMail@xxx.co.jp";
        String testPass = "testPass";
        int role = 1;
        UserServiceLogic.createUser(userName, dispName, mail, testPass, role, -1L, true);
        List<UserInfo> userInfoList = UserServiceLogic.getUserInfoList(1L);
        Optional<UserInfo> optUserInfo = userInfoList.stream().filter(info -> {
            return info.getUserName().equals(userName);
        }).filter(info -> {
            return info.getDisplayName().equals(dispName);
        }).filter(info -> {
            return info.getMail().equals(mail);
        }).filter(info -> {
            return info.getRole() == role;
        }).findFirst();
        assertTrue(optUserInfo.isPresent());
        userInfo = optUserInfo.get();

        String projectName = "testProjectName";
        ProjectServiceLogic.createProject(projectName, userInfo.getUserId());
        List<ProjectInfo> projectInfoList = ProjectServiceLogic.getProjects(userInfo.getUserId());
        Optional<ProjectInfo> optProjectInfo = projectInfoList.stream().filter(info -> info.getName().equals(projectName)).findFirst();
        assertTrue(optProjectInfo.isPresent());
        projectInfo = optProjectInfo.get();
    }

    @AfterClass
    public static void teardown() {
        ProjectServiceLogic.removeProject(Arrays.asList(projectInfo.getId()));
        UserServiceLogic.removeUsers(Arrays.asList(userInfo.getUserId()));
    }

    @Test
    public void test_UserProperty() {
        ProjectServiceLogic.createUserProperty(projectInfo.getId(), userInfo);
        List<UserPropertyInfo> infoList = ProjectServiceLogic.getUserPropertyInfoList(projectInfo.getId());
        assertEquals(infoList.size(), 1);
        assertEquals(infoList.get(0).getUserProperty(), null);

        ProjectServiceLogic.createUserProperty(projectInfo.getId(), userInfo);
        ProjectServiceLogic.updateUserProperty(infoList.get(0).getId(), "weight", "10", userInfo);
        infoList = ProjectServiceLogic.getUserPropertyInfoList(projectInfo.getId());
        assertEquals(infoList.size(), 2);
        assertEquals(infoList.get(0).getUserProperty(), null);
        assertEquals(infoList.get(1).getUserProperty(), "weight");
        assertEquals(infoList.get(1).getInitialValue(), "10");

        ProjectServiceLogic.removeUserProperty(infoList.get(0).getId());
        infoList = ProjectServiceLogic.getUserPropertyInfoList(projectInfo.getId());
        assertEquals(infoList.size(), 1);
        assertEquals(infoList.get(0).getUserProperty(), "weight");
        assertEquals(infoList.get(0).getInitialValue(), "10");
    }

    @Test
    public void test_UserProperty_Exception() {
        try {
            ProjectServiceLogic.createUserProperty(null, userInfo);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            ProjectServiceLogic.getUserPropertyInfoList(null);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            ProjectServiceLogic.updateUserProperty(null, "", "", userInfo);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            ProjectServiceLogic.removeUserProperty(null);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void test_PatternElement() {
        ProjectServiceLogic.createPatternElement(projectInfo.getId(), userInfo);
        List<PatternElementInfo> infoList = ProjectServiceLogic.getPatternElementInfoList(projectInfo.getId());
        assertEquals(infoList.size(), 1);
        assertEquals(infoList.get(0).getName(), null);
        assertEquals(infoList.get(0).getValue(), null);

        ProjectServiceLogic.createPatternElement(projectInfo.getId(), userInfo);
        ProjectServiceLogic.updatePatternElement(infoList.get(0).getId(), "importance", "sum(weight)", userInfo);
        infoList = ProjectServiceLogic.getPatternElementInfoList(projectInfo.getId());
        assertEquals(infoList.size(), 2);
        assertEquals(infoList.get(0).getName(), null);
        assertEquals(infoList.get(0).getValue(), null);
        assertEquals(infoList.get(1).getName(), "importance");
        assertEquals(infoList.get(1).getValue(), "sum(weight)");

        ProjectServiceLogic.removePatternElement(infoList.get(0).getId());
        infoList = ProjectServiceLogic.getPatternElementInfoList(projectInfo.getId());
        assertEquals(infoList.size(), 1);
        assertEquals(infoList.get(0).getName(), "importance");
        assertEquals(infoList.get(0).getValue(), "sum(weight)");
    }

    @Test
    public void test_PatternElement_Exception() {
        try {
            ProjectServiceLogic.createPatternElement(null, userInfo);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            ProjectServiceLogic.getPatternElementInfoList(null);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            ProjectServiceLogic.updatePatternElement(null, "", "", userInfo);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            ProjectServiceLogic.removePatternElement(null);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}
