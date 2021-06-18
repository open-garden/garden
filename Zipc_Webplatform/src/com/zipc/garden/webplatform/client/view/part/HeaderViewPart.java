package com.zipc.garden.webplatform.client.view.part;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.menu.IconMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.zipc.garden.webplatform.client.core.screentransition.FrameManager;
import com.zipc.garden.webplatform.client.core.screentransition.ViewDefine;
import com.zipc.garden.webplatform.client.service.LoginService;
import com.zipc.garden.webplatform.client.service.LoginServiceAsync;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * It is a class that manages the items displayed in the header part of the entire application.
 */
public class HeaderViewPart extends ViewPartBase {

    /** Asynchronous interface used to get or log out a user */
    private LoginServiceAsync loginService;

    /** Tool strip to display in the header part */
    private ToolStrip hederMenuBar;

    /** Used for topic paths. If you press this, you will be taken to the TOP page. */
    private ToolStripButton topBtn;

    /** Used for topic paths. Pressing this will take you to the specified project page. */
    private ToolStripButton projectLabel;

    /** A label that shows the hierarchy of the current page */
    private Label label;

    /** Separator displayed in the topic path */
    private Label separator;

    /** Used in the margin of the header. */
    private LayoutSpacer layoutSpac;

    /** Only available to admin users. Press this to move to the user management screen. */
    private ToolStripButton userManageBtn;

    /** If you press this, you will be taken to the screen where you can edit the logged-in user information. */
    private IconMenuButton myPageBtn;

    /** This menu is set to {@link #myPageBtn}. */
    private Menu myPageMenu;

    /** This menu is set to {@link #myPageMenu}. */
    private MenuItem logout;

    /** If you press it, you will be logged out. */
    private IButton logoutBtn;

    /**
     * constructor
     */
    public HeaderViewPart() {
        super();
    }

    /**
     * constructor
     * @param map GET parameter
     */
    public HeaderViewPart(Map<String, String> param) {
        super(param);
    }

    /**
     * <pre>
     * {@inheritDoc}
     *  
     * HLayout will be created and returned.
     * </pre>
     */
    @Override
    public Layout createLayout() {
        return GWT.create(HLayout.class);
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

        hederMenuBar = new ToolStrip();

        topBtn = new ToolStripButton();
        topBtn.setTitle("Top");

        projectLabel = new ToolStripButton();

        separator = new Label("/");
        separator.setAutoWidth();

        layoutSpac = new LayoutSpacer();

        userManageBtn = new ToolStripButton();
        userManageBtn.setIcon("pieces/16/gear.png");

        myPageBtn = new IconMenuButton("MyPage");
        myPageBtn.setIcon("[SKIN]/headerIcons/person_Over.png");

        myPageMenu = new Menu();

        logout = new MenuItem("Logout", "pieces/16/logout.png");
        logoutBtn = new IButton("Logout");

        myPageMenu.setItems(logout);

        myPageBtn.setMenu(myPageMenu);

        if (getParam().containsKey("name") && getParam().containsKey("setting")) {
            projectLabel.setTitle(getParam().get("name"));
            label = new Label("/ " + getParam().get("setting"));
            label.setBaseStyle("toolStripButton");
            hederMenuBar.addMembers(topBtn, separator, projectLabel, label);

        } else if (getParam().containsKey("name")) {
            label = new Label("/ " + getParam().get("name"));
            label.setOverflow(Overflow.CLIP_H);
            label.setBaseStyle("toolStripButton");
            hederMenuBar.addMembers(topBtn, label);

        } else {
            hederMenuBar.addMembers(topBtn);
        }

        hederMenuBar.addMembers(layoutSpac, myPageBtn, logoutBtn);
        return new Canvas[] { hederMenuBar };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        topBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                FrameManager.getInstance().transitionTo(ViewDefine.TOP);
            }
        });

        projectLabel.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", getParam().get("rootId"));
                map.put("name", getParam().get("name"));
                map.put("projectId", getParam().get("id"));
                FrameManager.getInstance().transitionTo(ViewDefine.MODEL, map);
            }
        });

        userManageBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                FrameManager.getInstance().transitionTo(ViewDefine.USERS);
            }
        });

        myPageBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                FrameManager.getInstance().transitionTo(ViewDefine.USER);
            }

        });

        logout.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                SC.ask("Are you sure you want to log out ?", new BooleanCallback() {
                    @Override
                    public void execute(Boolean value) {
                        if (!value) {
                            return;
                        }
                        loginService.logout(new AsyncCallback<Void>() {

                            @Override
                            public void onSuccess(Void result) {
                                FrameManager.getInstance().transitionTo(ViewDefine.LOGIN);
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                SC.warn(caught.getMessage());
                            }
                        });
                    }
                });
            }
        });

        logoutBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                logout.fireEvent(new MenuItemClickEvent(event.getFiringCanvas().getJsObj()));
            }
        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialized() {
        loginService.getLoginUserInfo(new AsyncCallback<UserInfo>() {

            @Override
            public void onSuccess(UserInfo result) {
                int maypagePosition = hederMenuBar.getMemberNumber(myPageBtn);
                if (result.getRole() == 0) {
                    hederMenuBar.addMember(userManageBtn, maypagePosition);
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                FrameManager.getInstance().transitionTo(ViewDefine.LOGIN);
            }
        });
    }
}
