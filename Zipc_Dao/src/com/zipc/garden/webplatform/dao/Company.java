package com.zipc.garden.webplatform.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Data Access Object Class for Company Table
 */
@Entity
@Table
public class Company implements Serializable {

    /** A unique identifier for the Serializable class */
    private static final long serialVersionUID = -7184199145364158814L;

    /** Unique ID of the Company table */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /** Company Name */
    @Column
    private String name;

    /**
     * constructor
     */
    public Company() {

    }

    /**
     * constructor <br>
     * Use this if the operation is for the specified Company ID.
     * @param id specified Company ID
     */
    public Company(Long id) {
        this.id = id;
    }

    /**
     * Get the company ID.
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the company ID.
     * @param Company ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the company name.
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Set the company name.
     * @param name Company name.
     */
    public void setName(String name) {
        this.name = name;
    }

}
