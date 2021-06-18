package com.zipc.garden.job.ontology.remover;

import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;

import com.zipc.garden.job.ontology.OntologyUtils;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.rdf.RDFUtil;
import com.zipc.garden.webplatform.dao.rdf.ontology.BPO;
import com.zipc.garden.webplatform.dao.rdf.ontology.BPSO;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * A class that deletes behavior pattern setting registered in RDF.
 */
public class BPSRemover extends AbstractRemover {

    /**
     * constructor.
     * @param file bps file
     */
    public BPSRemover(File file) {
        super(file);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReferenced() {
        // RDB上に同じハッシュ値のものが存在する(ユーザがパターンを生成した状態がRDB上に残っている)
        File rdbFile = EditResourceUtil.INSTANCE.getFile(getFile().getId());
        if (null != rdbFile) {
            if (rdbFile.getHash().equals(getFile().getHash())) {
                return true;
            }
        }
        // RDBにない場合はRDF上に自身を参照しているインスタンスがいるか確認する
        return RDFUtil.isResourceFileReferenced(getFileInstanceURI());
    }

    /**
     * <pre>
     * Delete hierarchically based on the RDF structure.
     * RDF構造に基づき階層的に削除していく
     * 
     * {@inheritDoc}
     * </pre>
     */
    @Override
    protected boolean removeOntology() {
        boolean result = true;
        String arcURI = getArcURI(getFileInstanceURI()); // RootNodeを取得
        String bpsURI = getBpsURI(getFileInstanceURI()); // BehaviorPatternSetを取得
        if (!removeFileOntology()) { // File情報を全て削除
            result = false;
        }
        if (!removeArcOntology(arcURI)) { // BPSが持つArc情報を全て削除
            result = false;
        }
        if (!removeBehaviorPatternSetOntology(bpsURI)) { // BPSにぶら下がるBP情報を全て削除
            result = false;
        }
        return result;
    }

    /**
     * The ontology whose subject is the URI of the File instance is removed.
     * @return True if the deletion is successful. False if the deletion ends abnormally.
     */
    private boolean removeFileOntology() {
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("     <" + getFileInstanceURI() + "> ?p ?o ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            UpdateRequest request = UpdateFactory.create(sparql.toString());
            conn.update(request);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Gets an object with the specified subject and "bps: hasArc" as the predicate.
     * subjectURI bps:hasArc ?o の?oをURI形式で取得する
     * </pre>
     * 
     * @param subjectURI File instance URI
     * @return Architecture model instance URI
     */
    private String getArcURI(String subjectURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ?o WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append(" <" + subjectURI + "> <" + BPSO.hasArc.getURI() + "> ?o ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (node.isResource()) {
                            return ((Resource) node).getURI();
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
        return null;
    }

    /**
     * <pre>
     * Gets an subject with the specified object and "bp: refSettingFile" as the predicate.
     * ?s bp:refSettingFile objectURI の?sをURI形式で取得する
     * </pre>
     * 
     * @param objectURI behavior pattern setting URI
     * @return behavior pattern URI
     */
    private String getBpsURI(String objectURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ?s WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append(" ?s <" + BPO.refSettingFile.getURI() + "> <" + objectURI + "> ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (node.isResource()) {
                            return ((Resource) node).getURI();
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
        return null;
    }

    /**
     * <pre>
     * Deletes all ontology with the specified subject.
     * arcURIを?sに持つ?s ?p ?oを全て削除する
     * </pre>
     * 
     * @param arcURI specified subject (architecture URI)
     * @return True if the deletion is successful. False if deletion fails.
     */
    private boolean removeArcOntology(String arcURI) {
        List<String> arcBehaviorURIs = OntologyUtils.getObjectURIs(arcURI, BPSO.hasBehavior.getURI());
        arcBehaviorURIs.forEach(arcBehaviorURI -> removeArcBehaviorOntology(arcBehaviorURI));
        List<String> arcDataflowURIs = OntologyUtils.getObjectURIs(arcURI, BPSO.hasDataflow.getURI());
        arcDataflowURIs.forEach(arcDataflowURI -> removeArcDataflowOntology(arcDataflowURI));
        return OntologyUtils.removeTripleBySubject(arcURI);
    }

    /**
     * <pre>
     * Deletes all ontology with the specified subject.
     * arcBehaviorURIを?sに持つ?s ?p ?oを全て削除する
     * </pre>
     * 
     * @param arcBehaviorURI specified subject (architecture behavior URI)
     * @return True if the deletion is successful. False if deletion fails.
     */
    private boolean removeArcBehaviorOntology(String arcBehaviorURI) {
        List<String> stateURIs = OntologyUtils.getObjectURIs(arcBehaviorURI, BPSO.hasState.getURI());
        stateURIs.forEach(stateURI -> removeStateOntology(stateURI));
        List<String> variableURIs = OntologyUtils.getObjectURIs(arcBehaviorURI, BPSO.hasVariable.getURI());
        variableURIs.forEach(variableURI -> removeVariableOntology(variableURI));
        return OntologyUtils.removeTripleBySubject(arcBehaviorURI);
    }

    /**
     * <pre>
     * Deletes all ontology with the specified subject.
     * arcDataflowURIを?sに持つ?s ?p ?oを全て削除する
     * </pre>
     * 
     * @param arcDataflowURI specified subject (architecture dataflow URI)
     * @return True if the deletion is successful. False if deletion fails.
     */
    private boolean removeArcDataflowOntology(String arcDataflowURI) {
        return OntologyUtils.removeTripleBySubject(arcDataflowURI);
    }

    /**
     * <pre>
     * Deletes all ontology with the specified subject.
     * stateURIを?sに持つ?s ?p ?oを全て削除する
     * </pre>
     * 
     * @param stateURI specified subject (state URI)
     * @return True if the deletion is successful. False if deletion fails.
     */
    private boolean removeStateOntology(String stateURI) {
        List<String> transitionURIs = OntologyUtils.getObjectURIs(stateURI, BPSO.hasTransition.getURI());
        transitionURIs.forEach(transitionURI -> removeTransitionOntology(transitionURI));
        return OntologyUtils.removeTripleBySubject(stateURI);
    }

    /**
     * <pre>
     * Deletes all ontology with the specified subject.
     * transitionURIを?sに持つ?s ?p ?oを全て削除する
     * </pre>
     * 
     * @param transitionURI specified subject (transition URI)
     * @return True if the deletion is successful. False if deletion fails.
     */
    private boolean removeTransitionOntology(String transitionURI) {
        return OntologyUtils.removeTripleBySubject(transitionURI);
    }

    /**
     * <pre>
     * Deletes all ontology with the specified subject.
     * variableURIを?sに持つ?s ?p ?oを全て削除する
     * </pre>
     * 
     * @param variableURI specified subject (variable URI)
     * @return True if the deletion is successful. False if deletion fails.
     */
    private boolean removeVariableOntology(String variableURI) {
        return OntologyUtils.removeTripleBySubject(variableURI);
    }

    // BP削除

    /**
     * <pre>
     * Deletes all ontology belonging to the specified Behavior Pattern URI.
     * bpURI以下のBehaviorPatternを全て削除する
     * </pre>
     * 
     * @param bpsURI behavior pattern URI
     * @return True if the deletion is successful. False if deletion fails.
     */
    private boolean removeBehaviorPatternSetOntology(String bpsURI) {
        List<String> bpURIs = getBehaviorPatternURIs(bpsURI);
        bpURIs.forEach(bpURI -> removeBehaviorPatternOntology(bpURI));
        return OntologyUtils.removeTripleBySubject(bpsURI);
    }

    /**
     * <pre>
     * Get the object with the specified subject and "bp: hasPattern" as the predicate.
     * bpURIの子Patternをリストで取得する
     * </pre>
     * 
     * @param bpsURI specified subject (behavior pattern URI)
     * @return list of pattern URI
     */
    private List<String> getBehaviorPatternURIs(String bpsURI) {
        return OntologyUtils.getObjectURIs(bpsURI, BPO.hasPattern.getURI());
    }

    /**
     * <pre>
     * Deletes all ontology belonging to the specified pattern URI.
     * pattern以下のBehaviorを全て削除する
     * </pre>
     * 
     * @param bpURI Specified pattern URI
     * @return True if the deletion is successful. False if deletion fails.
     */
    private boolean removeBehaviorPatternOntology(String bpURI) {
        List<String> behaviorURIs = getBehaviorURIs(bpURI);
        behaviorURIs.forEach(behaviorURI -> removeBehaviorOntology(behaviorURI));
        return OntologyUtils.removeTripleBySubject(bpURI);
    }

    /**
     * <pre>
     * Get the object with the specified subject and "bp: hasBehavior" as the predicate.
     * 子Behaviorを取得する
     * </pre>
     * 
     * @param patternURI specified subject (pattern URI)
     * @return list of BehaviorURI
     */
    private List<String> getBehaviorURIs(String patternURI) {
        return OntologyUtils.getObjectURIs(patternURI, BPO.hasBehavior.getURI());
    }

    /**
     * <pre>
     * Delete all Steps that belong to the specified behavior URI.
     * behaviorURI以下のStepを全て削除する
     * </pre>
     * 
     * @param behaviorURI specified subject (behavior URI)
     * @return True if the deletion is successful. False if deletion fails.
     */
    private boolean removeBehaviorOntology(String behaviorURI) {
        List<String> stepURIs = getStepURIs(behaviorURI);
        stepURIs.forEach(stepURI -> OntologyUtils.removeTripleBySubject(stepURI));
        return OntologyUtils.removeTripleBySubject(behaviorURI);
    }

    /**
     * <pre>
     * Get the object with the specified subject and "bp: hasStep" as the predicate.
     * 子Stepを取得する
     * </pre>
     * 
     * @param behaviorURI specified subject (behavior URI)
     * @return list of StepURI
     */
    private List<String> getStepURIs(String behaviorURI) {
        return OntologyUtils.getObjectURIs(behaviorURI, BPO.hasStep.getURI());
    }
}
