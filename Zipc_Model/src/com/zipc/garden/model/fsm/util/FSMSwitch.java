/**
 */
package com.zipc.garden.model.fsm.util;

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractLine;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.AbstractRootElement;

import com.zipc.garden.model.fsm.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model, starting
 * with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is returned, which is
 * the result of the switch. <!-- end-user-doc -->
 * @see com.zipc.garden.model.fsm.FSMPackage
 * @generated
 */
public class FSMSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static FSMPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FSMSwitch() {
        if (modelPackage == null) {
            modelPackage = FSMPackage.eINSTANCE;
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
        case FSMPackage.FSM_DSTATE_MACHINE: {
            FSMDStateMachine fsmdStateMachine = (FSMDStateMachine) theEObject;
            T result = caseFSMDStateMachine(fsmdStateMachine);
            if (result == null)
                result = caseAbstractRootElement(fsmdStateMachine);
            if (result == null)
                result = caseAbstractDiagram(fsmdStateMachine);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FSMPackage.FSM_DVERTEX: {
            FSMDVertex fsmdVertex = (FSMDVertex) theEObject;
            T result = caseFSMDVertex(fsmdVertex);
            if (result == null)
                result = caseFSMDReferenceable(fsmdVertex);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FSMPackage.FSM_DPROPERTY: {
            FSMDProperty fsmdProperty = (FSMDProperty) theEObject;
            T result = caseFSMDProperty(fsmdProperty);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FSMPackage.FSM_DSTATE: {
            FSMDState fsmdState = (FSMDState) theEObject;
            T result = caseFSMDState(fsmdState);
            if (result == null)
                result = caseFSMDVertex(fsmdState);
            if (result == null)
                result = caseFSMDReferenceable(fsmdState);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FSMPackage.FSM_DTRANSITION: {
            FSMDTransition fsmdTransition = (FSMDTransition) theEObject;
            T result = caseFSMDTransition(fsmdTransition);
            if (result == null)
                result = caseFSMDReferenceable(fsmdTransition);
            if (result == null)
                result = caseFSMDAbstractLine(fsmdTransition);
            if (result == null)
                result = caseAbstractLine(fsmdTransition);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FSMPackage.FSM_DREFERENCEABLE: {
            FSMDReferenceable fsmdReferenceable = (FSMDReferenceable) theEObject;
            T result = caseFSMDReferenceable(fsmdReferenceable);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FSMPackage.FSM_DMEMO: {
            FSMDMemo fsmdMemo = (FSMDMemo) theEObject;
            T result = caseFSMDMemo(fsmdMemo);
            if (result == null)
                result = caseFSMDVertex(fsmdMemo);
            if (result == null)
                result = caseFSMDReferenceable(fsmdMemo);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FSMPackage.FSM_DREGION: {
            FSMDRegion fsmdRegion = (FSMDRegion) theEObject;
            T result = caseFSMDRegion(fsmdRegion);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FSMPackage.FSM_DLINE: {
            FSMDLine fsmdLine = (FSMDLine) theEObject;
            T result = caseFSMDLine(fsmdLine);
            if (result == null)
                result = caseFSMDAbstractLine(fsmdLine);
            if (result == null)
                result = caseAbstractLine(fsmdLine);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FSMPackage.FSM_DEVENT: {
            FSMDEvent fsmdEvent = (FSMDEvent) theEObject;
            T result = caseFSMDEvent(fsmdEvent);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FSMPackage.FSM_DACTION: {
            FSMDAction fsmdAction = (FSMDAction) theEObject;
            T result = caseFSMDAction(fsmdAction);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FSMPackage.FSM_DABSTRACT_LINE: {
            FSMDAbstractLine fsmdAbstractLine = (FSMDAbstractLine) theEObject;
            T result = caseFSMDAbstractLine(fsmdAbstractLine);
            if (result == null)
                result = caseAbstractLine(fsmdAbstractLine);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FSMPackage.FSM_DPOINT: {
            FSMDPoint fsmdPoint = (FSMDPoint) theEObject;
            T result = caseFSMDPoint(fsmdPoint);
            if (result == null)
                result = caseAbstractPoint(fsmdPoint);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FSMPackage.FSM_DVARIABLE: {
            FSMDVariable fsmdVariable = (FSMDVariable) theEObject;
            T result = caseFSMDVariable(fsmdVariable);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DState Machine</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DState Machine</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFSMDStateMachine(FSMDStateMachine object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DVertex</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DVertex</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFSMDVertex(FSMDVertex object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DProperty</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DProperty</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFSMDProperty(FSMDProperty object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DState</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DState</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFSMDState(FSMDState object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DTransition</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DTransition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFSMDTransition(FSMDTransition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DReferenceable</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DReferenceable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFSMDReferenceable(FSMDReferenceable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DMemo</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DMemo</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFSMDMemo(FSMDMemo object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DRegion</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DRegion</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFSMDRegion(FSMDRegion object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DLine</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DLine</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFSMDLine(FSMDLine object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DEvent</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DEvent</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFSMDEvent(FSMDEvent object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DAction</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DAction</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFSMDAction(FSMDAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DAbstract Line</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DAbstract Line</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFSMDAbstractLine(FSMDAbstractLine object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DPoint</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DPoint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFSMDPoint(FSMDPoint object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DVariable</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DVariable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFSMDVariable(FSMDVariable object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Abstract Diagram</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Diagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractDiagram(AbstractDiagram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Line</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Line</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractLine(AbstractLine object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Point</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Point</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractPoint(AbstractPoint object) {
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

} // FSMSwitch
