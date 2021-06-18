/**
 */
package com.zipc.garden.model.fmcs;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Removes Expression</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> パラメータの削除 <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.FMCSRemovesExpression#getExpression <em>Expression</em>}</li>
 * <li>{@link com.zipc.garden.model.fmcs.FMCSRemovesExpression#getOdElement <em>Od Element</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSRemovesExpression()
 * @model
 * @generated
 */
public interface FMCSRemovesExpression extends FMCSExpression {
    /**
     * Returns the value of the '<em><b>Od Element</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @return the value of the '<em>Od Element</em>' containment reference.
     * @see #setOdElement(FMCSODElement)
     * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSRemovesExpression_OdElement()
     * @model containment="true" required="true"
     * @generated
     */
    FMCSODElement getOdElement();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmcs.FMCSRemovesExpression#getOdElement <em>Od Element</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Od Element</em>' containment reference.
     * @see #getOdElement()
     * @generated
     */
    void setOdElement(FMCSODElement value);

    /**
     * Returns the value of the '<em><b>Expression</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @return the value of the '<em>Expression</em>' containment reference.
     * @see #setExpression(FMCSExpression)
     * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSRemovesExpression_Expression()
     * @model containment="true" required="true"
     * @generated
     */
    FMCSExpression getExpression();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmcs.FMCSRemovesExpression#getExpression <em>Expression</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' containment reference.
     * @see #getExpression()
     * @generated
     */
    void setExpression(FMCSExpression value);

} // FMCSRemovesExpression
