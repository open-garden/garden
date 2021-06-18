/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.Phase;
import com.zipc.garden.model.scenario.PhaseAction;
import com.zipc.garden.model.scenario.PhaseCondition;
import com.zipc.garden.model.scenario.ScenarioPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Phase</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.PhaseImpl#getAction <em>Action</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.PhaseImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.PhaseImpl#getNextPhases <em>Next Phases</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.PhaseImpl#getPrevPhase <em>Prev Phase</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.PhaseImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PhaseImpl extends MinimalEObjectImpl.Container implements Phase {
    /**
     * The cached value of the '{@link #getAction() <em>Action</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAction()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected PhaseAction action;

    /**
     * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCondition()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected PhaseCondition condition;

    /**
     * The cached value of the '{@link #getNextPhases() <em>Next Phases</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNextPhases()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<Phase> nextPhases;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String name = NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PhaseImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.PHASE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public PhaseAction getAction() {
        return action;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAction(PhaseAction newAction, NotificationChain msgs) {
        PhaseAction oldAction = action;
        action = newAction;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.PHASE__ACTION, oldAction, newAction);
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
    public void setAction(PhaseAction newAction) {
        if (newAction != action) {
            NotificationChain msgs = null;
            if (action != null)
                msgs = ((InternalEObject)action).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.PHASE__ACTION, null, msgs);
            if (newAction != null)
                msgs = ((InternalEObject)newAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.PHASE__ACTION, null, msgs);
            msgs = basicSetAction(newAction, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.PHASE__ACTION, newAction, newAction));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public PhaseCondition getCondition() {
        return condition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCondition(PhaseCondition newCondition, NotificationChain msgs) {
        PhaseCondition oldCondition = condition;
        condition = newCondition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.PHASE__CONDITION, oldCondition, newCondition);
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
    public void setCondition(PhaseCondition newCondition) {
        if (newCondition != condition) {
            NotificationChain msgs = null;
            if (condition != null)
                msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.PHASE__CONDITION, null, msgs);
            if (newCondition != null)
                msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScenarioPackage.PHASE__CONDITION, null, msgs);
            msgs = basicSetCondition(newCondition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.PHASE__CONDITION, newCondition, newCondition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<Phase> getNextPhases() {
        if (nextPhases == null) {
            nextPhases = new EObjectContainmentWithInverseEList<Phase>(Phase.class, this, ScenarioPackage.PHASE__NEXT_PHASES, ScenarioPackage.PHASE__PREV_PHASE);
        }
        return nextPhases;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Phase getPrevPhase() {
        if (eContainerFeatureID() != ScenarioPackage.PHASE__PREV_PHASE) return null;
        return (Phase)eInternalContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPrevPhase(Phase newPrevPhase, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newPrevPhase, ScenarioPackage.PHASE__PREV_PHASE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setPrevPhase(Phase newPrevPhase) {
        if (newPrevPhase != eInternalContainer() || (eContainerFeatureID() != ScenarioPackage.PHASE__PREV_PHASE && newPrevPhase != null)) {
            if (EcoreUtil.isAncestor(this, newPrevPhase))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newPrevPhase != null)
                msgs = ((InternalEObject)newPrevPhase).eInverseAdd(this, ScenarioPackage.PHASE__NEXT_PHASES, Phase.class, msgs);
            msgs = basicSetPrevPhase(newPrevPhase, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.PHASE__PREV_PHASE, newPrevPhase, newPrevPhase));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.PHASE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScenarioPackage.PHASE__NEXT_PHASES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getNextPhases()).basicAdd(otherEnd, msgs);
            case ScenarioPackage.PHASE__PREV_PHASE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetPrevPhase((Phase)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScenarioPackage.PHASE__ACTION:
                return basicSetAction(null, msgs);
            case ScenarioPackage.PHASE__CONDITION:
                return basicSetCondition(null, msgs);
            case ScenarioPackage.PHASE__NEXT_PHASES:
                return ((InternalEList<?>)getNextPhases()).basicRemove(otherEnd, msgs);
            case ScenarioPackage.PHASE__PREV_PHASE:
                return basicSetPrevPhase(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case ScenarioPackage.PHASE__PREV_PHASE:
                return eInternalContainer().eInverseRemove(this, ScenarioPackage.PHASE__NEXT_PHASES, Phase.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ScenarioPackage.PHASE__ACTION:
                return getAction();
            case ScenarioPackage.PHASE__CONDITION:
                return getCondition();
            case ScenarioPackage.PHASE__NEXT_PHASES:
                return getNextPhases();
            case ScenarioPackage.PHASE__PREV_PHASE:
                return getPrevPhase();
            case ScenarioPackage.PHASE__NAME:
                return getName();
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
            case ScenarioPackage.PHASE__ACTION:
                setAction((PhaseAction)newValue);
                return;
            case ScenarioPackage.PHASE__CONDITION:
                setCondition((PhaseCondition)newValue);
                return;
            case ScenarioPackage.PHASE__NEXT_PHASES:
                getNextPhases().clear();
                getNextPhases().addAll((Collection<? extends Phase>)newValue);
                return;
            case ScenarioPackage.PHASE__PREV_PHASE:
                setPrevPhase((Phase)newValue);
                return;
            case ScenarioPackage.PHASE__NAME:
                setName((String)newValue);
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
            case ScenarioPackage.PHASE__ACTION:
                setAction((PhaseAction)null);
                return;
            case ScenarioPackage.PHASE__CONDITION:
                setCondition((PhaseCondition)null);
                return;
            case ScenarioPackage.PHASE__NEXT_PHASES:
                getNextPhases().clear();
                return;
            case ScenarioPackage.PHASE__PREV_PHASE:
                setPrevPhase((Phase)null);
                return;
            case ScenarioPackage.PHASE__NAME:
                setName(NAME_EDEFAULT);
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
            case ScenarioPackage.PHASE__ACTION:
                return action != null;
            case ScenarioPackage.PHASE__CONDITION:
                return condition != null;
            case ScenarioPackage.PHASE__NEXT_PHASES:
                return nextPhases != null && !nextPhases.isEmpty();
            case ScenarioPackage.PHASE__PREV_PHASE:
                return getPrevPhase() != null;
            case ScenarioPackage.PHASE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //PhaseImpl
