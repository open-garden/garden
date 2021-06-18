package com.zipc.garden.job.ontology.register;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import com.zipc.garden.job.ontology.OntologyUtils;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.tp.TPElement;
import com.zipc.garden.model.tp.TPPatternElement;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tp.TPTSDPattern;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.rdf.ontology.FPO;
import com.zipc.garden.webplatform.dao.rdf.ontology.FPSO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GBO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * A class that registers the ontology model of the feature pattern.
 */
public class FPRegister extends AbstractRegister {

    /**
     * URI of the feature pattern setting file instance
     */
    private String fpsURI;

    /**
     * URI of the node of the feature pattern setting <br>
     * {@link #fpsURI} + "_Node" is set.
     */
    private String nodeURI;

    /**
     * Feature pattern URI <br>
     * {@link #getFileInstanceURI()} + "_Pattern" is set.
     */
    private String patternURI;

    /**
     * constructor
     * @param root fp root
     * @param file fps file
     */
    public FPRegister(AbstractRootElement root, File file) {
        super(root, file);
        fpsURI = GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + FPSO.FPS_NAME;
        nodeURI = fpsURI + SEPARATOR + FPSO.NODE_NAME;
        patternURI = getFileInstanceURI() + SEPARATOR + FPO.PATTERN_NAME;
    }

    /**
     * {@inheritDoc}
     */
    protected String generateFileInstanceURI() {
        File file = getFile();
        return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + FPO.FP_NAME;
    }

    /**
     * <pre>
     * Register the related file (fps file) of the fp file in RDF.
     * 
     * {@inheritDoc}
     * </pre>
     */
    protected boolean generateRefOntology() {
        File refFile = getFile(); // FPのFileはFPSそのものなのでここで登録
        byte[] data = EditResourceUtil.INSTANCE.getFileContent(refFile.getId());
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        if (IRDFRegister.Result.FAIL == OntologyUtils.registAbstractRootElement(root, refFile)) {
            return false;
        }
        return true; // 関連が無いのでNOP
    }

    /**
     * {@inheritDoc}
     */
    protected OntModel generateOntology() {
        OntModel m = ModelFactory.createOntologyModel();

        // NameSpace
        m.setNsPrefix("fp", GDNNS.FP.NS);
        m.setNsPrefix("gdata", GDNNS.UD.NS);

        createClassOntology(m);

        // UserDataOntology
        Ontology ontology = m.createOntology(GDNNS.UD.URI);
        ontology.addProperty(OWL2.imports, GBO.garden);

        // Instance
        Resource instance = m.createOntResource(getFileInstanceURI());
        instance.addProperty(RDF.type, FPO.FeaturePatternSet);
        Resource settingInstance = m.createOntResource(fpsURI);
        instance.addProperty(FPO.refSettingFile, settingInstance);

        TPRoot tpRoot = (TPRoot) getRoot();
        TPSetting tpSetting = (TPSetting) tpRoot.getSettings().get(0);
        for (TPTSDPattern tpPattern : tpSetting.getPatterns()) {
            Resource pattern = m.createOntResource(patternURI + tpPattern.getId());
            pattern.addProperty(RDF.type, OWL2.NamedIndividual);
            pattern.addProperty(RDF.type, FPO.FeaturePattern);
            long patternNo = Long.valueOf(tpPattern.getId());
            pattern.addLiteral(FPO.hasPatternNo, patternNo);
            for (TPElement tpElement : tpPattern.getElements()) {
                String elementName = nodeURI + SEPARATOR + OntologyUtils.encodeStringToAscii(tpElement.getFullPath());
                Resource element = m.createOntResource(elementName);
                pattern.addProperty(FPO.refNode, element);
            }
            for (TPPatternElement tpPatternElement : tpPattern.getPatternElements()) {
                String patternElementURI = patternURI + tpPattern.getId() + SEPARATOR + tpPatternElement.getName();

                Resource patternElementData = m.createOntResource(patternElementURI);
                patternElementData.addProperty(RDF.type, OWL2.NamedIndividual);
                patternElementData.addProperty(RDF.type, FPO.PatternElement);
                patternElementData.addLiteral(FPO.hasPatternElementName, tpPatternElement.getName());
                patternElementData.addLiteral(FPO.hasPatternElementValue, tpPatternElement.getValue());

                Resource patternElement = m.createOntResource(patternElementURI);
                pattern.addProperty(FPO.hasPatternElement, patternElement);
            }
            instance.addProperty(FPO.hasPattern, pattern);
        }
        return m;
    }

    /**
     * Create an ontology definition for FP.
     * @param m FP ontology model
     */
    private void createClassOntology(OntModel m) {
        // Ontology
        Ontology fpOntology = m.createOntology(GDNNS.FP.URI);
        fpOntology.addProperty(OWL2.imports, GBO.garden);

        // Class
        Resource fps = m.createClass(FPO.FeaturePatternSet.getURI());
        fps.addProperty(RDF.type, OWL2.Class);
        Resource fp = m.createClass(FPO.FeaturePattern.getURI());
        fp.addProperty(RDF.type, OWL2.Class);
        Resource patternElement = m.createClass(FPO.PatternElement.getURI());
        patternElement.addProperty(RDF.type, OWL2.Class);
        Property hasPatternNo = m.createProperty(FPO.hasPatternNo.getURI());
        hasPatternNo.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasPatternNo.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Property hasPatternName = m.createProperty(FPO.hasPatternName.getURI());
        hasPatternName.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasPatternName.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Property hasPatternElementName = m.createProperty(FPO.hasPatternElementName.getURI());
        hasPatternElementName.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasPatternElementName.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Property hasPatternElementValue = m.createProperty(FPO.hasPatternElementValue.getURI());
        hasPatternElementValue.addProperty(RDF.type, OWL2.DatatypeProperty);
        hasPatternElementValue.addProperty(RDFS.subPropertyOf, OWL2.topDataProperty);
        Property refSettingFile = m.createProperty(FPO.refSettingFile.getURI());
        refSettingFile.addProperty(RDF.type, OWL2.ObjectProperty);
        refSettingFile.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Property hasPattern = m.createProperty(FPO.hasPattern.getURI());
        hasPattern.addProperty(RDF.type, OWL2.ObjectProperty);
        hasPattern.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Property refNode = m.createProperty(FPO.refNode.getURI());
        refNode.addProperty(RDF.type, OWL2.ObjectProperty);
        refNode.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
        Property hasPatternElement = m.createProperty(FPO.hasPatternElement.getURI());
        hasPatternElement.addProperty(RDF.type, OWL2.ObjectProperty);
        hasPatternElement.addProperty(RDFS.subPropertyOf, OWL2.topObjectProperty);
    }
}
