/**
 */
package com.zipc.garden.model.bp.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.bp.BPBehavior;
import com.zipc.garden.model.bp.BPPackage;
import com.zipc.garden.model.bp.BPStateMachine;
import com.zipc.garden.model.bp.BPStep;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Behavior</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.impl.BPBehaviorImpl#getSteps <em>Steps</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPBehaviorImpl#getStateMachineRef <em>State Machine Ref</em>}</li>
 * </ul>
 * @generated
 */
public class BPBehaviorImpl extends MinimalEObjectImpl.Container implements BPBehavior {
    /**
     * The cached value of the '{@link #getSteps() <em>Steps</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSteps()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<BPStep> steps;

    /**
     * The cached value of the '{@link #getStateMachineRef() <em>State Machine Ref</em>}' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStateMachineRef()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected BPStateMachine stateMachineRef;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPBehaviorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPPackage.Literals.BP_BEHAVIOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<BPStep> getSteps() {
        if (steps == null) {
            steps = new EObjectContainmentEList<BPStep>(BPStep.class, this, BPPackage.BP_BEHAVIOR__STEPS);
        }
        return steps;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPStateMachine getStateMachineRef() {
        if (stateMachineRef != null && stateMachineRef.eIsProxy()) {
            InternalEObject oldStateMachineRef = (InternalEObject) stateMachineRef;
            stateMachineRef = (BPStateMachine) eResolveProxy(oldStateMachineRef);
            if (stateMachineRef != oldStateMachineRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BPPackage.BP_BEHAVIOR__STATE_MACHINE_REF, oldStateMachineRef, stateMachineRef));
            }
        }
        return stateMachineRef;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPStateMachine basicGetStateMachineRef() {
        return stateMachineRef;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setStateMachineRef(BPStateMachine newStateMachineRef) {
        BPStateMachine oldStateMachineRef = stateMachineRef;
        stateMachineRef = newStateMachineRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_BEHAVIOR__STATE_MACHINE_REF, oldStateMachineRef, stateMachineRef));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BPPackage.BP_BEHAVIOR__STEPS:
            return ((InternalEList<?>) getSteps()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case BPPackage.BP_BEHAVIOR__STEPS:
            return getSteps();
        case BPPackage.BP_BEHAVIOR__STATE_MACHINE_REF:
            if (resolve)
                return getStateMachineRef();
            return basicGetStateMachineRef();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case BPPackage.BP_BEHAVIOR__STEPS:
            getSteps().clear();
            getSteps().addAll((Collection<? extends BPStep>) newValue);
            return;
        case BPPackage.BP_BEHAVIOR__STATE_MACHINE_REF:
            setStateMachineRef((BPStateMachine) newValue);
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
        case BPPackage.BP_BEHAVIOR__STEPS:
            getSteps().clear();
            return;
        case BPPackage.BP_BEHAVIOR__STATE_MACHINE_REF:
            setStateMachineRef((BPStateMachine) null);
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
        case BPPackage.BP_BEHAVIOR__STEPS:
            return steps != null && !steps.isEmpty();
        case BPPackage.BP_BEHAVIOR__STATE_MACHINE_REF:
            return stateMachineRef != null;
        }
        return super.eIsSet(featureID);
    }

} // BPBehaviorImpl
