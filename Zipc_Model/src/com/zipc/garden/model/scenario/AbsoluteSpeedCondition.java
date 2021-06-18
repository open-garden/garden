/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Absolute Speed Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.AbsoluteSpeedCondition#getKph <em>Kph</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getAbsoluteSpeedCondition()
 * @model
 * @generated
 */
public interface AbsoluteSpeedCondition extends SpeedCondition {
    /**
     * Returns the value of the '<em><b>Kph</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Kph</em>' containment reference.
     * @see #setKph(ValueCondition)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getAbsoluteSpeedCondition_Kph()
     * @model containment="true" required="true"
     * @generated
     */
    ValueCondition getKph();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.AbsoluteSpeedCondition#getKph <em>Kph</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kph</em>' containment reference.
     * @see #getKph()
     * @generated
     */
    void setKph(ValueCondition value);

} // AbsoluteSpeedCondition
