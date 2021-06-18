/**
 */
package com.zipc.garden.model.fsm.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.fsm.FSMDProperty;
import com.zipc.garden.model.fsm.FSMPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>DProperty</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDPropertyImpl#getKey <em>Key</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDPropertyImpl#getValue <em>Value</em>}</li>
 * </ul>
 * @generated
 */
public class FSMDPropertyImpl extends MinimalEObjectImpl.Container implements FSMDProperty {
    /**
     * The default value of the '{@link #getKey() <em>Key</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getKey()
     * @generated
     * @ordered
     */
    protected static final String KEY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getKey() <em>Key</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getKey()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String key = KEY_EDEFAULT;

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
    protected FSMDPropertyImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FSMPackage.Literals.FSM_DPROPERTY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getKey() {
        return key;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setKey(String newKey) {
        String oldKey = key;
        key = newKey;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DPROPERTY__KEY, oldKey, key));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DPROPERTY__VALUE, oldValue, value));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case FSMPackage.FSM_DPROPERTY__KEY:
            return getKey();
        case FSMPackage.FSM_DPROPERTY__VALUE:
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
        case FSMPackage.FSM_DPROPERTY__KEY:
            setKey((String) newValue);
            return;
        case FSMPackage.FSM_DPROPERTY__VALUE:
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
        case FSMPackage.FSM_DPROPERTY__KEY:
            setKey(KEY_EDEFAULT);
            return;
        case FSMPackage.FSM_DPROPERTY__VALUE:
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
        case FSMPackage.FSM_DPROPERTY__KEY:
            return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
        case FSMPackage.FSM_DPROPERTY__VALUE:
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
        result.append(" (key: ");
        result.append(key);
        result.append(", value: ");
        result.append(value);
        result.append(')');
        return result.toString();
    }

} // FSMDPropertyImpl
