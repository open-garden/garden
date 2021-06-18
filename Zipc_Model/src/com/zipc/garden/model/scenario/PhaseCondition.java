/**
 */
package com.zipc.garden.model.scenario;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Phase Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.PhaseCondition#getConditions <em>Conditions</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getPhaseCondition()
 * @model
 * @generated
 */
public interface PhaseCondition extends EObject {
    /**
     * Returns the value of the '<em><b>Conditions</b></em>' containment reference list.
     * The list contents are of type {@link com.zipc.garden.model.scenario.Condition}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Conditions</em>' containment reference list.
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getPhaseCondition_Conditions()
     * @model containment="true"
     * @generated
     */
    EList<Condition> getConditions();

} // PhaseCondition
