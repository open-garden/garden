package com.zipc.garden.webplatform.shared;

/**
 * ENUM that summarizes the character code
 */
public enum CharaCode {
    UTF8("UTF8"), Shift_JIS("SJIS"), UTF16("UTF-16"), EUC_JP("EUC_JP");

    /** Character code name */
    private final String name;

    /**
     * constructor
     * @param name {@link #name}
     */
    private CharaCode(String name) {
        this.name = name;
    }

    /**
     * Get the character code name
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Get the target character code from the specified string. If it cannot be acquired, UTF8 will be returned.
     * @param value specified string
     * @return the target character code
     */
    public static CharaCode getByName(String value) {
        for (CharaCode charaCode : CharaCode.values()) {
            if (charaCode.getName().equals(value)) {
                return charaCode;
            }
        }
        return CharaCode.UTF8;
    }

    /**
     * Gets the character code that matches the name of the enum constant as declared in the enum declaration.<br>
     * If it cannot be acquired, UTF8 will be returned.
     * @param key Name of enum constant declared in enum declaration
     * @return the target character code name
     */
    public static String getNameByKey(String key) {
        for (CharaCode charaCode : CharaCode.values()) {
            if (charaCode.name().equals(key)) {
                return charaCode.getName();
            }
        }
        return CharaCode.UTF8.getName();
    }
}
