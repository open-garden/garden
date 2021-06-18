/**
 */
package com.zipc.garden.model.arc;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Referenceable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.arc.ARCReferenceable#getId <em>Id</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.ARCReferenceable#getUuid <em>Uuid</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.arc.ARCPackage#getARCReferenceable()
 * @model abstract="true"
 * @generated
 */
public interface ARCReferenceable extends EObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(long)
     * @see com.zipc.garden.model.arc.ARCPackage#getARCReferenceable_Id()
     * @model
     * @generated
     */
    long getId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCReferenceable#getId <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(long value);

    /**
     * Returns the value of the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Uuid</em>' attribute.
     * @see #setUuid(String)
     * @see com.zipc.garden.model.arc.ARCPackage#getARCReferenceable_Uuid()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getUuid();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCReferenceable#getUuid <em>Uuid</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Uuid</em>' attribute.
     * @see #getUuid()
     * @generated
     */
    void setUuid(String value);

} // ARCReferenceable
