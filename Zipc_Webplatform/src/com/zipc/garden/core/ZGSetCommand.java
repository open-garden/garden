package com.zipc.garden.core;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Manages the command to set the value of the specified object.
 */
public class ZGSetCommand extends ZGCommand {

    /** Owner of Object */
    private EObject owner;

    /** model object */
    private EStructuralFeature feature;

    /** The old value of the object */
    private Object oldValue;

    /** The new value of the object */
    private Object newValue;

    /**
     * constructor.<br>
     * The command to set to Object is managed.
     * @param owner Owner of Object
     * @param feature model object
     * @param newValue The new value of the object
     */
    public ZGSetCommand(EObject owner, EStructuralFeature feature, Object newValue) {
        this.owner = owner;
        this.feature = feature;
        this.newValue = newValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean prepare() {
        if (owner == null || feature == null) {
            return false;
        }
        if (feature.isMany()) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        oldValue = owner.eGet(feature);
        owner.eSet(feature, newValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void undo() {
        owner.eSet(feature, oldValue);
    }
}
