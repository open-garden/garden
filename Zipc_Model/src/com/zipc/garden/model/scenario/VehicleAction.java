/**
 */
package com.zipc.garden.model.scenario;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vehicle Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.VehicleAction#getVehicle <em>Vehicle</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.VehicleAction#isAppear <em>Appear</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.VehicleAction#getActions <em>Actions</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getVehicleAction()
 * @model
 * @generated
 */
public interface VehicleAction extends Action {
    /**
     * Returns the value of the '<em><b>Vehicle</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Vehicle</em>' reference.
     * @see #setVehicle(Vehicle)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getVehicleAction_Vehicle()
     * @model required="true"
     * @generated
     */
    Vehicle getVehicle();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.VehicleAction#getVehicle <em>Vehicle</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Vehicle</em>' reference.
     * @see #getVehicle()
     * @generated
     */
    void setVehicle(Vehicle value);

    /**
     * Returns the value of the '<em><b>Appear</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Appear</em>' attribute.
     * @see #setAppear(boolean)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getVehicleAction_Appear()
     * @model
     * @generated
     */
    boolean isAppear();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.VehicleAction#isAppear <em>Appear</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Appear</em>' attribute.
     * @see #isAppear()
     * @generated
     */
    void setAppear(boolean value);

    /**
     * Returns the value of the '<em><b>Actions</b></em>' containment reference list.
     * The list contents are of type {@link com.zipc.garden.model.scenario.VehicleDetailAction}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Actions</em>' containment reference list.
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getVehicleAction_Actions()
     * @model containment="true" required="true"
     * @generated
     */
    EList<VehicleDetailAction> getActions();

} // VehicleAction
