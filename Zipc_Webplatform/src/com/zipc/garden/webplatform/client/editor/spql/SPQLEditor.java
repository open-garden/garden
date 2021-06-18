package com.zipc.garden.webplatform.client.editor.spql;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.model.bp.BPRoot;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.scs.SCSRoot;
import com.zipc.garden.model.spql.SPQLFactory;
import com.zipc.garden.model.spql.SPQLRoot;
import com.zipc.garden.model.spql.SPQLSetting;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.EditorOpener;
import com.zipc.garden.webplatform.client.editor.EditorUtil;
import com.zipc.garden.webplatform.client.editor.SearchViewerInterface;
import com.zipc.garden.webplatform.client.editor.ace.Ace;
import com.zipc.garden.webplatform.client.editor.ace.AceMode;
import com.zipc.garden.webplatform.client.editor.ace.EditSession;
import com.zipc.garden.webplatform.client.editor.ace.JsObject;
import com.zipc.garden.webplatform.client.editor.ace.UndoManager;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.service.SPARQLQueryService;
import com.zipc.garden.webplatform.client.service.SPARQLQueryServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;
import com.zipc.garden.webplatform.client.view.part.DependentFileViewPart;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * Is a class that issues a query and acquires the information registered in RDF. <br>
 * Targets are feature-pattern, behavior-pattern, scenario-set
 */
public class SPQLEditor extends Editor implements EditorOpener {

    /** Menu for saving the contents of SPQLEditor */
    private MenuItem saveItem = new MenuItem("Save");

    /** EMF model that summarizes the contents of SPQLEditor */
    private SPQLRoot root;

    /** ID of the project in which the SPARQL file is managed */
    private long projectId;

    /** Asynchronous interface to get information about the selected file in the SPQLEditor */
    private EditResourceServiceAsync editResourceService;

    /** Overall layout of SPQLEditor */
    private final Layout vLayout = new VLayout();

    /** A class that displays a selection view of .fp, .bp .scs files */
    private DependentFileViewPart fileView;

    /** ID of the selected file (either .fp, .bp, .scs) */
    private long fileId = -1L;

    /** UUID of the selected file (either .fp, .bp, .scs) */
    private String uuid = "";

    /** The tab displaying the SPARQLEditor */
    private Tab tab;

    /** SPQLRoot at the time of saving */
    private SPQLRoot savedRoot;

    /** Asynchronous interface for issuing SPARQL queries */
    private SPARQLQueryServiceAsync spqlQueryService;

    /** MenuItem for launching the selected file(either .fp, .bp, .scs) */
    private MenuItem openFileItem = new MenuItem();

    /** Layout that displays the pattern number (fps, bps, scss) of the setting class */
    private final Layout settingLayout = new VLayout();

    /** Files with the extension specified here are displayed in the file selection view. */
    private final List<String> dependentFileExtensions = Arrays.asList(new String[] { Extension.BP.getValue(), Extension.FP.getValue(), Extension.SCS.getValue() });

    /**
     * The search result is reflected to the selected file and the file is displayed.<br>
     * It is reflected in all setting classes.
     */
    private final ButtonItem showAllInViewer = new ButtonItem();

    /**
     * constructor
     * @param root {@link SPQLEditor#root}
     * @param tab {@link SPQLEditor#tab}
     * @param projectId {@link SPQLEditor#projectId}
     * @param editResourceService {@link SPQLEditor#editResourceService}
     */
    public SPQLEditor(SPQLRoot root, Tab tab, long projectId, EditResourceServiceAsync editResourceService) {
        this.root = root;
        this.tab = tab;
        this.projectId = projectId;
        this.editResourceService = editResourceService;
        this.spqlQueryService = GWT.create(SPARQLQueryService.class);
        setSavedPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(ModelingProjectView modelingProjectView, Editor editor, Tab tab, PostProcessHandler handler) {
        super.save(modelingProjectView, editor, tab, handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create() {
        initView();
        bind();
    }

    /**
     * Initial processing of SPQL Editor is performed.<br>
     * The layout is built and the registered data is displayed.
     */
    private void initView() {
        vLayout.setWidth100();
        vLayout.setHeight100();
        vLayout.draw();

        settingLayout.setWidth100();
        settingLayout.setHeight100();

        fileView = new DependentFileViewPart("Dependent file", root, projectId, vLayout.getWidth(), vLayout.getHeight(), dependentFileExtensions, editResourceService);
        fileView.getSelectItem().setTitleAlign(Alignment.LEFT);

        if (!root.getRefs().isEmpty()) {
            this.uuid = root.getRefs().get(0).getRefid();
        }

        showAllInViewer.setTitle("Show all in viewer");
        showAllInViewer.setWidth(150);
        showAllInViewer.setStartRow(false);
        showAllInViewer.setEndRow(false);

        DynamicForm formViewFmc = fileView.getDependentFileView();
        formViewFmc.setNumCols(5);
        formViewFmc.setColWidths(150, 300, 150, 150, 150);
        formViewFmc.setItems(fileView.getSelectItem(), fileView.getSelect(), fileView.getReload(), showAllInViewer);

        createSettingLayout();

        vLayout.addMembers(fileView.getDependentFileView(), settingLayout);
    }

    /**
     * It will create various handlers for the SPQL editor.
     */
    private void bind() {
        fileView.addSelectClickHandler(fileView.getSelect());
        fileView.getSelectItem().addChangedHandler(event -> {
            String refId = root.getRefs().get(0).getRefid();
            if (!this.uuid.equals(refId)) {
                this.fileId = fileView.getSelectItem().getValueAsInteger();
                this.uuid = refId;
                root.getSettings().clear();
                removeSettingLayout();
                createSettingLayout();
            }
        });
        fileView.addReloadClickHandler(event -> {
            removeSettingLayout();
            createSettingLayout();
        }, fileView.getReload());
        showAllInViewer.addClickHandler(event -> {
            showInViewerBtnClickEvent(-1, root.getSettings());
        });
        vLayout.addKeyPressHandler(event -> {
            if (event.isCtrlKeyDown() && "S".equals(event.getKeyName())) {
                event.cancel();
                saveItem.fireEvent(new MenuItemClickEvent(saveItem.getJsObj()));
            }
        });
    }

    /**
     * Not used.
     */
    @Override
    public EditManager getEditManager() {
        return null;
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
    public Layout getLayout() {
        return vLayout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SPQLRoot getRoot() {
        return root;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSaveHandler(ClickHandler handler) {
        saveItem.addClickHandler(handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChanged() {
        if (savedRoot.getSettings().size() != root.getSettings().size()) {
            return true;
        } else if (savedRoot.getUuid() != root.getUuid()) {
            return true;
        } else {
            if (savedRoot.getSettings().size() == root.getSettings().size()) {
                for (int i = 0; i < savedRoot.getSettings().size(); i++) {
                    if (!savedRoot.getSettings().get(i).getUuid().equals(root.getSettings().get(i).getUuid())) {
                        return true;
                    } else if (!savedRoot.getSettings().get(i).getQuery().equals(root.getSettings().get(i).getQuery())) {
                        return true;
                    } else if (!savedRoot.getSettings().get(i).getResult().equals(root.getSettings().get(i).getResult())) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSavedPosition() {
        savedRoot = EcoreUtil.copy(root);
    }

    /**
     * Controls whether the "Show in Viewer" button is available.<br>
     * It can be used when a numerical value is displayed in a TextItem (comma delimiters can also be used).
     * @param resultArea TextItem displaying SPARQL search results
     * @param showInViewerBtn Button that reflects the search result and displays the file
     */
    private void setDisabledShowInButton(TextItem resultArea, ButtonItem showInViewerBtn) {
        if (resultArea.getValue() == null || "".equals(resultArea.getValue()) || fileId == -1L) {
            showInViewerBtn.setDisabled(true);
        } else {
            if (resultArea.getValueAsString().matches("^(|([0-9])+(,[0-9]+)*(\\r\\n|\\n|\\r|))$")) {
                showInViewerBtn.setDisabled(false);
            } else {
                showInViewerBtn.setDisabled(true);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getOpenFileId() {
        return fileId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addOpenFileHandler(com.smartgwt.client.widgets.menu.events.ClickHandler handler) {
        openFileItem.addClickHandler(handler);
    }

    /**
     * Creates an input layout for the settings class associated with the selected file.
     */
    private void createSettingLayout() {
        if (root.getRefs().isEmpty()) {
            return;
        }
        editResourceService.getFileUuidMap(projectId, new AsyncCallback<Map<String, Long>>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(Map<String, Long> uuidMap) {
                fileId = uuidMap.get(root.getRefs().get(0).getRefid());
                editResourceService.getFileContentIsSetting(fileId, new AsyncCallback<byte[]>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(byte[] result) {
                        AbstractRootElement root = EditorUtil.getInstance().convertToRootElement(result);
                        List<AbstractSetting> settings = new ArrayList<>();
                        if (root instanceof TPRoot) {
                            settings = new ArrayList<>(((TPRoot) root).getSettings());
                        } else if (root instanceof BPRoot) {
                            settings = new ArrayList<>(((BPRoot) root).getSettings());
                        } else if (root instanceof SCSRoot) {
                            settings = new ArrayList<>(((SCSRoot) root).getSettings());
                        }

                        SPQLRoot copySpqlRoot = EcoreUtil.copy(SPQLEditor.this.root);
                        SPQLEditor.this.root.getSettings().clear();

                        for (AbstractSetting setting : settings) {
                            DynamicForm fileForm = new DynamicForm();
                            fileForm.setMargin(10);
                            fileForm.setWidth100();
                            fileForm.setNumCols(4);
                            fileForm.setColWidths(150, 470, 100, 200);

                            TextItem patternNosItem = new TextItem();
                            patternNosItem.setWidth(470);
                            patternNosItem.setTitleAlign(Alignment.LEFT);
                            patternNosItem.setEndRow(false);
                            patternNosItem.setCanEdit(false);
                            patternNosItem.setRequired(true);
                            patternNosItem.setShowFocused(false);

                            ButtonItem sparqlEditBtn = new ButtonItem();
                            sparqlEditBtn.setWidth(100);
                            sparqlEditBtn.setTitle("Edit");
                            sparqlEditBtn.setStartRow(false);
                            sparqlEditBtn.setEndRow(false);

                            ButtonItem showInViewerBtn = new ButtonItem();
                            showInViewerBtn.setWidth(200);
                            showInViewerBtn.setTitle("Show in viewer");
                            showInViewerBtn.setStartRow(false);
                            showInViewerBtn.setEndRow(true);

                            Optional<SPQLSetting> optSpqlSetting = copySpqlRoot.getSettings().stream().filter(s -> s.getUuid().equals(setting.getUuid())).findFirst();
                            SPQLSetting spqlSetting;
                            if (optSpqlSetting.isPresent()) {
                                spqlSetting = optSpqlSetting.get();
                                copySpqlRoot.getSettings().remove(spqlSetting);
                            } else {
                                StringBuilder query = new StringBuilder();
                                query.append("select ?g \r\n");
                                query.append("where \r\n");
                                query.append("{\r\n");
                                query.append("    graph ?g\r\n");
                                query.append("    {\r\n");
                                query.append("        ?s ?p ?o\r\n");
                                query.append("    }\r\n");
                                query.append("}\r\n");
                                query.append("group by ?g");

                                spqlSetting = SPQLFactory.eINSTANCE.createSPQLSetting();
                                spqlSetting.setUuid(setting.getUuid());
                                spqlSetting.setQuery(query.toString());
                                spqlSetting.setResult("");
                            }
                            SPQLEditor.this.root.getSettings().add(spqlSetting);
                            patternNosItem.setValue(spqlSetting.getResult());

                            sparqlEditBtn.addClickHandler(event -> {
                                openSPARQLEditorWindow(spqlSetting, setting, patternNosItem, showInViewerBtn);
                            });
                            showInViewerBtn.addClickHandler(event -> {
                                showInViewerBtnClickEvent(SPQLEditor.this.root.getSettings().indexOf(spqlSetting), SPQLEditor.this.root.getSettings());
                            });

                            reflectFileInfo(setting.getUuid(), setting.getSettingHash(), patternNosItem, sparqlEditBtn, showInViewerBtn);

                            fileForm.setItems(patternNosItem, sparqlEditBtn, showInViewerBtn);

                            setDisabledShowInButton(patternNosItem, showInViewerBtn);

                            settingLayout.addMember(fileForm);
                        }
                    }
                });
            }
        });
    }

    /**
     * It is called when the query input screen is started.<br>
     * Get id based on uuid and set it in fileIdItem.
     * @param uuid UUID of the file associated with the setting class
     * @param fileIdItem TextItem that displays the obtained file ID
     */
    private void setFileId(String uuid, TextItem fileIdItem) {
        editResourceService.getVMFile(uuid, projectId, new AsyncCallback<VMFile>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(VMFile result) {
                fileIdItem.setValue(result.getId());
            }
        });
    }

    /**
     * The background color changes depending on the state of the file related to the setting class.<br>
     * Gray if the file has been deleted.<br>
     * Orange when the file information has changed from when it was registered.<br>
     * Also, the button will not be available if the file has been deleted.
     * @param uuid UUID of the file associated with the setting class.
     * @param hash Hash value of the file related to the setting class.
     * @param patternItem A TextItem that displays the query search results
     * @param editBtn Button that starts the query edit screen
     * @param viewerBtn Button that applies search results to files and displays
     */
    private void reflectFileInfo(String uuid, String hash, TextItem patternItem, ButtonItem editBtn, ButtonItem viewerBtn) {
        editResourceService.getVMFile(uuid, projectId, new AsyncCallback<VMFile>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(VMFile result) {
                if (result == null) {
                    editResourceService.getVMFile(uuid, projectId, true, new AsyncCallback<VMFile>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }

                        @Override
                        public void onSuccess(VMFile result) {
                            patternItem.setTitle(result.getName() + "." + result.getExtensionStr());
                            patternItem.setTitleStyle("bg-Deleted");
                            patternItem.setCellStyle("bg-Deleted");
                            editBtn.setDisabled(true);
                            viewerBtn.setDisabled(true);
                            settingLayout.redraw();
                        }
                    });
                } else {
                    editResourceService.getHash(uuid, projectId, new AsyncCallback<String>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }

                        @Override
                        public void onSuccess(String result) {
                            if (!hash.equals(result)) {
                                patternItem.setTitleStyle("bg-ChgHash");
                                patternItem.setCellStyle("bg-ChgHash");
                            }
                        }
                    });
                    patternItem.setTitle(result.getName() + "." + result.getExtensionStr());
                    settingLayout.redraw();
                }
            }
        });
    }

    /**
     * The query is issued with the syntax you entered and the results are displayed.
     * @param spql The syntax you entered.
     * @param resultArea Display destination of the result of issuing the query
     */
    private void runQueryBtnClickEvent(String spql, TextAreaItem resultArea) {
        spqlQueryService.runQuery(spql, new AsyncCallback<List<String>>() {

            @Override
            public void onFailure(Throwable caught) {
                resultArea.setValue(caught.getLocalizedMessage());
            }

            @Override
            public void onSuccess(List<String> result) {
                String resultStr = "";
                resultArea.setValue(resultStr);
                for (int i = 0; i < result.size(); i++) {
                    resultStr += result.get(i) + "\r\n";
                }
                resultArea.setValue(resultStr);
            }
        });
    }

    /**
     * This is the process when showInViewerBtn is pressed. <br>
     * The search results are applied to the selected files and the files are displayed.
     * @param key Index position of the setting class to apply.<br>
     *            If -1, the search results will be applied to all setting class.
     * @param spqlSettings Class with information of query search results
     */
    private void showInViewerBtnClickEvent(int key, List<SPQLSetting> spqlSettings) {
        String errMsg = "Cannot start because the " + fileView.getSelectItem().getDisplayValue() + " file has been changed. Press the Reload button.";

        editResourceService.getFileContent(fileId, new AsyncCallback<byte[]>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(byte[] result) {
                AbstractRootElement root = EditorUtil.getInstance().convertToRootElement(result);
                List<AbstractSetting> settings = new ArrayList<>();
                if (root instanceof TPRoot) {
                    TPRoot tpRoot = (TPRoot) root;
                    settings = tpRoot.getSettings();
                } else if (root instanceof BPRoot) {
                    BPRoot bpRoot = (BPRoot) root;
                    settings = bpRoot.getSettings();
                } else if (root instanceof SCSRoot) {
                    SCSRoot scsRoot = (SCSRoot) root;
                    settings = scsRoot.getSettings();
                }

                if (settings.size() != spqlSettings.size()) {
                    SC.warn(errMsg);
                    return;
                }
                List<String> patternNos = new ArrayList<>();
                List<String> uuids = new ArrayList<>();

                for (int i = 0; i < spqlSettings.size(); i++) {
                    SPQLSetting spqlSetting = spqlSettings.get(i);
                    AbstractSetting setting = settings.get(i);
                    if (!spqlSetting.getUuid().equals(setting.getUuid())) {
                        SC.warn(errMsg);
                        return;
                    }
                    uuids.add(spqlSetting.getUuid());
                    if (spqlSetting.getResult() == null || spqlSetting.getResult().isEmpty() || !spqlSetting.getResult().matches("^(|([0-9])+(,[0-9]+)*(\\r\\n|\\n|\\r|))$")) {
                        patternNos.add(setting.getPatternNos());
                    } else {
                        if (key == -1 || key == i) {
                            patternNos.add(spqlSetting.getResult());
                        } else {
                            patternNos.add(setting.getPatternNos());
                        }
                    }
                }

                SearchViewerInterface openedEditor = EditorUtil.getInstance().findAndSelectEditor(tab, fileId);
                if (openedEditor != null) {
                    openedEditor.setRowIds(patternNos, uuids);
                    openedEditor.doApply();
                } else {
                    for (int i = 0; i < patternNos.size(); i++) {
                        settings.get(i).setPatternNos(patternNos.get(i));
                    }
                    BinaryResourceImpl r = new BinaryResourceImpl();
                    r.getContents().add(root);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    try {
                        r.save(outputStream, null);
                    } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                    }
                    byte[] bytes = outputStream.toByteArray();
                    editResourceService.saveFile(fileId, bytes, new AsyncCallback<Void>() {
                        @Override
                        public void onSuccess(Void result) {
                            openFileItem.fireEvent(new MenuItemClickEvent(openFileItem.getJsObj()));
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }
                    });
                }
            }
        });
    }

    /**
     * Opens a modal screen that issues a SPARQL query.
     * @param setting Class that stores the issuance result of query
     * @param absSetting Information about the setting class. It has a setting file ID and a hash value.
     * @param patternNosItem TextItem that displays the result of issuing the query
     * @param showInViewerBtn Button that reflects the search result and displays the file
     */
    private void openSPARQLEditorWindow(SPQLSetting setting, AbstractSetting absSetting, TextItem patternNosItem, ButtonItem showInViewerBtn) {
        Window winModal = new Window();
        winModal.setTitle("SPARQL Editor");
        winModal.setShowMaximizeButton(true);
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.setShowBody(true);
        winModal.setShowFooter(true);
        winModal.setShowResizer(true);
        winModal.setKeepInParentRect(true);
        winModal.setAutoCenter(true);
        winModal.setBackgroundColor("white");
        winModal.setCanDragResize(true);
        winModal.setWidth((int) Math.round(vLayout.getWidth() * 0.9));
        winModal.setHeight((int) Math.round(vLayout.getHeight() * 0.9));
        winModal.show();

        VLayout body = new VLayout();
        body.setWidth100();
        body.setHeight100();

        DynamicForm showFileForm = new DynamicForm();
        showFileForm.setMargin(10);
        showFileForm.setWidth100();
        showFileForm.setNumCols(2);
        showFileForm.setColWidths(100, 370);

        TextItem fileIdItem = new TextItem();
        fileIdItem.setWidth(470);
        fileIdItem.setTitle("File Id");
        fileIdItem.setTitleAlign(Alignment.LEFT);
        fileIdItem.setEndRow(false);
        fileIdItem.setCanEdit(false);
        fileIdItem.setRequired(true);

        setFileId(absSetting.getUuid(), fileIdItem);

        TextItem hashItem = new TextItem();
        hashItem.setWidth(470);
        hashItem.setTitle("Hash");
        hashItem.setTitleAlign(Alignment.LEFT);
        hashItem.setValue(absSetting.getSettingHash());
        hashItem.setEndRow(false);
        hashItem.setCanEdit(false);
        hashItem.setRequired(true);
        showFileForm.setItems(fileIdItem, hashItem);

        HLayout sparqlEditorArea = new HLayout();
        sparqlEditorArea.setWidth("100%");
        sparqlEditorArea.setHeight("100%");
        sparqlEditorArea.setEdgeSize(1);
        sparqlEditorArea.setShowEdges(true);
        sparqlEditorArea.setMinHeight(100);

        DynamicForm resultForm = new DynamicForm();
        resultForm.setMargin(10);
        resultForm.setWidth100();
        resultForm.setNumCols(1);

        ButtonItem runQueryBtn = new ButtonItem();
        runQueryBtn.setTitle("Run Query");
        runQueryBtn.setWidth(100);
        runQueryBtn.setEndRow(false);

        TextAreaItem resultArea = new TextAreaItem();
        resultArea.setWidth("100%");
        resultArea.setHeight(70);
        resultArea.setShowTitle(false);
        resultArea.setValue(setting.getResult());
        resultArea.setCanEdit(false);

        resultForm.setItems(runQueryBtn, resultArea);

        body.addMembers(showFileForm, sparqlEditorArea, resultForm);

        HLayout hlayout = new HLayout();
        hlayout.setHeight(30);
        hlayout.setWidth100();
        hlayout.setLayoutLeftMargin(20);
        IButton okBtn = new IButton("OK");
        okBtn.setHeight100();
        okBtn.setWidth100();
        okBtn.setMargin(5);
        IButton closeBtn = new IButton("Close");
        closeBtn.setHeight100();
        closeBtn.setWidth100();
        closeBtn.setMargin(5);
        LayoutSpacer hspacer = new LayoutSpacer(3, "100%");
        hspacer.setBackgroundColor("white");
        hlayout.addMembers(hspacer, okBtn, hspacer, closeBtn, hspacer);

        winModal.getBody().addChild(body);
        winModal.getFooter().addMember(hlayout, 0);

        Scheduler.get().scheduleDeferred(() -> {
            com.zipc.garden.webplatform.client.editor.ace.Editor editor = Ace.edit(sparqlEditorArea.getDOM().getId());
            String style = sparqlEditorArea.getDOM().getAttribute("style");
            sparqlEditorArea.getDOM().setAttribute("style", style + " height:100%;");
            editor.setTheme("ace/theme/chrome");
            editor.setFontSize(14);
            editor.setShowPrintMargin(false);
            EditSession session = editor.getSession();
            session.setMode(AceMode.SPARQL);
            session.setUseWrapMode(true);
            // session.setTabSize(2);
            UndoManager manager = session.getUndoManager();
            editor.blockScrolling = "Infinity";
            editor.setOption("enableBasicAutocompletion", "true");
            editor.setOption("enableSnippets", "true");
            editor.setOption("enableLiveAutocompletion", "false");
            editor.setValue(setting.getQuery());
            manager.reset();

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

            runQueryBtn.addClickHandler(event -> {
                setting.setQuery(editor.getValue());
                runQueryBtnClickEvent(editor.getValue(), resultArea);
            });

            okBtn.addClickHandler(event -> {
                setting.setQuery(editor.getValue());
                setting.setResult(resultArea.getValueAsString());
                patternNosItem.setValue(resultArea.getValue());
                setDisabledShowInButton(patternNosItem, showInViewerBtn);
                winModal.markForDestroy();
            });

            closeBtn.addClickHandler(event -> {
                winModal.markForDestroy();
            });

            winModal.addCloseClickHandler(event -> {
                event.cancel();
                winModal.markForDestroy();
            });

            winModal.addResizedHandler(event -> {
                // lscEditorArea is depend on ACEEditor,so you should arrange ACEEditor's style.
                sparqlEditorArea.getDOM().setAttribute("style", sparqlEditorArea.getDOM().getAttribute("style") + " height:100%;");
                // you have to arrange smartGWT width because button is hided.
                sparqlEditorArea.setWidth100();
                editor.resize();
                winModal.getBody().setWidth100();
                winModal.getFooter().setWidth100();
            });
        });
    }

    /**
     * Delete the layout that displays the pattern number of the setting class.
     */
    private void removeSettingLayout() {
        Arrays.stream(settingLayout.getMembers()).forEach(canvas -> canvas.getParentCanvas().removeChild(canvas));
    }
}
