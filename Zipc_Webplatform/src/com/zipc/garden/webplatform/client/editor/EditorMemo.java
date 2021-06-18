package com.zipc.garden.webplatform.client.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.CompoundCommand;

import com.smartgwt.client.types.KnobType;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.drawing.DrawRect;
import com.smartgwt.client.widgets.drawing.events.DragMove;
import com.smartgwt.client.widgets.drawing.events.DragMoveHandler;
import com.smartgwt.client.widgets.drawing.events.DragResizeStopEvent;
import com.smartgwt.client.widgets.drawing.events.DragResizeStopHandler;
import com.smartgwt.client.widgets.drawing.events.DragStart;
import com.smartgwt.client.widgets.drawing.events.DragStartHandler;
import com.smartgwt.client.widgets.drawing.events.DragStop;
import com.smartgwt.client.widgets.drawing.events.DragStopHandler;
import com.smartgwt.client.widgets.drawing.events.MouseOutEvent;
import com.smartgwt.client.widgets.drawing.events.MouseOutHandler;
import com.smartgwt.client.widgets.drawing.events.MouseOverEvent;
import com.smartgwt.client.widgets.drawing.events.MouseOverHandler;
import com.smartgwt.client.widgets.drawing.events.ShowContextMenuEvent;
import com.smartgwt.client.widgets.drawing.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.core.EditManager;
import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractNode;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.Memo;
import com.zipc.garden.webplatform.client.command.EditorCommandProvider;

/**
 * This class manages memos created on the canvas.
 */
public class EditorMemo {

    /** Commands for adding/editing memos are managed. */
    private EditManager manager;

    /** An abstract class that holds canvas information */
    private AbstractDiagram root;

    /** Canvas to draw memo on */
    private DrawPane drawPane;

    /** A map that associates the Memo class of the EMF model with a DrawRect on the canvas. */
    private Map<Memo, DrawRect> memos = new HashMap<>();

    /** Context menu for editing Memo */
    private MenuItem editItem;

    /** True if the mouse cursor is over the memo element */
    private boolean mouseMoveFlag = false;

    /** Difference between Y coordinate of memo element and Y coordinate of mouse click */
    private int dragTopDiff = 0;

    /** Difference between X coordinate of memo element and X coordinate of mouse click */
    private int dragLeftDiff = 0;

    /**
     * constructor
     * @param manager Editor command management class
     * @param root Model class to add memo
     * @param drawPane Canvas to add memo to
     */
    public EditorMemo(EditManager manager, AbstractDiagram root, DrawPane drawPane) {
        this.manager = manager;
        this.root = root;
        this.drawPane = drawPane;
    }

    /**
     * Get context menu for editing memo
     * @return Edit menu
     */
    public MenuItem getEditItem() {
        return editItem;
    }

    /**
     * Gets the mouseover flag for the memo element
     * @return mouseover flag
     */
    public boolean getMouseMoveFlag() {
        return mouseMoveFlag;
    }

    /**
     * Reflect the memo information saved in the model on the canvas.
     */
    public void refresh() {
        // Loop Model Memo
        root.getNodes().forEach(node -> {
            Memo memo = (Memo) node;
            if (!memos.containsKey(memo)) {
                makeNewMemo(memo);
            }
        });

        // Loop Canvas Memo
        List<Memo> delMemos = new ArrayList<>();
        memos.forEach((memo, drawRect) -> {
            Optional<AbstractNode> optMemo = root.getNodes().stream().filter(modelNode -> memo.equals(modelNode)).findFirst();
            if (!optMemo.isPresent()) {
                delMemos.add(memo);
            } else {
                drawRect.setTitle(memo.getText());
                drawRect.setLeft(memo.getLeft());
                drawRect.setTop(memo.getTop());
                drawRect.setWidth(memo.getWidth());
                drawRect.setHeight(memo.getHeight());
            }
        });

        // Canvas Memo Delete
        delMemos.forEach(memo -> {
            memos.get(memo).erase();
            memos.remove(memo);
        });
    }

    /**
     * Create a new memo element on the canvas.
     * @param memo Memo information to create
     */
    private void makeNewMemo(Memo memo) {
        DrawRect drawRect = new DrawRect();
        drawRect.setDrawPane(drawPane);
        drawRect.draw();
        drawRect.setTitle(memo.getText());
        drawRect.setCanDrag(true);
        drawRect.setCanHover(true);
        drawRect.setLeft(memo.getLeft());
        drawRect.setTop(memo.getTop());
        drawRect.setWidth(memo.getWidth());
        drawRect.setHeight(memo.getHeight());
        drawRect.setLineWidth(1);
        drawRect.setFillColor("#FFFFFF");
        drawRect.setKeepInParentRect(true);
        memos.put(memo, drawRect);

        drawRect.setContextMenu(createRightClickMenu(drawRect));

        addDragEvent(drawRect);
        addDragResizeEvent(drawRect);
        addLeftClickEvent(drawRect);
        addRightClickEvent(drawRect);
        addMouseEvent(drawRect);
    }

    /**
     * Process that is executed when the Add context menu is clicked.
     * @param text memo content
     * @param left X coordinate of memo element
     * @param top Y coordinate of memo element
     */
    public void onAdd(String text, int left, int top) {
        Memo memo = COREFactory.eINSTANCE.createMemo();
        memo.setText(text);
        memo.setLeft(left);
        memo.setTop(top);
        memo.setWidth(100);
        memo.setHeight(100);
        CompoundCommand cmd = EditorCommandProvider.getInstance().addMemo(root, memo);
        manager.execute(cmd);
        refresh();
    }

    /**
     * Process that is executed when the Edit context menu is clicked.
     * @param memo The memo model to be edited
     * @param notes The memo element to edit
     */
    public void onEdit(Memo memo, DrawRect notes) {
        int diffW = 10;
        int diffH = 80;

        final Window window = new Window();
        window.setTitle("Memo");
        window.setShowMinimizeButton(false);
        window.setIsModal(true);
        window.setShowModalMask(true);
        window.setShowFooter(true);
        window.setShowResizer(true);
        window.setKeepInParentRect(true);
        window.setBackgroundColor("white");
        window.setTop(notes.getPageTop());
        window.setLeft(notes.getPageLeft());
        window.setWidth(300);
        window.setHeight(300);
        window.setCanDragResize(true);
        window.addCloseClickHandler(e -> window.markForDestroy());

        VLayout notesLayout = new VLayout();
        notesLayout.setHeight100();
        notesLayout.setWidth100();

        HLayout buttonLayout = new HLayout();
        buttonLayout.setHeight(30);
        buttonLayout.setWidth100();
        buttonLayout.setLayoutLeftMargin(20);

        TextAreaItem textArea = new TextAreaItem();
        textArea.setValue(notes.getTitle());
        textArea.setWidth(window.getWidth() - diffW);
        textArea.setHeight(window.getHeight() - diffH);
        textArea.setShowTitle(false);
        textArea.setColSpan(3);

        IButton okBtn = new IButton("OK");
        okBtn.setHeight100();
        okBtn.setWidth100();
        okBtn.setMargin(5);
        okBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                CompoundCommand cmd = EditorCommandProvider.getInstance().editMemo(memo, textArea.getValueAsString());
                manager.execute(cmd);
                window.markForDestroy();
                refresh();
            }
        });

        IButton cancelBtn = new IButton("Cancel");
        cancelBtn.setHeight100();
        cancelBtn.setWidth100();
        cancelBtn.setMargin(5);
        cancelBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                window.markForDestroy();
            }
        });
        LayoutSpacer hspacer = new LayoutSpacer(3, "100%");
        hspacer.setBackgroundColor("white");

        DynamicForm notesForm = new DynamicForm();
        notesForm.setAutoFocus(true);
        notesForm.setMargin(5);
        notesForm.setFields(textArea);

        window.addResizedHandler(new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent event) {
                textArea.setWidth(window.getWidth() - diffW);
                textArea.setHeight(window.getHeight() - diffH);
            }
        });

        buttonLayout.addMembers(hspacer, okBtn, hspacer, cancelBtn, hspacer);
        notesLayout.addMembers(notesForm, buttonLayout);
        window.addMember(notesLayout);
        window.show();
        window.getFooter().addMember(buttonLayout, 0);

        window.addResizedHandler(new ResizedHandler() {
            @Override
            public void onResized(ResizedEvent event) {
                notesLayout.setWidth100();
            }
        });
    }

    /**
     * Process that is executed when the Delete context menu is clicked.
     * @param memo Memo model to be deleted
     */
    private void onDelete(Memo memo) {
        CompoundCommand cmd = EditorCommandProvider.getInstance().deleteMemo(root, memo);
        manager.execute(cmd);
        refresh();
    }

    /**
     * The size of the memo element is changed based on the argument.
     * @param memo Model of memo element to resize
     * @param left X coordinate after change
     * @param top Y coordinate after change
     * @param width Width after change
     * @param height Height after change
     */
    private void onResize(Memo memo, int left, int top, int width, int height) {
        CompoundCommand cmd = EditorCommandProvider.getInstance().resizeMemo(memo, left, top, width, height);
        manager.execute(cmd);
        refresh();
    }

    /**
     * Creates a right-click context menu for a memo element.
     * @param drawRect Right-clicked memo element
     * @return context menu
     */
    private Menu createRightClickMenu(DrawRect drawRect) {
        Memo memo = getKey(drawRect);

        Menu menu = new Menu();
        menu.setWidth(150);

        editItem = new MenuItem("Edit Memo");
        editItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                onEdit(memo, drawRect);
            }
        });

        MenuItem deleteItem = new MenuItem("Delete Memo");
        deleteItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                onDelete(memo);
            }
        });

        menu.setItems(editItem, deleteItem);

        return menu;
    }

    /**
     * Gets the memo model associated with the memo element.
     * @param value memo element
     * @return memo model
     */
    private Memo getKey(DrawRect value) {
        return memos.entrySet().stream().filter(entry -> value.equals(entry.getValue())).map(Map.Entry::getKey).findFirst().get();
    }

    /**
     * Gets the selected state of the memo element.
     * @param drawRect memo element.
     * @return select flag
     */
    public boolean getSelectFlag(DrawRect drawRect) {
        if (drawRect.getKnobs() == null)
            return false;
        else
            return Arrays.asList(drawRect.getKnobs()).stream().filter(type -> type.equals(KnobType.RESIZE)).findFirst().isPresent() || "blue".equals(drawRect.getLineColor());
    }

    /**
     * Get all selected memo.
     * @return Map of memo elements and models
     */
    public Map<Memo, DrawRect> getSelectedMemos() {
        return memos.entrySet().stream().filter(map -> getSelectFlag(map.getValue())).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    }

    /**
     * This method switches the selection state of the memo element.
     * @param drawRect memo element.
     * @param selectFlag select flag
     */
    public void selectMemo(DrawRect drawRect, boolean selectFlag) {
        if (selectFlag) {
            drawRect.setLineColor("blue");
            drawRect.showKnobs(KnobType.RESIZE);
        } else {
            drawRect.setLineColor("gray");
            drawRect.hideAllKnobs();
        }
    }

    /**
     * This method switches the selected state of all memo elements.
     * @param selectFlag select flag
     */
    public void selectMemoAll(boolean selectFlag) {
        memos.entrySet().forEach(map -> selectMemo(map.getValue(), selectFlag));
    }

    /**
     * Method that manages the drag event of the memo element.
     * @param drawRect memo element.
     */
    private void addDragEvent(DrawRect drawRect) {
        Memo memo = getKey(drawRect);
        drawRect.addDragStartHandler(new DragStartHandler() {
            @Override
            public void onDragStart(DragStart event) {
                dragLeftDiff = event.getX() - drawRect.getLeft();
                dragTopDiff = event.getY() - drawRect.getTop();
                drawRect.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
            }
        });
        drawRect.addDragMoveHandler(new DragMoveHandler() {
            @Override
            public void onDragMove(DragMove event) {
                event.cancel();
                int dragMoveTop = event.getY() - dragTopDiff;
                int dragMoveLeft = event.getX() - dragLeftDiff;
                if (dragMoveTop >= 0 && dragMoveTop + memo.getHeight() <= root.getScrollY()) {
                    drawRect.setTop(dragMoveTop);
                }
                if (dragMoveLeft >= 0 && dragMoveLeft + memo.getWidth() <= root.getScrollX()) {
                    drawRect.setLeft(dragMoveLeft);
                }
            }
        });
        drawRect.addDragStopHandler(new DragStopHandler() {
            @Override
            public void onDragStop(DragStop event) {
                Memo memo = getKey(drawRect);
                onResize(memo, drawRect.getLeft(), drawRect.getTop(), drawRect.getWidth(), drawRect.getHeight());
            }
        });
    }

    /**
     * Method to manage resize event of memo element.
     * @param drawRect memo element.
     */
    private void addDragResizeEvent(DrawRect drawRect) {
        drawRect.addDragResizeStopHandler(new DragResizeStopHandler() {
            @Override
            public void onDragResizeStop(DragResizeStopEvent event) {
                Memo memo = getKey(drawRect);
                onResize(memo, drawRect.getLeft(), drawRect.getTop(), drawRect.getWidth(), drawRect.getHeight());
            }
        });
    }

    /**
     * This method manages the click event of the memo element.
     * @param drawRect memo element.
     */
    private void addLeftClickEvent(DrawRect drawRect) {
        drawRect.addHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Map<Memo, DrawRect> memos = getSelectedMemos();
                memos.entrySet().stream().filter(map -> !drawRect.equals(map.getValue())).forEach(map -> {
                    selectMemo(map.getValue(), false);
                });
                selectMemo(drawRect, true);
            }
        }, ClickEvent.getType());
        drawRect.addClickHandler(new com.smartgwt.client.widgets.drawing.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.drawing.events.ClickEvent event) {
                drawRect.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
            }
        });
    }

    /**
     * Method to manage the right click event of the memo element.
     * @param drawRect memo element.
     */
    private void addRightClickEvent(DrawRect drawRect) {
        drawRect.addShowContextMenuHandler(new ShowContextMenuHandler() {
            @Override
            public void onShowContextMenu(ShowContextMenuEvent event) {
                drawRect.fireEvent(new ClickEvent(event.getFiringDrawItem().getJsObj()));
            }
        });
    }

    /**
     * Method to manage mouse over event for memo element.
     * @param drawRect memo element.
     */
    private void addMouseEvent(DrawRect drawRect) {
        drawRect.addMouseOverHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                mouseMoveFlag = true;
            }
        });
        drawRect.addMouseOutHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                mouseMoveFlag = false;
            }
        });
    }
}
