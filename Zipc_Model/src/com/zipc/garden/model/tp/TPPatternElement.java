/**
 */
package com.zipc.garden.model.tp;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Pattern Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tp.TPPatternElement#isChecked <em>Checked</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.TPPatternElement#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.TPPatternElement#getValue <em>Value</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.tp.TPPackage#getTPPatternElement()
 * @model
 * @generated
 */
public interface TPPatternElement extends EObject {
    /**
     * Returns the value of the '<em><b>Checked</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Checked</em>' attribute.
     * @see #setChecked(boolean)
     * @see com.zipc.garden.model.tp.TPPackage#getTPPatternElement_Checked()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     * @generated
     */
    boolean isChecked();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tp.TPPatternElement#isChecked <em>Checked</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Checked</em>' attribute.
     * @see #isChecked()
     * @generated
     */
    void setChecked(boolean value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see com.zipc.garden.model.tp.TPPackage#getTPPatternElement_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tp.TPPatternElement#getName <em>Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see com.zipc.garden.model.tp.TPPackage#getTPPatternElement_Value()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getValue();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tp.TPPatternElement#getValue <em>Value</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(String value);

} // TPPatternElement
