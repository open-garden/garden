/**
 */
package com.zipc.garden.model.fmcs.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.fmcs.FMCSMutexExpression;
import com.zipc.garden.model.fmcs.FMCSODElement;
import com.zipc.garden.model.fmcs.FMCSPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Mutex Expression</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.impl.FMCSMutexExpressionImpl#getOdElements <em>Od Elements</em>}</li>
 * </ul>
 * @generated
 */
public class FMCSMutexExpressionImpl extends FMCSExpressionImpl implements FMCSMutexExpression {
    /**
     * The cached value of the '{@link #getOdElements() <em>Od Elements</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getOdElements()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FMCSODElement> odElements;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FMCSMutexExpressionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FMCSPackage.Literals.FMCS_MUTEX_EXPRESSION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FMCSODElement> getOdElements() {
        if (odElements == null) {
            odElements = new EObjectContainmentEList<FMCSODElement>(FMCSODElement.class, this, FMCSPackage.FMCS_MUTEX_EXPRESSION__OD_ELEMENTS);
        }
        return odElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FMCSPackage.FMCS_MUTEX_EXPRESSION__OD_ELEMENTS:
            return ((InternalEList<?>) getOdElements()).basicRemove(otherEnd, msgs);
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
        case FMCSPackage.FMCS_MUTEX_EXPRESSION__OD_ELEMENTS:
            return getOdElements();
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
        case FMCSPackage.FMCS_MUTEX_EXPRESSION__OD_ELEMENTS:
            getOdElements().clear();
            getOdElements().addAll((Collection<? extends FMCSODElement>) newValue);
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
        case FMCSPackage.FMCS_MUTEX_EXPRESSION__OD_ELEMENTS:
            getOdElements().clear();
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
        case FMCSPackage.FMCS_MUTEX_EXPRESSION__OD_ELEMENTS:
            return odElements != null && !odElements.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // FMCSMutexExpressionImpl
