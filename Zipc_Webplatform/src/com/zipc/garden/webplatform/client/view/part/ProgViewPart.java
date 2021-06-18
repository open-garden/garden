package com.zipc.garden.webplatform.client.view.part;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.zipc.garden.webplatform.client.service.ProgressCheckServiceAsync;

/** This class manages the screen that displays the progress of the job being executed. */
public class ProgViewPart {

    /** A screen that displays the progress of running jobs */
    private Window winModal;

    /** A list grid showing the jobs that are running */
    private ListGrid progTable = new ListGrid();

    /**
     * <pre>
     * The progress acquisition task is scheduled.
     * The progress of the job is acquired every 2 seconds.
     * It takes 7 to 60 ms when connected to the server.
     * </pre>
     */
    private Timer t;

    /**
     * A screen showing the progress of the running job is displayed.<br>
     * paint the Progress Table and refresh every 2 sec.
     * @param progressCheckService Asynchronous interface for getting job progress
     * @param projectId Specified project ID
     */
    public void showWindow(ProgressCheckServiceAsync progressCheckService, long projectId) {
        winModal.show();
        winModal.focusInNextTabElement();
        winModal.setCanFocus(true);
        winModal.focus();

        // Repeat every 2 seconds, cost 7ms - 60 ms when connected with server
        t = new Timer() {
            @Override
            public void run() {
                progressCheckService.getAllJobByProjId(projectId, new AsyncCallback<List<Map<String, String>>>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(List<Map<String, String>> result) {
                        generateGridData(result, progTable);

                    }
                });
            }
        };
        t.scheduleRepeating(2000);
    }

    /**
     * The screen showing the progress of the job is destroy.
     */
    public void destroy() {
        winModal.markForDestroy();
    }

    /**
     * The screen showing the progress of the job is initialized.
     * @param progressCheckService Asynchronous interface used to cancel a running job
     */
    public void openProgWindow(ProgressCheckServiceAsync progressCheckService) {
        // This Function intial the layout of the ProgTable Dialog
        // if new interactive way is need, add interactive componetn Here

        // Main Layout
        winModal = new Window();
        winModal.setTitle("Progress Table");
        winModal.setShowMaximizeButton(true);
        winModal.setIsModal(false);
        winModal.setKeepInParentRect(true);
        winModal.setAutoCenter(true);
        winModal.setBackgroundColor("white");
        winModal.setCanDragResize(true);
        winModal.setWidth(600);
        winModal.setHeight(400);

        VLayout formlayout = new VLayout();
        formlayout.setWidth100();
        formlayout.setHeight100();

        // Main List Shower
        progTable.setBackgroundColor("white");
        progTable.setWidth100();
        progTable.setHeight100();
        progTable.setShowHeaderContextMenu(false);
        progTable.setShowHeaderMenuButton(false);
        progTable.setCanResizeFields(false);
        progTable.setCanReorderFields(false);
        progTable.setLeaveScrollbarGap(false);
        progTable.setCanEdit(false);
        progTable.setSelectionType(SelectionStyle.MULTIPLE);
        progTable.setSelectionAppearance(SelectionAppearance.CHECKBOX);

        progTable.setMargin(5);
        // Build ListGrid, id Attr. is the hidden Key
        ListGridField namefield = new ListGridField("name", "Name");// fileName
        namefield.setType(ListGridFieldType.TEXT);
        ListGridField progfield = new ListGridField("progress", "Progress");// progMsg
        progfield.setType(ListGridFieldType.TEXT);
        ListGridField idfield = new ListGridField("id", "id");// JobId
        idfield.setType(ListGridFieldType.INTEGER);
        idfield.setHidden(true);

        // IButton cxlButton = new IButton("Cancel");

        progTable.setFields(idfield, namefield, progfield);
        progTable.setShowHiddenFields(false);

        // Cancel Button
        DynamicForm cancelForm = new DynamicForm();
        cancelForm.setNumCols(5);
        cancelForm.setBackgroundColor("white");
        cancelForm.setMargin(3);
        cancelForm.setColWidths(120, 120, 120, 120, 120);
        ButtonItem cancelButton = new ButtonItem("cancel");
        cancelButton.setStartRow(false);
        cancelButton.setEndRow(false);
        cancelForm.setFields(cancelButton);

        cancelButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                ListGridRecord[] cancelJobs = progTable.getSelectedRecords();
                List<String> cancelJobsIdList = new ArrayList<String>();
                for (ListGridRecord r : cancelJobs) {
                    cancelJobsIdList.add(r.getAttribute("id"));
                }
                progressCheckService.setCancelStatus(cancelJobsIdList, new AsyncCallback() {

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Object result) {
                        // Do nothing
                    }

                });
            }
        });

        formlayout.addMembers(progTable, cancelForm);
        winModal.addMember(formlayout);
        winModal.addCloseClickHandler(new CloseClickHandler() {

            @Override
            public void onCloseClick(CloseClickEvent event) {
                event.cancel();
                winModal.hide();
                t.cancel();
            }
        });
    }

    /**
     * Paint the Data in the progress ListGridList
     * @param jobsIdSet List of acquired jobs.<br>
     *            the first String Key correspondto the Long type JobId in the Job Table, the latter String store the Progress
     *            Message or Error Message that depend on theJob Status
     * @param progTable {@link #progTable}
     */
    private void generateGridData(List<Map<String, String>> jobsIdSet, ListGrid progTable) {
        ListGridRecord[] rlist = progTable.getRecords();
        Set<String> curKeySet = new HashSet<String>();
        HashMap<String, Integer> oldListItemMap = new HashMap<String, Integer>();
        // Build Old List Based on content already show on the progTable
        for (int i = 0; i < rlist.length; i++) {
            ListGridRecord tempRecord = rlist[i];
            oldListItemMap.put(tempRecord.getAttributeAsString("id"), i);
        }

        Iterator<Map<String, String>> jobItr = jobsIdSet.iterator();
        while (jobItr.hasNext()) {
            Map<String, String> jsonJob = jobItr.next();
            String tempId = jsonJob.get("id").toString();
            curKeySet.add(String.valueOf(tempId));
            // if job is alread show on progress Window, just refresh the old content
            if (oldListItemMap.containsKey(tempId)) {
                int itemIndex = oldListItemMap.get(tempId);
                ListGridRecord lgr = rlist[itemIndex];
                lgr.setAttribute("progress", jsonJob.get("progMsg").toString());
                progTable.refreshRow(itemIndex);

            } else {
                // new Job, not exist in the Job
                Record newRecord = new Record();
                newRecord.setAttribute("id", Integer.valueOf(jsonJob.get("id").toString()));
                newRecord.setAttribute("name", jsonJob.get("fileName").toString());
                String tempProgMsg = "";
                if (jsonJob.get("progMsg").isEmpty()) {
                    tempProgMsg = "null";
                } else {
                    tempProgMsg = jsonJob.get("progMsg");
                }
                newRecord.setAttribute("progress", tempProgMsg);
                progTable.addData(newRecord);
            }

        }
        List<Record> removeableRecordList = new ArrayList<Record>();
        for (String fileKey : oldListItemMap.keySet()) {
            if (!curKeySet.contains(fileKey)) {
                Record removeRecord = progTable.getRecord(oldListItemMap.get(fileKey));
                removeableRecordList.add(removeRecord);
            }
        }
        for (Record r : removeableRecordList) {
            progTable.removeData(r);
        }
    }
}
