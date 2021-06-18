package com.zipc.garden.webplatform.client.editor.csc;

import com.google.gwt.core.client.JavaScriptObject;
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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.types.ContentsType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.SplitPane;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.ace.EditSession;
import com.zipc.garden.webplatform.client.editor.ace.JsObject;
import com.zipc.garden.webplatform.client.editor.ace.TextEditor;
import com.zipc.garden.webplatform.client.editor.xtext.XtextAce;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * Editor to create 3D Viewer based on JSON string
 */
public class CSCEditor extends TextEditor {

    /** Editor element to input JSON format text */
    private HTML editorElement;

    /** Canvas displaying 3D View */
    private HTMLPane htmlPane;

    /** Set the URI of the web request to call */
    private static final String requestUrl = Window.Location.getProtocol() + "//" + Window.Location.getHost()
            + "/com.zipc.garden.webplatform.dsl.sc.web/xtext-service/generate-all";

    /**
     * constructor
     * @param tab csc editor tabs
     * @param file Information of CSC file to be displayed
     * @param service Asynchronous interface to get the setting information of SCSEditor
     */
    public CSCEditor(Tab tab, VMFile file, EditResourceServiceAsync service) {
        super(tab, file, service);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create() {
        saveItem = new MenuItem("save");
        Menu menu = new Menu();
        menu.addItem(saveItem);
        layout = new Layout();
        layout.setWidth100();
        layout.setHeight100();
        layout.setContextMenu(menu);

        // Main Pane
        final SplitPane splitPane = new SplitPane();
        splitPane.setShowLeftButton(false);
        splitPane.setShowRightButton(false);
        splitPane.setBorder("1px solid Gray");
        splitPane.setWidth100();
        splitPane.setHeight100();
        splitPane.setShowDetailToolStrip(false);
        splitPane.setShowListToolStrip(false);
        splitPane.setShowMiniNav(false);
        splitPane.setShowNavigationBar(false);
        splitPane.setNavigationPaneWidth("40%");

        // DSL Editor Pane
        Layout editorLayout = new Layout();
        editorLayout.setWidth100();
        editorLayout.setHeight100();
        editorElement = new HTML();
        editorElement.setWidth("100%");
        editorElement.setHeight("100%");
        editorElement.getElement().setId(tab.getID() + "textEditor");
        editorLayout.addMember(editorElement);

        // 3D Viewer Pane
        Layout viewerLayout = new Layout();
        viewerLayout.setWidth100();
        viewerLayout.setHeight100();
        htmlPane = new HTMLPane();
        htmlPane.setWidth100();
        htmlPane.setHeight100();
        htmlPane.setShowEdges(false);
        htmlPane.setContentsType(ContentsType.PAGE);
        htmlPane.setID(tab.getID() + "_scenario_data");
        htmlPane.setContentsURL("zipc_webplatform/js/threejs/webgl_geometry_extrude_road.html");
        viewerLayout.addMember(htmlPane);
        // style='border:none'/>");

        // Main Pane Config
        splitPane.setNavigationPane(editorLayout);
        splitPane.setDetailPane(viewerLayout);
        layout.addMember(splitPane);

        scheduleDeferred(editorElement.getElement().getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void scheduleDeferred(String parentId) {
        Scheduler.get().scheduleDeferred(() -> {
            service.getTextFileContent(file.getId(), new AsyncCallback<String>() {

                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }

                @Override
                public void onSuccess(String result) {
                    JSONObject options = new JSONObject();
                    options.put("parent", new JSONString(parentId));
                    options.put("sendFullText", JSONBoolean.getInstance(true));
                    options.put("loadFromServer", JSONBoolean.getInstance(false));
                    options.put("resourceId", new JSONString(file.getId() + ".sc"));
                    options.put("syntaxDefinition", new JSONString("xtext/resource/sc"));
                    options.put("baseUrl", new JSONString("/com.zipc.garden.webplatform.dsl.sc.web"));
                    editor = XtextAce.createEditor(options.getJavaScriptObject());

                    editor.setTheme("ace/theme/eclipse");
                    editor.setFontSize(14);
                    EditSession session = editor.getSession();
                    session.setUseWrapMode(false);
                    session.setTabSize(2);
                    manager = session.getUndoManager();
                    editor.setValue(result);
                    manager.reset();
                    savedContents = editor.getValue();
                    session.on("change", (event) -> {
                        String tabName = tab.getAttributeAsString("TabName");
                        if (isChanged()) {
                            tabName = "*" + tabName;
                        }
                        tab.setTitle(ModelingProjectView.getTabImgTitle(tabName, file.getExtension()));
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

                    updateCSC3dViewer(htmlPane.getID(), file.getId() + ".csc", savedContents);
                }
            });
        });
    }

    /**
     * `{@inheritDoc}
     */
    @Override
    public void save(ModelingProjectView modelingProjectView, Editor editor, Tab tab, PostProcessHandler handler) {
        super.save(modelingProjectView, editor, tab, handler);

        updateCSC3dViewer(htmlPane.getID(), file.getId() + ".csc", ((CSCEditor) editor).getValue());
    }

    /**
     * Create Json Object for Text information with Xtext Generator function
     * @param id ID of the canvas displaying the 3D view
     * @param fileId String combination of CSC file ID and ".csc"
     * @param value Input value of text editor
     */
    private void updateCSC3dViewer(String id, String fileId, String value) {
        // Text 情報をXtextのGenerator機能でJson Objectを作成する
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, requestUrl);
        requestBuilder.setHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
        StringBuilder requestData = new StringBuilder();
        requestData.append("resource= +" + fileId + ".sc" + "&");
        requestData.append("fullText=" + URL.encodeQueryString(value));
        try {
            requestBuilder.sendRequest(requestData.toString(), new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    try {
                        JSONObject jsonData = new JSONObject(JsonUtils.safeEval(response.getText()));
                        JSONString textData = jsonData.get("artifacts").isArray().get(0).isObject().get("content").isString();
                        JavaScriptObject jsObject;
                        try {
                            jsObject = JsonUtils.safeEval(textData.stringValue());
                        } catch (Exception ex) {
                            jsObject = JsonUtils.safeEval("{\"Scenario\":{\"roads\": [], \"objects\": []}}");
                        }
                        updateCSCDataIndomElement(id, jsObject);
                        htmlPane.setContentsURL("zipc_webplatform/js/threejs/webgl_geometry_extrude_road.html?cscDataId=" + htmlPane.getID());
                    } catch (Exception exception) {
                        SC.warn(exception.getMessage());
                    }
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    SC.warn(exception.getMessage());
                }
            });
        } catch (RequestException caught) {
            SC.warn(caught.getMessage());
        }
    }

    /**
     * Method to reflect JSON expression on the specified canvas
     * @param key ID of the canvas displaying the 3D view
     * @param value JAVAScriptObject containing JSON expression
     */
    private native void updateCSCDataIndomElement(String key, JavaScriptObject value) /*-{
		$wnd.cscDatas.set(key, value);
    }-*/;
}
