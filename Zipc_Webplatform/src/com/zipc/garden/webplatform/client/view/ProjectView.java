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
import com.zipc.garden.webplatform.client.view.part.HeaderViewPart;
import com.zipc.garden.webplatform.client.view.part.ProjectKeyWordViewPart;
import com.zipc.garden.webplatform.client.view.part.ProjectMemberViewPart;
import com.zipc.garden.webplatform.client.view.part.ProjectSettingViewPart;
import com.zipc.garden.webplatform.client.view.part.ViewPartBase;

/**
 * This class summarizes the processing of the project setting screen. <br>
 * You can use the menus for Edit Project, Set Permissions, Set Default Risk Importance.
 */
public class ProjectView extends FrameBase {

    /** Constants used for menus (authority settings) */
    private static final String MEMBERS = "Members";

    /** Constants used for menus (project edit) */
    private static final String SETTINGS = "Settings";

    /** Constants used for menus (risk importance setting) */
    private static final String KEY_WORDS = "KeyWords";

    /** The layout on the right side of the project setting screen. Switches according to the selected menu. */
    private static Layout rightLayout;

    /** Menu list on the project setting screen. */
    private ListGrid menuList;

    /** Name of the project to be edited */
    private String projectName;

    /** ID of the project to be edited */
    private Long projectId;

    /** Root directory ID of the project to be edited */
    private Long rootId;

    /** Selected menu number */
    private int selectRow = 0;

    /**
     * constructor
     * @param map GET parameter
     */
    public ProjectView(Map<String, String> map) {
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
            FrameManager.getInstance().transitionTo(ViewDefine.TOP);
            return;
        }
        String menu = map.get("menu") == null ? SETTINGS : map.get("menu");

        selectRow = map.get("row") == null ? 0 : Integer.parseInt(map.get("row"));
        projectName = map.get("name");
        projectId = Long.valueOf(map.get("id"));
        rootId = Long.valueOf(map.get("rootId"));

        switch (menu) {
        case SETTINGS:
            frame = new ProjectSettingViewPart(map);
            break;
        case MEMBERS:
            frame = new ProjectMemberViewPart(map);
            break;
        case KEY_WORDS:
            frame = new ProjectKeyWordViewPart(map);
            break;
        default:
            menuList.selectSingleRecord(0);
            frame = new ProjectMemberViewPart(map);
            break;
        }
        rightLayout = frame.getLayout();
        rightLayout.setMinWidth(300);
    }

    /**
     * constructor
     * @param projectName Name of the project to be edited
     * @param projectId ID of the project to be edited
     */
    public ProjectView(String projectName, Long projectId) {
        this.projectName = projectName;
        this.projectId = projectId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Canvas[] createCanvases() {

        getLayout().setWidth100();
        getLayout().setHeight100();

        menuList = new ListGrid();
        menuList.setWidth("150");
        menuList.setCanEdit(false);
        menuList.setCanSort(false);
        menuList.setShowHeader(false);
        menuList.setShowAllRecords(true);
        menuList.setSelectionType(SelectionStyle.SINGLE);
        menuList.setAutoFitData(Autofit.BOTH);
        menuList.setAlternateRecordStyles(false);
        menuList.setAlternateFieldStyles(false);

        ListGridField indent = new ListGridField("indent", "", 20);
        ListGridField menuField = new ListGridField("menu", "");
        menuField.setType(ListGridFieldType.TEXT);

        menuList.setFields(new ListGridField[] { indent, menuField });

        ListGridRecord editMenu = new ListGridRecord();
        editMenu.setAttribute("menu", SETTINGS);
        ListGridRecord editProjMenu = new ListGridRecord();
        editProjMenu.setAttribute("menu", MEMBERS);
        ListGridRecord editKeyWordMenu = new ListGridRecord();
        editKeyWordMenu.setAttribute("menu", KEY_WORDS);

        menuList.setData(new ListGridRecord[] { editMenu, editProjMenu, editKeyWordMenu });

        menuList.selectSingleRecord(selectRow);

        ViewPartBase header = new HeaderViewPart(getParam());

        HLayout body = new HLayout();
        body.setHeight100();
        body.addMembers(menuList, rightLayout);

        return new Canvas[] { header.getLayout(), body };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        menuList.addCellClickHandler(new CellClickHandler() {
            @Override
            public void onCellClick(CellClickEvent event) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", projectId.toString());
                map.put("name", projectName);
                map.put("menu", menuList.getSelectedRecord().getAttribute("menu"));
                map.put("row", String.valueOf(event.getRowNum()));
                map.put("rootId", String.valueOf(rootId));
                FrameManager.getInstance().transitionTo(ViewDefine.PROJECT, map);
            }
        });
    }
}
