/**
 */
package com.zipc.garden.model.bps;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Variable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.BPSVariable#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSVariable#getType <em>Type</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bps.BPSPackage#getBPSVariable()
 * @model
 * @generated
 */
public interface BPSVariable extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSVariable_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSVariable#getName <em>Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSVariable_Type()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getType();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSVariable#getType <em>Type</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
    void setType(String value);

} // BPSVariable
