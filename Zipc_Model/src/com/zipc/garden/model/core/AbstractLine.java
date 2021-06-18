/**
 */
package com.zipc.garden.model.core;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Line</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.AbstractLine#getSourceAnchorX <em>Source Anchor X</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractLine#getSourceAnchorY <em>Source Anchor Y</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractLine#getTargetAnchorX <em>Target Anchor X</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractLine#getTargetAnchorY <em>Target Anchor Y</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractLine#getType <em>Type</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractLine#getConnectionPoint <em>Connection Point</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.core.COREPackage#getAbstractLine()
 * @model abstract="true"
 * @generated
 */
public interface AbstractLine extends EObject {
    /**
     * Returns the value of the '<em><b>Source Anchor X</b></em>' attribute. The default value is <code>"0.5"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Source Anchor X</em>' attribute.
     * @see #setSourceAnchorX(double)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractLine_SourceAnchorX()
     * @model default="0.5" dataType="org.eclipse.emf.ecore.xml.type.Double"
     * @generated
     */
    double getSourceAnchorX();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractLine#getSourceAnchorX <em>Source Anchor X</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Anchor X</em>' attribute.
     * @see #getSourceAnchorX()
     * @generated
     */
    void setSourceAnchorX(double value);

    /**
     * Returns the value of the '<em><b>Source Anchor Y</b></em>' attribute. The default value is <code>"0.5"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Source Anchor Y</em>' attribute.
     * @see #setSourceAnchorY(double)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractLine_SourceAnchorY()
     * @model default="0.5" dataType="org.eclipse.emf.ecore.xml.type.Double"
     * @generated
     */
    double getSourceAnchorY();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractLine#getSourceAnchorY <em>Source Anchor Y</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Anchor Y</em>' attribute.
     * @see #getSourceAnchorY()
     * @generated
     */
    void setSourceAnchorY(double value);

    /**
     * Returns the value of the '<em><b>Target Anchor X</b></em>' attribute. The default value is <code>"0.5"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Target Anchor X</em>' attribute.
     * @see #setTargetAnchorX(double)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractLine_TargetAnchorX()
     * @model default="0.5" dataType="org.eclipse.emf.ecore.xml.type.Double"
     * @generated
     */
    double getTargetAnchorX();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractLine#getTargetAnchorX <em>Target Anchor X</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Anchor X</em>' attribute.
     * @see #getTargetAnchorX()
     * @generated
     */
    void setTargetAnchorX(double value);

    /**
     * Returns the value of the '<em><b>Target Anchor Y</b></em>' attribute. The default value is <code>"0.5"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Target Anchor Y</em>' attribute.
     * @see #setTargetAnchorY(double)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractLine_TargetAnchorY()
     * @model default="0.5" dataType="org.eclipse.emf.ecore.xml.type.Double"
     * @generated
     */
    double getTargetAnchorY();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractLine#getTargetAnchorY <em>Target Anchor Y</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Anchor Y</em>' attribute.
     * @see #getTargetAnchorY()
     * @generated
     */
    void setTargetAnchorY(double value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute. The default value is <code>"SIMPLE"</code>. The literals are
     * from the enumeration {@link com.zipc.garden.model.core.LineType}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see com.zipc.garden.model.core.LineType
     * @see #setType(LineType)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractLine_Type()
     * @model default="SIMPLE"
     * @generated
     */
    LineType getType();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractLine#getType <em>Type</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see com.zipc.garden.model.core.LineType
     * @see #getType()
     * @generated
     */
    void setType(LineType value);

    /**
     * Returns the value of the '<em><b>Connection Point</b></em>' reference list. The list contents are of type
     * {@link com.zipc.garden.model.core.AbstractPoint}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Connection Point</em>' reference list.
     * @see com.zipc.garden.model.core.COREPackage#getAbstractLine_ConnectionPoint()
     * @model
     * @generated
     */
    EList<AbstractPoint> getConnectionPoint();

} // AbstractLine
