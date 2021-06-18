package com.zipc.garden.webplatform.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * This class stores the search results of the model search function of Project Explorer.
 */
public class SearchResult implements IsSerializable {

    /** file id */
    private long id;

    /** Node name (full path) or state name */
    private String name;

    /** Full path of file */
    private String fullPath;

    /** File extension */
    private String extension;

    /** Binary information of FMNode or FSMState class */
    private byte[] node;

    /**
     * constructor
     */
    public SearchResult() {

    }

    /**
     * constructor
     * @param id {@link #id}
     * @param name {@link #name}
     * @param fullPath {@link #fullPath}
     * @param extension {@link #extension}
     * @param node {@link #node}
     */
    public SearchResult(long id, String name, String fullPath, String extension, byte[] node) {
        this.id = id;
        this.name = name;
        this.fullPath = fullPath;
        this.extension = extension;
        this.node = node;
    }

    /**
     * constructor
     * @param id {@link #id}
     * @param name {@link #name}
     */
    public SearchResult(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get file id
     * @return {@link #id}
     */
    public long getId() {
        return id;
    }

    /**
     * Set file id
     * @param id file id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the node name (or State name).
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Set the node name (or State name).
     * @param name the node name (or State name).
     */
    public void setName(String name) {
        this.name = name;
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
     * @param fullPath the full path of the file.
     */
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    /**
     * Gets the file extension.
     * @return {@link #extension}
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets the file extension.
     * @param extension the file extension.
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Acquires the binary information of FMNode (or FSMState).
     * @return {@link #node}
     */
    public byte[] getNode() {
        return node;
    }

    /**
     * Set the binary information of FMNode (or FSMState).
     * @param node the binary information of FMNode (or FSMState).
     */
    public void setNode(byte[] node) {
        this.node = node;
    }
}
