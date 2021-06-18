package com.zipc.garden.job.tp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.zipc.garden.job.JobExecutorThread;
import com.zipc.garden.job.core.ACTSExecutor;
import com.zipc.garden.job.ontology.OntologyUtils;
import com.zipc.garden.job.ontology.register.IRDFRegister.Result;
import com.zipc.garden.job.tp.TCResolver.TCCopier;
import com.zipc.garden.job.tp.TCResolver.TCResolveResult;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.fmc.FMCRoot;
import com.zipc.garden.model.fmcs.FMCSFactory;
import com.zipc.garden.model.fmcs.FMCSRoot;
import com.zipc.garden.model.tc.TCFactory;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCProperty;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.model.tp.TPElement;
import com.zipc.garden.model.tp.TPFactory;
import com.zipc.garden.model.tp.TPPatternElement;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tp.TPTSDPattern;
import com.zipc.garden.model.tps.NodeVariable;
import com.zipc.garden.model.tps.TPSPatternElement;
import com.zipc.garden.model.tps.TPSRoot;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.FileReferences;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.server.logic.ProjectServiceLogic;
import com.zipc.garden.webplatform.shared.NodeUtil;
import com.zipc.garden.webplatform.shared.UserPropertyInfo;

/**
 * A generate class for feature patterns.
 */
public class TSDPatternGenerator extends JobExecutorThread {

    /** FPS file information */
    private TPSRoot tpsRoot;

    /** TC file information */
    private TCRoot tcRoot;

    /** TCRoot being edited in Feature pattern generation. */
    private TCRoot resolveTcRoot;

    /**
     * <pre>
     * A copy of the optional resolved TCRoot.
     *  key   : TCRoot where optional nodes are deployment
     *  value : Replication logic.
     * </pre>
     */
    private TCResolveResult<TCRoot, TCCopier> tcResolveResult;

    /** FMC file information */
    private FMCRoot fmcRoot;

    /** The result of conversion from text ({@link #fmcRoot}) to syntaxTree. */
    private FMCSRoot fmcsRoot;

    /** Result of flattening process */
    private TSDFlatteningResult flatteningResult;

    /** ACTS execution result */
    private String actsResult;

    /** Error message when {@link #readTps()} fails to execute. */
    private String READ_FPS_FAIL = "read .fps fail";

    /** Error message when {@link #readTc()} fails to execute. */
    private String READ_TC_FAIL = "read .tc fail";

    /** Error message when {@link #resolveOptional()} fails to execute. */
    private String RESOLVE_OPTIONAL_FAIL = "resolve optional fail";

    /** Error message when {@link #removeDisableNode()} fails to execute. */
    private String REMOVE_DISABLE_NODE_FAIL = "remove disable node fail";

    /** Error message when {@link #resolveCardinality()} fails to execute. */
    private String RESOLVE_CARDINALITY_FAIL = "resolve cardinality fail";

    /** Error message when {@link #isTcLeafEdgeTypeXOR()} fails to execute. */
    private String TC_NODE_TOBE_XOR1 = "EdgeType of ";

    /** Error message when {@link #isTcLeafEdgeTypeXOR()} fails to execute. */
    private String TC_NODE_TOBE_XOR2 = " in TSD condition has to be XOR";

    /** Error message when {@link #isTcNodeHasSomeLeaf()} fails to execute. */
    private String TC_ROOTNODE_TOBE_AND1 = "EdgeType of ";

    /** Error message when {@link #isTcNodeHasSomeLeaf()} fails to execute. */
    private String TC_ROOTNODE_TOBE_AND2 = " in TSD condition has to be AND";

    /** Error message when {@link #isTcNodeHasSomeLeaf()} fails to execute. */
    private String TC_NODE_HAS_NOT_AND = "At least one Node in TSD condition has to have AND edge type and have greater equal to two child nodes.";

    /** Error message when {@link #readFmc()} fails to execute. */
    private String READ_FMC_FAIL = "read .fmc fail";

    /** Error message when {@link #flattening(int)} fails to execute. */
    private String FLATTENING_FAIL = "flattening fail";

    /** Error message when {@link #executeACTS()} fails to execute. */
    private String ACTS_FAIL = "execute acts fail";

    /** Error message when {@link #patternElementCalculator(Map, TPSetting)} fails to execute. */
    private String CALC_FAIL = "Feature Pattern Element calculation failed :";

    /** {@link TSDFlatteningLogic.MODE_ACTS} is set. */
    private int mode;

    /**
     * constructor
     * @param jobId job id
     * @param projectId project id
     * @param outputFileId fp file id. -1 is set if only data is created.
     */
    public TSDPatternGenerator(long jobId, long projectId, long outputFileId) {
        super(jobId, projectId, outputFileId);
    }

    /**
     * <pre>
     * Executes the generation of feature pattern.
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
     * Generates a feature pattern.
     * 
     * TSDPatternを生成する
     * </pre>
     * 
     * @return true: success / false: failure
     */
    public boolean generate() {
        // Set generateMode
        mode = TSDFlatteningLogic.MODE_ACTS;
        // Read FPS/TC/ODD/FMC/FM
        updateJobProgress("Progressing (1/5) Read File...");
        // User Cancel Action checkpoint. hash change checkpoint
        if (!readFiles() || requestedCancel() || checkHashChanged()) {
            return false;
        }
        updateJobProgress("Progressing (2/5) Converting File...");
        // Convert FMC to FMCS
        // User Cancel Action checkpoint. hash change checkpoint
        if (!convertFMCS() || requestedCancel() || checkHashChanged()) {
            return false;
        }
        if (!resolveOptional() || requestedCancel() || checkHashChanged()) {
            return false;
        }
        if (!removeDisableNode()) {
            return false;
        }
        if (!resolveCardinality() || requestedCancel() || checkHashChanged()) {
            return false;
        }
        updateJobProgress("Progressing (3/5)  Flating...");
        if (!flattening(mode) || requestedCancel() || checkHashChanged()) {
            setErrorMessage(FLATTENING_FAIL);
            return false;
        }
        updateJobProgress("Progressing (4/5) PSD Generating...");

        if (mode == TSDFlatteningLogic.MODE_ACTS) {
            // User Cancel Action checkpoint. hash change checkpoint
            if (requestedCancel() || checkHashChanged()) {
                return false;
            } else if (tpsRoot.getNWiseCombination() == 0) {
                // generate TSDPattern by ACTS using actsInfo and nWise
                if (!executeACTS()) {
                    setErrorMessage(ACTS_FAIL);
                    return false;
                }
            } else {
                if (!executeACTS()) {
                    setErrorMessage(ACTS_FAIL);
                    return false;
                }
            }
            removeOptionalTCNode();
            // convert ID to NodeName and Insert DB
            // User Cancel Action checkpoint. hash change checkpoint
            if (!createTSDPatternForActs() || requestedCancel() || checkHashChanged()) {
                return false;
            }
        }
        updateJobProgress("Progressing (5/5) Completed...");
        return true;
    }

    /**
     * <pre>
     * Generate a feature pattern and register it in RDF.
     * 
     * RDFにFeaturePatternを登録する
     * </pre>
     * 
     * @param patternMap A map with pattern information to generate.<br>
     *            key: pattern ID / value: fullPath list of nodes that the pattern has<br>
     *            パターンIDと、そのパターンが持つノードのFullPathリストのマップ
     * @return true: success / false: failure
     */
    private boolean registTSDPatternToRDF(Map<String, List<String>> patternMap) {
        TPRoot tpRoot = TPFactory.eINSTANCE.createTPRoot();
        TPSetting tpSetting = TPFactory.eINSTANCE.createTPSetting();
        tpRoot.getSettings().add(tpSetting);
        Map<String, TPElement> elementMap = new HashMap<>(); // FullPathとTPElementのマップで順不同

        // TPElementを生成
        for (Entry<String, List<String>> entry : patternMap.entrySet()) {
            List<String> fullPathList = entry.getValue();
            for (String fullPath : fullPathList) {
                if (!elementMap.containsKey(fullPath)) {
                    TPElement element = TPFactory.eINSTANCE.createTPElement();
                    element.setFullPath(fullPath);
                    element.setSimplePath(null);
                    elementMap.put(fullPath, element);
                    tpSetting.getElements().add(element);
                }
            }
        }

        // TPTSDPatternを生成
        for (Entry<String, List<String>> entry : patternMap.entrySet()) {
            String patternNo = entry.getKey();
            List<String> fullPathList = entry.getValue();
            TPTSDPattern pattern = TPFactory.eINSTANCE.createTPTSDPattern();
            pattern.setId(patternNo);
            for (String fullPath : fullPathList) {
                TPElement element = elementMap.get(fullPath);
                if (null == element) {
                    return false;
                }
                pattern.getElements().add(element);
            }
            tpSetting.getPatterns().add(pattern);
        }

        // PatternElementの計算を実行して、tpSettingに格納する
        if (!patternElementCalculator(patternMap, tpSetting)) {
            return false;
        }

        // RDF登録
        File file = EditResourceUtil.INSTANCE.getFile(inputFileId);

        Result result = OntologyUtils.registAbstractRootElement(tpRoot, file);
        if (Result.FAIL == result) {
            return false;
        }

        return true;
    }

    /**
     * Calculate the risk importance and set it for TPSetting.
     * @param patternMap A map with pattern information to generate.<br>
     *            key: pattern ID / value: fullPath list of nodes that the pattern has<br>
     * @param tpSetting The setting destination of the calculation result.
     * @return true: success / false: failure
     */
    private boolean patternElementCalculator(Map<String, List<String>> patternMap, TPSetting tpSetting) {

        // 変数とその初期値を取得
        List<UserPropertyInfo> userPropertyInfoList = ProjectServiceLogic.getUserPropertyInfoList(projectId);

        // 全ての変数を持っている配列の変数名を取得
        String allVariablesName = ProjectServiceLogic.getAllVariablesName(projectId);
        if (allVariablesName == null || allVariablesName.isEmpty()) {
            allVariablesName = "allVariables";
        }

        for (Entry<String, List<String>> entry : patternMap.entrySet()) {
            List<String> fullPathList = entry.getValue();
            // 変数の組み合わせマップ Map<valueNodeの親ノード, Map<変数名, 値>>
            Map<TCNode, Map<String, String>> parentMap = new HashMap<TCNode, Map<String, String>>();

            // パターンの組み合わせから変数の組み合わせを作成する
            for (String fullPath : fullPathList) {
                // valueNodeの親ノードを取得する
                List<TCNode> nodeList = TCUtil.findNodes(tcRoot, fullPath, false);
                for (TCNode tcNode : nodeList) {
                    TCNode parent = TCUtil.getParentNode(tcNode, false);

                    // 設定されていないプロパティを初期値で補完する
                    for (UserPropertyInfo info : userPropertyInfoList) {
                        // プロパティが存在しないする場合は補完対象か確認
                        if (tcNode.getProperties() != null) {
                            Optional<TCProperty> opt = tcNode.getProperties().stream().filter(p -> {
                                return p.getKey() != null && p.getKey().equals(info.getUserProperty());
                            }).findFirst();
                            if (opt.isPresent()) {
                                // ノードにプロパティが設定されているので初期値補完はcontinue
                                continue;
                            }
                        }

                        // 変数が設定されていないので初期値で補完する処理
                        if (parentMap.containsKey(parent)) {
                            // 変数の組み合わせマップに存在している場合（多重度の場合）
                            Map<String, String> propMap = parentMap.get(parent);
                            if (propMap.containsKey(info.getUserProperty())) {
                                // 多重度かつすでにkeyが存在していた場合は","でつないで初期値をいれて上書き保存
                                String propValue = propMap.get(info.getUserProperty()) + ", " + info.getInitialValue();
                                propMap.put(info.getUserProperty(), propValue);
                            } else {
                                // 多重度でkeyが存在しない場合はそのまま初期値を入れる
                                propMap.put(info.getUserProperty(), info.getInitialValue());
                            }
                        } else {
                            // 組み合わせマップに存在していないので、新しくマップを追加
                            Map<String, String> propMap = new HashMap<String, String>();
                            propMap.put(info.getUserProperty(), info.getInitialValue());
                            parentMap.put(parent, propMap);
                        }
                    }

                    // 設定されているプロパティを組みあわせマップに追加する
                    for (TCProperty tcProp : tcNode.getProperties()) {
                        if (tcProp.getKey() == null || tcProp.getKey().isEmpty() || tcProp.getValue() == null || tcProp.getValue().isEmpty()) {
                            continue;
                        }

                        // valueNodeの親ノードを持っているtpsRootのNodeVariablesを取得する
                        Optional<NodeVariable> opt = tpsRoot.getNodeVariables().stream().filter(n -> EcoreUtil.equals(n.getTcNode(), parent)).findFirst();
                        if (!opt.isPresent()) {
                            continue;
                        }

                        // 変数の組み合わせマップに存在している場合（多重度の場合）、","でつないで値をいれて上書き保存
                        if (parentMap.containsKey(parent)) {
                            if (parentMap.get(parent).containsKey(tcProp.getKey())) {
                                String propValue = parentMap.get(parent).get(tcProp.getKey()) + " , " + tcProp.getValue();
                                parentMap.get(parent).put(tcProp.getKey(), propValue);
                            } else {
                                Map<String, String> propMap = parentMap.get(parent);
                                propMap.put(tcProp.getKey(), tcProp.getValue());
                            }

                        } else {
                            // 変数の組み合わせマップに存在しない場合は、マップに追加
                            Map<String, String> propMap = new HashMap<String, String>();
                            propMap.put(tcProp.getKey(), tcProp.getValue());
                            parentMap.put(parent, propMap);
                        }
                    }
                }
            }

            // 作成した変数の組み合わせをjavascriptのプロパティの形式に変更する
            StringBuilder variables = new StringBuilder();
            StringBuilder allVariables = new StringBuilder();
            for (TCNode parent : parentMap.keySet()) {
                StringBuilder variable = new StringBuilder();
                Map<String, String> propMap = parentMap.get(parent);

                Optional<NodeVariable> opt = tpsRoot.getNodeVariables().stream().filter(n -> EcoreUtil.equals(n.getTcNode(), parent)).findFirst();
                if (!opt.isPresent()) {
                    continue;
                }
                NodeVariable val = opt.get();

                for (String key : propMap.keySet()) {
                    if (variable.length() == 0) {
                        String variableName = val.getVariableName();
                        if (variableName == null || variableName.isEmpty()) {
                            variableName = val.getTcNode().getName();
                        }
                        variable.append("var " + variableName + " = {\r\n");
                        if (allVariables.length() == 0) {
                            allVariables.append("var " + allVariablesName + " = [ ");
                            allVariables.append(variableName);
                        } else {
                            allVariables.append("," + variableName);
                        }
                    } else {
                        variable.append(" ,\r\n");
                    }
                    variable.append(key + " : [ " + propMap.get(key) + " ]");
                }
                if (variable.length() != 0) {
                    variable.append("}\r\n");
                    variables.append(variable);
                }
            }
            if (allVariables.length() != 0) {
                allVariables.append("];\r\n");
                variables.append(allVariables);
            }
            // 作成したプロパティとユーザが設定したscritpをまとめてjavascriptで計算しTPRootに格納する
            if (variables.length() != 0) {
                ScriptEngineManager mng = new ScriptEngineManager();
                ScriptEngine engine = mng.getEngineByName("JavaScript");
                for (TPSPatternElement pe : tpsRoot.getPatternElements()) {
                    if (pe.getName().isEmpty() || pe.getValue().isEmpty()) {
                        continue;
                    }
                    try {
                        Object result = engine.eval(variables.toString() + "\r\n" + pe.getValue());
                        TPPatternElement patternElement = TPFactory.eINSTANCE.createTPPatternElement();
                        patternElement.setName(pe.getName());
                        patternElement.setValue(String.valueOf(result));
                        TPTSDPattern ptn = tpSetting.getPatterns().stream().filter(p -> p.getId().equals(entry.getKey())).findFirst().get();
                        ptn.getPatternElements().add(patternElement);
                    } catch (ScriptException e) {
                        setErrorMessage(CALC_FAIL + e.getMessage());
                        return false;
                    }
                }

            }
        }
        return true;

    }

    /**
     * <pre>
     * Create a pattern ID and node full path based on the ACTS result and perform RDF registration processing.
     * 
     * TSDPatternのACTS用IDをノードに変換してDBに登録する
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean createTSDPatternForActs() {
        List<String> actsResults = new ArrayList<>(Arrays.asList(actsResult.split(System.lineSeparator())));
        actsResults.remove(0); // Header

        // create TPTSDPattern List
        List<List<String>> patternsList = new ArrayList<>();
        for (String actsResult : actsResults) {
            List<String> elementIds = Arrays.asList(actsResult.split(","));
            elementIds = elementIds.stream().filter(elementId -> !(elementId.contains("D_") || elementId.equals(""))).collect(Collectors.toList());
            patternsList.add(elementIds);
        }

        // Jobテーブルにレコードが存在しない場合、falseを返却する
        if (Objects.equals(EditResourceUtil.INSTANCE.getJobInfo(jobId), null)) {
            return false;
        }

        // RDF登録
        Map<String, List<String>> patternsMapForRDF = new LinkedHashMap<>();
        for (List<String> patterns : patternsList) {
            List<String> fullPathList = new ArrayList<>();
            for (String pattern : patterns) {
                TCNode node = flatteningResult.getIdMap().get(pattern);
                String fullPathName = NodeUtil.getInstance().getTCNodeFullPath(node, true);
                fullPathList.add(fullPathName);
            }
            patternsMapForRDF.put(String.valueOf(patternsList.indexOf(patterns) + 1), fullPathList);
        }
        if (!registTSDPatternToRDF(patternsMapForRDF)) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * ACTS is executed and a combination using the N-wise is created.
     * 
     * ACTSを実行して組み合わせを生成する
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean executeACTS() {
        if (enableDebug()) {
            try (java.io.BufferedWriter w = new java.io.BufferedWriter(new java.io.FileWriter(new java.io.File("C:\\garden\\debug_in_acts.txt")))) {
                w.write(flatteningResult.getActsinfo());
            } catch (java.io.IOException e) {
            }
        }
        long nWise = getNwiseForACTS();
        ACTSExecutor calculator = new ACTSExecutor();
        actsResult = calculator.execute(flatteningResult.getActsinfo(), nWise);
        if (actsResult != null && !actsResult.isEmpty()) {
            if (enableDebug()) {
                try (java.io.BufferedWriter w = new java.io.BufferedWriter(new java.io.FileWriter(new java.io.File("C:\\garden\\debug_out_acts.txt")))) {
                    w.write(actsResult);
                } catch (java.io.IOException e) {
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * <pre>
     * If Nwise is not set in tpsRoot, the value of Nwise will be calculated / acquired from flatteningResult.
     * If set, get Nwise from tpsRoot.
     * 
     * ACTSに渡すNwiseの設定を行う。
     * </pre>
     * 
     * @return Nwise
     */
    private long getNwiseForACTS() {
        if (tpsRoot.getRootNodes().get(0).getNWiseCombination() == 0 || tpsRoot.getRootNodes().get(0).getNWiseCombination() == -1) {
            String text = flatteningResult.getActsinfo();
            List<String> lines = Arrays.asList(text.split(System.lineSeparator()));
            int matchSize = lines.stream().filter(line -> line.contains(":")).collect(Collectors.toList()).size();
            return matchSize;

        } else {
            return tpsRoot.getRootNodes().get(0).getNWiseCombination();
        }
    }

    /**
     * <pre>
     * Delete the added Optional node from {@link #actsResult}.
     * 
     * 追加したOptionalノードを削除する
     * </pre>
     */
    private void removeOptionalTCNode() {
        Map<String, String> mapping = new LinkedHashMap<String, String>();
        for (Map.Entry<String, TCNode> entry : flatteningResult.getIdMap().entrySet()) {
            TCNode tcNode = entry.getValue();
            if (tcNode.isTemporary() && StringUtils.equals("OFF", tcNode.getName())) {
                mapping.put(entry.getKey(), StringUtils.EMPTY);
            }
        }
        if (mode == TSDFlatteningLogic.MODE_ACTS) {
            actsResult = StringUtils.replaceEach(actsResult, mapping.keySet().toArray(new String[0]), mapping.values().toArray(new String[0]));
        }
    }

    /**
     * <pre>
     * The flattening process is executed and the tree structure becomes three layers.
     * 
     * Flatteningを行う
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean flattening(int mode) {
        try {
            TSDFlatteningLogic logic = new TSDFlatteningLogic();
            flatteningResult = logic.execute(resolveTcRoot, fmcsRoot, mode);
            if (null == flatteningResult) {
                return false;
            }
            if (mode == TSDFlatteningLogic.MODE_ACTS) {
                if (null == flatteningResult.getActsinfo()) {
                    return false;
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * We will solve the cardinality of TCRoot and FMCSRoot.
     * 
     * TCRootとFMCSRootのカーディナリティを解決します。
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean resolveCardinality() {
        TCCardinalityExpander expander = new TCCardinalityExpander();
        if (!expander.execute(resolveTcRoot, fmcsRoot)) {
            setErrorMessage(RESOLVE_CARDINALITY_FAIL);
            return false;
        }
        resolveTcRoot = expander.getExpandTCRoot();
        fmcsRoot = expander.getExpandFMCSRoot();
        return true;
    }

    /**
     * <pre>
     * Delete the Disable node for TCRoot and FMCSRoot.
     * 
     * Disableノードの削除をTSDとFMCSに対して行う
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean removeDisableNode() {
        TCDisableNodeRemover remover = new TCDisableNodeRemover();
        if (!remover.execute(resolveTcRoot, fmcsRoot)) {
            setErrorMessage(REMOVE_DISABLE_NODE_FAIL);
            return false;
        }
        resolveTcRoot = remover.getRemoveTCRoot();
        fmcsRoot = remover.getRemoveFMCSRoot();
        return true;
    }

    /**
     * <pre>
     * Resolve Optional for TCRoot and FMCSRoot.
     * 
     * Optionalの解決をTSDとFMCSに対して行う
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean resolveOptional() {
        TCOptionalExpander expander = new TCOptionalExpander();
        if (!expander.execute(tcRoot, fmcsRoot)) {
            setErrorMessage(RESOLVE_OPTIONAL_FAIL);
            return false;
        }
        tcResolveResult = expander.getExpandTCResolveResult();
        resolveTcRoot = tcResolveResult.getKey();
        fmcsRoot = expander.getExpandFMCSRoot();
        return true;
    }

    /**
     * <pre>
     * Convert FMC (text) to FMCS (syntax Tree).
     * 
     * FMC(text)をFMCS(syntaxTree)に変換する
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean convertFMCS() {
        if (null == fmcRoot) {
            fmcsRoot = FMCSFactory.eINSTANCE.createFMCSRoot();
            return true; // FMC未作成時のユースケース
        }
        FMCSConverter converter = new FMCSConverter(tcRoot, fmcRoot);
        fmcsRoot = converter.convert();
        if (null == fmcsRoot) {
            setErrorMessage(converter.getErrorMessage());
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Read the various files needed for generation from the DB.
     * 
     * generationに必要な各種ファイルをDBから読み込む
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean readFiles() {
        if (!readTps()) {
            setErrorMessage(READ_FPS_FAIL);
            return false;
        }
        if (!readTc()) {
            setErrorMessage(READ_TC_FAIL);
            return false;
        }
        if (!isTcLeafEdgeTypeXOR()) {
            return false; // 内部で個別ノードのエラーメッセージを登録しているのでここでは無し
        }
        if (!isTcNodeHasSomeLeaf()) {
            return false; // 内部で個別ノードのエラーメッセージを登録しているのでここでは無し
        }
        if (!readFmc()) {
            setErrorMessage(READ_FMC_FAIL);
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Determine if all end nodes in the TC file are XOR.
     * 
     * TCファイルの全末端ノードがXORか判定する
     * </pre>
     * 
     * @return true: normal / false: abnormal (there is something other than XOR)
     */
    private boolean isTcLeafEdgeTypeXOR() {
        List<TCNode> nodes = TCUtil.getAllContentsOfType(tcRoot, TCNode.class);
        List<TCNode> leafs = nodes.stream().filter(node -> node.eContainer() instanceof TCNode) // 親がいる
                .filter(node -> node.getChildren().isEmpty()) // 子供がいない
                .filter(node -> !node.isOptional()) // Optionalでない
                .collect(Collectors.toList());
        List<TCNode> wrongs = leafs.stream().filter(node -> ((TCNode) node.eContainer()).getChildType() == ChildType.AND).collect(Collectors.toList());
        if (!wrongs.isEmpty()) {
            setErrorMessage(TC_NODE_TOBE_XOR1 + NodeUtil.getInstance().getTCNodeFullPath((TCNode) wrongs.get(0).eContainer(), false) + TC_NODE_TOBE_XOR2);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Determine if the TC file is a combinable model.
     * 
     * TCファイルが組み合わせ可能なモデルか判定する
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean isTcNodeHasSomeLeaf() {
        TCNode rootNode = tcRoot.getActiveRootNode();
        if (rootNode.getChildType() != ChildType.AND) {
            setErrorMessage(TC_ROOTNODE_TOBE_AND1 + rootNode.getName() + TC_ROOTNODE_TOBE_AND2);
            return false;
        }
        List<TCNode> nodes = TCUtil.getAllContentsOfType(tcRoot, TCNode.class);
        List<TCNode> leafs = nodes.stream().filter(node -> node.getChildType() == ChildType.AND) // ANDである
                .filter(node -> node.getChildren().size() >= 2) // 2つ以上の子ノードを持つ
                .collect(Collectors.toList());
        if (leafs.isEmpty()) {
            setErrorMessage(TC_NODE_HAS_NOT_AND);
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Acquires the file ID of the specified extension among the dependent files of the EMF root model.
     * 
     * EMFが持つ依存ファイルのうち指定した拡張子のファイルIDを取得する
     * </pre>
     * 
     * @param root EMF root model.
     * @param extension specified extension
     * @return file uuid
     */
    private String getRefExtension(AbstractRootElement root, String extension) {
        String id = root.getRefs().stream().filter(ref -> ref.getRefExtension().equals(extension)).map(ref -> ref.getRefid()).findFirst().orElse(null);
        return id;
    }

    /**
     * <pre>
     * Get the FPS file from {@link #inputFileId} and convert the byte data to the TPS root.
     * 
     * FPSファイルを読み込む
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean readTps() {
        long id = inputFileId;
        byte[] byteContent = EditResourceUtil.INSTANCE.getFileContent(id);
        tpsRoot = convertToTPSRoot(byteContent);
        if (null == tpsRoot) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Create a tc root model based on the tc root node included in {@link #tpsRoot}.
     * 
     * TCファイルを読み込む
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean readTc() {
        TCNode tcNode = tpsRoot.getRootNodes().get(0);
        tcRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode copyNode = EcoreUtil.copy(tcNode);
        tcRoot.getRootNodes().add(copyNode);
        if (null == tcRoot) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Read the fmc root model contained in {@ link # tpsRoot}.
     * 
     * FMCファイルを読み込む
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean readFmc() {
        String idStr = getRefExtension(tpsRoot, "fmc");
        if (null == idStr) {
            return true; // FMC未作成時のユースケース
        }
        fmcRoot = tpsRoot.getFmcRoot(); // FPSに内包するようになったので直接取り出す
        if (null == fmcRoot) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Recursively searches for referenced files for files with the specified UUID.
     * If the extension of the reference file matches the specified extension, the UUID of the reference file is returned.
     *
     * 第1引数で指定したuuid から参照たどって，第2引数で指定したextension を探し，そのfileUuid を返却する
     * </pre>
     * 
     * @param sourceFileUuid specified UUID
     * @param fileExtension specified extension
     * @return the UUID of the reference file
     */
    String findFile(String sourceFileUuid, String fileExtension) {
        List<FileReferences> refs = EditResourceUtil.INSTANCE.getReferences(projectId, sourceFileUuid);
        for (FileReferences ref : refs) {
            if (ref.getRefextension().equals(fileExtension)) {
                return ref.getRefuuid();
            } else {
                String refUuid = ref.getRefuuid();
                String uuid = findFile(refUuid, fileExtension);
                if (uuid != null) {
                    return uuid;
                }
            }
        }
        return null;
    }

    /**
     * <pre>
     * Convert byte data read from DB to TPSRoot.
     * 
     * DBから読み込んだbyteデータをTPSRootに変換する
     * </pre>
     * 
     * @param data Byte data of TPSRoot.
     * @return TPSRoot
     */
    private TPSRoot convertToTPSRoot(byte[] data) {
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        if (root instanceof TPSRoot) {
            return (TPSRoot) root;
        }
        return null;
    }
}
