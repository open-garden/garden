/**
 */
package com.zipc.garden.model.arc.util;

import com.zipc.garden.model.arc.*;

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractLine;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.AbstractRootElement;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model, starting
 * with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is returned, which is
 * the result of the switch. <!-- end-user-doc -->
 * @see com.zipc.garden.model.arc.ARCPackage
 * @generated
 */
public class ARCSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static ARCPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ARCSwitch() {
        if (modelPackage == null) {
            modelPackage = ARCPackage.eINSTANCE;
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
        case ARCPackage.ARC_STATE: {
            ARCState arcState = (ARCState) theEObject;
            T result = caseARCState(arcState);
            if (result == null)
                result = caseARCVertex(arcState);
            if (result == null)
                result = caseARCReferenceable(arcState);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ARCPackage.ARC_VERTEX: {
            ARCVertex arcVertex = (ARCVertex) theEObject;
            T result = caseARCVertex(arcVertex);
            if (result == null)
                result = caseARCReferenceable(arcVertex);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ARCPackage.ARC_POINT: {
            ARCPoint arcPoint = (ARCPoint) theEObject;
            T result = caseARCPoint(arcPoint);
            if (result == null)
                result = caseAbstractPoint(arcPoint);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ARCPackage.ARC_LINE: {
            ARCLine arcLine = (ARCLine) theEObject;
            T result = caseARCLine(arcLine);
            if (result == null)
                result = caseARCReferenceable(arcLine);
            if (result == null)
                result = caseARCAbstractLine(arcLine);
            if (result == null)
                result = caseAbstractLine(arcLine);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ARCPackage.ARC_ABSTRACT_LINE: {
            ARCAbstractLine arcAbstractLine = (ARCAbstractLine) theEObject;
            T result = caseARCAbstractLine(arcAbstractLine);
            if (result == null)
                result = caseAbstractLine(arcAbstractLine);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ARCPackage.ARC_ROOT: {
            ARCRoot arcRoot = (ARCRoot) theEObject;
            T result = caseARCRoot(arcRoot);
            if (result == null)
                result = caseAbstractRootElement(arcRoot);
            if (result == null)
                result = caseAbstractDiagram(arcRoot);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ARCPackage.ARC_REFERENCEABLE: {
            ARCReferenceable arcReferenceable = (ARCReferenceable) theEObject;
            T result = caseARCReferenceable(arcReferenceable);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>State</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseARCState(ARCState object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Vertex</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Vertex</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseARCVertex(ARCVertex object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Point</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Point</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseARCPoint(ARCPoint object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Line</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Line</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseARCLine(ARCLine object) {
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
    public T caseARCAbstractLine(ARCAbstractLine object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Root</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseARCRoot(ARCRoot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Referenceable</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Referenceable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseARCReferenceable(ARCReferenceable object) {
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

} // ARCSwitch
