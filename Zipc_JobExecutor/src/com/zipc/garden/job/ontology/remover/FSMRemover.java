package com.zipc.garden.job.ontology.remover;

import com.zipc.garden.webplatform.dao.File;

/**
 * A class that deletes finite state machine registered in RDF.
 */
public class FSMRemover extends AbstractRemover {

    /**
     * constructor.
     * @param file fsm file
     */
    public FSMRemover(File file) {
        super(file);
    }
}
