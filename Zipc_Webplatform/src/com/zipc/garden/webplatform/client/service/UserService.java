package com.zipc.garden.webplatform.client.service;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * Defines an interface derived from RemoteService.<br>
 * Lists all RPC methods required to create, save, delete, get user information, etc.
 */
@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {

    /**
     * Save UserInfro. Returns empty message, if saving UserInfo success.
     * @param userId
     * @param name
     * @param displayName
     * @param mail
     * @param password
     * @param role
     * @return Returns Error message, if saving userInfo fails.
     * @throws IllegalArgumentException
     */
    String saveUserInfo(Long userId, String name, String displayName, String mail, String password, int role) throws IllegalArgumentException;

    /**
     * Get all {@link UserInfo}
     * @return {@code List<}{@link UserInfo}{@code >}
     * @throws IllegalArgumentException
     */
    List<UserInfo> getUserInfoList() throws IllegalArgumentException;

    /**
     * Create UserInfro. Returns empty message, if saving UserInfo success.
     * @param name
     * @param displayName
     * @param mail
     * @param password
     * @param role
     * @param isVerifyStatus
     * @return Returns Error message, if creating UserInfo fails.
     * @throws IllegalArgumentException
     */
    String createUser(String name, String displayName, String mail, String password, int role, boolean isVerifyStatus) throws IllegalArgumentException;

    /**
     * Remove the UserInfo with the UsesrId
     * @param userIdList
     * @throws IllegalArgumentException
     */
    void removeUsers(List<Long> userIdList) throws IllegalArgumentException;

    /**
     * Get ProjectMap
     * @return Returns map that key is projectId and value is projectName
     * @throws IllegalArgumentException
     */
    Map<Long, String> getProjectMap() throws IllegalArgumentException;

    /**
     * Get UserInfo belonging to projectId{@code (Parameters)}
     * @param projectId
     * @return {@code List<}{@link UserInfo}{@code >}
     * @throws IllegalArgumentException
     */
    List<UserInfo> getProjectUserInfoList(Long projectId) throws IllegalArgumentException;

    /**
     * Get UserInfo that does not belonging to projectId{@code (Parameters)}
     * @param projectId
     * @return {@code List<}{@link UserInfo}{@code >}
     * @throws IllegalArgumentException
     */
    List<UserInfo> getProjectNotExistUserInfoList(Long projectId) throws IllegalArgumentException;

    /**
     * Update UserInfo belonging to the project
     * @param projectId
     * @param userInfoList
     * @throws IllegalArgumentException
     */
    void updatePorjectUsers(Long projectId, List<UserInfo> userInfoList) throws IllegalArgumentException;

    /**
     * A confirmation email regarding the sign up will be sent.
     * @param email
     * @param token
     * @param displayName
     * @return {@code true} OK / {@code false} NG
     * @throws IllegalArgumentException
     */
    boolean sendVerificationMail(String email, String token, String displayName) throws IllegalArgumentException;

    /**
     * The user information and the created token are linked to verify email.
     * @param email
     * @return token
     * @throws IllegalArgumentException
     */
    String createTokenUser(String email) throws IllegalArgumentException;

    /**
     * Processing to check whether the argument "token" is a valid value.
     * @param token
     * @return {@code true} Success / {@code false} Failure
     * @throws IllegalArgumentException
     */
    boolean isValidToken(String token) throws IllegalArgumentException;

    /**
     * Update user's verify status
     * @param token
     * @throws IllegalArgumentException
     */
    void updateUserVerifyStatus(String token) throws IllegalArgumentException;

    /**
     * Remove user when signup verification mail cannot be sent
     * @param mail
     * @throws IllegalArgumentException
     */
    void removeUser(String mail) throws IllegalArgumentException;
}
