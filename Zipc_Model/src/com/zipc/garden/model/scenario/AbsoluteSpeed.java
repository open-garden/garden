/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Absolute Speed</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.AbsoluteSpeed#getKph <em>Kph</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getAbsoluteSpeed()
 * @model
 * @generated
 */
public interface AbsoluteSpeed extends SpeedSetting {
    /**
     * Returns the value of the '<em><b>Kph</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Kph</em>' attribute.
     * @see #setKph(double)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getAbsoluteSpeed_Kph()
     * @model required="true"
     * @generated
     */
    double getKph();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.AbsoluteSpeed#getKph <em>Kph</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kph</em>' attribute.
     * @see #getKph()
     * @generated
     */
    void setKph(double value);

} // AbsoluteSpeed
