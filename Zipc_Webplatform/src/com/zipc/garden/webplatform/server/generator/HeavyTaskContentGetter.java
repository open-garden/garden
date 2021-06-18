package com.zipc.garden.webplatform.server.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zipc.garden.model.bp.BPFactory;
import com.zipc.garden.model.bp.BPRoot;
import com.zipc.garden.model.bp.BPSetting;
import com.zipc.garden.model.bps.BPSRoot;
import com.zipc.garden.model.cb.CBFile;
import com.zipc.garden.model.cb.CBRoot;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.model.core.SettingInterface;
import com.zipc.garden.model.cscs.CSCSRoot;
import com.zipc.garden.model.scs.SCSFactory;
import com.zipc.garden.model.scs.SCSRoot;
import com.zipc.garden.model.scs.SCSSetting;
import com.zipc.garden.model.tp.TPFactory;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tps.TPSRoot;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.dao.JobStatus;
import com.zipc.garden.webplatform.dao.rdf.RDFUtil;
import com.zipc.garden.webplatform.dao.rdf.ontology.BPO;
import com.zipc.garden.webplatform.dao.rdf.ontology.FPO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.server.dao.accessor.BehaviorPatternAccessor;
import com.zipc.garden.webplatform.server.dao.accessor.FeaturePatternAccessor;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.JobStatusInfo;
import com.zipc.garden.webplatform.shared.NodeUtil;
import com.zipc.garden.webplatform.shared.UserInfo;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * The process of acquiring heavy task data is managed.
 * @param <T>
 */
public class HeavyTaskContentGetter<T extends AbstractRootElement & SettingInterface> {

    /** ID of the project to which the specified file belongs */
    private long projectId;

    /** Binary data of specified file (EMF root model) */
    private byte[] fileContent; // 応答するファイルのバイナリデータ

    /** The one that converted {@link #fileContent} to EMF root model */
    private AbstractRootElement root;

    /** specified file Id */
    private long fileId;

    /** Stores the reference file ID of the specified file. */
    private List<Long> settingFileIds;

    /** Information of logged-in user */
    private UserInfo userInfo;

    /**
     * Based on the specified argument, get one of TPRoot, BPRoot, SCSRoot, CSCSRoot.
     * @param projectId {@link #projectId}
     * @param fileId {@link #fileId}
     * @param startRecordOffset Acquisition start position
     * @param recordCount Number of records to retrieve
     * @param userInfo {@link #userInfo}
     * @return Binary data of AbstractRootElement class
     */
    public byte[] getPartOfFileContent(long projectId, long fileId, long startRecordOffset, long recordCount, UserInfo userInfo) {
        this.projectId = projectId;
        this.fileId = fileId;
        this.userInfo = userInfo;
        this.fileContent = getFileContent(fileId);
        this.root = convertToRootElement(fileContent);
        if (root instanceof SettingInterface) {
            setSettings((T) root, projectId);
        }
        this.settingFileIds = root.getRefs().stream().map(ref -> EditResourceUtil.INSTANCE.getVMFile(ref.getRefid(), projectId, null).getId()).collect(Collectors.toList());

        if (root instanceof TPRoot) {
            TPRoot tpRoot = (TPRoot) root;
            return getTPRoot(tpRoot, startRecordOffset, recordCount);
        } else if (root instanceof BPRoot) {
            BPRoot bpRoot = (BPRoot) root;
            return getBPRoot(bpRoot, startRecordOffset, recordCount);
        } else if (root instanceof SCSRoot) {
            SCSRoot scsRoot = (SCSRoot) root;
            return getSCSRoot(scsRoot, startRecordOffset, recordCount);
        } else if (root instanceof CSCSRoot) {
            CSCSRoot cscsRoot = (CSCSRoot) root;
            return getCSCSRoot(cscsRoot, startRecordOffset, recordCount);
        } else {
            return null;
        }
    }

    /**
     * Gets a TPRoot or BPRoot based on the given arguments. Acquires one pattern.
     * @param projectId {@link #projectId}
     * @param fileId {@link #fileId}
     * @param patternId Specified pattern Id
     * @param userInfo {@link #userInfo}
     * @param generationHash Hash value of fps or bps file at the time of scs generation.
     * @return Binary data of AbstractRootElement class
     */
    public byte[] getPartOfFileContent(long projectId, long fileId, long patternId, UserInfo userInfo, String generationHash) {
        this.projectId = projectId;
        this.fileId = fileId;
        this.userInfo = userInfo;
        this.fileContent = getFileContent(fileId);
        this.root = convertToRootElement(fileContent);
        this.settingFileIds = root.getRefs().stream().map(ref -> EditResourceUtil.INSTANCE.getVMFile(ref.getRefid(), projectId, null).getId()).collect(Collectors.toList());
        AbstractRootElement returnRoot = null;
        Reference ref = COREFactory.eINSTANCE.createReference();
        ref.setRefid(root.getId());
        ref.setHash(generationHash);
        if (root instanceof TPSRoot) {
            List<File> files = EditResourceUtil.INSTANCE.getChildFile(root.getId(), projectId);
            Optional<File> optFile = files.stream().filter(file -> Extension.FP.equals(Extension.getByCode(file.getExtension()))).findFirst();
            if (optFile.isPresent()) {
                returnRoot = EditResourceUtil.INSTANCE.convertToRootElement(optFile.get().getContent());
            } else {
                returnRoot = TPFactory.eINSTANCE.createTPRoot();
                returnRoot.getRefs().add(ref);
            }

            setSettings((T) returnRoot, projectId);
            TPSetting setting = (TPSetting) ((SettingInterface) returnRoot).getSettings().get(0);
            getTpRootFromRDF(patternId, (TPRoot) returnRoot, setting);
        } else if (root instanceof BPSRoot) {
            returnRoot = BPFactory.eINSTANCE.createBPRoot();
            returnRoot.getRefs().add(ref);
            setSettings((T) returnRoot, projectId);
            BPSetting setting = (BPSetting) ((SettingInterface) returnRoot).getSettings().get(0);
            getBpRootFromRDF(patternId, (BPRoot) returnRoot, setting);
        } else {
            return null;
        }

        return convertToBinary(returnRoot);

    }

    /**
     * If the setting class of the specified root is not set, the AbstractSetting class will be added to the root for the number
     * of reference files.
     * @param root specified root (TPRoot or BPRoot or SCSRoot）
     * @param projectId project id
     */
    public void setSettings(T root, long projectId) {
        root.getRefs().forEach(ref -> {
            // setting存在しない場合は作成、ある場合は取得
            Optional<AbstractSetting> opt = root.getSettings().stream().filter(set -> ref.getRefid().equals(set.getUuid())).findFirst();
            AbstractSetting setting = null;
            if (opt.isPresent()) {
                setting = opt.get();
            } else {
                if (root instanceof TPRoot) {
                    setting = TPFactory.eINSTANCE.createTPSetting();
                } else if (root instanceof BPRoot) {
                    setting = BPFactory.eINSTANCE.createBPSetting();
                } else if (root instanceof SCSRoot) {
                    setting = SCSFactory.eINSTANCE.createSCSSetting();
                } else if (root instanceof CSCSRoot) {
                    // TODO:
                } else {
                    return;
                }
                setting.setUuid(ref.getRefid());
                setting.setSettingHash(ref.getHash());
            }
            // settingに依存ファイルが存在しない場合は作成、ある場合は取得
            AbstractRootElement settingRoot;
            if (setting.getAbstractRoot() != null) {
                settingRoot = setting.getAbstractRoot();
            } else {
                long settingFileId = getFileId(ref.getRefid(), projectId);
                byte[] settingFileContent = getFileContent(settingFileId);
                settingRoot = convertToRootElement(settingFileContent);
                setting.setAbstractRoot(settingRoot);
            }

            if (root instanceof TPRoot) {
                // headerがなければ作成
                if (((TPSetting) setting).getHeader().isEmpty()) {
                    NodeUtil.getInstance().headerUpdate((TPSRoot) settingRoot, (TPSetting) setting);
                }
                // patternElementがなければ作成
                if (((TPSetting) setting).getPatternElements().isEmpty()) {
                    NodeUtil.getInstance().patternElementUpdate((TPSRoot) settingRoot, (TPSetting) setting);
                }
            }

            root.getSettings().add(setting);
        });
    }

    /**
     * Get Setting information based on the specified file and get JobStatus based on the UUID and hash value of Setting
     * information.<br>
     * The hash value of the setting information will be the hash value when RDF was generated.
     * @param projectId {@link #projectId}
     * @param fileId {@link #fileId}
     * @param userInfo {@link #userInfo}
     * @return job status info
     */
    public List<JobStatusInfo> getJobStatusInfo(long projectId, long fileId, UserInfo userInfo) {
        this.projectId = projectId;
        this.fileId = fileId;
        this.userInfo = userInfo;
        this.fileContent = getFileContent(fileId);
        this.root = convertToRootElement(fileContent);
        List<File> settingFileList = new ArrayList<File>();
        if (root instanceof SettingInterface) {
            setSettings((T) root, projectId);
            // ファイルのhashではなく、settingのhashからjobStatusを取得するためにhashを一時的に変更する
            settingFileList = ((T) root).getSettings().stream().map(s -> {
                File file = EditResourceUtil.INSTANCE.getFile(s.getUuid(), projectId, null);
                file.setHash(s.getSettingHash());
                return file;
            }).collect(Collectors.toList());
        } else if (root instanceof TPSRoot || root instanceof BPSRoot || root instanceof CBRoot) {
            settingFileList.add(EditResourceUtil.INSTANCE.getFile(root.getId(), projectId));
        }
        int jobType = -1;
        if (root instanceof TPRoot || root instanceof TPSRoot) {
            jobType = Job.TYPE_FP;
        } else if (root instanceof BPRoot || root instanceof BPSRoot) {
            jobType = Job.TYPE_BP;
        } else if (root instanceof SCSRoot || root instanceof CBRoot) {
            jobType = Job.TYPE_SCS;
        }
        List<JobStatusInfo> results = new ArrayList<JobStatusInfo>();
        for (File settingFile : settingFileList) {
            JobStatus status = getJobStatus(settingFile.getId(), settingFile.getHash());
            if (status == null) {
                continue;
            }
            int fileStatus = status.getStatus();
            System.out.println("HeavyTaskContentGetter#getTpRoot fileStatus[" + fileStatus + "]");
            switch (fileStatus) {
            case Job.STATUS_NOEXEC: // 未実行
            case Job.STATUS_EXECUTING: // 処理中
                // Jobを見に行ってJobStatusに保存する
                Job jobInfo = getJobInfo(settingFile.getId(), jobType);
                int jobStatus = jobInfo.getStatus();
                System.out.println("HeavyTaskContentGetter#getTpRoot jobStatus[" + jobStatus + "]");
                switch (jobStatus) {
                case Job.STATUS_NOEXEC: // 未実行
                case Job.STATUS_EXECUTING: // 処理中
                    if (fileStatus != jobStatus) {
                        saveJobStatus(settingFile.getId(), jobStatus, "");
                    }
                    break;
                case Job.STATUS_COMPLETE: // 完了
                    removeJobRowByIputFileIdAndType(settingFile.getId(), jobType);
                    saveJobStatus(settingFile.getId(), jobStatus, "");
                    break;
                case Job.STATUS_ERROR: // エラー
                case Job.STATUS_CANCEL:// キャンセル
                    removeJobRowByIputFileIdAndType(settingFile.getId(), jobType);
                    saveJobStatus(settingFile.getId(), jobStatus, jobInfo.getMessage());
                }
                break;
            case Job.STATUS_COMPLETE:// 完了
            case Job.STATUS_CANCEL: // キャンセル
            case Job.STATUS_ERROR: // エラー
                break;
            }
            status = getJobStatus(settingFile.getId(), settingFile.getHash());
            if (status != null) {
                results.add(new JobStatusInfo(status.getStatus(), status.getInfomation(), status.getHash()));
            } else {
                results.add(new JobStatusInfo());
            }
        }
        return results;
    }

    /**
     * Check the TP data generation status, and if it is generated, TPRoot will be acquired from RDF.<br>
     * If not, only the generation status and message is updated.
     * @param tpRoot Setting destination of the acquired record
     * @param startRecordOffset Acquisition start position
     * @param recordCount Number of records to retrieve
     * @return Binary data of TPRoot class
     */
    private byte[] getTPRoot(TPRoot tpRoot, long startRecordOffset, long recordCount) {
        tpRoot.setBegin((int) startRecordOffset);
        long totalCount = 0;
        for (AbstractSetting setting : tpRoot.getSettings()) {
            long settingFileId = getFileId(setting.getUuid(), projectId);
            JobStatus status = getJobStatus(settingFileId, setting.getSettingHash());
            if (status == null) {
                continue;
            }
            int fileStatus = status.getStatus();
            System.out.println("HeavyTaskContentGetter#getTpRoot fileStatus[" + fileStatus + "]");
            switch (fileStatus) {
            case Job.STATUS_NOEXEC: // 未実行
            case Job.STATUS_EXECUTING: // 処理中
                // Jobを見に行って結果に合わせて返す
                Job jobInfo = getJobInfo(settingFileId, Job.TYPE_FP);
                int jobStatus = jobInfo.getStatus();
                System.out.println("HeavyTaskContentGetter#getTpRoot jobStatus[" + jobStatus + "]");
                switch (jobStatus) {
                case Job.STATUS_NOEXEC: // 未実行
                case Job.STATUS_EXECUTING: // 処理中
                    if (fileStatus != jobStatus) {
                        saveJobStatus(settingFileId, jobStatus, "");
                    }
                    break;
                case Job.STATUS_COMPLETE: // 完了
                    removeJobRowByIputFileIdAndType(settingFileId, Job.TYPE_FP);
                    saveJobStatus(settingFileId, jobStatus, "");
                    totalCount = getTpRootFromRDF(startRecordOffset, recordCount, tpRoot, (TPSetting) setting); // 要求通りデータを詰める
                    break;
                case Job.STATUS_ERROR: // エラー
                case Job.STATUS_CANCEL:// Cancel,do same as error
                    // RDF変換処理のジョブを削除する
                    removeJobRowByIputFileIdAndType(settingFileId, Job.TYPE_FP);
                    saveJobStatus(settingFileId, jobStatus, jobInfo.getMessage());
                }
                break;
            case Job.STATUS_COMPLETE:// 完了
            case Job.STATUS_CANCEL:
                totalCount = getTpRootFromRDF(startRecordOffset, recordCount, tpRoot, (TPSetting) setting); // 要求通りデータを詰める
                break;
            case Job.STATUS_ERROR: // エラー
                break;
            }
            // 1ファイルでとれた件数をカウントから除外する（次のファイルの取得サイズにする）
            recordCount -= setting.getCount();

            // 取得できた場合は、次のファイルのスタートが0からということ
            if (setting.getCount() != 0) {
                startRecordOffset = 0;
            } else {
                // 取得できないということは、スタートの件数から取得したファイル数分のパターンを引いた数が次のスタートの開始位置になる
                startRecordOffset = startRecordOffset - totalCount;
            }
        }
        return convertToBinary(tpRoot);
    }

    /**
     * Check the BP data generation status, and if it is generated, BPRoot will be acquired from RDF.<br>
     * If not, only the generation status and message is updated.
     * @param bpRoot Setting destination of the acquired record
     * @param startRecordOffset Acquisition start position
     * @param recordCount Number of records to retrieve
     * @return Binary data of BPRoot class
     */
    private byte[] getBPRoot(BPRoot bpRoot, long startRecordOffset, long recordCount) {
        bpRoot.setBegin((int) startRecordOffset);
        long totalCount = 0;
        for (AbstractSetting setting : bpRoot.getSettings()) {
            long settingFileId = getFileId(setting.getUuid(), projectId);
            JobStatus status = getJobStatus(settingFileId, setting.getSettingHash());
            if (status == null) {
                continue;
            }
            int fileStatus = status.getStatus();
            System.out.println("HeavyTaskContentGetter#getBpRoot fileStatus[" + fileStatus + "]");
            switch (fileStatus) {
            case Job.STATUS_NOEXEC: // 未実行
            case Job.STATUS_EXECUTING: // 処理中
                // Jobを見に行って結果に合わせて返す
                Job jobInfo = getJobInfo(settingFileId, Job.TYPE_BP);
                int jobStatus = jobInfo.getStatus();
                System.out.println("HeavyTaskContentGetter#getBpRoot jobStatus[" + jobStatus + "]");
                switch (jobStatus) {
                case Job.STATUS_NOEXEC: // 未実行
                case Job.STATUS_EXECUTING: // 処理中
                    if (fileStatus != jobStatus) {
                        saveJobStatus(settingFileId, jobStatus, "");
                    }
                    break;
                case Job.STATUS_COMPLETE: // 完了
                    // RDF変換処理のジョブを削除する
                    removeJobRowByIputFileIdAndType(settingFileId, Job.TYPE_BP);
                    saveJobStatus(settingFileId, jobStatus, "");
                    totalCount = getBpRootFromRDF(startRecordOffset, recordCount, bpRoot, (BPSetting) setting); // 要求通りデータを詰める
                    break;
                case Job.STATUS_ERROR: // エラー
                case Job.STATUS_CANCEL:// Cancel,do same as error
                    // RDF変換処理のジョブを削除する
                    removeJobRowByIputFileIdAndType(settingFileId, Job.TYPE_BP);
                    saveJobStatus(settingFileId, jobStatus, jobInfo.getMessage());
                }
                break;
            case Job.STATUS_COMPLETE: // 完了
            case Job.STATUS_CANCEL:
                totalCount = getBpRootFromRDF(startRecordOffset, recordCount, bpRoot, (BPSetting) setting); // 要求通りデータを詰める
                break;
            case Job.STATUS_ERROR: // エラー
                break;
            }
            // 1ファイルでとれた件数をカウントから除外する（次のファイルの取得サイズにする）
            recordCount -= setting.getCount();
            // 取得できた場合は、次のファイルのスタートが0からということ
            if (setting.getCount() != 0) {
                startRecordOffset = 0;
            } else {
                // 取得できないということは、スタートの件数から取得したファイル数分のパターンを引いた数が次のスタートの開始位置になる
                startRecordOffset = startRecordOffset - totalCount;
            }
        }
        return convertToBinary(bpRoot);
    }

    /**
     * Check the SCS data generation status, and if it is generated, SCSRoot will be acquired from RDF.<br>
     * If not, only the generation status and message is updated.
     * @param scsRoot Setting destination of the acquired record
     * @param startRecordOffset Acquisition start position
     * @param recordCount Number of records to retrieve
     * @return Binary data of SCSRoot class
     */
    private byte[] getSCSRoot(SCSRoot scsRoot, long startRecordOffset, long recordCount) {

        long totalCount = 0;
        scsRoot.setBegin((int) startRecordOffset);
        for (AbstractSetting setting : scsRoot.getSettings()) {
            long settingFileId = getFileId(setting.getUuid(), projectId);
            // BP、FPファイルが生成されているか調べる
            byte[] scssBin = EditResourceUtil.INSTANCE.getFileContent(settingFileId);
            CBRoot scssRoot = (CBRoot) EditResourceUtil.INSTANCE.convertToRootElement(scssBin);
            for (CBFile cbFile : scssRoot.getLogic().getFile()) {
                if (!isGenerated(cbFile.getUuid(), cbFile.getHash())) {
                    removeJobRowByIputFileIdAndType(settingFileId, Job.TYPE_SCS);
                    File file = EditResourceUtil.INSTANCE.getFile(cbFile.getUuid(), projectId);
                    saveJobStatus(settingFileId, Job.STATUS_ERROR, file.getName() + "." + file.getExtension() + " is not generated.");
                    return convertToBinary(scsRoot);
                }
            }

            JobStatus status = getJobStatus(settingFileId, setting.getSettingHash());
            if (status == null) {
                continue;
            }
            int fileStatus = status.getStatus();
            System.out.println("HeavyTaskContentGetter#getSCSRoot fileStatus[" + fileStatus + "]");
            switch (fileStatus) {
            case Job.STATUS_NOEXEC: // 未実行
            case Job.STATUS_EXECUTING: // 処理中
                // Jobを見に行って結果に合わせて返す
                Job jobInfo = getJobInfo(settingFileId, Job.TYPE_SCS);
                int jobStatus = jobInfo.getStatus();
                System.out.println("HeavyTaskContentGetter#getSCSRoot jobStatus[" + jobStatus + "]");
                switch (jobStatus) {
                case Job.STATUS_NOEXEC: // 未実行
                case Job.STATUS_EXECUTING: // 処理中
                    if (fileStatus != jobStatus) {
                        saveJobStatus(settingFileId, jobStatus, "");
                    }
                    break;
                case Job.STATUS_COMPLETE: // 完了
                    removeJobRowByIputFileIdAndType(settingFileId, Job.TYPE_SCS);
                    saveJobStatus(settingFileId, jobStatus, "");
                    getScsRootFromRDF(startRecordOffset, recordCount, (SCSSetting) setting); // 要求通りデータを詰める
                    break;
                case Job.STATUS_ERROR: // エラー
                case Job.STATUS_CANCEL:// Cancel,do same as error
                    removeJobRowByIputFileIdAndType(settingFileId, Job.TYPE_SCS);
                    saveJobStatus(settingFileId, jobStatus, jobInfo.getMessage());
                    break;
                }
                break;
            case Job.STATUS_COMPLETE: // 完了
            case Job.STATUS_CANCEL:
                getScsRootFromRDF(startRecordOffset, recordCount, (SCSSetting) setting); // 要求通りデータを詰める
                break;
            case Job.STATUS_ERROR: // エラー
                break;
            }
            // ※FPとBPとSPQRQLの思想が違うため、以下のファイルの回し方（カウント方法）が異なる
            totalCount = setting.getEnd() - setting.getBegin();
            // 1ファイルでとれた件数をカウントから除外する（次のファイルの取得サイズにする）
            recordCount -= totalCount;
            // 取得できた場合、もしくはカウントにぴったり取得した場合、次のファイルのスタートが0からということ
            if (totalCount != 0 || recordCount == 0) {
                startRecordOffset = 0;
            } else {
                // 取得できないということは、スタートの件数から取得したファイル数分のパターンを引いた数が次のスタートの開始位置になる
                startRecordOffset = startRecordOffset - setting.getCount();
            }
            scsRoot.setAll(scsRoot.getAll() + setting.getCount());
        }
        scsRoot.setEnd((int) (scsRoot.getBegin() + scsRoot.getAll()));

        return convertToBinary(scsRoot);
    }

    /**
     * Check the CSCS data generation status, and if it is generated, CSCSRoot will be acquired from RDB.<br>
     * If not, only the generation status and message is updated.
     * @param cscsRoot Setting destination of the acquired record
     * @param startRecordOffset Acquisition start position
     * @param recordCount Number of records to retrieve
     * @return Binary data of CSCSRoot class
     */
    private byte[] getCSCSRoot(CSCSRoot cscsRoot, long startRecordOffset, long recordCount) {
        for (long settingFileId : settingFileIds) {
            int fileStatus = cscsRoot.getStatus();
            System.out.println("HeavyTaskContentGetter#getCSCSRoot fileStatus[" + fileStatus + "]");
            switch (fileStatus) {
            case Job.STATUS_NOEXEC: // 未実行
            case Job.STATUS_EXECUTING: // 処理中
                // Jobを見に行って結果に合わせて返す
                Job jobInfo = getJobInfo(settingFileId, Job.TYPE_CSCS);
                int jobStatus = jobInfo.getStatus();
                System.out.println("HeavyTaskContentGetter#getCSCSRoot jobStatus[" + jobStatus + "]");
                switch (jobStatus) {
                case Job.STATUS_NOEXEC: // 未実行
                case Job.STATUS_EXECUTING: // 処理中
                    if (fileStatus != jobStatus) {
                        cscsRoot.setStatus(jobStatus);
                        saveFile(fileId, cscsRoot);
                    }
                    break;
                case Job.STATUS_COMPLETE: // 完了
                    removeJobRowByIputFileIdAndType(settingFileId, Job.TYPE_CSCS);
                    cscsRoot.setStatus(jobStatus);
                    saveFile(fileId, cscsRoot);
                    getCscsRootFromRDB(fileId, startRecordOffset, recordCount, cscsRoot); // 要求通りデータを詰める
                    break;
                case Job.STATUS_ERROR: // エラー
                    removeJobRowByIputFileIdAndType(settingFileId, Job.TYPE_CSCS);
                    cscsRoot.setStatus(jobStatus);
                    cscsRoot.setMessage(jobInfo.getMessage());
                    saveFile(fileId, cscsRoot);
                    break;
                case Job.STATUS_CANCEL:// Cancel,do same as error
                    removeJobRowByIputFileIdAndType(settingFileId, Job.TYPE_CSCS);
                    cscsRoot.setStatus(jobStatus);
                    cscsRoot.setMessage(jobInfo.getMessage());
                    saveFile(fileId, cscsRoot);
                }
                break;
            case Job.STATUS_COMPLETE: // 完了
            case Job.STATUS_CANCEL:
                getCscsRootFromRDB(fileId, startRecordOffset, recordCount, cscsRoot); // 要求通りデータを詰める
                break;
            case Job.STATUS_ERROR: // エラー
                break;
            }
        }
        return convertToBinary(cscsRoot);
    }

    /**
     * Searches the file table based on the specified file UUID and project ID and gets the file ID.<br>
     * If the record cannot be retrieved, the ID of the deleted file will be retrieved.
     * @param uuid specified file UUID
     * @param projectId specified project Id
     * @return file id
     */
    private static long getFileId(String uuid, long projectId) {
        VMFile result = EditResourceUtil.INSTANCE.getVMFile(uuid, projectId, false);
        if (result == null) {
            result = EditResourceUtil.INSTANCE.getVMFile(uuid, projectId, true);
        }
        return result.getId();
    }

    /**
     * Searches the file table based on the specified file ID to get the "content" data.
     * @param fileId specified file Id
     * @return file's content.
     */
    private static byte[] getFileContent(long fileId) {
        return EditResourceUtil.INSTANCE.getFileContent(fileId);
    }

    /**
     * Convert the binary to the root class of the EMF model.
     * @param data Binary root class
     * @return EMF model root class
     */
    private AbstractRootElement convertToRootElement(byte[] data) {
        return EditResourceUtil.INSTANCE.convertToRootElement(data);
    }

    /**
     * Convert the root class of the EMF model to binary.
     * @param root EMF model root class
     * @return Root class binary
     */
    private byte[] convertToBinary(AbstractRootElement root) {
        return EditResourceUtil.INSTANCE.convertToBinary(root);
    }

    /**
     * Get the record that matches the specified file ID and hash value from the JobStatus table.
     * @param fileId specified file ID
     * @param hash specified hash value
     * @return Record of JobStatus table
     */
    private static JobStatus getJobStatus(long fileId, String hash) {
        return EditResourceUtil.INSTANCE.getJobStatusByInputFileId(fileId, hash);
    }

    /**
     * Gets the record that matches the specified fileID and job type from the Job table.
     * @param fileId specified File Id
     * @param jobType specified job Type
     * @return Record of Job table.
     */
    private static Job getJobInfo(long fileId, int jobType) {
        return EditResourceUtil.INSTANCE.getJobInfoByInputFileIdAndType(fileId, jobType);
    }

    /**
     * Delete the records in the JOB table that match the specified input file ID and Job type
     * @param fileId ID of the file that created the job
     * @param type Type of Job to be executed. See the constants for the {@link Job} class.
     */
    private static void removeJobRowByIputFileIdAndType(long fileId, int type) {
        EditResourceUtil.INSTANCE.removeJobByInputFileIdAndType(fileId, type);
    }

    /**
     * The argument "data" is reflected in the "Content" of the record of the file table that matches the specified file ID.<br>
     * Also, the file history table will be updated.
     * @param fileId specified file ID
     * @param data EMF root model
     */
    private void saveFile(long fileId, AbstractRootElement root) {
        byte[] data = convertToBinary(root);
        saveFile(fileId, data);
    }

    /**
     * The argument "data" is reflected in the "Content" of the record of the file table that matches the specified file ID.<br>
     * Also, the file history table will be updated.
     * @param fileId specified file ID
     * @param data EMF root model binary information
     */
    private void saveFile(long fileId, byte[] data) {
        EditResourceUtil.INSTANCE.saveFile(fileId, data, userInfo);
    }

    /**
     * Updates the status and information of the record in the job status table that matches the specified file ID.
     * @param settingFileId specified file ID.
     * @param status Job status update contents
     * @param infomation Job information update contents
     */
    private static void saveJobStatus(long settingFileId, int status, String infomation) {
        EditResourceUtil.INSTANCE.updateJobStatusByInputFileId(settingFileId, status, infomation);
    }

    /**
     * Get the data from RDF and set it in the argument "setting".
     * @param startRecordOffset Acquisition start position
     * @param recordCount Number of records to retrieve
     * @param tpRoot Setting destination of the acquired record
     * @param setting TPSetting class managed in the argument "tpRoot"
     * @return Total number of created operation patterns in one file
     */
    private long getTpRootFromRDF(long startRecordOffset, long recordCount, TPRoot tpRoot, TPSetting setting) {
        String refUuid = setting.getUuid();
        VMFile refVMFile = EditResourceUtil.INSTANCE.getVMFile(refUuid, projectId, false);
        long refId = refVMFile.getId();
        File refFile = EditResourceUtil.INSTANCE.getFile(refId, refVMFile.getDeleteFlg());
        return FeaturePatternAccessor.getFeaturePattern(refFile.getProjectid(), refFile.getId(), setting.getSettingHash(), startRecordOffset, recordCount, tpRoot, setting);
    }

    /**
     * Get the data from RDF and set it in the argument "setting".
     * @param patternId Specified pattern Id
     * @param tpRoot Setting destination of the acquired record
     * @param setting TPSetting class managed in the argument "tpRoot"
     */
    private void getTpRootFromRDF(long patternId, TPRoot tpRoot, TPSetting setting) {
        String refUuid = tpRoot.getRefs().get(0).getRefid();
        long refId = EditResourceUtil.INSTANCE.getVMFile(refUuid, projectId).getId();
        File refFile = EditResourceUtil.INSTANCE.getFile(refId);
        FeaturePatternAccessor.getFeaturePattern(refFile.getProjectid(), refFile.getId(), setting.getSettingHash(), patternId, tpRoot, setting);
    }

    /**
     * Get the data from RDF and set it in the argument "setting".
     * @param startRecordOffset Acquisition start position
     * @param recordCount Number of records to retrieve
     * @param bpRoot Setting destination of the acquired record
     * @param setting BPSetting class managed in the argument "bpRoot"
     * @return Total number of created operation patterns in one file
     */
    private long getBpRootFromRDF(long startRecordOffset, long recordCount, BPRoot bpRoot, BPSetting setting) {
        String refUuid = setting.getUuid();
        long refId = EditResourceUtil.INSTANCE.getVMFile(refUuid, projectId, false).getId();
        File refFile = EditResourceUtil.INSTANCE.getFile(refId);
        return BehaviorPatternAccessor.getBehaviorPattern(refFile.getProjectid(), refFile.getId(), setting.getSettingHash(), startRecordOffset, recordCount, bpRoot, setting);
    }

    /**
     * Get the data from RDF and set it in the argument "setting".
     * @param patternId Specified pattern Id
     * @param bpRoot Setting destination of the acquired record
     * @param setting BPSetting class managed in the argument "bpRoot"
     */
    private void getBpRootFromRDF(long patternId, BPRoot bpRoot, BPSetting setting) {
        String refUuid = bpRoot.getRefs().get(0).getRefid();
        long refId = EditResourceUtil.INSTANCE.getVMFile(refUuid, projectId).getId();
        File refFile = EditResourceUtil.INSTANCE.getFile(refId);
        BehaviorPatternAccessor.getBehaviorPattern(refFile.getProjectid(), refFile.getId(), setting.getSettingHash(), patternId, bpRoot, setting);
    }

    /**
     * Get the data from RDF and set it in the argument "scsSetting".
     * @param startRecordOffset Acquisition start position
     * @param recordCount Number of records to retrieve
     * @param scsSetting Setting destination of the acquired record
     */
    private void getScsRootFromRDF(long startRecordOffset, long recordCount, SCSSetting scsSetting) {
        EditResourceUtil.INSTANCE.getSCSFileByRecord(projectId, startRecordOffset, recordCount, scsSetting);
    }

    /**
     * Get the data from RDB and set it in the argument "cscsRoot".
     * @param fileId cscs file id
     * @param startRecordOffset Acquisition start position
     * @param recordCount Number of records to retrieve
     * @param cscsRoot Setting destination of the acquired record
     */
    private void getCscsRootFromRDB(long fileId, long startRecordOffset, long recordCount, CSCSRoot cscsRoot) {
        EditResourceUtil.INSTANCE.getCSCSFileByRecord(projectId, fileId, startRecordOffset, recordCount, cscsRoot);
    }

    /**
     * The RDF registration status and job status are checked.
     * @param settingFileUuid UUID of setting file (FPS or BPS)
     * @param settingFileHash Hash of setting file (FPS or BPS)
     * @return If True, generation is complete.
     */
    private boolean isGenerated(String settingFileUuid, String settingFileHash) {
        VMFile settingFile = EditResourceUtil.INSTANCE.getVMFile(settingFileUuid, projectId);

        String uri = GDNNS.UD.NS + settingFile.getId() + RDFUtil.SEPARATOR + settingFileHash + RDFUtil.SEPARATOR;
        switch (settingFile.getExtension()) {
        case BPS:
            uri += BPO.BP_NAME;
            break;
        case FPS:
            uri += FPO.FP_NAME;
            break;
        default:
            return false;
        }

        if (!RDFUtil.isResourceExists(uri)) {
            // RDF未登録なら未生成応答
            return false;
        }
        JobStatus status = EditResourceUtil.INSTANCE.getJobStatusByInputFileId(settingFile.getId(), settingFileHash);
        if (Job.STATUS_COMPLETE == status.getStatus()) {
            return true;
        } else {
            return false;
        }
    }
}
