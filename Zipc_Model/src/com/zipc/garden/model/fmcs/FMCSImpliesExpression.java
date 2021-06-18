/**
 */
package com.zipc.garden.model.fmcs;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Implies Expression</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> 論理包含 <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.FMCSImpliesExpression#getLeftExpression <em>Left Expression</em>}</li>
 * <li>{@link com.zipc.garden.model.fmcs.FMCSImpliesExpression#getRightExpression <em>Right Expression</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSImpliesExpression()
 * @model
 * @generated
 */
public interface FMCSImpliesExpression extends FMCSExpression {
    /**
     * Returns the value of the '<em><b>Left Expression</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the value of the '<em>Left Expression</em>' containment reference.
     * @see #setLeftExpression(FMCSExpression)
     * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSImpliesExpression_LeftExpression()
     * @model containment="true" required="true"
     * @generated
     */
    FMCSExpression getLeftExpression();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmcs.FMCSImpliesExpression#getLeftExpression <em>Left
     * Expression</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Left Expression</em>' containment reference.
     * @see #getLeftExpression()
     * @generated
     */
    void setLeftExpression(FMCSExpression value);

    /**
     * Returns the value of the '<em><b>Right Expression</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the value of the '<em>Right Expression</em>' containment reference.
     * @see #setRightExpression(FMCSExpression)
     * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSImpliesExpression_RightExpression()
     * @model containment="true" required="true"
     * @generated
     */
    FMCSExpression getRightExpression();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmcs.FMCSImpliesExpression#getRightExpression <em>Right
     * Expression</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Right Expression</em>' containment reference.
     * @see #getRightExpression()
     * @generated
     */
    void setRightExpression(FMCSExpression value);

} // FMCSImpliesExpression
