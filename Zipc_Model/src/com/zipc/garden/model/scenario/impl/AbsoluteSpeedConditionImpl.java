/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.AbsoluteSpeedCondition;
import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scenario.ValueCondition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Absolute Speed Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.AbsoluteSpeedConditionImpl#getKph <em>Kph</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AbsoluteSpeedConditionImpl extends SpeedConditionImpl implements AbsoluteSpeedCondition {
    /**
     * The cached value of the '{@link #getKph() <em>Kph</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKph()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected ValueCondition kph;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbsoluteSpeedConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.ABSOLUTE_SPEED_CONDITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ValueCondition getKph() {
        return kph;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetKph(ValueCondition newKph, NotificationChain msgs) {
        ValueCondition oldKph = kph;
        kph = newKph;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.ABSOLUTE_SPEED_CONDITION__KPH, oldKph, newKph);
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
    public void setKph(ValueCondition newKph) {
        if (newKph != kph) {
            NotificationChain msgs = null;
            if (kph != null)
                msgs = ((InternalEObject)kph).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.ABSOLUTE_SPEED_CONDITION__KPH, null, msgs);
            if (newKph != null)
                msgs = ((InternalEObject)newKph).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.ABSOLUTE_SPEED_CONDITION__KPH, null, msgs);
            msgs = basicSetKph(newKph, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.ABSOLUTE_SPEED_CONDITION__KPH, newKph, newKph));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScenarioPackage.ABSOLUTE_SPEED_CONDITION__KPH:
                return basicSetKph(null, msgs);
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
            case ScenarioPackage.ABSOLUTE_SPEED_CONDITION__KPH:
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
            case ScenarioPackage.ABSOLUTE_SPEED_CONDITION__KPH:
                setKph((ValueCondition)newValue);
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
            case ScenarioPackage.ABSOLUTE_SPEED_CONDITION__KPH:
                setKph((ValueCondition)null);
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
            case ScenarioPackage.ABSOLUTE_SPEED_CONDITION__KPH:
                return kph != null;
        }
        return super.eIsSet(featureID);
    }

} //AbsoluteSpeedConditionImpl
