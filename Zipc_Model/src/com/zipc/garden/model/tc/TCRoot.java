/**
 */
package com.zipc.garden.model.tc;

import org.eclipse.emf.common.util.EList;

import com.zipc.garden.model.core.AbstractRootElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tc.TCRoot#getRootNodes <em>Root Nodes</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.TCRoot#getShowingHierarchy <em>Showing Hierarchy</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.TCRoot#getKeywords <em>Keywords</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.tc.TCPackage#getTCRoot()
 * @model
 * @generated
 */
public interface TCRoot extends AbstractRootElement {

    /**
     * Returns the value of the '<em><b>Root Nodes</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.tc.TCNode}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Root Nodes</em>' containment reference list.
     * @see com.zipc.garden.model.tc.TCPackage#getTCRoot_RootNodes()
     * @model containment="true"
     * @generated
     */
    EList<TCNode> getRootNodes();

    /**
     * Returns the value of the '<em><b>Showing Hierarchy</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Showing Hierarchy</em>' attribute.
     * @see #setShowingHierarchy(int)
     * @see com.zipc.garden.model.tc.TCPackage#getTCRoot_ShowingHierarchy()
     * @model default="-1" dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getShowingHierarchy();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tc.TCRoot#getShowingHierarchy <em>Showing Hierarchy</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Showing Hierarchy</em>' attribute.
     * @see #getShowingHierarchy()
     * @generated
     */
    void setShowingHierarchy(int value);

    /**
     * Returns the value of the '<em><b>Keywords</b></em>' attribute list. The list contents are of type
     * {@link java.lang.String}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Keywords</em>' attribute list.
     * @see com.zipc.garden.model.tc.TCPackage#getTCRoot_Keywords()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    EList<String> getKeywords();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated NOT
     */
    TCNode getActiveRootNode();
} // TCRoot
