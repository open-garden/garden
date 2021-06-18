/**
 */
package com.zipc.garden.model.scs.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scs.SCSPackage;
import com.zipc.garden.model.scs.SCSPatternReference;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Pattern Reference</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.scs.impl.SCSPatternReferenceImpl#getFileId <em>File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scs.impl.SCSPatternReferenceImpl#getPatternId <em>Pattern Id</em>}</li>
 * </ul>
 * @generated
 */
public class SCSPatternReferenceImpl extends MinimalEObjectImpl.Container implements SCSPatternReference {
    /**
     * The default value of the '{@link #getFileId() <em>File Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFileId()
     * @generated
     * @ordered
     */
    protected static final long FILE_ID_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getFileId() <em>File Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long fileId = FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getPatternId() <em>Pattern Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getPatternId()
     * @generated
     * @ordered
     */
    protected static final long PATTERN_ID_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getPatternId() <em>Pattern Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getPatternId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long patternId = PATTERN_ID_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected SCSPatternReferenceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SCSPackage.Literals.SCS_PATTERN_REFERENCE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getFileId() {
        return fileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFileId(long newFileId) {
        long oldFileId = fileId;
        fileId = newFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCSPackage.SCS_PATTERN_REFERENCE__FILE_ID, oldFileId, fileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getPatternId() {
        return patternId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setPatternId(long newPatternId) {
        long oldPatternId = patternId;
        patternId = newPatternId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCSPackage.SCS_PATTERN_REFERENCE__PATTERN_ID, oldPatternId, patternId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case SCSPackage.SCS_PATTERN_REFERENCE__FILE_ID:
            return getFileId();
        case SCSPackage.SCS_PATTERN_REFERENCE__PATTERN_ID:
            return getPatternId();
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
        case SCSPackage.SCS_PATTERN_REFERENCE__FILE_ID:
            setFileId((Long) newValue);
            return;
        case SCSPackage.SCS_PATTERN_REFERENCE__PATTERN_ID:
            setPatternId((Long) newValue);
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
        case SCSPackage.SCS_PATTERN_REFERENCE__FILE_ID:
            setFileId(FILE_ID_EDEFAULT);
            return;
        case SCSPackage.SCS_PATTERN_REFERENCE__PATTERN_ID:
            setPatternId(PATTERN_ID_EDEFAULT);
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
        case SCSPackage.SCS_PATTERN_REFERENCE__FILE_ID:
            return fileId != FILE_ID_EDEFAULT;
        case SCSPackage.SCS_PATTERN_REFERENCE__PATTERN_ID:
            return patternId != PATTERN_ID_EDEFAULT;
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
        result.append(" (fileId: ");
        result.append(fileId);
        result.append(", patternId: ");
        result.append(patternId);
        result.append(')');
        return result.toString();
    }

} // SCSPatternReferenceImpl
