/**
 */
package com.zipc.garden.model.scs.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scs.SCSPackage;
import com.zipc.garden.model.scs.SCSPattern;
import com.zipc.garden.model.scs.SCSPatternReference;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Pattern</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.scs.impl.SCSPatternImpl#getLsc <em>Lsc</em>}</li>
 * <li>{@link com.zipc.garden.model.scs.impl.SCSPatternImpl#getPatternreferences <em>Patternreferences</em>}</li>
 * <li>{@link com.zipc.garden.model.scs.impl.SCSPatternImpl#getId <em>Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scs.impl.SCSPatternImpl#getPatternValue <em>Pattern Value</em>}</li>
 * <li>{@link com.zipc.garden.model.scs.impl.SCSPatternImpl#getCscUuid <em>Csc Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.scs.impl.SCSPatternImpl#getCscFileName <em>Csc File Name</em>}</li>
 * </ul>
 * @generated
 */
public class SCSPatternImpl extends MinimalEObjectImpl.Container implements SCSPattern {
    /**
     * The default value of the '{@link #getLsc() <em>Lsc</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLsc()
     * @generated
     * @ordered
     */
    protected static final String LSC_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLsc() <em>Lsc</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLsc()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String lsc = LSC_EDEFAULT;

    /**
     * The cached value of the '{@link #getPatternreferences() <em>Patternreferences</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see #getPatternreferences()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<SCSPatternReference> patternreferences;

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
     * The default value of the '{@link #getCscUuid() <em>Csc Uuid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getCscUuid()
     * @generated
     * @ordered
     */
    protected static final String CSC_UUID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCscUuid() <em>Csc Uuid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getCscUuid()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String cscUuid = CSC_UUID_EDEFAULT;

    /**
     * The default value of the '{@link #getCscFileName() <em>Csc File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCscFileName()
     * @generated
     * @ordered
     */
    protected static final String CSC_FILE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCscFileName() <em>Csc File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCscFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String cscFileName = CSC_FILE_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected SCSPatternImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SCSPackage.Literals.SCS_PATTERN;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getLsc() {
        return lsc;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLsc(String newLsc) {
        String oldLsc = lsc;
        lsc = newLsc;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCSPackage.SCS_PATTERN__LSC, oldLsc, lsc));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<SCSPatternReference> getPatternreferences() {
        if (patternreferences == null) {
            patternreferences = new EObjectContainmentEList<SCSPatternReference>(SCSPatternReference.class, this, SCSPackage.SCS_PATTERN__PATTERNREFERENCES);
        }
        return patternreferences;
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
            eNotify(new ENotificationImpl(this, Notification.SET, SCSPackage.SCS_PATTERN__ID, oldId, id));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SCSPackage.SCS_PATTERN__PATTERN_VALUE, oldPatternValue, patternValue));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCscUuid() {
        return cscUuid;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCscUuid(String newCscUuid) {
        String oldCscUuid = cscUuid;
        cscUuid = newCscUuid;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCSPackage.SCS_PATTERN__CSC_UUID, oldCscUuid, cscUuid));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCscFileName() {
        return cscFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCscFileName(String newCscFileName) {
        String oldCscFileName = cscFileName;
        cscFileName = newCscFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCSPackage.SCS_PATTERN__CSC_FILE_NAME, oldCscFileName, cscFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case SCSPackage.SCS_PATTERN__PATTERNREFERENCES:
            return ((InternalEList<?>) getPatternreferences()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case SCSPackage.SCS_PATTERN__LSC:
            return getLsc();
        case SCSPackage.SCS_PATTERN__PATTERNREFERENCES:
            return getPatternreferences();
        case SCSPackage.SCS_PATTERN__ID:
            return getId();
        case SCSPackage.SCS_PATTERN__PATTERN_VALUE:
            return getPatternValue();
        case SCSPackage.SCS_PATTERN__CSC_UUID:
            return getCscUuid();
        case SCSPackage.SCS_PATTERN__CSC_FILE_NAME:
            return getCscFileName();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case SCSPackage.SCS_PATTERN__LSC:
            setLsc((String) newValue);
            return;
        case SCSPackage.SCS_PATTERN__PATTERNREFERENCES:
            getPatternreferences().clear();
            getPatternreferences().addAll((Collection<? extends SCSPatternReference>) newValue);
            return;
        case SCSPackage.SCS_PATTERN__ID:
            setId((String) newValue);
            return;
        case SCSPackage.SCS_PATTERN__PATTERN_VALUE:
            setPatternValue((String) newValue);
            return;
        case SCSPackage.SCS_PATTERN__CSC_UUID:
            setCscUuid((String) newValue);
            return;
        case SCSPackage.SCS_PATTERN__CSC_FILE_NAME:
            setCscFileName((String) newValue);
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
        case SCSPackage.SCS_PATTERN__LSC:
            setLsc(LSC_EDEFAULT);
            return;
        case SCSPackage.SCS_PATTERN__PATTERNREFERENCES:
            getPatternreferences().clear();
            return;
        case SCSPackage.SCS_PATTERN__ID:
            setId(ID_EDEFAULT);
            return;
        case SCSPackage.SCS_PATTERN__PATTERN_VALUE:
            setPatternValue(PATTERN_VALUE_EDEFAULT);
            return;
        case SCSPackage.SCS_PATTERN__CSC_UUID:
            setCscUuid(CSC_UUID_EDEFAULT);
            return;
        case SCSPackage.SCS_PATTERN__CSC_FILE_NAME:
            setCscFileName(CSC_FILE_NAME_EDEFAULT);
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
        case SCSPackage.SCS_PATTERN__LSC:
            return LSC_EDEFAULT == null ? lsc != null : !LSC_EDEFAULT.equals(lsc);
        case SCSPackage.SCS_PATTERN__PATTERNREFERENCES:
            return patternreferences != null && !patternreferences.isEmpty();
        case SCSPackage.SCS_PATTERN__ID:
            return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
        case SCSPackage.SCS_PATTERN__PATTERN_VALUE:
            return PATTERN_VALUE_EDEFAULT == null ? patternValue != null : !PATTERN_VALUE_EDEFAULT.equals(patternValue);
        case SCSPackage.SCS_PATTERN__CSC_UUID:
            return CSC_UUID_EDEFAULT == null ? cscUuid != null : !CSC_UUID_EDEFAULT.equals(cscUuid);
        case SCSPackage.SCS_PATTERN__CSC_FILE_NAME:
            return CSC_FILE_NAME_EDEFAULT == null ? cscFileName != null : !CSC_FILE_NAME_EDEFAULT.equals(cscFileName);
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
        result.append(" (lsc: ");
        result.append(lsc);
        result.append(", id: ");
        result.append(id);
        result.append(", patternValue: ");
        result.append(patternValue);
        result.append(", cscUuid: ");
        result.append(cscUuid);
        result.append(", cscFileName: ");
        result.append(cscFileName);
        result.append(')');
        return result.toString();
    }

} // SCSPatternImpl
