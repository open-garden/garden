package com.zipc.garden.webplatform.server.logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.server.dao.accessor.UserAccessor;
import com.zipc.garden.webplatform.shared.ProjectInfo;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * The actual logic of user-related asynchronous processing is managed.
 */
public class UserServiceLogic {

    /**
     * Update process of UserMaster table.
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
    public static String saveUserInfo(Long userId, String name, String displayName, String mail, String password, int role, Long companyId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String result = UserAccessor.saveUserInfo(session, userId, name, displayName, mail, password, role, companyId);
                tx.commit();
                return result;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * Get all user information belonging to the specified company ID.
     * @param companyId specified company ID.
     * @return User information
     * @throws IllegalArgumentException
     */
    public static List<UserInfo> getUserInfoList(Long companyId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<UserInfo> userInfoList = UserAccessor.getUserInfoList(session, companyId);
                tx.commit();
                return userInfoList;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * Creates new data in the UserMaster table based on the specified arguments.
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
    public static String createUser(String name, String displayName, String mail, String password, int role, Long companyId,
            boolean isVerifyStatus) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String result = UserAccessor.createUser(session, name, displayName, mail, password, role, companyId, isVerifyStatus);
                tx.commit();
                return result;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * Deletes the users that match the specified list of user IDs from the UserMaster table.
     * @param userIdList specified list of user IDs
     * @throws IllegalArgumentException
     */
    public static void removeUsers(List<Long> userIdList) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                UserAccessor.removeUsers(session, userIdList);
                tx.commit();
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                System.err.println(e.getMessage());
                throw e;
            }
        }
    }

    /**
     * Get all project information.
     * @return key: Project ID / value: Project name
     * @throws IllegalArgumentException
     */
    public static Map<Long, String> getProjectMap() throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<ProjectInfo> projectInfoList = UserAccessor.getProjectList(session);
                tx.commit();
                Map<Long, String> projectMap = new HashMap<Long, String>();
                for (ProjectInfo projectInfo : projectInfoList) {
                    projectMap.put(projectInfo.getId(), projectInfo.getName());
                }
                return projectMap;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * Acquires the information of the user who can handle the specified project.
     * @param projectId specified project ID.
     * @return User information
     * @throws IllegalArgumentException
     */
    public static List<UserInfo> getProjectUserInfoList(Long projectId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<UserInfo> userInfoList = UserAccessor.getProjectUserInfoList(session, projectId);
                tx.commit();
                return userInfoList;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * Acquires the information of the user who cannot handle the specified project.
     * @param projectId specified project ID.
     * @return User information
     * @throws IllegalArgumentException
     */
    public static List<UserInfo> getProjectNotExistUserInfoList(Long projectId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<UserInfo> userInfoList = UserAccessor.getProjectNotExistUserInfoList(session, projectId);
                tx.commit();
                return userInfoList;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * The ProjectUsers table is updated so that the specified project can be operated by the specified user.
     * @param projectId specified project id
     * @param userInfoList specified user info
     * @throws IllegalArgumentException
     */
    public static void updatePorjectUsers(Long projectId, List<UserInfo> userInfoList) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                UserAccessor.updatePorjectUsers(session, projectId, userInfoList);
                tx.commit();
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * A confirmation email of the account entered in sign-up will be sent to the specified email address.
     * @param email Email address entered by the user
     * @param token Token to attach to email
     * @param displayName Display name entered by the user
     * @param serverName Server host name
     * @param serverPort Server port number
     * @param dirPath Property file directory path
     * @return True if the email has been sent to the email address entered by the user
     * @throws IllegalArgumentException
     */
    public static boolean sendVerificationMail(String email, String token, String displayName, String serverName, int serverPort, String dirPath) throws IllegalArgumentException {
        if (email == null)
            return false;
        if (token == null)
            return false;

        String subject = "Sign up confirmation from ZIPC GARDEN";
        Properties prop = new Properties();

        try {
            String fileName = "mail.properties";
            Path path = Paths.get(dirPath);
            String parentPathStr = path.getParent().toString();
            Path parentPath = Paths.get(parentPathStr + "/" + fileName);
            if (Files.notExists(parentPath)) {
                throw new IllegalArgumentException("Mail config file not found!");
            }
            InputStream inputStream = new FileInputStream(parentPath.toFile());
            prop.load(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        // propに設定した情報を使用して、sessionの作成
        javax.mail.Session session = javax.mail.Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(prop.getProperty("fromAddress"), prop.getProperty("password"));
            }
        });

        try {
            String confirmationUrl = "http://" + serverName + ":" + serverPort + "/Zipc_Webplatform" + "?token=" + token + "&signup=true";

            MimeMessage message = new MimeMessage(session);
            message.setFrom(prop.getProperty("fromAddress"));
            message.addRecipients(Message.RecipientType.TO, email);
            message.setSubject(subject);
            message.setText("Hi " + displayName + "," + "\n\n" + "Access the link below to verify your account." + "\n" + confirmationUrl + "\n");
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } catch (Throwable e) {
            throw e;
        }
    }

    /**
     * Gets the token for the user with the specified email address.
     * @param email specified email address.
     * @return token
     * @throws IllegalArgumentException
     */
    public static String createTokenUser(String email) throws IllegalArgumentException {
        if (email == null) {
            return null;
        }
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String token = UserAccessor.createTokenUser(session, email);
                tx.commit();
                return token;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * Check the expiration date of the token.
     * @param token
     * @return True if it has not expired, false if it has expired.
     * @throws IllegalArgumentException
     */
    public static boolean isValidToken(String token) throws IllegalArgumentException {
        if (token == null)
            return false;
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                boolean result = UserAccessor.isValidToken(session, token);
                tx.commit();
                return result;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }

    /**
     * Updates VerifyStatus so that the user associated with the specified token can log in to the system.
     * @param token
     * @throws IllegalArgumentException
     */
    public static void updateUserVerifyStatus(String token) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                UserAccessor.updateUserVerifyStatus(session, token);
                tx.commit();
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
     * @param mail specified email address
     * @throws IllegalArgumentException
     */
    public static void removeUser(String mail) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                UserAccessor.removeUser(session, mail);
                tx.commit();
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                System.err.println(e.getMessage());
                throw e;
            }
        }
    }
}
