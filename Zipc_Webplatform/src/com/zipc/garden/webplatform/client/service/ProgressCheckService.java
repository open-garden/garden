package com.zipc.garden.webplatform.client.service;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Defines an interface derived from RemoteService.<br>
 * Lists all the RPC methods needed to monitor a running job.
 */
@RemoteServiceRelativePath("progressCheck")
public interface ProgressCheckService extends RemoteService {

    /**
     * Execution of the job that matches the specified job ID is canceled.
     * @param cancelJobsList Job ID list to be canceled
     * @throws IllegalArgumentException
     */
    void setCancelStatus(List<String> cancelJobsList) throws IllegalArgumentException;

    /**
     * check All jobs correspond to the given project Id And return res. Return Type is in
     * <p>
     * List&ltMap&ltString,String&gt&gt
     * </p>
     * structure is a List of jobinfo Object. jobInfo Object is stored in Map&ltString,String&gt the first String Key correspond
     * to the Long type JobId in the Job Table, the latter String store the Progress Message or Error Message that depend on the
     * Job Status
     * @param projectId - long
     * @return jobsIdSet - List<Map<String, String>>
     * @throws IllegalArgumentException
     */
    List<Map<String, String>> getAllJobByProjId(long projectId) throws IllegalArgumentException;
}
