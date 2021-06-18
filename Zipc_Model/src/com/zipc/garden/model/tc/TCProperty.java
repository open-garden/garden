/**
 */
package com.zipc.garden.model.tc;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Property</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tc.TCProperty#getKey <em>Key</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.TCProperty#getValue <em>Value</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.tc.TCPackage#getTCProperty()
 * @model
 * @generated
 */
public interface TCProperty extends EObject {
    /**
     * Returns the value of the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Key</em>' attribute.
     * @see #setKey(String)
     * @see com.zipc.garden.model.tc.TCPackage#getTCProperty_Key()
     * @model
     * @generated
     */
    String getKey();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tc.TCProperty#getKey <em>Key</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Key</em>' attribute.
     * @see #getKey()
     * @generated
     */
    void setKey(String value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see com.zipc.garden.model.tc.TCPackage#getTCProperty_Value()
     * @model
     * @generated
     */
    String getValue();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tc.TCProperty#getValue <em>Value</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(String value);

} // TCProperty
