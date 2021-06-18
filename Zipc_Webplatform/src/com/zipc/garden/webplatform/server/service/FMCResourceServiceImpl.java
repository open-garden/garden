package com.zipc.garden.webplatform.server.service;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMPackage;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.webplatform.client.editor.fmc.FMCResourceService;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the FMCResourceService interface.
 */
@SuppressWarnings("serial")
public class FMCResourceServiceImpl extends RemoteServiceServlet implements FMCResourceService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, byte[]> getFMFileContent(String fileUuid, long projectId) throws IllegalArgumentException {
        EPackage.Registry.INSTANCE.put(FMPackage.eNS_URI, FMPackage.eINSTANCE);
        Map<String, byte[]> ret = new HashMap<>();
        setRefFMFileContent(ret, fileUuid, projectId);
        return ret;
    }

    /**
     * The file that matches the fileUuid argument is retrieved.<br>
     * It is checked whether the reference node exists in the obtained file.
     * @param fMRoots Acquired FM model
     * @param fileUuid FM file UUID
     * @param projectId project Id
     */
    private void setRefFMFileContent(Map<String, byte[]> fMRoots, String fileUuid, long projectId) {

        byte[] content = EditResourceUtil.INSTANCE.getFileContent(fileUuid, projectId);

        BinaryResourceImpl r = new BinaryResourceImpl();
        ByteArrayInputStream bi = new ByteArrayInputStream(content);
        try {
            r.load(bi, null);
            FMRoot root = (FMRoot) r.getContents().get(0);
            fMRoots.put(fileUuid, content);
            if (root.getNode() != null) {
                checkReference(fMRoots, root.getNode(), projectId);
            }
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }

    }

    /**
     * Check if the reference node exists.<br>
     * If it exists, get the file related to the reference node.
     * @param fMRoots Acquired FM model
     * @param node Node to check
     * @param projectId project Id
     */
    private void checkReference(Map<String, byte[]> fMRoots, FMNode node, long projectId) {
        if (node.getRefuuid() != null) {
            setRefFMFileContent(fMRoots, node.getRefuuid(), projectId);
        } else {
            if (!node.getChildren().isEmpty()) {
                node.getChildren().forEach(child -> checkReference(fMRoots, child, projectId));
            }
        }
    }
}
