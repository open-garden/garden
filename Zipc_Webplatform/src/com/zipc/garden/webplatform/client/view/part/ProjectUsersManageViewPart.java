package com.zipc.garden.webplatform.client.view.part;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.AdvancedCriteria;
import com.smartgwt.client.data.Criterion;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.OperatorId;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.TransferImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.VStack;
import com.zipc.garden.webplatform.client.service.UserService;
import com.zipc.garden.webplatform.client.service.UserServiceAsync;
import com.zipc.garden.webplatform.shared.UserInfo;

/**
 * It is a class to create a screen to edit the user usage authority of the created project.
 */
public class ProjectUsersManageViewPart extends ViewPartBase {

    /** Display destination of user information that cannot handle the specified project */
    private ListGrid userList;

    /** When pressed, the selected user will be given permission to use the project. */
    private TransferImgButton right;

    /** When pressed, the selected user's right to use the project is deprived. */
    private TransferImgButton left;

    /** Display destination of user information that can handle the specified project */
    private ListGrid projectUserList;

    /** TextItem used for fuzzy search of userList */
    private TextItem searchItem;

    /** Project selection form */
    private DynamicForm projectSearchForm;

    /** Project drop-down list */
    private SelectItem projectList;

    /** Asynchronous interface for retrieving user information */
    private UserServiceAsync userService;

    /** Data source for {@link #userList} */
    private UserDS udsl;

    /** Data source for {@link #projectUserList} */
    private UserDS udsr;

    /** Cancel the edited contents of the project usage authority. */
    private IButton cancelBtn;

    /** Apply the edited contents of the project usage authority. */
    private IButton saveBtn;

    /**
     * {@inheritDoc} <br>
     * Create an HLayout.
     */
    @Override
    public Layout createLayout() {
        return new HLayout();
    }

    /**
     * {@inheritDoc} <br>
     * Instantiate an asynchronous interface for getting user information.
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

        DynamicForm userSearchForm = new DynamicForm();
        userSearchForm.setIsGroup(true);
        userSearchForm.setGroupTitle("User Search");
        userSearchForm.setWidth("*");
        userSearchForm.setMinWidth(260);
        userSearchForm.setMinHeight(70);
        searchItem = new TextItem("search", "Search");
        userSearchForm.setFields(searchItem);

        userList = new ListGrid();
        userList.setShowAllRecords(true);
        userList.setLeaveScrollbarGap(false);
        userList.setCanReorderRecords(true);
        userList.setCanDragRecordsOut(true);
        userList.setCanAcceptDroppedRecords(true);
        userList.setDragDataAction(DragDataAction.MOVE);
        userList.setCanEdit(false);
        userList.setCanSort(true);
        userList.setShowSortArrow(SortArrow.NONE);
        userList.setShowHeaderContextMenu(false);
        userList.setShowHeaderMenuButton(false);
        userList.setCanResizeFields(false);
        userList.setCanReorderFields(false);
        userList.setWidth100();
        userList.setMinWidth(260);
        userList.setHeight("*");

        VStack transferStack = new VStack(3);
        transferStack.setWidth("20");
        transferStack.setAlign(VerticalAlignment.CENTER);

        right = new TransferImgButton(TransferImgButton.RIGHT);
        left = new TransferImgButton(TransferImgButton.LEFT);

        transferStack.addMembers(right, left);

        projectSearchForm = new DynamicForm();
        projectSearchForm.setWidth("*");
        projectSearchForm.setMinWidth(260);
        projectSearchForm.setNumCols(2);
        projectSearchForm.setAlign(Alignment.RIGHT);
        projectList = new SelectItem("projectList", "Project");
        projectList.setStartRow(false);
        projectList.setEndRow(false);
        projectList.setAlign(Alignment.RIGHT);

        saveBtn = new IButton("SAVE");
        saveBtn.setWidth(100);
        saveBtn.setHeight("*");
        saveBtn.setDisabled(true);
        cancelBtn = new IButton("CANCEL");
        cancelBtn.setWidth(100);
        cancelBtn.setHeight("*");
        cancelBtn.setDisabled(true);
        projectSearchForm.setItems(projectList);

        projectUserList = new ListGrid();
        projectUserList.setEmptyMessage("Drop Rows Here");
        projectUserList.setShowAllRecords(true);
        projectUserList.setLeaveScrollbarGap(false);
        projectUserList.setCanReorderRecords(true);
        projectUserList.setCanDragRecordsOut(true);
        projectUserList.setCanAcceptDroppedRecords(true);
        projectUserList.setDragDataAction(DragDataAction.MOVE);
        projectUserList.setCanEdit(false);
        projectUserList.setCanSort(true);
        projectUserList.setShowSortArrow(SortArrow.NONE);
        projectUserList.setShowHeaderContextMenu(false);
        projectUserList.setShowHeaderMenuButton(false);
        projectUserList.setCanResizeFields(false);
        projectUserList.setCanReorderFields(false);
        projectUserList.setWidth100();
        projectUserList.setHeight100();
        projectUserList.setMinWidth(260);

        VLayout userLayout = new VLayout();
        userLayout.setHeight100();
        userLayout.setWidth("*");
        userLayout.addMembers(userSearchForm, userList);

        HLayout btnLayout = new HLayout();
        btnLayout.setWidth100();
        btnLayout.addMembers(new LayoutSpacer(), saveBtn, new LayoutSpacer(10, 0), cancelBtn, new LayoutSpacer(10, 0));

        VLayout projectLayout = new VLayout();
        projectLayout.setHeight100();
        projectLayout.setWidth("*");
        projectLayout.addMembers(new LayoutSpacer(0, 20), projectSearchForm, btnLayout, projectUserList);
        return new Canvas[] { userLayout, transferStack, projectLayout };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        right.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                projectUserList.transferSelectedData(userList);
            }
        });
        left.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                userList.transferSelectedData(projectUserList);
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
                        new Criterion[] { new Criterion("displayName", OperatorId.ICONTAINS, searchVal), new Criterion("mail", OperatorId.ICONTAINS, searchVal) });
                userList.filterData(criteria);
            }
        });
        projectList.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent event) {
                Long projectId = Long.valueOf(event.getValue().toString());
                cancelBtn.setDisabled(false);
                saveBtn.setDisabled(false);
                projectList.setDisabled(true);
                cancelBtn.focus();
                userService.getProjectUserInfoList(projectId, new AsyncCallback<List<UserInfo>>() {
                    @Override
                    public void onSuccess(List<UserInfo> result) {
                        udsr.setTestData(getRecords(result));
                        projectUserList.setDataSource(udsr);
                        projectUserList.sort();
                        projectUserList.fetchData();
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }
                });
                userService.getProjectNotExistUserInfoList(projectId, new AsyncCallback<List<UserInfo>>() {

                    @Override
                    public void onSuccess(List<UserInfo> result) {
                        udsl.setTestData(getRecords(result));
                        userList.setDataSource(udsl);
                        userList.sort();
                        userList.fetchData();
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
                cancelBtn.setDisabled(true);
                saveBtn.setDisabled(true);
                projectList.setDisabled(false);
                projectList.setValue("");
                udsr.setTestData();
                projectUserList.setDataSource(udsr);
                projectUserList.fetchData();
                udsl.setTestData();
                userList.setDataSource(udsl);
                userList.fetchData();

            }
        });

        saveBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Long projectId = Long.valueOf(projectList.getValueAsString());

                userService.updatePorjectUsers(projectId, getUserInfoList(projectUserList.getRecordList()), new AsyncCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        SC.say("User updated successfully.");
                        cancelBtn.fireEvent(new ClickEvent(cancelBtn.getJsObj()));
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }
                });
            }
        });
    }

    /**
     * Record information is created and acquired based on the specified user information.
     * @param userList user information.
     * @return Record information
     */
    private ListGridRecord[] getRecords(List<UserInfo> userList) {
        ArrayList<ListGridRecord> records = new ArrayList<ListGridRecord>();
        for (UserInfo userInfo : userList) {
            ListGridRecord record = new ListGridRecord();
            record.setAttribute("userId", userInfo.getUserId());
            record.setAttribute("displayName", userInfo.getDisplayName());
            record.setAttribute("mail", userInfo.getMail());
            records.add(record);
        }

        return records.toArray(new ListGridRecord[records.size()]);
    }

    /**
     * User information is created and acquired based on the specified record list.
     * @param recordList specified record list
     * @return User information
     */
    private List<UserInfo> getUserInfoList(RecordList recordList) {
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        for (int i = 0; i < recordList.getLength(); i++) {
            Record record = recordList.get(i);
            UserInfo info = new UserInfo();
            info.setUserId(record.getAttributeAsLong("userId"));
            info.setDisplayName(record.getAttributeAsString("displayName"));
            info.setMail(record.getAttributeAsString("mail"));
            userInfoList.add(info);
        }
        return userInfoList;
    }

    /**
     * Data source associated with the ListGrid that displays user information
     */
    private static class UserDS extends DataSource {

        /**
         * Interface of data source related to ListGrid that displays user information that can operate the specified project
         */
        private static UserDS instanceR = null;

        /**
         * Interface of data source related to ListGrid that displays user information that cannot operate the specified project
         */
        private static UserDS instanceL = null;

        /**
         * Get an instance. If the instance has not been created, create it and get it.
         * @return {@link #instanceR}
         */
        public static UserDS getInstanceR() {
            if (instanceR == null) {
                instanceR = new UserDS("userDSR");
            }
            return instanceR;
        }

        /**
         * Get an instance. If the instance has not been created, create it and get it.
         * @return {@link #instanceL}
         */
        public static UserDS getInstanceL() {
            if (instanceL == null) {
                instanceL = new UserDS("userDSL");
            }
            return instanceL;
        }

        /**
         * constructor. <br>
         * The data source fields are set.
         * @param id Unique value specified when creating the instance
         */
        public UserDS(String id) {
            setID(id);

            DataSourceTextField dsfId = new DataSourceTextField("userId", "ID");
            dsfId.setHidden(true);
            dsfId.setPrimaryKey(true);
            DataSourceTextField dsfDisplayName = new DataSourceTextField("displayName", "DisplayName");
            DataSourceTextField dsfMail = new DataSourceTextField("mail", "Email");

            setFields(dsfId, dsfDisplayName, dsfMail);

            setClientOnly(true);
        }
    }

    /**
     * {@inheritDoc} <br>
     * ・All project information is retrieved. <br>
     * ・The data source is set for the ListGrid.
     */
    @Override
    public void initialized() {
        userService = GWT.create(UserService.class);
        userService.getProjectMap(new AsyncCallback<Map<Long, String>>() {
            @Override
            public void onSuccess(Map<Long, String> result) {
                projectList.setValueMap(result);
                projectList.setValue("");
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }
        });

        udsl = UserDS.getInstanceL();
        userList.setDataSource(udsl);
        udsr = UserDS.getInstanceR();
        projectUserList.setDataSource(udsr);
        cancelBtn.fireEvent(new ClickEvent(cancelBtn.getJsObj()));
    }

}
