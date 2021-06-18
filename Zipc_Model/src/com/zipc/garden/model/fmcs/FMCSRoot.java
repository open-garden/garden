/**
 */
package com.zipc.garden.model.fmcs;

import com.zipc.garden.model.core.AbstractRootElement;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.FMCSRoot#getConstraints <em>Constraints</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSRoot()
 * @model
 * @generated
 */
public interface FMCSRoot extends AbstractRootElement {
    /**
     * Returns the value of the '<em><b>Constraints</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fmcs.FMCSConstraint}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Constraints</em>' containment reference list.
     * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSRoot_Constraints()
     * @model containment="true"
     * @generated
     */
    EList<FMCSConstraint> getConstraints();

} // FMCSRoot
