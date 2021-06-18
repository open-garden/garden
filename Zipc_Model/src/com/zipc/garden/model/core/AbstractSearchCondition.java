/**
 */
package com.zipc.garden.model.core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Search Condition</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.AbstractSearchCondition#getMax <em>Max</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractSearchCondition#getRowIds <em>Row Ids</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.core.COREPackage#getAbstractSearchCondition()
 * @model abstract="true"
 * @generated
 */
public interface AbstractSearchCondition extends EObject {
    /**
     * Returns the value of the '<em><b>Max</b></em>' attribute. The default value is <code>"100"</code>. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the value of the '<em>Max</em>' attribute.
     * @see #setMax(int)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractSearchCondition_Max()
     * @model default="100" dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getMax();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractSearchCondition#getMax <em>Max</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Max</em>' attribute.
     * @see #getMax()
     * @generated
     */
    void setMax(int value);

    /**
     * Returns the value of the '<em><b>Row Ids</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Row Ids</em>' attribute.
     * @see #setRowIds(String)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractSearchCondition_RowIds()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getRowIds();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractSearchCondition#getRowIds <em>Row Ids</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Row Ids</em>' attribute.
     * @see #getRowIds()
     * @generated
     */
    void setRowIds(String value);

} // AbstractSearchCondition
