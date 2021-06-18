/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.LaneAction;
import com.zipc.garden.model.scenario.LaneSetting;
import com.zipc.garden.model.scenario.ScenarioPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lane Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.LaneActionImpl#getLane <em>Lane</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LaneActionImpl extends VehicleDetailActionImpl implements LaneAction {
    /**
     * The cached value of the '{@link #getLane() <em>Lane</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLane()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected LaneSetting lane;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LaneActionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.LANE_ACTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public LaneSetting getLane() {
        return lane;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLane(LaneSetting newLane, NotificationChain msgs) {
        LaneSetting oldLane = lane;
        lane = newLane;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.LANE_ACTION__LANE, oldLane, newLane);
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
    public void setLane(LaneSetting newLane) {
        if (newLane != lane) {
            NotificationChain msgs = null;
            if (lane != null)
                msgs = ((InternalEObject)lane).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.LANE_ACTION__LANE, null, msgs);
            if (newLane != null)
                msgs = ((InternalEObject)newLane).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.LANE_ACTION__LANE, null, msgs);
            msgs = basicSetLane(newLane, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.LANE_ACTION__LANE, newLane, newLane));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScenarioPackage.LANE_ACTION__LANE:
                return basicSetLane(null, msgs);
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
            case ScenarioPackage.LANE_ACTION__LANE:
                return getLane();
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
            case ScenarioPackage.LANE_ACTION__LANE:
                setLane((LaneSetting)newValue);
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
            case ScenarioPackage.LANE_ACTION__LANE:
                setLane((LaneSetting)null);
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
            case ScenarioPackage.LANE_ACTION__LANE:
                return lane != null;
        }
        return super.eIsSet(featureID);
    }

} //LaneActionImpl
