package com.zipc.garden.webplatform.client.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.command.CompoundCommand;

import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.types.KeyNames;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.drawing.events.DrawEndEvent;
import com.smartgwt.client.widgets.drawing.events.DrawEndHandler;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ColorPickerItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.PickerIconClickEvent;
import com.smartgwt.client.widgets.form.fields.events.PickerIconClickHandler;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.zipc.garden.model.core.AbstractStyle;
import com.zipc.garden.webplatform.client.command.EditorCommandProvider;

/**
 * Class that manages the process of changing the style of node elements
 */
public class NodeArrange implements RefreshEndListener {

    /** Interface that summarizes the methods for changing the style of the node */
    private NodeArrangeInterface nodeArrangeInterface;

    /** Sub menu of "nodeArrangeItem" */
    private Menu nodeArrangeMenu = new Menu();

    /** Menu for changing the style of nodes */
    private MenuItem nodeArrangeItem = new MenuItem("Node Arrange");

    /** Menu for changing the node font style */
    private MenuItem fontSizeMenuItem = new MenuItem("Font Size");

    /** Menu for changing the node font color */
    private MenuItem fontColorMenuItem = new MenuItem("Font Color");

    /** Menu for changing the node fill color */
    private MenuItem fillColorMenuItem = new MenuItem("Fill Color");

    /**
     * If you want to change the style of the element of the node, you need to call this method after generating the editor.
     * @param nodeArrange The editor in which the NodeArrangeInterface is implemented
     */
    public static void add(NodeArrangeInterface nodeArrange) {
        RefreshEndListener listener = new NodeArrange(nodeArrange);
        nodeArrange.setRefreshEndListener(listener);
    }

    /**
     * Private constructor called from the add method.
     * @param nodeArrangeInterface The editor in which the NodeArrangeInterface is implemented
     */
    private NodeArrange(NodeArrangeInterface nodeArrangeInterface) {
        this.nodeArrangeInterface = nodeArrangeInterface;
        nodeArrangeMenu.setItems(fontSizeMenuItem, fontColorMenuItem, fillColorMenuItem);
        nodeArrangeItem.setSubmenu(nodeArrangeMenu);
        nodeArrangeInterface.setNodeArrangeItem(nodeArrangeItem);
        onRefreshEnd();
        bind();
    }

    /**
     * Initial process executed when the constructor is processed. Implement various event handlers.
     */
    private void bind() {
        fontSizeMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                openArrangeWindow(ArrangeItems.FONTSIZE);
            }
        });

        fontColorMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                openArrangeWindow(ArrangeItems.FONTCOLOR);
            }
        });

        fillColorMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(MenuItemClickEvent event) {
                openArrangeWindow(ArrangeItems.FILLCOLOR);
            }
        });
    }

    /**
     * Launches the edit window depending on the menu selected.
     * @param arrangeItems The ENUM associated with the selected menu
     */
    private void openArrangeWindow(ArrangeItems arrangeItems) {
        final Window winModal = new Window();
        winModal.setTitle("Node Arrange");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.setKeepInParentRect(true);
        winModal.setCanFocus(true);
        winModal.setAutoCenter(true);
        winModal.setBackgroundColor("white");
        winModal.setWidth(300);
        winModal.setHeight(90);
        winModal.addCloseClickHandler(e -> winModal.markForDestroy());

        VLayout vLayout = new VLayout();
        vLayout.setWidth100();
        vLayout.setHeight100();

        DynamicForm form = new DynamicForm();
        form.setWidth100();
        form.setNumCols(2);
        form.setAutoFocus(true);

        final TextItem item;

        switch (arrangeItems) {
        case FONTSIZE:
            item = new IntegerItem("fontSize", "Font Size");
            int fontSize = 0;
            for (AbstractStyle node : nodeArrangeInterface.getSelectedArrangeStyleNode().keySet()) {
                if (fontSize == 0) {
                    fontSize = node.getFontSize();
                    item.setValue(fontSize);
                    item.setSelectOnFocus(true);
                }
                if (fontSize != node.getFontSize()) {
                    item.setValue("");
                    break;
                }
            }
            item.setWidth("100%");
            item.setKeyPressFilter("[0-9]");
            IntegerRangeValidator validator = new IntegerRangeValidator();
            validator.setMin(1);
            validator.setMax(409);
            item.setValidators(validator);
            item.addChangedHandler(new ChangedHandler() {
                @Override
                public void onChanged(ChangedEvent event) {
                    if (!item.validate()) {
                        return;
                    }
                }
            });
            break;
        case FONTCOLOR:
            item = new ColorPickerItem("fontColor", "Font Color");
            String fontColor = "";
            for (AbstractStyle node : nodeArrangeInterface.getSelectedArrangeStyleNode().keySet()) {
                if ("".equals(fontColor)) {
                    fontColor = node.getFontColor();
                    item.setValue(fontColor);
                }
                if (!fontColor.equals(node.getFontColor())) {
                    item.setValue("");
                    break;
                }
            }
            addChangedHandler(item, winModal);
            break;
        case FILLCOLOR:
            item = new ColorPickerItem("fillColor", "Fill Color");
            String fillColor = "";
            for (AbstractStyle node : nodeArrangeInterface.getSelectedArrangeStyleNode().keySet()) {
                if ("".equals(fillColor)) {
                    fillColor = node.getFillColor();
                    item.setValue(fillColor);
                }
                if (!fillColor.equals(node.getFillColor())) {
                    item.setValue("");
                    break;
                }
            }
            addChangedHandler(item, winModal);
            break;
        default:
            item = new TextItem();
            break;
        }
        HLayout hLayout = new HLayout();
        hLayout.setHeight(30);
        hLayout.setWidth100();
        IButton okBtn = new IButton("OK");
        okBtn.setHeight100();
        okBtn.setWidth100();
        okBtn.setMargin(5);
        IButton cancelBtn = new IButton("CANCEL");
        cancelBtn.setHeight100();
        cancelBtn.setWidth100();
        cancelBtn.setMargin(5);
        LayoutSpacer spacer = new LayoutSpacer(3, "100%");
        spacer.setBackgroundColor("white");

        hLayout.addMembers(spacer, okBtn, spacer, cancelBtn, spacer);

        okBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                executeCommand(arrangeItems, winModal, item);
            }
        });

        winModal.addCloseClickHandler(new CloseClickHandler() {
            @Override
            public void onCloseClick(CloseClickEvent event) {
                winModal.markForDestroy();
            }
        });

        cancelBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
            @Override
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
                winModal.markForDestroy();
            }
        });

        winModal.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (KeyNames.ENTER.equals(event.getKeyName())) {
                    item.blurItem();
                    executeCommand(arrangeItems, winModal, item);
                }
            }
        });

        if (item instanceof ColorPickerItem) {
            ((ColorPickerItem) item).addPickerIconClickHandler(new PickerIconClickHandler() {

                @Override
                public void onPickerIconClick(PickerIconClickEvent event) {
                    ((ColorPickerItem) item).showPicker();
                    ((ColorPickerItem) item).getPicker().addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
                        @Override
                        public void onClick(ClickEvent event) {
                            if (!((ColorPickerItem) item).getPicker().isVisible()) {
                                winModal.focus();
                            }
                        }
                    });
                }
            });
        }

        form.setItems(item);
        vLayout.addMembers(form, hLayout);
        winModal.addMember(vLayout);
        winModal.show();

        if (0 == item.getValueAsString().indexOf("#")) {
            item.setSelectionRange(1, item.getValueAsString().length());
        }

    }

    /**
     * Create an event handler that will be called when the TextItem's value changes.<br>
     * Maintain focus on modal windows.
     * @param item TextItem whose Style has changed
     * @param winModal Modal window to change style
     */
    private void addChangedHandler(TextItem item, Window winModal) {
        item.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent event) {
                winModal.focus();
            }
        });
    }

    /**
     * Add it to the command to reflect the changed style in the model
     * @param arrangeItems The ENUM associated with the selected menu
     * @param winModal Style edit window
     * @param item The edited TextItem element
     */
    private void executeCommand(ArrangeItems arrangeItems, final Window winModal, final TextItem item) {
        CompoundCommand cmd = new CompoundCommand();
        Map<AbstractStyle, DrawItem> styleMap = nodeArrangeInterface.getSelectedArrangeStyleNode();
        for (AbstractStyle node : styleMap.keySet()) {
            if (item != null && item.getValue() != null && !"".equals(item.getValue())) {
                switch (arrangeItems) {
                case FONTSIZE:
                    if (!item.validate()) {
                        return;
                    }
                    cmd = EditorCommandProvider.getInstance().setNodeFontSize(cmd, node, item.getValueAsInteger());
                    break;
                case FONTCOLOR:
                    cmd = EditorCommandProvider.getInstance().setNodeFontColor(cmd, node, item.getValueAsString());
                    break;
                case FILLCOLOR:
                    cmd = EditorCommandProvider.getInstance().setNodeFillColor(cmd, node, item.getValueAsString());
                }

            }
        }
        nodeArrangeInterface.getEditManager().execute(cmd.unwrap());
        nodeArrangeInterface.doRefresh();
        winModal.markForDestroy();
    }

    /**
     * The ENUM associated with the selected menu
     */
    private enum ArrangeItems {
        FONTSIZE, FONTCOLOR, FILLCOLOR
    }

    /** A list that manages the drawing end event handler of the node */
    List<HandlerRegistration> regList = new ArrayList<>();

    /**
     * Reflects the style of the node after the editor element has finished drawing.<br>
     * {@inheritDoc}
     */
    @Override
    public void onRefreshEnd() {
        Map<AbstractStyle, DrawItem> styleMap = nodeArrangeInterface.getArrangeStyleNode();
        for (Entry<AbstractStyle, DrawItem> entry : styleMap.entrySet()) {
            if (entry.getValue().getHandlerCount(DrawEndEvent.getType()) == 0) {
                regList.add(entry.getValue().addDrawEndHandler(new DrawEndHandler() {
                    @Override
                    public void onDrawEnd(DrawEndEvent event) {
                        entry.getValue().getTitleLabel().setFontSize(entry.getKey().getFontSize());
                        if (entry.getValue().getTitleLabel().getLineColor() != entry.getKey().getFontColor()) {
                            entry.getValue().getTitleLabel().setLineColor(entry.getKey().getFontColor());
                        }
                        if (entry.getValue().getKnobs() == null || entry.getValue().getKnobs().length == 0) {
                            if (entry.getValue().getFillColor() != entry.getKey().getFillColor()) {
                                entry.getValue().setFillColor(entry.getKey().getFillColor());
                            }
                        }
                    }
                }));
            }
        }
    }
}
