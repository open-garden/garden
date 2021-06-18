/**
 */
package com.zipc.garden.model.fsm.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.fsm.FSMDLine;
import com.zipc.garden.model.fsm.FSMDVertex;
import com.zipc.garden.model.fsm.FSMPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>DLine</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDLineImpl#getSource <em>Source</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDLineImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * @generated
 */
public class FSMDLineImpl extends FSMDAbstractLineImpl implements FSMDLine {
    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected FSMDVertex source;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected FSMDVertex target;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FSMDLineImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FSMPackage.Literals.FSM_DLINE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDVertex getSource() {
        if (source != null && source.eIsProxy()) {
            InternalEObject oldSource = (InternalEObject) source;
            source = (FSMDVertex) eResolveProxy(oldSource);
            if (source != oldSource) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FSMPackage.FSM_DLINE__SOURCE, oldSource, source));
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FSMDVertex basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSource(FSMDVertex newSource, NotificationChain msgs) {
        FSMDVertex oldSource = source;
        source = newSource;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DLINE__SOURCE, oldSource, newSource);
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
    public void setSource(FSMDVertex newSource) {
        if (newSource != source) {
            NotificationChain msgs = null;
            if (source != null)
                msgs = ((InternalEObject) source).eInverseRemove(this, FSMPackage.FSM_DVERTEX__OUTGOING_LINE, FSMDVertex.class, msgs);
            if (newSource != null)
                msgs = ((InternalEObject) newSource).eInverseAdd(this, FSMPackage.FSM_DVERTEX__OUTGOING_LINE, FSMDVertex.class, msgs);
            msgs = basicSetSource(newSource, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DLINE__SOURCE, newSource, newSource));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDVertex getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = (FSMDVertex) eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FSMPackage.FSM_DLINE__TARGET, oldTarget, target));
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FSMDVertex basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTarget(FSMDVertex newTarget, NotificationChain msgs) {
        FSMDVertex oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DLINE__TARGET, oldTarget, newTarget);
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
    public void setTarget(FSMDVertex newTarget) {
        if (newTarget != target) {
            NotificationChain msgs = null;
            if (target != null)
                msgs = ((InternalEObject) target).eInverseRemove(this, FSMPackage.FSM_DVERTEX__INCOMING_LINE, FSMDVertex.class, msgs);
            if (newTarget != null)
                msgs = ((InternalEObject) newTarget).eInverseAdd(this, FSMPackage.FSM_DVERTEX__INCOMING_LINE, FSMDVertex.class, msgs);
            msgs = basicSetTarget(newTarget, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DLINE__TARGET, newTarget, newTarget));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FSMPackage.FSM_DLINE__SOURCE:
            if (source != null)
                msgs = ((InternalEObject) source).eInverseRemove(this, FSMPackage.FSM_DVERTEX__OUTGOING_LINE, FSMDVertex.class, msgs);
            return basicSetSource((FSMDVertex) otherEnd, msgs);
        case FSMPackage.FSM_DLINE__TARGET:
            if (target != null)
                msgs = ((InternalEObject) target).eInverseRemove(this, FSMPackage.FSM_DVERTEX__INCOMING_LINE, FSMDVertex.class, msgs);
            return basicSetTarget((FSMDVertex) otherEnd, msgs);
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
        case FSMPackage.FSM_DLINE__SOURCE:
            return basicSetSource(null, msgs);
        case FSMPackage.FSM_DLINE__TARGET:
            return basicSetTarget(null, msgs);
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
        case FSMPackage.FSM_DLINE__SOURCE:
            if (resolve)
                return getSource();
            return basicGetSource();
        case FSMPackage.FSM_DLINE__TARGET:
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
        case FSMPackage.FSM_DLINE__SOURCE:
            setSource((FSMDVertex) newValue);
            return;
        case FSMPackage.FSM_DLINE__TARGET:
            setTarget((FSMDVertex) newValue);
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
        case FSMPackage.FSM_DLINE__SOURCE:
            setSource((FSMDVertex) null);
            return;
        case FSMPackage.FSM_DLINE__TARGET:
            setTarget((FSMDVertex) null);
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
        case FSMPackage.FSM_DLINE__SOURCE:
            return source != null;
        case FSMPackage.FSM_DLINE__TARGET:
            return target != null;
        }
        return super.eIsSet(featureID);
    }

} // FSMDLineImpl
