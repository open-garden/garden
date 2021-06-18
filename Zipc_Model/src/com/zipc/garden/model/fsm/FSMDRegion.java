/**
 */
package com.zipc.garden.model.fsm;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>DRegion</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.FSMDRegion#getStates <em>States</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDRegion()
 * @model
 * @generated
 */
public interface FSMDRegion extends EObject {
    /**
     * Returns the value of the '<em><b>States</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fsm.FSMDState}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>States</em>' containment reference list.
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDRegion_States()
     * @model containment="true"
     * @generated
     */
    EList<FSMDState> getStates();

} // FSMDRegion
