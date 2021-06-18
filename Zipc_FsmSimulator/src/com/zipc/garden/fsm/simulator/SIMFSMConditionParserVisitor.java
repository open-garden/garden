package com.zipc.garden.fsm.simulator;

import com.zipc.garden.parser.fsm.condition.ASTFSMAndExpression;
import com.zipc.garden.parser.fsm.condition.ASTFSMCondition;
import com.zipc.garden.parser.fsm.condition.ASTFSMEqualExpression;
import com.zipc.garden.parser.fsm.condition.ASTFSMNotEqualExpression;
import com.zipc.garden.parser.fsm.condition.ASTFSMNotExpression;
import com.zipc.garden.parser.fsm.condition.ASTFSMOrExpression;
import com.zipc.garden.parser.fsm.condition.ASTFSMStringValueExpression;
import com.zipc.garden.parser.fsm.condition.ASTFSMVariableReferenceValueExpression;
import com.zipc.garden.parser.fsm.condition.FSMConditionParserVisitor;
import com.zipc.garden.parser.fsm.condition.SimpleNode;

/**
 * A class that parses conditional expressions of Finite State Machine.
 */
public class SIMFSMConditionParserVisitor implements FSMConditionParserVisitor {

    /** State Machine environment for simulators */
    SIMStateMachine fsm;

    /**
     * Set the State Machine environment for the simulator.
     * @param fsm State Machine environment for simulators
     */
    public void setFsm(SIMStateMachine fsm) {
        this.fsm = fsm;
    }

    /**
     * <pre>
     * unused. null will be returned.
     * 
     * 未使用。 nullが返されます。
     * </pre>
     * 
     * @param node
     * @param data
     * @return null
     */
    @Override
    public Object visit(SimpleNode node, Object data) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * <pre>
     * Returns the result (True / False) of the lower conditional expression.
     * Throws a RuntimeException if the node has more than one child element.
     * 
     * 下位の条件式の結果（True / False）を返します。
     * ノードに複数の子要素がある場合、RuntimeExceptionをスローします。
     * </pre>
     * 
     * @param node
     * @param data
     * @return true / false
     */
    @Override
    public Object visit(ASTFSMCondition node, Object data) {
        int length = node.jjtGetNumChildren();
        if (length == 1) {
            return node.jjtGetChild(0).jjtAccept(this, data);
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * <pre>
     * Returns True if all child elements of the node are True.
     * If there is even one False, return the False.
     * Throw RuntimeException if there are no child elements of the node.
     * 
     * ノードのすべての子要素がTrueの場合、Trueを返します。
     * Falseが1つでもある場合は、Falseを返します。
     * ノードの子要素がない場合はRuntimeExceptionをスローします。
     * </pre>
     * 
     * @param node
     * @param data
     * @return true / false
     */
    @Override
    public Object visit(ASTFSMAndExpression node, Object data) {
        int length = node.jjtGetNumChildren();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                Boolean result = (Boolean) node.jjtGetChild(i).jjtAccept(this, data);
                if (!result) {
                    return false;
                }
            }
            return true;
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * <pre>
     * If any of the child elements of the node is True, True is returned.
     * If all are False, Return False.
     * Throws RuntimeException if there are no child elements of the node.
     * 
     * ノードの子要素のいずれかがTrueの場合、Trueが返されます。
     * すべてがFalseの場合、Falseを返します。
     * ノードの子要素がない場合、RuntimeExceptionをスローします。
     * </pre>
     * 
     * @param node
     * @param data
     * @return true / false
     */
    @Override
    public Object visit(ASTFSMOrExpression node, Object data) {
        int length = node.jjtGetNumChildren();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                Boolean result = (Boolean) node.jjtGetChild(i).jjtAccept(this, data);
                if (result) {
                    return true;
                }
            }
            return false;
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * <pre>
     * If the content of the child element of the node is True, False is returned.
     * Throws a RuntimeException if the node has more than one child element.
     * 
     * ノードの子要素の内容がTrueの場合、Falseが返されます。
     * ノードに複数の子要素がある場合、RuntimeExceptionをスローします。
     * </pre>
     * 
     * @param node
     * @param data
     * @return true / false
     */
    @Override
    public Object visit(ASTFSMNotExpression node, Object data) {
        int length = node.jjtGetNumChildren();
        if (length == 1) {
            Boolean result = (Boolean) node.jjtGetChild(0).jjtAccept(this, data);
            if (result == true) {
                return false;
            } else {
                return true;
            }
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * <pre>
     * True is returned if the left and right sides are equal.
     * If they are not equal, False will be returned.
     * If the number of child elements of node is not 2, a RuntimeException will be thrown.
     * 
     * 左辺と右辺が等しい場合はTrueが返されます。
     * 等しくない場合はFalseを返します。
     * ノードの子要素の数が2でない場合、RuntimeExceptionがスローされます。
     * 
     * Example: True if "A == A"
     *          False if "A == B"
     * </pre>
     * 
     * @param node
     * @param data
     * @return true / false
     */
    @Override
    public Object visit(ASTFSMEqualExpression node, Object data) {
        int length = node.jjtGetNumChildren();
        if (length == 2) {
            String left = (String) node.jjtGetChild(0).jjtAccept(this, data);
            String right = (String) node.jjtGetChild(1).jjtAccept(this, data);
            if (left.equals(right)) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * <pre>
     * False is returned if the left and right sides are equal.
     * If they are not equal, True will be returned.
     * If the number of child elements of node is not 2, a RuntimeException will be thrown.
     * 
     * 左側と右側が等しい場合はFalseが返されます。
     * 等しくない場合はTrueを返します。
     * ノードの子要素の数が2でない場合、RuntimeExceptionがスローされます。
     * 
     * Example: True if "A != B"
     *          False if "A != A"
     * </pre>
     * 
     * @param node
     * @param data
     * @return true / false
     */
    @Override
    public Object visit(ASTFSMNotEqualExpression node, Object data) {
        int length = node.jjtGetNumChildren();
        if (length == 2) {
            String left = (String) node.jjtGetChild(0).jjtAccept(this, data);
            String right = (String) node.jjtGetChild(1).jjtAccept(this, data);
            if (!left.equals(right)) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * <pre>
     * Get the node excluding the first and last characters.
     * 
     * 最初と最後の文字を除くノードの文字列を取得します。
     * </pre>
     * 
     * @param node
     * @param data
     * @return A node string excluding the first and last characters.
     */
    @Override
    public Object visit(ASTFSMStringValueExpression node, Object data) {
        // TODO パーサのバグを一時的にここで対応。先頭と最後の1文字を削除する
        String value = (String) node.jjtGetValue();
        return value.substring(1, value.length() - 1);
    }

    /**
     * <pre>
     * Identify and get the variable value from the variable name.
     * 
     * 変数名から変数値を特定して取得します。
     * </pre>
     * 
     * @param node
     * @param data
     * @return variable value or null
     */
    @Override
    public Object visit(ASTFSMVariableReferenceValueExpression node, Object data) {
        SIMVariable variable = fsm.getVariable((String) node.jjtGetValue());
        if (variable instanceof SIMStringVariable) {
            return ((SIMStringVariable) variable).getValue();
        }
        return null;
    }
}
