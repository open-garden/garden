/**
 */
package com.zipc.garden.model.bp;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Behavior</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.BPBehavior#getSteps <em>Steps</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.BPBehavior#getStateMachineRef <em>State Machine Ref</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bp.BPPackage#getBPBehavior()
 * @model
 * @generated
 */
public interface BPBehavior extends EObject {
    /**
     * Returns the value of the '<em><b>Steps</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.bp.BPStep}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Steps</em>' containment reference list.
     * @see com.zipc.garden.model.bp.BPPackage#getBPBehavior_Steps()
     * @model containment="true" required="true"
     * @generated
     */
    EList<BPStep> getSteps();

    /**
     * Returns the value of the '<em><b>State Machine Ref</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>State Machine Ref</em>' reference.
     * @see #setStateMachineRef(BPStateMachine)
     * @see com.zipc.garden.model.bp.BPPackage#getBPBehavior_StateMachineRef()
     * @model required="true"
     * @generated
     */
    BPStateMachine getStateMachineRef();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bp.BPBehavior#getStateMachineRef <em>State Machine Ref</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>State Machine Ref</em>' reference.
     * @see #getStateMachineRef()
     * @generated
     */
    void setStateMachineRef(BPStateMachine value);

} // BPBehavior
