/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Absolute Acceleration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.AbsoluteAcceleration#getMpss <em>Mpss</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getAbsoluteAcceleration()
 * @model
 * @generated
 */
public interface AbsoluteAcceleration extends AccelerationSetting {
    /**
     * Returns the value of the '<em><b>Mpss</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mpss</em>' attribute.
     * @see #setMpss(double)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getAbsoluteAcceleration_Mpss()
     * @model required="true"
     * @generated
     */
    double getMpss();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.AbsoluteAcceleration#getMpss <em>Mpss</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Mpss</em>' attribute.
     * @see #getMpss()
     * @generated
     */
    void setMpss(double value);

} // AbsoluteAcceleration
