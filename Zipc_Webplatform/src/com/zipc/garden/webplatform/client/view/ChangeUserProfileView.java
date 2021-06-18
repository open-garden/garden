package com.zipc.garden.webplatform.client.view;

import java.util.HashMap;
import java.util.Map;

import com.smartgwt.client.types.Autofit;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.zipc.garden.webplatform.client.core.screentransition.FrameBase;
import com.zipc.garden.webplatform.client.core.screentransition.FrameManager;
import com.zipc.garden.webplatform.client.core.screentransition.ViewDefine;
import com.zipc.garden.webplatform.client.view.part.EditPasswordViewPart;
import com.zipc.garden.webplatform.client.view.part.EditProfileViewPart;
import com.zipc.garden.webplatform.client.view.part.EditUserNameViewPart;
import com.zipc.garden.webplatform.client.view.part.EditUserSettingViewPart;
import com.zipc.garden.webplatform.client.view.part.HeaderViewPart;
import com.zipc.garden.webplatform.client.view.part.ViewPartBase;

/**
 * This is a view class that edits the profile of the logged-in user.
 */
public class ChangeUserProfileView extends FrameBase {

    /** Profile edit menu constants */
    private static final String PROFILE = "Profile";

    /** User name edit menu constants */
    private static final String USERNAME = "User Name";

    /** Password edit menu constants */
    private static final String PASSWORD = "Password";

    /** A constant for the menu that edits the types of extensions that can be used */
    private static final String SETTING = "Setting";

    /** List grid displaying the edit menu */
    private ListGrid listGrid;

    /** A layout that displays the contents of the selected menu */
    private Layout rightLayout;

    /** Variable that holds the selected menu number */
    private int selectedRow = 0;

    /**
     * constructor
     * @param map GET parameter
     */
    public ChangeUserProfileView(Map<String, String> map) {
        super(map);
    }

    /**
     * {@inheritDoc} <br>
     * Create an VLayout.
     */
    @Override
    public Layout createLayout() {
        return new VLayout();
    }

    /**
     * {@inheritDoc} <br>
     * A layout is created according to the menu you select.
     */
    @Override
    public void entry() {
        Map<String, String> map = getParam();
        ViewPartBase frame;
        if (map == null || map.isEmpty()) {
            frame = new EditProfileViewPart();
            rightLayout = frame.getLayout();
            rightLayout.setMinWidth(300);
            return;
        }

        for (String key : map.keySet()) {
            switch (map.get(key)) {
            case PROFILE:
                frame = new EditProfileViewPart();
                break;
            case USERNAME:
                frame = new EditUserNameViewPart();
                break;
            case PASSWORD:
                frame = new EditPasswordViewPart();
                break;
            case SETTING:
                frame = new EditUserSettingViewPart();
                break;
            default:
                listGrid.selectRecord(0);
                frame = new EditProfileViewPart();
                break;
            }
            selectedRow = Integer.parseInt(key);
            rightLayout = frame.getLayout();
            rightLayout.setMinWidth(300);
            return;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Canvas[] createCanvases() {
        getLayout().setWidth100();
        getLayout().setHeight100();

        listGrid = new ListGrid();
        listGrid.setWidth("150");
        listGrid.setCanEdit(false);
        listGrid.setCanSort(false);
        listGrid.setShowHeader(false);
        listGrid.setShowAllRecords(true);
        listGrid.setSelectionType(SelectionStyle.SINGLE);
        listGrid.setAutoFitData(Autofit.BOTH);
        listGrid.setAlternateRecordStyles(false);
        listGrid.setAlternateFieldStyles(false);

        ListGridField indent = new ListGridField("indent", "", 20);
        ListGridField menuField = new ListGridField("menu", "");
        menuField.setType(ListGridFieldType.TEXT);

        listGrid.setFields(new ListGridField[] { indent, menuField });

        ListGridRecord profileMenu = new ListGridRecord();
        profileMenu.setAttribute("menu", PROFILE);
        ListGridRecord userNameMenu = new ListGridRecord();
        userNameMenu.setAttribute("menu", USERNAME);
        ListGridRecord passwordMenu = new ListGridRecord();
        passwordMenu.setAttribute("menu", PASSWORD);
        ListGridRecord settingMenu = new ListGridRecord();
        settingMenu.setAttribute("menu", SETTING);

        listGrid.setData(new ListGridRecord[] { profileMenu, userNameMenu, passwordMenu, settingMenu });

        listGrid.selectRecord(selectedRow);

        ViewPartBase header = new HeaderViewPart();

        HLayout body = new HLayout();
        body.setHeight100();

        body.addMembers(listGrid, rightLayout);

        return new Canvas[] { header.getLayout(), body };
    }

    /**
     * {@inheritDoc}
     */
    public void bind() {
        listGrid.addCellClickHandler(new CellClickHandler() {

            @Override
            public void onCellClick(CellClickEvent event) {
                Map<String, String> map = new HashMap<String, String>();
                map.put(String.valueOf(event.getRowNum()), listGrid.getSelectedRecord().getAttribute("menu"));
                FrameManager.getInstance().transitionTo(ViewDefine.USER, map);
            }
        });
    }
}
