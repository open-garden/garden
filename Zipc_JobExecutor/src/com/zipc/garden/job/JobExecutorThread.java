package com.zipc.garden.job;

import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * A class that manages job thread processing.
 */
public abstract class JobExecutorThread implements Runnable {

    /**
     * The system property of "GARDENDEBUG" is compared with "true".
     * @return If true, it matches "true". False if there is a discrepancy.
     */
    public static boolean enableDebug() {
        return "true".equals(System.getProperty("GARDENDEBUG"));
    }

    /** The ID of the project in which the job is running. */
    protected long projectId;

    /** Job output file ID. */
    protected long outputFileId; // JobTableの出力ファイルID

    /** Job input file ID. */
    protected long inputFileId;

    /** ID of the running job. */
    protected long jobId;

    /** Message on error. */
    protected String errorMessage;

    /** Error message when the contents of the setting file are changed during job execution. */
    private final String SETTING_FILE_UPDATE_ERROR = "Processing was interrupted because the contents of the setting file were changed.";

    /**
     * constructor.
     * @param jobId ID of the running job.
     * @param projectId The ID of the project in which the job is running.
     * @param outputFileId Job output file ID.
     */
    public JobExecutorThread(long jobId, long projectId, long outputFileId) {
        this.jobId = jobId;
        this.projectId = projectId;
        this.outputFileId = outputFileId;
    }

    /**
     * <pre>
     * If there is a process you want to execute when starting the thread, override this and implement it.
     * スレッド起動時に実施したい処理があればOverrideして実装する
     * </pre>
     */
    protected void before() {

    }

    /**
     * <pre>
     * The actual processing of the thread is executed, and the status of the job is updated according to the execution status.
     * 
     * {@inheritDoc}
     * </pre>
     */
    @Override
    public void run() {
        System.out.println(this + "#run()");
        before();
        try {
            inputFileId = getJobInputFileId();
            if (execute(jobId)) {
                if (!updateJobStatus(Job.STATUS_COMPLETE)) {
                    System.out.println(this + "#run() faild " + this.jobId);
                }
            } else {
                Job job = EditResourceUtil.INSTANCE.getJobInfo(this.jobId);
                if (job.getStatus() == Job.STATUS_CANCEL) {
                    updateJobStatus(Job.STATUS_CANCEL, "Job Canceled");
                } else if (!updateJobStatus(Job.STATUS_ERROR, errorMessage)) {
                    System.out.println(this + "#run() faild " + this.jobId);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            setErrorMessage("Unknown error has occured. Please check files");
            if (!updateJobStatus(Job.STATUS_ERROR, errorMessage)) {
                System.out.println(this + "#run() faild " + this.jobId);
            }
        }
        after();
        System.out.println(this + "#run() end");
    }

    /**
     * <pre>
     * Implement the actual processing of the thread.
     * スレッドの実処理を実装する
     * </pre>
     * 
     * @param jobId
     * @return boolean true/false
     */
    protected abstract boolean execute(long jobId);

    /**
     * <pre>
     * If there is a process you want to execute at the end of the thread, override this and implement it.
     * スレッド終了時に実施したい処理があればOverrideして実装する
     * </pre>
     */
    protected void after() {

    }

    /**
     * <pre>
     * Gets the input file ID for the JOB.
     * JOBのインプットファイルIDを取得
     * </pre>
     * 
     * @return input file ID
     */
    private long getJobInputFileId() {
        return EditResourceUtil.INSTANCE.getJobInputFileId(jobId);
    }

    /**
     * <pre>
     * Gets the record that matches {@link #jobId} from the job table.
     * JOBの全情報を取得
     * </pre>
     * 
     * @return Job Information
     */
    protected Job getJobInfo() {
        return EditResourceUtil.INSTANCE.getJobInfo(jobId);
    }

    /**
     * <pre>
     * Update the JOB status.
     * JOBのステータスを更新
     * </pre>
     * 
     * @param status Job status update contents
     * @return Update successful: True / No target data: False
     */
    protected boolean updateJobStatus(int status) {
        return EditResourceUtil.INSTANCE.updateJobStatus(jobId, status);
    }

    /**
     * Update the progress of the JOB.
     * @param message A message indicating the progress of the job.
     * @return Update successful: True / No target data: False
     */
    protected boolean updateJobProgress(String message) {
        return updateJobProgress(-1, message);
    }

    /**
     * Update job status and progress.
     * @param status Job status update contents. If -1, only the message is updated.
     * @param message Job message update contents
     * @return Update successful: True / No target data: False
     */
    protected boolean updateJobProgress(int status, String message) {
        EditResourceUtil.INSTANCE.updateJobStatusByInputFileId(getJobInputFileId(), status, message);
        return EditResourceUtil.INSTANCE.updateJobProgress(jobId, message);
    }

    /**
     * <pre>
     * Update the JOB status and message.
     * JOBのステータスをメッセージ付きで更新
     * </pre>
     * 
     * @param status Job status update contents
     * @param errorMessage Job message update contents
     * @return Update successful: True / No target data: False
     */
    protected boolean updateJobStatus(int status, String errorMessage) {
        return EditResourceUtil.INSTANCE.updateJobStatus(jobId, status, errorMessage);
    }

    /**
     * It is confirmed whether the job being executed has been cancelled.
     * @return True if the job has been cancelled. If not, False.
     */
    protected boolean requestedCancel() {
        Job job = EditResourceUtil.INSTANCE.getJobInfo(jobId);
        return job.getStatus() == Job.STATUS_CANCEL;
    }

    /**
     * <pre>
     * Compare the Hash value of File at the time of JOB execution with the Hash value of File at the present time.
     * JOB実行時のFileのHash値と、JOB実行中のFileのHash値を比較する
     * </pre>
     * 
     * @return True if the Hash values ​​are different. False if the same.
     */
    protected boolean checkHashChanged() {
        Job job = EditResourceUtil.INSTANCE.getJobInfo(jobId);
        File inputFile = job.getInputFile();
        File newFile = EditResourceUtil.INSTANCE.getFile(inputFileId);
        if (inputFile.getHash().equals(newFile.getHash())) {
            return false;
        } else {
            this.setErrorMessage(this.SETTING_FILE_UPDATE_ERROR);
            return true;
        }
    }

    /**
     * <pre>
     * Set the message when an error occurs. (It will be registered in the JobTable when an error is answered.)
     * エラー時のメッセージを設定(エラー応答時にJobTableに登録)
     * </pre>
     * 
     * @param message Error message
     */
    protected void setErrorMessage(String message) {
        errorMessage = message;
    }
}
