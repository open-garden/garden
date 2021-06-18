/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.RangeValueCondition;
import com.zipc.garden.model.scenario.ScenarioPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Range Value Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.RangeValueConditionImpl#getUpperValue <em>Upper Value</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.RangeValueConditionImpl#getLowerValue <em>Lower Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RangeValueConditionImpl extends ValueConditionImpl implements RangeValueCondition {
    /**
     * The default value of the '{@link #getUpperValue() <em>Upper Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUpperValue()
     * @generated
     * @ordered
     */
    protected static final double UPPER_VALUE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getUpperValue() <em>Upper Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUpperValue()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected double upperValue = UPPER_VALUE_EDEFAULT;

    /**
     * The default value of the '{@link #getLowerValue() <em>Lower Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLowerValue()
     * @generated
     * @ordered
     */
    protected static final double LOWER_VALUE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getLowerValue() <em>Lower Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLowerValue()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected double lowerValue = LOWER_VALUE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RangeValueConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.RANGE_VALUE_CONDITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public double getUpperValue() {
        return upperValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setUpperValue(double newUpperValue) {
        double oldUpperValue = upperValue;
        upperValue = newUpperValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.RANGE_VALUE_CONDITION__UPPER_VALUE, oldUpperValue, upperValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public double getLowerValue() {
        return lowerValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLowerValue(double newLowerValue) {
        double oldLowerValue = lowerValue;
        lowerValue = newLowerValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.RANGE_VALUE_CONDITION__LOWER_VALUE, oldLowerValue, lowerValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ScenarioPackage.RANGE_VALUE_CONDITION__UPPER_VALUE:
                return getUpperValue();
            case ScenarioPackage.RANGE_VALUE_CONDITION__LOWER_VALUE:
                return getLowerValue();
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
            case ScenarioPackage.RANGE_VALUE_CONDITION__UPPER_VALUE:
                setUpperValue((Double)newValue);
                return;
            case ScenarioPackage.RANGE_VALUE_CONDITION__LOWER_VALUE:
                setLowerValue((Double)newValue);
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
            case ScenarioPackage.RANGE_VALUE_CONDITION__UPPER_VALUE:
                setUpperValue(UPPER_VALUE_EDEFAULT);
                return;
            case ScenarioPackage.RANGE_VALUE_CONDITION__LOWER_VALUE:
                setLowerValue(LOWER_VALUE_EDEFAULT);
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
            case ScenarioPackage.RANGE_VALUE_CONDITION__UPPER_VALUE:
                return upperValue != UPPER_VALUE_EDEFAULT;
            case ScenarioPackage.RANGE_VALUE_CONDITION__LOWER_VALUE:
                return lowerValue != LOWER_VALUE_EDEFAULT;
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
        result.append(" (upperValue: ");
        result.append(upperValue);
        result.append(", lowerValue: ");
        result.append(lowerValue);
        result.append(')');
        return result.toString();
    }

} //RangeValueConditionImpl
