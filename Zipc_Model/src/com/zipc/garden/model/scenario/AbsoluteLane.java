/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Absolute Lane</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.AbsoluteLane#getLaneId <em>Lane Id</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getAbsoluteLane()
 * @model
 * @generated
 */
public interface AbsoluteLane extends LaneSetting {
    /**
     * Returns the value of the '<em><b>Lane Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lane Id</em>' attribute.
     * @see #setLaneId(int)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getAbsoluteLane_LaneId()
     * @model required="true"
     * @generated
     */
    int getLaneId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.AbsoluteLane#getLaneId <em>Lane Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lane Id</em>' attribute.
     * @see #getLaneId()
     * @generated
     */
    void setLaneId(int value);

} // AbsoluteLane
