package com.zipc.garden.job.tp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.model.tps.TPSRoot;
import com.zipc.garden.webplatform.shared.NodeUtil;

/**
 * Common class of Test Condition.
 */
public class TCUtil {

    /**
     * <pre>
     * Search TCRoot and get a list of nodes that match the specified path.
     * 
     * TCRoot内でパスに一致するノードの一覧を取得する
     * </pre>
     * 
     * @param tcRoot TCRoot
     * @param pathsInFile Path of the specified node
     * @param skipTemporary True to skip temporary Node. False if not skipped.
     * @return A list of nodes that match the path of the specified node.
     */
    public static List<TCNode> findNodes(TCRoot tcRoot, String pathsInFile, boolean skipTemporary) {
        List<TCNode> findNodes = new ArrayList<>();
        List<TCNode> tcNodes = getAllContentsOfType(tcRoot, TCNode.class);
        List<String> names = NodeUtil.getInstance().splitNode(pathsInFile);
        Collections.reverse(names);
        for (TCNode tcNode : tcNodes) {
            Iterator<String> itr = names.iterator();
            if (isMatch(tcNode, itr, skipTemporary)) {
                findNodes.add(tcNode);
            }
        }
        return findNodes;
    }

    /**
     * Checks if the path of the specified node matches the path of the iterator.
     * @param tcNode Node to check if it matches the iterator path<br>
     *            名前が一致するか調べるノード
     * @param itr Iterator with split paths (reverse order). <br>
     *            カンマで区切られた文字列リストのイテレータ(降順にソート済)
     * @param skipTemporary True to skip temporary Node. False if not skipped.
     * @return true / false
     */
    private static boolean isMatch(TCNode tcNode, Iterator<String> itr, boolean skipTemporary) {
        while (itr.hasNext()) {
            if (!tcNode.getName().equals(itr.next())) {
                return false;
            }
            TCNode parentNode = getParentNode(tcNode, skipTemporary);
            if (null != parentNode) {
                tcNode = parentNode;
            } else {
                if (!itr.hasNext()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * <pre>
     * Gets the parent node of the specified node.
     * 
     * 親ノードを取得する
     * </pre>
     * 
     * @param tcNode specified node.
     * @param skipTemporary True to skip temporary Node. False if not skipped.
     * @return parent Node or null
     */
    public static TCNode getParentNode(TCNode tcNode, boolean skipTemporary) {
        EObject parent = tcNode.eContainer();
        if (!(parent instanceof TCNode)) {
            return null;
        }
        TCNode parentNode = (TCNode) parent;
        if (skipTemporary && parentNode.isTemporary()) {
            return getParentNode(parentNode, skipTemporary);
        }
        return parentNode;
    }

    /**
     * <pre>
     * Get all the same instances as the specified class from within EObject.
     * 
     * EObject内にある指定クラスと同じインスタンスを全て取得する
     * </pre>
     * 
     * @param <T>
     * @param ele EObject to be searched
     * @param type specified class
     * @return A list of specified classes within the EObject.
     */
    public static <T extends EObject> List<T> getAllContentsOfType(EObject ele, Class<T> type) {
        return Lists.newArrayList(Iterators.filter(ele.eAllContents(), type));
    }

    /**
     * <pre>
     * Get the simple path of the specified TCNode.
     * 
     * 指定したtcNodeのシンプルパスを取得する。TPSRoot上のTCNodeを使う場合に使用する。
     * </pre>
     * 
     * @param tpsRoot TPSRoot including TCNode.
     * @param tcNode TCNode that wants to get a simple path.
     * @return simple path of node
     */
    public static String getNodeSimplePath(TPSRoot tpsRoot, TCNode tcNode) {
        // TPSRoot内の全ノードのフルパスリストを生成
        List<TCNode> nodeList = getAllContentsOfType(tpsRoot, TCNode.class);
        List<String> fullPathList = new ArrayList<>();
        for (TCNode node : nodeList) {
            fullPathList.add(NodeUtil.getInstance().getTCNodeFullPath(node, true));
        }

        // 対象ノードのフルパスと全ノードのフルパスからシンプルパスを生成
        List<String> simplePathList = new ArrayList<>();
        for (int i = 0; i < fullPathList.size(); i++) {
            String fullPath = fullPathList.get(i);
            String nodeName = getNodeName(fullPath);
            String simplePath = checkNodePath(fullPathList, nodeName, i);
            simplePathList.add(simplePath);
        }

        // tcNodeと一致するフルパスのシンプルパスを取得
        String fullPath = NodeUtil.getInstance().getTCNodeFullPath(tcNode, true);
        for (int i = 0; i < fullPathList.size(); i++) {
            if (fullPath.equals(fullPathList.get(i))) {
                System.out.println("FullPath[" + fullPath + "] SimplePath[" + simplePathList.get(i) + "]");
                return simplePathList.get(i);
            }
        }
        return null;
    }

    /**
     * <pre>
     * Checks if the specified path exists in the full path list. 
     * It is checked recursively from the end node toward the parent node.
     * 
     * fullPathNameListに対象nodePathが存在するかチェックする
     * 末端ノードから親ノードを再帰的にチェックされます。
     * </pre>
     * 
     * @param fullPathNameList List of full paths to be searched
     * @param nodePath Search value (node ​​path)
     * @param ownPathIndex Index number of the full path being checked.
     * @return nodePath The path of the node when the same path no longer exists.
     */
    private static String checkNodePath(List<String> fullPathNameList, String nodePath, int ownPathIndex) {
        for (int i = 0; i < fullPathNameList.size(); i++) {
            if (i == ownPathIndex) {
                continue;
            }
            String fullPathName = fullPathNameList.get(i);
            int nodeIndex = fullPathName.lastIndexOf(nodePath);
            if (nodeIndex != -1 && nodePath.equals(fullPathName.substring(nodeIndex))) {
                int ownNodeIndex = fullPathNameList.get(ownPathIndex).lastIndexOf(nodePath);
                fullPathName = fullPathNameList.get(ownPathIndex).substring(0, ownNodeIndex);
                nodePath = getNodeName(fullPathName) + nodePath;
                nodePath = checkNodePath(fullPathNameList, nodePath, ownPathIndex);
                break;
            }
        }
        if (nodePath.indexOf(".") == 0) {
            nodePath = nodePath.substring(1, nodePath.length()); // 先頭の.(dot)を削除
        }
        return nodePath;
    }

    /**
     * <pre>
     * Get the terminal node name from the specified full path.
     * 
     * fullPathNameから1ノード分のノード名を取得する
     * </pre>
     * 
     * @param fullPathName specified full path
     * @return terminal node name (with dots)
     */
    private static String getNodeName(String fullPathName) {
        List<String> nodeNames = NodeUtil.getInstance().splitNode(fullPathName);
        return "." + NodeUtil.getInstance().getEscapedNodeName(nodeNames.get(nodeNames.size() - 1));
    }
}
