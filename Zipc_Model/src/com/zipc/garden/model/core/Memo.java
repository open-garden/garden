/**
 */
package com.zipc.garden.model.core;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Memo</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.Memo#getText <em>Text</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.core.COREPackage#getMemo()
 * @model
 * @generated
 */
public interface Memo extends AbstractNode {
    /**
     * Returns the value of the '<em><b>Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Text</em>' attribute.
     * @see #setText(String)
     * @see com.zipc.garden.model.core.COREPackage#getMemo_Text()
     * @model
     * @generated
     */
    String getText();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.Memo#getText <em>Text</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text</em>' attribute.
     * @see #getText()
     * @generated
     */
    void setText(String value);

} // Memo
