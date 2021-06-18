/**
 */
package com.zipc.garden.model.core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.AbstractStyle#getFontSize <em>Font Size</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractStyle#getFillColor <em>Fill Color</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractStyle#getFontColor <em>Font Color</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.core.COREPackage#getAbstractStyle()
 * @model abstract="true"
 * @generated
 */
public interface AbstractStyle extends EObject {
    /**
     * Returns the value of the '<em><b>Font Size</b></em>' attribute. The default value is <code>"11"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Font Size</em>' attribute.
     * @see #setFontSize(int)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractStyle_FontSize()
     * @model default="11"
     * @generated
     */
    int getFontSize();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractStyle#getFontSize <em>Font Size</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Font Size</em>' attribute.
     * @see #getFontSize()
     * @generated
     */
    void setFontSize(int value);

    /**
     * Returns the value of the '<em><b>Fill Color</b></em>' attribute. The default value is <code>"#FFFFFF"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Fill Color</em>' attribute.
     * @see #setFillColor(String)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractStyle_FillColor()
     * @model default="#FFFFFF"
     * @generated
     */
    String getFillColor();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractStyle#getFillColor <em>Fill Color</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Fill Color</em>' attribute.
     * @see #getFillColor()
     * @generated
     */
    void setFillColor(String value);

    /**
     * Returns the value of the '<em><b>Font Color</b></em>' attribute. The default value is <code>"#707070"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Font Color</em>' attribute.
     * @see #setFontColor(String)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractStyle_FontColor()
     * @model default="#707070"
     * @generated
     */
    String getFontColor();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractStyle#getFontColor <em>Font Color</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Font Color</em>' attribute.
     * @see #getFontColor()
     * @generated
     */
    void setFontColor(String value);

} // AbstractStyle
