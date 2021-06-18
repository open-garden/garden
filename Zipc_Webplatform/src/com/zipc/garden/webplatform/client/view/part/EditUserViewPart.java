package com.zipc.garden.webplatform.client.view.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.AdvancedCriteria;
import com.smartgwt.client.data.Criterion;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.OperatorId;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.CellContextClickEvent;
import com.smartgwt.client.widgets.grid.events.CellContextClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.webplatform.client.service.LoginService;
import com.zipc.garden.webplatform.client.service.LoginServiceAsync;
import com.zipc.garden.webplatform.client.service.UserService;
import com.zipc.garden.webplatform.client.service.UserServiceAsync;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * This class is for managing screens where users can be added / edited / deleted.
 */
public class EditUserViewPart extends ViewPartBase {

    /** A map that holds the roles that the user can select. */
    private static final Map<Integer, String> ROLE_MAP;

    /** {@link #ROLE_MAP} is initialized. */
    static {
        Map<Integer, String> temp = new HashMap<Integer, String>();
        temp.put(0, "Manager");
        temp.put(1, "Common");
        ROLE_MAP = Collections.unmodifiableMap(temp);
    }

    /** Context menu when right-clicking {@link #userList} */
    private Menu menu;

    /**
     * Edit menu for the selected user. <br>
     * {@link #editProfileItem}, {@link #editNameItem}, {@link #editPassItem} are set.
     */
    private MenuItem editMenuItem;

    /** This is a menu for deleting the selected user from the table. */
    private MenuItem removeItem;

    /** A menu for editing the user's DisplayName, Email, and Role. */
    private MenuItem editProfileItem;

    /** This menu is for changing the name of the selected user. */
    private MenuItem editNameItem;

    /** This menu is for changing the password of the selected user. */
    private MenuItem editPassItem;

    /** This menu is for creating new users. */
    private MenuItem createItem;

    /** A TextItem used for fuzzy search of user information. */
    private TextItem searchItem;;

    /** ListGrid that displays the user information registered in the table. */
    private ListGrid userList;

    /** The data source for {@link #userList} */
    private UserDS uds;

    /** This screen is for creating users or editing user information. */
    private Window window;

    /** Asynchronous interface for adding / updating / deleting users */
    private UserServiceAsync userService;

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
    public Canvas[] createCanvases() {
        getLayout().setHeight100();
        getLayout().setWidth("*");

        menu = new Menu();
        createItem = new MenuItem("Create");
        removeItem = new MenuItem("Remove");
        editMenuItem = new MenuItem("Edit...");
        Menu editMenu = new Menu();
        editMenuItem.setSubmenu(editMenu);
        editProfileItem = new MenuItem("Profile");
        editNameItem = new MenuItem("User name");
        editPassItem = new MenuItem("Password");
        editMenu.setItems(editProfileItem, editNameItem, editPassItem);

        DynamicForm userSearchForm = new DynamicForm();
        userSearchForm.setNumCols(2);
        userSearchForm.setLayoutAlign(Alignment.RIGHT);

        searchItem = new TextItem("search", "Search");
        searchItem.setTitleAlign(Alignment.RIGHT);
        userSearchForm.setFields(searchItem);

        userList = new ListGrid();

        uds = UserDS.getInstance();

        userList.setDataSource(uds);
        userList.setAutoFetchData(true);
        userList.setCanEdit(false);
        userList.setCanSort(true);
        userList.setShowSortArrow(SortArrow.NONE);
        userList.setShowHeaderContextMenu(false);
        userList.setShowHeaderMenuButton(false);
        userList.setCanResizeFields(false);
        userList.setCanReorderFields(false);
        userList.setShowAllRecords(true);
        userList.setLeaveScrollbarGap(false);

        ListGridField userIdField = new ListGridField("userId");
        ListGridField userNameField = new ListGridField("userName");
        userList.setFields(userIdField, userNameField);

        HLayout formLayout = new HLayout();
        formLayout.setHeight("*");
        formLayout.setDefaultLayoutAlign(VerticalAlignment.CENTER);
        formLayout.addMembers(new LayoutSpacer(), userSearchForm);

        HLayout hlayout = new HLayout();
        hlayout.setHeight100();
        hlayout.addMembers(userList);

        return new Canvas[] { new LayoutSpacer("100%", 5), formLayout, new LayoutSpacer("100%", 5), hlayout };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {

        userList.addCellContextClickHandler(new CellContextClickHandler() {
            @Override
            public void onCellContextClick(CellContextClickEvent event) {
                event.cancel();
                if (userList == null || userList.getSelectedRecords().length <= 0) {
                    menu.setItems(createItem);
                } else if (userList.getSelectedRecords().length > 1) {
                    menu.setItems(createItem, removeItem);
                } else {
                    menu.setItems(createItem, editMenuItem, removeItem);
                }

                menu.moveTo(event.getX(), event.getY());
                menu.show();
            }
        });

        userList.addCellClickHandler(new CellClickHandler() {
            @Override
            public void onCellClick(CellClickEvent event) {
                userList.fireEvent(new SelectionEvent(userList.getJsObj()));
            }
        });

        createItem.addClickHandler(new ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                createUserWindow();
            }
        });

        editProfileItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editProfileWindow();
            }
        });

        editNameItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editNameWindow();
            }
        });

        editPassItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editPasswordWindow();
            }
        });

        removeItem.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                removeUser();
            }
        });

        searchItem.addKeyUpHandler(new KeyUpHandler() {

            @Override
            public void onKeyUp(KeyUpEvent event) {
                String key = event.getKeyName();
                if (key.equals(KeyNames.BACKSPACE) || key.equals(KeyNames.DEL)) {
                    searchItem.fireEvent(new ChangedEvent(searchItem.getJsObj()));
                }
            }
        });
        searchItem.addChangedHandler(new ChangedHandler() {

            @Override
            public void onChanged(ChangedEvent event) {
                String searchVal = event.getValue() != null ? event.getValue().toString() : "";
                AdvancedCriteria criteria = new AdvancedCriteria(OperatorId.OR,
                        new Criterion[] { new Criterion("userId", OperatorId.ICONTAINS, searchVal), new Criterion("userName", OperatorId.ICONTAINS, searchVal),
                                new Criterion("displayName", OperatorId.ICONTAINS, searchVal), new Criterion("mail", OperatorId.ICONTAINS, searchVal) });
                userList.filterData(criteria);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void entry() {
        userService = GWT.create(UserService.class);
        loginService = GWT.create(LoginService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialized() {
        loadDataSource();
    }

    /**
     * The user selected from the {@link #userList} is deleted.
     */
    private void removeUser() {
        List<Long> targetList = new ArrayList<Long>();
        for (ListGridRecord record : userList.getSelectedRecords()) {
            targetList.add(record.getAttributeAsLong("userId"));
        }
        String msg = "Are you sure you want to remove ";
        if (userList.getSelectedRecords().length > 1) {
            msg += " these " + userList.getSelectedRecords().length + " elements ?";
        } else {
            msg += "user '" + userList.getSelectedRecord().getAttribute("userName") + "'?";
        }
        SC.confirm("Remove", msg, new BooleanCallback() {
            @Override
            public void execute(Boolean value) {
                if (value != null && value) {
                    loginService.getLoginUserInfo(new AsyncCallback<UserInfo>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }

                        @Override
                        public void onSuccess(UserInfo result) {
                            for (Long target : targetList) {
                                if (target == result.getUserId()) {
                                    SC.warn("Could not remove users because targets has your userId.");
                                    return;
                                }
                            }
                            userService.removeUsers(targetList, new AsyncCallback<Void>() {
                                @Override
                                public void onFailure(Throwable caught) {
                                    SC.warn(caught.getMessage());
                                }

                                @Override
                                public void onSuccess(Void result) {
                                    loadDataSource();
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    /**
     * A screen for editing the displayName, Email, and Role of the user selected from the {@link #userList} is created.
     */
    private void editProfileWindow() {

        TextItem displayName = new TextItem("displayName", "Display Name");
        displayName.setRequired(true);
        displayName.setWidth("*");
        displayName.setColSpan(2);
        displayName.setLength(255);
        displayName.setValue(userList.getSelectedRecord().getAttributeAsString("displayName"));
        displayName.setSelectOnFocus(true);

        TextItem mail = new TextItem("mail", "Email");
        mail.setRequired(true);
        mail.setWidth("*");
        mail.setColSpan(2);
        mail.setLength(255);
        mail.setValue(userList.getSelectedRecord().getAttributeAsString("mail"));
        mail.setValidators(createEMailValidator());

        SelectItem role = new SelectItem("role", "Role");
        role.setRequired(true);
        role.setWidth("*");
        role.setValue(userList.getSelectedRecord().getAttributeAsInt("role"));
        role.setValueMap(ROLE_MAP);
        role.setColSpan(2);

        List<FormItem> formItems = new ArrayList<FormItem>();
        formItems.add(displayName);
        formItems.add(mail);
        formItems.add(role);
        createEditWindow("Edit user profile", formItems, false);

    }

    /**
     * A screen for editing the Name of the user selected from the {@link #userList} is created.
     */
    private void editNameWindow() {
        TextItem userName = new TextItem("username", "User Name");
        userName.setLength(20);
        userName.setRequired(true);
        userName.setWidth("*");
        userName.setColSpan(2);
        userName.setValue(userList.getSelectedRecord().getAttributeAsString("userName"));
        userName.setSelectOnFocus(true);

        List<FormItem> formItems = new ArrayList<FormItem>();
        formItems.add(userName);
        createEditWindow("Edit user name", formItems, false);
    }

    /**
     * A screen for changing the Password of the user selected from the {@link #userList} is created.
     */
    private void editPasswordWindow() {
        TextItem password = new PasswordItem("password", "Password");
        password.setLength(20);
        password.setWidth("*");
        password.setColSpan(2);
        password.setRequired(true);
        PasswordItem confirmPassword = new PasswordItem("confirmPassword", "Confirm Password");
        confirmPassword.setLength(20);
        confirmPassword.setWidth("*");
        confirmPassword.setColSpan(2);
        confirmPassword.setRequired(true);
        confirmPassword.setValidators(createPswdValidator());

        List<FormItem> formItems = new ArrayList<FormItem>();
        formItems.add(password);
        formItems.add(confirmPassword);
        createEditWindow("Edit user password", formItems, false);
    }

    /**
     * A window for creating a new user is displayed.
     */
    private void createUserWindow() {
        TextItem userName = new TextItem("username", "User Name");
        userName.setLength(20);
        userName.setRequired(true);
        userName.setWidth("*");
        userName.setColSpan(2);

        TextItem displayName = new TextItem("displayName", "Display Name");
        displayName.setLength(255);
        displayName.setRequired(true);
        displayName.setWidth("*");
        displayName.setColSpan(2);

        TextItem mail = new TextItem("mail", "Email");
        mail.setLength(255);
        mail.setRequired(true);
        mail.setWidth("*");
        mail.setColSpan(2);
        mail.setValidators(createEMailValidator());

        PasswordItem password = new PasswordItem("password", "Password");
        password.setRequired(true);
        password.setLength(20);
        password.setWidth("*");
        password.setColSpan(2);
        PasswordItem confirmPassword = new PasswordItem("confirmPassword", "Confirm Password");
        confirmPassword.setRequired(true);
        confirmPassword.setLength(20);
        confirmPassword.setWidth("*");
        confirmPassword.setColSpan(2);
        confirmPassword.setValidators(createPswdValidator());

        SelectItem role = new SelectItem("role", "Role");
        role.setRequired(true);
        role.setWidth("*");
        role.setValueMap(ROLE_MAP);
        role.setColSpan(2);

        List<FormItem> formItems = new ArrayList<FormItem>();
        formItems.add(userName);
        formItems.add(displayName);
        formItems.add(mail);
        formItems.add(password);
        formItems.add(confirmPassword);
        formItems.add(role);

        createEditWindow("Create User", formItems, true);
    }

    /**
     * Validator of password items. <br>
     * If the password you entered does not match the password you entered for confirmation, an error message will be displayed.
     * @return Validator of password items.
     */
    private MatchesFieldValidator createPswdValidator() {
        MatchesFieldValidator matchesValidator = new MatchesFieldValidator();
        matchesValidator.setOtherField("password");
        matchesValidator.setErrorMessage("Passwords do not match");
        return matchesValidator;
    }

    /**
     * Validator of Email Address. <br>
     * If the email address you entered is not in the correct format, an error message will be displayed.
     * @return Validator of Email Address.
     */
    private RegExpValidator createEMailValidator() {
        RegExpValidator emailValidator = new RegExpValidator();
        emailValidator.setErrorMessage("Invalid email address");
        emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");
        return emailValidator;
    }

    /**
     * A new user creation window or user information edit window is created.
     * @param title Window title
     * @param formItems Items displayed on the window form
     * @param isCreate If True, a new user will be created, and if False, user information will be updated.
     */
    private void createEditWindow(String title, List<FormItem> formItems, boolean isCreate) {
        window = new Window();
        window.setAutoSize(true);
        window.setTitle(title);
        window.setShowMinimizeButton(false);
        window.setIsModal(true);
        window.setShowModalMask(true);
        window.centerInPage();
        DynamicForm userForm = new DynamicForm();
        userForm.setWidth(300);
        userForm.setNumCols(3);
        userForm.setAlign(Alignment.CENTER);
        userForm.setAutoFocus(true);

        ButtonItem okBtn = new ButtonItem("okBtn", "O K");
        okBtn.setStartRow(false);
        okBtn.setEndRow(false);
        okBtn.setWidth("*");
        ButtonItem cancelBtn = new ButtonItem("cancelBtn", "Cancel");
        cancelBtn.setStartRow(false);
        cancelBtn.setWidth("*");
        setClickListener(userForm, okBtn, cancelBtn, isCreate);
        formItems.add(new SpacerItem());
        formItems.add(okBtn);
        formItems.add(cancelBtn);
        userForm.setItems(formItems.toArray(new FormItem[formItems.size()]));
        window.addItem(userForm);
        window.addCloseClickHandler(new CloseClickHandler() {
            @Override
            public void onCloseClick(CloseClickEvent event) {
                window.markForDestroy();
                loadDataSource();
            }
        });
        window.show();
    }

    /**
     * An event for the OK button and Cancel button of {@link #window} is created.
     * @param userForm Forms related to {@link #window}
     * @param okBtn OK button related to {@link #window}
     * @param cancelBtn Cancel button related to {@link #window}
     * @param isCreate If True, a new user will be created, and if False, user information will be updated.
     */
    private void setClickListener(DynamicForm userForm, ButtonItem okBtn, ButtonItem cancelBtn, boolean isCreate) {

        cancelBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                window.markForDestroy();
                loadDataSource();
            }
        });

        okBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                if (!userForm.validate()) {
                    return;
                }

                String userName = userForm.getValueAsString("username");
                String password = userForm.getValueAsString("password");
                String displayName = userForm.getValueAsString("displayName");
                String mail = userForm.getValueAsString("mail");
                String roleStr = userForm.getValueAsString("role");
                Long userId = Long.valueOf(userList.getSelectedRecord().getAttributeAsLong("userId"));
                int role = -1;
                if (roleStr != null) {
                    role = Integer.parseInt(roleStr);
                }

                if (isCreate) {
                    userService.createUser(userName, displayName, mail, password, role, true, new AsyncCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if ("".equals(result)) {
                                userForm.clearValues();
                                SC.say("User edit is successfully.");
                                window.fireEvent(new CloseClickEvent(window.getJsObj()));
                            } else {
                                SC.warn(result);
                            }
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }
                    });
                } else {
                    userService.saveUserInfo(userId, userName, displayName, mail, password, role, new AsyncCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if ("".equals(result)) {
                                userForm.clearValues();
                                SC.say("User edit is successfully.");
                                window.fireEvent(new CloseClickEvent(window.getJsObj()));
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
        userForm.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.ENTER.equals(event.getKeyName())) {
                    okBtn.fireEvent(new com.smartgwt.client.widgets.form.fields.events.ClickEvent(okBtn.getJsObj()));
                }
            }
        });
    }

    /**
     * A ListGrid record is created based on the specified user information.
     * @param userList Specified user information
     * @return ListGrid record created
     */
    private ListGridRecord[] getUserRecords(List<UserInfo> userList) {
        ArrayList<ListGridRecord> records = new ArrayList<ListGridRecord>();
        for (UserInfo userInfo : userList) {
            ListGridRecord record = new ListGridRecord();
            record.setAttribute("userId", userInfo.getUserId());
            record.setAttribute("userName", userInfo.getUserName());
            record.setAttribute("displayName", userInfo.getDisplayName());
            record.setAttribute("mail", userInfo.getMail());
            record.setAttribute("role", userInfo.getRole());
            records.add(record);
        }

        return records.toArray(new ListGridRecord[records.size()]);
    }

    /**
     * Data source class related to {@link #userList}
     */
    private static class UserDS extends DataSource {

        /** UserDS instance */
        private static UserDS instance = null;

        /**
         * You will get an instance of UserDS.<br>
         * If the instance has not been created, it will be created and retrieved.
         * @return UserDS instance
         */
        public static UserDS getInstance() {
            if (instance == null) {
                instance = new UserDS("userDS");
            }
            return instance;
        }

        /**
         * constructor. <br>
         * The data source field is set.
         * @param id Unique ID specified when creating the instance
         */
        public UserDS(String id) {
            setID(id);

            DataSourceTextField userIdField = new DataSourceTextField("userId", "User ID", 10);
            DataSourceTextField userNameField = new DataSourceTextField("userName", "User Name");
            DataSourceTextField displayNameField = new DataSourceTextField("displayName", "Display Name");
            DataSourceTextField mailField = new DataSourceTextField("mail", "Email");
            DataSourceIntegerField roleField = new DataSourceIntegerField("role");
            roleField.setHidden(true);
            setFields(userIdField, userNameField, displayNameField, mailField, roleField);

            setClientOnly(true);
        }
    }

    /**
     * User information is fetched from the table and displayed in {@link #userList}.
     */
    private void loadDataSource() {
        userService.getUserInfoList(new AsyncCallback<List<UserInfo>>() {
            @Override
            public void onSuccess(List<UserInfo> result) {
                uds.setTestData(getUserRecords(result));
                userList.setDataSource(uds);
                userList.sort();
                userList.fetchData();
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }
        });
    }
}
