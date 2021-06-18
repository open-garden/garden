package com.zipc.garden.webplatform.client.editor.fm;

/**
 * A class that summarizes the initial values ​​of variables related to Auto Layout
 */
public final class FMEditorConstants {

    /**
     * Initial value of Auto Horizontal mode<br>
     * Used to automatically arrange nodes from left to right
     */
    public class LeftToRightStrategy {
        /** The drawing start position (Y coordinate) of the node. */
        public static final int PADDING_TOP = 20;

        /** The drawing start position (X coordinate) of the node. */
        public static final int PADDING_LEFT = 20;

        /** Default width between nodes during AutoLayout */
        public static final int GUTTER_WIDTH = 100;

        /** Default height between nodes during AutoLayout */
        public static final int GUTTER_HEIGHT = 40;

        /** Initial display position (X coordinate) of cardinality label */
        public static final int CARDINALITY_OFFSET_X = 10;

        /** Initial display position (Y coordinate) of cardinality label */
        public static final int CARDINALITY_OFFSET_Y = -5;
    }

    /**
     * Initial value of Auto Vertical mode<br>
     * Used to automatically arrange nodes from top to bottom
     */
    public class TopToBottomStrategy {
        /** The drawing start position (Y coordinate) of the node. */
        public static final int PADDING_TOP = 20;

        /** The drawing start position (X coordinate) of the node. */
        public static final int PADDING_LEFT = 20;

        /** Default width between nodes during AutoLayout */
        public static final int GUTTER_WIDTH = 60;

        /** Default height between nodes during AutoLayout */
        public static final int GUTTER_HEIGHT = 60;

        /** Initial display position (X coordinate) of cardinality label */
        public static final int CARDINALITY_OFFSET_X = -10;

        /** Initial display position (Y coordinate) of cardinality label */
        public static final int CARDINALITY_OFFSET_Y = 5;
    }

    /**
     * Radius of circle at contact point<br>
     * 接点にある円の半径
     */
    public static final int OVAL_SIZE = 5;

    /**
     * Upper right triangle with property settings<br>
     * プロパティ設定有の右上の三角
     */
    public static final int TRIANGLE_SIZE = 10;

    /**
     * private constructor to block the instantiation from other class.
     */
    private FMEditorConstants() {
    };
}
