package com.zipc.garden.webplatform.client.view;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.zipc.garden.webplatform.client.core.screentransition.FrameBase;
import com.zipc.garden.webplatform.client.core.screentransition.FrameManager;
import com.zipc.garden.webplatform.client.core.screentransition.ViewDefine;
import com.zipc.garden.webplatform.client.service.PasswordResetService;
import com.zipc.garden.webplatform.client.service.PasswordResetServiceAsync;

/**
 * This class manages the screen for reissuing the password.
 */
public class PasswordForgetView extends FrameBase {

    /** Items displayed in the header */
    private HeaderItem headerItem;

    /** Enter field for the email address of the user who will reissue the password */
    private TextItem email;

    /** Button to reissue password */
    private SubmitItem resetBtn;

    /** Form to display various items */
    private DynamicForm form;

    /** Asynchronous interface for reissuing passwords */
    private PasswordResetServiceAsync passwordResetService;

    /**
     * constructor.
     */
    public PasswordForgetView() {
        super.setCheckSession(false);
    }

    /**
     * constructor
     * @param map GET parameter
     */
    public PasswordForgetView(Map<String, String> param) {
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
        form.setHeight(160);
        form.setCellPadding(10);
        form.setTitleOrientation(TitleOrientation.TOP);
        form.setNumCols(1);
        form.setBackgroundColor("#FFFFFF");
        form.setSnapTo("T");
        form.setSnapOffsetTop(20);

        headerItem = new HeaderItem();
        headerItem.setDefaultValue("Reset password");

        email = new TextItem("email", "Email");
        email.setRequired(true);
        email.setLength(255);
        email.setWidth("*");

        RegExpValidator emailValidator = new RegExpValidator();
        emailValidator.setErrorMessage("Invalid email address");
        emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");

        email.setValidators(emailValidator);

        resetBtn = new SubmitItem("resetBtn", "Reset password");
        resetBtn.setStartRow(false);
        resetBtn.setWidth("*");

        form.setFields(headerItem, email, resetBtn);

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
                String email = form.getValueAsString("email");
                passwordResetService.isEmailRegistered(email, new AsyncCallback<Boolean>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            // email exists
                            passwordResetService.resetPassword(email, new AsyncCallback<String>() {

                                @Override
                                public void onFailure(Throwable caught) {
                                    SC.warn(caught.getMessage());
                                }

                                @Override
                                public void onSuccess(String token) {
                                    passwordResetService.sendPasswordResetMail(email, token, new AsyncCallback<Boolean>() {

                                        @Override
                                        public void onFailure(Throwable caught) {
                                            SC.warn(caught.getMessage());
                                        }

                                        @Override
                                        public void onSuccess(Boolean result) {
                                            if (!result) {
                                                SC.warn("Password reset mail cannot be sent.\nPlease try again later.");
                                            } else {
                                                SC.say("An email with instructions about how to reset your password has been sent.");
                                            }
                                            FrameManager.getInstance().transitionTo(ViewDefine.LOGIN);
                                        }
                                    });
                                }
                            });
                        } else {
                            // email does not exist
                            SC.warn("The email " + email + " is not registered.");
                        }
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
