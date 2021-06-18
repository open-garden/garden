package com.zipc.garden.webplatform.client.view;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.zipc.garden.webplatform.client.core.screentransition.FrameBase;
import com.zipc.garden.webplatform.client.service.PasswordResetService;
import com.zipc.garden.webplatform.client.service.PasswordResetServiceAsync;

/**
 * This class creates a screen for resetting the password. <br>
 * If you access the link in the password reissue email, it will work.
 */
public class PasswordResetView extends FrameBase {

    /** Items displayed in the header */
    private HeaderItem headerItem;

    /** New password input items */
    private PasswordItem password;

    /** Item to re-enter the entered password */
    private PasswordItem confirmPassword;

    /** Button to reset password */
    private SubmitItem resetBtn;

    /** Asynchronous interface for resetting passwords */
    private PasswordResetServiceAsync passwordResetService;

    /** Form to display various items */
    private DynamicForm form;

    /**
     * constructor
     */
    public PasswordResetView() {
        super.setCheckSession(false);
    }

    /**
     * constructor
     * @param map GET parameter
     */
    public PasswordResetView(Map<String, String> param) {
        super(param);
        super.setCheckSession(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void entry() {
        passwordResetService = GWT.create(PasswordResetService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Canvas[] createCanvases() {
        getLayout().setWidth100();
        getLayout().setHeight100();
        getLayout().setBackgroundColor("#F2F2F2");

        form = new DynamicForm();
        form.setWidth(250);
        form.setHeight(210);
        form.setCellPadding(10);
        form.setTitleOrientation(TitleOrientation.TOP);
        form.setNumCols(1);
        form.setBackgroundColor("#FFFFFF");
        form.setSnapTo("T");
        form.setSnapOffsetTop(20);

        headerItem = new HeaderItem();
        headerItem.setDefaultValue("Reset password");

        password = new PasswordItem("password", "Password");
        password.setRequired(true);
        password.setLength(20);
        password.setWidth("*");

        confirmPassword = new PasswordItem("confirmPassword", "Retype password");
        confirmPassword.setRequired(true);
        confirmPassword.setLength(20);
        confirmPassword.setWidth("*");

        MatchesFieldValidator matchesValidator = new MatchesFieldValidator();
        matchesValidator.setOtherField("password");
        matchesValidator.setErrorMessage("Passwords do not match");
        confirmPassword.setValidators(matchesValidator);

        resetBtn = new SubmitItem("restBtn", "Reset");
        resetBtn.setStartRow(false);
        resetBtn.setWidth("*");

        form.setFields(headerItem, password, confirmPassword, resetBtn);

        return new Canvas[] { form };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        resetBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!form.validate()) {
                    return;
                }
                String passResetToken = Window.Location.getParameter("token");
                String newPassword = form.getValueAsString("password");
                passwordResetService.changePassword(passResetToken, newPassword, new AsyncCallback<Boolean>() {

                    @Override
                    public void onSuccess(Boolean result) {
                        SC.say("Password is changed successfully.", new BooleanCallback() {
                            @Override
                            public void execute(Boolean value) {
                                // go to login view
                                Window.Location.replace(GWT.getHostPageBaseURL() + "Zipc_Webplatform.html");
                            }
                        });
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }
                });
            }
        });

        form.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.ENTER.equals(event.getKeyName())) {
                    resetBtn.fireEvent(new ClickEvent(form.getJsObj()));
                }
            }
        });
    }

}
