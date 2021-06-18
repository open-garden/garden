/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.RelativeSpeed;
import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scenario.Vehicle;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relative Speed</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.RelativeSpeedImpl#getVehicle <em>Vehicle</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.RelativeSpeedImpl#getKph <em>Kph</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelativeSpeedImpl extends SpeedSettingImpl implements RelativeSpeed {
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
     * The default value of the '{@link #getKph() <em>Kph</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKph()
     * @generated
     * @ordered
     */
    protected static final double KPH_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getKph() <em>Kph</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKph()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected double kph = KPH_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RelativeSpeedImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.RELATIVE_SPEED;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScenarioPackage.RELATIVE_SPEED__VEHICLE, oldVehicle, vehicle));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.RELATIVE_SPEED__VEHICLE, oldVehicle, vehicle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public double getKph() {
        return kph;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setKph(double newKph) {
        double oldKph = kph;
        kph = newKph;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.RELATIVE_SPEED__KPH, oldKph, kph));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ScenarioPackage.RELATIVE_SPEED__VEHICLE:
                if (resolve) return getVehicle();
                return basicGetVehicle();
            case ScenarioPackage.RELATIVE_SPEED__KPH:
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
            case ScenarioPackage.RELATIVE_SPEED__VEHICLE:
                setVehicle((Vehicle)newValue);
                return;
            case ScenarioPackage.RELATIVE_SPEED__KPH:
                setKph((Double)newValue);
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
            case ScenarioPackage.RELATIVE_SPEED__VEHICLE:
                setVehicle((Vehicle)null);
                return;
            case ScenarioPackage.RELATIVE_SPEED__KPH:
                setKph(KPH_EDEFAULT);
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
            case ScenarioPackage.RELATIVE_SPEED__VEHICLE:
                return vehicle != null;
            case ScenarioPackage.RELATIVE_SPEED__KPH:
                return kph != KPH_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (kph: ");
        result.append(kph);
        result.append(')');
        return result.toString();
    }

} //RelativeSpeedImpl
