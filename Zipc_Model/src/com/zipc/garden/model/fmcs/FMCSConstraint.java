/**
 */
package com.zipc.garden.model.fmcs;

import com.zipc.garden.model.fmc.FMCConstraint;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Constraint</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> 制約 <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.FMCSConstraint#getExpression <em>Expression</em>}</li>
 * <li>{@link com.zipc.garden.model.fmcs.FMCSConstraint#getRef <em>Ref</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSConstraint()
 * @model
 * @generated
 */
public interface FMCSConstraint extends EObject {
    /**
     * Returns the value of the '<em><b>Expression</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @return the value of the '<em>Expression</em>' containment reference.
     * @see #setExpression(FMCSExpression)
     * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSConstraint_Expression()
     * @model containment="true" required="true"
     * @generated
     */
    FMCSExpression getExpression();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmcs.FMCSConstraint#getExpression <em>Expression</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' containment reference.
     * @see #getExpression()
     * @generated
     */
    void setExpression(FMCSExpression value);

    /**
     * Returns the value of the '<em><b>Ref</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Ref</em>' reference.
     * @see #setRef(FMCConstraint)
     * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSConstraint_Ref()
     * @model
     * @generated
     */
    FMCConstraint getRef();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmcs.FMCSConstraint#getRef <em>Ref</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Ref</em>' reference.
     * @see #getRef()
     * @generated
     */
    void setRef(FMCConstraint value);

} // FMCSConstraint
