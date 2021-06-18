package com.zipc.garden.webplatform.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.zipc.garden.webplatform.client.core.screentransition.FrameManager;
import com.zipc.garden.webplatform.client.core.screentransition.ViewDefine;
import com.zipc.garden.webplatform.client.service.LoginService;
import com.zipc.garden.webplatform.client.service.LoginServiceAsync;
import com.zipc.garden.webplatform.client.service.PasswordResetService;
import com.zipc.garden.webplatform.client.service.PasswordResetServiceAsync;
import com.zipc.garden.webplatform.client.service.UserService;
import com.zipc.garden.webplatform.client.service.UserServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Zipc_Webplatform implements EntryPoint {

    /** Asynchronous interface for Login process */
    private final LoginServiceAsync loginService = GWT.create(LoginService.class);

    /** Asynchronous interface for password reset */
    private final PasswordResetServiceAsync passwordResetService = GWT.create(PasswordResetService.class);

    /** Asynchronous interface for users */
    private final UserServiceAsync userService = GWT.create(UserService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void onModuleLoad() {
        String historyToken = History.getToken();
        if (ViewDefine.SIGNUP.name().equals(historyToken)) {
            FrameManager.getInstance().transitionTo(historyToken);
            return;
        }
        loginService.checkLogin(new AsyncCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean resule) {
                if (resule) {
                    FrameManager.getInstance().transitionTo(historyToken);
                } else {
                    String token = Window.Location.getParameter("token");
                    if (token != null && !"".contentEquals(token.trim())) {
                        // check validity of token

                        boolean isSignupToken = Boolean.valueOf(Window.Location.getParameter("signup"));

                        if (isSignupToken) {
                            userService.isValidToken(token, new AsyncCallback<Boolean>() {

                                @Override
                                public void onFailure(Throwable caught) {
                                    SC.warn(caught.getMessage());
                                }

                                @Override
                                public void onSuccess(Boolean isValidToken) {
                                    if (isValidToken) {
                                        userService.updateUserVerifyStatus(token, new AsyncCallback<Void>() {

                                            @Override
                                            public void onFailure(Throwable caught) {
                                                SC.say(caught.getMessage());
                                            }

                                            @Override
                                            public void onSuccess(Void result) {
                                                FrameManager.getInstance().transitionTo(ViewDefine.WELCOME);
                                            }
                                        });

                                    } else {
                                        SC.say("Your link has expired.", new BooleanCallback() {
                                            @Override
                                            public void execute(Boolean value) {
                                                // go to login view
                                                Window.Location.replace(GWT.getHostPageBaseURL() + "Zipc_Webplatform.html");
                                            }
                                        });
                                    }
                                }
                            });
                        } else {
                            passwordResetService.isValidToken(token, new AsyncCallback<Boolean>() {

                                @Override
                                public void onFailure(Throwable caught) {
                                    SC.warn(caught.getMessage());
                                }

                                @Override
                                public void onSuccess(Boolean isValidToken) {
                                    if (isValidToken) {
                                        FrameManager.getInstance().transitionTo(ViewDefine.RESET);
                                    } else {
                                        SC.say("Your link has expired.", new BooleanCallback() {
                                            @Override
                                            public void execute(Boolean value) {
                                                // go to login view
                                                Window.Location.replace(GWT.getHostPageBaseURL() + "Zipc_Webplatform.html");
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    }
                    FrameManager.getInstance().transitionTo(ViewDefine.LOGIN);

                }
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }
        });
    }
}
