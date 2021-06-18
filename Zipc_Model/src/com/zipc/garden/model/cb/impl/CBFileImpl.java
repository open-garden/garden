/**
 */
package com.zipc.garden.model.cb.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.cb.CBFile;
import com.zipc.garden.model.cb.CBPackage;

import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.impl.AbstractNodeImpl;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>File</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.cb.impl.CBFileImpl#getUuid <em>Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.cb.impl.CBFileImpl#getPattern <em>Pattern</em>}</li>
 * <li>{@link com.zipc.garden.model.cb.impl.CBFileImpl#getAbstractRoot <em>Abstract Root</em>}</li>
 * <li>{@link com.zipc.garden.model.cb.impl.CBFileImpl#getHash <em>Hash</em>}</li>
 * </ul>
 * @generated
 */
public class CBFileImpl extends AbstractNodeImpl implements CBFile {
    /**
     * The default value of the '{@link #getUuid() <em>Uuid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getUuid()
     * @generated
     * @ordered
     */
    protected static final String UUID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUuid() <em>Uuid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getUuid()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String uuid = UUID_EDEFAULT;

    /**
     * The default value of the '{@link #getPattern() <em>Pattern</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getPattern()
     * @generated
     * @ordered
     */
    protected static final String PATTERN_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPattern() <em>Pattern</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getPattern()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String pattern = PATTERN_EDEFAULT;

    /**
     * The cached value of the '{@link #getAbstractRoot() <em>Abstract Root</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getAbstractRoot()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected AbstractRootElement abstractRoot;

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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected CBFileImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CBPackage.Literals.CB_FILE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getUuid() {
        return uuid;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setUuid(String newUuid) {
        String oldUuid = uuid;
        uuid = newUuid;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CBPackage.CB_FILE__UUID, oldUuid, uuid));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getPattern() {
        return pattern;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setPattern(String newPattern) {
        String oldPattern = pattern;
        pattern = newPattern;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CBPackage.CB_FILE__PATTERN, oldPattern, pattern));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AbstractRootElement getAbstractRoot() {
        return abstractRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAbstractRoot(AbstractRootElement newAbstractRoot, NotificationChain msgs) {
        AbstractRootElement oldAbstractRoot = abstractRoot;
        abstractRoot = newAbstractRoot;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CBPackage.CB_FILE__ABSTRACT_ROOT, oldAbstractRoot, newAbstractRoot);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAbstractRoot(AbstractRootElement newAbstractRoot) {
        if (newAbstractRoot != abstractRoot) {
            NotificationChain msgs = null;
            if (abstractRoot != null)
                msgs = ((InternalEObject) abstractRoot).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CBPackage.CB_FILE__ABSTRACT_ROOT, null, msgs);
            if (newAbstractRoot != null)
                msgs = ((InternalEObject) newAbstractRoot).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CBPackage.CB_FILE__ABSTRACT_ROOT, null, msgs);
            msgs = basicSetAbstractRoot(newAbstractRoot, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CBPackage.CB_FILE__ABSTRACT_ROOT, newAbstractRoot, newAbstractRoot));
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
            eNotify(new ENotificationImpl(this, Notification.SET, CBPackage.CB_FILE__HASH, oldHash, hash));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case CBPackage.CB_FILE__ABSTRACT_ROOT:
            return basicSetAbstractRoot(null, msgs);
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
        case CBPackage.CB_FILE__UUID:
            return getUuid();
        case CBPackage.CB_FILE__PATTERN:
            return getPattern();
        case CBPackage.CB_FILE__ABSTRACT_ROOT:
            return getAbstractRoot();
        case CBPackage.CB_FILE__HASH:
            return getHash();
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
        case CBPackage.CB_FILE__UUID:
            setUuid((String) newValue);
            return;
        case CBPackage.CB_FILE__PATTERN:
            setPattern((String) newValue);
            return;
        case CBPackage.CB_FILE__ABSTRACT_ROOT:
            setAbstractRoot((AbstractRootElement) newValue);
            return;
        case CBPackage.CB_FILE__HASH:
            setHash((String) newValue);
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
        case CBPackage.CB_FILE__UUID:
            setUuid(UUID_EDEFAULT);
            return;
        case CBPackage.CB_FILE__PATTERN:
            setPattern(PATTERN_EDEFAULT);
            return;
        case CBPackage.CB_FILE__ABSTRACT_ROOT:
            setAbstractRoot((AbstractRootElement) null);
            return;
        case CBPackage.CB_FILE__HASH:
            setHash(HASH_EDEFAULT);
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
        case CBPackage.CB_FILE__UUID:
            return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
        case CBPackage.CB_FILE__PATTERN:
            return PATTERN_EDEFAULT == null ? pattern != null : !PATTERN_EDEFAULT.equals(pattern);
        case CBPackage.CB_FILE__ABSTRACT_ROOT:
            return abstractRoot != null;
        case CBPackage.CB_FILE__HASH:
            return HASH_EDEFAULT == null ? hash != null : !HASH_EDEFAULT.equals(hash);
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
        result.append(" (uuid: ");
        result.append(uuid);
        result.append(", pattern: ");
        result.append(pattern);
        result.append(", hash: ");
        result.append(hash);
        result.append(')');
        return result.toString();
    }

} // CBFileImpl
