package com.zipc.garden.webplatform.server.dao.accessor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.zipc.garden.webplatform.client.editor.ace.AceMode;
import com.zipc.garden.webplatform.dao.Company;
import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.Project;
import com.zipc.garden.webplatform.dao.ProjectUsers;
import com.zipc.garden.webplatform.dao.ProjectUsers.ProjectUsersPK;
import com.zipc.garden.webplatform.dao.UserMaster;
import com.zipc.garden.webplatform.dao.VerificationToken;
import com.zipc.garden.webplatform.server.PasswordUtil;
import com.zipc.garden.webplatform.shared.ProjectInfo;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * Manages the process of accessing user related databases.
 */
public class UserAccessor {

    /**
     * Information of the specified user is reflected in the UserMaster table.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param userId ID of user to update
     * @param name Name of user to update
     * @param displayName Display name of user to update
     * @param mail MailAddress of user to update
     * @param password Password of user to update
     * @param role Role of user to update
     * @param companyId Company ID of user to update
     * @return If the string is empty, the update has been successful. In case of error, an error message will be returned.
     * @throws IllegalArgumentException
     */
    public static String saveUserInfo(Session session, Long userId, String name, String displayName, String mail, String password, int role,
            Long companyId) throws IllegalArgumentException {
        UserMaster user = session.byId(UserMaster.class).load(userId);
        if (name != null && (!name.equals(user.getName()) || user.getCompany().getId() != companyId)) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<UserMaster> root = query.from(UserMaster.class);
            query.select(builder.count(root)).where(builder.equal(root.get("name"), name), builder.equal(root.get("company").get("id"), companyId));
            Long count = session.createQuery(query).getSingleResult();
            if (count > 0) {
                return "The name you entered is already used.";
            }
        }
        if (mail != null && !mail.equals(user.getMail())) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<UserMaster> root = query.from(UserMaster.class);
            query.select(builder.count(root)).where(builder.equal(root.get("mail"), mail), builder.equal(root.get("company").get("id"), companyId));
            Long count = session.createQuery(query).getSingleResult();
            if (count > 0) {
                return "The email address you entered is already exists.";
            }
        }

        if (name != null && !name.isEmpty()) {
            user.setName(name);
        }

        if (displayName != null && !displayName.isEmpty()) {
            user.setDisplayName(displayName);
        }

        if (mail != null && !mail.isEmpty()) {
            user.setMail(mail);
        }

        if (role != -1) {
            user.setRole(role);
        }

        if (password != null && !password.isEmpty()) {
            String passwordHash = PasswordUtil.getSafetyPassword(password, companyId.toString() + user.getId().toString());
            user.setPassword(passwordHash);
        }

        user.setCompany(new Company(companyId));

        session.save(user);
        return "";
    }

    /**
     * Access the UserMaster table and Company table to get all user information belonging to the specified company ID.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param companyId specified company ID.
     * @return User information
     * @throws IllegalArgumentException
     */
    public static List<UserInfo> getUserInfoList(Session session, Long companyId) throws IllegalArgumentException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserInfo> userInfoQuery = builder.createQuery(UserInfo.class);
        Root<UserMaster> userTbl = userInfoQuery.from(UserMaster.class);
        Join<UserMaster, Company> joinCompany = userTbl.join("company");
        userInfoQuery.select(builder.construct(UserInfo.class, userTbl.get("id"), joinCompany.get("id"), userTbl.get("name"), joinCompany.get("name"), userTbl.get("mail"),
                userTbl.get("displayName"), userTbl.get("role"), userTbl.get("info1"), userTbl.get("info2")));
        userInfoQuery.where(builder.equal(joinCompany.get("id"), companyId));
        List<UserInfo> userInfoList = session.createQuery(userInfoQuery).list();
        return userInfoList;
    }

    /**
     * Creates new data in the UserMaster table based on the specified arguments.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param name Name of user to create
     * @param displayName Display name of user to create
     * @param mail MailAddress of user to create
     * @param password Password of user to create
     * @param role Role of user to create
     * @param companyId Company ID of user to create
     * @param isVerifyStatus Whether to create a user whose account is authenticated.<br>
     *            True: Certified<br>
     *            False: Not authenticated
     * @return If the string is empty, the update has been successful. In case of error, an error message will be returned.
     * @throws IllegalArgumentException
     */
    public static String createUser(Session session, String name, String displayName, String mail, String password, int role, Long companyId,
            boolean isVerifyStatus) throws IllegalArgumentException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        if (companyId == -1L) {
            CriteriaQuery<Company> query = builder.createQuery(Company.class);
            Root<Company> root = query.from(Company.class);
            query.select(root).where(builder.equal(root.get("name"), "Unknown"));
            Company company = session.createQuery(query).getSingleResult();
            companyId = company.getId();
        }
        if (name != null && !name.isEmpty()) {
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<UserMaster> root = query.from(UserMaster.class);
            query.select(builder.count(root)).where(builder.equal(root.get("name"), name), builder.equal(root.get("company").get("id"), companyId));
            Long count = session.createQuery(query).getSingleResult();
            if (count > 0) {
                return "The name you entered is already used.";
            }
        }

        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<UserMaster> root = query.from(UserMaster.class);
        query.select(builder.count(root)).where(builder.equal(root.get("mail"), mail));
        Long count = session.createQuery(query).getSingleResult();
        if (count > 0) {
            return "The email address you entered is already exists.";
        }
        UserMaster user = new UserMaster();
        user.setName(name);
        user.setCompany(new Company(companyId));
        user.setRole(role);
        user.setMail(mail);
        user.setDisplayName(displayName);
        String extensions = "";
        for (AceMode mode : AceMode.values()) {
            if (AceMode.UNDEFINED != mode) {
                extensions += mode.getExtension() + ";";
            }
        }
        user.setEditExtensions(extensions);
        user.setVerifyStatus(isVerifyStatus);
        session.persist(user);
        String passwordHash = PasswordUtil.getSafetyPassword(password, companyId.toString() + user.getId().toString());
        user.setPassword(passwordHash);
        session.save(user);
        return "";
    }

    /**
     * Deletes the users that match the specified list of user IDs from the UserMaster table.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param userIdList specified list of user IDs
     * @throws IllegalArgumentException
     */
    public static void removeUsers(Session session, List<Long> userIdList) throws IllegalArgumentException {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaDelete<UserMaster> delQuery = builder.createCriteriaDelete(UserMaster.class);
        Root<UserMaster> root = delQuery.from(UserMaster.class);
        delQuery.where(root.get("id").in(userIdList));
        session.createQuery(delQuery).executeUpdate();
    }

    /**
     * Access the Project table to get information about all projects.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @return information about all projects.
     * @throws IllegalArgumentException
     */
    public static List<ProjectInfo> getProjectList(Session session) throws IllegalArgumentException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ProjectInfo> projectInfoQuery = builder.createQuery(ProjectInfo.class);
        Root<Project> projectTbl = projectInfoQuery.from(Project.class);
        projectInfoQuery.select(builder.construct(ProjectInfo.class, projectTbl.get("id"), projectTbl.get("name")));
        List<ProjectInfo> projectInfoList = session.createQuery(projectInfoQuery).list();
        return projectInfoList;
    }

    /**
     * Access the UserMaster table and ProjectUsers table to get information about users who can handle the specified project.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param projectId specified project ID.
     * @return User information
     * @throws IllegalArgumentException
     */
    public static List<UserInfo> getProjectUserInfoList(Session session, Long projectId) throws IllegalArgumentException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserInfo> usesrInfoQuery = builder.createQuery(UserInfo.class);
        Root<ProjectUsers> projectUsersTbl = usesrInfoQuery.from(ProjectUsers.class);
        Root<UserMaster> userTbl = usesrInfoQuery.from(UserMaster.class);
        usesrInfoQuery.select(builder.construct(UserInfo.class, userTbl.get("id"), userTbl.get("displayName"), userTbl.get("mail")))
                .where(builder.equal(projectUsersTbl.get("pk").get("userMaster"), userTbl.get("id")), builder.equal(projectUsersTbl.get("pk").get("project"), projectId));
        List<UserInfo> userInfoList = session.createQuery(usesrInfoQuery).getResultList();
        return userInfoList;
    }

    /**
     * Access the UserMaster table and ProjectUsers table to get information about users who cannot handle the specified
     * project.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param projectId specified project ID.
     * @return User information
     * @throws IllegalArgumentException
     */
    public static List<UserInfo> getProjectNotExistUserInfoList(Session session, Long projectId) throws IllegalArgumentException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserInfo> usesrInfoQuery = builder.createQuery(UserInfo.class);
        Root<UserMaster> userTbl = usesrInfoQuery.from(UserMaster.class);

        Subquery<ProjectUsers> subQuery = usesrInfoQuery.subquery(ProjectUsers.class);
        Root<ProjectUsers> projectUsersTbl = subQuery.from(ProjectUsers.class);
        subQuery.select(projectUsersTbl).where(builder.equal(userTbl.get("id"), projectUsersTbl.get("pk").get("userMaster")),
                builder.equal(projectUsersTbl.get("pk").get("project"), projectId));

        usesrInfoQuery.select(builder.construct(UserInfo.class, userTbl.get("id"), userTbl.get("displayName"), userTbl.get("mail"))).where(builder.not(builder.exists(subQuery)));
        List<UserInfo> userInfoList = session.createQuery(usesrInfoQuery).getResultList();
        return userInfoList;
    }

    /**
     * The ProjectUsers table is updated so that the specified project can be operated by the specified user.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param projectId specified project id
     * @param userInfoList specified user info
     * @throws IllegalArgumentException
     */
    public static void updatePorjectUsers(Session session, Long projectId, List<UserInfo> userInfoList) throws IllegalArgumentException {
        Project project = session.byId(Project.class).load(projectId);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaDelete<ProjectUsers> delQuery = builder.createCriteriaDelete(ProjectUsers.class);
        Root<ProjectUsers> root = delQuery.from(ProjectUsers.class);
        delQuery.where(builder.equal(root.get("pk").get("project"), project));
        session.createQuery(delQuery).executeUpdate();

        for (UserInfo userInfo : userInfoList) {
            ProjectUsers pu = new ProjectUsers();
            pu.setPk(new ProjectUsersPK(new UserMaster(userInfo.getUserId()), project));
            session.persist(pu);
        }
    }

    /**
     * Gets the token for the user with the specified email address.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param email specified email address.
     * @return token
     * @throws IllegalArgumentException
     */
    public static String createTokenUser(Session session, String email) throws IllegalArgumentException {
        // get User from email
        String searchColumn = "mail";
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserMaster> mailQuery = builder.createQuery(UserMaster.class);
        Root<UserMaster> root = mailQuery.from(UserMaster.class);
        mailQuery.select(root).where(builder.like(root.get(searchColumn), email.trim()));
        Query<UserMaster> query = session.createQuery(mailQuery);
        UserMaster userMaster = query.uniqueResult();
        // generate token
        String token = generateToken();
        // create VerificationToken for user
        VerificationToken verificationToken = new VerificationToken(token, userMaster);
        session.save(verificationToken);
        return token;
    }

    /**
     * Generate a new token.
     * @return token
     */
    private static String generateToken() {
        String token = UUID.randomUUID().toString();
        return token;
    }

    /**
     * Check the expiration date of the token.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param token
     * @return True if it has not expired, false if it has expired.
     * @throws IllegalArgumentException
     */
    public static boolean isValidToken(Session session, String token) throws IllegalArgumentException {
        String searchColumn = "token";
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<VerificationToken> tokenQuery = builder.createQuery(VerificationToken.class);
        Root<VerificationToken> root = tokenQuery.from(VerificationToken.class);
        tokenQuery.select(root).where(builder.like(root.get(searchColumn), token.trim()));
        Query<VerificationToken> query = session.createQuery(tokenQuery);
        VerificationToken verificationToken = query.uniqueResult();
        if (verificationToken == null) {
            return false;
        } else {
            // check VerificationToken expire date
            Date expiryDate = verificationToken.getExpiryDate();
            Date now = new Date();
            if (expiryDate.compareTo(now) <= 0) {
                // VerificationToken has expired
                // delete the token
                session.delete(verificationToken);
                return false;
            }
            return true;
        }
    }

    /**
     * Updates VerifyStatus so that the user associated with the specified token can log in to the system.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param token
     * @throws IllegalArgumentException
     */
    public static void updateUserVerifyStatus(Session session, String token) throws IllegalArgumentException {
        Long userId = getTokenUser(token);
        String searchColumn = "id";
        String updateColumn = "verifyStatus";
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaUpdate<UserMaster> updQuery = builder.createCriteriaUpdate(UserMaster.class);
        Root<UserMaster> root = updQuery.from(UserMaster.class);
        updQuery.where(builder.equal(root.get(searchColumn), userId));
        updQuery.set(updateColumn, true);
        session.createQuery(updQuery).executeUpdate();

        // remove token
        CriteriaDelete<VerificationToken> delQuery = builder.createCriteriaDelete(VerificationToken.class);
        Root<VerificationToken> tokenRoot = delQuery.from(VerificationToken.class);
        delQuery.where(builder.equal(tokenRoot.get("token"), token));
        session.createQuery(delQuery).executeUpdate();
    }

    /**
     * Fetch the ID of the user who has the specified token.
     * @param token
     * @return ID of the user
     * @throws IllegalArgumentException
     */
    private static Long getTokenUser(String token) throws IllegalArgumentException {
        if (token == null)
            return 0L;
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String searchColumn = "token";
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<VerificationToken> tokenQuery = builder.createQuery(VerificationToken.class);
                Root<VerificationToken> root = tokenQuery.from(VerificationToken.class);
                tokenQuery.select(root).where(builder.like(root.get(searchColumn), token.trim()));
                Query<VerificationToken> query = session.createQuery(tokenQuery);
                VerificationToken verificationToken = query.uniqueResult();
                if (verificationToken == null) {
                    tx.commit();
                    return 0L;
                } else {
                    Long userId = verificationToken.getUser().getId();
                    tx.commit();
                    return userId;
                }
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * Delete the token information that matches the specified email address and the user information temporarily created during
     * sign-up.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param mail specified email address
     * @throws IllegalArgumentException
     */
    public static void removeUser(Session session, String mail) throws IllegalArgumentException {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<UserMaster> selectUserQuery = builder.createQuery(UserMaster.class);
        Root<UserMaster> root = selectUserQuery.from(UserMaster.class);
        selectUserQuery.select(root).where(builder.equal(root.get("mail"), mail));
        Query<UserMaster> query = session.createQuery(selectUserQuery);
        UserMaster user = query.uniqueResult();
        Long userId = user.getId();

        CriteriaQuery<VerificationToken> selectTokenQuery = builder.createQuery(VerificationToken.class);
        Root<VerificationToken> selectTokenRoot = selectTokenQuery.from(VerificationToken.class);
        Join<VerificationToken, UserMaster> jointFile = selectTokenRoot.join("user");
        selectTokenQuery.select(selectTokenRoot).where(builder.equal(jointFile.get("id"), userId));
        VerificationToken token = session.createQuery(selectTokenQuery).uniqueResult();

        CriteriaDelete<VerificationToken> delTokenQuery = builder.createCriteriaDelete(VerificationToken.class);
        Root<VerificationToken> tokenRoot = delTokenQuery.from(VerificationToken.class);
        delTokenQuery.where(builder.equal(tokenRoot.get("id"), token.getId()));
        session.createQuery(delTokenQuery).executeUpdate();

        CriteriaDelete<UserMaster> delUserQuery = builder.createCriteriaDelete(UserMaster.class);
        root = delUserQuery.from(UserMaster.class);
        delUserQuery.where(builder.equal(root.get("id"), userId));
        session.createQuery(delUserQuery).executeUpdate();
    }
}
