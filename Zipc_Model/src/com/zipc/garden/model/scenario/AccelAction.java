/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Accel Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.AccelAction#getAccel <em>Accel</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getAccelAction()
 * @model
 * @generated
 */
public interface AccelAction extends VehicleDetailAction {
    /**
     * Returns the value of the '<em><b>Accel</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Accel</em>' containment reference.
     * @see #setAccel(AccelerationSetting)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getAccelAction_Accel()
     * @model containment="true" required="true"
     * @generated
     */
    AccelerationSetting getAccel();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.AccelAction#getAccel <em>Accel</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Accel</em>' containment reference.
     * @see #getAccel()
     * @generated
     */
    void setAccel(AccelerationSetting value);

} // AccelAction
