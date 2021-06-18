/**
 */
package com.zipc.garden.model.scs.impl;

import com.zipc.garden.model.scs.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class SCSFactoryImpl extends EFactoryImpl implements SCSFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static SCSFactory init() {
        try {
            SCSFactory theSCSFactory = (SCSFactory) EPackage.Registry.INSTANCE.getEFactory(SCSPackage.eNS_URI);
            if (theSCSFactory != null) {
                return theSCSFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new SCSFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public SCSFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case SCSPackage.SCS_ROOT:
            return createSCSRoot();
        case SCSPackage.SCS_PATTERN:
            return createSCSPattern();
        case SCSPackage.SCS_PATTERN_REFERENCE:
            return createSCSPatternReference();
        case SCSPackage.SCS_SETTING:
            return createSCSSetting();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SCSRoot createSCSRoot() {
        SCSRootImpl scsRoot = new SCSRootImpl();
        return scsRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SCSPattern createSCSPattern() {
        SCSPatternImpl scsPattern = new SCSPatternImpl();
        return scsPattern;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SCSPatternReference createSCSPatternReference() {
        SCSPatternReferenceImpl scsPatternReference = new SCSPatternReferenceImpl();
        return scsPatternReference;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SCSSetting createSCSSetting() {
        SCSSettingImpl scsSetting = new SCSSettingImpl();
        return scsSetting;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SCSPackage getSCSPackage() {
        return (SCSPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static SCSPackage getPackage() {
        return SCSPackage.eINSTANCE;
    }

} // SCSFactoryImpl
