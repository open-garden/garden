package com.zipc.garden.core;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;

/**
 * Factory class used when handling EMFResource on ZipcGarden
 */
public class ZGResourceFactory implements Factory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Resource createResource(URI uri) {
        return new ZGResource(uri);
    }
}
