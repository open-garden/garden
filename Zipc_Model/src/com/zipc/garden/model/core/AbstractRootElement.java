/**
 */
package com.zipc.garden.model.core;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Root Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.AbstractRootElement#getId <em>Id</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractRootElement#getRefs <em>Refs</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.core.COREPackage#getAbstractRootElement()
 * @model abstract="true"
 * @generated
 */
public interface AbstractRootElement extends EObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractRootElement_Id()
     * @model
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractRootElement#getId <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Refs</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.core.Reference}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Refs</em>' containment reference list.
     * @see com.zipc.garden.model.core.COREPackage#getAbstractRootElement_Refs()
     * @model containment="true"
     * @generated
     */
    EList<Reference> getRefs();

} // AbstractRootElement
