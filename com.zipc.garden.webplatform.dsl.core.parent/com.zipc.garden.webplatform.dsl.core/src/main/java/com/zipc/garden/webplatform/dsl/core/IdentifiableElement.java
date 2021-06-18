/**
 */
package com.zipc.garden.webplatform.dsl.core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Identifiable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.webplatform.dsl.core.IdentifiableElement#getIdentifier <em>Identifier</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.webplatform.dsl.core.DSLCOREPackage#getIdentifiableElement()
 * @model
 * @generated
 */
public interface IdentifiableElement extends EObject
{
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * The literals are from the enumeration {@link com.zipc.garden.webplatform.dsl.core.OperatorIdentifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see com.zipc.garden.webplatform.dsl.core.OperatorIdentifier
	 * @see #setIdentifier(OperatorIdentifier)
	 * @see com.zipc.garden.webplatform.dsl.core.DSLCOREPackage#getIdentifiableElement_Identifier()
	 * @model
	 * @generated
	 */
	OperatorIdentifier getIdentifier();

	/**
	 * Sets the value of the '{@link com.zipc.garden.webplatform.dsl.core.IdentifiableElement#getIdentifier <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see com.zipc.garden.webplatform.dsl.core.OperatorIdentifier
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(OperatorIdentifier value);

} // IdentifiableElement
