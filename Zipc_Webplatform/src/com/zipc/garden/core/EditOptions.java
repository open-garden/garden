package com.zipc.garden.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

/**
 * Manages the options used when loading "InputStream" or saving "OutputStream".
 */
public class EditOptions {

    /** The options specified when saving the resource to "Output Stream" are stored. */
    private static Map<Object, Object> defaultSaveOptions;

    /** Contains options to specify when loading the "InputStream" resource. */
    private static Map<Object, Object> defaultLoadOptions;

    /**
     * Optional default value to specify when saving the resource to the "OutputStream".
     * @return
     */
    public static Map<Object, Object> getDefaultSaveOptions() {
        if (defaultSaveOptions == null) {
            defaultSaveOptions = new HashMap<Object, Object>();
            defaultSaveOptions.put(BinaryResourceImpl.OPTION_VERSION, BinaryResourceImpl.BinaryIO.Version.VERSION_1_1);
            defaultSaveOptions.put(BinaryResourceImpl.OPTION_STYLE_BINARY_FLOATING_POINT, Boolean.FALSE);
        }
        return Collections.unmodifiableMap(defaultSaveOptions);
    }

    /**
     * Optional default value specified when loading the resource of "ByteArrayInputStream".
     * @return
     */
    public static Map<Object, Object> getDefaultLoadOptions() {
        if (defaultLoadOptions == null) {
            defaultLoadOptions = new HashMap<Object, Object>();
            defaultLoadOptions.put(BinaryResourceImpl.OPTION_VERSION, BinaryResourceImpl.BinaryIO.Version.VERSION_1_1);
            defaultLoadOptions.put(BinaryResourceImpl.OPTION_STYLE_BINARY_FLOATING_POINT, Boolean.FALSE);
        }
        return Collections.unmodifiableMap(defaultLoadOptions);
    }
}
