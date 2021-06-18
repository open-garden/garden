package com.zipc.garden.core;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

/**
 * Class that defines EMF resource handled on ZipcGarden (inherits BinaryResourceImpl)
 */
public class ZGResource extends BinaryResourceImpl {

    /**
     * constructor<br>
     * Creates a resource with the specified URI.
     * @param uri
     */
    public ZGResource(URI uri) {
        super(uri);
    }
}
