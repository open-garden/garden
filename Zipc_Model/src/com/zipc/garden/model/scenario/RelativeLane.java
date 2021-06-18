/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relative Lane</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.RelativeLane#getVehicle <em>Vehicle</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.RelativeLane#getOffset <em>Offset</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.RelativeLane#getDirection <em>Direction</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getRelativeLane()
 * @model
 * @generated
 */
public interface RelativeLane extends LaneSetting {
    /**
     * Returns the value of the '<em><b>Vehicle</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Vehicle</em>' reference.
     * @see #setVehicle(Vehicle)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getRelativeLane_Vehicle()
     * @model required="true"
     * @generated
     */
    Vehicle getVehicle();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.RelativeLane#getVehicle <em>Vehicle</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Vehicle</em>' reference.
     * @see #getVehicle()
     * @generated
     */
    void setVehicle(Vehicle value);

    /**
     * Returns the value of the '<em><b>Offset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Offset</em>' attribute.
     * @see #setOffset(int)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getRelativeLane_Offset()
     * @model required="true"
     * @generated
     */
    int getOffset();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.RelativeLane#getOffset <em>Offset</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Offset</em>' attribute.
     * @see #getOffset()
     * @generated
     */
    void setOffset(int value);

    /**
     * Returns the value of the '<em><b>Direction</b></em>' attribute.
     * The literals are from the enumeration {@link com.zipc.garden.model.scenario.LaneDirection}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Direction</em>' attribute.
     * @see com.zipc.garden.model.scenario.LaneDirection
     * @see #setDirection(LaneDirection)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getRelativeLane_Direction()
     * @model required="true"
     * @generated
     */
    LaneDirection getDirection();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.RelativeLane#getDirection <em>Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Direction</em>' attribute.
     * @see com.zipc.garden.model.scenario.LaneDirection
     * @see #getDirection()
     * @generated
     */
    void setDirection(LaneDirection value);

} // RelativeLane
