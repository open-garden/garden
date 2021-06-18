/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.DistanceAction;
import com.zipc.garden.model.scenario.DistanceSetting;
import com.zipc.garden.model.scenario.ScenarioPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Distance Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.DistanceActionImpl#getDistance <em>Distance</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DistanceActionImpl extends VehicleDetailActionImpl implements DistanceAction {
    /**
     * The cached value of the '{@link #getDistance() <em>Distance</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDistance()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected DistanceSetting distance;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DistanceActionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.DISTANCE_ACTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public DistanceSetting getDistance() {
        return distance;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDistance(DistanceSetting newDistance, NotificationChain msgs) {
        DistanceSetting oldDistance = distance;
        distance = newDistance;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.DISTANCE_ACTION__DISTANCE, oldDistance, newDistance);
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
    public void setDistance(DistanceSetting newDistance) {
        if (newDistance != distance) {
            NotificationChain msgs = null;
            if (distance != null)
                msgs = ((InternalEObject)distance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.DISTANCE_ACTION__DISTANCE, null, msgs);
            if (newDistance != null)
                msgs = ((InternalEObject)newDistance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.DISTANCE_ACTION__DISTANCE, null, msgs);
            msgs = basicSetDistance(newDistance, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.DISTANCE_ACTION__DISTANCE, newDistance, newDistance));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScenarioPackage.DISTANCE_ACTION__DISTANCE:
                return basicSetDistance(null, msgs);
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
            case ScenarioPackage.DISTANCE_ACTION__DISTANCE:
                return getDistance();
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
            case ScenarioPackage.DISTANCE_ACTION__DISTANCE:
                setDistance((DistanceSetting)newValue);
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
            case ScenarioPackage.DISTANCE_ACTION__DISTANCE:
                setDistance((DistanceSetting)null);
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
            case ScenarioPackage.DISTANCE_ACTION__DISTANCE:
                return distance != null;
        }
        return super.eIsSet(featureID);
    }

} //DistanceActionImpl
