package com.zipc.garden.job;

import com.zipc.garden.job.bp.BehaviorPatternGenerator;
import com.zipc.garden.job.bp.BehaviorPatternRemover;
import com.zipc.garden.job.cscs.CSCSPatternGenerator;
import com.zipc.garden.job.ontology.remover.RDFRemoverThread;
import com.zipc.garden.job.scs.SCSPatternGenerator;
import com.zipc.garden.job.scs.ScenarioSetRemover;
import com.zipc.garden.job.tp.TSDPatternGenerator;
import com.zipc.garden.job.tp.TSDPatternRemover;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * A class that manages the methods that execute jobs.
 */
public class JobExecutorUtil {

    /**
     * The process is executed according to the specified job type.
     * @param job Job executed
     * @return Class that executes a job as a thread.
     */
    public static JobExecutorThread createThread(Job job) {
        long projectId = -1;
        long jobId = job.getId();
        long type = job.getType();
        long outputFileId = job.getOutputFileId();
        if (type == Job.TYPE_FP) {
            projectId = EditResourceUtil.INSTANCE.getProjectId(Long.valueOf(job.getInputFileId()));
            return new TSDPatternGenerator(jobId, projectId, outputFileId);
        } else if (type == Job.TYPE_BP) {
            projectId = EditResourceUtil.INSTANCE.getProjectId(Long.valueOf(job.getInputFileId()));
            return new BehaviorPatternGenerator(jobId, projectId, outputFileId);
        } else if (type == Job.TYPE_SCS) {
            projectId = EditResourceUtil.INSTANCE.getProjectId(Long.valueOf(job.getInputFileId()));
            return new SCSPatternGenerator(jobId, projectId, outputFileId);
        } else if (type == Job.TYPE_CSCS) {
            projectId = EditResourceUtil.INSTANCE.getProjectId(Long.valueOf(job.getInputFileId()));
            return new CSCSPatternGenerator(jobId, projectId, outputFileId);
        } else if (type == Job.TYPE_REMOVE_FPS) {
            projectId = -1;
            return new TSDPatternRemover(jobId, projectId, outputFileId);
        } else if (type == Job.TYPE_REMOVE_BPS) {
            projectId = -1;
            return new BehaviorPatternRemover(jobId, projectId, outputFileId);
        } else if (type == Job.TYPE_REMOVE_SCSS) {
            projectId = -1;
            return new ScenarioSetRemover(jobId, projectId, outputFileId);
        } else if (type == Job.TYPE_REMOVE_RDF) {
            projectId = -1;
            return new RDFRemoverThread(jobId, projectId, outputFileId);
        }
        return null;
    }
}
