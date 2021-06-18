/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Value Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.SimpleValueCondition#getValue <em>Value</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.SimpleValueCondition#getOperator <em>Operator</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getSimpleValueCondition()
 * @model
 * @generated
 */
public interface SimpleValueCondition extends ValueCondition {
    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(double)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getSimpleValueCondition_Value()
     * @model required="true"
     * @generated
     */
    double getValue();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.SimpleValueCondition#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(double value);

    /**
     * Returns the value of the '<em><b>Operator</b></em>' attribute.
     * The literals are from the enumeration {@link com.zipc.garden.model.scenario.ComparisonOperator}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operator</em>' attribute.
     * @see com.zipc.garden.model.scenario.ComparisonOperator
     * @see #setOperator(ComparisonOperator)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getSimpleValueCondition_Operator()
     * @model required="true"
     * @generated
     */
    ComparisonOperator getOperator();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.SimpleValueCondition#getOperator <em>Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operator</em>' attribute.
     * @see com.zipc.garden.model.scenario.ComparisonOperator
     * @see #getOperator()
     * @generated
     */
    void setOperator(ComparisonOperator value);

} // SimpleValueCondition
