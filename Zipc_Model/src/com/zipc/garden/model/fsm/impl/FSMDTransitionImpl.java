/**
 */
package com.zipc.garden.model.fsm.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.AbstractLine;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.core.LineType;
import com.zipc.garden.model.fsm.FSMDAbstractLine;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMDTransition;
import com.zipc.garden.model.fsm.FSMPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>DTransition</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getSourceAnchorX <em>Source Anchor X</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getSourceAnchorY <em>Source Anchor Y</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getTargetAnchorX <em>Target Anchor X</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getTargetAnchorY <em>Target Anchor Y</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getType <em>Type</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getConnectionPoint <em>Connection Point</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getTrigger <em>Trigger</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getEvent <em>Event</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getCondition <em>Condition</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getAction <em>Action</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getX <em>X</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getY <em>Y</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getTarget <em>Target</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getSource <em>Source</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl#getPriority <em>Priority</em>}</li>
 * </ul>
 * @generated
 */
public class FSMDTransitionImpl extends FSMDReferenceableImpl implements FSMDTransition {
    /**
     * The default value of the '{@link #getSourceAnchorX() <em>Source Anchor X</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSourceAnchorX()
     * @generated
     * @ordered
     */
    protected static final double SOURCE_ANCHOR_X_EDEFAULT = 0.5;

    /**
     * The cached value of the '{@link #getSourceAnchorX() <em>Source Anchor X</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSourceAnchorX()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected double sourceAnchorX = SOURCE_ANCHOR_X_EDEFAULT;

    /**
     * The default value of the '{@link #getSourceAnchorY() <em>Source Anchor Y</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSourceAnchorY()
     * @generated
     * @ordered
     */
    protected static final double SOURCE_ANCHOR_Y_EDEFAULT = 0.5;

    /**
     * The cached value of the '{@link #getSourceAnchorY() <em>Source Anchor Y</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSourceAnchorY()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected double sourceAnchorY = SOURCE_ANCHOR_Y_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetAnchorX() <em>Target Anchor X</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTargetAnchorX()
     * @generated
     * @ordered
     */
    protected static final double TARGET_ANCHOR_X_EDEFAULT = 0.5;

    /**
     * The cached value of the '{@link #getTargetAnchorX() <em>Target Anchor X</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTargetAnchorX()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected double targetAnchorX = TARGET_ANCHOR_X_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetAnchorY() <em>Target Anchor Y</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTargetAnchorY()
     * @generated
     * @ordered
     */
    protected static final double TARGET_ANCHOR_Y_EDEFAULT = 0.5;

    /**
     * The cached value of the '{@link #getTargetAnchorY() <em>Target Anchor Y</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTargetAnchorY()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected double targetAnchorY = TARGET_ANCHOR_Y_EDEFAULT;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final LineType TYPE_EDEFAULT = LineType.SIMPLE;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected LineType type = TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getConnectionPoint() <em>Connection Point</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see #getConnectionPoint()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<AbstractPoint> connectionPoint;

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
     * The default value of the '{@link #getX() <em>X</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getX()
     * @generated
     * @ordered
     */
    protected static final int X_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getX() <em>X</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getX()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int x = X_EDEFAULT;

    /**
     * The default value of the '{@link #getY() <em>Y</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getY()
     * @generated
     * @ordered
     */
    protected static final int Y_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getY() <em>Y</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getY()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int y = Y_EDEFAULT;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected FSMDState target;

    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected FSMDState source;

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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FSMDTransitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FSMPackage.Literals.FSM_DTRANSITION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public double getSourceAnchorX() {
        return sourceAnchorX;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSourceAnchorX(double newSourceAnchorX) {
        double oldSourceAnchorX = sourceAnchorX;
        sourceAnchorX = newSourceAnchorX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__SOURCE_ANCHOR_X, oldSourceAnchorX, sourceAnchorX));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public double getSourceAnchorY() {
        return sourceAnchorY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSourceAnchorY(double newSourceAnchorY) {
        double oldSourceAnchorY = sourceAnchorY;
        sourceAnchorY = newSourceAnchorY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__SOURCE_ANCHOR_Y, oldSourceAnchorY, sourceAnchorY));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public double getTargetAnchorX() {
        return targetAnchorX;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTargetAnchorX(double newTargetAnchorX) {
        double oldTargetAnchorX = targetAnchorX;
        targetAnchorX = newTargetAnchorX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__TARGET_ANCHOR_X, oldTargetAnchorX, targetAnchorX));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public double getTargetAnchorY() {
        return targetAnchorY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTargetAnchorY(double newTargetAnchorY) {
        double oldTargetAnchorY = targetAnchorY;
        targetAnchorY = newTargetAnchorY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__TARGET_ANCHOR_Y, oldTargetAnchorY, targetAnchorY));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<AbstractPoint> getConnectionPoint() {
        if (connectionPoint == null) {
            connectionPoint = new EObjectResolvingEList<AbstractPoint>(AbstractPoint.class, this, FSMPackage.FSM_DTRANSITION__CONNECTION_POINT);
        }
        return connectionPoint;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__TRIGGER, oldTrigger, trigger));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public LineType getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setType(LineType newType) {
        LineType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__TYPE, oldType, type));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__EVENT, oldEvent, event));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__CONDITION, oldCondition, condition));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__ACTION, oldAction, action));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setX(int newX) {
        int oldX = x;
        x = newX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__X, oldX, x));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setY(int newY) {
        int oldY = y;
        y = newY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__Y, oldY, y));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDState getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = (FSMDState) eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FSMPackage.FSM_DTRANSITION__TARGET, oldTarget, target));
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FSMDState basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTarget(FSMDState newTarget, NotificationChain msgs) {
        FSMDState oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__TARGET, oldTarget, newTarget);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTarget(FSMDState newTarget) {
        if (newTarget != target) {
            NotificationChain msgs = null;
            if (target != null)
                msgs = ((InternalEObject) target).eInverseRemove(this, FSMPackage.FSM_DSTATE__INCOMING_TRANSITION, FSMDState.class, msgs);
            if (newTarget != null)
                msgs = ((InternalEObject) newTarget).eInverseAdd(this, FSMPackage.FSM_DSTATE__INCOMING_TRANSITION, FSMDState.class, msgs);
            msgs = basicSetTarget(newTarget, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__TARGET, newTarget, newTarget));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDState getSource() {
        if (source != null && source.eIsProxy()) {
            InternalEObject oldSource = (InternalEObject) source;
            source = (FSMDState) eResolveProxy(oldSource);
            if (source != oldSource) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FSMPackage.FSM_DTRANSITION__SOURCE, oldSource, source));
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FSMDState basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSource(FSMDState newSource, NotificationChain msgs) {
        FSMDState oldSource = source;
        source = newSource;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__SOURCE, oldSource, newSource);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSource(FSMDState newSource) {
        if (newSource != source) {
            NotificationChain msgs = null;
            if (source != null)
                msgs = ((InternalEObject) source).eInverseRemove(this, FSMPackage.FSM_DSTATE__OUTGOING_TRANSITION, FSMDState.class, msgs);
            if (newSource != null)
                msgs = ((InternalEObject) newSource).eInverseAdd(this, FSMPackage.FSM_DSTATE__OUTGOING_TRANSITION, FSMDState.class, msgs);
            msgs = basicSetSource(newSource, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__SOURCE, newSource, newSource));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DTRANSITION__PRIORITY, oldPriority, priority));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FSMPackage.FSM_DTRANSITION__TARGET:
            if (target != null)
                msgs = ((InternalEObject) target).eInverseRemove(this, FSMPackage.FSM_DSTATE__INCOMING_TRANSITION, FSMDState.class, msgs);
            return basicSetTarget((FSMDState) otherEnd, msgs);
        case FSMPackage.FSM_DTRANSITION__SOURCE:
            if (source != null)
                msgs = ((InternalEObject) source).eInverseRemove(this, FSMPackage.FSM_DSTATE__OUTGOING_TRANSITION, FSMDState.class, msgs);
            return basicSetSource((FSMDState) otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FSMPackage.FSM_DTRANSITION__TARGET:
            return basicSetTarget(null, msgs);
        case FSMPackage.FSM_DTRANSITION__SOURCE:
            return basicSetSource(null, msgs);
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
        case FSMPackage.FSM_DTRANSITION__SOURCE_ANCHOR_X:
            return getSourceAnchorX();
        case FSMPackage.FSM_DTRANSITION__SOURCE_ANCHOR_Y:
            return getSourceAnchorY();
        case FSMPackage.FSM_DTRANSITION__TARGET_ANCHOR_X:
            return getTargetAnchorX();
        case FSMPackage.FSM_DTRANSITION__TARGET_ANCHOR_Y:
            return getTargetAnchorY();
        case FSMPackage.FSM_DTRANSITION__TYPE:
            return getType();
        case FSMPackage.FSM_DTRANSITION__CONNECTION_POINT:
            return getConnectionPoint();
        case FSMPackage.FSM_DTRANSITION__TRIGGER:
            return getTrigger();
        case FSMPackage.FSM_DTRANSITION__EVENT:
            return getEvent();
        case FSMPackage.FSM_DTRANSITION__CONDITION:
            return getCondition();
        case FSMPackage.FSM_DTRANSITION__ACTION:
            return getAction();
        case FSMPackage.FSM_DTRANSITION__X:
            return getX();
        case FSMPackage.FSM_DTRANSITION__Y:
            return getY();
        case FSMPackage.FSM_DTRANSITION__TARGET:
            if (resolve)
                return getTarget();
            return basicGetTarget();
        case FSMPackage.FSM_DTRANSITION__SOURCE:
            if (resolve)
                return getSource();
            return basicGetSource();
        case FSMPackage.FSM_DTRANSITION__PRIORITY:
            return getPriority();
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
        case FSMPackage.FSM_DTRANSITION__SOURCE_ANCHOR_X:
            setSourceAnchorX((Double) newValue);
            return;
        case FSMPackage.FSM_DTRANSITION__SOURCE_ANCHOR_Y:
            setSourceAnchorY((Double) newValue);
            return;
        case FSMPackage.FSM_DTRANSITION__TARGET_ANCHOR_X:
            setTargetAnchorX((Double) newValue);
            return;
        case FSMPackage.FSM_DTRANSITION__TARGET_ANCHOR_Y:
            setTargetAnchorY((Double) newValue);
            return;
        case FSMPackage.FSM_DTRANSITION__TYPE:
            setType((LineType) newValue);
            return;
        case FSMPackage.FSM_DTRANSITION__CONNECTION_POINT:
            getConnectionPoint().clear();
            getConnectionPoint().addAll((Collection<? extends AbstractPoint>) newValue);
            return;
        case FSMPackage.FSM_DTRANSITION__TRIGGER:
            setTrigger((String) newValue);
            return;
        case FSMPackage.FSM_DTRANSITION__EVENT:
            setEvent((String) newValue);
            return;
        case FSMPackage.FSM_DTRANSITION__CONDITION:
            setCondition((String) newValue);
            return;
        case FSMPackage.FSM_DTRANSITION__ACTION:
            setAction((String) newValue);
            return;
        case FSMPackage.FSM_DTRANSITION__X:
            setX((Integer) newValue);
            return;
        case FSMPackage.FSM_DTRANSITION__Y:
            setY((Integer) newValue);
            return;
        case FSMPackage.FSM_DTRANSITION__TARGET:
            setTarget((FSMDState) newValue);
            return;
        case FSMPackage.FSM_DTRANSITION__SOURCE:
            setSource((FSMDState) newValue);
            return;
        case FSMPackage.FSM_DTRANSITION__PRIORITY:
            setPriority((Integer) newValue);
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
        case FSMPackage.FSM_DTRANSITION__SOURCE_ANCHOR_X:
            setSourceAnchorX(SOURCE_ANCHOR_X_EDEFAULT);
            return;
        case FSMPackage.FSM_DTRANSITION__SOURCE_ANCHOR_Y:
            setSourceAnchorY(SOURCE_ANCHOR_Y_EDEFAULT);
            return;
        case FSMPackage.FSM_DTRANSITION__TARGET_ANCHOR_X:
            setTargetAnchorX(TARGET_ANCHOR_X_EDEFAULT);
            return;
        case FSMPackage.FSM_DTRANSITION__TARGET_ANCHOR_Y:
            setTargetAnchorY(TARGET_ANCHOR_Y_EDEFAULT);
            return;
        case FSMPackage.FSM_DTRANSITION__TYPE:
            setType(TYPE_EDEFAULT);
            return;
        case FSMPackage.FSM_DTRANSITION__CONNECTION_POINT:
            getConnectionPoint().clear();
            return;
        case FSMPackage.FSM_DTRANSITION__TRIGGER:
            setTrigger(TRIGGER_EDEFAULT);
            return;
        case FSMPackage.FSM_DTRANSITION__EVENT:
            setEvent(EVENT_EDEFAULT);
            return;
        case FSMPackage.FSM_DTRANSITION__CONDITION:
            setCondition(CONDITION_EDEFAULT);
            return;
        case FSMPackage.FSM_DTRANSITION__ACTION:
            setAction(ACTION_EDEFAULT);
            return;
        case FSMPackage.FSM_DTRANSITION__X:
            setX(X_EDEFAULT);
            return;
        case FSMPackage.FSM_DTRANSITION__Y:
            setY(Y_EDEFAULT);
            return;
        case FSMPackage.FSM_DTRANSITION__TARGET:
            setTarget((FSMDState) null);
            return;
        case FSMPackage.FSM_DTRANSITION__SOURCE:
            setSource((FSMDState) null);
            return;
        case FSMPackage.FSM_DTRANSITION__PRIORITY:
            setPriority(PRIORITY_EDEFAULT);
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
        case FSMPackage.FSM_DTRANSITION__SOURCE_ANCHOR_X:
            return sourceAnchorX != SOURCE_ANCHOR_X_EDEFAULT;
        case FSMPackage.FSM_DTRANSITION__SOURCE_ANCHOR_Y:
            return sourceAnchorY != SOURCE_ANCHOR_Y_EDEFAULT;
        case FSMPackage.FSM_DTRANSITION__TARGET_ANCHOR_X:
            return targetAnchorX != TARGET_ANCHOR_X_EDEFAULT;
        case FSMPackage.FSM_DTRANSITION__TARGET_ANCHOR_Y:
            return targetAnchorY != TARGET_ANCHOR_Y_EDEFAULT;
        case FSMPackage.FSM_DTRANSITION__TYPE:
            return type != TYPE_EDEFAULT;
        case FSMPackage.FSM_DTRANSITION__CONNECTION_POINT:
            return connectionPoint != null && !connectionPoint.isEmpty();
        case FSMPackage.FSM_DTRANSITION__TRIGGER:
            return TRIGGER_EDEFAULT == null ? trigger != null : !TRIGGER_EDEFAULT.equals(trigger);
        case FSMPackage.FSM_DTRANSITION__EVENT:
            return EVENT_EDEFAULT == null ? event != null : !EVENT_EDEFAULT.equals(event);
        case FSMPackage.FSM_DTRANSITION__CONDITION:
            return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
        case FSMPackage.FSM_DTRANSITION__ACTION:
            return ACTION_EDEFAULT == null ? action != null : !ACTION_EDEFAULT.equals(action);
        case FSMPackage.FSM_DTRANSITION__X:
            return x != X_EDEFAULT;
        case FSMPackage.FSM_DTRANSITION__Y:
            return y != Y_EDEFAULT;
        case FSMPackage.FSM_DTRANSITION__TARGET:
            return target != null;
        case FSMPackage.FSM_DTRANSITION__SOURCE:
            return source != null;
        case FSMPackage.FSM_DTRANSITION__PRIORITY:
            return priority != PRIORITY_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == AbstractLine.class) {
            switch (derivedFeatureID) {
            case FSMPackage.FSM_DTRANSITION__SOURCE_ANCHOR_X:
                return COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_X;
            case FSMPackage.FSM_DTRANSITION__SOURCE_ANCHOR_Y:
                return COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_Y;
            case FSMPackage.FSM_DTRANSITION__TARGET_ANCHOR_X:
                return COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_X;
            case FSMPackage.FSM_DTRANSITION__TARGET_ANCHOR_Y:
                return COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_Y;
            case FSMPackage.FSM_DTRANSITION__TYPE:
                return COREPackage.ABSTRACT_LINE__TYPE;
            case FSMPackage.FSM_DTRANSITION__CONNECTION_POINT:
                return COREPackage.ABSTRACT_LINE__CONNECTION_POINT;
            default:
                return -1;
            }
        }
        if (baseClass == FSMDAbstractLine.class) {
            switch (derivedFeatureID) {
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
        if (baseClass == AbstractLine.class) {
            switch (baseFeatureID) {
            case COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_X:
                return FSMPackage.FSM_DTRANSITION__SOURCE_ANCHOR_X;
            case COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_Y:
                return FSMPackage.FSM_DTRANSITION__SOURCE_ANCHOR_Y;
            case COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_X:
                return FSMPackage.FSM_DTRANSITION__TARGET_ANCHOR_X;
            case COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_Y:
                return FSMPackage.FSM_DTRANSITION__TARGET_ANCHOR_Y;
            case COREPackage.ABSTRACT_LINE__TYPE:
                return FSMPackage.FSM_DTRANSITION__TYPE;
            case COREPackage.ABSTRACT_LINE__CONNECTION_POINT:
                return FSMPackage.FSM_DTRANSITION__CONNECTION_POINT;
            default:
                return -1;
            }
        }
        if (baseClass == FSMDAbstractLine.class) {
            switch (baseFeatureID) {
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
        result.append(" (sourceAnchorX: ");
        result.append(sourceAnchorX);
        result.append(", sourceAnchorY: ");
        result.append(sourceAnchorY);
        result.append(", targetAnchorX: ");
        result.append(targetAnchorX);
        result.append(", targetAnchorY: ");
        result.append(targetAnchorY);
        result.append(", type: ");
        result.append(type);
        result.append(", trigger: ");
        result.append(trigger);
        result.append(", event: ");
        result.append(event);
        result.append(", condition: ");
        result.append(condition);
        result.append(", action: ");
        result.append(action);
        result.append(", x: ");
        result.append(x);
        result.append(", y: ");
        result.append(y);
        result.append(", priority: ");
        result.append(priority);
        result.append(')');
        return result.toString();
    }

} // FSMDTransitionImpl
