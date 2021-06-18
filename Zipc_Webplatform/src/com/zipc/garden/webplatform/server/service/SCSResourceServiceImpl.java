package com.zipc.garden.webplatform.server.service;

import java.io.ByteArrayInputStream;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.model.scs.SCSPattern;
import com.zipc.garden.webplatform.client.editor.scs.SCSResourceService;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the SCSResourceService interface.
 */
@SuppressWarnings("serial")
public class SCSResourceServiceImpl extends RemoteServiceServlet implements SCSResourceService {

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveLSC(long fileId, byte[] SCSPatternBinary) throws IllegalArgumentException {
        BinaryResourceImpl r = new BinaryResourceImpl();
        ByteArrayInputStream bi = new ByteArrayInputStream(SCSPatternBinary);
        try {
            r.load(bi, null);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        SCSPattern scsPattern = (SCSPattern) r.getContents().get(0);
        EditResourceUtil.INSTANCE.saveSCSPattern(fileId, scsPattern);
    }
}
