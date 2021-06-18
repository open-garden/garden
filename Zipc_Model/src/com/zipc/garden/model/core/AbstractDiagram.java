/**
 */
package com.zipc.garden.model.core;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Diagram</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.AbstractDiagram#getScrollX <em>Scroll X</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractDiagram#getScrollY <em>Scroll Y</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractDiagram#getPositionX <em>Position X</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractDiagram#getPositionY <em>Position Y</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractDiagram#getGridSize <em>Grid Size</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractDiagram#getNodes <em>Nodes</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractDiagram#getLineMode <em>Line Mode</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.core.COREPackage#getAbstractDiagram()
 * @model abstract="true"
 * @generated
 */
public interface AbstractDiagram extends EObject {
    /**
     * Returns the value of the '<em><b>Scroll X</b></em>' attribute. The default value is <code>"1000"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Scroll X</em>' attribute.
     * @see #setScrollX(int)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractDiagram_ScrollX()
     * @model default="1000"
     * @generated
     */
    int getScrollX();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractDiagram#getScrollX <em>Scroll X</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Scroll X</em>' attribute.
     * @see #getScrollX()
     * @generated
     */
    void setScrollX(int value);

    /**
     * Returns the value of the '<em><b>Scroll Y</b></em>' attribute. The default value is <code>"700"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Scroll Y</em>' attribute.
     * @see #setScrollY(int)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractDiagram_ScrollY()
     * @model default="700"
     * @generated
     */
    int getScrollY();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractDiagram#getScrollY <em>Scroll Y</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Scroll Y</em>' attribute.
     * @see #getScrollY()
     * @generated
     */
    void setScrollY(int value);

    /**
     * Returns the value of the '<em><b>Position X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Position X</em>' attribute.
     * @see #setPositionX(int)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractDiagram_PositionX()
     * @model
     * @generated
     */
    int getPositionX();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractDiagram#getPositionX <em>Position X</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Position X</em>' attribute.
     * @see #getPositionX()
     * @generated
     */
    void setPositionX(int value);

    /**
     * Returns the value of the '<em><b>Position Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Position Y</em>' attribute.
     * @see #setPositionY(int)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractDiagram_PositionY()
     * @model
     * @generated
     */
    int getPositionY();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractDiagram#getPositionY <em>Position Y</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Position Y</em>' attribute.
     * @see #getPositionY()
     * @generated
     */
    void setPositionY(int value);

    /**
     * Returns the value of the '<em><b>Grid Size</b></em>' attribute. The default value is <code>"20"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Grid Size</em>' attribute.
     * @see #setGridSize(int)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractDiagram_GridSize()
     * @model default="20"
     * @generated
     */
    int getGridSize();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractDiagram#getGridSize <em>Grid Size</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Grid Size</em>' attribute.
     * @see #getGridSize()
     * @generated
     */
    void setGridSize(int value);

    /**
     * Returns the value of the '<em><b>Nodes</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.core.AbstractNode}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Nodes</em>' containment reference list.
     * @see com.zipc.garden.model.core.COREPackage#getAbstractDiagram_Nodes()
     * @model containment="true"
     * @generated
     */
    EList<AbstractNode> getNodes();

    /**
     * Returns the value of the '<em><b>Line Mode</b></em>' attribute. The default value is <code>"0"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Line Mode</em>' attribute.
     * @see #setLineMode(int)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractDiagram_LineMode()
     * @model default="0" dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getLineMode();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractDiagram#getLineMode <em>Line Mode</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Line Mode</em>' attribute.
     * @see #getLineMode()
     * @generated
     */
    void setLineMode(int value);

} // AbstractDiagram
