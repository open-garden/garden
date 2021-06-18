/**
 */
package com.zipc.garden.model.scenario;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Phase</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.Phase#getAction <em>Action</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.Phase#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.Phase#getNextPhases <em>Next Phases</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.Phase#getPrevPhase <em>Prev Phase</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.Phase#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getPhase()
 * @model
 * @generated
 */
public interface Phase extends EObject {
    /**
     * Returns the value of the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Action</em>' containment reference.
     * @see #setAction(PhaseAction)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getPhase_Action()
     * @model containment="true" required="true"
     * @generated
     */
    PhaseAction getAction();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.Phase#getAction <em>Action</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Action</em>' containment reference.
     * @see #getAction()
     * @generated
     */
    void setAction(PhaseAction value);

    /**
     * Returns the value of the '<em><b>Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Condition</em>' containment reference.
     * @see #setCondition(PhaseCondition)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getPhase_Condition()
     * @model containment="true" required="true"
     * @generated
     */
    PhaseCondition getCondition();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.Phase#getCondition <em>Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Condition</em>' containment reference.
     * @see #getCondition()
     * @generated
     */
    void setCondition(PhaseCondition value);

    /**
     * Returns the value of the '<em><b>Next Phases</b></em>' containment reference list.
     * The list contents are of type {@link com.zipc.garden.model.scenario.Phase}.
     * It is bidirectional and its opposite is '{@link com.zipc.garden.model.scenario.Phase#getPrevPhase <em>Prev Phase</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Next Phases</em>' containment reference list.
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getPhase_NextPhases()
     * @see com.zipc.garden.model.scenario.Phase#getPrevPhase
     * @model opposite="prevPhase" containment="true"
     * @generated
     */
    EList<Phase> getNextPhases();

    /**
     * Returns the value of the '<em><b>Prev Phase</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link com.zipc.garden.model.scenario.Phase#getNextPhases <em>Next Phases</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Prev Phase</em>' container reference.
     * @see #setPrevPhase(Phase)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getPhase_PrevPhase()
     * @see com.zipc.garden.model.scenario.Phase#getNextPhases
     * @model opposite="nextPhases" transient="false"
     * @generated
     */
    Phase getPrevPhase();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.Phase#getPrevPhase <em>Prev Phase</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Prev Phase</em>' container reference.
     * @see #getPrevPhase()
     * @generated
     */
    void setPrevPhase(Phase value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getPhase_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.Phase#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // Phase
