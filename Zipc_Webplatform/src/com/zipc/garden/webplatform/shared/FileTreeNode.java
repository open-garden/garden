package com.zipc.garden.webplatform.shared;

import com.smartgwt.client.widgets.tree.TreeNode;
import com.zipc.garden.webplatform.shared.resource.VMDirectory;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * TreeNode class for files displayed in Project Explorer
 */
public class FileTreeNode extends TreeNode {

    /**
     * Get the VMResource associated with the TreeNode.
     * @return VMResource associated with TreeNode
     */
    public VMResource getResource() {
        return (VMResource) getAttributeAsObject("resource");
    }

    /**
     * Associate VMResource with the "resource" attribute of TreeNode.
     * @param resource VMResource to associate
     */
    public void setResource(VMResource resource) {
        setAttribute("resource", resource);
        setIsFolder(resource instanceof VMDirectory);
        String fullName = resource.getName();
        if (resource instanceof VMFile) {
            fullName += "." + ((VMFile) resource).getExtensionStr();
        }
        setAttribute("fullName", fullName);

        setName(resource.getName());
    }
}
