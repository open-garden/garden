package com.zipc.garden.job.tp;

import com.zipc.garden.job.JobExecutorThread;
import com.zipc.garden.job.ontology.OntologyUtils;
import com.zipc.garden.job.ontology.remover.IRDFRemover;
import com.zipc.garden.job.ontology.remover.IRDFRemover.Result;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * A class that removes the feature pattern ontology.
 */
public class TSDPatternRemover extends JobExecutorThread {

    /**
     * constructor.
     * @param jobId job id
     * @param projectId -1
     * @param outputFileId fps file id
     */
    public TSDPatternRemover(long jobId, long projectId, long outputFileId) {
        super(jobId, projectId, outputFileId);
    }

    /**
     * <pre>
     * The feature pattern deletion process is executed.
     * 
     * {@inheritDoc}
     * </pre>
     */
    @Override
    protected boolean execute(long jobId) {
        return remove();
    }

    /**
     * <pre>
     * Remove the feature pattern ontology.
     * </pre>
     * 
     * @return true: success / false: failure
     */
    public boolean remove() {
        Job job = EditResourceUtil.INSTANCE.getJobInfo(jobId);
        long fileId = job.getInputFileId();
        File file = EditResourceUtil.INSTANCE.getFile(fileId);
        if (file == null) {
            file = EditResourceUtil.INSTANCE.getFile(fileId, true);
        }
        IRDFRemover remover = OntologyUtils.createRemover(file);
        Result result = remover.execute();
        if (result == Result.FAIL) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Jobs matching {@link JobExecutorThread#jobId} are deleted from the table.
     * 
     * {@inheritDoc}
     * </pre>
     */
    @Override
    protected void after() {
        EditResourceUtil.INSTANCE.removeJob(jobId);
    }
}
