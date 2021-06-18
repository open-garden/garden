package com.zipc.garden.job.ontology.register;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.rdf.ontology.BHVO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GBO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;

/**
 * A class that registers the ontology model of the behavior model.
 */
public class BHVRegister extends AbstractRegister {

    /**
     * constructor.
     * @param root fsm state machine
     * @param file fsm file
     */
    public BHVRegister(AbstractRootElement root, File file) {
        super(root, file);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String generateFileInstanceURI() {
        File file = getFile();
        return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + BHVO.BHV_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected OntModel generateOntology() {
        OntModel ontModel = ModelFactory.createOntologyModel();

        // NameSpace
        ontModel.setNsPrefix("garden", GDNNS.GARDEN.NS);
        ontModel.setNsPrefix("bhv", GDNNS.BM.NS);
        ontModel.setNsPrefix("gdata", GDNNS.UD.NS);

        // Ontology
        Ontology bhvOntology = ontModel.createOntology(GDNNS.BM.URI);
        bhvOntology.addProperty(OWL2.imports, GBO.garden);

        // Class
        Resource bhv = ontModel.createClass(BHVO.BehaviorModel.getURI());
        bhv.addProperty(RDF.type, OWL2.Class);
        bhv.addProperty(RDFS.subClassOf, GBO.File);

        // UserDataOntology
        Ontology ontology = ontModel.createOntology(GDNNS.UD.URI);
        ontology.addProperty(OWL2.imports, GBO.garden);

        // Instance
        Resource instance = ontModel.createOntResource(getFileInstanceURI());
        setFileInstanceInfo(ontModel, instance, BHVO.BehaviorModel);

        return ontModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean generateRefOntology() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setFileInstanceInfo(OntModel m, Resource instance, OntClass instanceClass) {
        instance.addProperty(RDF.type, OWL2.NamedIndividual);
        instance.addProperty(RDF.type, instanceClass);
        instance.addLiteral(GBO.hasFileId, getFile().getId());
        instance.addLiteral(GBO.hasFileName, getFile().getName());
        instance.addLiteral(GBO.hasProjectId, getFile().getProjectid());
        instance.addLiteral(GBO.hasHash, getFile().getHash());
    }

}
