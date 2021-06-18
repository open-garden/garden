/**
 */
package com.zipc.garden.model.spql.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.impl.AbstractRootElementImpl;

import com.zipc.garden.model.spql.SPQLPackage;
import com.zipc.garden.model.spql.SPQLRoot;

import com.zipc.garden.model.spql.SPQLSetting;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.spql.impl.SPQLRootImpl#getUuid <em>Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.spql.impl.SPQLRootImpl#getSettings <em>Settings</em>}</li>
 * </ul>
 * @generated
 */
public class SPQLRootImpl extends AbstractRootElementImpl implements SPQLRoot {
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
     * The cached value of the '{@link #getSettings() <em>Settings</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSettings()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<SPQLSetting> settings;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected SPQLRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SPQLPackage.Literals.SPQL_ROOT;
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
            eNotify(new ENotificationImpl(this, Notification.SET, SPQLPackage.SPQL_ROOT__UUID, oldUuid, uuid));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<SPQLSetting> getSettings() {
        if (settings == null) {
            settings = new EObjectContainmentEList<SPQLSetting>(SPQLSetting.class, this, SPQLPackage.SPQL_ROOT__SETTINGS);
        }
        return settings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case SPQLPackage.SPQL_ROOT__SETTINGS:
            return ((InternalEList<?>) getSettings()).basicRemove(otherEnd, msgs);
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
        case SPQLPackage.SPQL_ROOT__UUID:
            return getUuid();
        case SPQLPackage.SPQL_ROOT__SETTINGS:
            return getSettings();
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
        case SPQLPackage.SPQL_ROOT__UUID:
            setUuid((String) newValue);
            return;
        case SPQLPackage.SPQL_ROOT__SETTINGS:
            getSettings().clear();
            getSettings().addAll((Collection<? extends SPQLSetting>) newValue);
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
        case SPQLPackage.SPQL_ROOT__UUID:
            setUuid(UUID_EDEFAULT);
            return;
        case SPQLPackage.SPQL_ROOT__SETTINGS:
            getSettings().clear();
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
        case SPQLPackage.SPQL_ROOT__UUID:
            return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
        case SPQLPackage.SPQL_ROOT__SETTINGS:
            return settings != null && !settings.isEmpty();
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
        result.append(')');
        return result.toString();
    }

} // SPQLRootImpl
