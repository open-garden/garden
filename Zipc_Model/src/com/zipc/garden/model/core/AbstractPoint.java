/**
 */
package com.zipc.garden.model.core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Point</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.AbstractPoint#getX <em>X</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractPoint#getY <em>Y</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.core.COREPackage#getAbstractPoint()
 * @model abstract="true"
 * @generated
 */
public interface AbstractPoint extends EObject {
    /**
     * Returns the value of the '<em><b>X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' attribute.
     * @see #setX(int)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractPoint_X()
     * @model
     * @generated
     */
    int getX();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractPoint#getX <em>X</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>X</em>' attribute.
     * @see #getX()
     * @generated
     */
    void setX(int value);

    /**
     * Returns the value of the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Y</em>' attribute.
     * @see #setY(int)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractPoint_Y()
     * @model
     * @generated
     */
    int getY();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractPoint#getY <em>Y</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #getY()
     * @generated
     */
    void setY(int value);

} // AbstractPoint
