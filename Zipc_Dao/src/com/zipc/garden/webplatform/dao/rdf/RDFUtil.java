package com.zipc.garden.webplatform.dao.rdf;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.system.Txn;

import com.zipc.garden.webplatform.dao.rdf.ontology.GDNNS;

/**
 * This class summarizes the common processing of RDF.
 */
public class RDFUtil {

    /** URL of RDF dataset */
    public static final String fuseki_url = "http://localhost:3030/garden";

    /** Used as a separator for Instance URI. */
    public static final String SEPARATOR = "_";

    /**
     * Registers the specified model with RDF.
     * @param m specified model
     * @return If True, registration is successful. If False, registration has failed.
     */
    public static boolean registerToRDF(Model m) {
        try (RDFConnection conn = RDFConnectionFactory.connect(fuseki_url)) {
            Txn.executeWrite(conn, () -> {
                // debug //
                // System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
                // m.write(System.out, "TTL");
                // System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
                // ***** //

                conn.load(GDNNS.SCENARIO.URI, m);
                conn.commit();
                conn.end();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Returns whether the specified instanceURI is registered as "?s" in "?s ?p ?o" in RDF DB. <br>
     * RDF DB内に引数のinstanceURIが?s ?p ?oの?sとして登録済かを返す
     * @param instanceURI Instance URI corresponding to "?s"
     * @return True if registered. False if not registered.
     */
    public static boolean isResourceExists(String instanceURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT ( <" + instanceURI + "> AS ?s ) WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    <" + instanceURI + "> ?p ?o ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        try (RDFConnection conn = RDFConnectionFactory.connect(fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                if (query.isSelectType()) {
                    ResultSet rs = qexec.execSelect();
                    List<String> variables = rs.getResultVars();
                    while (rs.hasNext()) {
                        QuerySolution soln = rs.nextSolution();
                        for (String variable : variables) {
                            RDFNode node = soln.get(variable);
                            if (node.isResource()) {
                                String getInstanceURI = ((Resource) node).getURI();
                                if (instanceURI.equals(getInstanceURI)) {
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                } else {
                    return false;
                }
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
     * Returns whether the specified instanceURI is registered as "?o" in "?s ?p ?o" in RDF DB. RDF DB内に引数のinstanceURIが?s ?p
     * ?oの?oとして登録済かを返す
     * @param instanceURI Instance URI corresponding to "?o"
     * @return True if registered. False if not registered.
     */
    public static boolean isResourceReferenced(String instanceURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT ( <" + instanceURI + "> AS ?o ) WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    ?s ?p <" + instanceURI + "> ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        try (RDFConnection conn = RDFConnectionFactory.connect(fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (node.isResource()) {
                            String getInstanceURI = ((Resource) node).getURI();
                            if (instanceURI.equals(getInstanceURI)) {
                                return true;
                            }
                        }
                    }
                }
                return false;
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
     * Returns whether the instanceURI specified in the RDF DB has been registered as "?o" in "?s garden:refFile ?o".<br>
     * RDF DB内に引数のinstanceURIが?s garden:refFile ?oの?oとして登録済かを返す
     * @param instanceURI Instance URI corresponding to "?o"
     * @return True if registered. False if not registered.
     */
    public static boolean isResourceFileReferenced(String instanceURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT ( <" + instanceURI + "> AS ?o ) WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    ?s garden:refFile <" + instanceURI + "> ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        try (RDFConnection conn = RDFConnectionFactory.connect(fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (node.isResource()) {
                            String getInstanceURI = ((Resource) node).getURI();
                            if (instanceURI.equals(getInstanceURI)) {
                                return true;
                            }
                        }
                    }
                }
                return false;
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
     * Returns a list of "?o" referenced by the instanceURI specified in the RDF DB as "?s garden:refFile ?o". <br>
     * RDF DB内に引数のinstanceURIが?s garden:refFile ?oとして参照している?oをリストで返す
     * @param instanceURI Instance URI corresponding to "?s"
     * @return list of refFile
     */
    public static List<String> getRefFiles(String instanceURI) {
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT DISTINCT ?o WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    <" + instanceURI + "> garden:refFile ?o ." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        List<String> refFiles = new ArrayList<>();
        try (RDFConnection conn = RDFConnectionFactory.connect(fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (node.isResource()) {
                            String getInstanceURI = ((Resource) node).getURI();
                            if (!refFiles.contains(getInstanceURI)) {
                                refFiles.add(getInstanceURI);
                            }
                        }
                    }
                }
                return refFiles;
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
     * Returns a list of instanceURIs with "garden: File" properties that exist in RDF DB.<br>
     * RDF DB内に存在する garden:Fileのプロパティを持つinstanceURIをリストで返す
     * @return list of instanceURI
     */
    public static List<String> getFiles() {
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT DISTINCT ?s WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    ?s rdf:type ?subClass ." + System.lineSeparator());
        builder.append("    ?subClass rdfs:subClassOf garden:File." + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        List<String> refFiles = new ArrayList<>();
        try (RDFConnection conn = RDFConnectionFactory.connect(fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (node.isResource()) {
                            String getInstanceURI = ((Resource) node).getURI();
                            if (!refFiles.contains(getInstanceURI)) {
                                refFiles.add(getInstanceURI);
                            }
                        }
                    }
                }
                return refFiles;
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
     * Returns a list of instanceURIs that exist in RDF DB and have a "garden: File" property and are not referenced by
     * refFile.<br>
     * RDF DB内に存在する garden:Fileのプロパティを持ちrefFileで参照されていないinstanceURIをリストで返す
     * @return list of instanceURI
     */
    public static List<String> getNotReferencedFiles() {
        StringBuilder builder = new StringBuilder();
        builder.append("PREFIX garden:<http://www.zipc.com/garden#> " + System.lineSeparator());
        builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + System.lineSeparator());
        builder.append("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " + System.lineSeparator());
        builder.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + System.lineSeparator());
        builder.append("SELECT DISTINCT ?s WHERE {" + System.lineSeparator());
        builder.append("  GRAPH <http://www.zipc.com/garden/scenario> {" + System.lineSeparator());
        builder.append("    ?s rdf:type ?subClass ." + System.lineSeparator());
        builder.append("    ?subClass rdfs:subClassOf garden:File." + System.lineSeparator());
        builder.append("    FILTER NOT EXISTS {" + System.lineSeparator());
        builder.append("      ?other garden:refFile ?s." + System.lineSeparator());
        builder.append("    }" + System.lineSeparator());
        builder.append("  }" + System.lineSeparator());
        builder.append("}" + System.lineSeparator());
        String sparql = builder.toString();
        List<String> refFiles = new ArrayList<>();
        try (RDFConnection conn = RDFConnectionFactory.connect(fuseki_url)) {
            Query query = QueryFactory.create(sparql);
            try (QueryExecution qexec = conn.query(query)) {
                ResultSet rs = qexec.execSelect();
                List<String> variables = rs.getResultVars();
                while (rs.hasNext()) {
                    QuerySolution soln = rs.nextSolution();
                    for (String variable : variables) {
                        RDFNode node = soln.get(variable);
                        if (node.isResource()) {
                            String getInstanceURI = ((Resource) node).getURI();
                            if (!refFiles.contains(getInstanceURI)) {
                                refFiles.add(getInstanceURI);
                            }
                        }
                    }
                }
                return refFiles;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
