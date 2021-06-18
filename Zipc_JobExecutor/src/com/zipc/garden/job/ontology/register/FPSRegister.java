package com.zipc.garden.job.ontology.register;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import com.zipc.garden.job.ontology.OntologyUtils;
import com.zipc.garden.job.tp.TCUtil;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCProperty;
import com.zipc.garden.model.tps.TPSRoot;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.rdf.ontology.FPSO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GBO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;
import com.zipc.garden.webplatform.shared.NodeUtil;

/**
 * A class that registers the ontology model of the feature pattern setting.
 */
public class FPSRegister extends AbstractRegister {

    /** The URI of the node. {@link #getFileInstanceURI()} + "_Node" is set. */
    private String nodeURI;

    /** feature pattern setting root model */
    private TPSRoot tpsRoot;

    /**
     * constructor.
     * @param root fps state machine
     * @param file fps file
     */
    public FPSRegister(AbstractRootElement root, File file) {
        super(root, file);
        nodeURI = getFileInstanceURI() + SEPARATOR + FPSO.NODE_NAME;
        tpsRoot = (TPSRoot) root;
    }

    /**
     * {@inheritDoc}
     */
    protected String generateFileInstanceURI() {
        File file = getFile();
        return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + FPSO.FPS_NAME;
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
        m.setNsPrefix("fps", GDNNS.FPS.NS);
        m.setNsPrefix("gdata", GDNNS.UD.NS);

        createClassOntology(m);

        // Ontology
        Ontology fpsOntology = m.createOntology(GDNNS.FPS.URI);
        fpsOntology.addProperty(OWL2.imports, GBO.garden);

        // Class
        Resource fps = m.createClass(FPSO.FeaturePatternSetting.getURI());
        fps.addProperty(RDF.type, OWL2.Class);
        fps.addProperty(RDFS.subClassOf, GBO.File);

        // UserDataOntology
        Ontology ontology = m.createOntology(GDNNS.UD.URI);
        ontology.addProperty(OWL2.imports, GBO.garden);

        // Instance
        Resource instance = m.createOntResource(getFileInstanceURI());
        setFileInstanceInfo(m, instance, FPSO.FeaturePatternSetting);

        // Extended Information
        TPSRoot tpsRoot = (TPSRoot) getRoot();
        TCNode rootTpsNode = tpsRoot.getRootNodes().get(0);
        String rootTpsNodeName = nodeURI + SEPARATOR + OntologyUtils.encodeStringToAscii(rootTpsNode.getName());
        Resource rootNode = m.createOntResource(rootTpsNodeName);
        instance.addProperty(FPSO.hasRootNode, rootNode);
        createNode(m, rootTpsNode);
        return m;
    }

    /**
     * <pre>
     * Recursively transforms TCNode into an RDF model and sets it in the ontology model.
     * 
     * OntModelにTCNodeをRDFモデルに再帰的に変換して格納する
     * </pre>
     * 
     * @param m FPS ontology model
     * @param tpsNode Node information registered in the fps root model.
     */
    private void createNode(OntModel m, TCNode tpsNode) {
        String nodeName = OntologyUtils.encodeStringToAscii(NodeUtil.getInstance().getTCNodeFullPath(tpsNode, true));
        Resource node = m.createOntResource(nodeURI + SEPARATOR + nodeName);
        node.addProperty(RDF.type, OWL2.NamedIndividual);
        node.addProperty(RDF.type, FPSO.Node);
        node.addLiteral(FPSO.hasNodeName, tpsNode.getName());
        node.addLiteral(FPSO.hasFullPath, NodeUtil.getInstance().getTCNodeFullPath(tpsNode, true));
        node.addLiteral(FPSO.hasSimplePath, TCUtil.getNodeSimplePath(tpsRoot, tpsNode));
        node.addLiteral(FPSO.isOptional, tpsNode.isOptional());
        node.addLiteral(FPSO.hasMin, tpsNode.getMin());
        node.addLiteral(FPSO.hasMax, tpsNode.getMax());
        node.addProperty(FPSO.hasChildType, tpsNode.getChildType() == ChildType.AND ? FPSO.ChildTypeAND : FPSO.ChildTypeXOR);

        for (TCProperty tcProperty : tpsNode.getProperties()) {
            if (tcProperty.getKey() == null || tcProperty.getKey().isEmpty()) {
                continue;
            }
            String propertyURI = nodeURI + SEPARATOR + nodeName + SEPARATOR + tcProperty.getKey();

            Resource propResource = m.createOntResource(propertyURI);
            propResource.addProperty(RDF.type, OWL2.NamedIndividual);
            propResource.addProperty(RDF.type, FPSO.UserProperty);
            propResource.addLiteral(FPSO.hasKey, tcProperty.getKey());
            propResource.addLiteral(FPSO.hasValue, tcProperty.getValue());

            Resource hasUserProperty = m.createOntResource(propertyURI);
            node.addProperty(FPSO.hasUserProperty, hasUserProperty);
        }

        for (TCNode childTpsNode : tpsNode.getChildren()) {
            String childNodeName = OntologyUtils.encodeStringToAscii(NodeUtil.getInstance().getTCNodeFullPath(childTpsNode, true));
            Resource childNode = m.createOntResource(nodeURI + SEPARATOR + childNodeName);
            node.addProperty(FPSO.hasChildNode, childNode);
        }

        for (TCNode childNode : tpsNode.getChildren()) {
            createNode(m, childNode);
        }
    }

    /**
     * Create an ontology definition for FPS.
     * @param m FPS ontology model
     */
    private void createClassOntology(OntModel m) {
        // Ontology
        Ontology fpsOntology = m.createOntology(GDNNS.FPS.URI);
        fpsOntology.addProperty(OWL2.imports, GBO.garden);

        // Class
        Resource fps = m.createClass(FPSO.FeaturePatternSetting.getURI());
        fps.addProperty(RDF.type, OWL2.Class);
        Resource node = m.createClass(FPSO.Node.getURI());
        node.addProperty(RDF.type, OWL2.Class);
        Resource childType = m.createClass(FPSO.ChildType.getURI());
        childType.addProperty(RDF.type, OWL2.Class);
        Resource userProperty = m.createClass(FPSO.UserProperty.getURI());
        userProperty.addProperty(RDF.type, OWL2.Class);
        Resource childTypeAND = m.createClass(FPSO.ChildTypeAND.getURI());
        childTypeAND.addProperty(RDF.type, OWL2.Class);
        childTypeAND.addProperty(RDFS.subClassOf, FPSO.ChildType);
        Resource childTypeXOR = m.createClass(FPSO.ChildTypeXOR.getURI());
        childTypeXOR.addProperty(RDF.type, OWL2.Class);
        childTypeXOR.addProperty(RDFS.subClassOf, FPSO.ChildType);
        Resource hasNodeName = m.createProperty(FPSO.hasNodeName.getURI());
        hasNodeName.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasNodeName.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasFullPath = m.createProperty(FPSO.hasFullPath.getURI());
        hasFullPath.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasFullPath.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasSimplePath = m.createProperty(FPSO.hasSimplePath.getURI());
        hasSimplePath.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasSimplePath.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource isOptional = m.createProperty(FPSO.isOptional.getURI());
        isOptional.addProperty(RDF.type, OWL2.DatatypeProperty);
        isOptional.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasMin = m.createProperty(FPSO.hasMin.getURI());
        hasMin.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasMin.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasMax = m.createProperty(FPSO.hasMax.getURI());
        hasMax.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasMax.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource isChecked = m.createProperty(FPSO.isChecked.getURI());
        isChecked.addProperty(RDF.type, OWL2.DatatypeProperty);
        isChecked.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasRootNode = m.createProperty(FPSO.hasRootNode.getURI());
        hasRootNode.addProperty(RDF.type, OWL2.ObjectProperty);
        hasRootNode.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource hasChildNode = m.createProperty(FPSO.hasChildNode.getURI());
        hasChildNode.addProperty(RDF.type, OWL2.ObjectProperty);
        hasChildNode.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource hasChildType = m.createProperty(FPSO.hasChildType.getURI());
        hasChildType.addProperty(RDF.type, OWL2.ObjectProperty);
        hasChildType.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource hasUserProperty = m.createProperty(FPSO.hasUserProperty.getURI());
        hasUserProperty.addProperty(RDF.type, OWL2.ObjectProperty);
        hasUserProperty.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Resource hasKey = m.createProperty(FPSO.hasUserProperty.getURI());
        hasKey.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasKey.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Resource hasValue = m.createProperty(FPSO.hasUserProperty.getURI());
        hasValue.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasValue.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
    }
}
