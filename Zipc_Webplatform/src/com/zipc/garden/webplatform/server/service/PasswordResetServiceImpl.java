package com.zipc.garden.webplatform.server.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.servlet.ServletContext;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.webplatform.client.service.PasswordResetService;
import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.UserMaster;
import com.zipc.garden.webplatform.dao.VerificationToken;
import com.zipc.garden.webplatform.server.PasswordUtil;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the PasswordResetService interface.
 */
@SuppressWarnings("serial")
public class PasswordResetServiceImpl extends RemoteServiceServlet implements PasswordResetService {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmailRegistered(String email) throws IllegalArgumentException {
        if (email == null) {
            return false;
        }
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String searchColumn = "mail";
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<UserMaster> mailQuery = builder.createQuery(UserMaster.class);
                Root<UserMaster> root = mailQuery.from(UserMaster.class);
                mailQuery.select(root).where(builder.like(root.get(searchColumn), email.trim()));
                Query<UserMaster> query = session.createQuery(mailQuery);
                UserMaster userMaster = query.uniqueResult();
                tx.commit();
                if (userMaster == null) {
                    return false;
                } else {
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
    public String resetPassword(String email) throws IllegalArgumentException {
        if (email == null) {
            return null;
        }
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
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
     * Generate a new token to associate with the user information.
     * @return new token.
     */
    private String generateToken() {
        String token = UUID.randomUUID().toString();
        return token;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendPasswordResetMail(String email, String token) throws IllegalArgumentException {
        if (email == null)
            return false;
        if (token == null)
            return false;
        Properties prop = new Properties();
        try {
            String fileName = "mail.properties";

            ServletContext context = getServletContext();
            String pathStr = context.getRealPath("./");

            Path path = Paths.get(pathStr);
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
            String serverName = getThreadLocalRequest().getServerName();
            int serverPort = getThreadLocalRequest().getServerPort();
            String confirmationUrl = "http://" + serverName + ":" + serverPort + "/Zipc_Webplatform" + "?token=" + token;

            MimeMessage message = new MimeMessage(session);
            message.setFrom(prop.getProperty("fromAddress"));
            message.addRecipients(Message.RecipientType.TO, email);
            message.setSubject("Password Reset Email", prop.getProperty("encord"));
            message.setText("\"Please confirm your email by clicking the link \"" + "\n" + confirmationUrl + "\n");
            System.out.println(confirmationUrl);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        } catch (Throwable e) {
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidToken(String token) throws IllegalArgumentException {
        if (token == null)
            return false;
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
                    return false;
                } else {
                    // check VerificationToken expire date
                    Date expiryDate = verificationToken.getExpiryDate();
                    Date now = new Date();
                    if (expiryDate.compareTo(now) <= 0) {
                        // VerificationToken has expired
                        // delete the token
                        session.delete(verificationToken);
                        tx.commit();
                        return false;
                    }
                    tx.commit();
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
    public boolean changePassword(String token, String newPassword) throws IllegalArgumentException {
        if (token == null) {
            return false;
        }
        if (newPassword == null) {
            return false;
        }
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                // get UserMaster from token
                tx = session.beginTransaction();
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<UserMaster> userMasterQuery = builder.createQuery(UserMaster.class);
                Root<UserMaster> userMasterTbl = userMasterQuery.from(UserMaster.class);
                Join<UserMaster, VerificationToken> joinVerificationToken = userMasterTbl.join("verificationTokens");
                userMasterQuery.select(userMasterTbl).where(builder.equal(joinVerificationToken.get("token"), token));
                Query<UserMaster> query = session.createQuery(userMasterQuery);
                UserMaster userMaster = query.uniqueResult();
                // update UserMaster with new password
                if (userMaster == null) {
                    // can UserMaster be null here?
                    // if UserMaster row is deleted, related VerificationToken row get deleted also?
                    return false;
                }

                String passwordHash = PasswordUtil.getSafetyPassword(newPassword, userMaster.getCompany().getId().toString() + userMaster.getId().toString());
                userMaster.setPassword(passwordHash);
                session.save(userMaster);
                // remove token
                CriteriaDelete<VerificationToken> delQuery = builder.createCriteriaDelete(VerificationToken.class);
                Root<VerificationToken> root = delQuery.from(VerificationToken.class);
                delQuery.where(builder.equal(root.get("token"), token));
                session.createQuery(delQuery).executeUpdate();
                // commit the transaction
                tx.commit();
                return true;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }
}
