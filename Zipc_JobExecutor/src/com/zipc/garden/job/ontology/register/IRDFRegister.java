package com.zipc.garden.job.ontology.register;

/**
 * <pre>
 * RDF registration interface.
 * The functions required for registration are summarized.
 * </pre>
 */
public interface IRDFRegister {

    /**
     * <pre>
     * The result of RDF registration. SKIP response if the same hash is already registered.
     * 
     * RDF登録の結果。同一ハッシュが登録済の場合はSKIP応答
     * </pre>
     */
    public enum Result {
        SUCCESS, FAIL, SKIP
    }

    /**
     * <pre>
     * Register root information in RDF. Also register the relevant snap information.
     * 
     * rootの情報をRDFに登録する。関連となるスナップ情報も登録する
     * </pre>
     * 
     * @return The result of RDF registration.
     */
    public Result execute();
}
