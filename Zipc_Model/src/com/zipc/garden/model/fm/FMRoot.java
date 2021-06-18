/**
 */
package com.zipc.garden.model.fm;

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractRootElement;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fm.FMRoot#getNode <em>Node</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMRoot#getProperties <em>Properties</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMRoot#getConstraints <em>Constraints</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMRoot#getAutoLayout <em>Auto Layout</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fm.FMPackage#getFMRoot()
 * @model
 * @generated
 */
public interface FMRoot extends AbstractRootElement, AbstractDiagram {

    /**
     * Returns the value of the '<em><b>Node</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Node</em>' containment reference.
     * @see #setNode(FMNode)
     * @see com.zipc.garden.model.fm.FMPackage#getFMRoot_Node()
     * @model containment="true"
     * @generated
     */
    FMNode getNode();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMRoot#getNode <em>Node</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Node</em>' containment reference.
     * @see #getNode()
     * @generated
     */
    void setNode(FMNode value);

    /**
     * Returns the value of the '<em><b>Properties</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fm.FMProperty}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Properties</em>' containment reference list.
     * @see com.zipc.garden.model.fm.FMPackage#getFMRoot_Properties()
     * @model containment="true"
     * @generated
     */
    EList<FMProperty> getProperties();

    /**
     * Returns the value of the '<em><b>Constraints</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fm.FMConstraint}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Constraints</em>' containment reference list.
     * @see com.zipc.garden.model.fm.FMPackage#getFMRoot_Constraints()
     * @model containment="true"
     * @generated
     */
    EList<FMConstraint> getConstraints();

    /**
     * Returns the value of the '<em><b>Auto Layout</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Auto Layout</em>' attribute.
     * @see #setAutoLayout(int)
     * @see com.zipc.garden.model.fm.FMPackage#getFMRoot_AutoLayout()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getAutoLayout();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMRoot#getAutoLayout <em>Auto Layout</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Auto Layout</em>' attribute.
     * @see #getAutoLayout()
     * @generated
     */
    void setAutoLayout(int value);
} // FMRoot
