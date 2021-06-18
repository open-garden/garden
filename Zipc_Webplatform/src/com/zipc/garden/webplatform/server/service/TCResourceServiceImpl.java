package com.zipc.garden.webplatform.server.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.model.tc.TCFactory;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.webplatform.client.editor.tc.TCResourceService;
import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.server.TCModelFactory;
import com.zipc.garden.webplatform.server.TCModelParserServer;
import com.zipc.garden.webplatform.shared.Extension;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the TCResourceService interface.
 */
@SuppressWarnings("serial")
public class TCResourceServiceImpl extends RemoteServiceServlet implements TCResourceService {

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getFileContent(long fileId, byte[] root) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            byte[] returnData = null;
            // read old root
            BinaryResourceImpl oldResource = new BinaryResourceImpl();
            ByteArrayInputStream byteArray = new ByteArrayInputStream(root);
            TCRoot oldRoot = null;
            try {
                oldResource.load(byteArray, null);
                oldRoot = (TCRoot) oldResource.getContents().get(0);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            TCRoot newRoot = null;
            // load DB filecontent
            File refFile = session.byId(File.class).load(fileId);
            BinaryResourceImpl r = new BinaryResourceImpl();
            ByteArrayInputStream bi = new ByteArrayInputStream(refFile.getContent());
            if (refFile.getExtension().equals(Extension.FM.getValue())) {
                FMRoot fmRoot = null;
                try {
                    r.load(bi, null);
                    fmRoot = (FMRoot) r.getContents().get(0);
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
                newRoot = new TCModelParserServer().parseFMModel(fmRoot, refFile.getProjectid());
                Reference ref = COREFactory.eINSTANCE.createReference();
                ref.setRefid(fmRoot.getId());
                newRoot.getRefs().clear();
                newRoot.getRefs().add(ref);
            } else if (refFile.getExtension().equals(Extension.TC.getValue())) {
                TCRoot tcRoot = null;
                try {
                    r.load(bi, null);
                    tcRoot = (TCRoot) r.getContents().get(0);
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
                newRoot = new TCModelParserServer().parseTCModel(tcRoot);
                Reference ref = COREFactory.eINSTANCE.createReference();
                ref.setRefid(tcRoot.getId());
                newRoot.getRefs().clear();
                newRoot.getRefs().add(ref);
            } else {
                newRoot = TCFactory.eINSTANCE.createTCRoot();
                newRoot.getRefs().clear();
            }
            TCRoot retRoot = new TCModelFactory().createTCRoot(oldRoot, newRoot);
            retRoot.setId(oldRoot.getId());
            retRoot.getRefs().add(oldRoot.getRefs().get(0));
            BinaryResourceImpl re = new BinaryResourceImpl();
            re.getContents().add(retRoot);
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            re.save(outputStream, EditOptions.getDefaultSaveOptions());
            returnData = outputStream.toByteArray();
            return returnData;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }
}
