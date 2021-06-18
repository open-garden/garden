/**
 */
package com.zipc.garden.model.lsc.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.lsc.LSCPackage;
import com.zipc.garden.model.lsc.LSCScenario;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Scenario</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.lsc.impl.LSCScenarioImpl#getTpId <em>Tp Id</em>}</li>
 * <li>{@link com.zipc.garden.model.lsc.impl.LSCScenarioImpl#getBpId <em>Bp Id</em>}</li>
 * </ul>
 * @generated
 */
public class LSCScenarioImpl extends MinimalEObjectImpl.Container implements LSCScenario {
    /**
     * The default value of the '{@link #getTpId() <em>Tp Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTpId()
     * @generated
     * @ordered
     */
    protected static final String TP_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTpId() <em>Tp Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTpId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String tpId = TP_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getBpId() <em>Bp Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getBpId()
     * @generated
     * @ordered
     */
    protected static final String BP_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBpId() <em>Bp Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getBpId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String bpId = BP_ID_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected LSCScenarioImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return LSCPackage.Literals.LSC_SCENARIO;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getTpId() {
        return tpId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTpId(String newTpId) {
        String oldTpId = tpId;
        tpId = newTpId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LSCPackage.LSC_SCENARIO__TP_ID, oldTpId, tpId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getBpId() {
        return bpId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBpId(String newBpId) {
        String oldBpId = bpId;
        bpId = newBpId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, LSCPackage.LSC_SCENARIO__BP_ID, oldBpId, bpId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case LSCPackage.LSC_SCENARIO__TP_ID:
            return getTpId();
        case LSCPackage.LSC_SCENARIO__BP_ID:
            return getBpId();
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
        case LSCPackage.LSC_SCENARIO__TP_ID:
            setTpId((String) newValue);
            return;
        case LSCPackage.LSC_SCENARIO__BP_ID:
            setBpId((String) newValue);
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
        case LSCPackage.LSC_SCENARIO__TP_ID:
            setTpId(TP_ID_EDEFAULT);
            return;
        case LSCPackage.LSC_SCENARIO__BP_ID:
            setBpId(BP_ID_EDEFAULT);
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
        case LSCPackage.LSC_SCENARIO__TP_ID:
            return TP_ID_EDEFAULT == null ? tpId != null : !TP_ID_EDEFAULT.equals(tpId);
        case LSCPackage.LSC_SCENARIO__BP_ID:
            return BP_ID_EDEFAULT == null ? bpId != null : !BP_ID_EDEFAULT.equals(bpId);
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
        result.append(" (tpId: ");
        result.append(tpId);
        result.append(", bpId: ");
        result.append(bpId);
        result.append(')');
        return result.toString();
    }

} // LSCScenarioImpl
