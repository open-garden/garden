/**
 */
package com.zipc.garden.model.bp.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.bp.BPBehavior;
import com.zipc.garden.model.bp.BPBehaviorPattern;
import com.zipc.garden.model.bp.BPPackage;
import com.zipc.garden.model.bp.BPSpec;

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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Behavior Pattern</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.impl.BPBehaviorPatternImpl#getBehaviors <em>Behaviors</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPBehaviorPatternImpl#getSpecification <em>Specification</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPBehaviorPatternImpl#getId <em>Id</em>}</li>
 * </ul>
 * @generated
 */
public class BPBehaviorPatternImpl extends MinimalEObjectImpl.Container implements BPBehaviorPattern {
    /**
     * The cached value of the '{@link #getBehaviors() <em>Behaviors</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBehaviors()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<BPBehavior> behaviors;

    /**
     * The cached value of the '{@link #getSpecification() <em>Specification</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSpecification()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected BPSpec specification;

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String id = ID_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPBehaviorPatternImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPPackage.Literals.BP_BEHAVIOR_PATTERN;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<BPBehavior> getBehaviors() {
        if (behaviors == null) {
            behaviors = new EObjectContainmentEList<BPBehavior>(BPBehavior.class, this, BPPackage.BP_BEHAVIOR_PATTERN__BEHAVIORS);
        }
        return behaviors;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSpec getSpecification() {
        if (specification != null && specification.eIsProxy()) {
            InternalEObject oldSpecification = (InternalEObject) specification;
            specification = (BPSpec) eResolveProxy(oldSpecification);
            if (specification != oldSpecification) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BPPackage.BP_BEHAVIOR_PATTERN__SPECIFICATION, oldSpecification, specification));
            }
        }
        return specification;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPSpec basicGetSpecification() {
        return specification;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSpecification(BPSpec newSpecification) {
        BPSpec oldSpecification = specification;
        specification = newSpecification;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_BEHAVIOR_PATTERN__SPECIFICATION, oldSpecification, specification));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_BEHAVIOR_PATTERN__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BPPackage.BP_BEHAVIOR_PATTERN__BEHAVIORS:
            return ((InternalEList<?>) getBehaviors()).basicRemove(otherEnd, msgs);
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
        case BPPackage.BP_BEHAVIOR_PATTERN__BEHAVIORS:
            return getBehaviors();
        case BPPackage.BP_BEHAVIOR_PATTERN__SPECIFICATION:
            if (resolve)
                return getSpecification();
            return basicGetSpecification();
        case BPPackage.BP_BEHAVIOR_PATTERN__ID:
            return getId();
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
        case BPPackage.BP_BEHAVIOR_PATTERN__BEHAVIORS:
            getBehaviors().clear();
            getBehaviors().addAll((Collection<? extends BPBehavior>) newValue);
            return;
        case BPPackage.BP_BEHAVIOR_PATTERN__SPECIFICATION:
            setSpecification((BPSpec) newValue);
            return;
        case BPPackage.BP_BEHAVIOR_PATTERN__ID:
            setId((String) newValue);
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
        case BPPackage.BP_BEHAVIOR_PATTERN__BEHAVIORS:
            getBehaviors().clear();
            return;
        case BPPackage.BP_BEHAVIOR_PATTERN__SPECIFICATION:
            setSpecification((BPSpec) null);
            return;
        case BPPackage.BP_BEHAVIOR_PATTERN__ID:
            setId(ID_EDEFAULT);
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
        case BPPackage.BP_BEHAVIOR_PATTERN__BEHAVIORS:
            return behaviors != null && !behaviors.isEmpty();
        case BPPackage.BP_BEHAVIOR_PATTERN__SPECIFICATION:
            return specification != null;
        case BPPackage.BP_BEHAVIOR_PATTERN__ID:
            return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (id: ");
        result.append(id);
        result.append(')');
        return result.toString();
    }

} // BPBehaviorPatternImpl
