package com.zipc.garden.job.ontology.remover;

import com.zipc.garden.webplatform.dao.File;

/**
 * A class that deletes feature model registered in RDF.
 */
public class FMRemover extends AbstractRemover {

    /**
     * constructor.
     * @param file fm file
     */
    public FMRemover(File file) {
        super(file);
    }
}
