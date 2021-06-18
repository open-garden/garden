/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.AccelAction;
import com.zipc.garden.model.scenario.AccelerationSetting;
import com.zipc.garden.model.scenario.ScenarioPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Accel Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.AccelActionImpl#getAccel <em>Accel</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AccelActionImpl extends VehicleDetailActionImpl implements AccelAction {
    /**
     * The cached value of the '{@link #getAccel() <em>Accel</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAccel()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected AccelerationSetting accel;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AccelActionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.ACCEL_ACTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AccelerationSetting getAccel() {
        return accel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAccel(AccelerationSetting newAccel, NotificationChain msgs) {
        AccelerationSetting oldAccel = accel;
        accel = newAccel;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.ACCEL_ACTION__ACCEL, oldAccel, newAccel);
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
    public void setAccel(AccelerationSetting newAccel) {
        if (newAccel != accel) {
            NotificationChain msgs = null;
            if (accel != null)
                msgs = ((InternalEObject)accel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.ACCEL_ACTION__ACCEL, null, msgs);
            if (newAccel != null)
                msgs = ((InternalEObject)newAccel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.ACCEL_ACTION__ACCEL, null, msgs);
            msgs = basicSetAccel(newAccel, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.ACCEL_ACTION__ACCEL, newAccel, newAccel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScenarioPackage.ACCEL_ACTION__ACCEL:
                return basicSetAccel(null, msgs);
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
            case ScenarioPackage.ACCEL_ACTION__ACCEL:
                return getAccel();
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
            case ScenarioPackage.ACCEL_ACTION__ACCEL:
                setAccel((AccelerationSetting)newValue);
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
            case ScenarioPackage.ACCEL_ACTION__ACCEL:
                setAccel((AccelerationSetting)null);
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
            case ScenarioPackage.ACCEL_ACTION__ACCEL:
                return accel != null;
        }
        return super.eIsSet(featureID);
    }

} //AccelActionImpl
