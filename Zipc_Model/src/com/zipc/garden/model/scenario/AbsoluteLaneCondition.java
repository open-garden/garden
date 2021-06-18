/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Absolute Lane Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.AbsoluteLaneCondition#getLaneId <em>Lane Id</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.AbsoluteLaneCondition#getOperator <em>Operator</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getAbsoluteLaneCondition()
 * @model
 * @generated
 */
public interface AbsoluteLaneCondition extends LaneCondition {
    /**
     * Returns the value of the '<em><b>Lane Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lane Id</em>' attribute.
     * @see #setLaneId(int)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getAbsoluteLaneCondition_LaneId()
     * @model required="true"
     * @generated
     */
    int getLaneId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.AbsoluteLaneCondition#getLaneId <em>Lane Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lane Id</em>' attribute.
     * @see #getLaneId()
     * @generated
     */
    void setLaneId(int value);

    /**
     * Returns the value of the '<em><b>Operator</b></em>' attribute.
     * The literals are from the enumeration {@link com.zipc.garden.model.scenario.ComparisonOperator}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operator</em>' attribute.
     * @see com.zipc.garden.model.scenario.ComparisonOperator
     * @see #setOperator(ComparisonOperator)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getAbsoluteLaneCondition_Operator()
     * @model required="true"
     * @generated
     */
    ComparisonOperator getOperator();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.AbsoluteLaneCondition#getOperator <em>Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operator</em>' attribute.
     * @see com.zipc.garden.model.scenario.ComparisonOperator
     * @see #getOperator()
     * @generated
     */
    void setOperator(ComparisonOperator value);

} // AbsoluteLaneCondition
