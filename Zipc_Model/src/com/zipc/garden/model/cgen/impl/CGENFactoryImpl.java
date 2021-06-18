/**
 */
package com.zipc.garden.model.cgen.impl;

import com.zipc.garden.model.cgen.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class CGENFactoryImpl extends EFactoryImpl implements CGENFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static CGENFactory init() {
        try {
            CGENFactory theCGENFactory = (CGENFactory) EPackage.Registry.INSTANCE.getEFactory(CGENPackage.eNS_URI);
            if (theCGENFactory != null) {
                return theCGENFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new CGENFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public CGENFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case CGENPackage.CGEN_ROOT:
            return createCGENRoot();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CGENRoot createCGENRoot() {
        CGENRootImpl cgenRoot = new CGENRootImpl();
        return cgenRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CGENPackage getCGENPackage() {
        return (CGENPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static CGENPackage getPackage() {
        return CGENPackage.eINSTANCE;
    }

} // CGENFactoryImpl
