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

/**
 * Data Access Object Class for Directory Table
 */
@Entity
@Table
public class Directory implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = -5302539392640967718L;

    /** Unique ID of the Directory table */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /** Directory name */
    @Column
    private String name;

    /** Parent directory. Obtained based on the parent directory ID. */
    @OneToOne
    @JoinColumn(name = "parentDirId")
    private Directory directory;

    /** Full directory path */
    @Column
    private String fullpath;

    /** The ID of the project that manages this directory */
    @Column
    private Long projectid;

    /** Directory deletion flag. If True, it has been deleted. If False, it has not been deleted. */
    @Column
    private boolean deleteFlg;

    /**
     * constructor
     */
    public Directory() {

    }

    /**
     * constructor <br>
     * Use this if the operation is for the specified Directory ID.
     * @param id specified Directory ID.
     */
    public Directory(Long id) {
        this.id = id;
    }

    /**
     * Get the directory ID.
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the directory ID.
     * @param Directory ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the directory name.
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Set the directory name.
     * @param name Directory name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the parent directory.
     * @return {@link #directory}
     */
    public Directory getDirectory() {
        return directory;
    }

    /**
     * Set the parent directory.
     * @param directory Parent directory.
     */
    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    /**
     * Get the full path of the directory.
     * @return {@link #fullpath}
     */
    public String getFullpath() {
        return fullpath;
    }

    /**
     * Set the full path of the directory.
     * @param fullpath The full path of the directory.
     */
    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }

    /**
     * Get the project ID.
     * @return {@link #projectid}
     */
    public Long getProjectid() {
        return projectid;
    }

    /**
     * Set the project ID.
     * @param projectid Project ID.
     */
    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

    /**
     * Gets the directory deletion flag.
     * @return {@link #deleteFlg}
     */
    public boolean isDeleteFlg() {
        return deleteFlg;
    }

    /**
     * Set the directory deletion flag.
     * @param deleteFlg Directory delete flag.
     */
    public void setDeleteFlg(boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

}
