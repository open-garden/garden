package com.zipc.garden.webplatform.client.editor.ace;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * A class for interoperating Java and Ace.js.<br>
 * This object maintains the undo stack for an EditSession.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "UndoManager")
public class UndoManager {

    public native void execute(Object options);

    public native boolean hasRedo();

    public native boolean hasUndo();

    public native void redo(boolean dontSelect);

    public native void reset();

    public native Range undo(boolean dontSelect);

    public native void bookmark(int... index);

    public native boolean isAtBookmark();

    public native int getRevision();

}
