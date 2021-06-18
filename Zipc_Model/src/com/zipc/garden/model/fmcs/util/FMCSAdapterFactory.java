/**
 */
package com.zipc.garden.model.fmcs.util;

import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.fmcs.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code> method for
 * each class of the model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.fmcs.FMCSPackage
 * @generated
 */
public class FMCSAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static FMCSPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FMCSAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = FMCSPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This implementation
     * returns <code>true</code> if the object is either the model's package or is an instance object of the model. <!--
     * end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FMCSSwitch<Adapter> modelSwitch = new FMCSSwitch<Adapter>() {
        @Override
        public Adapter caseFMCSRoot(FMCSRoot object) {
            return createFMCSRootAdapter();
        }

        @Override
        public Adapter caseFMCSConstraint(FMCSConstraint object) {
            return createFMCSConstraintAdapter();
        }

        @Override
        public Adapter caseFMCSExpression(FMCSExpression object) {
            return createFMCSExpressionAdapter();
        }

        @Override
        public Adapter caseFMCSNotExpression(FMCSNotExpression object) {
            return createFMCSNotExpressionAdapter();
        }

        @Override
        public Adapter caseFMCSAndExpression(FMCSAndExpression object) {
            return createFMCSAndExpressionAdapter();
        }

        @Override
        public Adapter caseFMCSOrExpression(FMCSOrExpression object) {
            return createFMCSOrExpressionAdapter();
        }

        @Override
        public Adapter caseFMCSImpliesExpression(FMCSImpliesExpression object) {
            return createFMCSImpliesExpressionAdapter();
        }

        @Override
        public Adapter caseFMCSSelectExpression(FMCSSelectExpression object) {
            return createFMCSSelectExpressionAdapter();
        }

        @Override
        public Adapter caseFMCSODElement(FMCSODElement object) {
            return createFMCSODElementAdapter();
        }

        @Override
        public Adapter caseFMCSRemovesExpression(FMCSRemovesExpression object) {
            return createFMCSRemovesExpressionAdapter();
        }

        @Override
        public Adapter caseFMCSMutexExpression(FMCSMutexExpression object) {
            return createFMCSMutexExpressionAdapter();
        }

        @Override
        public Adapter caseAbstractRootElement(AbstractRootElement object) {
            return createAbstractRootElementAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fmcs.FMCSRoot <em>Root</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fmcs.FMCSRoot
     * @generated
     */
    public Adapter createFMCSRootAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fmcs.FMCSConstraint <em>Constraint</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fmcs.FMCSConstraint
     * @generated
     */
    public Adapter createFMCSConstraintAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fmcs.FMCSExpression <em>Expression</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fmcs.FMCSExpression
     * @generated
     */
    public Adapter createFMCSExpressionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fmcs.FMCSImpliesExpression <em>Implies
     * Expression</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fmcs.FMCSImpliesExpression
     * @generated
     */
    public Adapter createFMCSImpliesExpressionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fmcs.FMCSOrExpression <em>Or
     * Expression</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fmcs.FMCSOrExpression
     * @generated
     */
    public Adapter createFMCSOrExpressionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fmcs.FMCSAndExpression <em>And
     * Expression</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fmcs.FMCSAndExpression
     * @generated
     */
    public Adapter createFMCSAndExpressionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fmcs.FMCSNotExpression <em>Not
     * Expression</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fmcs.FMCSNotExpression
     * @generated
     */
    public Adapter createFMCSNotExpressionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fmcs.FMCSSelectExpression <em>Select
     * Expression</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fmcs.FMCSSelectExpression
     * @generated
     */
    public Adapter createFMCSSelectExpressionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fmcs.FMCSODElement <em>OD Element</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fmcs.FMCSODElement
     * @generated
     */
    public Adapter createFMCSODElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fmcs.FMCSMutexExpression <em>Mutex
     * Expression</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fmcs.FMCSMutexExpression
     * @generated
     */
    public Adapter createFMCSMutexExpressionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.core.AbstractRootElement <em>Abstract Root
     * Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.core.AbstractRootElement
     * @generated
     */
    public Adapter createAbstractRootElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fmcs.FMCSRemovesExpression <em>Removes
     * Expression</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fmcs.FMCSRemovesExpression
     * @generated
     */
    public Adapter createFMCSRemovesExpressionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null. <!--
     * end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // FMCSAdapterFactory
