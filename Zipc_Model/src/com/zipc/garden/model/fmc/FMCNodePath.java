/**
 */
package com.zipc.garden.model.fmc;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Node Path</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmc.FMCNodePath#getFullpath <em>Fullpath</em>}</li>
 * <li>{@link com.zipc.garden.model.fmc.FMCNodePath#getSimplePath <em>Simple Path</em>}</li>
 * <li>{@link com.zipc.garden.model.fmc.FMCNodePath#getNodeName <em>Node Name</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fmc.FMCPackage#getFMCNodePath()
 * @model
 * @generated
 */
public interface FMCNodePath extends EObject {
    /**
     * Returns the value of the '<em><b>Fullpath</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Fullpath</em>' attribute.
     * @see #setFullpath(String)
     * @see com.zipc.garden.model.fmc.FMCPackage#getFMCNodePath_Fullpath()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getFullpath();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmc.FMCNodePath#getFullpath <em>Fullpath</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Fullpath</em>' attribute.
     * @see #getFullpath()
     * @generated
     */
    void setFullpath(String value);

    /**
     * Returns the value of the '<em><b>Simple Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Simple Path</em>' attribute.
     * @see #setSimplePath(String)
     * @see com.zipc.garden.model.fmc.FMCPackage#getFMCNodePath_SimplePath()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getSimplePath();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmc.FMCNodePath#getSimplePath <em>Simple Path</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Simple Path</em>' attribute.
     * @see #getSimplePath()
     * @generated
     */
    void setSimplePath(String value);

    /**
     * Returns the value of the '<em><b>Node Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Node Name</em>' attribute.
     * @see #setNodeName(String)
     * @see com.zipc.garden.model.fmc.FMCPackage#getFMCNodePath_NodeName()
     * @model
     * @generated
     */
    String getNodeName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmc.FMCNodePath#getNodeName <em>Node Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Node Name</em>' attribute.
     * @see #getNodeName()
     * @generated
     */
    void setNodeName(String value);

} // FMCNodePath
