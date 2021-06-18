package com.zipc.garden.webplatform.client.editor.ace;

import com.google.gwt.dom.client.Element;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * A class for interoperating Java and Ace.js.<br>
 * JavaScript object class prepared for Ace.js
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class JsObject {

    /** Command name of shortcut operation */
    public String name;

    /** Windows shortcut key */
    public String win;

    /** Mac shortcut keys */
    public String mac;

    /** Holds information on variables "win" and "mac" */
    public JsObject bindKey;

    /** Executed when the shortcut key is pressed */
    public Exec exec;

    /** the auto-complete function of Ace.js. */
    public Autocomplete Autocomplete;

    /**
     * It is used to display the candidates displayed by the auto-complete function before the input item. <br>
     * (Change the position of z-index)
     */
    public Element container;

    /** Used to perform autocomplete function */
    public JsObject startAutocomplete;

    @JsFunction
    public interface Exec {
        void function(Editor editor);
    }
}
