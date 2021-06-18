package com.zipc.garden.webplatform.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.zipc.garden.webplatform.client.core.screentransition.FrameBase;
import com.zipc.garden.webplatform.client.core.screentransition.FrameManager;
import com.zipc.garden.webplatform.client.core.screentransition.ViewDefine;
import com.zipc.garden.webplatform.client.service.LoginService;
import com.zipc.garden.webplatform.client.service.LoginServiceAsync;

/**
 * This class manages the functions of the login screen.
 */
public class LoginView extends FrameBase {

    /** Disclaimer (Japanese version). */
    private static final String DISCLAIMER_JA = "（保証）<br>"
            + "１　乙は、甲に対し、ソフトウェアの動作保証、使用目的への適合性の保証、使用結果についての的確性や信頼性の保証、第三者の著作権その他の権利の非侵害性の保証、他のソフトウェア又はハードウェアに対する悪影響の不発生、その他ソフトウェアに関して一切の保証をするものではありません。甲は、自己の責任と負担においてソフトウェアを使用するものとし、ソフトウェアの使用によって甲に生じた損害（直接的、間接的を問わず、契約不適合責任も含む）に対して、乙は一切の責任を負わないものとします。<br>"
            + "<br>"
            + "２　乙は、ソフトウェアの使用の結果、甲が第三者の著作権その他の権利を侵害し、当該第三者から甲に対してなされる権利侵害を直接又は間接の原因とする如何なる請求（甲と第三者との間の紛争を理由に、甲からなされる請求を含む）に関しても、一切の責任を負わないものとします。甲は、当該第三者との間の紛争の解決は、自己の費用と負担で行うものとし、乙は何らの責任を負わないものとします。";

    /** Disclaimer (English version). */
    private static final String DISCLAIMER_EN = "Disclaimer of Warranties and Limitation of Liability.<br>" + "<br>"
            + "a. UNLESS OTHERWISE SEPARATELY UNDERTAKEN BY THE LICENSOR, TO THE EXTENT POSSIBLE, THE LICENSOR OFFERS THE LICENSED MATERIAL AS-IS<br>"
            + "AND AS-AVAILABLE, AND MAKES NO REPRESENTATIONS OR WARRANTIES OF ANY KIND CONCERNING THE LICENSED MATERIAL, WHETHER EXPRESS,<br>"
            + "IMPLIED, STATUTORY, OR OTHER. THIS INCLUDES, WITHOUT LIMITATION, WARRANTIES OF TITLE, MERCHANTABILITY, FITNESS FOR A PARTICULAR<br>"
            + "PURPOSE, NON-INFRINGEMENT, ABSENCE OF LATENT OR OTHER DEFECTS, ACCURACY, OR THE PRESENCE OR ABSENCE OF ERRORS, WHETHER OR NOT<br>"
            + "KNOWN OR DISCOVERABLE. WHERE DISCLAIMERS OF WARRANTIES ARE NOT ALLOWED IN FULL OR IN PART, THIS DISCLAIMER MAY NOT APPLY TO YOU.<br>" + "<br>"
            + "b. TO THE EXTENT POSSIBLE, IN NO EVENT WILL THE LICENSOR BE LIABLE TO YOU ON ANY LEGAL THEORY (INCLUDING, WITHOUT LIMITATION,<br>"
            + "NEGLIGENCE) OR OTHERWISE FOR ANY DIRECT, SPECIAL, INDIRECT, INCIDENTAL, CONSEQUENTIAL, PUNITIVE, EXEMPLARY, OR OTHER LOSSES,<br>"
            + "COSTS, EXPENSES, OR DAMAGES ARISING OUT OF THIS PUBLIC LICENSE OR USE OF THE LICENSED MATERIAL, EVEN IF THE LICENSOR HAS BEEN<br>"
            + "ADVISED OF THE POSSIBILITY OF SUCH LOSSES, COSTS, EXPENSES, OR DAMAGES. WHERE A LIMITATION OF LIABILITY IS NOT ALLOWED IN FULL OR<br>"
            + "IN PART, THIS LIMITATION MAY NOT APPLY TO YOU. <br>" + "<br>" + "c. The disclaimer of warranties and limitation of liability provided<br>"
            + "above shall be interpreted in a manner that, to the extent possible, most closely approximates an absolute disclaimer and<br>" + "waiver of all liability.";

    /** Items displayed in the header */
    private HeaderItem headerItem;

    /** Input field of user name to log in */
    private TextItem username;

    /** Input field of user password to log in */
    private PasswordItem password;

    /** Login button */
    private SubmitItem loginBtn;

    /** Link to screen to reissue password */
    private LinkItem resetPasswordLink;

    /** Form to display various items */
    private DynamicForm form;

    /** Asynchronous interface for user login process */
    private LoginServiceAsync loginService;

    /**
     * constructor.
     */
    public LoginView() {
        super.setCheckSession(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void entry() {
        loginService = GWT.create(LoginService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Canvas[] createCanvases() {
        getLayout().setWidth100();
        getLayout().setHeight100();
        getLayout().setBackgroundColor("#F2F2F2");
        getLayout().setOverflow(Overflow.AUTO);

        VLayout loginLayout = new VLayout();
        loginLayout.setMinWidth(900);
        loginLayout.setMinHeight(760);
        loginLayout.setBackgroundColor("#FFFFFF");

        Label disclaimerJa = new Label(DISCLAIMER_JA);
        disclaimerJa.setMargin(20);
        Label disclaimerEn = new Label(DISCLAIMER_EN);
        disclaimerEn.setMargin(20);

        form = new DynamicForm();
        form.setWidth(500);
        form.setHeight(210);
        form.setCellPadding(10);
        form.setTitleOrientation(TitleOrientation.TOP);
        form.setNumCols(1);
        form.setBackgroundColor("#FFFFFF");

        resetPasswordLink = new LinkItem();
        resetPasswordLink.setShowTitle(false);
        resetPasswordLink.setLinkTitle("Forgot password?");

        headerItem = new HeaderItem();
        headerItem.setDefaultValue("Log in");

        username = new TextItem("username", "Username or Email");
        username.setRequired(true);
        username.setLength(255);
        username.setWidth("*");

        password = new PasswordItem("password", "Password");
        password.setRequired(true);
        password.setLength(20);
        password.setWidth("*");

        loginBtn = new SubmitItem("loginbtn", "Login");
        loginBtn.setStartRow(false);
        loginBtn.setWidth("*");

        form.setFields(headerItem, resetPasswordLink, username, password, loginBtn);

        LayoutSpacer spacerL1 = new LayoutSpacer();
        LayoutSpacer spacerR1 = new LayoutSpacer();
        HLayout formLayout = new HLayout();

        formLayout.setMembers(spacerL1, form, spacerR1);

        LayoutSpacer spacerTop = new LayoutSpacer();
        spacerTop.setHeight(20);
        LayoutSpacer spacerBottom = new LayoutSpacer();
        spacerBottom.setHeight(20);

        VLayout vLayout = new VLayout();
        vLayout.setHeight100();

        loginLayout.setMembers(formLayout, disclaimerJa, disclaimerEn, new LayoutSpacer());

        vLayout.setMembers(spacerTop, loginLayout, spacerBottom);

        LayoutSpacer spacerL2 = new LayoutSpacer();
        spacerL2.setMinWidth(20);
        LayoutSpacer spacerR2 = new LayoutSpacer();
        spacerR2.setMinWidth(20);
        return new Canvas[] { spacerL2, vLayout, spacerR2 };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        loginBtn.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (!form.validate()) {
                    return;
                }

                String userName = form.getValueAsString("username");
                String pass = form.getValueAsString("password");

                loginService.login(userName, pass, new AsyncCallback<Boolean>() {

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            FrameManager.getInstance().transitionTo(ViewDefine.TOP);
                        } else {
                            SC.warn("The login attempt failed.</br>Either the username or password is invalid.");
                        }
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
                    loginBtn.fireEvent(new ClickEvent(form.getJsObj()));
                }
            }
        });

        resetPasswordLink.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                FrameManager.getInstance().transitionTo(ViewDefine.FORGET);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialized() {
        super.initialized();
        username.focusInItem();
    }

}
