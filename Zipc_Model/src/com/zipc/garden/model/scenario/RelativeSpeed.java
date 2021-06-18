/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relative Speed</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.RelativeSpeed#getVehicle <em>Vehicle</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.RelativeSpeed#getKph <em>Kph</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getRelativeSpeed()
 * @model
 * @generated
 */
public interface RelativeSpeed extends SpeedSetting {
    /**
     * Returns the value of the '<em><b>Vehicle</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Vehicle</em>' reference.
     * @see #setVehicle(Vehicle)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getRelativeSpeed_Vehicle()
     * @model required="true"
     * @generated
     */
    Vehicle getVehicle();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.RelativeSpeed#getVehicle <em>Vehicle</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Vehicle</em>' reference.
     * @see #getVehicle()
     * @generated
     */
    void setVehicle(Vehicle value);

    /**
     * Returns the value of the '<em><b>Kph</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Kph</em>' attribute.
     * @see #setKph(double)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getRelativeSpeed_Kph()
     * @model required="true"
     * @generated
     */
    double getKph();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.RelativeSpeed#getKph <em>Kph</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kph</em>' attribute.
     * @see #getKph()
     * @generated
     */
    void setKph(double value);

} // RelativeSpeed
