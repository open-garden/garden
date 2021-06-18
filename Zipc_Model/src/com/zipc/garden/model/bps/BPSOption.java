/**
 */
package com.zipc.garden.model.bps;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Option</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.BPSOption#getTargetStateMachines <em>Target State Machines</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bps.BPSPackage#getBPSOption()
 * @model abstract="true"
 * @generated
 */
public interface BPSOption extends EObject {

    /**
     * Returns the value of the '<em><b>Target State Machines</b></em>' attribute list. The list contents are of type
     * {@link java.lang.String}. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> 生成対象とするFSMのuuid <!--
     * end-model-doc -->
     * @return the value of the '<em>Target State Machines</em>' attribute list.
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSOption_TargetStateMachines()
     * @model required="true"
     * @generated
     */
    EList<String> getTargetStateMachines();

} // BPSOption
