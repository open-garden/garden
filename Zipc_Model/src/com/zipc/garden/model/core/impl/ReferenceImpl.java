/**
 */
package com.zipc.garden.model.core.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.core.Reference;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Reference</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.impl.ReferenceImpl#getRefid <em>Refid</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.ReferenceImpl#getHash <em>Hash</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.ReferenceImpl#getRefName <em>Ref Name</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.ReferenceImpl#getRefExtension <em>Ref Extension</em>}</li>
 * </ul>
 * @generated
 */
public class ReferenceImpl extends MinimalEObjectImpl.Container implements Reference {
    /**
     * The default value of the '{@link #getRefid() <em>Refid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRefid()
     * @generated
     * @ordered
     */
    protected static final String REFID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRefid() <em>Refid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRefid()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String refid = REFID_EDEFAULT;

    /**
     * The default value of the '{@link #getHash() <em>Hash</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getHash()
     * @generated
     * @ordered
     */
    protected static final String HASH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHash() <em>Hash</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getHash()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String hash = HASH_EDEFAULT;

    /**
     * The default value of the '{@link #getRefName() <em>Ref Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getRefName()
     * @generated
     * @ordered
     */
    protected static final String REF_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRefName() <em>Ref Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getRefName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String refName = REF_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getRefExtension() <em>Ref Extension</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getRefExtension()
     * @generated
     * @ordered
     */
    protected static final String REF_EXTENSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRefExtension() <em>Ref Extension</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getRefExtension()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String refExtension = REF_EXTENSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected ReferenceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return COREPackage.Literals.REFERENCE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getRefid() {
        return refid;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRefid(String newRefid) {
        String oldRefid = refid;
        refid = newRefid;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.REFERENCE__REFID, oldRefid, refid));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getHash() {
        return hash;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setHash(String newHash) {
        String oldHash = hash;
        hash = newHash;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.REFERENCE__HASH, oldHash, hash));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getRefName() {
        return refName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRefName(String newRefName) {
        String oldRefName = refName;
        refName = newRefName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.REFERENCE__REF_NAME, oldRefName, refName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getRefExtension() {
        return refExtension;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRefExtension(String newRefExtension) {
        String oldRefExtension = refExtension;
        refExtension = newRefExtension;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.REFERENCE__REF_EXTENSION, oldRefExtension, refExtension));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case COREPackage.REFERENCE__REFID:
            return getRefid();
        case COREPackage.REFERENCE__HASH:
            return getHash();
        case COREPackage.REFERENCE__REF_NAME:
            return getRefName();
        case COREPackage.REFERENCE__REF_EXTENSION:
            return getRefExtension();
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
        case COREPackage.REFERENCE__REFID:
            setRefid((String) newValue);
            return;
        case COREPackage.REFERENCE__HASH:
            setHash((String) newValue);
            return;
        case COREPackage.REFERENCE__REF_NAME:
            setRefName((String) newValue);
            return;
        case COREPackage.REFERENCE__REF_EXTENSION:
            setRefExtension((String) newValue);
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
        case COREPackage.REFERENCE__REFID:
            setRefid(REFID_EDEFAULT);
            return;
        case COREPackage.REFERENCE__HASH:
            setHash(HASH_EDEFAULT);
            return;
        case COREPackage.REFERENCE__REF_NAME:
            setRefName(REF_NAME_EDEFAULT);
            return;
        case COREPackage.REFERENCE__REF_EXTENSION:
            setRefExtension(REF_EXTENSION_EDEFAULT);
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
        case COREPackage.REFERENCE__REFID:
            return REFID_EDEFAULT == null ? refid != null : !REFID_EDEFAULT.equals(refid);
        case COREPackage.REFERENCE__HASH:
            return HASH_EDEFAULT == null ? hash != null : !HASH_EDEFAULT.equals(hash);
        case COREPackage.REFERENCE__REF_NAME:
            return REF_NAME_EDEFAULT == null ? refName != null : !REF_NAME_EDEFAULT.equals(refName);
        case COREPackage.REFERENCE__REF_EXTENSION:
            return REF_EXTENSION_EDEFAULT == null ? refExtension != null : !REF_EXTENSION_EDEFAULT.equals(refExtension);
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
        result.append(" (refid: ");
        result.append(refid);
        result.append(", hash: ");
        result.append(hash);
        result.append(", refName: ");
        result.append(refName);
        result.append(", refExtension: ");
        result.append(refExtension);
        result.append(')');
        return result.toString();
    }

} // ReferenceImpl
