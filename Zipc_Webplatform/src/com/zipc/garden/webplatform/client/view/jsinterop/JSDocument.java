package com.zipc.garden.webplatform.client.view.jsinterop;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * It is a class for interoperating Java and JavaScript Document interfaces.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "document")
public class JSDocument {
    public static native void execCommand(String str);
}
