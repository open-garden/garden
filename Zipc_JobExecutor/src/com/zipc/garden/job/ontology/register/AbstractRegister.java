package com.zipc.garden.job.ontology.register;

import java.util.List;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDF;

import com.zipc.garden.job.ontology.OntologyUtils;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.FileReferences;
import com.zipc.garden.webplatform.dao.rdf.RDFUtil;
import com.zipc.garden.webplatform.dao.rdf.ontology.GBO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * <pre>
 * An abstract class that implements the RDF registration interface.
 * It summarizes the processing required for registration.
 * </pre>
 */
public abstract class AbstractRegister implements IRDFRegister {

    /** Root model of the file to register. */
    private AbstractRootElement root;

    /** The file to register. */
    private File file;

    /** The instance URI of the file to register. */
    private String fileInstanceURI;

    /**
     * constructor.
     * @param root Root model of the file to register.
     * @param file Files to register
     */
    public AbstractRegister(AbstractRootElement root, File file) {
        this.root = root;
        this.file = file;
        this.fileInstanceURI = generateFileInstanceURI();
    }

    /**
     * <pre>
     * Generate the URI of the File instance.
     * Set in the instance variable {@link #fileInstanceURI} and used to check the existence of files on RDF.
     * 
     * FileインスタンスのURIを生成する。インスタンス変数fileURIに設定され、ファイルのRDF存在チェックに使用される
     * </pre>
     * 
     * @return instance of File URI
     */
    protected abstract String generateFileInstanceURI();

    /**
     * {@inheritDoc}
     */
    public Result execute() {
        if (isExists()) {
            return Result.SKIP;
        }
        if (!regist()) {
            return Result.FAIL;
        }
        return Result.SUCCESS;
    }

    /**
     * <pre>
     * Get the files to register.
     * 
     * fileId/ProjectId/Hashを持つFileを取得する
     * </pre>
     * 
     * @return {@link #file}
     */
    protected File getFile() {
        return file;
    }

    /**
     * <pre>
     * Get the root model you want to register with RDF.
     * 
     * RDFに登録したいrootを取得する
     * </pre>
     * 
     * @return {@link #root}
     */
    protected AbstractRootElement getRoot() {
        return root;
    }

    /**
     * <pre>
     * Get the URI of the File instance.
     * 
     * {@link #generateFileInstanceURI()}で生成したFileインスタンスのURIを取得する
     * </pre>
     * 
     * @return {@link #fileInstanceURI}
     */
    protected String getFileInstanceURI() {
        return fileInstanceURI;
    }

    /**
     * <pre>
     * It is checked whether the URI of the File instance exists on RDF.
     * 
     * RDFに既に同一File+Hashの情報が格納済かを返す
     * </pre>
     * 
     * @return True if registered. False if not registered.
     */
    protected boolean isExists() {
        return RDFUtil.isResourceExists(getFileInstanceURI());
        // return false;
    }

    /**
     * <pre>
     * Register the generated ontology model on RDF. 
     * The snap information at the time of generation is also registered.
     * 
     * RDF DBにフィーチャパターンを登録する。生成時のスナップ情報も登録する
     * </pre>
     * 
     * @return True if registration is successful. False if registration fails.
     */
    private boolean regist() {
        // RDFに関連ファイルを登録する
        if (!generateRefOntology()) {
            return false;
        }
        // RDFに登録するモデルをrootから生成する
        OntModel m = generateOntology();
        // RDFに生成したモデルを登録する
        if (!RDFUtil.registerToRDF(m)) {
            return false;
        }
        return true;
    }

    /** Used as a separator for Instance URI. */
    protected static final String SEPARATOR = "_";

    /**
     * <pre>
     * Register the root model of the reference file in RDF.
     * The part to override in the subclass if needed.
     * 
     * AbstractRootElementの関連をRDFに登録する。必要に応じてサブクラスでオーバーライドする部分
     * </pre>
     * 
     * @return True if registration is successful. False if registration fails.
     */
    protected boolean generateRefOntology() {
        List<FileReferences> refs = EditResourceUtil.INSTANCE.getReferences(file.getProjectid(), file.getUuid());
        if (!refs.isEmpty()) {
            for (FileReferences ref : refs) {
                File refFile = ref.getRefFile();
                byte[] data = EditResourceUtil.INSTANCE.getFileContent(refFile.getId());
                AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
                if (IRDFRegister.Result.FAIL == OntologyUtils.registAbstractRootElement(root, refFile)) {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * <pre>
     * Generate an Ontology model. The part that the subclass overrides.
     * 
     * Ontologyモデルを生成する。サブクラスがオーバーライドする部分
     * </pre>
     * 
     * @return ontology model
     */
    protected OntModel generateOntology() {
        File file = getFile();
        OntModel m = ModelFactory.createOntologyModel();

        // Instance
        Resource instance = m.createOntResource(GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + GBO.FILE_NAME);
        setFileInstanceInfo(m, instance, GBO.File);
        return m;
    }

    /**
     * <pre>
     * Set common properties for File instances.
     * 
     * Fileインスタンスに共通のプロパティを設定する
     * </pre>
     * 
     * @param m ontology model
     * @param instance instance to set
     * @param instanceClass class of instance
     */
    protected void setFileInstanceInfo(OntModel m, Resource instance, OntClass instanceClass) {
        instance.addProperty(RDF.type, OWL2.NamedIndividual);
        instance.addProperty(RDF.type, instanceClass);
        instance.addLiteral(GBO.hasFileId, file.getId());
        instance.addLiteral(GBO.hasFileName, file.getName());
        instance.addLiteral(GBO.hasProjectId, file.getProjectid());
        instance.addLiteral(GBO.hasHash, file.getHash());

        // reference
        List<FileReferences> refs = EditResourceUtil.INSTANCE.getReferences(file.getProjectid(), file.getUuid());
        for (FileReferences ref : refs) {
            File refFile = ref.getRefFile();
            String refInstanceBase = GDNNS.UD.NS + refFile.getId() + SEPARATOR + refFile.getHash() + SEPARATOR;
            String refInstanceName = refInstanceBase + OntologyUtils.getInstanceExtension(refFile.getExtension());
            Resource refInstance = m.createOntResource(refInstanceName);
            instance.addProperty(GBO.refFile, refInstance);
        }
    }
}
