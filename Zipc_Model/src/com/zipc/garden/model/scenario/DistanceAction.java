/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Distance Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.DistanceAction#getDistance <em>Distance</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getDistanceAction()
 * @model
 * @generated
 */
public interface DistanceAction extends VehicleDetailAction {
    /**
     * Returns the value of the '<em><b>Distance</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Distance</em>' containment reference.
     * @see #setDistance(DistanceSetting)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getDistanceAction_Distance()
     * @model containment="true" required="true"
     * @generated
     */
    DistanceSetting getDistance();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.DistanceAction#getDistance <em>Distance</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Distance</em>' containment reference.
     * @see #getDistance()
     * @generated
     */
    void setDistance(DistanceSetting value);

} // DistanceAction
