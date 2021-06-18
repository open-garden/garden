package com.zipc.garden.webplatform.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Data Access Object Class for Job Table
 */
@Entity
@Table
public class Job implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = 6625893486920308204L;

    /**
     * Value to be specified when the file to be put in inputFildId and outputFileId does not exist <br>
     * (such as not creating a file as a generation result) <br>
     * inputFildId, outputFileId に入れるべきファイルが存在しない場合に指定する値（生成結果としてファイルを作らないなど）
     */
    public static long NO_APPILCABLE_FILE_ID = -1L;

    /** Unique ID of the Job table */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /** The ID of the file that created the job. */
    @Column
    private long inputFileId;

    /** The file that created the job. */
    @Column
    private File inputFile;

    /** ID of the generated file */
    @Column
    private long outputFileId;

    /** The ID of the project that manages this job */
    @Column
    private long projectId;

    /**
     * <pre>
     * Job status.
     *  100 : Wait for execution
     *  102 : Processing
     *  200 : Complete
     *  420 : Method Failure
     *  499 : Client Cancel job
     * </pre>
     */
    @Column
    private int status;

    /** Job progress message. */
    @Column
    private String stepProgressMessage;

    /** The type of job to be executed. */
    @Column
    private int type; // 0:FP,1:BP,2:LSC,3:SCS,4:BPtoRDF

    /** Job execution result message */
    @Column
    private String message;

    // Change Code to HTTP Style
    // 4xx or 5xx for error handle event
    // 1xx for information response
    // 2xx for success event
    /** Job status (Client Cancel job) */
    public static final int STATUS_CANCEL = 499; // Client Cancel job

    /** Job status (Wait for execution) */
    public static final int STATUS_NOEXEC = 100;// Wait for execution

    /** Job status (Processing) */
    public static final int STATUS_EXECUTING = 102;// Processing

    /** Job status (Complete) */
    public static final int STATUS_COMPLETE = 200;// CompleteR

    /** Job status (Method Failure) */
    public static final int STATUS_ERROR = 420; // Method Failure

    /** Job type (feature pattern generation) */
    public static final int TYPE_FP = 0;

    /** Job type (behavior pattern generation) */
    public static final int TYPE_BP = 1;

    /** Job type (ScenarioSet generation) */
    public static final int TYPE_SCS = 3;

    /** Job type (Remove feature pattern) */
    public static final int TYPE_REMOVE_FPS = 5;

    /** Job type (Remove behavior pattern) */
    public static final int TYPE_REMOVE_BPS = 6;

    /** Job type (concrete scenario set generation) */
    public static final int TYPE_CSCS = 7;

    /** Job type (Remove ScenarioSet) */
    public static final int TYPE_REMOVE_SCSS = 8;

    /** Job type (Remove unreferenced RDF) */
    public static final int TYPE_REMOVE_RDF = 10;

    /**
     * constructor
     */
    public Job() {

    }

    /**
     * constructor <br>
     * Use this if the operation is for the specified Job ID.
     * @param id specified Job ID.
     */
    public Job(Long id) {
        this.id = id;
    }

    /**
     * Get the ID of the project that manages this job.
     * @return {@link #projectId}
     */
    public long getProjectId() {
        return projectId;
    }

    /**
     * Set the ID of the project that manages this job.
     * @param projectjId The ID of the project that manages this job
     */
    public void setProjectId(long projectjId) {
        this.projectId = projectjId;
    }

    /**
     * Gets the unique ID of the job table.
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set a unique ID for the job table.
     * @param id Unique ID of the Job table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the ID of the file that created the job.
     * @return {@link #inputFileId}
     */
    public long getInputFileId() {
        return inputFileId;
    }

    /**
     * Set the ID of the file that created the job.
     * @param inputFileId The ID of the file that created the job.
     */
    public void setInputFileId(long inputFileId) {
        this.inputFileId = inputFileId;
    }

    /**
     * Get the ID of the generated file.
     * @return {@link #outputFileId}
     */
    public long getOutputFileId() {
        return outputFileId;
    }

    /**
     * Set the ID of the generated file.
     * @param outputFileId ID of the generated file
     */
    public void setOutputFileId(long outputFileId) {
        this.outputFileId = outputFileId;
    }

    /**
     * Gets the status of the job.
     * @return {@link #status}
     */
    public int getStatus() {
        return status;
    }

    /**
     * Set the status of the Job.
     * @param status Job status.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the type of job to run.
     * @return {@link #type}
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the type of job to run.
     * @param type The type of job to be executed.
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Acquires the job execution result message.
     * @return {@link #message}
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the job execution result message.
     * @param message Job execution result message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Set the job progress message.
     * @param message Job progress message.
     */
    public void setStepProgressMessage(String message) {
        this.stepProgressMessage = message;
    }

    /**
     * Gets the job progress message.
     * @return {@link #stepProgressMessage}
     */
    public String getStepProgressMessage() {
        return stepProgressMessage;
    }

    /**
     * Set the file that created the job.
     * @param inputFile The file that created the job.
     */
    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * Get the file that created the job.
     * @return {@link #inputFile}
     */
    public File getInputFile() {
        return inputFile;
    }
}
