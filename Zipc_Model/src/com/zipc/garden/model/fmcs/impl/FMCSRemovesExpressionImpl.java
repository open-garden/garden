/**
 */
package com.zipc.garden.model.fmcs.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.fmcs.FMCSExpression;
import com.zipc.garden.model.fmcs.FMCSODElement;
import com.zipc.garden.model.fmcs.FMCSPackage;
import com.zipc.garden.model.fmcs.FMCSRemovesExpression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Removes Expression</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.impl.FMCSRemovesExpressionImpl#getExpression <em>Expression</em>}</li>
 * <li>{@link com.zipc.garden.model.fmcs.impl.FMCSRemovesExpressionImpl#getOdElement <em>Od Element</em>}</li>
 * </ul>
 * @generated
 */
public class FMCSRemovesExpressionImpl extends FMCSExpressionImpl implements FMCSRemovesExpression {
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
     * The cached value of the '{@link #getOdElement() <em>Od Element</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getOdElement()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected FMCSODElement odElement;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FMCSRemovesExpressionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FMCSPackage.Literals.FMCS_REMOVES_EXPRESSION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSODElement getOdElement() {
        return odElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOdElement(FMCSODElement newOdElement, NotificationChain msgs) {
        FMCSODElement oldOdElement = odElement;
        odElement = newOdElement;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_REMOVES_EXPRESSION__OD_ELEMENT, oldOdElement, newOdElement);
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
    public void setOdElement(FMCSODElement newOdElement) {
        if (newOdElement != odElement) {
            NotificationChain msgs = null;
            if (odElement != null)
                msgs = ((InternalEObject) odElement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMCSPackage.FMCS_REMOVES_EXPRESSION__OD_ELEMENT, null, msgs);
            if (newOdElement != null)
                msgs = ((InternalEObject) newOdElement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMCSPackage.FMCS_REMOVES_EXPRESSION__OD_ELEMENT, null, msgs);
            msgs = basicSetOdElement(newOdElement, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_REMOVES_EXPRESSION__OD_ELEMENT, newOdElement, newOdElement));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_REMOVES_EXPRESSION__EXPRESSION, oldExpression, newExpression);
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
                msgs = ((InternalEObject) expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMCSPackage.FMCS_REMOVES_EXPRESSION__EXPRESSION, null, msgs);
            if (newExpression != null)
                msgs = ((InternalEObject) newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMCSPackage.FMCS_REMOVES_EXPRESSION__EXPRESSION, null, msgs);
            msgs = basicSetExpression(newExpression, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_REMOVES_EXPRESSION__EXPRESSION, newExpression, newExpression));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FMCSPackage.FMCS_REMOVES_EXPRESSION__EXPRESSION:
            return basicSetExpression(null, msgs);
        case FMCSPackage.FMCS_REMOVES_EXPRESSION__OD_ELEMENT:
            return basicSetOdElement(null, msgs);
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
        case FMCSPackage.FMCS_REMOVES_EXPRESSION__EXPRESSION:
            return getExpression();
        case FMCSPackage.FMCS_REMOVES_EXPRESSION__OD_ELEMENT:
            return getOdElement();
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
        case FMCSPackage.FMCS_REMOVES_EXPRESSION__EXPRESSION:
            setExpression((FMCSExpression) newValue);
            return;
        case FMCSPackage.FMCS_REMOVES_EXPRESSION__OD_ELEMENT:
            setOdElement((FMCSODElement) newValue);
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
        case FMCSPackage.FMCS_REMOVES_EXPRESSION__EXPRESSION:
            setExpression((FMCSExpression) null);
            return;
        case FMCSPackage.FMCS_REMOVES_EXPRESSION__OD_ELEMENT:
            setOdElement((FMCSODElement) null);
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
        case FMCSPackage.FMCS_REMOVES_EXPRESSION__EXPRESSION:
            return expression != null;
        case FMCSPackage.FMCS_REMOVES_EXPRESSION__OD_ELEMENT:
            return odElement != null;
        }
        return super.eIsSet(featureID);
    }

} // FMCSRemovesExpressionImpl
