package com.zipc.garden.webplatform.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.LoginServiceImpl;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * Asynchronous interface for managing user login information
 */
public interface LoginServiceAsync {

    /**
     * {@link LoginServiceImpl#checkLogin()}
     */
    void checkLogin(AsyncCallback<Boolean> callback) throws IllegalArgumentException;

    /**
     * {@link LoginServiceImpl#login(String, String)}
     */
    void login(String user, String pass, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

    /**
     * {@link LoginServiceImpl#getLoginUserInfo()}
     */
    void getLoginUserInfo(AsyncCallback<UserInfo> callback) throws IllegalArgumentException;

    /**
     * {@link LoginServiceImpl#logout()}
     */
    void logout(AsyncCallback<Void> callback) throws IllegalArgumentException;
}
