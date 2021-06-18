/**
 */
package com.zipc.garden.model.spql.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.spql.SPQLPackage;
import com.zipc.garden.model.spql.SPQLSetting;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Setting</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.spql.impl.SPQLSettingImpl#getUuid <em>Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.spql.impl.SPQLSettingImpl#getQuery <em>Query</em>}</li>
 * <li>{@link com.zipc.garden.model.spql.impl.SPQLSettingImpl#getResult <em>Result</em>}</li>
 * </ul>
 * @generated
 */
public class SPQLSettingImpl extends MinimalEObjectImpl.Container implements SPQLSetting {
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
     * The default value of the '{@link #getQuery() <em>Query</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getQuery()
     * @generated
     * @ordered
     */
    protected static final String QUERY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getQuery() <em>Query</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getQuery()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String query = QUERY_EDEFAULT;

    /**
     * The default value of the '{@link #getResult() <em>Result</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getResult()
     * @generated
     * @ordered
     */
    protected static final String RESULT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getResult() <em>Result</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getResult()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String result = RESULT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected SPQLSettingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SPQLPackage.Literals.SPQL_SETTING;
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
            eNotify(new ENotificationImpl(this, Notification.SET, SPQLPackage.SPQL_SETTING__UUID, oldUuid, uuid));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getQuery() {
        return query;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setQuery(String newQuery) {
        String oldQuery = query;
        query = newQuery;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SPQLPackage.SPQL_SETTING__QUERY, oldQuery, query));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getResult() {
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setResult(String newResult) {
        String oldResult = result;
        result = newResult;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SPQLPackage.SPQL_SETTING__RESULT, oldResult, result));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case SPQLPackage.SPQL_SETTING__UUID:
            return getUuid();
        case SPQLPackage.SPQL_SETTING__QUERY:
            return getQuery();
        case SPQLPackage.SPQL_SETTING__RESULT:
            return getResult();
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
        case SPQLPackage.SPQL_SETTING__UUID:
            setUuid((String) newValue);
            return;
        case SPQLPackage.SPQL_SETTING__QUERY:
            setQuery((String) newValue);
            return;
        case SPQLPackage.SPQL_SETTING__RESULT:
            setResult((String) newValue);
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
        case SPQLPackage.SPQL_SETTING__UUID:
            setUuid(UUID_EDEFAULT);
            return;
        case SPQLPackage.SPQL_SETTING__QUERY:
            setQuery(QUERY_EDEFAULT);
            return;
        case SPQLPackage.SPQL_SETTING__RESULT:
            setResult(RESULT_EDEFAULT);
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
        case SPQLPackage.SPQL_SETTING__UUID:
            return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
        case SPQLPackage.SPQL_SETTING__QUERY:
            return QUERY_EDEFAULT == null ? query != null : !QUERY_EDEFAULT.equals(query);
        case SPQLPackage.SPQL_SETTING__RESULT:
            return RESULT_EDEFAULT == null ? result != null : !RESULT_EDEFAULT.equals(result);
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
        result.append(", query: ");
        result.append(query);
        result.append(", result: ");
        result.append(result);
        result.append(')');
        return result.toString();
    }

} // SPQLSettingImpl
