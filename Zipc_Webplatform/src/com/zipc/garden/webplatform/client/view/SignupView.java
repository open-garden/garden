package com.zipc.garden.webplatform.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.zipc.garden.webplatform.client.core.screentransition.FrameBase;
import com.zipc.garden.webplatform.client.service.UserService;
import com.zipc.garden.webplatform.client.service.UserServiceAsync;

/**
 * <pre>
 * This class is a class that summarizes the processing of the sign-up screen.
 * The user information entered on the sign-up screen will be temporarily registered, 
 * and will be fully registered when email authentication is successfully performed.
 * </pre>
 */
public class SignupView extends FrameBase {

    /**
     * When you press it, the entered information will be temporarily registered in the user table and an email for
     * authentication will be sent.
     */
    private ButtonItem okBtn;

    /** If you press it, the entered information will be cleared. */
    private ButtonItem cancelBtn;

    /** This is an input form for sign-up information. */
    private DynamicForm form;

    /** The screen display name of the user. */
    private TextItem displayName;

    /** User's email address */
    private TextItem mail;

    /** User's password */
    private PasswordItem password;

    /** Confirmation password */
    private PasswordItem confirmPassword;

    /** Asynchronous interface used for temporary user registration and sending authentication emails */
    private UserServiceAsync userService;

    /**
     * constructor
     */
    public SignupView() {
        super.setCheckSession(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void entry() {
        userService = GWT.create(UserService.class);
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
        form.setWidth(300);
        form.setColWidths(100, 100, 100);
        form.setNumCols(3);
        form.setBackgroundColor("#FFFFFF");
        form.setSnapTo("T");
        form.setSnapOffsetTop(20);

        HeaderItem headerItem = new HeaderItem();
        headerItem.setDefaultValue("Signup");
        headerItem.setColSpan(3);

        // TextItem
        displayName = new TextItem("displayName", "Display Name");
        displayName.setRequired(true);
        displayName.setWidth("*");
        displayName.setLength(255);
        displayName.setColSpan(2);
        // TextItem
        mail = new TextItem("mail", "Email");
        mail.setRequired(true);
        mail.setWidth("*");
        mail.setLength(255);
        mail.setColSpan(2);
        RegExpValidator emailValidator = new RegExpValidator();
        emailValidator.setErrorMessage("Invalid email address");
        emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");
        mail.setValidators(emailValidator);
        // PasswordItem
        password = new PasswordItem("password", "Password");
        password.setRequired(true);
        password.setLength(20);
        password.setWidth("*");
        password.setColSpan(2);
        // PasswordItem
        confirmPassword = new PasswordItem("confirmPassword", "Confirm Password");
        confirmPassword.setRequired(true);
        confirmPassword.setLength(20);
        confirmPassword.setWidth("*");
        confirmPassword.setColSpan(2);
        MatchesFieldValidator matchesValidator = new MatchesFieldValidator();
        matchesValidator.setOtherField("password");
        matchesValidator.setErrorMessage("Passwords do not match");
        confirmPassword.setValidators(matchesValidator);

        okBtn = new ButtonItem("OK");
        okBtn.setStartRow(false);
        okBtn.setEndRow(false);
        okBtn.setWidth("*");

        cancelBtn = new ButtonItem("Cancel");
        cancelBtn.setStartRow(false);
        cancelBtn.setEndRow(false);
        cancelBtn.setWidth("*");

        form.setItems(headerItem, displayName, mail, password, confirmPassword, new SpacerItem(), okBtn, cancelBtn);

        return new Canvas[] { form };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {

        form.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.ENTER.equals(event.getKeyName())) {
                    okBtn.fireEvent(new ClickEvent(form.getJsObj()));
                }
            }
        });

        okBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!form.validate()) {
                    return;
                }
                String displayName = form.getValueAsString("displayName");
                String mail = form.getValueAsString("mail");
                String password = form.getValueAsString("password");

                userService.createUser("", displayName, mail, password, 1, false, new AsyncCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if ("".equals(result)) {

                            userService.createTokenUser(mail, new AsyncCallback<String>() {

                                @Override
                                public void onFailure(Throwable caught) {
                                    SC.warn(caught.getMessage());
                                }

                                @Override
                                public void onSuccess(String token) {
                                    userService.sendVerificationMail(mail, token, displayName, new AsyncCallback<Boolean>() {

                                        @Override
                                        public void onFailure(Throwable caught) {
                                            SC.warn(caught.getMessage());
                                        }

                                        @Override
                                        public void onSuccess(Boolean result) {
                                            if (!result) {
                                                SC.warn("Verification mail cannot be sent.\nPlease try again later.");
                                                userService.removeUser(mail, new AsyncCallback<Void>() {

                                                    @Override
                                                    public void onFailure(Throwable caught) {
                                                    }

                                                    @Override
                                                    public void onSuccess(Void result) {
                                                    }
                                                });
                                            } else {
                                                clear();
                                                SC.say("To complete your signup, click the link in the confirmation email.");
                                            }
                                        }
                                    });
                                }
                            });
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
        cancelBtn.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                clear();
            }
        });
    }

    /**
     * Clear the input values ​​on the sign-up form.
     */
    private void clear() {
        form.setValue("displayName", "");
        form.setValue("mail", "");
        form.setValue("password", "");
        form.setValue("confirmPassword", "");
        form.focusInItem(displayName);
    }
}
