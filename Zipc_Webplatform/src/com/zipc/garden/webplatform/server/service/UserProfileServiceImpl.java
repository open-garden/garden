package com.zipc.garden.webplatform.server.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.webplatform.client.service.UserProfileService;
import com.zipc.garden.webplatform.dao.Company;
import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.UserMaster;
import com.zipc.garden.webplatform.server.PasswordUtil;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the UserProfileService interface.
 */
@SuppressWarnings("serial")
public class UserProfileServiceImpl extends RemoteServiceServlet implements UserProfileService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String editUserProfile(String displayName, String mail, String info1, String info2) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new IllegalArgumentException("Session time out!");
        }
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                // Change user profile
                UserMaster newUser = session.byId(UserMaster.class).load(userInfo.getUserId());
                newUser.setDisplayName(displayName);
                newUser.setMail(mail);
                newUser.setInfo1(info1);
                newUser.setInfo2(info2);
                session.save(newUser);

                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                CriteriaQuery<UserInfo> userInfoQuery = criteriaBuilder.createQuery(UserInfo.class);
                Root<UserMaster> userTbl = userInfoQuery.from(UserMaster.class);
                Join<UserMaster, Company> joinCompany = userTbl.join("company");
                userInfoQuery
                        .select(criteriaBuilder.construct(UserInfo.class, userTbl.get("id"), joinCompany.get("id"), userTbl.get("name"), joinCompany.get("name"),
                                userTbl.get("mail"), userTbl.get("displayName"), userTbl.get("role"), userTbl.get("info1"), userTbl.get("info2"), userTbl.get("editExtensions")))
                        .where(criteriaBuilder.equal(userTbl.get("name"), newUser.getName()), criteriaBuilder.equal(userTbl.get("password"), newUser.getPassword()),
                                criteriaBuilder.equal(joinCompany.get("name"), userInfo.getCompanyName()));
                UserInfo newInfo = session.createQuery(userInfoQuery).uniqueResult();

                tx.commit();
                if (newInfo == null) {
                    throw new IllegalArgumentException("Session time out!");
                } else {
                    getThreadLocalRequest().getSession().setAttribute("userInfo", newInfo);
                    return "";
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
    public String editUserProfile(String password, String oldPassword) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new IllegalArgumentException("Session time out!");
        }
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                // change password
                UserMaster newUser = session.byId(UserMaster.class).load(userInfo.getUserId());
                String passwordHash = PasswordUtil.getSafetyPassword(oldPassword, userInfo.getCompanyId().toString() + userInfo.getUserId().toString());
                String newPass = "";
                if (null != password && !"".equals(password)) {
                    newPass = PasswordUtil.getSafetyPassword(password, userInfo.getCompanyId().toString() + userInfo.getUserId().toString());
                }

                if (passwordHash.equals(newUser.getPassword())) {
                    if (null != newPass && !"".equals(newPass)) {
                        // check password not change input
                        if (passwordHash.equals(newPass)) {
                            return "User password not update, user input the same password as the current password.";
                        }
                        newUser.setPassword(newPass);
                        session.save(newUser);
                    }

                } else {
                    return "The old password you entered is incorrect.";
                }

                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                CriteriaQuery<UserInfo> userInfoQuery = criteriaBuilder.createQuery(UserInfo.class);
                Root<UserMaster> userTbl = userInfoQuery.from(UserMaster.class);
                Join<UserMaster, Company> joinCompany = userTbl.join("company");
                userInfoQuery
                        .select(criteriaBuilder.construct(UserInfo.class, userTbl.get("id"), joinCompany.get("id"), userTbl.get("name"), joinCompany.get("name"),
                                userTbl.get("mail"), userTbl.get("displayName"), userTbl.get("role"), userTbl.get("info1"), userTbl.get("info2"), userTbl.get("editExtensions")))
                        .where(criteriaBuilder.equal(userTbl.get("name"), newUser.getName()), criteriaBuilder.equal(userTbl.get("password"), newUser.getPassword()),
                                criteriaBuilder.equal(joinCompany.get("name"), userInfo.getCompanyName()));
                UserInfo newInfo = session.createQuery(userInfoQuery).uniqueResult();

                tx.commit();
                if (newInfo == null) {
                    throw new IllegalArgumentException("Session time out!");
                } else {
                    getThreadLocalRequest().getSession().setAttribute("userInfo", newInfo);
                    return "";
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
    public String editUserProfile(String name) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new IllegalArgumentException("Session time out!");
        }
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                // change user name
                UserMaster newUser = session.byId(UserMaster.class).load(userInfo.getUserId());
                if (newUser.getName() == null || !newUser.getName().equals(name)) {
                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaQuery<Long> query = builder.createQuery(Long.class);
                    Root<UserMaster> root = query.from(UserMaster.class);
                    query.select(builder.count(root)).where(builder.equal(root.get("name"), name), builder.equal(root.get("company").get("id"), userInfo.getCompanyId()));
                    Long count = session.createQuery(query).getSingleResult();
                    if (count > 0) {
                        return "The user name you entered is already exists.";
                    } else {
                        newUser.setName(name);
                        session.save(newUser);
                    }
                }

                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                CriteriaQuery<UserInfo> userInfoQuery = criteriaBuilder.createQuery(UserInfo.class);
                Root<UserMaster> userTbl = userInfoQuery.from(UserMaster.class);
                Join<UserMaster, Company> joinCompany = userTbl.join("company");
                userInfoQuery
                        .select(criteriaBuilder.construct(UserInfo.class, userTbl.get("id"), joinCompany.get("id"), userTbl.get("name"), joinCompany.get("name"),
                                userTbl.get("mail"), userTbl.get("displayName"), userTbl.get("role"), userTbl.get("info1"), userTbl.get("info2"), userTbl.get("editExtensions")))
                        .where(criteriaBuilder.equal(userTbl.get("name"), newUser.getName()), criteriaBuilder.equal(userTbl.get("password"), newUser.getPassword()),
                                criteriaBuilder.equal(joinCompany.get("name"), userInfo.getCompanyName()));
                UserInfo newInfo = session.createQuery(userInfoQuery).uniqueResult();

                tx.commit();
                if (newInfo == null) {
                    throw new IllegalArgumentException("Session time out!");
                } else {
                    getThreadLocalRequest().getSession().setAttribute("userInfo", newInfo);
                    return "";
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
    public String editUserExtensionType(String extensionType) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        if (userInfo == null) {
            throw new IllegalArgumentException("Session time out!");
        }
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                UserMaster newUser = session.byId(UserMaster.class).load(userInfo.getUserId());
                if (extensionType != null && !extensionType.isEmpty()) {
                    newUser.setEditExtensions(extensionType);
                    session.save(newUser);
                }

                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                CriteriaQuery<UserInfo> userInfoQuery = criteriaBuilder.createQuery(UserInfo.class);
                Root<UserMaster> userTbl = userInfoQuery.from(UserMaster.class);
                Join<UserMaster, Company> joinCompany = userTbl.join("company");
                userInfoQuery
                        .select(criteriaBuilder.construct(UserInfo.class, userTbl.get("id"), joinCompany.get("id"), userTbl.get("name"), joinCompany.get("name"),
                                userTbl.get("mail"), userTbl.get("displayName"), userTbl.get("role"), userTbl.get("info1"), userTbl.get("info2"), userTbl.get("editExtensions")))
                        .where(criteriaBuilder.equal(userTbl.get("name"), newUser.getName()), criteriaBuilder.equal(userTbl.get("password"), newUser.getPassword()),
                                criteriaBuilder.equal(joinCompany.get("name"), userInfo.getCompanyName()));
                UserInfo newInfo = session.createQuery(userInfoQuery).uniqueResult();

                tx.commit();
                if (newInfo == null) {
                    throw new IllegalArgumentException("Session time out!");
                } else {
                    getThreadLocalRequest().getSession().setAttribute("userInfo", newInfo);
                    return "";
                }
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }
}
