package com.zipc.garden.webplatform.dao.rdf.ontology;

import org.apache.jena.graph.Node;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.RDFS;

/**
 * Scenarioset-setting Ontology
 */
public class SCSSO extends GBO {

    /** "Scenarioset setting" extension name on the URI */
    public static final String SCSS_NAME = "SCSS";

    /**
     * Creates a scenario set generation setting ontology based on the specified URI.
     * @param local specified URI
     * @return A scenario set generation setting ontology
     */
    protected static final Ontology scssOntology(String local) {
        return ModelFactory.createOntologyModel().createOntology(local);
    }

    /**
     * Create a scenario set generation setting class based on the SCSS namespace and arguments.
     * @param local String after the SCSS namespace
     * @return A class that means a scenario set generation setting.
     */
    protected static final OntClass ScenarioSetSetting(String local) {
        return ModelFactory.createOntologyModel().createClass(GDNNS.SCSS.NS + local);
    }

    /** Ontology of scenario set generation settings. */
    public static final Ontology scssOntology = Init.scssOntology();

    /** A class that means a scenario set generation setting. A subclass of File. */
    public static final OntClass ScenarioSetSetting = Init.ScenarioSetSetting();

    /**
     * A class that summarizes the initial processing methods of Ontology and OntClass.
     */
    public static class Init {

        /**
         * Create / Get ontology of scenario set generation settings.
         * @return Ontology of scenario set generation settings.
         */
        public static Ontology scssOntology() {
            return SCSSO.scssOntology(GDNNS.SCSS.URI);
        }

        /**
         * Create / Get a class which means a scenario set generation setting.
         * @return a class which means a scenario set generation setting.
         */
        public static OntClass ScenarioSetSetting() {
            OntClass scss = SCSSO.ScenarioSetSetting("ScenarioSetSetting");
            scss.addProperty(RDFS.subClassOf, GBO.File);
            return scss;
        }
    }

    /**
     * The RDFS vocabulary, expressed for the SPI layer in terms of .graph Nodes.
     */
    @SuppressWarnings("hiding")
    public static class Nodes {

        /** Node of Ontology created by {@link Init#scssOntology()}. */
        public static final Node scssOntology = Init.scssOntology().asNode();

        /** Node of OntClass created by {@link Init#ScenarioSetSetting()}. */
        public static final Node ScenarioSetSetting = Init.ScenarioSetSetting().asNode();
    }
}
