/**
 */
package com.zipc.garden.model.bps;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Instance Arc</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceArc#getOriginalUuid <em>Original Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceArc#getStateMachines <em>State Machines</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceArc#getDataflows <em>Dataflows</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceArc()
 * @model
 * @generated
 */
public interface BPSInstanceArc extends BPSInstanceElement {
    /**
     * Returns the value of the '<em><b>Original Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Original Uuid</em>' attribute.
     * @see #setOriginalUuid(String)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceArc_OriginalUuid()
     * @model
     * @generated
     */
    String getOriginalUuid();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSInstanceArc#getOriginalUuid <em>Original Uuid</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Original Uuid</em>' attribute.
     * @see #getOriginalUuid()
     * @generated
     */
    void setOriginalUuid(String value);

    /**
     * Returns the value of the '<em><b>State Machines</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.bps.BPSInstanceStateMachine}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>State Machines</em>' containment reference list.
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceArc_StateMachines()
     * @model containment="true"
     * @generated
     */
    EList<BPSInstanceStateMachine> getStateMachines();

    /**
     * Returns the value of the '<em><b>Dataflows</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.bps.BPSDataflow}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Dataflows</em>' containment reference list.
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceArc_Dataflows()
     * @model containment="true"
     * @generated
     */
    EList<BPSDataflow> getDataflows();

} // BPSInstanceArc
