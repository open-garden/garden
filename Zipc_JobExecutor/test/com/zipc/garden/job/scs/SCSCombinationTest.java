package com.zipc.garden.job.scs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
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
import com.zipc.garden.model.arc.ARCRoot;
import com.zipc.garden.model.bp.BPBehavior;
import com.zipc.garden.model.bp.BPBehaviorPattern;
import com.zipc.garden.model.bp.BPEvent;
import com.zipc.garden.model.bp.BPFactory;
import com.zipc.garden.model.bp.BPRoot;
import com.zipc.garden.model.bp.BPSetting;
import com.zipc.garden.model.bp.BPState;
import com.zipc.garden.model.bp.BPStateMachine;
import com.zipc.garden.model.bp.BPStep;
import com.zipc.garden.model.bps.BPSRoot;
import com.zipc.garden.model.cb.CBRoot;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.model.core.SettingInterface;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.model.fmc.FMCRoot;
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.model.scs.SCSFactory;
import com.zipc.garden.model.scs.SCSPattern;
import com.zipc.garden.model.scs.SCSPatternReference;
import com.zipc.garden.model.scs.SCSRoot;
import com.zipc.garden.model.scs.SCSSetting;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.model.tp.TPElement;
import com.zipc.garden.model.tp.TPFactory;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tp.TPTSDPattern;
import com.zipc.garden.model.tps.TPSRoot;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.server.generator.HeavyTaskContentGetter;
import com.zipc.garden.webplatform.server.logic.ProjectServiceLogic;
import com.zipc.garden.webplatform.server.logic.UserServiceLogic;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SCSCombinationTest {

    private static Path userFilePath = Paths.get("test/resources/com/zipc/garden/job/ontology/register/environment/user.txt");

    private static Path projectFilePath = Paths.get("test/resources/com/zipc/garden/job/ontology/register/environment/project.txt");

    private static Path contentsFolderPath = Paths.get("test/resources/com/zipc/garden/job/ontology/register/content");

    /**
     * Add user, project, content(fm, fmc, tc, fps, fsm, arc, bps)
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
        prepare_RegistFeatureModel();
        prepare_RegistFMConstraint();
        prepare_RegistTestCondition();
        prepare_RegistFeaturePatternSetting();
        prepare_RegistFeaturePattern();
        prepare_RegistBehaviorModel("Car1.fsm");
        prepare_RegistBehaviorModel("Car3.fsm");
        prepare_RegistArchitectureModel();
        prepare_RegistBehaviorPatternSetting();
        prepare_RegistBehaviorPattern();
    }

    public static void prepare_RegistFeatureModel() {
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

    public static void prepare_RegistFMConstraint() {
        VMFile vmFile = getVMFile("ODConstraint.fmc");
        long fileId = vmFile.getId();
        File file = EditResourceUtil.INSTANCE.getFile(fileId);
        byte[] data = EditResourceUtil.INSTANCE.getFileContent(fileId);
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        assertTrue(root instanceof FMCRoot);
        IRDFRegister register = OntologyUtils.createRegister(root, file);
        IRDFRegister.Result result = register.execute();
        assertEquals(result, IRDFRegister.Result.SUCCESS);
    }

    public static void prepare_RegistTestCondition() {
        VMFile vmFile = getVMFile("ODD.tc");
        long fileId = vmFile.getId();
        File file = EditResourceUtil.INSTANCE.getFile(fileId);
        byte[] data = EditResourceUtil.INSTANCE.getFileContent(fileId);
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        assertTrue(root instanceof TCRoot);
        IRDFRegister register = OntologyUtils.createRegister(root, file);
        IRDFRegister.Result result = register.execute();
        assertEquals(result, IRDFRegister.Result.SUCCESS);
    }

    public static void prepare_RegistFeaturePatternSetting() {
        VMFile vmFile = getVMFile("TSD.fps");
        long fileId = vmFile.getId();
        File file = EditResourceUtil.INSTANCE.getFile(fileId);
        byte[] data = EditResourceUtil.INSTANCE.getFileContent(fileId);
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        assertTrue(root instanceof TPSRoot);
        IRDFRegister register = OntologyUtils.createRegister(root, file);
        IRDFRegister.Result result = register.execute();
        assertEquals(result, IRDFRegister.Result.SUCCESS);
    }

    public static void prepare_RegistFeaturePattern() {
        VMFile vmFile = getVMFile("TSD.fps");
        long fileId = vmFile.getId();
        File file = EditResourceUtil.INSTANCE.getFile(fileId);
        byte[] data = EditResourceUtil.INSTANCE.getFileContent(fileId);
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        assertTrue(root instanceof TPSRoot);

        TPRoot tpRoot = TPFactory.eINSTANCE.createTPRoot();

        TPSetting tpSetting = TPFactory.eINSTANCE.createTPSetting();
        tpRoot.getSettings().add(tpSetting);

        TPElement elementFine = TPFactory.eINSTANCE.createTPElement();
        elementFine.setFullPath("RootNode.Weather.Fine");
        tpSetting.getElements().add(elementFine);
        TPElement elementRain = TPFactory.eINSTANCE.createTPElement();
        elementRain.setFullPath("RootNode.Weather.Rain");
        tpSetting.getElements().add(elementRain);
        TPElement elementHighway = TPFactory.eINSTANCE.createTPElement();
        elementHighway.setFullPath("RootNode.Road.Highway");
        tpSetting.getElements().add(elementHighway);
        TPElement elementUrban = TPFactory.eINSTANCE.createTPElement();
        elementUrban.setFullPath("RootNode.Road.Urban");
        tpSetting.getElements().add(elementUrban);

        TPTSDPattern pattern1 = TPFactory.eINSTANCE.createTPTSDPattern();
        pattern1.setId("1");
        pattern1.getElements().add(elementFine);
        pattern1.getElements().add(elementHighway);
        tpSetting.getPatterns().add(pattern1);
        TPTSDPattern pattern2 = TPFactory.eINSTANCE.createTPTSDPattern();
        pattern2.setId("2");
        pattern2.getElements().add(elementFine);
        pattern2.getElements().add(elementUrban);
        tpSetting.getPatterns().add(pattern2);
        TPTSDPattern pattern3 = TPFactory.eINSTANCE.createTPTSDPattern();
        pattern3.setId("3");
        pattern3.getElements().add(elementRain);
        pattern3.getElements().add(elementHighway);
        tpSetting.getPatterns().add(pattern3);
        TPTSDPattern pattern4 = TPFactory.eINSTANCE.createTPTSDPattern();
        pattern4.setId("4");
        pattern4.getElements().add(elementRain);
        pattern4.getElements().add(elementUrban);
        tpSetting.getPatterns().add(pattern4);

        IRDFRegister register = OntologyUtils.createRegister(tpRoot, file);
        IRDFRegister.Result result = register.execute();
        assertEquals(result, IRDFRegister.Result.SUCCESS);
    }

    public static void prepare_RegistBehaviorModel(String fileName) {
        VMFile vmFile = getVMFile(fileName);
        long fileId = vmFile.getId();
        File file = EditResourceUtil.INSTANCE.getFile(fileId);
        byte[] data = EditResourceUtil.INSTANCE.getFileContent(fileId);
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        assertTrue(root instanceof FSMDStateMachine);
        IRDFRegister register = OntologyUtils.createRegister(root, file);
        IRDFRegister.Result result = register.execute();
        assertEquals(result, IRDFRegister.Result.SUCCESS);
    }

    public static void prepare_RegistArchitectureModel() {
        VMFile vmFile = getVMFile("Cutin.arc");
        long fileId = vmFile.getId();
        File file = EditResourceUtil.INSTANCE.getFile(fileId);
        byte[] data = EditResourceUtil.INSTANCE.getFileContent(fileId);
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        assertTrue(root instanceof ARCRoot);
        IRDFRegister register = OntologyUtils.createRegister(root, file);
        IRDFRegister.Result result = register.execute();
        assertEquals(result, IRDFRegister.Result.SUCCESS);
    }

    public static void prepare_RegistBehaviorPatternSetting() {
        VMFile vmFile = getVMFile("Cutin.bps");
        long fileId = vmFile.getId();
        File file = EditResourceUtil.INSTANCE.getFile(fileId);
        byte[] data = EditResourceUtil.INSTANCE.getFileContent(fileId);
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        assertTrue(root instanceof BPSRoot);
        IRDFRegister register = OntologyUtils.createRegister(root, file);
        IRDFRegister.Result result = register.execute();
        assertEquals(result, IRDFRegister.Result.SUCCESS);
    }

    public static void prepare_RegistBehaviorPattern() {
        VMFile bpsVmFile = getVMFile("Cutin.bps");
        long bpsFileId = bpsVmFile.getId();
        File bpsFile = EditResourceUtil.INSTANCE.getFile(bpsFileId);
        byte[] bpsData = EditResourceUtil.INSTANCE.getFileContent(bpsFileId);
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(bpsData);
        assertTrue(root instanceof BPSRoot);

        BPRoot bpRoot = BPFactory.eINSTANCE.createBPRoot();

        BPSetting bpSetting = BPFactory.eINSTANCE.createBPSetting();
        bpRoot.getSettings().add(bpSetting);

        BPStateMachine bpStateMachine1 = BPFactory.eINSTANCE.createBPStateMachine();
        bpStateMachine1.setName("Car1");
        BPStateMachine bpStateMachine3 = BPFactory.eINSTANCE.createBPStateMachine();
        bpStateMachine3.setName("Car3");

        BPEvent bpEvent1 = BPFactory.eINSTANCE.createBPEvent();
        bpEvent1.setValue(null);
        BPEvent bpEvent2 = BPFactory.eINSTANCE.createBPEvent();
        bpEvent2.setValue("OtoRF");
        BPEvent bpEvent3 = BPFactory.eINSTANCE.createBPEvent();
        bpEvent3.setValue("RFtoF");

        BPState bpState1 = BPFactory.eINSTANCE.createBPState();
        bpState1.setValue("Out");
        BPState bpState2 = BPFactory.eINSTANCE.createBPState();
        bpState2.setValue("RF");
        BPState bpState3 = BPFactory.eINSTANCE.createBPState();
        bpState3.setValue("F");

        BPStep step11 = BPFactory.eINSTANCE.createBPStep();
        step11.setEvent(null);
        step11.setState(EcoreUtil.copy(bpState1));
        BPStep step12 = BPFactory.eINSTANCE.createBPStep();
        step12.setEvent(EcoreUtil.copy(bpEvent1));
        step12.setState(EcoreUtil.copy(bpState1));
        BPStep step13 = BPFactory.eINSTANCE.createBPStep();
        step13.setEvent(EcoreUtil.copy(bpEvent1));
        step13.setState(EcoreUtil.copy(bpState1));
        BPStep step14 = BPFactory.eINSTANCE.createBPStep();
        step14.setEvent(EcoreUtil.copy(bpEvent1));
        step14.setState(EcoreUtil.copy(bpState1));

        BPStep step21 = BPFactory.eINSTANCE.createBPStep();
        step21.setEvent(null);
        step21.setState(EcoreUtil.copy(bpState1));
        BPStep step22 = BPFactory.eINSTANCE.createBPStep();
        step22.setEvent(EcoreUtil.copy(bpEvent2));
        step22.setState(EcoreUtil.copy(bpState2));
        BPStep step23 = BPFactory.eINSTANCE.createBPStep();
        step23.setEvent(EcoreUtil.copy(bpEvent3));
        step23.setState(EcoreUtil.copy(bpState3));
        BPStep step24 = BPFactory.eINSTANCE.createBPStep();
        step24.setEvent(EcoreUtil.copy(bpEvent1));
        step24.setState(EcoreUtil.copy(bpState3));

        BPBehavior bpBehavior1 = BPFactory.eINSTANCE.createBPBehavior();
        bpBehavior1.setStateMachineRef(EcoreUtil.copy(bpStateMachine1));
        bpBehavior1.setStateMachineRef(EcoreUtil.copy(bpStateMachine3));
        bpBehavior1.getSteps().add(step11);
        bpBehavior1.getSteps().add(step12);
        bpBehavior1.getSteps().add(step13);
        bpBehavior1.getSteps().add(step14);

        BPBehavior bpBehavior2 = BPFactory.eINSTANCE.createBPBehavior();
        bpBehavior2.setStateMachineRef(EcoreUtil.copy(bpStateMachine1));
        bpBehavior2.setStateMachineRef(EcoreUtil.copy(bpStateMachine3));
        bpBehavior2.getSteps().add(step21);
        bpBehavior2.getSteps().add(step22);
        bpBehavior2.getSteps().add(step23);
        bpBehavior2.getSteps().add(step24);

        BPBehaviorPattern ptn1 = BPFactory.eINSTANCE.createBPBehaviorPattern();
        ptn1.setId("1");
        ptn1.getBehaviors().add(bpBehavior1);

        BPBehaviorPattern ptn2 = BPFactory.eINSTANCE.createBPBehaviorPattern();
        ptn2.setId("2");
        ptn2.getBehaviors().add(bpBehavior2);

        bpSetting.getPattern().add(ptn1);
        bpSetting.getPattern().add(ptn2);

        IRDFRegister register = OntologyUtils.createRegister(bpRoot, bpsFile);
        IRDFRegister.Result result = register.execute();
        assertEquals(result, IRDFRegister.Result.SUCCESS);
    }

    /**
     * Remove user, project, content(fm, fmc, tc, fps, fsm, arc, bps)
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
        List<java.io.File> result = new ArrayList<>();
        result.addAll(getContents(contentsFolderPath.toFile()));
        return result;
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

    private static VMFile getVMFile(String file) {
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

    @Test
    public <T extends AbstractRootElement & SettingInterface> void test01_Combination() {
        HeavyTaskContentGetter<T> getter = new HeavyTaskContentGetter<>();
        VMFile scssVmFile = getVMFile("SCSS.scss");
        File scssFile = EditResourceUtil.INSTANCE.getFile(scssVmFile.getId());
        byte[] scssData = EditResourceUtil.INSTANCE.getFileContent(scssVmFile.getId());
        AbstractRootElement scssRoot = EditResourceUtil.INSTANCE.convertToRootElement(scssData);
        assertTrue(scssRoot instanceof CBRoot);
        IRDFRegister scssRegister = OntologyUtils.createRegister(scssRoot, scssFile);
        IRDFRegister.Result scssResult = scssRegister.execute();
        assertEquals(scssResult, IRDFRegister.Result.SUCCESS);

        Reference reference = COREFactory.eINSTANCE.createReference();
        reference.setRefid(scssFile.getUuid());
        reference.setRefName(scssFile.getName());
        reference.setRefExtension(scssFile.getExtension());
        reference.setHash(scssFile.getHash());

        VMFile scsVmFile = getVMFile("SCSS.scs");
        File scsFile = EditResourceUtil.INSTANCE.getFile(scsVmFile.getId());

        SCSRoot scsRoot = SCSFactory.eINSTANCE.createSCSRoot();
        scsRoot.getRefs().add(reference);

        Map<String, AbstractSetting> patternRootMap = new HashMap<>();
        for (Reference ref : scssRoot.getRefs()) {
            AbstractSetting setting = null;
            if (ref.getRefExtension().equals(Extension.BPS.getValue()) || ref.getRefExtension().equals(Extension.FPS.getValue())) {
                List<File> files = EditResourceUtil.INSTANCE.getChildFile(ref.getRefid(), scssFile.getProjectid());
                File file = files.stream().filter(f -> {
                    switch (Extension.getByCode(f.getExtension())) {
                    case FP:
                    case BP:
                        return true;
                    default:
                        return false;
                    }
                }).findFirst().get();
                AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(file.getContent());
                getter.setSettings((T) root, scssFile.getProjectid());

                SettingInterface settingInterface = (SettingInterface) root;
                setting = settingInterface.getSettings().get(0);
            }
            EditResourceUtil.INSTANCE.setPattern(scssFile.getProjectid(), ref.getRefid(), setting, null);
            patternRootMap.put(ref.getRefid(), setting);
        }

        Map<SCSPatternReference, String> scsPtnRefMap = new HashMap<>();
        SCSPtnRefGenerator gen = new SCSPtnRefGenerator();
        scsPtnRefMap = gen.execute((CBRoot) scssRoot, patternRootMap, scssFile.getProjectid());

        // create patterns.
        SCSCombinationLogic logic = new SCSCombinationLogic();
        List<List<SCSPatternReference>> scsPtnRefList = logic.execute((CBRoot) scssRoot, scsPtnRefMap);
        int i = 1;
        scsRoot.getSettings().clear();
        SCSSetting setting = SCSFactory.eINSTANCE.createSCSSetting();
        setting.setUuid(scssFile.getUuid());
        setting.setSettingHash(scssFile.getHash());
        scsRoot.getSettings().add(setting);
        for (List<SCSPatternReference> refList : scsPtnRefList) {
            SCSPattern ptn = SCSFactory.eINSTANCE.createSCSPattern();
            ptn.setId(String.valueOf(i++));
            for (SCSPatternReference ref : refList) {
                SCSPatternReference refPtn = SCSFactory.eINSTANCE.createSCSPatternReference();
                refPtn.setFileId(ref.getFileId());
                refPtn.setPatternId(ref.getPatternId());
                ptn.getPatternreferences().add(refPtn);
            }
            setting.getPatterns().add(ptn);
        }
        getter.setSettings((T) scsRoot, scssFile.getProjectid());

        // savePattern number
        scsRoot.setAll(setting.getPatterns().size());
        EditResourceUtil.INSTANCE.saveFile(scsFile.getId(), EditResourceUtil.INSTANCE.convertToBinary(scsRoot), null);

        // create scenarioset to RDF.
        IRDFRegister scsRegister = OntologyUtils.createRegister(scsRoot, scssFile);
        IRDFRegister.Result scsResult = scsRegister.execute();
        assertEquals(scsResult, IRDFRegister.Result.SUCCESS);

        // ----------------------------------------
        // Test result: normal case
        // ----------------------------------------
        EditResourceUtil.INSTANCE.getSCSFileByRecord(scsFile.getProjectid(), 0, 100, setting);
        assertEquals(setting.getPatterns().size(), 8); // fp:4 * bp:2 = 8
        assertEquals(setting.getBegin(), 0);
        assertEquals(setting.getEnd(), setting.getPatterns().size());
        assertEquals(setting.getPatterns().get(0).getPatternValue(), "Cutin-1,TSD-1");
        assertEquals(setting.getPatterns().get(1).getPatternValue(), "Cutin-1,TSD-2");
        assertEquals(setting.getPatterns().get(2).getPatternValue(), "Cutin-1,TSD-3");
        assertEquals(setting.getPatterns().get(3).getPatternValue(), "Cutin-1,TSD-4");
        assertEquals(setting.getPatterns().get(4).getPatternValue(), "Cutin-2,TSD-1");
        assertEquals(setting.getPatterns().get(5).getPatternValue(), "Cutin-2,TSD-2");
        assertEquals(setting.getPatterns().get(6).getPatternValue(), "Cutin-2,TSD-3");
        assertEquals(setting.getPatterns().get(7).getPatternValue(), "Cutin-2,TSD-4");

        // ----------------------------------------
        // Test result: Searched by RowId
        // ----------------------------------------
        setting.setPatternNos("1, 5");
        EditResourceUtil.INSTANCE.getSCSFileByRecord(scsFile.getProjectid(), 0, 100, setting);
        assertEquals(setting.getPatterns().size(), 2); // Row:1,5
        assertEquals(setting.getBegin(), 0);
        assertEquals(setting.getEnd(), setting.getPatterns().size());
        assertEquals(setting.getPatterns().get(0).getPatternValue(), "Cutin-1,TSD-1");
        assertEquals(setting.getPatterns().get(1).getPatternValue(), "Cutin-2,TSD-1");

        // ----------------------------------------
        // Test result: When referring source file is deleted
        // ----------------------------------------
        List<VMFile> vmFiles = EditResourceUtil.INSTANCE.getVMFiles(getProjectId());
        List<VMResource> vmResources = new ArrayList<>();
        vmFiles.stream().filter(vmFile -> {
            switch (vmFile.getExtension()) {
            case FPS:
            case FP:
            case BPS:
            case BP:
                return true;
            default:
                return false;
            }
        }).forEach(vmFile -> vmResources.add((VMResource) vmFile));
        EditResourceUtil.INSTANCE.removeResources(vmResources, null);
        setting.setPatternNos("");
        EditResourceUtil.INSTANCE.getSCSFileByRecord(scsFile.getProjectid(), 0, 100, setting);
        assertEquals(setting.getPatterns().size(), 8); // fp:4 * bp:2 = 8
        assertEquals(setting.getBegin(), 0);
        assertEquals(setting.getEnd(), setting.getPatterns().size());
        assertEquals(setting.getPatterns().get(0).getPatternValue(), "Cutin-1,TSD-1");
        assertEquals(setting.getPatterns().get(1).getPatternValue(), "Cutin-1,TSD-2");
        assertEquals(setting.getPatterns().get(2).getPatternValue(), "Cutin-1,TSD-3");
        assertEquals(setting.getPatterns().get(3).getPatternValue(), "Cutin-1,TSD-4");
        assertEquals(setting.getPatterns().get(4).getPatternValue(), "Cutin-2,TSD-1");
        assertEquals(setting.getPatterns().get(5).getPatternValue(), "Cutin-2,TSD-2");
        assertEquals(setting.getPatterns().get(6).getPatternValue(), "Cutin-2,TSD-3");
        assertEquals(setting.getPatterns().get(7).getPatternValue(), "Cutin-2,TSD-4");
    }
}
