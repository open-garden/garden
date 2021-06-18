/**
 */
package com.zipc.garden.model.fmcs.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.fmcs.FMCSExpression;
import com.zipc.garden.model.fmcs.FMCSImpliesExpression;
import com.zipc.garden.model.fmcs.FMCSPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Implies Expression</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.impl.FMCSImpliesExpressionImpl#getLeftExpression <em>Left Expression</em>}</li>
 * <li>{@link com.zipc.garden.model.fmcs.impl.FMCSImpliesExpressionImpl#getRightExpression <em>Right Expression</em>}</li>
 * </ul>
 * @generated
 */
public class FMCSImpliesExpressionImpl extends FMCSExpressionImpl implements FMCSImpliesExpression {
    /**
     * The cached value of the '{@link #getLeftExpression() <em>Left Expression</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see #getLeftExpression()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected FMCSExpression leftExpression;

    /**
     * The cached value of the '{@link #getRightExpression() <em>Right Expression</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see #getRightExpression()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected FMCSExpression rightExpression;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FMCSImpliesExpressionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FMCSPackage.Literals.FMCS_IMPLIES_EXPRESSION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSExpression getLeftExpression() {
        return leftExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLeftExpression(FMCSExpression newLeftExpression, NotificationChain msgs) {
        FMCSExpression oldLeftExpression = leftExpression;
        leftExpression = newLeftExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_IMPLIES_EXPRESSION__LEFT_EXPRESSION, oldLeftExpression,
                    newLeftExpression);
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
    public void setLeftExpression(FMCSExpression newLeftExpression) {
        if (newLeftExpression != leftExpression) {
            NotificationChain msgs = null;
            if (leftExpression != null)
                msgs = ((InternalEObject) leftExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMCSPackage.FMCS_IMPLIES_EXPRESSION__LEFT_EXPRESSION, null, msgs);
            if (newLeftExpression != null)
                msgs = ((InternalEObject) newLeftExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMCSPackage.FMCS_IMPLIES_EXPRESSION__LEFT_EXPRESSION, null, msgs);
            msgs = basicSetLeftExpression(newLeftExpression, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_IMPLIES_EXPRESSION__LEFT_EXPRESSION, newLeftExpression, newLeftExpression));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSExpression getRightExpression() {
        return rightExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRightExpression(FMCSExpression newRightExpression, NotificationChain msgs) {
        FMCSExpression oldRightExpression = rightExpression;
        rightExpression = newRightExpression;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_IMPLIES_EXPRESSION__RIGHT_EXPRESSION, oldRightExpression,
                    newRightExpression);
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
    public void setRightExpression(FMCSExpression newRightExpression) {
        if (newRightExpression != rightExpression) {
            NotificationChain msgs = null;
            if (rightExpression != null)
                msgs = ((InternalEObject) rightExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMCSPackage.FMCS_IMPLIES_EXPRESSION__RIGHT_EXPRESSION, null, msgs);
            if (newRightExpression != null)
                msgs = ((InternalEObject) newRightExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMCSPackage.FMCS_IMPLIES_EXPRESSION__RIGHT_EXPRESSION, null, msgs);
            msgs = basicSetRightExpression(newRightExpression, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_IMPLIES_EXPRESSION__RIGHT_EXPRESSION, newRightExpression, newRightExpression));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FMCSPackage.FMCS_IMPLIES_EXPRESSION__LEFT_EXPRESSION:
            return basicSetLeftExpression(null, msgs);
        case FMCSPackage.FMCS_IMPLIES_EXPRESSION__RIGHT_EXPRESSION:
            return basicSetRightExpression(null, msgs);
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
        case FMCSPackage.FMCS_IMPLIES_EXPRESSION__LEFT_EXPRESSION:
            return getLeftExpression();
        case FMCSPackage.FMCS_IMPLIES_EXPRESSION__RIGHT_EXPRESSION:
            return getRightExpression();
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
        case FMCSPackage.FMCS_IMPLIES_EXPRESSION__LEFT_EXPRESSION:
            setLeftExpression((FMCSExpression) newValue);
            return;
        case FMCSPackage.FMCS_IMPLIES_EXPRESSION__RIGHT_EXPRESSION:
            setRightExpression((FMCSExpression) newValue);
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
        case FMCSPackage.FMCS_IMPLIES_EXPRESSION__LEFT_EXPRESSION:
            setLeftExpression((FMCSExpression) null);
            return;
        case FMCSPackage.FMCS_IMPLIES_EXPRESSION__RIGHT_EXPRESSION:
            setRightExpression((FMCSExpression) null);
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
        case FMCSPackage.FMCS_IMPLIES_EXPRESSION__LEFT_EXPRESSION:
            return leftExpression != null;
        case FMCSPackage.FMCS_IMPLIES_EXPRESSION__RIGHT_EXPRESSION:
            return rightExpression != null;
        }
        return super.eIsSet(featureID);
    }

} // FMCSImpliesExpressionImpl
