/**
 */
package com.zipc.garden.model.fsm.impl;

import com.zipc.garden.model.fsm.*;

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
public class FSMFactoryImpl extends EFactoryImpl implements FSMFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static FSMFactory init() {
        try {
            FSMFactory theFSMFactory = (FSMFactory) EPackage.Registry.INSTANCE.getEFactory(FSMPackage.eNS_URI);
            if (theFSMFactory != null) {
                return theFSMFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new FSMFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FSMFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case FSMPackage.FSM_DSTATE_MACHINE:
            return createFSMDStateMachine();
        case FSMPackage.FSM_DPROPERTY:
            return createFSMDProperty();
        case FSMPackage.FSM_DSTATE:
            return createFSMDState();
        case FSMPackage.FSM_DTRANSITION:
            return createFSMDTransition();
        case FSMPackage.FSM_DMEMO:
            return createFSMDMemo();
        case FSMPackage.FSM_DREGION:
            return createFSMDRegion();
        case FSMPackage.FSM_DLINE:
            return createFSMDLine();
        case FSMPackage.FSM_DEVENT:
            return createFSMDEvent();
        case FSMPackage.FSM_DACTION:
            return createFSMDAction();
        case FSMPackage.FSM_DPOINT:
            return createFSMDPoint();
        case FSMPackage.FSM_DVARIABLE:
            return createFSMDVariable();
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
        case FSMPackage.FSM_DPSEUDO_STATE_TYPE:
            return createFSMDPseudoStateTypeFromString(eDataType, initialValue);
        case FSMPackage.FSM_DLINE_TYPE:
            return createFSMDLineTypeFromString(eDataType, initialValue);
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
        case FSMPackage.FSM_DPSEUDO_STATE_TYPE:
            return convertFSMDPseudoStateTypeToString(eDataType, instanceValue);
        case FSMPackage.FSM_DLINE_TYPE:
            return convertFSMDLineTypeToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDStateMachine createFSMDStateMachine() {
        FSMDStateMachineImpl fsmdStateMachine = new FSMDStateMachineImpl();
        return fsmdStateMachine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDProperty createFSMDProperty() {
        FSMDPropertyImpl fsmdProperty = new FSMDPropertyImpl();
        return fsmdProperty;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDState createFSMDState() {
        FSMDStateImpl fsmdState = new FSMDStateImpl();
        return fsmdState;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDTransition createFSMDTransition() {
        FSMDTransitionImpl fsmdTransition = new FSMDTransitionImpl();
        return fsmdTransition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDMemo createFSMDMemo() {
        FSMDMemoImpl fsmdMemo = new FSMDMemoImpl();
        return fsmdMemo;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDRegion createFSMDRegion() {
        FSMDRegionImpl fsmdRegion = new FSMDRegionImpl();
        return fsmdRegion;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDLine createFSMDLine() {
        FSMDLineImpl fsmdLine = new FSMDLineImpl();
        return fsmdLine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDEvent createFSMDEvent() {
        FSMDEventImpl fsmdEvent = new FSMDEventImpl();
        return fsmdEvent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDAction createFSMDAction() {
        FSMDActionImpl fsmdAction = new FSMDActionImpl();
        return fsmdAction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDPoint createFSMDPoint() {
        FSMDPointImpl fsmdPoint = new FSMDPointImpl();
        return fsmdPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDVariable createFSMDVariable() {
        FSMDVariableImpl fsmdVariable = new FSMDVariableImpl();
        return fsmdVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FSMDPseudoStateType createFSMDPseudoStateTypeFromString(EDataType eDataType, String initialValue) {
        FSMDPseudoStateType result = FSMDPseudoStateType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertFSMDPseudoStateTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FSMDLineType createFSMDLineTypeFromString(EDataType eDataType, String initialValue) {
        FSMDLineType result = FSMDLineType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertFSMDLineTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMPackage getFSMPackage() {
        return (FSMPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static FSMPackage getPackage() {
        return FSMPackage.eINSTANCE;
    }

} // FSMFactoryImpl
