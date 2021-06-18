/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.AbsoluteAccelCondition;
import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scenario.ValueCondition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Absolute Accel Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.AbsoluteAccelConditionImpl#getMpss <em>Mpss</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AbsoluteAccelConditionImpl extends AccelConditionImpl implements AbsoluteAccelCondition {
    /**
     * The cached value of the '{@link #getMpss() <em>Mpss</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMpss()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected ValueCondition mpss;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbsoluteAccelConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.ABSOLUTE_ACCEL_CONDITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ValueCondition getMpss() {
        return mpss;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMpss(ValueCondition newMpss, NotificationChain msgs) {
        ValueCondition oldMpss = mpss;
        mpss = newMpss;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.ABSOLUTE_ACCEL_CONDITION__MPSS, oldMpss, newMpss);
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
    public void setMpss(ValueCondition newMpss) {
        if (newMpss != mpss) {
            NotificationChain msgs = null;
            if (mpss != null)
                msgs = ((InternalEObject)mpss).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.ABSOLUTE_ACCEL_CONDITION__MPSS, null, msgs);
            if (newMpss != null)
                msgs = ((InternalEObject)newMpss).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.ABSOLUTE_ACCEL_CONDITION__MPSS, null, msgs);
            msgs = basicSetMpss(newMpss, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.ABSOLUTE_ACCEL_CONDITION__MPSS, newMpss, newMpss));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScenarioPackage.ABSOLUTE_ACCEL_CONDITION__MPSS:
                return basicSetMpss(null, msgs);
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
            case ScenarioPackage.ABSOLUTE_ACCEL_CONDITION__MPSS:
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
            case ScenarioPackage.ABSOLUTE_ACCEL_CONDITION__MPSS:
                setMpss((ValueCondition)newValue);
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
            case ScenarioPackage.ABSOLUTE_ACCEL_CONDITION__MPSS:
                setMpss((ValueCondition)null);
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
            case ScenarioPackage.ABSOLUTE_ACCEL_CONDITION__MPSS:
                return mpss != null;
        }
        return super.eIsSet(featureID);
    }

} //AbsoluteAccelConditionImpl
