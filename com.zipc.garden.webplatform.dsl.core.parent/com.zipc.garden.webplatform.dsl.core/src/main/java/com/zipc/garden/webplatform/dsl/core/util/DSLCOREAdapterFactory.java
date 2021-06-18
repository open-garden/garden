/**
 */
package com.zipc.garden.webplatform.dsl.core.util;

import com.zipc.garden.webplatform.dsl.core.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.zipc.garden.webplatform.dsl.core.DSLCOREPackage
 * @generated
 */
public class DSLCOREAdapterFactory extends AdapterFactoryImpl
{
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DSLCOREPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DSLCOREAdapterFactory()
	{
		if (modelPackage == null)
		{
			modelPackage = DSLCOREPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object)
	{
		if (object == modelPackage)
		{
			return true;
		}
		if (object instanceof EObject)
		{
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DSLCORESwitch<Adapter> modelSwitch =
		new DSLCORESwitch<Adapter>()
		{
			@Override
			public Adapter caseExpression(Expression object)
			{
				return createExpressionAdapter();
			}
			@Override
			public Adapter caseAbstractOperation(AbstractOperation object)
			{
				return createAbstractOperationAdapter();
			}
			@Override
			public Adapter caseBinaryOperation(BinaryOperation object)
			{
				return createBinaryOperationAdapter();
			}
			@Override
			public Adapter caseUnaryOperation(UnaryOperation object)
			{
				return createUnaryOperationAdapter();
			}
			@Override
			public Adapter caseIdentifiableElement(IdentifiableElement object)
			{
				return createIdentifiableElementAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object)
			{
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target)
	{
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.zipc.garden.webplatform.dsl.core.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.zipc.garden.webplatform.dsl.core.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.zipc.garden.webplatform.dsl.core.AbstractOperation <em>Abstract Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.zipc.garden.webplatform.dsl.core.AbstractOperation
	 * @generated
	 */
	public Adapter createAbstractOperationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.zipc.garden.webplatform.dsl.core.BinaryOperation <em>Binary Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.zipc.garden.webplatform.dsl.core.BinaryOperation
	 * @generated
	 */
	public Adapter createBinaryOperationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.zipc.garden.webplatform.dsl.core.UnaryOperation <em>Unary Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.zipc.garden.webplatform.dsl.core.UnaryOperation
	 * @generated
	 */
	public Adapter createUnaryOperationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.zipc.garden.webplatform.dsl.core.IdentifiableElement <em>Identifiable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.zipc.garden.webplatform.dsl.core.IdentifiableElement
	 * @generated
	 */
	public Adapter createIdentifiableElementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter()
	{
		return null;
	}

} //DSLCOREAdapterFactory
