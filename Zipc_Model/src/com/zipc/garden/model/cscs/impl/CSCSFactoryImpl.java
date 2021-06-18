/**
 */
package com.zipc.garden.model.cscs.impl;

import com.zipc.garden.model.cscs.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class CSCSFactoryImpl extends EFactoryImpl implements CSCSFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static CSCSFactory init() {
        try {
            CSCSFactory theCSCSFactory = (CSCSFactory) EPackage.Registry.INSTANCE.getEFactory(CSCSPackage.eNS_URI);
            if (theCSCSFactory != null) {
                return theCSCSFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new CSCSFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public CSCSFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case CSCSPackage.CSCS_ROOT:
            return createCSCSRoot();
        case CSCSPackage.CSCS_PATTERN:
            return createCSCSPattern();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CSCSRoot createCSCSRoot() {
        CSCSRootImpl cscsRoot = new CSCSRootImpl();
        return cscsRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CSCSPattern createCSCSPattern() {
        CSCSPatternImpl cscsPattern = new CSCSPatternImpl();
        return cscsPattern;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CSCSPackage getCSCSPackage() {
        return (CSCSPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static CSCSPackage getPackage() {
        return CSCSPackage.eINSTANCE;
    }

} // CSCSFactoryImpl
