/**
 */
package com.zipc.garden.model.tp;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>TSD Pattern</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> A Test Scenario Domain pattern (Test Scene) <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tp.TPTSDPattern#getElements <em>Elements</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.tp.TPPackage#getTPTSDPattern()
 * @model
 * @generated
 */
public interface TPTSDPattern extends EObject {
    /**
     * Returns the value of the '<em><b>Elements</b></em>' reference list. The list contents are of type
     * {@link com.zipc.garden.model.tp.TPElement}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Elements</em>' reference list.
     * @see com.zipc.garden.model.tp.TPPackage#getTPTSDPattern_Elements()
     * @model
     * @generated
     */
    EList<TPElement> getElements();

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see com.zipc.garden.model.tp.TPPackage#getTPTSDPattern_Id()
     * @model required="true"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tp.TPTSDPattern#getId <em>Id</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Pattern Elements</b></em>' reference list. The list contents are of type
     * {@link com.zipc.garden.model.tp.TPPatternElement}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Elements</em>' reference list.
     * @see com.zipc.garden.model.tp.TPPackage#getTPTSDPattern_PatternElements()
     * @model
     * @generated
     */
    EList<TPPatternElement> getPatternElements();

} // TPTSDPattern
