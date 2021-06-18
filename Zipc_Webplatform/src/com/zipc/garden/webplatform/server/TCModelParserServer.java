package com.zipc.garden.webplatform.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.smartgwt.client.util.SC;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.model.tc.TCFactory;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCProperty;
import com.zipc.garden.model.tc.TCRoot;

/**
 * <pre>
 * This class manages the following:
 * The process of analyzing the FMRoot model (OD: Operational Domain) and creating the TCRoot model (ODD: Operational Design Domain).
 * The process of analyzing the TCRoot model (ODD: Operational Design Domain) and creating the TCRoot model (TSD: Test Scenario Domain).
 * </pre>
 */
public class TCModelParserServer {

    /**
     * Analyze TCRoot Model (ODD:Operational Design Domain) and create TCRoot Model (TSD:Test Scenario Domain).
     * @param tcRoot TC file contents
     * @return Created TCRoot model
     */
    public TCRoot parseTCModel(TCRoot tcRoot) {
        TCRoot retRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode rootNode = null;
        for (TCNode sourceRoot : tcRoot.getRootNodes()) {
            rootNode = TCFactory.eINSTANCE.createTCNode();
            rootNode.setName(sourceRoot.getName());
            rootNode.setChildType(sourceRoot.getChildType());
            rootNode.setOptional(sourceRoot.isOptional());
            rootNode.setChecked(sourceRoot.isChecked());
            rootNode.setInherited(sourceRoot.isChecked());
            rootNode.setMin(sourceRoot.getMin());
            rootNode.setMax(sourceRoot.getMax());
            rootNode.setNWiseCombination(sourceRoot.getNWiseCombination());
            rootNode.getProperties().addAll(sourceRoot.getProperties());
            retRoot.getRootNodes().add(rootNode);
        }
        for (TCNode parent : tcRoot.getRootNodes()) {
            for (TCNode child : parent.getChildren()) {
                parseTCNode(rootNode, parent, child);
            }
        }
        return retRoot;
    }

    /**
     * Analyze TCRoot Model (ODD:Operational Design Domain) and create TCRoot Model (TSD:Test Scenario Domain). <br>
     * Check the child nodes recursively.
     * @param tcNode Parent TCNode
     * @param parentNode TODO 未使用
     * @param childNode Child TCNode
     */
    private void parseTCNode(TCNode tcNode, TCNode parentNode, TCNode childNode) {
        TCNode temp = TCFactory.eINSTANCE.createTCNode();
        temp.setName(childNode.getName());
        temp.setChildType(childNode.getChildType());
        temp.setOptional(childNode.isOptional());
        temp.setChecked(childNode.isChecked());
        temp.setInherited(childNode.isChecked());
        temp.setMin(childNode.getMin());
        temp.setMax(childNode.getMax());
        temp.setNWiseCombination(childNode.getNWiseCombination());
        temp.getProperties().addAll(childNode.getProperties());
        tcNode.getChildren().add(temp);
        if (childNode.getChildren() != null && childNode.getChildren().size() > 0) {
            childNode.getChildren().forEach(x -> {
                parseTCNode(temp, childNode, x);
            });
        }
    }

    /**
     * Analyze FMRoot Model and create TCRoot Model.
     * @param fmRoot FM file contents
     * @param projectId FM file project ID
     * @return Created TCRoot model
     */
    public TCRoot parseFMModel(FMRoot fmRoot, long projectId) {
        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();
        if (fmRoot.getNode() == null) {
            return tcRoot;
        }
        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName(fmRoot.getNode().getName());
        rootNode.setChildType(fmRoot.getNode().getChildType());
        rootNode.setOptional(fmRoot.getNode().isOptional());
        rootNode.setMin(fmRoot.getNode().getMin());
        rootNode.setMax(fmRoot.getNode().getMax());
        rootNode.setNWiseCombination(0);
        fmRoot.getNode().getProperties().forEach(fmProp -> {
            TCProperty tcProp = TCFactory.eINSTANCE.createTCProperty();
            tcProp.setKey(fmProp.getKey());
            tcProp.setValue(fmProp.getValue());
            rootNode.getProperties().add(tcProp);
        });
        tcRoot.getRootNodes().add(rootNode);
        if (fmRoot.getNode().getChildren() != null && fmRoot.getNode().getChildren().size() > 0) {
            fmRoot.getNode().getChildren().forEach(x -> {
                parseFMNode(rootNode, fmRoot.getNode(), x, projectId);
            });
        }
        return tcRoot;
    }

    /**
     * Analyze FMRoot Model and create TCRoot Model.<br>
     * Check the child nodes recursively.
     * @param tcNode Parent TCNode
     * @param parentNode TODO 未使用
     * @param childNode Child FMNode
     * @param projectId FM file project ID
     */
    private void parseFMNode(TCNode tcNode, FMNode parentNode, FMNode childNode, long projectId) {
        TCNode temp = TCFactory.eINSTANCE.createTCNode();
        temp.setName(childNode.getName());
        temp.setChildType(childNode.getChildType());
        temp.setOptional(childNode.isOptional());
        temp.setMin(childNode.getMin());
        temp.setMax(childNode.getMax());
        childNode.getProperties().forEach(fmProp -> {
            TCProperty tcProp = TCFactory.eINSTANCE.createTCProperty();
            tcProp.setKey(fmProp.getKey());
            tcProp.setValue(fmProp.getValue());
            temp.getProperties().add(tcProp);
        });
        if (childNode.getRefuuid() != null) {
            byte[] result = EditResourceUtil.INSTANCE.getFileContent(childNode.getRefuuid(), projectId);
            BinaryResourceImpl r = new BinaryResourceImpl();
            ByteArrayInputStream bi = new ByteArrayInputStream(result);
            FMRoot fmRoot = null;
            try {
                r.load(bi, null);
                fmRoot = (FMRoot) r.getContents().get(0);
            } catch (IOException e) {
                SC.warn(e.getMessage());
            }
            final FMNode rootNode = fmRoot.getNode();
            temp.setChildType(rootNode.getChildType());
            temp.setMin(rootNode.getMin());
            temp.setMax(rootNode.getMax());
            fmRoot.getNode().getChildren().forEach(x -> {
                parseFMNode(temp, rootNode, x, projectId);
            });
            tcNode.getChildren().add(temp);
        } else {
            System.out.println(temp.getName() + ":" + temp.getProperties());
            tcNode.getChildren().add(temp);
            if (childNode.getChildren() != null && childNode.getChildren().size() > 0) {
                childNode.getChildren().forEach(x -> {
                    parseFMNode(temp, childNode, x, projectId);
                });
            }
        }
    }

}
