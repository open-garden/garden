/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.RelativeSpeedCondition;
import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scenario.ValueCondition;
import com.zipc.garden.model.scenario.Vehicle;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relative Speed Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.RelativeSpeedConditionImpl#getVehicle <em>Vehicle</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.RelativeSpeedConditionImpl#getKph <em>Kph</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelativeSpeedConditionImpl extends SpeedConditionImpl implements RelativeSpeedCondition {
    /**
     * The cached value of the '{@link #getVehicle() <em>Vehicle</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVehicle()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected Vehicle vehicle;

    /**
     * The cached value of the '{@link #getKph() <em>Kph</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKph()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected ValueCondition kph;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RelativeSpeedConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.RELATIVE_SPEED_CONDITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Vehicle getVehicle() {
        if (vehicle != null && vehicle.eIsProxy()) {
            InternalEObject oldVehicle = (InternalEObject)vehicle;
            vehicle = (Vehicle)eResolveProxy(oldVehicle);
            if (vehicle != oldVehicle) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScenarioPackage.RELATIVE_SPEED_CONDITION__VEHICLE, oldVehicle, vehicle));
            }
        }
        return vehicle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Vehicle basicGetVehicle() {
        return vehicle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setVehicle(Vehicle newVehicle) {
        Vehicle oldVehicle = vehicle;
        vehicle = newVehicle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.RELATIVE_SPEED_CONDITION__VEHICLE, oldVehicle, vehicle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ValueCondition getKph() {
        return kph;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetKph(ValueCondition newKph, NotificationChain msgs) {
        ValueCondition oldKph = kph;
        kph = newKph;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.RELATIVE_SPEED_CONDITION__KPH, oldKph, newKph);
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
    public void setKph(ValueCondition newKph) {
        if (newKph != kph) {
            NotificationChain msgs = null;
            if (kph != null)
                msgs = ((InternalEObject)kph).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.RELATIVE_SPEED_CONDITION__KPH, null, msgs);
            if (newKph != null)
                msgs = ((InternalEObject)newKph).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.RELATIVE_SPEED_CONDITION__KPH, null, msgs);
            msgs = basicSetKph(newKph, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.RELATIVE_SPEED_CONDITION__KPH, newKph, newKph));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScenarioPackage.RELATIVE_SPEED_CONDITION__KPH:
                return basicSetKph(null, msgs);
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
            case ScenarioPackage.RELATIVE_SPEED_CONDITION__VEHICLE:
                if (resolve) return getVehicle();
                return basicGetVehicle();
            case ScenarioPackage.RELATIVE_SPEED_CONDITION__KPH:
                return getKph();
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
            case ScenarioPackage.RELATIVE_SPEED_CONDITION__VEHICLE:
                setVehicle((Vehicle)newValue);
                return;
            case ScenarioPackage.RELATIVE_SPEED_CONDITION__KPH:
                setKph((ValueCondition)newValue);
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
            case ScenarioPackage.RELATIVE_SPEED_CONDITION__VEHICLE:
                setVehicle((Vehicle)null);
                return;
            case ScenarioPackage.RELATIVE_SPEED_CONDITION__KPH:
                setKph((ValueCondition)null);
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
            case ScenarioPackage.RELATIVE_SPEED_CONDITION__VEHICLE:
                return vehicle != null;
            case ScenarioPackage.RELATIVE_SPEED_CONDITION__KPH:
                return kph != null;
        }
        return super.eIsSet(featureID);
    }

} //RelativeSpeedConditionImpl
