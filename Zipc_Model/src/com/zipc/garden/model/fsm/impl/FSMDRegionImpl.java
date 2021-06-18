/**
 */
package com.zipc.garden.model.fsm.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.fsm.FSMDRegion;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>DRegion</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDRegionImpl#getStates <em>States</em>}</li>
 * </ul>
 * @generated
 */
public class FSMDRegionImpl extends MinimalEObjectImpl.Container implements FSMDRegion {
    /**
     * The cached value of the '{@link #getStates() <em>States</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getStates()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FSMDState> states;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FSMDRegionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FSMPackage.Literals.FSM_DREGION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FSMDState> getStates() {
        if (states == null) {
            states = new EObjectContainmentEList<FSMDState>(FSMDState.class, this, FSMPackage.FSM_DREGION__STATES);
        }
        return states;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FSMPackage.FSM_DREGION__STATES:
            return ((InternalEList<?>) getStates()).basicRemove(otherEnd, msgs);
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
        case FSMPackage.FSM_DREGION__STATES:
            return getStates();
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
        case FSMPackage.FSM_DREGION__STATES:
            getStates().clear();
            getStates().addAll((Collection<? extends FSMDState>) newValue);
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
        case FSMPackage.FSM_DREGION__STATES:
            getStates().clear();
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
        case FSMPackage.FSM_DREGION__STATES:
            return states != null && !states.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // FSMDRegionImpl
