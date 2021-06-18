package com.zipc.garden.fsm.simulator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.zipc.garden.model.arc.ARCLine;
import com.zipc.garden.model.arc.ARCRoot;
import com.zipc.garden.model.arc.ARCState;
import com.zipc.garden.model.bps.BPSDataflow;
import com.zipc.garden.model.bps.BPSFactory;
import com.zipc.garden.model.bps.BPSInstanceArc;
import com.zipc.garden.model.bps.BPSInstancePseudoStateType;
import com.zipc.garden.model.bps.BPSInstanceState;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;
import com.zipc.garden.model.bps.BPSInstanceTransition;
import com.zipc.garden.model.bps.BPSVariable;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.model.fsm.FSMDTransition;
import com.zipc.garden.model.fsm.FSMDVariable;
import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.server.logic.ProjectServiceLogic;
import com.zipc.garden.webplatform.server.logic.UserServiceLogic;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

//JUnit5はテスト順制御アノテーションが未サポートなのでJUnit4を使う
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FSMSimulatorTest {

    private static Path userFilePath = Paths.get("test/resources/FSMSimulatorTest/environment/user.txt");

    private static Path projectFilePath = Paths.get("test/resources/FSMSimulatorTest/environment/project.txt");

    private static Path contentsFolderPath = Paths.get("test/resources/FSMSimulatorTest/content/");

    /**
     * Add user, project, content(arc, fsm)
     */
    @BeforeClass
    public static void setup() {
        File userFile = userFilePath.toFile();
        DBUtil.INSTANCE.createUser(userFile.getAbsolutePath());
        Long userId = DBUtil.INSTANCE.getUserId(userFile.getAbsolutePath());

        File projectFile = projectFilePath.toFile();
        DBUtil.INSTANCE.createProject(projectFile.getAbsolutePath(), userId);
        Long projectId = DBUtil.INSTANCE.getProjectId(projectFile.getAbsolutePath(), userId);

        List<File> contentFiles = getAllContents();
        for (File contentFile : contentFiles) {
            Long fileId = DBUtil.INSTANCE.uploadFile(contentFile.getAbsolutePath(), userId, projectId);
            System.out.println(contentFile.getAbsolutePath() + " fileId:" + fileId);
        }
    }

    @Test
    public void test01_2CarsArc_Car1moveOnce() {
        VMFile vmFile = getVMFile("2CarsArc.arc");
        BPSInstanceArc bpsArc = getBpsArc(vmFile.getId());
        // long fileId = vmFile.getId();
        IFSMSimulator sim = FSMSimulatorUtil.createSimulator();
        assertTrue(sim.configure(bpsArc));

        // Initial
        assertEquals("Other", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));

        // Event Car1 OtoF
        assertTrue(sim.setFsmEvent("Car1", "OtoF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertEquals("F", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car3", "Car1"));
    }

    @Test
    public void test02_2CarsArc_Car1moveTwice() {
        VMFile vmFile = getVMFile("2CarsArc.arc");
        BPSInstanceArc bpsArc = getBpsArc(vmFile.getId());
        // long fileId = vmFile.getId();
        IFSMSimulator sim = FSMSimulatorUtil.createSimulator();
        assertTrue(sim.configure(bpsArc));

        // Initial
        assertEquals("Other", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));

        // Event Car1 OtoF
        assertTrue(sim.setFsmEvent("Car1", "OtoF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertEquals("F", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car3", "Car1"));

        // Event Car1 FtoRF
        assertTrue(sim.setFsmEvent("Car1", "FtoRF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertEquals("RF", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("RF", sim.getFsmVarCurrentValue("Car3", "Car1"));
    }

    @Test
    public void test03_2CarsArc_Car1moveWithNoCondition() {
        VMFile vmFile = getVMFile("2CarsArc.arc");
        BPSInstanceArc bpsArc = getBpsArc(vmFile.getId());
        // long fileId = vmFile.getId();
        IFSMSimulator sim = FSMSimulatorUtil.createSimulator();
        assertTrue(sim.configure(bpsArc));

        // Initial
        assertEquals("Other", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));

        // Event Car1 OtoF
        assertTrue(sim.setFsmEvent("Car1", "OtoF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertEquals("F", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car3", "Car1"));

        // Event Car1 FtoLF with no condition
        assertTrue(sim.setFsmEvent("Car1", "FtoLF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertEquals("LF", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("LF", sim.getFsmVarCurrentValue("Car3", "Car1"));
    }

    @Test
    public void test04_2CarsArc_Car1andCar3moveConflict() {
        VMFile vmFile = getVMFile("2CarsArc.arc");
        BPSInstanceArc bpsArc = getBpsArc(vmFile.getId());
        // long fileId = vmFile.getId();
        IFSMSimulator sim = FSMSimulatorUtil.createSimulator();
        assertTrue(sim.configure(bpsArc));

        // Initial
        assertEquals("Other", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));

        // Event Car1 OtoF
        assertTrue(sim.setFsmEvent("Car1", "OtoF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertEquals("F", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car3", "Car1"));

        // Event Car1 FtoLF
        assertTrue(sim.setFsmEvent("Car1", "FtoRF"));
        assertTrue(sim.setFsmEvent("Car3", "OtoRF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertTrue(sim.clearFsmEvent("Car3"));
        assertEquals("F", sim.getFsmCurrentState("Car1"));
        assertEquals("RF", sim.getFsmCurrentState("Car3"));
        assertEquals("RF", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car3", "Car1"));
    }

    @Test
    public void test05_2CarsArc_Car1andCar3moveConflict() {
        VMFile vmFile = getVMFile("2CarsArc.arc");
        BPSInstanceArc bpsArc = getBpsArc(vmFile.getId());
        // long fileId = vmFile.getId();
        IFSMSimulator sim = FSMSimulatorUtil.createSimulator();
        assertTrue(sim.configure(bpsArc));

        // Initial
        assertEquals("Other", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));

        // Event Car3 OtoRF
        assertTrue(sim.setFsmEvent("Car3", "OtoRF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car3"));
        assertEquals("Other", sim.getFsmCurrentState("Car1"));
        assertEquals("RF", sim.getFsmCurrentState("Car3"));
        assertEquals("RF", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car3", "Car1"));

        // Event Car1 OtoF and Car3 RFtoF Conflict
        assertTrue(sim.setFsmEvent("Car1", "OtoF"));
        assertTrue(sim.setFsmEvent("Car3", "RFtoF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertTrue(sim.clearFsmEvent("Car3"));
        assertEquals("Other", sim.getFsmCurrentState("Car1"));
        assertEquals("F", sim.getFsmCurrentState("Car3"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car3", "Car1"));
    }

    @Test
    public void test06_2CarsArc_Car1andCar3moveConflictChangePriority() {
        VMFile vmFile = getVMFile("2CarsArc.arc");
        BPSInstanceArc bpsArc = getBpsArc(vmFile.getId());
        // long fileId = vmFile.getId();
        IFSMSimulator sim = FSMSimulatorUtil.createSimulator();
        assertTrue(sim.configure(bpsArc));

        // Priority
        Map<String, Integer> fsmPriorityMap = new LinkedHashMap<>();
        fsmPriorityMap.put("Car1", 1);
        fsmPriorityMap.put("Car3", 2);
        assertTrue(sim.setFsmPriority(fsmPriorityMap));

        // Initial
        assertEquals("Other", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));

        // Event Car1 OtoF
        assertTrue(sim.setFsmEvent("Car1", "OtoF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertEquals("F", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car3", "Car1"));

        // Event Car1 FtoRF and Car3 OtoRF Conflict
        assertTrue(sim.setFsmEvent("Car1", "FtoRF"));
        assertTrue(sim.setFsmEvent("Car3", "OtoRF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertTrue(sim.clearFsmEvent("Car3"));
        assertEquals("RF", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("RF", sim.getFsmVarCurrentValue("Car3", "Car1"));
    }

    @Test
    public void test11_3CarsArc_Car1moveOnce() {
        VMFile vmFile = getVMFile("3CarsArc.arc");
        BPSInstanceArc bpsArc = getBpsArc(vmFile.getId());
        // long fileId = vmFile.getId();
        IFSMSimulator sim = FSMSimulatorUtil.createSimulator();
        assertTrue(sim.configure(bpsArc));

        // Initial
        assertEquals("Other", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("Other", sim.getFsmCurrentState("Car6"));

        // Event Car1 OtoF
        assertTrue(sim.setFsmEvent("Car1", "OtoF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertEquals("F", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("Other", sim.getFsmCurrentState("Car6"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car6"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car3", "Car1"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car3", "Car6"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car6", "Car1"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car6", "Car3"));
    }

    @Test
    public void test12_3CarsArc_Car1moveTwice() {
        VMFile vmFile = getVMFile("3CarsArc.arc");
        BPSInstanceArc bpsArc = getBpsArc(vmFile.getId());
        // long fileId = vmFile.getId();
        IFSMSimulator sim = FSMSimulatorUtil.createSimulator();
        assertTrue(sim.configure(bpsArc));

        // Initial
        assertEquals("Other", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("Other", sim.getFsmCurrentState("Car6"));

        // Event Car1 OtoF
        assertTrue(sim.setFsmEvent("Car1", "OtoF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertEquals("F", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("Other", sim.getFsmCurrentState("Car6"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car6"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car3", "Car1"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car3", "Car6"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car6", "Car1"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car6", "Car3"));

        // Event Car1 FtoRF
        assertTrue(sim.setFsmEvent("Car1", "FtoRF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertEquals("RF", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("Other", sim.getFsmCurrentState("Car6"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car6"));
        assertEquals("RF", sim.getFsmVarCurrentValue("Car3", "Car1"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car3", "Car6"));
        assertEquals("RF", sim.getFsmVarCurrentValue("Car6", "Car1"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car6", "Car3"));
    }

    @Test
    public void test13_3CarsArc_Car1Car3Car6moveConflict() {
        VMFile vmFile = getVMFile("3CarsArc.arc");
        BPSInstanceArc bpsArc = getBpsArc(vmFile.getId());
        // long fileId = vmFile.getId();
        IFSMSimulator sim = FSMSimulatorUtil.createSimulator();
        assertTrue(sim.configure(bpsArc));

        // Initial
        assertEquals("Other", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("Other", sim.getFsmCurrentState("Car6"));

        // Event Car1 OtoF
        assertTrue(sim.setFsmEvent("Car1", "OtoF"));
        assertTrue(sim.setFsmEvent("Car6", "OtoLF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertTrue(sim.clearFsmEvent("Car6"));
        assertEquals("F", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("LF", sim.getFsmCurrentState("Car6"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("LF", sim.getFsmVarCurrentValue("Car1", "Car6"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car3", "Car1"));
        assertEquals("LF", sim.getFsmVarCurrentValue("Car3", "Car6"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car6", "Car1"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car6", "Car3"));

        // Event Car1 FtoRF and Car3 OtoRF and Car6 LFtoF
        assertTrue(sim.setFsmEvent("Car1", "FtoRF"));
        assertTrue(sim.setFsmEvent("Car3", "OtoRF"));
        assertTrue(sim.setFsmEvent("Car6", "LFtoF"));
        assertTrue(sim.execute());
        assertTrue(sim.clearFsmEvent("Car1"));
        assertTrue(sim.clearFsmEvent("Car3"));
        assertTrue(sim.clearFsmEvent("Car6"));
        assertEquals("RF", sim.getFsmCurrentState("Car1"));
        assertEquals("Other", sim.getFsmCurrentState("Car3"));
        assertEquals("F", sim.getFsmCurrentState("Car6"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car1", "Car3"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car1", "Car6"));
        assertEquals("RF", sim.getFsmVarCurrentValue("Car3", "Car1"));
        assertEquals("F", sim.getFsmVarCurrentValue("Car3", "Car6"));
        assertEquals("RF", sim.getFsmVarCurrentValue("Car6", "Car1"));
        assertEquals("Other", sim.getFsmVarCurrentValue("Car6", "Car3"));
    }

    /**
     * Remove user, project, content(arc, fsm)
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
        File userFile = userFilePath.toFile();
        Long userId = DBUtil.INSTANCE.getUserId(userFile.getAbsolutePath());
        return userId;
    }

    private static Long getProjectId() {
        File projectFile = projectFilePath.toFile();
        Long projectId = DBUtil.INSTANCE.getProjectId(projectFile.getAbsolutePath(), getUserId());
        return projectId;
    }

    private static List<File> getAllContents() {
        return getContents(contentsFolderPath.toFile());
    }

    private static List<File> getContents(File file) {
        List<File> contents = new ArrayList<>();
        for (File childFile : Arrays.asList(file.listFiles())) {
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

    private BPSInstanceArc getBpsArc(Long fileId) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            com.zipc.garden.webplatform.dao.File file = session.byId(com.zipc.garden.webplatform.dao.File.class).load(fileId);
            EObject arcRootEObject = EditResourceUtil.INSTANCE.convertToRootElement(file.getContent());
            if (!file.getExtension().equals(Extension.ARC.getValue()) || !(arcRootEObject instanceof ARCRoot))
                throw new IllegalArgumentException("Invalid binary data for a ARCRoot Model.");

            return createInstanceArc((ARCRoot) arcRootEObject, new ArrayList<String>(), file.getProjectid());
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Comparator<BPSInstanceStateMachine> stmComparator = Comparator.comparing(BPSInstanceStateMachine::getOriginalName);

    /*
     * BPS内のインスタンス情報を生成する際の，Arcモデルの変換処理
     */
    private BPSInstanceArc createInstanceArc(ARCRoot arcRoot, List<String> activeStmNames, long projectId) {
        // BPSInstanceArcの作成
        BPSInstanceArc newBpsArc = BPSFactory.eINSTANCE.createBPSInstanceArc();
        newBpsArc.setOriginalUuid(arcRoot.getId());

        // BPSInstanceStateMachineの作成
        Map<ARCState, BPSInstanceStateMachine> arcMap = new HashMap<ARCState, BPSInstanceStateMachine>();
        for (ARCState arcState : arcRoot.getStates()) {
            String bpsStmOriginalName = getOriginalName(arcState.getName(), arcState.getFileId(), projectId);
            BPSInstanceStateMachine newBpsStm = createInstanceStateMachine(arcState, bpsStmOriginalName,
                    activeStmNames.isEmpty() ? true : activeStmNames.remove(bpsStmOriginalName), projectId);
            arcMap.put(arcState, newBpsStm);
            newBpsArc.getStateMachines().add(newBpsStm);
        }

        // BPSDataflowの作成
        for (ARCLine arcLine : arcRoot.getLines()) {
            BPSDataflow newBpsDataflow = BPSFactory.eINSTANCE.createBPSDataflow();
            newBpsDataflow.setSource(arcMap.get(arcLine.getSource()));
            newBpsDataflow.setTarget(getBPSDataflowTarget(arcMap.get(arcLine.getTarget()), arcLine.getVariableName()));
            newBpsArc.getDataflows().add(newBpsDataflow);
        }

        return newBpsArc;
    }

    /*
     * BPS内のインスタンス情報を生成する際の，StateMachineモデルの変換処理
     */
    private BPSInstanceStateMachine createInstanceStateMachine(ARCState arcState, String bpsStmOriginalName, boolean isActived, long projectId) {
        // BPSInstanceStateMachineの作成
        BPSInstanceStateMachine newBpsStm = BPSFactory.eINSTANCE.createBPSInstanceStateMachine();
        newBpsStm.setEnabled(isActived);
        newBpsStm.setOriginalName(bpsStmOriginalName);
        newBpsStm.setOriginalUuid(arcState.getFileId());
        newBpsStm.setEvalPriority(arcState.getEvalPriority());

        // read FSMDStateMachine from DB
        byte[] fsmFileContent = EditResourceUtil.INSTANCE.getFileContent(arcState.getFileId(), projectId);
        FSMDStateMachine fsmdStm = (FSMDStateMachine) EditResourceUtil.INSTANCE.convertToRootElement(fsmFileContent);

        // BPSInstanceStateの作成
        Map<FSMDState, BPSInstanceState> stateMap = new HashMap<FSMDState, BPSInstanceState>();
        if (fsmdStm.getRegions().size() > 0) {
            for (FSMDState fsmdState : fsmdStm.getRegions().get(0).getStates()) {
                BPSInstanceState newBpsState = BPSFactory.eINSTANCE.createBPSInstanceState();
                newBpsState.setEnabled(isActived);
                stateMap.put(fsmdState, newBpsState);
                newBpsStm.getStates().add(newBpsState);
            }

            FSMDState fsmdState;
            BPSInstanceState newBpsState;
            for (int i = 0; i < fsmdStm.getRegions().get(0).getStates().size(); i++) {
                fsmdState = fsmdStm.getRegions().get(0).getStates().get(i);
                newBpsState = newBpsStm.getStates().get(i);
                newBpsState.setOriginalName(fsmdState.getName());
                newBpsState.setType(BPSInstancePseudoStateType.getByName(fsmdState.getType().getName()));

                // newBpsTransitionの作成
                BPSInstanceTransition newBpsTransition;
                for (FSMDTransition fsmdTransition : fsmdState.getOutgoingTransition()) {
                    newBpsTransition = BPSFactory.eINSTANCE.createBPSInstanceTransition();
                    newBpsTransition.setEnabled(isActived);
                    newBpsTransition.setPriority(fsmdTransition.getPriority());
                    newBpsTransition.setEvent(fsmdTransition.getEvent());
                    newBpsTransition.setCondition(fsmdTransition.getCondition());
                    newBpsTransition.setAction(fsmdTransition.getAction());
                    newBpsTransition.setSource(newBpsState);
                    newBpsTransition.setTarget(stateMap.get(fsmdTransition.getTarget()));
                    newBpsState.getTransitions().add(newBpsTransition);
                }

                // initialStateの設定
                if (BPSInstancePseudoStateType.INITIAL == newBpsState.getType()) {
                    newBpsStm.setInitialState(newBpsState);
                }
            }
        }

        // BPSVariableの作成
        BPSVariable newBpsVariable;
        for (FSMDVariable fsmdVariable : fsmdStm.getVariables()) {
            newBpsVariable = BPSFactory.eINSTANCE.createBPSVariable();
            newBpsVariable.setType(fsmdVariable.getType());
            newBpsVariable.setName(fsmdVariable.getName());
            newBpsStm.getVariables().add(newBpsVariable);
        }

        return newBpsStm;
    }

    private BPSVariable getBPSDataflowTarget(BPSInstanceStateMachine bpsStm, String varibaleName) {
        if (bpsStm == null || varibaleName == null || varibaleName.isEmpty())
            return null;

        for (BPSVariable bpsVariable : bpsStm.getVariables()) {
            if (varibaleName.equals(bpsVariable.getName())) {
                return bpsVariable;
            }
        }

        return null;
    }

    private String getOriginalName(String name, String fileId, long projectId) {
        if (name != null && !name.isEmpty())
            return name;

        VMFile file = EditResourceUtil.INSTANCE.getVMFile(fileId, projectId);
        if (file == null)
            return "";

        return file.getName();
    }
}
