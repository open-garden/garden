/**
 */
package com.zipc.garden.model.bps.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.bps.BPSDataflow;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;
import com.zipc.garden.model.bps.BPSPackage;
import com.zipc.garden.model.bps.BPSVariable;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Dataflow</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSDataflowImpl#getSource <em>Source</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSDataflowImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * @generated
 */
public class BPSDataflowImpl extends MinimalEObjectImpl.Container implements BPSDataflow {
    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected BPSInstanceStateMachine source;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected BPSVariable target;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPSDataflowImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPSPackage.Literals.BPS_DATAFLOW;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSInstanceStateMachine getSource() {
        if (source != null && source.eIsProxy()) {
            InternalEObject oldSource = (InternalEObject) source;
            source = (BPSInstanceStateMachine) eResolveProxy(oldSource);
            if (source != oldSource) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BPSPackage.BPS_DATAFLOW__SOURCE, oldSource, source));
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPSInstanceStateMachine basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSource(BPSInstanceStateMachine newSource) {
        BPSInstanceStateMachine oldSource = source;
        source = newSource;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_DATAFLOW__SOURCE, oldSource, source));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSVariable getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = (BPSVariable) eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BPSPackage.BPS_DATAFLOW__TARGET, oldTarget, target));
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPSVariable basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTarget(BPSVariable newTarget) {
        BPSVariable oldTarget = target;
        target = newTarget;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_DATAFLOW__TARGET, oldTarget, target));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case BPSPackage.BPS_DATAFLOW__SOURCE:
            if (resolve)
                return getSource();
            return basicGetSource();
        case BPSPackage.BPS_DATAFLOW__TARGET:
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
        case BPSPackage.BPS_DATAFLOW__SOURCE:
            setSource((BPSInstanceStateMachine) newValue);
            return;
        case BPSPackage.BPS_DATAFLOW__TARGET:
            setTarget((BPSVariable) newValue);
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
        case BPSPackage.BPS_DATAFLOW__SOURCE:
            setSource((BPSInstanceStateMachine) null);
            return;
        case BPSPackage.BPS_DATAFLOW__TARGET:
            setTarget((BPSVariable) null);
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
        case BPSPackage.BPS_DATAFLOW__SOURCE:
            return source != null;
        case BPSPackage.BPS_DATAFLOW__TARGET:
            return target != null;
        }
        return super.eIsSet(featureID);
    }

} // BPSDataflowImpl
