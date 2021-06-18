/**
 */
package com.zipc.garden.model.fmcs.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.fmcs.FMCSODElement;
import com.zipc.garden.model.fmcs.FMCSPackage;
import com.zipc.garden.model.fmcs.FMCSSelectExpression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Select Expression</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.impl.FMCSSelectExpressionImpl#getOdElement <em>Od Element</em>}</li>
 * </ul>
 * @generated
 */
public class FMCSSelectExpressionImpl extends FMCSExpressionImpl implements FMCSSelectExpression {
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
    protected FMCSSelectExpressionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FMCSPackage.Literals.FMCS_SELECT_EXPRESSION;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_SELECT_EXPRESSION__OD_ELEMENT, oldOdElement, newOdElement);
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
                msgs = ((InternalEObject) odElement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMCSPackage.FMCS_SELECT_EXPRESSION__OD_ELEMENT, null, msgs);
            if (newOdElement != null)
                msgs = ((InternalEObject) newOdElement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMCSPackage.FMCS_SELECT_EXPRESSION__OD_ELEMENT, null, msgs);
            msgs = basicSetOdElement(newOdElement, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_SELECT_EXPRESSION__OD_ELEMENT, newOdElement, newOdElement));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FMCSPackage.FMCS_SELECT_EXPRESSION__OD_ELEMENT:
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
        case FMCSPackage.FMCS_SELECT_EXPRESSION__OD_ELEMENT:
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
        case FMCSPackage.FMCS_SELECT_EXPRESSION__OD_ELEMENT:
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
        case FMCSPackage.FMCS_SELECT_EXPRESSION__OD_ELEMENT:
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
        case FMCSPackage.FMCS_SELECT_EXPRESSION__OD_ELEMENT:
            return odElement != null;
        }
        return super.eIsSet(featureID);
    }

} // FMCSSelectExpressionImpl
