package com.zipc.garden.webplatform.client.editor.tc;

import java.util.LinkedHashMap;
import java.util.Map;

import com.smartgwt.client.util.SC;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.model.tps.TPSRoot;

/**
 * Class that verifies TCModel and creates {@link TCModelParser#parseRoot}.
 */
public class TCModelParser {

    /**
     * Map holding unique TCNode information.<br>
     * key : Unique path <br>
     * value : TCNode managed by TCRoot or TPSRoot
     */
    private Map<String, TCNode> parseRoot = new LinkedHashMap<String, TCNode>();

    /**
     * Method to validate TCNode located in TCRoot.
     * @param root TCRoot
     * @return {@link TCModelParser#parseRoot}
     */
    public Map<String, TCNode> getParseTCRootResult(TCRoot root) {
        if (root.getRootNodes() != null) {
            root.getRootNodes().forEach(node -> {
                parseTCRootNode(node, "");
            });
        }
        return parseRoot;
    }

    /**
     * Method to validate TCNode located in TPSRoot.
     * @param root TPSRoot
     * @return {@link TCModelParser#parseRoot}
     */
    public Map<String, TCNode> getParseTPSRootResult(TPSRoot root) {
        if (root.getRootNodes() != null) {
            root.getRootNodes().forEach(node -> {
                parseTCRootNode(node, "");
            });
        }
        return parseRoot;
    }

    /**
     * Verify the contents of TCNode and store it in parseRoot if it is a unique TCNode.<br>
     * If a child node exists, it is processed recursively.
     * @param targetNode Verification target
     * @param path Unique path. If the process is recursive, the path of the parent node is stored.
     */
    private void parseTCRootNode(TCNode targetNode, String path) {
        path += "/" + targetNode.getName() + targetNode.getChildType().getName() + (targetNode.isOptional() ? "Optional" : "" + targetNode.getMin() + targetNode.getMax());
        final String fullPath = path;
        if (parseRoot.containsKey(fullPath)) {
            SC.warn("This file has duplicated node.</br> Please check " + fullPath + " has same name?");
        }
        parseRoot.put(fullPath, targetNode);
        if (targetNode.getChildren() != null && targetNode.getChildren().size() > 0) {
            targetNode.getChildren().forEach(x -> {
                parseTCRootNode(x, fullPath);
            });
        }
    }
}
