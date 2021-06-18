package com.zipc.garden.job.tp;

import com.zipc.garden.model.fmcs.FMCSAndExpression;
import com.zipc.garden.model.fmcs.FMCSConstraint;
import com.zipc.garden.model.fmcs.FMCSExpression;
import com.zipc.garden.model.fmcs.FMCSFactory;
import com.zipc.garden.model.fmcs.FMCSImpliesExpression;
import com.zipc.garden.model.fmcs.FMCSMutexExpression;
import com.zipc.garden.model.fmcs.FMCSNotExpression;
import com.zipc.garden.model.fmcs.FMCSODElement;
import com.zipc.garden.model.fmcs.FMCSOrExpression;
import com.zipc.garden.model.fmcs.FMCSRemovesExpression;
import com.zipc.garden.model.fmcs.FMCSRoot;
import com.zipc.garden.model.fmcs.FMCSSelectExpression;
import com.zipc.garden.parser.fmc.constraint.ASTAndExpression;
import com.zipc.garden.parser.fmc.constraint.ASTExpression;
import com.zipc.garden.parser.fmc.constraint.ASTIdentifier;
import com.zipc.garden.parser.fmc.constraint.ASTImpliesExpression;
import com.zipc.garden.parser.fmc.constraint.ASTMutexExpression;
import com.zipc.garden.parser.fmc.constraint.ASTNode;
import com.zipc.garden.parser.fmc.constraint.ASTNotExpression;
import com.zipc.garden.parser.fmc.constraint.ASTOrExpression;
import com.zipc.garden.parser.fmc.constraint.ASTParentNode;
import com.zipc.garden.parser.fmc.constraint.ASTRoot;
import com.zipc.garden.parser.fmc.constraint.ASTUnaryExpression;
import com.zipc.garden.parser.fmc.constraint.FMConstraintParserVisitor;
import com.zipc.garden.parser.fmc.constraint.SimpleNode;

/**
 * A visitor class that converts AST to an FMCS class.
 */
public class ASTRoot2FmcsConvertVisitor implements FMConstraintParserVisitor {

    /**
     * <pre>
     * The specified constraint expression is added to the children of FMCSRoot.
     * 
     * 指定した制約式がFMCSRootの子に追加されます。
     * </pre>
     * 
     * @param parent FMCSRoot
     * @param self specified constraint expression
     */
    private void setParent(Object parent, FMCSConstraint self) {
        if (parent instanceof FMCSRoot) {
            ((FMCSRoot) parent).getConstraints().add(self);
        }
    }

    /**
     * <pre>
     * Set the second argument in the expression for the first argument.
     * If the first argument is of an unsupported type, a RuntimeException will be thrown.
     * If the type of the first argument is FMCSImpliesExpression and the expression is already set, a RuntimeException will be thrown.
     * 
     * 第一引数の式に第二引数を設定します。
     * 第一引数がサポートされていない型である場合、RuntimeExceptionがスローされます。
     * 第一引数の型がFMCSImpliesExpressionであり、式がすでに設定されている場合、RuntimeExceptionがスローされます。
     * </pre>
     * 
     * @param parent The types that can be specified are as follows. <br>
     *            FMCSConstraint、 FMCSRemovesExpression、 FMCSImpliesExpression、 FMCSOrExpression、 FMCSAndExpression、
     *            FMCSNotExpression
     * @param self expression
     */
    private void setParent(Object parent, FMCSExpression self) {
        if (parent instanceof FMCSConstraint) {
            ((FMCSConstraint) parent).setExpression(self);
        } else if (parent instanceof FMCSRemovesExpression) {
            ((FMCSRemovesExpression) parent).setExpression(self);
        } else if (parent instanceof FMCSImpliesExpression) {
            FMCSImpliesExpression imp = (FMCSImpliesExpression) parent;
            if (null == imp.getLeftExpression()) {
                imp.setLeftExpression(self);
            } else if (null == imp.getRightExpression()) {
                imp.setRightExpression(self);
            } else {
                throw new RuntimeException("Children of FMCSImpliesExpression are already setted");
            }
        } else if (parent instanceof FMCSOrExpression) {
            ((FMCSOrExpression) parent).getExpressions().add(self);
        } else if (parent instanceof FMCSAndExpression) {
            ((FMCSAndExpression) parent).getExpressions().add(self);
        } else if (parent instanceof FMCSNotExpression) {
            ((FMCSNotExpression) parent).setExpression(self);
        } else {
            throw new RuntimeException("parent[" + parent + "] self[" + self + "] is unsupported");
        }
    }

    /**
     * <pre>
     * Set the second argument in the ODElement for the first argument.
     * If the first argument is of an unsupported type, a RuntimeException will be thrown.
     * 
     * 第一引数のODElementに第二引数を設定します。
     * 第一引数がサポートされていない型である場合、RuntimeExceptionがスローされます。
     * </pre>
     * 
     * @param parent The types that can be specified are as follows. <br>
     *            FMCSSelectExpression、 FMCSMutexExpression、 FMCSRemovesExpression
     * @param self ODElement
     */
    private void setParent(Object parent, FMCSODElement self) {
        if (parent instanceof FMCSSelectExpression) {
            ((FMCSSelectExpression) parent).setOdElement(self);
        } else if (parent instanceof FMCSMutexExpression) {
            ((FMCSMutexExpression) parent).getOdElements().add(self);
        } else if (parent instanceof FMCSRemovesExpression) {
            ((FMCSRemovesExpression) parent).setOdElement(self);
        } else {
            throw new RuntimeException("parent[" + parent + "] self[" + self + "] is unsupported");
        }
    }

    /**
     * <pre>
     * Concatenate the FullName of the first argument (FMCSODElement) with the second argument.
     * 
     * 第一引数（FMCSODElement）のFullNameを第二引数と連結します。
     * </pre>
     * 
     * @param parent FMCSODElement
     * @param self fullName or concatenated characters
     */
    private void setParent(Object parent, String self) {
        if (parent instanceof FMCSODElement) {
            String fullName = ((FMCSODElement) parent).getFullName();
            if (null == fullName) {
                fullName = self;
            } else {
                fullName += self;
            }
            ((FMCSODElement) parent).setFullName(fullName);
        } else {
            throw new RuntimeException("parent[" + parent + "] self[" + self + "] is unsupported");
        }
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
     * Convert the ASTRoot constraint expression to FMCSConstraint and add it to FMCSRoot.
     * If the conversion fails, or if ASTRoot has more than one child, a RuntimeException will be thrown.
     * 
     * ASTRoot制約式をFMCSConstraintに変換し、FMCSRootに追加します。
     * 変換が失敗した場合、またはASTRootに複数の子がある場合、RuntimeExceptionがスローされます。
     * </pre>
     * 
     * @param node
     * @param data
     * @return
     */
    @Override
    public Object visit(ASTRoot node, Object data) {
        FMCSConstraint constraint = FMCSFactory.eINSTANCE.createFMCSConstraint();
        int length = node.jjtGetNumChildren();
        if (length == 1) {
            Object result = node.childrenAccept(this, constraint);
            if (null != result) {
                setParent(data, constraint);
                return (Object) constraint;
            } else {
                throw new RuntimeException("Failed to parse the expression of ASTRoot's children");
            }
        } else {
            throw new RuntimeException("ASTRoot has wrong childlen length[" + length + "]");
        }
    }

    /**
     * <pre>
     * If the number of children of ASTExpression is 1, ASTExpression is converted to FMCSExpression.
     * If the number of children of ASTExpression is 2, ASTExpression will be converted to FMCSRemovesExpression.
     * If the number of children of ASTExpression is not 1 or 2, a RuntimeException will be thrown.
     * 
     * ASTExpressionの子の数が1の場合、ASTExpressionはFMCSExpressionに変換されます。
     * ASTExpressionの子の数が2の場合、ASTExpressionはFMCSRemovesExpressionに変換されます。
     * ASTExpressionの子の数が1または2でない場合、RuntimeExceptionがスローされます。
     * </pre>
     * 
     * @param node
     * @param data
     * @return
     */
    @Override
    public Object visit(ASTExpression node, Object data) {
        int length = node.jjtGetNumChildren();
        if (length == 1) {
            // Childが1つの場合Removesではないので子供に処理をバイパス
            Object result = node.childrenAccept(this, data);
            return result;
        } else if (length == 2) {
            // Childが2つの場合Removes
            FMCSRemovesExpression removes = FMCSFactory.eINSTANCE.createFMCSRemovesExpression();
            node.childrenAccept(this, removes);
            setParent(data, removes);
            return removes;
        } else {
            throw new RuntimeException("ASTExpression has wrong childlen length[" + length + "]");
        }
    }

    /**
     * <pre>
     * If node has one child, ASTImplies is disabled, so convert the child of node to FMCS and respond.
     * If you have two children, ASTImplies are enabled, so create FMCSImplies, convert the children of node to FMCS, store them in FMCSImplies, and respond with FMCSImplies.
     * If the number of children of node is not 1 or 2, a RuntimeException will be thrown.
     * 
     * ノードに子が1つある場合、ASTImpliesは無効になっているため、ノードの子をFMCSに変換して応答します。
     * 子が2つある場合、ASTImpliesが有効になっているため、FMCSImpliesを作成し、ノードの子をFMCSに変換して、FMCSImpliesに格納し、FMCSImpliesで応答します。
     * ノードの子の数が1または2でない場合、RuntimeExceptionがスローされます。
     * </pre>
     * 
     * @param node
     * @param data
     * @return
     */
    @Override
    public Object visit(ASTImpliesExpression node, Object data) {
        int length = node.jjtGetNumChildren();
        if (length == 1) {
            Object result = node.childrenAccept(this, data);
            return result;
        } else if (length == 2) {
            FMCSImpliesExpression imp = FMCSFactory.eINSTANCE.createFMCSImpliesExpression();
            node.childrenAccept(this, imp);
            setParent(data, imp);
            return imp;
        } else {
            throw new RuntimeException("ASTImpliesExpression has wrong childlen length[" + length + "]");
        }
    }

    /**
     * <pre>
     * If the node has one child, ASTOr is disabled, so convert the node's child to FMCS and respond.
     * If the node has multiple children, ASTOr is enabled, so create an FMCSOr, convert the node's children to FMCS, store them in FMCSOr, and respond with FMCSOr.
     * 
     * ノードの子が1つある場合、ASTOrが無効になっているため、ノードの子をFMCSに変換して応答します。
     * ノードに複数の子がある場合、ASTOrが有効になっているため、FMCSOrを作成し、ノードの子をFMCSに変換してFMCSOrに格納し、FMCSOrで応答します。
     * </pre>
     * 
     * @param node
     * @param data
     * @return
     */
    @Override
    public Object visit(ASTOrExpression node, Object data) {
        int length = node.jjtGetNumChildren();
        if (length == 1) {
            Object result = node.childrenAccept(this, data);
            return result;
        } else {
            FMCSOrExpression or = FMCSFactory.eINSTANCE.createFMCSOrExpression();
            node.childrenAccept(this, or);
            setParent(data, or);
            return or;
        }
    }

    /**
     * <pre>
     * If the node has one child, ASTAnd is disabled, so convert the node's child to FMCS and respond.
     * If the node has multiple children, ASTAnd is enabled, so create an FMCSAnd, convert the node's children to FMCS, store them in FMCSAnd, and respond with FMCSAnd.
     * 
     * ノードに子が1つある場合、ASTAndは無効になっているため、ノードの子をFMCSに変換して応答します。
     * ノードに複数の子がある場合、ASTAndが有効になっているため、FMCSAndを作成し、ノードの子をFMCSに変換してFMCSAndに格納し、FMCSAndで応答します。
     * </pre>
     * 
     * @param node
     * @param data
     * @return
     */
    @Override
    public Object visit(ASTAndExpression node, Object data) {
        int length = node.jjtGetNumChildren();
        if (length == 1) {
            Object result = node.childrenAccept(this, data);
            return result;
        } else {
            FMCSAndExpression and = FMCSFactory.eINSTANCE.createFMCSAndExpression();
            node.childrenAccept(this, and);
            setParent(data, and);
            return and;
        }
    }

    /**
     * <pre>
     * If the child of ASTUnaryExpression is ASTNode, ASTUnaryExpression is converted to FMCSSelectExpression and responded with FMCSSelectExpression.
     * If the child of ASTUnaryExpression is not ASTNode, ASTUnaryExpression is converted to FMCSExpression.
     * If the number of children of ASTUnaryExpression is not 1, a RuntimeException will be thrown.
     * 
     * ASTUnaryExpressionの子がASTNodeの場合、ASTUnaryExpressionはFMCSSelectExpressionに変換され、FMCSSelectExpressionで応答されます。
     * ASTUnaryExpressionの子がASTNodeでない場合、ASTUnaryExpressionはFMCSExpressionに変換されます。
     * ASTUnaryExpressionの子の数が1でない場合、RuntimeExceptionがスローされます。
     * </pre>
     * 
     * @param node
     * @param data
     * @return
     */
    @Override
    public Object visit(ASTUnaryExpression node, Object data) {
        int length = node.jjtGetNumChildren();
        if (length == 1) {
            if (node.jjtGetChild(0) instanceof ASTNode) {
                FMCSSelectExpression select = FMCSFactory.eINSTANCE.createFMCSSelectExpression();
                node.childrenAccept(this, select);
                setParent(data, select);
                return select;
            } else {
                Object result = node.childrenAccept(this, data);
                return result;
            }
        } else {
            throw new RuntimeException("ASTUnaryExpression has wrong childlen length[" + length + "]");
        }
    }

    /**
     * <pre>
     * If the child of ASTMutexExpression is ASTNode, it will be converted to FMCSMutexExpression and responded with FMCSMutexExpression.
     * If the child of ASTMutexExpression is not ASTNode, it will be converted to FMCSExpression.
     * If the number of child of ASTMutexExpression is 1, RuntimeException is thrown.
     * 
     * ASTMutexExpressionの子がASTNodeの場合、FMCSMutexExpressionに変換され、FMCSMutexExpressionで応答されます。
     * ASTMutexExpressionの子がASTNodeでない場合、FMCSExpressionに変換されます。
     * ASTMutexExpressionの子の数が1の場合、RuntimeExceptionがスローされます。
     * </pre>
     * 
     * @param node
     * @param data
     * @return
     */
    @Override
    public Object visit(ASTMutexExpression node, Object data) {
        int length = node.jjtGetNumChildren();
        if (length != 1) {
            if (node.jjtGetChild(0) instanceof ASTNode) {
                FMCSMutexExpression mutex = FMCSFactory.eINSTANCE.createFMCSMutexExpression();
                node.childrenAccept(this, mutex);
                setParent(data, mutex);
                return mutex;
            } else {
                Object result = node.childrenAccept(this, data);
                return result;
            }
        } else {
            throw new RuntimeException("ASTMutexExpression has wrong childlen length[" + length + "]");
        }
    }

    /**
     * <pre>
     * ASTNotExpression is converted to FMCSNotExpression and responded with FMCSNotExpression.
     * If the number of children of ASTNotExpression is not 1, a RuntimeException will be thrown.
     * 
     * ASTNotExpressionはFMCSNotExpressionに変換され、FMCSNotExpressionで応答されます。
     * ASTNotExpressionの子の数が1でない場合、RuntimeExceptionがスローされます。
     * </pre>
     * 
     * @param node
     * @param data
     * @return
     */
    @Override
    public Object visit(ASTNotExpression node, Object data) {
        int length = node.jjtGetNumChildren();
        if (length == 1) {
            FMCSNotExpression not = FMCSFactory.eINSTANCE.createFMCSNotExpression();
            node.childrenAccept(this, not);
            setParent(data, not);
            return not;
        } else {
            throw new RuntimeException("ASTNotExpression has wrong childlen length[" + length + "]");
        }
    }

    /**
     * <pre>
     * The ASTNode is converted to an FMCSODElement and responded with an FMCSODElement.
     * 
     * ASTNodeはFMCSODElementに変換され、FMCSODElementで応答されます。
     * </pre>
     * 
     * @param node
     * @param data
     * @return
     */
    @Override
    public Object visit(ASTNode node, Object data) {
        int length = node.jjtGetNumChildren();
        if (0 < length) {
            FMCSODElement element = FMCSFactory.eINSTANCE.createFMCSODElement();
            node.childrenAccept(this, element);
            setParent(data, element);
            return element;
        } else {
            throw new RuntimeException("ASTNode has wrong childlen length[" + length + "]");
        }
    }

    /**
     * <pre>
     * The fullName of the specified FMCSODElement and the colon are combined.
     * 
     * 指定されたFMCSODElementのfullNameとコロンが結合されます。
     * </pre>
     * 
     * @param node
     * @param data FMCSODElement
     * @return
     */
    @Override
    public Object visit(ASTParentNode node, Object data) {
        ASTNode parent = (ASTNode) node.jjtGetParent();
        if (parent.jjtGetChild(0) != node) {
            setParent(data, ":");
        }
        Object result = node.childrenAccept(this, data);
        return result;
    }

    /**
     * <pre>
     * The FullName of the specified FMCSODElement and the file extension are combined.
     * 
     * 指定されたFMCSODElementのFullNameとファイル拡張子が結合されます。
     * </pre>
     * 
     * @param node
     * @param data FMCSODElement
     * @return null
     */
    @Override
    public Object visit(ASTIdentifier node, Object data) {
        ASTParentNode parent = (ASTParentNode) node.jjtGetParent();
        if (parent.jjtGetChild(0) != node) {
            setParent(data, ".");
        }
        Object value = node.jjtGetValue();
        if (value instanceof String) {
            String identifier = new String((String) value);
            setParent(data, identifier);
        }
        return null;
    }

}
