package com.zipc.garden.webplatform.server.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.FileReferences;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.UserInfo;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * JOB that generates Feature-pattern is added to JOB table.<br>
 * Also, we will create a Feature-pattern file.
 */
public class GenerateTSDPatternAcceptor {

    /** ID of the project that created the FPS file */
    long projectId;

    /** feature-pattern-setting File ID */
    long settingFileId;

    /** User information of logged in */
    UserInfo userInfo;

    /** feature-pattern-setting file record */
    File tpsFile;

    /** feature-pattern-setting file info */
    VMFile tpsVMFile;

    /** feature-pattern file info */
    VMFile tpVMFile;

    /** EMF root model for feature-pattern */
    TPRoot tpRoot;

    /** feature-pattern File ID */
    long tpFileId;

    /** True if creating a feature-pattern file, False otherwise */
    boolean isCreateFile;

    /**
     * feature-pattern generation process is executed.<br>
     * Here, an empty feature-pattern file is created and a job is registered.
     * @param settingFileId {@link #settingFileId}
     * @param userInfo {@link #userInfo}
     * @param isCreateFile {@link #isCreateFile}
     * @return The feature-pattern file you created. If not created, null is returned.
     */
    public VMFile execute(long settingFileId, UserInfo userInfo, boolean isCreateFile) {
        System.out.println("settingFileId:" + settingFileId);
        this.settingFileId = settingFileId;
        this.userInfo = userInfo;
        this.isCreateFile = isCreateFile;
        this.tpsFile = EditResourceUtil.INSTANCE.getFile(settingFileId);
        this.tpsVMFile = EditResourceUtil.INSTANCE.getVMFile(settingFileId);
        this.projectId = EditResourceUtil.INSTANCE.getProjectId(tpsVMFile.getId());

        if (!createTPFile()) {
            return null;
        }

        // Jobが実行中の場合は実行しない
        if (!isJobExecuting()) {
            addJob();
        }
        return tpVMFile;
    }

    /**
     * Generate feature-pattern Check if the JOB is already running.
     * @return If true, the JOB is running. If false, there are no jobs in progress.
     */
    private boolean isJobExecuting() {
        List<Job> jobList = EditResourceUtil.INSTANCE.getAllJobInfo().stream().filter(job -> job.getInputFileId() == settingFileId).filter(job -> job.getType() == Job.TYPE_FP)
                .collect(Collectors.toList());
        if (jobList.isEmpty()) {
            return false;
        } else {
            List<Job> removeJobList = jobList.stream().filter(job -> job.getStatus() == Job.STATUS_COMPLETE).collect(Collectors.toList());
            removeJobList.forEach(job -> EditResourceUtil.INSTANCE.removeJob(job.getId()));
            jobList.removeIf(job -> removeJobList.contains(job));
            if (jobList.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Create a VMFile (initial state) of feature-pattern that is generated based on the feature-pattern-setting file.
     * @param tpsVMFile feature-pattern-setting file info
     * @param createFileName The name of the feature-pattern file to create. <br>
     *            If null, it will be created with the same name as the feature-pattern-setting file name.
     * @return Created feature-pattern file information
     */
    private VMFile registDefaultTPVMFile(VMFile tpsVMFile, String createFileName) {
        // set File
        String tpsFileName = createFileName == null ? tpsVMFile.getName() : createFileName;
        VMFile tpVMFile = new VMFile();
        tpVMFile.setName(tpsFileName);
        tpVMFile.setExtension(Extension.FP);

        // set FileReferences
        long dirId = EditResourceUtil.INSTANCE.getDirId(tpsVMFile.getId());
        List<Long> refList = new ArrayList<>();
        refList.add(tpsVMFile.getId());

        // create File and File References
        long fileId = EditResourceUtil.INSTANCE.createFile(dirId, tpVMFile, refList, userInfo);
        tpFileId = fileId;
        tpVMFile.setId(fileId);
        System.out.println("outputFileId:" + tpFileId);
        return tpVMFile;
    }

    /**
     * If {@link #isCreateFile} is true, an empty feature-pattern file will be created. <br>
     * The JobStatus record is updated to the waiting state.
     * @return True if successful
     */
    private boolean createTPFile() {
        if (isCreateFile) {
            if (!createDefaultTPFile()) {
                return false;
            }
        } else {
            tpFileId = Job.NO_APPILCABLE_FILE_ID;
        }

        if (!saveJobStatus()) {
            return false;
        }
        return true;
    }

    /**
     * Create a default feature-pattern file. Only the meta information is set here.
     * @return True if successful
     */
    private boolean createDefaultTPFile() {
        List<FileReferences> fileRefs = EditResourceUtil.INSTANCE.getReferencesByRefuuid(projectId, tpsVMFile.getUuid()).stream()
                .filter(ref -> Extension.getByCode(ref.getExtension()) == Extension.FP).collect(Collectors.toList());
        if (fileRefs.isEmpty()) {
            // FP Fileがない場合は空のファイルを作成する。ReferenceもここでAPI経由で設定する。
            tpVMFile = registDefaultTPVMFile(tpsVMFile, null);
            return true;
        } else {
            // FP Fileが既に存在している場合は、同一名か確認し、同一名の場合は"_数"をつける
            StringBuilder createFileName = new StringBuilder(tpsVMFile.getName());
            for (int i = 0; i < fileRefs.size(); i++) {
                Optional<FileReferences> opt = fileRefs.stream().filter(ref -> createFileName.toString().equals(ref.getFile().getName())).findFirst();
                if (!opt.isPresent()) {
                    break;
                }
                createFileName.setLength(0);
                createFileName.append(tpsVMFile.getName() + "_" + (i + 1));
            }
            tpVMFile = registDefaultTPVMFile(tpsVMFile, createFileName.toString());
        }

        return true;
    }

    /**
     * JOB for feature-pattern generation is added in the waiting state.
     * @return True if successful
     */
    private boolean addJob() {
        EditResourceUtil.INSTANCE.addJob(settingFileId, tpsFile, tpFileId, Job.TYPE_FP, projectId);
        return true;
    }

    /**
     * The JOBStatus record for feature-pattern generation is updated to the waiting state.
     * @return True if successful
     */
    private boolean saveJobStatus() {
        EditResourceUtil.INSTANCE.updateJobStatusByInputFileId(settingFileId, Job.STATUS_NOEXEC, "");
        return true;
    }
}
