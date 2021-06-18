package com.zipc.garden.webplatform.client.view.viz;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * <pre>
 * It is a class for interoperating Java and Viz.js.
 * Returns the result when the asynchronous processing of Viz.js is completed.
 * Importing a type from an external JavaScript script
 * @see <a href="https://github.com/mdaines/viz.js/">viz</a>
 * </pre>
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class Promise {
    @JsFunction
    public interface FunctionParam {
        void exec(Object e);
    }

    @JsFunction
    public interface ConstructorParam {
        void exec(FunctionParam resolve, FunctionParam reject);
    }

    @JsConstructor
    public Promise(ConstructorParam parameters) {
    }

    public native Promise then(FunctionParam f);

    @JsMethod(name = "catch")
    public native Promise catch_(FunctionParam f);
}
