package com.zipc.garden.webplatform.dao.rdf.ontology;

import org.apache.jena.graph.Node;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDFS;

/**
 * Scenario set Ontology
 */
public class SCSO extends GBO {

    /** Scenario set extension name on the URI */
    public static final String SCS_NAME = "SCS";

    /**
     * Creates an scenario set ontology based on the specified URI.
     * @param local specified URI
     * @return Scenario set ontology
     */
    protected static final Ontology scsOntology(String local) {
        return ModelFactory.createOntologyModel().createOntology(local);
    }

    /**
     * Create an scenario set class based on the SCS namespace and arguments..
     * @param local String after the SCS namespace
     * @return Means a scenario set.
     */
    protected static final OntClass ScenarioSet(String local) {
        return ModelFactory.createOntologyModel().createClass(GDNNS.SCS.NS + local);
    }

    /**
     * Create an scenario class based on the SCS namespace and arguments..
     * @param local String after the SCS namespace
     * @return Means one scenario that the scenario set has.
     */
    protected static final OntClass Scenario(String local) {
        return ModelFactory.createOntologyModel().createClass(GDNNS.SCS.NS + local);
    }

    /**
     * Create an DatatypeProperty based on the SCS namespace and arguments.
     * @param local String after the SCS namespace
     * @return DatatypeProperty
     */
    protected static final Property scsProperty(String local) {
        return ModelFactory.createOntologyModel().createDatatypeProperty(GDNNS.SCS.NS + local);
    }

    /**
     * Create an ObjectProperty based on the SCS namespace and arguments.
     * @param local String after the SCS namespace
     * @return ObjectProperty
     */
    protected static final Property scsObjProperty(String local) {
        return ModelFactory.createOntologyModel().createObjectProperty(GDNNS.SCS.NS + local);
    }

    /** Ontology of scenario set. */
    public static final Ontology scsOntology = Init.scsOntology();

    /** Means a scenario set. */
    public static final OntClass ScenarioSet = Init.ScenarioSet();

    /** Means one scenario that the scenario set has. */
    public static final OntClass Scenario = Init.Scenario();

    /**
     * A property that has a scenario no. <br>
     * This must be possessed by an instance of Scenario.
     */
    public static final Property hasScenarioNo = Init.hasScenarioNo();

    /**
     * A property that has a scenario name. <br>
     * This must be possessed by an instance of Scenario.
     */
    public static final Property hasScenarioName = Init.hasScenarioName();

    /**
     * A property that represents a relationship to the scenario set generation settings. <br>
     * This must be possessed by an instance of ScenarioSet.
     */
    public static final Property refSettingFile = Init.refSettingFile();

    /**
     * A property that represents having a scenario. <br>
     * This must be possessed by an instance of ScenarioSet.
     */
    public static final Property hasScenario = Init.hasScenario();

    /**
     * A property that represents a relationship to a pattern. <br>
     * This must be possessed by an instance of Scenario.
     */
    public static final Property refPattern = Init.refPattern();

    /**
     * A class that summarizes the initial processing methods of Ontology, OntClass, and Property.
     */
    public static class Init {

        /**
         * Create / Get ontology of scenario set.
         * @return Ontology of scenario set.
         */
        public static Ontology scsOntology() {
            return SCSO.scsOntology(GDNNS.SCS.URI);
        }

        /**
         * Create / Get a class that means a scenario set.
         * @return A class that means a scenario set.
         */
        public static OntClass ScenarioSet() {
            return SCSO.ScenarioSet("ScenarioSet");
        }

        /**
         * Create / Get a class that means one scenario in a scenario set.
         * @return A class that means one scenario in a scenario set.
         */
        public static OntClass Scenario() {
            return SCSO.Scenario("Scenario");
        }

        /**
         * Create / Get a property that has a scenario no.
         * @return A property that has a scenario no.
         */
        public static Property hasScenarioNo() {
            Property p = scsProperty("hasScenarioNo");
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that has a scenario name.
         * @return A property that has a scenario name.
         */
        public static Property hasScenarioName() {
            Property p = scsProperty("hasScenarioName");
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that represents a relationship to the scenario set generation settings.
         * @return A property that represents a relationship to the scenario set generation settings.
         */
        public static Property refSettingFile() {
            Property p = scsObjProperty("refSettingFile");
            p.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
            return p;
        }

        /**
         * Create / Get a property that represents having a scenario.
         * @return A property that represents having a scenario.
         */
        public static Property hasScenario() {
            Property p = scsObjProperty("hasScenario");
            p.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return p;
        }

        /**
         * Create / Get a property that represents a relationship to a pattern.
         * @return A property that represents a relationship to a pattern.
         */
        public static Property refPattern() {
            Property p = scsObjProperty("refPattern");
            p.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
            return p;
        }
    }

    /**
     * The RDFS vocabulary, expressed for the SPI layer in terms of .graph Nodes.
     */
    @SuppressWarnings("hiding")
    public static class Nodes {

        /** Node of Ontology created by {@link Init#ScenarioSet()}. */
        public static final Node scsOntology = Init.scsOntology().asNode();

        /** Node of OntClass created by {@link Init#ScenarioSet()}. */
        public static final Node ScenarioSet = Init.ScenarioSet().asNode();

        /** Node of OntClass created by {@link Init#Scenario()}. */
        public static final Node Scenario = Init.Scenario().asNode();

        /** Node of Property created by {@link Init#hasScenarioNo()}. */
        public static final Node hasScenarioNo = Init.hasScenarioNo().asNode();

        /** Node of Property created by {@link Init#hasScenarioName()}. */
        public static final Node hasScenarioName = Init.hasScenarioName().asNode();

        /** Node of Property created by {@link Init#refSettingFile()}. */
        public static final Node refSettingFile = Init.refSettingFile().asNode();

        /** Node of Property created by {@link Init#hasScenario()}. */
        public static final Node hasScenario = Init.hasScenario().asNode();

        /** Node of Property created by {@link Init#refPattern()}. */
        public static final Node refPattern = Init.refPattern().asNode();
    }
}
