package com.zipc.garden.job.ontology.register;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import com.zipc.garden.job.ontology.OntologyUtils;
import com.zipc.garden.model.bp.BPBehavior;
import com.zipc.garden.model.bp.BPBehaviorPattern;
import com.zipc.garden.model.bp.BPRoot;
import com.zipc.garden.model.bp.BPSetting;
import com.zipc.garden.model.bp.BPStep;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.rdf.ontology.BPO;
import com.zipc.garden.webplatform.dao.rdf.ontology.BPSO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GBO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * A class that registers the ontology model of the behavior pattern.
 */
public class BPRegister extends AbstractRegister {

    /**
     * <pre>
     * Generation settings file ontology creation flag.
     * Behavior patterns are registered in specified number units, but ontology definitions need only be registered once.
     * This flag is False for the first round of the registration process and True for the second and subsequent registration processes.
     * If False, the ontology will be registered.
     * If True, the ontology registration will be skipped.
     * </pre>
     */
    private boolean isCreatedRefOnt = false;

    /**
     * constructor.
     * @param root bp root
     * @param file bps file
     */
    public BPRegister(AbstractRootElement root, File file) {
        super(root, file);
        // refが存在しない場合はすでに生成設定ファイルを作成済み
        isCreatedRefOnt = root.getRefs().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isExists() {
        // 生成設定ファイルがない場合、2回目以降の登録のため、存在チェックををスキップ
        if (isCreatedRefOnt) {
            return false;
        }
        return super.isExists();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String generateFileInstanceURI() {
        File file = getFile();
        return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + BPO.BP_NAME;
    }

    /**
     * <pre>
     * Register the related file (bps file) of the bp file in RDF.
     * 
     * {@inheritDoc}
     * </pre>
     */
    @Override
    protected boolean generateRefOntology() {
        // 生成設定ファイルがない場合、2回目以降の登録のため、登録をスキップ
        if (isCreatedRefOnt) {
            return true;
        }

        File refFile = getFile(); // BPのFileはBPSそのものなのでここで登録
        byte[] data = EditResourceUtil.INSTANCE.getFileContent(refFile.getId());
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        if (IRDFRegister.Result.FAIL == OntologyUtils.registAbstractRootElement(root, refFile)) {
            return false;
        }
        return true; // 関連が無いのでNOP
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected OntModel generateOntology() {
        OntModel ontModel = ModelFactory.createOntologyModel();

        // NameSpace
        ontModel.setNsPrefix("garden", GDNNS.GARDEN.NS);
        ontModel.setNsPrefix("bps", GDNNS.BPS.NS);
        ontModel.setNsPrefix("bp", GDNNS.BP.NS);
        ontModel.setNsPrefix("gdata", GDNNS.UD.NS);

        createClassOntology(ontModel);

        // Ontology
        Ontology bpOntology = ontModel.createOntology(GDNNS.BP.URI);
        bpOntology.addProperty(OWL2.imports, GBO.garden);

        // UserDataOntology
        Ontology ontology = ontModel.createOntology(GDNNS.UD.URI);
        ontology.addProperty(OWL2.imports, GBO.garden);

        // Instance
        Resource instance = ontModel.createOntResource(generateFileInstanceURI());
        setFileInstanceInfo(ontModel, instance, BPO.BehaviorPatternSet);

        setExtensionInfo(ontModel, instance);

        return ontModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setFileInstanceInfo(OntModel m, Resource instance, OntClass instanceClass) {
        instance.addProperty(RDF.type, OWL2.NamedIndividual);
        instance.addProperty(RDF.type, instanceClass);
        // instance.addLiteral(GBO.hasFileId, getFile().getId());
        // instance.addLiteral(GBO.hasFileName, getFile().getName());
        // instance.addLiteral(GBO.hasProjectId, getFile().getProjectid());
        // instance.addLiteral(GBO.hasHash, getFile().getHash());
    }

    /**
     * Create an ontology definition for BP.
     * @param m BP ontology model
     */
    private void createClassOntology(OntModel m) {
        // Ontology
        Ontology bpOntology = m.createOntology(GDNNS.BP.URI);
        bpOntology.addProperty(OWL2.imports, GBO.garden);

        // Class
        Resource bpSet = m.createClass(BPO.BehaviorPatternSet.getURI());
        bpSet.addProperty(RDF.type, OWL2.Class);
        Resource refSettingFile = m.createProperty(BPO.refSettingFile.getURI());
        refSettingFile.addProperty(RDF.type, OWL2.ObjectProperty);
        refSettingFile.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource hasPattern = m.createProperty(BPO.hasPattern.getURI());
        hasPattern.addProperty(RDF.type, OWL2.ObjectProperty);
        hasPattern.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource behavior = m.createClass(BPO.Behavior.getURI());
        behavior.addProperty(RDF.type, OWL2.Class);
        Resource stateMachineInstance = m.createProperty(BPO.stateMachineInstance.getURI());
        stateMachineInstance.addProperty(RDF.type, OWL2.ObjectProperty);
        stateMachineInstance.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource hasStep = m.createProperty(BPO.hasStep.getURI());
        hasStep.addProperty(RDF.type, OWL2.ObjectProperty);
        hasStep.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource step = m.createClass(BPO.Step.getURI());
        step.addProperty(RDF.type, OWL2.Class);
        Resource hasStepNo = m.createProperty(BPO.hasStepNo.getURI());
        hasStepNo.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasStepNo.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasState = m.createProperty(BPO.hasState.getURI());
        hasState.addProperty(RDF.type, OWL2.ObjectProperty);
        hasState.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource hasTransition = m.createProperty(BPO.hasTransition.getURI());
        hasTransition.addProperty(RDF.type, OWL2.ObjectProperty);
        hasTransition.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource hasNext = m.createProperty(BPO.hasNext.getURI());
        hasNext.addProperty(RDF.type, OWL2.ObjectProperty);
        hasNext.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
    }

    /**
     * Create the contents of BPRoot and set it in the ontology model.
     * @param ontModel BP ontology model
     * @param instance The created instance is set.
     */
    private void setExtensionInfo(OntModel ontModel, Resource instance) {
        BPRoot bpRoot = (BPRoot) getRoot();

        // --> Start bp:BehaviorPatternSet
        instance.addProperty(BPO.refSettingFile, ontModel.createResource(GDNNS.UD.NS + getFile().getId() + SEPARATOR + getFile().getHash() + SEPARATOR + BPSO.BPS_NAME));

        // --> Start bp:BehaviorPattern
        Resource iPattern;
        BPSetting bpSetting = (BPSetting) bpRoot.getSettings().get(0);
        for (BPBehaviorPattern bpPattern : bpSetting.getPattern()) {
            iPattern = ontModel.createOntResource(instance.getURI() + SEPARATOR + "Pattern" + bpPattern.getId());
            iPattern.addProperty(RDF.type, OWL2.NamedIndividual);
            iPattern.addProperty(RDF.type, BPO.BehaviorPattern);
            iPattern.addLiteral(BPO.hasPatternNo, Long.parseLong(bpPattern.getId()));

            // --> Start bp:Behavior
            Resource iBehavior;
            for (BPBehavior bpBehavior : bpPattern.getBehaviors()) {
                iBehavior = ontModel.createOntResource(iPattern.getURI() + SEPARATOR + bpBehavior.getStateMachineRef().getName());
                iBehavior.addProperty(RDF.type, OWL2.NamedIndividual);
                iBehavior.addProperty(RDF.type, BPO.Behavior);
                iBehavior.addProperty(BPO.stateMachineInstance, ontModel.createResource(GDNNS.UD.NS + getFile().getId() + SEPARATOR + getFile().getHash() + SEPARATOR
                        + BPSO.BPS_NAME + SEPARATOR + "BehaviorInstance" + SEPARATOR + bpBehavior.getStateMachineRef().getName()));

                // --> Start bp:Step
                Resource iStep;
                BPStep bpStep;
                String stateName = null;
                for (int i = 0; i < bpBehavior.getSteps().size(); i++) {
                    bpStep = bpBehavior.getSteps().get(i);
                    iStep = ontModel.createOntResource(iBehavior.getURI() + SEPARATOR + i);
                    iStep.addProperty(RDF.type, OWL2.NamedIndividual);
                    iStep.addProperty(RDF.type, BPO.Step);
                    iStep.addLiteral(BPO.hasStepNo, new Long(i));
                    if (i == 0) {
                        stateName = bpStep.getState().getValue();
                    }

                    if (bpStep.getEvent() != null && !StringUtils.isEmpty(bpStep.getEvent().getValue())) {
                        iStep.addProperty(BPO.hasTransition,
                                ontModel.createResource(GDNNS.UD.NS + getFile().getId() + SEPARATOR + getFile().getHash() + SEPARATOR + BPSO.BPS_NAME + SEPARATOR
                                        + "BehaviorInstance" + SEPARATOR + bpBehavior.getStateMachineRef().getName() + SEPARATOR + "State" + SEPARATOR + stateName + SEPARATOR
                                        + "Transition" + SEPARATOR + bpStep.getEvent().getValue()));
                    }
                    if (bpStep.getState() != null && !StringUtils.isEmpty(bpStep.getState().getValue())) {
                        iStep.addProperty(BPO.hasState,
                                ontModel.createResource(
                                        GDNNS.UD.NS + getFile().getId() + SEPARATOR + getFile().getHash() + SEPARATOR + BPSO.BPS_NAME + SEPARATOR + "BehaviorInstance" + SEPARATOR
                                                + bpBehavior.getStateMachineRef().getName() + SEPARATOR + "State" + SEPARATOR + bpStep.getState().getValue()));
                        stateName = bpStep.getState().getValue();
                    }
                    if (i < (bpBehavior.getSteps().size() - 1)) {
                        iStep.addProperty(BPO.hasNext, ontModel.createResource(iBehavior.getURI() + SEPARATOR + (i + 1)));
                    }

                    iBehavior.addProperty(BPO.hasStep, iStep);
                } // <-- End bp:Step

                iPattern.addProperty(BPO.hasBehavior, iBehavior);
            } // <-- End bp:Behavior

            instance.addProperty(BPO.hasPattern, iPattern);
        } // <-- End bp:BehaviorPattern

        // <-- End bp:BehaviorPatternSet
    }

}
