/**
 */
package com.zipc.garden.model.tps;

import com.zipc.garden.model.tc.TCNode;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Node Variable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tps.NodeVariable#getTcNode <em>Tc Node</em>}</li>
 * <li>{@link com.zipc.garden.model.tps.NodeVariable#getVariableName <em>Variable Name</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.tps.TPSPackage#getNodeVariable()
 * @model
 * @generated
 */
public interface NodeVariable extends EObject {
    /**
     * Returns the value of the '<em><b>Tc Node</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Tc Node</em>' reference.
     * @see #setTcNode(TCNode)
     * @see com.zipc.garden.model.tps.TPSPackage#getNodeVariable_TcNode()
     * @model
     * @generated
     */
    TCNode getTcNode();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tps.NodeVariable#getTcNode <em>Tc Node</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Tc Node</em>' reference.
     * @see #getTcNode()
     * @generated
     */
    void setTcNode(TCNode value);

    /**
     * Returns the value of the '<em><b>Variable Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Variable Name</em>' attribute.
     * @see #setVariableName(String)
     * @see com.zipc.garden.model.tps.TPSPackage#getNodeVariable_VariableName()
     * @model
     * @generated
     */
    String getVariableName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tps.NodeVariable#getVariableName <em>Variable Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Variable Name</em>' attribute.
     * @see #getVariableName()
     * @generated
     */
    void setVariableName(String value);

} // NodeVariable
