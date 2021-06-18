/**
 */
package com.zipc.garden.model.bp;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Spec</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.BPSpec#getPaths <em>Paths</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bp.BPPackage#getBPSpec()
 * @model
 * @generated
 */
public interface BPSpec extends EObject {
    /**
     * Returns the value of the '<em><b>Paths</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.bp.BPSpecElement}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Paths</em>' containment reference list.
     * @see com.zipc.garden.model.bp.BPPackage#getBPSpec_Paths()
     * @model containment="true" required="true"
     * @generated
     */
    EList<BPSpecElement> getPaths();

} // BPSpec
