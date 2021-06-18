package com.zipc.garden.webplatform.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Defines an interface derived from RemoteService.<br>
 * List all RPC methods for password reissue.
 */
@RemoteServiceRelativePath("passwordReset")
public interface PasswordResetService extends RemoteService {

    /**
     * Check if the argument "email" is registered in the UserMaster table.
     * @param email Email address of the user for whom the password is reissued
     * @return {@code true} Registered / {@code false} Unregistered
     * @throws IllegalArgumentException
     */
    boolean isEmailRegistered(String email) throws IllegalArgumentException;

    /**
     * The user information and the created token are linked to change the password.
     * @param email Email address of the user for whom the password is reissued
     * @return token Token associated with user information
     * @throws IllegalArgumentException
     */
    String resetPassword(String email) throws IllegalArgumentException;

    /**
     * A confirmation email regarding the password change will be sent.
     * @param email Email address of the user for whom the password is reissued
     * @param token Token associated with user information
     * @return {@code true} OK / {@code false} NG
     * @throws IllegalArgumentException
     */
    boolean sendPasswordResetMail(String email, String token) throws IllegalArgumentException;

    /**
     * Processing to check whether the argument "token" is a valid value.
     * @param token Token associated with user information
     * @return {@code true} Success / {@code false} Failure
     * @throws IllegalArgumentException
     */
    boolean isValidToken(String token) throws IllegalArgumentException;

    /**
     * Create a new password for the user that matches the token.
     * @param token Token associated with user information
     * @param newPassword Newly issued password
     * @return {@code true} Success / {@code false} Failure
     * @throws IllegalArgumentException
     */
    boolean changePassword(String token, String newPassword) throws IllegalArgumentException;
}
