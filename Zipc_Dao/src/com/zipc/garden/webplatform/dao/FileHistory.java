package com.zipc.garden.webplatform.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Data Access Object Class for FileHistory Table
 */
@Entity
@Table
public class FileHistory implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = 888484027929487000L;

    /** Unique ID of the FileHistory table */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /** A file in which the change history is registered. Associated with the file table. */
    @OneToOne
    @JoinColumn(name = "fileId")
    private File file;

    /** file name */
    @Column
    private String name;

    /** File extension */
    @Column
    private String extension;

    /** Full path of the file */
    @Column
    private String fullPath;

    /** File contents */
    @Column
    private byte[] content;

    /** ID of the user who updated the file */
    @Column
    private Long updateUser;

    /** File updated date and time. */
    @Column
    private Timestamp updateTime;

    /** File hash value */
    @Column
    private String hash;

    /** ID of the project that manages the file */
    @Column
    private Long projectid;

    /**
     * Gets the unique ID of the file history table.
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set a unique ID for the file history table.
     * @param id Unique ID of the file history table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Acquires the information of the file in which the change history is registered.
     * @return {@link #file}
     */
    public File getFile() {
        return file;
    }

    /**
     * Set the information of the file in which the change history is registered.
     * @param file file info
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Get the file name.
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Set the file name.
     * @param name file name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the file extension.
     * @return {@link #extension}
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Set the file extension.
     * @param extension file extension.
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Get the full path of the file.
     * @return {@link #fullPath}
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * Set the full path of the file.
     * @param fullPath Full path of the file
     */
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    /**
     * Gets the contents of the file.
     * @return {@link #content}
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * Set the contents of the file.
     * @param content EMF model binary data or text binary data
     */
    public void setContent(byte[] content) {
        this.content = content;
    }

    /**
     * Gets the ID of the user who last updated the file.
     * @return {@link #updateUser}
     */
    public Long getUpdateUser() {
        return updateUser;
    }

    /**
     * Set the ID of the user who last updated the file.
     * @param updateUser ID of the user who last updated the file
     */
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * Gets the modification date and time of the file.
     * @return {@link #updateTime}
     */
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    /**
     * Set the modification date and time of the file.
     * @param updateTime File update date and time
     */
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Gets the hash value of the file.
     * @return {@link #hash}
     */
    public String getHash() {
        return hash;
    }

    /**
     * Set the hash value of the file.
     * @param hash File hash value
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * Get the ID of the project that manages the file.
     * @return {@link #projectid}
     */
    public Long getProjectid() {
        return projectid;
    }

    /**
     * Set the ID of the project that manages the file.
     * @param projectid ID of the project that manages the file
     */
    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

}
