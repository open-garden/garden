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
 * Data Access Object Class for CSCSPatternDAO Table
 */
@Entity
@Table(indexes = @Index(name = "cscspattern_delete_index", columnList = "fileUuid,projectId"))
public class CSCSPatternDAO implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = 4379438627187159390L;

    /** Unique ID of the CSCSPatternDAO table */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /** ID of the project that manages the cscs file */
    @Column
    private Long projectId;

    /** UUID of the cscs file */
    @Column
    private String fileUuid;

    /** Pattern ID of the concrete scenario set. */
    @Column
    private Long CSCSPatternId;

    /** The pattern ID of the Scenario set. It is associated with the scspatternid in the SCSPatternDAO table. */
    @Column
    private Long SCSPatternId;

    /** Pattern name for concrete scenario */
    @Column
    private String pattern;

    /** A textual representation of a concrete scenario */
    @Column(columnDefinition = "TEXT")
    private String csc;

    /**
     * constructor
     */
    public CSCSPatternDAO() {

    }

    /**
     * Gets the unique ID of the CSCSPatternDAO table.
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set a unique ID in the CSCSPatternDAO table.
     * @param id unique ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the ID of the project that manages the cscs file.
     * @return {@link #projectId}
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * Set the ID of the project that manages the cscs file.
     * @param projectId project id
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets the UUID of the cscs file.
     * @return {@link #fileUuid}
     */
    public String getFileUuid() {
        return fileUuid;
    }

    /**
     * Set the UUID of the cscs file.
     * @param fileUuid UUID of the cscs file
     */
    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    /**
     * Gets the pattern ID for the concrete scenario set.
     * @return {@link #CSCSPatternId}
     */
    public Long getCSCSPatternId() {
        return CSCSPatternId;
    }

    /**
     * Sets the pattern ID for the concrete scenario set.
     * @param cscsPatternId Pattern ID of the concrete scenario set.
     */
    public void setCSCSPatternId(Long cscsPatternId) {
        CSCSPatternId = cscsPatternId;
    }

    /**
     * Gets the pattern ID of the scenario set.
     * @return {@link #SCSPatternId}
     */
    public Long getSCSPatternId() {
        return SCSPatternId;
    }

    /**
     * Set the pattern ID of the scenario set.
     * @param scsPatternId Scenario set pattern ID
     */
    public void setSCSPatternId(Long scsPatternId) {
        SCSPatternId = scsPatternId;
    }

    /**
     * Gets the textual representation of a concrete scenario.
     * @return {@link #csc}
     */
    public String getCsc() {
        return csc;
    }

    /**
     * Sets the textual representation of the concrete scenario.
     * @param csc A textual representation of a concrete scenario.
     */
    public void setCsc(String csc) {
        this.csc = csc;
    }

    /**
     * Gets the pattern name for the concrete scenario.
     * @return {@link #pattern}
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Set the pattern name for the concrete scenario.
     * @param pattern pattern name
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
