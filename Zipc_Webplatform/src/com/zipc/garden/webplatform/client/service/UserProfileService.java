package com.zipc.garden.webplatform.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Defines an interface derived from RemoteService.<br>
 * Lists all RPC methods needed to edit a user profile.
 */
@RemoteServiceRelativePath("profile")
public interface UserProfileService extends RemoteService {

    /**
     * Update dispalyName, mail, info1 and info2 of UserInfo.<BR>
     * Returns empty message, if updating UserInfo success.
     * @param displayName
     * @param mail
     * @param info1
     * @param info2
     * @return Returns Error message, if updating UserInfo fails.
     * @throws IllegalArgumentException
     */
    String editUserProfile(String displayName, String mail, String info1, String info2) throws IllegalArgumentException;

    /**
     * Update password of UserInfo.<BR>
     * Returns empty message, if updating UserInfo success.
     * @param password
     * @param oldPassword
     * @return Returns Error message, if updating UserInfo fails.
     * @throws IllegalArgumentException
     */
    String editUserProfile(String password, String oldPassword) throws IllegalArgumentException;

    /**
     * Update userName of UserInfo.<BR>
     * Returns empty message, if updating UserInfo success.
     * @param userName
     * @return Returns Error message, if updating UserInfo fails.
     * @throws IllegalArgumentException
     */
    String editUserProfile(String userName) throws IllegalArgumentException;

    /**
     * Update extensions available to users.<BR>
     * Returns empty message, if updating UserInfo success.
     * @param extensionType
     * @return Returns Error message, if updating UserInfo fails.
     * @throws IllegalArgumentException
     */
    String editUserExtensionType(String extensionType) throws IllegalArgumentException;
}
