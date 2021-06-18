package com.zipc.garden.webplatform.client.view.part;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.zipc.garden.webplatform.client.core.screentransition.FrameManager;
import com.zipc.garden.webplatform.client.core.screentransition.ViewDefine;
import com.zipc.garden.webplatform.client.service.LoginService;
import com.zipc.garden.webplatform.client.service.LoginServiceAsync;
import com.zipc.garden.webplatform.client.service.UserProfileService;
import com.zipc.garden.webplatform.client.service.UserProfileServiceAsync;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * This class creates a screen for changing the logged-in user's display name, email, company name, position.
 */
public class EditProfileViewPart extends ViewPartBase {

    /** It is a form that displays input items. */
    private DynamicForm userForm;

    /** It is a button to apply the changed contents of the user profile */
    private IButton saveBtn;

    /** Asynchronous interface to reflect the modified user profile in the table */
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

        userForm = new DynamicForm();
        userForm.setWidth(400);
        userForm.setHeight(150);
        userForm.setNumCols(1);
        userForm.setCellPadding(5);
        TextItem displayName = new TextItem("displayName", "Display Name");
        displayName.setLength(255);
        displayName.setRequired(true);
        TextItem mail = new TextItem("mail", "Email");
        mail.setRequired(true);
        mail.setLength(255);
        RegExpValidator emailValidator = new RegExpValidator();
        emailValidator.setErrorMessage("Invalid email address");
        emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");
        mail.setValidators(emailValidator);
        TextItem info1 = new TextItem("infoCompany", "Company");
        info1.setLength(255);
        TextItem info2 = new TextItem("infoPosition", "Position");
        info2.setLength(255);
        HLayout btns = new HLayout();
        btns.setAlign(Alignment.CENTER);
        btns.setHeight(30);
        btns.setMargin(20);
        saveBtn = new IButton("Save");
        btns.addMembers(new LayoutSpacer(270, 0), saveBtn);
        userForm.setItems(displayName, mail, info1, info2);
        loginService.getLoginUserInfo(new AsyncCallback<UserInfo>() {

            @Override
            public void onSuccess(UserInfo result) {
                displayName.setDefaultValue(result.getDisplayName());
                mail.setDefaultValue(result.getMail());
                info1.setDefaultValue(result.getInfo1());
                info2.setDefaultValue(result.getInfo2());
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
        body.addMembers(btns, userForm);
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
                if (!userForm.validate()) {
                    return;
                }
                String displayName = userForm.getValueAsString("displayName");
                String mail = userForm.getValueAsString("mail");
                String info1 = userForm.getValueAsString("infoCompany");
                String info2 = userForm.getValueAsString("infoPosition");

                userProfileService.editUserProfile(displayName, mail, info1, info2, new AsyncCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        if ("".equals(result)) {
                            SC.say("User profile changed successfully.");
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
        });
    }

}
