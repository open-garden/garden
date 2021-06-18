package com.zipc.garden.webplatform.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.UserProfileServiceImpl;

/**
 * Asynchronous interface for editing user profile
 */
public interface UserProfileServiceAsync {

    /**
     * {@link UserProfileServiceImpl#editUserProfile(String, String, String, String)}
     */
    void editUserProfile(String displayName, String mail, String info1, String info2, AsyncCallback<String> callback) throws IllegalArgumentException;

    /**
     * {@link UserProfileServiceImpl#editUserProfile(String, String)}
     */
    void editUserProfile(String oldPassword, String password, AsyncCallback<String> callback) throws IllegalArgumentException;

    /**
     * {@link UserProfileServiceImpl#editUserProfile(String)}
     */
    void editUserProfile(String userName, AsyncCallback<String> callback) throws IllegalArgumentException;

    /**
     * {@link UserProfileServiceImpl#editUserExtensionType(String)}
     */
    void editUserExtensionType(String extensionType, AsyncCallback<String> callback) throws IllegalArgumentException;
}
