package com.zipc.garden.job.ontology.register;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import com.zipc.garden.job.ontology.OntologyUtils;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.scs.SCSPattern;
import com.zipc.garden.model.scs.SCSPatternReference;
import com.zipc.garden.model.scs.SCSRoot;
import com.zipc.garden.model.scs.SCSSetting;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.rdf.ontology.FPO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GBO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;
import com.zipc.garden.webplatform.dao.rdf.ontology.SCSO;
import com.zipc.garden.webplatform.dao.rdf.ontology.SCSSO;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.shared.Extension;

/**
 * A class that registers the ontology model of the scenario set.
 */
public class SCSRegister extends AbstractRegister {

    /** Used as part of the URI to identify one scenario. */
    private static final String SCENARIO = "Scenario";

    /**
     * <pre>
     * Generation settings file ontology creation flag.
     * Scenarioset are registered in specified number units, but ontology definitions need only be registered once.
     * This flag is False for the first round of the registration process and True for the second and subsequent registration processes.
     * If False, the ontology will be registered.
     * If True, the ontology registration will be skipped.
     * </pre>
     */
    private boolean isCreatedRefOnt = false;

    /**
     * constructor.
     * @param root scs root
     * @param file scss file
     */
    public SCSRegister(AbstractRootElement root, File file) {
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
    protected OntModel generateOntology() {
        File file = getFile();
        OntModel m = ModelFactory.createOntologyModel();

        // UserDataOntology
        Ontology ontology = m.createOntology(GDNNS.UD.URI);
        ontology.addProperty(OWL2.imports, GBO.garden);

        // Ontology
        Ontology scsOntology = m.createOntology(GDNNS.SCS.URI);
        scsOntology.addProperty(OWL2.imports, GBO.garden);

        // Class
        Resource scs = m.createClass(SCSO.ScenarioSet.getURI());
        scs.addProperty(RDF.type, OWL2.Class);

        Resource sc = m.createClass(SCSO.Scenario.getURI());
        sc.addProperty(RDF.type, OWL2.Class);

        DatatypeProperty scNo = m.createDatatypeProperty(SCSO.hasScenarioNo.getURI());
        scNo.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);

        DatatypeProperty scName = m.createDatatypeProperty(SCSO.hasScenarioName.getURI());
        scName.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);

        ObjectProperty scRefFile = m.createObjectProperty(SCSO.refSettingFile.getURI());
        scRefFile.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);

        ObjectProperty scScenario = m.createObjectProperty(SCSO.hasScenario.getURI());
        scScenario.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);

        ObjectProperty scRefPtn = m.createObjectProperty(SCSO.refPattern.getURI());
        scRefPtn.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);

        // Instance
        Resource instance = m.createOntResource(getFileInstanceURI());
        // refSettingFile
        Resource scssFile = m.createOntResource(GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + SCSSO.SCSS_NAME);
        instance.addProperty(SCSO.refSettingFile, scssFile);

        SCSRoot root = (SCSRoot) getRoot();
        SCSSetting setting = (SCSSetting) root.getSettings().get(0);
        // create Scenario
        for (SCSPattern scsPattern : setting.getPatterns()) {
            Resource pattern = m.createOntResource(GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + SCSO.SCS_NAME + SEPARATOR + SCENARIO + scsPattern.getId());
            instance.addProperty(SCSO.hasScenario, pattern);
            pattern.addProperty(RDF.type, OWL2.NamedIndividual);
            pattern.addProperty(RDF.type, SCSO.Scenario);
            pattern.addLiteral(SCSO.hasScenarioNo, scsPattern.getId());
            for (SCSPatternReference scsRef : scsPattern.getPatternreferences()) {
                File tpsOrBpsFile = EditResourceUtil.INSTANCE.getFile(scsRef.getFileId());
                String extension = "";
                if (Extension.getByCode(tpsOrBpsFile.getExtension()) == Extension.FPS) {
                    extension = Extension.FP.getValue();
                } else if (Extension.getByCode(tpsOrBpsFile.getExtension()) == Extension.BPS) {
                    extension = Extension.BP.getValue();
                }
                String patternName = OntologyUtils.getInstanceExtension(extension) + SEPARATOR + FPO.PATTERN_NAME + scsRef.getPatternId();
                Resource res = m.createOntResource(GDNNS.UD.NS + tpsOrBpsFile.getId() + SEPARATOR + tpsOrBpsFile.getHash() + SEPARATOR + patternName);
                pattern.addProperty(SCSO.refPattern, res);
            }
        }

        return m;
    }

    /**
     * <pre>
     * Register the related file (scss file) of the scs file in RDF.
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
        File refFile = getFile(); // SCSのFileはSCSSそのものなのでここで登録
        byte[] data = EditResourceUtil.INSTANCE.getFileContent(refFile.getId());
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        if (IRDFRegister.Result.FAIL == OntologyUtils.registAbstractRootElement(root, refFile)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String generateFileInstanceURI() {
        File file = getFile();
        return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + SCSO.SCS_NAME;
    }
}
