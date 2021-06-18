/**
 */
package com.zipc.garden.model.bps.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.bps.BPSEnablement;
import com.zipc.garden.model.bps.BPSInstanceState;
import com.zipc.garden.model.bps.BPSInstanceTransition;
import com.zipc.garden.model.bps.BPSPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Instance Transition</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceTransitionImpl#isEnabled <em>Enabled</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceTransitionImpl#getPriority <em>Priority</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceTransitionImpl#getTrigger <em>Trigger</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceTransitionImpl#getEvent <em>Event</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceTransitionImpl#getCondition <em>Condition</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceTransitionImpl#getAction <em>Action</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceTransitionImpl#getSource <em>Source</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSInstanceTransitionImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * @generated
 */
public class BPSInstanceTransitionImpl extends MinimalEObjectImpl.Container implements BPSInstanceTransition {
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
     * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getPriority()
     * @generated
     * @ordered
     */
    protected static final int PRIORITY_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getPriority()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int priority = PRIORITY_EDEFAULT;

    /**
     * The default value of the '{@link #getTrigger() <em>Trigger</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getTrigger()
     * @generated
     * @ordered
     */
    protected static final String TRIGGER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTrigger()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String trigger = TRIGGER_EDEFAULT;

    /**
     * The default value of the '{@link #getEvent() <em>Event</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getEvent()
     * @generated
     * @ordered
     */
    protected static final String EVENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEvent() <em>Event</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getEvent()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String event = EVENT_EDEFAULT;

    /**
     * The default value of the '{@link #getCondition() <em>Condition</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCondition()
     * @generated
     * @ordered
     */
    protected static final String CONDITION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCondition() <em>Condition</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getCondition()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String condition = CONDITION_EDEFAULT;

    /**
     * The default value of the '{@link #getAction() <em>Action</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getAction()
     * @generated
     * @ordered
     */
    protected static final String ACTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAction() <em>Action</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getAction()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String action = ACTION_EDEFAULT;

    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected BPSInstanceState source;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected BPSInstanceState target;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPSInstanceTransitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPSPackage.Literals.BPS_INSTANCE_TRANSITION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_TRANSITION__ENABLED, oldEnabled, enabled));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getPriority() {
        return priority;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setPriority(int newPriority) {
        int oldPriority = priority;
        priority = newPriority;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_TRANSITION__PRIORITY, oldPriority, priority));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getTrigger() {
        return trigger;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTrigger(String newTrigger) {
        String oldTrigger = trigger;
        trigger = newTrigger;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_TRANSITION__TRIGGER, oldTrigger, trigger));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getEvent() {
        return event;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setEvent(String newEvent) {
        String oldEvent = event;
        event = newEvent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_TRANSITION__EVENT, oldEvent, event));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCondition() {
        return condition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCondition(String newCondition) {
        String oldCondition = condition;
        condition = newCondition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_TRANSITION__CONDITION, oldCondition, condition));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getAction() {
        return action;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAction(String newAction) {
        String oldAction = action;
        action = newAction;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_TRANSITION__ACTION, oldAction, action));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSInstanceState getSource() {
        if (source != null && source.eIsProxy()) {
            InternalEObject oldSource = (InternalEObject) source;
            source = (BPSInstanceState) eResolveProxy(oldSource);
            if (source != oldSource) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BPSPackage.BPS_INSTANCE_TRANSITION__SOURCE, oldSource, source));
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPSInstanceState basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSource(BPSInstanceState newSource) {
        BPSInstanceState oldSource = source;
        source = newSource;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_TRANSITION__SOURCE, oldSource, source));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSInstanceState getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = (BPSInstanceState) eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BPSPackage.BPS_INSTANCE_TRANSITION__TARGET, oldTarget, target));
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPSInstanceState basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTarget(BPSInstanceState newTarget) {
        BPSInstanceState oldTarget = target;
        target = newTarget;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_INSTANCE_TRANSITION__TARGET, oldTarget, target));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case BPSPackage.BPS_INSTANCE_TRANSITION__ENABLED:
            return isEnabled();
        case BPSPackage.BPS_INSTANCE_TRANSITION__PRIORITY:
            return getPriority();
        case BPSPackage.BPS_INSTANCE_TRANSITION__TRIGGER:
            return getTrigger();
        case BPSPackage.BPS_INSTANCE_TRANSITION__EVENT:
            return getEvent();
        case BPSPackage.BPS_INSTANCE_TRANSITION__CONDITION:
            return getCondition();
        case BPSPackage.BPS_INSTANCE_TRANSITION__ACTION:
            return getAction();
        case BPSPackage.BPS_INSTANCE_TRANSITION__SOURCE:
            if (resolve)
                return getSource();
            return basicGetSource();
        case BPSPackage.BPS_INSTANCE_TRANSITION__TARGET:
            if (resolve)
                return getTarget();
            return basicGetTarget();
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
        case BPSPackage.BPS_INSTANCE_TRANSITION__ENABLED:
            setEnabled((Boolean) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_TRANSITION__PRIORITY:
            setPriority((Integer) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_TRANSITION__TRIGGER:
            setTrigger((String) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_TRANSITION__EVENT:
            setEvent((String) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_TRANSITION__CONDITION:
            setCondition((String) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_TRANSITION__ACTION:
            setAction((String) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_TRANSITION__SOURCE:
            setSource((BPSInstanceState) newValue);
            return;
        case BPSPackage.BPS_INSTANCE_TRANSITION__TARGET:
            setTarget((BPSInstanceState) newValue);
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
        case BPSPackage.BPS_INSTANCE_TRANSITION__ENABLED:
            setEnabled(ENABLED_EDEFAULT);
            return;
        case BPSPackage.BPS_INSTANCE_TRANSITION__PRIORITY:
            setPriority(PRIORITY_EDEFAULT);
            return;
        case BPSPackage.BPS_INSTANCE_TRANSITION__TRIGGER:
            setTrigger(TRIGGER_EDEFAULT);
            return;
        case BPSPackage.BPS_INSTANCE_TRANSITION__EVENT:
            setEvent(EVENT_EDEFAULT);
            return;
        case BPSPackage.BPS_INSTANCE_TRANSITION__CONDITION:
            setCondition(CONDITION_EDEFAULT);
            return;
        case BPSPackage.BPS_INSTANCE_TRANSITION__ACTION:
            setAction(ACTION_EDEFAULT);
            return;
        case BPSPackage.BPS_INSTANCE_TRANSITION__SOURCE:
            setSource((BPSInstanceState) null);
            return;
        case BPSPackage.BPS_INSTANCE_TRANSITION__TARGET:
            setTarget((BPSInstanceState) null);
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
        case BPSPackage.BPS_INSTANCE_TRANSITION__ENABLED:
            return enabled != ENABLED_EDEFAULT;
        case BPSPackage.BPS_INSTANCE_TRANSITION__PRIORITY:
            return priority != PRIORITY_EDEFAULT;
        case BPSPackage.BPS_INSTANCE_TRANSITION__TRIGGER:
            return TRIGGER_EDEFAULT == null ? trigger != null : !TRIGGER_EDEFAULT.equals(trigger);
        case BPSPackage.BPS_INSTANCE_TRANSITION__EVENT:
            return EVENT_EDEFAULT == null ? event != null : !EVENT_EDEFAULT.equals(event);
        case BPSPackage.BPS_INSTANCE_TRANSITION__CONDITION:
            return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
        case BPSPackage.BPS_INSTANCE_TRANSITION__ACTION:
            return ACTION_EDEFAULT == null ? action != null : !ACTION_EDEFAULT.equals(action);
        case BPSPackage.BPS_INSTANCE_TRANSITION__SOURCE:
            return source != null;
        case BPSPackage.BPS_INSTANCE_TRANSITION__TARGET:
            return target != null;
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
            case BPSPackage.BPS_INSTANCE_TRANSITION__ENABLED:
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
                return BPSPackage.BPS_INSTANCE_TRANSITION__ENABLED;
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
        result.append(", priority: ");
        result.append(priority);
        result.append(", trigger: ");
        result.append(trigger);
        result.append(", event: ");
        result.append(event);
        result.append(", condition: ");
        result.append(condition);
        result.append(", action: ");
        result.append(action);
        result.append(')');
        return result.toString();
    }

} // BPSInstanceTransitionImpl
