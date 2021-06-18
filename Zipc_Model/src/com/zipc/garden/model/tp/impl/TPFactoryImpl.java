/**
 */
package com.zipc.garden.model.tp.impl;

import com.zipc.garden.model.tp.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class TPFactoryImpl extends EFactoryImpl implements TPFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static TPFactory init() {
        try {
            TPFactory theTPFactory = (TPFactory) EPackage.Registry.INSTANCE.getEFactory(TPPackage.eNS_URI);
            if (theTPFactory != null) {
                return theTPFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new TPFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public TPFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case TPPackage.TP_ROOT:
            return createTPRoot();
        case TPPackage.TP_TSD_PATTERN:
            return createTPTSDPattern();
        case TPPackage.TP_ELEMENT:
            return createTPElement();
        case TPPackage.TP_SETTING:
            return createTPSetting();
        case TPPackage.TP_PATTERN_ELEMENT:
            return createTPPatternElement();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
        case TPPackage.PATH_TYPE:
            return createPathTypeFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case TPPackage.PATH_TYPE:
            return convertPathTypeToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TPRoot createTPRoot() {
        TPRootImpl tpRoot = new TPRootImpl();
        return tpRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TPTSDPattern createTPTSDPattern() {
        TPTSDPatternImpl tptsdPattern = new TPTSDPatternImpl();
        return tptsdPattern;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TPElement createTPElement() {
        TPElementImpl tpElement = new TPElementImpl();
        return tpElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TPSetting createTPSetting() {
        TPSettingImpl tpSetting = new TPSettingImpl();
        return tpSetting;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TPPatternElement createTPPatternElement() {
        TPPatternElementImpl tpPatternElement = new TPPatternElementImpl();
        return tpPatternElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public PathType createPathTypeFromString(EDataType eDataType, String initialValue) {
        PathType result = PathType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertPathTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TPPackage getTPPackage() {
        return (TPPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static TPPackage getPackage() {
        return TPPackage.eINSTANCE;
    }

} // TPFactoryImpl
