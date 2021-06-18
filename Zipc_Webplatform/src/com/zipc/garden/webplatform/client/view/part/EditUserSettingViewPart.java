package com.zipc.garden.webplatform.client.view.part;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.zipc.garden.webplatform.client.core.screentransition.FrameManager;
import com.zipc.garden.webplatform.client.core.screentransition.ViewDefine;
import com.zipc.garden.webplatform.client.editor.ace.AceMode;
import com.zipc.garden.webplatform.client.service.LoginService;
import com.zipc.garden.webplatform.client.service.LoginServiceAsync;
import com.zipc.garden.webplatform.client.service.UserProfileService;
import com.zipc.garden.webplatform.client.service.UserProfileServiceAsync;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * This class creates a screen for registering a file extension that is opened in text format in Project Explorer.
 */
public class EditUserSettingViewPart extends ViewPartBase {

    /** It is a form that displays input items. */
    private DynamicForm extensionForm;

    /** It is an input item of the file extension */
    private TextItem extensionType;

    /** It is a button to apply the changed contents of the file extension */
    private IButton saveBtn;

    /** Asynchronous interface to reflect the modified file extension in the table */
    private UserProfileServiceAsync userProfileService;

    /** Asynchronous interface for getting logged-in user information */
    private LoginServiceAsync loginService;

    /**
     * <pre>
     * {@inheritDoc}
     *  
     * VLayout will be created and returned.
     * </pre>
     */
    @Override
    public Layout createLayout() {
        return new VLayout();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void entry() {
        userProfileService = GWT.create(UserProfileService.class);
        loginService = GWT.create(LoginService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Canvas[] createCanvases() {
        getLayout().setHeight100();
        getLayout().setWidth("*");

        extensionForm = new DynamicForm();
        extensionForm.setWidth(400);
        extensionForm.setHeight(20);
        extensionForm.setNumCols(1);

        extensionType = new TextItem("extensionType", "Extension Type");
        extensionType.setRequired(true);
        extensionType.setLength(255);
        extensionForm.setItems(extensionType);

        HLayout label = new HLayout();
        label.setAlign(Alignment.CENTER);
        label.setHeight(15);
        label.setWidth(250);
        Label caution = new Label();
        caution.setWidth100();
        caution.setContents("<span>※Please separate by [ ; ]</span>");
        label.addMembers(new LayoutSpacer(110, 0), caution);

        HLayout btns = new HLayout();
        btns.setAlign(Alignment.CENTER);
        btns.setMargin(20);
        btns.setHeight(30);
        saveBtn = new IButton("Save");
        btns.addMembers(new LayoutSpacer(275, 0), saveBtn);

        loginService.getLoginUserInfo(new AsyncCallback<UserInfo>() {

            @Override
            public void onSuccess(UserInfo result) {
                extensionType.setDefaultValue(result.getEditExtensions());
            }

            @Override
            public void onFailure(Throwable caught) {
                FrameManager.getInstance().transitionTo(ViewDefine.LOGIN);
            }
        });

        VLayout body = new VLayout();
        body.setHeight100();
        body.setWidth100();
        body.setDefaultLayoutAlign(Alignment.CENTER);
        body.addMembers(btns, extensionForm, label);
        return new Canvas[] { body };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        saveBtn.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (!extensionForm.validate()) {
                    return;
                }
                if (!(extensionType.getValueAsString().substring(extensionType.getValueAsString().length() - 1).equals(";"))) {
                    SC.say("Please enter [ ; ] at the end.");
                    return;
                }
                if (extensionType.getValueAsString().substring(0, 1).equals(";")) {
                    SC.say("Don't need [ ; ] at the first.");
                    return;
                } else {
                    String[] extension = extensionType.getValueAsString().split(";");
                    boolean juge = false;
                    for (int i = 0; i < extension.length; i++) {
                        for (AceMode mode : AceMode.values()) {
                            if (mode.getExtension().equals(extension[i])) {
                                juge = true;
                                break;
                            }
                        }
                        if (!juge) {
                            SC.say(extension[i] + " can not be set.");
                            return;
                        }
                        juge = false;
                    }
                    userProfileService.editUserExtensionType(extensionType.getValueAsString(), new AsyncCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if ("".equals(result)) {
                                SC.say("Extension Type changed successfully.");
                            } else {
                                SC.warn(result);
                            }
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }
                    });
                }

            }
        });

    }

}
