/**
 */
package com.zipc.garden.model.bps.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.bps.BPSDataflow;
import com.zipc.garden.model.bps.BPSInstanceArc;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;
import com.zipc.garden.model.bps.BPSPackage;

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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Instance Arc</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceArcImpl#getOriginalUuid <em>Original Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceArcImpl#getStateMachines <em>State Machines</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceArcImpl#getDataflows <em>Dataflows</em>}</li>
 * </ul>
 * @generated
 */
public class BPSInstanceArcImpl extends MinimalEObjectImpl.Container implements BPSInstanceArc {
    /**
     * The default value of the '{@link #getOriginalUuid() <em>Original Uuid</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getOriginalUuid()
     * @generated
     * @ordered
     */
    protected static final String ORIGINAL_UUID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOriginalUuid() <em>Original Uuid</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getOriginalUuid()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String originalUuid = ORIGINAL_UUID_EDEFAULT;

    /**
     * The cached value of the '{@link #getStateMachines() <em>State Machines</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see #getStateMachines()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<BPSInstanceStateMachine> stateMachines;

    /**
     * The cached value of the '{@link #getDataflows() <em>Dataflows</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataflows()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<BPSDataflow> dataflows;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPSInstanceArcImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPSPackage.Literals.BPS_INSTANCE_ARC;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getOriginalUuid() {
        return originalUuid;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setOriginalUuid(String newOriginalUuid) {
        String oldOriginalUuid = originalUuid;
        originalUuid = newOriginalUuid;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_ARC__ORIGINAL_UUID, oldOriginalUuid, originalUuid));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<BPSInstanceStateMachine> getStateMachines() {
        if (stateMachines == null) {
            stateMachines = new EObjectContainmentEList<BPSInstanceStateMachine>(BPSInstanceStateMachine.class, this, BPSPackage.BPS_INSTANCE_ARC__STATE_MACHINES);
        }
        return stateMachines;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<BPSDataflow> getDataflows() {
        if (dataflows == null) {
            dataflows = new EObjectContainmentEList<BPSDataflow>(BPSDataflow.class, this, BPSPackage.BPS_INSTANCE_ARC__DATAFLOWS);
        }
        return dataflows;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BPSPackage.BPS_INSTANCE_ARC__STATE_MACHINES:
            return ((InternalEList<?>) getStateMachines()).basicRemove(otherEnd, msgs);
        case BPSPackage.BPS_INSTANCE_ARC__DATAFLOWS:
            return ((InternalEList<?>) getDataflows()).basicRemove(otherEnd, msgs);
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
        case BPSPackage.BPS_INSTANCE_ARC__ORIGINAL_UUID:
            return getOriginalUuid();
        case BPSPackage.BPS_INSTANCE_ARC__STATE_MACHINES:
            return getStateMachines();
        case BPSPackage.BPS_INSTANCE_ARC__DATAFLOWS:
            return getDataflows();
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
        case BPSPackage.BPS_INSTANCE_ARC__ORIGINAL_UUID:
            setOriginalUuid((String) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_ARC__STATE_MACHINES:
            getStateMachines().clear();
            getStateMachines().addAll((Collection<? extends BPSInstanceStateMachine>) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_ARC__DATAFLOWS:
            getDataflows().clear();
            getDataflows().addAll((Collection<? extends BPSDataflow>) newValue);
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
        case BPSPackage.BPS_INSTANCE_ARC__ORIGINAL_UUID:
            setOriginalUuid(ORIGINAL_UUID_EDEFAULT);
            return;
        case BPSPackage.BPS_INSTANCE_ARC__STATE_MACHINES:
            getStateMachines().clear();
            return;
        case BPSPackage.BPS_INSTANCE_ARC__DATAFLOWS:
            getDataflows().clear();
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
        case BPSPackage.BPS_INSTANCE_ARC__ORIGINAL_UUID:
            return ORIGINAL_UUID_EDEFAULT == null ? originalUuid != null : !ORIGINAL_UUID_EDEFAULT.equals(originalUuid);
        case BPSPackage.BPS_INSTANCE_ARC__STATE_MACHINES:
            return stateMachines != null && !stateMachines.isEmpty();
        case BPSPackage.BPS_INSTANCE_ARC__DATAFLOWS:
            return dataflows != null && !dataflows.isEmpty();
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
        result.append(" (originalUuid: ");
        result.append(originalUuid);
        result.append(')');
        return result.toString();
    }

} // BPSInstanceArcImpl
