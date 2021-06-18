/**
 */
package com.zipc.garden.model.cb;

import com.zipc.garden.model.core.AbstractNode;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Logic</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.cb.CBLogic#getChildren <em>Children</em>}</li>
 * <li>{@link com.zipc.garden.model.cb.CBLogic#getFile <em>File</em>}</li>
 * <li>{@link com.zipc.garden.model.cb.CBLogic#getType <em>Type</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.cb.CBPackage#getCBLogic()
 * @model
 * @generated
 */
public interface CBLogic extends AbstractNode {
    /**
     * Returns the value of the '<em><b>Children</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.cb.CBLogic}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Children</em>' containment reference list.
     * @see com.zipc.garden.model.cb.CBPackage#getCBLogic_Children()
     * @model containment="true"
     * @generated
     */
    EList<CBLogic> getChildren();

    /**
     * Returns the value of the '<em><b>File</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.cb.CBFile}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>File</em>' containment reference list.
     * @see com.zipc.garden.model.cb.CBPackage#getCBLogic_File()
     * @model containment="true"
     * @generated
     */
    EList<CBFile> getFile();

    /**
     * Returns the value of the '<em><b>Type</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' containment reference.
     * @see #setType(CBLogicType)
     * @see com.zipc.garden.model.cb.CBPackage#getCBLogic_Type()
     * @model containment="true" required="true"
     * @generated
     */
    CBLogicType getType();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.cb.CBLogic#getType <em>Type</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' containment reference.
     * @see #getType()
     * @generated
     */
    void setType(CBLogicType value);

} // CBLogic
