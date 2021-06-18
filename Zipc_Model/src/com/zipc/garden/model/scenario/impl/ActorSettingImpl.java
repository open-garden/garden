/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.Actor;
import com.zipc.garden.model.scenario.ActorSetting;
import com.zipc.garden.model.scenario.EgoVehicle;
import com.zipc.garden.model.scenario.ScenarioPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Actor Setting</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.ActorSettingImpl#getEgo <em>Ego</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.ActorSettingImpl#getOthers <em>Others</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActorSettingImpl extends MinimalEObjectImpl.Container implements ActorSetting {
    /**
     * The cached value of the '{@link #getEgo() <em>Ego</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEgo()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EgoVehicle ego;

    /**
     * The cached value of the '{@link #getOthers() <em>Others</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOthers()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<Actor> others;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ActorSettingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.ACTOR_SETTING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EgoVehicle getEgo() {
        return ego;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEgo(EgoVehicle newEgo, NotificationChain msgs) {
        EgoVehicle oldEgo = ego;
        ego = newEgo;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.ACTOR_SETTING__EGO, oldEgo, newEgo);
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
    public void setEgo(EgoVehicle newEgo) {
        if (newEgo != ego) {
            NotificationChain msgs = null;
            if (ego != null)
                msgs = ((InternalEObject)ego).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.ACTOR_SETTING__EGO, null, msgs);
            if (newEgo != null)
                msgs = ((InternalEObject)newEgo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.ACTOR_SETTING__EGO, null, msgs);
            msgs = basicSetEgo(newEgo, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.ACTOR_SETTING__EGO, newEgo, newEgo));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<Actor> getOthers() {
        if (others == null) {
            others = new EObjectContainmentEList<Actor>(Actor.class, this, ScenarioPackage.ACTOR_SETTING__OTHERS);
        }
        return others;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScenarioPackage.ACTOR_SETTING__EGO:
                return basicSetEgo(null, msgs);
            case ScenarioPackage.ACTOR_SETTING__OTHERS:
                return ((InternalEList<?>)getOthers()).basicRemove(otherEnd, msgs);
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
            case ScenarioPackage.ACTOR_SETTING__EGO:
                return getEgo();
            case ScenarioPackage.ACTOR_SETTING__OTHERS:
                return getOthers();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ScenarioPackage.ACTOR_SETTING__EGO:
                setEgo((EgoVehicle)newValue);
                return;
            case ScenarioPackage.ACTOR_SETTING__OTHERS:
                getOthers().clear();
                getOthers().addAll((Collection<? extends Actor>)newValue);
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
            case ScenarioPackage.ACTOR_SETTING__EGO:
                setEgo((EgoVehicle)null);
                return;
            case ScenarioPackage.ACTOR_SETTING__OTHERS:
                getOthers().clear();
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
            case ScenarioPackage.ACTOR_SETTING__EGO:
                return ego != null;
            case ScenarioPackage.ACTOR_SETTING__OTHERS:
                return others != null && !others.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //ActorSettingImpl
