/**
 */
package com.zipc.garden.model.tp.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.tp.TPPackage;
import com.zipc.garden.model.tp.TPPatternElement;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Pattern Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tp.impl.TPPatternElementImpl#isChecked <em>Checked</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPPatternElementImpl#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPPatternElementImpl#getValue <em>Value</em>}</li>
 * </ul>
 * @generated
 */
public class TPPatternElementImpl extends MinimalEObjectImpl.Container implements TPPatternElement {
    /**
     * The default value of the '{@link #isChecked() <em>Checked</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isChecked()
     * @generated
     * @ordered
     */
    protected static final boolean CHECKED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isChecked() <em>Checked</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isChecked()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected boolean checked = CHECKED_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getValue() <em>Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected static final String VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String value = VALUE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected TPPatternElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TPPackage.Literals.TP_PATTERN_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isChecked() {
        return checked;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setChecked(boolean newChecked) {
        boolean oldChecked = checked;
        checked = newChecked;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TPPackage.TP_PATTERN_ELEMENT__CHECKED, oldChecked, checked));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TPPackage.TP_PATTERN_ELEMENT__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setValue(String newValue) {
        String oldValue = value;
        value = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TPPackage.TP_PATTERN_ELEMENT__VALUE, oldValue, value));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case TPPackage.TP_PATTERN_ELEMENT__CHECKED:
            return isChecked();
        case TPPackage.TP_PATTERN_ELEMENT__NAME:
            return getName();
        case TPPackage.TP_PATTERN_ELEMENT__VALUE:
            return getValue();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case TPPackage.TP_PATTERN_ELEMENT__CHECKED:
            setChecked((Boolean) newValue);
            return;
        case TPPackage.TP_PATTERN_ELEMENT__NAME:
            setName((String) newValue);
            return;
        case TPPackage.TP_PATTERN_ELEMENT__VALUE:
            setValue((String) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case TPPackage.TP_PATTERN_ELEMENT__CHECKED:
            setChecked(CHECKED_EDEFAULT);
            return;
        case TPPackage.TP_PATTERN_ELEMENT__NAME:
            setName(NAME_EDEFAULT);
            return;
        case TPPackage.TP_PATTERN_ELEMENT__VALUE:
            setValue(VALUE_EDEFAULT);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case TPPackage.TP_PATTERN_ELEMENT__CHECKED:
            return checked != CHECKED_EDEFAULT;
        case TPPackage.TP_PATTERN_ELEMENT__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case TPPackage.TP_PATTERN_ELEMENT__VALUE:
            return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (checked: ");
        result.append(checked);
        result.append(", name: ");
        result.append(name);
        result.append(", value: ");
        result.append(value);
        result.append(')');
        return result.toString();
    }

} // TPPatternElementImpl
