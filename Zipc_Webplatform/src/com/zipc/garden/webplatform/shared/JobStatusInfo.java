package com.zipc.garden.webplatform.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.zipc.garden.webplatform.dao.Job;

/**
 * Class that retains record information of JobStatus table
 */
public class JobStatusInfo implements IsSerializable {

    /** Message when JOB is No processing */
    private static final String NO_EXEC = "No processing";

    /** Message when JOB is in process */
    private static final String EXECUTING = "Processing";

    /** Message when JOB processing is completed */
    private static final String COMPLETE = "Complete";

    /** Message when an error occurred during JOB processing */
    private static final String ERROR = "Failure";

    /**
     * <pre>
     *  Job status number. There are the following types.
     *   {@link Job#STATUS_EXECUTING} 
     *   {@link Job#STATUS_COMPLETE}
     *   {@link Job#STATUS_ERROR}
     *   {@link Job#STATUS_CANCEL}
     *   {@link Job#STATUS_NOEXEC}
     * </pre>
     */
    private int status;

    /** Information such as job progress status */
    private String infomation;

    /** File hash value when the job is executed */
    private String hash;

    /**
     * constructor (data not set)
     */
    public JobStatusInfo() {
    }

    /**
     * constructor (set data)
     * @param status {@link #status}
     * @param info {@link #infomation}
     * @param hash {@link #hash}
     */
    public JobStatusInfo(int status, String info, String hash) {
        this.status = status;
        this.infomation = info;
        this.setHash(hash);
    }

    /**
     * <pre>
     * Messages related to JOB Status are retrieved.
     *   Messages related to {@link Job#STATUS_EXECUTING} are {@link #EXECUTING}
     *   Messages related to {@link Job#STATUS_COMPLETE} are {@link #COMPLETE}
     *   Messages related to {@link Job#STATUS_ERROR} are {@link #ERROR}
     *   Messages related to {@link Job#STATUS_CANCEL} are {@link #NO_EXEC}
     *   Messages related to {@link Job#STATUS_NOEXEC} are {@link #NO_EXEC}
     * </pre>
     * 
     * @return Messages related to JOB status
     */
    public String getStatus() {
        switch (status) {
        case Job.STATUS_EXECUTING:
            return EXECUTING;
        case Job.STATUS_COMPLETE:
            return COMPLETE;
        case Job.STATUS_ERROR:
            return ERROR;
        case Job.STATUS_CANCEL:
        case Job.STATUS_NOEXEC:
        default:
            return NO_EXEC;
        }
    }

    /**
     * Get the job status number.
     * @return {@link #status}
     */
    public int getStatusNum() {
        return status;
    }

    /**
     * Set the job status number.
     * @param status {@link #status}
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Get information such as job progress.
     * @return {@link #infomation}
     */
    public String getInfomation() {
        return infomation;
    }

    /**
     * Set information such as the job progress status.
     * @param infomation {@link #infomation}
     */
    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }

    /**
     * Gets the file hash value when the job was executed.
     * @return {@link #hash}
     */
    public String getHash() {
        return hash;
    }

    /**
     * Set the file hash value when the job is executed.
     * @param hash {@link #hash}
     */
    public void setHash(String hash) {
        this.hash = hash;
    }
}
