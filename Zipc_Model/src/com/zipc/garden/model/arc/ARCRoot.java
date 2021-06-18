/**
 */
package com.zipc.garden.model.arc;

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractRootElement;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.arc.ARCRoot#getLines <em>Lines</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.ARCRoot#getStates <em>States</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.arc.ARCPackage#getARCRoot()
 * @model
 * @generated
 */
public interface ARCRoot extends AbstractRootElement, AbstractDiagram {
    /**
     * Returns the value of the '<em><b>Lines</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.arc.ARCLine}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Lines</em>' containment reference list.
     * @see com.zipc.garden.model.arc.ARCPackage#getARCRoot_Lines()
     * @model containment="true"
     * @generated
     */
    EList<ARCLine> getLines();

    /**
     * Returns the value of the '<em><b>States</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.arc.ARCState}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>States</em>' containment reference list.
     * @see com.zipc.garden.model.arc.ARCPackage#getARCRoot_States()
     * @model containment="true"
     * @generated
     */
    EList<ARCState> getStates();

} // ARCRoot
