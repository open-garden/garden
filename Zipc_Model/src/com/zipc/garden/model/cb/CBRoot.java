/**
 */
package com.zipc.garden.model.cb;

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractRootElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.cb.CBRoot#getLogic <em>Logic</em>}</li>
 * <li>{@link com.zipc.garden.model.cb.CBRoot#getLayoutMode <em>Layout Mode</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.cb.CBPackage#getCBRoot()
 * @model
 * @generated
 */
public interface CBRoot extends AbstractRootElement, AbstractDiagram {
    /**
     * Returns the value of the '<em><b>Logic</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Logic</em>' containment reference.
     * @see #setLogic(CBLogic)
     * @see com.zipc.garden.model.cb.CBPackage#getCBRoot_Logic()
     * @model containment="true"
     * @generated
     */
    CBLogic getLogic();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.cb.CBRoot#getLogic <em>Logic</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Logic</em>' containment reference.
     * @see #getLogic()
     * @generated
     */
    void setLogic(CBLogic value);

    /**
     * Returns the value of the '<em><b>Layout Mode</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Layout Mode</em>' attribute.
     * @see #setLayoutMode(int)
     * @see com.zipc.garden.model.cb.CBPackage#getCBRoot_LayoutMode()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getLayoutMode();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.cb.CBRoot#getLayoutMode <em>Layout Mode</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Layout Mode</em>' attribute.
     * @see #getLayoutMode()
     * @generated
     */
    void setLayoutMode(int value);

} // CBRoot
