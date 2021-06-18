package com.zipc.garden.webplatform.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * Defines an interface derived from RemoteService.<br>
 * Lists all RPC methods needed to manage user login information.
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {

    /**
     * Check the UserInfo session.
     * @return {@code true} OK / {@code false} Session expired.
     * @throws IllegalArgumentException
     */
    boolean checkLogin() throws IllegalArgumentException;

    /**
     * Log in based on the argument "user, pass".
     * @param user User name entered on the login screen
     * @param pass Password entered on the login screen
     * @return {@code true} OK / {@code false} NG
     * @throws IllegalArgumentException
     */
    boolean login(String user, String pass) throws IllegalArgumentException;

    /**
     * Get information about the currently logged in user.
     * @return user info
     * @throws IllegalArgumentException
     */
    UserInfo getLoginUserInfo() throws IllegalArgumentException;

    /**
     * Log out the currently logged-in user.
     * @throws IllegalArgumentException
     */
    void logout() throws IllegalArgumentException;
}
