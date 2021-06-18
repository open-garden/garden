/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scenario.TimeCondition;
import com.zipc.garden.model.scenario.VehicleDetailCondition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vehicle Detail Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.VehicleDetailConditionImpl#getTime <em>Time</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class VehicleDetailConditionImpl extends MinimalEObjectImpl.Container implements VehicleDetailCondition {
    /**
     * The cached value of the '{@link #getTime() <em>Time</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTime()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected TimeCondition time;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VehicleDetailConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.VEHICLE_DETAIL_CONDITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TimeCondition getTime() {
        return time;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTime(TimeCondition newTime, NotificationChain msgs) {
        TimeCondition oldTime = time;
        time = newTime;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.VEHICLE_DETAIL_CONDITION__TIME, oldTime, newTime);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTime(TimeCondition newTime) {
        if (newTime != time) {
            NotificationChain msgs = null;
            if (time != null)
                msgs = ((InternalEObject)time).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.VEHICLE_DETAIL_CONDITION__TIME, null, msgs);
            if (newTime != null)
                msgs = ((InternalEObject)newTime).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.VEHICLE_DETAIL_CONDITION__TIME, null, msgs);
            msgs = basicSetTime(newTime, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.VEHICLE_DETAIL_CONDITION__TIME, newTime, newTime));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScenarioPackage.VEHICLE_DETAIL_CONDITION__TIME:
                return basicSetTime(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ScenarioPackage.VEHICLE_DETAIL_CONDITION__TIME:
                return getTime();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ScenarioPackage.VEHICLE_DETAIL_CONDITION__TIME:
                setTime((TimeCondition)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case ScenarioPackage.VEHICLE_DETAIL_CONDITION__TIME:
                setTime((TimeCondition)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ScenarioPackage.VEHICLE_DETAIL_CONDITION__TIME:
                return time != null;
        }
        return super.eIsSet(featureID);
    }

} //VehicleDetailConditionImpl
