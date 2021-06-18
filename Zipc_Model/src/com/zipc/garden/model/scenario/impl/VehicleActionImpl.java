/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scenario.Vehicle;
import com.zipc.garden.model.scenario.VehicleAction;
import com.zipc.garden.model.scenario.VehicleDetailAction;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vehicle Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.VehicleActionImpl#getVehicle <em>Vehicle</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.VehicleActionImpl#isAppear <em>Appear</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.VehicleActionImpl#getActions <em>Actions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VehicleActionImpl extends ActionImpl implements VehicleAction {
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
     * The default value of the '{@link #isAppear() <em>Appear</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isAppear()
     * @generated
     * @ordered
     */
    protected static final boolean APPEAR_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isAppear() <em>Appear</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isAppear()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected boolean appear = APPEAR_EDEFAULT;

    /**
     * The cached value of the '{@link #getActions() <em>Actions</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActions()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<VehicleDetailAction> actions;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VehicleActionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.VEHICLE_ACTION;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScenarioPackage.VEHICLE_ACTION__VEHICLE, oldVehicle, vehicle));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.VEHICLE_ACTION__VEHICLE, oldVehicle, vehicle));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isAppear() {
        return appear;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAppear(boolean newAppear) {
        boolean oldAppear = appear;
        appear = newAppear;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.VEHICLE_ACTION__APPEAR, oldAppear, appear));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<VehicleDetailAction> getActions() {
        if (actions == null) {
            actions = new EObjectContainmentEList<VehicleDetailAction>(VehicleDetailAction.class, this, ScenarioPackage.VEHICLE_ACTION__ACTIONS);
        }
        return actions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScenarioPackage.VEHICLE_ACTION__ACTIONS:
                return ((InternalEList<?>)getActions()).basicRemove(otherEnd, msgs);
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
            case ScenarioPackage.VEHICLE_ACTION__VEHICLE:
                if (resolve) return getVehicle();
                return basicGetVehicle();
            case ScenarioPackage.VEHICLE_ACTION__APPEAR:
                return isAppear();
            case ScenarioPackage.VEHICLE_ACTION__ACTIONS:
                return getActions();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ScenarioPackage.VEHICLE_ACTION__VEHICLE:
                setVehicle((Vehicle)newValue);
                return;
            case ScenarioPackage.VEHICLE_ACTION__APPEAR:
                setAppear((Boolean)newValue);
                return;
            case ScenarioPackage.VEHICLE_ACTION__ACTIONS:
                getActions().clear();
                getActions().addAll((Collection<? extends VehicleDetailAction>)newValue);
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
            case ScenarioPackage.VEHICLE_ACTION__VEHICLE:
                setVehicle((Vehicle)null);
                return;
            case ScenarioPackage.VEHICLE_ACTION__APPEAR:
                setAppear(APPEAR_EDEFAULT);
                return;
            case ScenarioPackage.VEHICLE_ACTION__ACTIONS:
                getActions().clear();
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
            case ScenarioPackage.VEHICLE_ACTION__VEHICLE:
                return vehicle != null;
            case ScenarioPackage.VEHICLE_ACTION__APPEAR:
                return appear != APPEAR_EDEFAULT;
            case ScenarioPackage.VEHICLE_ACTION__ACTIONS:
                return actions != null && !actions.isEmpty();
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
        result.append(" (appear: ");
        result.append(appear);
        result.append(')');
        return result.toString();
    }

} //VehicleActionImpl
