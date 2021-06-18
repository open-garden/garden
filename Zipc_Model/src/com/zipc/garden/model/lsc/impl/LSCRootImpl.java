/**
 */
package com.zipc.garden.model.lsc.impl;

import com.google.gwt.user.client.rpc.GwtTransient;
import com.zipc.garden.model.core.impl.AbstractRootElementImpl;

import com.zipc.garden.model.lsc.LSCPackage;
import com.zipc.garden.model.lsc.LSCRoot;

import com.zipc.garden.model.lsc.LSCScenario;
import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.lsc.impl.LSCRootImpl#getScenarios <em>Scenarios</em>}</li>
 * </ul>
 * @generated
 */
public class LSCRootImpl extends AbstractRootElementImpl implements LSCRoot {
    /**
     * The cached value of the '{@link #getScenarios() <em>Scenarios</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScenarios()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<LSCScenario> scenarios;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected LSCRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return LSCPackage.Literals.LSC_ROOT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<LSCScenario> getScenarios() {
        if (scenarios == null) {
            scenarios = new EObjectContainmentEList<LSCScenario>(LSCScenario.class, this, LSCPackage.LSC_ROOT__SCENARIOS);
        }
        return scenarios;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case LSCPackage.LSC_ROOT__SCENARIOS:
            return ((InternalEList<?>) getScenarios()).basicRemove(otherEnd, msgs);
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
        case LSCPackage.LSC_ROOT__SCENARIOS:
            return getScenarios();
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
        case LSCPackage.LSC_ROOT__SCENARIOS:
            getScenarios().clear();
            getScenarios().addAll((Collection<? extends LSCScenario>) newValue);
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
        case LSCPackage.LSC_ROOT__SCENARIOS:
            getScenarios().clear();
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
        case LSCPackage.LSC_ROOT__SCENARIOS:
            return scenarios != null && !scenarios.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // LSCRootImpl
