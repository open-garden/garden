/**
 */
package com.zipc.garden.model.tps.impl;

import com.zipc.garden.model.tps.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class TPSFactoryImpl extends EFactoryImpl implements TPSFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static TPSFactory init() {
        try {
            TPSFactory theTPSFactory = (TPSFactory) EPackage.Registry.INSTANCE.getEFactory(TPSPackage.eNS_URI);
            if (theTPSFactory != null) {
                return theTPSFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new TPSFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public TPSFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case TPSPackage.TPS_ROOT:
            return createTPSRoot();
        case TPSPackage.TPS_PATTERN_ELEMENT:
            return createTPSPatternElement();
        case TPSPackage.NODE_VARIABLE:
            return createNodeVariable();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TPSRoot createTPSRoot() {
        TPSRootImpl tpsRoot = new TPSRootImpl();
        return tpsRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TPSPatternElement createTPSPatternElement() {
        TPSPatternElementImpl tpsPatternElement = new TPSPatternElementImpl();
        return tpsPatternElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NodeVariable createNodeVariable() {
        NodeVariableImpl nodeVariable = new NodeVariableImpl();
        return nodeVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TPSPackage getTPSPackage() {
        return (TPSPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static TPSPackage getPackage() {
        return TPSPackage.eINSTANCE;
    }

} // TPSFactoryImpl
