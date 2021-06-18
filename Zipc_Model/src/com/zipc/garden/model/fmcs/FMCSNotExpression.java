/**
 */
package com.zipc.garden.model.fmcs;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Not Expression</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> 否定 <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.FMCSNotExpression#getExpression <em>Expression</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSNotExpression()
 * @model
 * @generated
 */
public interface FMCSNotExpression extends FMCSExpression {
    /**
     * Returns the value of the '<em><b>Expression</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @return the value of the '<em>Expression</em>' containment reference.
     * @see #setExpression(FMCSExpression)
     * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSNotExpression_Expression()
     * @model containment="true" required="true"
     * @generated
     */
    FMCSExpression getExpression();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmcs.FMCSNotExpression#getExpression <em>Expression</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' containment reference.
     * @see #getExpression()
     * @generated
     */
    void setExpression(FMCSExpression value);

} // FMCSNotExpression
