/**
 */
package com.zipc.garden.model.cscs.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.cscs.CSCSPackage;
import com.zipc.garden.model.cscs.CSCSPattern;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Pattern</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.cscs.impl.CSCSPatternImpl#getId <em>Id</em>}</li>
 * <li>{@link com.zipc.garden.model.cscs.impl.CSCSPatternImpl#getPatternValue <em>Pattern Value</em>}</li>
 * <li>{@link com.zipc.garden.model.cscs.impl.CSCSPatternImpl#getCsc <em>Csc</em>}</li>
 * </ul>
 * @generated
 */
public class CSCSPatternImpl extends MinimalEObjectImpl.Container implements CSCSPattern {
    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String id = ID_EDEFAULT;

    /**
     * The default value of the '{@link #getPatternValue() <em>Pattern Value</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getPatternValue()
     * @generated
     * @ordered
     */
    protected static final String PATTERN_VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPatternValue() <em>Pattern Value</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getPatternValue()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String patternValue = PATTERN_VALUE_EDEFAULT;

    /**
     * The default value of the '{@link #getCsc() <em>Csc</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getCsc()
     * @generated
     * @ordered
     */
    protected static final String CSC_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCsc() <em>Csc</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getCsc()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String csc = CSC_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected CSCSPatternImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CSCSPackage.Literals.CSCS_PATTERN;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CSCSPackage.CSCS_PATTERN__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getPatternValue() {
        return patternValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setPatternValue(String newPatternValue) {
        String oldPatternValue = patternValue;
        patternValue = newPatternValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CSCSPackage.CSCS_PATTERN__PATTERN_VALUE, oldPatternValue, patternValue));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCsc() {
        return csc;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCsc(String newCsc) {
        String oldCsc = csc;
        csc = newCsc;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CSCSPackage.CSCS_PATTERN__CSC, oldCsc, csc));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case CSCSPackage.CSCS_PATTERN__ID:
            return getId();
        case CSCSPackage.CSCS_PATTERN__PATTERN_VALUE:
            return getPatternValue();
        case CSCSPackage.CSCS_PATTERN__CSC:
            return getCsc();
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
        case CSCSPackage.CSCS_PATTERN__ID:
            setId((String) newValue);
            return;
        case CSCSPackage.CSCS_PATTERN__PATTERN_VALUE:
            setPatternValue((String) newValue);
            return;
        case CSCSPackage.CSCS_PATTERN__CSC:
            setCsc((String) newValue);
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
        case CSCSPackage.CSCS_PATTERN__ID:
            setId(ID_EDEFAULT);
            return;
        case CSCSPackage.CSCS_PATTERN__PATTERN_VALUE:
            setPatternValue(PATTERN_VALUE_EDEFAULT);
            return;
        case CSCSPackage.CSCS_PATTERN__CSC:
            setCsc(CSC_EDEFAULT);
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
        case CSCSPackage.CSCS_PATTERN__ID:
            return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
        case CSCSPackage.CSCS_PATTERN__PATTERN_VALUE:
            return PATTERN_VALUE_EDEFAULT == null ? patternValue != null : !PATTERN_VALUE_EDEFAULT.equals(patternValue);
        case CSCSPackage.CSCS_PATTERN__CSC:
            return CSC_EDEFAULT == null ? csc != null : !CSC_EDEFAULT.equals(csc);
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
        result.append(" (id: ");
        result.append(id);
        result.append(", patternValue: ");
        result.append(patternValue);
        result.append(", csc: ");
        result.append(csc);
        result.append(')');
        return result.toString();
    }

} // CSCSPatternImpl
