package com.zipc.garden.core;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * Manage additional commands to the list.
 */
public class ZGAddCommand extends ZGCommand {

    /** Owner of List class */
    private EObject owner;

    /** The element to add to the list */
    private EObject child;

    /** The meta object literal for the list. */
    private EReference feature;

    /** The position of the element */
    private int position;

    /**
     * constructor.<br>
     * The command to add to List is managed.
     * @param owner Owner of List class
     * @param feature The meta object literal for the list.
     * @param child The element to add to the list
     * @param position The position of the element
     */
    public ZGAddCommand(EObject owner, EReference feature, EObject child, int position) {
        this.owner = owner;
        this.child = child;
        this.feature = feature;
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean prepare() {
        if (owner == null || child == null || feature == null) {
            return false;
        }
        if (!feature.isMany()) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        ((List<EObject>) owner.eGet(feature)).add(position, child);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void undo() {
        ((List<EObject>) owner.eGet(feature)).remove(child);
    }
}
