/**
 */
package com.zipc.garden.webplatform.dsl.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Operator Identifier</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.zipc.garden.webplatform.dsl.core.DSLCOREPackage#getOperatorIdentifier()
 * @model
 * @generated
 */
public enum OperatorIdentifier implements Enumerator
{
	/**
	 * The '<em><b>Operator undefined</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OPERATOR_UNDEFINED_VALUE
	 * @generated
	 * @ordered
	 */
	OPERATOR_UNDEFINED(0, "operator_undefined", "operator_undefined"),

	/**
	 * The '<em><b>Operator removes</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OPERATOR_REMOVES_VALUE
	 * @generated
	 * @ordered
	 */
	OPERATOR_REMOVES(1, "operator_removes", "operator_removes"),

	/**
	 * The '<em><b>Operator implies</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OPERATOR_IMPLIES_VALUE
	 * @generated
	 * @ordered
	 */
	OPERATOR_IMPLIES(2, "operator_implies", "operator_implies"),

	/**
	 * The '<em><b>Operator or</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OPERATOR_OR_VALUE
	 * @generated
	 * @ordered
	 */
	OPERATOR_OR(3, "operator_or", "operator_or"),

	/**
	 * The '<em><b>Operator and</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OPERATOR_AND_VALUE
	 * @generated
	 * @ordered
	 */
	OPERATOR_AND(4, "operator_and", "operator_and"),

	/**
	 * The '<em><b>Operator not</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OPERATOR_NOT_VALUE
	 * @generated
	 * @ordered
	 */
	OPERATOR_NOT(5, "operator_not", "operator_not");

	/**
	 * The '<em><b>Operator undefined</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Operator undefined</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OPERATOR_UNDEFINED
	 * @model name="operator_undefined"
	 * @generated
	 * @ordered
	 */
	public static final int OPERATOR_UNDEFINED_VALUE = 0;

	/**
	 * The '<em><b>Operator removes</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Operator removes</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OPERATOR_REMOVES
	 * @model name="operator_removes"
	 * @generated
	 * @ordered
	 */
	public static final int OPERATOR_REMOVES_VALUE = 1;

	/**
	 * The '<em><b>Operator implies</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Operator implies</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OPERATOR_IMPLIES
	 * @model name="operator_implies"
	 * @generated
	 * @ordered
	 */
	public static final int OPERATOR_IMPLIES_VALUE = 2;

	/**
	 * The '<em><b>Operator or</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Operator or</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OPERATOR_OR
	 * @model name="operator_or"
	 * @generated
	 * @ordered
	 */
	public static final int OPERATOR_OR_VALUE = 3;

	/**
	 * The '<em><b>Operator and</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Operator and</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OPERATOR_AND
	 * @model name="operator_and"
	 * @generated
	 * @ordered
	 */
	public static final int OPERATOR_AND_VALUE = 4;

	/**
	 * The '<em><b>Operator not</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Operator not</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OPERATOR_NOT
	 * @model name="operator_not"
	 * @generated
	 * @ordered
	 */
	public static final int OPERATOR_NOT_VALUE = 5;

	/**
	 * An array of all the '<em><b>Operator Identifier</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final OperatorIdentifier[] VALUES_ARRAY =
		new OperatorIdentifier[]
		{
			OPERATOR_UNDEFINED,
			OPERATOR_REMOVES,
			OPERATOR_IMPLIES,
			OPERATOR_OR,
			OPERATOR_AND,
			OPERATOR_NOT,
		};

	/**
	 * A public read-only list of all the '<em><b>Operator Identifier</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<OperatorIdentifier> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Operator Identifier</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static OperatorIdentifier get(String literal)
	{
		for (int i = 0; i < VALUES_ARRAY.length; ++i)
		{
			OperatorIdentifier result = VALUES_ARRAY[i];
			if (result.toString().equals(literal))
			{
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Operator Identifier</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static OperatorIdentifier getByName(String name)
	{
		for (int i = 0; i < VALUES_ARRAY.length; ++i)
		{
			OperatorIdentifier result = VALUES_ARRAY[i];
			if (result.getName().equals(name))
			{
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Operator Identifier</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static OperatorIdentifier get(int value)
	{
		switch (value)
		{
			case OPERATOR_UNDEFINED_VALUE: return OPERATOR_UNDEFINED;
			case OPERATOR_REMOVES_VALUE: return OPERATOR_REMOVES;
			case OPERATOR_IMPLIES_VALUE: return OPERATOR_IMPLIES;
			case OPERATOR_OR_VALUE: return OPERATOR_OR;
			case OPERATOR_AND_VALUE: return OPERATOR_AND;
			case OPERATOR_NOT_VALUE: return OPERATOR_NOT;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private OperatorIdentifier(int value, String name, String literal)
	{
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue()
	{
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName()
	{
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral()
	{
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		return literal;
	}
	
} //OperatorIdentifier
