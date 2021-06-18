/**
 */
package com.zipc.garden.webplatform.dsl.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unary Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.webplatform.dsl.core.UnaryOperation#getOperand <em>Operand</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.webplatform.dsl.core.DSLCOREPackage#getUnaryOperation()
 * @model
 * @generated
 */
public interface UnaryOperation extends AbstractOperation
{
	/**
	 * Returns the value of the '<em><b>Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operand</em>' containment reference.
	 * @see #setOperand(Expression)
	 * @see com.zipc.garden.webplatform.dsl.core.DSLCOREPackage#getUnaryOperation_Operand()
	 * @model containment="true"
	 * @generated
	 */
	Expression getOperand();

	/**
	 * Sets the value of the '{@link com.zipc.garden.webplatform.dsl.core.UnaryOperation#getOperand <em>Operand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operand</em>' containment reference.
	 * @see #getOperand()
	 * @generated
	 */
	void setOperand(Expression value);

} // UnaryOperation
