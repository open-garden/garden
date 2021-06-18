/**
 */
package com.zipc.garden.model.fm.util;

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractRootElement;

import com.zipc.garden.model.core.AbstractStyle;
import com.zipc.garden.model.fm.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model, starting
 * with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is returned, which is
 * the result of the switch. <!-- end-user-doc -->
 * @see com.zipc.garden.model.fm.FMPackage
 * @generated
 */
public class FMSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static FMPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FMSwitch() {
        if (modelPackage == null) {
            modelPackage = FMPackage.eINSTANCE;
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
        case FMPackage.FM_ROOT: {
            FMRoot fmRoot = (FMRoot) theEObject;
            T result = caseFMRoot(fmRoot);
            if (result == null)
                result = caseAbstractRootElement(fmRoot);
            if (result == null)
                result = caseAbstractDiagram(fmRoot);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FMPackage.FM_NODE: {
            FMNode fmNode = (FMNode) theEObject;
            T result = caseFMNode(fmNode);
            if (result == null)
                result = caseAbstractStyle(fmNode);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FMPackage.FM_PROPERTY: {
            FMProperty fmProperty = (FMProperty) theEObject;
            T result = caseFMProperty(fmProperty);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FMPackage.FM_CONSTRAINT: {
            FMConstraint fmConstraint = (FMConstraint) theEObject;
            T result = caseFMConstraint(fmConstraint);
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
    public T caseFMRoot(FMRoot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Node</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFMNode(FMNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Property</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Property</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFMProperty(FMProperty object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Constraint</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFMConstraint(FMConstraint object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Abstract Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractStyle(AbstractStyle object) {
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

} // FMSwitch
