/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.DistanceDirection;
import com.zipc.garden.model.scenario.RelativeDistance;
import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scenario.Vehicle;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relative Distance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.RelativeDistanceImpl#getVehicle <em>Vehicle</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.RelativeDistanceImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.RelativeDistanceImpl#getMeter <em>Meter</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelativeDistanceImpl extends DistanceSettingImpl implements RelativeDistance {
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
     * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDirection()
     * @generated
     * @ordered
     */
    protected static final DistanceDirection DIRECTION_EDEFAULT = DistanceDirection.FORWARD;

    /**
     * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDirection()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected DistanceDirection direction = DIRECTION_EDEFAULT;

    /**
     * The default value of the '{@link #getMeter() <em>Meter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMeter()
     * @generated
     * @ordered
     */
    protected static final double METER_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getMeter() <em>Meter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMeter()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected double meter = METER_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RelativeDistanceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.RELATIVE_DISTANCE;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScenarioPackage.RELATIVE_DISTANCE__VEHICLE, oldVehicle, vehicle));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.RELATIVE_DISTANCE__VEHICLE, oldVehicle, vehicle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public DistanceDirection getDirection() {
        return direction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDirection(DistanceDirection newDirection) {
        DistanceDirection oldDirection = direction;
        direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.RELATIVE_DISTANCE__DIRECTION, oldDirection, direction));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public double getMeter() {
        return meter;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMeter(double newMeter) {
        double oldMeter = meter;
        meter = newMeter;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.RELATIVE_DISTANCE__METER, oldMeter, meter));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ScenarioPackage.RELATIVE_DISTANCE__VEHICLE:
                if (resolve) return getVehicle();
                return basicGetVehicle();
            case ScenarioPackage.RELATIVE_DISTANCE__DIRECTION:
                return getDirection();
            case ScenarioPackage.RELATIVE_DISTANCE__METER:
                return getMeter();
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
            case ScenarioPackage.RELATIVE_DISTANCE__VEHICLE:
                setVehicle((Vehicle)newValue);
                return;
            case ScenarioPackage.RELATIVE_DISTANCE__DIRECTION:
                setDirection((DistanceDirection)newValue);
                return;
            case ScenarioPackage.RELATIVE_DISTANCE__METER:
                setMeter((Double)newValue);
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
            case ScenarioPackage.RELATIVE_DISTANCE__VEHICLE:
                setVehicle((Vehicle)null);
                return;
            case ScenarioPackage.RELATIVE_DISTANCE__DIRECTION:
                setDirection(DIRECTION_EDEFAULT);
                return;
            case ScenarioPackage.RELATIVE_DISTANCE__METER:
                setMeter(METER_EDEFAULT);
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
            case ScenarioPackage.RELATIVE_DISTANCE__VEHICLE:
                return vehicle != null;
            case ScenarioPackage.RELATIVE_DISTANCE__DIRECTION:
                return direction != DIRECTION_EDEFAULT;
            case ScenarioPackage.RELATIVE_DISTANCE__METER:
                return meter != METER_EDEFAULT;
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
        result.append(" (direction: ");
        result.append(direction);
        result.append(", meter: ");
        result.append(meter);
        result.append(')');
        return result.toString();
    }

} //RelativeDistanceImpl
