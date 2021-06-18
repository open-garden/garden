package com.zipc.garden.webplatform.shared.resource;

import com.zipc.garden.webplatform.shared.Extension;

/**
 * Manages the file information displayed in the project explorer.
 */
public class VMFile extends VMResource {

    /** File extension */
    private String extension;

    /** UUID of file */
    private String uuid;

    /** File deletion flag. If True, it has been deleted. */
    private boolean deleteFlg;

    /** File hash value */
    private String hash;

    /**
     * constructor
     */
    public VMFile() {
    }

    /**
     * constructor.<br>
     * Used to get records in the file table.<br>
     * The item specified in the SELECT statement must be set in the argument.
     * @param id file id
     * @param name file name
     * @param extensionStr {@link #extension}
     */
    public VMFile(long id, String name, String extensionStr) {
        setId(id);
        setName(name);
        setExtensionStr(extensionStr);
    }

    /**
     * constructor.<br>
     * Used to get records in the file table.<br>
     * The item specified in the SELECT statement must be set in the argument.
     * @param id file id
     * @param name file name
     * @param extensionStr {@link #extension}
     * @param fullPath Full path of file
     * @param uuid {@link #uuid}
     * @param deleteFlg {@link #deleteFlg}
     * @param hash {@link #hash}
     */
    public VMFile(long id, String name, String extensionStr, String fullPath, String uuid, boolean deleteFlg, String hash) {
        setId(id);
        setName(name);
        setExtensionStr(extensionStr);
        setFullPath(fullPath);
        setUuid(uuid);
        setDeleteFlg(deleteFlg);
        setHash(hash);
    }

    /**
     * Get the enumeration constant of Extension class based on the extension of the field variable.
     * @return enumeration constant of Extension class
     */
    public Extension getExtension() {
        return Extension.getByCode(extension);
    }

    /**
     * Sets the extension of enumeration constants.
     * @param extension Enumeration constant
     */
    public void setExtension(Extension extension) {
        this.extension = extension.getValue();
    }

    /**
     * Gets the file extension.
     * @return {@link #extension}
     */
    public String getExtensionStr() {
        return extension;
    }

    /**
     * Set the file extension.
     * @param extensionStr {@link #extension}
     */
    public void setExtensionStr(String extensionStr) {
        this.extension = extensionStr;
    }

    /**
     * Get UUID of file
     * @return {@link #uuid}
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Set UUID of file
     * @param uuid {@link #uuid}
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Get file deletion flag
     * @return {@link #deleteFlg}
     */
    public boolean getDeleteFlg() {
        return deleteFlg;
    }

    /**
     * Set file deletion flag
     * @param deleteFlg {@link #deleteFlg}
     */
    public void setDeleteFlg(boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    /**
     * Get the hash value of a file
     * @return {@link #hash}
     */
    public String getHash() {
        return hash;
    }

    /**
     * Set the hash value of the file
     * @param hash {@link #hash}
     */
    public void setHash(String hash) {
        this.hash = hash;
    }
}
