/**
 */
package com.zipc.garden.model.lsc.impl;

import com.zipc.garden.model.lsc.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class LSCFactoryImpl extends EFactoryImpl implements LSCFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static LSCFactory init() {
        try {
            LSCFactory theLSCFactory = (LSCFactory) EPackage.Registry.INSTANCE.getEFactory(LSCPackage.eNS_URI);
            if (theLSCFactory != null) {
                return theLSCFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new LSCFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public LSCFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case LSCPackage.LSC_ROOT:
            return createLSCRoot();
        case LSCPackage.LSC_SCENARIO:
            return createLSCScenario();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public LSCRoot createLSCRoot() {
        LSCRootImpl lscRoot = new LSCRootImpl();
        return lscRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public LSCScenario createLSCScenario() {
        LSCScenarioImpl lscScenario = new LSCScenarioImpl();
        return lscScenario;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public LSCPackage getLSCPackage() {
        return (LSCPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static LSCPackage getPackage() {
        return LSCPackage.eINSTANCE;
    }

} // LSCFactoryImpl
