package com.zipc.garden.webplatform.client.editor.tp;

import com.google.gwt.core.client.JavaScriptObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * It is a class for interoperating Java and vue.js.
 * @see <a href="http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsJsInterop.html">JsInterop</a><br>
 * @see <a href="https://jp.vuejs.org/v2/guide/instance.html">VUE</a>
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class Vue {

    /**
     * Create a Vue instance.<br>
     * Vue application will be started by creating.
     * @param options JSON format data
     */
    public Vue(JavaScriptObject options) {
    }
}
