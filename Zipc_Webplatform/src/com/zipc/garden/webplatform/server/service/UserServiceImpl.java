package com.zipc.garden.webplatform.server.service;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.webplatform.client.service.UserService;
import com.zipc.garden.webplatform.server.logic.UserServiceLogic;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the UserService interface.
 */
@SuppressWarnings("serial")
public class UserServiceImpl extends RemoteServiceServlet implements UserService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveUserInfo(Long userId, String name, String displayName, String mail, String password, int role) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        Long companyId = userInfo.getCompanyId();
        String result = UserServiceLogic.saveUserInfo(userId, name, displayName, mail, password, role, companyId);
        if (result.equals("") && userId.equals(userInfo.getUserId())) {
            if (name != null && !name.isEmpty()) {
                userInfo.setUserName(name);
            }
            if (displayName != null && !displayName.isEmpty()) {
                userInfo.setDisplayName(displayName);
            }
            if (mail != null && !mail.isEmpty()) {
                userInfo.setMail(mail);
            }
            if (role != -1) {
                userInfo.setRole(role);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfo> getUserInfoList() throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        return UserServiceLogic.getUserInfoList(userInfo.getCompanyId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String createUser(String name, String displayName, String mail, String password, int role, boolean isVerifyStatus) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        Long companyId;
        if (userInfo != null) {
            companyId = userInfo.getCompanyId();
        } else {
            companyId = -1L;
        }
        return UserServiceLogic.createUser(name, displayName, mail, password, role, companyId, isVerifyStatus);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUsers(List<Long> userIdList) throws IllegalArgumentException {
        UserServiceLogic.removeUsers(userIdList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Long, String> getProjectMap() throws IllegalArgumentException {
        return UserServiceLogic.getProjectMap();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfo> getProjectUserInfoList(Long projectId) throws IllegalArgumentException {
        return UserServiceLogic.getProjectUserInfoList(projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfo> getProjectNotExistUserInfoList(Long projectId) throws IllegalArgumentException {
        return UserServiceLogic.getProjectNotExistUserInfoList(projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePorjectUsers(Long projectId, List<UserInfo> userInfoList) throws IllegalArgumentException {
        UserServiceLogic.updatePorjectUsers(projectId, userInfoList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendVerificationMail(String email, String token, String displayName) throws IllegalArgumentException {
        String serverName = getThreadLocalRequest().getServerName();
        int serverPort = getThreadLocalRequest().getServerPort();
        String dirPath = getServletContext().getRealPath("./");
        return UserServiceLogic.sendVerificationMail(email, token, displayName, serverName, serverPort, dirPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String createTokenUser(String email) throws IllegalArgumentException {
        return UserServiceLogic.createTokenUser(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidToken(String token) throws IllegalArgumentException {
        return UserServiceLogic.isValidToken(token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUserVerifyStatus(String token) throws IllegalArgumentException {
        UserServiceLogic.updateUserVerifyStatus(token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUser(String mail) throws IllegalArgumentException {
        UserServiceLogic.removeUser(mail);
    }
}
