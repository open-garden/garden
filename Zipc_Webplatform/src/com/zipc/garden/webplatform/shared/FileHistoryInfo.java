package com.zipc.garden.webplatform.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Class that manages file history information
 */
public class FileHistoryInfo implements IsSerializable {

    /** File ID */
    private long fileId;

    /** File update time */
    private Date updateTime;

    /** File update user ID */
    private Long user;

    /** File update user name */
    private String userName;

    /** File name */
    private String fileName;

    /** File full path */
    private String filePath;

    /** File hash value */
    private String hash;

    /**
     * Get file ID
     * @return {@link #fileId}
     */
    public long getFileId() {
        return fileId;
    }

    /**
     * Set file ID
     * @param fileId {@link #fileId}
     */
    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    /**
     * Get file update time
     * @return {@link #updateTime}
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * Set file update time
     * @param updateTime {@link #updateTime}
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Get the ID of the user who updated the file
     * @return {@link #user}
     */
    public Long getUser() {
        return user;
    }

    /**
     * Set the ID of the user who updated the file
     * @param user {@link #user}
     */
    public void setUser(Long user) {
        this.user = user;
    }

    /**
     * Get the name of the user who updated the file
     * @return {@link #userName}
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the name of the user who updated the file
     * @param userName {@link #userName}
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get file name
     * @return {@link #fileName}
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set file name
     * @param fileName {@link #fileName}
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get full path of file
     * @return {@link #filePath}
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Set the full path of the file
     * @param filePath {@link #filePath}
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Get the hash value of a file
     * @return {@link #hash}
     */
    public String getHash() {
        return hash;
    }

    /**
     * Set hash value of file
     * @param hash {@link #hash}
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * constructor.
     */
    public FileHistoryInfo() {
        super();
    }

    /**
     * constructor.<br>
     * Used to get records in the file history table.<br>
     * The item specified in the SELECT statement must be set in the argument.
     * @param fileId {@link #fileId}
     * @param updateTime {@link #updateTime}
     * @param user {@link #user}
     * @param userName {@link #userName}
     * @param fileName {@link #fileName}
     * @param filePath {@link #filePath}
     * @param hash {@link #hash}
     */
    public FileHistoryInfo(long fileId, Date updateTime, Long user, String userName, String fileName, String filePath, String hash) {
        this.fileId = fileId;
        this.updateTime = updateTime;
        this.user = user;
        this.userName = userName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.hash = hash;
    }

}
