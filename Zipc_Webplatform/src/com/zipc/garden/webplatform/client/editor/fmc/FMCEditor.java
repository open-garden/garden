package com.zipc.garden.webplatform.client.editor.fmc;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.fmc.FMCConstraint;
import com.zipc.garden.model.fmc.FMCFactory;
import com.zipc.garden.model.fmc.FMCRoot;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.ace.EditSession;
import com.zipc.garden.webplatform.client.editor.ace.JsObject;
import com.zipc.garden.webplatform.client.editor.ace.UndoManager;
import com.zipc.garden.webplatform.client.editor.xtext.XtextAce;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.part.DependentFileViewPart;
import com.zipc.garden.webplatform.shared.Extension;

/**
 * Class for editors that set feature-model-constraint.
 */
public class FMCEditor extends Editor {

    /** A variable that holds the contents of the constraint expression at the time of saving */
    protected String savedContents;

    /** Editor for running ace.js */
    private com.zipc.garden.webplatform.client.editor.ace.Editor editor;

    /** Manage command history */
    private final EditManager manager = EditManager.createInstance();

    /** Context menu for saving the FMC editor */
    private MenuItem saveItem = new MenuItem("Save");

    /** feature-model-constraint EMF root model */
    private FMCRoot root;

    /** FMC file id */
    private long fileId;

    /** The project ID in which this FMC is managed. */
    private long projectId;

    /** Asynchronous interface to get a list of FM files */
    private EditResourceServiceAsync editResourceService;

    /** Asynchronous interface used when reloading the selected FM file information */
    private final FMCResourceServiceAsync service = GWT.create(FMCResourceService.class);

    /** Overall layout of FMCEditor */
    private final Layout vLayout = new VLayout();

    /** DependentFileView */
    private DependentFileViewPart fileView;

    /** Set the URI of the web request to call */
    private String requestUrl;

    /**
     * constructor
     * @param root {@link FMCEditor#root}
     * @param fileId {@link FMCEditor#fileId}
     * @param projectId {@link FMCEditor#projectId}
     * @param editResourceService {@link FMCEditor#editResourceService}
     */
    public FMCEditor(FMCRoot root, long fileId, long projectId, EditResourceServiceAsync editResourceService) {
        this.root = root;
        this.fileId = fileId;
        this.projectId = projectId;
        this.editResourceService = editResourceService;
        setSavedContents();
    }

    /**
     * Format the constraint expression saved in the FMCRoot model in text format and set it in the
     * {@link FMCEditor#savedContents} variable.
     */
    private void setSavedContents() {
        StringBuilder builder = new StringBuilder();

        if (root != null) {
            if (root.getDocument() != null && !root.getDocument().isEmpty()) {
                builder.append(root.getDocument());
            } else {
                for (FMCConstraint c : root.getConstraints()) {
                    if (c.getComment() != null && !c.getComment().trim().isEmpty()) {
                        builder.append("// ");
                        builder.append(c.getComment().trim());
                        builder.append("\n");
                    }
                    if (c.getConstraint() != null && !c.getConstraint().trim().isEmpty()) {
                        if (!c.isEnabled()) {
                            builder.append("// ");
                        }
                        builder.append(c.getConstraint().trim());
                        builder.append("\n");
                    }
                    builder.append("\n");
                }
            }

        }

        this.savedContents = builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EditManager getEditManager() {
        return manager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuItem getSaveItem() {
        return saveItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractRootElement getRoot() {
        return root;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChanged() {
        return (manager.getSavedPosition() != manager.getStackCoount()) || (savedContents != null && !savedContents.equals(editor.getValue()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSavedPosition() {
        savedContents = editor.getValue();
        manager.setSavedPosition(manager.getStackCoount());
    }

    /**
     * Get FMCRoot model
     * @return {@link FMCEditor#root}
     */
    public FMCRoot getFMCRoot() {
        return root;
    }

    /**
     * Get Asynchronous interface
     * @return {@link FMCEditor#editResourceService}
     */
    protected EditResourceServiceAsync getEditResourceService() {
        return editResourceService;
    }

    /**
     * Get Asynchronous interface
     * @return {@link FMCEditor#service}
     */
    public FMCResourceServiceAsync getService() {
        return service;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Layout getLayout() {
        return vLayout;
    }

    /**
     * {@inheritDoc}
     */
    public void create() {
        createDslEditor();
        keyPressEvent();
    }

    /**
     * {@link DependentFileViewPart#setFileName(com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler)}
     */
    protected void refresh() {
        fileView.setFileName(null);
    }

    /**
     * A text input form using ace.js is created.
     */
    private void createDslEditor() {
        vLayout.setWidth100();
        vLayout.setHeight100();
        vLayout.draw();

        List<String> extensions = Arrays.asList(new String[] { Extension.FM.getValue() });
        fileView = new DependentFileViewPart("Dependent file", root, projectId, vLayout.getWidth(), vLayout.getHeight(), extensions, editResourceService);

        fileView.addSelectClickHandler(fileView.getSelect());

        fileView.addReloadClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                TextItem selectItem = fileView.getSelectItem();
                if (selectItem.getValueMap() == null || selectItem.getValueMap().isEmpty()) {
                    SC.say("Please Select Dependent File.");
                    return;
                }
                new FMCNodePathManager().reload(FMCEditor.this, selectItem.getValueMap(), projectId);
            }
        }, fileView.getReload());

        DynamicForm resultForm = new DynamicForm();
        resultForm.setMargin(2);
        resultForm.setWidth100();
        resultForm.setHeight100();
        resultForm.setBorder("1px solid Gray");
        resultForm.setNumCols(1);

        HTML html = new HTML();
        html.setWidth("100%");
        html.setHeight("100%");
        html.getElement().setId(fileId + "_textEditor");

        resultForm.addChild(html);

        requestUrl = Window.Location.getProtocol() + "//" + Window.Location.getHost() + "/com.zipc.garden.webplatform.dsl.fmc.web/xtext-service/generate-all";

        Scheduler.get().scheduleDeferred(() -> {
            vLayout.addMember(fileView.getDependentFileView());
            vLayout.addMember(resultForm);

            Scheduler.get().scheduleDeferred(() -> {
                JSONObject options = new JSONObject();
                options.put("parent", new JSONString(html.getElement().getId()));
                options.put("sendFullText", JSONBoolean.getInstance(true));
                options.put("loadFromServer", JSONBoolean.getInstance(false));
                options.put("resourceId", new JSONString(fileId + ".fmc"));
                options.put("syntaxDefinition", new JSONString("xtext/resource/fmc"));
                options.put("baseUrl", new JSONString("/com.zipc.garden.webplatform.dsl.fmc.web"));
                editor = XtextAce.createEditor(options.getJavaScriptObject());

                editor.setTheme("ace/theme/eclipse");
                editor.setFontSize(12);
                EditSession session = editor.getSession();
                session.setUseWrapMode(false);
                session.setTabSize(2);
                UndoManager undoManager = session.getUndoManager();
                editor.setValue(savedContents);
                undoManager.reset();
                session.on("change", (event) -> {
                    if (isChanged()) {
                        // Text 情報を FMCRoot の document に保存する
                        String document = editor.getValue();
                        root.setDocument(document);

                        // Text 情報をXtextのGenerator機能で制約式に解析して、FMCRoot の constraints に保存する
                        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, requestUrl);
                        requestBuilder.setHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");

                        StringBuilder requestData = new StringBuilder();
                        requestData.append("resource= +" + fileId + ".fmc" + "&");
                        requestData.append("fullText=" + URL.encodeQueryString(document));

                        try {
                            requestBuilder.sendRequest(requestData.toString(), new RequestCallback() {
                                @Override
                                public void onResponseReceived(Request request, Response response) {
                                    try {
                                        JSONObject jsonData = new JSONObject(JsonUtils.safeEval(response.getText()));
                                        JSONString textData = jsonData.get("artifacts").isArray().get(0).isObject().get("content").isString();
                                        String[] values = textData.stringValue().split("\n");
                                        root.getConstraints().clear();
                                        for (String val : values) {
                                            if (!val.trim().isEmpty()) {
                                                FMCConstraint fmcConst = FMCFactory.eINSTANCE.createFMCConstraint();
                                                fmcConst.setEnabled(true);
                                                fmcConst.setConstraint(val);
                                                fmcConst.setComment("");
                                                root.getConstraints().add(fmcConst);
                                            }
                                        }
                                    } catch (Exception ex) {
                                        root.getConstraints().clear();
                                    }
                                }

                                @Override
                                public void onError(Request request, Throwable exception) {
                                    root.getConstraints().clear();
                                }
                            });
                        } catch (RequestException caught) {
                            SC.warn(caught.getMessage());
                        }

                    }
                });

                /** This is like Native Javascript **/
                JsObject command = new JsObject();
                JsObject bindKey = new JsObject();
                bindKey.win = "Ctrl-S";
                bindKey.mac = "Cmd-S";
                command.name = "save";
                command.bindKey = bindKey;
                command.exec = (event) -> {
                    saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
                };
                /***********************************/

                editor.commands.addCommand(command);

            });
        });
    }

    /**
     * Create a layout key press event.
     */
    private void keyPressEvent() {
        vLayout.addKeyPressHandler(FMCEditorKeyEventHolder.createShortcutKeyEvent(FMCEditor.this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSaveHandler(ClickHandler handler) {
        saveItem.addClickHandler(handler);
    }
}
