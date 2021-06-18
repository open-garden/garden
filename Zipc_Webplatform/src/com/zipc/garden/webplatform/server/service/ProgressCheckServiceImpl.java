package com.zipc.garden.webplatform.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.webplatform.client.service.ProgressCheckService;
import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.Job;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the ProgressCheckService interface.
 */
@SuppressWarnings("serial")
public class ProgressCheckServiceImpl extends RemoteServiceServlet implements ProgressCheckService {

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCancelStatus(List<String> cancelJobsList) throws IllegalArgumentException {
        Transaction tx = null;
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            for (String cxlId : cancelJobsList) {
                CriteriaQuery<Job> criteria = session.getCriteriaBuilder().createQuery(Job.class);
                Root<Job> jobTbl = criteria.from(Job.class);
                criteria.where(session.getCriteriaBuilder().equal(jobTbl.get("id"), Long.valueOf(cxlId)));
                List<Job> jobs = session.createQuery(criteria).getResultList();
                for (Job j : jobs) {
                    if (j.getStatus() != Job.STATUS_EXECUTING && j.getStatus() != Job.STATUS_NOEXEC) {
                        continue;
                    }
                    j.setStatus(Job.STATUS_CANCEL);
                    j.setStepProgressMessage("Canceled");
                    session.update(j);
                }
            }
            tx.commit();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, String>> getAllJobByProjId(long projectId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            // Job Table acces
            CriteriaQuery<Job> criteria = session.getCriteriaBuilder().createQuery(Job.class);
            Root<Job> jobTbl = criteria.from(Job.class);
            // File Table acces
            CriteriaQuery<File> criteriaFile = session.getCriteriaBuilder().createQuery(File.class);
            Root<File> fileTable = criteriaFile.from(File.class);

            criteria.where(session.getCriteriaBuilder().equal(jobTbl.get("projectId"), projectId));
            List<Job> jobs = session.createQuery(criteria).getResultList();
            List<Map<String, String>> res = new ArrayList<Map<String, String>>();

            for (Job jobInstance : jobs) {

                Map<String, String> tableContent = new HashMap<String, String>();
                tableContent.put("id", jobInstance.getId().toString());
                String tempProgMsg = "";
                if (jobInstance.getStepProgressMessage() == null || jobInstance.getStepProgressMessage().equals("")) {
                    tempProgMsg = "No Infomation";
                } else if (jobInstance.getStatus() == Job.STATUS_ERROR) {
                    // when status is error, get error Message instead of Step Progress Message
                    tempProgMsg = jobInstance.getMessage();
                } else {
                    // get step ProgressMessage
                    tempProgMsg = jobInstance.getStepProgressMessage();
                }
                tableContent.put("progMsg", tempProgMsg);
                if (jobInstance.getOutputFileId() == Job.NO_APPILCABLE_FILE_ID) {
                    criteriaFile.where(session.getCriteriaBuilder().equal(fileTable.get("id"), jobInstance.getInputFileId()));
                    List<File> files = session.createQuery(criteriaFile).getResultList();
                    String filename = files.get(0).getFullPath();
                    tableContent.put("fileName", filename + "RDF Process");
                } else {
                    criteriaFile.where(session.getCriteriaBuilder().equal(fileTable.get("id"), jobInstance.getOutputFileId()));
                    List<File> files = session.createQuery(criteriaFile).getResultList();

                    String filename = files.get(0).getFullPath();
                    tableContent.put("fileName", filename);
                }
                res.add(tableContent);
            }
            return res;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }
}
