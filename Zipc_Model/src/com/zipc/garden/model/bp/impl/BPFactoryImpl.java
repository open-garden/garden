/**
 */
package com.zipc.garden.model.bp.impl;

import com.zipc.garden.model.bp.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class BPFactoryImpl extends EFactoryImpl implements BPFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static BPFactory init() {
        try {
            BPFactory theBPFactory = (BPFactory) EPackage.Registry.INSTANCE.getEFactory(BPPackage.eNS_URI);
            if (theBPFactory != null) {
                return theBPFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new BPFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case BPPackage.BP_ROOT:
            return createBPRoot();
        case BPPackage.BP_STATE_MACHINE:
            return createBPStateMachine();
        case BPPackage.BP_BEHAVIOR_PATTERN:
            return createBPBehaviorPattern();
        case BPPackage.BP_BEHAVIOR:
            return createBPBehavior();
        case BPPackage.BP_STEP:
            return createBPStep();
        case BPPackage.BP_STATE:
            return createBPState();
        case BPPackage.BP_EVENT:
            return createBPEvent();
        case BPPackage.BP_SPEC:
            return createBPSpec();
        case BPPackage.BP_SPEC_ELEMENT:
            return createBPSpecElement();
        case BPPackage.BP_SETTING:
            return createBPSetting();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPRoot createBPRoot() {
        BPRootImpl bpRoot = new BPRootImpl();
        return bpRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPStateMachine createBPStateMachine() {
        BPStateMachineImpl bpStateMachine = new BPStateMachineImpl();
        return bpStateMachine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPBehaviorPattern createBPBehaviorPattern() {
        BPBehaviorPatternImpl bpBehaviorPattern = new BPBehaviorPatternImpl();
        return bpBehaviorPattern;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPBehavior createBPBehavior() {
        BPBehaviorImpl bpBehavior = new BPBehaviorImpl();
        return bpBehavior;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPStep createBPStep() {
        BPStepImpl bpStep = new BPStepImpl();
        return bpStep;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPState createBPState() {
        BPStateImpl bpState = new BPStateImpl();
        return bpState;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPEvent createBPEvent() {
        BPEventImpl bpEvent = new BPEventImpl();
        return bpEvent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSpec createBPSpec() {
        BPSpecImpl bpSpec = new BPSpecImpl();
        return bpSpec;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSpecElement createBPSpecElement() {
        BPSpecElementImpl bpSpecElement = new BPSpecElementImpl();
        return bpSpecElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSetting createBPSetting() {
        BPSettingImpl bpSetting = new BPSettingImpl();
        return bpSetting;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPPackage getBPPackage() {
        return (BPPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static BPPackage getPackage() {
        return BPPackage.eINSTANCE;
    }

} // BPFactoryImpl
