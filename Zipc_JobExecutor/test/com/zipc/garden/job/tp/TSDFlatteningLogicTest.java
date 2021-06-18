package com.zipc.garden.job.tp;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

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

/**
 * Method to create test model has tree image in the javadoc.
 * <p>
 * Legend:
 * <ol>
 * <li>")-" means XOR.</li>
 * <li>"--" means AND.</li>
 * </ol>
 * <ol>
 * <li>"-*" means required node.</li>
 * <li>"-o" means optional node.</li>
 * </ol>
 * 
 * <pre>
 * A --* A1 )-* A11
 *    |      -o A12
 *    |
 *    -* A2 --* A21
 *           -* A22
 * </pre>
 * 
 * A has children(A1, A2) with AND.<br/>
 * A1 has children(A11, A12) with XOR.<br/>
 * A2 has children(A21, A22) with AND.<br/>
 * A12 is optional. Others are required.
 * </p>
 */

@RunWith(Enclosed.class)
public class TSDFlatteningLogicTest {

    public static class ForActs {
        @Test
        public void test01_deleteMiddleNodesConectedXORRelation() throws IOException {
            // create test case
            treeWithMiddleNodeConectedXorRelation();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test01_deleteMiddleNodesConectedXorRelation.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test02_deleteLeafNodesConecteAndRelation() throws IOException {
            // create test case
            treeWithLeafNodeConnectedANDRelation();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test02_deleteLeafNodesConecteAndRelation.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test03_deleteMiddleNodesConectedAndRelation() throws IOException {
            // create test case
            treeWithMiddleNodeConectedAndRelationUnderRootnode();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test03_deleteMiddleNodesConectedAndRelation.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test04_deleteMiddleNodesConectedXorAndRelation() throws IOException {
            // create test case
            treeWithMiddleNodeConectedXorAndRelation();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test04_deleteMiddleNodesConectedXorAndRelation.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test05_deleteMiddleNodesConectedXorAndXorRelation() throws IOException {
            // create test case
            treeWithMiddleNodeConnectedOneXorAndXorRelation();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test05_deleteMiddleNodesConectedXorAndXorRelation.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test06_addOneDummyNodeAndLiftOneNodeToRootNode() throws IOException {
            // create test case
            treeWithMiddleNodeConnectedTwoXorAndXorRelationsHorizontally();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test06_addOneDummyNodeAndLiftOneNodeToRootNode.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test07_addTwoDummyNodeAndLiftTwoNodesToRootNode() throws IOException {
            // create test case
            treeWithMiddleNodeConnectedThreeXorAndXorRelationsHorizontally();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test07_addTwoDummyNodeAndLiftTwoNodesToRootNode.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test08_addDummyNodeAndLiftTwoNodesToRootNode() throws IOException {
            // create test case
            treeWithMiddleNodeConnectedMultipleXorAndXorRelationsVertically();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test08_addDummyNodeAndLiftTwoNodesToRootNode.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test09_addAndConstraint() throws IOException {
            // create test case
            treeWithAndConstraint();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test09_addAndConstraint.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test10_addAndConstraintAndaddDummyNodeAndLiftOneNodeToRootNode() throws IOException {
            // create test case
            treeWithAndConstraintAndMultipleXorAndXorRelationsVertically();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test10_addAndConstraintAndaddDummyNodeAndLiftOneNodeToRootNode.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test11_addMultipleAndConstraintsToLeafNode() throws IOException {
            // create test case
            treeWithMultipleAndConstraintsAmongLeafNodes();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test11_addMultipleAndConstraintsToLeafNode.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test12_addMultipleAndConstraintsToMiddleNodes() throws IOException {
            // create test case
            treeWithMultipleAndConstraintsAmongMiddleNodes();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test12_addMultipleAndConstraintsToMiddleNodes.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test13_addOrConstraint() throws IOException {
            // create test case
            treeWithOrConstraint();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test13_addOrConstraint.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test14_addNotConstraintToLeafNode() throws IOException {
            // create test case
            treeWithNotConstraintToLeafNode();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test14_addNotConstraintToLeafNode.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test15_addNotConstraintToLeafNodes() throws IOException {
            // create test case
            treeWithNotConstraintToMiddleNode();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test15_addNotConstraintToLeafNodes.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test16_addNotOrConstraint() throws IOException {
            // create test case
            treeWithSingleMutexConstraint();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test16_addNotOrConstraint.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test17_addTwoNotOrConstraintToLeafNodes() throws IOException {
            // create test case
            treeWithMultipleMutexConstraintsToleafNodes();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test17_addTwoNotOrConstraintToLeafNodes.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test18_addMultipleNotOrConstraintToRelatedLeafNodes() throws IOException {
            // create test case
            treeWithMultipleMutexConstraintsToMiddleNodes();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test18_addMultipleNotOrConstraintToRelatedLeafNodes.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test19_addAndOrConstraint() throws IOException {
            // create test case
            treeWithAndOrConstraint();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test19_addAndOrConstraint.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test20_addAndOrSingleNotConstraint() throws IOException {
            // create test case
            treeWithAndOrSingleNotConstraints();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test20_addAndOrSingleNotConstraint.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test21_addAndOrMultipleNotConstraint() throws IOException {
            // create test case
            treeWithAndOrMultipleNotConstraints();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test21_addAndOrMultipleNotConstraint.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test22_addAndOrNotConstraintAlongDeMorganLaw() throws IOException {
            // create test case
            treeWithMultipleOrNotConstraints();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test22_addAndOrNotConstraintAlongDeMorganLaw.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test23_addAndOrNotConstraintAlongMutexExpansion() throws IOException {
            // create test case
            treeWithAndNotMutexConstraints();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test23_addAndOrNotConstraintAlongMutexExpansion.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test24_addOrAndNotConstraintAlongMutexExpansion() throws IOException {
            // create test case
            treeWithOrNotMutexConstraints();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test24_addOrAndNotConstraintAlongMutexExpansion.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test25_addImpliesConstraintFromLeafToLeafNode() throws IOException {
            // create test case
            treeWithImpliesConstraintFromLeafNodeToLeafNode();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test25_addImpliesConstraintFromLeafToLeafNode.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test26_addImpliesConstraintFromLeafToMiddleNode() throws IOException {
            // create test case
            treeWithImpliesConstraintFromLeafNodeToMiddleNode();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test26_addImpliesConstraintFromLeafToMiddleNode.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test27_addImpliesConstraintFromMiddleToLeafNode() throws IOException {
            // create test case
            treeWithImpliesConstraintFromMiddleNodeToLeafNode();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test27_addImpliesConstraintFromMiddleToLeafNode.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test28_addImpliesConstraintFromMiddleToMiddleNode() throws IOException {
            // create test case
            treeWithImpliesConstraintFromMiddleNodeToMiddleNode();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test28_addImpliesConstraintFromMiddleToMiddleNode.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test29_addImpliesConstraintAlongImpliesTransformation() throws IOException {
            // create test case
            translateConstraintFromImpliesToNotOr();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test29_addImpliesConstraintAlongImpliesTransformation.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test30_addImpliesConstraintAlongImpliesTransformationAndFormulaExpansions() throws IOException {
            // create test case
            treeWithImpliesConstraintTranslateExpression();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test30_addImpliesConstraintAlongImpliesTransformationAndFormulaExpansions.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test31_addRemovesConstraintFromNodeToNodeUnderExpansion() throws IOException {
            // create test case
            treeWithRemovesConstraintFromNodeToNode();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test31_addRemovesConstraintFromNodeToNodeUnderExpansion.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test32_addRemovesConstraintFromLogicalExpressionToNodeUnderExpansion() throws IOException {
            // create test case
            treeWithRemovesConstraintFromLogicalExpressionFormulaToNode();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test32_addRemovesConstraintFromLogicalExpressionToNodeUnderExpansion.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test33_deleteMiddleNodeWithSerialConnectionWithoutBranch() throws IOException {
            // create test case
            treeWithSerialConnectionWithoutBranch();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test33_deleteMiddleNodeWithSerialConnectionWithoutBranch.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test34_deleteMiddleNodeWithSerialConnectionAndBranch() throws IOException {
            // create test case
            treeWithSerialConnectionAndBranch();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test34_deleteMiddleNodeWithSerialConnectionAndBranch.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void test35_deleteMiddleNodeWithSerialConnectionAndBranchAndAddDummynode() throws IOException {
            // create test case
            treeWithSerialConnectionWithXorAndXorRelation();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = readFile(testResourceFolder() + "ForActs_test35_deleteMiddleNodeWithSerialConnectionAndBranchAndAddDummynode.txt");

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }

        @Test
        public void flatteningUnexcutableByNumberOfLayer() throws IOException {
            // create test case
            treeWithUnsatisfiedLayer();

            // execute method
            TSDFlatteningLogic testCase = new TSDFlatteningLogic();
            TSDFlatteningResult result = testCase.execute(odRoot, fmcs, actsMode);

            // expected
            final String expected = null;

            // actual
            final String actual = result.actsinfo;

            // judgement
            assertEquals(expected, actual);
        }
    }

    private static TCRoot odRoot;

    private static FMCSRoot fmcs;

    private static final int actsMode = 0;

    private static final int pictMode = 1;

    @Before
    public void printBefore() {
        System.out.println("------");
    }

    @After
    public void printAfter() {
        System.out.println("------");

    }

    private static TCNode createOdNode(String str) {
        TCNode odNode = TCFactory.eINSTANCE.createTCNode();
        odNode.setName(str);
        return odNode;
    }

    private static FMCSSelectExpression createSelectExpression(String str) {
        FMCSODElement e = createOdElement(str);
        FMCSSelectExpression s = FMCSFactory.eINSTANCE.createFMCSSelectExpression();
        s.setOdElement(e);
        return s;
    }

    private static FMCSODElement createOdElement(String str) {
        FMCSODElement e = FMCSFactory.eINSTANCE.createFMCSODElement();
        e.setFullName(str);
        return e;
    }

    private static final String testResourceFolder() {
        final String testResourcePath = "test/resources";
        final String packagePath = TSDFlatteningLogicTest.class.getPackage().getName().replaceAll("\\.", "/");
        return testResourcePath + "/" + packagePath + "/";
    }

    public static final String readFile(String filePath) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append(System.getProperty("line.separator"));
            }
        }
        return fileContent.toString();
    }

    /**
     * 親および子ノードとXORの関係がある中間ノードを持つ木
     * 
     * <pre>
     * A --* A1 )-* A11 )-* A111
     *    |      |       -* A112
     *    |      |
     *    |      -* A12 )-* A121
     *    |              -* A122
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithMiddleNodeConectedXorRelation() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.XOR);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode12.setChildType(ChildType.XOR);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        fmcs = null;
    }

    /**
     * 親および子ノードとANDの関係がある中間ノードを持つ木
     * 
     * <pre>
     * A --* A1 )-* A11 --* A111
     *    |      |       -* A112
     *    |      |
     *    |      -* A12 )-* A121
     *    |              -* A122
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithLeafNodeConnectedANDRelation() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = TCFactory.eINSTANCE.createTCNode();
        odNodeRoot.setName("A");
        TCNode odNode1 = TCFactory.eINSTANCE.createTCNode();
        odNode1.setName("A1");
        TCNode odNode11 = TCFactory.eINSTANCE.createTCNode();
        odNode11.setName("A11");
        TCNode odNode111 = TCFactory.eINSTANCE.createTCNode();
        odNode111.setName("A111");
        TCNode odNode112 = TCFactory.eINSTANCE.createTCNode();
        odNode112.setName("A112");
        TCNode odNode12 = TCFactory.eINSTANCE.createTCNode();
        odNode12.setName("A12");
        TCNode odNode121 = TCFactory.eINSTANCE.createTCNode();
        odNode121.setName("A121");
        TCNode odNode122 = TCFactory.eINSTANCE.createTCNode();
        odNode122.setName("A122");
        TCNode odNode2 = TCFactory.eINSTANCE.createTCNode();
        odNode2.setName("A2");
        TCNode odNode21 = TCFactory.eINSTANCE.createTCNode();
        odNode21.setName("A21");
        TCNode odNode211 = TCFactory.eINSTANCE.createTCNode();
        odNode211.setName("A211");
        TCNode odNode212 = TCFactory.eINSTANCE.createTCNode();
        odNode212.setName("A212");
        TCNode odNode22 = TCFactory.eINSTANCE.createTCNode();
        odNode22.setName("A22");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.XOR);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.AND);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode12.setChildType(ChildType.XOR);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        fmcs = null;
    }

    /**
     * ACTSおよびPICTのインプットとして層数が不足している木
     * 
     * <pre>
     * A --* A1
     *    -* A2
     * </pre>
     */
    private static void treeWithUnsatisfiedLayer() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = TCFactory.eINSTANCE.createTCNode();
        odNodeRoot.setName("A");
        TCNode odNode1 = TCFactory.eINSTANCE.createTCNode();
        TCNode odNode2 = TCFactory.eINSTANCE.createTCNode();
        odNode1.setName("A1");
        odNode2.setName("A2");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);

        // constraint
        fmcs = null;
    }

    /**
     * RootnodeとANDの関係および子ノードとANDの関係がある中間ノードを持つ木
     * 
     * <pre>
     * A --* A1 --* A11 )-* A111
     *    |      |       -* A112
     *    |      |
     *    |      -* A12 )-* A121
     *    |              -* A122
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithMiddleNodeConectedAndRelationUnderRootnode() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = TCFactory.eINSTANCE.createTCNode();
        odNodeRoot.setName("A");
        TCNode odNode1 = TCFactory.eINSTANCE.createTCNode();
        odNode1.setName("A1");
        TCNode odNode11 = TCFactory.eINSTANCE.createTCNode();
        odNode11.setName("A11");
        TCNode odNode111 = TCFactory.eINSTANCE.createTCNode();
        odNode111.setName("A111");
        TCNode odNode112 = TCFactory.eINSTANCE.createTCNode();
        odNode112.setName("A112");
        TCNode odNode12 = TCFactory.eINSTANCE.createTCNode();
        odNode12.setName("A12");
        TCNode odNode121 = TCFactory.eINSTANCE.createTCNode();
        odNode121.setName("A121");
        TCNode odNode122 = TCFactory.eINSTANCE.createTCNode();
        odNode122.setName("A122");
        TCNode odNode2 = TCFactory.eINSTANCE.createTCNode();
        odNode2.setName("A2");
        TCNode odNode21 = TCFactory.eINSTANCE.createTCNode();
        odNode21.setName("A21");
        TCNode odNode211 = TCFactory.eINSTANCE.createTCNode();
        odNode211.setName("A211");
        TCNode odNode212 = TCFactory.eINSTANCE.createTCNode();
        odNode212.setName("A212");
        TCNode odNode22 = TCFactory.eINSTANCE.createTCNode();
        odNode22.setName("A22");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode12.setChildType(ChildType.XOR);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        fmcs = null;
    }

    /**
     * LeafNodeとANDの関係および親ノードとXORの関係がある中間ノードを持つ木
     * 
     * <pre>
     * A --* A1 --* A11 --* A111 --* A1111 )-* A11111
     *    |      |       |        |         -* A11112
     *    |      |       |        |
     *    |      |       |        -* A1112 )-* A11121
     *    |      |       |                  -* A11122
     *    |      |       |
     *    |      |       -* A112 )-* A1121
     *    |      |                -* A1122
     *    |      |
     *    |      -* A12 )-* A121
     *    |              -* A122
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithMiddleNodeConectedXorAndRelation() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1111 = createOdNode("A1111");
        TCNode odNode1112 = createOdNode("A1112");
        TCNode odNode1121 = createOdNode("A1121");
        TCNode odNode1122 = createOdNode("A1122");
        TCNode odNode11111 = createOdNode("A11111");
        TCNode odNode11112 = createOdNode("A11112");
        TCNode odNode11121 = createOdNode("A11121");
        TCNode odNode11122 = createOdNode("A11122");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.AND);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode12.setChildType(ChildType.XOR);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode111.setChildType(ChildType.AND);
        odNode111.getChildren().add(odNode1111);
        odNode111.getChildren().add(odNode1112);
        odNode112.setChildType(ChildType.XOR);
        odNode112.getChildren().add(odNode1121);
        odNode112.getChildren().add(odNode1122);
        odNode1111.setChildType(ChildType.XOR);
        odNode1111.getChildren().add(odNode11111);
        odNode1111.getChildren().add(odNode11112);
        odNode1112.setChildType(ChildType.XOR);
        odNode1112.getChildren().add(odNode11121);
        odNode1112.getChildren().add(odNode11122);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        fmcs = null;
    }

    /**
     * 自ノードと親ノード間でXORの関係 かつ 自ノードと子ノード間でANDの関係をもつノードが１つ かつ 子ノードと孫ノード間でXORの関係があるノードを持つ木
     * 
     * <pre>
     * A --* A1 )-* A11 --* A111 )-* A1111
     *    |      |                -* A1112
     *    |      |
     *    |      -* A12 )-* A121
     *    |              -* A122
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithMiddleNodeConnectedOneXorAndXorRelation() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1111 = createOdNode("A1111");
        TCNode odNode1112 = createOdNode("A1112");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.XOR);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.AND);
        odNode11.getChildren().add(odNode111);
        odNode12.setChildType(ChildType.XOR);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode111.setChildType(ChildType.XOR);
        odNode111.getChildren().add(odNode1111);
        odNode111.getChildren().add(odNode1112);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        fmcs = null;
    }

    /**
     * 自ノードと親ノード間でXORの関係 かつ 自ノードと子ノード間でANDの関係をもつノードが２つ かつ 子ノードと孫ノード間でXORの関係があるノードを持つ木
     * 
     * <pre>
     * A --* A1 )-* A11 --* A111 )-* A1111
     *    |      |       |        -* A1112
     *    |      |       |
     *    |      |       -* A112 )-* A1121
     *    |      |                -* A1122
     *    |      |
     *    |      -* A12 )-* A121
     *    |              -* A122
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           -* A22
     * </pre>
     */
    private static void treeWithMiddleNodeConnectedTwoXorAndXorRelationsHorizontally() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1111 = createOdNode("A1111");
        TCNode odNode1112 = createOdNode("A1112");
        TCNode odNode1121 = createOdNode("A1121");
        TCNode odNode1122 = createOdNode("A1122");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.XOR);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.AND);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode12.setChildType(ChildType.XOR);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode111.setChildType(ChildType.XOR);
        odNode111.getChildren().add(odNode1111);
        odNode111.getChildren().add(odNode1112);
        odNode112.setChildType(ChildType.XOR);
        odNode112.getChildren().add(odNode1121);
        odNode112.getChildren().add(odNode1122);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        fmcs = null;
    }

    /**
     * 自ノードと親ノード間でXORの関係 かつ 自ノードと子ノード間でANDの関係をもつノードが３つ かつ 子ノードと孫ノード間でXORの関係があるノードを持つ木
     * 
     * <pre>
     * A --* A1 )-* A11 --* A111 )-* A1111
     *    |      |       |        -* A1112
     *    |      |       |
     *    |      |       -* A112 )-* A1121
     *    |      |       |        -* A1122
     *    |      |       |
     *    |      |       -* A113 )-* A1131
     *    |      |                -* A1132
     *    |      |
     *    |      -* A12 )-* A121
     *    |              -* A122
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           -* A22
     * </pre>
     */
    private static void treeWithMiddleNodeConnectedThreeXorAndXorRelationsHorizontally() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1111 = createOdNode("A1111");
        TCNode odNode1112 = createOdNode("A1112");
        TCNode odNode1121 = createOdNode("A1121");
        TCNode odNode1122 = createOdNode("A1122");
        TCNode odNode1131 = createOdNode("A1131");
        TCNode odNode1132 = createOdNode("A1132");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.XOR);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.AND);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.XOR);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode111.setChildType(ChildType.XOR);
        odNode111.getChildren().add(odNode1111);
        odNode111.getChildren().add(odNode1112);
        odNode112.setChildType(ChildType.XOR);
        odNode112.getChildren().add(odNode1121);
        odNode112.getChildren().add(odNode1122);
        odNode113.setChildType(ChildType.XOR);
        odNode113.getChildren().add(odNode1131);
        odNode113.getChildren().add(odNode1132);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        fmcs = null;
    }

    /**
     * 自ノードと親ノード間でXORの関係 かつ 自ノードと子ノード間でANDの関係をもつノードが２つ かつ 子ノードと孫ノード間でXORの関係があるノードを持ち、さらに孫ノードを自ノードとして深さ方向に同様の階層を持つ木
     * 
     * <pre>
     * A --* A1 )-* A11 --* A111 )-* A1111--* A11111)-* A111111
     *    |      |       |        |        |         -* A111112
     *    |      |       |        |        |
     *    |      |       |        |        -* A11112)-* A111121
     *    |      |       |        |                  -* A111122
     *    |      |       |        |
     *    |      |       |        |* A1112 
     *    |      |       |
     *    |      |       -* A112 )-* A1121
     *    |      |                -* A1122
     *    |      |
     *    |      -* A12 )-* A121
     *    |              -* A122
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           -* A22
     * </pre>
     */
    private static void treeWithMiddleNodeConnectedMultipleXorAndXorRelationsVertically() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1111 = createOdNode("A1111");
        TCNode odNode1112 = createOdNode("A1112");
        TCNode odNode1121 = createOdNode("A1121");
        TCNode odNode1122 = createOdNode("A1122");
        TCNode odNode11111 = createOdNode("A11111");
        TCNode odNode11112 = createOdNode("A11112");
        TCNode odNode111111 = createOdNode("A111111");
        TCNode odNode111112 = createOdNode("A111112");
        TCNode odNode111121 = createOdNode("A111121");
        TCNode odNode111122 = createOdNode("A111122");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.XOR);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.AND);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode111.setChildType(ChildType.XOR);
        odNode111.getChildren().add(odNode1111);
        odNode111.getChildren().add(odNode1112);
        odNode112.setChildType(ChildType.XOR);
        odNode112.getChildren().add(odNode1121);
        odNode112.getChildren().add(odNode1122);
        odNode1111.setChildType(ChildType.AND);
        odNode1111.getChildren().add(odNode11111);
        odNode1111.getChildren().add(odNode11112);
        odNode11111.setChildType(ChildType.XOR);
        odNode11111.getChildren().add(odNode111111);
        odNode11111.getChildren().add(odNode111112);
        odNode11112.setChildType(ChildType.XOR);
        odNode11112.getChildren().add(odNode111121);
        odNode11112.getChildren().add(odNode111122);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        fmcs = null;
    }

    /**
     * AND制約を１つ持つ木
     * 
     * <pre>
     * A --* A1 --* A11 )-* A111
     *    |      |      |
     *    |      |      |-* A112
     *    |      |      |
     *    |      |      |-* A113
     *    |      |
     *    |      -* A12 --* A121 )-* A1211
     *    |              |        -* A1212
     *    |              |
     *    |              -* A122 --* A1221 )-* A12211
     *    |                       |         -* A12212
     *    |                       |
     *    |                       -* A1222 )-* A12221
     *    |                                 -* A12222
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithAndConstraint() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.AND);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A11.A111");
        FMCSSelectExpression select2 = createSelectExpression("A.A2.A21.A211");

        // --- and
        FMCSAndExpression and1 = FMCSFactory.eINSTANCE.createFMCSAndExpression();
        and1.getExpressions().add(select1);
        and1.getExpressions().add(select2);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(and1);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * AND制約を１つ持ち かつ 自ノードと親ノード間でXORの関係 かつ 自ノードと子ノード間でANDの関係をもつノードが２つ かつ 子ノードと孫ノード間でXORの関係があるノードを持つ木
     * 
     * <pre>
     * A --* A1 --* A11 )-* A111
     *    |      |      |
     *    |      |      |-* A112
     *    |      |      |
     *    |      |      |-* A113
     *    |      |
     *    |      -* A12 --* A121 )-* A1211
     *    |              |        -* A1212
     *    |              |
     *    |              -* A122 --* A1221 )-* A12211
     *    |                       |         -* A12212
     *    |                       |
     *    |                       -* A1222 )-* A12221
     *    |                                 -* A12222
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithAndConstraintAndMultipleXorAndXorRelationsVertically() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.XOR);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.AND);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A11.A111");
        FMCSSelectExpression select2 = createSelectExpression("A.A2.A21.A211");

        // --- and
        FMCSAndExpression and1 = FMCSFactory.eINSTANCE.createFMCSAndExpression();
        and1.getExpressions().add(select1);
        and1.getExpressions().add(select2);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(and1);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * リーフノード間でAND制約を複数持つ木
     * 
     * <pre>
     * A --* A1 --* A11 )-* A111
     *    |      |      |
     *    |      |      |-* A112
     *    |      |      |
     *    |      |      |-* A113
     *    |      |
     *    |      -* A12 --* A121 )-* A1211
     *    |              |        -* A1212
     *    |              |
     *    |              -* A122 )-* A1221 )-* A12211
     *    |                       |         -* A12212
     *    |                       |
     *    |                       -* A1222 )-* A12221
     *    |                                 -* A12222
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithMultipleAndConstraintsAmongLeafNodes() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A11.A111");
        FMCSSelectExpression select2 = createSelectExpression("A.A2.A21.A211");
        FMCSSelectExpression select3 = createSelectExpression("A.A1.A12.A122.A1221.A12212");

        // --- and
        FMCSAndExpression and1 = FMCSFactory.eINSTANCE.createFMCSAndExpression();
        and1.getExpressions().add(select1);
        and1.getExpressions().add(select2);
        and1.getExpressions().add(select3);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(and1);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * 中間ノード間でAND制約を複数持つ木
     * 
     * <pre>
     * A --* A1 --* A11 )-* A111
     *    |      |      |
     *    |      |      |-* A112
     *    |      |      |
     *    |      |      |-* A113
     *    |      |
     *    |      -* A12 --* A121 )-* A1211
     *    |              |        -* A1212
     *    |              |
     *    |              -* A122 )-* A1221 )-* A12211
     *    |                       |         -* A12212
     *    |                       |
     *    |                       -* A1222 )-* A12221
     *    |                                 -* A12222
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithMultipleAndConstraintsAmongMiddleNodes() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A11.A111");
        FMCSSelectExpression select2 = createSelectExpression("A.A2.A21.A211");
        FMCSSelectExpression select3 = createSelectExpression("A.A1.A12.A122.A1221");

        // --- and
        FMCSAndExpression and1 = FMCSFactory.eINSTANCE.createFMCSAndExpression();
        and1.getExpressions().add(select1);
        and1.getExpressions().add(select2);
        and1.getExpressions().add(select3);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(and1);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * OR制約を１つ持つ木
     * 
     * <pre>
     * A --* A1 --* A11 )-* A111
     *    |      |      |
     *    |      |      |-* A112
     *    |      |      |
     *    |      |      |-* A113
     *    |      |
     *    |      -* A12 --* A121 )-* A1211
     *    |              |        -* A1212
     *    |              |
     *    |              -* A122 )-* A1221 )-* A12211
     *    |                       |         -* A12212
     *    |                       |
     *    |                       -* A1222 )-* A12221
     *    |                                 -* A12222
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithOrConstraint() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A11.A111");
        FMCSSelectExpression select2 = createSelectExpression("A.A2.A21.A211");
        FMCSSelectExpression select3 = createSelectExpression("A.A1.A12.A122.A1221");

        // --- and
        FMCSOrExpression or1 = FMCSFactory.eINSTANCE.createFMCSOrExpression();
        or1.getExpressions().add(select1);
        or1.getExpressions().add(select2);
        or1.getExpressions().add(select3);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(or1);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * リーフノードに対してNOT制約を持つ木
     * 
     * <pre>
     * A --* A1 --* A11 )-* A111
     *    |      |      |
     *    |      |      |-* A112
     *    |      |      |
     *    |      |      |-* A113
     *    |      |
     *    |      -* A12 --* A121 )-* A1211
     *    |              |        -* A1212
     *    |              |
     *    |              -* A122 )-* A1221 )-* A12211
     *    |                       |         -* A12212
     *    |                       |
     *    |                       -* A1222 )-* A12221
     *    |                                 -* A12222
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithNotConstraintToLeafNode() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A11.A113");

        // --- not
        FMCSNotExpression not1 = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        not1.setExpression(select1);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(not1);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * 中間ノードに対してNOT制約を持つ木
     * 
     * <pre>
     * A --* A1 --* A11 )-* A111
     *    |      |      |
     *    |      |      |-* A112
     *    |      |      |
     *    |      |      |-* A113
     *    |      |
     *    |      -* A12 --* A121 )-* A1211
     *    |              |        -* A1212
     *    |              |
     *    |              -* A122 )-* A1221 )-* A12211
     *    |                       |         -* A12212
     *    |                       |
     *    |                       -* A1222 )-* A12221
     *    |                                 -* A12222
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithNotConstraintToMiddleNode() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A12");

        // --- not
        FMCSNotExpression not1 = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        not1.setExpression(select1);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(not1);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * MUTEX制約を１つ持つ木
     * 
     * <pre>
     * A --* A1 --* A11 )-* A111
     *    |      |      |
     *    |      |      |-* A112
     *    |      |      |
     *    |      |      |-* A113
     *    |      |
     *    |      -* A12 --* A121 )-* A1211
     *    |              |        -* A1212
     *    |              |
     *    |              -* A122 --* A1221 )-* A12211
     *    |                       |         -* A12212
     *    |                       |
     *    |                       -* A1222 )-* A12221
     *    |                                 -* A12222
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithSingleMutexConstraint() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.AND);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSODElement element1 = createOdElement("A.A1.A11.A111");
        FMCSODElement element2 = createOdElement("A.A2.A21.A211");

        // --- mutex
        FMCSMutexExpression mutex1 = FMCSFactory.eINSTANCE.createFMCSMutexExpression();
        mutex1.getOdElements().add(element1);
        mutex1.getOdElements().add(element2);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(mutex1);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * リーフノードに対してMUTEX制約を複数持つ木
     * 
     * <pre>
     * A --* A1 --* A11 )-* A111
     *    |      |      |
     *    |      |      |-* A112
     *    |      |      |
     *    |      |      |-* A113
     *    |      |
     *    |      -* A12 --* A121 )-* A1211
     *    |              |        -* A1212
     *    |              |
     *    |              -* A122 )-* A1221 )-* A12211
     *    |                       |         -* A12212
     *    |                       |
     *    |                       -* A1222 )-* A12221
     *    |                                 -* A12222
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithMultipleMutexConstraintsToleafNodes() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSODElement element1 = createOdElement("A.A1.A11.A111");
        FMCSODElement element2 = createOdElement("A.A2.A21.A211");
        FMCSODElement element3 = createOdElement("A.A1.A12.A122.A1221");

        // --- mutex
        FMCSMutexExpression mutex1 = FMCSFactory.eINSTANCE.createFMCSMutexExpression();
        mutex1.getOdElements().add(element1);
        mutex1.getOdElements().add(element2);
        mutex1.getOdElements().add(element3);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(mutex1);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * 中間ノードに対してMUTEX制約を複数持つ木
     * 
     * <pre>
     * A --* A1 --* A11 )-* A111
     *    |      |      |
     *    |      |      |-* A112
     *    |      |      |
     *    |      |      |-* A113
     *    |      |
     *    |      -* A12 --* A121 )-* A1211
     *    |              |        -* A1212
     *    |              |
     *    |              -* A122 )-* A1221 )-* A12211
     *    |                       |         -* A12212
     *    |                       |
     *    |                       -* A1222 )-* A12221
     *    |                                 -* A12222
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithMultipleMutexConstraintsToMiddleNodes() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.XOR);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSODElement element1 = createOdElement("A.A1.A11.A111");
        FMCSODElement element2 = createOdElement("A.A2.A21.A211");
        FMCSODElement element3 = createOdElement("A.A1.A12.A122");

        // --- mutex
        FMCSMutexExpression mutex1 = FMCSFactory.eINSTANCE.createFMCSMutexExpression();
        mutex1.getOdElements().add(element1);
        mutex1.getOdElements().add(element2);
        mutex1.getOdElements().add(element3);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(mutex1);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * ANDおよびOR制約を持つ木
     * 
     * <pre>
     * A --* A1 --* A11 )-* A111
     *    |      |      |
     *    |      |      |-* A112
     *    |      |      |
     *    |      |      |-* A113
     *    |      |
     *    |      -* A12 --* A121 )-* A1211
     *    |              |        -* A1212
     *    |              |
     *    |              -* A122 )-* A1221 )-* A12211
     *    |                       |         -* A12212
     *    |                       |
     *    |                       -* A1222 )-* A12221
     *    |                                 -* A12222
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithAndOrConstraint() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A11.A111");
        FMCSSelectExpression select2 = createSelectExpression("A.A2.A21.A211");
        FMCSSelectExpression select3 = createSelectExpression("A.A1.A12.A122.A1221");

        // --- and
        FMCSAndExpression and1 = FMCSFactory.eINSTANCE.createFMCSAndExpression();
        and1.getExpressions().add(select1);
        and1.getExpressions().add(select2);

        // --- or
        FMCSOrExpression or1 = FMCSFactory.eINSTANCE.createFMCSOrExpression();
        or1.getExpressions().add(and1);
        or1.getExpressions().add(select3);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(or1);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * ANDおよびORおよび１つのNOT制約を持つ木
     * 
     * <pre>
     * A --* A1 --* A11 )-* A111
     *    |      |      |
     *    |      |      |-* A112
     *    |      |      |
     *    |      |      |-* A113
     *    |      |
     *    |      -* A12 --* A121 )-* A1211
     *    |              |        -* A1212
     *    |              |
     *    |              -* A122 )-* A1221 )-* A12211
     *    |                       |         -* A12212
     *    |                       |
     *    |                       -* A1222 )-* A12221
     *    |                                 -* A12222
     *    |
     *    -* A2 )-* A21 )-* A211
     *           |       -* A212
     *           |
     *           -* A22
     * </pre>
     */
    private static void treeWithAndOrSingleNotConstraints() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A11.A111");
        FMCSSelectExpression select2 = createSelectExpression("A.A2.A21.A211");
        FMCSSelectExpression select3 = createSelectExpression("A.A1.A12.A122.A1221");

        // --- and
        FMCSAndExpression and1 = FMCSFactory.eINSTANCE.createFMCSAndExpression();
        and1.getExpressions().add(select1);
        and1.getExpressions().add(select2);

        // --- or
        FMCSOrExpression or1 = FMCSFactory.eINSTANCE.createFMCSOrExpression();
        or1.getExpressions().add(and1);
        or1.getExpressions().add(select3);

        // --- not
        FMCSNotExpression not1 = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        not1.setExpression(or1);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(not1);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * ANDおよびORおよび複数のNOT制約を持つ木
     */
    private static void treeWithAndOrMultipleNotConstraints() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A11.A111");
        FMCSSelectExpression select2 = createSelectExpression("A.A2.A21.A211");
        FMCSSelectExpression select3 = createSelectExpression("A.A1.A12.A122.A1221");

        // --- not
        FMCSNotExpression not1 = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        not1.setExpression(select3);

        // --- and
        FMCSAndExpression and1 = FMCSFactory.eINSTANCE.createFMCSAndExpression();
        FMCSAndExpression and2 = FMCSFactory.eINSTANCE.createFMCSAndExpression();
        and1.getExpressions().add(select1);
        and1.getExpressions().add(select2);
        and2.getExpressions().add(and1);
        and2.getExpressions().add(not1);

        // --- not
        FMCSNotExpression not2 = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        not2.setExpression(and2);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(not2);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * 複数のORおよび複数のNOT制約を持つ木
     */
    private static void treeWithMultipleOrNotConstraints() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A11.A111");
        FMCSSelectExpression select2 = createSelectExpression("A.A2.A21.A211");
        FMCSSelectExpression select3 = createSelectExpression("A.A1.A12.A122.A1221");

        // --- or
        FMCSOrExpression or1 = FMCSFactory.eINSTANCE.createFMCSOrExpression();
        FMCSOrExpression or2 = FMCSFactory.eINSTANCE.createFMCSOrExpression();
        or1.getExpressions().add(select1);
        or1.getExpressions().add(select2);

        // --- not
        FMCSNotExpression not1 = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        not1.setExpression(or1);

        // --- or
        or2.getExpressions().add(not1);
        or2.getExpressions().add(select3);

        // --- not
        FMCSNotExpression not2 = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        not2.setExpression(or2);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(not2);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * ANDおよびNOTおよびMUTEX制約を持つ木
     */
    private static void treeWithAndNotMutexConstraints() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSODElement od1 = createOdElement("A.A1.A11.A111");
        FMCSODElement od2 = createOdElement("A.A1.A12.A122.A1221");
        FMCSODElement od3 = createOdElement("A.A1.A12.A122.A1222");
        FMCSODElement od4 = createOdElement("A.A2.A21.A211");

        // --- mutex
        FMCSMutexExpression m1 = FMCSFactory.eINSTANCE.createFMCSMutexExpression();
        FMCSMutexExpression m2 = FMCSFactory.eINSTANCE.createFMCSMutexExpression();
        m1.getOdElements().add(od1);
        m1.getOdElements().add(od2);
        m2.getOdElements().add(od3);
        m2.getOdElements().add(od4);

        // --- not
        FMCSNotExpression not1 = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        not1.setExpression(m2);

        // --- and
        FMCSAndExpression and1 = FMCSFactory.eINSTANCE.createFMCSAndExpression();
        and1.getExpressions().add(m1);
        and1.getExpressions().add(not1);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(and1);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * ORおよびNOTおよびMUTEX制約を持つ木
     */
    private static void treeWithOrNotMutexConstraints() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSODElement od1 = createOdElement("A.A1.A11.A111");
        FMCSODElement od2 = createOdElement("A.A1.A12.A122.A1221");
        FMCSODElement od3 = createOdElement("A.A1.A12.A122.A1222");
        FMCSODElement od4 = createOdElement("A.A2.A21.A211");

        // --- mutex
        FMCSMutexExpression m1 = FMCSFactory.eINSTANCE.createFMCSMutexExpression();
        FMCSMutexExpression m2 = FMCSFactory.eINSTANCE.createFMCSMutexExpression();
        m1.getOdElements().add(od1);
        m1.getOdElements().add(od2);
        m2.getOdElements().add(od3);
        m2.getOdElements().add(od4);

        // --- not
        FMCSNotExpression not1 = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        not1.setExpression(m1);

        // --- or
        FMCSOrExpression or1 = FMCSFactory.eINSTANCE.createFMCSOrExpression();
        or1.getExpressions().add(not1);
        or1.getExpressions().add(m2);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(or1);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * リーフノードからリーフノードに対してIMPLIES制約を持つ木
     */
    private static void treeWithImpliesConstraintFromLeafNodeToLeafNode() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A11.A113");
        FMCSSelectExpression select2 = createSelectExpression("A.A2.A21.A211");

        // --- implies
        FMCSImpliesExpression implies = FMCSFactory.eINSTANCE.createFMCSImpliesExpression();
        implies.setLeftExpression(select1);
        implies.setRightExpression(select2);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(implies);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * リーフノードから中間ノードに対してIMPLIES制約を持つ木
     */
    private static void treeWithImpliesConstraintFromLeafNodeToMiddleNode() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A11.A113");
        FMCSSelectExpression select2 = createSelectExpression("A.A2.A21");

        // --- implies
        FMCSImpliesExpression implies = FMCSFactory.eINSTANCE.createFMCSImpliesExpression();
        implies.setLeftExpression(select1);
        implies.setRightExpression(select2);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(implies);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * 中間ノードからリーフノードに対してIMPLIES制約を持つ木
     */
    private static void treeWithImpliesConstraintFromMiddleNodeToLeafNode() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A12.A122.A1221");
        FMCSSelectExpression select2 = createSelectExpression("A.A2.A21.A211");

        // --- implies
        FMCSImpliesExpression implies = FMCSFactory.eINSTANCE.createFMCSImpliesExpression();
        implies.setLeftExpression(select1);
        implies.setRightExpression(select2);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(implies);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * 中間ノードから中間ノードに対してIMPLIES制約を持つ木
     */
    private static void treeWithImpliesConstraintFromMiddleNodeToMiddleNode() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A12.A122.A1221");
        FMCSSelectExpression select2 = createSelectExpression("A.A2.A21");

        // --- implies
        FMCSImpliesExpression implies = FMCSFactory.eINSTANCE.createFMCSImpliesExpression();
        implies.setLeftExpression(select1);
        implies.setRightExpression(select2);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(implies);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * IMPLIES制約の左辺／右辺に論理演算式が定義された制約を持つ木
     */
    private static void treeWithImpliesConstraintTranslateExpression() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- select
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A12.A122.A1221");
        FMCSSelectExpression select2 = createSelectExpression("A.A1.A11.A111");
        FMCSSelectExpression select3 = createSelectExpression("A.A1.A12.A122.A1221.A12211");
        FMCSSelectExpression select4 = createSelectExpression("A.A1.A12.A122.A1222");
        FMCSSelectExpression select5 = createSelectExpression("A.A2.A21.A211");

        // --- and
        FMCSAndExpression and = FMCSFactory.eINSTANCE.createFMCSAndExpression();
        and.getExpressions().add(select1);
        and.getExpressions().add(select2);

        // --- not
        FMCSNotExpression not = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        not.setExpression(and);

        // --- or
        FMCSOrExpression or1 = FMCSFactory.eINSTANCE.createFMCSOrExpression();
        FMCSOrExpression or2 = FMCSFactory.eINSTANCE.createFMCSOrExpression();
        or1.getExpressions().add(select4);
        or1.getExpressions().add(select5);
        or2.getExpressions().add(select3);
        or2.getExpressions().add(or1);

        // --- implies
        FMCSImpliesExpression implies = FMCSFactory.eINSTANCE.createFMCSImpliesExpression();
        implies.setLeftExpression(not);
        implies.setRightExpression(or2);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(implies);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * IMPLIES制約をNOT制約とOR制約に展開した制約を持つ木
     */
    private static void translateConstraintFromImpliesToNotOr() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- select
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A12.A122.A1221");
        FMCSSelectExpression select2 = createSelectExpression("A.A1.A11.A111");
        FMCSSelectExpression select3 = createSelectExpression("A.A1.A12.A122.A1221.A12211");
        FMCSSelectExpression select4 = createSelectExpression("A.A1.A12.A122.A1222");
        FMCSSelectExpression select5 = createSelectExpression("A.A2.A21.A211");

        // --- and
        FMCSAndExpression and = FMCSFactory.eINSTANCE.createFMCSAndExpression();
        and.getExpressions().add(select1);
        and.getExpressions().add(select2);

        // --- not
        FMCSNotExpression not1 = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        FMCSNotExpression not2 = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        not1.setExpression(and);
        not2.setExpression(not1);

        // --- or
        FMCSOrExpression or1 = FMCSFactory.eINSTANCE.createFMCSOrExpression();
        FMCSOrExpression or2 = FMCSFactory.eINSTANCE.createFMCSOrExpression();
        FMCSOrExpression or3 = FMCSFactory.eINSTANCE.createFMCSOrExpression();
        or1.getExpressions().add(select4);
        or1.getExpressions().add(select5);
        or2.getExpressions().add(select3);
        or2.getExpressions().add(or1);
        or3.getExpressions().add(not2);
        or3.getExpressions().add(or2);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(or3);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * ノードからノードに対してREMOVES制約を持つ木
     */
    private static void treeWithRemovesConstraintFromNodeToNode() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        // --- select, odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A2.A21");
        FMCSODElement od1 = createOdElement("A.A1.A12.A122");

        // --- removes
        FMCSRemovesExpression removes = FMCSFactory.eINSTANCE.createFMCSRemovesExpression();
        removes.setExpression(select1);
        removes.setOdElement(od1);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(removes);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * REMOVS制約の左辺に論理演算式が定義された制約を持つ木
     */
    private static void treeWithRemovesConstraintFromLogicalExpressionFormulaToNode() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode221 = createOdNode("A221");
        TCNode odNode222 = createOdNode("A222");
        TCNode odNode1211 = createOdNode("A1211");
        TCNode odNode1212 = createOdNode("A1212");
        TCNode odNode1221 = createOdNode("A1221");
        TCNode odNode1222 = createOdNode("A1222");
        TCNode odNode12211 = createOdNode("A12211");
        TCNode odNode12212 = createOdNode("A12212");
        TCNode odNode12221 = createOdNode("A12221");
        TCNode odNode12222 = createOdNode("A12222");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode121.setChildType(ChildType.XOR);
        odNode121.getChildren().add(odNode1211);
        odNode121.getChildren().add(odNode1212);
        odNode122.setChildType(ChildType.XOR);
        odNode122.getChildren().add(odNode1221);
        odNode122.getChildren().add(odNode1222);
        odNode1221.setChildType(ChildType.XOR);
        odNode1221.getChildren().add(odNode12211);
        odNode1221.getChildren().add(odNode12212);
        odNode1222.setChildType(ChildType.XOR);
        odNode1222.getChildren().add(odNode12221);
        odNode1222.getChildren().add(odNode12222);
        odNode2.setChildType(ChildType.AND);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);
        odNode22.setChildType(ChildType.XOR);
        odNode22.getChildren().add(odNode221);
        odNode22.getChildren().add(odNode222);

        // constraint
        // --- select, odelement
        FMCSSelectExpression select1 = createSelectExpression("A.A1.A12.A121.A1211");
        FMCSSelectExpression select2 = createSelectExpression("A.A1.A12.A122.A1221");
        FMCSSelectExpression select3 = createSelectExpression("A.A1.A11.A113");
        FMCSODElement od1 = createOdElement("A.A2.A21");

        // --- not
        FMCSNotExpression not = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        not.setExpression(select1);

        // --- and
        FMCSAndExpression and = FMCSFactory.eINSTANCE.createFMCSAndExpression();
        and.getExpressions().add(not);
        and.getExpressions().add(select2);

        // --- or
        FMCSOrExpression or = FMCSFactory.eINSTANCE.createFMCSOrExpression();
        or.getExpressions().add(and);
        or.getExpressions().add(select3);

        // --- removes
        FMCSRemovesExpression removes = FMCSFactory.eINSTANCE.createFMCSRemovesExpression();
        removes.setExpression(or);
        removes.setOdElement(od1);

        // --- set
        FMCSConstraint constraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint1.setExpression(removes);
        fmcs = FMCSFactory.eINSTANCE.createFMCSRoot();
        fmcs.getConstraints().add(constraint1);
    }

    /**
     * 自ノードの子ノードが１つ かつ 孫ノードが１つ かつ曾孫ノードが１つと深さ方向に直列な関係のみを持つ木
     */
    private static void treeWithSerialConnectionWithoutBranch() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("R");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode3 = createOdNode("A3");
        TCNode odNode4 = createOdNode("A4");
        TCNode odNode5 = createOdNode("A5");
        TCNode odNode6 = createOdNode("A6");
        TCNode odNode7 = createOdNode("A7");
        TCNode odNode8 = createOdNode("A8");
        TCNode odNode11 = createOdNode("B1");
        TCNode odNode12 = createOdNode("B2");
        TCNode odNode13 = createOdNode("B3");
        TCNode odNode14 = createOdNode("B4");
        TCNode odNode15 = createOdNode("B5");
        TCNode odNode16 = createOdNode("B6");
        TCNode odNode17 = createOdNode("B7");
        TCNode odNode18 = createOdNode("B8");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode11);
        odNode1.setChildType(ChildType.XOR);
        odNode1.getChildren().add(odNode2);
        odNode2.setChildType(ChildType.AND);
        odNode2.getChildren().add(odNode3);
        odNode3.setChildType(ChildType.XOR);
        odNode3.getChildren().add(odNode4);
        odNode4.setChildType(ChildType.AND);
        odNode4.getChildren().add(odNode5);
        odNode5.setChildType(ChildType.XOR);
        odNode5.getChildren().add(odNode6);
        odNode6.setChildType(ChildType.AND);
        odNode6.getChildren().add(odNode7);
        odNode7.setChildType(ChildType.XOR);
        odNode7.getChildren().add(odNode8);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode12);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode13);
        odNode13.setChildType(ChildType.XOR);
        odNode13.getChildren().add(odNode14);
        odNode14.setChildType(ChildType.AND);
        odNode14.getChildren().add(odNode15);
        odNode15.setChildType(ChildType.XOR);
        odNode15.getChildren().add(odNode16);
        odNode16.setChildType(ChildType.AND);
        odNode16.getChildren().add(odNode17);
        odNode17.setChildType(ChildType.XOR);
        odNode17.getChildren().add(odNode18);
    }

    /**
     * 自ノードの子ノードが１つ かつ 孫ノードが２つ かつ 孫ノードの子ノードが各１つ かつその孫ノードが各１つと深さ方向に直列および並列な関係を持つ木
     */
    private static void treeWithSerialConnectionAndBranch() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("R");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode odNode3 = createOdNode("A3");
        TCNode odNode4 = createOdNode("A4");
        TCNode odNode5 = createOdNode("A5");
        TCNode odNode61 = createOdNode("A61");
        TCNode odNode71 = createOdNode("A71");
        TCNode odNode81 = createOdNode("A81");
        TCNode odNode62 = createOdNode("A62");
        TCNode odNode72 = createOdNode("A72");
        TCNode odNode82 = createOdNode("A82");
        TCNode odNode11 = createOdNode("B1");
        TCNode odNode12 = createOdNode("B2");
        TCNode odNode13 = createOdNode("B3");
        TCNode odNode141 = createOdNode("B41");
        TCNode odNode142 = createOdNode("B42");
        TCNode odNode151 = createOdNode("B51");
        TCNode odNode152 = createOdNode("B52");
        TCNode odNode161 = createOdNode("B61");
        TCNode odNode162 = createOdNode("B62");
        TCNode odNode163 = createOdNode("B63");
        TCNode odNode171 = createOdNode("B71");
        TCNode odNode172 = createOdNode("B72");
        TCNode odNode173 = createOdNode("B73");
        TCNode odNode181 = createOdNode("B81");
        TCNode odNode182 = createOdNode("B82");
        TCNode odNode183 = createOdNode("B83");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode11);
        odNode1.setChildType(ChildType.XOR);
        odNode1.getChildren().add(odNode2);
        odNode2.setChildType(ChildType.AND);
        odNode2.getChildren().add(odNode3);
        odNode3.setChildType(ChildType.XOR);
        odNode3.getChildren().add(odNode4);
        odNode4.setChildType(ChildType.AND);
        odNode4.getChildren().add(odNode5);
        odNode5.setChildType(ChildType.XOR);
        odNode5.getChildren().add(odNode61);
        odNode5.getChildren().add(odNode62);
        odNode61.setChildType(ChildType.AND);
        odNode61.getChildren().add(odNode71);
        odNode71.setChildType(ChildType.XOR);
        odNode71.getChildren().add(odNode81);
        odNode62.setChildType(ChildType.AND);
        odNode62.getChildren().add(odNode72);
        odNode72.setChildType(ChildType.XOR);
        odNode72.getChildren().add(odNode82);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode12);
        odNode12.setChildType(ChildType.AND);
        odNode12.getChildren().add(odNode13);
        odNode13.setChildType(ChildType.XOR);
        odNode13.getChildren().add(odNode141);
        odNode13.getChildren().add(odNode142);
        odNode141.setChildType(ChildType.AND);
        odNode141.getChildren().add(odNode151);
        odNode151.setChildType(ChildType.XOR);
        odNode151.getChildren().add(odNode161);
        odNode161.setChildType(ChildType.AND);
        odNode161.getChildren().add(odNode171);
        odNode171.setChildType(ChildType.XOR);
        odNode171.getChildren().add(odNode181);
        odNode142.setChildType(ChildType.AND);
        odNode142.getChildren().add(odNode152);
        odNode152.setChildType(ChildType.XOR);
        odNode152.getChildren().add(odNode162);
        odNode152.getChildren().add(odNode163);
        odNode162.setChildType(ChildType.AND);
        odNode162.getChildren().add(odNode172);
        odNode163.setChildType(ChildType.AND);
        odNode163.getChildren().add(odNode173);
        odNode172.setChildType(ChildType.XOR);
        odNode172.getChildren().add(odNode182);
        odNode173.setChildType(ChildType.XOR);
        odNode173.getChildren().add(odNode183);

        // constraint
        fmcs = null;
    }

    /**
     * 深さ方向に子ノードおよび孫ノードをそれぞれ１つのみ存在する関係を持ち、かつ 自ノードと親ノード間でXORの関係 かつ 自ノードと子ノード間でANDの関係をもつノードが２つ かつ 子ノードと孫ノード間でXORの関係があるノードを持つ木
     */
    private static void treeWithSerialConnectionWithXorAndXorRelation() {
        // tree structure
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = createOdNode("A");
        TCNode odNode1 = createOdNode("A1");
        TCNode odNode2 = createOdNode("A2");
        TCNode dummy = createOdNode("dummy1");
        TCNode odNode11 = createOdNode("A11");
        TCNode odNode12 = createOdNode("A12");
        TCNode odNode21 = createOdNode("A21");
        TCNode odNode22 = createOdNode("A22");
        TCNode odNode111 = createOdNode("A111");
        TCNode odNode112 = createOdNode("A112");
        TCNode odNode113 = createOdNode("A113");
        TCNode odNode121 = createOdNode("A121");
        TCNode odNode122 = createOdNode("A122");
        TCNode odNode211 = createOdNode("A211");
        TCNode odNode212 = createOdNode("A212");
        TCNode odNode1111 = createOdNode("A1111");
        TCNode odNode1112 = createOdNode("A1112");
        TCNode odNode1121 = createOdNode("A1121");
        TCNode odNode1122 = createOdNode("A1122");
        TCNode odNode1131 = createOdNode("A1131");
        TCNode odNode1132 = createOdNode("A1132");
        TCNode odNode11111 = createOdNode("A11111");
        TCNode odNode111111 = createOdNode("A111111");

        odRoot.getRootNodes().clear();
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.XOR);
        odNode1.getChildren().add(dummy);
        dummy.setChildType(ChildType.XOR);
        dummy.getChildren().add(odNode11);
        dummy.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.AND);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode11.getChildren().add(odNode113);
        odNode12.setChildType(ChildType.XOR);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode111.setChildType(ChildType.XOR);
        odNode111.getChildren().add(odNode1111);
        odNode111.getChildren().add(odNode1112);
        odNode1111.setChildType(ChildType.AND);
        odNode1111.getChildren().add(odNode11111);
        odNode11111.setChildType(ChildType.XOR);
        odNode11111.getChildren().add(odNode111111);
        odNode112.setChildType(ChildType.XOR);
        odNode112.getChildren().add(odNode1121);
        odNode112.getChildren().add(odNode1122);
        odNode113.setChildType(ChildType.XOR);
        odNode113.getChildren().add(odNode1131);
        odNode113.getChildren().add(odNode1132);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);
        odNode21.setChildType(ChildType.XOR);
        odNode21.getChildren().add(odNode211);
        odNode21.getChildren().add(odNode212);

        // constraint
        fmcs = null;
    }
}
