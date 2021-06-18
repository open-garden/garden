/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.AbsoluteSpeed;
import com.zipc.garden.model.scenario.ScenarioPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Absolute Speed</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.AbsoluteSpeedImpl#getKph <em>Kph</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AbsoluteSpeedImpl extends SpeedSettingImpl implements AbsoluteSpeed {
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
    protected AbsoluteSpeedImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.ABSOLUTE_SPEED;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.ABSOLUTE_SPEED__KPH, oldKph, kph));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ScenarioPackage.ABSOLUTE_SPEED__KPH:
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
            case ScenarioPackage.ABSOLUTE_SPEED__KPH:
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
            case ScenarioPackage.ABSOLUTE_SPEED__KPH:
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
            case ScenarioPackage.ABSOLUTE_SPEED__KPH:
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

} //AbsoluteSpeedImpl
