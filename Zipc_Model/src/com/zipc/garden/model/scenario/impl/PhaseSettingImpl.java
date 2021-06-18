/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.Phase;
import com.zipc.garden.model.scenario.PhaseSetting;
import com.zipc.garden.model.scenario.ScenarioPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Phase Setting</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.PhaseSettingImpl#getInitialPhase <em>Initial Phase</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PhaseSettingImpl extends MinimalEObjectImpl.Container implements PhaseSetting {
    /**
     * The cached value of the '{@link #getInitialPhase() <em>Initial Phase</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInitialPhase()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected Phase initialPhase;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PhaseSettingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.PHASE_SETTING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Phase getInitialPhase() {
        return initialPhase;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInitialPhase(Phase newInitialPhase, NotificationChain msgs) {
        Phase oldInitialPhase = initialPhase;
        initialPhase = newInitialPhase;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.PHASE_SETTING__INITIAL_PHASE, oldInitialPhase, newInitialPhase);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setInitialPhase(Phase newInitialPhase) {
        if (newInitialPhase != initialPhase) {
            NotificationChain msgs = null;
            if (initialPhase != null)
                msgs = ((InternalEObject)initialPhase).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.PHASE_SETTING__INITIAL_PHASE, null, msgs);
            if (newInitialPhase != null)
                msgs = ((InternalEObject)newInitialPhase).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.PHASE_SETTING__INITIAL_PHASE, null, msgs);
            msgs = basicSetInitialPhase(newInitialPhase, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.PHASE_SETTING__INITIAL_PHASE, newInitialPhase, newInitialPhase));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScenarioPackage.PHASE_SETTING__INITIAL_PHASE:
                return basicSetInitialPhase(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ScenarioPackage.PHASE_SETTING__INITIAL_PHASE:
                return getInitialPhase();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ScenarioPackage.PHASE_SETTING__INITIAL_PHASE:
                setInitialPhase((Phase)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case ScenarioPackage.PHASE_SETTING__INITIAL_PHASE:
                setInitialPhase((Phase)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ScenarioPackage.PHASE_SETTING__INITIAL_PHASE:
                return initialPhase != null;
        }
        return super.eIsSet(featureID);
    }

} //PhaseSettingImpl
