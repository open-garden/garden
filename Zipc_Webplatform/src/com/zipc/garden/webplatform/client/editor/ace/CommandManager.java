package com.zipc.garden.webplatform.client.editor.ace;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * It is a class for interoperating Java and Ace.js. {@link Ace}<br>
 * The keyboard operation commands on the editor are managed.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "CommandManager")
public class CommandManager {

    public JsObject commands;

    public native void addCommand(JsObject obj);

    public native void on(String eventType, function fn);

    @JsFunction
    public interface function {
        void call(Object event);
    }
}
