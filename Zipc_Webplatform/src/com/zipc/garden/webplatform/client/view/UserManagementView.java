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
import com.zipc.garden.webplatform.client.view.part.EditUserViewPart;
import com.zipc.garden.webplatform.client.view.part.HeaderViewPart;
import com.zipc.garden.webplatform.client.view.part.ProjectUsersManageViewPart;
import com.zipc.garden.webplatform.client.view.part.ViewPartBase;

/**
 * This class is a class that summarizes the processing of the management screen of the user registered in the application. <br>
 * You can use menus that allow you to edit users and set permissions.
 */
public class UserManagementView extends FrameBase {

    /** Constants used for menus (edit user) */
    private static final String EDIT = "Edit User";

    /** Constants used for menus (edit project user) */
    private static final String PROJECT = "Edit Project Users";

    /** Menu list on the user management screen. */
    private ListGrid listGrid;

    /** The layout on the right side of the user management screen. Switches according to the selected menu. */
    private Layout rightLayout;

    /** Selected menu number */
    private int selectedRow = 0;

    /**
     * constructor
     * @param map GET parameter
     */
    public UserManagementView(Map<String, String> map) {
        super(map);
    }

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
        Map<String, String> map = getParam();
        ViewPartBase frame;
        if (map == null || map.isEmpty()) {
            frame = new EditUserViewPart();
            rightLayout = frame.getLayout();
            rightLayout.setMinWidth(300);
            return;
        }

        for (String key : map.keySet()) {
            switch (map.get(key)) {
            case EDIT:
                frame = new EditUserViewPart();
                break;
            case PROJECT:
                frame = new ProjectUsersManageViewPart();
                break;
            default:
                listGrid.selectRecord(0);
                frame = new EditUserViewPart();
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

        ListGridRecord editMenu = new ListGridRecord();
        editMenu.setAttribute("menu", EDIT);
        ListGridRecord editProjMenu = new ListGridRecord();
        editProjMenu.setAttribute("menu", PROJECT);

        listGrid.setData(new ListGridRecord[] { editMenu, editProjMenu });

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
                FrameManager.getInstance().transitionTo(ViewDefine.USERS, map);
            }
        });
    }
}
