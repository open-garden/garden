package com.zipc.garden.webplatform.server.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.webplatform.client.service.FileHistoryService;
import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.FileHistory;
import com.zipc.garden.webplatform.dao.UserMaster;
import com.zipc.garden.webplatform.shared.FileHistoryInfo;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the FileHistoryService interface.
 */
@SuppressWarnings("serial")
public class FileHistoryServiceImpl extends RemoteServiceServlet implements FileHistoryService {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("rawtypes")
    @Override
    public List<FileHistoryInfo> getFileHistoryInfo(long fileId) throws IllegalArgumentException {
        List<FileHistoryInfo> info = new ArrayList<FileHistoryInfo>();
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                CriteriaBuilder builder = session.getCriteriaBuilder();

                CriteriaQuery<FileHistoryInfo> fileHistoryInfoQuery = builder.createQuery(FileHistoryInfo.class);
                CriteriaQuery<FileHistoryInfo> fileInfoQuery = builder.createQuery(FileHistoryInfo.class);

                Root<File> fileTbl = fileInfoQuery.from(File.class);
                Root<UserMaster> fileUserTbl = fileInfoQuery.from(UserMaster.class);

                fileInfoQuery
                        .select(builder.construct(FileHistoryInfo.class, fileTbl.get("id"), fileTbl.get("updateTime"), fileTbl.get("updateUser"), fileUserTbl.get("name"),
                                fileTbl.get("name"), fileTbl.get("fullPath"), fileTbl.get("hash")))
                        .where(builder.equal(fileTbl.get("id"), fileId), builder.equal(fileTbl.get("updateUser"), fileUserTbl.get("id")));
                info.add(session.createQuery(fileInfoQuery).uniqueResult());

                Root<FileHistory> fileHistoryTbl = fileHistoryInfoQuery.from(FileHistory.class);
                Join<File, FileHistory> jointFile = fileHistoryTbl.join("file");
                Root<UserMaster> fileHistoryUserTbl = fileHistoryInfoQuery.from(UserMaster.class);

                fileHistoryInfoQuery
                        .select(builder.construct(FileHistoryInfo.class, jointFile.get("id"), fileHistoryTbl.get("updateTime"), fileHistoryTbl.get("updateUser"),
                                fileHistoryUserTbl.get("name"), fileHistoryTbl.get("name"), fileHistoryTbl.get("fullPath"), fileHistoryTbl.get("hash")))
                        .where(builder.equal(jointFile.get("id"), fileId), builder.equal(fileHistoryTbl.get("updateUser"), fileHistoryUserTbl.get("id")))
                        .orderBy(builder.desc(fileHistoryTbl.get("updateTime")));
                info.addAll(session.createQuery(fileHistoryInfoQuery).getResultList());

                tx.commit();
                return info;
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw e;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateFileContent(long fileId, String hash, Date updateTime) throws IllegalArgumentException {

        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // get history data
                FileHistory history;
                CriteriaQuery<FileHistory> fileHistoryQuery = builder.createQuery(FileHistory.class);
                Root<FileHistory> fileHistoryTbl = fileHistoryQuery.from(FileHistory.class);
                Timestamp timestamp = new Timestamp(updateTime.getTime());
                fileHistoryQuery.select(fileHistoryTbl).where(builder.equal(fileHistoryTbl.get("hash"), hash), builder.equal(fileHistoryTbl.get("file"), fileId),
                        builder.equal(fileHistoryTbl.get("updateTime"), timestamp));
                history = session.createQuery(fileHistoryQuery).uniqueResult();

                // update
                CriteriaUpdate<File> criteriaUpdate = builder.createCriteriaUpdate(File.class);
                Root<File> root = criteriaUpdate.from(File.class);
                criteriaUpdate.set("content", history.getContent());
                criteriaUpdate.set("hash", history.getHash());
                criteriaUpdate.where(builder.equal(root.get("id"), fileId));

                session.createQuery(criteriaUpdate).executeUpdate();
                tx.commit();
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw e;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveFileToHistory(long fileId, String hash) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                CriteriaBuilder builder = session.getCriteriaBuilder();

                // current file
                File file;
                CriteriaQuery<File> fileQuery = builder.createQuery(File.class);
                Root<File> fileTbl = fileQuery.from(File.class);
                fileQuery.select(fileTbl).where(builder.equal(fileTbl.get("id"), fileId));
                file = session.createQuery(fileQuery).uniqueResult();

                // save current file to history
                FileHistory history = new FileHistory();
                history.setFile(file);
                history.setName(file.getName());
                history.setExtension(file.getExtension());
                history.setFullPath(file.getFullPath());
                history.setContent(file.getContent());
                history.setUpdateTime(file.getUpdateTime());
                history.setUpdateUser(file.getUpdateUser());
                history.setHash(file.getHash());

                session.save(history);
                tx.commit();

            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw e;
            }
        }
    }
}
