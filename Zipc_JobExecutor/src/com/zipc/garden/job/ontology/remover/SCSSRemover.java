package com.zipc.garden.job.ontology.remover;

import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.apache.jena.vocabulary.XSD;

import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.rdf.RDFUtil;
import com.zipc.garden.webplatform.dao.rdf.ontology.GBO;
import com.zipc.garden.webplatform.dao.rdf.ontology.SCSO;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * A class that deletes scenarioset setting registered in RDF.
 */
public class SCSSRemover extends AbstractRemover {

    /**
     * constructor.
     * @param file scss file
     */
    public SCSSRemover(File file) {
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
     * {@inheritDoc}
     */
    @Override
    protected boolean removeOntology() {
        boolean result = true;
        if (!removeFileOntology()) {
            result = false;
        }
        return result;
    }

    /**
     * The ontology associated with the scss file ID and hash value is removed.
     * @return True if the deletion is successful. False if the deletion ends abnormally.
     */
    private boolean removeFileOntology() {
        StringBuilder delete = new StringBuilder();
        delete.append("WITH <http://www.zipc.com/garden/scenario>");
        delete.append("DELETE { ?ScsInsNameScenario ?p ?o }");
        delete.append(" WHERE {");
        delete.append("  ?ScssInsName <").append(GBO.hasFileId.getURI()).append(">\"").append(getFile().getId()).append("\"^^<").append(XSD.xlong.getURI()).append("> .");
        delete.append("  ?ScssInsName <").append(GBO.hasHash.getURI()).append(">\"").append(getFile().getHash()).append("\" .");
        delete.append("  ?ScsInsName <").append(SCSO.refSettingFile.getURI()).append("> ?ScssInsName .");
        delete.append("  ?ScsInsName <").append(SCSO.hasScenario.getURI()).append("> ?ScsInsNameScenario .");
        delete.append("  ?ScsInsNameScenario ?p ?o .");
        delete.append(" };");

        delete.append("WITH <http://www.zipc.com/garden/scenario>");
        delete.append("DELETE { ?ScsInsName ?p ?o }");
        delete.append(" WHERE {");
        delete.append("  ?ScssInsName <").append(GBO.hasFileId.getURI()).append(">\"").append(getFile().getId()).append("\"^^<").append(XSD.xlong.getURI()).append("> .");
        delete.append("  ?ScssInsName <").append(GBO.hasHash.getURI()).append(">\"").append(getFile().getHash()).append("\" .");
        delete.append("  ?ScsInsName <").append(SCSO.refSettingFile.getURI()).append("> ?ScssInsName .");
        delete.append("  ?ScsInsName ?p ?o .");
        delete.append(" };");

        delete.append("WITH <http://www.zipc.com/garden/scenario>");
        delete.append("DELETE { ?ScssInsName ?p ?o }");
        delete.append(" WHERE {");
        delete.append("  ?ScssInsName <").append(GBO.hasFileId.getURI()).append(">\"").append(getFile().getId()).append("\"^^<").append(XSD.xlong.getURI()).append("> .");
        delete.append("  ?ScssInsName <").append(GBO.hasHash.getURI()).append(">\"").append(getFile().getHash()).append("\" .");
        delete.append("  ?ScssInsName ?p ?o .");
        delete.append(" };");

        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            UpdateRequest deleteRequest = UpdateFactory.create(delete.toString());
            conn.update(deleteRequest);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
