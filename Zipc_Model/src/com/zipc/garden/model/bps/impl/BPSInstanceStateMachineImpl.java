/**
 */
package com.zipc.garden.model.bps.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.bps.BPSEnablement;
import com.zipc.garden.model.bps.BPSInstanceState;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;
import com.zipc.garden.model.bps.BPSPackage;
import com.zipc.garden.model.bps.BPSVariable;

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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Instance State Machine</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceStateMachineImpl#isEnabled <em>Enabled</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceStateMachineImpl#getOriginalName <em>Original Name</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceStateMachineImpl#getOriginalUuid <em>Original Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceStateMachineImpl#getEvalPriority <em>Eval Priority</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceStateMachineImpl#getInitialState <em>Initial State</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceStateMachineImpl#getStates <em>States</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceStateMachineImpl#getVariables <em>Variables</em>}</li>
 * </ul>
 * @generated
 */
public class BPSInstanceStateMachineImpl extends MinimalEObjectImpl.Container implements BPSInstanceStateMachine {
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
     * The default value of the '{@link #getOriginalUuid() <em>Original Uuid</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getOriginalUuid()
     * @generated
     * @ordered
     */
    protected static final String ORIGINAL_UUID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOriginalUuid() <em>Original Uuid</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getOriginalUuid()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String originalUuid = ORIGINAL_UUID_EDEFAULT;

    /**
     * The default value of the '{@link #getEvalPriority() <em>Eval Priority</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getEvalPriority()
     * @generated
     * @ordered
     */
    protected static final int EVAL_PRIORITY_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getEvalPriority() <em>Eval Priority</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getEvalPriority()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int evalPriority = EVAL_PRIORITY_EDEFAULT;

    /**
     * The cached value of the '{@link #getInitialState() <em>Initial State</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getInitialState()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected BPSInstanceState initialState;

    /**
     * The cached value of the '{@link #getStates() <em>States</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getStates()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<BPSInstanceState> states;

    /**
     * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariables()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<BPSVariable> variables;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPSInstanceStateMachineImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPSPackage.Literals.BPS_INSTANCE_STATE_MACHINE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_STATE_MACHINE__ENABLED, oldEnabled, enabled));
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
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_STATE_MACHINE__ORIGINAL_NAME, oldOriginalName, originalName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getOriginalUuid() {
        return originalUuid;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setOriginalUuid(String newOriginalUuid) {
        String oldOriginalUuid = originalUuid;
        originalUuid = newOriginalUuid;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_STATE_MACHINE__ORIGINAL_UUID, oldOriginalUuid, originalUuid));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getEvalPriority() {
        return evalPriority;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setEvalPriority(int newEvalPriority) {
        int oldEvalPriority = evalPriority;
        evalPriority = newEvalPriority;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_STATE_MACHINE__EVAL_PRIORITY, oldEvalPriority, evalPriority));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSInstanceState getInitialState() {
        if (initialState != null && initialState.eIsProxy()) {
            InternalEObject oldInitialState = (InternalEObject) initialState;
            initialState = (BPSInstanceState) eResolveProxy(oldInitialState);
            if (initialState != oldInitialState) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BPSPackage.BPS_INSTANCE_STATE_MACHINE__INITIAL_STATE, oldInitialState, initialState));
            }
        }
        return initialState;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPSInstanceState basicGetInitialState() {
        return initialState;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setInitialState(BPSInstanceState newInitialState) {
        BPSInstanceState oldInitialState = initialState;
        initialState = newInitialState;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_STATE_MACHINE__INITIAL_STATE, oldInitialState, initialState));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<BPSInstanceState> getStates() {
        if (states == null) {
            states = new EObjectContainmentEList<BPSInstanceState>(BPSInstanceState.class, this, BPSPackage.BPS_INSTANCE_STATE_MACHINE__STATES);
        }
        return states;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<BPSVariable> getVariables() {
        if (variables == null) {
            variables = new EObjectContainmentEList<BPSVariable>(BPSVariable.class, this, BPSPackage.BPS_INSTANCE_STATE_MACHINE__VARIABLES);
        }
        return variables;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__STATES:
            return ((InternalEList<?>) getStates()).basicRemove(otherEnd, msgs);
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__VARIABLES:
            return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
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
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__ENABLED:
            return isEnabled();
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__ORIGINAL_NAME:
            return getOriginalName();
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__ORIGINAL_UUID:
            return getOriginalUuid();
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__EVAL_PRIORITY:
            return getEvalPriority();
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__INITIAL_STATE:
            if (resolve)
                return getInitialState();
            return basicGetInitialState();
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__STATES:
            return getStates();
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__VARIABLES:
            return getVariables();
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
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__ENABLED:
            setEnabled((Boolean) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__ORIGINAL_NAME:
            setOriginalName((String) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__ORIGINAL_UUID:
            setOriginalUuid((String) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__EVAL_PRIORITY:
            setEvalPriority((Integer) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__INITIAL_STATE:
            setInitialState((BPSInstanceState) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__STATES:
            getStates().clear();
            getStates().addAll((Collection<? extends BPSInstanceState>) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__VARIABLES:
            getVariables().clear();
            getVariables().addAll((Collection<? extends BPSVariable>) newValue);
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
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__ENABLED:
            setEnabled(ENABLED_EDEFAULT);
            return;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__ORIGINAL_NAME:
            setOriginalName(ORIGINAL_NAME_EDEFAULT);
            return;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__ORIGINAL_UUID:
            setOriginalUuid(ORIGINAL_UUID_EDEFAULT);
            return;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__EVAL_PRIORITY:
            setEvalPriority(EVAL_PRIORITY_EDEFAULT);
            return;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__INITIAL_STATE:
            setInitialState((BPSInstanceState) null);
            return;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__STATES:
            getStates().clear();
            return;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__VARIABLES:
            getVariables().clear();
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
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__ENABLED:
            return enabled != ENABLED_EDEFAULT;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__ORIGINAL_NAME:
            return ORIGINAL_NAME_EDEFAULT == null ? originalName != null : !ORIGINAL_NAME_EDEFAULT.equals(originalName);
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__ORIGINAL_UUID:
            return ORIGINAL_UUID_EDEFAULT == null ? originalUuid != null : !ORIGINAL_UUID_EDEFAULT.equals(originalUuid);
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__EVAL_PRIORITY:
            return evalPriority != EVAL_PRIORITY_EDEFAULT;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__INITIAL_STATE:
            return initialState != null;
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__STATES:
            return states != null && !states.isEmpty();
        case BPSPackage.BPS_INSTANCE_STATE_MACHINE__VARIABLES:
            return variables != null && !variables.isEmpty();
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
            case BPSPackage.BPS_INSTANCE_STATE_MACHINE__ENABLED:
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
                return BPSPackage.BPS_INSTANCE_STATE_MACHINE__ENABLED;
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
        result.append(", originalUuid: ");
        result.append(originalUuid);
        result.append(", evalPriority: ");
        result.append(evalPriority);
        result.append(')');
        return result.toString();
    }

} // BPSInstanceStateMachineImpl
