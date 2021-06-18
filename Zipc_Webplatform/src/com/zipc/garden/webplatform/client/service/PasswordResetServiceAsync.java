package com.zipc.garden.webplatform.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.zipc.garden.webplatform.server.service.PasswordResetServiceImpl;

/**
 * Asynchronous interface to send password reissue email to user when password is lost
 */
public interface PasswordResetServiceAsync {

    /**
     * {@link PasswordResetServiceImpl#isEmailRegistered(String)}
     */
    void isEmailRegistered(String email, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

    /**
     * {@link PasswordResetServiceImpl#resetPassword(String)}
     */
    void resetPassword(String email, AsyncCallback<String> callback) throws IllegalArgumentException;

    /**
     * {@link PasswordResetServiceImpl#sendPasswordResetMail(String, String)}
     */
    void sendPasswordResetMail(String email, String token, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

    /**
     * {@link PasswordResetServiceImpl#isValidToken(String)}
     */
    void isValidToken(String token, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

    /**
     * {@link PasswordResetServiceImpl#changePassword(String, String)}
     */
    void changePassword(String token, String newPassword, AsyncCallback<Boolean> callback) throws IllegalArgumentException;
}
