package com.zipc.garden.job.ontology.register;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.rdf.ontology.GBO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;
import com.zipc.garden.webplatform.dao.rdf.ontology.SCSSO;

/**
 * A class that registers the ontology model of the scenario set generation settings.
 */
public class SCSSRegister extends AbstractRegister {

    /**
     * constructor.
     * @param root scss Root
     * @param file scss File
     */
    public SCSSRegister(AbstractRootElement root, File file) {
        super(root, file);
    }

    /**
     * {@inheritDoc}
     */
    protected OntModel generateOntology() {
        File file = getFile();
        OntModel m = ModelFactory.createOntologyModel();

        // Ontology
        Ontology scssOntology = m.createOntology(GDNNS.SCSS.URI);
        scssOntology.addProperty(OWL2.imports, GBO.garden);

        // Class
        Resource scss = m.createClass(SCSSO.ScenarioSetSetting.getURI());
        scss.addProperty(RDF.type, OWL2.Class);
        scss.addProperty(RDFS.subClassOf, GBO.File);

        // UserDataOntology
        Ontology ontology = m.createOntology(GDNNS.UD.URI);
        ontology.addProperty(OWL2.imports, GBO.garden);

        // Instance
        Resource instance = m.createOntResource(getFileInstanceURI());
        setFileInstanceInfo(m, instance, SCSSO.ScenarioSetSetting);
        return m;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String generateFileInstanceURI() {
        File file = getFile();
        return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + SCSSO.SCSS_NAME;
    }
}
