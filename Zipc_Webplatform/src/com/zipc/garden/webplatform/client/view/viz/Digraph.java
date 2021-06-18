package com.zipc.garden.webplatform.client.view.viz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * A class for managing information to represent directed graphs
 */
public class Digraph {

    /** Graph name */
    private final String graphName;

    /** Information on the nodes to be drawn */
    private List<Node> nodes;

    /** Information on the edges to be drawn */
    private List<Edge> edges;

    /** Graph layout type */
    private Layout layout = Layout.circo;

    /** Determines if and how node overlaps should be removed. */
    private boolean overlap = false;

    /** Controls how, and if, edges are represented. */
    private boolean splines = true;

    /** Node font size */
    private int nodeFontsize = 9;

    /** Node style */
    private String nodeStyle[] = { NodeStyle.solid.name() };

    /** Node font name */
    private FontName fontName = FontName.MSGothic;

    /**
     * constructor.
     * @param graphName {@link #graphName}
     */
    public Digraph(String graphName) {
        this.graphName = graphName;
        nodes = new ArrayList<Digraph.Node>();
        edges = new ArrayList<Digraph.Edge>();
    }

    /**
     * Gets the information of the node to draw.
     * @return {@link #nodes}
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * Set the information of the node to be drawn.
     * @param edges {@link #nodes}
     */
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * Gets the information of the edge to draw.
     * @return {@link #edges}
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * Set the information of the edge to be drawn.
     * @param edges {@link #edges}
     */
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    /**
     * Gets the layout type of the graph.
     * @return {@link #layout}
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * Sets the layout type of the graph.
     * @param layout {@link #layout}
     */
    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    /**
     * Gets whether to remove duplicate nodes.
     * @return {@link #overlap}
     */
    public boolean isOverlap() {
        return overlap;
    }

    /**
     * Sets whether to remove duplicate nodes.
     * @param overlap {@link #overlap}
     */
    public void setOverlap(boolean overlap) {
        this.overlap = overlap;
    }

    /**
     * Gets how the edges are displayed.
     * @return {@link #splines}
     */
    public boolean isSplines() {
        return splines;
    }

    /**
     * Sets how the edges are displayed.
     * @param splines {@link #splines}
     */
    public void setSplines(boolean splines) {
        this.splines = splines;
    }

    /**
     * Gets the font size of the node.
     * @return {@link #nodeFontsize}
     */
    public int getNodeFontsize() {
        return nodeFontsize;
    }

    /**
     * Sets the font size of the node.
     * @param nodeFontsize {@link #nodeFontsize}
     */
    public void setNodeFontsize(int nodeFontsize) {
        this.nodeFontsize = nodeFontsize;
    }

    /**
     * Gets the node style.
     * @return {@link #nodeStyle}
     */
    public NodeStyle[] getNodeStyle() {
        return Arrays.stream(nodeStyle).map(NodeStyle::valueOf).collect(Collectors.toList()).toArray(new NodeStyle[nodeStyle.length]);
    }

    /**
     * Set the node style.
     * @param nodeStyle
     */
    public void setNodeStyle(NodeStyle... nodeStyle) {
        this.nodeStyle = Arrays.stream(nodeStyle).map(NodeStyle::name).collect(Collectors.toList()).toArray(new String[nodeStyle.length]);
    }

    /**
     * Gets the set font name.
     * @return {@link #fontName}
     */
    public FontName getFontName() {
        return fontName;
    }

    /**
     * Set the font name.
     * @param fontName {@link #fontName}
     */
    public void setFontName(FontName fontName) {
        this.fontName = fontName;
    }

    /**
     * Creates nodes and edges based on the dependencies of the specified file.
     * @param fileList VMFile[0] is source, VMFile[1] is target
     */
    public void create(List<VMFile[]> fileList) {
        fileList.forEach(f -> {
            Node sourceNode = new Node(f[0].getUuid(), f[0].getId());
            sourceNode.setLabel(f[0].getName());
            sourceNode.setExtension(f[0].getExtensionStr());
            sourceNode.setFullPath(f[0].getFullPath());
            nodes.add(sourceNode);
            Node targetNode = new Node(f[1].getUuid(), f[1].getId());
            targetNode.setLabel(f[1].getName());
            targetNode.setExtension(f[1].getExtensionStr());
            targetNode.setFullPath(f[1].getFullPath());
            nodes.add(targetNode);
            Edge edge = new Edge(sourceNode, targetNode);
            edges.add(edge);
        });
    }

    /**
     * This class manages the information of the node to be drawn.
     */
    public class Node {

        /** file uuid */
        private final String uuid;

        /** file id */
        private long fileId;

        /** The name of the file displayed on the node */
        private String label = "";

        /** File extension */
        private String extension = "";

        /** Full path of the file */
        private String fullPath = "";

        /** The border color of the node. */
        private String color = "black";

        /** The color used to fill the background of the node */
        private String fillcolor = "white";

        /**
         * constructor
         * @param uuid {@link #uuid}
         * @param fileId {@link #fileId}
         */
        public Node(String uuid, long fileId) {
            this.uuid = uuid;
            this.fileId = fileId;
        }

        /**
         * Gets the UUID of the file.
         * @return {@link #uuid}
         */
        public String getUuid() {
            return uuid;
        }

        /**
         * Gets the ID of the file.
         * @return {@link #fileId}
         */
        public long getFileId() {
            return fileId;
        }

        /**
         * Gets the name of the file.
         * @return {@link #label}
         */
        public String getLabel() {
            return label;
        }

        /**
         * Set the name of the file.
         * @param label {@link #label}
         */
        public void setLabel(String label) {
            this.label = label;
        }

        /**
         * Gets the extension of the file.
         * @return {@link #extension}
         */
        public String getExtension() {
            return extension;
        }

        /**
         * Set the extension of the file.
         * @param extension {@link #extension}
         */
        public void setExtension(String extension) {
            this.extension = extension;
        }

        /**
         * Gets the full Path of the file.
         * @return {@link #fullPath}
         */
        public String getFullPath() {
            return fullPath;
        }

        /**
         * Set the full path of the file.
         * @param fullPath {@link #fullPath}
         */
        public void setFullPath(String fullPath) {
            this.fullPath = fullPath;
        }

        /**
         * Gets the border color of the node.
         * @return {@link #color}
         */
        public String getColor() {
            return color;
        }

        /**
         * Sets the border color of the node.
         * @param color {@link #color}
         */
        public void setColor(String color) {
            this.color = color;
        }

        /**
         * Gets the color used to fill the background of the node.
         * @return {@link #fillcolor}
         */
        public String getFillcolor() {
            return fillcolor;
        }

        /**
         * Sets the color used to fill the background of the node.
         * @param fillcolor {@link #fillcolor}
         */
        public void setFillcolor(String fillcolor) {
            this.fillcolor = fillcolor;
        }

        /**
         * Returns the string (DOT language) that creates the node.
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("\r\n");
            sb.append("\"").append(uuid).append("\"[\r\n");
            if (label != null && !label.isEmpty()) {
                sb.append(" id = \"").append(uuid).append("\"\r\n");
                sb.append(" label = \"").append(label).append(".").append(extension).append("\"\r\n");
                sb.append(" tooltip = \"").append(fullPath).append("\"\r\n");
            }
            sb.append(" style = \"").append("filled").append("\"\r\n");
            sb.append(" color = \"").append(color).append("\"\r\n");
            sb.append(" fillcolor = \"").append(fillcolor).append("\"\r\n");
            sb.append("];").append("\r\n");
            return sb.toString();
        }
    }

    /**
     * This class manages the information of the edge to be drawn.
     */
    public class Edge {

        /** Connection source node */
        private Node source;

        /** Connection target node */
        private Node target;

        /**
         * constructor
         * @param source {@link source}
         * @param target {@link target}
         */
        public Edge(Node source, Node target) {
            this.source = source;
            this.target = target;
        }

        /**
         * Returns a string (DOT language) that creates a line connecting the source and target nodes.
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("\r\n");
            sb.append("\"").append(source.uuid).append("\" -> \"").append(target.uuid).append("\" [").append("\r\n");
            sb.append("];").append("\r\n");
            return sb.toString();
        }
    }

    /**
     * ENUM that manages the layout of the graph
     */
    public enum Layout {
        circo, dot, fdp, neato, osage, sfdp, twopi
    }

    /**
     * ENUM that manages the style of the node
     */
    public enum NodeStyle {
        solid, filled
    }

    /**
     * ENUM that manages the font name of the node
     */
    public enum FontName {
        MeiryoUI("Meiryo UI"),

        MSGothic("MS Gothic");

        /** font name */
        private final String name;

        /**
         * constructor
         * @param name {@link #name}
         */
        private FontName(String name) {
            this.name = name;
        }

        /**
         * Get the font name.
         * @return {@link #name}
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * Create a character string (DOT language) to display the digraph based on the contents set in this class.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\r\n");
        sb.append("digraph ").append(graphName).append(" { \r\n");
        sb.append("  graph [").append("\r\n");
        sb.append("    layout = \"").append(layout).append("\"\r\n");
        sb.append("   ,overlap = \"").append(overlap).append("\"\r\n");
        sb.append("   ,splines = \"").append(splines).append("\"\r\n");
        sb.append("  ];").append("\r\n");
        sb.append("  node [").append("\r\n");
        sb.append("    fontsize = ").append(nodeFontsize).append("\r\n");
        if (nodeStyle != null && nodeStyle.length > 0) {
            sb.append("    ,style = \"").append(String.join(",", nodeStyle)).append("\"\r\n");
        }
        if (fontName != null) {
            sb.append("    ,fontname = \"").append(fontName.getName()).append("\"\r\n");
        }
        sb.append("  ];").append("\r\n");
        if (nodes != null && !nodes.isEmpty()) {
            nodes.forEach(sb::append);
        }
        if (edges != null && !edges.isEmpty()) {
            edges.forEach(sb::append);
        }

        sb.append("}").append("\r\n");
        return sb.toString();
    }
}
