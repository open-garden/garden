package com.zipc.garden.webplatform.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Data Access Object Class for UserProperty Table
 */
@Entity
@Table
public class UserProperty implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = -3150510111290915159L;

    /** Unique ID of the UserProperty table */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /** The project that manages this property. Joined by property ID. */
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "project")
    private Project project;

    /** FM Editor Property Name Choices. */
    @Column
    private String userProperty;

    /** Initial values ​​of FM editor properties */
    @Column
    private String InitialValue;

    /** The user who created the property */
    @OneToOne
    @JoinColumn(name = "createUser")
    private UserMaster createUser;

    /** Property creation date and time */
    @Column
    @CreationTimestamp
    private Timestamp createTime;

    /** The user who last updated the property */
    @OneToOne
    @JoinColumn(name = "updateUser")
    private UserMaster updateUser;

    /** Property update date and time */
    @Column
    @CreationTimestamp
    private Timestamp updateTime;

    /**
     * Get the User Property ID.
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the User Property ID.
     * @param id User Property ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the project that manages this property.
     * @return {@link #project}
     */
    public Project getProject() {
        return project;
    }

    /**
     * Set the project that manages this property.
     * @param project project info
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Gets the name of the property.
     * @return {@link #userProperty}
     */
    public String getUserProperty() {
        return userProperty;
    }

    /**
     * Set the name of the property.
     * @param userProperty Property name choices.
     */
    public void setUserProperty(String userProperty) {
        this.userProperty = userProperty;
    }

    /**
     * Gets the initial value of the property.
     * @return {@link #InitialValue}
     */
    public String getInitialValue() {
        return InitialValue;
    }

    /**
     * Set the initial value of the property.
     * @param initialValue The initial value of the property.
     */
    public void setInitialValue(String initialValue) {
        InitialValue = initialValue;
    }

    /**
     * Get the ID of the user who created the property.
     * @return {@link #createUser}
     */
    public UserMaster getCreateUser() {
        return createUser;
    }

    /**
     * Set the ID of the user who created the property.
     * @param createUser ID of the user who created the property
     */
    public void setCreateUser(UserMaster createUser) {
        this.createUser = createUser;
    }

    /**
     * Gets the property creation date and time.
     * @return {@link #createTime}
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * Set the property creation date and time.
     * @param createTime Property creation date and time
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets the ID of the user who last updated the property.
     * @return {@link #updateUser}
     */
    public UserMaster getUpdateUser() {
        return updateUser;
    }

    /**
     * Set the ID of the user who last updated the property.
     * @param updateUser ID of the user who last updated the property
     */
    public void setUpdateUser(UserMaster updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * Gets the modification date and time of the property.
     * @return {@link #updateTime}
     */
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    /**
     * Set the modification date and time of the property.
     * @param updateTime Property update date and time
     */
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

}
