package com.zipc.garden.webplatform.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * A class that retains the records fetched from UserProperty table.
 */
public class UserPropertyInfo implements IsSerializable {

    /** user property id */
    private Long id;

    /** user property name */
    private String userProperty;

    /** user property initial value */
    private String initialValue;

    /**
     * Get user property ID
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Set user property ID
     * @param id {@link #id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the name of a user property
     * @return {@link #userProperty}
     */
    public String getUserProperty() {
        return userProperty;
    }

    /**
     * Set the name of the user property
     * @param userProperty {@link #userProperty}
     */
    public void setUserProperty(String userProperty) {
        this.userProperty = userProperty;
    }

    /**
     * Get the initial value of the user property
     * @return {@link #initialValue}
     */
    public String getInitialValue() {
        return initialValue;
    }

    /**
     * Set initial values ​​for user properties
     * @param initialValue {@link #initialValue}
     */
    public void setInitialValue(String initialValue) {
        this.initialValue = initialValue;
    }

}
