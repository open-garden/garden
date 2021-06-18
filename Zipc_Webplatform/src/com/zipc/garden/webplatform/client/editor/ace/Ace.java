package com.zipc.garden.webplatform.client.editor.ace;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * It is a class for interoperating Java and Ace.js.
 * @see <a href="http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsJsInterop.html">JsInterop</a><br>
 *      Importing a type from an external JavaScript script
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "ace")
public class Ace {
    @JsMethod
    public static native Editor edit(String id);
}
