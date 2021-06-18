package com.zipc.garden.job.ontology;

import java.util.List;
import java.util.stream.Collectors;

import com.zipc.garden.job.ontology.remover.IRDFRemover;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.dao.rdf.RDFUtil;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * RDF garbage collection. A class that removes unreferenced RDF resources.
 */
public class RDFGarbageCollector {

    /**
     * Perform garbage collection of RDF.
     * @return True if it ends normally or cannot be deleted due to a conflict.
     */
    public boolean execute() {
        // 生成や削除JOB実行前または実行中はRDFの登録削除が競合するため消さない
        List<Job> jobs = EditResourceUtil.INSTANCE.getAllJobInfo();
        long conflictJobCount = jobs.stream().filter(job -> {
            int jobType = job.getType();
            switch (jobType) {
            case Job.TYPE_FP:
            case Job.TYPE_BP:
            case Job.TYPE_SCS:
            case Job.TYPE_REMOVE_FPS:
            case Job.TYPE_REMOVE_BPS:
            case Job.TYPE_REMOVE_SCSS:
                switch (job.getStatus()) {
                case Job.STATUS_NOEXEC:
                case Job.STATUS_EXECUTING:
                    return true;
                default:
                    return false;
                }
            default:
                return false;
            }
        }).count();
        if (conflictJobCount > 0) {
            return true;
        }

        // 削除候補のRDFリソースを取得する
        List<String> instanceURIs = RDFUtil.getNotReferencedFiles();
        List<File> files = instanceURIs.stream().map(OntologyUtils::getFileFromInstanceURI).collect(Collectors.toList());
        List<Job> rmRdfJobs = jobs.stream().filter(job -> job.getType() == Job.TYPE_REMOVE_RDF).collect(Collectors.toList());
        for (File file : files) {
            IRDFRemover remover = OntologyUtils.createRemover(file);
            if (null == remover) { // 削除中のRDFリソースはsubjectのみ残っていてremover化できないので対象外
                continue;
            }
            if (remover.isReferenced()) { // RDFリソースを参照しているものがいれば対象外
                continue;
            }
            List<Job> matchJobs = rmRdfJobs.stream().filter(job -> job.getInputFile().getId().equals(file.getId()))
                    .filter(job -> job.getInputFile().getHash().equals(file.getHash())).collect(Collectors.toList());
            if (!matchJobs.isEmpty()) { // 同一RDFリソースの削除JOBが登録済なら対象外
                continue;
            }
            // 誰からも参照されておらず削除JOBが未登録なら登録
            String removeJobId = EditResourceUtil.INSTANCE.addJob(file.getId(), file, -1, Job.TYPE_REMOVE_RDF, file.getProjectid());
            System.out.println("JobID[" + removeJobId + "]RDF FileID[" + file.getId() + "] Hash[" + file.getHash() + "] Name[" + file.getName() + "." + file.getExtension()
                    + "] regist remove");
        }
        return true;
    }
}
