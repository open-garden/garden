/**
 */
package com.zipc.garden.model.scs.util;

import com.zipc.garden.model.core.AbstractRootElement;

import com.zipc.garden.model.core.AbstractSearchCondition;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.core.SettingInterface;
import com.zipc.garden.model.scs.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model, starting
 * with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is returned, which is
 * the result of the switch. <!-- end-user-doc -->
 * @see com.zipc.garden.model.scs.SCSPackage
 * @generated
 */
public class SCSSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static SCSPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public SCSSwitch() {
        if (modelPackage == null) {
            modelPackage = SCSPackage.eINSTANCE;
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
        case SCSPackage.SCS_ROOT: {
            SCSRoot scsRoot = (SCSRoot) theEObject;
            T result = caseSCSRoot(scsRoot);
            if (result == null)
                result = caseAbstractRootElement(scsRoot);
            if (result == null)
                result = caseAbstractSearchCondition(scsRoot);
            if (result == null)
                result = caseSettingInterface(scsRoot);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case SCSPackage.SCS_PATTERN: {
            SCSPattern scsPattern = (SCSPattern) theEObject;
            T result = caseSCSPattern(scsPattern);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case SCSPackage.SCS_PATTERN_REFERENCE: {
            SCSPatternReference scsPatternReference = (SCSPatternReference) theEObject;
            T result = caseSCSPatternReference(scsPatternReference);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case SCSPackage.SCS_SETTING: {
            SCSSetting scsSetting = (SCSSetting) theEObject;
            T result = caseSCSSetting(scsSetting);
            if (result == null)
                result = caseAbstractSetting(scsSetting);
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
    public T caseSCSRoot(SCSRoot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Pattern</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Pattern</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSCSPattern(SCSPattern object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Pattern Reference</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Pattern Reference</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSCSPatternReference(SCSPatternReference object) {
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
    public T caseSCSSetting(SCSSetting object) {
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

} // SCSSwitch
