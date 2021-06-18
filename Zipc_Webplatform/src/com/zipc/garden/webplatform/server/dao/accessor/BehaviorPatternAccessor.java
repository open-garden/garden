package com.zipc.garden.webplatform.server.dao.accessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

import com.zipc.garden.model.bp.BPBehavior;
import com.zipc.garden.model.bp.BPBehaviorPattern;
import com.zipc.garden.model.bp.BPEvent;
import com.zipc.garden.model.bp.BPFactory;
import com.zipc.garden.model.bp.BPRoot;
import com.zipc.garden.model.bp.BPSetting;
import com.zipc.garden.model.bp.BPState;
import com.zipc.garden.model.bp.BPStateMachine;
import com.zipc.garden.model.bp.BPStep;
import com.zipc.garden.webplatform.dao.rdf.RDFUtil;
import com.zipc.garden.webplatform.dao.rdf.ontology.BPO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;

/**
 * This is a class that summarizes the process for acquiring behavior-pattern data from RDF.
 */
public class BehaviorPatternAccessor {

    /**
     * Get a specified range of behavior-pattern from RDF. <br>
     * RDFから指定した場所から指定数のパターンを取得する
     * @param projectId ID of the project that manages the BPS file
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @param startRecordOffset Pattern acquisition start position <br>
     *            開始時のパターンのOffset(Offsetは0始まりで番号は1始まり)
     * @param recordCount Number of patterns to acquire<br>
     * @param bpRoot Storage location for the total number of behavior-pattern.
     * @param bpSetting Setting destination of the acquired behavior-pattern.
     * @return Total number of patterns in one file
     * @throws IllegalArgumentException
     */
    public static long getBehaviorPattern(long projectId, long fileId, String hash, long startRecordOffset, long recordCount, BPRoot bpRoot,
            BPSetting bpSetting) throws IllegalArgumentException {
        int totalCount;
        // StateMachine情報を取得
        getAllBehaviorInstance(projectId, fileId, hash, bpSetting);
        // PatternIDリストを取得
        List<Long> patternIdList = getBehaviorPatternIdByBPRoot(bpSetting);
        // Filterと分母数を生成
        StringBuilder filterBuilder = new StringBuilder();
        if (patternIdList.isEmpty()) { // 取得したいパターンIDの指定無し
            long startPatternNo = startRecordOffset + 1;
            long endPatternNo = startPatternNo + recordCount - 1;
            filterBuilder.append("    FILTER(?patternNo >= " + startPatternNo + ")" + System.lineSeparator());
            filterBuilder.append("    FILTER(?patternNo <= " + endPatternNo + ")" + System.lineSeparator());
            // Patternの総数をRDF登録数から設定
            totalCount = getBehaviorPatternIdCount(projectId, fileId, hash);

            // Pageの表示範囲を設定
            bpSetting.setBegin((int) startRecordOffset);
            bpSetting.setEnd((int) endPatternNo);
        } else { // 取得したいパターンIDの指定有り
            // 取得したいパターンが本当に存在するもののみ取得
            patternIdList = getExistsPatternIdList(projectId, fileId, hash, patternIdList);
            totalCount = patternIdList.size();

            long endRecordOffset = startRecordOffset + recordCount;
            List<Long> getPatternIdList = new ArrayList<>();
            for (long i = startRecordOffset; (i < endRecordOffset) && (i < patternIdList.size()); i++) { // 取得したい数か分母の最後に達したら終了
                getPatternIdList.add(patternIdList.get((int) i));
            }

            for (Long getPatternId : getPatternIdList) {
                if (filterBuilder.length() != 0) {
                    filterBuilder.append(" , ");
                }
                filterBuilder.append(getPatternId);
            }
            filterBuilder.insert(0, "     FILTER( ?patternNo in (");
            filterBuilder.append(" ) ) " + System.lineSeparator());
            // Pageの表示範囲を設定
            bpSetting.setBegin((int) startRecordOffset);
            // Endは取得した後ではないと本当に存在しているIDか不明なので createBehaviorInPatternSetで設定
        }

        // RDFから取得すべきBehaviorPatternのIDリストを取得
        List<Long> getPatternIdList = getBehaviorPatternIdList(projectId, fileId, hash, filterBuilder.toString());
        // RDFからBehaviorPatternを取得してBPRootに詰める
        readBehaviorPattern(projectId, fileId, hash, getPatternIdList, bpRoot, bpSetting, false);
        bpRoot.setAll(bpRoot.getAll() + totalCount);
        return totalCount;
    }

    /**
     * Get the data that matches the specified behavior-pattern ID from RDF.<br>
     * RDFから該当するビヘイビアパターンを取得する
     * @param projectId ID of the project that manages the BPS file
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @param patternId ID of the behavior-pattern you want to get
     * @param bpRoot Storage location for the total number of behavior-pattern.
     * @param bpSetting Setting destination of the acquired behavior-pattern.
     * @throws IllegalArgumentException
     */
    public static void getBehaviorPattern(long projectId, long fileId, String hash, long patternId, BPRoot bpRoot, BPSetting bpSetting) throws IllegalArgumentException {
        // Patternの数を取得
        int bpCount = getBehaviorPatternIdCount(projectId, fileId, hash);
        bpRoot.setAll(bpCount);
        // Pageの表示範囲を設定
        bpSetting.setBegin(1);
        bpSetting.setEnd(1);
        // StateMachine情報を取得 TODO:
        getAllBehaviorInstance(projectId, fileId, hash, bpSetting);
        List<Long> getPatternIdList = new ArrayList<>();
        getPatternIdList.add(patternId);
        readBehaviorPattern(projectId, fileId, hash, getPatternIdList, bpRoot, bpSetting, false);
    }

    /**
     * Get the pattern ID stored in BPSetting
     * @param bpSetting BPSetting where the pattern ID to be acquired is stored
     * @return List of PatternID
     */
    private static List<Long> getBehaviorPatternIdByBPRoot(BPSetting bpSetting) {
        List<Long> patternIdList = new ArrayList<>();
        if (null == bpSetting.getPatternNos() || bpSetting.getPatternNos().isEmpty()) {
            return patternIdList;
        }
        if (bpSetting.getPatternNos() != null && !bpSetting.getPatternNos().isEmpty()) {
            List<String> strList = Arrays.asList(bpSetting.getPatternNos().replaceAll("\\r|\\n|\\s", "").split(","));
            strList.forEach(list -> patternIdList.add(Long.parseLong(list)));
            Collections.sort(patternIdList);
        }
        return patternIdList;
    }

    /**
     * Of the specified pattern ID, only the pattern ID registered in RDF is acquired.
     * @param projectId ID of the project that manages the BPS file
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @param patternIdList specified pattern ID
     * @return Pattern ID existing in RDF
     */
    private static List<Long> getExistsPatternIdList(long projectId, long fileId, String hash, List<Long> patternIdList) {
        List<Long> results = new ArrayList<Long>();
        StringBuilder ptnBuilder = new StringBuilder();
        for (Long patternId : patternIdList) {
            if (ptnBuilder.length() != 0) {
                ptnBuilder.append(" , ");
            }
            ptnBuilder.append(patternId);
        }

        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX bps:<http://www.zipc.com/garden/behavior-pattern-setting#> " + System.lineSeparator());
        builder.append("PREFIX bp:<http://www.zipc.com/garden/behavior-pattern#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT DISTINCT ?patternNo WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    ?patternSet rdf:type bp:BehaviorPatternSet ." + System.lineSeparator());
        builder.append("    ?patternSet bp:refSettingFile ?bps." + System.lineSeparator());
        builder.append("    ?bps garden:hasProjectId \"" + projectId + "\"^^xsd:long ." + System.lineSeparator());
        builder.append("    ?bps garden:hasFileId \"" + fileId + "\"^^xsd:long ." + System.lineSeparator());
        builder.append("    ?bps garden:hasHash \"" + hash + "\" ." + System.lineSeparator());
        builder.append("    ?patternSet bp:hasPattern ?pattern ." + System.lineSeparator());
        builder.append("    ?pattern rdf:type bp:BehaviorPattern ." + System.lineSeparator());
        builder.append("    ?pattern bp:hasPatternNo ?patternNo ." + System.lineSeparator());
        builder.append("     FILTER( ?patternNo in (");
        builder.append(ptnBuilder);
        builder.append(" ) ) " + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        builder.append("order by ?patternNo" + System.lineSeparator());

        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            Query query = QueryFactory.create(builder.toString());
            try (QueryExecution qe = conn.query(query)) {
                ResultSet rs = qe.execSelect();
                while (rs.hasNext()) {
                    QuerySolution qs = rs.next();
                    results.add(qs.getLiteral("patternNo").getLong());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return results;
    }

    /**
     * Gets the behavior-pattern ID list from RDF based on the specified Filter condition.<br>
     * Filterの指定付きでRDFからビヘイビアパターンのIDリストを取得する
     * @param projectId ID of the project that manages the BPS file
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @param filter specified Filter condition
     * @return list of patternID
     * @throws IllegalArgumentException
     */
    private static List<Long> getBehaviorPatternIdList(long projectId, long fileId, String hash, String filter) throws IllegalArgumentException {
        // Queryを生成
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX bps:<http://www.zipc.com/garden/behavior-pattern-setting#> " + System.lineSeparator());
        builder.append("PREFIX bp:<http://www.zipc.com/garden/behavior-pattern#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT DISTINCT ?patternNo WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    ?patternSet rdf:type bp:BehaviorPatternSet ." + System.lineSeparator());
        builder.append("    ?patternSet bp:refSettingFile ?bps." + System.lineSeparator());
        builder.append("    ?bps garden:hasProjectId \"" + projectId + "\"^^xsd:long ." + System.lineSeparator());
        builder.append("    ?bps garden:hasFileId \"" + fileId + "\"^^xsd:long ." + System.lineSeparator());
        builder.append("    ?bps garden:hasHash \"" + hash + "\" ." + System.lineSeparator());
        builder.append("    ?patternSet bp:hasPattern ?pattern ." + System.lineSeparator());
        builder.append("    ?pattern rdf:type bp:BehaviorPattern ." + System.lineSeparator());
        builder.append("    ?pattern bp:hasPatternNo ?patternNo ." + System.lineSeparator());
        builder.append(filter);
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        List<Long> patternNoList = new ArrayList<>();
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                if (query.isSelectType()) {
                    ResultSet rs = qexec.execSelect();
                    List<String> variables = rs.getResultVars();
                    while (rs.hasNext()) {
                        QuerySolution soln = rs.nextSolution();
                        for (String variable : variables) {
                            RDFNode node = soln.get(variable);
                            if (variable.equals("patternNo")) {
                                if (node.isLiteral()) {
                                    patternNoList.add(((Literal) node).getLong());
                                }
                            }
                        }
                    }
                }
                Collections.sort(patternNoList);
                return patternNoList;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Get the total number of behavior-pattern from RDF.<br>
     * RDFからビヘイビアパターンの数を取得する
     * @param projectId ID of the project that manages the BPS file
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @return count of patternID
     * @throws IllegalArgumentException
     */
    private static int getBehaviorPatternIdCount(long projectId, long fileId, String hash) throws IllegalArgumentException {
        // Queryを生成
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX bps:<http://www.zipc.com/garden/behavior-pattern-setting#> " + System.lineSeparator());
        builder.append("PREFIX bp:<http://www.zipc.com/garden/behavior-pattern#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT (COUNT(DISTINCT ?patternNo) AS ?patternNoCount) WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    ?patternSet rdf:type bp:BehaviorPatternSet ." + System.lineSeparator());
        builder.append("    ?patternSet bp:refSettingFile ?bps." + System.lineSeparator());
        builder.append("    ?bps garden:hasProjectId \"" + projectId + "\"^^xsd:long ." + System.lineSeparator());
        builder.append("    ?bps garden:hasFileId \"" + fileId + "\"^^xsd:long ." + System.lineSeparator());
        builder.append("    ?bps garden:hasHash \"" + hash + "\" ." + System.lineSeparator());
        builder.append("    ?patternSet bp:hasPattern ?pattern ." + System.lineSeparator());
        builder.append("    ?pattern rdf:type bp:BehaviorPattern ." + System.lineSeparator());
        builder.append("    ?pattern bp:hasPatternNo ?patternNo ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                if (query.isSelectType()) {
                    ResultSet rs = qexec.execSelect();
                    List<String> variables = rs.getResultVars();
                    while (rs.hasNext()) {
                        QuerySolution soln = rs.nextSolution();
                        for (String variable : variables) {
                            RDFNode node = soln.get(variable);
                            if (variable.equals("patternNoCount")) {
                                if (node.isLiteral()) {
                                    return ((Literal) node).getInt();
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return 0;
    }

    /**
     * Obtain the behavior-pattern from RDF and set it in BPSetting.<br>
     * RDFからBehaviorPatternを取得してBPRootに詰める
     * @param projectId ID of the project that manages the BPS file
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @param patternNoList ID of the behavior-pattern you want to get
     * @param bpRoot Storage location for the total number of behavior-pattern.
     * @param bpSetting Setting destination of the acquired behavior-pattern.
     * @param isScsGenerate True when used in SCS generation
     * @throws IllegalArgumentException
     */
    private static void readBehaviorPattern(long projectId, long fileId, String hash, List<Long> patternNoList, BPRoot bpRoot, BPSetting bpSetting,
            boolean isScsGenerate) throws IllegalArgumentException {
        // Patternがないなら応答
        if (patternNoList.isEmpty()) {
            bpSetting.setCount(0);
            return;
        }
        // PatternSet内で共通のBehavior名称とStep数を取得
        List<String> behaviorNames = getBehaviorNamesInPattern(fileId, hash, patternNoList.get(0));
        List<Long> stepNos = null;
        if (!behaviorNames.isEmpty()) {
            stepNos = getStepsInBehavior(fileId, hash, patternNoList.get(0), behaviorNames.get(0));
        } else {
            stepNos = new ArrayList<>();
        }
        // Patternを読み込み
        readBehaviorInPatternSet(fileId, hash, patternNoList, behaviorNames, stepNos, bpRoot, bpSetting, isScsGenerate);
    }

    /**
     * Gets the instance name of behavior-pattern from RDF.<br>
     * FileId/Hash/PatternNoが持つBehaviorのインスタンス名をリストで取得する
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @param patternNo behavior-pattern ID
     * @return List of BehaviorName
     */
    private static List<String> getBehaviorNamesInPattern(long fileId, String hash, long patternNo) {
        String patternURI = GDNNS.UD.NS + fileId + "_" + hash + "_" + "BP_Pattern" + patternNo;
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX bps:<http://www.zipc.com/garden/behavior-pattern-setting#> " + System.lineSeparator());
        builder.append("PREFIX bp:<http://www.zipc.com/garden/behavior-pattern#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT DISTINCT ?behaviorName WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    <" + patternURI + "> bp:hasBehavior ?behavior ." + System.lineSeparator());
        builder.append("    ?behavior bp:stateMachineInstance ?instance ." + System.lineSeparator());
        builder.append("    ?instance bps:hasOriginalName ?behaviorName ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        List<String> behaviorNamesInPattern = new ArrayList<>();
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (variable.equals("behaviorName")) {
                            if (node.isLiteral()) {
                                behaviorNamesInPattern.add(((Literal) node).getString());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        Collections.sort(behaviorNamesInPattern);
        return behaviorNamesInPattern;
    }

    /**
     * Get the step number of behavior-pattern from RDF..<br>
     * FileId/Hash/PatternNo/BehaviorNameが持つBehaviorのStepNoをリストで取得する
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @param patternNo behavior-pattern ID
     * @param behaviorName behavior name
     * @return List of StepNo
     */
    private static List<Long> getStepsInBehavior(long fileId, String hash, long patternNo, String behaviorName) {
        String behaviorURI = GDNNS.UD.NS + fileId + "_" + hash + "_" + "BP_Pattern" + patternNo + "_" + behaviorName;
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX bps:<http://www.zipc.com/garden/behavior-pattern-setting#> " + System.lineSeparator());
        builder.append("PREFIX bp:<http://www.zipc.com/garden/behavior-pattern#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT DISTINCT ?stepNo WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    <" + behaviorURI + "> bp:hasStep ?step ." + System.lineSeparator());
        builder.append("    ?step bp:hasStepNo ?stepNo ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        List<Long> behaviorNames = new ArrayList<>();
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (variable.equals("stepNo")) {
                            if (node.isLiteral()) {
                                behaviorNames.add(((Literal) node).getLong());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        Collections.sort(behaviorNames);
        return behaviorNames;
    }

    /**
     * Reads information in all specified patterns from RDF<br>
     * RDFから指定した全パターン内の情報を読み込む
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @param patternNos ID of the behavior-pattern you want to get
     * @param behaviorNames Behavior name list obtained from RDF
     * @param stepNos List of step numbers obtained from RDF
     * @param bpRoot Storage location for the total number of behavior-pattern.
     * @param bpSetting Setting destination of the acquired behavior-pattern.
     * @param isScsGenerate True when used in SCS generation
     */
    private static void readBehaviorInPatternSet(long fileId, String hash, List<Long> patternNos, List<String> behaviorNames, List<Long> stepNos, BPRoot bpRoot,
            BPSetting bpSetting, boolean isScsGenerate) {
        Map<String, String> eventMap = readEventInPatternSet(fileId, hash, patternNos, bpSetting, isScsGenerate);
        Map<String, String> stateMap = readStateInPatternSet(fileId, hash, patternNos, bpSetting, isScsGenerate);
        createBehaviorInPatternSet(fileId, hash, patternNos, behaviorNames, stepNos, eventMap, stateMap, bpRoot, bpSetting);
        return;
    }

    /**
     * Set the Event / State obtained from RDF.<br>
     * BPRootにRDFから取得したEvent/Stateを詰める
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @param patternNos List of specified pattern numbers
     * @param behaviorNames Behavior name list obtained from RDF
     * @param stepNos List of step numbers obtained from RDF
     * @param eventMap Map of StepURI and Event name obtained from RDF
     * @param stateMap Map of StepURI and State name obtained from RDF
     * @param bpRoot Storage location for the total number of behavior-pattern.
     * @param bpSetting Setting destination of the acquired behavior-pattern.
     */
    private static void createBehaviorInPatternSet(long fileId, String hash, List<Long> patternNos, List<String> behaviorNames, List<Long> stepNos, Map<String, String> eventMap,
            Map<String, String> stateMap, BPRoot bpRoot, BPSetting bpSetting) {
        for (Long patternNo : patternNos) {
            BPBehaviorPattern bpPattern = BPFactory.eINSTANCE.createBPBehaviorPattern();
            bpPattern.setId(String.valueOf(patternNo));
            for (String behaviorName : behaviorNames) {
                BPBehavior bpBehavior = BPFactory.eINSTANCE.createBPBehavior();
                bpBehavior.setStateMachineRef(getStateMachine(bpSetting, behaviorName));
                for (Long stepNo : stepNos) {
                    BPStep bpStep = BPFactory.eINSTANCE.createBPStep();
                    bpBehavior.getSteps().add(bpStep);
                    String stepURI = GDNNS.UD.NS + fileId + "_" + hash + "_" + "BP_Pattern" + patternNo + "_" + behaviorName + "_" + stepNo;
                    String eventName = eventMap.get(stepURI);
                    if (null != eventName) {
                        BPEvent bpEvent = getEvent(bpBehavior.getStateMachineRef(), eventName);
                        if (null != bpEvent) {
                            bpStep.setEvent(bpEvent);
                        }
                    }
                    String stateName = stateMap.get(stepURI);
                    if (null != stateName) {
                        BPState bpState = getState(bpBehavior.getStateMachineRef(), stateName);
                        if (null != bpState) {
                            bpStep.setState(bpState);
                        }
                    }
                    bpBehavior.getSteps().add(bpStep);
                }
                bpPattern.getBehaviors().add(bpBehavior);
            }
            bpSetting.getPattern().add(bpPattern);
        }
        bpSetting.setCount(bpSetting.getPattern().size());
        bpSetting.setEnd(bpSetting.getPattern().size());
        bpRoot.setEnd(bpRoot.getEnd() + bpSetting.getPattern().size());
    }

    /**
     * Read Event information in all specified patterns from RDF<br>
     * RDFから指定した全パターン内のEvent情報を読み込む
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @param patternNos List of specified pattern numbers
     * @param bpSetting Setting destination of the acquired behavior-pattern.
     * @param isScsGenerate True when used with SCS generation.
     * @return Map<stepURI, eventName>
     */
    private static Map<String, String> readEventInPatternSet(long fileId, String hash, List<Long> patternNos, BPSetting bpSetting, boolean isScsGenerate) {
        // PatternNoのフィルタ
        StringBuilder filterBuilder = new StringBuilder();
        if (!isScsGenerate) {
            filterBuilder.append("    FILTER( ?pattern in ( ");
            for (Long patternNo : patternNos) {
                filterBuilder.append("<http://www.zipc.com/garden/user-data#");
                filterBuilder.append(fileId).append("_");
                filterBuilder.append(hash).append("_");
                filterBuilder.append(BPO.BP_NAME).append("_");
                filterBuilder.append("Pattern").append(patternNo).append(">");
                if (patternNos.indexOf(patternNo) < patternNos.size() - 1) {
                    filterBuilder.append(" , ");
                }
            }
            filterBuilder.append(" ) ) ." + System.lineSeparator());
        }

        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX bps:<http://www.zipc.com/garden/behavior-pattern-setting#> " + System.lineSeparator());
        builder.append("PREFIX bp:<http://www.zipc.com/garden/behavior-pattern#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT DISTINCT ?stepURI ?eventName WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        if (isScsGenerate) {
            builder.append("?bps garden:hasFileId \"").append(fileId).append("\"^^xsd:long ." + System.lineSeparator());
            builder.append("?bps garden:hasHash \"").append(hash).append("\"^^xsd:string ." + System.lineSeparator());
            builder.append("?bp bp:refSettingFile ?bps ." + System.lineSeparator());
            builder.append("?bp bp:hasPattern ?pattern .");
        }
        builder.append("    ?pattern bp:hasBehavior ?behavior ." + System.lineSeparator());
        builder.append("    ?behavior bp:hasStep ?stepURI ." + System.lineSeparator());
        builder.append("    ?stepURI bp:hasTransition ?transition ." + System.lineSeparator());
        builder.append("    ?transition bps:hasEvent ?eventName." + System.lineSeparator());
        builder.append(filterBuilder.toString());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        Map<String, String> eventMap = new LinkedHashMap<>();
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            try (QueryExecution qexec = conn.query(sparql)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    String stepURI = null;
                    String eventName = null;
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (variable.equals("stepURI")) {
                            if (node.isResource()) {
                                stepURI = ((Resource) node).getURI();
                            }
                        }
                        if (variable.equals("eventName")) {
                            if (node.isLiteral()) {
                                eventName = ((Literal) node).getString();
                            }
                        }
                    }
                    eventMap.put(stepURI, eventName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return eventMap;
    }

    /**
     * Read State information in all specified patterns from RDF<br>
     * RDFから指定した全パターン内のState情報を読み込む
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @param patternNos List of specified pattern numbers
     * @param bpSetting Setting destination of the acquired behavior-pattern.
     * @param isScsGenerate True when used with SCS generation.
     * @return Map<stepURI, eventName>
     */
    private static Map<String, String> readStateInPatternSet(long fileId, String hash, List<Long> patternNos, BPSetting bpSetting, boolean isScsGenerate) {
        // PatternNoのフィルタ
        StringBuilder filterBuilder = new StringBuilder();
        if (!isScsGenerate) {
            filterBuilder.append("    FILTER( ?pattern in ( ");
            for (Long patternNo : patternNos) {
                filterBuilder.append("<http://www.zipc.com/garden/user-data#");
                filterBuilder.append(fileId).append("_");
                filterBuilder.append(hash).append("_");
                filterBuilder.append(BPO.BP_NAME).append("_");
                filterBuilder.append("Pattern").append(patternNo).append(">");
                if (patternNos.indexOf(patternNo) < patternNos.size() - 1) {
                    filterBuilder.append(" , ");
                }
            }
            filterBuilder.append(" ) ) ." + System.lineSeparator());
        }

        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX bps:<http://www.zipc.com/garden/behavior-pattern-setting#> " + System.lineSeparator());
        builder.append("PREFIX bp:<http://www.zipc.com/garden/behavior-pattern#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT DISTINCT ?stepURI ?behaviorInstanceURI ?stateURI WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        if (isScsGenerate) {
            builder.append("?bps garden:hasFileId \"").append(fileId).append("\"^^xsd:long ." + System.lineSeparator());
            builder.append("?bps garden:hasHash \"").append(hash).append("\"^^xsd:string ." + System.lineSeparator());
            builder.append("?bp bp:refSettingFile ?bps ." + System.lineSeparator());
            builder.append("?bp bp:hasPattern ?pattern .");
        }
        builder.append("    ?pattern bp:hasBehavior ?behavior ." + System.lineSeparator());
        builder.append("    ?behavior bp:hasStep ?stepURI ." + System.lineSeparator());
        builder.append("    ?stepURI bp:hasState ?stateURI ." + System.lineSeparator());
        builder.append("    ?behaviorInstanceURI bps:hasState ?stateURI ." + System.lineSeparator());
        builder.append(filterBuilder.toString());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        Map<String, String> eventMap = new LinkedHashMap<>();
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            try (QueryExecution qexec = conn.query(sparql)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    String stepURI = null;
                    String behaviorInstanceURI = null;
                    String stateName = null;
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (variable.equals("stepURI")) {
                            if (node.isResource()) {
                                stepURI = ((Resource) node).getURI();
                            }
                        } else if (variable.equals("behaviorInstanceURI")) {
                            if (node.isResource()) {
                                behaviorInstanceURI = ((Resource) node).getURI();
                            }
                        } else if (variable.equals("stateURI")) {
                            if (node.isResource()) {
                                String stateURI = ((Resource) node).getURI();
                                stateName = stateURI.replace(behaviorInstanceURI + "_State_", "");
                            }
                        }
                    }
                    eventMap.put(stepURI, stateName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return eventMap;
    }

    /**
     * Gets the BPStateMachine that matches the specified FSM instance name from BPSetting.
     * @param bpSetting BPSetting to be searched
     * @param instanceName FSM instance name
     * @return BPStateMachine
     */
    private static BPStateMachine getStateMachine(BPSetting bpSetting, String instanceName) {
        return bpSetting.getStateMachines().stream().filter(fsm -> fsm.getName().equals(instanceName)).findFirst().orElse(null);
    }

    /**
     * Gets the BPEvent that matches the specified event name from the BPStateMachine.
     * @param bpFsm BPStateMachine to be searched
     * @param eventName specified event name
     * @return BPEvent
     */
    private static BPEvent getEvent(BPStateMachine bpFsm, String eventName) {
        return bpFsm.getEvents().stream().filter(event -> event.getValue().equals(eventName)).findFirst().orElse(null);
    }

    /**
     * Gets the BPState that matches the specified state name from the BPStateMachine.
     * @param bpFsm BPStateMachine to be searched
     * @param stateName specified state name
     * @return BPState
     */
    private static BPState getState(BPStateMachine bpFsm, String stateName) {
        return bpFsm.getStates().stream().filter(state -> state.getValue().equals(stateName)).findFirst().orElse(null);
    }

    // BPSが持つBehaviorを取得
    /**
     * Get an instance of Behavior owned by BP from RDF and set it in BPRoot.<br>
     * RDFからBPが持つBehaviorのインスタンスを取得してBPRootに設定する
     * @param projectId ID of the project that manages the BPS file
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @param bpSetting Setting destination of the acquired behavior-pattern. <br>
     *            格納先のBPRoot
     */
    private static void getAllBehaviorInstance(long projectId, long fileId, String hash, BPSetting bpSetting) {
        List<String> behaviorInstanceURIs = getBehaviorInstanceURIs(projectId, fileId, hash);
        behaviorInstanceURIs.forEach(behaviorInstanceURI -> {
            bpSetting.getStateMachines().add(getBehaviorInstance(behaviorInstanceURI));
        });
    }

    /**
     * Get the behavior instance URI from RDF.<br>
     * RDFからビヘイビアインスタンスのURIを取得する
     * @param projectId ID of the project that manages the BPS file
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @return behavior instance URI
     */
    private static List<String> getBehaviorInstanceURIs(long projectId, long fileId, String hash) {
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX bps:<http://www.zipc.com/garden/behavior-pattern-setting#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT DISTINCT ?behavior WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    ?bps garden:hasProjectId \"" + projectId + "\"^^xsd:long ." + System.lineSeparator());
        builder.append("    ?bps garden:hasFileId \"" + fileId + "\"^^xsd:long ." + System.lineSeparator());
        builder.append("    ?bps garden:hasHash \"" + hash + "\" ." + System.lineSeparator());
        builder.append("    ?bps bps:hasArc ?arc ." + System.lineSeparator());
        builder.append("    ?arc bps:hasBehavior ?behavior ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());

        String sparql = builder.toString();
        List<String> behaviorInstanceURIs = new ArrayList<>();
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (variable.equals("behavior")) {
                            if (node.isResource()) {
                                behaviorInstanceURIs.add(((Resource) node).getURI());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return behaviorInstanceURIs;
    }

    /**
     * Get an instance of Behavior owned by BP from RDF and set it in BPStateMachine.<br>
     * RDFからBPが持つBehaviorのインスタンスを取得してBPStateMachineに設定する
     * @param behaviorInstanceURI Behavior instance URI of BPS.<br>
     *            BPSが持つビヘイビアインスタンスURI
     * @return BPStateMachine
     */
    private static BPStateMachine getBehaviorInstance(String behaviorInstanceURI) {
        // FsmのuuidとinstanceNameを取得
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX bps:<http://www.zipc.com/garden/behavior-pattern-setting#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT DISTINCT ?id ?name WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    <" + behaviorInstanceURI + "> bps:hasOriginalUuid ?id ." + System.lineSeparator());
        builder.append("    <" + behaviorInstanceURI + "> bps:hasOriginalName ?name ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        String fsmInstanceId = null;
        String fsmInstanceName = null;
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (variable.equals("id")) {
                            if (node.isLiteral()) {
                                fsmInstanceId = (((Literal) node).getString());
                            }
                        } else if (variable.equals("name")) {
                            if (node.isLiteral()) {
                                fsmInstanceName = (((Literal) node).getString());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        BPStateMachine bpFsm = BPFactory.eINSTANCE.createBPStateMachine();
        bpFsm.setFsmId(fsmInstanceId);
        bpFsm.setName(fsmInstanceName);
        // Fsm内の全Stateを取得
        List<String> stateNames = getBehaviorInstanceStateNames(behaviorInstanceURI);
        for (String stateName : stateNames) {
            BPState bpState = BPFactory.eINSTANCE.createBPState();
            bpState.setName(fsmInstanceId);
            bpState.setValue(stateName);
            bpFsm.getStates().add(bpState);
        }
        // Fsm内の全Eventを取得
        List<String> eventNames = getBehaviorInstanceEventNames(behaviorInstanceURI);
        for (String eventName : eventNames) {
            BPEvent bpEvent = BPFactory.eINSTANCE.createBPEvent();
            bpEvent.setName(fsmInstanceId);
            bpEvent.setValue(eventName);
            bpFsm.getEvents().add(bpEvent);
        }
        return bpFsm;
    }

    /**
     * Get the State name of the instance URI from RDF. <br>
     * RDFからインスタンスURIが持つState名をリストで取得する
     * @param behaviorInstanceURI Behavior instance URI of BPS.<br>
     *            BPSが持つビヘイビアインスタンスURI
     * @return list of StateName
     */
    private static List<String> getBehaviorInstanceStateNames(String behaviorInstanceURI) {
        List<String> states = new ArrayList<>();
        List<String> stateURIs = getBehaviorInstanceStateURIs(behaviorInstanceURI);
        for (String stateURI : stateURIs) {
            String state = stateURI.replace(behaviorInstanceURI + "_State_", "");
            states.add(state);
        }
        return states;
    }

    /**
     * Get the StateURI that the instance URI has from RDF. <br>
     * RDFからインスタンスURIが持つStateURIをリストで取得する
     * @param behaviorInstanceURI Behavior instance URI of BPS.<br>
     *            BPSが持つビヘイビアインスタンスURI
     * @return list of StateURI
     */
    private static List<String> getBehaviorInstanceStateURIs(String behaviorInstanceURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX bps:<http://www.zipc.com/garden/behavior-pattern-setting#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT DISTINCT ?state ?name WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    <" + behaviorInstanceURI + "> bps:hasState ?state ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());

        String sparql = builder.toString();
        List<String> stateURIs = new ArrayList<>();
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (variable.equals("state")) {
                            if (node.isResource()) {
                                stateURIs.add((((Resource) node).getURI()));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return stateURIs;
    }

    /**
     * Get the Event name of the instance URI from RDF.<br>
     * RDFからインスタンスURIが持つEvent名をリストで取得する
     * @param behaviorInstanceURI Behavior instance URI of BPS.<br>
     *            BPSが持つビヘイビアインスタンスURI
     * @return list of EventName
     */
    private static List<String> getBehaviorInstanceEventNames(String behaviorInstanceURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX bps:<http://www.zipc.com/garden/behavior-pattern-setting#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT DISTINCT ?event WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    <" + behaviorInstanceURI + "> bps:hasState ?state ." + System.lineSeparator());
        builder.append("    ?state bps:hasTransition ?transition ." + System.lineSeparator());
        builder.append("    ?transition bps:hasEvent ?event ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());

        String sparql = builder.toString();
        List<String> eventNames = new ArrayList<>();
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (variable.equals("event")) {
                            if (node.isLiteral()) {
                                eventNames.add((((Literal) node).getString()));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return eventNames;
    }

    /**
     * Obtain the pattern number from RDF and set it in the argument bpSetting.
     * @param projectId ID of the project that manages the BPS file
     * @param fileId BPS file ID
     * @param hash Hash value of BPS file
     * @param bpSetting Setting destination of the acquired behavior-pattern.
     * @param patternNums Pattern number of the behavior-pattern to get
     * @throws IllegalArgumentException
     */
    public static void getAllBehaviorPattern(long projectId, long fileId, String hash, BPSetting bpSetting, String[] patternNums) throws IllegalArgumentException {
        // StateMachine情報を取得
        getAllBehaviorInstance(projectId, fileId, hash, bpSetting);

        // 指定されたパターンの文字列を作成
        StringBuilder filterBuilder = new StringBuilder();
        if (patternNums != null) {
            for (String getPatternId : patternNums) {
                if (filterBuilder.length() != 0) {
                    filterBuilder.append(" , ");
                }
                filterBuilder.append(getPatternId);
            }
        }
        // 指定されたパターンがあればフィルターをかける
        if (filterBuilder.length() != 0) {
            filterBuilder.insert(0, "     FILTER( ?patternNo in (");
            filterBuilder.append(" ) ) " + System.lineSeparator());
        }
        // RDFからBehaviorPatternのIDリストを取得
        List<Long> patternIdList = getBehaviorPatternIdList(projectId, fileId, hash, filterBuilder.toString());

        // PatternNoをBPRootに詰める
        patternIdList.forEach(id -> {
            BPBehaviorPattern ptn = BPFactory.eINSTANCE.createBPBehaviorPattern();
            ptn.setId(String.valueOf(id));
            bpSetting.getPattern().add(ptn);
        });
    }
}
