package com.zipc.garden.webplatform.dao.rdf.ontology;

/**
 * Garden NameSpace definition
 */
public enum GDNNS {

    /** An ontology common to all models. */
    GARDEN(),

    /** Ontology of feature models. */
    FM("/feature-model"),

    /** Ontology of feature model constraints. */
    FMC("/feature-model-constraint"),

    /** Ontology of test conditions used for ODD and TSD generated from the feature model. */
    TC("/test-condition"),

    /** Ontology of feature pattern generation settings. */
    FPS("/feature-pattern-setting"),

    /** An ontology of feature patterns generated from the feature model. */
    FP("/feature-pattern"),

    /** Ontology of behavior model. */
    BM("/behavior-model"),

    /** An architecture model ontology with relationships between behavior model. */
    ARC("/architecture-model"),

    /** Ontology of behavior pattern generation settings. */
    BPS("/behavior-pattern-setting"),

    /** Ontology of patterns generated from behavior model. */
    BP("/behavior-pattern"),

    /** Ontology of scenario set generation settings. */
    SCSS("/scenarioset-setting"),

    /** Ontology of scenario set. */
    SCS("/scenarioset"),

    /** An instance ontology created by the user using each ontology. */
    UD("/user-data"),

    /** Ontology of scenario. */
    SCENARIO("/scenario");

    /** Base URI that identifies the resource for GARDEN */
    private static final String BASE_URI = "http://www.zipc.com/garden";

    /** URI of each ontology */
    public String URI;

    /** name space */
    public String NS;

    /**
     * <pre>
     * Create an ontology that is common to all models.
     * It is set as follows.
     *  URI : "http://www.zipc.com/garden"
     *  NS  : "http://www.zipc.com/garden#"
     * </pre>
     */
    private GDNNS() {
        this("");
    }

    /**
     * <pre>
     * Creates the specified ontology.
     * (Example) When "FM ("/feature-model")" is specified, it is set as follows.
     *  URI : "http://www.zipc.com/garden/feature-model"
     *  NS  : "http://www.zipc.com/garden/feature-model#"
     * </pre>
     * 
     * @param uri URI specified by ENUM
     */
    private GDNNS(String uri) {
        this.URI = BASE_URI + uri;
        this.NS = URI + "#";
    }
}
