package com.zipc.garden.job.tp;

import java.util.List;

import com.zipc.garden.job.tp.TCResolver.TCCopier;
import com.zipc.garden.job.tp.TCResolver.TCResolveResult;
import com.zipc.garden.model.fmcs.FMCSODElement;
import com.zipc.garden.model.fmcs.FMCSRoot;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.webplatform.shared.NodeUtil;

/**
 * A class that expands and resolves optional nodes.
 */
public class TCOptionalExpander {

    /** TCRoot. The result after expanding the option node is not reflected. */
    private TCRoot tcRoot;

    /** FMCSRoot. The result after expanding the option node is reflected. */
    private FMCSRoot fmcsRoot;

    /** An entry class with the copied TPRoot as the key and the copy class as the value. */
    private TCResolveResult<TCRoot, TCCopier> tcResolveResult;

    /**
     * The optional node expand is executed.
     * @param tcRoot tc root
     * @param fmcsRoot fmcs root
     * @return true: success / false: failure
     */
    public boolean execute(TCRoot tcRoot, FMCSRoot fmcsRoot) {
        this.tcRoot = tcRoot;
        this.fmcsRoot = fmcsRoot;
        if (!expandTCRoot()) {
            return false;
        }
        if (!expandFMCSRoot()) {
            return false;
        }
        return true;
    }

    /**
     * The optional node expand of TCRoot is executed.
     * @return true: success / false: failure
     */
    private boolean expandTCRoot() {
        tcResolveResult = TCResolver.copy(tcRoot);
        if (null == tcResolveResult || tcResolveResult.getKey() == null || tcResolveResult.getValue() == null) {
            return false;
        }
        return true;
    }

    /**
     * The optional node expand of FMCSRoot is executed.
     * @return true: success / false: failure
     */
    private boolean expandFMCSRoot() {
        if (null == fmcsRoot) {
            return true;
        }
        if (0 == fmcsRoot.getConstraints().size()) {
            return true;
        }
        List<FMCSODElement> odElements = TCUtil.getAllContentsOfType(fmcsRoot, FMCSODElement.class);
        TCRoot resolveTcRoot = tcResolveResult.getKey();
        for (FMCSODElement odElement : odElements) {
            List<TCNode> tcNodes = TCUtil.findNodes(resolveTcRoot, odElement.getFullName(), true); // 展開済TCRootに対して展開前のノード名で検索
            if (tcNodes.isEmpty()) {
                return false;
            }
            TCNode tcNode = tcNodes.get(0); // オリジナルにOptionalノードを付与しただけなので1つしか取れない前提
            String fullPath = NodeUtil.getInstance().getTCNodeFullPath(tcNode, false); // Optional付きのフルパスを取得
            odElement.setFullName(fullPath);
        }
        return true;
    }

    /**
     * Get a copy of the optional resolved TCRoot.
     * @return {@link #tcResolveResult}
     */
    public TCResolveResult<TCRoot, TCCopier> getExpandTCResolveResult() {
        return this.tcResolveResult;
    }

    /**
     * The FMCSRoot after expanding the optional node is acquired.
     * @return {@link #fmcsRoot}}
     */
    public FMCSRoot getExpandFMCSRoot() {
        return this.fmcsRoot;
    }
}
