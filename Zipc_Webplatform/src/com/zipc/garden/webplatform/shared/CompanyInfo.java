package com.zipc.garden.webplatform.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Class that manages company information
 */
public class CompanyInfo implements IsSerializable {

    /** company id */
    private Long id;

    /** company name */
    private String name;

    /**
     * constructor
     */
    public CompanyInfo() {

    }

    /**
     * constructor Used to get records in the company table.<br>
     * The item specified in the SELECT statement must be set in the argument.
     * @param id {@link #id}
     * @param name {@link #name}
     */
    public CompanyInfo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get company id
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set company id
     * @param id {@link #id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get company name
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Set company name
     * @param name {@link #name}
     */
    public void setName(String name) {
        this.name = name;
    }
}
