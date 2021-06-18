/**
 */
package com.zipc.garden.model.arc.impl;

import com.zipc.garden.model.arc.*;

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
public class ARCFactoryImpl extends EFactoryImpl implements ARCFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static ARCFactory init() {
        try {
            ARCFactory theARCFactory = (ARCFactory) EPackage.Registry.INSTANCE.getEFactory(ARCPackage.eNS_URI);
            if (theARCFactory != null) {
                return theARCFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ARCFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ARCFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case ARCPackage.ARC_STATE:
            return createARCState();
        case ARCPackage.ARC_POINT:
            return createARCPoint();
        case ARCPackage.ARC_LINE:
            return createARCLine();
        case ARCPackage.ARC_ROOT:
            return createARCRoot();
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
        case ARCPackage.ARC_LINE_TYPE:
            return createARCLineTypeFromString(eDataType, initialValue);
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
        case ARCPackage.ARC_LINE_TYPE:
            return convertARCLineTypeToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ARCState createARCState() {
        ARCStateImpl arcState = new ARCStateImpl();
        return arcState;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ARCPoint createARCPoint() {
        ARCPointImpl arcPoint = new ARCPointImpl();
        return arcPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ARCLine createARCLine() {
        ARCLineImpl arcLine = new ARCLineImpl();
        return arcLine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ARCRoot createARCRoot() {
        ARCRootImpl arcRoot = new ARCRootImpl();
        return arcRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ARCLineType createARCLineTypeFromString(EDataType eDataType, String initialValue) {
        ARCLineType result = ARCLineType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertARCLineTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ARCPackage getARCPackage() {
        return (ARCPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ARCPackage getPackage() {
        return ARCPackage.eINSTANCE;
    }

} // ARCFactoryImpl
