package com.zipc.garden.webplatform.server.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.model.cscs.CSCSPattern;
import com.zipc.garden.webplatform.client.editor.cscs.CSCSResourceService;
import com.zipc.garden.webplatform.dao.CSCSPatternDAO;
import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.File;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the CSCSResourceService interface.
 */
@SuppressWarnings("serial")
public class CSCSResourceServiceImpl extends RemoteServiceServlet implements CSCSResourceService {

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(long fileId, byte[] CSCPatternBinary) throws IllegalArgumentException {
        BinaryResourceImpl r = new BinaryResourceImpl();
        ByteArrayInputStream bi = new ByteArrayInputStream(CSCPatternBinary);
        try {
            r.load(bi, null);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        CSCSPattern cscsPattern = (CSCSPattern) r.getContents().get(0);
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                File file = session.byId(File.class).load(fileId);
                long ptnId = Long.valueOf(cscsPattern.getId());
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<CSCSPatternDAO> query = session.getCriteriaBuilder().createQuery(CSCSPatternDAO.class);
                Root<CSCSPatternDAO> ptnTable = query.from(CSCSPatternDAO.class);
                Root<File> fileTable = query.from(File.class);
                query.select(ptnTable);
                List<Predicate> preList = new ArrayList<Predicate>();
                preList.add(builder.equal(ptnTable.get("fileUuid"), file.getUuid()));
                preList.add(builder.equal(ptnTable.get("projectId"), file.getProjectid()));
                preList.add(builder.equal(ptnTable.get("CSCSPatternId"), ptnId));
                preList.add(builder.equal(fileTable.get("uuid"), ptnTable.get("fileUuid")));
                preList.add(builder.equal(fileTable.get("projectid"), ptnTable.get("projectId")));
                preList.add(builder.isFalse(fileTable.get("deleteFlg")));
                query.where(preList.toArray(new Predicate[preList.size()]));
                CSCSPatternDAO patternDAO = session.createQuery(query).getSingleResult();
                patternDAO.setCsc(cscsPattern.getCsc());
                patternDAO.setPattern(cscsPattern.getPatternValue());
                session.save(patternDAO);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw e;
            }
        }
    }

}
