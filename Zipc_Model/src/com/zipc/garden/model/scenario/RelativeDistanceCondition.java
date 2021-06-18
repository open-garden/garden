/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relative Distance Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.RelativeDistanceCondition#getVehicle <em>Vehicle</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.RelativeDistanceCondition#getDirection <em>Direction</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.RelativeDistanceCondition#getMeter <em>Meter</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getRelativeDistanceCondition()
 * @model
 * @generated
 */
public interface RelativeDistanceCondition extends DistanceCondition {
    /**
     * Returns the value of the '<em><b>Vehicle</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Vehicle</em>' reference.
     * @see #setVehicle(Vehicle)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getRelativeDistanceCondition_Vehicle()
     * @model required="true"
     * @generated
     */
    Vehicle getVehicle();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.RelativeDistanceCondition#getVehicle <em>Vehicle</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Vehicle</em>' reference.
     * @see #getVehicle()
     * @generated
     */
    void setVehicle(Vehicle value);

    /**
     * Returns the value of the '<em><b>Direction</b></em>' attribute.
     * The literals are from the enumeration {@link com.zipc.garden.model.scenario.DistanceDirection}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Direction</em>' attribute.
     * @see com.zipc.garden.model.scenario.DistanceDirection
     * @see #setDirection(DistanceDirection)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getRelativeDistanceCondition_Direction()
     * @model required="true"
     * @generated
     */
    DistanceDirection getDirection();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.RelativeDistanceCondition#getDirection <em>Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Direction</em>' attribute.
     * @see com.zipc.garden.model.scenario.DistanceDirection
     * @see #getDirection()
     * @generated
     */
    void setDirection(DistanceDirection value);

    /**
     * Returns the value of the '<em><b>Meter</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Meter</em>' containment reference.
     * @see #setMeter(ValueCondition)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getRelativeDistanceCondition_Meter()
     * @model containment="true" required="true"
     * @generated
     */
    ValueCondition getMeter();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.RelativeDistanceCondition#getMeter <em>Meter</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Meter</em>' containment reference.
     * @see #getMeter()
     * @generated
     */
    void setMeter(ValueCondition value);

} // RelativeDistanceCondition
