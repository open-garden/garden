/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scenario.SpeedAction;
import com.zipc.garden.model.scenario.SpeedSetting;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Speed Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.SpeedActionImpl#getSpeed <em>Speed</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SpeedActionImpl extends VehicleDetailActionImpl implements SpeedAction {
    /**
     * The cached value of the '{@link #getSpeed() <em>Speed</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSpeed()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected SpeedSetting speed;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SpeedActionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.SPEED_ACTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SpeedSetting getSpeed() {
        return speed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSpeed(SpeedSetting newSpeed, NotificationChain msgs) {
        SpeedSetting oldSpeed = speed;
        speed = newSpeed;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.SPEED_ACTION__SPEED, oldSpeed, newSpeed);
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
    public void setSpeed(SpeedSetting newSpeed) {
        if (newSpeed != speed) {
            NotificationChain msgs = null;
            if (speed != null)
                msgs = ((InternalEObject)speed).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.SPEED_ACTION__SPEED, null, msgs);
            if (newSpeed != null)
                msgs = ((InternalEObject)newSpeed).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.SPEED_ACTION__SPEED, null, msgs);
            msgs = basicSetSpeed(newSpeed, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SPEED_ACTION__SPEED, newSpeed, newSpeed));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScenarioPackage.SPEED_ACTION__SPEED:
                return basicSetSpeed(null, msgs);
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
            case ScenarioPackage.SPEED_ACTION__SPEED:
                return getSpeed();
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
            case ScenarioPackage.SPEED_ACTION__SPEED:
                setSpeed((SpeedSetting)newValue);
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
            case ScenarioPackage.SPEED_ACTION__SPEED:
                setSpeed((SpeedSetting)null);
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
            case ScenarioPackage.SPEED_ACTION__SPEED:
                return speed != null;
        }
        return super.eIsSet(featureID);
    }

} //SpeedActionImpl
