package com.zipc.garden.job.ontology.remover;

import com.zipc.garden.webplatform.dao.File;

/**
 * A class that deletes architecture model registered in RDF.
 */
public class ARCRemover extends AbstractRemover {

    /**
     * constructor.
     * @param file arc file
     */
    public ARCRemover(File file) {
        super(file);
    }
}
