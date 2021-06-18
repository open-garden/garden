/**
 */
package com.zipc.garden.model.cb.impl;

import com.zipc.garden.model.cb.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class CBFactoryImpl extends EFactoryImpl implements CBFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static CBFactory init() {
        try {
            CBFactory theCBFactory = (CBFactory) EPackage.Registry.INSTANCE.getEFactory(CBPackage.eNS_URI);
            if (theCBFactory != null) {
                return theCBFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new CBFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public CBFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case CBPackage.CB_ROOT:
            return createCBRoot();
        case CBPackage.CB_LOGIC:
            return createCBLogic();
        case CBPackage.CB_FILE:
            return createCBFile();
        case CBPackage.CB_ALL_COMBINATION:
            return createCBAllCombination();
        case CBPackage.CB_PAIR_WISE:
            return createCBPairWise();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CBRoot createCBRoot() {
        CBRootImpl cbRoot = new CBRootImpl();
        return cbRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CBLogic createCBLogic() {
        CBLogicImpl cbLogic = new CBLogicImpl();
        return cbLogic;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CBFile createCBFile() {
        CBFileImpl cbFile = new CBFileImpl();
        return cbFile;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CBAllCombination createCBAllCombination() {
        CBAllCombinationImpl cbAllCombination = new CBAllCombinationImpl();
        return cbAllCombination;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CBPairWise createCBPairWise() {
        CBPairWiseImpl cbPairWise = new CBPairWiseImpl();
        return cbPairWise;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CBPackage getCBPackage() {
        return (CBPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static CBPackage getPackage() {
        return CBPackage.eINSTANCE;
    }

} // CBFactoryImpl
