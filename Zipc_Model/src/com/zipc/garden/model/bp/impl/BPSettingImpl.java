/**
 */
package com.zipc.garden.model.bp.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.bp.BPBehaviorPattern;
import com.zipc.garden.model.bp.BPPackage;
import com.zipc.garden.model.bp.BPSetting;
import com.zipc.garden.model.bp.BPStateMachine;

import com.zipc.garden.model.core.AbstractRootElement;
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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Setting</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.impl.BPSettingImpl#getUuid <em>Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPSettingImpl#getSettingHash <em>Setting Hash</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPSettingImpl#getPatternNos <em>Pattern Nos</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPSettingImpl#getBegin <em>Begin</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPSettingImpl#getEnd <em>End</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPSettingImpl#getCount <em>Count</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPSettingImpl#getAbstractRoot <em>Abstract Root</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPSettingImpl#getStateMachines <em>State Machines</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPSettingImpl#getPattern <em>Pattern</em>}</li>
 * </ul>
 * @generated
 */
public class BPSettingImpl extends MinimalEObjectImpl.Container implements BPSetting {
    /**
     * The default value of the '{@link #getUuid() <em>Uuid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getUuid()
     * @generated
     * @ordered
     */
    protected static final String UUID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUuid() <em>Uuid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getUuid()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String uuid = UUID_EDEFAULT;

    /**
     * The default value of the '{@link #getSettingHash() <em>Setting Hash</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSettingHash()
     * @generated
     * @ordered
     */
    protected static final String SETTING_HASH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSettingHash() <em>Setting Hash</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSettingHash()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String settingHash = SETTING_HASH_EDEFAULT;

    /**
     * The default value of the '{@link #getPatternNos() <em>Pattern Nos</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getPatternNos()
     * @generated
     * @ordered
     */
    protected static final String PATTERN_NOS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPatternNos() <em>Pattern Nos</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getPatternNos()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String patternNos = PATTERN_NOS_EDEFAULT;

    /**
     * The default value of the '{@link #getBegin() <em>Begin</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getBegin()
     * @generated
     * @ordered
     */
    protected static final int BEGIN_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getBegin() <em>Begin</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getBegin()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int begin = BEGIN_EDEFAULT;

    /**
     * The default value of the '{@link #getEnd() <em>End</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getEnd()
     * @generated
     * @ordered
     */
    protected static final int END_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getEnd() <em>End</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getEnd()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int end = END_EDEFAULT;

    /**
     * The default value of the '{@link #getCount() <em>Count</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getCount()
     * @generated
     * @ordered
     */
    protected static final int COUNT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getCount() <em>Count</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getCount()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int count = COUNT_EDEFAULT;

    /**
     * The cached value of the '{@link #getAbstractRoot() <em>Abstract Root</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getAbstractRoot()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected AbstractRootElement abstractRoot;

    /**
     * The cached value of the '{@link #getStateMachines() <em>State Machines</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see #getStateMachines()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<BPStateMachine> stateMachines;

    /**
     * The cached value of the '{@link #getPattern() <em>Pattern</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getPattern()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<BPBehaviorPattern> pattern;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPSettingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPPackage.Literals.BP_SETTING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getUuid() {
        return uuid;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setUuid(String newUuid) {
        String oldUuid = uuid;
        uuid = newUuid;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_SETTING__UUID, oldUuid, uuid));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getSettingHash() {
        return settingHash;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSettingHash(String newSettingHash) {
        String oldSettingHash = settingHash;
        settingHash = newSettingHash;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_SETTING__SETTING_HASH, oldSettingHash, settingHash));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getPatternNos() {
        return patternNos;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setPatternNos(String newPatternNos) {
        String oldPatternNos = patternNos;
        patternNos = newPatternNos;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_SETTING__PATTERN_NOS, oldPatternNos, patternNos));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getBegin() {
        return begin;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBegin(int newBegin) {
        int oldBegin = begin;
        begin = newBegin;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_SETTING__BEGIN, oldBegin, begin));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getEnd() {
        return end;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setEnd(int newEnd) {
        int oldEnd = end;
        end = newEnd;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_SETTING__END, oldEnd, end));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getCount() {
        return count;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCount(int newCount) {
        int oldCount = count;
        count = newCount;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_SETTING__COUNT, oldCount, count));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AbstractRootElement getAbstractRoot() {
        return abstractRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAbstractRoot(AbstractRootElement newAbstractRoot, NotificationChain msgs) {
        AbstractRootElement oldAbstractRoot = abstractRoot;
        abstractRoot = newAbstractRoot;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BPPackage.BP_SETTING__ABSTRACT_ROOT, oldAbstractRoot, newAbstractRoot);
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
    public void setAbstractRoot(AbstractRootElement newAbstractRoot) {
        if (newAbstractRoot != abstractRoot) {
            NotificationChain msgs = null;
            if (abstractRoot != null)
                msgs = ((InternalEObject) abstractRoot).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BPPackage.BP_SETTING__ABSTRACT_ROOT, null, msgs);
            if (newAbstractRoot != null)
                msgs = ((InternalEObject) newAbstractRoot).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BPPackage.BP_SETTING__ABSTRACT_ROOT, null, msgs);
            msgs = basicSetAbstractRoot(newAbstractRoot, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_SETTING__ABSTRACT_ROOT, newAbstractRoot, newAbstractRoot));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<BPStateMachine> getStateMachines() {
        if (stateMachines == null) {
            stateMachines = new EObjectContainmentEList<BPStateMachine>(BPStateMachine.class, this, BPPackage.BP_SETTING__STATE_MACHINES);
        }
        return stateMachines;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<BPBehaviorPattern> getPattern() {
        if (pattern == null) {
            pattern = new EObjectContainmentEList<BPBehaviorPattern>(BPBehaviorPattern.class, this, BPPackage.BP_SETTING__PATTERN);
        }
        return pattern;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BPPackage.BP_SETTING__ABSTRACT_ROOT:
            return basicSetAbstractRoot(null, msgs);
        case BPPackage.BP_SETTING__STATE_MACHINES:
            return ((InternalEList<?>) getStateMachines()).basicRemove(otherEnd, msgs);
        case BPPackage.BP_SETTING__PATTERN:
            return ((InternalEList<?>) getPattern()).basicRemove(otherEnd, msgs);
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
        case BPPackage.BP_SETTING__UUID:
            return getUuid();
        case BPPackage.BP_SETTING__SETTING_HASH:
            return getSettingHash();
        case BPPackage.BP_SETTING__PATTERN_NOS:
            return getPatternNos();
        case BPPackage.BP_SETTING__BEGIN:
            return getBegin();
        case BPPackage.BP_SETTING__END:
            return getEnd();
        case BPPackage.BP_SETTING__COUNT:
            return getCount();
        case BPPackage.BP_SETTING__ABSTRACT_ROOT:
            return getAbstractRoot();
        case BPPackage.BP_SETTING__STATE_MACHINES:
            return getStateMachines();
        case BPPackage.BP_SETTING__PATTERN:
            return getPattern();
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
        case BPPackage.BP_SETTING__UUID:
            setUuid((String) newValue);
            return;
        case BPPackage.BP_SETTING__SETTING_HASH:
            setSettingHash((String) newValue);
            return;
        case BPPackage.BP_SETTING__PATTERN_NOS:
            setPatternNos((String) newValue);
            return;
        case BPPackage.BP_SETTING__BEGIN:
            setBegin((Integer) newValue);
            return;
        case BPPackage.BP_SETTING__END:
            setEnd((Integer) newValue);
            return;
        case BPPackage.BP_SETTING__COUNT:
            setCount((Integer) newValue);
            return;
        case BPPackage.BP_SETTING__ABSTRACT_ROOT:
            setAbstractRoot((AbstractRootElement) newValue);
            return;
        case BPPackage.BP_SETTING__STATE_MACHINES:
            getStateMachines().clear();
            getStateMachines().addAll((Collection<? extends BPStateMachine>) newValue);
            return;
        case BPPackage.BP_SETTING__PATTERN:
            getPattern().clear();
            getPattern().addAll((Collection<? extends BPBehaviorPattern>) newValue);
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
        case BPPackage.BP_SETTING__UUID:
            setUuid(UUID_EDEFAULT);
            return;
        case BPPackage.BP_SETTING__SETTING_HASH:
            setSettingHash(SETTING_HASH_EDEFAULT);
            return;
        case BPPackage.BP_SETTING__PATTERN_NOS:
            setPatternNos(PATTERN_NOS_EDEFAULT);
            return;
        case BPPackage.BP_SETTING__BEGIN:
            setBegin(BEGIN_EDEFAULT);
            return;
        case BPPackage.BP_SETTING__END:
            setEnd(END_EDEFAULT);
            return;
        case BPPackage.BP_SETTING__COUNT:
            setCount(COUNT_EDEFAULT);
            return;
        case BPPackage.BP_SETTING__ABSTRACT_ROOT:
            setAbstractRoot((AbstractRootElement) null);
            return;
        case BPPackage.BP_SETTING__STATE_MACHINES:
            getStateMachines().clear();
            return;
        case BPPackage.BP_SETTING__PATTERN:
            getPattern().clear();
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
        case BPPackage.BP_SETTING__UUID:
            return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
        case BPPackage.BP_SETTING__SETTING_HASH:
            return SETTING_HASH_EDEFAULT == null ? settingHash != null : !SETTING_HASH_EDEFAULT.equals(settingHash);
        case BPPackage.BP_SETTING__PATTERN_NOS:
            return PATTERN_NOS_EDEFAULT == null ? patternNos != null : !PATTERN_NOS_EDEFAULT.equals(patternNos);
        case BPPackage.BP_SETTING__BEGIN:
            return begin != BEGIN_EDEFAULT;
        case BPPackage.BP_SETTING__END:
            return end != END_EDEFAULT;
        case BPPackage.BP_SETTING__COUNT:
            return count != COUNT_EDEFAULT;
        case BPPackage.BP_SETTING__ABSTRACT_ROOT:
            return abstractRoot != null;
        case BPPackage.BP_SETTING__STATE_MACHINES:
            return stateMachines != null && !stateMachines.isEmpty();
        case BPPackage.BP_SETTING__PATTERN:
            return pattern != null && !pattern.isEmpty();
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
        result.append(" (uuid: ");
        result.append(uuid);
        result.append(", settingHash: ");
        result.append(settingHash);
        result.append(", patternNos: ");
        result.append(patternNos);
        result.append(", begin: ");
        result.append(begin);
        result.append(", end: ");
        result.append(end);
        result.append(", count: ");
        result.append(count);
        result.append(')');
        return result.toString();
    }

} // BPSettingImpl
