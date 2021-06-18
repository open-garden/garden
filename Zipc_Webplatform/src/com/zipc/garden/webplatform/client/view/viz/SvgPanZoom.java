package com.zipc.garden.webplatform.client.view.viz;

import com.google.gwt.dom.client.Element;
import com.google.gwt.json.client.JSONObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * <pre>
 * It is a class for interoperating Java and svg-pan-zoom.min.js.
 * Importing a type from an external JavaScript script
 * @see <a href="https://github.com/ariutta/svg-pan-zoom">svg-pan-zoom</a>
 * </pre>
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "svgPanZoom")
public class SvgPanZoom {
    public SvgPanZoom(Element svg) {
    }

    public SvgPanZoom(Element svg, JSONObject options) {
    }

    public native void zoom(double d);

    public native void disableDblClickZoom();
}
