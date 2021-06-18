package com.zipc.garden.webplatform.client.view.part;

import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.EditorExitEvent;
import com.smartgwt.client.widgets.form.fields.events.EditorExitHandler;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.zipc.garden.webplatform.client.service.ProjectService;
import com.zipc.garden.webplatform.client.service.ProjectServiceAsync;
import com.zipc.garden.webplatform.shared.CharaCode;
import com.zipc.garden.webplatform.shared.ProjectInfo;

/**
 * This class is for creating a screen to edit the contents of the created project.<br>
 * The save process is performed by the servlet ({@link com.zipc.garden.webplatform.server.FileUpload FileUpload}).
 */
public class ProjectSettingViewPart extends ViewPartBase {

    /** Asynchronous interface for retrieving project information */
    private ProjectServiceAsync projectService;

    /** Items to display / edit the project name */
    private TextItem projectName;

    /** Items to display / edit the project description */
    private TextAreaItem descriptionItem;

    /** Items to display / edit the project encoding */
    private SelectItem encodingItem;

    /** Items to display the project image */
    private Image img;

    /** Project submission form */
    private FormPanel form;

    /** Used to select a local image file. */
    private FileUpload file;

    /** This button displays the [Project Image] selection dialog. */
    private IButton fileBtn;

    /** It is a button to clear [Project image] */
    private IButton clearBtn;

    /** Input item for the width of the project image */
    private IntegerItem imageWidth;

    /** Input item for the height of the project image */
    private IntegerItem imageHeight;

    /** Button to save project edits */
    private IButton saveBtn;

    /** A label that displays the name of the project image */
    private Label fileName;

    /** Form that holds items for height and width of the image */
    private DynamicForm imageForm;

    /** A variable that holds the ID of the project you are editing */
    private Long projectId;

    /** Parameters passed to the Servlet. (project id) */
    private Hidden idHidden;

    /** Parameters passed to the Servlet. (project name) */
    private Hidden nameHidden;

    /** Parameters passed to the Servlet. (description) */
    private Hidden descriptionHidden;

    /** Parameters passed to the Servlet. (encoding) */
    private Hidden encodingHidden;

    /** Parameters passed to the Servlet. (Image name) */
    private Hidden imageNameHidden;

    /** Parameters passed to the Servlet. (Image width) */
    private Hidden imageWidthHidden;

    /** Parameters passed to the Servlet. (Image height) */
    private Hidden imageHeightHidden;

    /** Parameters passed to the Servlet. (Remove image) */
    private Hidden clearHidden;

    /** True to clear the set image file. */
    private boolean clearJuge = false;

    /**
     * constructor
     * @param map GET parameter
     */
    public ProjectSettingViewPart(Map<String, String> map) {
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
        projectId = Long.valueOf(map.get("id"));
        projectService = GWT.create(ProjectService.class);
    }

    /**
     * {@inheritDoc}
     */
    public Canvas[] createCanvases() {
        getLayout().setWidth100();
        getLayout().setHeight100();

        DynamicForm settingForm = new DynamicForm();
        settingForm.setWidth("*");
        settingForm.setMinWidth(400);
        settingForm.setHeight(200);

        projectName = new TextItem("projectName", "ProjectName");
        projectName.setWidth(300);
        projectName.setHeight(20);

        descriptionItem = new TextAreaItem("description", "Description");
        descriptionItem.setWidth(300);
        descriptionItem.setHeight(100);

        encodingItem = new SelectItem("encodeType", "EncodeType");
        encodingItem.setWidth(300);
        Map<CharaCode, String> lineStyleValues = EnumSet.allOf(CharaCode.class).stream().collect(Collectors.toMap(x -> x, x -> x.getName()));
        encodingItem.setValueMap(lineStyleValues);

        Label frontLabel = new Label();
        frontLabel.setBackgroundColor("#5FB9F2");
        frontLabel.setHeight(10);
        frontLabel.setWidth100();

        HLayout banner = new HLayout();
        banner.addMember(frontLabel);
        banner.addMember(new LayoutSpacer(0, 30));

        VerticalPanel panel = new VerticalPanel();
        form = new FormPanel();
        file = new FileUpload();
        file.getElement().setPropertyString("accept", "image/*");
        file.setName("file");
        panel.add(file);

        idHidden = new Hidden("projectId");
        nameHidden = new Hidden("name");
        descriptionHidden = new Hidden("description");
        encodingHidden = new Hidden("encode");
        imageNameHidden = new Hidden("imageName");
        imageWidthHidden = new Hidden("imageWidth");
        imageHeightHidden = new Hidden("imageHeight");
        clearHidden = new Hidden("clear");

        panel.add(idHidden);
        panel.add(nameHidden);
        panel.add(descriptionHidden);
        panel.add(encodingHidden);
        panel.add(imageNameHidden);
        panel.add(imageWidthHidden);
        panel.add(imageHeightHidden);
        panel.add(clearHidden);

        form.setVisible(false);
        form.add(panel);
        form.setAction(GWT.getModuleBaseURL() + "file");
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_POST);

        Label fileLabel = new Label();
        fileLabel.setAlign(Alignment.RIGHT);
        fileLabel.setValign(VerticalAlignment.CENTER);
        fileLabel.setContents("<span style='color:#003168; font-size:11px;'>Image：</span> ");

        fileBtn = new IButton("Select");
        fileBtn.setWidth(100);
        fileBtn.setHeight(30);

        fileName = new Label();
        fileName.setWidth(100);
        fileName.setHeight(30);
        fileName.setAlign(Alignment.CENTER);

        clearBtn = new IButton("clear");
        clearBtn.setWidth(50);
        clearBtn.setHeight(30);
        clearBtn.setMargin(5);
        clearBtn.setAlign(Alignment.CENTER);

        IntegerRangeValidator maxValidator = new IntegerRangeValidator();
        maxValidator.setMax(9999);
        imageWidth = new IntegerItem("imageWidth", "Width(px)");
        imageWidth.setWidth(55);
        imageWidth.setHeight(20);
        imageWidth.setBrowserInputType("tel");
        imageWidth.setKeyPressFilter("[0-9]");
        imageWidth.setValidators(maxValidator);

        imageHeight = new IntegerItem("imageHeight", "Height(px)");
        imageHeight.setWidth(55);
        imageHeight.setHeight(20);
        imageHeight.setBrowserInputType("tel");
        imageHeight.setKeyPressFilter("[0-9]");
        imageHeight.setValidators(maxValidator);

        img = new Image();

        Label imageLabel = new Label();
        imageLabel.setAlign(Alignment.RIGHT);
        imageLabel.setValign(VerticalAlignment.TOP);
        imageLabel.setContents("<span style='color:#003168; font-size:11px;'>ProjectImage：</span> ");

        HLayout formLayout = new HLayout();
        formLayout.setHeight(30);
        formLayout.addMember(fileLabel);
        formLayout.addMember(fileBtn);
        formLayout.addMember(fileName);
        formLayout.addMember(clearBtn);
        formLayout.addMember(form);

        saveBtn = new IButton("SAVE");
        saveBtn.setWidth(100);
        saveBtn.setHeight(30);
        HLayout saveBtnLayout = new HLayout();
        saveBtnLayout.addMembers(new LayoutSpacer(descriptionItem.getWidth(), 0));
        saveBtnLayout.addMember(saveBtn);

        HLayout imgLayout = new HLayout();
        imgLayout.setWidth100();
        imgLayout.setHeight100();
        imgLayout.addMember(imageLabel);
        imgLayout.addMember(img);

        settingForm.setFields(new SpacerItem(), projectName, new SpacerItem(), descriptionItem, new SpacerItem(), encodingItem, new SpacerItem());

        imageForm = new DynamicForm();
        imageForm.setWidth("*");
        imageForm.setMinWidth(400);
        imageForm.setHeight(20);

        imageForm.setFields(imageWidth, imageHeight);

        return new Canvas[] { banner, saveBtnLayout, settingForm, new LayoutSpacer(100, 0), formLayout, imageForm, new LayoutSpacer(0, 30), imgLayout };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {

        file.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                JavaScriptObject obj = event.getNativeEvent().getEventTarget().cast();
                showPreview(obj, img.getElement().cast());
                fileName.setContents(getImageName());
                if (imageWidth.getValue() != null && imageHeight.getValue() != null) {
                    img.setPixelSize((int) imageWidth.getValue(), (int) imageHeight.getValue());
                }
                img.setPixelSize(150, 100);
                imageWidth.setValue(150);
                imageHeight.setValue(100);
            }
        });
        fileBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                file.click();
            }
        });
        clearBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                img.setPixelSize(0, 0);
                imageHeight.setValue(0);
                imageWidth.setValue(0);
                fileName.setContents("File is not selected");
                clearJuge = true;
            }
        });
        imageWidth.addEditorExitHandler(new EditorExitHandler() {
            @Override
            public void onEditorExit(EditorExitEvent event) {
                img.setPixelSize((int) imageWidth.getValue(), (int) imageHeight.getValue());
            }
        });
        imageHeight.addEditorExitHandler(new EditorExitHandler() {
            @Override
            public void onEditorExit(EditorExitEvent event) {
                img.setPixelSize((int) imageWidth.getValue(), (int) imageHeight.getValue());
            }
        });
        saveBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                form.submit();
            }
        });

        form.addSubmitHandler(new FormPanel.SubmitHandler() {
            @Override
            public void onSubmit(SubmitEvent event) {
                idHidden.setValue(projectId.toString());
                nameHidden.setValue(projectName.getValueAsString());
                descriptionHidden.setValue(descriptionItem.getValueAsString());
                encodingHidden.setValue(encodingItem.getValueAsString());
                imageNameHidden.setValue(getImageName());
                imageWidthHidden.setValue(imageWidth.getValueAsString());
                imageHeightHidden.setValue(imageHeight.getValueAsString());
                if (clearJuge) {
                    clearHidden.setValue("clear");
                }
                SC.say("Saving successfully.");
            }
        });
    }

    /**
     * Gets the file name of the selected image file.
     * @return file name
     */
    public String getImageName() {
        int slaIndex = file.getFilename().lastIndexOf("\\");
        String fileName = file.getFilename().substring(slaIndex + 1, file.getFilename().length());
        return fileName;
    }

    /**
     * JavaScript is called. The selected image file is displayed.
     * @param jsFile FileList object
     * @param image Display destination of the selected image file
     */
    private static native void showPreview(JavaScriptObject jsFile, JavaScriptObject image)
    /*-{
		var file = jsFile.files[0];
		var reader = new FileReader();
		reader.onload = function(e) {
			image.src = e.target.result;
		};
		reader.readAsDataURL(file);
    }-*/;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialized() {
        loadProjects();
    }

    /**
     * Get the data that matches {@link #projectId} from the project table and set it in various items.
     */
    public void loadProjects() {
        projectService.getProject(projectId, new AsyncCallback<ProjectInfo>() {
            @Override
            public void onSuccess(ProjectInfo result) {
                projectName.setValue(result.getName());
                descriptionItem.setValue(result.getDescription().replaceAll("<br />", System.getProperty("line.separator", "\r\n")));
                encodingItem.setValue(result.getEncodingType());
                if (result.getImageData() != null) {
                    img.setUrl(result.getImageData());
                    img.setPixelSize(result.getImageWidth(), result.getImageHeight());
                } else {
                    img.setPixelSize(0, 0);
                }
                if (result.getImageName() != null && !result.getImageName().isEmpty()) {
                    fileName.setContents(result.getImageName());
                    imageWidth.setValue(result.getImageWidth());
                    imageHeight.setValue(result.getImageHeight());
                } else {
                    fileName.setContents("File is not selected");
                    imageWidth.setValue(0);
                    imageHeight.setValue(0);
                }
                clearHidden.setValue(null);
                clearJuge = false;
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }
        });
    }
}
