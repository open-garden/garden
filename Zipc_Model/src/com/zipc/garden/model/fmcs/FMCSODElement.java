/**
 */
package com.zipc.garden.model.fmcs;

import com.zipc.garden.model.fm.FMNode;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>OD Element</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> ODElement <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.FMCSODElement#getFullName <em>Full Name</em>}</li>
 * <li>{@link com.zipc.garden.model.fmcs.FMCSODElement#getNode <em>Node</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSODElement()
 * @model
 * @generated
 */
public interface FMCSODElement extends EObject {
    /**
     * Returns the value of the '<em><b>Node</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Node</em>' reference.
     * @see #setNode(FMNode)
     * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSODElement_Node()
     * @model required="true"
     * @generated
     */
    FMNode getNode();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmcs.FMCSODElement#getNode <em>Node</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Node</em>' reference.
     * @see #getNode()
     * @generated
     */
    void setNode(FMNode value);

    /**
     * Returns the value of the '<em><b>Full Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Full Name</em>' attribute.
     * @see #setFullName(String)
     * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSODElement_FullName()
     * @model
     * @generated
     */
    String getFullName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmcs.FMCSODElement#getFullName <em>Full Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Full Name</em>' attribute.
     * @see #getFullName()
     * @generated
     */
    void setFullName(String value);

} // FMCSODElement
