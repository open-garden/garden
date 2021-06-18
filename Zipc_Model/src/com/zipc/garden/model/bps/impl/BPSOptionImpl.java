/**
 */
package com.zipc.garden.model.bps.impl;

import com.google.gwt.user.client.rpc.GwtTransient;
import com.zipc.garden.model.bps.BPSOption;
import com.zipc.garden.model.bps.BPSPackage;
import java.util.Collection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Option</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSOptionImpl#getTargetStateMachines <em>Target State Machines</em>}</li>
 * </ul>
 * @generated
 */
public abstract class BPSOptionImpl extends MinimalEObjectImpl.Container implements BPSOption {
    /**
     * The cached value of the '{@link #getTargetStateMachines() <em>Target State Machines</em>}' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see #getTargetStateMachines()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<String> targetStateMachines;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPSOptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPSPackage.Literals.BPS_OPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<String> getTargetStateMachines() {
        if (targetStateMachines == null) {
            targetStateMachines = new EDataTypeUniqueEList<String>(String.class, this, BPSPackage.BPS_OPTION__TARGET_STATE_MACHINES);
        }
        return targetStateMachines;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case BPSPackage.BPS_OPTION__TARGET_STATE_MACHINES:
            return getTargetStateMachines();
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
        case BPSPackage.BPS_OPTION__TARGET_STATE_MACHINES:
            getTargetStateMachines().clear();
            getTargetStateMachines().addAll((Collection<? extends String>) newValue);
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
        case BPSPackage.BPS_OPTION__TARGET_STATE_MACHINES:
            getTargetStateMachines().clear();
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
        case BPSPackage.BPS_OPTION__TARGET_STATE_MACHINES:
            return targetStateMachines != null && !targetStateMachines.isEmpty();
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
        result.append(" (targetStateMachines: ");
        result.append(targetStateMachines);
        result.append(')');
        return result.toString();
    }

} // BPSOptionImpl
