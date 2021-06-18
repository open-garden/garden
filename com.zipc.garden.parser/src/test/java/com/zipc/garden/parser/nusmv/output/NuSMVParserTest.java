package com.zipc.garden.parser.nusmv.output;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class NuSMVParserTest {
    private ASTRoot sut;

    private static NuSMVResultParserVisitor stubVisitor;

    @BeforeAll
    public static void initialize() {
        stubVisitor = new SampleVisitorImpl();
    }

    @Test
    public void test() throws FileNotFoundException, IOException, ParseException {
        try (Reader in = new FileReader("src/test/resources/NuSMVResultParser/cut_in_1switch.txt")) {
            NuSMVResultParser parser = new NuSMVResultParser(in);
            sut = parser.Root();
            sut.jjtAccept(stubVisitor, 0);
        }

    }

    @Test
    public void canParseAndReturnStructure() throws FileNotFoundException, IOException, ParseException {
        try (Reader in = new FileReader("src/test/resources/NuSMVResultParser/firsttest.input")) {
            NuSMVResultParser parser = new NuSMVResultParser(in);
            sut = parser.Root();
            sut.jjtAccept(stubVisitor, 0);
        }

        // assert model structure
        SimpleNode header = getDescendant(sut, 0);
        SimpleNode results = getDescendant(sut, 1);
        SimpleNode result1 = getDescendant(sut, 1, 0);
        SimpleNode specification = getDescendant(sut, 1, 0, 0);
        SimpleNode step1_1 = getDescendant(sut, 1, 0, 1);
        SimpleNode assign = getDescendant(sut, 1, 0, 1, 0);
        SimpleNode variable = getDescendant(sut, 1, 0, 1, 0, 0);
        SimpleNode value = getDescendant(sut, 1, 0, 1, 0, 1);
        assertThat(header, instanceOf(ASTHeader.class));
        assertThat(results, instanceOf(ASTResults.class));
        assertThat(result1, instanceOf(ASTResult.class));
        assertThat(specification, instanceOf(ASTSpecification.class));
        assertThat(step1_1, instanceOf(ASTStep.class));
        assertThat(assign, instanceOf(ASTAssignment.class));
        assertThat(variable, instanceOf(ASTVariable.class));
        assertThat(value, instanceOf(ASTValue.class));

        // assert size
        assertHasChildren(results, 3); // two results
        assertHasChildren(result1, 3); // one spec, two steps
        assertHasChildren(step1_1, 2); // two assignments
        SimpleNode step1_2 = getDescendant(sut, 1, 0, 2);
        assertHasChildren(step1_2, 1); // one assignments
        SimpleNode result2 = getDescendant(sut, 1, 1);
        assertHasChildren(result2, 1); // one spec, no step
        SimpleNode result3 = getDescendant(sut, 1, 2);
        assertHasChildren(result3, 2); // one spec, one step
        SimpleNode step2_1 = getDescendant(sut, 1, 2, 1);
        assertHasChildren(step2_1, 7); // seven assignments
    }

    private void assertHasChildren(SimpleNode sut, int size) {
        assertThat(sut.children.length, is(size));
    }

    private SimpleNode getDescendant(SimpleNode node, int... hierarchyIndexes) {
        SimpleNode targetNode = node;
        for (int index : hierarchyIndexes) {
            Node child = targetNode.children[index];
            if (!(child instanceof SimpleNode)) {
                throw new RuntimeException("Wrong hierarchy.");
            }
            targetNode = (SimpleNode) child;
        }
        return targetNode;
    }

    static class SampleVisitorImpl extends NuSMVResultParserDefaultVisitor {
        @Override
        public Object defaultVisit(SimpleNode node, Object data) {
            Integer indentLevel = (Integer) data;
            String indent = ">" + Stream.generate(() -> " ").limit(indentLevel).collect(Collectors.joining());

            if (node.value == null) {
                System.out.printf("%s%s:\n", indent, node);
            } else {
                System.out.printf("%s%s: [%s]\n", indent, node, node.value);
            }

            if (node.jjtGetNumChildren() == 0) {
                return data;
            }

            return node.childrenAccept(this, indentLevel + 1);
        }

        @Override
        public Object visit(ASTAssignment node, Object data) {
            Integer indentLevel = (Integer) data;
            String indent = ">" + Stream.generate(() -> " ").limit(indentLevel).collect(Collectors.joining());

            SimpleNode variable = (SimpleNode) node.children[0];
            SimpleNode value = (SimpleNode) node.children[1];
            System.out.printf("%s%s: %s = %s\n", indent, node, variable.value, value.value);

            return data;
        }

    }

}
