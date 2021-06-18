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
import com.zipc.garden.webplatform.dao.rdf.ontology.ARCO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GBO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;

/**
 * A class that registers the ontology model of the architecture model.
 */
public class ARCRegister extends AbstractRegister {

    /**
     * constructor.
     * @param root arc Root
     * @param file arc File
     */
    public ARCRegister(AbstractRootElement root, File file) {
        super(root, file);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String generateFileInstanceURI() {
        File file = getFile();
        return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + ARCO.ARC_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected OntModel generateOntology() {
        OntModel ontModel = ModelFactory.createOntologyModel();

        // NameSpace
        ontModel.setNsPrefix("garden", GDNNS.GARDEN.NS);
        ontModel.setNsPrefix("arc", GDNNS.ARC.NS);
        ontModel.setNsPrefix("bhv", GDNNS.BM.NS);
        ontModel.setNsPrefix("gdata", GDNNS.UD.NS);

        // Ontology
        Ontology arcOntology = ontModel.createOntology(GDNNS.ARC.URI);
        arcOntology.addProperty(OWL2.imports, GBO.garden);

        // Class
        Resource arc = ontModel.createClass(ARCO.ArchitectureModel.getURI());
        arc.addProperty(RDF.type, OWL2.Class);
        arc.addProperty(RDFS.subClassOf, GBO.File);

        // UserDataOntology
        Ontology ontology = ontModel.createOntology(GDNNS.UD.URI);
        ontology.addProperty(OWL2.imports, GBO.garden);

        // Instance
        Resource instance = ontModel.createOntResource(generateFileInstanceURI());
        setFileInstanceInfo(ontModel, instance, ARCO.ArchitectureModel);

        return ontModel;
    }

}
