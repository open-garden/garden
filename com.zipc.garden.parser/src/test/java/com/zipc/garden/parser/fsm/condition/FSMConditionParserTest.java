package com.zipc.garden.parser.fsm.condition;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class FSMConditionParserTest {

    class SampleVisitorImpl implements FSMConditionParserVisitor {
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
        public Object visit(ASTFSMCondition node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTFSMOrExpression node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTFSMAndExpression node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTFSMNotEqualExpression node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTFSMEqualExpression node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTFSMNotExpression node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTFSMStringValueExpression node, Object data) {
            return visitAndPrint(node, data);
        }

        @Override
        public Object visit(ASTFSMVariableReferenceValueExpression node, Object data) {
            return visitAndPrint(node, data);
        }
    }

    @Test
    public void test01() {
        Reader in = new StringReader("(Car3 != \"F\") && (Car6 != \"F\")");
        FSMConditionParser parser = new FSMConditionParser(in);
        try {
            ASTFSMCondition fsmCondition = parser.parse();
            fsmCondition.jjtAccept(new SampleVisitorImpl(), new HashMap<String, Object>());
            assertTrue(true);
        } catch (ParseException e) {
            fail(e);
        }
    }
}
