package com.zipc.garden.webplatform.shared.resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the directory information displayed in the project explorer.
 */
public class VMDirectory extends VMResource {

    /** List of resources under this directory */
    private List<VMResource> resources = new ArrayList<VMResource>();

    /**
     * constructor
     */
    public VMDirectory() {
    }

    /**
     * constructor<br>
     * Used to get records in the directory table.<br>
     * The item specified in the SELECT statement must be set in the argument.
     * @param id Directory ID
     * @param name Directory name
     */
    public VMDirectory(long id, String name) {
        setId(id);
        setName(name);
    }

    /**
     * Add new resources under this directory.
     * @param resource Resource to add
     */
    public void addResource(VMResource resource) {
        this.resources.add(resource);
    }

    /**
     * Get the resource list under this directory.
     * @return {@link #resources}
     */
    public List<VMResource> getResources() {
        return this.resources;
    }

    /**
     * Recursively search the specified directory and get the Resource that matches the targetId.
     * @param dir Directory to search
     * @param targetId The specified ID
     * @return VMResource (Empty is null)
     */
    public VMResource getResource(VMDirectory dir, long targetId) {
        if (this.getId() == targetId)
            return this;
        for (VMResource ret : dir.getResources()) {
            if (ret.getId() == targetId) {
                return ret;
            } else if (ret instanceof VMDirectory) {
                VMResource temp = ((VMDirectory) ret).getResource((VMDirectory) ret, targetId);
                if (temp == null) {
                    continue;
                } else {
                    return temp;
                }
            }
        }
        return null;
    }

    /**
     * Recursively searches the specified directory to get the parent directory that has the resource matching the targetId.
     * @param parent Directory to search
     * @param targetId The specified ID
     * @return VMDirectory (Empty is null)
     */
    public VMDirectory getParent(VMDirectory parent, long targetId) {
        if (this.getId() == targetId)
            return this;
        for (VMResource ret : parent.getResources()) {
            if (ret.getId() == targetId) {
                return parent;
            } else if (ret instanceof VMDirectory) {
                VMDirectory temp = ((VMDirectory) ret).getParent((VMDirectory) ret, targetId);
                if (temp == null) {
                    continue;
                } else {
                    return temp;
                }
            }
        }
        return null;
    }

    /**
     * Recursively searches the specified directory and deletes the resource that matches the targetId.
     * @param parentDir Directory to search
     * @param targetId The specified ID
     * @return Deleted resource (Empty is null)
     */
    public VMResource removeResource(VMDirectory parentDir, long targetId) {
        for (VMResource ret : parentDir.getResources()) {
            if (ret.getId() == targetId) {
                if (parentDir.getResources().remove(ret)) {
                    return ret;
                }
            } else if (ret instanceof VMDirectory) {
                VMResource temp = ((VMDirectory) ret).removeResource((VMDirectory) ret, targetId);
                if (temp == null) {
                    continue;
                } else {
                    return temp;
                }
            }
        }
        return null;
    }
}
