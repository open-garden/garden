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
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.zipc.garden.webplatform.client.service.UserProfileService;
import com.zipc.garden.webplatform.client.service.UserProfileServiceAsync;

/**
 * This class creates a screen to change the password of the logged-in user.
 */
public class EditPasswordViewPart extends ViewPartBase {

    /** It is a form that displays input items. */
    private DynamicForm userForm;

    /** It is a button to apply the changed of the user password */
    private IButton saveBtn;

    /** Asynchronous interface to reflect the modified user password in the table */
    private UserProfileServiceAsync userProfileService;

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
        userForm.setHeight(130);
        userForm.setNumCols(1);
        userForm.setCellPadding(5);
        PasswordItem oldPassword = new PasswordItem("oldpassword", "Old Password");
        oldPassword.setRequired(true);
        oldPassword.setLength(20);
        PasswordItem password = new PasswordItem("password", "Password");
        password.setLength(20);
        PasswordItem confirmPassword = new PasswordItem("confirmPassword", "Confirm Password");
        confirmPassword.setLength(20);
        MatchesFieldValidator matchesValidator = new MatchesFieldValidator();
        matchesValidator.setOtherField("password");
        matchesValidator.setErrorMessage("Passwords do not match");
        confirmPassword.setValidators(matchesValidator);
        HLayout btns = new HLayout();
        btns.setAlign(Alignment.CENTER);
        btns.setHeight(30);
        btns.setMargin(20);
        saveBtn = new IButton("Save");
        btns.addMembers(new LayoutSpacer(270, 0), saveBtn);
        userForm.setItems(oldPassword, password, confirmPassword);

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

                String oldPassword = userForm.getValueAsString("oldpassword");
                String password = userForm.getValueAsString("password");

                userProfileService.editUserProfile(password, oldPassword, new AsyncCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        if ("".equals(result)) {
                            SC.say("User password changed successfully.");
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
