/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.AbsoluteAcceleration;
import com.zipc.garden.model.scenario.ScenarioPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Absolute Acceleration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.AbsoluteAccelerationImpl#getMpss <em>Mpss</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AbsoluteAccelerationImpl extends AccelerationSettingImpl implements AbsoluteAcceleration {
    /**
     * The default value of the '{@link #getMpss() <em>Mpss</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMpss()
     * @generated
     * @ordered
     */
    protected static final double MPSS_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getMpss() <em>Mpss</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMpss()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected double mpss = MPSS_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbsoluteAccelerationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.ABSOLUTE_ACCELERATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public double getMpss() {
        return mpss;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMpss(double newMpss) {
        double oldMpss = mpss;
        mpss = newMpss;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.ABSOLUTE_ACCELERATION__MPSS, oldMpss, mpss));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ScenarioPackage.ABSOLUTE_ACCELERATION__MPSS:
                return getMpss();
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
            case ScenarioPackage.ABSOLUTE_ACCELERATION__MPSS:
                setMpss((Double)newValue);
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
            case ScenarioPackage.ABSOLUTE_ACCELERATION__MPSS:
                setMpss(MPSS_EDEFAULT);
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
            case ScenarioPackage.ABSOLUTE_ACCELERATION__MPSS:
                return mpss != MPSS_EDEFAULT;
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
        result.append(" (mpss: ");
        result.append(mpss);
        result.append(')');
        return result.toString();
    }

} //AbsoluteAccelerationImpl
