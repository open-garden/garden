package com.zipc.garden.webplatform.client.view;

import com.google.gwt.core.client.JavaScriptObject;
import com.zipc.garden.webplatform.client.view.viz.Promise;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * <pre>
 * It is a class for interoperating Java and file-util.js.
 * Used for dragging and dropping folders or files.
 * </pre>
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class FileUtil {

    /** A variable that holds the path of the dropped directory */
    public String[] folderPaths;

    /** A variable that holds the path of the dropped file */
    public String[] filePaths;

    /** Variable that holds the dropped file */
    public byte[][] fileBytes;

    /**
     * Call the convertToByte method of file-util.js. <br>
     * The information of the dropped file is stored in variables ({@link #folderPaths}, {@link #filePaths},
     * {@link #fileBytes}).
     * @param dataTransfer
     * @return
     */
    public native Promise convertToByte(JavaScriptObject dataTransfer);
}
