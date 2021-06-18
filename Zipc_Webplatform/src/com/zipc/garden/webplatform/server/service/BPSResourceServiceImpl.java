package com.zipc.garden.webplatform.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
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
import com.zipc.garden.model.bps.BPSOption;
import com.zipc.garden.model.bps.BPSRoot;
import com.zipc.garden.model.bps.BPSVariable;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.model.fsm.FSMDTransition;
import com.zipc.garden.model.fsm.FSMDVariable;
import com.zipc.garden.webplatform.client.editor.bps.BPSResourceService;
import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the BPSResourceService interface.
 */
@SuppressWarnings("serial")
public class BPSResourceServiceImpl extends RemoteServiceServlet implements BPSResourceService {

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getFileContent(long fileId, byte[] bpsRootContent) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            // read old root
            EObject bpsRootEObject = EditResourceUtil.INSTANCE.convertToRootElement(bpsRootContent);
            if (!(bpsRootEObject instanceof BPSRoot))
                throw new IllegalArgumentException("Invalid binary data for a BPSRoot Model.");

            BPSRoot oldBpsRoot = (BPSRoot) bpsRootEObject;
            // load DB filecontent
            File refFile = session.byId(File.class).load(fileId);
            EObject arcRootEObject = EditResourceUtil.INSTANCE.convertToRootElement(refFile.getContent());

            // load ARCRoot EObject
            if (!refFile.getExtension().equals(Extension.ARC.getValue()) || !(arcRootEObject instanceof ARCRoot))
                throw new IllegalArgumentException("Invalid binary data for a ARCRoot Model.");

            BPSOption activeOption = oldBpsRoot.getActiveOption();
            List<String> activeStmNames = activeOption.getTargetStateMachines();

            BPSInstanceArc newBpsArc = createInstanceArc((ARCRoot) arcRootEObject, activeStmNames, refFile.getProjectid());
            // Create new BPSRoot
            BPSRoot newBpsRoot = BPSFactory.eINSTANCE.createBPSRoot();
            newBpsRoot.setActiveOptionIndex(oldBpsRoot.getActiveOptionIndex());
            newBpsRoot.getOptions().addAll(oldBpsRoot.getOptions());
            newBpsRoot.getRefs().add(oldBpsRoot.getRefs().get(0));
            newBpsRoot.setId(oldBpsRoot.getId());
            newBpsRoot.setInstanceArc(newBpsArc);

            return EditResourceUtil.INSTANCE.convertToBinary(newBpsRoot);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * ARC Model conversion process when generating instance information in BPS.
     * @param arcRoot
     * @param activeStmNames
     * @param projectId
     * @return
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

        // originalName の昇順になるように，evalPriority の値を格納する
        // AtomicInteger evalPriorityValue = new AtomicInteger();
        // newBpsArc.getStateMachines().stream().sorted(stmComparator).forEach(stm ->
        // stm.setEvalPriority(evalPriorityValue.getAndIncrement()));

        // BPSDataflowの作成
        for (ARCLine arcLine : arcRoot.getLines()) {
            BPSDataflow newBpsDataflow = BPSFactory.eINSTANCE.createBPSDataflow();
            newBpsDataflow.setSource(arcMap.get(arcLine.getSource()));
            newBpsDataflow.setTarget(getBPSDataflowTarget(arcMap.get(arcLine.getTarget()), arcLine.getVariableName()));
            newBpsArc.getDataflows().add(newBpsDataflow);
        }

        return newBpsArc;
    }

    /**
     * StateMachine model conversion process when generating instance information in BPS.
     * @param arcState
     * @param bpsStmOriginalName
     * @param isActived
     * @param projectId
     * @return
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
                    newBpsTransition.setTrigger(fsmdTransition.getTrigger());
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

    /**
     * Get the BPS Dataflow corresponding to the ARCLine connection destination.
     * @param bpsStm ARCLine connection destination (ARCState)
     * @param varibaleName ARCLine variable name
     * @return
     */
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

    /**
     * Get the name of ARCState set in ARCEditor.
     * @param name Name of ARCState that is set
     * @param fileId FSM file ID associated with ARCState
     * @param projectId ID of the project that manages the ARC file
     * @return ARCState name
     */
    private String getOriginalName(String name, String fileId, long projectId) {
        if (name != null && !name.isEmpty())
            return name;

        VMFile file = EditResourceUtil.INSTANCE.getVMFile(fileId, projectId);
        if (file == null)
            return "";

        return file.getName();
    }
}
