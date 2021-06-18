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
import com.zipc.garden.webplatform.shared.NodeUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TCResolverTest {

    @Before
    public void printBefore() {
        System.out.println("------");
    }

    @After
    public void printAfter() {
        System.out.println("------");

    }

    private TCNode createTCNode(TCNode parent, String name, boolean temporary, boolean optional, int min, int max) {
        TCNode node = TCFactory.eINSTANCE.createTCNode();
        node.setName(name);
        if (temporary) {
            node.setTemporary(temporary);
        }
        if (optional) {
            node.setOptional(optional);
        }
        node.setMin(min);
        node.setMax(max);
        parent.getChildren().add(node);
        return node;
    }

    @Test
    public void Test01_Normal() {
        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        // 参考:parentNode, temporary, optional, min, max
        TCNode node1 = createTCNode(rootNode, "1", false, false, -1, -1);
        TCNode node11 = createTCNode(node1, "11", false, false, -1, -1);
        TCNode node111 = createTCNode(node11, "111", false, false, -1, -1);

        TCNode node2 = createTCNode(rootNode, "2", false, false, -1, -1);
        TCNode node21 = createTCNode(node2, "21", false, false, -1, -1);
        TCNode node211 = createTCNode(node21, "211", false, false, -1, -1);

        TCNode node3 = createTCNode(rootNode, "3", false, false, -1, -1);
        TCNode node31 = createTCNode(node3, "31", false, false, -1, -1);
        TCNode node311 = createTCNode(node31, "311", false, false, -1, -1);
        TCNode node312 = createTCNode(node31, "312", false, false, -1, -1);
        TCNode node3111 = createTCNode(node311, "3111", false, false, -1, -1);

        TCNode resolveNode = TCResolver.copy(tcRoot).getKey().getActiveRootNode();
        List<TCNode> nodes = TCUtil.getAllContentsOfType(rootNode, TCNode.class);
        StringBuilder before = new StringBuilder();
        nodes.stream().forEach(n -> before.append(n.getName()).append(System.lineSeparator()));

        StringBuilder after = new StringBuilder();
        List<TCNode> result = TCUtil.getAllContentsOfType(resolveNode, TCNode.class);
        result.stream().forEach(n -> after.append(n.getName()).append(System.lineSeparator()));

        assertEquals(before.toString(), after.toString());
        System.out.println("-------------------------------------- before -------------------------------------");
        System.out.println(before.toString());
        System.out.println("-------------------------------------- after -------------------------------------");
        System.out.println(after.toString());
    }

    @Test
    public void Test02_hasOptional() {
        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        // 参考:parentNode, temporary, optional, min, max
        TCNode node1 = createTCNode(rootNode, "1", false, false, -1, -1);
        TCNode node11 = createTCNode(node1, "11", false, false, -1, -1);
        TCNode node111 = createTCNode(node11, "111", false, false, -1, -1);

        TCNode node2 = createTCNode(rootNode, "2", false, false, -1, -1);
        TCNode node21 = createTCNode(node2, "21", false, false, -1, -1);
        TCNode node211 = createTCNode(node21, "211", false, false, -1, -1);

        TCNode node3 = createTCNode(rootNode, "3", false, false, -1, -1);
        TCNode node31 = createTCNode(node3, "31", false, false, -1, -1);
        TCNode node311 = createTCNode(node31, "311", false, true, -1, -1);
        TCNode node312 = createTCNode(node31, "312", false, false, -1, -1);
        TCNode node3111 = createTCNode(node311, "3111", false, false, -1, -1);

        TCNode resolveNode = TCResolver.copy(tcRoot).getKey().getActiveRootNode();

        StringBuilder after = new StringBuilder();
        List<TCNode> result = TCUtil.getAllContentsOfType(resolveNode, TCNode.class);
        result.stream().forEach(n -> after.append(NodeUtil.getInstance().getTCNodeFullPath(n, false)).append(System.lineSeparator()));

        assertEquals("Root.1\r\nRoot.1.11\r\nRoot.1.11.111\r\nRoot.2\r\nRoot.2.21\r\nRoot.2.21.211\r\nRoot.3\r\nRoot.3.31\r\nRoot.3.31.Optional\r\n"
                + "Root.3.31.Optional.OFF\r\nRoot.3.31.Optional.311\r\nRoot.3.31.Optional.311.3111\r\nRoot.3.31.Normal\r\nRoot.3.31.Normal.312\r\n", after.toString());
        System.out.println("-------------------------------------- after -------------------------------------");
        System.out.println(after.toString());
    }

    @Test
    public void Test03_hasCardinality() {
        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        rootNode.setMin(1);
        rootNode.setMax(2);
        tcRoot.getRootNodes().add(rootNode);

        // 参考:parentNode, temporary, optional, min, max
        TCNode node1 = createTCNode(rootNode, "1", false, false, -1, -1);
        TCNode node11 = createTCNode(node1, "11", false, false, -1, -1);
        TCNode node111 = createTCNode(node11, "111", false, false, -1, -1);

        TCNode node2 = createTCNode(rootNode, "2", false, false, -1, -1);
        TCNode node21 = createTCNode(node2, "21", false, false, -1, -1);
        TCNode node211 = createTCNode(node21, "211", false, false, -1, -1);

        TCNode node3 = createTCNode(rootNode, "3", false, true, -1, -1);
        TCNode node31 = createTCNode(node3, "31", false, false, -1, -1);
        TCNode node311 = createTCNode(node31, "311", false, true, -1, -1);
        TCNode node312 = createTCNode(node31, "312", false, false, -1, -1);
        TCNode node3111 = createTCNode(node311, "3111", false, false, -1, -1);

        TCNode resolveNode = TCResolver.copy(tcRoot).getKey().getActiveRootNode();

        StringBuilder after = new StringBuilder();
        List<TCNode> result = TCUtil.getAllContentsOfType(resolveNode, TCNode.class);
        result.stream().forEach(n -> after.append(NodeUtil.getInstance().getTCNodeFullPath(n, false)).append(System.lineSeparator()));

        assertEquals("Root.Optional\r\nRoot.Optional.OFF\r\nRoot.Optional.3\r\nRoot.Optional.3.31\r\nRoot.Optional.3.31.Optional\r\n"
                + "Root.Optional.3.31.Optional.OFF\r\nRoot.Optional.3.31.Optional.311\r\nRoot.Optional.3.31.Optional.311.3111\r\n"
                + "Root.Optional.3.31.Normal\r\nRoot.Optional.3.31.Normal.312\r\nRoot.Normal\r\nRoot.Normal.1\r\n"
                + "Root.Normal.1.11\r\nRoot.Normal.1.11.111\r\nRoot.Normal.2\r\nRoot.Normal.2.21\r\nRoot.Normal.2.21.211\r\n", after.toString());
        System.out.println("-------------------------------------- after -------------------------------------");
        System.out.println(after.toString());
    }
}
