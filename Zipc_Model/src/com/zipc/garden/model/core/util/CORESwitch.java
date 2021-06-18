/**
 */
package com.zipc.garden.model.core.util;

import com.zipc.garden.model.core.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model, starting
 * with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is returned, which is
 * the result of the switch. <!-- end-user-doc -->
 * @see com.zipc.garden.model.core.COREPackage
 * @generated
 */
public class CORESwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static COREPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public CORESwitch() {
        if (modelPackage == null) {
            modelPackage = COREPackage.eINSTANCE;
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
        case COREPackage.ABSTRACT_ROOT_ELEMENT: {
            AbstractRootElement abstractRootElement = (AbstractRootElement) theEObject;
            T result = caseAbstractRootElement(abstractRootElement);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case COREPackage.REFERENCE: {
            Reference reference = (Reference) theEObject;
            T result = caseReference(reference);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case COREPackage.ABSTRACT_DIAGRAM: {
            AbstractDiagram abstractDiagram = (AbstractDiagram) theEObject;
            T result = caseAbstractDiagram(abstractDiagram);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case COREPackage.ABSTRACT_NODE: {
            AbstractNode abstractNode = (AbstractNode) theEObject;
            T result = caseAbstractNode(abstractNode);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case COREPackage.MEMO: {
            Memo memo = (Memo) theEObject;
            T result = caseMemo(memo);
            if (result == null)
                result = caseAbstractNode(memo);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case COREPackage.ABSTRACT_POINT: {
            AbstractPoint abstractPoint = (AbstractPoint) theEObject;
            T result = caseAbstractPoint(abstractPoint);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case COREPackage.ABSTRACT_LINE: {
            AbstractLine abstractLine = (AbstractLine) theEObject;
            T result = caseAbstractLine(abstractLine);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case COREPackage.ABSTRACT_STYLE: {
            AbstractStyle abstractStyle = (AbstractStyle) theEObject;
            T result = caseAbstractStyle(abstractStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case COREPackage.ABSTRACT_SEARCH_CONDITION: {
            AbstractSearchCondition abstractSearchCondition = (AbstractSearchCondition) theEObject;
            T result = caseAbstractSearchCondition(abstractSearchCondition);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case COREPackage.ABSTRACT_SETTING: {
            AbstractSetting abstractSetting = (AbstractSetting) theEObject;
            T result = caseAbstractSetting(abstractSetting);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case COREPackage.SETTING_INTERFACE: {
            SettingInterface settingInterface = (SettingInterface) theEObject;
            T result = caseSettingInterface(settingInterface);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
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
     * Returns the result of interpreting the object as an instance of '<em>Reference</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Reference</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseReference(Reference object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Memo</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Memo</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMemo(Memo object) {
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

} // CORESwitch
