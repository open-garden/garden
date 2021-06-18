package com.zipc.garden.webplatform.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.SPARQLQueryServiceImpl;

/**
 * Asynchronous interface for SPQLEditor original processing
 */
public interface SPARQLQueryServiceAsync {

    /**
     * {@link SPARQLQueryServiceImpl#runQuery(String)}
     */
    void runQuery(String query, AsyncCallback<List<String>> callback);
}
