package com.zipc.garden.webplatform.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

/**
 * Data Access Object Class for File Table
 */
@Entity
@Table(indexes = { @Index(name = "ix_file_projectid", columnList = "projectid", unique = false), @Index(name = "ix_file_parentDirId", columnList = "parentDirId", unique = false) })
public class File implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = -7156042397260876102L;

    /** Unique ID of the File table */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /** UUID of the file */
    @Column
    private String uuid;

    /** File name */
    @Column
    private String name;

    /** File extension */
    @Column
    private String extension;

    /** Parent directory */
    @OneToOne
    @JoinColumn(name = "parentDirId")
    private Directory directory;

    /** Full path of the file */
    @Column
    private String fullPath;

    /** File contents (EMF model or text binary data) */
    @Column
    private byte[] content;

    /** The user who created the file */
    @Column
    private Long createUser;

    /** File creation date and time */
    @Column
    @CreationTimestamp
    private Timestamp createTime;

    /** The user who last updated the file */
    @Column
    private Long updateUser;

    /** File update date and time */
    @Column
    @CreationTimestamp
    private Timestamp updateTime;

    /** File hash */
    @Column
    private String hash;

    /** ID of the project that manages the file */
    @Column
    private Long projectid;

    /** File deletion flag. If True, it has been deleted. If False, it has not been deleted. */
    @Column
    private boolean deleteFlg;

    /** @deprecated 用途不明 */
    @Column
    private boolean commitFlg;

    /**
     * constructor
     */
    public File() {

    }

    /**
     * constructor <br>
     * Use this if the operation is for the specified File ID.
     * @param id specified File ID.
     */
    public File(Long id) {
        this.id = id;
    }

    /**
     * Get the file ID.
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the file ID.
     * @param id file ID.
     */
    public void setId(Long id) {
        this.id = id;
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
     * @param name file name.
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
     * Gets the parent directory information.
     * @return {@link #directory}
     */
    public Directory getDirectory() {
        return directory;
    }

    /**
     * Set the parent directory information.
     * @param directory parent directory info
     */
    public void setDirectory(Directory directory) {
        this.directory = directory;
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
     * Get the ID of the user who created the file.
     * @return {@link #createUser}
     */
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * Set the ID of the user who created the file.
     * @param createUser ID of the user who created the file
     */
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    /**
     * Gets the file creation date and time.
     * @return {@link #createTime}
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * Set the file creation date and time.
     * @param createTime File creation date and time
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    /**
     * Gets the file deletion flag.
     * @return {@link #deleteFlg}
     */
    public boolean isDeleteFlg() {
        return deleteFlg;
    }

    /**
     * Set the file deletion flag.
     * @param deleteFlg file deletion flag.
     */
    public void setDeleteFlg(boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    /**
     * @deprecated 用途不明
     * @return
     */
    public boolean isCommitFlg() {
        return commitFlg;
    }

    /**
     * @deprecated 用途不明
     * @param commitFlg
     */
    public void setCommitFlg(boolean commitFlg) {
        this.commitFlg = commitFlg;
    }

    /**
     * Gets the UUID of the file.
     * @return {@link #uuid}
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Set the UUID of the file.
     * @param uuid UUID of the file
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
