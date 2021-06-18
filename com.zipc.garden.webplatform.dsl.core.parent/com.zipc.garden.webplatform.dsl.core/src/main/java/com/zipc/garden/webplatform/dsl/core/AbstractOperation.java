/**
 */
package com.zipc.garden.webplatform.dsl.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.webplatform.dsl.core.AbstractOperation#getOperator <em>Operator</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.webplatform.dsl.core.DSLCOREPackage#getAbstractOperation()
 * @model abstract="true"
 * @generated
 */
public interface AbstractOperation extends Expression
{
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' reference.
	 * @see #setOperator(IdentifiableElement)
	 * @see com.zipc.garden.webplatform.dsl.core.DSLCOREPackage#getAbstractOperation_Operator()
	 * @model
	 * @generated
	 */
	IdentifiableElement getOperator();

	/**
	 * Sets the value of the '{@link com.zipc.garden.webplatform.dsl.core.AbstractOperation#getOperator <em>Operator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' reference.
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(IdentifiableElement value);

} // AbstractOperation
