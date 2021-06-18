package com.zipc.garden.webplatform.server.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.webplatform.client.editor.ace.AceMode;
import com.zipc.garden.webplatform.client.service.LoginService;
import com.zipc.garden.webplatform.dao.Company;
import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.UserMaster;
import com.zipc.garden.webplatform.server.PasswordUtil;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the LoginService interface.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    /** Attribute name of user information saved in session */
    private static final String USER_INFO = "userInfo";

    /** Company ID. The ID is fixed at 1 because the company is not currently managed. */
    private static final Long UNKNOWN_COMPANY_ID = 1L;

    /**
     * constructor<br>
     * Executed when the EntryPoint class is called.
     */
    public LoginServiceImpl() {

        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Long> query = builder.createQuery(Long.class);
                query.select(builder.count(query.from(Company.class)));
                Long count = session.createQuery(query).getSingleResult();
                if (count <= 0) {
                    Company adminCompany = new Company();
                    adminCompany.setName("Unknown");
                    session.persist(adminCompany);
                    query = builder.createQuery(Long.class);
                    query.select(builder.count(query.from(UserMaster.class)));
                    count = session.createQuery(query).getSingleResult();
                    if (count <= 0) {
                        UserMaster adminUser = new UserMaster();
                        adminUser.setName("a");
                        adminUser.setMail("a@a.co.jp");
                        adminUser.setCompany(adminCompany);
                        adminUser.setRole(0);
                        String extensions = "";
                        for (AceMode mode : AceMode.values()) {
                            if (AceMode.UNDEFINED != mode) {
                                extensions += mode.getExtension() + ";";
                            }
                        }
                        adminUser.setEditExtensions(extensions);
                        adminUser.setVerifyStatus(true);
                        session.persist(adminUser);
                        String password = PasswordUtil.getSafetyPassword("a", adminCompany.getId().toString() + adminUser.getId().toString());
                        adminUser.setPassword(password);
                        session.save(adminUser);
                    }
                }
                tx.commit();

            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkLogin() throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute(USER_INFO);
        if (userInfo == null) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean login(String user, String pass) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String searchColumn = "name";

                CriteriaBuilder builder = session.getCriteriaBuilder();

                CriteriaQuery<UserMaster> userQuery = builder.createQuery(UserMaster.class);
                userQuery.where(builder.equal(userQuery.from(UserMaster.class).get(searchColumn), user));
                UserMaster userTmp = session.createQuery(userQuery).uniqueResult();
                if (userTmp == null) {
                    searchColumn = "mail";
                    CriteriaQuery<UserMaster> mailQuery = builder.createQuery(UserMaster.class);
                    mailQuery.where(builder.equal(mailQuery.from(UserMaster.class).get(searchColumn), user));
                    userTmp = session.createQuery(mailQuery).uniqueResult();
                    if (userTmp == null) {
                        return false;
                    }
                }

                // check verified user
                if (!userTmp.isVerifyStatus()) {
                    return false;
                }

                String password = PasswordUtil.getSafetyPassword(pass, userTmp.getCompany().getId().toString() + userTmp.getId().toString());
                if (!userTmp.getPassword().equals(password)) {
                    return false;
                }

                CriteriaQuery<UserInfo> userInfoQuery = builder.createQuery(UserInfo.class);
                Root<UserMaster> userTbl = userInfoQuery.from(UserMaster.class);
                Join<UserMaster, Company> joinCompany = userTbl.join("company");
                userInfoQuery
                        .select(builder.construct(UserInfo.class, userTbl.get("id"), joinCompany.get("id"), userTbl.get("name"), joinCompany.get("name"), userTbl.get("mail"),
                                userTbl.get("displayName"), userTbl.get("role"), userTbl.get("info1"), userTbl.get("info2"), userTbl.get("editExtensions")))
                        .where(builder.equal(userTbl.get(searchColumn), user), builder.equal(userTbl.get("password"), password));
                UserInfo userInfo = session.createQuery(userInfoQuery).uniqueResult();

                tx.commit();
                if (userInfo == null) {
                    return false;
                } else {
                    getThreadLocalRequest().getSession().setAttribute(USER_INFO, userInfo);
                    return true;
                }
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void logout() throws IllegalArgumentException {
        getThreadLocalRequest().getSession().removeAttribute(USER_INFO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfo getLoginUserInfo() throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute(USER_INFO);
        if (userInfo == null) {
            throw new IllegalArgumentException("Session Time Out");
        }
        return userInfo;
    }

}
