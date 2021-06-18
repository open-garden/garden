/**
 */
package com.zipc.garden.model.project.impl;

import com.zipc.garden.model.project.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class GPModelFactoryImpl extends EFactoryImpl implements GPModelFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static GPModelFactory init() {
        try {
            GPModelFactory theGPModelFactory = (GPModelFactory) EPackage.Registry.INSTANCE.getEFactory(GPModelPackage.eNS_URI);
            if (theGPModelFactory != null) {
                return theGPModelFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new GPModelFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public GPModelFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case GPModelPackage.GP_RESOURCE:
            return createGPResource();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public GPResource createGPResource() {
        GPResourceImpl gpResource = new GPResourceImpl();
        return gpResource;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public GPModelPackage getGPModelPackage() {
        return (GPModelPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static GPModelPackage getPackage() {
        return GPModelPackage.eINSTANCE;
    }

} // GPModelFactoryImpl
