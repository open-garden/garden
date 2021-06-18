/**
 */
package com.zipc.garden.model.bps.impl;

import com.zipc.garden.model.bps.*;

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
public class BPSFactoryImpl extends EFactoryImpl implements BPSFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static BPSFactory init() {
        try {
            BPSFactory theBPSFactory = (BPSFactory) EPackage.Registry.INSTANCE.getEFactory(BPSPackage.eNS_URI);
            if (theBPSFactory != null) {
                return theBPSFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new BPSFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPSFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case BPSPackage.BPS_ROOT:
            return createBPSRoot();
        case BPSPackage.BPS_STATE_COMBINATION_OPTION:
            return createBPSStateCombinationOption();
        case BPSPackage.BPS_NSWITCH_OPTION:
            return createBPSNSwitchOption();
        case BPSPackage.BPS_PATH_COMBINATION_OPTION:
            return createBPSPathCombinationOption();
        case BPSPackage.BPS_VARIABLE:
            return createBPSVariable();
        case BPSPackage.BPS_DATAFLOW:
            return createBPSDataflow();
        case BPSPackage.BPS_INSTANCE_ARC:
            return createBPSInstanceArc();
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE:
            return createBPSInstanceStateMachine();
        case BPSPackage.BPS_INSTANCE_STATE:
            return createBPSInstanceState();
        case BPSPackage.BPS_INSTANCE_TRANSITION:
            return createBPSInstanceTransition();
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
        case BPSPackage.BPS_INSTANCE_PSEUDO_STATE_TYPE:
            return createBPSInstancePseudoStateTypeFromString(eDataType, initialValue);
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
        case BPSPackage.BPS_INSTANCE_PSEUDO_STATE_TYPE:
            return convertBPSInstancePseudoStateTypeToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSRoot createBPSRoot() {
        BPSRootImpl bpsRoot = new BPSRootImpl();
        return bpsRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSStateCombinationOption createBPSStateCombinationOption() {
        BPSStateCombinationOptionImpl bpsStateCombinationOption = new BPSStateCombinationOptionImpl();
        return bpsStateCombinationOption;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSNSwitchOption createBPSNSwitchOption() {
        BPSNSwitchOptionImpl bpsnSwitchOption = new BPSNSwitchOptionImpl();
        return bpsnSwitchOption;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSPathCombinationOption createBPSPathCombinationOption() {
        BPSPathCombinationOptionImpl bpsPathCombinationOption = new BPSPathCombinationOptionImpl();
        return bpsPathCombinationOption;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSVariable createBPSVariable() {
        BPSVariableImpl bpsVariable = new BPSVariableImpl();
        return bpsVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSDataflow createBPSDataflow() {
        BPSDataflowImpl bpsDataflow = new BPSDataflowImpl();
        return bpsDataflow;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSInstanceArc createBPSInstanceArc() {
        BPSInstanceArcImpl bpsInstanceArc = new BPSInstanceArcImpl();
        return bpsInstanceArc;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSInstanceStateMachine createBPSInstanceStateMachine() {
        BPSInstanceStateMachineImpl bpsInstanceStateMachine = new BPSInstanceStateMachineImpl();
        return bpsInstanceStateMachine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSInstanceState createBPSInstanceState() {
        BPSInstanceStateImpl bpsInstanceState = new BPSInstanceStateImpl();
        return bpsInstanceState;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSInstanceTransition createBPSInstanceTransition() {
        BPSInstanceTransitionImpl bpsInstanceTransition = new BPSInstanceTransitionImpl();
        return bpsInstanceTransition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPSInstancePseudoStateType createBPSInstancePseudoStateTypeFromString(EDataType eDataType, String initialValue) {
        BPSInstancePseudoStateType result = BPSInstancePseudoStateType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertBPSInstancePseudoStateTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSPackage getBPSPackage() {
        return (BPSPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static BPSPackage getPackage() {
        return BPSPackage.eINSTANCE;
    }

} // BPSFactoryImpl
