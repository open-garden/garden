package com.zipc.garden.webplatform.client.core.screentransition;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.zipc.garden.webplatform.client.service.LoginService;
import com.zipc.garden.webplatform.client.service.LoginServiceAsync;

/**
 * The class that will be the frame of the newly created View.
 * 
 * <pre>
 * (Implementation example)
 * FrameManager.getInstance().transitionTo(ViewDefine.TOP);
 * </pre>
 */
public final class FrameManager {

    /** The view to create */
    private FrameInterface currentFrame;

    /** Class for asynchronous call from client. Things related to login */
    private final LoginServiceAsync loginService = GWT.create(LoginService.class);

    /**
     * Support browser back and forward buttons.<BR>
     * note:This handler will be invoked even if the URL changes
     */
    private FrameManager() {
        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            public void onValueChange(ValueChangeEvent<String> event) {
                transitionTo(event.getValue());
            }
        });
    }

    /**
     * method to get the instance
     * @return This class
     */
    public static synchronized FrameManager getInstance() {
        return FrameManagerHolder.INSTANCE;
    }

    /**
     * class to hold the instance as final
     */
    private static class FrameManagerHolder {
        private static final FrameManager INSTANCE = new FrameManager();
    }

    /**
     * Create and display a view from history tokens.
     * @param historyToken
     */
    public void transitionTo(String historyToken) {
        String param = "";
        String token = URL.decode(historyToken);
        int question = token.indexOf("?");
        if (question != -1) {
            param = token.substring(question + 1);
            token = token.substring(0, question);
        }
        Map<String, String> paramMap = createParamMap(param);

        ViewDefine viewDefine = ViewDefine.LOGIN;
        if (ViewDefine.contains(token)) {
            viewDefine = ViewDefine.valueOf(token);
        }

        transitionTo(viewDefine, paramMap, false);
    }

    /**
     * Create and display a view from {@link ViewDefine}.
     * @param viewDefine {@link ViewDefine}
     */
    public void transitionTo(ViewDefine viewDefine) {
        transitionTo(viewDefine, Collections.emptyMap());
    }

    /**
     * Create and display a view from {@link ViewDefine} and url parameter.
     * @param viewDefine {@link ViewDefine}
     * @param param Map containing the type of View to be displayed
     */
    public void transitionTo(ViewDefine viewDefine, Map<String, String> param) {
        transitionTo(viewDefine, param, true);
    }

    /**
     * Create and display a view from {@link ViewDefine} and url parameter.
     * @param viewDefine {@link ViewDefine}
     * @param param Map containing the type of View to be displayed
     * @param isCreateHistory If true, support browser back and forward buttons
     */
    private void transitionTo(ViewDefine viewDefine, Map<String, String> param, boolean isCreateHistory) {
        FrameInterface frame = viewDefine.createInstance(param);
        if (currentFrame != null) {
            if (currentFrame.getLayout().isDrawn()) {
                currentFrame.exit();
                currentFrame.getLayout().markForDestroy();
            }
        }
        chackSession(frame, isCreateHistory);
    }

    /**
     * Check the session. transition to login, if session expired.
     * @param frame {@link ViewDefine}
     * @param isCreateHistory If true, support browser back and forward buttons
     */
    private final void chackSession(FrameInterface frame, boolean isCreateHistory) {
        loginService.checkLogin(new AsyncCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if (frame.isCheckSession() && !result) {
                    SC.warn("Session Expired", "Your session is expired.");
                    transitionTo(ViewDefine.LOGIN);
                } else {
                    if (isCreateHistory) {
                        String historyToken = createHistoryToken(frame);
                        History.newItem(historyToken, false);
                    }
                    currentFrame = frame;
                    frame.setLayout(frame.createLayout());
                    frame.entry();
                    Canvas[] members = frame.createCanvases();
                    frame.getLayout().addMembers(members);
                    frame.bind();
                    if (frame.getLayout().isDrawn()) {
                        frame.getLayout().redraw();
                    } else {
                        frame.getLayout().draw();
                    }
                    frame.initialized();
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }
        });
    }

    /**
     * Create parameter of map from url parameter.<BR>
     * eg) http://~~~~.html?key=value => map(key,value)
     * @param param May be {@code null}, in which case retrun the empty map.
     * @return paramMap Returns {@link Collections#emptyMap()} if @param is {@code null} or empty
     */
    private Map<String, String> createParamMap(String param) {
        if (param == null || param.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, String> paramMap = new HashMap<String, String>();
        String[] args = param.split("&");
        for (String element : args) {
            int equalIndex = element.indexOf("=");
            if (equalIndex == -1) {
                paramMap.put(element, "");
            } else {
                paramMap.put(element.substring(0, equalIndex), element.substring(equalIndex + 1));
            }
        }
        return paramMap;
    }

    /**
     * Create history token (url) from frame and parameter of map
     * @param frame {@link ViewDefine}
     * @return eg) String http://~~~~.html#LOGIN?key=value
     */
    private String createHistoryToken(FrameInterface frame) {
        StringBuilder token = new StringBuilder();
        token.append(ViewDefine.get(frame).name());
        Map<String, String> paramMap = frame.getParam();
        if (paramMap != null && !paramMap.isEmpty()) {
            token.append("?");
            StringBuilder param = new StringBuilder();
            paramMap.forEach((key, value) -> {
                if (param.length() != 0) {
                    param.append("&");
                }
                param.append(key).append("=").append(value);
            });
            token.append(param);
        }
        return token.toString();

    }

}
