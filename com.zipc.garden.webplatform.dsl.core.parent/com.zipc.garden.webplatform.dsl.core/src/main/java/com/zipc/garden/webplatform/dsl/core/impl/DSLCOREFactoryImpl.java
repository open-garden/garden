/**
 */
package com.zipc.garden.webplatform.dsl.core.impl;

import com.zipc.garden.webplatform.dsl.core.*;

import com.zipc.garden.webplatform.dsl.terminals.ID;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DSLCOREFactoryImpl extends EFactoryImpl implements DSLCOREFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DSLCOREFactory init()
	{
		try
		{
			DSLCOREFactory theDSLCOREFactory = (DSLCOREFactory)EPackage.Registry.INSTANCE.getEFactory(DSLCOREPackage.eNS_URI);
			if (theDSLCOREFactory != null)
			{
				return theDSLCOREFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DSLCOREFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DSLCOREFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass)
	{
		switch (eClass.getClassifierID())
		{
			case DSLCOREPackage.BINARY_OPERATION: return createBinaryOperation();
			case DSLCOREPackage.UNARY_OPERATION: return createUnaryOperation();
			case DSLCOREPackage.IDENTIFIABLE_ELEMENT: return createIdentifiableElement();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue)
	{
		switch (eDataType.getClassifierID())
		{
			case DSLCOREPackage.OPERATOR_IDENTIFIER:
				return createOperatorIdentifierFromString(eDataType, initialValue);
			case DSLCOREPackage.ID:
				return createIDFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue)
	{
		switch (eDataType.getClassifierID())
		{
			case DSLCOREPackage.OPERATOR_IDENTIFIER:
				return convertOperatorIdentifierToString(eDataType, instanceValue);
			case DSLCOREPackage.ID:
				return convertIDToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryOperation createBinaryOperation()
	{
		BinaryOperationImpl binaryOperation = new BinaryOperationImpl();
		return binaryOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnaryOperation createUnaryOperation()
	{
		UnaryOperationImpl unaryOperation = new UnaryOperationImpl();
		return unaryOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdentifiableElement createIdentifiableElement()
	{
		IdentifiableElementImpl identifiableElement = new IdentifiableElementImpl();
		return identifiableElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperatorIdentifier createOperatorIdentifierFromString(EDataType eDataType, String initialValue)
	{
		OperatorIdentifier result = OperatorIdentifier.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOperatorIdentifierToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ID createIDFromString(EDataType eDataType, String initialValue)
	{
		return (ID)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIDToString(EDataType eDataType, Object instanceValue)
	{
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DSLCOREPackage getDSLCOREPackage()
	{
		return (DSLCOREPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DSLCOREPackage getPackage()
	{
		return DSLCOREPackage.eINSTANCE;
	}

} //DSLCOREFactoryImpl
