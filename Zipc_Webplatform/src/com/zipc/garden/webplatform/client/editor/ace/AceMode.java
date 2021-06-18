package com.zipc.garden.webplatform.client.editor.ace;

import jsinterop.annotations.JsType;

/**
 * A class that summarizes the types of files that use Ace.js
 */
@JsType
public enum AceMode {
    TEXT("ace/mode/text", "txt"), HTML("ace/mode/html", "html"), CSS("ace/mode/css", "css"), JAVA("ace/mode/java", "java"), JAVASCRIPT("ace/mode/javascript",
            "js"), PHP("ace/mode/php", "php"), XML("ace/mode/xml", "xml"), MD("ace/mode/text", "md"), UNDEFINED("ace/mode/text", ""), SPARQL("ace/mode/sparql", "spql");

    /** Value passed to Ace.js */
    private final String value;

    /** File extension */
    private final String extension;

    /**
     * constructor
     * @param value {@link AceMode#value}
     * @param extension {@link AceMode#extension}
     */
    AceMode(String value, String extension) {
        this.value = value;
        this.extension = extension;
    }

    /**
     * Get value
     * @return {@link AceMode#value}
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Get extension
     * @return {@link AceMode#extension}
     */
    public String getExtension() {
        return this.extension;
    }

    /**
     * Get enumeration constant
     * @param extension Extension of enumeration constant to be acquired
     * @return Enumeration constant
     */
    public static AceMode getByExtension(String extension) {
        for (AceMode mode : AceMode.values()) {
            if (mode.getExtension().equals(extension)) {
                return mode;
            }
        }
        return UNDEFINED;
    }
}
