package com.zipc.garden.webplatform.shared;

/**
 * An enumeration type that manages file extension information that can be created in Project Explorer.
 */
public enum Extension {

    SCD("scd", "scd.png", "Scenario Design"),

    FM("fm", "fm.png", "Feature Model"),

    FMC("fmc", "fmc.png", "Feature Model Constraint"),

    TC("tc", "tc.png", "Test Condition"),

    ARC("arc", "arc.png", "Behavior Architecture"),

    FSM("fsm", "fsm.png", "Finite State Machine"),

    FPS("fps", "tps.png", "Feature Pattern Setting"),

    FP("fp", "tp.png", "Feature Pattern"),

    BPS("bps", "bps.png", "Behavior Pattern Setting"),

    BP("bp", "bp.png", "Behavior Pattern"),

    LSC("lsc", "lsc.png", "Logical Scenario"),

    CSC("csc", "csc.png", "Concrete Scenario"),

    SPQL("spql", "spql.png", "SPARQL File"),

    TXT("txt", "txt.gif", "Text File"),

    SCSS("scss", "txt.gif", "Scenario Set Setting"),

    SCS("scs", "txt.gif", "Scenario Set"),

    CSCS("cscs", "txt.gif", "Concrete Scenario Set");

    /** File extension */
    private final String name;

    /** File image icon */
    private final String imgPath;

    /** Official name of the file extension */
    private final String fileName;

    /**
     * constructor
     * @param name {@link #name}
     * @param imgPath {@link #imgPath}
     * @param fileName {@link #fileName}
     */
    private Extension(String name, String imgPath, String fileName) {
        this.name = name;
        this.imgPath = imgPath;
        this.fileName = fileName;
    }

    /**
     * Get file extension
     * @return {@link #name}
     */
    public String getValue() {
        return this.name;
    }

    /**
     * Get file image icon
     * @return {@link #imgPath}
     */
    public String getImgPath() {
        return this.imgPath;
    }

    /**
     * Get the official name of the file extension
     * @return {@link #fileName}
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Get enum based on the specified extension.
     * @param value A value that matches the extension ({@link #name})
     * @return Returns an enum.<br>
     *         If it cannot be obtained, it returns null.
     */
    public static Extension getByCode(String value) {
        for (Extension extension : Extension.values()) {
            if (extension.getValue().equals(value)) {
                return extension;
            }
        }
        return null;
    }
}
