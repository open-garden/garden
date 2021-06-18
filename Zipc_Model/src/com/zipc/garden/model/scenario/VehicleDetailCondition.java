/**
 */
package com.zipc.garden.model.scenario;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vehicle Detail Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.VehicleDetailCondition#getTime <em>Time</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getVehicleDetailCondition()
 * @model abstract="true"
 * @generated
 */
public interface VehicleDetailCondition extends EObject {
    /**
     * Returns the value of the '<em><b>Time</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Time</em>' containment reference.
     * @see #setTime(TimeCondition)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getVehicleDetailCondition_Time()
     * @model containment="true" required="true"
     * @generated
     */
    TimeCondition getTime();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.VehicleDetailCondition#getTime <em>Time</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Time</em>' containment reference.
     * @see #getTime()
     * @generated
     */
    void setTime(TimeCondition value);

} // VehicleDetailCondition
