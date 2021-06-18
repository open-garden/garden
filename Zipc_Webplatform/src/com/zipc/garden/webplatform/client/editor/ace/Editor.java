package com.zipc.garden.webplatform.client.editor.ace;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * A class for interoperating Java and Ace.js.<br>
 * Manage the editor.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Editor")
public class Editor {

    public String blockScrolling;

    public CommandManager commands;

    public native void focus();

    public native void gotoLine(int n);

    public native void setTheme(String theme);

    public native void setFontSize(int size);

    public native EditSession getSession();

    public native String getValue();

    public Autocomplete completer;

    public native void setValue(String value);

    public native void setOption(String optionName, String optionValue);

    public native void resize();

    public native void setShowPrintMargin(Boolean showPrintMargin);

}
