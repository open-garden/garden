/**
 */
package com.zipc.garden.model.spql.impl;

import com.zipc.garden.model.spql.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class SPQLFactoryImpl extends EFactoryImpl implements SPQLFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static SPQLFactory init() {
        try {
            SPQLFactory theSPQLFactory = (SPQLFactory) EPackage.Registry.INSTANCE.getEFactory(SPQLPackage.eNS_URI);
            if (theSPQLFactory != null) {
                return theSPQLFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new SPQLFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public SPQLFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case SPQLPackage.SPQL_ROOT:
            return createSPQLRoot();
        case SPQLPackage.SPQL_SETTING:
            return createSPQLSetting();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SPQLRoot createSPQLRoot() {
        SPQLRootImpl spqlRoot = new SPQLRootImpl();
        return spqlRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SPQLSetting createSPQLSetting() {
        SPQLSettingImpl spqlSetting = new SPQLSettingImpl();
        return spqlSetting;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SPQLPackage getSPQLPackage() {
        return (SPQLPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static SPQLPackage getPackage() {
        return SPQLPackage.eINSTANCE;
    }

} // SPQLFactoryImpl
