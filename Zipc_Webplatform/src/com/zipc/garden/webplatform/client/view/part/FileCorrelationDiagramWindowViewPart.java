package com.zipc.garden.webplatform.client.view.part;

import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.tree.Tree;
import com.zipc.garden.webplatform.client.service.EditResourceServiceAsync;
import com.zipc.garden.webplatform.client.view.ModelingProjectView;
import com.zipc.garden.webplatform.client.view.ProcessHandler.PostProcessHandler;
import com.zipc.garden.webplatform.client.view.viz.Digraph;
import com.zipc.garden.webplatform.client.view.viz.Digraph.Node;
import com.zipc.garden.webplatform.client.view.viz.SvgPanZoom;
import com.zipc.garden.webplatform.client.view.viz.Viz;
import com.zipc.garden.webplatform.shared.FileTreeNodeFactory;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * A class that summarizes the processing of the window that displays the file correlation diagram.
 */
public class FileCorrelationDiagramWindowViewPart extends Window {

    /**
     * constructor.<br>
     * Use Viz.js to create a file correlation diagram.
     * @param editResourceService Asynchronous interface for getting file dependencies from a table
     * @param projectId ID of the open project
     * @param modelingProjectView A class that manages the contents of Project Explorer
     */
    public FileCorrelationDiagramWindowViewPart(EditResourceServiceAsync editResourceService, long projectId, ModelingProjectView modelingProjectView) {
        this.setTitle("File Correlation Diagram");
        this.setShowMinimizeButton(true);
        this.setIsModal(false);
        this.setKeepInParentRect(true);
        this.setAutoCenter(true);
        this.setBackgroundColor("white");
        this.setCanDragResize(true);
        this.setWidth(600);
        this.setHeight(400);
        this.setShowFooter(true);
        this.setFooterHeight(10);
        this.setShowMaximizeButton(true);

        Canvas visArea = new Canvas();

        visArea.setHeight100();
        this.addMember(visArea);
        this.show();

        this.addCloseClickHandler(new com.smartgwt.client.widgets.events.CloseClickHandler() {
            @Override
            public void onCloseClick(CloseClickEvent event) {
                FileCorrelationDiagramWindowViewPart.this.markForDestroy();
            }
        });

        this.addResizedHandler(new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent event) {
                visArea.setWidth100();
            }
        });

        Viz viz = new Viz();
        editResourceService.getFileDependencies(projectId, new AsyncCallback<List<VMFile[]>>() {
            @Override
            public void onSuccess(List<VMFile[]> result) {
                Digraph digraph = new Digraph("References");
                digraph.create(result);
                digraph.setLayout(com.zipc.garden.webplatform.client.view.viz.Digraph.Layout.neato);
                viz.renderSVGElement(digraph).then(obj -> {
                    Element e = (Element) obj;
                    Style style = visArea.getDOM().getStyle();
                    style.setWidth(100, Unit.PCT);
                    style.setHeight(100, Unit.PCT);
                    e.setAttribute("width", "100%");
                    e.setAttribute("height", "100%");
                    visArea.getDOM().setInnerText("");
                    visArea.getDOM().appendChild(e);

                    SvgPanZoom svgPanZoom = new SvgPanZoom(e);
                    svgPanZoom.disableDblClickZoom();
                    digraph.getNodes().forEach(n -> {
                        Element etmp = DOM.getElementById(n.getUuid());
                        Event.sinkEvents(etmp, Event.ONCLICK | Event.ONDBLCLICK);
                        Event.setEventListener(etmp, createEventListener(editResourceService, projectId, modelingProjectView, n));
                    });
                });
            }

            @Override
            public void onFailure(Throwable e) {
                SC.warn(e.getMessage());
            }
        });
    }

    /**
     * <pre>
     * The event for the displayed file correlation diagram is created.
     * If you click on a node, the associated file will be activated in the Project Explorer.
     * If you double-click on the node, the associated file will open.
     * </pre>
     * 
     * @param editResourceService Asynchronous interface for getting file dependencies from a table
     * @param projectId ID of the open project
     * @param modelingProjectView A class that manages the contents of Project Explorer
     * @param n Nodes displayed in the file correlation diagram
     * @return EventListener
     */
    private EventListener createEventListener(EditResourceServiceAsync editResourceService, long projectId, ModelingProjectView modelingProjectView, Node n) {
        return new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                if (Event.ONCLICK == event.getTypeInt()) {
                    modelingProjectView.getTreeGrid().deselectAllRecords();
                    Tree tree = modelingProjectView.getTree();
                    FileTreeNodeFactory fileTreeNodeFactory = modelingProjectView.getFileTreeNodeFactory();
                    editResourceService.getTargetToRootDirIds(n.getFileId(), new AsyncCallback<List<Long>>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            SC.warn(caught.getMessage());
                        }

                        @Override
                        public void onSuccess(List<Long> result) {
                            SearchFileViewPart.openParentFolder(modelingProjectView, result, new PostProcessHandler() {
                                @Override
                                public void execute() {
                                    SearchFileViewPart.selectTargetNode(modelingProjectView, n.getFileId(), tree, fileTreeNodeFactory);
                                }
                            });
                            SearchFileViewPart.selectTargetNode(modelingProjectView, n.getFileId(), tree, fileTreeNodeFactory);
                        }
                    });
                } else if (Event.ONDBLCLICK == event.getTypeInt()) {
                    modelingProjectView.createOpenEditorTab(n.getFileId(), true);
                    FileCorrelationDiagramWindowViewPart.this.fireEvent(new CloseClickEvent(FileCorrelationDiagramWindowViewPart.this.getJsObj()));
                }
            }
        };
    }
}
