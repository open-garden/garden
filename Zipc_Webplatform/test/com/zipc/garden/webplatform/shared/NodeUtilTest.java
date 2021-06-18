package com.zipc.garden.webplatform.shared;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.zipc.garden.model.tc.TCFactory;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tp.TPFactory;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tps.TPSFactory;
import com.zipc.garden.model.tps.TPSPatternElement;
import com.zipc.garden.model.tps.TPSRoot;

public class NodeUtilTest {

    private final NodeUtil nodeUtil = NodeUtil.getInstance();

    private TCNode createTCNode(TCNode parentNode, String name) {
        TCNode tcNode = TCFactory.eINSTANCE.createTCNode();
        tcNode.setName(name);
        tcNode.setChecked(true);

        if (parentNode != null) {
            parentNode.getChildren().add(tcNode);
        }
        return tcNode;
    }

    @Test
    public void test_getUniqueShortestPaths() {
        List<String> fullPaths = new ArrayList<>();
        fullPaths.add("root.A.A1.\"C.\\\\ \\\"1\"");
        fullPaths.add("root.A.A1.\"C.2\"");
        fullPaths.add("root.A.A2");
        fullPaths.add("root.B.B1.\"C.\\\\ \\\"1\"");
        fullPaths.add("root.B.B1.\"C.2\"");
        fullPaths.add("root.B.B2");

        List<String> simplePaths = nodeUtil.getUniqueShortestPaths(fullPaths);
        assertEquals(simplePaths.get(0), "A1.\"C.\\\\ \\\"1\"");
        assertEquals(simplePaths.get(1), "A1.\"C.2\"");
        assertEquals(simplePaths.get(2), "A2");
        assertEquals(simplePaths.get(3), "B1.\"C.\\\\ \\\"1\"");
        assertEquals(simplePaths.get(4), "B1.\"C.2\"");
        assertEquals(simplePaths.get(5), "B2");
    }

    @Test
    public void test_removeEscape() {
        String fullPath_noEsc = nodeUtil.removeEscape("\"ro ot\".\"\\\"A\".\"A\\\\1\".\"A1.1\"");
        assertEquals(fullPath_noEsc, "ro ot.\"A.A\\1.A1.1");
    }

    @Test
    public void test_headerUpdate() {
        TCNode rootNode = createTCNode(null, "root");
        TCNode a_Node = createTCNode(rootNode, "A");
        TCNode b_Node = createTCNode(rootNode, "B");
        TCNode a1_Node = createTCNode(a_Node, "A1");
        TCNode a2_Node = createTCNode(a_Node, "A2");
        TCNode b1_Node = createTCNode(b_Node, "B1");
        TCNode b2_Node = createTCNode(b_Node, "B2");
        TCNode a11_Node = createTCNode(a1_Node, "A11");
        TCNode a12_Node = createTCNode(a1_Node, "A12");
        TCNode b11_Node = createTCNode(b1_Node, "B11");
        TCNode b12_Node = createTCNode(b1_Node, "B12");

        TPSRoot tpsRoot = TPSFactory.eINSTANCE.createTPSRoot();
        tpsRoot.getRootNodes().add(rootNode);

        TPSetting tpSetting = TPFactory.eINSTANCE.createTPSetting();

        nodeUtil.headerUpdate(tpsRoot, tpSetting);

        assertEquals(tpSetting.getHeader().size(), 4);
        tpSetting.getHeader().forEach(header -> {
            switch (header) {
            case "root.A":
                assertTrue(true);
                break;
            case "root.A.A1":
                assertTrue(true);
                break;
            case "root.B":
                assertTrue(true);
                break;
            case "root.B.B1":
                assertTrue(true);
                break;
            default:
                assertTrue(false);
            }
        });
    }

    @Test
    public void test_patternElementUpdate() {
        TPSRoot tpsRoot = TPSFactory.eINSTANCE.createTPSRoot();

        TPSPatternElement tpsPatternElement_1 = TPSFactory.eINSTANCE.createTPSPatternElement();
        tpsPatternElement_1.setName("importance");
        tpsPatternElement_1.setValue("sum(weight)");

        TPSPatternElement tpsPatternElement_2 = TPSFactory.eINSTANCE.createTPSPatternElement();
        tpsPatternElement_2.setName("risk");
        tpsPatternElement_2.setValue("sum(weight) * sum(risk)");

        tpsRoot.getPatternElements().add(tpsPatternElement_1);
        tpsRoot.getPatternElements().add(tpsPatternElement_2);

        TPSetting tpSetting = TPFactory.eINSTANCE.createTPSetting();

        nodeUtil.patternElementUpdate(tpsRoot, tpSetting);

        assertEquals(tpSetting.getPatternElements().size(), 2);
        assertEquals(true, tpSetting.getPatternElements().get(0).isChecked());
        assertEquals(tpsPatternElement_1.getName(), tpSetting.getPatternElements().get(0).getName());
        assertEquals(tpsPatternElement_1.getValue(), tpSetting.getPatternElements().get(0).getValue());
        assertEquals(true, tpSetting.getPatternElements().get(1).isChecked());
        assertEquals(tpsPatternElement_2.getName(), tpSetting.getPatternElements().get(1).getName());
        assertEquals(tpsPatternElement_2.getValue(), tpSetting.getPatternElements().get(1).getValue());
    }
}
