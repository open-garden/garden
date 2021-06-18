package com.zipc.garden.webplatform.client.editor;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.webplatform.client.service.GenerateResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.shared.JobStatusInfo;

/**
 * Class that manages common processing of editor
 */
public class EditorUtil {

    /**
     * private constructor to block the instantiation from other class.
     */
    private EditorUtil() {
    }

    /**
     * method to get the instance
     * @return This class
     */
    public static EditorUtil getInstance() {
        return EditorUtilHolder.PROVIDER;
    }

    /**
     * class to hold the instance as final
     */
    private static class EditorUtilHolder {
        private static final EditorUtil PROVIDER = new EditorUtil();
    }

    /** Setting value of name tag of text item that displays JobStatus */
    private static final String GENERATION_STATUS_NAME = "GenerationStatus";

    /** Title of the text item that displays JobStatus */
    private static final String GENERATION_STATUS_TITLE = "Generation Status";

    /** Setting value of name tag of text item that displays JobInfomation */
    private static final String INFOMATION_NAME = "Infomation";

    /** Title of the text item that displays JobInformation */
    private static final String INFOMATION_TITLE = "Infomation";

    /**
     * Used in SPARQL editor.<br>
     * If there is a tab that matches the specified file ID, activate that tab and get the model.
     * @param tab Tab currently displayed
     * @param fileId Check if this file id exists in the tab
     * @return Model to display SPARQL search results
     */
    public SearchViewerInterface findAndSelectEditor(Tab tab, long fileId) {
        TabSet tabSet = tab.getTabSet();
        for (Tab t : tabSet.getTabs()) {
            if (fileId == t.getAttributeAsLong("ref")) {
                tabSet.selectTab(t);
                return (SearchViewerInterface) t.getAttributeAsObject(ModelingProjectView.EDITOR);
            }
        }
        return null;
    }

    /**
     * This is the process of creating a layout that displays the RDF generation status (job status).
     * @param fileId Job information of this file ID is acquired.
     * @param projectId ID of the project to which the file belongs
     * @param service Asynchronous interface to get Job information from the server
     * @return Layout created
     */
    public HLayout createGenerationStatusLayout(long fileId, long projectId, GenerateResourceServiceAsync service) {
        HLayout statusLayout = new HLayout();
        HLayout statusBorderLayout = new HLayout();
        statusBorderLayout.setWidth(110 + 220 + 150 + 150 + 110);
        statusBorderLayout.setBorder("1px solid #808080");
        statusLayout.setMembers(new LayoutSpacer(), statusBorderLayout, new LayoutSpacer());
        DynamicForm statusForm = new DynamicForm();
        statusForm.setWidth100();
        statusForm.setNumCols(5);
        statusForm.setMargin(10);
        statusForm.setColWidths("*", 150, 150, 220, "*");
        statusBorderLayout.setMembers(statusForm);

        TextItem genStatusText = new TextItem(GENERATION_STATUS_NAME, GENERATION_STATUS_TITLE);
        genStatusText.setWidth("*");
        genStatusText.setRequired(true);
        genStatusText.setCanEdit(false);

        ButtonItem updateBtn = new ButtonItem();
        updateBtn.setStartRow(false);
        updateBtn.setTitle("Update");
        updateBtn.setEndRow(true);

        TextItem infoText = new TextItem(INFOMATION_NAME, INFOMATION_TITLE);
        infoText.setStartRow(true);
        infoText.setRequired(true);
        infoText.setWidth("*");
        infoText.setColSpan(4);
        infoText.setCanEdit(false);

        statusForm.setItems(genStatusText, updateBtn, infoText);

        setJobStatus(fileId, projectId, service, genStatusText, infoText);

        updateBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                setJobStatus(fileId, projectId, service, genStatusText, infoText);
            }
        });

        return statusLayout;
    }

    /**
     * Get the text item from the layout and set the job status.
     * @param fileId Job information of this file ID is acquired.
     * @param projectId ID of the project to which the file belongs
     * @param service Asynchronous interface to get Job information from the server
     * @param statusLayout Layout with job information elements set
     */
    public void setGenerationStatus(long fileId, long projectId, GenerateResourceServiceAsync service, HLayout statusLayout) {
        TextItem genStatusText = null;
        TextItem infoText = null;
        DynamicForm form = (DynamicForm) Arrays.stream(statusLayout.getMembers()).filter(c -> c instanceof HLayout).flatMap(hl -> Arrays.stream(((HLayout) hl).getMembers()))
                .filter(df -> df instanceof DynamicForm).findFirst().get();
        genStatusText = (TextItem) form.getItem(GENERATION_STATUS_NAME);
        infoText = (TextItem) form.getItem(INFOMATION_NAME);
        if (genStatusText != null && infoText != null) {
            setJobStatus(fileId, projectId, service, genStatusText, infoText);
        }

    }

    /**
     * Method to get Job information from the database and set it in a text item.
     * @param fileId Job information of this file ID is acquired.
     * @param projectId ID of the project to which the file belongs
     * @param service Asynchronous interface to get Job information from the server
     * @param genStatusText A text item that displays the job status
     * @param infoText A text item that displays the contents of the job
     */
    private void setJobStatus(long fileId, long projectId, GenerateResourceServiceAsync service, TextItem genStatusText, TextItem infoText) {
        service.getSettingFileJobStatusList(fileId, projectId, new AsyncCallback<List<JobStatusInfo>>() {

            @Override
            public void onSuccess(List<JobStatusInfo> result) {
                if (!result.isEmpty()) {
                    genStatusText.setValue(result.get(0).getStatus());
                    infoText.setValue(result.get(0).getInfomation());
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }
        });
    }

    /**
     * Method to convert from binary to AbstractRootElement.
     * @param data The byte array to convert
     * @return model object after conversion
     */
    public AbstractRootElement convertToRootElement(byte[] data) {
        BinaryResourceImpl r = new BinaryResourceImpl();
        ByteArrayInputStream bi = new ByteArrayInputStream(data);
        try {
            r.load(bi, EditOptions.getDefaultLoadOptions());
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
        return (AbstractRootElement) r.getContents().get(0);
    }
}
