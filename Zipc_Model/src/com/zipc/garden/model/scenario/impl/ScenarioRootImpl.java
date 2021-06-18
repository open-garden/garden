/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.impl.AbstractRootElementImpl;

import com.zipc.garden.model.scenario.ActorSetting;
import com.zipc.garden.model.scenario.PhaseSetting;
import com.zipc.garden.model.scenario.RoadSetting;
import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scenario.ScenarioRoot;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.ScenarioRootImpl#getRoadSetting <em>Road Setting</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.ScenarioRootImpl#getActorSetting <em>Actor Setting</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.ScenarioRootImpl#getPhaseSetting <em>Phase Setting</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScenarioRootImpl extends AbstractRootElementImpl implements ScenarioRoot {
    /**
     * The cached value of the '{@link #getRoadSetting() <em>Road Setting</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRoadSetting()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected RoadSetting roadSetting;

    /**
     * The cached value of the '{@link #getActorSetting() <em>Actor Setting</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActorSetting()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected ActorSetting actorSetting;

    /**
     * The cached value of the '{@link #getPhaseSetting() <em>Phase Setting</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPhaseSetting()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected PhaseSetting phaseSetting;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ScenarioRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.SCENARIO_ROOT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public RoadSetting getRoadSetting() {
        return roadSetting;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRoadSetting(RoadSetting newRoadSetting, NotificationChain msgs) {
        RoadSetting oldRoadSetting = roadSetting;
        roadSetting = newRoadSetting;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_ROOT__ROAD_SETTING, oldRoadSetting, newRoadSetting);
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
    public void setRoadSetting(RoadSetting newRoadSetting) {
        if (newRoadSetting != roadSetting) {
            NotificationChain msgs = null;
            if (roadSetting != null)
                msgs = ((InternalEObject)roadSetting).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.SCENARIO_ROOT__ROAD_SETTING, null, msgs);
            if (newRoadSetting != null)
                msgs = ((InternalEObject)newRoadSetting).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.SCENARIO_ROOT__ROAD_SETTING, null, msgs);
            msgs = basicSetRoadSetting(newRoadSetting, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_ROOT__ROAD_SETTING, newRoadSetting, newRoadSetting));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ActorSetting getActorSetting() {
        return actorSetting;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetActorSetting(ActorSetting newActorSetting, NotificationChain msgs) {
        ActorSetting oldActorSetting = actorSetting;
        actorSetting = newActorSetting;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_ROOT__ACTOR_SETTING, oldActorSetting, newActorSetting);
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
    public void setActorSetting(ActorSetting newActorSetting) {
        if (newActorSetting != actorSetting) {
            NotificationChain msgs = null;
            if (actorSetting != null)
                msgs = ((InternalEObject)actorSetting).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.SCENARIO_ROOT__ACTOR_SETTING, null, msgs);
            if (newActorSetting != null)
                msgs = ((InternalEObject)newActorSetting).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.SCENARIO_ROOT__ACTOR_SETTING, null, msgs);
            msgs = basicSetActorSetting(newActorSetting, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_ROOT__ACTOR_SETTING, newActorSetting, newActorSetting));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public PhaseSetting getPhaseSetting() {
        return phaseSetting;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPhaseSetting(PhaseSetting newPhaseSetting, NotificationChain msgs) {
        PhaseSetting oldPhaseSetting = phaseSetting;
        phaseSetting = newPhaseSetting;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_ROOT__PHASE_SETTING, oldPhaseSetting, newPhaseSetting);
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
    public void setPhaseSetting(PhaseSetting newPhaseSetting) {
        if (newPhaseSetting != phaseSetting) {
            NotificationChain msgs = null;
            if (phaseSetting != null)
                msgs = ((InternalEObject)phaseSetting).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.SCENARIO_ROOT__PHASE_SETTING, null, msgs);
            if (newPhaseSetting != null)
                msgs = ((InternalEObject)newPhaseSetting).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.SCENARIO_ROOT__PHASE_SETTING, null, msgs);
            msgs = basicSetPhaseSetting(newPhaseSetting, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_ROOT__PHASE_SETTING, newPhaseSetting, newPhaseSetting));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScenarioPackage.SCENARIO_ROOT__ROAD_SETTING:
                return basicSetRoadSetting(null, msgs);
            case ScenarioPackage.SCENARIO_ROOT__ACTOR_SETTING:
                return basicSetActorSetting(null, msgs);
            case ScenarioPackage.SCENARIO_ROOT__PHASE_SETTING:
                return basicSetPhaseSetting(null, msgs);
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
            case ScenarioPackage.SCENARIO_ROOT__ROAD_SETTING:
                return getRoadSetting();
            case ScenarioPackage.SCENARIO_ROOT__ACTOR_SETTING:
                return getActorSetting();
            case ScenarioPackage.SCENARIO_ROOT__PHASE_SETTING:
                return getPhaseSetting();
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
            case ScenarioPackage.SCENARIO_ROOT__ROAD_SETTING:
                setRoadSetting((RoadSetting)newValue);
                return;
            case ScenarioPackage.SCENARIO_ROOT__ACTOR_SETTING:
                setActorSetting((ActorSetting)newValue);
                return;
            case ScenarioPackage.SCENARIO_ROOT__PHASE_SETTING:
                setPhaseSetting((PhaseSetting)newValue);
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
            case ScenarioPackage.SCENARIO_ROOT__ROAD_SETTING:
                setRoadSetting((RoadSetting)null);
                return;
            case ScenarioPackage.SCENARIO_ROOT__ACTOR_SETTING:
                setActorSetting((ActorSetting)null);
                return;
            case ScenarioPackage.SCENARIO_ROOT__PHASE_SETTING:
                setPhaseSetting((PhaseSetting)null);
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
            case ScenarioPackage.SCENARIO_ROOT__ROAD_SETTING:
                return roadSetting != null;
            case ScenarioPackage.SCENARIO_ROOT__ACTOR_SETTING:
                return actorSetting != null;
            case ScenarioPackage.SCENARIO_ROOT__PHASE_SETTING:
                return phaseSetting != null;
        }
        return super.eIsSet(featureID);
    }

} //ScenarioRootImpl
