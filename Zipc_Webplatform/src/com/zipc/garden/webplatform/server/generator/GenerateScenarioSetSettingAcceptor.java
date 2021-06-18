package com.zipc.garden.webplatform.server.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zipc.garden.model.scs.SCSRoot;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.FileReferences;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.UserInfo;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * JOB that generates Scenarioset is added to JOB table.<br>
 * Also, we will create a Scenarioset file.
 */
public class GenerateScenarioSetSettingAcceptor {

    /** ID of the project that created the SCSS file */
    long projectId;

    /** scenarioset-setting File ID */
    long settingFileId;

    /** User information of logged in */
    UserInfo userInfo;

    /** scenarioset-setting file record */
    File scssFile;

    /** scenarioset-setting file info */
    VMFile scssVMFile;

    /** scenarioset file info */
    VMFile scsVMFile;

    /** EMF root model for scenarioset */
    SCSRoot scsRoot;

    /** scenarioset File ID */
    long scsFileId;

    /** True if creating a scenarioset file, False otherwise */
    boolean isCreateFile;

    /**
     * scenarioset generation process is executed.<br>
     * Here, the scenarioset file is created and the JOB is registered.
     * @param settingFileId {@link #settingFileId}
     * @param userInfo {@link #userInfo}
     * @param isCreateFile {@link #isCreateFile}
     * @return The scenarioset file you created. If not created, null is returned.
     */
    public VMFile execute(long settingFileId, UserInfo userInfo, boolean isCreateFile) {
        System.out.println("settingFileId:" + settingFileId);
        this.settingFileId = settingFileId;
        this.userInfo = userInfo;
        this.isCreateFile = isCreateFile;
        this.scssFile = EditResourceUtil.INSTANCE.getFile(settingFileId);
        this.scssVMFile = EditResourceUtil.INSTANCE.getVMFile(settingFileId);
        this.projectId = EditResourceUtil.INSTANCE.getProjectId(scssVMFile.getId());

        if (!createSCSFile()) {
            return null;
        }

        // Jobが実行中の場合は実行しない
        if (!isJobExecuting()) {
            addJob();
        }

        return scsVMFile;
    }

    /**
     * Generate scenarioset Check if the JOB is already running.
     * @return If true, the JOB is running. If false, there are no jobs in progress.
     */
    private boolean isJobExecuting() {
        List<Job> jobList = EditResourceUtil.INSTANCE.getAllJobInfo().stream().filter(job -> job.getInputFileId() == settingFileId).filter(job -> job.getType() == Job.TYPE_SCS)
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
     * Create a VMFile (initial state) of scenarioset that is generated based on the scenarioset-setting file.
     * @param scssVMFile scenarioset-setting file info
     * @param createFileName The name of the scenarioset file to create. <br>
     *            If null, it will be created with the same name as the scenarioset-setting file name.
     * @return Created scenarioset file information
     */
    private VMFile registDefaultSCSVMFile(VMFile scssVMFile, String createFileName) {
        // set File
        String scssFileName = createFileName == null ? scssVMFile.getName() : createFileName;
        VMFile scsVMFile = new VMFile();
        scsVMFile.setName(scssFileName);
        scsVMFile.setExtension(Extension.SCS);

        // set FileReferences
        long dirId = EditResourceUtil.INSTANCE.getDirId(scssVMFile.getId());
        List<Long> refList = new ArrayList<>();
        refList.add(scssVMFile.getId());

        // create File and File References
        long fileId = EditResourceUtil.INSTANCE.createFile(dirId, scsVMFile, refList, userInfo);
        scsFileId = fileId;
        scsVMFile.setId(fileId);
        System.out.println("outputFileId:" + scsFileId);
        return scsVMFile;
    }

    /**
     * If {@link #isCreateFile} is true, an empty scenario set file will be created. <br>
     * The JobStatus record is updated to the waiting state.
     * @return True if successful
     */
    private boolean createSCSFile() {
        if (isCreateFile) {
            if (!createDefaultSCSFile()) {
                return false;
            }
        } else {
            scsFileId = Job.NO_APPILCABLE_FILE_ID;
        }
        if (!saveJobStatus()) {
            return false;
        }
        return true;
    }

    /**
     * Create a default scenarioset file. Only the meta information is set here.
     * @return True if successful
     */
    private boolean createDefaultSCSFile() {
        List<FileReferences> fileRefs = EditResourceUtil.INSTANCE.getReferencesByRefuuid(projectId, scssVMFile.getUuid());
        if (fileRefs.isEmpty()) {
            // SCS Fileがない場合は空のファイルを作成する。ReferenceもここでAPI経由で設定する。
            scsVMFile = registDefaultSCSVMFile(scssVMFile, null);
            return true;
        } else {
            // BP Fileが既に存在している場合は、同一名か確認し、同一名の場合は"_数"をつける
            StringBuilder createFileName = new StringBuilder(scssVMFile.getName());
            for (int i = 0; i < fileRefs.size(); i++) {
                Optional<FileReferences> opt = fileRefs.stream().filter(ref -> createFileName.toString().equals(ref.getFile().getName())).findFirst();
                if (!opt.isPresent()) {
                    break;
                }
                createFileName.setLength(0);
                createFileName.append(scssVMFile.getName() + "_" + (i + 1));
            }
            scsVMFile = registDefaultSCSVMFile(scssVMFile, createFileName.toString());
        }
        return true;
    }

    /**
     * JOB for scenarioset generation is added in the waiting state.
     * @return True if successful
     */
    private boolean addJob() {
        EditResourceUtil.INSTANCE.addJob(settingFileId, scssFile, scsFileId, Job.TYPE_SCS, projectId);
        return true;
    }

    /**
     * The JOBStatus record for scenarioset generation is updated to the waiting state.
     * @return True if successful
     */
    private boolean saveJobStatus() {
        EditResourceUtil.INSTANCE.updateJobStatusByInputFileId(settingFileId, Job.STATUS_NOEXEC, "");
        return true;
    }
}
