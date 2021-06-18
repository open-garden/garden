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
 * Data Access Object Class for FileReferences Table
 */
@Entity
@Table
public class FileReferences implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = -8972846138954965044L;

    /** Unique ID of the FileReferences table */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /** File information. Joined by file ID. */
    @OneToOne
    @JoinColumn(name = "fileid")
    private File file;

    /** File extension */
    @Column
    private String extension;

    /** Reference file information. Joined by ref file ID. */
    @OneToOne
    @JoinColumn(name = "refid")
    private File refFile;

    /** UUID of the file */
    @Column
    private String fileuuid;

    /**
     * Gets the UUID of the file.
     * @return {@link #fileuuid}
     */
    public String getFileuuid() {
        return fileuuid;
    }

    /**
     * Set the UUID of the file.
     * @param fileuuid The UUID of the file.
     */
    public void setFileuuid(String fileuuid) {
        this.fileuuid = fileuuid;
    }

    /**
     * Gets the UUID of the referenced file.
     * @return {@link #refuuid}
     */
    public String getRefuuid() {
        return refuuid;
    }

    /**
     * Set the UUID of the reference file.
     * @param refuuid The UUID of the reference file.
     */
    public void setRefuuid(String refuuid) {
        this.refuuid = refuuid;
    }

    /** The UUID of the reference file. */
    @Column
    private String refuuid;

    /**
     * The hash value of the reference file. <br>
     * This value is the hash value of the reference file at the time of file registration.
     */
    @Column
    private String hash;

    /** Reference file extension. */
    @Column
    private String refextension;

    /** A project that manages files. Joined by ref project ID. */
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "projectId")
    private Project project;

    /** The project that manages the reference file. Joined by ref project ID. */
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "refprojectid")
    private Project refproject;

    /**
     * constructor
     */
    public FileReferences() {

    }

    /**
     * Gets the unique ID of the FileReferences table.
     * @return {@link id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets a unique ID for the FileReferences table.
     * @param id unique ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the file.
     * @return {@link #file}
     */
    public File getFile() {
        return file;
    }

    /**
     * Set the file.
     * @param file file.
     */
    public void setFile(File file) {
        this.file = file;
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
     * @param extension file extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Get the reference file.
     * @return {@link #refFile}
     */
    public File getRefFile() {
        return refFile;
    }

    /**
     * Set the reference file.
     * @param refFile reference file.
     */
    public void setRefFile(File refFile) {
        this.refFile = refFile;
    }

    /**
     * Gets the hash value of the reference file at the time of file registration.
     * @return {@link #hash}
     */
    public String getHash() {
        return hash;
    }

    /**
     * Set the hash value of the reference file at the time of file registration.
     * @param hash Hash value of reference file
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * Gets the extension of the referenced file.
     * @return {@link #refextension}
     */
    public String getRefextension() {
        return refextension;
    }

    /**
     * Set the extension of the reference file.
     * @param refextension {@link #refextension}
     */
    public void setRefextension(String refextension) {
        this.refextension = refextension;
    }

    /**
     * Gets the information of the project in which the file is managed.
     * @return {@link #project}
     */
    public Project getProject() {
        return project;
    }

    /**
     * Set the information of the project in which the file is managed.
     * @param project Project information
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Gets the information of the project in which the reference file is managed.
     * @return {@link #refproject}
     */
    public Project getRefproject() {
        return refproject;
    }

    /**
     * Set the information of the project in which the reference file is managed.
     * @param refproject Project information
     */
    public void setRefproject(Project refproject) {
        this.refproject = refproject;
    }
}
