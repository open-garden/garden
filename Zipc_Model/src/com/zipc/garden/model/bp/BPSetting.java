/**
 */
package com.zipc.garden.model.bp;

import com.zipc.garden.model.core.AbstractSetting;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Setting</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.BPSetting#getStateMachines <em>State Machines</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.BPSetting#getPattern <em>Pattern</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bp.BPPackage#getBPSetting()
 * @model
 * @generated
 */
public interface BPSetting extends AbstractSetting {
    /**
     * Returns the value of the '<em><b>State Machines</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.bp.BPStateMachine}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>State Machines</em>' containment reference list.
     * @see com.zipc.garden.model.bp.BPPackage#getBPSetting_StateMachines()
     * @model containment="true" required="true"
     * @generated
     */
    EList<BPStateMachine> getStateMachines();

    /**
     * Returns the value of the '<em><b>Pattern</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.bp.BPBehaviorPattern}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Pattern</em>' containment reference list.
     * @see com.zipc.garden.model.bp.BPPackage#getBPSetting_Pattern()
     * @model containment="true"
     * @generated
     */
    EList<BPBehaviorPattern> getPattern();

} // BPSetting
