package com.zipc.garden.job.ontology.remover;

/**
 * <pre>
 * RDF remove interface.
 * It summarizes the features required for removal.
 * </pre>
 */
public interface IRDFRemover {

    /**
     * <pre>
     * The result of RDF deletion. SKIP response if it is referenced by others and cannot be deleted.
     * RDF削除の結果。他から参照されていて削除不可の場合はSKIP応答
     * </pre>
     */
    public enum Result {
        SUCCESS, FAIL, SKIP
    }

    /**
     * <pre>
     * Remover the specified instance from RDF. Delete related snap information if possible.
     * RDFから指定したインスタンスを削除する。関連するスナップ情報も可能なら削除する
     * </pre>
     * 
     * @return The result of RDF deletion.
     */
    public Result execute();

    /**
     * <pre>
     * Returns whether it is referenced by others.
     * 他から参照されているかを返す
     * </pre>
     * 
     * @return True if referenced. False if not referenced anywhere.
     */
    public boolean isReferenced();
}
