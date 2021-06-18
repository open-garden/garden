/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Absolute Accel Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.AbsoluteAccelCondition#getMpss <em>Mpss</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getAbsoluteAccelCondition()
 * @model
 * @generated
 */
public interface AbsoluteAccelCondition extends AccelCondition {
    /**
     * Returns the value of the '<em><b>Mpss</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mpss</em>' containment reference.
     * @see #setMpss(ValueCondition)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getAbsoluteAccelCondition_Mpss()
     * @model containment="true" required="true"
     * @generated
     */
    ValueCondition getMpss();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.AbsoluteAccelCondition#getMpss <em>Mpss</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Mpss</em>' containment reference.
     * @see #getMpss()
     * @generated
     */
    void setMpss(ValueCondition value);

} // AbsoluteAccelCondition
