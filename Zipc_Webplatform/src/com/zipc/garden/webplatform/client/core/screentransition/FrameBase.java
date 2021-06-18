package com.zipc.garden.webplatform.client.core.screentransition;

import java.util.Collections;
import java.util.Map;

import com.smartgwt.client.widgets.layout.Layout;

/**
 * Need to edit the class "{@link ViewDefine}" and extends this class, if you want to create a new view. <BR>
 * Override the constructor if you use parameters. <BR>
 * The order of execution is "{@link #entry()}", "{@link #createCanvases()}", "{@link #bind()}", "{@link #initialized()}",
 * "{@link #exit()}".
 */
public abstract class FrameBase implements FrameInterface {

    /** Contains the newly created Layout. */
    private Layout layout;

    /** Value converted from url parameter to map */
    private Map<String, String> param;

    /** Set to True if checking the session to the new page created, False otherwise. */
    private boolean checkSession = true;

    /**
     * constructor<br>
     * An empty parameter is created.
     */
    public FrameBase() {
        this(Collections.emptyMap());
    }

    /**
     * Override this if you use parameters.
     * @param param
     */
    public FrameBase(Map<String, String> param) {
        this.param = param;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void entry() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Layout createLayout() {
        return new Layout();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Layout getLayout() {
        return layout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setLayout(Layout layout) {
        this.layout = layout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialized() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getParam() {
        return param;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCheckSession() {
        return checkSession;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCheckSession(boolean checkSession) {
        this.checkSession = checkSession;
    }
}
