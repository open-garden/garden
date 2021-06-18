/**
 */
package com.zipc.garden.model.fmcs;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Select Expression</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> 選択・有効 <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.FMCSSelectExpression#getOdElement <em>Od Element</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSSelectExpression()
 * @model
 * @generated
 */
public interface FMCSSelectExpression extends FMCSExpression {
    /**
     * Returns the value of the '<em><b>Od Element</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @return the value of the '<em>Od Element</em>' containment reference.
     * @see #setOdElement(FMCSODElement)
     * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSSelectExpression_OdElement()
     * @model containment="true" required="true"
     * @generated
     */
    FMCSODElement getOdElement();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fmcs.FMCSSelectExpression#getOdElement <em>Od Element</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Od Element</em>' containment reference.
     * @see #getOdElement()
     * @generated
     */
    void setOdElement(FMCSODElement value);

} // FMCSSelectExpression
