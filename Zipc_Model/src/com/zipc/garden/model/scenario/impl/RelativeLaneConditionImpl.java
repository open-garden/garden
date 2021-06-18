/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.LaneDirection;
import com.zipc.garden.model.scenario.RelativeLaneCondition;
import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scenario.Vehicle;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relative Lane Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.RelativeLaneConditionImpl#getVehicle <em>Vehicle</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.RelativeLaneConditionImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.RelativeLaneConditionImpl#getOffset <em>Offset</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelativeLaneConditionImpl extends LaneConditionImpl implements RelativeLaneCondition {
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
    protected static final LaneDirection DIRECTION_EDEFAULT = LaneDirection.EQUAL;

    /**
     * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDirection()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected LaneDirection direction = DIRECTION_EDEFAULT;

    /**
     * The default value of the '{@link #getOffset() <em>Offset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOffset()
     * @generated
     * @ordered
     */
    protected static final int OFFSET_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getOffset() <em>Offset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOffset()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int offset = OFFSET_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RelativeLaneConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.RELATIVE_LANE_CONDITION;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScenarioPackage.RELATIVE_LANE_CONDITION__VEHICLE, oldVehicle, vehicle));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.RELATIVE_LANE_CONDITION__VEHICLE, oldVehicle, vehicle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public LaneDirection getDirection() {
        return direction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDirection(LaneDirection newDirection) {
        LaneDirection oldDirection = direction;
        direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.RELATIVE_LANE_CONDITION__DIRECTION, oldDirection, direction));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getOffset() {
        return offset;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setOffset(int newOffset) {
        int oldOffset = offset;
        offset = newOffset;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.RELATIVE_LANE_CONDITION__OFFSET, oldOffset, offset));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ScenarioPackage.RELATIVE_LANE_CONDITION__VEHICLE:
                if (resolve) return getVehicle();
                return basicGetVehicle();
            case ScenarioPackage.RELATIVE_LANE_CONDITION__DIRECTION:
                return getDirection();
            case ScenarioPackage.RELATIVE_LANE_CONDITION__OFFSET:
                return getOffset();
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
            case ScenarioPackage.RELATIVE_LANE_CONDITION__VEHICLE:
                setVehicle((Vehicle)newValue);
                return;
            case ScenarioPackage.RELATIVE_LANE_CONDITION__DIRECTION:
                setDirection((LaneDirection)newValue);
                return;
            case ScenarioPackage.RELATIVE_LANE_CONDITION__OFFSET:
                setOffset((Integer)newValue);
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
            case ScenarioPackage.RELATIVE_LANE_CONDITION__VEHICLE:
                setVehicle((Vehicle)null);
                return;
            case ScenarioPackage.RELATIVE_LANE_CONDITION__DIRECTION:
                setDirection(DIRECTION_EDEFAULT);
                return;
            case ScenarioPackage.RELATIVE_LANE_CONDITION__OFFSET:
                setOffset(OFFSET_EDEFAULT);
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
            case ScenarioPackage.RELATIVE_LANE_CONDITION__VEHICLE:
                return vehicle != null;
            case ScenarioPackage.RELATIVE_LANE_CONDITION__DIRECTION:
                return direction != DIRECTION_EDEFAULT;
            case ScenarioPackage.RELATIVE_LANE_CONDITION__OFFSET:
                return offset != OFFSET_EDEFAULT;
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
        result.append(", offset: ");
        result.append(offset);
        result.append(')');
        return result.toString();
    }

} //RelativeLaneConditionImpl
