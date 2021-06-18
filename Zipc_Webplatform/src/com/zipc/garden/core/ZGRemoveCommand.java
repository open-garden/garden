package com.zipc.garden.core;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * Manages the remove command on the list.
 */
public class ZGRemoveCommand extends ZGCommand {

    /** Owner of List class */
    private EObject owner;

    /** The meta object literal for the list. */
    private EReference feature;

    /** The element to remove to the list */
    private EObject child;

    /** The position of the element */
    private int position;

    /**
     * constructor.<br>
     * The command to remove to List is managed.
     * @param owner Owner of List class
     * @param feature The meta object literal for the list.
     * @param child The element to remove to the list
     */
    public ZGRemoveCommand(EObject owner, EReference feature, EObject child) {
        this.owner = owner;
        this.feature = feature;
        this.child = child;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean prepare() {
        if (owner == null || feature == null || child == null) {
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
        position = ((List<EObject>) owner.eGet(feature)).indexOf(child);
        ((List<EObject>) owner.eGet(feature)).remove(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void undo() {
        ((List<EObject>) owner.eGet(feature)).add(position, child);
    }
}
