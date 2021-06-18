package com.zipc.garden.job.tp;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.zipc.garden.model.fmcs.FMCSRoot;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCRoot;

/**
 * This class excludes invalid nodes and optimizes constraint expressions.
 */
public class TCDisableNodeRemover {

    /** TCRoot. When executed, invalid nodes are excluded. */
    private TCRoot tcRoot;

    /** FMCSRoot. When executed, the constraint expression is optimized. */
    private FMCSRoot fmcsRoot;

    /**
     * Executes the exclusion process of invalid nodes.
     * @param tcRoot TCRoot before excluding invalid nodes
     * @param fmcsRoot FMCSRoot before excluding invalid nodes
     * @return true: success / false: failure
     */
    public boolean execute(TCRoot tcRoot, FMCSRoot fmcsRoot) {
        this.tcRoot = tcRoot;
        this.fmcsRoot = fmcsRoot;
        if (!removeTCRoot()) {
            return false;
        }
        if (!removeFMCSRoot()) {
            return false;
        }
        return true;
    }

    /**
     * Remove the invalid node from {@link #tcRoot}.
     * @return True if any node is selected. False if the root node is unselected.
     */
    private boolean removeTCRoot() {
        List<TCNode> tcNodes = TCUtil.getAllContentsOfType(tcRoot, TCNode.class);
        for (TCNode tcNode : tcNodes) {
            if (!tcNode.isChecked()) {
                EObject parent = tcNode.eContainer();
                if (null == parent) {
                    tcRoot = null; // ありえないケース
                } else if (parent instanceof TCRoot) {
                    tcRoot = null; // RootNodeが対象外なので全て対象外
                } else if (parent instanceof TCNode) {
                    ((TCNode) parent).getChildren().remove(tcNode);
                } else {
                    tcRoot = null; // ありえないケース
                }
            }
        }
        if (null == tcRoot) {
            return false;
        }
        return true;
    }

    /**
     * Remove the invalid node from {@link #fmcsRoot}.
     * @return true
     */
    private boolean removeFMCSRoot() {
        FMCSOptimizer optimizer = new FMCSOptimizer(tcRoot, fmcsRoot);
        fmcsRoot = optimizer.optimize();
        return true;
    }

    /**
     * Get TCRoot.
     * @return {@link #tcRoot}
     */
    public TCRoot getRemoveTCRoot() {
        return this.tcRoot;
    }

    /**
     * Get FMCSRoot.
     * @return {@link #fmcsRoot}
     */
    public FMCSRoot getRemoveFMCSRoot() {
        return this.fmcsRoot;
    }
}
