/**
 */
package com.zipc.garden.model.fmcs.impl;

import com.google.gwt.user.client.rpc.GwtTransient;
import com.zipc.garden.model.fmc.FMCConstraint;
import com.zipc.garden.model.fmcs.FMCSConstraint;
import com.zipc.garden.model.fmcs.FMCSExpression;
import com.zipc.garden.model.fmcs.FMCSPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Constraint</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.impl.FMCSConstraintImpl#getExpression <em>Expression</em>}</li>
 * <li>{@link com.zipc.garden.model.fmcs.impl.FMCSConstraintImpl#getRef <em>Ref</em>}</li>
 * </ul>
 * @generated
 */
public class FMCSConstraintImpl extends MinimalEObjectImpl.Container implements FMCSConstraint {
    /**
     * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExpression()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected FMCSExpression expression;

    /**
     * The cached value of the '{@link #getRef() <em>Ref</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRef()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected FMCConstraint ref;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FMCSConstraintImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FMCSPackage.Literals.FMCS_CONSTRAINT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSExpression getExpression() {
        return expression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetExpression(FMCSExpression newExpression, NotificationChain msgs) {
        FMCSExpression oldExpression = expression;
        expression = newExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_CONSTRAINT__EXPRESSION, oldExpression, newExpression);
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
    public void setExpression(FMCSExpression newExpression) {
        if (newExpression != expression) {
            NotificationChain msgs = null;
            if (expression != null)
                msgs = ((InternalEObject) expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMCSPackage.FMCS_CONSTRAINT__EXPRESSION, null, msgs);
            if (newExpression != null)
                msgs = ((InternalEObject) newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMCSPackage.FMCS_CONSTRAINT__EXPRESSION, null, msgs);
            msgs = basicSetExpression(newExpression, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_CONSTRAINT__EXPRESSION, newExpression, newExpression));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCConstraint getRef() {
        if (ref != null && ref.eIsProxy()) {
            InternalEObject oldRef = (InternalEObject) ref;
            ref = (FMCConstraint) eResolveProxy(oldRef);
            if (ref != oldRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FMCSPackage.FMCS_CONSTRAINT__REF, oldRef, ref));
            }
        }
        return ref;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FMCConstraint basicGetRef() {
        return ref;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRef(FMCConstraint newRef) {
        FMCConstraint oldRef = ref;
        ref = newRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_CONSTRAINT__REF, oldRef, ref));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FMCSPackage.FMCS_CONSTRAINT__EXPRESSION:
            return basicSetExpression(null, msgs);
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
        case FMCSPackage.FMCS_CONSTRAINT__EXPRESSION:
            return getExpression();
        case FMCSPackage.FMCS_CONSTRAINT__REF:
            if (resolve)
                return getRef();
            return basicGetRef();
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
        case FMCSPackage.FMCS_CONSTRAINT__EXPRESSION:
            setExpression((FMCSExpression) newValue);
            return;
        case FMCSPackage.FMCS_CONSTRAINT__REF:
            setRef((FMCConstraint) newValue);
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
        case FMCSPackage.FMCS_CONSTRAINT__EXPRESSION:
            setExpression((FMCSExpression) null);
            return;
        case FMCSPackage.FMCS_CONSTRAINT__REF:
            setRef((FMCConstraint) null);
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
        case FMCSPackage.FMCS_CONSTRAINT__EXPRESSION:
            return expression != null;
        case FMCSPackage.FMCS_CONSTRAINT__REF:
            return ref != null;
        }
        return super.eIsSet(featureID);
    }

} // FMCSConstraintImpl
