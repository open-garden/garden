package com.zipc.garden.job.ontology.remover;

import com.zipc.garden.webplatform.dao.File;

/**
 * A class that deletes feature model constraint registered in RDF.
 */
public class FMCRemover extends AbstractRemover {

    /**
     * constructor.
     * @param file fmc file
     */
    public FMCRemover(File file) {
        super(file);
    }
}
