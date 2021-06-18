package com.zipc.garden.job.tp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.fmcs.FMCSAndExpression;
import com.zipc.garden.model.fmcs.FMCSConstraint;
import com.zipc.garden.model.fmcs.FMCSFactory;
import com.zipc.garden.model.fmcs.FMCSImpliesExpression;
import com.zipc.garden.model.fmcs.FMCSMutexExpression;
import com.zipc.garden.model.fmcs.FMCSNotExpression;
import com.zipc.garden.model.fmcs.FMCSODElement;
import com.zipc.garden.model.fmcs.FMCSOrExpression;
import com.zipc.garden.model.fmcs.FMCSRemovesExpression;
import com.zipc.garden.model.fmcs.FMCSRoot;
import com.zipc.garden.model.fmcs.FMCSSelectExpression;
import com.zipc.garden.model.tc.TCFactory;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.webplatform.shared.NodeUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TCCardinalityExpanderTest {

    TCCardinalityExpander cardinalityExpander = new TCCardinalityExpander();

    @Before
    public void printBefore() {
        System.out.println("------");
    }

    @After
    public void printAfter() {
        System.out.println("------");

    }

    private TCNode createTCNode(TCNode parent, String name) {
        TCNode node = TCFactory.eINSTANCE.createTCNode();
        node.setName(name);
        parent.getChildren().add(node);
        return node;
    }

    @Test
    public void Test01_TCRoot_InputCheck() {
        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();
        FMCSRoot fmcsRoot = FMCSFactory.eINSTANCE.createFMCSRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        TCNode nodeA = createTCNode(rootNode, "A");
        nodeA.setChildType(ChildType.XOR);
        TCNode nodeB = createTCNode(rootNode, "B");

        TCNode nodeA1 = createTCNode(nodeA, "A1");
        TCNode nodeA2 = createTCNode(nodeA, "A2");
        TCNode nodeA3 = createTCNode(nodeA, "A3");

        TCNode nodeB1 = createTCNode(nodeB, "B1");
        TCNode nodeB2 = createTCNode(nodeB, "B2");
        TCNode nodeB3 = createTCNode(nodeB, "B3");

        // minが-1の場合はcardinality未対応ノードと判断する。
        nodeA.setMin(-1);
        nodeA.setMax(-1);
        assertEquals(cardinalityExpander.execute(EcoreUtil.copy(tcRoot), fmcsRoot), true);

        // minが0の場合はモデル不正
        nodeA.setMin(0);
        nodeA.setMax(-1);
        assertEquals(cardinalityExpander.execute(EcoreUtil.copy(tcRoot), fmcsRoot), false);

        // minがchildren超過の場合はモデル不正
        nodeA.setMin(nodeA.getChildren().size() + 1);
        nodeA.setMax(-1);
        assertEquals(cardinalityExpander.execute(EcoreUtil.copy(tcRoot), fmcsRoot), false);

        // maxが0の場合はモデル不正
        nodeA.setMin(1);
        nodeA.setMax(0);
        assertEquals(cardinalityExpander.execute(EcoreUtil.copy(tcRoot), fmcsRoot), false);

        // maxがchildren超過の場合はモデル不正
        nodeA.setMin(1);
        nodeA.setMax(4);
        assertEquals(cardinalityExpander.execute(EcoreUtil.copy(tcRoot), fmcsRoot), false);
    }

    @Test
    public void Test02_TCRoot_WhenCardinalityMaxUseChildrenNumber() {

        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();
        FMCSRoot fmcsRoot = FMCSFactory.eINSTANCE.createFMCSRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        TCNode nodeA = createTCNode(rootNode, "A");
        nodeA.setChildType(ChildType.XOR);
        TCNode nodeB = createTCNode(rootNode, "B");

        TCNode nodeA1 = createTCNode(nodeA, "A1");
        TCNode nodeA2 = createTCNode(nodeA, "A2");
        TCNode nodeA3 = createTCNode(nodeA, "A3");

        TCNode nodeB1 = createTCNode(nodeB, "B1");
        TCNode nodeB2 = createTCNode(nodeB, "B2");
        TCNode nodeB3 = createTCNode(nodeB, "B3");

        // maxが-1の場合はchildren数として扱う(FMEditorではアスタリスク"*"として表示)。
        nodeA.setMin(1);
        nodeA.setMax(-1);
        cardinalityExpander.execute(tcRoot, fmcsRoot);
        assertEquals(nodeA.getChildren().size() == 7, true);
    }

    @Test
    public void Test03_TCRoot_MinLessThanMax() {

        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();
        FMCSRoot fmcsRoot = FMCSFactory.eINSTANCE.createFMCSRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        TCNode nodeA = createTCNode(rootNode, "A");
        nodeA.setChildType(ChildType.XOR);
        TCNode nodeB = createTCNode(rootNode, "B");

        TCNode nodeA1 = createTCNode(nodeA, "A1");
        TCNode nodeA2 = createTCNode(nodeA, "A2");
        TCNode nodeA3 = createTCNode(nodeA, "A3");

        TCNode nodeB1 = createTCNode(nodeB, "B1");
        TCNode nodeB2 = createTCNode(nodeB, "B2");
        TCNode nodeB3 = createTCNode(nodeB, "B3");

        // min>max
        nodeA.setMin(2);
        nodeA.setMax(1);
        cardinalityExpander.execute(tcRoot, fmcsRoot);
        assertEquals(nodeA.getChildren().stream().filter(TCNode::isTemporary).collect(Collectors.toList()).size() == 0, true);
    }

    @Test
    public void Test04_TCRoot_MinEqualsMax() {

        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();
        FMCSRoot fmcsRoot = FMCSFactory.eINSTANCE.createFMCSRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        TCNode nodeA = createTCNode(rootNode, "A");
        nodeA.setChildType(ChildType.XOR);
        nodeA.setMin(1);
        nodeA.setMax(1);
        TCNode nodeB = createTCNode(rootNode, "B");

        TCNode nodeA1 = createTCNode(nodeA, "A1");
        TCNode nodeA2 = createTCNode(nodeA, "A2");
        TCNode nodeA3 = createTCNode(nodeA, "A3");

        TCNode nodeB1 = createTCNode(nodeB, "B1");
        TCNode nodeB2 = createTCNode(nodeB, "B2");
        TCNode nodeB3 = createTCNode(nodeB, "B3");

        cardinalityExpander.execute(tcRoot, fmcsRoot);
        assertEquals(nodeA.getChildren().stream().filter(TCNode::isTemporary).collect(Collectors.toList()).size() == 3, true);
    }

    @Test
    public void Test05_TCRoot_MultipleLayers() {

        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();
        FMCSRoot fmcsRoot = FMCSFactory.eINSTANCE.createFMCSRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        TCNode nodeA = createTCNode(rootNode, "A");
        nodeA.setChildType(ChildType.XOR);
        nodeA.setMin(1);
        nodeA.setMax(2);
        TCNode nodeB = createTCNode(rootNode, "B");
        nodeB.setChildType(ChildType.XOR);

        TCNode nodeA1 = createTCNode(nodeA, "A1");
        TCNode nodeA2 = createTCNode(nodeA, "A2");

        TCNode nodeB1 = createTCNode(nodeB, "B1");
        TCNode nodeB2 = createTCNode(nodeB, "B2");

        TCNode nodeA11 = createTCNode(nodeA1, "A11");
        nodeA11.setChildType(ChildType.XOR);
        nodeA11.setMin(1);
        nodeA11.setMax(2);
        TCNode nodeA12 = createTCNode(nodeA1, "A12");
        nodeA12.setChildType(ChildType.XOR);

        TCNode nodeA111 = createTCNode(nodeA11, "A111");
        nodeA111.setChildType(ChildType.XOR);
        TCNode nodeA112 = createTCNode(nodeA11, "A112");

        TCNode nodeA121 = createTCNode(nodeA12, "A121");
        TCNode nodeA122 = createTCNode(nodeA12, "A122");

        TCNode nodeA1111 = createTCNode(nodeA111, "A1111");
        TCNode nodeA1112 = createTCNode(nodeA111, "A1112");

        // 多重層
        cardinalityExpander.execute(tcRoot, fmcsRoot);

        List<TCNode> tcNodes = TCUtil.getAllContentsOfType(tcRoot, TCNode.class);
        tcNodes.forEach(tcNode -> {
            switch (tcNode.getName()) {
            case "1_1":
            case "1_2":
            case "2_1":
            case "1":
            case "2":
            case "OFF":
                assertEquals(tcNode.isTemporary(), true);
                break;
            default:
                assertEquals(tcNode.isTemporary(), false);
                break;
            }
        });

        List<TCNode> endNodes = tcNodes.stream().filter(tcNode -> tcNode.getChildren().isEmpty()).collect(Collectors.toList());

        List<String> fullPaths = new ArrayList<String>();
        fullPaths.add("Root.A.1_1.1.A1.A11.1_1.1.A111.A1111");
        fullPaths.add("Root.A.1_1.1.A1.A11.1_1.1.A111.A1112");
        fullPaths.add("Root.A.1_1.1.A1.A11.1_2.1.A112");
        fullPaths.add("Root.A.1_1.1.A1.A11.1_2.1.OFF");
        fullPaths.add("Root.A.1_1.1.A1.A11.2_1.1.A111.A1111");
        fullPaths.add("Root.A.1_1.1.A1.A11.2_1.1.A111.A1112");
        fullPaths.add("Root.A.1_1.1.A1.A11.2_1.2.A112");
        fullPaths.add("Root.A.1_1.1.A1.A11.2_1.2.OFF");
        fullPaths.add("Root.A.1_1.1.A1.A12.A121");
        fullPaths.add("Root.A.1_1.1.A1.A12.A122");
        fullPaths.add("Root.A.1_2.1.A2");
        fullPaths.add("Root.A.1_2.1.OFF");
        fullPaths.add("Root.A.2_1.1.A1.A11.1_1.1.A111.A1111");
        fullPaths.add("Root.A.2_1.1.A1.A11.1_1.1.A111.A1112");
        fullPaths.add("Root.A.2_1.1.A1.A11.1_2.1.A112");
        fullPaths.add("Root.A.2_1.1.A1.A11.1_2.1.OFF");
        fullPaths.add("Root.A.2_1.1.A1.A11.2_1.1.A111.A1111");
        fullPaths.add("Root.A.2_1.1.A1.A11.2_1.1.A111.A1112");
        fullPaths.add("Root.A.2_1.1.A1.A11.2_1.2.A112");
        fullPaths.add("Root.A.2_1.1.A1.A11.2_1.2.OFF");
        fullPaths.add("Root.A.2_1.1.A1.A12.A121");
        fullPaths.add("Root.A.2_1.1.A1.A12.A122");
        fullPaths.add("Root.A.2_1.2.A2");
        fullPaths.add("Root.A.2_1.2.OFF");
        fullPaths.add("Root.B.B1");
        fullPaths.add("Root.B.B2");

        AtomicInteger i = new AtomicInteger(0);
        endNodes.forEach(tcNode -> {
            TCNode parentNode = (TCNode) tcNode.eContainer();
            assertEquals(ChildType.XOR.equals(parentNode.getChildType()), true);
            assertEquals(fullPaths.get(i.getAndIncrement()).equals(NodeUtil.getInstance().getTCNodeFullPath(tcNode, false)), true);
        });
    }

    @Test
    public void Test06_FMCSRoot_InputCheck() {
        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        TCNode nodeA = createTCNode(rootNode, "A");
        TCNode nodeB = createTCNode(rootNode, "B");

        TCNode nodeA1 = createTCNode(nodeA, "A1");
        TCNode nodeA2 = createTCNode(nodeA, "A2");
        TCNode nodeA3 = createTCNode(nodeA, "A3");

        TCNode nodeB1 = createTCNode(nodeB, "B1");
        TCNode nodeB2 = createTCNode(nodeB, "B2");
        TCNode nodeB3 = createTCNode(nodeB, "B3");

        FMCSODElement odElement = FMCSFactory.eINSTANCE.createFMCSODElement();
        odElement.setFullName("Root.A.A1");

        FMCSODElement odElement2 = FMCSFactory.eINSTANCE.createFMCSODElement();
        odElement2.setFullName("Root.B.B1");

        FMCSMutexExpression mutex = FMCSFactory.eINSTANCE.createFMCSMutexExpression();
        mutex.getOdElements().add(odElement);
        mutex.getOdElements().add(odElement2);

        FMCSConstraint constraint = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint.setExpression(mutex);

        FMCSRoot fmcsRoot = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcsRoot.getConstraints().add(constraint);

        assertEquals(cardinalityExpander.execute(tcRoot, FMCSFactory.eINSTANCE.createFMCSRoot()), true);
        assertEquals(cardinalityExpander.execute(tcRoot, null), true);
        assertEquals(cardinalityExpander.execute(TCFactory.eINSTANCE.createTCRoot(), fmcsRoot), false);
        assertEquals(cardinalityExpander.execute(tcRoot, fmcsRoot), true);
    }

    @Test
    public void Test07_FMCSRoot_Select() {
        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        TCNode nodeA = createTCNode(rootNode, "A");
        nodeA.setChildType(ChildType.XOR);
        nodeA.setMin(1);
        nodeA.setMax(2);
        TCNode nodeB = createTCNode(rootNode, "B");

        TCNode nodeA1 = createTCNode(nodeA, "A1");
        TCNode nodeA2 = createTCNode(nodeA, "A2");
        TCNode nodeA3 = createTCNode(nodeA, "A3");

        TCNode nodeB1 = createTCNode(nodeB, "B1");
        TCNode nodeB2 = createTCNode(nodeB, "B2");
        TCNode nodeB3 = createTCNode(nodeB, "B3");

        FMCSODElement odElement = FMCSFactory.eINSTANCE.createFMCSODElement();
        odElement.setFullName("Root.A.A1");

        FMCSODElement odElement2 = FMCSFactory.eINSTANCE.createFMCSODElement();
        odElement2.setFullName("Root.B.B1");

        FMCSSelectExpression select1 = FMCSFactory.eINSTANCE.createFMCSSelectExpression();
        select1.setOdElement(odElement);

        FMCSSelectExpression select2 = FMCSFactory.eINSTANCE.createFMCSSelectExpression();
        select2.setOdElement(odElement2);

        FMCSImpliesExpression implies = FMCSFactory.eINSTANCE.createFMCSImpliesExpression();
        implies.setLeftExpression(select1);
        implies.setRightExpression(select2);

        FMCSConstraint constraint = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint.setExpression(implies);

        FMCSRoot fmcsRoot = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcsRoot.getConstraints().add(constraint);

        cardinalityExpander.execute(tcRoot, fmcsRoot);

        cardinalityExpander.getExpandFMCSRoot().getConstraints().forEach(cns -> {
            if (cns.getExpression() instanceof FMCSImpliesExpression) {
                FMCSImpliesExpression implies2 = (FMCSImpliesExpression) cns.getExpression();
                FMCSOrExpression leftOr = (FMCSOrExpression) implies2.getLeftExpression();
                FMCSSelectExpression rightSelect = (FMCSSelectExpression) implies2.getRightExpression();

                List<String> fullNames = new ArrayList<String>();
                fullNames.add("Root.A.1_1.1.A1");
                fullNames.add("Root.A.2_1.1.A1");
                fullNames.add("Root.A.2_2.1.A1");

                AtomicInteger i = new AtomicInteger(0);
                leftOr.getExpressions().forEach(sel -> {
                    FMCSSelectExpression select3 = (FMCSSelectExpression) sel;
                    assertEquals(fullNames.get(i.getAndIncrement()).equals(select3.getOdElement().getFullName()), true);
                });
                assertEquals("Root.B.B1".equals(rightSelect.getOdElement().getFullName()), true);

            } else {
                FMCSNotExpression not = (FMCSNotExpression) cns.getExpression();
                FMCSOrExpression or = (FMCSOrExpression) not.getExpression();

                List<String> fullNames = new ArrayList<String>();
                fullNames.add("Root.A.1_1.1.OFF");
                fullNames.add("Root.A.1_2.1.OFF");
                fullNames.add("Root.A.1_3.1.OFF");
                fullNames.add("Root.A.2_1.1.OFF");
                fullNames.add("Root.A.2_1.2.OFF");
                fullNames.add("Root.A.2_2.1.OFF");
                fullNames.add("Root.A.2_2.2.OFF");
                fullNames.add("Root.A.2_3.1.OFF");
                fullNames.add("Root.A.2_3.2.OFF");

                AtomicInteger i = new AtomicInteger(0);
                or.getExpressions().forEach(exp -> {
                    FMCSSelectExpression select = (FMCSSelectExpression) exp;
                    assertEquals(fullNames.get(i.getAndIncrement()).equals(select.getOdElement().getFullName()), true);
                });
            }
        });
    }

    @Test
    public void Test07_FMCSRoot_Select2() {
        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        TCNode nodeA = createTCNode(rootNode, "A");
        nodeA.setChildType(ChildType.XOR);
        nodeA.setMin(1);
        nodeA.setMax(2);
        TCNode nodeB = createTCNode(rootNode, "B");

        TCNode nodeA1 = createTCNode(nodeA, "A1");
        TCNode nodeA2 = createTCNode(nodeA, "A2");
        TCNode nodeA3 = createTCNode(nodeA, "A3");

        TCNode nodeB1 = createTCNode(nodeB, "B1");
        TCNode nodeB2 = createTCNode(nodeB, "B2");
        TCNode nodeB3 = createTCNode(nodeB, "B3");

        FMCSODElement odElement = FMCSFactory.eINSTANCE.createFMCSODElement();
        odElement.setFullName("Root.B.B1");

        FMCSODElement odElement2 = FMCSFactory.eINSTANCE.createFMCSODElement();
        odElement2.setFullName("Root.A.A1");

        FMCSSelectExpression select1 = FMCSFactory.eINSTANCE.createFMCSSelectExpression();
        select1.setOdElement(odElement);

        FMCSSelectExpression select2 = FMCSFactory.eINSTANCE.createFMCSSelectExpression();
        select2.setOdElement(odElement2);

        FMCSImpliesExpression implies = FMCSFactory.eINSTANCE.createFMCSImpliesExpression();
        implies.setLeftExpression(select1);
        implies.setRightExpression(select2);

        FMCSConstraint constraint = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint.setExpression(implies);

        FMCSRoot fmcsRoot = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcsRoot.getConstraints().add(constraint);

        cardinalityExpander.execute(tcRoot, fmcsRoot);

        cardinalityExpander.getExpandFMCSRoot().getConstraints().forEach(cns -> {
            if (cns.getExpression() instanceof FMCSImpliesExpression) {
                FMCSImpliesExpression implies2 = (FMCSImpliesExpression) cns.getExpression();
                FMCSOrExpression rightOr = (FMCSOrExpression) implies2.getRightExpression();
                FMCSSelectExpression leftSelect = (FMCSSelectExpression) implies2.getLeftExpression();

                List<String> fullNames = new ArrayList<String>();
                fullNames.add("Root.A.1_1.1.A1");
                fullNames.add("Root.A.2_1.1.A1");
                fullNames.add("Root.A.2_2.1.A1");

                AtomicInteger i = new AtomicInteger(0);
                rightOr.getExpressions().forEach(sel -> {
                    FMCSSelectExpression select3 = (FMCSSelectExpression) sel;
                    assertEquals(fullNames.get(i.getAndIncrement()).equals(select3.getOdElement().getFullName()), true);
                });
                assertEquals("Root.B.B1".equals(leftSelect.getOdElement().getFullName()), true);
            }
        });
    }

    @Test
    public void Test08_FMCSRoot_Removes() {
        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        TCNode nodeA = createTCNode(rootNode, "A");
        nodeA.setChildType(ChildType.XOR);
        nodeA.setMin(1);
        nodeA.setMax(2);
        TCNode nodeB = createTCNode(rootNode, "B");

        TCNode nodeA1 = createTCNode(nodeA, "A1");
        TCNode nodeA2 = createTCNode(nodeA, "A2");
        TCNode nodeA3 = createTCNode(nodeA, "A3");

        TCNode nodeB1 = createTCNode(nodeB, "B1");
        TCNode nodeB2 = createTCNode(nodeB, "B2");
        TCNode nodeB3 = createTCNode(nodeB, "B3");

        FMCSODElement odElement = FMCSFactory.eINSTANCE.createFMCSODElement();
        odElement.setFullName("Root.A.A1");

        FMCSODElement odElement2 = FMCSFactory.eINSTANCE.createFMCSODElement();
        odElement2.setFullName("Root.B");

        FMCSSelectExpression select = FMCSFactory.eINSTANCE.createFMCSSelectExpression();
        select.setOdElement(odElement);

        FMCSRemovesExpression removes = FMCSFactory.eINSTANCE.createFMCSRemovesExpression();
        removes.setExpression(select);
        removes.setOdElement(odElement2);

        FMCSConstraint constraint = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint.setExpression(removes);

        FMCSRoot fmcsRoot = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcsRoot.getConstraints().add(constraint);

        cardinalityExpander.execute(tcRoot, fmcsRoot);

        cardinalityExpander.getExpandFMCSRoot().getConstraints().forEach(cns -> {
            if (cns.getExpression() instanceof FMCSRemovesExpression) {
                FMCSRemovesExpression removes2 = (FMCSRemovesExpression) cns.getExpression();
                FMCSOrExpression or = (FMCSOrExpression) removes.getExpression();

                List<String> fullNames = new ArrayList<String>();
                fullNames.add("Root.A.1_1.1.A1");
                fullNames.add("Root.A.2_1.1.A1");
                fullNames.add("Root.A.2_2.1.A1");

                AtomicInteger i = new AtomicInteger(0);
                or.getExpressions().forEach(sel -> {
                    FMCSSelectExpression select2 = (FMCSSelectExpression) sel;
                    assertEquals(fullNames.get(i.getAndIncrement()).equals(select2.getOdElement().getFullName()), true);
                });
                assertEquals("Root.B".equals(removes2.getOdElement().getFullName()), true);
            }
        });
    }

    @Test
    public void Test09_FMCSRoot_Removes_MultipleLayers() {
        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);
        TCNode nodeA = createTCNode(rootNode, "A");
        nodeA.setChildType(ChildType.XOR);
        nodeA.setMin(1);
        nodeA.setMax(2);
        TCNode nodeB = createTCNode(rootNode, "B");
        nodeB.setChildType(ChildType.XOR);
        TCNode nodeA1 = createTCNode(nodeA, "A1");
        TCNode nodeA2 = createTCNode(nodeA, "A2");
        TCNode nodeB1 = createTCNode(nodeB, "B1");
        TCNode nodeB2 = createTCNode(nodeB, "B2");
        TCNode nodeA11 = createTCNode(nodeA1, "A11");
        nodeA11.setChildType(ChildType.XOR);
        nodeA11.setMin(1);
        nodeA11.setMax(2);
        TCNode nodeA12 = createTCNode(nodeA1, "A12");
        nodeA12.setChildType(ChildType.XOR);
        TCNode nodeA111 = createTCNode(nodeA11, "A111");
        TCNode nodeA112 = createTCNode(nodeA11, "A112");
        TCNode nodeA121 = createTCNode(nodeA12, "A121");
        TCNode nodeA122 = createTCNode(nodeA12, "A122");

        FMCSODElement odElement = FMCSFactory.eINSTANCE.createFMCSODElement();
        odElement.setFullName("Root.B.B2");

        FMCSODElement odElement2 = FMCSFactory.eINSTANCE.createFMCSODElement();
        odElement2.setFullName("Root.A.A1.A11.A111");

        FMCSSelectExpression select = FMCSFactory.eINSTANCE.createFMCSSelectExpression();
        select.setOdElement(odElement);

        FMCSRemovesExpression removes = FMCSFactory.eINSTANCE.createFMCSRemovesExpression();
        removes.setExpression(select);
        removes.setOdElement(odElement2);

        FMCSConstraint constraint = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint.setExpression(removes);

        FMCSRoot fmcsRoot = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcsRoot.getConstraints().add(constraint);

        cardinalityExpander.execute(tcRoot, fmcsRoot);

        cardinalityExpander.getExpandFMCSRoot().getConstraints().forEach(cns -> {
            if (cns.getExpression() instanceof FMCSAndExpression) {
                FMCSAndExpression and = (FMCSAndExpression) cns.getExpression();
                AtomicInteger i = new AtomicInteger(0);
                List<String> fullNames = new ArrayList<String>();
                fullNames.add("Root.A.1_1.1.A1.A11.1_1.1.A111");
                fullNames.add("Root.A.1_1.1.A1.A11.2_1.1.A111");
                fullNames.add("Root.A.2_1.1.A1.A11.1_1.1.A111");
                fullNames.add("Root.A.2_1.1.A1.A11.2_1.1.A111");
                and.getExpressions().forEach(exp -> {
                    FMCSRemovesExpression removes2 = (FMCSRemovesExpression) exp;
                    FMCSSelectExpression select2 = (FMCSSelectExpression) removes2.getExpression();
                    assertEquals("Root.B.B2".equals(select2.getOdElement().getFullName()), true);
                    assertEquals(fullNames.get(i.getAndIncrement()).equals(removes2.getOdElement().getFullName()), true);
                });
            }
        });
    }

    @Test
    public void Test10_FMCSRoot_Mutex() {
        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        TCNode nodeA = createTCNode(rootNode, "A");
        nodeA.setChildType(ChildType.XOR);
        nodeA.setMin(1);
        nodeA.setMax(2);
        TCNode nodeB = createTCNode(rootNode, "B");

        TCNode nodeA1 = createTCNode(nodeA, "A1");
        TCNode nodeA2 = createTCNode(nodeA, "A2");
        TCNode nodeA3 = createTCNode(nodeA, "A3");

        TCNode nodeB1 = createTCNode(nodeB, "B1");
        TCNode nodeB2 = createTCNode(nodeB, "B2");
        TCNode nodeB3 = createTCNode(nodeB, "B3");

        FMCSODElement odElement = FMCSFactory.eINSTANCE.createFMCSODElement();
        odElement.setFullName("Root.A.A1");

        FMCSODElement odElement2 = FMCSFactory.eINSTANCE.createFMCSODElement();
        odElement2.setFullName("Root.B.B1");

        FMCSMutexExpression mutex = FMCSFactory.eINSTANCE.createFMCSMutexExpression();
        mutex.getOdElements().add(odElement);
        mutex.getOdElements().add(odElement2);

        FMCSConstraint constraint = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint.setExpression(mutex);

        FMCSRoot fmcsRoot = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcsRoot.getConstraints().add(constraint);

        cardinalityExpander.execute(tcRoot, fmcsRoot);

        cardinalityExpander.getExpandFMCSRoot().getConstraints().forEach(cns -> {
            if (cns.getExpression() instanceof FMCSMutexExpression) {
                FMCSMutexExpression mutex2 = (FMCSMutexExpression) cns.getExpression();
                List<String> fullNames = new ArrayList<String>();
                fullNames.add("Root.A.1_1.1.A1");
                fullNames.add("Root.A.2_1.1.A1");
                fullNames.add("Root.A.2_2.1.A1");
                fullNames.add("Root.B.B1");

                AtomicInteger i = new AtomicInteger(0);
                mutex2.getOdElements().forEach(od -> {
                    assertEquals(fullNames.get(i.getAndIncrement()).equals(od.getFullName()), true);
                });
            }
        });
    }

    @Test
    public void Test11_FMCSRoot_Optional() {
        TCRoot tcRoot = TCFactory.eINSTANCE.createTCRoot();

        TCNode rootNode = TCFactory.eINSTANCE.createTCNode();
        rootNode.setName("Root");
        tcRoot.getRootNodes().add(rootNode);

        TCNode normal = createTCNode(rootNode, "Normal");
        normal.setChildType(ChildType.XOR);
        normal.setMin(1);
        normal.setMax(2);
        normal.setTemporary(true);

        TCNode optional = createTCNode(rootNode, "Optional");
        optional.setChildType(ChildType.XOR);
        optional.setTemporary(true);

        TCNode nodeA = createTCNode(normal, "A");
        TCNode nodeB = createTCNode(normal, "B");
        TCNode nodeC = createTCNode(optional, "C");
        TCNode off = createTCNode(optional, "OFF");
        off.setTemporary(true);

        TCNode nodeA1 = createTCNode(nodeA, "A1");
        TCNode nodeA2 = createTCNode(nodeA, "A2");

        TCNode nodeB1 = createTCNode(nodeB, "B1");
        TCNode nodeB2 = createTCNode(nodeB, "B2");

        TCNode nodeC1 = createTCNode(nodeC, "C1");
        TCNode nodeC2 = createTCNode(nodeC, "C2");

        FMCSODElement odElement = FMCSFactory.eINSTANCE.createFMCSODElement();
        odElement.setFullName("Root.Normal.A.A1");

        FMCSODElement odElement2 = FMCSFactory.eINSTANCE.createFMCSODElement();
        odElement2.setFullName("Root.B.B1");

        FMCSMutexExpression mutex = FMCSFactory.eINSTANCE.createFMCSMutexExpression();
        mutex.getOdElements().add(odElement);
        mutex.getOdElements().add(odElement2);

        FMCSConstraint constraint = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint.setExpression(mutex);

        FMCSRoot fmcsRoot = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcsRoot.getConstraints().add(constraint);

        cardinalityExpander.execute(tcRoot, fmcsRoot);

        cardinalityExpander.getExpandFMCSRoot().getConstraints().forEach(cns -> {
            if (cns.getExpression() instanceof FMCSMutexExpression) {
                FMCSMutexExpression mutex2 = (FMCSMutexExpression) cns.getExpression();
                List<String> fullNames = new ArrayList<String>();
                fullNames.add("Root.Normal.1_1.1.A.A1");
                fullNames.add("Root.Normal.2_1.1.A.A1");
                fullNames.add("Root.B.B1");

                AtomicInteger i = new AtomicInteger(0);
                mutex2.getOdElements().forEach(od -> {
                    assertEquals(fullNames.get(i.getAndIncrement()).equals(od.getFullName()), true);
                });
            }
        });
    }
}
