/**
 */
package com.zipc.garden.model.scenario;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Time Duration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.TimeDuration#getMsec <em>Msec</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getTimeDuration()
 * @model
 * @generated
 */
public interface TimeDuration extends TimeCondition {
    /**
     * Returns the value of the '<em><b>Msec</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Msec</em>' attribute.
     * @see #setMsec(double)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getTimeDuration_Msec()
     * @model required="true"
     * @generated
     */
    double getMsec();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.TimeDuration#getMsec <em>Msec</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Msec</em>' attribute.
     * @see #getMsec()
     * @generated
     */
    void setMsec(double value);

} // TimeDuration
