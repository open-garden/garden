/**
 */
package com.zipc.garden.model.bp;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>State Machine</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.BPStateMachine#getFsmId <em>Fsm Id</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.BPStateMachine#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.BPStateMachine#getStates <em>States</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.BPStateMachine#getEvents <em>Events</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bp.BPPackage#getBPStateMachine()
 * @model
 * @generated
 */
public interface BPStateMachine extends EObject {
    /**
     * Returns the value of the '<em><b>Fsm Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The ID of FSMDStateMachine that this has been referring. <!-- end-model-doc -->
     * @return the value of the '<em>Fsm Id</em>' attribute.
     * @see #setFsmId(String)
     * @see com.zipc.garden.model.bp.BPPackage#getBPStateMachine_FsmId()
     * @model required="true"
     * @generated
     */
    String getFsmId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bp.BPStateMachine#getFsmId <em>Fsm Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Fsm Id</em>' attribute.
     * @see #getFsmId()
     * @generated
     */
    void setFsmId(String value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see com.zipc.garden.model.bp.BPPackage#getBPStateMachine_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bp.BPStateMachine#getName <em>Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>States</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.bp.BPState}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>States</em>' containment reference list.
     * @see com.zipc.garden.model.bp.BPPackage#getBPStateMachine_States()
     * @model containment="true" required="true"
     * @generated
     */
    EList<BPState> getStates();

    /**
     * Returns the value of the '<em><b>Events</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.bp.BPEvent}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Events</em>' containment reference list.
     * @see com.zipc.garden.model.bp.BPPackage#getBPStateMachine_Events()
     * @model containment="true"
     * @generated
     */
    EList<BPEvent> getEvents();

} // BPStateMachine
