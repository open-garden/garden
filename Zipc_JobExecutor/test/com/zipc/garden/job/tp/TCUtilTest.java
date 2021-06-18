package com.zipc.garden.job.tp;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.zipc.garden.model.tc.TCFactory;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.model.tps.TPSFactory;
import com.zipc.garden.model.tps.TPSRoot;
import com.zipc.garden.webplatform.shared.NodeUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TCUtilTest {

    private TCRoot tcRoot;

    @Before
    public void printBefore() {
        System.out.println("------");
    }

    @After
    public void printAfter() {
        System.out.println("------");

    }

    private TCNode createTCNode(TCNode parent, String name, boolean temporary) {
        TCNode node = TCFactory.eINSTANCE.createTCNode();
        node.setName(name);
        if (temporary) {
            node.setTemporary(temporary);
        }
        parent.getChildren().add(node);
        return node;
    }

    @Test
    public void Test01_getTCNodeFullPath() {
        tcRoot = TCFactory.eINSTANCE.createTCRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        TCNode node1 = createTCNode(rootNode, "1", true);
        TCNode node11 = createTCNode(node1, "11", false);
        TCNode node111 = createTCNode(node11, "111", false);

        TCNode node2 = createTCNode(rootNode, "2", false);
        TCNode node21 = createTCNode(node2, "21", true);
        TCNode node211 = createTCNode(node21, "211", false);

        TCNode node3 = createTCNode(rootNode, "3", true);
        TCNode node31 = createTCNode(node3, "31", false);
        TCNode node311 = createTCNode(node31, "311", true);
        TCNode node3111 = createTCNode(node311, "3\\1.1 \"1", false);

        assertEquals("Root.1", NodeUtil.getInstance().getTCNodeFullPath(node1, true));
        assertEquals("Root.1", NodeUtil.getInstance().getTCNodeFullPath(node1, false));
        assertEquals("Root.11", NodeUtil.getInstance().getTCNodeFullPath(node11, true));
        assertEquals("Root.1.11", NodeUtil.getInstance().getTCNodeFullPath(node11, false));
        assertEquals("Root.11.111", NodeUtil.getInstance().getTCNodeFullPath(node111, true));
        assertEquals("Root.1.11.111", NodeUtil.getInstance().getTCNodeFullPath(node111, false));
        assertEquals("Root.2", NodeUtil.getInstance().getTCNodeFullPath(node2, true));
        assertEquals("Root.2", NodeUtil.getInstance().getTCNodeFullPath(node2, false));
        assertEquals("Root.2.21", NodeUtil.getInstance().getTCNodeFullPath(node21, true));
        assertEquals("Root.2.21", NodeUtil.getInstance().getTCNodeFullPath(node21, false));
        assertEquals("Root.2.211", NodeUtil.getInstance().getTCNodeFullPath(node211, true));
        assertEquals("Root.2.21.211", NodeUtil.getInstance().getTCNodeFullPath(node211, false));
        assertEquals("Root.3", NodeUtil.getInstance().getTCNodeFullPath(node3, true));
        assertEquals("Root.3", NodeUtil.getInstance().getTCNodeFullPath(node3, false));
        assertEquals("Root.31", NodeUtil.getInstance().getTCNodeFullPath(node31, true));
        assertEquals("Root.3.31", NodeUtil.getInstance().getTCNodeFullPath(node31, false));
        assertEquals("Root.31.311", NodeUtil.getInstance().getTCNodeFullPath(node311, true));
        assertEquals("Root.3.31.311", NodeUtil.getInstance().getTCNodeFullPath(node311, false));
        assertEquals("Root.31.\"3\\\\1.1 \\\"1\"", NodeUtil.getInstance().getTCNodeFullPath(node3111, true));
        assertEquals("Root.3.31.311.\"3\\\\1.1 \\\"1\"", NodeUtil.getInstance().getTCNodeFullPath(node3111, false));
    }

    @Test
    public void Test02_findNodes() {
        tcRoot = TCFactory.eINSTANCE.createTCRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        TCNode node1 = createTCNode(rootNode, "Optional", false);
        TCNode node11 = createTCNode(node1, "11", false);
        TCNode node111 = createTCNode(node11, "111", false);
        createTCNode(node111, "1111", false);

        TCNode node2 = createTCNode(rootNode, "Optional", true);
        TCNode node21 = createTCNode(node2, "21", false);
        TCNode node211 = createTCNode(node21, "111", true);
        createTCNode(node211, "1111", false);

        TCNode node3 = createTCNode(rootNode, "Optional", true);
        TCNode node31 = createTCNode(node3, "31", false);
        TCNode node311 = createTCNode(node31, "311", false);
        createTCNode(node311, "1\\1.1 \"1", false);

        List<TCNode> tcNodes;
        tcNodes = TCUtil.findNodes(tcRoot, "1111", false);
        assertEquals(tcNodes.size(), 2);
        assertEquals("Root.Optional.11.111.1111", NodeUtil.getInstance().getTCNodeFullPath(tcNodes.get(0), false));
        assertEquals("Root.Optional.21.111.1111", NodeUtil.getInstance().getTCNodeFullPath(tcNodes.get(1), false));

        tcNodes = TCUtil.findNodes(tcRoot, "111.1111", false);
        assertEquals(tcNodes.size(), 2);
        assertEquals("Root.Optional.11.111.1111", NodeUtil.getInstance().getTCNodeFullPath(tcNodes.get(0), false));
        assertEquals("Root.Optional.21.111.1111", NodeUtil.getInstance().getTCNodeFullPath(tcNodes.get(1), false));

        tcNodes = TCUtil.findNodes(tcRoot, "111.1111", true);
        assertEquals(tcNodes.size(), 1);
        assertEquals("Root.Optional.11.111.1111", NodeUtil.getInstance().getTCNodeFullPath(tcNodes.get(0), false));

        tcNodes = TCUtil.findNodes(tcRoot, "Root.31.311.\"1\\\\1.1 \\\"1\"", true);
        assertEquals(tcNodes.size(), 1);
        assertEquals("Root.Optional.31.311.\"1\\\\1.1 \\\"1\"", NodeUtil.getInstance().getTCNodeFullPath(tcNodes.get(0), false));
        assertEquals("Root.31.311.\"1\\\\1.1 \\\"1\"", NodeUtil.getInstance().getTCNodeFullPath(tcNodes.get(0), true));
    }

    @Test
    public void Test03_getNodeSimplePath() {
        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");

        TCNode node1 = createTCNode(rootNode, "1", false);
        TCNode node11 = createTCNode(node1, "11", false);
        TCNode node111 = createTCNode(node11, "11.1 \\\"", false);
        TCNode node1111 = createTCNode(node111, "1111", false);

        TCNode node2 = createTCNode(rootNode, "2", false);
        TCNode node21 = createTCNode(node2, "21", false);
        TCNode node211 = createTCNode(node21, "11.1 \\\"", false);
        TCNode node2111 = createTCNode(node211, "1111", false);

        TPSRoot tpsRoot = TPSFactory.eINSTANCE.createTPSRoot();
        tpsRoot.getRootNodes().add(rootNode);

        assertEquals("11.\"11.1 \\\\\\\"\".1111", TCUtil.getNodeSimplePath(tpsRoot, node1111));
        assertEquals("21.\"11.1 \\\\\\\"\".1111", TCUtil.getNodeSimplePath(tpsRoot, node2111));
    }
}
