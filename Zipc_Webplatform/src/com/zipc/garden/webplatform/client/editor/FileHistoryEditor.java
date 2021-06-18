package com.zipc.garden.webplatform.client.editor;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.RightMouseDownEvent;
import com.smartgwt.client.widgets.events.RightMouseDownHandler;
import com.smartgwt.client.widgets.events.ShowContextMenuEvent;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.zipc.garden.webplatform.client.service.FileHistoryService;
import com.zipc.garden.webplatform.client.service.FileHistoryServiceAsync;
import com.zipc.garden.webplatform.shared.FileHistoryInfo;

/**
 * Class that manages the change history of files
 */
public class FileHistoryEditor {

    /** ID of the file whose change history you want to check */
    private Long fileId;

    /** Screen layout that displays change history */
    private Layout layout = new Layout();

    /** List grid showing a list of change history */
    private ListGrid historyGrid = new ListGrid();

    /** Records added to the change history list grid */
    private List<ListGridRecord> recordList = new ArrayList<ListGridRecord>();

    /** Context menu assigned to each record in the list grid. The file reverts to the selected record version. */
    private Menu revertMenu = new Menu();

    /** Class that manages the tabs displayed on the screen */
    private TabSet editorTabSet;

    /** Tab of created file history screen */
    private Tab tab;

    /** Asynchronous interface for reflecting the contents operated on the file history screen in the database */
    private final FileHistoryServiceAsync infoService = GWT.create(FileHistoryService.class);

    /**
     * constructor
     * @param fileId ID of the file whose change history you want to check
     * @param editorTabSet Class that manages the tabs displayed on the screen
     * @param tab Tab of created file history screen
     */
    public FileHistoryEditor(Long fileId, TabSet editorTabSet, Tab tab) {
        this.fileId = fileId;
        this.editorTabSet = editorTabSet;
        this.tab = tab;
    }

    /**
     * Get change history screen layout
     * @return layout
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * This method creates the change history screen.
     */
    public void create() {

        layout.setWidth("100%");
        layout.setHeight100();

        historyGrid.setWidth("100%");
        historyGrid.setHeight100();
        historyGrid.setShowAllRecords(true);
        historyGrid.setSelectionType(SelectionStyle.SINGLE);

        // Able sorting
        historyGrid.setCanSort(true);

        ListGridField srnoField = new ListGridField("srno", "#", 40);
        srnoField.setAlign(Alignment.CENTER);

        ListGridField updTimeField = new ListGridField("updateTime", "Update Time");
        updTimeField.setAlign(Alignment.LEFT);
        updTimeField.setDateFormatter(DateDisplayFormat.TOSERIALIZEABLEDATE);
        ListGridField user = new ListGridField("user", "User");
        ListGridField fileName = new ListGridField("fileName", "File Name");
        ListGridField filePath = new ListGridField("path", "Path");
        ListGridField hash = new ListGridField("hash", "Hash");

        historyGrid.setFields(srnoField, updTimeField, user, fileName, filePath, hash);

        layout.addChild(historyGrid);

        getHistoryList();

        historyGrid.addRightMouseDownHandler(new RightMouseDownHandler() {

            @Override
            public void onRightMouseDown(RightMouseDownEvent event) {

                ListGridRecord selectedRecord = historyGrid.getSelectedRecord();
                int index = historyGrid.getRecordIndex(selectedRecord);
                if (index == 0) {
                    revertMenu.setDisabled(true);
                } else {
                    revertMenu.setDisabled(false);
                }

                setMenu();
                historyGrid.setContextMenu(revertMenu);
            }
        });

        historyGrid.addShowContextMenuHandler(new ShowContextMenuHandler() {

            @Override
            public void onShowContextMenu(ShowContextMenuEvent event) {
                setMenu();
                revertMenu.moveTo(event.getX(), event.getY());
                revertMenu.show();
            }
        });

        revertMenu.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {

                String historyTabId = tab.getID();
                String editorTabId = historyTabId.substring(0, historyTabId.lastIndexOf("_historyEditor"));

                // Prompt the user to close the editor if it is open.
                // Only proceed if the user closes the editor
                for (Tab tab : editorTabSet.getTabs()) {
                    if (editorTabId.equals(tab.getID())) {
                        SC.say("Please close the editor.");
                        return;
                    }
                }

                ListGridRecord currentRecord = historyGrid.getRecord(0);
                ListGridRecord[] records = historyGrid.getRecords();
                boolean isExistInHistory = false;
                for (int i = 1; i < records.length; i++) {
                    // Compare the current file's hash value with the history of all of this file's history,
                    if (currentRecord.getAttribute("hash").equals(records[i].getAttribute("hash"))) {
                        isExistInHistory = true;
                    }
                }

                // Add the current file information to the history if it is not the same
                // Get content (file history table) of selected history and save as current content (file table)
                ListGridRecord selectedRecord = historyGrid.getSelectedRecord();
                if (!isExistInHistory) {
                    save(selectedRecord);
                    update(selectedRecord);
                } else {
                    update(selectedRecord);
                }
            }
        });
    }

    /**
     * Create a menu to revert to the selected version.
     */
    private void setMenu() {
        MenuItem revertMenuItem = new MenuItem("Revert to this version");
        revertMenu.setData(revertMenuItem);
    }

    /**
     * Revert files to selected version
     * @param selectedRecord Selected version
     */
    private void update(ListGridRecord selectedRecord) {
        infoService.updateFileContent(fileId, selectedRecord.getAttribute("hash"), selectedRecord.getAttributeAsDate("updateTime"), new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(Void result) {
                SC.say("Reverting completed.");
                getHistoryList();
            }
        });
    }

    /**
     * The selected version of the file is saved in the file history table.
     * @param selectedRecord Selected version
     */
    private void save(ListGridRecord selectedRecord) {
        infoService.saveFileToHistory(fileId, selectedRecord.getAttribute("hash"), new AsyncCallback<Void>() {

            @Override
            public void onSuccess(Void result) {
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }
        });
    }

    /**
     * Acquires the change history of the file and displays it in ListGrid.
     */
    public void getHistoryList() {

        infoService.getFileHistoryInfo(fileId, new AsyncCallback<List<FileHistoryInfo>>() {

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(List<FileHistoryInfo> result) {
                recordList.clear();
                for (int i = 0; i < result.size(); i++) {
                    FileHistoryInfo r = result.get(i);
                    ListGridRecord record = new ListGridRecord();

                    if (i != 0) {
                        record.setAttribute("srno", i);
                    }
                    record.setAttribute("updateTime", r.getUpdateTime());
                    record.setAttribute("user", r.getUserName());
                    record.setAttribute("fileName", r.getFileName());
                    record.setAttribute("path", r.getFilePath());
                    record.setAttribute("hash", r.getHash());

                    recordList.add(record);
                }
                ListGridRecord[] records = new ListGridRecord[recordList.size()];
                records = recordList.toArray(records);

                historyGrid.setData(records);
                historyGrid.redraw();
            }
        });
    }
}
