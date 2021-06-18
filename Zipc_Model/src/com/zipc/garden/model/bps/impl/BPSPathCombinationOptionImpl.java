/**
 */
package com.zipc.garden.model.bps.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.bps.BPSPackage;
import com.zipc.garden.model.bps.BPSPathCombinationOption;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Path Combination Option</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSPathCombinationOptionImpl#getPathLength <em>Path Length</em>}</li>
 * </ul>
 * @generated
 */
public class BPSPathCombinationOptionImpl extends BPSOptionImpl implements BPSPathCombinationOption {
    /**
     * The default value of the '{@link #getPathLength() <em>Path Length</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getPathLength()
     * @generated
     * @ordered
     */
    protected static final int PATH_LENGTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getPathLength() <em>Path Length</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getPathLength()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int pathLength = PATH_LENGTH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPSPathCombinationOptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPSPackage.Literals.BPS_PATH_COMBINATION_OPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getPathLength() {
        return pathLength;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setPathLength(int newPathLength) {
        int oldPathLength = pathLength;
        pathLength = newPathLength;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_PATH_COMBINATION_OPTION__PATH_LENGTH, oldPathLength, pathLength));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case BPSPackage.BPS_PATH_COMBINATION_OPTION__PATH_LENGTH:
            return getPathLength();
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
        case BPSPackage.BPS_PATH_COMBINATION_OPTION__PATH_LENGTH:
            setPathLength((Integer) newValue);
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
        case BPSPackage.BPS_PATH_COMBINATION_OPTION__PATH_LENGTH:
            setPathLength(PATH_LENGTH_EDEFAULT);
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
        case BPSPackage.BPS_PATH_COMBINATION_OPTION__PATH_LENGTH:
            return pathLength != PATH_LENGTH_EDEFAULT;
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
        result.append(" (pathLength: ");
        result.append(pathLength);
        result.append(')');
        return result.toString();
    }

} // BPSPathCombinationOptionImpl
