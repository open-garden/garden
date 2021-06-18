package com.zipc.garden.webplatform.client.core.screentransition;

import java.util.Map;

import com.zipc.garden.webplatform.client.view.ChangeUserProfileView;
import com.zipc.garden.webplatform.client.view.LoginView;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.client.view.PasswordForgetView;
import com.zipc.garden.webplatform.client.view.PasswordResetView;
import com.zipc.garden.webplatform.client.view.ProjectView;
import com.zipc.garden.webplatform.client.view.SignupView;
import com.zipc.garden.webplatform.client.view.TopView;
import com.zipc.garden.webplatform.client.view.UserManagementView;
import com.zipc.garden.webplatform.client.view.WelcomeView;

/**
 * Need to add new element of view to this class, if you want to create a new view. <BR>
 * @see FrameBase
 */
public enum ViewDefine {

    USER(ChangeUserProfileView.class) {
        @Override
        public FrameBase createInstance(Map<String, String> param) {
            return new ChangeUserProfileView(param);
        }
    },
    LOGIN(LoginView.class) {
        @Override
        public FrameBase createInstance(Map<String, String> param) {
            return new LoginView();
        }
    },
    MODEL(ModelingProjectView.class) {
        @Override
        public FrameBase createInstance(Map<String, String> param) {
            return new ModelingProjectView(param);
        }
    },
    RESET(PasswordResetView.class) {
        @Override
        public FrameBase createInstance(Map<String, String> param) {
            return new PasswordResetView(param);
        }
    },
    FORGET(PasswordForgetView.class) {
        @Override
        public FrameBase createInstance(Map<String, String> param) {
            return new PasswordForgetView(param);
        }

    },
    PROJECT(ProjectView.class) {
        @Override
        public FrameBase createInstance(Map<String, String> param) {
            return new ProjectView(param);
        }
    },
    SIGNUP(SignupView.class) {
        @Override
        public FrameBase createInstance(Map<String, String> param) {
            return new SignupView();
        }
    },
    TOP(TopView.class) {
        @Override
        public FrameBase createInstance(Map<String, String> param) {
            return new TopView();
        }
    },
    USERS(UserManagementView.class) {
        @Override
        public FrameBase createInstance(Map<String, String> param) {
            return new UserManagementView(param);
        }
    },
    WELCOME(WelcomeView.class) {
        @Override
        public FrameBase createInstance(Map<String, String> param) {
            return new WelcomeView();
        }
    };

    /** A class that extends FramgeBase */
    private Class<? extends FrameBase> clazz;

    /**
     * constructor<br>
     * A class that extends FramgeBase is set.
     * @param clazz
     */
    private ViewDefine(Class<? extends FrameBase> clazz) {
        this.clazz = clazz;
    }

    /**
     * @param name String of element
     * @return Returns true if String of element exists this enum.
     */
    public static boolean contains(String name) {
        for (ViewDefine view : ViewDefine.values()) {
            if (view.name().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return Returns class that extends FramgeBase
     */
    public Class<? extends FrameBase> getViewClass() {
        return this.clazz;
    }

    /**
     * You need to implement a method that creates a new View instance.
     * @param param
     * @return FrameBase {@link FrameBase}
     */
    public abstract FrameBase createInstance(Map<String, String> param);

    /**
     * @param frame inherits FrameInterface
     * @return Returns the element that matches @param. <BR>
     *         <b>note: Returns null, if it doesn't matche @param.</b>
     */
    public static ViewDefine get(FrameInterface frame) {
        for (ViewDefine view : ViewDefine.values()) {
            if (view.clazz == frame.getClass()) {
                return view;
            }
        }
        return null;
    }

}
