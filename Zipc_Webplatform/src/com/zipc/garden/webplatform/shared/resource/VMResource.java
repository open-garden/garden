package com.zipc.garden.webplatform.shared.resource;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Manages common information of directories and files displayed in Project Explorer.
 */
public abstract class VMResource implements IsSerializable {

    /** Directory ID or file ID */
    private long id;

    /** Directory name or file name */
    private String name;

    /** Full path of directory or full path of file */
    private String fullPath;

    /**
     * constructor
     */
    public VMResource() {
    }

    /**
     * Get the directory ID or file ID.
     * @return {@link #id}
     */
    public long getId() {
        return id;
    }

    /**
     * Set the directory ID or file ID.
     * @param id {@link #id}
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the directory name or file name.
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Set the directory name or file name.
     * @param name {@link #name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the full path of a directory or file.
     * @return {@link #fullPath}
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * Set the full path of the directory or file.
     * @param fullPath {@link #fullPath}
     */
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

}
