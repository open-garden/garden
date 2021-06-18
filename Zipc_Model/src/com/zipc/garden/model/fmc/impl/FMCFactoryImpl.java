/**
 */
package com.zipc.garden.model.fmc.impl;

import com.zipc.garden.model.fmc.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class FMCFactoryImpl extends EFactoryImpl implements FMCFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static FMCFactory init() {
        try {
            FMCFactory theFMCFactory = (FMCFactory) EPackage.Registry.INSTANCE.getEFactory(FMCPackage.eNS_URI);
            if (theFMCFactory != null) {
                return theFMCFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new FMCFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FMCFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case FMCPackage.FMC_ROOT:
            return createFMCRoot();
        case FMCPackage.FMC_CONSTRAINT:
            return createFMCConstraint();
        case FMCPackage.FMC_NODE_PATH:
            return createFMCNodePath();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCRoot createFMCRoot() {
        FMCRootImpl fmcRoot = new FMCRootImpl();
        return fmcRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCConstraint createFMCConstraint() {
        FMCConstraintImpl fmcConstraint = new FMCConstraintImpl();
        return fmcConstraint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCNodePath createFMCNodePath() {
        FMCNodePathImpl fmcNodePath = new FMCNodePathImpl();
        return fmcNodePath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCPackage getFMCPackage() {
        return (FMCPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static FMCPackage getPackage() {
        return FMCPackage.eINSTANCE;
    }

} // FMCFactoryImpl
