package com.zipc.garden.webplatform.server.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.model.fmc.FMCRoot;
import com.zipc.garden.model.tc.TCFactory;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.model.tps.NodeVariable;
import com.zipc.garden.model.tps.TPSFactory;
import com.zipc.garden.model.tps.TPSRoot;
import com.zipc.garden.webplatform.client.editor.tps.TPSResourceService;
import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.server.TCModelFactory;
import com.zipc.garden.webplatform.server.TCModelParserServer;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.NodeUtil;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the TPSResourceService interface.
 */
@SuppressWarnings("serial")
public class TPSResourceServiceImpl extends RemoteServiceServlet implements TPSResourceService {

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
            TPSRoot oldRoot = null;
            try {
                oldResource.load(byteArray, null);
                oldRoot = (TPSRoot) oldResource.getContents().get(0);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            File refFile = session.byId(File.class).load(fileId);
            if (refFile.getExtension().equals(Extension.FMC.getValue())) { // FMCのReloadによるTPSRoot更新処理
                FMCRoot fmcRoot = null;
                try {
                    BinaryResourceImpl r = new BinaryResourceImpl();
                    ByteArrayInputStream bi = new ByteArrayInputStream(refFile.getContent());
                    r.load(bi, null);
                    fmcRoot = (FMCRoot) r.getContents().get(0);
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
                // TPSRootに旧FMCへの関連があれば削除する
                long projectId = EditResourceUtil.INSTANCE.getProjectId(fileId);
                Reference removeRef = getFmcRef(oldRoot, projectId);
                if (null != removeRef) {
                    oldRoot.getRefs().remove(removeRef);
                }
                Reference ref = COREFactory.eINSTANCE.createReference();
                ref.setRefid(refFile.getUuid());
                ref.setHash(refFile.getHash());
                ref.setRefExtension(refFile.getExtension());
                ref.setRefName(refFile.getName());
                oldRoot.getRefs().add(ref);
                oldRoot.setFmcRoot(fmcRoot);
                // Binary化
                BinaryResourceImpl re = new BinaryResourceImpl();
                re.getContents().add(oldRoot);
                final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                re.save(outputStream, EditOptions.getDefaultSaveOptions());
                returnData = outputStream.toByteArray();
                return returnData;
            } else { // FM/TCのReloadによるFPS更新処理
                TCRoot newRoot = null;
                // load DB filecontent
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
                    ref.setRefid(refFile.getUuid());
                    ref.setHash(refFile.getHash());
                    ref.setRefExtension(refFile.getExtension());
                    ref.setRefName(refFile.getName());
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
                    ref.setRefid(refFile.getUuid());
                    ref.setHash(refFile.getHash());
                    ref.setRefExtension(refFile.getExtension());
                    ref.setRefName(refFile.getName());
                    newRoot.getRefs().clear();
                    newRoot.getRefs().add(ref);
                } else {
                    newRoot = TCFactory.eINSTANCE.createTCRoot();
                    newRoot.getRefs().clear();
                }
                TPSRoot retRoot = TPSFactory.eINSTANCE.createTPSRoot();
                EList<TCNode> tcNodes = new TCModelFactory().createTCNodes(oldRoot.getRootNodes(), newRoot);
                retRoot.getRootNodes().addAll(tcNodes);
                retRoot.setId(oldRoot.getId());
                retRoot.getRefs().addAll(newRoot.getRefs());
                // FMCを設定
                long projectId = EditResourceUtil.INSTANCE.getProjectId(fileId);
                Reference fmcRef = getFmcRef(oldRoot, projectId);
                if (null != fmcRef) {
                    retRoot.getRefs().add(fmcRef);
                    retRoot.setFmcRoot(oldRoot.getFmcRoot());
                }
                // PatternElementを設定
                retRoot.getPatternElements().addAll(oldRoot.getPatternElements());

                // 変数を設定
                retRoot.getNodeVariables().addAll(oldRoot.getNodeVariables());
                int variableNameCnt = 1;
                List<TCNode> endNodeParentsAll = new ArrayList<TCNode>();
                for (TCNode tcNode : retRoot.getRootNodes()) {
                    List<TCNode> endNodeParents = NodeUtil.getInstance().getEndNodeParent(tcNode, false);
                    endNodeParentsAll.addAll(endNodeParents);
                    for (TCNode parent : endNodeParents) {
                        Optional<NodeVariable> opt = retRoot.getNodeVariables().stream().filter(n -> {
                            String fullPathA = NodeUtil.getInstance().getTCNodeFullPath(n.getTcNode(), false);
                            String fullPathB = NodeUtil.getInstance().getTCNodeFullPath(parent, false);
                            return fullPathA.equals(fullPathB);
                        }).findFirst();
                        NodeVariable nodeVariable;
                        if (opt.isPresent()) {
                            nodeVariable = opt.get();
                            nodeVariable.setTcNode(parent);
                        } else {
                            nodeVariable = TPSFactory.eINSTANCE.createNodeVariable();
                            nodeVariable.setTcNode(parent);
                            nodeVariable.setVariableName("val" + variableNameCnt++);
                        }
                        retRoot.getNodeVariables().add(nodeVariable);
                    }
                }
                retRoot.getNodeVariables().removeIf(nv -> {
                    Optional<TCNode> opt = endNodeParentsAll.stream().filter(enp -> EcoreUtil.equals(enp, nv.getTcNode())).findFirst();
                    return !opt.isPresent();
                });

                // Binary化
                BinaryResourceImpl re = new BinaryResourceImpl();
                re.getContents().add(retRoot);
                final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                re.save(outputStream, EditOptions.getDefaultSaveOptions());
                returnData = outputStream.toByteArray();
                return returnData;
            }
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Get Reference pointing to FMC from refs in feature-pattern-setting Root
     * @param root feature-pattern-setting Root
     * @param projectId Project ID to which FPS / FMC belongs
     * @return Reference of FMCRoot. Null if not set
     */
    private Reference getFmcRef(TPSRoot root, long projectId) {
        for (Reference ref : root.getRefs()) {
            String tpsUuid = ref.getRefid();
            Extension extension = EditResourceUtil.INSTANCE.getVMFile(tpsUuid, projectId).getExtension();
            if (extension == Extension.FMC) {
                return ref;
            }
        }
        return null;
    }
}
