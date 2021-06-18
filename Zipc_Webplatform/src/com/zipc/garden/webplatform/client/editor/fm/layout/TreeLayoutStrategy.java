package com.zipc.garden.webplatform.client.editor.fm.layout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zipc.garden.model.fm.FMNode;

/**
 * An abstract class that manages the processing when the layout mode is selected
 */
public abstract class TreeLayoutStrategy implements LayoutStrategy {
    // **TERMINOLOGY**
    // left to right: 画面上で左から右にノードを並べる
    // top to bottom: 画面上で上から下にノードを並べる
    // horizontal: ノードの兄弟関係方向。レイアウトモードにより画面上の方向は変わる
    // vertical: ノードの親子関係方向（階層）。レイアウトモードにより画面上の方向は変わる

    /** The drawing start position (Y coordinate) of the node. */
    protected final int originTop;

    /** The drawing start position (X coordinate) of the node. */
    protected final int originLeft;

    /** Width between nodes during AutoLayout. */
    protected final int gutterWidth;

    /** Height between nodes during AutoLayout. */
    protected final int gutterHeight;

    /**
     * constructor
     * @param originTop {@link TreeLayoutStrategy#originTop}
     * @param originLeft {@link TreeLayoutStrategy#originLeft}
     * @param gutterWidth {@link TreeLayoutStrategy#gutterWidth}
     * @param gutterHeight {@link TreeLayoutStrategy#gutterHeight}
     */
    public TreeLayoutStrategy(int originTop, int originLeft, int gutterWidth, int gutterHeight) {
        this.originTop = originTop;
        this.originLeft = originLeft;
        this.gutterWidth = gutterWidth;
        this.gutterHeight = gutterHeight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(FMNode rootNode) {
        adjustVerticalLayout(rootNode);
        adjustHorizontalLayout(rootNode);
    }

    /**
     * <pre>
     * The position of the node in the hierarchy below, including the rootNode, is adjusted.
     * In horizontal layout mode, X coordinate is set.
     * In vertical layout mode,  Y coordinate is set.
     * </pre>
     * 
     * @param rootNode Root node
     */
    protected void adjustVerticalLayout(FMNode rootNode) {
        Map<Integer, Integer> virtualSizes = computeVerticalSizes(rootNode, 0);
        int origin = getVerticalOrigin();
        setVerticalOffset(rootNode, origin);
        adjustVerticalOffset(rootNode, origin, 0, virtualSizes);
    }

    /**
     * <pre>
     * Calculate the virtual size of each hierarchy
     * 階層ごとの仮想的なサイズを算出する
     * 
     * The virtual size of the hierarchy is the longest size of the nodes in that hierarchy plus the gutter size.
     * その階層にあるノード中の一番長いサイズにガターサイズを加えたものをその階層の仮想サイズとする
     * </pre>
     * 
     * @param node Calculation target node
     * @param level Calculation target node hierarchy<br>
     *            算出対象ノードの階層（ルート[0]、ルートの子[1]、ルートの孫[2]...となる）
     * @returns key : Calculation target node hierarchy<br>
     *          value : virtual size
     */
    private Map<Integer, Integer> computeVerticalSizes(FMNode node, int level) {
        Map<Integer, Integer> virtualSizes = new HashMap<>();
        List<FMNode> nodes = node.getChildren();
        int size = getVerticalSizeIncludingGutter(node);
        if (virtualSizes.getOrDefault(level, 0) < size) {
            virtualSizes.put(level, size);
        }
        for (FMNode target : nodes) {
            for (Map.Entry<Integer, Integer> entry : computeVerticalSizes(target, level + 1).entrySet()) {
                if (virtualSizes.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                    virtualSizes.put(entry.getKey(), entry.getValue());
                }
            }
        }

        return virtualSizes;
    }

    /**
     * <pre>
     * The positions of child nodes are adjusted.
     * In horizontal layout mode, the X coordinate is set.
     * For vertical layout, the Y coordinate is set.
     * </pre>
     * 
     * @param node Target node
     * @param offset The start position of the node
     * @param level Calculation target node hierarchy
     * @param virtualSizes Virtual size for each hierarchy
     */
    private void adjustVerticalOffset(FMNode node, int offset, int level, Map<Integer, Integer> virtualSizes) {
        List<FMNode> nodes = node.getChildren();
        offset += virtualSizes.get(level);
        for (FMNode target : nodes) {
            setVerticalOffset(target, offset);
            adjustVerticalOffset(target, offset, level + 1, virtualSizes);
        }
    }

    /**
     * <pre>
     * The position of the node in the hierarchy below, including the rootNode, is adjusted.
     * In horizontal layout mode, Y coordinate is set.
     * In vertical layout mode,  X coordinate is set.
     * </pre>
     * 
     * @param rootNode Root node
     */
    protected void adjustHorizontalLayout(FMNode rootNode) {
        Map<Integer, Integer> virtualSizes = computeHorizontalSizes(rootNode);
        int origin = getHorizontalOrigin();
        setHorizontalOffset(rootNode, origin + computeHorizontalOffset(rootNode, virtualSizes));
        adjustHorizontalOffset(rootNode, origin, virtualSizes);
    }

    /**
     * <pre>
     * Calculates the virtual size of sibling nodes.
     * ノードの仮想的な兄弟関係方向のサイズを算出する
     * 
     * The larger of the size of the own node or the sum of the sizes of descendant nodes is the virtual size.
     * 自ノードのサイズか自ノードの子孫ノードのサイズの和のどちらか大きい方を仮想的なサイズとする
     * 
     * Size includes gutter size.
     * なお、サイズの算出にはガターサイズを含める
     * </pre>
     * 
     * @param node Calculation target node
     * @return key: Hash code of Node<br>
     *         value: Virtual size
     */
    private Map<Integer, Integer> computeHorizontalSizes(FMNode node) {
        Map<Integer, Integer> virtualSizes = new HashMap<>();
        List<FMNode> nodes = node.getChildren();
        int size = getHorizontalSizeIncludingGutter(node);
        for (FMNode child : nodes) {
            virtualSizes.putAll(computeHorizontalSizes(child));
        }
        virtualSizes.put(node.hashCode(), Math.max(size, nodes.stream().mapToInt(v -> virtualSizes.get(v.hashCode())).sum()));

        return virtualSizes;
    }

    /**
     * <pre>
     * The positions of child nodes are adjusted.
     * In horizontal layout mode, the Y coordinate is set.
     * For vertical layout, the X coordinate is set.
     * </pre>
     * 
     * @param node Target node
     * @param offset The start position of the node
     * @param virtualSizes Size of node in virtual sibling direction
     */
    private void adjustHorizontalOffset(FMNode node, int offset, Map<Integer, Integer> virtualSizes) {
        List<FMNode> nodes = node.getChildren();
        int size = 0;
        for (FMNode target : nodes) {
            offset += size;
            size = virtualSizes.get(target.hashCode());
            setHorizontalOffset(target, offset + computeHorizontalOffset(target, virtualSizes));
            adjustHorizontalOffset(target, offset, virtualSizes);
        }
    }

    /**
     * <pre>
     * Gets the center position of the child node coordinates.
     * In horizontal layout mode, the Y coordinate is get.
     * In vertical layout mode, the X coordinate is get.
     * </pre>
     * 
     * @param node Target node
     * @param virtualSizes Size of node in virtual sibling direction
     * @return coordinate
     */
    private int computeHorizontalOffset(FMNode node, Map<Integer, Integer> virtualSizes) {
        if (node.getChildren().isEmpty()) {
            return 0;
        } else {
            int size = getHorizontalSizeIncludingGutter(node);
            return (virtualSizes.getOrDefault(node.hashCode(), 80) - size) / 2;
        }
    }

    /**
     * <pre>
     * Gets the drawing start position of the node.
     * In horizontal layout mode, the X coordinate is obtained.
     * In vertical layout mode, the Y coordinate is obtained.
     * </pre>
     */
    protected abstract int getVerticalOrigin();

    /**
     * <pre>
     * Gets the drawing start position of the node.
     * In horizontal layout mode, the Y coordinate is obtained.
     * In vertical layout mode, the X coordinate is obtained.
     * </pre>
     */
    protected abstract int getHorizontalOrigin();

    /**
     * <pre>
     * The coordinates of the node are specified.
     * In horizontal layout mode, offset is set to the X coordinate.
     * In vertical layout mode, offset is set to the Y coordinate.
     * </pre>
     * 
     * @param node Target node
     * @param offset Node coordinates
     */
    protected abstract void setVerticalOffset(FMNode node, int offset);

    /**
     * <pre>
     * The coordinates of the node are specified.
     * In horizontal layout mode, offset is set to the Y coordinate.
     * In vertical layout mode, offset is set to the X coordinate.
     * </pre>
     * 
     * @param node Target node
     * @param offset Node coordinates
     */
    protected abstract void setHorizontalOffset(FMNode node, int offset);

    /**
     * <pre>
     * Gets the size including the gutter for the node.
     * In the case of horizontal layout mode, width is acquired.
     * In the case of vertical layout mode, height is acquired.
     * </pre>
     * 
     * @param node Target node
     * @return Node size including gutter
     */
    protected abstract int getVerticalSizeIncludingGutter(FMNode node);

    /**
     * <pre>
     * Gets the size including the gutter for the node.
     * In the case of horizontal layout mode, height is acquired.
     * In the case of vertical layout mode, width is acquired.
     * </pre>
     * 
     * @param node Target node
     * @return Node size including gutter
     */
    protected abstract int getHorizontalSizeIncludingGutter(FMNode node);
}
