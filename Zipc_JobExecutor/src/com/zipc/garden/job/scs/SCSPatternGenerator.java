package com.zipc.garden.job.scs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zipc.garden.job.JobExecutorThread;
import com.zipc.garden.job.ontology.OntologyUtils;
import com.zipc.garden.job.ontology.register.IRDFRegister.Result;
import com.zipc.garden.model.bp.BPFactory;
import com.zipc.garden.model.cb.CBFile;
import com.zipc.garden.model.cb.CBRoot;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.model.scs.SCSFactory;
import com.zipc.garden.model.scs.SCSPattern;
import com.zipc.garden.model.scs.SCSPatternReference;
import com.zipc.garden.model.scs.SCSRoot;
import com.zipc.garden.model.scs.SCSSetting;
import com.zipc.garden.model.tp.TPFactory;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.rdf.RDFUtil;
import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;
import com.zipc.garden.webplatform.dao.rdf.ontology.SCSO;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.shared.Extension;

/**
 * A class that generates a scenario set pattern.
 */
public class SCSPatternGenerator extends JobExecutorThread {

    /** Error message when saving the generated ScenarioSetPattrens failed. */
    private static final String Message_FailedSave = "Generated ScenarioSet Pattrens saving is failed.";

    /** Maximum number of pattern that can be registered in RDF at one time. */
    private static final int REGISTER_LIMIT = 1000;

    /** SCSS file contents */
    private CBRoot cbRoot;

    /**
     * <pre>
     * Association of UUID of setting file(fps or bps) and pattern information.
     *  key: fps or bps uuid
     *  value: Pattern information obtained from RDF
     * </pre>
     */
    private Map<String, AbstractSetting> patternSettingMap;

    /**
     * <pre>
     * Association of pattern information obtained from RDF with uuid.
     *  key: Pattern information existing on RDF
     *  value: uuid (fps or bps)
     * </pre>
     */
    private Map<SCSPatternReference, String> scsPtnRefMap;

    /** Error message when reading SCSS file fails. */
    private String READ_SCSS_FAIL = "read .scss fail";

    /** Error message when the file set in the SCSS file is not FPS or BPS. */
    private String READ_PATTERN_FAIL = "read .fps or .bps fail";

    /** Error message when the pattern number set in SCSS is not a numerical value. */
    private String PATTERN_NUMBER_FAIL = "Incorrect pattern number";

    /**
     * constructor
     * @param jobId job id
     * @param projectId project id
     * @param outputFileId scs file id or -1
     */
    public SCSPatternGenerator(long jobId, long projectId, long outputFileId) {
        super(jobId, projectId, outputFileId);
    }

    /**
     * <pre>
     * The pattern generation process of the scenario set is executed.
     * 
     * {@inheritDoc}
     * </pre>
     */
    @Override
    protected boolean execute(long jobId) {
        return generate();
    }

    /**
     * <pre>
     * Generate a pattern for the scenario set.
     * 
     * SCSPatternを生成する
     * </pre>
     * 
     * @return true: success / false: failure
     */
    public boolean generate() {

        // Read SCSS
        updateJobProgress("Progressing (1/4) Read File ...");
        // User Cancel Action checkpoint. hash change checkpoint
        if (!readFiles() || requestedCancel() || checkHashChanged()) {
            return false;
        }

        // Create SCSPatternReference
        updateJobProgress("Progressing (2/4) Create Scs File ...");
        // User Cancel Action checkpoint. hash change checkpoint
        if (!createSCSPtnRef() || requestedCancel() || checkHashChanged()) {
            return false;
        }

        updateJobProgress("Progressing (3/4) Create Pattern ... ");
        // register
        if (!createPattern()) {
            return false;
        }
        // User Cancel Action checkpoint. hash change checkpoint
        if (requestedCancel() || checkHashChanged()) {
            return false;
        } else {
            updateJobProgress("Progressing (4/4) Completed");
            return true;
        }

    }

    /**
     * Create an association between the pattern information obtained from RDF and the uuid.
     * @return true: success / false: failure
     */
    private boolean createSCSPtnRef() {
        SCSPtnRefGenerator gen = new SCSPtnRefGenerator();
        scsPtnRefMap = gen.execute(cbRoot, patternSettingMap, projectId);
        return scsPtnRefMap != null;
    }

    /**
     * <pre>
     * Create a scenario set pattern and perform RDF registration processing.
     * 
     * SCSPatternをDBに登録する
     * </pre>
     * 
     * @return true: success / false: failure
     */
    public boolean createPattern() {
        File file = EditResourceUtil.INSTANCE.getFile(inputFileId);
        String instanceURI = GDNNS.UD.NS + file.getId() + RDFUtil.SEPARATOR + file.getHash() + RDFUtil.SEPARATOR + SCSO.SCS_NAME;
        boolean isCreatedRefOnt = RDFUtil.isResourceExists(instanceURI);
        if (isCreatedRefOnt) {
            return true;
        }
        SCSCombinationLogic logic = new SCSCombinationLogic();
        // create patterns.
        List<List<SCSPatternReference>> scsPtnRefList = logic.execute(cbRoot, scsPtnRefMap);
        // 登録するパターン
        List<SCSPattern> patterns = new ArrayList<SCSPattern>();
        int i = 1;
        for (List<SCSPatternReference> refList : scsPtnRefList) {
            SCSPattern ptn = SCSFactory.eINSTANCE.createSCSPattern();
            ptn.setId(String.valueOf(i++));
            for (SCSPatternReference ref : refList) {
                SCSPatternReference refPtn = SCSFactory.eINSTANCE.createSCSPatternReference();
                refPtn.setFileId(ref.getFileId());
                refPtn.setPatternId(ref.getPatternId());
                ptn.getPatternreferences().add(refPtn);
            }
            patterns.add(ptn);

            // パターンが登録件数までたまったら登録
            if (0 == i % REGISTER_LIMIT) {
                if (!registerSCS(patterns, isCreatedRefOnt)) {
                    return false;
                }
                // 登録済みのパターンは削除
                patterns.clear();
                // 生成設定ファイルを登録したフラグをtrueに変更
                isCreatedRefOnt = true;
                // キャンセルではないかつ、設定ファイルのhash値が変更されていない場合登録した件数をジョブに表示
                if (!requestedCancel() && !checkHashChanged()) {
                    updateJobProgress("Progressing (3/4) Create Pattern (" + i + "/" + scsPtnRefList.size() + ")... ");
                }

            }

            // キャンセルされた場合又は、設定ファイルのhash値が変更されている場合は登録終了
            if (requestedCancel() || checkHashChanged()) {
                break;
            }
        }
        // 残りのパターンを登録
        if (!registerSCS(patterns, isCreatedRefOnt)) {
            return false;
        }

        return true;
    }

    /**
     * Register the scenario set pattern information in RDF.
     * @param generated Pattern information to be registered.
     * @param isCreatedRefOnt Registration flag for "setting file". <br>
     *            It will be False only when you register the ontology for the first time.
     * @return true: success / false: failure
     */
    private boolean registerSCS(List<SCSPattern> generated, boolean isCreatedRefOnt) {
        SCSRoot scsRoot = SCSFactory.eINSTANCE.createSCSRoot();
        SCSSetting scsSetting = SCSFactory.eINSTANCE.createSCSSetting();
        scsSetting.getPatterns().addAll(generated);
        scsRoot.getSettings().add(scsSetting);
        if (!isCreatedRefOnt) {
            Reference ref = COREFactory.eINSTANCE.createReference();
            scsRoot.getRefs().add(ref);
        }
        File file = EditResourceUtil.INSTANCE.getFile(inputFileId);
        Result result = OntologyUtils.registAbstractRootElement(scsRoot, file);
        if (result.equals(Result.FAIL)) {
            setErrorMessage(Message_FailedSave);
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * The SCSS file is read and the FPS and BPS patterns are read.
     * 
     * generationに必要な各種ファイルをDBから読み込む
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean readFiles() {
        if (!readCb()) {
            setErrorMessage(READ_SCSS_FAIL);
            return false;
        }
        if (!readAndValidatePattern()) {
            return false;// 内部で個別ノードのエラーメッセージを登録しているのでここでは無し
        }
        return true;
    }

    /**
     * <pre>
     * Obtain the pattern information of the FPS and BPS files associated with {@link #cbRoot} from RDF and set it in {@link #patternSettingMap}.
     * 
     * パターンファイルを読み込みつつチェックを行う
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean readAndValidatePattern() {
        patternSettingMap = new HashMap<String, AbstractSetting>();
        for (Reference ref : cbRoot.getRefs()) {
            AbstractSetting setting = null;
            if (ref.getRefExtension().equals(Extension.BPS.getValue())) {
                setting = BPFactory.eINSTANCE.createBPSetting();
            } else if (ref.getRefExtension().equals(Extension.FPS.getValue())) {
                setting = TPFactory.eINSTANCE.createTPSetting();
            } else {
                setErrorMessage(READ_PATTERN_FAIL);
                return false;
            }

            String[] patternNums = null;
            CBFile cbFile = cbRoot.getLogic().getFile().stream().filter(f -> ref.getRefid().equals(f.getUuid())).findFirst().orElse(null);
            if (cbFile != null && cbFile.getPattern() != null && !cbFile.getPattern().isEmpty()) {
                patternNums = cbFile.getPattern().split(",");
                for (int i = 0; i < patternNums.length; i++) {
                    String num = patternNums[i];
                    if (!StringUtils.isNumeric(num)) {
                        setErrorMessage(PATTERN_NUMBER_FAIL);
                        return false;
                    }
                }
            }
            if (!EditResourceUtil.INSTANCE.setPattern(projectId, ref.getRefid(), setting, patternNums)) {
                setErrorMessage(PATTERN_NUMBER_FAIL);
                return false;
            }
            patternSettingMap.put(ref.getRefid(), setting);
        }
        return true;
    }

    /**
     * <pre>
     * Read the SCSS file that matches {@link #inputFileId} from the DB and create {@link #cbRoot}.
     * 
     * SCSSファイルを読み込む
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean readCb() {
        long id = inputFileId;
        byte[] byteContent = EditResourceUtil.INSTANCE.getFileContent(id);
        cbRoot = convertToCBRoot(byteContent);
        if (null == cbRoot) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Converts the specified byte data to CBRoot.
     * 
     * DBから読み込んだbyteデータをCBRootに変換する
     * </pre>
     * 
     * @param data specified byte data
     * @return CBRoot
     */
    private CBRoot convertToCBRoot(byte[] data) {
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        if (root instanceof CBRoot) {
            return (CBRoot) root;
        }
        return null;
    }

}
