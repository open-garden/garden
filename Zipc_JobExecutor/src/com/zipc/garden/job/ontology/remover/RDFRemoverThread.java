package com.zipc.garden.job.ontology.remover;

import com.zipc.garden.job.JobExecutorThread;
import com.zipc.garden.job.ontology.OntologyUtils;
import com.zipc.garden.job.ontology.remover.IRDFRemover.Result;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * A class that removes unreferenced RDF.
 */
public class RDFRemoverThread extends JobExecutorThread {

    /**
     * constructor
     * @param jobId The ID of the job being run.
     * @param projectId -1
     * @param outputFileId -1
     */
    public RDFRemoverThread(long jobId, long projectId, long outputFileId) {
        super(jobId, projectId, outputFileId);
    }

    /**
     * <pre>
     * Gets the job that matches the job ID and gets the input file from the job.
     * Deletes RDF based on the information in the input file.
     * 
     * {@inheritDoc}
     * </pre>
     */
    @Override
    protected boolean execute(long jobId) {
        Job job = EditResourceUtil.INSTANCE.getJobInfo(jobId);
        File file = job.getInputFile();
        IRDFRemover remover = OntologyUtils.createRemover(file);
        if (null != remover) {
            Result result = remover.execute();
            if (result == Result.SUCCESS) {
                System.out.println(
                        "JobID[" + jobId + "] RDF FileID[" + file.getId() + "] Hash[" + file.getHash() + "]Name[" + file.getName() + "." + file.getExtension() + "] is removed");
                return true;
            } else if (result == Result.FAIL) {
                System.out.println(
                        "JobID[" + jobId + "] RDF FileID[" + file.getId() + "] Hash[" + file.getHash() + "]Name[" + file.getName() + "." + file.getExtension() + "] remove fail");
                return false;
            }
        }
        return false;
    }

    /**
     * <pre>
     * Deletes the record that matches the job ID from the job table.
     * 
     * {@inheritDoc}
     * </pre>
     */
    @Override
    protected void after() {
        EditResourceUtil.INSTANCE.removeJob(jobId);
    }
}
