package com.zipc.garden.job.ontology;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.apache.jena.vocabulary.RDF;

import com.zipc.garden.job.ontology.register.ARCRegister;
import com.zipc.garden.job.ontology.register.BHVRegister;
import com.zipc.garden.job.ontology.register.BPRegister;
import com.zipc.garden.job.ontology.register.BPSRegister;
import com.zipc.garden.job.ontology.register.FMCRegister;
import com.zipc.garden.job.ontology.register.FMRegister;
import com.zipc.garden.job.ontology.register.FPRegister;
import com.zipc.garden.job.ontology.register.FPSRegister;
import com.zipc.garden.job.ontology.register.IRDFRegister;
import com.zipc.garden.job.ontology.register.IRDFRegister.Result;
import com.zipc.garden.job.ontology.register.SCSRegister;
import com.zipc.garden.job.ontology.register.SCSSRegister;
import com.zipc.garden.job.ontology.register.TCRegister;
import com.zipc.garden.job.ontology.remover.ARCRemover;
import com.zipc.garden.job.ontology.remover.BPSRemover;
import com.zipc.garden.job.ontology.remover.FMCRemover;
import com.zipc.garden.job.ontology.remover.FMRemover;
import com.zipc.garden.job.ontology.remover.FPSRemover;
import com.zipc.garden.job.ontology.remover.FSMRemover;
import com.zipc.garden.job.ontology.remover.IRDFRemover;
import com.zipc.garden.job.ontology.remover.SCSSRemover;
import com.zipc.garden.job.ontology.remover.TCRemover;
import com.zipc.garden.model.arc.ARCRoot;
import com.zipc.garden.model.bp.BPRoot;
import com.zipc.garden.model.bps.BPSRoot;
import com.zipc.garden.model.cb.CBRoot;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.model.fmc.FMCRoot;
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.model.scs.SCSRoot;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.model.tps.TPSRoot;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.rdf.RDFUtil;
import com.zipc.garden.webplatform.dao.rdf.ontology.ARCO;
import com.zipc.garden.webplatform.dao.rdf.ontology.BHVO;
import com.zipc.garden.webplatform.dao.rdf.ontology.BPO;
import com.zipc.garden.webplatform.dao.rdf.ontology.BPSO;
import com.zipc.garden.webplatform.dao.rdf.ontology.FMCO;
import com.zipc.garden.webplatform.dao.rdf.ontology.FMO;
import com.zipc.garden.webplatform.dao.rdf.ontology.FPO;
import com.zipc.garden.webplatform.dao.rdf.ontology.FPSO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GBO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;
import com.zipc.garden.webplatform.dao.rdf.ontology.SCSO;
import com.zipc.garden.webplatform.dao.rdf.ontology.SCSSO;
import com.zipc.garden.webplatform.dao.rdf.ontology.TCO;
import com.zipc.garden.webplatform.shared.Extension;

/**
 * This class summarizes the common processing of ontology.
 */
public class OntologyUtils {

    /** URL of the dataset */
    public static final String fuseki_url = "http://localhost:3030/garden";

    /** Separator used in instance URI */
    public static final String SEPARATOR = "_";

    /**
     * <pre>
     * Register with RDF based on the specified root and file.
     * Related files are also registered.
     * 
     * AbstractRootElementのサブクラスをRDFに登録する。
     * 関連ファイルも登録する
     * </pre>
     * 
     * @param root e.g. FMRoot,FMCRoot...
     * @param file need fileId,fileName,hash,projectId
     * @return SUCCESS / SKIP / FAIL
     */
    public static Result registAbstractRootElement(AbstractRootElement root, File file) {
        IRDFRegister register = OntologyUtils.createRegister(root, file);
        return register.execute();
    }

    /**
     * <pre>
     * Generate RDF registration class based on root and file
     * 
     * rootを元にRDF登録クラスを生成する
     * </pre>
     * 
     * @param root e.g. FMRoot,FMCRoot...
     * @param file need fileId,fileName,hash,projectId
     * @return instance of register, or null
     */
    public static IRDFRegister createRegister(AbstractRootElement root, File file) {
        if (root instanceof FMRoot) {
            return new FMRegister((FMRoot) root, file);
        } else if (root instanceof FMCRoot) {
            return new FMCRegister((FMCRoot) root, file);
        } else if (root instanceof TCRoot) {
            return new TCRegister((TCRoot) root, file);
        } else if (root instanceof TPSRoot) {
            return new FPSRegister((TPSRoot) root, file);
        } else if (root instanceof TPRoot) {
            return new FPRegister((TPRoot) root, file);
        } else if (root instanceof CBRoot) {
            return new SCSSRegister((CBRoot) root, file);
        } else if (root instanceof SCSRoot) {
            return new SCSRegister((SCSRoot) root, file);
        } else if (root instanceof FSMDStateMachine) {
            return new BHVRegister((FSMDStateMachine) root, file);
        } else if (root instanceof ARCRoot) {
            return new ARCRegister((ARCRoot) root, file);
        } else if (root instanceof BPSRoot) {
            return new BPSRegister((BPSRoot) root, file);
        } else if (root instanceof BPRoot) {
            return new BPRegister((BPRoot) root, file);
        }
        return null;
    }

    /**
     * <pre>
     * Generates an RDF delete class based on the specified file.
     * 
     * fileを元にRDF削除クラスを生成する
     * </pre>
     * 
     * @param file need fileId,fileName,hash,projectId
     * @return instance of remover, or null
     */
    public static IRDFRemover createRemover(File file) {
        Extension ext = Extension.getByCode(file.getExtension());
        if (null == ext) {
            return null;
        }
        switch (ext) {
        case FM:
            return new FMRemover(file);
        case FMC:
            return new FMCRemover(file);
        case TC:
            return new TCRemover(file);
        case FPS:
            return new FPSRemover(file);
        case FP:
            // FPS削除の延長で削除されるので不要。来てはならない
            System.out.println("FPRemover is not implemented.");
            return null;
        case FSM:
            return new FSMRemover(file);
        case ARC:
            return new ARCRemover(file);
        case BPS:
            return new BPSRemover(file);
        case BP:
            // BPS削除の延長で削除されるので不要。来てはならない
            System.out.println("BPRemover is not implemented.");
            return null;
        case SCSS:
            return new SCSSRemover(file);
        case SCS:
            System.out.println("SCSRemover is not implemented.");
            return null;
        default:
            return null;
        }
    }

    /**
     * <pre>
     * Get the extension name on the RDF instance URI from the file extension.
     * 
     * ファイルの拡張子からRDFのインスタンスURI上の拡張子名を取得する
     * </pre>
     * 
     * @param extension Extension of file
     * @return extension name of instance URI
     */
    public static String getInstanceExtension(String extension) {
        Extension ext = Extension.getByCode(extension);
        switch (ext) {
        case FM:
            return FMO.FM_NAME;
        case FMC:
            return FMCO.FMC_NAME;
        case TC:
            return TCO.TC_NAME;
        case FPS:
            return FPSO.FPS_NAME;
        case FP:
            return FPO.FP_NAME;
        case FSM:
            return BHVO.BHV_NAME;
        case ARC:
            return ARCO.ARC_NAME;
        case BPS:
            return BPSO.BPS_NAME;
        case BP:
            return BPO.BP_NAME;
        case SCSS:
            return SCSSO.SCSS_NAME;
        case SCS:
            return SCSO.SCS_NAME;
        default:
            return null;
        }
    }

    /**
     * <pre>
     * Convert text to a string expressed in ASCII.
     * If there are prohibited characters in the URI of RDF, there will be a problem, so ASCII conversion processing is required.
     * 
     * textをASCIIで表現された文字列に変換する。
     * RDFのURIに使用禁止文字があると問題が出るためのASCII変換処理
     * </pre>
     * 
     * @param text Character string to be converted (may include prohibited characters in RDF)<br>
     *            変換したい文字列(RDFでの使用禁止文字を含んでも良い)
     * @return Character string expressed in ASCII<br>
     *         ASCIIで表現された文字列
     */
    public static String encodeStringToAscii(String text) {
        return DatatypeConverter.printHexBinary(text.getBytes());
    }

    /**
     * <pre>
     * Decodes a string expressed in ASCII and returns it to the original string
     * 
     * ASCIIで表現された文字列をデコードして元の文字列に戻す
     * </pre>
     * 
     * @param asciiText Character string expressed in ASCII<br>
     *            ASCIIで表現された文字列
     * @return Original string<br>
     *         元の文字列
     */
    public static String decodeAsciiToString(String asciiText) {
        return new String(DatatypeConverter.parseHexBinary(asciiText));
    }

    /**
     * <pre>
     * Get the instance URI on RDF from File
     * 
     * FileからRDF上のインスタンスURIを取得する
     * </pre>
     * 
     * @param file
     * @return file instanceURI in RDF
     */
    public static String getFileInstanceURI(File file) {
        Extension ext = Extension.getByCode(file.getExtension());
        switch (ext) {
        case FM:
            return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + FMO.FM_NAME;
        case FMC:
            return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + FMCO.FMC_NAME;
        case TC:
            return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + TCO.TC_NAME;
        case FPS:
            return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + FPSO.FPS_NAME;
        case FP:
            return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + FPO.FP_NAME;
        case FSM:
            return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + BHVO.BHV_NAME;
        case ARC:
            return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + ARCO.ARC_NAME;
        case BPS:
            return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + BPSO.BPS_NAME;
        case BP:
            return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + BPO.BP_NAME;
        case SCSS:
            return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + SCSSO.SCSS_NAME;
        case SCS:
            return GDNNS.UD.NS + file.getId() + SEPARATOR + file.getHash() + SEPARATOR + SCSO.SCS_NAME;
        default:
            return null;
        }
    }

    /**
     * <pre>
     * Get a File instance on RDB from the URI indicating "garden:File" in RDF.
     * 
     * RDFでgarden:Fileを示すURIからRDB上のFileインスタンスを取得する。
     * </pre>
     * 
     * @param instanceURI URI indicating "garden:File"
     * @return file A file that contains "ID / hash / name / ProjectId / extension".
     */
    public static File getFileFromInstanceURI(String instanceURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT ?p ?o WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    <" + instanceURI + "> ?p ?o ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        File file = new File();
        try (RDFConnection conn = RDFConnectionFactory.connect(fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    RDFNode pNode = soln.get(variables.get(0));
                    RDFNode oNode = soln.get(variables.get(1));
                    if (pNode.isResource()) {
                        String pURI = ((Resource) pNode).getURI();
                        if (pURI.equals(RDF.type.getURI())) {
                            String ext = getExtension(oNode);
                            if (null != ext) {
                                file.setExtension(ext);
                            }
                        } else if (pURI.equals(GBO.hasFileId.getURI())) {
                            if (oNode.isLiteral()) {
                                long fileId = ((Literal) oNode).getLong();
                                file.setId(fileId);
                            }
                        } else if (pURI.equals(GBO.hasFileName.getURI())) {
                            if (oNode.isLiteral()) {
                                String fileName = ((Literal) oNode).getString();
                                file.setName(fileName);
                            }
                        } else if (pURI.equals(GBO.hasProjectId.getURI())) {
                            if (oNode.isLiteral()) {
                                long projectId = ((Literal) oNode).getLong();
                                file.setProjectid(projectId);
                            }
                        } else if (pURI.equals(GBO.hasHash.getURI())) {
                            if (oNode.isLiteral()) {
                                String hash = ((Literal) oNode).getString();
                                file.setHash(hash);
                            }
                        }
                    }
                }
                return file;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * <pre>
     * Get the extension string on RDB from RDFNode.
     * 
     * RDFNodeからRDB上の拡張子の文字列を取得する
     * </pre>
     * 
     * @param node
     * @return text of extension
     */
    private static String getExtension(RDFNode node) {
        if (node.isResource()) {
            String uri = ((Resource) node).getURI();
            if (uri.equals(FMO.FeatureModel.getURI())) {
                return Extension.FM.getValue();
            } else if (uri.equals(FMCO.FMConstraint.getURI())) {
                return Extension.FMC.getValue();
            } else if (uri.equals(TCO.TestCondition.getURI())) {
                return Extension.TC.getValue();
            } else if (uri.equals(FPSO.FeaturePatternSetting.getURI())) {
                return Extension.FPS.getValue();
            } else if (uri.equals(FPO.FeaturePattern.getURI())) {
                return Extension.FPS.getValue(); // RDF上のFPはRDB上のFPSと結びつくので注意
            } else if (uri.equals(BHVO.BehaviorModel.getURI())) {
                return Extension.FSM.getValue();
            } else if (uri.equals(ARCO.ArchitectureModel.getURI())) {
                return Extension.ARC.getValue();
            } else if (uri.equals(BPSO.BehaviorPatternSetting.getURI())) {
                return Extension.BPS.getValue();
            } else if (uri.equals(BPO.BehaviorPattern.getURI())) {
                return Extension.BPS.getValue(); // RDF上のBPはRDB上のBPSと結びつくので注意
            } else if (uri.equals(SCSSO.ScenarioSetSetting.getURI())) {
                return Extension.SCSS.getValue();
            } else if (uri.equals(SCSO.ScenarioSet.getURI())) {
                return Extension.SCSS.getValue();
            }
        }
        return null;
    }

    /**
     * <pre>
     * Deletes an RDF ontology that has the specified URI in "?s".
     * 
     * subjectURIを?sに持つ?s ?p ?oを全て削除する
     * </pre>
     * 
     * @param subjectURI The subject URI of the ontology to be deleted.
     * @return true: success / false: failure
     */
    public static boolean removeTripleBySubject(String subjectURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("     <" + subjectURI + "> ?p ?o ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            UpdateRequest request = UpdateFactory.create(sparql.toString());
            conn.update(request);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Gets the object URI with the specified subject URI and predicate URI.
     * 
     * subjectURI predicateURI ?o の?oをURI形式で取得する
     * </pre>
     * 
     * @param subjectURI URI specified in the subject.
     * @param predicateURI URI specified in the predicate.
     * @return list of object URI
     */
    public static List<String> getObjectURIs(String subjectURI, String predicateURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ?o WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append(" <" + subjectURI + "> <" + predicateURI + "> ?o ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        List<String> objectURIs = new ArrayList<>();
        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (node.isResource()) {
                            objectURIs.add(((Resource) node).getURI());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return objectURIs;
    }

}
