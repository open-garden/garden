/**
 */
package com.zipc.garden.model.scenario;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vehicle Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.VehicleCondition#getVehicle <em>Vehicle</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.VehicleCondition#getConditions <em>Conditions</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getVehicleCondition()
 * @model
 * @generated
 */
public interface VehicleCondition extends Condition {
    /**
     * Returns the value of the '<em><b>Vehicle</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Vehicle</em>' reference.
     * @see #setVehicle(Vehicle)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getVehicleCondition_Vehicle()
     * @model required="true"
     * @generated
     */
    Vehicle getVehicle();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.VehicleCondition#getVehicle <em>Vehicle</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Vehicle</em>' reference.
     * @see #getVehicle()
     * @generated
     */
    void setVehicle(Vehicle value);

    /**
     * Returns the value of the '<em><b>Conditions</b></em>' containment reference list.
     * The list contents are of type {@link com.zipc.garden.model.scenario.VehicleDetailCondition}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Conditions</em>' containment reference list.
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getVehicleCondition_Conditions()
     * @model containment="true" required="true"
     * @generated
     */
    EList<VehicleDetailCondition> getConditions();

} // VehicleCondition
