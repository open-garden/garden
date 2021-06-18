package com.zipc.garden.webplatform.server.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zipc.garden.model.bp.BPRoot;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.FileReferences;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.UserInfo;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * JOB that generates behavior-pattern is added to JOB table.<br>
 * Also, we will create a behavior-pattern file.
 */
public class GenerateBehaviorPatternAcceptor {

    /** ID of the project that created the BPS file */
    long projectId;

    /** behavior-pattern-setting File ID */
    long settingFileId;

    /** User information of logged in */
    UserInfo userInfo;

    /** behavior-pattern-setting file record */
    File bpsFile;

    /** behavior-pattern-setting file info */
    VMFile bpsVMFile;

    /** behavior-pattern file info */
    VMFile bpVMFile;

    /** EMF root model for behavior-pattern */
    BPRoot bpRoot;

    /** behavior-pattern File ID */
    long bpFileId;

    /** True if creating a behavior-pattern file, False otherwise */
    boolean isCreateFile;

    /**
     * behavior-pattern generation process is executed.<br>
     * Here, an empty behavior-pattern file is created and a job is registered.
     * @param settingFileId {@link #settingFileId}
     * @param userInfo {@link #userInfo}
     * @param isCreateFile {@link #isCreateFile}
     * @return The behavior-pattern file you created. If not created, null is returned.
     */
    public VMFile execute(long settingFileId, UserInfo userInfo, boolean isCreateFile) {
        System.out.println("settingFileId:" + settingFileId);
        this.settingFileId = settingFileId;
        this.userInfo = userInfo;
        this.isCreateFile = isCreateFile;
        this.bpsFile = EditResourceUtil.INSTANCE.getFile(settingFileId);
        this.bpsVMFile = EditResourceUtil.INSTANCE.getVMFile(settingFileId);
        this.projectId = EditResourceUtil.INSTANCE.getProjectId(bpsVMFile.getId());

        if (!createBPFile()) {
            return null;
        }

        // Jobが実行中の場合は実行しない
        if (!isJobExecuting()) {
            addJob();
        }

        return bpVMFile;
    }

    /**
     * Generate behavior-pattern Check if the JOB is already running.
     * @return If true, the JOB is running. If false, there are no jobs in progress.
     */
    private boolean isJobExecuting() {
        List<Job> jobList = EditResourceUtil.INSTANCE.getAllJobInfo().stream().filter(job -> job.getInputFileId() == settingFileId).filter(job -> job.getType() == Job.TYPE_BP)
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
     * Create a VMFile (initial state) of behavior-pattern that is generated based on the behavior-pattern-setting file.
     * @param bpsVMFile behavior-pattern-setting file info
     * @param createFileName The name of the behavior-pattern file to create. <br>
     *            If null, it will be created with the same name as the behavior-pattern-setting file name.
     * @return Created behavior-pattern file information
     */
    private VMFile registDefaultBPVMFile(VMFile bpsVMFile, String createFileName) {
        // set File
        String bpsFileName = createFileName == null ? bpsVMFile.getName() : createFileName;
        VMFile bpVMFile = new VMFile();
        bpVMFile.setName(bpsFileName);
        bpVMFile.setExtension(Extension.BP);

        // set FileReferences
        long dirId = EditResourceUtil.INSTANCE.getDirId(bpsVMFile.getId());
        List<Long> refList = new ArrayList<>();
        refList.add(bpsVMFile.getId());

        // create File and File References
        long fileId = EditResourceUtil.INSTANCE.createFile(dirId, bpVMFile, refList, userInfo);
        bpFileId = fileId;
        bpVMFile.setId(fileId);
        System.out.println("outputFileId:" + bpFileId);
        return bpVMFile;
    }

    /**
     * If {@link #isCreateFile} is true, an empty behavior-pattern file will be created. <br>
     * The JobStatus record is updated to the waiting state.
     * @return True if successful
     */
    private boolean createBPFile() {
        if (isCreateFile) {
            if (!createDefaultBPFile()) {
                return false;
            }
        } else {
            bpFileId = Job.NO_APPILCABLE_FILE_ID;
        }
        if (!saveJobStatus()) {
            return false;
        }
        return true;
    }

    /**
     * Create a default behavior-pattern file. Only the meta information is set here.
     * @return True if successful
     */
    private boolean createDefaultBPFile() {
        List<FileReferences> fileRefs = EditResourceUtil.INSTANCE.getReferencesByRefuuid(projectId, bpsVMFile.getUuid()).stream()
                .filter(ref -> Extension.getByCode(ref.getExtension()) == Extension.BP).collect(Collectors.toList());
        if (fileRefs.isEmpty()) {
            // BP Fileがない場合は空のファイルを作成する。ReferenceもここでAPI経由で設定する。
            bpVMFile = registDefaultBPVMFile(bpsVMFile, null);
            return true;
        } else {
            // BP Fileが既に存在している場合は、同一名か確認し、同一名の場合は"_数"をつける
            StringBuilder createFileName = new StringBuilder(bpsVMFile.getName());
            for (int i = 0; i < fileRefs.size(); i++) {
                Optional<FileReferences> opt = fileRefs.stream().filter(ref -> createFileName.toString().equals(ref.getFile().getName())).findFirst();
                if (!opt.isPresent()) {
                    break;
                }
                createFileName.setLength(0);
                createFileName.append(bpsVMFile.getName() + "_" + (i + 1));
            }
            bpVMFile = registDefaultBPVMFile(bpsVMFile, createFileName.toString());
        }
        return true;
    }

    /**
     * JOB for behavior-pattern generation is added in the waiting state.
     * @return True if successful
     */
    private boolean addJob() {
        EditResourceUtil.INSTANCE.addJob(settingFileId, bpsFile, bpFileId, Job.TYPE_BP, projectId);
        return true;
    }

    /**
     * The JOBStatus record for behavior-pattern generation is updated to the waiting state.
     * @return True if successful
     */
    private boolean saveJobStatus() {
        EditResourceUtil.INSTANCE.updateJobStatusByInputFileId(settingFileId, Job.STATUS_NOEXEC, "");
        return true;
    }
}
