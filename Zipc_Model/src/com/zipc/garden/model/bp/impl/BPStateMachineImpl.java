/**
 */
package com.zipc.garden.model.bp.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.bp.BPEvent;
import com.zipc.garden.model.bp.BPPackage;
import com.zipc.garden.model.bp.BPState;
import com.zipc.garden.model.bp.BPStateMachine;

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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>State Machine</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.impl.BPStateMachineImpl#getFsmId <em>Fsm Id</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPStateMachineImpl#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPStateMachineImpl#getStates <em>States</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPStateMachineImpl#getEvents <em>Events</em>}</li>
 * </ul>
 * @generated
 */
public class BPStateMachineImpl extends MinimalEObjectImpl.Container implements BPStateMachine {
    /**
     * The default value of the '{@link #getFsmId() <em>Fsm Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFsmId()
     * @generated
     * @ordered
     */
    protected static final String FSM_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFsmId() <em>Fsm Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFsmId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String fsmId = FSM_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String name = NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getStates() <em>States</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getStates()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<BPState> states;

    /**
     * The cached value of the '{@link #getEvents() <em>Events</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getEvents()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<BPEvent> events;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPStateMachineImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPPackage.Literals.BP_STATE_MACHINE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getFsmId() {
        return fsmId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFsmId(String newFsmId) {
        String oldFsmId = fsmId;
        fsmId = newFsmId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_STATE_MACHINE__FSM_ID, oldFsmId, fsmId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_STATE_MACHINE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<BPState> getStates() {
        if (states == null) {
            states = new EObjectContainmentEList<BPState>(BPState.class, this, BPPackage.BP_STATE_MACHINE__STATES);
        }
        return states;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<BPEvent> getEvents() {
        if (events == null) {
            events = new EObjectContainmentEList<BPEvent>(BPEvent.class, this, BPPackage.BP_STATE_MACHINE__EVENTS);
        }
        return events;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BPPackage.BP_STATE_MACHINE__STATES:
            return ((InternalEList<?>) getStates()).basicRemove(otherEnd, msgs);
        case BPPackage.BP_STATE_MACHINE__EVENTS:
            return ((InternalEList<?>) getEvents()).basicRemove(otherEnd, msgs);
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
        case BPPackage.BP_STATE_MACHINE__FSM_ID:
            return getFsmId();
        case BPPackage.BP_STATE_MACHINE__NAME:
            return getName();
        case BPPackage.BP_STATE_MACHINE__STATES:
            return getStates();
        case BPPackage.BP_STATE_MACHINE__EVENTS:
            return getEvents();
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
        case BPPackage.BP_STATE_MACHINE__FSM_ID:
            setFsmId((String) newValue);
            return;
        case BPPackage.BP_STATE_MACHINE__NAME:
            setName((String) newValue);
            return;
        case BPPackage.BP_STATE_MACHINE__STATES:
            getStates().clear();
            getStates().addAll((Collection<? extends BPState>) newValue);
            return;
        case BPPackage.BP_STATE_MACHINE__EVENTS:
            getEvents().clear();
            getEvents().addAll((Collection<? extends BPEvent>) newValue);
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
        case BPPackage.BP_STATE_MACHINE__FSM_ID:
            setFsmId(FSM_ID_EDEFAULT);
            return;
        case BPPackage.BP_STATE_MACHINE__NAME:
            setName(NAME_EDEFAULT);
            return;
        case BPPackage.BP_STATE_MACHINE__STATES:
            getStates().clear();
            return;
        case BPPackage.BP_STATE_MACHINE__EVENTS:
            getEvents().clear();
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
        case BPPackage.BP_STATE_MACHINE__FSM_ID:
            return FSM_ID_EDEFAULT == null ? fsmId != null : !FSM_ID_EDEFAULT.equals(fsmId);
        case BPPackage.BP_STATE_MACHINE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case BPPackage.BP_STATE_MACHINE__STATES:
            return states != null && !states.isEmpty();
        case BPPackage.BP_STATE_MACHINE__EVENTS:
            return events != null && !events.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (fsmId: ");
        result.append(fsmId);
        result.append(", name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} // BPStateMachineImpl
