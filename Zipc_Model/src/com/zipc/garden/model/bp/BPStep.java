/**
 */
package com.zipc.garden.model.bp;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Step</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.BPStep#getState <em>State</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.BPStep#getEvent <em>Event</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bp.BPPackage#getBPStep()
 * @model
 * @generated
 */
public interface BPStep extends EObject {
    /**
     * Returns the value of the '<em><b>State</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>State</em>' reference.
     * @see #setState(BPState)
     * @see com.zipc.garden.model.bp.BPPackage#getBPStep_State()
     * @model
     * @generated
     */
    BPState getState();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bp.BPStep#getState <em>State</em>}' reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>State</em>' reference.
     * @see #getState()
     * @generated
     */
    void setState(BPState value);

    /**
     * Returns the value of the '<em><b>Event</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Event</em>' reference.
     * @see #setEvent(BPEvent)
     * @see com.zipc.garden.model.bp.BPPackage#getBPStep_Event()
     * @model
     * @generated
     */
    BPEvent getEvent();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bp.BPStep#getEvent <em>Event</em>}' reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Event</em>' reference.
     * @see #getEvent()
     * @generated
     */
    void setEvent(BPEvent value);

    /**
     * @return StateもEventも持っていない場合true
     */
    boolean isEmpty();

} // BPStep
