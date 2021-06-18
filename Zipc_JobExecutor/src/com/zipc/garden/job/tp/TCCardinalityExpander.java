package com.zipc.garden.job.tp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

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
import com.zipc.garden.model.tc.TCNodeState;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.webplatform.shared.NodeUtil;

/**
 * This class summarizes the processes that solve the cardinality of TCRoot and FMCSRoot.
 */
public class TCCardinalityExpander {

    /** TCRoot. After executing the process, it becomes the TCRoot with the cardinality resolved. */
    private TCRoot tcRoot;

    /** TCRoot before resolution. */
    private TCRoot copyTcRoot;

    /** FMCSRoot. After executing the process, it becomes the FMCSRoot with the cardinality resolved. */
    private FMCSRoot fmcsRoot;

    /**
     * Executes the process of resolving the cardinality of TCRoot and FMCSRoot.
     * @param tcRoot TCRoot before resolution
     * @param fmcsRoot FMCSRoot before resolution
     * @return true: success / false: failure
     */
    public boolean execute(TCRoot tcRoot, FMCSRoot fmcsRoot) {
        this.tcRoot = tcRoot;
        this.copyTcRoot = EcoreUtil.copy(tcRoot);
        this.fmcsRoot = fmcsRoot;
        if (!expandTCRoot()) {
            return false;
        }
        if (!expandFMCSRoot()) {
            return false;
        }

        // Cardinalityで追加したOFFノードを選択しない制約式の作成
        createNotExpression();
        return true;
    }

    /**
     * Expand the cardinality for the TCRoot.
     * @return true: success / false: failure
     */
    private boolean expandTCRoot() {
        List<TCNode> tcNodes = TCUtil.getAllContentsOfType(tcRoot, TCNode.class);
        Collections.reverse(tcNodes);// 要素を逆順にする。階層の深いノードからチェックする。
        for (TCNode tcNode : tcNodes) {
            // CardinalityはEdgeTypeがXORのノードにのみ設定可能
            if (!ChildType.XOR.equals(tcNode.getChildType())) {
                continue;
            }
            // minが-1の場合はcardinality未対応ノードと判断する
            else if (tcNode.getMin() == -1) {
                continue;
            }
            // minが0またはchildren超過の場合はモデル不正
            else if (tcNode.getMin() == 0 || tcNode.getMin() > tcNode.getChildren().size()) {
                return false;
            }
            // maxが0またはchildren超過の場合はモデル不正
            else if (tcNode.getMax() == 0 || tcNode.getMax() > tcNode.getChildren().size()) {
                return false;
            }

            // maxが-1の場合はchildren数として扱う
            int max = tcNode.getMax() == -1 ? tcNode.getChildren().size() : tcNode.getMax();

            // 子ノードを退避する
            List<TCNode> children = new ArrayList<>(tcNode.getChildren());

            // 子ノードとの関連性を一旦除外する
            if (tcNode.getMin() <= max) {
                tcNode.getChildren().clear();
            }

            // cardinalityを展開
            for (int i = tcNode.getMin(); i <= max; i++) {

                // 子ノードの組み合わせパターンを取得
                List<List<TCNode>> combinationList = getCombination(children, i);

                // ノードを追加（展開）する
                addCombination(tcNode, combinationList);
            }
        }

        return true;
    }

    /**
     * Expand the cardinality for the FMCSRoot.
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
        for (FMCSODElement odElement : odElements) {

            // 展開前TCRootに対して展開前のノード名で検索し、temporaryNodeを除去したフルパスを取得する
            List<String> oldFullNames = getFullNames(copyTcRoot, odElement.getFullName(), false, true);
            if (oldFullNames.isEmpty()) {
                return false;
            }

            // 展開済TCRootに対してtemporaryNodeを除去したフルパスで検索し、 展開済TCNodeのフルパスを取得する
            List<String> fullNames = getFullNames(tcRoot, oldFullNames.get(0), true, false);
            if (fullNames.isEmpty()) {
                return false;
            }
            // 展開後ノードが単一の場合、再構築の対象外
            else if (fullNames.size() == 1) {
                continue;
            }

            // FMCSODElementの親クラスを取得する
            EObject odElementContainer = odElement.eContainer();

            if (odElementContainer instanceof FMCSSelectExpression) {
                FMCSSelectExpression selectExpression = (FMCSSelectExpression) odElementContainer;
                EObject selectExpressionContainer = selectExpression.eContainer();

                // Selectの展開
                if (selectExpressionContainer instanceof FMCSImpliesExpression) {
                    FMCSImpliesExpression impliesExpression = (FMCSImpliesExpression) selectExpressionContainer;
                    FMCSOrExpression or = FMCSFactory.eINSTANCE.createFMCSOrExpression();
                    if (impliesExpression.getLeftExpression().equals(selectExpression)) {
                        impliesExpression.setLeftExpression(or);
                    } else if (impliesExpression.getRightExpression().equals(selectExpression)) {
                        impliesExpression.setRightExpression(or);
                    }
                    for (String fullName : fullNames) {
                        FMCSSelectExpression newSelectExpression = EcoreUtil.copy(selectExpression);
                        newSelectExpression.getOdElement().setFullName(fullName);
                        or.getExpressions().add(newSelectExpression);
                    }
                }
                // Removesの展開
                else if (selectExpressionContainer instanceof FMCSRemovesExpression) {
                    FMCSRemovesExpression removesExpression = (FMCSRemovesExpression) selectExpressionContainer;
                    FMCSOrExpression orExpression = FMCSFactory.eINSTANCE.createFMCSOrExpression();
                    removesExpression.setExpression(orExpression);
                    for (String fullName : fullNames) {
                        FMCSSelectExpression newSelectExpression = EcoreUtil.copy(selectExpression);
                        newSelectExpression.getOdElement().setFullName(fullName);
                        orExpression.getExpressions().add(newSelectExpression);
                    }
                }
            }
            // Removesの展開
            else if (odElementContainer instanceof FMCSRemovesExpression) {
                FMCSRemovesExpression removesExpression = (FMCSRemovesExpression) odElementContainer;
                FMCSConstraint constraint = (FMCSConstraint) removesExpression.eContainer();
                FMCSAndExpression andExpression = FMCSFactory.eINSTANCE.createFMCSAndExpression();
                constraint.setExpression(andExpression);
                for (String fullName : fullNames) {
                    FMCSRemovesExpression newRemovesExpression = EcoreUtil.copy(removesExpression);
                    andExpression.getExpressions().add(newRemovesExpression);
                    newRemovesExpression.getOdElement().setFullName(fullName);
                }
            }
            // Mutexの展開
            else if (odElementContainer instanceof FMCSMutexExpression) {
                FMCSMutexExpression mutexExpression = (FMCSMutexExpression) odElementContainer;
                int idx = mutexExpression.getOdElements().indexOf(odElement);
                mutexExpression.getOdElements().remove(odElement);
                for (String fullName : fullNames) {
                    FMCSODElement newOdElement = EcoreUtil.copy(odElement);
                    newOdElement.setFullName(fullName);
                    mutexExpression.getOdElements().add(idx++, newOdElement);
                }
            }
        }

        return true;
    }

    /**
     * Get TCRoot
     * @return {@link #tcRoot}
     */
    public TCRoot getExpandTCRoot() {
        return this.tcRoot;
    }

    /**
     * Get FMCSRoot
     * @return {@link #fmcsRoot}
     */
    public FMCSRoot getExpandFMCSRoot() {
        return this.fmcsRoot;
    }

    /**
     * <pre>
     * Gets the combination pattern of the cardinality nodes.
     * nCrの組み合わせを取得する
     * </pre>
     * 
     * @param values TCNode used for combination
     * @param size The number to combine from the list of specified values.
     * @return List Combination pattern of child nodes
     */
    private <T> List<List<T>> getCombination(List<T> values, int size) {

        if (0 == size) {
            return Collections.singletonList(Collections.<T> emptyList());
        }

        if (values.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<T>> combination = new LinkedList<List<T>>();

        T actual = values.iterator().next();

        List<T> subSet = new LinkedList<T>(values);
        subSet.remove(actual);

        List<List<T>> subSetCombination = getCombination(subSet, size - 1);

        for (List<T> set : subSetCombination) {
            List<T> newSet = new LinkedList<T>(set);
            newSet.add(0, actual);
            combination.add(newSet);
        }

        combination.addAll(getCombination(subSet, size));

        return combination;
    }

    /**
     * <pre>
     * We will create nodes for the combination of cardinality.
     * 多重度の組み合わせ分ノードを作成する
     * </pre>
     * 
     * @param tcNode Parent node of cardinality node
     * @param combinationList Cardinality combination
     */
    private void addCombination(TCNode tcNode, List<List<TCNode>> combinationList) {

        int i = 0;
        for (List<TCNode> combinationNodes : combinationList) {

            TCNode tempNode = TCFactory.eINSTANCE.createTCNode();
            tempNode.setName(combinationNodes.size() + "_" + ++i);
            tempNode.setChecked(true);
            tempNode.setInherited(true);
            tempNode.setChildType(ChildType.AND);
            tempNode.setState(TCNodeState.UNCHANGED);
            tempNode.setTemporary(true);

            tcNode.getChildren().add(tempNode);

            int j = 0;
            for (TCNode conbinationNode : combinationNodes) {

                TCNode tempNode2 = TCFactory.eINSTANCE.createTCNode();
                tempNode2.setName(String.valueOf(++j));
                tempNode2.setChecked(true);
                tempNode2.setInherited(true);
                tempNode2.setChildType(ChildType.XOR);
                tempNode2.setState(TCNodeState.UNCHANGED);
                tempNode2.setTemporary(true);

                tempNode.getChildren().add(tempNode2);
                tempNode2.getChildren().add(EcoreUtil.copy(conbinationNode));

                if (conbinationNode.getChildren().isEmpty()) {
                    TCNode offNode = TCFactory.eINSTANCE.createTCNode();
                    offNode.setName("OFF");
                    offNode.setChecked(true);
                    offNode.setInherited(true);
                    offNode.setChildType(ChildType.AND);
                    offNode.setState(TCNodeState.UNCHANGED);
                    offNode.setTemporary(true);
                    tempNode2.getChildren().add(offNode);
                }
            }
        }
    }

    /**
     * <pre>
     * Create a constraint expression that "does not select the OFF node added by Cardinality".
     * Cardinalityで追加したOFFノードを選択しない制約式の作成
     * </pre>
     */
    private void createNotExpression() {
        List<TCNode> tcNodes = TCUtil.getAllContentsOfType(tcRoot, TCNode.class);
        List<TCNode> endTempNodes = tcNodes.stream().filter(tcNode -> {
            return tcNode.isTemporary() && tcNode.getChildren().isEmpty() && tcNode.eContainer().eContents().size() > 1;
        }).filter(tcNode -> {
            TCNode parentTCNode = (TCNode) tcNode.eContainer();
            return !(parentTCNode.isTemporary() && "Optional".equals(parentTCNode.getName()));
        }).collect(Collectors.toList());
        if (endTempNodes.size() == 0) {
            return;
        }

        FMCSOrExpression orExpression = FMCSFactory.eINSTANCE.createFMCSOrExpression();

        for (TCNode endTempNode : endTempNodes) {
            FMCSODElement odElement = FMCSFactory.eINSTANCE.createFMCSODElement();
            odElement.setFullName(NodeUtil.getInstance().getTCNodeFullPath(endTempNode, false));

            FMCSSelectExpression selectExpression = FMCSFactory.eINSTANCE.createFMCSSelectExpression();
            selectExpression.setOdElement(odElement);

            orExpression.getExpressions().add(selectExpression);
        }

        FMCSNotExpression notExpression = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        notExpression.setExpression(orExpression);

        FMCSConstraint constraint = FMCSFactory.eINSTANCE.createFMCSConstraint();
        constraint.setExpression(notExpression);

        fmcsRoot.getConstraints().add(constraint);
    }

    /**
     * <pre>
     * From TCRoot, search for nodes that match the specified path, and create and get the full path from the list of found nodes.
     * TCRootから、指定されたパスに一致するノードを検索し、見つかったノードのリストからフルパスを作成して取得します。
     * </pre>
     * 
     * @param tcRoot TCRoot to be searched.
     * @param pathsInFile Path of the specified node
     * @param findSkipTemporary True to skip temporary nodes and search. False if not skipped.
     * @param pathSkipTemporary If True, create the full path without including the temporary node. If False, include.
     * @return Full path list of nodes
     */
    private List<String> getFullNames(TCRoot tcRoot, String pathsInFile, boolean findSkipTemporary, boolean pathSkipTemporary) {

        // TCRootに対してノード名で検索
        List<TCNode> tcNodes = TCUtil.findNodes(tcRoot, pathsInFile, findSkipTemporary);

        // フルパスを取得する
        List<String> fullNames = new ArrayList<String>();
        for (TCNode tcNode : tcNodes) {
            fullNames.add(NodeUtil.getInstance().getTCNodeFullPath(tcNode, pathSkipTemporary));
        }
        return fullNames;
    }
}
