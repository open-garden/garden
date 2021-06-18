package com.zipc.garden.webplatform.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.fm.FMFactory;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMProperty;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.webplatform.server.logic.ProjectServiceLogic;
import com.zipc.garden.webplatform.server.logic.UserServiceLogic;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.ProjectInfo;
import com.zipc.garden.webplatform.shared.UserInfo;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * このテストは，DBの読み書きを行うため，遅い． 特に，書き込みが遅い．
 */
@RunWith(Enclosed.class)
public class EditResourceUtilTest {

    public static class CreateFileTest {

        private static UserInfo userInfo;

        private static ProjectInfo projectInfo;

        private static VMFile fmVMFile;

        private static VMFile fmVMFile02;

        private static VMFile tcVMFile01 = null;

        private static VMFile tcVMFile02 = null;

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

            FMProperty root_prop = createFMProperty("test", "1");
            FMProperty rootNode_prop = createFMProperty("test", "2");
            FMProperty a1_prop1 = createFMProperty("weight", "10");
            FMProperty a1_prop2 = createFMProperty("risk", "1");
            FMProperty a2_prop1 = createFMProperty("weight", "20");
            FMProperty a2_prop2 = createFMProperty("risk", "2");
            FMProperty root_b_prop = createFMProperty("test", "3");
            FMProperty b_prop = createFMProperty("test", "4");
            FMProperty b1_prop1 = createFMProperty("weight", "30");
            FMProperty b1_prop2 = createFMProperty("risk", "3");
            FMProperty b2_prop1 = createFMProperty("weight", "40");
            FMProperty b2_prop2 = createFMProperty("risk", "4");

            FMRoot fmRoot = FMFactory.eINSTANCE.createFMRoot();
            FMNode rootNode = createFMNode(null, "root", ChildType.AND, false, 0, 0, Arrays.asList(rootNode_prop));
            FMNode a_Node = createFMNode(rootNode, "A", ChildType.XOR, false, 1, 2, null);
            FMNode b_Node = createFMNode(rootNode, "B", ChildType.XOR, true, 0, 0, Arrays.asList(b_prop));
            FMNode a1_Node = createFMNode(a_Node, "A1", ChildType.AND, false, 0, 0, Arrays.asList(a1_prop1, a1_prop2));
            FMNode a2_Node = createFMNode(a_Node, "A2", ChildType.AND, false, 0, 0, Arrays.asList(a2_prop1, a2_prop2));
            fmRoot.getProperties().add(root_prop);
            fmRoot.setId(UUID.randomUUID().toString());
            fmRoot.setNode(rootNode);

            FMRoot fmRoot02 = FMFactory.eINSTANCE.createFMRoot();
            FMNode root_b_Node = createFMNode(null, "B", ChildType.XOR, true, 0, 0, Arrays.asList(root_b_prop));
            FMNode b1_Node = createFMNode(root_b_Node, "B1", ChildType.AND, false, 0, 0, Arrays.asList(b1_prop1, b1_prop2));
            FMNode b2_Node = createFMNode(root_b_Node, "B2", ChildType.AND, false, 0, 0, Arrays.asList(b2_prop1, b2_prop2));
            fmRoot02.setId(UUID.randomUUID().toString());
            fmRoot02.setNode(root_b_Node);
            fmVMFile02 = createFile(fmRoot02, "fmFile02", Extension.FM);

            b_Node.setRefuuid(fmVMFile02.getUuid());
            fmVMFile = createFile(fmRoot, "fmFile", Extension.FM);
        }

        private static VMFile createFile(AbstractRootElement root, String name, Extension ext) {
            VMFile vmFile = new VMFile();
            vmFile.setName(name);
            vmFile.setExtension(ext);
            byte[] content = EditResourceUtil.INSTANCE.convertToBinary(root);
            Long fileId = EditResourceUtil.INSTANCE.uploadFile(projectInfo.getRootDirId().longValue(), vmFile, content, userInfo);
            return EditResourceUtil.INSTANCE.getVMFile(fileId.longValue());
        }

        private static FMNode createFMNode(FMNode parentNode, String name, ChildType childType, boolean optional, int min, int max, List<FMProperty> fmPropertyList) {
            FMNode fmNode = FMFactory.eINSTANCE.createFMNode();
            fmNode.setName(name);
            fmNode.setChildType(childType);
            fmNode.setOptional(optional);
            fmNode.setMin(min);
            fmNode.setMax(max);
            if (fmPropertyList != null) {
                fmPropertyList.forEach(prop -> fmNode.getProperties().add(prop));
            }
            if (parentNode != null) {
                parentNode.getChildren().add(fmNode);
            }
            return fmNode;
        }

        private static FMProperty createFMProperty(String key, String value) {
            FMProperty fmProperty = FMFactory.eINSTANCE.createFMProperty();
            fmProperty.setKey(key);
            fmProperty.setValue(value);
            return fmProperty;
        }

        @AfterClass
        public static void teardown() {
            ProjectServiceLogic.removeProject(Arrays.asList(projectInfo.getId()));
            UserServiceLogic.removeUsers(Arrays.asList(userInfo.getUserId()));
            List<VMResource> vmResources = new ArrayList<>();
            vmResources.add((VMResource) fmVMFile);
            if (tcVMFile01 != null)
                vmResources.add((VMResource) tcVMFile01);
            if (tcVMFile02 != null)
                vmResources.add((VMResource) tcVMFile02);
            EditResourceUtil.INSTANCE.removeResources(vmResources, null);
        }

        @Test
        public void test_TCCreateFile() {
            tcVMFile01 = new VMFile();
            tcVMFile01.setName("tcFile01");
            tcVMFile01.setExtension(Extension.TC);
            Long tcFileId01 = EditResourceUtil.INSTANCE.createFile(projectInfo.getRootDirId().longValue(), tcVMFile01, Arrays.asList(fmVMFile.getId()), userInfo);
            tcVMFile01 = EditResourceUtil.INSTANCE.getVMFile(tcFileId01.longValue());

            tcVMFile02 = new VMFile();
            tcVMFile02.setName("tcFile02");
            tcVMFile02.setExtension(Extension.TC);
            Long tcFileId02 = EditResourceUtil.INSTANCE.createFile(projectInfo.getRootDirId().longValue(), tcVMFile02, Arrays.asList(tcVMFile01.getId()), userInfo);
            tcVMFile02 = EditResourceUtil.INSTANCE.getVMFile(tcFileId02.longValue());

            AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(EditResourceUtil.INSTANCE.getFileContent(fmVMFile.getId()));
            assertTrue(root instanceof FMRoot);
            FMRoot fmRoot = (FMRoot) root;

            AbstractRootElement root1 = EditResourceUtil.INSTANCE.convertToRootElement(EditResourceUtil.INSTANCE.getFileContent(tcVMFile01.getId()));
            assertTrue(root1 instanceof TCRoot);
            TCRoot tcRoot01 = (TCRoot) root1;

            AbstractRootElement root2 = EditResourceUtil.INSTANCE.convertToRootElement(EditResourceUtil.INSTANCE.getFileContent(tcVMFile02.getId()));
            assertTrue(root2 instanceof TCRoot);
            TCRoot tcRoot02 = (TCRoot) root2;

            comparingFMNodeAndTCNode(fmRoot.getNode(), tcRoot01.getRootNodes().get(0));
            comparingTCNodeAndTCNode(tcRoot02.getRootNodes().get(0), tcRoot01.getRootNodes().get(0)); // rootNode
        }

        private void comparingFMNodeAndTCNode(FMNode fmNode, TCNode tcNode) {
            assertEquals(fmNode.getName(), tcNode.getName());
            assertEquals(fmNode.getChildType(), tcNode.getChildType());
            assertEquals(fmNode.isOptional(), tcNode.isOptional());
            assertEquals(fmNode.getMin(), tcNode.getMin());
            assertEquals(fmNode.getMax(), tcNode.getMax());
            assertEquals(fmNode.getProperties().size(), tcNode.getProperties().size());
            for (int i = 0; i < fmNode.getProperties().size(); i++) {
                assertEquals(fmNode.getProperties().get(i).getKey(), tcNode.getProperties().get(i).getKey());
                assertEquals(fmNode.getProperties().get(i).getValue(), tcNode.getProperties().get(i).getValue());
            }
            if (fmNode.getRefuuid() != null) {
                byte[] content = EditResourceUtil.INSTANCE.getFileContent(fmNode.getRefuuid(), projectInfo.getId().longValue());
                AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(content);
                assertTrue(root instanceof FMRoot);
                FMRoot fmRoot = (FMRoot) root;
                fmNode = fmRoot.getNode();
            }
            assertEquals(fmNode.getChildren().size(), tcNode.getChildren().size());
            for (int i = 0; i < fmNode.getChildren().size(); i++) {
                comparingFMNodeAndTCNode(fmNode.getChildren().get(i), tcNode.getChildren().get(i));
            }

        }

        private void comparingTCNodeAndTCNode(TCNode tcNode01, TCNode tcNode02) {
            assertEquals(tcNode01.getName(), tcNode02.getName());
            assertEquals(tcNode01.getChildType(), tcNode02.getChildType());
            assertEquals(tcNode01.isOptional(), tcNode02.isOptional());
            assertEquals(tcNode01.getMin(), tcNode02.getMin());
            assertEquals(tcNode01.getMax(), tcNode02.getMax());
            assertEquals(tcNode01.getProperties().size(), tcNode02.getProperties().size());
            for (int i = 0; i < tcNode01.getProperties().size(); i++) {
                assertEquals(tcNode01.getProperties().get(i).getKey(), tcNode02.getProperties().get(i).getKey());
                assertEquals(tcNode01.getProperties().get(i).getValue(), tcNode02.getProperties().get(i).getValue());
            }
            assertEquals(tcNode01.getChildren().size(), tcNode02.getChildren().size());
            for (int i = 0; i < tcNode01.getChildren().size(); i++) {
                comparingTCNodeAndTCNode(tcNode01.getChildren().get(i), tcNode02.getChildren().get(i));
            }
        }
    }
}
