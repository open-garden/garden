/**
 */
package com.zipc.garden.model.csc.impl;

import com.zipc.garden.model.csc.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class CSCFactoryImpl extends EFactoryImpl implements CSCFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static CSCFactory init() {
        try {
            CSCFactory theCSCFactory = (CSCFactory) EPackage.Registry.INSTANCE.getEFactory(CSCPackage.eNS_URI);
            if (theCSCFactory != null) {
                return theCSCFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new CSCFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public CSCFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case CSCPackage.CSC_ROOT:
            return createCSCRoot();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CSCRoot createCSCRoot() {
        CSCRootImpl cscRoot = new CSCRootImpl();
        return cscRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CSCPackage getCSCPackage() {
        return (CSCPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static CSCPackage getPackage() {
        return CSCPackage.eINSTANCE;
    }

} // CSCFactoryImpl
