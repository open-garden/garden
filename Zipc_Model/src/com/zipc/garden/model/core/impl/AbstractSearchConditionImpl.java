/**
 */
package com.zipc.garden.model.core.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.AbstractSearchCondition;
import com.zipc.garden.model.core.COREPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Search Condition</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractSearchConditionImpl#getMax <em>Max</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractSearchConditionImpl#getRowIds <em>Row Ids</em>}</li>
 * </ul>
 * @generated
 */
public abstract class AbstractSearchConditionImpl extends MinimalEObjectImpl.Container implements AbstractSearchCondition {
    /**
     * The default value of the '{@link #getMax() <em>Max</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMax()
     * @generated
     * @ordered
     */
    protected static final int MAX_EDEFAULT = 100;

    /**
     * The cached value of the '{@link #getMax() <em>Max</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMax()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int max = MAX_EDEFAULT;

    /**
     * The default value of the '{@link #getRowIds() <em>Row Ids</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRowIds()
     * @generated
     * @ordered
     */
    protected static final String ROW_IDS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRowIds() <em>Row Ids</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRowIds()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String rowIds = ROW_IDS_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected AbstractSearchConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return COREPackage.Literals.ABSTRACT_SEARCH_CONDITION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getMax() {
        return max;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMax(int newMax) {
        int oldMax = max;
        max = newMax;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_SEARCH_CONDITION__MAX, oldMax, max));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getRowIds() {
        return rowIds;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRowIds(String newRowIds) {
        String oldRowIds = rowIds;
        rowIds = newRowIds;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_SEARCH_CONDITION__ROW_IDS, oldRowIds, rowIds));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case COREPackage.ABSTRACT_SEARCH_CONDITION__MAX:
            return getMax();
        case COREPackage.ABSTRACT_SEARCH_CONDITION__ROW_IDS:
            return getRowIds();
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
        case COREPackage.ABSTRACT_SEARCH_CONDITION__MAX:
            setMax((Integer) newValue);
            return;
        case COREPackage.ABSTRACT_SEARCH_CONDITION__ROW_IDS:
            setRowIds((String) newValue);
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
        case COREPackage.ABSTRACT_SEARCH_CONDITION__MAX:
            setMax(MAX_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_SEARCH_CONDITION__ROW_IDS:
            setRowIds(ROW_IDS_EDEFAULT);
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
        case COREPackage.ABSTRACT_SEARCH_CONDITION__MAX:
            return max != MAX_EDEFAULT;
        case COREPackage.ABSTRACT_SEARCH_CONDITION__ROW_IDS:
            return ROW_IDS_EDEFAULT == null ? rowIds != null : !ROW_IDS_EDEFAULT.equals(rowIds);
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
        result.append(" (max: ");
        result.append(max);
        result.append(", rowIds: ");
        result.append(rowIds);
        result.append(')');
        return result.toString();
    }

} // AbstractSearchConditionImpl
