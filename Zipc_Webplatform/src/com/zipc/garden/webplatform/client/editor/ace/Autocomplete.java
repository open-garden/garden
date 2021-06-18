package com.zipc.garden.webplatform.client.editor.ace;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * It is a class for interoperating Java and Ace.js. {@link Ace}<br>
 * Manage the auto-complete function of Ace.js.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Autocomplete")
public class Autocomplete {

    public native JsObject getPopup();

    public native void showPopup(Editor editor);

    public native void cancelContextMenu();

    public native void detach();
}
