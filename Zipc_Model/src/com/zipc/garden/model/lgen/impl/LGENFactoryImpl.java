/**
 */
package com.zipc.garden.model.lgen.impl;

import com.zipc.garden.model.lgen.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class LGENFactoryImpl extends EFactoryImpl implements LGENFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static LGENFactory init() {
        try {
            LGENFactory theLGENFactory = (LGENFactory) EPackage.Registry.INSTANCE.getEFactory(LGENPackage.eNS_URI);
            if (theLGENFactory != null) {
                return theLGENFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new LGENFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public LGENFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case LGENPackage.LGEN_ROOT:
            return createLGENRoot();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public LGENRoot createLGENRoot() {
        LGENRootImpl lgenRoot = new LGENRootImpl();
        return lgenRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public LGENPackage getLGENPackage() {
        return (LGENPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static LGENPackage getPackage() {
        return LGENPackage.eINSTANCE;
    }

} // LGENFactoryImpl
