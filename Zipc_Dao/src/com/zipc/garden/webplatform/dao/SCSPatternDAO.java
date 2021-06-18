package com.zipc.garden.webplatform.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Data Access Object Class for SCSPatternDAO Table
 */
@Entity
@Table(indexes = @Index(name = "scspattern_delete_index", columnList = "fileUuid,projectId"))
public class SCSPatternDAO implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = 4379438627187159390L;

    /** Unique ID of the SCSPatternDAO table */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /** ID of the project that generated the SCS */
    @Column
    private Long projectId;

    /** UUID of the scss file. */
    @Column
    private String fileUuid;

    /** SCS pattern ID. It is related to the data of TDB2. */
    @Column
    private Long SCSPatternId;

    /** Contents of the logical scenario. */
    @Column(columnDefinition = "TEXT")
    private String lsc;

    /** UUID of the cscs file. */
    @Column
    private String cscUuid;

    /** The name of the cscs file. */
    @Column
    private String cscFileName;

    /**
     * constructor
     */
    public SCSPatternDAO() {

    }

    /**
     * Get a unique ID.
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set a unique ID.
     * @param id Unique ID of the SCSPatternDAO table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the project ID.
     * @return {@link #projectId}
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * Set the project ID.
     * @param projectId ID of the project that generated the SCS
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * Get the UUID of the scss file.
     * @return {@link #fileUuid}
     */
    public String getFileUuid() {
        return fileUuid;
    }

    /**
     * Set the UUID of the scss file.
     * @param fileUuid UUID of the scss file.
     */
    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    /**
     * Gets the SCS pattern ID.
     * @return {@link #SCSPatternId}
     */
    public Long getSCSPatternId() {
        return SCSPatternId;
    }

    /**
     * Set the SCS pattern ID.
     * @param scsPatternId SCS pattern ID.
     */
    public void setSCSPatternId(Long scsPatternId) {
        SCSPatternId = scsPatternId;
    }

    /**
     * Gets the contents of the logical scenario.
     * @return {@link #lsc}
     */
    public String getLsc() {
        return lsc;
    }

    /**
     * Set the contents of the logical scenario.
     * @param lsc logical scenario
     */
    public void setLsc(String lsc) {
        this.lsc = lsc;
    }

    /**
     * Gets the UUID of the cscs file.
     * @return {@link #cscUuid}
     */
    public String getCscUuid() {
        return cscUuid;
    }

    /**
     * Set the UUID of the cscs file.
     * @param cscUuid UUID of the cscs file.
     */
    public void setCscUuid(String cscUuid) {
        this.cscUuid = cscUuid;
    }

    /**
     * Gets the name of the cscs file.
     * @return {@link #cscFileName}
     */
    public String getCscFileName() {
        return cscFileName;
    }

    /**
     * Set the name of the cscs file.
     * @param cscFileName The name of the cscs file.
     */
    public void setCscFileName(String cscFileName) {
        this.cscFileName = cscFileName;
    }

}
