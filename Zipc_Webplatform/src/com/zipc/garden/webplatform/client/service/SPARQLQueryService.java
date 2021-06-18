package com.zipc.garden.webplatform.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Defines an interface derived from RemoteService.<br>
 * Lists all RPC methods needed to issue a SPARQL query.
 */
@RemoteServiceRelativePath("sparqlQuery")
public interface SPARQLQueryService extends RemoteService {

    /**
     * Issue the SPARQL query of the argument and get the result.
     * @param query SPARQL query syntax
     * @return Query issuance result
     * @throws IllegalArgumentException
     */
    List<String> runQuery(String query) throws IllegalArgumentException;
}
