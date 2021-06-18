package com.zipc.garden.webplatform.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Data Access Object Class for JobStatus Table
 */
@Entity
@Table
public class JobStatus implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = -3633755587215403339L;

    /** Unique ID of the JobStatus table */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /** The file that generated the job. Joined with file ID. */
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fileId")
    private File file;

    /** The current status of the job. */
    @Column
    private int status;

    /** The current information for the job. */
    @Column
    private String infomation;

    /** The hash value of the file when the job is executed. */
    @Column
    private String hash;

    /**
     * constructor
     */
    public JobStatus() {
    }

    /**
     * Gets the unique ID of the JobStatus table.
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set a unique ID for the JobStatus table.
     * @param id Unique ID of the JobStatus table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the file that generated the job.
     * @return {@link #file}
     */
    public File getFile() {
        return file;
    }

    /**
     * Set the file that generated the job.
     * @param file The file that generated the job.
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Gets the current status of the job.
     * @return {@link #status}
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the current status of the job.
     * @param status The current status of the job.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the current information for the job.
     * @return {@link #infomation}
     */
    public String getInfomation() {
        return infomation;
    }

    /**
     * Sets the current information for the job.
     * @param infomation The current information for the job.
     */
    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }

    /**
     * Gets the hash value of the file at the time the job was executed.
     * @return {@link #hash}
     */
    public String getHash() {
        return hash;
    }

    /**
     * Sets the hash value of the file when the job is run.
     * @param hash The hash value of the file when the job is executed.
     */
    public void setHash(String hash) {
        this.hash = hash;
    }
}
