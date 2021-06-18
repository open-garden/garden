package com.zipc.garden.webplatform.client.view.viz;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * <pre>
 * It is a class for interoperating Java and Viz.js.
 * Importing a type from an external JavaScript script
 * @see <a href="https://github.com/mdaines/viz.js/">viz</a>
 * </pre>
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class Viz {
    public native Promise renderSVGElement(String string);

    public native Promise renderString(String string);

    public native Promise renderImageElement(String string);

    @JsOverlay
    public final Promise renderSVGElement(Digraph digraph) {
        return renderSVGElement(digraph.toString());
    }
}
