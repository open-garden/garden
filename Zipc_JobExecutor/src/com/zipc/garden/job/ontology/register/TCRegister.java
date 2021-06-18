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
import com.zipc.garden.webplatform.dao.rdf.ontology.TCO;

/**
 * A class that registers the ontology model of the test conditions.
 */
public class TCRegister extends AbstractRegister {

    /**
     * constructor.
     * @param root tc Root
     * @param file tc File
     */
    public TCRegister(AbstractRootElement root, File file) {
        super(root, file);
    }

    /**
     * {@inheritDoc}
     */
    protected String generateFileInstanceURI() {
        File file = getFile();
        return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + TCO.TC_NAME;
    }

    /**
     * {@inheritDoc}
     */
    protected OntModel generateOntology() {
        OntModel m = ModelFactory.createOntologyModel();

        // NameSpace
        m.setNsPrefix("garden", GDNNS.GARDEN.NS);
        m.setNsPrefix("fm", GDNNS.FM.NS);
        m.setNsPrefix("tc", GDNNS.TC.NS);
        m.setNsPrefix("gdata", GDNNS.UD.NS);

        // Ontology
        Ontology tcOntology = m.createOntology(GDNNS.TC.URI);
        tcOntology.addProperty(OWL2.imports, GBO.garden);

        // Class
        Resource tc = m.createClass(TCO.TestCondition.getURI());
        tc.addProperty(RDF.type, OWL2.Class);
        tc.addProperty(RDFS.subClassOf, GBO.File);

        // UserDataOntology
        Ontology ontology = m.createOntology(GDNNS.UD.URI);
        ontology.addProperty(OWL2.imports, GBO.garden);

        // Instance
        Resource instance = m.createOntResource(getFileInstanceURI());
        setFileInstanceInfo(m, instance, TCO.TestCondition);
        return m;
    }
}
