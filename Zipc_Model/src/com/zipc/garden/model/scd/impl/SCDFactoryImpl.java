/**
 */
package com.zipc.garden.model.scd.impl;

import com.zipc.garden.model.scd.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class SCDFactoryImpl extends EFactoryImpl implements SCDFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static SCDFactory init() {
        try {
            SCDFactory theSCDFactory = (SCDFactory) EPackage.Registry.INSTANCE.getEFactory(SCDPackage.eNS_URI);
            if (theSCDFactory != null) {
                return theSCDFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new SCDFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public SCDFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case SCDPackage.SCD_ROOT:
            return createSCDRoot();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SCDRoot createSCDRoot() {
        SCDRootImpl scdRoot = new SCDRootImpl();
        return scdRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SCDPackage getSCDPackage() {
        return (SCDPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static SCDPackage getPackage() {
        return SCDPackage.eINSTANCE;
    }

} // SCDFactoryImpl
