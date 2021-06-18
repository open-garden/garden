package com.zipc.garden.job.ontology.remover;

import java.util.ArrayList;
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
import com.zipc.garden.webplatform.dao.rdf.ontology.FPO;
import com.zipc.garden.webplatform.dao.rdf.ontology.FPSO;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * A class that deletes feature pattern setting registered in RDF.
 */
public class FPSRemover extends AbstractRemover {

    /**
     * constructor.
     * @param file fps file
     */
    public FPSRemover(File file) {
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
        String rootNodeURI = getRootNodeURI(getFileInstanceURI()); // RootNodeを取得
        String fpsURI = getFpsURI(getFileInstanceURI()); // FeaturePatternSetを取得
        if (!removeFileOntology()) {
            result = false;
        }
        if (!removeNode(rootNodeURI)) {
            result = false;
        }
        if (!removeFeaturePatternOntology(fpsURI)) {
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
     * Gets an object with the specified subject and "fps: hasRootNode" as the predicate.
     * subjectURI fps:hasRootNode ?o の?oをURI形式で取得する
     * </pre>
     * 
     * @param subjectURI File instance URI
     * @return URI of the root node
     */
    private String getRootNodeURI(String subjectURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ?o WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    <" + subjectURI + "> <" + FPSO.hasRootNode.getURI() + "> ?o ." + System.lineSeparator());
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
     * The RDF ontology whose subject is the specified URI is deleted.
     * subjectURIを?sに持つ?s ?p ?oを全て削除する
     * </pre>
     * 
     * @param subjectURI root node URI
     * @return True if the deletion is successful. False if deletion fails.
     */
    private boolean removeNode(String subjectURI) {
        List<String> childNodes = getChildNodeURIs(subjectURI);
        childNodes.forEach(childNode -> removeNode(childNode)); // 先に子ノードを削除する
        return OntologyUtils.removeTripleBySubject(subjectURI);
    }

    /**
     * <pre>
     * Gets an object with the specified subject and "fps: hasChildNode" as the predicate.
     * Nodeの子Nodeを取得する
     * </pre>
     * 
     * @param subjectURI root node URI
     * @return list of child node URI
     */
    private List<String> getChildNodeURIs(String subjectURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ?o WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    <" + subjectURI + "> <" + FPSO.hasChildNode.getURI() + "> ?o ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        List<String> childNodeURIs = new ArrayList<>();
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
                            childNodeURIs.add(((Resource) node).getURI());
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
        return childNodeURIs;
    }

    /**
     * <pre>
     * Gets an subject with the specified object and "fp:refSettingFile" as the predicate.
     * ?s fp:refSettingFile objectURI の?sをURI形式で取得する
     * </pre>
     * 
     * @param objectURI feature pattern setting URI
     * @return feature pattern URI
     */
    private String getFpsURI(String objectURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ?s WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    ?s <" + FPO.refSettingFile.getURI() + "> <" + objectURI + "> ." + System.lineSeparator());
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
     * feature Pattern Delete all Feature Patterns under the URI
     * feature Pattern URI以下のFeaturePatternを全て削除する
     * </pre>
     * 
     * @param fpsURI feature pattern URI
     * @return True if the deletion is successful. False if deletion fails.
     */
    private boolean removeFeaturePatternOntology(String fpsURI) {
        List<String> fpURIs = getFeaturePatternURIs(fpsURI);
        fpURIs.forEach(fpURI -> OntologyUtils.removeTripleBySubject(fpURI)); // 先に子ノードを削除する
        return OntologyUtils.removeTripleBySubject(fpsURI);
    }

    /**
     * <pre>
     * Gets an object with the specified subject and "fp:hasPattern" as the predicate.
     * </pre>
     * 
     * @param fpsURI feature pattern URI
     * @return list of feature pattern setting URI
     */
    private List<String> getFeaturePatternURIs(String fpsURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ?o WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    <" + fpsURI + "> <" + FPO.hasPattern.getURI() + "> ?o ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        List<String> fpURIs = new ArrayList<>();
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
                            fpURIs.add(((Resource) node).getURI());
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
        return fpURIs;
    }
}
