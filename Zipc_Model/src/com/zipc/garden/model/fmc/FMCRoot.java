/**
 */
package com.zipc.garden.model.fmc;

import com.zipc.garden.model.core.AbstractRootElement;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmc.FMCRoot#getConstraints <em>Constraints</em>}</li>
 * <li>{@link com.zipc.garden.model.fmc.FMCRoot#getNodepaths <em>Nodepaths</em>}</li>
 * <li>{@link com.zipc.garden.model.fmc.FMCRoot#getDocument <em>Document</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fmc.FMCPackage#getFMCRoot()
 * @model
 * @generated
 */
public interface FMCRoot extends AbstractRootElement {
    /**
     * Returns the value of the '<em><b>Constraints</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fmc.FMCConstraint}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Constraints</em>' containment reference list.
     * @see com.zipc.garden.model.fmc.FMCPackage#getFMCRoot_Constraints()
     * @model containment="true"
     * @generated
     */
    EList<FMCConstraint> getConstraints();

    /**
     * Returns the value of the '<em><b>Nodepaths</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fmc.FMCNodePath}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Nodepaths</em>' containment reference list.
     * @see com.zipc.garden.model.fmc.FMCPackage#getFMCRoot_Nodepaths()
     * @model containment="true"
     * @generated
     */
    EList<FMCNodePath> getNodepaths();

    /**
     * Returns the value of the '<em><b>Document</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Document</em>' attribute.
     * @see #setDocument(String)
     * @see com.zipc.garden.model.fmc.FMCPackage#getFMCRoot_Document()
     * @model
     * @generated
     */
    String getDocument();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmc.FMCRoot#getDocument <em>Document</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Document</em>' attribute.
     * @see #getDocument()
     * @generated
     */
    void setDocument(String value);

} // FMCRoot
