package com.zipc.garden.webplatform.server.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.webplatform.client.service.SPARQLQueryService;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the SPARQLQueryService interface.
 */
@SuppressWarnings("serial")
public class SPARQLQueryServiceImpl extends RemoteServiceServlet implements SPARQLQueryService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> runQuery(String queryStr) throws IllegalArgumentException {
        try (RDFConnection conn = RDFConnectionRemote.create().destination("http://localhost:3030").queryEndpoint("/garden/query").build();) {
            // Fusekiのコネクションを設定する

            // SPARQL Editorの内容を取得する
            // Queryを生成する
            Query query = QueryFactory.create(queryStr);
            // 接続し、Queryを発行する
            List<String> matchResult = new ArrayList<>();
            try (QueryExecution qexec = conn.query(query)) {
                if (query.isSelectType()) {
                    ResultSet results = qexec.execSelect();
                    List<String> varibles = results.getResultVars();
                    while (results.hasNext()) {
                        // ＊クエリソリューションに検索結果から移送する
                        QuerySolution soln = results.nextSolution();
                        // ＊クエリ中に発行したアイテムを取得する
                        for (String varible : varibles) {
                            RDFNode node = soln.get(varible);
                            if (node.isLiteral()) {
                                matchResult.add(((Literal) node).getString());
                            }
                            if (node.isResource()) {
                                Resource resource = (Resource) node;
                                if (!resource.isAnon()) {
                                    matchResult.add(resource.getURI());
                                }
                            }
                        }

                    }
                    return matchResult;
                } else if (query.isAskType()) {
                    if (qexec.execAsk()) {
                        matchResult.add(0, "true");
                        return matchResult;
                    } else {
                        matchResult.add(0, "false");
                        return matchResult;
                    }
                } else {
                    return null;
                }

            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            } finally {
                conn.close();
            }

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

    }

}
