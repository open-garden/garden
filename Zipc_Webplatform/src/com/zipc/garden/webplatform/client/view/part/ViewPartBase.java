package com.zipc.garden.webplatform.client.view.part;

import java.util.Collections;
import java.util.Map;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.Layout;

/**
 * extends this class create new view part. <BR>
 * Override the constructor if you use parameters. <BR>
 * The order of execution is "{@link #entry()}", "{@link #createCanvases()}", "{@link #bind()}", "{@link #initialized()}".
 */
abstract public class ViewPartBase {

    /**
     * constructor. <br>
     * {@link #param} is created empty.
     */
    public ViewPartBase() {
        this(Collections.emptyMap());
    }

    /**
     * constructor. <br>
     * The value specified for {@link #param} is set.
     * @param param {@link #param}
     */
    public ViewPartBase(Map<String, String> param) {
        this.param = param;
        setLayout(createLayout());
        entry();
        Canvas[] members = createCanvases();
        getLayout().addMembers(members);
        bind();
        initialized();
    }

    /** Created layout */
    private Layout layout;

    /** Value converted from url parameter to map */
    private Map<String, String> param;

    /** Whether to check the session */
    private boolean checkSession = true;

    /**
     * <p>
     * Create a canvas to place in this layout.
     * </p>
     * <p>
     * Please write only GUI creation, not implementation of events as much as possible.
     * </p>
     * <b>Eg)</b>
     * 
     * <pre>
     * {@code
     *  override
     *  public Canvas[] createCanvases() {
     *      Layout body = new Layout();
     *      body.setWidth100();
     *      body.setHeight100();
     *      body.addMembers(projectpart.getCanvas(), editorpart.getCanvas());
     *
     *      VLayout vl = new VLayout();
     *      vl.setWidth100();
     *      vl.setHeight100();
     *      vl.addMember(header.getLayout());
     *      vl.addMember(body);
     *  
     *      return new Canvas[] { vl };
     *  }
     * }
     * </pre>
     * 
     * @return Canvas[]
     */
    public abstract Canvas[] createCanvases();

    /**
     * Write the event implementation
     */
    public abstract void bind();

    /**
     * This method is executed before the canvas is created.
     */
    public void entry() {

    }

    /**
     * Override this method and use a class that inherits from the class "{@link Layout}", if you want to use a class other than
     * the class "{@link Layout}".
     */
    public Layout createLayout() {
        return new Layout();
    }

    /**
     * Returns created layout.
     * @return {@link #createLayout()}
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * Set the layout.
     * @param layout {@link #layout}
     */
    private final void setLayout(Layout layout) {
        this.layout = layout;
    }

    /**
     * This method is executed after the canvas is created.
     */
    public void initialized() {

    }

    /**
     * Returns value converted from url parameter to map
     * @return {@link #param}
     */
    public Map<String, String> getParam() {
        return param;
    }

    /**
     * Returns whether to check the session.<br>
     * If false, do not check session
     * @return {@link #checkSession}
     */
    public boolean isCheckSession() {
        return checkSession;
    }

    /**
     * Need to override and return false, if you don't want to check the session to your created new page.
     */
    public void setCheckSession(boolean checkSession) {
        this.checkSession = checkSession;
    }
}
