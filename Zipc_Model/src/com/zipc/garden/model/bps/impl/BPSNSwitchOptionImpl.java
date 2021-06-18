/**
 */
package com.zipc.garden.model.bps.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.bps.BPSNSwitchOption;
import com.zipc.garden.model.bps.BPSPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>NSwitch Option</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSNSwitchOptionImpl#getTSMFileId <em>TSM File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSNSwitchOptionImpl#getNSwitch <em>NSwitch</em>}</li>
 * </ul>
 * @generated
 */
public class BPSNSwitchOptionImpl extends BPSOptionImpl implements BPSNSwitchOption {
    /**
     * The default value of the '{@link #getTSMFileId() <em>TSM File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTSMFileId()
     * @generated
     * @ordered
     */
    protected static final String TSM_FILE_ID_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getTSMFileId() <em>TSM File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTSMFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String tsmFileId = TSM_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getNSwitch() <em>NSwitch</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getNSwitch()
     * @generated
     * @ordered
     */
    protected static final int NSWITCH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getNSwitch() <em>NSwitch</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getNSwitch()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int nSwitch = NSWITCH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPSNSwitchOptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPSPackage.Literals.BPS_NSWITCH_OPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getTSMFileId() {
        return tsmFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTSMFileId(String newTSMFileId) {
        String oldTSMFileId = tsmFileId;
        tsmFileId = newTSMFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_NSWITCH_OPTION__TSM_FILE_ID, oldTSMFileId, tsmFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getNSwitch() {
        return nSwitch;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setNSwitch(int newNSwitch) {
        int oldNSwitch = nSwitch;
        nSwitch = newNSwitch;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_NSWITCH_OPTION__NSWITCH, oldNSwitch, nSwitch));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case BPSPackage.BPS_NSWITCH_OPTION__TSM_FILE_ID:
            return getTSMFileId();
        case BPSPackage.BPS_NSWITCH_OPTION__NSWITCH:
            return getNSwitch();
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
        case BPSPackage.BPS_NSWITCH_OPTION__TSM_FILE_ID:
            setTSMFileId((String) newValue);
            return;
        case BPSPackage.BPS_NSWITCH_OPTION__NSWITCH:
            setNSwitch((Integer) newValue);
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
        case BPSPackage.BPS_NSWITCH_OPTION__TSM_FILE_ID:
            setTSMFileId(TSM_FILE_ID_EDEFAULT);
            return;
        case BPSPackage.BPS_NSWITCH_OPTION__NSWITCH:
            setNSwitch(NSWITCH_EDEFAULT);
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
        case BPSPackage.BPS_NSWITCH_OPTION__TSM_FILE_ID:
            return TSM_FILE_ID_EDEFAULT == null ? tsmFileId != null : !TSM_FILE_ID_EDEFAULT.equals(tsmFileId);
        case BPSPackage.BPS_NSWITCH_OPTION__NSWITCH:
            return nSwitch != NSWITCH_EDEFAULT;
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
        result.append(" (TSMFileId: ");
        result.append(tsmFileId);
        result.append(", nSwitch: ");
        result.append(nSwitch);
        result.append(')');
        return result.toString();
    }

} // BPSNSwitchOptionImpl
