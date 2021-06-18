/**
 */
package com.zipc.garden.model.bp.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.bp.BPEvent;
import com.zipc.garden.model.bp.BPPackage;
import com.zipc.garden.model.bp.BPState;
import com.zipc.garden.model.bp.BPStep;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Step</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.impl.BPStepImpl#getState <em>State</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPStepImpl#getEvent <em>Event</em>}</li>
 * </ul>
 * @generated
 */
public class BPStepImpl extends MinimalEObjectImpl.Container implements BPStep {
    /**
     * The cached value of the '{@link #getState() <em>State</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getState()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected BPState state;

    /**
     * The cached value of the '{@link #getEvent() <em>Event</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getEvent()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected BPEvent event;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPStepImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPPackage.Literals.BP_STEP;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPState getState() {
        if (state != null && state.eIsProxy()) {
            InternalEObject oldState = (InternalEObject) state;
            state = (BPState) eResolveProxy(oldState);
            if (state != oldState) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BPPackage.BP_STEP__STATE, oldState, state));
            }
        }
        return state;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPState basicGetState() {
        return state;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setState(BPState newState) {
        BPState oldState = state;
        state = newState;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_STEP__STATE, oldState, state));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPEvent getEvent() {
        if (event != null && event.eIsProxy()) {
            InternalEObject oldEvent = (InternalEObject) event;
            event = (BPEvent) eResolveProxy(oldEvent);
            if (event != oldEvent) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BPPackage.BP_STEP__EVENT, oldEvent, event));
            }
        }
        return event;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPEvent basicGetEvent() {
        return event;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setEvent(BPEvent newEvent) {
        BPEvent oldEvent = event;
        event = newEvent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_STEP__EVENT, oldEvent, event));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case BPPackage.BP_STEP__STATE:
            if (resolve)
                return getState();
            return basicGetState();
        case BPPackage.BP_STEP__EVENT:
            if (resolve)
                return getEvent();
            return basicGetEvent();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case BPPackage.BP_STEP__STATE:
            setState((BPState) newValue);
            return;
        case BPPackage.BP_STEP__EVENT:
            setEvent((BPEvent) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case BPPackage.BP_STEP__STATE:
            setState((BPState) null);
            return;
        case BPPackage.BP_STEP__EVENT:
            setEvent((BPEvent) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case BPPackage.BP_STEP__STATE:
            return state != null;
        case BPPackage.BP_STEP__EVENT:
            return event != null;
        }
        return super.eIsSet(featureID);
    }

    @Override
    public boolean isEmpty() {
        return (getState() == null) && (getEvent() == null);
    }

} // BPStepImpl
