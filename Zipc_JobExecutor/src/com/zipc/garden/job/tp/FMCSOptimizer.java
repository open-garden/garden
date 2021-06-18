package com.zipc.garden.job.tp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;

import com.zipc.garden.model.fmcs.FMCSAndExpression;
import com.zipc.garden.model.fmcs.FMCSConstraint;
import com.zipc.garden.model.fmcs.FMCSExpression;
import com.zipc.garden.model.fmcs.FMCSImpliesExpression;
import com.zipc.garden.model.fmcs.FMCSMutexExpression;
import com.zipc.garden.model.fmcs.FMCSNotExpression;
import com.zipc.garden.model.fmcs.FMCSODElement;
import com.zipc.garden.model.fmcs.FMCSOrExpression;
import com.zipc.garden.model.fmcs.FMCSRemovesExpression;
import com.zipc.garden.model.fmcs.FMCSRoot;
import com.zipc.garden.model.fmcs.FMCSSelectExpression;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.webplatform.shared.NodeUtil;

/**
 * A class that optimizes the FMCSRoot constraint expression.
 */
public class FMCSOptimizer {

    /** TCRoot excluding invalid nodes */
    TCRoot tsd;

    /** FMCSRoot. When the process is executed, it is optimized based on the contents of {@link #tsd}. */
    FMCSRoot fmcs;

    /**
     * constructor
     * @param tsd TCRoot excluding invalid nodes
     * @param fmcs FMCSRoot before optimization
     */
    public FMCSOptimizer(TCRoot tsd, FMCSRoot fmcs) {
        this.tsd = tsd;
        this.fmcs = fmcs;
    }

    /**
     * Performs FMCSRoot constraint expression optimization.
     * @return FMCSRoot after optimization.
     */
    public FMCSRoot optimize() {
        List<String> nodeName = new ArrayList<String>();
        List<TCNode> nodeList = tsd.getRootNodes();
        nodeName = findTCNode(nodeList, nodeName);
        List<FMCSConstraint> fmcsCs = fmcs.getConstraints();
        List<FMCSConstraint> found = new ArrayList<FMCSConstraint>();
        for (FMCSConstraint fmcsC : fmcsCs) {
            FMCSExpression expression = fmcsC.getExpression();
            ReturnValue value = fineUncheckNode(expression, nodeName);
            if (value == ReturnValue.REMOVE) {
                found.add(fmcsC);
            }
        }
        fmcsCs.removeAll(found);
        return fmcs;
    }

    /**
     * Recursively searches the child nodes of the specified node to get the full path list.
     * @param nodeList specified node
     * @param nodeName Full path list
     * @return Full path list
     */
    private List<String> findTCNode(List<TCNode> nodeList, List<String> nodeName) {
        for (TCNode node : nodeList) {
            if (node.getChildren() != null) {
                findTCNode(node.getChildren(), nodeName);
            }
            nodeName.add(getNodeFullPath(node));
        }

        return nodeName;
    }

    /**
     * The constraint expression is recursively checked and optimized.
     * @param expression Constraint expression
     * @param nodeName Full path list created by TCRoot.
     * @return The return value of the optimization process.
     */
    private ReturnValue fineUncheckNode(FMCSExpression expression, List<String> nodeName) {
        if (expression instanceof FMCSImpliesExpression) {
            FMCSExpression leftExpression = ((FMCSImpliesExpression) expression).getLeftExpression();
            FMCSExpression rightExpression = ((FMCSImpliesExpression) expression).getRightExpression();
            ReturnValue deleteLeft = fineUncheckNode(leftExpression, nodeName);
            ReturnValue deleteRight = fineUncheckNode(rightExpression, nodeName);
            if (deleteLeft == ReturnValue.REMOVE) {
                ((FMCSImpliesExpression) expression).setLeftExpression(null);
                return deleteLeft;
            } else if (deleteRight == ReturnValue.REMOVE) {
                ((FMCSImpliesExpression) expression).setRightExpression(null);
                return deleteRight;
            } else if (deleteLeft == ReturnValue.TAKE) {
                FMCSExpression leftExp = ((FMCSImpliesExpression) expression).getLeftExpression();
                FMCSSelectExpression exp = getChildNode(leftExp);
                ((FMCSImpliesExpression) expression).setLeftExpression(exp);
            } else if (deleteRight == ReturnValue.TAKE) {
                FMCSExpression rightExp = ((FMCSImpliesExpression) expression).getRightExpression();
                FMCSSelectExpression exp = getChildNode(rightExp);
                ((FMCSImpliesExpression) expression).setRightExpression(exp);
            }
            return ReturnValue.KEEP;
        } else if (expression instanceof FMCSAndExpression)

        {
            List<FMCSExpression> expressions = ((FMCSAndExpression) expression).getExpressions();
            List<FMCSExpression> found = new ArrayList<FMCSExpression>();
            for (FMCSExpression exp : expressions) {
                ReturnValue delete = fineUncheckNode(exp, nodeName);
                if (delete == ReturnValue.REMOVE) {
                    found.add(exp);
                } else if (delete == ReturnValue.TAKE) {
                    FMCSSelectExpression e = getChildNode(exp);
                    ((FMCSAndExpression) expression).getExpressions().add(e);
                }
            }
            expressions.removeAll(found);
            if (expressions.size() < 1) {
                return ReturnValue.REMOVE;
            } else if (expressions.size() == 1) {
                return ReturnValue.TAKE;
            }
            return ReturnValue.KEEP;
        } else if (expression instanceof FMCSOrExpression) {
            List<FMCSExpression> expressions = ((FMCSOrExpression) expression).getExpressions();
            List<FMCSExpression> found = new ArrayList<FMCSExpression>();
            for (FMCSExpression exp : expressions) {
                ReturnValue delete = fineUncheckNode(exp, nodeName);
                if (delete == ReturnValue.REMOVE) {
                    found.add(exp);
                } else if (delete == ReturnValue.TAKE) {
                    FMCSSelectExpression e = getChildNode(exp);
                    ((FMCSOrExpression) expression).getExpressions().add(e);
                }
            }
            expressions.removeAll(found);
            if (expressions.size() < 1) {
                return ReturnValue.REMOVE;
            } else if (expressions.size() == 1) {
                return ReturnValue.TAKE;
            }
            return ReturnValue.KEEP;
        } else if (expression instanceof FMCSNotExpression) {
            FMCSExpression exp = ((FMCSNotExpression) expression).getExpression();
            ReturnValue deleteExpression = fineUncheckNode(exp, nodeName);
            if (deleteExpression == ReturnValue.REMOVE) {
                ((FMCSNotExpression) expression).setExpression(null);
                return ReturnValue.REMOVE;
            } else if (deleteExpression == ReturnValue.TAKE) {
                FMCSSelectExpression e = getChildNode(exp);
                ((FMCSNotExpression) expression).setExpression(e);
            }
            return ReturnValue.KEEP;
        } else if (expression instanceof FMCSSelectExpression) {
            FMCSODElement element = ((FMCSSelectExpression) expression).getOdElement();
            boolean isExistence = findNode(element, nodeName);
            if (isExistence == true) {
                ((FMCSSelectExpression) expression).setOdElement(null);
                return ReturnValue.REMOVE;
            }
            return ReturnValue.KEEP;
        } else if (expression instanceof FMCSMutexExpression) {
            List<FMCSODElement> elements = ((FMCSMutexExpression) expression).getOdElements();
            List<FMCSODElement> found = new ArrayList<FMCSODElement>();
            for (FMCSODElement element : elements) {
                boolean isExistence = findNode(element, nodeName);
                if (isExistence == true) {
                    found.add(element);
                }
            }
            elements.removeAll(found);
            if (elements.size() < 2) {
                return ReturnValue.REMOVE;
            }
            return ReturnValue.KEEP;
        } else if (expression instanceof FMCSRemovesExpression) {
            FMCSODElement element = ((FMCSRemovesExpression) expression).getOdElement();
            FMCSExpression exp = ((FMCSRemovesExpression) expression).getExpression();
            boolean isExistence = findNode(element, nodeName);
            if (isExistence == true) {
                ((FMCSRemovesExpression) expression).setOdElement(null);
                return ReturnValue.REMOVE;
            }
            ReturnValue deleteExpression = fineUncheckNode(exp, nodeName);
            if (deleteExpression == ReturnValue.TAKE) {
                FMCSSelectExpression e = getChildNode(exp);
                ((FMCSRemovesExpression) expression).setExpression(e);
            }
            return deleteExpression;
        }
        return ReturnValue.KEEP;
    }

    /**
     * Check if the specified element exists in the full path list.
     * @param element specified element
     * @param nodeName full path list
     * @return True if the value does not exist. False if present.
     */
    private boolean findNode(FMCSODElement element, List<String> nodeName) {
        Optional<String> name = nodeName.stream().filter(i -> i.equals(element.getFullName())).findFirst();
        if (!name.isPresent()) {
            return true;
        }
        return false;
    }

    /**
     * Gets the child elements of an And or Or constraint expression. (Assuming only one child element.)
     * @param exp FMCSAndExpression or FMCSOrExpression
     * @return the child elements
     */
    private FMCSSelectExpression getChildNode(FMCSExpression exp) {
        FMCSExpression e;
        if (exp instanceof FMCSAndExpression) {
            e = ((FMCSAndExpression) exp).getExpressions().get(0);
        } else {
            e = ((FMCSOrExpression) exp).getExpressions().get(0);
        }
        return (FMCSSelectExpression) e;
    }

    /**
     * Gets the full path of the specified node.
     * @param node specified node.
     * @return The full path of the node.
     */
    private String getNodeFullPath(TCNode node) {
        StringBuilder builderNodeName = new StringBuilder(NodeUtil.getInstance().getEscapedNodeName(node.getName()));
        EObject container = node.eContainer();
        while (container instanceof TCNode) {
            String nodeName = NodeUtil.getInstance().getEscapedNodeName(((TCNode) container).getName());
            builderNodeName.insert(0, nodeName + ".");
            container = container.eContainer();
        }
        return builderNodeName.toString();
    }

    /**
     * ENUM that manages the return value type of optimization processing.
     */
    private enum ReturnValue {

        /** Return value when the element not to be deleted. */
        KEEP,

        /** Return value when the element to be deleted. */
        REMOVE,

        /** Return value when the number of nodes becomes one due to the deletion of non-target nodes. */
        TAKE;
    }
}
