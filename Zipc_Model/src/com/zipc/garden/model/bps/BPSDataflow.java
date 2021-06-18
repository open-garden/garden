/**
 */
package com.zipc.garden.model.bps;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Dataflow</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.BPSDataflow#getSource <em>Source</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSDataflow#getTarget <em>Target</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bps.BPSPackage#getBPSDataflow()
 * @model
 * @generated
 */
public interface BPSDataflow extends EObject {
    /**
     * Returns the value of the '<em><b>Source</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(BPSInstanceStateMachine)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSDataflow_Source()
     * @model
     * @generated
     */
    BPSInstanceStateMachine getSource();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSDataflow#getSource <em>Source</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(BPSInstanceStateMachine value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(BPSVariable)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSDataflow_Target()
     * @model
     * @generated
     */
    BPSVariable getTarget();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSDataflow#getTarget <em>Target</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(BPSVariable value);

} // BPSDataflow
