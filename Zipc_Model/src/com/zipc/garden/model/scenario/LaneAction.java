/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Lane Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.LaneAction#getLane <em>Lane</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getLaneAction()
 * @model
 * @generated
 */
public interface LaneAction extends VehicleDetailAction {
    /**
     * Returns the value of the '<em><b>Lane</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lane</em>' containment reference.
     * @see #setLane(LaneSetting)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getLaneAction_Lane()
     * @model containment="true" required="true"
     * @generated
     */
    LaneSetting getLane();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.LaneAction#getLane <em>Lane</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lane</em>' containment reference.
     * @see #getLane()
     * @generated
     */
    void setLane(LaneSetting value);

} // LaneAction
