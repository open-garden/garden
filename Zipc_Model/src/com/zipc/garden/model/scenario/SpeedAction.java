/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Speed Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.SpeedAction#getSpeed <em>Speed</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getSpeedAction()
 * @model
 * @generated
 */
public interface SpeedAction extends VehicleDetailAction {
    /**
     * Returns the value of the '<em><b>Speed</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Speed</em>' containment reference.
     * @see #setSpeed(SpeedSetting)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getSpeedAction_Speed()
     * @model containment="true" required="true"
     * @generated
     */
    SpeedSetting getSpeed();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.SpeedAction#getSpeed <em>Speed</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Speed</em>' containment reference.
     * @see #getSpeed()
     * @generated
     */
    void setSpeed(SpeedSetting value);

} // SpeedAction
