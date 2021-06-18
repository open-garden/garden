package com.zipc.garden.webplatform.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Class that manages pattern element information
 */
public class PatternElementInfo implements IsSerializable {

    /** pattern element id */
    private Long id;

    /** pattern element name */
    private String name;

    /** pattern element value */
    private String value;

    /**
     * Get pattern elenent id
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set pattern element id
     * @param id {@link #id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get pattern element name
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Set pattern element name
     * @param name {@link #name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get pattern element value
     * @return {@link #value}
     */
    public String getValue() {
        return value;
    }

    /**
     * Set pattern element value
     * @param value {@link #value}
     */
    public void setValue(String value) {
        this.value = value;
    }

}
