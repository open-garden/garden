/**
 */
package com.zipc.garden.model.bps.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.bps.BPSEnablement;
import com.zipc.garden.model.bps.BPSInstancePseudoStateType;
import com.zipc.garden.model.bps.BPSInstanceState;
import com.zipc.garden.model.bps.BPSInstanceTransition;
import com.zipc.garden.model.bps.BPSPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Instance State</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceStateImpl#isEnabled <em>Enabled</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceStateImpl#getOriginalName <em>Original Name</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceStateImpl#getType <em>Type</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceStateImpl#getTransitions <em>Transitions</em>}</li>
 * </ul>
 * @generated
 */
public class BPSInstanceStateImpl extends MinimalEObjectImpl.Container implements BPSInstanceState {
    /**
     * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isEnabled()
     * @generated
     * @ordered
     */
    protected static final boolean ENABLED_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isEnabled()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected boolean enabled = ENABLED_EDEFAULT;

    /**
     * The default value of the '{@link #getOriginalName() <em>Original Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getOriginalName()
     * @generated
     * @ordered
     */
    protected static final String ORIGINAL_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOriginalName() <em>Original Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getOriginalName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String originalName = ORIGINAL_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final BPSInstancePseudoStateType TYPE_EDEFAULT = BPSInstancePseudoStateType.NONE;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected BPSInstancePseudoStateType type = TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getTransitions() <em>Transitions</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getTransitions()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<BPSInstanceTransition> transitions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPSInstanceStateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPSPackage.Literals.BPS_INSTANCE_STATE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setEnabled(boolean newEnabled) {
        boolean oldEnabled = enabled;
        enabled = newEnabled;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_STATE__ENABLED, oldEnabled, enabled));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getOriginalName() {
        return originalName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setOriginalName(String newOriginalName) {
        String oldOriginalName = originalName;
        originalName = newOriginalName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_STATE__ORIGINAL_NAME, oldOriginalName, originalName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSInstancePseudoStateType getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setType(BPSInstancePseudoStateType newType) {
        BPSInstancePseudoStateType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_STATE__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<BPSInstanceTransition> getTransitions() {
        if (transitions == null) {
            transitions = new EObjectContainmentEList<BPSInstanceTransition>(BPSInstanceTransition.class, this, BPSPackage.BPS_INSTANCE_STATE__TRANSITIONS);
        }
        return transitions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BPSPackage.BPS_INSTANCE_STATE__TRANSITIONS:
            return ((InternalEList<?>) getTransitions()).basicRemove(otherEnd, msgs);
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
        case BPSPackage.BPS_INSTANCE_STATE__ENABLED:
            return isEnabled();
        case BPSPackage.BPS_INSTANCE_STATE__ORIGINAL_NAME:
            return getOriginalName();
        case BPSPackage.BPS_INSTANCE_STATE__TYPE:
            return getType();
        case BPSPackage.BPS_INSTANCE_STATE__TRANSITIONS:
            return getTransitions();
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
        case BPSPackage.BPS_INSTANCE_STATE__ENABLED:
            setEnabled((Boolean) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_STATE__ORIGINAL_NAME:
            setOriginalName((String) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_STATE__TYPE:
            setType((BPSInstancePseudoStateType) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_STATE__TRANSITIONS:
            getTransitions().clear();
            getTransitions().addAll((Collection<? extends BPSInstanceTransition>) newValue);
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
        case BPSPackage.BPS_INSTANCE_STATE__ENABLED:
            setEnabled(ENABLED_EDEFAULT);
            return;
        case BPSPackage.BPS_INSTANCE_STATE__ORIGINAL_NAME:
            setOriginalName(ORIGINAL_NAME_EDEFAULT);
            return;
        case BPSPackage.BPS_INSTANCE_STATE__TYPE:
            setType(TYPE_EDEFAULT);
            return;
        case BPSPackage.BPS_INSTANCE_STATE__TRANSITIONS:
            getTransitions().clear();
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
        case BPSPackage.BPS_INSTANCE_STATE__ENABLED:
            return enabled != ENABLED_EDEFAULT;
        case BPSPackage.BPS_INSTANCE_STATE__ORIGINAL_NAME:
            return ORIGINAL_NAME_EDEFAULT == null ? originalName != null : !ORIGINAL_NAME_EDEFAULT.equals(originalName);
        case BPSPackage.BPS_INSTANCE_STATE__TYPE:
            return type != TYPE_EDEFAULT;
        case BPSPackage.BPS_INSTANCE_STATE__TRANSITIONS:
            return transitions != null && !transitions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == BPSEnablement.class) {
            switch (derivedFeatureID) {
            case BPSPackage.BPS_INSTANCE_STATE__ENABLED:
                return BPSPackage.BPS_ENABLEMENT__ENABLED;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == BPSEnablement.class) {
            switch (baseFeatureID) {
            case BPSPackage.BPS_ENABLEMENT__ENABLED:
                return BPSPackage.BPS_INSTANCE_STATE__ENABLED;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (enabled: ");
        result.append(enabled);
        result.append(", originalName: ");
        result.append(originalName);
        result.append(", type: ");
        result.append(type);
        result.append(')');
        return result.toString();
    }

} // BPSInstanceStateImpl
