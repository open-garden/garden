package com.zipc.garden.job.bp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zipc.garden.job.JobExecutorThread;
import com.zipc.garden.job.bp.nlength.NLengthBehaviorPatternGenerator;
import com.zipc.garden.job.ontology.OntologyUtils;
import com.zipc.garden.job.ontology.register.IRDFRegister.Result;
import com.zipc.garden.model.bp.BPBehavior;
import com.zipc.garden.model.bp.BPBehaviorPattern;
import com.zipc.garden.model.bp.BPFactory;
import com.zipc.garden.model.bp.BPRoot;
import com.zipc.garden.model.bp.BPSetting;
import com.zipc.garden.model.bps.BPSNSwitchOption;
import com.zipc.garden.model.bps.BPSOption;
import com.zipc.garden.model.bps.BPSPathCombinationOption;
import com.zipc.garden.model.bps.BPSRoot;
import com.zipc.garden.model.bps.BPSStateCombinationOption;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.dao.rdf.RDFUtil;
import com.zipc.garden.webplatform.dao.rdf.ontology.BPO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * This class is used to generate Behavior Pattern.
 */
public class BehaviorPatternGenerator extends JobExecutorThread {

    /** Error message when reading the BPS file fails. */
    private static final String Message_FailedLoadBPS = "bps file loading is failed.";

    /** Error message when the generated operation pattern cannot be registered in RDF. */
    private static final String Message_FailedSave = "Generated Behavior Pattrens saving is failed.";

    /** Error message when the length of the path to be generated is less than or equal to zero. */
    private static final String Message_PathLengthLessOrEqualThanZero = "The Path Length must more than zero.";

    /** A fixed phrase that is always displayed at the beginning of a message when an error occurs. */
    private static final String Message_Prefix = "Behavior Pattern Generation failed. ";

    /** Job input file information (BPS file) */
    private BPSRoot inputSetting;

    /** for JUnit. */
    private BPRoot generatedResult;

    /**
     * constructor
     * @param jobId job id
     * @param projectId project id
     * @param outputFileId Behavior pattern generation file (*.bp)
     */
    public BehaviorPatternGenerator(long jobId, long projectId, long outputFileId) {
        super(jobId, projectId, outputFileId);
    }

    /** A handler that manages the BehaviorPattern generation process. */
    private IGenerationHandler<BPBehaviorPattern> generationHandler = new IGenerationHandler<BPBehaviorPattern>() {

        /** Maximum number of pattern that can be registered in RDF at one time. */
        private static final int NumOfLot = 100;

        /**
         * <pre>
         * A list that holds BehaviorPattern information to be registered.
         * Cleared for each number of {@link #NumOfLot}.
         * </pre>
         */
        private List<BPBehaviorPattern> generated;

        /** Total number of registered patterns */
        private String amount;

        /**
         * <pre>
         * Generation settings file ontology creation flag.
         * BehaviorPattern are registered in {@link #NumOfLot} units, but ontology definitions need only be registered once.
         * This flag is False for the first round of the registration process and True for the second and subsequent registration processes.
         * If False, the ontology will be registered.
         * If True, the ontology registration will be skipped.
         * </pre>
         */
        private boolean isCreatedRefOnt = false;

        /**
         * {@inheritDoc}
         */
        @Override
        public void start() {
            generated = new ArrayList<>(NumOfLot);

        }

        /**
         * <pre>
         * {@inheritDoc}
         * 
         * When the specified element is added to {@link #generated} and becomes {@link #NumOfLot} or more, the registration process is executed.
         * </pre>
         */
        @Override
        public void generated(BPBehaviorPattern newElement) throws GenerationCancellationException {
            generated.add(newElement);
            if (generated.size() >= NumOfLot) {
                saveAndClear();
                if (requestedCancel()) {
                    throw new GenerationCancellationException("Canceled.");
                } else if (checkHashChanged()) {
                    throw new GenerationCancellationException("Error.");
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void finished() {
            if (!generated.isEmpty()) {
                saveAndClear();
            }
            updateJobProgress(Job.STATUS_COMPLETE, "Generation completed.");
        }

        /**
         * The registration process to RDF is executed and {@link #generated} is cleared.
         */
        private void saveAndClear() {
            registerBP(generated, isCreatedRefOnt);
            isCreatedRefOnt = true;
            generated.clear();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setAmount(double amount) {
            this.amount = BigDecimal.valueOf(amount).toPlainString();
        }

        /**
         * <pre>
         * {@inheritDoc}
         * 
         * The progress of the JobStatus table is updated. (For each number of {@link #NumOfLot})
         * </pre>
         */
        @Override
        public void setCurrent(double current) {
            if ((current % NumOfLot) == 1) {
                String message = String.format("Progressing (2/3) Generating (%s/ %s)...", BigDecimal.valueOf(current).toPlainString(), amount);
                updateJobProgress(message);
            }
        }
    };

    /**
     * <pre>
     * Executes the generation of behavior pattern.
     * 
     * {@inheritDoc}
     * </pre>
     */
    @Override
    public boolean execute(long jobId) {

        Job job = EditResourceUtil.INSTANCE.getJobInfo(jobId);

        File file = EditResourceUtil.INSTANCE.getFile(inputFileId);
        String instanceURI = GDNNS.UD.NS + file.getId() + RDFUtil.SEPARATOR + file.getHash() + RDFUtil.SEPARATOR + BPO.BP_NAME;
        boolean isCreatedRefOnt = RDFUtil.isResourceExists(instanceURI);
        if (isCreatedRefOnt) {
            return true;
        }

        updateJobProgress("Progressing (1/3) Read File...");
        if (!readFiles()) {
            return false;
        }
        // User Cancel Action checkpoint. hash change checkpoint
        if (job.getStatus() == Job.STATUS_CANCEL || checkHashChanged()) { // cancel for debug
            return false;
        }

        if (!validate(inputSetting)) {
            return false;
        }

        if (inputSetting.getActiveOption() instanceof BPSPathCombinationOption) {
            if (!generatePathCombination()) {
                return false;
            }
        } else if ((inputSetting.getActiveOption() instanceof BPSStateCombinationOption) || (inputSetting.getActiveOption() instanceof BPSNSwitchOption)) {
            return false;
        } else {
            // not allowed other option yet.
            return false;
        }

        return true;
    }

    /**
     * Generate a path and combination for each state machine.
     * @return true: success / false: failure
     */
    private boolean generatePathCombination() {
        NLengthBehaviorPatternGenerator generator = new NLengthBehaviorPatternGenerator();
        try {
            generator.configure(inputSetting.getInstanceArc(), (BPSPathCombinationOption) inputSetting.getActiveOption());
        } catch (BPGenerationException e) {
            setErrorMessage(e.getLocalizedMessage());
            return false;
        }
        generator.addGenerationHandler(generationHandler);
        try {
            generator.generate();
        } catch (GenerationCancellationException e) {
            return false;
        }
        return true;
    }

    /**
     * The options are checked for correctness.
     * @param input BPS file information
     * @return true: success / false: failure
     */
    boolean validate(BPSRoot input) {
        BPSOption option = input.getActiveOption();
        if (option instanceof BPSPathCombinationOption) {
            BPSPathCombinationOption pathOption = (BPSPathCombinationOption) option;
            if (pathOption.getPathLength() <= 0) {
                setErrorMessage(Message_PathLengthLessOrEqualThanZero);
                return false;
            }
        } else {
            setErrorMessage("Unknow option was set.");
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setErrorMessage(String message) {
        super.setErrorMessage(Message_Prefix + message);
    }

    /**
     * for JUnit.
     * @param generated The value to set for {@link #generatedResult}.
     */
    void setGeneratedBP(BPRoot generated) {
        this.generatedResult = generated;
    }

    /**
     * The registration process with RDF is carried out.
     * @param generated Behavior Pattern to be registered
     * @param isCreatedRefOnt Generation settings file ontology creation flag. <br>
     *            If False, the ontology will be registered. <br>
     *            If True, the ontology registration will be skipped.
     * @return true: success / false: failure
     */
    boolean registerBP(List<BPBehaviorPattern> generated, boolean isCreatedRefOnt) {
        // RDF登録
        BPRoot bpRoot = BPFactory.eINSTANCE.createBPRoot();
        BPSetting bpSetting = BPFactory.eINSTANCE.createBPSetting();
        bpSetting.getPattern().addAll(generated);
        bpRoot.getSettings().add(bpSetting);
        if (!isCreatedRefOnt) {
            Reference ref = COREFactory.eINSTANCE.createReference();
            bpRoot.getRefs().add(ref);
        }
        File file = EditResourceUtil.INSTANCE.getFile(inputFileId);
        Result result = OntologyUtils.registAbstractRootElement(bpRoot, file);
        if (Result.FAIL == result) {
            setErrorMessage(Message_FailedSave);
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * for JUnit.
     * BP生成オプションで選択されなかったステートマシンをBPEditorで表示しないために，保存対象から削る．<br/>
     * </pre>
     */
    void removeUnselectedStateMachines() {
        BPSOption option = inputSetting.getActiveOption();
        if (!(option instanceof BPSStateCombinationOption)) {
            return;
        }

        BPSStateCombinationOption stateOption = (BPSStateCombinationOption) option;
        List<String> selected = stateOption.getTargetStateMachines();
        BPSetting bpSetting = (BPSetting) generatedResult.getSettings().get(0);
        for (BPBehaviorPattern bp : bpSetting.getPattern()) {
            for (Iterator<BPBehavior> it = bp.getBehaviors().iterator(); it.hasNext();) {
                BPBehavior behavior = it.next();
                if (!selected.contains(behavior.getStateMachineRef().getFsmId())) {
                    it.remove();
                }
            }
        }
    }

    /**
     * for JUnit. Add the valid number of specified BPBehaviorPattern to "All" of BPRoot.
     * @param saveSubject
     * @param addition
     */
    void updateCountOfAll(BPRoot saveSubject, List<BPBehaviorPattern> addition) {
        long additionCount = addition.stream().filter(i -> !i.getBehaviors().isEmpty()).count();
        int current = saveSubject.getAll();
        saveSubject.setAll((int) (current + additionCount));
    }

    /**
     * <pre>
     * Read the model used for BP generation.
     * この処理で使用するモデルをDBから読みだす.
     * <p>
     * BPSRoot
     * </pre>
     * 
     * @return True: Success / False: Failure
     */
    private boolean readFiles() {
        if (!readBps()) {
            return false;
        }
        return true;
    }

    /**
     * Read the file (BPS file) that matches {@link #inputFileId} and set the content to {@link #inputSetting}.
     * @return True: Success / False: Failure
     */
    private boolean readBps() {
        long id = inputFileId;
        byte[] byteContent = EditResourceUtil.INSTANCE.getFileContent(id);
        inputSetting = convertToBPSRoot(byteContent);
        if (null == inputSetting) {
            setErrorMessage(Message_FailedLoadBPS);
            return false;
        }
        return true;
    }

    /**
     * Convert byte data read from DB to BPSRoot
     * @param data Byte data read from DB
     * @return BPSRoot
     */
    private BPSRoot convertToBPSRoot(byte[] data) {
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        if (root instanceof BPSRoot) {
            return (BPSRoot) root;
        }
        return null;
    }

    /**
     * for JUnit. Set BPSRoot to {@link #inputSetting}.
     * @param inputSetting
     */
    void setInputBPS(BPSRoot inputSetting) {
        this.inputSetting = inputSetting;
    }
}
