/**
 */
package com.zipc.garden.model.bps.util;

import com.zipc.garden.model.bps.*;

import com.zipc.garden.model.core.AbstractRootElement;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model, starting
 * with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is returned, which is
 * the result of the switch. <!-- end-user-doc -->
 * @see com.zipc.garden.model.bps.BPSPackage
 * @generated
 */
public class BPSSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static BPSPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPSSwitch() {
        if (modelPackage == null) {
            modelPackage = BPSPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case BPSPackage.BPS_ROOT: {
            BPSRoot bpsRoot = (BPSRoot) theEObject;
            T result = caseBPSRoot(bpsRoot);
            if (result == null)
                result = caseAbstractRootElement(bpsRoot);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPSPackage.BPS_OPTION: {
            BPSOption bpsOption = (BPSOption) theEObject;
            T result = caseBPSOption(bpsOption);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPSPackage.BPS_STATE_COMBINATION_OPTION: {
            BPSStateCombinationOption bpsStateCombinationOption = (BPSStateCombinationOption) theEObject;
            T result = caseBPSStateCombinationOption(bpsStateCombinationOption);
            if (result == null)
                result = caseBPSOption(bpsStateCombinationOption);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPSPackage.BPS_NSWITCH_OPTION: {
            BPSNSwitchOption bpsnSwitchOption = (BPSNSwitchOption) theEObject;
            T result = caseBPSNSwitchOption(bpsnSwitchOption);
            if (result == null)
                result = caseBPSOption(bpsnSwitchOption);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPSPackage.BPS_PATH_COMBINATION_OPTION: {
            BPSPathCombinationOption bpsPathCombinationOption = (BPSPathCombinationOption) theEObject;
            T result = caseBPSPathCombinationOption(bpsPathCombinationOption);
            if (result == null)
                result = caseBPSOption(bpsPathCombinationOption);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPSPackage.BPS_ENABLEMENT: {
            BPSEnablement bpsEnablement = (BPSEnablement) theEObject;
            T result = caseBPSEnablement(bpsEnablement);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPSPackage.BPS_VARIABLE: {
            BPSVariable bpsVariable = (BPSVariable) theEObject;
            T result = caseBPSVariable(bpsVariable);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPSPackage.BPS_DATAFLOW: {
            BPSDataflow bpsDataflow = (BPSDataflow) theEObject;
            T result = caseBPSDataflow(bpsDataflow);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPSPackage.BPS_INSTANCE_ELEMENT: {
            BPSInstanceElement bpsInstanceElement = (BPSInstanceElement) theEObject;
            T result = caseBPSInstanceElement(bpsInstanceElement);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPSPackage.BPS_INSTANCE_ARC: {
            BPSInstanceArc bpsInstanceArc = (BPSInstanceArc) theEObject;
            T result = caseBPSInstanceArc(bpsInstanceArc);
            if (result == null)
                result = caseBPSInstanceElement(bpsInstanceArc);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE: {
            BPSInstanceStateMachine bpsInstanceStateMachine = (BPSInstanceStateMachine) theEObject;
            T result = caseBPSInstanceStateMachine(bpsInstanceStateMachine);
            if (result == null)
                result = caseBPSInstanceElement(bpsInstanceStateMachine);
            if (result == null)
                result = caseBPSEnablement(bpsInstanceStateMachine);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPSPackage.BPS_INSTANCE_STATE: {
            BPSInstanceState bpsInstanceState = (BPSInstanceState) theEObject;
            T result = caseBPSInstanceState(bpsInstanceState);
            if (result == null)
                result = caseBPSInstanceElement(bpsInstanceState);
            if (result == null)
                result = caseBPSEnablement(bpsInstanceState);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPSPackage.BPS_INSTANCE_TRANSITION: {
            BPSInstanceTransition bpsInstanceTransition = (BPSInstanceTransition) theEObject;
            T result = caseBPSInstanceTransition(bpsInstanceTransition);
            if (result == null)
                result = caseBPSInstanceElement(bpsInstanceTransition);
            if (result == null)
                result = caseBPSEnablement(bpsInstanceTransition);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Root</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSRoot(BPSRoot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Option</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Option</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSOption(BPSOption object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>State Combination Option</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>State Combination Option</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSStateCombinationOption(BPSStateCombinationOption object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>NSwitch Option</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>NSwitch Option</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSNSwitchOption(BPSNSwitchOption object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Path Combination Option</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Path Combination Option</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSPathCombinationOption(BPSPathCombinationOption object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Enablement</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Enablement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSEnablement(BPSEnablement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Variable</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSVariable(BPSVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Dataflow</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dataflow</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSDataflow(BPSDataflow object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Instance Element</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Instance Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSInstanceElement(BPSInstanceElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Instance Arc</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Instance Arc</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSInstanceArc(BPSInstanceArc object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Instance State Machine</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Instance State Machine</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSInstanceStateMachine(BPSInstanceStateMachine object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Instance State</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Instance State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSInstanceState(BPSInstanceState object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Instance Transition</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Instance Transition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSInstanceTransition(BPSInstanceTransition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Root Element</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Root Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractRootElement(AbstractRootElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} // BPSSwitch
