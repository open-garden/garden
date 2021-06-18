/**
 */
package com.zipc.garden.model.fmcs.impl;

import com.zipc.garden.model.fmcs.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class FMCSFactoryImpl extends EFactoryImpl implements FMCSFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static FMCSFactory init() {
        try {
            FMCSFactory theFMCSFactory = (FMCSFactory) EPackage.Registry.INSTANCE.getEFactory(FMCSPackage.eNS_URI);
            if (theFMCSFactory != null) {
                return theFMCSFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new FMCSFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FMCSFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case FMCSPackage.FMCS_ROOT:
            return createFMCSRoot();
        case FMCSPackage.FMCS_CONSTRAINT:
            return createFMCSConstraint();
        case FMCSPackage.FMCS_NOT_EXPRESSION:
            return createFMCSNotExpression();
        case FMCSPackage.FMCS_AND_EXPRESSION:
            return createFMCSAndExpression();
        case FMCSPackage.FMCS_OR_EXPRESSION:
            return createFMCSOrExpression();
        case FMCSPackage.FMCS_IMPLIES_EXPRESSION:
            return createFMCSImpliesExpression();
        case FMCSPackage.FMCS_SELECT_EXPRESSION:
            return createFMCSSelectExpression();
        case FMCSPackage.FMCS_OD_ELEMENT:
            return createFMCSODElement();
        case FMCSPackage.FMCS_REMOVES_EXPRESSION:
            return createFMCSRemovesExpression();
        case FMCSPackage.FMCS_MUTEX_EXPRESSION:
            return createFMCSMutexExpression();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSRoot createFMCSRoot() {
        FMCSRootImpl fmcsRoot = new FMCSRootImpl();
        return fmcsRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSConstraint createFMCSConstraint() {
        FMCSConstraintImpl fmcsConstraint = new FMCSConstraintImpl();
        return fmcsConstraint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSImpliesExpression createFMCSImpliesExpression() {
        FMCSImpliesExpressionImpl fmcsImpliesExpression = new FMCSImpliesExpressionImpl();
        return fmcsImpliesExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSOrExpression createFMCSOrExpression() {
        FMCSOrExpressionImpl fmcsOrExpression = new FMCSOrExpressionImpl();
        return fmcsOrExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSAndExpression createFMCSAndExpression() {
        FMCSAndExpressionImpl fmcsAndExpression = new FMCSAndExpressionImpl();
        return fmcsAndExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSNotExpression createFMCSNotExpression() {
        FMCSNotExpressionImpl fmcsNotExpression = new FMCSNotExpressionImpl();
        return fmcsNotExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSSelectExpression createFMCSSelectExpression() {
        FMCSSelectExpressionImpl fmcsSelectExpression = new FMCSSelectExpressionImpl();
        return fmcsSelectExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSODElement createFMCSODElement() {
        FMCSODElementImpl fmcsodElement = new FMCSODElementImpl();
        return fmcsodElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSMutexExpression createFMCSMutexExpression() {
        FMCSMutexExpressionImpl fmcsMutexExpression = new FMCSMutexExpressionImpl();
        return fmcsMutexExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSRemovesExpression createFMCSRemovesExpression() {
        FMCSRemovesExpressionImpl fmcsRemovesExpression = new FMCSRemovesExpressionImpl();
        return fmcsRemovesExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSPackage getFMCSPackage() {
        return (FMCSPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static FMCSPackage getPackage() {
        return FMCSPackage.eINSTANCE;
    }

} // FMCSFactoryImpl
