/**
 */
package com.zipc.garden.model.scenario;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Phase Setting</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.PhaseSetting#getInitialPhase <em>Initial Phase</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getPhaseSetting()
 * @model
 * @generated
 */
public interface PhaseSetting extends EObject {
    /**
     * Returns the value of the '<em><b>Initial Phase</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Initial Phase</em>' containment reference.
     * @see #setInitialPhase(Phase)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getPhaseSetting_InitialPhase()
     * @model containment="true" required="true"
     * @generated
     */
    Phase getInitialPhase();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.PhaseSetting#getInitialPhase <em>Initial Phase</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Initial Phase</em>' containment reference.
     * @see #getInitialPhase()
     * @generated
     */
    void setInitialPhase(Phase value);

} // PhaseSetting
