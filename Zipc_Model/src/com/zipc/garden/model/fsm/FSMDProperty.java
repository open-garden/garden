/**
 */
package com.zipc.garden.model.fsm;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>DProperty</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.FSMDProperty#getKey <em>Key</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDProperty#getValue <em>Value</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDProperty()
 * @model
 * @generated
 */
public interface FSMDProperty extends EObject {
    /**
     * Returns the value of the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Key</em>' attribute.
     * @see #setKey(String)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDProperty_Key()
     * @model
     * @generated
     */
    String getKey();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDProperty#getKey <em>Key</em>}' attribute. <!-- begin-user-doc
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
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDProperty_Value()
     * @model
     * @generated
     */
    String getValue();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDProperty#getValue <em>Value</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(String value);

} // FSMDProperty
