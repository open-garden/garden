/**
 */
package com.zipc.garden.webplatform.dsl.core;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.zipc.garden.webplatform.dsl.core.DSLCOREFactory
 * @model kind="package"
 * @generated
 */
public interface DSLCOREPackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "core";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.zipc.com/garden/webplatform/dsl/core/COREModel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "DSLCore";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DSLCOREPackage eINSTANCE = com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.zipc.garden.webplatform.dsl.core.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.zipc.garden.webplatform.dsl.core.impl.ExpressionImpl
	 * @see com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.zipc.garden.webplatform.dsl.core.impl.AbstractOperationImpl <em>Abstract Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.zipc.garden.webplatform.dsl.core.impl.AbstractOperationImpl
	 * @see com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl#getAbstractOperation()
	 * @generated
	 */
	int ABSTRACT_OPERATION = 1;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_OPERATION__OPERATOR = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_OPERATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Abstract Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_OPERATION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.zipc.garden.webplatform.dsl.core.impl.BinaryOperationImpl <em>Binary Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.zipc.garden.webplatform.dsl.core.impl.BinaryOperationImpl
	 * @see com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl#getBinaryOperation()
	 * @generated
	 */
	int BINARY_OPERATION = 2;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATION__OPERATOR = ABSTRACT_OPERATION__OPERATOR;

	/**
	 * The feature id for the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATION__LEFT_OPERAND = ABSTRACT_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATION__RIGHT_OPERAND = ABSTRACT_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Binary Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATION_FEATURE_COUNT = ABSTRACT_OPERATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Binary Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATION_OPERATION_COUNT = ABSTRACT_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.zipc.garden.webplatform.dsl.core.impl.UnaryOperationImpl <em>Unary Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.zipc.garden.webplatform.dsl.core.impl.UnaryOperationImpl
	 * @see com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl#getUnaryOperation()
	 * @generated
	 */
	int UNARY_OPERATION = 3;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATION__OPERATOR = ABSTRACT_OPERATION__OPERATOR;

	/**
	 * The feature id for the '<em><b>Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATION__OPERAND = ABSTRACT_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Unary Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATION_FEATURE_COUNT = ABSTRACT_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Unary Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATION_OPERATION_COUNT = ABSTRACT_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.zipc.garden.webplatform.dsl.core.impl.IdentifiableElementImpl <em>Identifiable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.zipc.garden.webplatform.dsl.core.impl.IdentifiableElementImpl
	 * @see com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl#getIdentifiableElement()
	 * @generated
	 */
	int IDENTIFIABLE_ELEMENT = 4;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_ELEMENT__IDENTIFIER = 0;

	/**
	 * The number of structural features of the '<em>Identifiable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Identifiable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.zipc.garden.webplatform.dsl.core.OperatorIdentifier <em>Operator Identifier</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.zipc.garden.webplatform.dsl.core.OperatorIdentifier
	 * @see com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl#getOperatorIdentifier()
	 * @generated
	 */
	int OPERATOR_IDENTIFIER = 5;

	/**
	 * The meta object id for the '<em>ID</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.zipc.garden.webplatform.dsl.terminals.ID
	 * @see com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl#getID()
	 * @generated
	 */
	int ID = 6;


	/**
	 * Returns the meta object for class '{@link com.zipc.garden.webplatform.dsl.core.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see com.zipc.garden.webplatform.dsl.core.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for class '{@link com.zipc.garden.webplatform.dsl.core.AbstractOperation <em>Abstract Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Operation</em>'.
	 * @see com.zipc.garden.webplatform.dsl.core.AbstractOperation
	 * @generated
	 */
	EClass getAbstractOperation();

	/**
	 * Returns the meta object for the reference '{@link com.zipc.garden.webplatform.dsl.core.AbstractOperation#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operator</em>'.
	 * @see com.zipc.garden.webplatform.dsl.core.AbstractOperation#getOperator()
	 * @see #getAbstractOperation()
	 * @generated
	 */
	EReference getAbstractOperation_Operator();

	/**
	 * Returns the meta object for class '{@link com.zipc.garden.webplatform.dsl.core.BinaryOperation <em>Binary Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binary Operation</em>'.
	 * @see com.zipc.garden.webplatform.dsl.core.BinaryOperation
	 * @generated
	 */
	EClass getBinaryOperation();

	/**
	 * Returns the meta object for the containment reference '{@link com.zipc.garden.webplatform.dsl.core.BinaryOperation#getLeftOperand <em>Left Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Operand</em>'.
	 * @see com.zipc.garden.webplatform.dsl.core.BinaryOperation#getLeftOperand()
	 * @see #getBinaryOperation()
	 * @generated
	 */
	EReference getBinaryOperation_LeftOperand();

	/**
	 * Returns the meta object for the containment reference '{@link com.zipc.garden.webplatform.dsl.core.BinaryOperation#getRightOperand <em>Right Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Operand</em>'.
	 * @see com.zipc.garden.webplatform.dsl.core.BinaryOperation#getRightOperand()
	 * @see #getBinaryOperation()
	 * @generated
	 */
	EReference getBinaryOperation_RightOperand();

	/**
	 * Returns the meta object for class '{@link com.zipc.garden.webplatform.dsl.core.UnaryOperation <em>Unary Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unary Operation</em>'.
	 * @see com.zipc.garden.webplatform.dsl.core.UnaryOperation
	 * @generated
	 */
	EClass getUnaryOperation();

	/**
	 * Returns the meta object for the containment reference '{@link com.zipc.garden.webplatform.dsl.core.UnaryOperation#getOperand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operand</em>'.
	 * @see com.zipc.garden.webplatform.dsl.core.UnaryOperation#getOperand()
	 * @see #getUnaryOperation()
	 * @generated
	 */
	EReference getUnaryOperation_Operand();

	/**
	 * Returns the meta object for class '{@link com.zipc.garden.webplatform.dsl.core.IdentifiableElement <em>Identifiable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Identifiable Element</em>'.
	 * @see com.zipc.garden.webplatform.dsl.core.IdentifiableElement
	 * @generated
	 */
	EClass getIdentifiableElement();

	/**
	 * Returns the meta object for the attribute '{@link com.zipc.garden.webplatform.dsl.core.IdentifiableElement#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see com.zipc.garden.webplatform.dsl.core.IdentifiableElement#getIdentifier()
	 * @see #getIdentifiableElement()
	 * @generated
	 */
	EAttribute getIdentifiableElement_Identifier();

	/**
	 * Returns the meta object for enum '{@link com.zipc.garden.webplatform.dsl.core.OperatorIdentifier <em>Operator Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Operator Identifier</em>'.
	 * @see com.zipc.garden.webplatform.dsl.core.OperatorIdentifier
	 * @generated
	 */
	EEnum getOperatorIdentifier();

	/**
	 * Returns the meta object for data type '{@link com.zipc.garden.webplatform.dsl.terminals.ID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>ID</em>'.
	 * @see com.zipc.garden.webplatform.dsl.terminals.ID
	 * @model instanceClass="com.zipc.garden.webplatform.dsl.terminals.ID"
	 * @generated
	 */
	EDataType getID();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DSLCOREFactory getDSLCOREFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link com.zipc.garden.webplatform.dsl.core.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.zipc.garden.webplatform.dsl.core.impl.ExpressionImpl
		 * @see com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '{@link com.zipc.garden.webplatform.dsl.core.impl.AbstractOperationImpl <em>Abstract Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.zipc.garden.webplatform.dsl.core.impl.AbstractOperationImpl
		 * @see com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl#getAbstractOperation()
		 * @generated
		 */
		EClass ABSTRACT_OPERATION = eINSTANCE.getAbstractOperation();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_OPERATION__OPERATOR = eINSTANCE.getAbstractOperation_Operator();

		/**
		 * The meta object literal for the '{@link com.zipc.garden.webplatform.dsl.core.impl.BinaryOperationImpl <em>Binary Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.zipc.garden.webplatform.dsl.core.impl.BinaryOperationImpl
		 * @see com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl#getBinaryOperation()
		 * @generated
		 */
		EClass BINARY_OPERATION = eINSTANCE.getBinaryOperation();

		/**
		 * The meta object literal for the '<em><b>Left Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_OPERATION__LEFT_OPERAND = eINSTANCE.getBinaryOperation_LeftOperand();

		/**
		 * The meta object literal for the '<em><b>Right Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_OPERATION__RIGHT_OPERAND = eINSTANCE.getBinaryOperation_RightOperand();

		/**
		 * The meta object literal for the '{@link com.zipc.garden.webplatform.dsl.core.impl.UnaryOperationImpl <em>Unary Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.zipc.garden.webplatform.dsl.core.impl.UnaryOperationImpl
		 * @see com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl#getUnaryOperation()
		 * @generated
		 */
		EClass UNARY_OPERATION = eINSTANCE.getUnaryOperation();

		/**
		 * The meta object literal for the '<em><b>Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNARY_OPERATION__OPERAND = eINSTANCE.getUnaryOperation_Operand();

		/**
		 * The meta object literal for the '{@link com.zipc.garden.webplatform.dsl.core.impl.IdentifiableElementImpl <em>Identifiable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.zipc.garden.webplatform.dsl.core.impl.IdentifiableElementImpl
		 * @see com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl#getIdentifiableElement()
		 * @generated
		 */
		EClass IDENTIFIABLE_ELEMENT = eINSTANCE.getIdentifiableElement();

		/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIABLE_ELEMENT__IDENTIFIER = eINSTANCE.getIdentifiableElement_Identifier();

		/**
		 * The meta object literal for the '{@link com.zipc.garden.webplatform.dsl.core.OperatorIdentifier <em>Operator Identifier</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.zipc.garden.webplatform.dsl.core.OperatorIdentifier
		 * @see com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl#getOperatorIdentifier()
		 * @generated
		 */
		EEnum OPERATOR_IDENTIFIER = eINSTANCE.getOperatorIdentifier();

		/**
		 * The meta object literal for the '<em>ID</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.zipc.garden.webplatform.dsl.terminals.ID
		 * @see com.zipc.garden.webplatform.dsl.core.impl.DSLCOREPackageImpl#getID()
		 * @generated
		 */
		EDataType ID = eINSTANCE.getID();

	}

} //DSLCOREPackage
