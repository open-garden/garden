/**
 */
package com.zipc.garden.model.fmcs.util;

import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.fmcs.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model, starting
 * with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is returned, which is
 * the result of the switch. <!-- end-user-doc -->
 * @see com.zipc.garden.model.fmcs.FMCSPackage
 * @generated
 */
public class FMCSSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static FMCSPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FMCSSwitch() {
        if (modelPackage == null) {
            modelPackage = FMCSPackage.eINSTANCE;
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
        case FMCSPackage.FMCS_ROOT: {
            FMCSRoot fmcsRoot = (FMCSRoot) theEObject;
            T result = caseFMCSRoot(fmcsRoot);
            if (result == null)
                result = caseAbstractRootElement(fmcsRoot);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FMCSPackage.FMCS_CONSTRAINT: {
            FMCSConstraint fmcsConstraint = (FMCSConstraint) theEObject;
            T result = caseFMCSConstraint(fmcsConstraint);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FMCSPackage.FMCS_EXPRESSION: {
            FMCSExpression fmcsExpression = (FMCSExpression) theEObject;
            T result = caseFMCSExpression(fmcsExpression);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FMCSPackage.FMCS_NOT_EXPRESSION: {
            FMCSNotExpression fmcsNotExpression = (FMCSNotExpression) theEObject;
            T result = caseFMCSNotExpression(fmcsNotExpression);
            if (result == null)
                result = caseFMCSExpression(fmcsNotExpression);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FMCSPackage.FMCS_AND_EXPRESSION: {
            FMCSAndExpression fmcsAndExpression = (FMCSAndExpression) theEObject;
            T result = caseFMCSAndExpression(fmcsAndExpression);
            if (result == null)
                result = caseFMCSExpression(fmcsAndExpression);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FMCSPackage.FMCS_OR_EXPRESSION: {
            FMCSOrExpression fmcsOrExpression = (FMCSOrExpression) theEObject;
            T result = caseFMCSOrExpression(fmcsOrExpression);
            if (result == null)
                result = caseFMCSExpression(fmcsOrExpression);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FMCSPackage.FMCS_IMPLIES_EXPRESSION: {
            FMCSImpliesExpression fmcsImpliesExpression = (FMCSImpliesExpression) theEObject;
            T result = caseFMCSImpliesExpression(fmcsImpliesExpression);
            if (result == null)
                result = caseFMCSExpression(fmcsImpliesExpression);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FMCSPackage.FMCS_SELECT_EXPRESSION: {
            FMCSSelectExpression fmcsSelectExpression = (FMCSSelectExpression) theEObject;
            T result = caseFMCSSelectExpression(fmcsSelectExpression);
            if (result == null)
                result = caseFMCSExpression(fmcsSelectExpression);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FMCSPackage.FMCS_OD_ELEMENT: {
            FMCSODElement fmcsodElement = (FMCSODElement) theEObject;
            T result = caseFMCSODElement(fmcsodElement);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FMCSPackage.FMCS_REMOVES_EXPRESSION: {
            FMCSRemovesExpression fmcsRemovesExpression = (FMCSRemovesExpression) theEObject;
            T result = caseFMCSRemovesExpression(fmcsRemovesExpression);
            if (result == null)
                result = caseFMCSExpression(fmcsRemovesExpression);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case FMCSPackage.FMCS_MUTEX_EXPRESSION: {
            FMCSMutexExpression fmcsMutexExpression = (FMCSMutexExpression) theEObject;
            T result = caseFMCSMutexExpression(fmcsMutexExpression);
            if (result == null)
                result = caseFMCSExpression(fmcsMutexExpression);
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
    public T caseFMCSRoot(FMCSRoot object) {
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
    public T caseFMCSConstraint(FMCSConstraint object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Expression</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFMCSExpression(FMCSExpression object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Implies Expression</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Implies Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFMCSImpliesExpression(FMCSImpliesExpression object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Or Expression</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Or Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFMCSOrExpression(FMCSOrExpression object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>And Expression</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>And Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFMCSAndExpression(FMCSAndExpression object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Not Expression</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Not Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFMCSNotExpression(FMCSNotExpression object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Select Expression</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Select Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFMCSSelectExpression(FMCSSelectExpression object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>OD Element</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>OD Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFMCSODElement(FMCSODElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Mutex Expression</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Mutex Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFMCSMutexExpression(FMCSMutexExpression object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Removes Expression</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Removes Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFMCSRemovesExpression(FMCSRemovesExpression object) {
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

} // FMCSSwitch
