/**
 */
package com.zipc.garden.model.fmc;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Constraint</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmc.FMCConstraint#getConstraint <em>Constraint</em>}</li>
 * <li>{@link com.zipc.garden.model.fmc.FMCConstraint#getComment <em>Comment</em>}</li>
 * <li>{@link com.zipc.garden.model.fmc.FMCConstraint#isEnabled <em>Enabled</em>}</li>
 * <li>{@link com.zipc.garden.model.fmc.FMCConstraint#getRelatedNodes <em>Related Nodes</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fmc.FMCPackage#getFMCConstraint()
 * @model
 * @generated
 */
public interface FMCConstraint extends EObject {
    /**
     * Returns the value of the '<em><b>Constraint</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Constraint</em>' attribute.
     * @see #setConstraint(String)
     * @see com.zipc.garden.model.fmc.FMCPackage#getFMCConstraint_Constraint()
     * @model
     * @generated
     */
    String getConstraint();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmc.FMCConstraint#getConstraint <em>Constraint</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Constraint</em>' attribute.
     * @see #getConstraint()
     * @generated
     */
    void setConstraint(String value);

    /**
     * Returns the value of the '<em><b>Comment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Comment</em>' attribute.
     * @see #setComment(String)
     * @see com.zipc.garden.model.fmc.FMCPackage#getFMCConstraint_Comment()
     * @model
     * @generated
     */
    String getComment();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmc.FMCConstraint#getComment <em>Comment</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Comment</em>' attribute.
     * @see #getComment()
     * @generated
     */
    void setComment(String value);

    /**
     * Returns the value of the '<em><b>Enabled</b></em>' attribute. The default value is <code>"true"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Enabled</em>' attribute.
     * @see #setEnabled(boolean)
     * @see com.zipc.garden.model.fmc.FMCPackage#getFMCConstraint_Enabled()
     * @model default="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     * @generated
     */
    boolean isEnabled();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmc.FMCConstraint#isEnabled <em>Enabled</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Enabled</em>' attribute.
     * @see #isEnabled()
     * @generated
     */
    void setEnabled(boolean value);

    /**
     * Returns the value of the '<em><b>Related Nodes</b></em>' reference list. The list contents are of type
     * {@link com.zipc.garden.model.fmc.FMCNodePath}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Related Nodes</em>' reference list.
     * @see com.zipc.garden.model.fmc.FMCPackage#getFMCConstraint_RelatedNodes()
     * @model
     * @generated
     */
    EList<FMCNodePath> getRelatedNodes();

} // FMCConstraint
