package com.zipc.garden.webplatform.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * A class that retains the records fetched from UserMaster table and Company table.
 */
public class UserInfo implements IsSerializable {

    /** Company name in the Company table */
    private String companyName;

    /** Company ID in the Company table */
    private Long companyId;

    /** User's name */
    private String userName;

    /** User's ID */
    private Long userId;

    /** User's mail address */
    private String mail;

    /** User's display name */
    private String displayName;

    /** User's role */
    private int role;

    /** User's company name */
    private String info1;

    /** User's position */
    private String info2;

    /**
     * The extension of a file that can be edited in text format. <br>
     * If the Extension class has the same definition, that will take precedence.<br>
     * You can specify multiple extensions by separating them with a semicolon.
     */
    private String editExtensions;

    /**
     * constructor.
     */
    public UserInfo() {

    }

    /**
     * constructor.<br>
     * Used to get records in the UserMaster table.<br>
     * The item specified in the SELECT statement must be set in the argument.
     * @param userId {@link #userId}
     * @param displayName {@link #displayName}
     * @param mail {@link #mail}
     */
    public UserInfo(Long userId, String displayName, String mail) {
        this.userId = userId;
        this.displayName = displayName;
        this.mail = mail;
    }

    /**
     * constructor.<br>
     * Used to get records in the UserMaster table and Company table.<br>
     * The item specified in the SELECT statement must be set in the argument.
     * @param userId {@link #userId}
     * @param companyId {@link #companyId}
     * @param userName {@link #userName}
     * @param companyName {@link #companyName}
     * @param mail {@link #mail}
     * @param displayName {@link #displayName}
     * @param role {@link #role}
     * @param info1 {@link #info1}
     * @param info2 {@link #info2}
     * @param editExtensions {@link #editExtensions}
     */
    public UserInfo(Long userId, Long companyId, String userName, String companyName, String mail, String displayName, int role, String info1, String info2,
            String editExtensions) {
        this.userId = userId;
        this.companyId = companyId;
        this.userName = userName;
        this.companyName = companyName;
        this.setMail(mail);
        this.setDisplayName(displayName);
        this.setRole(role);
        this.setInfo1(info1);
        this.setInfo2(info2);
        this.setEditExtensions(editExtensions);
    }

    /**
     * constructor.<br>
     * Used to get records in the UserMaster table and Company table.<br>
     * The item specified in the SELECT statement must be set in the argument.
     * @param userId {@link #userId}
     * @param companyId {@link #companyId}
     * @param userName {@link #userName}
     * @param companyName {@link #companyName}
     * @param mail {@link #mail}
     * @param displayName {@link #displayName}
     * @param role {@link #role}
     * @param info1 {@link #info1}
     * @param info2 {@link #info2}
     */
    public UserInfo(Long userId, Long companyId, String userName, String companyName, String mail, String displayName, int role, String info1, String info2) {
        this(userId, companyId, userName, companyName, mail, displayName, role, info1, info2, "");
    }

    /**
     * Get the company name.
     * @return {@link #companyName}
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Set the company name.
     * @param companyName {@link #companyName}
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Get the company ID.
     * @return {@link #companyId}
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * Set the company ID.
     * @param companyId {@link #companyId}
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * Get the user's name.
     * @return {@link #userName}
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the user's name.
     * @param userName {@link #userName}
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the user's ID.
     * @return {@link #userId}
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Set the user ID.
     * @param userId {@link #userId}
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
     * @param mail {@link #mail}
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Get the display name of the user.
     * @return {@link #displayName}
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Set the display name of the user.
     * @param displayName {@link #displayName}
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the user role.
     * @return {@link #role}
     */
    public int getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     * @param role {@link #role}
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * Gets the company name of the user.
     * @return {@link #info1}
     */
    public String getInfo1() {
        return info1;
    }

    /**
     * Set the company name of the user.
     * @param info1 {@link #info1}
     */
    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    /**
     * Gets the company position of the user.
     * @return {@link #info2}
     */
    public String getInfo2() {
        return info2;
    }

    /**
     * Set the company position of the user.
     * @param info2 {@link #info2}
     */
    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    /**
     * Gets the file extension that can be edited in text format.
     * @return {@link #editExtensions}
     */
    public String getEditExtensions() {
        return editExtensions;
    }

    /**
     * Set the file extension that can be edited in text format.
     * @param editExtensions {@link #editExtensions}
     */
    public void setEditExtensions(String editExtensions) {
        this.editExtensions = editExtensions;
    }

}
