package com.zipc.garden.webplatform.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.zipc.garden.webplatform.client.core.screentransition.FrameBase;

/**
 * This class manages the WELCOME screen that is displayed when you sign up by email authentication.
 */
public class WelcomeView extends FrameBase {

    /** When you press it, you will be taken to the login screen. */
    private Button okBtn;

    /** WELCOME screen layout */
    private Layout body;

    /**
     * constructor
     */
    public WelcomeView() {
        super.setCheckSession(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Canvas[] createCanvases() {
        getLayout().setWidth100();
        getLayout().setHeight100();
        getLayout().setBackgroundColor("#F2F2F2");

        body = new VLayout();
        body.setWidth100();
        body.setHeight100();
        body.setDefaultLayoutAlign(Alignment.CENTER);

        Label label1 = new Label("Welcome to ZIPC Garden!");
        label1.setAlign(Alignment.CENTER);
        label1.setBaseStyle("label_Label");

        Label label2 = new Label("Your account has been verified.");
        label2.setAlign(Alignment.CENTER);

        okBtn = new Button("OK");
        body.addMembers(label1, label2, okBtn);

        return new Canvas[] { body };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        okBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Window.Location.replace(GWT.getHostPageBaseURL() + "Zipc_Webplatform.html");
            }
        });
    }

}
