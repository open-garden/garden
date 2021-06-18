package com.zipc.garden.webplatform.client.editor.xtext;

import com.google.gwt.core.client.JavaScriptObject;
import com.zipc.garden.webplatform.client.editor.ace.Editor;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Class used to create a DSL editor using Ace.js
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "xtextace")
public class XtextAce {

    @JsMethod
    public static native Editor createEditor(JavaScriptObject options);

}
