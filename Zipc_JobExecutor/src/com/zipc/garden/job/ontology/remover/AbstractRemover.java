package com.zipc.garden.job.ontology.remover;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;

import com.zipc.garden.job.ontology.OntologyUtils;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.rdf.RDFUtil;

/**
 * <pre>
 * An abstract class that implements the RDF remove interface.
 * It summarizes the processing required for deletion.
 * </pre>
 */
public abstract class AbstractRemover implements IRDFRemover {

    /** The file to delete. */
    private File file;

    /** The instance URI of the file to delete. */
    private String fileInstanceURI;

    /** Reference file for the file to delete. */
    private List<File> refFiles = new ArrayList<>();

    /**
     * constructor.
     * @param file Files to delete
     */
    public AbstractRemover(File file) {
        this.file = file;
        this.fileInstanceURI = generateFileInstanceURI();
    }

    /**
     * <pre>
     * Generate the URI of the File instance.
     * Set in an instance variable ({@link #fileInstanceURI}) and used to check the association between files and RDF.
     * 
     * FileインスタンスのURIを生成する。
     * インスタンス変数fileURIに設定され、ファイルのRDF被関連チェックに使用される
     * </pre>
     * 
     * @return instance of File URI
     */
    protected String generateFileInstanceURI() {
        return OntologyUtils.getFileInstanceURI(file);
    }

    /**
     * {@inheritDoc}
     */
    public Result execute() {
        if (isReferenced()) {
            return Result.SKIP;
        }
        if (!remove()) {
            return Result.FAIL;
        }
        return Result.SUCCESS;
    }

    /**
     * <pre>
     * Get the files to delete.
     * 
     * fileId / ProjectId / Hashを持つFileを取得する
     * </pre>
     * 
     * @return {@link #file}
     */
    protected File getFile() {
        return file;
    }

    /**
     * <pre>
     * Gets the reference file for the file to be deleted.
     * This is a file obtained from RDF.
     * 
     * RDFで関連しているファイルを取得する
     * </pre>
     * 
     * @return {@link #refFiles}
     */
    protected List<File> getRefFiles() {
        return refFiles;
    }

    /**
     * Get the URI of the File instance.
     * @return {@link #fileInstanceURI}
     */
    protected String getFileInstanceURI() {
        return fileInstanceURI;
    }

    /**
     * <pre>
     * Returns whether the model registered in RDF is referenced by others.
     * Override with subclasses as needed.
     * 
     * RDFに登録したモデルが他から参照されているかを返す。
     * 必要に応じてサブクラスでオーバーライドする部分
     * </pre>
     * 
     * @return True if referenced. False if not referenced.
     */
    public boolean isReferenced() {
        return RDFUtil.isResourceFileReferenced(getFileInstanceURI());
    }

    /**
     * Deletes the file model registered in RDF and the reference file model.
     * @return True if the deletion is successful. False if unsuccessful.
     */
    private boolean remove() {
        // RDFで参照しているモデルリストを作成する
        createRefList();
        // RDFに登録したモデルを削除する
        if (!removeOntology()) {
            return false;
        }
        // RDFで参照しているモデルを削除する
        if (!removeRefOntology()) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Create a list of reference file models from RDF based on the {@link #fileInstanceURI instance URI}.
     * It is used to delete the ontology of the referenced file after deleting the ontology of the own file.
     * Override with subclasses as needed.
     * 
     * RDFで自分が参照しているモデルのリストを作成する。
     * 自身を削除した後削除しに行くために作成する。
     * 必要に応じてサブクラスでオーバーライドする部分
     * </pre>
     */
    protected void createRefList() {
        List<String> refInstanceURIs = RDFUtil.getRefFiles(fileInstanceURI);
        refFiles.clear();
        refInstanceURIs.forEach(uri -> refFiles.add(OntologyUtils.getFileFromInstanceURI(uri)));
    }

    /**
     * <pre>
     * Delete the model of the file registered in RDF.
     * Override with subclasses as needed.
     * 
     * RDFに登録したモデルを削除する。
     * 必要に応じてサブクラスがオーバーライドする部分
     * </pre>
     * 
     * @return True if the deletion is successful. False if unsuccessful.
     */
    protected boolean removeOntology() {
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
     * Delete the model of the reference file registered in RDF.
     * Override with subclasses as needed.
     * 
     * RDFで自分が参照しているモデルリストを削除する。
     * 必要に応じてサブクラスでオーバーライドする部分
     * </pre>
     * 
     * @return True if the deletion is successful. False if unsuccessful.
     */
    protected boolean removeRefOntology() {
        boolean totalResult = true;
        for (File refFile : refFiles) {
            IRDFRemover remover = OntologyUtils.createRemover(refFile);
            Result result = remover.execute();
            if (result == Result.FAIL) { // 一つでも失敗したら失敗応答
                totalResult = false;
            }
        }
        return totalResult;
    }
}
