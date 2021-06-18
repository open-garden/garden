package com.zipc.garden.webplatform.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Data Access Object Class for UserMaster Table
 */
@Entity
@Table(name = "UserMaster")
public class UserMaster implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = -5409307912881023467L;

    /** Unique ID of the UserMaster table */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /** user name */
    @Column
    private String name;

    /** user password */
    @Column
    private String password;

    /** The company to which the user belongs. Joined to the company ID. */
    @OneToOne
    @JoinColumn(name = "companyId")
    private Company company;

    /** User Role. */
    @Column
    private int role;

    /** User's email address. */
    @Column
    private String mail;

    /** The user's display name. */
    @Column
    private String displayName;

    /** User company information */
    @Column
    private String info1;

    /** User's official position */
    @Column
    private String info2;

    /** Text-editable file extension. You can specify multiple items separated by semicolons. */
    @Column
    private String editExtensions;

    /** The token information associated with the user. */
    @OneToMany(mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<VerificationToken> verificationTokens;

    /** A flag that confirms that you are an authenticated user. */
    @Column
    private boolean verifyStatus;

    /**
     * constructor
     */
    public UserMaster() {

    }

    /**
     * constructor. <br>
     * Used when dealing with the specified user.
     * @param id user id
     */
    public UserMaster(Long id) {
        this.id = id;
    }

    /**
     * Get the user ID.
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the user ID.
     * @param id user ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the user name.
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Set the user name.
     * @param name user name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the user password.
     * @return {@link #password}
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user password.
     * @param password user password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Acquires the company information to which the user belongs.
     * @return {@link #company}
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Set the company information to which the user belongs.
     * @param company The company to which the user belongs.
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Get the user role.
     * @return {@link #role}
     */
    public int getRole() {
        return role;
    }

    /**
     * Set the user role.
     * @param role user role.
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * Get the user's email address.
     * @return {@link #mail}
     */
    public String getMail() {
        return mail;
    }

    /**
     * Set the user's email address.
     * @param mail User's email address.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Gets the user's display name.
     * @return {@link #displayName}
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Set the user's display name.
     * @param displayName user's display name.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Get user company information.
     * @return {@link #info1}
     */
    public String getInfo1() {
        return info1;
    }

    /**
     * Set user company information.
     * @param info1 User company information
     */
    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    /**
     * Get the user's official position.
     * @return {@link #info2}
     */
    public String getInfo2() {
        return info2;
    }

    /**
     * Set the user's official position.
     * @param info2 User's official position
     */
    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    /**
     * Gets the editable extension.
     * @return {@link #editExtensions}
     */
    public String getEditExtensions() {
        return editExtensions;
    }

    /**
     * Set the editable extension.
     * @param editExtensions Text-editable file extension. You can specify multiple items separated by semicolons.
     */
    public void setEditExtensions(String editExtensions) {
        this.editExtensions = editExtensions;
    }

    /**
     * Gets the token associated with the user.
     * @return {@link #verificationTokens}
     */
    public List<VerificationToken> getVerificationTokens() {
        return verificationTokens;
    }

    /**
     * Set the token information for the user.
     * @param verificationTokens The token information associated with the user.
     */
    public void setVerificationTokens(List<VerificationToken> verificationTokens) {
        this.verificationTokens = verificationTokens;
    }

    /**
     * Get verify status.
     * @return {@link #verifyStatus}<br>
     *         In the case of ture, it is an authenticated user.
     */
    public boolean isVerifyStatus() {
        return verifyStatus;
    }

    /**
     * Set verify status.
     * @param verifyStatus Whether it is authenticated.<br>
     *            In the case of ture, it is an authenticated user.
     */
    public void setVerifyStatus(boolean verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
}
