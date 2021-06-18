package com.zipc.garden.webplatform.client.editor.ace;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * A class for interoperating Java and Ace.js.<br>
 * Stores all the data about Editor state providing easy way to change editors state.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "EditSession")
public abstract class EditSession {

    protected native void setMode(String mode);

    public native void setUseWrapMode(boolean useWrapMode);

    public native void setTabSize(int size);

    public native UndoManager getUndoManager();

    @JsOverlay
    public final void setMode(AceMode mode) {
        this.setMode(mode.getValue());
    }

    @JsFunction
    public interface function {
        void call(Object event);
    }

    public native void on(String eventType, function fn);

}
