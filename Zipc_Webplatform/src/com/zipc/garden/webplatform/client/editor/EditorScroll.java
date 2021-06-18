package com.zipc.garden.webplatform.client.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.drawing.DrawRect;
import com.smartgwt.client.widgets.drawing.events.DragMove;
import com.smartgwt.client.widgets.events.DrawEvent;
import com.smartgwt.client.widgets.events.DrawHandler;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.events.MouseDownEvent;
import com.smartgwt.client.widgets.events.MouseDownHandler;
import com.smartgwt.client.widgets.events.MouseMoveEvent;
import com.smartgwt.client.widgets.events.MouseMoveHandler;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseStillDownEvent;
import com.smartgwt.client.widgets.events.MouseStillDownHandler;
import com.smartgwt.client.widgets.events.MouseUpEvent;
import com.smartgwt.client.widgets.events.MouseUpHandler;
import com.smartgwt.client.widgets.events.MovedEvent;
import com.smartgwt.client.widgets.events.MovedHandler;
import com.smartgwt.client.widgets.events.ScrolledEvent;
import com.smartgwt.client.widgets.events.ScrolledHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemIfFunction;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.model.core.AbstractDiagram;

/**
 * This class manages canvas scrolling and object selection functionality.
 */
public class EditorScroll {

    /** The canvas that is being drawn. */
    private final DrawPane pane;

    /** The layout in which the canvas is drawn */
    private final Layout parentLayout;

    /** Menu to launch the screen to change the canvas size */
    private final MenuItem panelSizeItem;

    /** This is a menu for setting the grid spacing. */
    private final MenuItem gridItem;

    /** This is a submenu of "gridItem". Specify the grid spacing in this menu. */
    private final List<MenuItem> gridMenuList;

    /** The model class for the canvas being drawn. */
    private AbstractDiagram root;

    /** X coordinate position where "left click + space" or "mouse wheel" is pressed */
    private int dragStartPanePosLeft;

    /** Y coordinate position where "left click + space" or "mouse wheel" is pressed */
    private int dragStartPanePosTop;

    /** True if the mouse is dragged on the canvas */
    private boolean isMouseDown = false;

    /** Start position of object selection (X coordinate) */
    private int selectionStartPosLeft;

    /** Start position of object selection (Y coordinate) */
    private int selectionStartPosTop;

    /** Left mouse click down flag */
    private boolean isLeftMouseDown = false;

    /** If there is an element at the position where the canvas is pressed, that element will be stored. */
    private DrawItem mouseOverItem;

    /** Object selection diagram for selecting multiple elements on the canvas. */
    private final DrawRect selection;

    /** Label that displays the coordinates of the mouse cursor */
    private final Label positionLabel;

    /** Declaration of class of object selection process */
    private ItemSelection itemSelection;

    /** A list of event handlers related to scrolling and object selection. */
    List<HandlerRegistration> handlerRegList = new ArrayList<HandlerRegistration>();

    /** True if the space key is being pressed */
    private boolean isSpaceDown = false;

    /**
     * constructor
     * @param pane The canvas that is being drawn.
     * @param parentLayout The layout in which the canvas is drawn
     * @param root The model class for the canvas being drawn.
     */
    public EditorScroll(DrawPane pane, Layout parentLayout, AbstractDiagram root) {
        this.pane = pane;
        this.root = root;
        this.parentLayout = parentLayout;
        this.parentLayout.setOverflow(Overflow.AUTO);
        pane.setBackgroundImage(GWT.getModuleBaseURL() + "grid?size=" + root.getGridSize());
        pane.setBackgroundRepeat(BackgroundRepeat.REPEAT);
        pane.setBackgroundPosition(Integer.toString(root.getGridSize() - 1));
        pane.setBorder("1px solid blue");
        this.parentLayout.addChild(pane);

        positionLabel = new Label();
        parentLayout.addMember(positionLabel);
        positionLabel.setAlign(Alignment.RIGHT);
        positionLabel.setBackgroundColor("white");
        positionLabel.setWidth(100);
        positionLabel.setHeight(15);
        positionLabel.setKeepInParentRect(true);
        positionLabel.setContents("(0,0)");

        setPanelSize(root.getScrollX(), root.getScrollY());
        setSnapGap(root.getGridSize());

        panelSizeItem = new MenuItem("Panel Size");

        Menu gridMenu = new Menu();
        gridItem = new MenuItem("Grid");
        gridItem.setSubmenu(gridMenu);
        gridMenuList = new ArrayList<MenuItem>();
        gridMenuList.add(new MenuItem("None"));
        gridMenuList.add(new MenuItem("10"));
        gridMenuList.add(new MenuItem("20"));
        gridMenuList.add(new MenuItem("30"));
        gridMenuList.add(new MenuItem("40"));
        gridMenuList.add(new MenuItem("50"));
        gridMenu.setItems(gridMenuList.toArray(new MenuItem[gridMenuList.size()]));

        selection = new DrawRect();
        selection.setFillColor("#B9B9B9");
        selection.setFillOpacity(0.7F);
        selection.setDrawPane(pane);
        selection.setWidth(0);
        selection.setHeight(0);
        selection.setLineWidth(1);
        selection.hide();

        bind();
    }

    /**
     * A method that implements object selection.
     * @param itemSelection
     */
    public void addItemSelection(ItemSelection itemSelection) {
        this.itemSelection = itemSelection;
    }

    /**
     * Get context menu to resize canvas
     * @return menu
     */
    public MenuItem getPanelSizeItem() {
        return panelSizeItem;
    }

    /**
     * Get the menu to set the grid spacing
     * @return sub menu
     */
    public MenuItem getGridItem() {
        return gridItem;
    }

    /**
     * Set grid spacing
     * @param snapGap
     */
    public void setSnapGap(int snapGap) {
        root.setGridSize(snapGap);
        pane.setBackgroundImage(GWT.getModuleBaseURL() + "grid?size=" + snapGap);
    }

    /**
     * A method to scroll the canvas horizontally.
     * @param left Scroll position
     */
    public void setPanelLeftPosition(int left) {
        parentLayout.scrollTo(left, root.getPositionY());
    }

    /**
     * A method that scrolls the canvas vertically.
     * @param top Scroll position
     */
    public void setPanelTopPosition(int top) {
        parentLayout.scrollTo(root.getPositionX(), top);
    }

    /**
     * Method to scroll the canvas.
     * @param left Horizontal scroll position
     * @param top Vertical scroll position
     */
    public void setPanelPosition(int left, int top) {
        setPanelLeftPosition(left);
        setPanelTopPosition(top);
    }

    /**
     * Method to resize the canvas.
     * @param width Canvas width
     * @param height Canvas height
     */
    public void setPanelSize(int width, int height) {
        root.setScrollX(width);
        root.setScrollY(height);
        pane.setWidth(width);
        pane.setHeight(height);
    }

    /**
     * Method that sets the coordinates of the mouse cursor to the label.
     * @param eventX X coordinate of mouse cursor
     * @param eventY Y coordinate of mouse cursor
     */
    private void setContents(int eventX, int eventY) {
        double x = eventX - parentLayout.getPageLeft() + parentLayout.getScrollLeft();
        double y = eventY - parentLayout.getPageTop() + parentLayout.getScrollTop();
        positionLabel.setContents("(" + (int) x + "," + (int) y + ")");
    }

    /**
     * The process of always displaying the mouse cursor coordinate label in the lower right corner of the canvas
     * @param isScrolled True if the canvas has scrolled
     */
    private void moveToBottomRight(boolean isScrolled) {
        double scrollLeft = parentLayout.getScrollLeft();
        double scrollTop = parentLayout.getScrollTop();
        double width = parentLayout.getWidth();
        double height = parentLayout.getHeight();

        if (isScrolled) {
            root.setPositionX((int) scrollLeft);
            root.setPositionY((int) scrollTop);
        }

        int x = root.getPositionX() + (int) width - positionLabel.getWidth() - 25;
        int y = root.getPositionY() + (int) height - positionLabel.getHeight() - 25;
        positionLabel.moveTo(x, y);
    }

    /**
     * Initial process executed when the constructor is processed. Implement various event handlers.
     */
    private void bind() {

        panelSizeItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                openPanelSizeWindow();
            }
        });

        gridMenuList.forEach(item -> {
            final int snapGap;
            if (isInt(item.getTitle())) {
                snapGap = Integer.parseInt(item.getTitle());
            } else {
                snapGap = 1;
            }
            item.setCheckIfCondition(new MenuItemIfFunction() {
                @Override
                public boolean execute(Canvas target, Menu menu, MenuItem item) {
                    return root.getGridSize() == snapGap;
                }
            });
            item.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(MenuItemClickEvent event) {
                    setSnapGap(snapGap);
                }
            });
        });

        handlerRegList.add(pane.addMouseDownHandler(new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent event) {
                Optional<DrawItem> op = Arrays.asList(pane.getDrawItems()).stream()
                        .filter(v -> v.isPointInPath(event.getX() - pane.getAbsoluteLeft(), event.getY() - pane.getAbsoluteTop())).findFirst();
                if (op.isPresent()) {
                    mouseOverItem = op.get();
                    return;
                }
                mouseOverItem = null;
                if (!event.isLeftButtonDown() && !event.isRightButtonDown()) {
                    dragStartPanePosLeft = event.getX();
                    dragStartPanePosTop = event.getY();
                    isMouseDown = true;
                } else if (event.isLeftButtonDown() && isSpaceDown) {
                    dragStartPanePosLeft = event.getX();
                    dragStartPanePosTop = event.getY();
                    isLeftMouseDown = true;
                } else if (event.isLeftButtonDown()) {
                    pane.fireEvent(new com.smartgwt.client.widgets.events.ClickEvent(pane.getJsObj()));
                    selectionStartPosLeft = event.getX() - pane.getAbsoluteLeft();
                    selectionStartPosTop = event.getY() - pane.getAbsoluteTop();
                    selection.moveTo(selectionStartPosLeft, selectionStartPosTop);
                    selection.bringToFront();
                    selection.show();
                    isLeftMouseDown = true;
                }
            }

        }));

        handlerRegList.add(pane.addMouseUpHandler(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent event) {
                mouseOverItem = null;
                if (!event.isLeftButtonDown() && !event.isRightButtonDown()) {
                    isMouseDown = false;
                } else if (event.isLeftButtonDown()) {
                    isLeftMouseDown = false;
                    itemSelection.selectedRange(selection);
                    selection.setWidth(0);
                    selection.setHeight(0);
                    selection.hide();
                }
            }
        }));

        handlerRegList.add(pane.addMouseOutHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                if (!event.isLeftButtonDown() && !event.isRightButtonDown()) {
                    isMouseDown = false;
                } else if (event.isLeftButtonDown() && isSpaceDown) {
                    isLeftMouseDown = false;
                    isSpaceDown = false;
                } else if (event.isLeftButtonDown() && (!isInCanvas(pane, event.getX(), event.getY()))) {
                    isLeftMouseDown = false;
                    itemSelection.selectedRange(selection);
                    selection.setWidth(0);
                    selection.setHeight(0);
                    selection.hide();
                }
            }
        }));
        handlerRegList.add(pane.addMouseMoveHandler(new MouseMoveHandler() {
            @Override
            public void onMouseMove(MouseMoveEvent event) {
                if (KeyNames.SPACE.equals(EventHandler.getKey()) && selection.getWidth() == 0 && selection.getHeight() == 0) {
                    isSpaceDown = true;
                    pane.setCursor(Cursor.MOVE);
                } else {
                    isSpaceDown = false;
                    pane.setCursor(Cursor.DEFAULT);
                }
                setContents(event.getX(), event.getY());
                if (isMouseDown || (isSpaceDown && isLeftMouseDown)) {
                    int left = dragStartPanePosLeft - event.getX();
                    int top = dragStartPanePosTop - event.getY();
                    setPanelPosition(root.getPositionX() + left, root.getPositionY() + top);
                    dragStartPanePosLeft = event.getX();
                    dragStartPanePosTop = event.getY();
                } else if (isLeftMouseDown) {
                    int fixPosX = 0;
                    int fixPosY = 0;
                    if (selectionStartPosLeft > event.getX() - pane.getAbsoluteLeft()) {
                        fixPosX = -1;
                    }
                    if (selectionStartPosTop > event.getY() - pane.getAbsoluteTop()) {
                        fixPosY = -1;
                    }
                    selection.setWidth((event.getX() - pane.getAbsoluteLeft()) - selectionStartPosLeft + fixPosX);
                    selection.setHeight((event.getY() - pane.getAbsoluteTop()) - selectionStartPosTop + fixPosY);

                    int movement = 10;
                    int interval = 50;
                    if (event.getX() < parentLayout.getPageLeft() + interval) {
                        setPanelPosition(parentLayout.getScrollLeft() - movement, parentLayout.getScrollTop());
                    } else if (event.getX() > parentLayout.getPageRight() - interval) {
                        setPanelPosition(parentLayout.getScrollLeft() + movement, parentLayout.getScrollTop());
                    }
                    if (event.getY() < parentLayout.getPageTop() + interval) {
                        setPanelPosition(parentLayout.getScrollLeft(), parentLayout.getScrollTop() - movement);
                    } else if (event.getY() > parentLayout.getPageBottom() - interval) {
                        setPanelPosition(parentLayout.getScrollLeft(), parentLayout.getScrollTop() + movement);
                    }
                }
            }
        }));
        handlerRegList.add(pane.addMouseStillDownHandler(new MouseStillDownHandler() {
            private final int movement = 10;

            @Override
            public void onMouseStillDown(MouseStillDownEvent event) {
                if (mouseOverItem == null) {
                    return;
                }
                setContents(event.getX(), event.getY());
                DragMove dragMove = new DragMove(mouseOverItem.getJsObj()) {
                    @Override
                    public int getX() {
                        return event.getX() - pane.getPageLeft();
                    };

                    @Override
                    public int getY() {
                        return event.getY() - pane.getPageTop();
                    };
                };

                if (event.getX() < parentLayout.getPageLeft()) {
                    setPanelPosition(parentLayout.getScrollLeft() - movement, parentLayout.getScrollTop());
                    mouseOverItem.fireEvent(dragMove);
                } else if (event.getX() > parentLayout.getPageRight()) {
                    setPanelPosition(parentLayout.getScrollLeft() + movement, parentLayout.getScrollTop());
                    mouseOverItem.fireEvent(dragMove);
                }
                if (event.getY() < parentLayout.getPageTop()) {
                    setPanelPosition(parentLayout.getScrollLeft(), parentLayout.getScrollTop() - movement);
                    mouseOverItem.fireEvent(dragMove);
                } else if (event.getY() > parentLayout.getPageBottom()) {
                    setPanelPosition(parentLayout.getScrollLeft(), parentLayout.getScrollTop() + movement);
                    mouseOverItem.fireEvent(dragMove);
                }
            }
        }));
        handlerRegList.add(pane.addKeyPressHandler(new KeyPressHandler() {

            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.SPACE.equals(event.getKeyName())) {
                    event.cancel();
                }
            }
        }));
        handlerRegList.add(parentLayout.addDrawHandler(new DrawHandler() {

            @Override
            public void onDraw(DrawEvent event) {
                setPanelPosition(root.getPositionX(), root.getPositionY());
                handlerRegList.add(parentLayout.addScrolledHandler(new ScrolledHandler() {
                    @Override
                    public void onScrolled(ScrolledEvent event) {
                        moveToBottomRight(true);
                    }
                }));
            }
        }));
        handlerRegList.add(positionLabel.addMovedHandler(new MovedHandler() {

            @Override
            public void onMoved(MovedEvent event) {
                moveToBottomRight(false);
            }
        }));
    }

    /**
     * The process of launching the canvas size edit screen.
     */
    private void openPanelSizeWindow() {
        final Window window = new Window();
        window.setAutoSize(true);
        window.setTitle("Panel size");
        window.setShowMinimizeButton(false);
        window.setIsModal(true);
        window.setShowModalMask(true);
        window.centerInPage();
        window.addCloseClickHandler(e -> window.markForDestroy());
        DynamicForm form = new DynamicForm();
        form.setNumCols(4);
        form.setColWidths("100", "100", "100");
        form.setAutoFocus(true);
        IntegerRangeValidator validator = new IntegerRangeValidator();
        validator.setMax(4000);
        validator.setMin(500);

        IntegerItem width = new IntegerItem("widthItem", "width");
        width.setBrowserInputType("tel");
        width.setKeyPressFilter("[0-9]");
        width.setWidth("*");
        width.setAlign(Alignment.CENTER);
        width.setRequired(true);
        width.setValidators(validator);

        IntegerItem height = new IntegerItem("heightItem", "height");
        height.setBrowserInputType("tel");
        height.setKeyPressFilter("[0-9]");
        height.setWidth("*");
        height.setAlign(Alignment.CENTER);
        height.setRequired(true);
        height.setValidators(validator);

        ButtonItem okBtn = new ButtonItem("okBtn", "O K");
        okBtn.setAlign(Alignment.CENTER);
        okBtn.setColSpan(2);
        okBtn.setEndRow(false);

        ButtonItem cancelBtn = new ButtonItem("cancelBtn", "Cancel");
        cancelBtn.setAlign(Alignment.CENTER);
        cancelBtn.setColSpan(2);
        cancelBtn.setStartRow(false);

        form.setItems(width, height, okBtn, cancelBtn);
        window.addItem(form);
        window.show();

        width.setValue(root.getScrollX());
        height.setValue(root.getScrollY());

        okBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!form.validate()) {
                    return;
                }
                setPanelSize(width.getValueAsInteger(), height.getValueAsInteger());
                window.markForDestroy();
            }
        });

        cancelBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                window.markForDestroy();
            }
        });
        form.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.ENTER.equals(event.getKeyName())) {
                    okBtn.fireEvent(new com.smartgwt.client.widgets.form.fields.events.ClickEvent(okBtn.getJsObj()));
                }
            }
        });
    }

    /**
     * A function that checks whether the argument is a numeric type.
     * @param str
     * @return True for numeric types
     */
    private final static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * A method that scrolls the canvas to the coordinate position of the argument.
     * @param x The X coordinate to scroll.
     * @param y The Y coordinate to scroll.
     */
    public void moveLayoutPosition(int x, int y) {
        setPanelPosition(x - (parentLayout.getRight() / 2), y - (parentLayout.getBottom() / 2));
    }

    /**
     * A function that checks if the mouse cursor is inside the canvas frame.
     * @param canvas The canvas being drawn
     * @param x Mouse cursor position (X coordinate)
     * @param y Mouse cursor position (Y coordinate)
     * @return True if the mouse cursor is in the canvas
     */
    public static boolean isInCanvas(Canvas canvas, int x, int y) {
        return canvas.getPageTop() < y && y < canvas.getPageTop() + canvas.getHeight() && canvas.getPageLeft() < x && x < canvas.getPageLeft() + canvas.getWidth();
    }

    /**
     * Interface for selecting elements on canvas.
     */
    public interface ItemSelection {

        /**
         * Please describe the process in the implementation destination so that all the elements are selected within the range
         * of "argument: selection".
         * @param selection Range of object selection.
         */
        public void selectedRange(DrawRect selection);
    }
}
