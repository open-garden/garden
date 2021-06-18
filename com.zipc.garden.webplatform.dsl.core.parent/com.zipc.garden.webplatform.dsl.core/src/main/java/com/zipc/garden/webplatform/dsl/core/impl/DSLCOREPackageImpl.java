/**
 */
package com.zipc.garden.webplatform.dsl.core.impl;

import com.zipc.garden.webplatform.dsl.core.AbstractOperation;
import com.zipc.garden.webplatform.dsl.core.BinaryOperation;
import com.zipc.garden.webplatform.dsl.core.DSLCOREFactory;
import com.zipc.garden.webplatform.dsl.core.DSLCOREPackage;
import com.zipc.garden.webplatform.dsl.core.Expression;
import com.zipc.garden.webplatform.dsl.core.IdentifiableElement;
import com.zipc.garden.webplatform.dsl.core.OperatorIdentifier;
import com.zipc.garden.webplatform.dsl.core.UnaryOperation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DSLCOREPackageImpl extends EPackageImpl implements DSLCOREPackage
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass binaryOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unaryOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass identifiableElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum operatorIdentifierEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType idEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.zipc.garden.webplatform.dsl.core.DSLCOREPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DSLCOREPackageImpl()
	{
		super(eNS_URI, DSLCOREFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link DSLCOREPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DSLCOREPackage init()
	{
		if (isInited) return (DSLCOREPackage)EPackage.Registry.INSTANCE.getEPackage(DSLCOREPackage.eNS_URI);

		// Obtain or create and register package
		DSLCOREPackageImpl theDSLCOREPackage = (DSLCOREPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DSLCOREPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DSLCOREPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theDSLCOREPackage.createPackageContents();

		// Initialize created meta-data
		theDSLCOREPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDSLCOREPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DSLCOREPackage.eNS_URI, theDSLCOREPackage);
		return theDSLCOREPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpression()
	{
		return expressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractOperation()
	{
		return abstractOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractOperation_Operator()
	{
		return (EReference)abstractOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBinaryOperation()
	{
		return binaryOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryOperation_LeftOperand()
	{
		return (EReference)binaryOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryOperation_RightOperand()
	{
		return (EReference)binaryOperationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnaryOperation()
	{
		return unaryOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnaryOperation_Operand()
	{
		return (EReference)unaryOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIdentifiableElement()
	{
		return identifiableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdentifiableElement_Identifier()
	{
		return (EAttribute)identifiableElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getOperatorIdentifier()
	{
		return operatorIdentifierEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getID()
	{
		return idEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DSLCOREFactory getDSLCOREFactory()
	{
		return (DSLCOREFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents()
	{
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		expressionEClass = createEClass(EXPRESSION);

		abstractOperationEClass = createEClass(ABSTRACT_OPERATION);
		createEReference(abstractOperationEClass, ABSTRACT_OPERATION__OPERATOR);

		binaryOperationEClass = createEClass(BINARY_OPERATION);
		createEReference(binaryOperationEClass, BINARY_OPERATION__LEFT_OPERAND);
		createEReference(binaryOperationEClass, BINARY_OPERATION__RIGHT_OPERAND);

		unaryOperationEClass = createEClass(UNARY_OPERATION);
		createEReference(unaryOperationEClass, UNARY_OPERATION__OPERAND);

		identifiableElementEClass = createEClass(IDENTIFIABLE_ELEMENT);
		createEAttribute(identifiableElementEClass, IDENTIFIABLE_ELEMENT__IDENTIFIER);

		// Create enums
		operatorIdentifierEEnum = createEEnum(OPERATOR_IDENTIFIER);

		// Create data types
		idEDataType = createEDataType(ID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents()
	{
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		abstractOperationEClass.getESuperTypes().add(this.getExpression());
		binaryOperationEClass.getESuperTypes().add(this.getAbstractOperation());
		unaryOperationEClass.getESuperTypes().add(this.getAbstractOperation());

		// Initialize classes, features, and operations; add parameters
		initEClass(expressionEClass, Expression.class, "Expression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractOperationEClass, AbstractOperation.class, "AbstractOperation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractOperation_Operator(), this.getIdentifiableElement(), null, "operator", null, 0, 1, AbstractOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(binaryOperationEClass, BinaryOperation.class, "BinaryOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBinaryOperation_LeftOperand(), this.getExpression(), null, "leftOperand", null, 0, 1, BinaryOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBinaryOperation_RightOperand(), this.getExpression(), null, "rightOperand", null, 0, 1, BinaryOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unaryOperationEClass, UnaryOperation.class, "UnaryOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnaryOperation_Operand(), this.getExpression(), null, "operand", null, 0, 1, UnaryOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(identifiableElementEClass, IdentifiableElement.class, "IdentifiableElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIdentifiableElement_Identifier(), this.getOperatorIdentifier(), "identifier", null, 0, 1, IdentifiableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(operatorIdentifierEEnum, OperatorIdentifier.class, "OperatorIdentifier");
		addEEnumLiteral(operatorIdentifierEEnum, OperatorIdentifier.OPERATOR_UNDEFINED);
		addEEnumLiteral(operatorIdentifierEEnum, OperatorIdentifier.OPERATOR_REMOVES);
		addEEnumLiteral(operatorIdentifierEEnum, OperatorIdentifier.OPERATOR_IMPLIES);
		addEEnumLiteral(operatorIdentifierEEnum, OperatorIdentifier.OPERATOR_OR);
		addEEnumLiteral(operatorIdentifierEEnum, OperatorIdentifier.OPERATOR_AND);
		addEEnumLiteral(operatorIdentifierEEnum, OperatorIdentifier.OPERATOR_NOT);

		// Initialize data types
		initEDataType(idEDataType, com.zipc.garden.webplatform.dsl.terminals.ID.class, "ID", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //DSLCOREPackageImpl
