package com.zipc.garden.parser.fmc.constraint;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class FMConstraintParserTest {
    class SampleVisitorImpl implements FMConstraintParserVisitor {
        public Object visitAndPrint(SimpleNode node, Object data) {
            @SuppressWarnings("unchecked")
            Map<String, Object> dataMap = (Map<String, Object>) data;
            StringBuffer indent = new StringBuffer(">");
            if (!dataMap.containsKey("indent")) {
                dataMap.put("indent", 0);
            }

            int indentSize = (Integer) dataMap.get("indent");
            for (int i = 0; i < indentSize; i++) {
                indent.append(" ");
            }

            if (node.value == null) {
                System.out.printf("%s%s: \n", indent, node);
            } else {
                System.out.printf("%s%s: [%s]\n", indent, node, node.value);
            }

            for (int i = 0; i < node.jjtGetNumChildren(); ++i) {
                dataMap.put("indent", indentSize + 1);
                node.jjtGetChild(i).jjtAccept(this, data);
                dataMap.put("indent", indentSize);
            }
            return data;
        }

        @Override
        public Object visit(SimpleNode node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTRoot node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTExpression node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTImpliesExpression node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTOrExpression node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTAndExpression node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTUnaryExpression node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTMutexExpression node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTNotExpression node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTNode node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTParentNode node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTIdentifier node, Object data) {
            return visitAndPrint(node, data);
        }
    }

    @Test
    public void testWithoutRemoves() {
        Reader in = new StringReader("(A.a1 || éQè∆B:\"b.v1\") && (!X || mutex(x, y, z)) || C -> S");
        FMConstraintParser parser = new FMConstraintParser(in);
        try {
            ASTRoot root = parser.Root();
            root.jjtAccept(new SampleVisitorImpl(), new HashMap<String, Object>());
            assertTrue(true);
        } catch (ParseException e) {
            fail(e);
        }
    }

    @Test
    public void testWithRemoves() {
        Reader in = new StringReader("(A.a1 || éQè∆B:b.v1) && (!X || mutex(x, y, z)) || C removes Z");
        FMConstraintParser parser = new FMConstraintParser(in);
        try {
            ASTRoot root = parser.Root();
            root.jjtAccept(new SampleVisitorImpl(), new HashMap<String, Object>());
            assertTrue(true);
        } catch (ParseException e) {
            fail(e);
        }
    }
}
