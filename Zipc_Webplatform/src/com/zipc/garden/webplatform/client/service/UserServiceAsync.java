package com.zipc.garden.webplatform.client.service;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.UserServiceImpl;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * Asynchronous interface needed to access user table
 */
public interface UserServiceAsync {

    /**
     * {@link UserServiceImpl#saveUserInfo(Long, String, String, String, String, int)}
     */
    void saveUserInfo(Long userId, String name, String displayName, String mail, String password, int role, AsyncCallback<String> callback) throws IllegalArgumentException;

    /**
     * {@link UserServiceImpl#getUserInfoList()}
     */
    void getUserInfoList(AsyncCallback<List<UserInfo>> callback) throws IllegalArgumentException;

    /**
     * {@link UserServiceImpl#createUser(String, String, String, String, int, boolean)}
     */
    void createUser(String name, String displayName, String mail, String password, int role, boolean isVerifyStatus,
            AsyncCallback<String> callback) throws IllegalArgumentException;

    /**
     * {@link UserServiceImpl#removeUsers(List)}
     */
    void removeUsers(List<Long> userIdList, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link UserServiceImpl#getProjectMap()}
     */
    void getProjectMap(AsyncCallback<Map<Long, String>> callback) throws IllegalArgumentException;

    /**
     * {@link UserServiceImpl#getProjectUserInfoList(Long)}
     */
    void getProjectUserInfoList(Long projectId, AsyncCallback<List<UserInfo>> callback) throws IllegalArgumentException;

    /**
     * {@link UserServiceImpl#getProjectNotExistUserInfoList(Long)}
     */
    void getProjectNotExistUserInfoList(Long projectId, AsyncCallback<List<UserInfo>> callback) throws IllegalArgumentException;

    /**
     * {@link UserServiceImpl#updatePorjectUsers(Long, List)}
     */
    void updatePorjectUsers(Long projectId, List<UserInfo> userInfoList, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link UserServiceImpl#sendVerificationMail(String, String, String)}
     */
    void sendVerificationMail(String email, String token, String displayName, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

    /**
     * {@link UserServiceImpl#createTokenUser(String)}
     */
    void createTokenUser(String email, AsyncCallback<String> callback) throws IllegalArgumentException;

    /**
     * {@link UserServiceImpl#isValidToken(String)}
     */
    void isValidToken(String token, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

    /**
     * {@link UserServiceImpl#updateUserVerifyStatus(String)}
     */
    void updateUserVerifyStatus(String token, AsyncCallback<Void> callback) throws IllegalArgumentException;

    /**
     * {@link UserServiceImpl#removeUser(String)}
     */
    void removeUser(String mail, AsyncCallback<Void> callback) throws IllegalArgumentException;

}
