package com.zipc.garden.webplatform.client.factory.tc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.tc.TCFactory;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCNodeState;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.webplatform.server.TCModelFactory;

public class TCFactoryTest {

    private TCRoot odRoot;

    private TCRoot oldRoot;

    @Before
    public void printBefore() {
        System.out.println("------------------------");
    }

    @After
    public void printAfter() {
        System.out.println("------------------------");

    }

    public TCRoot createTestData1() {
        TCRoot newRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode nodeA = TCFactory.eINSTANCE.createTCNode();
        TCNode nodeB = TCFactory.eINSTANCE.createTCNode();
        TCNode nodeC = TCFactory.eINSTANCE.createTCNode();
        TCNode nodeD = TCFactory.eINSTANCE.createTCNode();
        nodeA.setName("nodeA");
        nodeB.setName("nodeB");
        nodeC.setName("nodeC");
        nodeD.setName("nodeD");
        newRoot.getRootNodes().add(nodeA);
        nodeA.getChildren().add(nodeB);
        nodeA.getChildren().add(nodeC);
        nodeB.getChildren().add(nodeD);
        return newRoot;
    }

    public TCRoot createTestData2() {
        TCRoot newRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode nodeA = TCFactory.eINSTANCE.createTCNode();
        TCNode nodeB = TCFactory.eINSTANCE.createTCNode();
        TCNode nodeC = TCFactory.eINSTANCE.createTCNode();
        TCNode nodeD = TCFactory.eINSTANCE.createTCNode();
        nodeA.setName("nodeA");
        nodeB.setName("nodeB");
        nodeC.setName("nodeC");
        nodeD.setName("nodeD");
        newRoot.getRootNodes().add(nodeA);
        nodeA.getChildren().add(nodeB);
        nodeA.getChildren().add(nodeC);
        nodeC.getChildren().add(nodeD);
        return newRoot;
    }

    private void createTC02() {
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = TCFactory.eINSTANCE.createTCNode();
        odNodeRoot.setName("A");

        TCNode odNode1 = TCFactory.eINSTANCE.createTCNode();
        odNode1.setName("A1");

        TCNode odNode12 = TCFactory.eINSTANCE.createTCNode();
        odNode12.setName("A12");

        TCNode odNode2 = TCFactory.eINSTANCE.createTCNode();
        odNode2.setName("A2");
        TCNode odNode21 = TCFactory.eINSTANCE.createTCNode();
        odNode21.setName("A21");
        TCNode odNode22 = TCFactory.eINSTANCE.createTCNode();
        odNode22.setName("A22");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.XOR);
        odNode1.getChildren().add(odNode12);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);

    }

    private void createTC03() {
        oldRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = TCFactory.eINSTANCE.createTCNode();
        odNodeRoot.setName("A");

        TCNode odNode1 = TCFactory.eINSTANCE.createTCNode();
        odNode1.setName("A1");
        TCNode odNode11 = TCFactory.eINSTANCE.createTCNode();
        odNode11.setName("A11");
        TCNode odNode12 = TCFactory.eINSTANCE.createTCNode();
        odNode12.setName("A12");

        TCNode odNode2 = TCFactory.eINSTANCE.createTCNode();
        odNode2.setName("A2");
        TCNode odNode22 = TCFactory.eINSTANCE.createTCNode();
        odNode22.setName("A22");

        oldRoot.getRootNodes().clear();
        oldRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.XOR);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode22);

    }

    @Test
    public void test01() {
        TCRoot newRoot = createTestData1();
        TCRoot oldRoot = createTestData2();
        TCModelFactory f = new TCModelFactory();
        TCRoot mergedRoot = f.createTCRoot(oldRoot, newRoot);

        assertEquals(1, mergedRoot.getRootNodes().size());
        assertEquals("nodeA", mergedRoot.getRootNodes().get(0).getName());
        TCNode mergedA = mergedRoot.getRootNodes().get(0);
        assertEquals(2, mergedA.getChildren().size());

        Map<String, TCNode> namedMap = createMap(mergedA);
        assertEquals(2, mergedA.getChildren().size());
        assertTrue(namedMap.containsKey("nodeB"));
        assertTrue(namedMap.containsKey("nodeC"));

        TCNode mergedB = namedMap.get("nodeB");
        TCNode mergedC = namedMap.get("nodeC");

        namedMap = createMap(mergedB);
        assertTrue(namedMap.containsKey("nodeD"));

        TCNode mergedB_D = namedMap.get("nodeD");

        namedMap = createMap(mergedC);
        assertTrue(namedMap.containsKey("nodeD"));

        TCNode mergedC_D = namedMap.get("nodeD");

        assertEquals(TCNodeState.NEW, mergedB_D.getState());

        assertEquals(TCNodeState.DELETED, mergedC_D.getState());

    }

    @Test
    public void test02_createFromTCRoot() {
        // TCRootよりTCRootを生成するパターン
        // TCRootからのツリー構造が正しく生成されているか

        createTC02();

        TCModelFactory modelFactory = new TCModelFactory();

        // createTCRoot(TCRoot old, TCRoot new)
        TCRoot result = modelFactory.createTCRoot(odRoot, odRoot);

        StringBuilder sb = new StringBuilder();
        checkTC(odRoot, sb, true);

        // expected
        final String expected = sb.toString();

        StringBuilder nsb = new StringBuilder();
        checkTC(result, nsb, true);

        // actual
        final String actual = nsb.toString();

        System.out.println("02 : " + expected);
        System.out.println("02 : " + actual);
        // judgement
        assertEquals(expected, actual);
    }

    @Test
    public void test03_createFromTCRoot() {
        // TCRootよりTCRootを生成するパターン
        // TCRootの比較前後の差分が正しく出力されるか

        createTC02();
        createTC03();

        TCModelFactory modelFactory = new TCModelFactory();

        // createTCRoot(TCRoot old, TCRoot new)
        TCRoot result = modelFactory.createTCRoot(oldRoot, odRoot);

        StringBuilder sb = new StringBuilder();
        checkTC(odRoot, sb, true);
        StringBuilder sb2 = new StringBuilder();
        checkTC(oldRoot, sb2, true);

        // expected
        final String expected = "/A/A1/A12/false/AND/UNCHANGED/A/A1/A11/false/AND/DELETED/A/A1/false/XOR/UNCHANGED/A/A2/A21/false/AND/NEW"
                + "/A/A2/A22/false/AND/UNCHANGED/A/A2/false/XOR/UNCHANGED/A/false/AND/UNCHANGED";

        StringBuilder nsb = new StringBuilder();
        checkTC(result, nsb, true);

        // actual
        final String actual = nsb.toString();

        System.out.println("03 : " + expected);
        System.out.println("03 : " + actual);
        // judgement
        assertEquals(expected, actual);
    }

    private Map<String, TCNode> createMap(TCNode node) {
        List<TCNode> children = node.getChildren();
        Map<String, TCNode> namedMap = new HashMap<String, TCNode>();
        children.forEach(n -> namedMap.put(n.getName(), n));
        return namedMap;
    }

    private void checkTC(TCRoot root, StringBuilder sb, boolean isTCNode) {
        if (root.getRootNodes() != null) {
            root.getRootNodes().forEach(x -> {
                outputNode(x, "", sb, isTCNode);
            });
        }
    }

    private void outputNode(TCNode x, String path, StringBuilder sb, boolean isTCNode) {
        path += "/" + x.getName();
        final String full = path;
        if (x.getChildren() != null && x.getChildren().size() > 0) {
            x.getChildren().forEach(child -> {
                outputNode(child, full, sb, isTCNode);
            });
        }
        if (isTCNode) {
            sb.append(full + "/" + x.isChecked() + "/" + x.getChildType() + "/" + x.getState());
        } else {
            sb.append(full + "/" + x.getChildType());
        }
    }
}
