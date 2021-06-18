package com.zipc.garden.webplatform.server.dao.rdf;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.rdf.model.Property;

/**
 * It is a class that supports the creation of SPARQL syntax.
 */
public class QueryBuilder {

    /** Parameterized Sparql syntax */
    private ParameterizedSparqlString q;

    /** SPARQL syntax */
    private StringBuilder query;

    /** Property list of predicate specified in SPARQL syntax */
    private Map<String, Property> paraMap;

    /**
     * constructor.<br>
     * Each field variable is initialized.
     * @param sparql
     */
    public QueryBuilder(String sparql) {
        q = new ParameterizedSparqlString();
        query = new StringBuilder();
        query.append(sparql);
        paraMap = new LinkedHashMap<String, Property>();
    }

    /**
     * The specified subject, predicate, and object are added to the syntax.<br>
     * In addition, the predicate is stored in {@link #paraMap}.
     * @param s subject
     * @param p predicate
     * @param o object
     * @return {@link QueryBuilder this}
     */
    public QueryBuilder append(String s, Property p, String o) {
        String uniqueString = p.toString();
        query.append("?").append(s).append(" ?").append(uniqueString).append(" ?").append(o).append(". ");
        paraMap.put(uniqueString, p);
        return this;
    }

    /**
     * The specified query is added to the syntax.
     * @param queryString specified query
     * @return {@link QueryBuilder this}
     */
    public QueryBuilder append(String queryString) {
        query.append(queryString);
        return this;
    }

    /**
     * Convert the created query to the parameterized SPARQL syntax.<br>
     * Also, set the contents of {@link #paraMap} in the parameter.
     * @return {@link #q Parameterized Sparql syntax}
     */
    public ParameterizedSparqlString build() {
        q.setCommandText(query.toString());
        for (Entry<String, Property> params : paraMap.entrySet()) {
            q.setParam(params.getKey(), params.getValue());
        }
        return q;
    }
}
