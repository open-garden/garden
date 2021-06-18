/**
 */
package com.zipc.garden.model.bp.util;

import com.zipc.garden.model.bp.*;

import com.zipc.garden.model.core.AbstractRootElement;

import com.zipc.garden.model.core.AbstractSearchCondition;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.core.SettingInterface;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model, starting
 * with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is returned, which is
 * the result of the switch. <!-- end-user-doc -->
 * @see com.zipc.garden.model.bp.BPPackage
 * @generated
 */
public class BPSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static BPPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPSwitch() {
        if (modelPackage == null) {
            modelPackage = BPPackage.eINSTANCE;
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
        case BPPackage.BP_ROOT: {
            BPRoot bpRoot = (BPRoot) theEObject;
            T result = caseBPRoot(bpRoot);
            if (result == null)
                result = caseAbstractRootElement(bpRoot);
            if (result == null)
                result = caseAbstractSearchCondition(bpRoot);
            if (result == null)
                result = caseSettingInterface(bpRoot);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPPackage.BP_STATE_MACHINE: {
            BPStateMachine bpStateMachine = (BPStateMachine) theEObject;
            T result = caseBPStateMachine(bpStateMachine);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPPackage.BP_BEHAVIOR_PATTERN: {
            BPBehaviorPattern bpBehaviorPattern = (BPBehaviorPattern) theEObject;
            T result = caseBPBehaviorPattern(bpBehaviorPattern);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPPackage.BP_BEHAVIOR: {
            BPBehavior bpBehavior = (BPBehavior) theEObject;
            T result = caseBPBehavior(bpBehavior);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPPackage.BP_STEP: {
            BPStep bpStep = (BPStep) theEObject;
            T result = caseBPStep(bpStep);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPPackage.BP_STATE: {
            BPState bpState = (BPState) theEObject;
            T result = caseBPState(bpState);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPPackage.BP_EVENT: {
            BPEvent bpEvent = (BPEvent) theEObject;
            T result = caseBPEvent(bpEvent);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPPackage.BP_SPEC: {
            BPSpec bpSpec = (BPSpec) theEObject;
            T result = caseBPSpec(bpSpec);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPPackage.BP_SPEC_ELEMENT: {
            BPSpecElement bpSpecElement = (BPSpecElement) theEObject;
            T result = caseBPSpecElement(bpSpecElement);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case BPPackage.BP_SETTING: {
            BPSetting bpSetting = (BPSetting) theEObject;
            T result = caseBPSetting(bpSetting);
            if (result == null)
                result = caseAbstractSetting(bpSetting);
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
    public T caseBPRoot(BPRoot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>State Machine</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>State Machine</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPStateMachine(BPStateMachine object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Behavior Pattern</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Behavior Pattern</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPBehaviorPattern(BPBehaviorPattern object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Behavior</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Behavior</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPBehavior(BPBehavior object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Step</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Step</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPStep(BPStep object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>State</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPState(BPState object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Event</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPEvent(BPEvent object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Spec</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Spec</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSpec(BPSpec object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Spec Element</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Spec Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSpecElement(BPSpecElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Setting</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Setting</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPSetting(BPSetting object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Setting</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Setting</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractSetting(AbstractSetting object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Abstract Search Condition</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Search Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractSearchCondition(AbstractSearchCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Setting Interface</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Setting Interface</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSettingInterface(SettingInterface object) {
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

} // BPSwitch
