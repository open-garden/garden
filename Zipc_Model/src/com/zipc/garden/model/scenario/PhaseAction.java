/**
 */
package com.zipc.garden.model.scenario;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Phase Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.PhaseAction#getActions <em>Actions</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getPhaseAction()
 * @model
 * @generated
 */
public interface PhaseAction extends EObject {
    /**
     * Returns the value of the '<em><b>Actions</b></em>' containment reference list.
     * The list contents are of type {@link com.zipc.garden.model.scenario.Action}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Actions</em>' containment reference list.
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getPhaseAction_Actions()
     * @model containment="true"
     * @generated
     */
    EList<Action> getActions();

} // PhaseAction
