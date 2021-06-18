/**
 */
package com.zipc.garden.model.cb.util;

import com.zipc.garden.model.cb.*;

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractNode;
import com.zipc.garden.model.core.AbstractRootElement;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model, starting
 * with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is returned, which is
 * the result of the switch. <!-- end-user-doc -->
 * @see com.zipc.garden.model.cb.CBPackage
 * @generated
 */
public class CBSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static CBPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public CBSwitch() {
        if (modelPackage == null) {
            modelPackage = CBPackage.eINSTANCE;
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
        case CBPackage.CB_ROOT: {
            CBRoot cbRoot = (CBRoot) theEObject;
            T result = caseCBRoot(cbRoot);
            if (result == null)
                result = caseAbstractRootElement(cbRoot);
            if (result == null)
                result = caseAbstractDiagram(cbRoot);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case CBPackage.CB_LOGIC: {
            CBLogic cbLogic = (CBLogic) theEObject;
            T result = caseCBLogic(cbLogic);
            if (result == null)
                result = caseAbstractNode(cbLogic);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case CBPackage.CB_FILE: {
            CBFile cbFile = (CBFile) theEObject;
            T result = caseCBFile(cbFile);
            if (result == null)
                result = caseAbstractNode(cbFile);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case CBPackage.CB_ALL_COMBINATION: {
            CBAllCombination cbAllCombination = (CBAllCombination) theEObject;
            T result = caseCBAllCombination(cbAllCombination);
            if (result == null)
                result = caseCBLogicType(cbAllCombination);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case CBPackage.CB_PAIR_WISE: {
            CBPairWise cbPairWise = (CBPairWise) theEObject;
            T result = caseCBPairWise(cbPairWise);
            if (result == null)
                result = caseCBLogicType(cbPairWise);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case CBPackage.CB_LOGIC_TYPE: {
            CBLogicType cbLogicType = (CBLogicType) theEObject;
            T result = caseCBLogicType(cbLogicType);
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
    public T caseCBRoot(CBRoot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Logic</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Logic</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCBLogic(CBLogic object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>File</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>File</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCBFile(CBFile object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>All Combination</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>All Combination</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCBAllCombination(CBAllCombination object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Pair Wise</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Pair Wise</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCBPairWise(CBPairWise object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Logic Type</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Logic Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCBLogicType(CBLogicType object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Abstract Node</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractNode(AbstractNode object) {
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

} // CBSwitch
