package com.zipc.garden.webplatform.client.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragLeaveHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.util.ValueCallback;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.drawing.DrawRect;
import com.smartgwt.client.widgets.events.ClearEvent;
import com.smartgwt.client.widgets.events.ClearHandler;
import com.smartgwt.client.widgets.events.DragStopEvent;
import com.smartgwt.client.widgets.events.DragStopHandler;
import com.smartgwt.client.widgets.events.DropCompleteEvent;
import com.smartgwt.client.widgets.events.DropCompleteHandler;
import com.smartgwt.client.widgets.events.DropOutEvent;
import com.smartgwt.client.widgets.events.DropOutHandler;
import com.smartgwt.client.widgets.events.KeyDownEvent;
import com.smartgwt.client.widgets.events.KeyDownHandler;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.events.RightMouseDownEvent;
import com.smartgwt.client.widgets.events.RightMouseDownHandler;
import com.smartgwt.client.widgets.events.ShowContextMenuEvent;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellContextClickEvent;
import com.smartgwt.client.widgets.grid.events.CellContextClickHandler;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemIfFunction;
import com.smartgwt.client.widgets.menu.MenuItemSeparator;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.CloseClickHandler;
import com.smartgwt.client.widgets.tab.events.TabCloseClickEvent;
import com.smartgwt.client.widgets.tab.events.TabDeselectedEvent;
import com.smartgwt.client.widgets.tab.events.TabDeselectedHandler;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.FolderOpenedEvent;
import com.smartgwt.client.widgets.tree.events.FolderOpenedHandler;
import com.zipc.garden.core.CommandListener;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.core.EditOptions;
import com.zipc.garden.core.ZGCreateDirCommand;
import com.zipc.garden.core.ZGCreateFileCommand;
import com.zipc.garden.core.ZGMoveCommand;
import com.zipc.garden.core.ZGRenameCommand;
import com.zipc.garden.core.ZGResourceRemoveCommand;
import com.zipc.garden.model.arc.ARCFactory;
import com.zipc.garden.model.arc.ARCRoot;
import com.zipc.garden.model.arc.ARCState;
import com.zipc.garden.model.bp.BPRoot;
import com.zipc.garden.model.bps.BPSRoot;
import com.zipc.garden.model.cb.CBRoot;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.cscs.CSCSRoot;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMPackage;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.model.fmc.FMCRoot;
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.model.fsm.FSMPackage;
import com.zipc.garden.model.scd.SCDRoot;
import com.zipc.garden.model.scs.SCSRoot;
import com.zipc.garden.model.spql.SPQLRoot;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.model.tps.TPSRoot;
import com.zipc.garden.webplatform.client.command.ARCEditorCommandProvider;
import com.zipc.garden.webplatform.client.command.FMEditorCommandProvider;
import com.zipc.garden.webplatform.client.core.screentransition.FrameBase;
import com.zipc.garden.webplatform.client.core.screentransition.FrameManager;
import com.zipc.garden.webplatform.client.core.screentransition.ViewDefine;
import com.zipc.garden.webplatform.client.editor.Editor;
import com.zipc.garden.webplatform.client.editor.EditorOpener;
import com.zipc.garden.webplatform.client.editor.FileHistoryEditor;
import com.zipc.garden.webplatform.client.editor.NodeArrange;
import com.zipc.garden.webplatform.client.editor.NodeArrangeInterface;
import com.zipc.garden.webplatform.client.editor.ace.TextEditor;
import com.zipc.garden.webplatform.client.editor.arc.ARCEditor;
import com.zipc.garden.webplatform.client.editor.bp.BPEditor;
import com.zipc.garden.webplatform.client.editor.bps.BPSEditor;
import com.zipc.garden.webplatform.client.editor.cb.CBEditor;
import com.zipc.garden.webplatform.client.editor.csc.CSCEditor;
import com.zipc.garden.webplatform.client.editor.cscs.CSCSEditor;
import com.zipc.garden.webplatform.client.editor.fm.FMDrawNode;
import com.zipc.garden.webplatform.client.editor.fm.FMEditor;
import com.zipc.garden.webplatform.client.editor.fmc.FMCEditor;
import com.zipc.garden.webplatform.client.editor.fsm.FSMEditor;
import com.zipc.garden.webplatform.client.editor.scd.SCDEditor;
import com.zipc.garden.webplatform.client.editor.scs.SCSEditor;
import com.zipc.garden.webplatform.client.editor.spql.SPQLEditor;
import com.zipc.garden.webplatform.client.editor.tc.TCEditor;
import com.zipc.garden.webplatform.client.editor.tp.TPViewer;
import com.zipc.garden.webplatform.client.editor.tps.TPSEditor;
import com.zipc.garden.webplatform.client.service.EditResourceService;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.service.LoginService;
import com.zipc.garden.webplatform.client.service.LoginServiceAsync;
import com.zipc.garden.webplatform.client.service.ProgressCheckService;
import com.zipc.garden.webplatform.client.service.ProgressCheckServiceAsync;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;
import com.zipc.garden.webplatform.client.view.jsinterop.JSDocument;
import com.zipc.garden.webplatform.client.view.part.FileCorrelationDiagramWindowViewPart;
import com.zipc.garden.webplatform.client.view.part.HeaderViewPart;
import com.zipc.garden.webplatform.client.view.part.ProgViewPart;
import com.zipc.garden.webplatform.client.view.part.SearchFileViewPart;
import com.zipc.garden.webplatform.client.view.part.SearchModelViewPart;
import com.zipc.garden.webplatform.client.view.part.ViewPartBase;
import com.zipc.garden.webplatform.shared.ExportUtil;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.FileTreeNode;
import com.zipc.garden.webplatform.shared.FileTreeNodeFactory;
import com.zipc.garden.webplatform.shared.UserInfo;
import com.zipc.garden.webplatform.shared.resource.VMDirectory;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * <pre>
 * This class summarizes the processing related to Project Explorer. 
 * The main functions are as follows.
 * ・New / edit / delete / duplicate file or directory
 * ・Undo / Redo for a file or directory
 * ・Download file or directory
 * ・File or directory upload
 * </pre>
 */
public class ModelingProjectView extends FrameBase {

    /** Editor attribute name. Set to tab. */
    public static final String EDITOR = "Editor";

    /** ListGrid showing directories and files registered in the project */
    private TreeGrid treeGrid;

    /** Tree information associated with {@link #treeGrid} */
    private Tree tree;

    /** Context menu that appears when you right-click on Project Explorer */
    private Menu mainMenu;

    /** A submenu of {@link #newMenuItem}. You can select a new file to create. */
    private Menu newSubMenu;

    /** A submenu of {@link #mainMenu}. When selected, the directory (or file) rename window is displayed. */
    private MenuItem renameMenuItem;

    /** A submenu of {@link #mainMenu}. When selected, {@link #newSubMenu} will be expanded. */
    private MenuItem newMenuItem;

    /** A list of new file creation menus set to {@link #newSubMenu}. */
    private SubMenuItemList subMenuList;

    /** A submenu of {@link #mainMenu}. When selected, the screen will change to the project information edit screen. */
    private MenuItem settingMenuItem;

    /**
     * A submenu of {@link #mainMenu}. <br>
     * When selected, a confirmation message for deleting the selected directory (or file) is displayed.
     */
    private MenuItem deleteMenu;

    /** A submenu of {@link #mainMenu}. When selected, the change history screen for the selected file is displayed. */
    private MenuItem historyMenuItem;

    /** A submenu of {@link #mainMenu}. When selected, the copied directory or file will be pasted. */
    private MenuItem pasteMenuItem;

    /** A submenu of {@link #mainMenu}. When selected, the selected directory or file will be downloaded. */
    private MenuItem downloadMenuItem;

    /**
     * A submenu of {@link #mainMenu}. <br>
     * When selected, a screen will appear showing the progress of the jobs running in this project.
     */
    private MenuItem progMenuItem;

    /** A submenu of {@link #mainMenu}. When selected, the selected directory or file is copied. */
    private MenuItem copyMenuItem;

    /** Used to launch files without the extension. */
    private MenuItem openWithMenuItem;

    /** If clicked, it launches a text editor. */
    private MenuItem openTextMenuItem;

    /** Click to start the node search screen of FM editor and FSM editor. */
    private MenuItem searchMenuItem;

    /** Click to launch the FP export condition input screen. */
    private MenuItem exportMenuItem;

    /** It works by pressing Ctrl + Shift + R. The file search screen is launched. */
    private MenuItem searchFileMenuItem;

    /** When pressed, the screen that displays the file correlation diagram is launched. */
    private MenuItem correlationDiagramMenuItem;

    /** When pressed, the path of the selected directory or file will be copied. */
    private MenuItem copyPathMenuItem;

    /** When pressed, a new FPS file will be created. */
    private SubMenuItem newFPSItem;

    /** When pressed, a new FP file will be created. */
    private SubMenuItem newFPItem;

    /** When pressed, a new TC file will be created. */
    private SubMenuItem newTCItem;

    /** When pressed, a new FMC file will be created. */
    private SubMenuItem newFMCItem;

    /** When pressed, a new BPS file will be created. */
    private SubMenuItem newBPSItem;

    /** When pressed, a new BP file will be created. */
    private SubMenuItem newBPItem;

    /** When pressed, a new SCS file will be created. */
    private SubMenuItem newSCSItem;

    /** When pressed, a new SPQL file will be created. */
    private SubMenuItem newSPQLItem;

    /** It is the layout of the project explorer part */
    private Layout innerPane;

    /** The layout on the left side of the project screen. Includes the layout of the project explorer. */
    private VLayout leftLayout;

    /** The layout on the right side of the project screen. The contents of the file to be opened are displayed. */
    private VLayout editorLayout;

    /** A class that contains tabs that appear at the top of the editor. Users can select tabs to display each pane. */
    private TabSet editorTabSet;

    /** Root directory ID */
    private long rootId;

    /** ID of the project to edit */
    private long projectId;

    /** Name of the project to edit */
    private String projectName;

    /** File extension to edit in text format */
    private Set<String> editExtensions;

    /** RootPanel event handler. Used for drag and drop. */
    private HandlerRegistration rootPanelHandler;

    /** A class that manages the node search screens of the FM editor and FSM editor. */
    private SearchModelViewPart search;

    /** A class that manages the file search screen. */
    private SearchFileViewPart searchFile;

    /** Show the Progress in right key click menu */
    private ProgViewPart progDialog;

    /** Service Used to check the Job Progress */
    private ProgressCheckServiceAsync progressCheckService;

    /** The registration object that is returned when the event handler is bound and is used to unregister. */
    private List<HandlerRegistration> viewHandler = new ArrayList<HandlerRegistration>();

    /** Services used to retrieve, update, and delete files in Project Explorer */
    private EditResourceServiceAsync editResourceService;

    /** The service used to retrieve logged-in user information */
    private LoginServiceAsync loginService;

    /** A class that holds the operation history of Project Explorer. You can use the Undo / Redo function. */
    private final EditManager manager = EditManager.createInstance();

    /**
     * Holds event handlers that occur within the active tab. <br>
     * It is used to indicate whether the information of the file displayed on the tab is being edited.<br>
     * key: tab ID / Value: handler
     */
    private Map<String, HandlerRegistration> tabRegs = new HashMap<>();

    /**
     * Used when copying directories or files. Sets the number of directories or files with the same name as the copied files.
     */
    private int copyNumber;

    /** A class that summarizes the processing related to TreeNode of the file displayed in Project Explorer */
    private FileTreeNodeFactory fileTreeNodeFactory = new FileTreeNodeFactory();

    /** Map with directory path and ID. */
    private Map<String, Long> folderPath_idMap = new HashMap<String, Long>();

    /** Used when dragging and dropping. The number of files in the folder is retained. */
    private int filesUnderFolder;

    /** It is used to check the existence of uploaded files. If it already exists, it will be set to True. */
    private boolean isAlreadyExist;

    /** Used when dragging and dropping. True is retained if the root folder is uploaded. */
    private boolean rootFolderUploaded;

    /** Used when dragging and dropping. The path of the folder being uploaded is retained. */
    private List<String> loopDir;

    /** It is a window to display the file correlation diagram */
    private Window fileCorrelationDiagramWindow;

    /**
     * constructor
     * @param map GET parameter
     */
    public ModelingProjectView(Map<String, String> param) {
        super(param);
    }

    /**
     * The tree information associated with the {@link #treeGrid} is retrieved.
     * @return {@link #tree}
     */
    public Tree getTree() {
        return tree;
    }

    /**
     * Gets a class that summarizes the processing related to the TreeNode the file.
     * @return {@link #fileTreeNodeFactory}
     */
    public FileTreeNodeFactory getFileTreeNodeFactory() {
        return fileTreeNodeFactory;
    }

    /**
     * {@inheritDoc}
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
        this.projectId = Long.valueOf(getParam().get("projectId"));
        this.projectName = getParam().get("name");
        editResourceService = GWT.create(EditResourceService.class);
        loginService = GWT.create(LoginService.class);
        progressCheckService = GWT.create(ProgressCheckService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Canvas[] createCanvases() {
        getLayout().setWidth100();
        getLayout().setHeight100();

        leftLayout = new VLayout();
        leftLayout.setWidth("25%");
        leftLayout.setHeight100();
        leftLayout.setGroupBorderCSS("2px solid black");
        leftLayout.setShowResizeBar(true);

        editorLayout = new VLayout();
        editorLayout.setWidth100();
        editorLayout.setHeight100();

        editorTabSet = new TabSet();
        editorLayout.addMember(editorTabSet);

        treeGrid = new TreeGrid();
        treeGrid.setBorder("0px");
        treeGrid.setPadding(3);
        treeGrid.setBodyStyleName("normal");
        treeGrid.setShowHeader(true);
        treeGrid.setTreeFieldTitle(projectName);
        treeGrid.setLeaveScrollbarGap(false);
        treeGrid.setManyItemsImage("cubes_all.png");
        treeGrid.setAppImgDir("pieces/16/");
        treeGrid.setShowCellContextMenus(true);
        treeGrid.setShowEmptyMessage(false);
        treeGrid.setCanEdit(false);
        treeGrid.setCanSort(false);
        treeGrid.setShowSortArrow(SortArrow.NONE);
        treeGrid.setShowHeaderContextMenu(false);
        treeGrid.setShowHeaderMenuButton(false);
        treeGrid.setCanResizeFields(false);
        treeGrid.setCanReorderFields(false);
        treeGrid.setCanAcceptDroppedRecords(true);
        treeGrid.setSeparateFolders(true);
        treeGrid.setSortFoldersBeforeLeaves(true);
        treeGrid.setSortDirection(SortDirection.ASCENDING);

        mainMenu = new Menu();
        newSubMenu = new Menu();

        subMenuList = new SubMenuItemList();
        subMenuList.add("Directory");
        subMenuList.add(Extension.SCD);
        subMenuList.add(Extension.FM);
        subMenuList.add(Extension.FMC);
        subMenuList.add(Extension.TC);
        subMenuList.add(Extension.ARC);
        subMenuList.add(Extension.FSM);
        subMenuList.add(Extension.FPS);
        subMenuList.add(Extension.FP);
        subMenuList.add(Extension.BPS);
        subMenuList.add(Extension.BP);
        subMenuList.add(Extension.SPQL);
        subMenuList.add(Extension.SCSS);
        subMenuList.add(Extension.SCS);
        subMenuList.add(Extension.TXT);
        subMenuList.add(Extension.CSC);

        newFPSItem = new SubMenuItem(Extension.FPS);
        newFPItem = new SubMenuItem(Extension.FP);
        newTCItem = new SubMenuItem(Extension.TC);
        newFMCItem = new SubMenuItem(Extension.FMC);
        newBPSItem = new SubMenuItem(Extension.BPS);
        newBPItem = new SubMenuItem(Extension.BP);
        newSCSItem = new SubMenuItem(Extension.SCS);
        newSPQLItem = new SubMenuItem(Extension.SPQL);

        newMenuItem = new MenuItem("New");
        settingMenuItem = new MenuItem("Settings");
        deleteMenu = new MenuItem("Delete", "pieces/16/delete.gif");
        renameMenuItem = new MenuItem("Rename...", null, "F2");
        historyMenuItem = new MenuItem("History", "pieces/16/history.png");
        copyMenuItem = new MenuItem("Copy", "pieces/16/copy.gif", "Crtl+C");
        pasteMenuItem = new MenuItem("Paste", "pieces/16/paste.gif", "Crtl+V");
        downloadMenuItem = new MenuItem("Download");
        if (getCopiedResources().size() == 0) {
            pasteMenuItem.setEnabled(false);
        } else {
            pasteMenuItem.setEnabled(true);
        }
        searchMenuItem = new MenuItem("Search Model");
        exportMenuItem = new MenuItem("Export");
        searchFileMenuItem = new MenuItem("Search File");
        progMenuItem = new MenuItem("Check Progress");

        correlationDiagramMenuItem = new MenuItem("File Correlation Diagram");

        Menu openWithSubMenu = new Menu();
        openWithMenuItem = new MenuItem("Open with");
        openWithMenuItem.setSubmenu(openWithSubMenu);
        openTextMenuItem = new MenuItem("TextEditor");
        openWithSubMenu.setItems(openTextMenuItem);

        copyPathMenuItem = new MenuItem("Copy Path");

        newSubMenu.setItems(subMenuList.getArray());
        newMenuItem.setSubmenu(newSubMenu);

        innerPane = new Layout();
        innerPane.setWidth100();
        innerPane.setHeight100();
        innerPane.setMembers(treeGrid);

        Canvas[] leftCanvases = { innerPane };

        leftLayout.addMembers(leftCanvases);

        ViewPartBase header = new HeaderViewPart(getParam());

        Layout body = new Layout();
        body.setWidth100();
        body.setHeight100();
        body.addMembers(leftLayout, editorLayout);

        search = new SearchModelViewPart();
        search.openSearchWindow(this, editResourceService, projectId);

        searchFile = new SearchFileViewPart();
        searchFile.createSearchWindow(this, editResourceService, projectId);

        progDialog = new ProgViewPart();
        // check and get all the job belongs to the projectId
        progDialog.openProgWindow(progressCheckService);

        return new Canvas[] { header.getLayout(), body };
    }

    /**
     * <pre>
     * {@inheritDoc}
     * 
     * Create a project explorer.
     * Then get the text-editable file extension.
     * </pre>
     */
    @Override
    public void initialized() {
        Long id = Long.valueOf(getParam().get("id"));
        loadProjectTree(id);
        loginService.getLoginUserInfo(new AsyncCallback<UserInfo>() {
            @Override
            public void onSuccess(UserInfo result) {
                String[] extensions = result.getEditExtensions().split(";");
                editExtensions = new HashSet<String>(Arrays.asList(extensions));
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }
        });
    }

    /**
     * The directories and files belonging to the specified root directory ID are acquired and displayed in Explorer.
     * @param id Root directory ID
     */
    public void loadProjectTree(Long id) {
        rootId = id;
        editResourceService.getResources(id, new AsyncCallback<List<VMResource>>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(List<VMResource> result) {
                if (rootId == id) {
                    VMDirectory rootDir = new VMDirectory();
                    rootDir.setId(rootId);
                    rootDir.setName("/");
                    fileTreeNodeFactory.setRootDirectory(rootDir);
                }
                TreeNode[] nodes = fileTreeNodeFactory.getFileTreeNodes(id, result);
                tree = new Tree();
                tree.setDefaultIsFolder(true);
                tree.setShowRoot(true);
                tree.setModelType(TreeModelType.CHILDREN);
                tree.setNameProperty("fullName");
                tree.setData(nodes);
                treeGrid.setData(tree);
                treeGrid.sort();
            }
        });
    }

    /**
     * <pre>
     * {@inheritDoc}
     * 
     * The drag and drop event handler and the node search window are destroyed.
     * </pre>
     */
    @Override
    public void exit() {
        search.destroy();
        rootPanelHandler.removeHandler();
    }

    /**
     * JavaScript processing. <br>
     * The file or folder will be uploaded.
     * @param fileObj It is used to hold the data being dragged.
     * @param view It is used to call the processing of this Java class from JavaScript.
     * @param rootId Root directory ID
     */
    private static native void nativeUploadData(JavaScriptObject fileObj, ModelingProjectView view, Long rootId)
    /*-{
        if (fileObj.files.length === 0) {
            return;
        }
        var items = fileObj.items;
        var loopedDir = [];
        view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::filesUnderFolder = 0;
        view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::isAlreadyExist = false;
        view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::rootFolderUploaded = false;
    
        for (var i = 0; i < items.length; i++) {
            var item = items[i].webkitGetAsEntry();
            if (item) {
                createFolderItems(item, null, loopEntries);
            }
        }
    
        function createFolderItems(item, parent, callback) {
            if (item.isDirectory) {
                loopedDir.push(item.fullPath);
                view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::addLoopDir(Ljava/lang/String;)(item.fullPath);
    
                if (parent == null) {
                    var exist = view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::isAlreadyExist(Ljava/lang/String;Ljava/lang/String;)(item.name,"");
                    if (!exist) {
                        view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::createFolder(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)(item.name,item.fullPath,rootId);
                        view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::rootFolderUploaded = true;
                    } else {
                        return;
                    }
                } else {
                    var id = view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::getParentId(Ljava/lang/String;)(parent.fullPath);
                    view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::createFolder(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)(item.name,item.fullPath,id);
                }
                setTimeout(function() {
                    callback(item);
                }, 3000);
            } else {
                if (parent == null) {
                    var index = item.name.lastIndexOf(".") == 0 ? item.name
                            .length() : item.name.lastIndexOf(".") + 1;
                    var name = item.name.substring(0, index - 1);
                    var extension = item.name.substring(index);
                    var exist = view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::isAlreadyExist(Ljava/lang/String;Ljava/lang/String;)(name,extension);
                    if (!exist) {
                        item
                                .file(function(item) {
                                    var reader = new FileReader();
                                    reader.readAsArrayBuffer(item);
                                    reader.onload = function(e) {
                                        var arrayBuffer = e.target.result;
                                        var bytes = new Uint8Array(arrayBuffer);
                                        var byteArray = Array.from(bytes);
                                        view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::dropIn(Ljava/lang/String;[BLjava/lang/Long;)(item.name,byteArray,rootId);
                                    };
                                });
                    }
                } else {
                    view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::filesUnderFolder += 1;
                    var id = view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::getParentId(Ljava/lang/String;)(parent.fullPath);
                    if (id) {
                        item
                                .file(function(item) {
                                    var reader = new FileReader();
                                    reader.readAsArrayBuffer(item);
                                    reader.onload = function(e) {
                                        var arrayBuffer = e.target.result;
                                        var bytes = new Uint8Array(arrayBuffer);
                                        var byteArray = Array.from(bytes);
                                        view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::dropIn(Ljava/lang/String;[BLjava/lang/Long;)(item.name,byteArray,id);
                                    };
                                });
                    }
                }
            }
        }
        function loopEntries(item) {
            // Get folder contents
            var dirReader = item.createReader();
            dirReader.readEntries(function(entries) {
                for (var i = 0; i < entries.length; i++) {
                    createFolderItems(entries[i], item, loopEntries);
                    if (i == entries.length - 1) {
                        for (var j = 0; j < loopedDir.length; j++) {
                            if (loopedDir[j] === item.fullPath) {
                                loopedDir.splice(j, 1);
                                view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::removeFromLoopDir(I)(j);
                            }
                        }
                    }
                }
                if (entries.length == 0) {
                        for (var j = 0; j < loopedDir.length; j++) {
                            if (loopedDir[j] === item.fullPath) {
                                loopedDir.splice(j, 1);
                                view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::removeFromLoopDir(I)(j);
                            }
                        }
                }
                view.@com.zipc.garden.webplatform.client.view.ModelingProjectView::showUploadFinishMessage()();
            });
        }
    }-*/;

    /**
     * It will be checked if the one that matches the specified name and extension already exists in the project explorer.
     * @param name Specified file name or directory name
     * @param extension The extension of the specified file. If it is a directory, it will be an empty string.
     * @return True if the same directory name or file name exists.
     */
    private boolean isAlreadyExist(String name, String extension) {
        if (extension.equals("")) {
            if (isSameNameExist(tree.getRoot(), name, true, extension)) {
                SC.warn("Folder is already exist");
                if ("yellow".equals(leftLayout.getBackgroundColor())) {
                    leftLayout.setBackgroundColor("white");
                }
                return true;
            }
        } else {
            if (isSameNameExist(tree.getRoot(), name, false, extension)) {
                SC.warn("File is already exist");
                if ("yellow".equals(leftLayout.getBackgroundColor())) {
                    leftLayout.setBackgroundColor("white");
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Get the ID that matches the specified folder path from {@link #folderPath_idMap}.
     * @param path folder path
     * @return Directory ID. If it cannot be obtained, Null is returned.
     */
    private Long getParentId(String path) {
        for (Map.Entry mapElement : folderPath_idMap.entrySet()) {
            if (path.equals((String) mapElement.getKey())) {
                return (Long) mapElement.getValue();
            }
        }
        return null;
    }

    /**
     * Used when dragging and dropping.<br>
     * A new folder will be created in the specified directory.
     * @param name The name of the newly added folder
     * @param path The path of the newly added folder
     * @param id Parent directory ID
     */
    private void createFolder(String name, String path, Long id) {
        VMDirectory newDir = new VMDirectory();
        newDir.setName(name);
        editResourceService.createDir(id, name, new AsyncCallback<Long>() {

            @Override
            public void onFailure(Throwable caught) {
                SC.say(caught.getMessage());
            }

            @Override
            public void onSuccess(Long result) {
                createCallbackFile(fileTreeNodeFactory.findTreeNode(tree, id), newDir, result, true);
                folderPath_idMap.put(path, result);
                fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                treeGrid.sort();
                treeGrid.redraw();
            }
        });
    }

    /**
     * Used for drag and drop. <br>
     * The dropped file will be reflected in the project explorer.
     * @param name file name
     * @param str Binary data of the file
     * @param id directory id
     */
    private void dropIn(String name, byte[] str, Long id) {
        VMFile uploadFile = new VMFile();
        // javascript からの戻りが0～255の範囲なので-128～127の範囲に補完する
        byte[] datas = new byte[str.length];
        for (int i = 0; i < str.length; i++) {
            datas[i] = (byte) (str[i] & 0xFF);
        }
        int index = name.lastIndexOf(".") == 0 ? name.length() : name.lastIndexOf(".") + 1;
        uploadFile.setName(name.substring(0, index - 1));
        uploadFile.setExtensionStr(name.substring(index));
        editResourceService.uploadFile(id, uploadFile, datas, new AsyncCallback<Long>() {

            @Override
            public void onFailure(Throwable caught) {
                GWT.log(caught.getMessage());
            }

            @Override
            public void onSuccess(Long result) {
                if (result.equals(-1L)) {
                    isAlreadyExist = true;
                    showUploadFinishMessage();
                    return;
                }
                uploadFile.setId(result);
                showUploadFinishMessage();

                fileTreeNodeFactory.getFileTreeNode(tree, id, uploadFile);
                if (treeGrid.anySelected())
                    treeGrid.deselectAllRecords();
                fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                treeGrid.sort();
                treeGrid.redraw();
                treeGrid.deselectAllRecords();
                treeGrid.selectRecord(fileTreeNodeFactory.findTreeNode(tree, uploadFile.getId()));
            }
        });
    }

    /**
     * The directory or file rename window is displayed.
     */
    private void renameFileWithExtension() {
        TreeNode node = treeGrid.getSelectedRecord();
        VMResource resource = ((FileTreeNode) node).getResource();
        String resourceFullName;
        if (resource instanceof VMFile) {
            String resourceExtension = ((VMFile) resource).getExtensionStr();
            resourceFullName = resource.getName() + "." + resourceExtension;
        } else {
            resourceFullName = resource.getName();
        }

        final Window winModal = new Window();
        winModal.setHeight(100);
        winModal.setWidth(310);
        winModal.setTitle("Rename Resource");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.setKeepInParentRect(true);
        winModal.setAutoCenter(true);
        winModal.addCloseClickHandler(e -> winModal.markForDestroy());

        DynamicForm form = new DynamicForm();
        form.setHeight100();
        form.setWidth100();
        form.setPadding(5);
        form.setNumCols(5);
        form.setColWidths(70, 70, 5, 70, 70);
        form.setLayoutAlign(VerticalAlignment.BOTTOM);
        form.setAutoFocus(true);

        TextItem renameText = new TextItem();
        renameText.setTitle("New Name");
        renameText.setTitleColSpan(1);
        renameText.setColSpan(4);
        renameText.setValue(resourceFullName);

        ButtonItem okButton = new ButtonItem();
        okButton.setTitle("OK");
        okButton.setColSpan(2);
        okButton.setWidth(80);
        okButton.setAlign(Alignment.RIGHT);
        okButton.setStartRow(false);
        okButton.setEndRow(false);
        okButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                String inputValue = renameText.getValueAsString();

                if (inputValue == null || inputValue.isEmpty()) {
                    // ""を入力する際に、もう一度入力を要求する
                    SC.warn("Please enter the new name.");
                    return;
                } else {
                    String newFileFullName = inputValue.trim();
                    TreeNode selectedNode = treeGrid.getSelectedRecord();
                    TreeNode parentNode = tree.getParent(selectedNode);
                    TreeNode[] nodes = tree.getChildren(parentNode);
                    ArrayList<TreeNode> nodeList = new ArrayList<TreeNode>();
                    for (TreeNode treeNode : nodes) {
                        if (!treeNode.equals(node)) {
                            nodeList.add(treeNode);
                        }
                    }
                    VMResource resource = fileTreeNodeFactory.getResource(selectedNode);

                    if (resource instanceof VMFile) {
                        // check the inputed extension if it is correct
                        if ((newFileFullName.lastIndexOf(".") == -1) || newFileFullName.substring(newFileFullName.lastIndexOf(".") + 1).isEmpty()) {
                            winModal.markForDestroy();
                            renameFileWithExtension();
                            SC.warn("There is no Extension with your file! Please write a correct file name.");
                            return;
                        } else if (!isAbailableExtension(newFileFullName.substring(newFileFullName.lastIndexOf(".") + 1))) {
                            winModal.markForDestroy();
                            renameFileWithExtension();
                            SC.warn("The Extension you inputed is not available! Please write a correct file name.");
                            return;
                        } else if (newFileFullName.substring(0, newFileFullName.lastIndexOf(".")).isEmpty()) {
                            winModal.markForDestroy();
                            renameFileWithExtension();
                            SC.warn("Please enter the new name.");
                            return;
                        }
                    }
                    int index = newFileFullName.lastIndexOf(".");
                    String newBaseName = newFileFullName.substring(0, newFileFullName.lastIndexOf("."));
                    String newExtension = newFileFullName.substring(newFileFullName.lastIndexOf(".") + 1);
                    if (!checkSameName(nodeList.toArray(new TreeNode[nodeList.size()]), index != -1 ? newBaseName : newFileFullName, resource instanceof VMDirectory,
                            resource instanceof VMFile ? newExtension : "")) {
                        winModal.markForDestroy();
                        renameFileWithExtension();
                        SC.warn("An item with this name has been already existed. Please change a new name and try again.");
                        return;
                    }

                    ZGRenameCommand renameCommand = new ZGRenameCommand(editResourceService, viewHandler, resource.getId(), newFileFullName, resource.getName());
                    renameCommand.addCommandListener(new CommandListener() {

                        @Override
                        public void executeEvent() {
                            renameTreeView(renameCommand.getNewName(), selectedNode, parentNode);
                        }

                        @Override
                        public void undoEvent() {
                            renameTreeView(renameCommand.getOldName(), selectedNode, parentNode);
                        }

                        @Override
                        public void redoEvent() {
                            renameTreeView(renameCommand.getNewName(), selectedNode, parentNode);
                        }

                        @Override
                        public void bindEvent() {
                            viewHandler.clear();
                            registerRightClickEvent();
                        }
                    });
                    CompoundCommand c = new CompoundCommand();
                    c.append(renameCommand);
                    manager.execute(c.unwrap());
                }
                winModal.markForDestroy();
            }
        });
        ButtonItem canselButton = new ButtonItem();
        canselButton.setTitle("Cancel");
        canselButton.setColSpan(2);
        canselButton.setWidth(80);
        canselButton.setStartRow(false);
        canselButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                winModal.markForDestroy();
            }
        });
        SpacerItem space = new SpacerItem();
        space.setWidth(5);
        form.setFields(renameText, okButton, space, canselButton);
        form.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.ENTER.equals(event.getKeyName())) {
                    okButton.fireEvent(new com.smartgwt.client.widgets.form.fields.events.ClickEvent(okButton.getJsObj()));
                }
            }
        });
        winModal.addItem(form);
        winModal.show();
        renameText.setSelectionRange(0, renameText.getValueAsString().indexOf("."));
    }

    /**
     * Checks if the specified file extension is available.
     * @param value specified file extension
     * @return If True, it is available.
     */
    private boolean isAbailableExtension(String value) {
        for (Extension extension : Extension.values()) {
            if (extension.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {

        RootPanel rootPanel = RootPanel.get();
        rootPanelHandler = rootPanel.addDomHandler(new DragOverHandler() {
            @Override
            public void onDragOver(DragOverEvent event) {
                if (!"yellow".equals(leftLayout.getBackgroundColor())) {
                    leftLayout.setBackgroundColor("yellow");
                }
            }
        }, DragOverEvent.getType());
        rootPanelHandler = rootPanel.addDomHandler(new DragLeaveHandler() {
            @Override
            public void onDragLeave(DragLeaveEvent event) {
                if ("yellow".equals(leftLayout.getBackgroundColor())) {
                    leftLayout.setBackgroundColor("white");
                }
            }
        }, DragLeaveEvent.getType());
        rootPanelHandler = rootPanel.addDomHandler(new DropHandler() {
            @Override
            public void onDrop(DropEvent event) {
                event.stopPropagation();
                event.preventDefault();
                loopDir = new ArrayList<String>();
                nativeUploadData(event.getDataTransfer(), ModelingProjectView.this, rootId);
                treeGrid.sort();
                treeGrid.redraw();
            }
        }, DropEvent.getType());

        innerPane.addKeyDownHandler(new KeyDownHandler() {

            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.isCtrlKeyDown() && EventHandler.getKey().equalsIgnoreCase("Z")) {
                    manager.undo();
                } else if (event.isCtrlKeyDown() && EventHandler.getKey().equalsIgnoreCase("Y")) {
                    manager.redo();
                } else if (event.isCtrlKeyDown() && EventHandler.getKey().equalsIgnoreCase("C")) {
                    copy();
                } else if (event.isCtrlKeyDown() && EventHandler.getKey().equalsIgnoreCase("V")) {
                    paste();
                } else if (event.isCtrlKeyDown() && EventHandler.getKey().equalsIgnoreCase("F")) {
                    event.cancel();
                    searchMenuItem.fireEvent(new MenuItemClickEvent(searchMenuItem.getJsObj()));
                } else if (event.isCtrlKeyDown() && EventHandler.shiftKeyDown() && EventHandler.getKey().equalsIgnoreCase("R")) {
                    event.cancel();
                    searchFileMenuItem.fireEvent(new MenuItemClickEvent(searchFileMenuItem.getJsObj()));
                }
            }
        });

        treeGrid.addFolderOpenedHandler(new FolderOpenedHandler() {
            @Override
            public void onFolderOpened(FolderOpenedEvent event) {
                folderOpendAction(event.getNode(), tree, fileTreeNodeFactory);
            }

        });

        if (editorLayout != null) {
            treeGrid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
                @Override
                public void onCellDoubleClick(CellDoubleClickEvent event) {
                    FileTreeNode selectedNode = (FileTreeNode) treeGrid.getSelectedRecord();
                    VMResource resource = selectedNode.getResource();
                    if (resource instanceof VMDirectory) {
                        return;
                    }

                    EnumSet<Extension> extensions = EnumSet.allOf(Extension.class);
                    if (!extensions.contains(((VMFile) resource).getExtension()) && editExtensions != null && !editExtensions.isEmpty()
                            && !editExtensions.contains(((VMFile) resource).getExtensionStr())) {
                        downloadFile(new ArrayList<VMFile>(Arrays.asList(((VMFile) resource))));
                        return;
                    }
                    openEditor(selectedNode, resource, null);
                }
            });
        }

        treeGrid.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.getKeyName().equalsIgnoreCase("f2")) {
                    renameFileWithExtension();
                }
            }
        });

        openTextMenuItem.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                FileTreeNode selectedNode = (FileTreeNode) treeGrid.getSelectedRecord();
                VMResource resource = selectedNode.getResource();
                if (resource instanceof VMDirectory) {
                    return;
                }
                openEditor(selectedNode, resource, null);
            }
        });

        treeGrid.addDropOutHandler(new DropOutHandler() {

            @Override
            public void onDropOut(DropOutEvent event) {
                TreeNode dropTarget = treeGrid.getDropFolder();
                folderOpendAction(dropTarget, tree, fileTreeNodeFactory);
            }
        });

        treeGrid.addDropCompleteHandler(new DropCompleteHandler() {
            @Override
            public void onDropComplete(DropCompleteEvent event) {
                dropCompleteMoveTreeNodeAction();
            }
        });

        subMenuList.forEach(item -> {
            item.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(MenuItemClickEvent event) {
                    clickCreateDirOrFile(item);
                }
            });
        });

        newFPSItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                clickCreateDirOrFile(newFPSItem);
            }
        });
        newFPItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                clickCreateDirOrFile(newFPItem, null, getFileIds(treeGrid.getSelectedRecords()));
            }
        });
        newTCItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                clickCreateDirOrFile(newTCItem);
            }
        });
        newFMCItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                clickCreateDirOrFile(newFMCItem);
            }
        });
        newBPSItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                clickCreateDirOrFile(newBPSItem);
            }
        });
        newBPItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                clickCreateDirOrFile(newBPItem, null, getFileIds(treeGrid.getSelectedRecords()));
            }
        });
        newSCSItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                clickCreateDirOrFile(newSCSItem, null, getFileIds(treeGrid.getSelectedRecords()));
            }
        });
        newSPQLItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                clickCreateDirOrFile(newSPQLItem);
            }
        });

        settingMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", getParam().get("projectId"));
                map.put("name", projectName);
                map.put("rootId", getParam().get("id"));
                map.put("setting", "Settings");
                FrameManager.getInstance().transitionTo(ViewDefine.PROJECT, map);
            }
        });

        deleteMenu.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                clickDelete();
            }
        });

        renameMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                renameFileWithExtension();
            }
        });

        historyMenuItem.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                FileTreeNode selectedNode = (FileTreeNode) treeGrid.getSelectedRecord();
                VMResource resource = selectedNode.getResource();
                if (resource instanceof VMDirectory) {
                    return;
                }

                EnumSet<Extension> extensions = EnumSet.allOf(Extension.class);
                if (!extensions.contains(((VMFile) resource).getExtension())) {
                    downloadFile(new ArrayList<VMFile>(Arrays.asList(((VMFile) resource))));
                    return;
                }
                String tabId = selectedNode.getAttribute("UniqueId");
                for (Tab tab : editorTabSet.getTabs()) {
                    long ref = tab.getAttributeAsLong("ref");
                    if (resource.getId() == ref) {
                        tabId = tab.getAttributeAsString("UniqueId");
                        if (!tabId.contains("_historyEditor")) {
                            tabId = tabId.concat("_historyEditor");
                        }
                        selectedNode.setAttribute("UniqueId", tabId);
                        selectedNode.setResource(resource);
                        break;
                    }
                }
                if (null == tabId || "".equals(tabId)) {
                    tabId = HTMLPanel.createUniqueId().replaceAll("-", "_");
                    tabId = tabId.concat("_historyEditor");

                    selectedNode.setAttribute("UniqueId", tabId);
                    selectedNode.setResource(resource);
                }

                String tabName = resource.getName();
                Tab tab = editorTabSet.getTab(tabId);
                if (tab == null) {
                    tab = new Tab();
                    tab.setTitle(getHistoryTabImgTitle(tabName));
                    tab.setCanClose(true);
                    tab.setID(tabId);
                    tab.setAttribute("ref", resource.getId());
                    tab.setAttribute("UniqueId", tabId);
                    tab.setAttribute("Extension", ((VMFile) resource).getExtension().getValue());
                    tab.setAttribute("TabName", tabName);
                    editorTabSet.addTab(tab);
                    FileHistoryEditor fileHistoryEditor = new FileHistoryEditor(((VMFile) resource).getId(), editorTabSet, tab);
                    fileHistoryEditor.create();
                    tab.setPane(fileHistoryEditor.getLayout());
                    tab.setAttribute("FileHistoryEditor", fileHistoryEditor);
                }
                editorTabSet.selectTab(tabId);
            }

        });

        copyMenuItem.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                copy();
            }
        });
        pasteMenuItem.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                paste();
            }
        });
        downloadMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                List<VMFile> dlFiles = new ArrayList<VMFile>();

                List<VMDirectory> dlDirs = new ArrayList<VMDirectory>();
                for (ListGridRecord record : treeGrid.getSelectedRecords()) {
                    VMResource resource = ((FileTreeNode) record).getResource();
                    if (resource instanceof VMDirectory) {
                        dlDirs.add((VMDirectory) resource);
                    } else {
                        dlFiles.add((VMFile) resource);
                    }
                }
                download(dlDirs, dlFiles);
                return;
            }
        });

        searchMenuItem.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                search.showWindow();
            }

        });

        exportMenuItem.addClickHandler(new ClickHandler() {
            private String fileTypeValue;

            private String titleValue;

            private String patternValue;

            private String pathValue;

            private String showValue;

            @Override
            public void onClick(MenuItemClickEvent event) {
                fileTypeValue = ExportUtil.FileType.CSV.getValue();
                titleValue = ExportUtil.Title.SHOW.getValue();
                patternValue = ExportUtil.Pattern.DEPENDS_ON_PATTERN_FILE.getValue();
                pathValue = ExportUtil.Path.DEPENDS_ON_SETTING_FILE.getValue();
                showValue = ExportUtil.Show.DEPENDS_ON_SETTING_FILE.getValue();

                final Window winModal = new Window();
                winModal.setTitle("Export");
                winModal.setShowMinimizeButton(false);
                winModal.setIsModal(true);
                winModal.setShowModalMask(true);
                winModal.setShowFooter(true);
                winModal.setKeepInParentRect(true);
                winModal.setAutoCenter(true);
                winModal.setBackgroundColor("white");
                winModal.setWidth(400);
                winModal.setHeight(250);

                VLayout formlayout = new VLayout();
                formlayout.setWidth100();
                formlayout.setHeight100();
                formlayout.setLeft(15);

                DynamicForm exportViewForm = new DynamicForm();
                exportViewForm.setHeight100();
                exportViewForm.setWidth100();
                exportViewForm.setPadding(5);
                exportViewForm.setMargin(5);
                exportViewForm.setNumCols(5);
                exportViewForm.setColWidths(50, "*", "*", "*", "*");
                exportViewForm.setLayoutAlign(VerticalAlignment.BOTTOM);
                exportViewForm.setAutoFocus(true);

                RadioGroupItem fileTypeRGItem = new RadioGroupItem();
                fileTypeRGItem.setTitle("FileType");
                fileTypeRGItem.setValueMap(ExportUtil.FileType.getValues());
                fileTypeRGItem.setVertical(false);
                fileTypeRGItem.setColSpan("*");
                fileTypeRGItem.setDefaultValue(fileTypeValue);
                fileTypeRGItem.addChangedHandler(e -> fileTypeValue = (String) e.getValue());

                RadioGroupItem titleRGItem = new RadioGroupItem();
                titleRGItem.setTitle("Title");
                titleRGItem.setVertical(false);
                titleRGItem.setValueMap(ExportUtil.Title.getValues());
                titleRGItem.setColSpan("*");
                titleRGItem.setDefaultValue(titleValue);
                titleRGItem.addChangedHandler(e -> titleValue = (String) e.getValue());

                RadioGroupItem patternRGItem = new RadioGroupItem();
                patternRGItem.setTitle("Pattern");
                patternRGItem.setVertical(false);
                patternRGItem.setValueMap(ExportUtil.Pattern.getValues());
                patternRGItem.setDefaultValue(patternValue);
                patternRGItem.setColSpan("*");
                patternRGItem.addChangedHandler(e -> patternValue = (String) e.getValue());

                RadioGroupItem pathRGItem = new RadioGroupItem();
                pathRGItem.setTitle("Path");
                pathRGItem.setVertical(false);
                pathRGItem.setValueMap(ExportUtil.Path.getValues());
                pathRGItem.setDefaultValue(pathValue);
                pathRGItem.setColSpan("*");
                pathRGItem.addChangedHandler(e -> pathValue = (String) e.getValue());

                RadioGroupItem showRGItem = new RadioGroupItem();
                showRGItem.setTitle("Show");
                showRGItem.setVertical(false);
                showRGItem.setValueMap(ExportUtil.Show.getValues());
                showRGItem.setDefaultValue(showValue);
                showRGItem.setColSpan("*");
                showRGItem.addChangedHandler(e -> showValue = (String) e.getValue());

                exportViewForm.setItems(fileTypeRGItem, titleRGItem, patternRGItem, pathRGItem, showRGItem);

                IButton okButton = new IButton("OK");
                okButton.setHeight100();
                okButton.setWidth100();
                okButton.setMargin(5);
                okButton.addClickHandler(e -> {
                    List<VMFile> exportFilesList = new ArrayList<VMFile>();
                    for (ListGridRecord record : treeGrid.getSelectedRecords()) {
                        VMResource resource = ((FileTreeNode) record).getResource();
                        exportFilesList.add((VMFile) resource);
                    }
                    String fIds = exportFilesList.stream().map(f -> ((Long) f.getId()).toString()).collect(Collectors.joining(","));
                    if (!fIds.isEmpty()) {
                        String exportFileName = exportFilesList.stream().findFirst().map(VMFile::getName).orElse(projectName);
                        String fileExportURL = GWT.getModuleBaseURL() + "export?name=" + exportFileName + "&ids=" + fIds + "&projectid=" + projectId + "&fileType=" + fileTypeValue
                                + "&title=" + titleValue + "&pattern=" + patternValue + "&path=" + pathValue + "&show=" + showValue;
                        com.google.gwt.user.client.Window.open(fileExportURL, "_self", "");
                        winModal.markForDestroy();
                    }
                    return;
                });

                IButton cancelButton = new IButton("Cancel");
                cancelButton.setHeight100();
                cancelButton.setWidth100();
                cancelButton.setMargin(5);
                cancelButton.addClickHandler(e -> winModal.markForDestroy());

                LayoutSpacer hspacer = new LayoutSpacer(3, "100%");
                hspacer.setBackgroundColor("white");

                HLayout footer = new HLayout();
                footer.setHeight(30);
                footer.setWidth100();
                footer.setLayoutLeftMargin(5);
                footer.addMembers(hspacer, okButton, hspacer, cancelButton, hspacer);

                formlayout.addMembers(exportViewForm);
                winModal.addMember(formlayout);
                winModal.show();
                winModal.getFooter().addMember(footer, 0);

                winModal.addResizedHandler(e -> formlayout.setWidth100());
                winModal.addCloseClickHandler(e -> winModal.markForDestroy());
            }
        });

        // Click Respones When Left Click the Check Progerss
        progMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                progDialog.showWindow(progressCheckService, projectId);
            }
        });

        searchFileMenuItem.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                searchFile.showWindow();
            }
        });

        correlationDiagramMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                if (fileCorrelationDiagramWindow == null || !fileCorrelationDiagramWindow.isCreated()) {
                    fileCorrelationDiagramWindow = new FileCorrelationDiagramWindowViewPart(editResourceService, projectId, ModelingProjectView.this);
                }
            }
        });

        copyPathMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                /*
                 * クリップボードにコピーする際には一旦TextBoxに貼り付けてそれを選択状態にしなければいけません。
                 * TextBoxをSmartGWTに追加するためにはLayoutにaddできないため、DynmicFormを使用する必要があります。
                 */
                DynamicForm tmpForm = new DynamicForm();
                try {
                    String path = "/" + projectName + tree.getPath(treeGrid.getSelectedRecord());
                    TextItem copyItem = new TextItem();
                    tmpForm.setItems(copyItem);
                    tmpForm.setPosition(Positioning.ABSOLUTE);
                    tmpForm.moveTo(-1000, -1000);
                    tmpForm.draw();
                    copyItem.setValue(path);
                    copyItem.setSelectionRange(0, path.length());
                    JSDocument.execCommand("copy");
                } catch (Exception e) {
                    GWT.log(e.getMessage());
                } finally {
                    tmpForm.markForDestroy();
                    tmpForm = null;
                }

            }
        });

        registerRightClickEvent();

        editorTabSet.addTabDeselectedHandler(new TabDeselectedHandler() {

            @Override
            public void onTabDeselected(TabDeselectedEvent event) {
                tabRegs.get(event.getTab().getID()).removeHandler();
            }
        });

        editorTabSet.addTabSelectedHandler(new TabSelectedHandler() {

            @Override
            public void onTabSelected(TabSelectedEvent event) {
                final Tab tab = event.getTab();

                if (tab.getAttributeAsObject("FileHistoryEditor") instanceof FileHistoryEditor) {
                    FileHistoryEditor fileHistoryEditor = (FileHistoryEditor) tab.getAttributeAsObject("FileHistoryEditor");
                    fileHistoryEditor.getHistoryList();
                }

                tabRegs.put(tab.getID(), Event.addNativePreviewHandler(new NativePreviewHandler() {

                    @Override
                    public void onPreviewNativeEvent(NativePreviewEvent event) {
                        setDirtyFlagToTabTitle(tab);
                    }
                }));
            }
        });

        editorTabSet.addCloseClickHandler(new CloseClickHandler() {
            @Override
            public void onCloseClick(TabCloseClickEvent event) {
                event.cancel();
                final Tab tab = event.getTab();
                tabCloseEvent(tab);
            }
        });

        editorLayout.addClearHandler(new ClearHandler() {

            @Override
            public void onClear(ClearEvent event) {
                tabRegs.values().forEach(reg -> reg.removeHandler());
            }
        });

        editorTabSet.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (event.isCtrlKeyDown() && event.getKeyName().equals("S")) {
                    event.cancel();
                    MenuItem[] menuItems = editorTabSet.getSelectedTab().getContextMenu().getItems();
                    for (MenuItem item : menuItems) {
                        if (item.getTitle().equals("Save")) {
                            item.fireEvent(new MenuItemClickEvent(item.getJsObj()));
                            return;
                        }
                    }
                }
            }
        });

        treeGrid.addDragStopHandler(new DragStopHandler() {

            @Override
            public void onDragStop(DragStopEvent event) {
                ARCEditor editor = null;
                if (editorTabSet.getSelectedTab() != null && editorTabSet.getSelectedTab().getAttributeAsObject(EDITOR) instanceof ARCEditor) {
                    editor = (ARCEditor) editorTabSet.getSelectedTab().getAttributeAsObject(EDITOR);
                } else {
                    return;
                }
                if (editorTabSet.containsPoint(event.getX(), event.getY()) && editor != null) {
                    int startX = event.getX() - editorTabSet.getAbsoluteLeft();
                    int startY = event.getY() - editorTabSet.getAbsoluteTop() - editorTabSet.getTabBar().getAbsoluteTop();
                    ListGridRecord[] records = treeGrid.getSelectedRecords();
                    startX = startX < 0 ? 0 : startX;
                    startY = startY < 0 ? 0 : startY;
                    List<String> uuids = new ArrayList<String>();
                    for (ListGridRecord record : records) {
                        FileTreeNode node = (FileTreeNode) record;
                        if (node.getResource() instanceof VMDirectory) {
                            continue;
                        }
                        VMFile resource = ((VMFile) node.getResource());
                        if (Extension.FSM.getValue().equals(resource.getExtensionStr())) {
                            uuids.add(resource.getUuid());
                        }
                    }
                    if (!uuids.isEmpty()) {
                        editor.getARCStateManager().addARCStates(startX, startY, uuids);
                    }
                }
            }
        });
    }

    /**
     * The process of closing the specified tab is performed.
     * @param tab specified tab
     */
    private void tabCloseEvent(Tab tab) {
        final String tabName = tab.getAttributeAsString("TabName");
        final Editor editor = (Editor) tab.getAttributeAsObject(EDITOR);

        if (editor != null && editor.isChanged()) {
            final Dialog dialog = new Dialog();
            dialog.setIsModal(true);
            dialog.setShowModalMask(true);
            dialog.setCanDrag(true);
            dialog.setCanDragReposition(true);
            dialog.setTitle("Save File");
            dialog.setMessage("Save changes in '" + tabName + "'?");
            dialog.setIcon("[SKIN]ask.png");
            dialog.addCloseClickHandler(e -> dialog.markForDestroy());
            Button dialogSave = new Button("Save");
            Button dialogDontSave = new Button("Don't Save");
            Button dialogCancel = new Button("Cancel");
            dialogSave.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
                @Override
                public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                    editor.save(ModelingProjectView.this, editor, tab, new PostProcessHandler() {
                        @Override
                        public void execute() {
                            editorTabSet.selectTab(tab);
                            tab.getTabSet().removeTab(tab);
                            tabRegs.get(tab.getID()).removeHandler();
                        }
                    });
                    dialog.markForDestroy();
                }
            });
            dialogDontSave.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
                @Override
                public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                    editorTabSet.selectTab(tab);
                    tab.getTabSet().removeTab(tab);
                    tabRegs.get(tab.getID()).removeHandler();
                    dialog.markForDestroy();
                }
            });
            dialogCancel.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
                @Override
                public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                    dialog.markForDestroy();
                }
            });
            dialog.setButtons(dialogSave, dialogDontSave, dialogCancel);
            dialog.draw();
        } else {
            tab.getTabSet().removeTab(tab);
            tabRegs.get(tab.getID()).removeHandler();
        }
    }

    /**
     * The directory or file name will be changed to the specified name.
     * @param value new name
     * @param selectedNode TreeNode of the selected file
     * @param parentNode TreeNode in the parent directory
     */
    private void renameTreeView(String value, TreeNode selectedNode, TreeNode parentNode) {
        VMResource resource = fileTreeNodeFactory.getResource(selectedNode);
        String tabId = selectedNode.getAttribute("UniqueId");
        if (resource instanceof VMFile) {
            int index = value.lastIndexOf(".");
            if (index != -1) {
                String newBaseName = value.substring(0, index);
                String newExtension = value.substring(index + 1);
                resource.setName(newBaseName);
                ((VMFile) resource).setExtension(Extension.getByCode(newExtension));
                selectedNode.setIcon(((VMFile) resource).getExtension().getImgPath());
            } else {
                SC.warn("Extension is lost!");
                return;
            }
        } else {
            resource.setName(value);
        }
        TreeNode treeNode;
        if ("/".equals(tree.getPath(parentNode))) {
            treeNode = fileTreeNodeFactory.getFileTreeNode(tree, rootId, resource);
        } else {
            treeNode = fileTreeNodeFactory.getFileTreeNode(tree, ((FileTreeNode) parentNode).getResource().getId(), resource);
        }
        if (tabId != null) {
            treeNode.setAttribute("UniqueId", tabId);
            if (editorTabSet.getTab(tabId) != null) {
                editorTabSet.getTab(tabId).setTitle(getTabImgTitle(value, ((VMFile) resource).getExtension()));
                editorTabSet.getTab(tabId).setAttribute("ref", resource.getId());
                editorTabSet.getTab(tabId).setAttribute("TabName", value);
            }
        }
        fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
        treeGrid.sort();
        treeGrid.redraw();
    }

    /**
     * Called by dragging and dropping a TreeNode (directory or file). The TreeNode will move to the dropped position.
     */
    protected void dropCompleteMoveTreeNodeAction() {
        List<Map<Long, VMResource>> targets = new ArrayList<Map<Long, VMResource>>();
        List<Map<Long, VMResource>> oldTargets = new ArrayList<Map<Long, VMResource>>();
        Arrays.asList(treeGrid.getSelectedRecords()).forEach(x -> {
            Map<Long, VMResource> target = new HashMap<Long, VMResource>();
            Map<Long, VMResource> oldTarget = new HashMap<Long, VMResource>();
            FileTreeNode targetNode = (FileTreeNode) x;
            Long parentId = tree.getRoot().equals(tree.getParent((TreeNode) x)) ? rootId : ((FileTreeNode) tree.getParent((TreeNode) x)).getResource().getId();
            target.put(parentId, targetNode.getResource());
            targets.add(target);
            oldTarget.put(fileTreeNodeFactory.getParentDirId(targetNode.getResource().getId()), targetNode.getResource());
            oldTargets.add(oldTarget);
            fileTreeNodeFactory.moveVMResource(parentId, targetNode.getResource().getId());
        });
        ZGMoveCommand moveCommand = new ZGMoveCommand(editResourceService, viewHandler, targets, oldTargets, fileTreeNodeFactory);
        moveCommand.addCommandListener(new CommandListener() {
            @Override
            public void executeEvent() {
                fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                treeGrid.sort();
                treeGrid.redraw();
            }

            @Override
            public void undoEvent() {
                fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                for (Map<Long, VMResource> oldTarget : moveCommand.getOldTargets()) {
                    for (Entry<Long, VMResource> entry : oldTarget.entrySet()) {
                        tree.move(fileTreeNodeFactory.findTreeNode(tree, entry.getValue().getId()), fileTreeNodeFactory.findTreeNode(tree, entry.getKey()));
                    }
                }
                treeGrid.sort();
                treeGrid.redraw();
            }

            @Override
            public void redoEvent() {
                fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                for (Map<Long, VMResource> target : moveCommand.getTargets()) {
                    for (Entry<Long, VMResource> entry : target.entrySet()) {
                        tree.move(fileTreeNodeFactory.findTreeNode(tree, entry.getValue().getId()), fileTreeNodeFactory.findTreeNode(tree, entry.getKey()));
                    }
                }
                treeGrid.sort();
                treeGrid.redraw();
            }

            @Override
            public void bindEvent() {
                viewHandler.clear();
                registerRightClickEvent();
            }
        });

        CompoundCommand c = new CompoundCommand();
        c.append(moveCommand);
        manager.execute(c.unwrap());
    }

    /**
     * Register the right-click event in {@link #viewHandler}.
     */
    private void registerRightClickEvent() {
        viewHandler.add(registerRightMouseDownHandler());
        viewHandler.add(registerCellContextClickHandler());
        viewHandler.add(registerShowContextMenuHandler());
    }

    /**
     * A right-click event handler for {@link #innerPane} is created.
     * @return Show Context Menu Handler
     */
    private HandlerRegistration registerShowContextMenuHandler() {
        return innerPane.addShowContextMenuHandler(new ShowContextMenuHandler() {
            @Override
            public void onShowContextMenu(ShowContextMenuEvent event) {
                event.cancel();
                treeGrid.deselectAllRecords();
                setMenu();
                mainMenu.moveTo(event.getX(), event.getY());
                mainMenu.show();
            }
        });
    }

    /**
     * A right-click event handler for {@link #treeGrid} is created.
     * @return Cell Context Click Handler
     */
    private HandlerRegistration registerCellContextClickHandler() {
        return treeGrid.addCellContextClickHandler(new CellContextClickHandler() {
            @Override
            public void onCellContextClick(CellContextClickEvent event) {
                setMenu();
                mainMenu.moveTo(event.getX(), event.getY());
                mainMenu.show();
            }
        });
    }

    /**
     * A right-click event handler for {@link #innerPane} is created.
     * @return Right Mouse Down Handler
     */
    private HandlerRegistration registerRightMouseDownHandler() {
        return innerPane.addRightMouseDownHandler(new RightMouseDownHandler() {
            @Override
            public void onRightMouseDown(RightMouseDownEvent event) {
                setMenu();
                mainMenu.moveTo(event.getX(), event.getY());
                mainMenu.show();
            }
        });
    }

    /**
     * Executed when the project explorer folder is expanded. <br>
     * Get the information of the file or folder belonging to the expanded folder from the database and reflect it in the
     * project explorer.
     * @param target Folder to extract
     * @param tree Tree information associated with {@link #treeGrid}
     * @param fileTreeNodeFactory A class that summarizes the processing related to TreeNode of the file displayed in Project
     *            Explorer
     * @param afterOpenFolderHandler Processing executed after folder expansion
     */
    public void folderOpendAction(TreeNode target, Tree tree, FileTreeNodeFactory fileTreeNodeFactory, PostProcessHandler afterOpenFolderHandler) {
        final long parentId = tree.getRoot().equals(target) ? rootId : ((FileTreeNode) target).getResource().getId();
        editResourceService.getResources(parentId, new AsyncCallback<List<VMResource>>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(List<VMResource> result) {
                if (!tree.hasChildren(target)) {
                    tree.addList(fileTreeNodeFactory.getFileTreeNodes(parentId, result), target);
                    fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                    treeGrid.sort();
                    treeGrid.redraw();
                    for (TreeNode treeNode : tree.getChildren(fileTreeNodeFactory.findTreeNode(tree, parentId))) {
                        FileTreeNode fileTreeNode = (FileTreeNode) treeNode;
                        VMResource resource = fileTreeNode.getResource();
                        for (Tab tab : editorTabSet.getTabs()) {
                            long ref = tab.getAttributeAsLong("ref");
                            if (resource.getId() == ref) {
                                String tabId = tab.getAttributeAsString("UniqueId");
                                fileTreeNode.setAttribute("UniqueId", tabId);
                                fileTreeNode.setResource(resource);
                                break;
                            }
                        }
                    }
                }
                // this time folder is opend.
                if (afterOpenFolderHandler != null) {
                    afterOpenFolderHandler.execute();
                }
            }
        });
    }

    /**
     * {@link #folderOpendAction(TreeNode, Tree, FileTreeNodeFactory, PostProcessHandler) folderOpendAction} is executed without
     * post-processing.
     * @param target Folder to extract
     * @param tree Tree information associated with {@link #treeGrid}
     * @param fileTreeNodeFactory A class that summarizes the processing related to TreeNode of the file displayed in Project
     *            Explorer
     */
    public void folderOpendAction(TreeNode target, Tree tree, FileTreeNodeFactory fileTreeNodeFactory) {
        folderOpendAction(target, tree, fileTreeNodeFactory, null);
    }

    /**
     * An editor is created on the specified tab and the contents of the specified file are displayed.
     * @param tab specified tab
     * @param file File information displayed in the editor
     * @param handler Post-processing after creating the editor
     */
    private void createView(Tab tab, VMFile file, PostProcessHandler handler) {
        editResourceService.getFileContent(file.getId(), new AsyncCallback<byte[]>() {

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(byte[] result) {
                BinaryResourceImpl r = new BinaryResourceImpl();
                ByteArrayInputStream bi = new ByteArrayInputStream(result);
                AbstractRootElement root = null;
                try {
                    if (file.getExtension() != Extension.TXT && file.getExtension() != Extension.LSC && file.getExtension() != Extension.CSC) {
                        r.load(bi, EditOptions.getDefaultLoadOptions());
                        root = (AbstractRootElement) r.getContents().get(0);
                    }
                } catch (IOException e) {
                    SC.warn(e.getMessage());
                }
                Editor editor;
                if (file.getExtension() == null) {
                    editor = new TextEditor(tab, file, editResourceService);
                    editor.create();
                } else
                    switch (file.getExtension()) {

                    case ARC:
                        editor = new ARCEditor((ARCRoot) root, projectId, editResourceService);
                        editor.create();
                        clickNewStateMachine((ARCEditor) editor, file);
                        clickOpenFile((ARCEditor) editor);
                        break;
                    case FM:
                        editor = new FMEditor((FMRoot) root, projectId, file.getId(), editResourceService);
                        editor.create();
                        clickOpenFile((FMEditor) editor);
                        clickCreateChildModel((FMEditor) editor, file);
                        setFileNameToReferenceNode((FMEditor) editor);
                        break;
                    case FMC:
                        editor = new FMCEditor((FMCRoot) root, file.getId(), projectId, editResourceService);
                        editor.create();
                        break;
                    case FSM:
                        editor = new FSMEditor((FSMDStateMachine) root, file.getId());
                        editor.create();
                        break;
                    case SCD:
                        editor = new SCDEditor((SCDRoot) root, tab, projectId, ModelingProjectView.this, editResourceService);
                        editor.create();
                        clickOpenFile((SCDEditor) editor);
                        break;
                    case SPQL:
                        editor = new SPQLEditor((SPQLRoot) root, tab, projectId, editResourceService);
                        editor.create();
                        clickOpenFile((SPQLEditor) editor);
                        break;
                    case TC:
                        editor = new TCEditor((TCRoot) root, projectId, file.getName(), editResourceService);
                        editor.create();
                        break;
                    case BPS:
                        editor = new BPSEditor((BPSRoot) root, ModelingProjectView.this, projectId, file.getId(), editResourceService);
                        editor.create();
                        break;
                    case BP:
                        editor = new BPEditor((BPRoot) root, projectId, file, editResourceService);
                        editor.create();
                        break;
                    case FPS:
                        editor = new TPSEditor((TPSRoot) root, ModelingProjectView.this, projectId, file.getId(), editResourceService);
                        editor.create();
                        break;
                    case FP:
                        editor = new TPViewer((TPRoot) root, projectId, file, editResourceService);
                        editor.create();
                        break;
                    case CSC:
                        editor = new CSCEditor(tab, file, editResourceService);
                        editor.create();
                        break;
                    case SCSS:
                        editor = new CBEditor((CBRoot) root, ModelingProjectView.this, projectId, file.getId(), editResourceService);
                        editor.create();
                        break;
                    case SCS:
                        editor = new SCSEditor((SCSRoot) root, ModelingProjectView.this, projectId, file, editResourceService);
                        editor.create();
                        break;
                    case CSCS:
                        editor = new CSCSEditor((CSCSRoot) root, projectId, file);
                        editor.create();
                        break;
                    default:
                        editor = new TextEditor(tab, file, editResourceService);
                        editor.create();
                    }
                createTabMenu(tab, editor.getSaveItem());
                clickSaveFile(editor, tab);

                if (editor instanceof NodeArrangeInterface) {
                    NodeArrange.add((NodeArrangeInterface) editor);
                }
                tab.setPane(editor.getLayout());
                tab.setAttribute(EDITOR, editor);
                editorTabSet.addTab(tab);
                editorTabSet.selectTab(tab.getAttributeAsString("UniqueId"));
                if (handler != null) {
                    handler.execute();
                }
            }
        });
    }

    /**
     * The project explorer context menu is set.
     */
    private void setMenu() {

        if (tree.isEmpty() || !treeGrid.anySelected()) {
            mainMenu.setItems(newMenuItem, new MenuItemSeparator(), settingMenuItem, searchMenuItem, correlationDiagramMenuItem, progMenuItem);
        } else if (treeGrid.getSelectedRecord() == null) {
            mainMenu.setItems(renameMenuItem, searchMenuItem, correlationDiagramMenuItem, progMenuItem);
        } else if (treeGrid.getSelectedRecords().length > 1) {
            ListGridRecord[] selectedNode = treeGrid.getSelectedRecords();
            if (isSameExtension(selectedNode, Extension.FP)) {
                mainMenu.setItems(deleteMenu, copyMenuItem, pasteMenuItem, downloadMenuItem, searchMenuItem, progMenuItem, exportMenuItem);
            } else if (isSameExtension(selectedNode, Extension.FPS)) {
                mainMenu.setItems(newFPItem, deleteMenu, copyMenuItem, pasteMenuItem, downloadMenuItem, searchMenuItem, progMenuItem);
            } else if (isSameExtension(selectedNode, Extension.BPS)) {
                mainMenu.setItems(newBPItem, deleteMenu, copyMenuItem, pasteMenuItem, downloadMenuItem, searchMenuItem, progMenuItem);
            } else if (isSameExtension(selectedNode, Extension.SCSS)) {
                mainMenu.setItems(newSCSItem, deleteMenu, copyMenuItem, pasteMenuItem, downloadMenuItem, searchMenuItem, progMenuItem);
            } else {
                mainMenu.setItems(deleteMenu, copyMenuItem, pasteMenuItem, downloadMenuItem, searchMenuItem, progMenuItem);
            }
        } else if (tree.isFolder(treeGrid.getSelectedRecord())) {
            mainMenu.setItems(newMenuItem, deleteMenu, renameMenuItem, copyMenuItem, pasteMenuItem, searchMenuItem, correlationDiagramMenuItem, progMenuItem, downloadMenuItem,
                    copyPathMenuItem);
        } else {
            FileTreeNode selectedNode = (FileTreeNode) treeGrid.getSelectedRecord();
            VMResource resource = selectedNode.getResource();
            if (resource instanceof VMDirectory) {
                return;
            }
            Extension extension = ((VMFile) resource).getExtension();
            if (extension == null) {
                mainMenu.setItems(openWithMenuItem, deleteMenu, renameMenuItem, historyMenuItem, copyMenuItem, pasteMenuItem, downloadMenuItem, searchMenuItem,
                        correlationDiagramMenuItem, copyPathMenuItem);
            } else
                switch (extension) {
                case ARC:
                    mainMenu.setItems(newBPSItem, deleteMenu, renameMenuItem, historyMenuItem, copyMenuItem, pasteMenuItem, downloadMenuItem, searchMenuItem,
                            correlationDiagramMenuItem, copyPathMenuItem);
                    break;
                case FM:
                    mainMenu.setItems(newFMCItem, newTCItem, newFPSItem, deleteMenu, renameMenuItem, historyMenuItem, copyMenuItem, pasteMenuItem, downloadMenuItem, searchMenuItem,
                            correlationDiagramMenuItem, copyPathMenuItem);
                    break;
                case FMC:
                    mainMenu.setItems(deleteMenu, renameMenuItem, historyMenuItem, downloadMenuItem, searchMenuItem, correlationDiagramMenuItem, copyPathMenuItem);
                    break;
                case TC:
                    mainMenu.setItems(newTCItem, newFPSItem, deleteMenu, renameMenuItem, historyMenuItem, downloadMenuItem, searchMenuItem, correlationDiagramMenuItem,
                            copyPathMenuItem);
                    break;
                case FPS:
                    mainMenu.setItems(newFPItem, deleteMenu, renameMenuItem, historyMenuItem, copyMenuItem, pasteMenuItem, downloadMenuItem, searchMenuItem,
                            correlationDiagramMenuItem, copyPathMenuItem);
                    break;
                case FP:
                    mainMenu.setItems(newSPQLItem, deleteMenu, renameMenuItem, historyMenuItem, copyMenuItem, pasteMenuItem, downloadMenuItem, searchMenuItem, exportMenuItem,
                            correlationDiagramMenuItem, copyPathMenuItem);
                    break;
                case BPS:
                    mainMenu.setItems(newBPItem, deleteMenu, renameMenuItem, historyMenuItem, copyMenuItem, pasteMenuItem, downloadMenuItem, searchMenuItem,
                            correlationDiagramMenuItem, copyPathMenuItem);
                    break;
                case BP:
                    mainMenu.setItems(newSPQLItem, deleteMenu, renameMenuItem, historyMenuItem, downloadMenuItem, searchMenuItem, correlationDiagramMenuItem, copyPathMenuItem);
                    break;
                case SCSS:
                    mainMenu.setItems(newSCSItem, deleteMenu, renameMenuItem, historyMenuItem, copyMenuItem, pasteMenuItem, downloadMenuItem, searchMenuItem,
                            correlationDiagramMenuItem, copyPathMenuItem);
                    break;
                case SCS:
                    mainMenu.setItems(newSPQLItem, deleteMenu, renameMenuItem, historyMenuItem, copyMenuItem, pasteMenuItem, downloadMenuItem, searchMenuItem,
                            correlationDiagramMenuItem, copyPathMenuItem);
                    break;
                default:
                    mainMenu.setItems(deleteMenu, renameMenuItem, historyMenuItem, copyMenuItem, pasteMenuItem, downloadMenuItem, searchMenuItem, correlationDiagramMenuItem,
                            copyPathMenuItem);
                    break;
                }
        }
    }

    /**
     * <pre>
     * Execute {@link #clickCreateDirOrFile(Extension, List)}.
     *  First argument: Extension of the specified file
     *  Second argument: null
     * </pre>
     * 
     * @param ext File extension
     */
    public void clickCreateDirOrFile(Extension ext) {
        clickCreateDirOrFile(ext, null);
    }

    /**
     * <pre>
     * Execute {@link #clickCreateDirOrFile(SubMenuItem, TreeNode, List)}.
     *  First argument: Submenu of the file to create
     *  Second argument: TreeNode associated with the reference file
     *  Third argument: List of reference files for the file to be created
     * </pre>
     * 
     * @param ext File extension
     * @param refIdList List of reference files
     */
    public void clickCreateDirOrFile(Extension ext, List<Long> refIdList) {
        SubMenuItem item = new SubMenuItem(ext);
        TreeNode node = null;
        if (refIdList != null) {
            for (long refId : refIdList) {
                node = fileTreeNodeFactory.findTreeNode(tree, refId);
                break;
            }
        }
        clickCreateDirOrFile(item, node, refIdList);
    }

    /**
     * <pre>
     * Execute {@link #clickCreateDirOrFile(SubMenuItem, TreeNode, List)}.
     *  First argument: Submenu of the file to create
     *  Second argument: null
     *  Third argument: null
     * </pre>
     * 
     * @param item Submenu of the file to create
     */
    private void clickCreateDirOrFile(SubMenuItem item) {
        clickCreateDirOrFile(item, null, null);
    }

    /**
     * A directory or the specified file will be created and reflected in Project Explorer.
     * @param item Submenu with information on the directory or file to be created
     * @param node TreeNode associated with the reference file
     * @param refIdList List of reference files for the file to be created
     */
    private void clickCreateDirOrFile(SubMenuItem item, TreeNode node, List<Long> refIdList) {
        String title = "New " + item.getFileName();
        String fileName = item.getFileName();
        String msg = "Please enter the " + fileName;
        if (!item.isDirectory()) {
            title += " file";
            msg += " file name (." + item.getExtension() + ")";
        } else {
            msg += " name";
        }
        SC.askforValue(title, msg, new ValueCallback() {
            @Override
            public void execute(String value) {
                TreeNode selectedNode = node;
                if (selectedNode == null) {
                    selectedNode = treeGrid.getSelectedRecord();
                }

                if (value != null && !"".equals(value)) {
                    VMDirectory targetDir = new VMDirectory();
                    VMFile file = new VMFile();
                    if (item.isDirectory) {
                        targetDir.setName(value);
                    } else {
                        file.setExtension(Extension.getByCode(item.getExtension()));
                        file.setName(value);
                    }
                    VMDirectory dir;
                    if (tree.isEmpty()) {
                        dir = new VMDirectory();
                        dir.setId(rootId);
                    } else if (!treeGrid.anySelected()) {
                        dir = new VMDirectory();
                        dir.setId(rootId);
                        if (!checkSameName(tree.getChildren(tree.find("/")), value, item.isDirectory, item.getExtension())) {
                            SC.warn("This item already contains a child item with that name.");
                            return;
                        }
                    } else if (((FileTreeNode) selectedNode).getResource() instanceof VMFile) {
                        TreeNode parentNode = tree.getParent(selectedNode);
                        if ("/".equals(tree.getPath(parentNode))) {
                            dir = new VMDirectory();
                            dir.setId(rootId);
                            if (!checkSameName(tree.getChildren(tree.find("/")), value, item.isDirectory, item.getExtension())) {
                                SC.warn("This item already contains a child item with that name.");
                                return;
                            }
                        } else {
                            dir = (VMDirectory) ((FileTreeNode) parentNode).getResource();
                            if (!checkSameName(tree.getChildren(parentNode), value, item.isDirectory, item.getExtension())) {
                                SC.warn("This item already contains a child item with that name.");
                                return;
                            }
                        }

                    } else {
                        dir = (VMDirectory) ((FileTreeNode) selectedNode).getResource();
                        if (!checkSameName(tree.getChildren(selectedNode), value, item.isDirectory, item.getExtension())) {
                            SC.warn("This item already contains a child item with that name.");
                            return;
                        }
                    }
                    if (item.isDirectory) {
                        ZGCreateDirCommand createDirCommand = new ZGCreateDirCommand(editResourceService, viewHandler, dir.getId(), targetDir);
                        createDirCommand.addCommandListener(new CommandListener() {

                            @Override
                            public void undoEvent() {
                                treeGrid.deselectAllRecords();
                                fileTreeNodeFactory.removeVMResource(createDirCommand.getFileId());
                                fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                                treeGrid.sort();
                                treeGrid.redraw();
                            }

                            @Override
                            public void redoEvent() {
                                createCallbackFile(fileTreeNodeFactory.findTreeNode(tree, createDirCommand.getParentId()), createDirCommand.getDirectory(),
                                        createDirCommand.getFileId(), true);
                            }

                            @Override
                            public void executeEvent() {
                                createCallbackFile(fileTreeNodeFactory.findTreeNode(tree, createDirCommand.getParentId()), createDirCommand.getDirectory(),
                                        createDirCommand.getFileId(), true);
                            }

                            @Override
                            public void bindEvent() {
                                viewHandler.clear();
                                registerRightClickEvent();
                            }

                        });

                        CompoundCommand c = new CompoundCommand();
                        c.append(createDirCommand);
                        manager.execute(c.unwrap());
                    } else {
                        List<Long> tmpRefIdlist = refIdList;
                        if (tmpRefIdlist == null && selectedNode != null) {
                            tmpRefIdlist = new ArrayList<Long>();
                            tmpRefIdlist.add(((FileTreeNode) selectedNode).getResource().getId());

                        }

                        ZGCreateFileCommand createFileCommand = new ZGCreateFileCommand(editResourceService, viewHandler, dir.getId(), file, tmpRefIdlist);
                        createFileCommand.addCommandListener(new CommandListener() {

                            @Override
                            public void undoEvent() {
                                treeGrid.deselectAllRecords();
                                fileTreeNodeFactory.removeVMResource(createFileCommand.getFileId());
                                fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                                treeGrid.sort();
                                treeGrid.redraw();
                            }

                            @Override
                            public void redoEvent() {
                                createCallbackFile(fileTreeNodeFactory.findTreeNode(tree, createFileCommand.getParentId()), createFileCommand.getFile(),
                                        createFileCommand.getFileId(), true);
                            }

                            @Override
                            public void executeEvent() {
                                createCallbackFile(fileTreeNodeFactory.findTreeNode(tree, createFileCommand.getParentId()), createFileCommand.getFile(),
                                        createFileCommand.getFileId(), true);
                            }

                            @Override
                            public void bindEvent() {
                                viewHandler.clear();
                                registerRightClickEvent();
                            }
                        });
                        CompoundCommand c = new CompoundCommand();
                        c.append(createFileCommand);
                        manager.execute(c.unwrap());
                    }
                    if (dir.getId() != rootId) {
                        tree.openFolder(treeGrid.getSelectedRecord());
                    }
                } else if (value != null) {
                    SC.warn("<br>Name field is empty");
                }

            }

        });
    }

    /**
     * A resource (directory or file) is added to the specified TreeNode and reflected in the project explorer.
     * @param selectedNode A file or directory is added within this TreeNode (directory).
     * @param resource Resources to add
     * @param result ID of the file to be reacquired from the database
     * @param windowOpen True to open the specified file
     */
    private void createCallbackFile(TreeNode selectedNode, VMResource resource, Long result, boolean windowOpen) {
        resource.setId(result);
        long parentId;
        if (selectedNode == null ? true : "/".equals(tree.getPath(selectedNode))) {
            fileTreeNodeFactory.getFileTreeNode(tree, rootId, resource);
            parentId = rootId;
        } else {
            parentId = ((FileTreeNode) selectedNode).getResource().getId();
            fileTreeNodeFactory.getFileTreeNode(tree, parentId, resource);
        }
        fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
        treeGrid.sort();
        treeGrid.redraw();
        treeGrid.deselectAllRecords();
        if (selectedNode != null && tree.isOpen(selectedNode) && resource instanceof VMFile) {
            treeGrid.selectRecord(fileTreeNodeFactory.findTreeNode(tree, resource.getId()));
            updateFileResource(result);
            if (windowOpen) {
                openEditor((FileTreeNode) fileTreeNodeFactory.findTreeNode(tree, resource.getId()), resource, new PostProcessHandler() {

                    @Override
                    public void execute() {
                        Editor editor = (Editor) editorTabSet.getSelectedTab().getAttributeAsObject(EDITOR);
                        if (editor != null && editor instanceof BPSEditor) {
                            ((BPSEditor) editor).setIsReDrawEditor(false);
                        }
                    }
                });
            }
        }
    }

    /**
     * Based on the specified file ID, <br>
     * the file information is acquired from the database and reflected in the target TreeNode (file) of Project Explorer.
     * @param fileId specified file ID
     */
    private void updateFileResource(Long fileId) {
        editResourceService.getVMFile(fileId, new AsyncCallback<VMFile>() {
            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(VMFile result) {
                FileTreeNode targetNode = (FileTreeNode) fileTreeNodeFactory.findTreeNode(tree, fileId);
                targetNode.setResource(result);
            }
        });
    }

    /**
     * This is the {@link #deleteMenu} process. <br>
     * The selected directory or file will be deleted.
     */
    private void clickDelete() {
        String msg = "Are you sure you want to delete ";
        if (treeGrid.getSelectedRecords().length > 1) {
            msg += " these " + treeGrid.getSelectedRecords().length + " elements ?";
        } else {
            if (tree.isFolder(treeGrid.getSelectedRecord())) {
                msg += "directory";
            } else {
                msg += "file";
            }
            msg += " '" + treeGrid.getSelectedRecord().getAttribute("name") + "'?";
        }
        SC.confirm("Delete", msg, new BooleanCallback() {
            @Override
            public void execute(Boolean value) {
                if (value != null && value) {
                    List<Long> activeIds = new ArrayList<Long>();
                    for (ListGridRecord target : treeGrid.getSelectedRecords()) {
                        activeIds.add(((FileTreeNode) target).getResource().getId());
                    }
                    editResourceService.getActiveDirOrFileMap(projectId, activeIds, new AsyncCallback<List<Map<Long, VMResource>>>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }

                        @Override
                        public void onSuccess(List<Map<Long, VMResource>> result) {
                            ZGResourceRemoveCommand removeCommand = new ZGResourceRemoveCommand(editResourceService, viewHandler, result);
                            removeCommand.addCommandListener(new CommandListener() {

                                @Override
                                public void executeEvent() {
                                    for (Map<Long, VMResource> temp : removeCommand.getTargets()) {
                                        for (Entry<Long, VMResource> target : temp.entrySet()) {
                                            fileTreeNodeFactory.removeVMResource(target.getValue().getId());
                                        }
                                    }
                                    fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                                    treeGrid.sort();
                                    treeGrid.redraw();
                                }

                                @Override
                                public void undoEvent() {
                                    List<Map<Long, VMResource>> temp = removeCommand.getTargets().stream().sorted((l, r) -> {
                                        if (((VMResource) l.values().iterator().next()).getId() == r.keySet().iterator().next()) {
                                            return -1;
                                        } else {
                                            return 1;
                                        }
                                    }).collect(Collectors.toList());
                                    for (Map<Long, VMResource> target : temp) {
                                        for (Entry<Long, VMResource> entry : target.entrySet()) {
                                            createCallbackFile(fileTreeNodeFactory.findTreeNode(tree, entry.getKey()), entry.getValue(), entry.getValue().getId(), false);
                                        }
                                    }
                                }

                                @Override
                                public void redoEvent() {
                                    for (Map<Long, VMResource> temp : removeCommand.getTargets()) {
                                        for (Entry<Long, VMResource> target : temp.entrySet()) {
                                            fileTreeNodeFactory.removeVMResource(target.getValue().getId());
                                        }
                                    }
                                    fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                                    treeGrid.sort();
                                    treeGrid.redraw();
                                }

                                @Override
                                public void bindEvent() {
                                    viewHandler.clear();
                                    registerRightClickEvent();
                                }
                            });
                            CompoundCommand c = new CompoundCommand();
                            c.append(removeCommand);
                            manager.execute(c.unwrap());
                            innerPane.focus();
                        }
                    });
                }
            }
        });
    }

    /**
     * A class that manages all {@link SubMenuItem} created.
     */
    private class SubMenuItemList extends ArrayList<SubMenuItem> {

        /** Unique identifier of the class */
        private static final long serialVersionUID = -6417313667784455049L;

        /**
         * A submenu for adding a new directory is added to the list.
         * @param directoryName "Directory"
         */
        public void add(String directoryName) {
            this.add(new SubMenuItem(directoryName));
        }

        /**
         * A submenu for adding new files is added to the list.
         * @param extension file extension
         */
        public void add(Extension extension) {
            this.add(new SubMenuItem(extension));
        }

        /**
         * Gets the list of submenus created for this class.
         * @return list of submenus
         */
        public SubMenuItem[] getArray() {
            return this.toArray(new SubMenuItem[this.size()]);
        }
    }

    /**
     * A new directory or new file creation menu is managed.
     */
    private class SubMenuItem extends MenuItem {

        /** Fixed string "Directory" or the official name of the file */
        private String fileName;

        /** File extension */
        private String extension;

        /** Directory flag. If True, it is a directory. */
        private boolean isDirectory;

        /**
         * constructor. <br>
         * A submenu for creating a new directory is created.
         * @param directoryName directory name
         */
        SubMenuItem(String directoryName) {
            super(directoryName, "pieces/16/folder.png");
            this.fileName = directoryName;
            this.extension = "";
            this.isDirectory = true;
        }

        /**
         * constructor. <br>
         * A submenu is created to create a new file.
         * @param extension File extension
         */
        SubMenuItem(Extension extension) {
            super(extension.getFileName() + "(." + extension.getValue() + ")", "pieces/16/" + extension.getImgPath());
            this.fileName = extension.getFileName();
            this.extension = extension.getValue();
            this.isDirectory = false;
        }

        /**
         * The extension of the file is obtained.
         * @return {@link #extension}
         */
        public String getExtension() {
            return extension;
        }

        /**
         * The official name of the directory or file is acquired.
         * @return {@link #fileName}
         */
        public String getFileName() {
            return fileName;
        }

        /**
         * Get the directory flag.
         * @return {@link #isDirectory}
         */
        public boolean isDirectory() {
            return isDirectory;
        }
    }

    /**
     * Checks if the specified name exists in the TreeNode list.
     * @param tn It is checked if there is a name that matches this TreeNode list.
     * @param checkName The name of the directory or file to check
     * @param checkDirectory True if the check target is a directory
     * @param checkExtension File extension to check
     * @return True if no matching name exists, False if it exists
     */
    private boolean checkSameName(TreeNode[] tn, String checkName, boolean checkDirectory, String checkExtension) {
        boolean result = true;
        for (TreeNode treeNode : tn) {
            VMResource resource = ((FileTreeNode) treeNode).getResource();
            String tnName = resource.getName();
            if (tnName != null && tnName.equals(checkName)) {
                if (!checkDirectory && !tree.isFolder(treeNode) && resource instanceof VMFile) {
                    String ext = ((VMFile) resource).getExtensionStr();
                    if (ext == null || !ext.equals(checkExtension)) {
                        continue;
                    }
                } else if (checkDirectory != tree.isFolder(treeNode)) {
                    continue;
                }
                return false;
            }
        }
        return result;
    }

    /**
     * Create a title for the tab that displays the file. <br>
     * If the file does not have an extension, no image icon will be created.
     * @param title Text to display on tabs
     * @param extension File extension
     * @return Text to display on tabs (including images and tags)
     */
    public static String getTabImgTitle(String title, Extension extension) {
        if (extension == null) {
            return "<span style=\"font-family: 'calibri';\">" + title + "</span>";
        }
        return "<span style=\"font-family: 'calibri';\">" + Canvas.imgHTML("pieces/16/" + extension.getImgPath()) + "&nbsp;" + title + "</span>";
    }

    /**
     * Create a title for the tab that displays the change history of the file.
     * @param title Text to display on tabs
     * @return Text to display on tabs (including images and tags)
     */
    private String getHistoryTabImgTitle(String title) {
        return "<span style=\"font-family: 'calibri';\">" + Canvas.imgHTML("pieces/16/history.png") + "&nbsp;" + title + "</span>";
    }

    /**
     * The save event handler for the editor is added.
     * @param editor specified editor
     * @param tab Tabs associated with the specified editor
     */
    private void clickSaveFile(Editor editor, Tab tab) {
        editor.addSaveHandler(createClickSavaHandler(editor, tab));
    }

    /**
     * An event handler is created that fires the save process of the editor.
     * @param editor specified editor
     * @param tab Tabs associated with the specified editor
     * @return The created event handler for the save process
     */
    private com.smartgwt.client.widgets.menu.events.ClickHandler createClickSavaHandler(Editor editor, Tab tab) {
        return new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editor.save(ModelingProjectView.this, editor, tab, null);
            }
        };
    }

    /**
     * The save process of the specified editor is performed.
     * @param editor specified editor
     * @param tab Tabs associated with the specified editor
     * @param handler Processing to be performed after the saving process
     */
    public void executeSaveAction(Editor editor, Tab tab, PostProcessHandler handler) {
        Long ref = tab.getAttributeAsLong("ref");
        editResourceService.getVMFile(ref, new AsyncCallback<VMFile>() {

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }

            @Override
            public void onSuccess(VMFile result) {
                if (result == null) {
                    SC.warn("File not found.");
                    return;
                }
                if (result.getExtension() == Extension.TXT || result.getExtension() == Extension.LSC || result.getExtension() == Extension.CSC) {
                    editResourceService.saveTextFile(result.getId(), ((TextEditor) editor).getValue(), projectId, new AsyncCallback<Void>() {
                        @Override
                        public void onSuccess(Void result) {
                            editor.setSavedPosition();
                            setDirtyFlagToTabTitle(tab);
                            if (handler != null) {
                                handler.execute();
                            }
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }
                    });
                } else if (result.getExtension() != null) {
                    AbstractRootElement root = editor.getRoot();
                    BinaryResourceImpl r = new BinaryResourceImpl();
                    r.getContents().add(root);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    try {
                        r.save(outputStream, null);
                    } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                    }
                    byte[] bytes = outputStream.toByteArray();
                    editResourceService.saveFile(result.getId(), bytes, new AsyncCallback<Void>() {
                        @Override
                        public void onSuccess(Void result) {
                            editor.setSavedPosition();
                            setDirtyFlagToTabTitle(tab);
                            if (handler != null) {
                                handler.execute();
                            }
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }
                    });
                } else {
                    editResourceService.saveTextFile(result.getId(), ((TextEditor) editor).getValue(), projectId, new AsyncCallback<Void>() {
                        @Override
                        public void onSuccess(Void result) {
                            editor.setSavedPosition();
                            setDirtyFlagToTabTitle(tab);
                            if (handler != null) {
                                handler.execute();
                            }
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
     * Used to open another file with an operation in the editor.. <br>
     * A process is created to open the file obtained by {@link EditorOpener#getOpenFileId()} .
     * @param editorOpener specified editor
     */
    private void clickOpenFile(EditorOpener editorOpener) {
        ClickHandler clickHandler = new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                long fileId = editorOpener.getOpenFileId();
                if (fileId == -1L) {
                    SC.warn("Please select one node.");
                    return;
                } else if (fileId == -2L) {
                    SC.warn("Please select end node.");
                    return;
                } else if (fileId == -3L) {
                    SC.warn("File not found.");
                    return;
                }
                selectRootNode(createOpenEditorTab(fileId, false));
            }
        };
        editorOpener.addOpenFileHandler(clickHandler);
    }

    /**
     * The tab associated with the specified file ID is selected. <br>
     * If there is no tab, a tab associated with the specified file ID will be created and the editor will be opened.
     * @param fileId specified file ID
     * @param useSearch True when searching for FMNode or FSMState
     * @return Created or selected tab
     */
    public Tab createOpenEditorTab(long fileId, boolean useSearch) {
        Tab retTab = new Tab();
        long ref = 0;
        StringBuilder tabId = new StringBuilder();
        for (Tab tab : editorTabSet.getTabs()) {
            if (tab.getAttributeAsLong("ref") == fileId) {
                ref = tab.getAttributeAsLong("ref");
                tabId.append(tab.getAttributeAsString("UniqueId"));
                editorTabSet.selectTab(tabId.toString());
                if (useSearch) {
                    search.selectSearchResultModel(tab);
                    return null;
                } else {
                    return tab;
                }
            }
        }

        if (ref == 0) {
            editResourceService.getVMFile(fileId, new AsyncCallback<VMFile>() {

                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }

                @Override
                public void onSuccess(VMFile result) {
                    if (result == null) {
                        SC.warn("File not found.");
                        return;
                    }

                    tabId.append(HTMLPanel.createUniqueId().replaceAll("-", "_"));
                    String tabName = result.getName();
                    retTab.setTitle(getTabImgTitle(tabName, result.getExtension()));
                    retTab.setCanClose(true);
                    retTab.setID(tabId.toString());
                    String path = result.getFullPath().replaceAll("(.*)/" + result.getName() + "\\." + result.getExtensionStr() + "$", "$1");
                    retTab.setPrompt(path);
                    retTab.setPane(new Layout());
                    retTab.setAttribute("ref", fileId);
                    retTab.setAttribute("UniqueId", tabId.toString());
                    retTab.setAttribute("Extension", result.getExtension().getValue());
                    retTab.setAttribute("TabName", tabName);
                    createView(retTab, result, new PostProcessHandler() {
                        @Override
                        public void execute() {
                            if (useSearch) {
                                search.selectSearchResultModel(retTab);
                            }
                        }
                    });
                }
            });
            return retTab;
        }
        return retTab;
    }

    /**
     * The FM Editor creates a new node and creates a new FM file associated with the node.
     * @param fmEditor FM editor
     * @param file FM file
     */
    private void clickCreateChildModel(FMEditor fmEditor, VMFile file) {
        ClickHandler clickHandler = new ClickHandler() {

            @Override
            public void onClick(MenuItemClickEvent event) {
                List<DrawRect> drawRects = fmEditor.nodeManager.getSelectDrawRect();
                FMDrawNode drawNode = fmEditor.getDrawRectMap().get(drawRects.get(0).hashCode());
                FMNode node = drawNode.getFmNode();

                editResourceService.getDirId(file.getId(), new AsyncCallback<Long>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Long dirID) {
                        VMFile childModelFile = new VMFile();
                        childModelFile.setExtension(Extension.FM);
                        String fileName = file.getName() + "_child";

                        editResourceService.getResources(dirID, new AsyncCallback<List<VMResource>>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                SC.warn(caught.getMessage());
                            }

                            @Override
                            public void onSuccess(List<VMResource> result) {
                                // check duplicate file name under same directory and set file name
                                childModelFile.setName(checkDuplicateName(result, fileName, childModelFile.getExtension(), 0));
                                ZGCreateFileCommand createFileCommand = new ZGCreateFileCommand(editResourceService, viewHandler, dirID, childModelFile, null);
                                createFileCommand.addCommandListener(new CommandListener() {

                                    @Override
                                    public void undoEvent() {
                                        treeGrid.deselectAllRecords();
                                        fileTreeNodeFactory.removeVMResource(createFileCommand.getFileId());
                                        fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                                        treeGrid.sort();
                                        treeGrid.redraw();
                                    }

                                    @Override
                                    public void redoEvent() {
                                        createCallbackFile(fileTreeNodeFactory.findTreeNode(tree, createFileCommand.getParentId()), createFileCommand.getFile(),
                                                createFileCommand.getFileId(), true);
                                    }

                                    @Override
                                    public void executeEvent() {
                                        createCallbackFile(fileTreeNodeFactory.findTreeNode(tree, createFileCommand.getParentId()), createFileCommand.getFile(),
                                                createFileCommand.getFileId(), true);
                                        // set node reference
                                        editResourceService.getFileContent(createFileCommand.getFileId(), new AsyncCallback<byte[]>() {

                                            @Override
                                            public void onFailure(Throwable caught) {
                                                SC.warn(caught.getMessage());
                                            }

                                            @Override
                                            public void onSuccess(byte[] result) {
                                                BinaryResourceImpl r = new BinaryResourceImpl();
                                                ByteArrayInputStream bi = new ByteArrayInputStream(result);
                                                EPackage.Registry.INSTANCE.put(FMPackage.eNS_URI, FMPackage.eINSTANCE);

                                                FMRoot root = null;
                                                try {
                                                    r.load(bi, EditOptions.getDefaultLoadOptions());
                                                    root = (FMRoot) r.getContents().get(0);
                                                } catch (IOException e) {
                                                    SC.warn(e.getMessage());
                                                }
                                                String refFileName = childModelFile.getName() + "." + childModelFile.getExtensionStr();
                                                CompoundCommand cmd = FMEditorCommandProvider.getInstance().setReferenceNode(node, createFileCommand.getFileId(),
                                                        root.getNode().getName(), refFileName, root.getId());
                                                fmEditor.getEditManager().execute(cmd.unwrap());
                                                drawNode.getDrawRect().setTitle(node.getName() + ":" + node.getRefName() + "\n(" + refFileName + ")");

                                                selectRootNode(editorTabSet.getSelectedTab());
                                            }
                                        });
                                    }

                                    @Override
                                    public void bindEvent() {
                                        viewHandler.clear();
                                        registerRightClickEvent();
                                    }
                                });
                                CompoundCommand c = new CompoundCommand();
                                c.append(createFileCommand);
                                manager.execute(c.unwrap());
                            }
                        });
                    }
                });
            }
        };
        fmEditor.addCreateChildModelHandler(clickHandler);
    }

    /**
     * The ARC Editor creates a new state and creates a new FSM file associated with the state.
     * @param arcEditor ARC Editor
     * @param file ARC file
     */
    private void clickNewStateMachine(ARCEditor arcEditor, VMFile file) {
        ClickHandler clickHandler = new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                editResourceService.getDirId(file.getId(), new AsyncCallback<Long>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Long dirID) {
                        VMFile newFile = new VMFile();
                        newFile.setExtension(Extension.FSM);
                        String fileName = "StateMachine";

                        editResourceService.getResources(dirID, new AsyncCallback<List<VMResource>>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                SC.warn(caught.getMessage());
                            }

                            @Override
                            public void onSuccess(List<VMResource> result) {
                                // check duplicate file name under same directory and set file name
                                newFile.setName(checkDuplicateName(result, fileName, newFile.getExtension(), 0));
                                ZGCreateFileCommand createFileCommand = new ZGCreateFileCommand(editResourceService, viewHandler, dirID, newFile, null);
                                createFileCommand.addCommandListener(new CommandListener() {

                                    @Override
                                    public void undoEvent() {
                                        treeGrid.deselectAllRecords();
                                        fileTreeNodeFactory.removeVMResource(createFileCommand.getFileId());
                                        fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                                        treeGrid.sort();
                                        treeGrid.redraw();
                                    }

                                    @Override
                                    public void redoEvent() {
                                        createCallbackFile(fileTreeNodeFactory.findTreeNode(tree, createFileCommand.getParentId()), createFileCommand.getFile(),
                                                createFileCommand.getFileId(), true);
                                    }

                                    @Override
                                    public void executeEvent() {
                                        createCallbackFile(fileTreeNodeFactory.findTreeNode(tree, createFileCommand.getParentId()), createFileCommand.getFile(),
                                                createFileCommand.getFileId(), true);
                                        editResourceService.getFileContent(createFileCommand.getFileId(), new AsyncCallback<byte[]>() {

                                            @Override
                                            public void onFailure(Throwable caught) {
                                                SC.warn(caught.getMessage());
                                            }

                                            @Override
                                            public void onSuccess(byte[] result) {
                                                BinaryResourceImpl r = new BinaryResourceImpl();
                                                ByteArrayInputStream bi = new ByteArrayInputStream(result);
                                                EPackage.Registry.INSTANCE.put(FSMPackage.eNS_URI, FSMPackage.eINSTANCE);

                                                FSMDStateMachine machine = null;
                                                try {
                                                    r.load(bi, EditOptions.getDefaultLoadOptions());
                                                    machine = (FSMDStateMachine) r.getContents().get(0);
                                                } catch (IOException e) {
                                                    SC.warn(e.getMessage());
                                                }
                                                ARCState newState = ARCFactory.eINSTANCE.createARCState();
                                                newState.setFileId(machine.getId());
                                                newState.setTop(arcEditor.getStateTop());
                                                newState.setLeft(arcEditor.getStateLeft());
                                                newState.setHeight(40);
                                                newState.setWidth(80);
                                                newState.setEvalPriority(arcEditor.getMaxEvalPriority());

                                                CompoundCommand cmd = ARCEditorCommandProvider.getInstance().addState(arcEditor.getARCRoot(), newState,
                                                        arcEditor.getARCRoot().getStates().size());
                                                arcEditor.getEditManager().execute(cmd.unwrap());
                                                arcEditor.refresh();
                                            }
                                        });
                                    }

                                    @Override
                                    public void bindEvent() {
                                        viewHandler.clear();
                                        registerRightClickEvent();
                                    }
                                });
                                CompoundCommand c = new CompoundCommand();
                                c.append(createFileCommand);
                                manager.execute(c.unwrap());
                            }
                        });
                    }
                });
            }
        };
        arcEditor.addNewStateMachineHandler(clickHandler);
    }

    /**
     * Recursive processing is performed until a unique file name is obtained.
     * @param result A list of resources in a directory. It will be compared with the specified file name.
     * @param fileName File name to be checked for duplicates
     * @param extension File extension to be checked for duplicates
     * @param index How many times the recursive process was done.
     * @return If index is 0, the filename is returned. <br>
     *         If it is 1 or more, the file name + index is returned.
     */
    private String checkDuplicateName(List<VMResource> result, String fileName, Extension extension, int index) {
        String childFileName = fileName;
        int num = index;
        if (num > 0) {
            childFileName = fileName + num;
        }
        boolean isAlreadyExist = false;
        for (VMResource resource : result) {
            if (resource instanceof VMFile) {
                VMFile file = (VMFile) resource;
                if (file.getName().equals(childFileName) && file.getExtension().equals(extension)) {
                    isAlreadyExist = true;
                    break;
                }
            }
        }
        if (isAlreadyExist) {
            childFileName = checkDuplicateName(result, fileName, extension, ++num);
        }
        return childFileName;
    }

    /**
     * The specified file will be downloaded.
     * @param resource specified file
     */
    protected void downloadFile(List<VMFile> resources) {
        String fileDownloadURL;
        String ids = "";
        String fileName = "";
        for (VMFile file : resources) {
            if (!ids.isEmpty()) {
                ids += ",";
            } else {
                fileName = file.getName() + "." + file.getExtensionStr();
            }
            ids += file.getId();
        }
        if (resources.size() > 1) {
            fileName = projectName;
        }
        fileDownloadURL = GWT.getModuleBaseURL() + "download?name=" + fileName + "&ids=" + ids;
        com.google.gwt.user.client.Window.open(fileDownloadURL, "", "");
    }

    /**
     * The file information is added to the reference node of the specified FM editor.
     * @param fmEditor feature-model editor
     */
    private void setFileNameToReferenceNode(FMEditor fmEditor) {
        fmEditor.getDrawRectMap().values().stream().filter(drawNode -> drawNode.getFmNode().getRefuuid() != null)
                .forEach(drawNode -> editResourceService.getVMFile(drawNode.getFmNode().getRefuuid(), projectId, new AsyncCallback<VMFile>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        SC.warn(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(VMFile result) {
                        if (result != null) {
                            drawNode.getFmNode().setRefInfo(result.getName() + "." + result.getExtensionStr());

                            editResourceService.getFileContent(drawNode.getFmNode().getRefuuid(), projectId, new AsyncCallback<byte[]>() {
                                @Override
                                public void onFailure(Throwable caught) {
                                    SC.warn(caught.getMessage());
                                }

                                @Override
                                public void onSuccess(byte[] result) {
                                    BinaryResourceImpl r = new BinaryResourceImpl();
                                    ByteArrayInputStream bi = new ByteArrayInputStream(result);

                                    EPackage.Registry.INSTANCE.put(FMPackage.eNS_URI, FMPackage.eINSTANCE);
                                    FMRoot root = null;
                                    try {
                                        r.load(bi, EditOptions.getDefaultLoadOptions());
                                        root = (FMRoot) r.getContents().get(0);
                                    } catch (IOException e) {
                                        SC.warn(e.getMessage());
                                    }
                                    if (root.getNode() != null) {
                                        drawNode.getFmNode().setRefName(root.getNode().getName());
                                    } else {
                                        drawNode.getFmNode().setRefName("");
                                    }
                                    drawNode.getDrawRect()
                                            .setTitle(drawNode.getFmNode().getName() + ":" + drawNode.getFmNode().getRefName() + "\n(" + drawNode.getFmNode().getRefInfo() + ")");
                                }
                            });
                        } else {
                            drawNode.getFmNode().setRef(0);
                            drawNode.getFmNode().setRefInfo(null);
                            drawNode.getFmNode().setRefName(null);
                            drawNode.getFmNode().setRefuuid(null);
                            drawNode.getDrawRect().setTitle(drawNode.getFmNode().getName());
                        }
                    }
                }));
    }

    /**
     * If the editor displayed on the specified tab is the FM editor, the root node will be selected. <br>
     * The command is executed after the browser event ends.
     * @param tab specified tab
     */
    private void selectRootNode(Tab tab) {
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                Editor editor = (Editor) tab.getAttributeAsObject(EDITOR);
                if (editor instanceof FMEditor) {
                    FMEditor fmEditor = (FMEditor) editor;
                    fmEditor.nodeManager.selectRootNode(fmEditor);
                }

            }
        });
    }

    /**
     * The editor opens based on the specified TreeNode and resources.
     * @param selectedNode Specified TreeNode (file)
     * @param resource Resources related to TreeNode
     * @param handler What to do after the editor opens
     */
    public void openEditor(FileTreeNode selectedNode, VMResource resource, PostProcessHandler handler) {
        String tabId = selectedNode.getAttribute("UniqueId");

        for (Tab tab : editorTabSet.getTabs()) {
            long ref = tab.getAttributeAsLong("ref");
            if (resource.getId() == ref) {
                tabId = tab.getAttributeAsString("UniqueId");
                if (tabId.contains("_historyEditor")) {
                    tabId = tabId.substring(0, tabId.lastIndexOf("_historyEditor"));
                }
                selectedNode.setAttribute("UniqueId", tabId);
                selectedNode.setResource(resource);
                break;
            }
        }
        if (null == tabId || "".equals(tabId)) {
            tabId = HTMLPanel.createUniqueId().replaceAll("-", "_");
            selectedNode.setAttribute("UniqueId", tabId);
            selectedNode.setResource(resource);
        }
        String tabName = resource.getName();
        Tab tab = editorTabSet.getTab(tabId);
        if (tab == null) {
            tab = new Tab();
            Extension extension = ((VMFile) resource).getExtension();
            String extensionStr = ((VMFile) resource).getExtensionStr();
            tab.setTitle(getTabImgTitle(tabName, extension));
            tab.setCanClose(true);
            tab.setID(tabId);
            tab.setPrompt(tree.getParentPath(selectedNode));
            tab.setAttribute("ref", resource.getId());
            tab.setAttribute("UniqueId", tabId);
            tab.setAttribute("Extension", extensionStr);
            tab.setAttribute("TabName", tabName);
            createView(tab, (VMFile) resource, handler);
        } else {
            editorTabSet.selectTab(tabId);
        }
    }

    /**
     * The resources related to the record selected from the {@link #treeGrid} are copied to the {@link #copiedResourceMap}.
     */
    private void copy() {
        clearResources();
        Arrays.asList(treeGrid.getSelectedRecords()).forEach(rec -> {
            VMResource resource = ((FileTreeNode) rec).getResource();
            addSelectedResource(resource);
            getResourceMap(resource);

            pasteMenuItem.setEnabled(true);
        });
    }

    /**
     * get child resources from parent folder
     * @param parent parent folder
     */
    private void getResourceMap(VMResource parent) {
        editResourceService.getResources(parent.getId(), new AsyncCallback<List<VMResource>>() {

            @Override
            public void onSuccess(List<VMResource> result) {
                copyResource(parent, result);
                for (VMResource child : result) {
                    getResourceMap(child);
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                SC.warn(caught.getMessage());
            }
        });
    }

    /**
     * Processing of paste menu. <br>
     * The copied directories and files will be pasted.
     */
    private void paste() {
        FileTreeNode treeNode = (FileTreeNode) treeGrid.getSelectedRecord();
        VMResource targetRes = treeNode.getResource();

        if (fileTreeNodeFactory.getParentDirId(targetRes.getId()) == rootId && targetRes instanceof VMFile) {
            for (VMResource resource : getSelectedResources()) {
                copyPaste(resource, rootId, tree.getRoot());
            }
        } else {
            long targetId = 0;
            TreeNode targetTreeNode = treeNode;
            if (targetRes instanceof VMDirectory) {
                targetId = ((VMDirectory) targetRes).getId();
            } else if (targetRes instanceof VMFile) {
                targetTreeNode = tree.getParent(targetTreeNode);
                targetId = ((VMDirectory) fileTreeNodeFactory.getResource(targetTreeNode)).getId();
            }
            for (VMResource resource : getSelectedResources()) {
                copyPaste(resource, targetId, targetTreeNode);
            }
        }
    }

    /**
     * Copy and Paste File and Directory
     * @param copiedRes Resources to be copied
     * @param targetId Directory ID of the paste destination
     * @param treeNode TreeNode of the paste destination
     */
    private void copyPaste(VMResource copiedRes, long targetId, TreeNode treeNode) {

        // copy and paste for Folder
        if (copiedRes instanceof VMDirectory) {
            String name = copiedRes.getName();
            if (treeNode != null && isSameNameExist(treeNode, name, true, "")) {
                name += " - Copy";
                if (copyNumber != 0) {
                    name += " (" + copyNumber + ")";
                }
            }
            VMDirectory newDir = new VMDirectory();
            newDir.setName(name);
            ZGCreateDirCommand createDirCommand = new ZGCreateDirCommand(editResourceService, viewHandler, targetId, newDir);
            createDirCommand.addCommandListener(new CommandListener() {

                @Override
                public void undoEvent() {
                    treeGrid.deselectAllRecords();
                    fileTreeNodeFactory.removeVMResource(createDirCommand.getFileId());
                    fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                    treeGrid.sort();
                    treeGrid.redraw();
                }

                @Override
                public void redoEvent() {
                    createCallbackFile(fileTreeNodeFactory.findTreeNode(tree, createDirCommand.getParentId()), createDirCommand.getDirectory(), createDirCommand.getFileId(),
                            false);
                    getCopiedResources().get(copiedRes).forEach(res -> {
                        copyPaste(res, createDirCommand.getDirectory().getId(), null);
                    });
                }

                @Override
                public void executeEvent() {
                    createCallbackFile(fileTreeNodeFactory.findTreeNode(tree, createDirCommand.getParentId()), createDirCommand.getDirectory(), createDirCommand.getFileId(),
                            false);
                    // copy and paste for child resources
                    getCopiedResources().get(copiedRes).forEach(res -> {
                        copyPaste(res, createDirCommand.getDirectory().getId(), null);
                    });
                }

                @Override
                public void bindEvent() {
                    viewHandler.clear();
                    registerRightClickEvent();
                }

            });

            CompoundCommand c = new CompoundCommand();
            c.append(createDirCommand);
            manager.execute(c.unwrap());

        } else if (copiedRes instanceof VMFile) { // copy and paste for file
            VMFile oldFile = (VMFile) copiedRes;
            editResourceService.getFileContent(oldFile.getId(), new AsyncCallback<byte[]>() {

                @Override
                public void onFailure(Throwable caught) {
                    SC.warn(caught.getMessage());
                }

                @Override
                public void onSuccess(byte[] result) {
                    String fileExtension = oldFile.getExtensionStr();
                    VMFile newFile = new VMFile();
                    // create new file name
                    String fileName = oldFile.getName();
                    if (treeNode != null && isSameNameExist(treeNode, fileName, false, fileExtension)) {
                        fileName += " - Copy";
                        if (copyNumber != 0) {
                            fileName += " (" + copyNumber + ")";
                        }
                    }
                    newFile.setName(fileName);
                    // get data from old file
                    byte[] datas = new byte[result.length];
                    for (int i = 0; i < result.length; i++) {
                        datas[i] = (byte) (result[i] & 0xFF);
                    }
                    // Set file extension
                    newFile.setExtensionStr(fileExtension);

                    // create new file command
                    ZGCreateFileCommand createFileCommand = new ZGCreateFileCommand(editResourceService, viewHandler, targetId, newFile, null);
                    createFileCommand.addCommandListener(new CommandListener() {

                        @Override
                        public void undoEvent() {
                            treeGrid.deselectAllRecords();
                            fileTreeNodeFactory.removeVMResource(createFileCommand.getFileId());
                            fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                            treeGrid.sort();
                            treeGrid.redraw();
                        }

                        @Override
                        public void redoEvent() {
                            createCallbackFile(fileTreeNodeFactory.findTreeNode(tree, createFileCommand.getParentId()), createFileCommand.getFile(), createFileCommand.getFileId(),
                                    false);
                            editResourceService.saveFile(createFileCommand.getFileId(), datas, new AsyncCallback<Void>() {

                                @Override
                                public void onFailure(Throwable caught) {
                                    GWT.log(caught.getMessage());
                                }

                                @Override
                                public void onSuccess(Void result) {
                                    fileTreeNodeFactory.getFileTreeNode(tree, createFileCommand.getParentId(), createFileCommand.getFile());
                                    if (treeGrid.anySelected())
                                        treeGrid.deselectAllRecords();
                                    fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                                    treeGrid.sort();
                                    treeGrid.redraw();
                                    treeGrid.deselectAllRecords();
                                    treeGrid.selectRecord(fileTreeNodeFactory.findTreeNode(tree, createFileCommand.getFileId()));
                                }
                            });
                        }

                        @Override
                        public void executeEvent() {
                            // save old file data
                            editResourceService.saveFile(createFileCommand.getFileId(), datas, new AsyncCallback<Void>() {

                                @Override
                                public void onFailure(Throwable caught) {
                                    GWT.log(caught.getMessage());
                                }

                                @Override
                                public void onSuccess(Void result) {
                                    fileTreeNodeFactory.getFileTreeNode(tree, createFileCommand.getParentId(), createFileCommand.getFile());
                                    if (treeGrid.anySelected())
                                        treeGrid.deselectAllRecords();
                                    fileTreeNodeFactory.refresh(editorTabSet, tree, tabRegs);
                                    treeGrid.sort();
                                    treeGrid.redraw();
                                    treeGrid.deselectAllRecords();
                                    treeGrid.selectRecord(fileTreeNodeFactory.findTreeNode(tree, createFileCommand.getFileId()));
                                }
                            });
                            createCallbackFile(fileTreeNodeFactory.findTreeNode(tree, createFileCommand.getParentId()), createFileCommand.getFile(), createFileCommand.getFileId(),
                                    false);
                        }

                        @Override
                        public void bindEvent() {
                            viewHandler.clear();
                            registerRightClickEvent();
                        }
                    });
                    CompoundCommand c = new CompoundCommand();
                    c.append(createFileCommand);
                    manager.execute(c.unwrap());
                }

            });
        }
    }

    /**
     * A context menu is created to set to the specified tab.
     * @param tab specified tab.
     * @param saveItemTab "Save menu" to be set on the tab
     */
    private void createTabMenu(Tab tab, MenuItem saveItemTab) {
        Menu tabMenu = new Menu();
        MenuItem closeItem = new MenuItem("Close");
        closeItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                tabCloseEvent(tab);
            }
        });
        MenuItem closeOtersItem = new MenuItem("Close Others");
        closeOtersItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                for (Tab closeTab : editorTabSet.getTabs()) {
                    if (editorTabSet.getTabNumber(closeTab.getID()) != editorTabSet.getTabNumber(tab.getID())) {
                        tabCloseEvent(closeTab);
                    }
                }
            }
        });
        MenuItem closeLeftItem = new MenuItem("Close Tabs to the Left");
        closeLeftItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                for (Tab closeTab : editorTabSet.getTabs()) {
                    if (editorTabSet.getTabNumber(closeTab.getID()) < editorTabSet.getTabNumber(tab.getID())) {
                        tabCloseEvent(closeTab);
                    }
                }
            }
        });
        MenuItem closeRightItem = new MenuItem("Close Tabs to the Right");
        closeRightItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                for (Tab closeTab : editorTabSet.getTabs()) {
                    if (editorTabSet.getTabNumber(closeTab.getID()) > editorTabSet.getTabNumber(tab.getID())) {
                        tabCloseEvent(closeTab);
                    }
                }
            }
        });
        MenuItem closeAllItem = new MenuItem("Close All");
        closeAllItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                for (Tab closeTab : editorTabSet.getTabs()) {
                    tabCloseEvent(closeTab);
                }
            }
        });
        MenuItemSeparator sep = new MenuItemSeparator();
        if (saveItemTab != null) {
            MenuItemIfFunction enableCondition = new MenuItemIfFunction() {
                public boolean execute(Canvas target, Menu menu, MenuItem item) {
                    return tab == editorTabSet.getSelectedTab();
                }
            };

            saveItemTab.setEnableIfCondition(enableCondition);
            saveItemTab.setKeyTitle("Ctrl + S");

            // If you use addItem, the association with the shortcut key is lost.
            tabMenu.setItems(saveItemTab, sep, closeItem, closeOtersItem, closeLeftItem, closeRightItem, sep, closeAllItem);
        } else {
            tabMenu.setItems(closeItem, closeOtersItem, closeLeftItem, closeRightItem, sep, closeAllItem);
        }
        tab.setContextMenu(tabMenu);
    }

    /**
     * to check same name exist in target directory
     * @param tn The TreeNode to be checked.
     * @param checkName Original name of copy
     * @param checkDirectory True to check the directory.
     * @param extension Copy source file extension
     * @return True if there is the same name.
     */
    private boolean isSameNameExist(TreeNode tn, String checkName, boolean checkDirectory, String extension) {
        if (checkDirectory) {
            TreeNode[] childNodes = tree.getFolders(tn);
            for (TreeNode child : childNodes) {
                if (child.getName().equals(checkName)) {
                    copyNumber = getCopyNumber(childNodes, checkName, "");
                    return true;
                }
            }
        } else {
            for (TreeNode f : tree.getChildren(tn)) {
                if (fileTreeNodeFactory.getResource(f) instanceof VMFile) {
                    VMFile res = (VMFile) fileTreeNodeFactory.getResource(f);
                    if (res.getExtensionStr().equals(extension) && res.getName().equals(checkName)) {
                        copyNumber = getCopyNumber(tree.getChildren(tn), checkName, extension);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * to check how many copies of same name exist in target directory
     * @param nodes Nodes in the same hierarchy as the copy destination
     * @param name Original name of copy
     * @param extension Copy source file extension
     * @return Number of copies with the same name
     */
    private int getCopyNumber(TreeNode[] nodes, String name, String extension) {
        copyNumber = 1;
        boolean exist = false;
        String copyName = name + " - Copy";
        for (TreeNode f : nodes) {
            if (f.getName().startsWith(copyName)) {
                if (!extension.equals("")) {
                    VMResource res = fileTreeNodeFactory.getResource(f);
                    if (res instanceof VMFile) {
                        VMFile file = (VMFile) res;
                        if (extension.equals(file.getExtensionStr())) {
                            exist = true;
                            copyNumber = file.getName().equals(copyName + " (" + copyNumber + ")") ? copyNumber + 1 : copyNumber;
                        }
                    }
                } else {
                    exist = true;
                    copyNumber = f.getName().equals(copyName + " (" + copyNumber + ")") ? copyNumber + 1 : copyNumber;
                }
            }
        }
        if (!exist) {
            copyNumber = 0;
        }
        return copyNumber;
    }

    /**
     * treeGrid will be returned.
     * @return {@link #treeGrid}
     */
    public TreeGrid getTreeGrid() {
        return treeGrid;
    }

    /**
     * editorTabSet will be returned.
     * @return {@link #editorTabSet}
     */
    public TabSet getEditorTabSet() {
        return editorTabSet;
    }

    /**
     * tabRegs will be returned.
     * @return {@link #tabRegs}
     */
    public Map<String, HandlerRegistration> getTabRegs() {
        return this.tabRegs;
    }

    /**
     * Get the root directory ID.
     * @return {@link #rootId}.
     */
    public long getRootId() {
        return rootId;
    }

    /**
     * Sets a dirty flag for the title of the specified tab.
     * @param tab specified tab.
     */
    private void setDirtyFlagToTabTitle(Tab tab) {
        String extension = tab.getAttributeAsString("Extension");
        String tabName = tab.getAttributeAsString("TabName");

        final Editor editor = (Editor) tab.getAttributeAsObject(EDITOR);
        if (editor != null && editor.isChanged() && tabName.indexOf('*') == -1) {
            tabName = "*" + tabName;
        } else if (editor != null && !editor.isChanged() && tabName.indexOf('*') > -1) {
            tabName = tabName.replace("*", "");
        }
        if (!tabName.equals(tab.getAttributeAsString("TabName"))) {
            tab.setAttribute("TabName", tabName);
            editorTabSet.setTabTitle(tab, getTabImgTitle(tabName, Extension.getByCode(extension)));
            editorTabSet.markForRedraw();
        }
        if (tab.getAttributeAsObject("FileHistoryEditor") instanceof FileHistoryEditor) {
            editorTabSet.setTabTitle(tab, getHistoryTabImgTitle(tab.getAttributeAsString("TabName")));
            editorTabSet.markForRedraw();
        }
    }

    /** List that holds the selected resource information. */
    private List<VMResource> selectedResources = new ArrayList<VMResource>();

    /** A map that holds child resource information for the selected resource. */
    private Map<VMResource, List<VMResource>> copiedResourceMap = new HashMap<VMResource, List<VMResource>>();

    /**
     * Clear {@link #selectedResources} and {@link #copiedResourceMap}.
     */
    public void clearResources() {
        copiedResourceMap.clear();
        selectedResources.clear();
    }

    /**
     * Add the copied resources to {@link #selectedResources}.
     * @param resources copied resources
     */
    public void addSelectedResource(VMResource resources) {
        selectedResources.add(resources);
    }

    /**
     * Add the child resources of the copied resource to {@link #selectedResources}.
     * @param parent Parent resource
     * @param childList Child resource
     */
    public void copyResource(VMResource parent, List<VMResource> childList) {
        copiedResourceMap.put(parent, childList);
    }

    /**
     * Gets the selected resource information.
     * @return {@link #selectedResources}.
     */
    public List<VMResource> getSelectedResources() {
        return selectedResources;
    }

    /**
     * Gets child resource information.
     * @return {@link #copiedResourceMap}.
     */
    public Map<VMResource, List<VMResource>> getCopiedResources() {
        return copiedResourceMap;
    }

    /**
     * The specified directory or file will be downloaded. <br>
     * You cannot mix the directories and files. <br>
     * The directory you specify must be single.
     * @param dirList specified directory
     * @param fileList specified files
     */
    protected void download(List<VMDirectory> dirList, List<VMFile> fileList) {
        String fileDownloadURL;
        String fIds = fileList.stream().map(f -> ((Long) f.getId()).toString()).collect(Collectors.joining(","));
        String downloadFileName = "";
        String dIds = dirList.stream().map(d -> ((Long) d.getId()).toString()).collect(Collectors.joining(","));

        // folder and file selection
        if (!fileList.isEmpty() && !dirList.isEmpty()) {
            SC.say("Please select either directory or file.");
            return;
        }
        // folder and folder selection
        if (dirList.size() > 1) {
            SC.say("Please select only one directory.");
            return;
        }
        // one file or (file and file) selection
        if (dirList.isEmpty()) {
            downloadFileName = fileList.stream().findFirst().map(VMFile::getName).orElse(projectName);
            fileDownloadURL = GWT.getModuleBaseURL() + "download?name=" + downloadFileName + "&ids=" + fIds;
            com.google.gwt.user.client.Window.open(fileDownloadURL, "", "");
        }
        // one folder selection
        if (dirList.size() == 1 && fileList.isEmpty()) {
            downloadFileName = dirList.stream().findFirst().map(VMDirectory::getName).orElse(projectName);
            fileDownloadURL = GWT.getModuleBaseURL() + "download?name=" + downloadFileName + "&ids=" + dIds + "&type=zip";
            com.google.gwt.user.client.Window.open(fileDownloadURL, "", "");
        }
    }

    /**
     * It will be checked if the folder / file upload is complete and a message will be displayed if it is complete.
     */
    private void showUploadFinishMessage() {
        if (loopDir.isEmpty()) {
            if (filesUnderFolder == 0) {
                if (rootFolderUploaded) {
                    SC.say("Your folder is uploaded.");
                } else {
                    if (isAlreadyExist) {
                        SC.say("Upload file is already exists.");
                    } else {
                        SC.say("Your file is uploaded.");
                    }
                }
            } else {
                if (isAlreadyExist) {
                    SC.say("Your folder is uploaded. Some files already exists.");
                } else {
                    SC.say("Your folder is uploaded.");
                }
            }
            if ("yellow".equals(leftLayout.getBackgroundColor())) {
                leftLayout.setBackgroundColor("white");
            }
        }
    }

    /**
     * The specified path is added to {@link #loopDir}.
     * @param str specified path
     */
    private void addLoopDir(String str) {
        loopDir.add(str);
    }

    /**
     * The data at the specified index position will be deleted from {@link #loopDir}.
     * @param index specified index position
     */
    private void removeFromLoopDir(int index) {
        loopDir.remove(index);
    }

    /**
     * The extension of file associated with the specified record is checked.
     * @param selectedNode specified record
     * @param extension extension of the specified file
     * @return Returns True if it matches the extension of the specified file. <br>
     *         Returns False if there is even one mismatched file extension.
     */
    private boolean isSameExtension(ListGridRecord[] selectedNode, Extension extension) {
        boolean result = true;
        for (ListGridRecord record : selectedNode) {
            FileTreeNode node = (FileTreeNode) record;
            VMResource resource = node.getResource();
            if (resource instanceof VMDirectory) {
                result = false;
                break;
            }
            if (resource instanceof VMFile) {
                Extension selExtension = ((VMFile) resource).getExtension();
                if (selExtension != extension) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Gets the file ID associated with the specified record.
     * @param records specified record.
     * @return file ID
     */
    private List<Long> getFileIds(ListGridRecord[] records) {
        List<Long> fileIds = new ArrayList<>();
        for (ListGridRecord record : records) {
            FileTreeNode selectedNode = (FileTreeNode) record;
            VMResource resource = selectedNode.getResource();
            fileIds.add(resource.getId());
        }
        return fileIds;
    }
}
