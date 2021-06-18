package com.zipc.garden.webplatform.client.core.screentransition;

import java.util.Map;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.Layout;

/**
 * An interface that summarizes the methods required to create a new view
 */
public interface FrameInterface {

    /**
     * Override this method and use a class that inherits from the class "{@link Layout}", if you want to use a class other than
     * the class "{@link Layout}".
     */
    public Layout createLayout();

    /**
     * @return Returns created layout. {@link #createLayout()}
     */
    public Layout getLayout();

    /**
     * This method is executed before the canvas is created.
     */
    public void entry();

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
    public Canvas[] createCanvases();

    /**
     * Write the event implementation
     */
    public void bind();

    /**
     * This method is executed after the canvas is created.
     */
    public void initialized();

    /**
     * This method is executed after the canvas is destroyed.
     */
    public void exit();

    /**
     * Set the created layout.
     * @param layout
     */
    public void setLayout(Layout layout);

    /**
     * @return Returns value converted from url parameter to map
     */
    public Map<String, String> getParam();

    /**
     * @return If false, do not check session
     */
    public boolean isCheckSession();

    /**
     * Need to override and return false, if you don't want to check the session to your created new page.
     */
    public void setCheckSession(boolean checkSession);

}
