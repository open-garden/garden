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
 * Data Access Object Class for PatternElement Table
 */
@Entity
@Table
public class PatternElement implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = -3150510111290915159L;

    /** Unique ID of the JobStatus table */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /** The project that manages the Pattern Element. Joined by project ID. */
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "project")
    private Project project;

    /** The name of the pattern element. */
    @Column
    private String name;

    /** The value of the pattern element. */
    @Column(columnDefinition = "TEXT")
    private String value;

    /** The user who created the pattern element. Joined by user ID. */
    @OneToOne
    @JoinColumn(name = "createUser")
    private UserMaster createUser;

    /** The date and time when the pattern element was created. */
    @Column
    @CreationTimestamp
    private Timestamp createTime;

    /** The user who updated the pattern element. Joined by user ID. */
    @OneToOne
    @JoinColumn(name = "updateUser")
    private UserMaster updateUser;

    /** The date and time when the pattern element was updated. */
    @Column
    @CreationTimestamp
    private Timestamp updateTime;

    /**
     * Gets the unique ID of the PatternElement table.
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set a unique ID for the PatternElement table.
     * @param id Unique ID of the PatternElement table
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the information of the project in which the pattern element is managed.
     * @return {@link #project}
     */
    public Project getProject() {
        return project;
    }

    /**
     * Set up a project that manages pattern elements.
     * @param project Project to manage pattern elements
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Gets the name of the pattern element.
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the pattern element.
     * @param name The name of the pattern element
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of the pattern element.
     * @return {@link #value}
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the pattern element.
     * @param value The value of the pattern element.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the user who created the pattern element.
     * @return {@link #createUser}
     */
    public UserMaster getCreateUser() {
        return createUser;
    }

    /**
     * Set the user who created the pattern element.
     * @param createUser The user who created the pattern element
     */
    public void setCreateUser(UserMaster createUser) {
        this.createUser = createUser;
    }

    /**
     * Gets the creation date and time of the pattern element.
     * @return {@link createTime}
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * Set the creation date and time of the pattern element.
     * @param updateTime Pattern element creation date and time
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets the user who last updated the pattern element.
     * @return {@link #updateUser}
     */
    public UserMaster getUpdateUser() {
        return updateUser;
    }

    /**
     * Sets the user who last updated the pattern element.
     * @param updateUser The user who last updated the pattern element
     */
    public void setUpdateUser(UserMaster updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * Gets the updated date and time of the pattern element.
     * @return {@link #updateTime}
     */
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    /**
     * Set the updated date and time of the pattern element.
     * @param updateTime Pattern element update date and time
     */
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

}
