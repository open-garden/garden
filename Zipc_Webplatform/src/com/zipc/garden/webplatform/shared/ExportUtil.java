package com.zipc.garden.webplatform.shared;

import java.util.ArrayList;
import java.util.List;

/**
 * class that manages the export condition of the feature pattern file.
 */
public class ExportUtil {

    /** Comma separated characters */
    public static final String COMMA_SIGN = ",";

    /** Tab separated characters */
    public static final char TAB_SIGN = '\t';

    /**
     * Define the format of the file to be exported
     */
    public enum FileType {
        CSV("CSV"),

        TSV("TSV");

        /** Enumerator value */
        private String fileType;

        /**
         * constructor
         * @param fileType {@link #fileType}
         */
        private FileType(String fileType) {
            this.fileType = fileType;
        }

        /**
         * Get ENUM definition contents
         * @return {@link #fileType}
         */
        public String getValue() {
            return this.fileType;
        }

        /**
         * Get all the definition contents of this ENUM.
         * @return All definitions
         */
        public static String[] getValues() {
            List<String> ret = new ArrayList<>();
            for (FileType fileType : values()) {
                ret.add(fileType.getValue());
            }
            return ret.toArray(new String[ret.size()]);
        }
    }

    /**
     * Defines the Choices of whether to display the header of the exported file.
     */
    public enum Title {
        SHOW("On"),

        HIDE("Off");

        /** Enumerator value */
        private String title;

        /**
         * constructor
         * @param title {@link #title}
         */
        private Title(String title) {
            this.title = title;
        }

        /**
         * Get ENUM definition contents
         * @return {@link #title}
         */
        public String getValue() {
            return this.title;
        }

        /**
         * Get all the definition contents of this ENUM.
         * @return All definitions
         */
        public static String[] getValues() {
            List<String> ret = new ArrayList<>();
            for (Title title : values()) {
                ret.add(title.getValue());
            }
            return ret.toArray(new String[ret.size()]);
        }
    }

    /**
     * Define the Choices whether to target only the data that matches the entered pattern number.
     */
    public enum Pattern {
        DEPENDS_ON_PATTERN_FILE("Depends on pattern file"),

        ALL_PATTERNS("All patterns");

        /** Enumerator value */
        private String pattern;

        /**
         * constructor
         * @param pattern {@link #pattern}
         */
        private Pattern(String pattern) {
            this.pattern = pattern;
        }

        /**
         * Get ENUM definition contents
         * @return {@link #pattern}
         */
        public String getValue() {
            return this.pattern;
        }

        /**
         * Get all the definition contents of this ENUM.
         * @return All definitions
         */
        public static String[] getValues() {
            List<String> ret = new ArrayList<>();
            for (Pattern pattern : values()) {
                ret.add(pattern.getValue());
            }
            return ret.toArray(new String[ret.size()]);
        }
    }

    /**
     * Define the display format choices for the exported Node path.
     */
    public enum Path {
        DEPENDS_ON_SETTING_FILE("Depends on setting file"),

        FULL_PATH("FullPath"),

        SIMPLE_PATH("SimplePath");

        /** Enumerator value */
        private String path;

        /**
         * constructor
         * @param path {@link #path}
         */
        private Path(String path) {
            this.path = path;
        }

        /**
         * Get ENUM definition contents
         * @return {@link #path}
         */
        public String getValue() {
            return this.path;
        }

        /**
         * Get all the definition contents of this ENUM.
         * @return All definitions
         */
        public static String[] getValues() {
            List<String> ret = new ArrayList<>();
            for (Path path : values()) {
                ret.add(path.getValue());
            }
            return ret.toArray(new String[ret.size()]);
        }
    }

    /**
     * The choice of whether to output the risk importance is defined.
     */
    public enum Show {
        DEPENDS_ON_SETTING_FILE("Depends on setting file"),

        ALL_ON("All on"),

        OFF("Off");

        /** Enumerator value */
        private String show;

        /**
         * constructor
         * @param show {@link #show}
         */
        private Show(String show) {
            this.show = show;
        }

        /**
         * Get ENUM definition contents
         * @return {@link #show}
         */
        public String getValue() {
            return this.show;
        }

        /**
         * Get all the definition contents of this ENUM.
         * @return All definitions
         */
        public static String[] getValues() {
            List<String> ret = new ArrayList<>();
            for (Show show : values()) {
                ret.add(show.getValue());
            }
            return ret.toArray(new String[ret.size()]);
        }
    }
}
