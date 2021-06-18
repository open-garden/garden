package com.zipc.garden.webplatform.server.dao.accessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.eclipse.emf.common.util.ECollections;

import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.tp.TPElement;
import com.zipc.garden.model.tp.TPFactory;
import com.zipc.garden.model.tp.TPPatternElement;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tp.TPTSDPattern;
import com.zipc.garden.webplatform.dao.rdf.RDFUtil;

/**
 * This is a class that summarizes the process for acquiring feature-pattern data from RDF.
 */
public class FeaturePatternAccessor {

    /**
     * Get a specified range of feature-pattern from RDF. <br>
     * RDFから指定した場所から指定数のパターンを取得する
     * @param projectId ID of the project that manages the FPS file
     * @param fileId FPS file ID
     * @param hash Hash value of FPS file
     * @param startRecordOffset Pattern acquisition start position <br>
     *            開始時のパターンのOffset(Offsetは0始まりで番号は1始まり)
     * @param recordCount Number of patterns to acquire
     * @param tpRoot Storage location for the total number of feature-pattern.
     * @param setting Setting destination of the acquired feature-pattern.
     * @return Total number of patterns in one file
     * @throws IllegalArgumentException
     */
    public static long getFeaturePattern(long projectId, long fileId, String hash, long startRecordOffset, long recordCount, TPRoot tpRoot,
            TPSetting setting) throws IllegalArgumentException {
        List<Long> patternIdList = getFeaturePatternIdByTPRoot(setting);
        double totalCount;

        // Filterと分母数を生成
        StringBuilder filterBuilder = new StringBuilder();
        if (patternIdList.isEmpty()) { // 取得したいパターンIDの指定無し
            totalCount = getFeaturePatternCountByRDF(projectId, fileId, hash);
            long startPatternNo = startRecordOffset + 1;
            long requiredEndPatternNo = startPatternNo + recordCount - 1;
            long endPatternNo = (requiredEndPatternNo < totalCount) ? requiredEndPatternNo : (long) totalCount;
            filterBuilder.append("    FILTER(?patternNo >= " + startPatternNo + ")" + System.lineSeparator());
            filterBuilder.append("    FILTER(?patternNo <= " + endPatternNo + ")" + System.lineSeparator());
            // Pageの表示範囲を設定
            setting.setBegin((int) startRecordOffset);
            setting.setEnd((int) endPatternNo);
        } else { // 取得したいパターンIDの指定有り
            // 取得したいパターンが本当に存在するもののみ取得
            patternIdList = getExistsPatternIdList(projectId, fileId, hash, patternIdList);
            totalCount = patternIdList.size();
            long endRecordOffset = startRecordOffset + recordCount;
            List<Long> getPatternIdList = new ArrayList<>();
            for (long i = startRecordOffset; (i < endRecordOffset) && (i < patternIdList.size()); i++) { //
                // 取得したい数か分母の最後に達したら終了
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
            setting.setBegin((int) startRecordOffset);
            // Endは取得した後ではないと本当に存在しているIDか不明なので getFeaturePatternByRDFで設定
        }

        // FeaturePatternを取得
        getFeaturePattern(projectId, fileId, hash, filterBuilder.toString(), setting);
        tpRoot.setEnd(tpRoot.getEnd() + setting.getPatterns().size());
        // Patternの総数をRDF登録数から設定
        tpRoot.setAll(tpRoot.getAll() + totalCount);
        return (long) totalCount;
    }

    /**
     * Get the data that matches the specified feature-pattern ID from RDF.
     * @param projectId ID of the project that manages the FPS file
     * @param fileId FPS file ID
     * @param hash Hash value of FPS file
     * @param patternId ID of the feature-pattern you want to get
     * @param tpRoot Storage location for the total number of feature-pattern.
     * @param setting Setting destination of the acquired feature-pattern.
     * @throws IllegalArgumentException
     */
    public static void getFeaturePattern(long projectId, long fileId, String hash, long patternId, TPRoot tpRoot, TPSetting setting) throws IllegalArgumentException {
        StringBuilder filterBuilder = new StringBuilder();
        filterBuilder.append("    FILTER(?patternNo = " + patternId + ")" + System.lineSeparator());
        long totalCount = getFeaturePatternCountByRDF(projectId, fileId, hash);

        // FeaturePatternを取得
        getFeaturePattern(projectId, fileId, hash, filterBuilder.toString(), setting);
        tpRoot.setAll(totalCount);
    }

    /**
     * The pattern number of the setting class of the specified feature-pattern will be returned as a list.
     * @param setting Setting class of the specified feature pattern
     * @return List of PatternID
     */
    private static List<Long> getFeaturePatternIdByTPRoot(AbstractSetting setting) {
        List<Long> patternIdList = new ArrayList<>();
        if (null == setting.getPatternNos() || setting.getPatternNos().isEmpty()) {
            return patternIdList;
        }
        if (setting.getPatternNos() != null && !setting.getPatternNos().isEmpty()) {
            List<String> strList = Arrays.asList(setting.getPatternNos().replaceAll("\\r|\\n|\\s", "").split(","));
            strList.forEach(list -> patternIdList.add(Long.parseLong(list)));
        }
        return patternIdList;
    }

    /**
     * Of the specified pattern ID, only the pattern ID registered in RDF is acquired.
     * @param projectId ID of the project that manages the FPS file
     * @param fileId FPS file ID
     * @param hash Hash value of FPS file
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
        builder.append("PREFIX fps:<http://www.zipc.com/garden/feature-pattern-setting#> " + System.lineSeparator());
        builder.append("PREFIX fp:<http://www.zipc.com/garden/feature-pattern#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT ?patternNo WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    ?patternSet rdf:type fp:FeaturePatternSet ." + System.lineSeparator());
        builder.append("    ?patternSet fp:refSettingFile ?fps." + System.lineSeparator());
        builder.append("    ?fps garden:hasProjectId \"" + projectId + "\"^^xsd:long ." + System.lineSeparator());
        builder.append("    ?fps garden:hasFileId \"" + fileId + "\"^^xsd:long ." + System.lineSeparator());
        builder.append("    ?fps garden:hasHash \"" + hash + "\" ." + System.lineSeparator());
        builder.append("    ?patternSet fp:hasPattern ?pattern ." + System.lineSeparator());
        builder.append("    ?pattern rdf:type fp:FeaturePattern ." + System.lineSeparator());
        builder.append("    ?pattern fp:hasPatternNo ?patternNo ." + System.lineSeparator());
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
     * Get the number of feature patterns registered in RDF<br>
     * RDFに登録されているフィーチャパターン数を取得する
     * @param projectId ID of the project that manages the FPS file
     * @param fileId FPS file ID
     * @param hash Hash value of FPS file
     * @return count of patterns
     * @throws IllegalArgumentException
     */
    private static int getFeaturePatternCountByRDF(long projectId, long fileId, String hash) throws IllegalArgumentException {

        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX fps:<http://www.zipc.com/garden/feature-pattern-setting#> " + System.lineSeparator());
        builder.append("PREFIX fp:<http://www.zipc.com/garden/feature-pattern#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT (COUNT(?pattern) AS ?count) WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    ?patternSet rdf:type fp:FeaturePatternSet ." + System.lineSeparator());
        builder.append("    ?patternSet fp:refSettingFile ?fps." + System.lineSeparator());
        builder.append("    ?fps garden:hasProjectId \"" + projectId + "\"^^xsd:long ." + System.lineSeparator());
        builder.append("    ?fps garden:hasFileId \"" + fileId + "\"^^xsd:long ." + System.lineSeparator());
        builder.append("    ?fps garden:hasHash \"" + hash + "\" ." + System.lineSeparator());
        builder.append("    ?patternSet fp:hasPattern ?pattern ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());

        String sparql = builder.toString();
        int count = 0;
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (variable.equals("count")) {
                            if (node.isLiteral()) {
                                count = ((Literal) node).getInt();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return count;
    }

    /**
     * Get feature-pattern data from RDF.
     * @param projectId ID of the project that manages the FPS file
     * @param fileId FPS file ID
     * @param hash Hash value of FPS file
     * @param filter Filter condition of feature-pattern to be acquired
     * @param setting Setting destination of the acquired feature-pattern.
     * @throws IllegalArgumentException
     */
    private static void getFeaturePattern(long projectId, long fileId, String hash, String filter, TPSetting setting) throws IllegalArgumentException {
        // Queryを生成
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX fps:<http://www.zipc.com/garden/feature-pattern-setting#> " + System.lineSeparator());
        builder.append("PREFIX fp:<http://www.zipc.com/garden/feature-pattern#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT ?patternNo ?fullPath ?simplePath ?patternElementName ?patternElementValue WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    ?patternSet rdf:type fp:FeaturePatternSet ." + System.lineSeparator());
        builder.append("    ?patternSet fp:refSettingFile ?fps." + System.lineSeparator());
        builder.append("    ?fps garden:hasProjectId \"" + projectId + "\"^^xsd:long ." + System.lineSeparator());
        builder.append("    ?fps garden:hasFileId \"" + fileId + "\"^^xsd:long ." + System.lineSeparator());
        builder.append("    ?fps garden:hasHash \"" + hash + "\" ." + System.lineSeparator());
        builder.append("    ?patternSet fp:hasPattern ?pattern ." + System.lineSeparator());
        builder.append("    ?pattern rdf:type fp:FeaturePattern ." + System.lineSeparator());
        builder.append("    ?pattern fp:hasPatternNo ?patternNo ." + System.lineSeparator());
        builder.append("    ?pattern fp:refNode ?refNode ." + System.lineSeparator());
        builder.append("    ?refNode fps:hasFullPath ?fullPath ." + System.lineSeparator());
        builder.append("    ?refNode fps:hasSimplePath ?simplePath ." + System.lineSeparator());
        builder.append("    OPTIONAL {" + System.lineSeparator());
        builder.append("      ?pattern fp:hasPatternElement ?patternElement ." + System.lineSeparator());
        builder.append("      ?patternElement fp:hasPatternElementName ?patternElementName ." + System.lineSeparator());
        builder.append("      ?patternElement fp:hasPatternElementValue ?patternElementValue ." + System.lineSeparator());
        builder.append("    } ." + System.lineSeparator());
        builder.append(filter);
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        getFeaturePatternByRDF(builder.toString(), setting);
    }

    /**
     * Issue the specified SPARQL syntax and get the feature-pattern data from RDF.
     * @param sparql specified SPARQL syntax
     * @param setting Setting destination of the acquired feature-pattern.
     * @throws IllegalArgumentException
     */
    private static void getFeaturePatternByRDF(String sparql, TPSetting setting) throws IllegalArgumentException {
        Map<String, TPElement> fullPathElementMap = new HashMap<>();
        Map<String, TPTSDPattern> idPatternMap = new HashMap<>();

        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                if (query.isSelectType()) {
                    ResultSet rs = qexec.execSelect();
                    List<String> variables = rs.getResultVars();
                    while (rs.hasNext()) {
                        String patternNo = null;
                        String fullPath = null;
                        String simplePath = null;
                        String patternElementName = null;
                        String patternElementValue = null;
                        QuerySolution soln = rs.nextSolution();
                        for (String variable : variables) {
                            RDFNode node = soln.get(variable);
                            if (variable.equals("patternNo")) {
                                if (node.isLiteral()) {
                                    patternNo = String.valueOf(((Literal) node).getLong());
                                }
                            } else if (variable.equals("fullPath")) {
                                if (node.isLiteral()) {
                                    fullPath = ((Literal) node).getString();
                                }
                            } else if (variable.equals("simplePath")) {
                                if (node.isLiteral()) {
                                    simplePath = ((Literal) node).getString();
                                }
                            } else if (variable.equals("patternElementName")) {
                                if (node != null && node.isLiteral()) {
                                    patternElementName = ((Literal) node).getString();
                                }
                            } else if (variable.equals("patternElementValue")) {
                                if (node != null && node.isLiteral()) {
                                    patternElementValue = ((Literal) node).getString();
                                }
                            }
                        }

                        // フルパスからPatternのインスタンスを取得または生成
                        TPTSDPattern pattern = null;
                        if (idPatternMap.containsKey(patternNo)) {
                            pattern = idPatternMap.get(patternNo);
                        } else {
                            pattern = TPFactory.eINSTANCE.createTPTSDPattern();
                            pattern.setId(patternNo);
                            idPatternMap.put(patternNo, pattern);
                        }

                        // フルパスからElementのインスタンスを生成
                        TPElement element = null;
                        if (fullPathElementMap.containsKey(fullPath)) {
                            element = fullPathElementMap.get(fullPath);
                        } else {
                            element = TPFactory.eINSTANCE.createTPElement();
                            element.setFullPath(fullPath);
                            element.setSimplePath(simplePath);
                            fullPathElementMap.put(fullPath, element);
                        }

                        // PatternElementのインスタンスを生成
                        if (patternElementName != null) {
                            TPPatternElement patternElement = TPFactory.eINSTANCE.createTPPatternElement();
                            patternElement.setName(patternElementName);
                            patternElement.setValue(patternElementValue);

                            // PatternにPatternElementを追加
                            pattern.getPatternElements().add(patternElement);
                        }

                        // PatternにElementを追加(生成済は参照がはられる)
                        pattern.getElements().add(element);

                        // tpRootにPatternが未追加なら追加
                        if (!setting.getPatterns().contains(pattern)) {
                            setting.getPatterns().add(pattern);
                        }
                    }
                    ECollections.sort(setting.getPatterns(), (s1, s2) -> (int) (Long.valueOf(s1.getId()) - Long.valueOf(s2.getId())));
                    setting.setCount(setting.getPatterns().size());
                    setting.setEnd(setting.getPatterns().size());
                    // tpRoot.setEnd(tpRoot.getEnd() + setting.getPatterns().size());
                    return;
                } else {
                    return;
                }
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
     * Get feature-pattern data from RDF.
     * @param projectId ID of the project that manages the FPS file
     * @param fileId FPS file ID
     * @param hash Hash value of FPS file
     * @param patternNums Pattern number of the feature-pattern to get
     * @param setting Setting destination of the acquired feature-pattern.
     * @throws IllegalArgumentException
     */
    public static void getAllFeaturePattern(long projectId, long fileId, String hash, String[] patternNums, TPSetting setting) throws IllegalArgumentException {
        StringBuilder filterBuilder = new StringBuilder();
        // 指定されたパターンの文字列を作成
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
        // RDFからBehaviorPatternのIDリストを取得してTPRootに詰める
        getFeaturePattern(projectId, fileId, hash, filterBuilder.toString(), setting);
    }

}
