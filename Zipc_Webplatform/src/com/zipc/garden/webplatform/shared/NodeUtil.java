package com.zipc.garden.webplatform.shared;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tp.TPFactory;
import com.zipc.garden.model.tp.TPPatternElement;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tps.TPSRoot;

/**
 * A class that summarizes the processing related to feature-pattern nodes and test-condition nodes.
 */
public class NodeUtil {

    /**
     * private constructor to block the instantiation from other class.
     */
    private NodeUtil() {
    }

    /**
     * method to get the instance
     * @return This class
     */
    public static NodeUtil getInstance() {
        return NodeUtilHolder.INSTANCE;
    }

    /**
     * class to hold the instance as final
     */
    private static class NodeUtilHolder {
        private static final NodeUtil INSTANCE = new NodeUtil();
    }

    /**
     * Decompose the path of each node and return a list
     * @param path Path to decompose
     * @return list of node
     */
    public List<String> splitNode(String path) {
        char[] pathCharcters = path.toCharArray();
        List<String> result = new ArrayList<String>();
        String tmpPath = "";
        int i = 0;
        boolean isFirstChar = true;
        boolean isParenthetical = false;
        boolean escapeFlag = false;
        while (i < pathCharcters.length) {
            char character = pathCharcters[i];
            if (isFirstChar && character == '"') {
                i++;
                isFirstChar = false;
                isParenthetical = true;
                continue;
            }
            if (character == '\\') {
                if (escapeFlag) {
                    tmpPath += character;
                    escapeFlag = false;
                } else {
                    escapeFlag = true;
                }
            } else if (!isParenthetical && character == '.') {
                result.add(tmpPath);
                tmpPath = "";
                isFirstChar = true;
            } else if (!escapeFlag && character == '"') {
                isParenthetical = false;
            } else {
                tmpPath += character;
                escapeFlag = false;
            }
            i++;
            if (i >= pathCharcters.length) {
                result.add(tmpPath);
            }
        }
        return result;
    }

    /**
     * Overload of {@link #getEndNodeParent(TCNode, boolean)}. <br>
     * Call the method with the first argument "tcNode" and the second argument "true".
     * @param tcNode TCNode that gets the parent node of the end node
     * @return parent TCNode of terminal TCNode
     */
    public List<TCNode> getEndNodeParent(TCNode tcNode) {
        return getEndNodeParent(tcNode, true);
    }

    /**
     * Argument: Based on tcNode, get parent TCNode of terminal TCNode
     * @param tcNode TCNode that gets the parent node of the end node
     * @param confirmCheck True to get only checked terminal TCNodes. False to get all terminal TCNodes.
     * @return parent TCNode of terminal TCNode
     */
    public List<TCNode> getEndNodeParent(TCNode tcNode, boolean confirmCheck) {
        List<TCNode> endTcNodes = new ArrayList<>();
        getEndNode(tcNode, endTcNodes, confirmCheck);

        List<TCNode> endNodeParents = new ArrayList<>();
        endTcNodes.forEach(endTcNode -> {
            TCNode endNodeParent = (TCNode) endTcNode.eContainer();
            if (!endNodeParents.contains(endNodeParent)) {
                endNodeParents.add(endNodeParent);
            }
        });
        return endNodeParents;
    }

    /**
     * Get the terminal TCNode and store the result in the argument: endNodes.
     * @param tcNode TCNode to get the end node
     * @param endNodes Storage location of the acquired end node
     * @param confirmCheck True to get only checked terminal TCNodes. False to get all terminal TCNodes.
     */
    private void getEndNode(TCNode tcNode, List<TCNode> endNodes, boolean confirmCheck) {
        tcNode.getChildren().stream().filter(childNode -> !confirmCheck || childNode.isChecked()).forEach(childNode -> {
            if (childNode.getChildren().isEmpty()) {
                endNodes.add(childNode);
            } else {
                getEndNode(childNode, endNodes, confirmCheck);
            }
        });
    }

    /**
     * Convert FullPath to SimplePath
     * @param fullPaths Full path list to convert
     * @return List of simple paths
     */
    public List<String> getUniqueShortestPaths(List<String> fullPaths) {
        List<String> result = new ArrayList<>();
        fullPaths.forEach(fullPath -> {
            List<String> spPath = splitNode(fullPath);
            Collections.reverse(spPath);
            StringBuilder shortestPath = new StringBuilder("");
            for (String nodeName : spPath) {
                nodeName = getEscapedNodeName(nodeName);
                if (shortestPath.toString().length() == 0) {
                    shortestPath.append(nodeName);
                } else {
                    shortestPath.insert(0, ".").insert(0, nodeName);
                }
                List<String> findList = fullPaths.stream().filter(findPath -> findPath.endsWith("." + shortestPath.toString())).collect(Collectors.toList());
                if (findList.size() > 1) {
                    continue;
                } else {
                    break;
                }
            }
            result.add(shortestPath.toString());
        });
        return result;
    }

    /**
     * 「"」,「\」 are escaped.<br>
     * If you use 「"」, 「.」, 「\」, 「space」, enclose it in double quotation marks.
     * @param nodeName Single node name
     * @return Character string after conversion
     */
    public String getEscapedNodeName(String nodeName) {
        String convertedPath = nodeName;
        if (convertedPath.matches("^(?=.*(\\s|\\.|\\\\|\")).*$")) {
            convertedPath = "\"" + convertedPath + "\"";
        }
        if (convertedPath.indexOf("\"") == 0 && convertedPath.lastIndexOf("\"") == convertedPath.length() - 1) {
            String tmp = convertedPath.substring(1, convertedPath.length() - 1).replaceAll("\\\\", "\\\\\\\\");
            tmp = tmp.replaceAll("\\\"", "\\\\\"");
            convertedPath = "\"" + tmp + "\"";
        } else {
            convertedPath = convertedPath.replaceAll("\\\\", "\\\\\\\\");
            convertedPath = convertedPath.replaceAll("\\\"", "\\\\\"");
        }
        return convertedPath;
    }

    /**
     * Remove the escape character from the node path and get the result.
     * @param path The path of the node that contains the escape character
     * @return Path of node that does not include escape character
     */
    public String removeEscape(String path) {
        return String.join(".", splitNode(path));
    }

    /**
     * Based on the parent node of each end node,The node path of the header displayed in TPViewer is created.
     * @param tpsRoot Root class with nodes used for headers
     * @param setting The header created will be set to this
     */
    public void headerUpdate(TPSRoot tpsRoot, TPSetting setting) {
        setting.getHeader().clear();
        List<TCNode> endNodeParents = getEndNodeParent(tpsRoot.getRootNodes().stream().findFirst().get());
        endNodeParents.forEach(endNodeParent -> {
            String fullPath = NodeUtil.getInstance().getEscapedNodeName(endNodeParent.getName());
            EObject eObject = endNodeParent.eContainer();
            while (eObject instanceof TCNode) {
                fullPath = NodeUtil.getInstance().getEscapedNodeName(((TCNode) eObject).getName()) + "." + fullPath;
                eObject = eObject.eContainer();
            }
            setting.getHeader().add(fullPath);
        });
    }

    /**
     * The pattern element set in TPSRoot is reflected in TPSetting.
     * @param tpsRoot Source of pattern element
     * @param setting The pattern element created will be set to this
     */
    public void patternElementUpdate(TPSRoot tpsRoot, TPSetting setting) {
        setting.getPatternElements().clear();
        tpsRoot.getPatternElements().forEach(el -> {
            TPPatternElement patternElement = TPFactory.eINSTANCE.createTPPatternElement();
            patternElement.setChecked(true);
            patternElement.setName(el.getName());
            patternElement.setValue(el.getValue());
            setting.getPatternElements().add(patternElement);
        });
    }

    /**
     * Get the full path of TCNode
     * @param tcNode Instance of node
     * @param skipTemporary True to create a full path without including temporary nodes
     * @return full path
     */
    public String getTCNodeFullPath(TCNode tcNode, boolean skipTemporary) {
        StringBuilder builderNodeName = new StringBuilder(getEscapedNodeName(tcNode.getName())); // 指定した自ノードはスキップしない
        EObject container = tcNode.eContainer();
        while (container instanceof TCNode) {
            TCNode parentNode = (TCNode) container;
            if (!(skipTemporary && parentNode.isTemporary())) {
                String nodeName = getEscapedNodeName(((TCNode) container).getName());
                builderNodeName.insert(0, nodeName + ".");
            }
            container = container.eContainer();
        }
        return builderNodeName.toString();
    }
}
