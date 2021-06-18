/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Range Value Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.RangeValueCondition#getUpperValue <em>Upper Value</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.RangeValueCondition#getLowerValue <em>Lower Value</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getRangeValueCondition()
 * @model
 * @generated
 */
public interface RangeValueCondition extends ValueCondition {
    /**
     * Returns the value of the '<em><b>Upper Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Upper Value</em>' attribute.
     * @see #setUpperValue(double)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getRangeValueCondition_UpperValue()
     * @model required="true"
     * @generated
     */
    double getUpperValue();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.RangeValueCondition#getUpperValue <em>Upper Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Upper Value</em>' attribute.
     * @see #getUpperValue()
     * @generated
     */
    void setUpperValue(double value);

    /**
     * Returns the value of the '<em><b>Lower Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lower Value</em>' attribute.
     * @see #setLowerValue(double)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getRangeValueCondition_LowerValue()
     * @model required="true"
     * @generated
     */
    double getLowerValue();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.RangeValueCondition#getLowerValue <em>Lower Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lower Value</em>' attribute.
     * @see #getLowerValue()
     * @generated
     */
    void setLowerValue(double value);

} // RangeValueCondition
