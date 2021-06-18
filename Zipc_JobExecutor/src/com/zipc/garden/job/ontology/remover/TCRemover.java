package com.zipc.garden.job.ontology.remover;

import com.zipc.garden.webplatform.dao.File;

/**
 * A class that deletes test conditions registered in RDF.
 */
public class TCRemover extends AbstractRemover {

    /**
     * constructor.
     * @param file tc file
     */
    public TCRemover(File file) {
        super(file);
    }
}
