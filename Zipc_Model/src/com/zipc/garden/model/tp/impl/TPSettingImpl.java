/**
 */
package com.zipc.garden.model.tp.impl;

import com.google.gwt.user.client.rpc.GwtTransient;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.tp.PathType;
import com.zipc.garden.model.tp.TPElement;
import com.zipc.garden.model.tp.TPPackage;
import com.zipc.garden.model.tp.TPPatternElement;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tp.TPTSDPattern;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Setting</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tp.impl.TPSettingImpl#getUuid <em>Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPSettingImpl#getSettingHash <em>Setting Hash</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPSettingImpl#getPatternNos <em>Pattern Nos</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPSettingImpl#getBegin <em>Begin</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPSettingImpl#getEnd <em>End</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPSettingImpl#getCount <em>Count</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPSettingImpl#getAbstractRoot <em>Abstract Root</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPSettingImpl#getHeader <em>Header</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPSettingImpl#getTitle <em>Title</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPSettingImpl#getElements <em>Elements</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPSettingImpl#getPatterns <em>Patterns</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPSettingImpl#getPatternElements <em>Pattern Elements</em>}</li>
 * </ul>
 * @generated
 */
public class TPSettingImpl extends MinimalEObjectImpl.Container implements TPSetting {
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
     * The cached value of the '{@link #getHeader() <em>Header</em>}' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getHeader()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<String> header;

    /**
     * The default value of the '{@link #getTitle() <em>Title</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTitle()
     * @generated
     * @ordered
     */
    protected static final PathType TITLE_EDEFAULT = PathType.SIMPLE_PATH;

    /**
     * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTitle()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected PathType title = TITLE_EDEFAULT;

    /**
     * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getElements()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<TPElement> elements;

    /**
     * The cached value of the '{@link #getPatterns() <em>Patterns</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPatterns()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<TPTSDPattern> patterns;

    /**
     * The cached value of the '{@link #getPatternElements() <em>Pattern Elements</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see #getPatternElements()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<TPPatternElement> patternElements;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected TPSettingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TPPackage.Literals.TP_SETTING;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TPPackage.TP_SETTING__UUID, oldUuid, uuid));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TPPackage.TP_SETTING__SETTING_HASH, oldSettingHash, settingHash));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TPPackage.TP_SETTING__PATTERN_NOS, oldPatternNos, patternNos));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TPPackage.TP_SETTING__BEGIN, oldBegin, begin));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TPPackage.TP_SETTING__END, oldEnd, end));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TPPackage.TP_SETTING__COUNT, oldCount, count));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TPPackage.TP_SETTING__ABSTRACT_ROOT, oldAbstractRoot, newAbstractRoot);
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
                msgs = ((InternalEObject) abstractRoot).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TPPackage.TP_SETTING__ABSTRACT_ROOT, null, msgs);
            if (newAbstractRoot != null)
                msgs = ((InternalEObject) newAbstractRoot).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TPPackage.TP_SETTING__ABSTRACT_ROOT, null, msgs);
            msgs = basicSetAbstractRoot(newAbstractRoot, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TPPackage.TP_SETTING__ABSTRACT_ROOT, newAbstractRoot, newAbstractRoot));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<String> getHeader() {
        if (header == null) {
            header = new EDataTypeUniqueEList<String>(String.class, this, TPPackage.TP_SETTING__HEADER);
        }
        return header;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public PathType getTitle() {
        return title;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTitle(PathType newTitle) {
        PathType oldTitle = title;
        title = newTitle == null ? TITLE_EDEFAULT : newTitle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TPPackage.TP_SETTING__TITLE, oldTitle, title));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TPElement> getElements() {
        if (elements == null) {
            elements = new EObjectContainmentEList<TPElement>(TPElement.class, this, TPPackage.TP_SETTING__ELEMENTS);
        }
        return elements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TPTSDPattern> getPatterns() {
        if (patterns == null) {
            patterns = new EObjectContainmentEList<TPTSDPattern>(TPTSDPattern.class, this, TPPackage.TP_SETTING__PATTERNS);
        }
        return patterns;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TPPatternElement> getPatternElements() {
        if (patternElements == null) {
            patternElements = new EObjectContainmentEList<TPPatternElement>(TPPatternElement.class, this, TPPackage.TP_SETTING__PATTERN_ELEMENTS);
        }
        return patternElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TPPackage.TP_SETTING__ABSTRACT_ROOT:
            return basicSetAbstractRoot(null, msgs);
        case TPPackage.TP_SETTING__ELEMENTS:
            return ((InternalEList<?>) getElements()).basicRemove(otherEnd, msgs);
        case TPPackage.TP_SETTING__PATTERNS:
            return ((InternalEList<?>) getPatterns()).basicRemove(otherEnd, msgs);
        case TPPackage.TP_SETTING__PATTERN_ELEMENTS:
            return ((InternalEList<?>) getPatternElements()).basicRemove(otherEnd, msgs);
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
        case TPPackage.TP_SETTING__UUID:
            return getUuid();
        case TPPackage.TP_SETTING__SETTING_HASH:
            return getSettingHash();
        case TPPackage.TP_SETTING__PATTERN_NOS:
            return getPatternNos();
        case TPPackage.TP_SETTING__BEGIN:
            return getBegin();
        case TPPackage.TP_SETTING__END:
            return getEnd();
        case TPPackage.TP_SETTING__COUNT:
            return getCount();
        case TPPackage.TP_SETTING__ABSTRACT_ROOT:
            return getAbstractRoot();
        case TPPackage.TP_SETTING__HEADER:
            return getHeader();
        case TPPackage.TP_SETTING__TITLE:
            return getTitle();
        case TPPackage.TP_SETTING__ELEMENTS:
            return getElements();
        case TPPackage.TP_SETTING__PATTERNS:
            return getPatterns();
        case TPPackage.TP_SETTING__PATTERN_ELEMENTS:
            return getPatternElements();
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
        case TPPackage.TP_SETTING__UUID:
            setUuid((String) newValue);
            return;
        case TPPackage.TP_SETTING__SETTING_HASH:
            setSettingHash((String) newValue);
            return;
        case TPPackage.TP_SETTING__PATTERN_NOS:
            setPatternNos((String) newValue);
            return;
        case TPPackage.TP_SETTING__BEGIN:
            setBegin((Integer) newValue);
            return;
        case TPPackage.TP_SETTING__END:
            setEnd((Integer) newValue);
            return;
        case TPPackage.TP_SETTING__COUNT:
            setCount((Integer) newValue);
            return;
        case TPPackage.TP_SETTING__ABSTRACT_ROOT:
            setAbstractRoot((AbstractRootElement) newValue);
            return;
        case TPPackage.TP_SETTING__HEADER:
            getHeader().clear();
            getHeader().addAll((Collection<? extends String>) newValue);
            return;
        case TPPackage.TP_SETTING__TITLE:
            setTitle((PathType) newValue);
            return;
        case TPPackage.TP_SETTING__ELEMENTS:
            getElements().clear();
            getElements().addAll((Collection<? extends TPElement>) newValue);
            return;
        case TPPackage.TP_SETTING__PATTERNS:
            getPatterns().clear();
            getPatterns().addAll((Collection<? extends TPTSDPattern>) newValue);
            return;
        case TPPackage.TP_SETTING__PATTERN_ELEMENTS:
            getPatternElements().clear();
            getPatternElements().addAll((Collection<? extends TPPatternElement>) newValue);
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
        case TPPackage.TP_SETTING__UUID:
            setUuid(UUID_EDEFAULT);
            return;
        case TPPackage.TP_SETTING__SETTING_HASH:
            setSettingHash(SETTING_HASH_EDEFAULT);
            return;
        case TPPackage.TP_SETTING__PATTERN_NOS:
            setPatternNos(PATTERN_NOS_EDEFAULT);
            return;
        case TPPackage.TP_SETTING__BEGIN:
            setBegin(BEGIN_EDEFAULT);
            return;
        case TPPackage.TP_SETTING__END:
            setEnd(END_EDEFAULT);
            return;
        case TPPackage.TP_SETTING__COUNT:
            setCount(COUNT_EDEFAULT);
            return;
        case TPPackage.TP_SETTING__ABSTRACT_ROOT:
            setAbstractRoot((AbstractRootElement) null);
            return;
        case TPPackage.TP_SETTING__HEADER:
            getHeader().clear();
            return;
        case TPPackage.TP_SETTING__TITLE:
            setTitle(TITLE_EDEFAULT);
            return;
        case TPPackage.TP_SETTING__ELEMENTS:
            getElements().clear();
            return;
        case TPPackage.TP_SETTING__PATTERNS:
            getPatterns().clear();
            return;
        case TPPackage.TP_SETTING__PATTERN_ELEMENTS:
            getPatternElements().clear();
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
        case TPPackage.TP_SETTING__UUID:
            return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
        case TPPackage.TP_SETTING__SETTING_HASH:
            return SETTING_HASH_EDEFAULT == null ? settingHash != null : !SETTING_HASH_EDEFAULT.equals(settingHash);
        case TPPackage.TP_SETTING__PATTERN_NOS:
            return PATTERN_NOS_EDEFAULT == null ? patternNos != null : !PATTERN_NOS_EDEFAULT.equals(patternNos);
        case TPPackage.TP_SETTING__BEGIN:
            return begin != BEGIN_EDEFAULT;
        case TPPackage.TP_SETTING__END:
            return end != END_EDEFAULT;
        case TPPackage.TP_SETTING__COUNT:
            return count != COUNT_EDEFAULT;
        case TPPackage.TP_SETTING__ABSTRACT_ROOT:
            return abstractRoot != null;
        case TPPackage.TP_SETTING__HEADER:
            return header != null && !header.isEmpty();
        case TPPackage.TP_SETTING__TITLE:
            return title != TITLE_EDEFAULT;
        case TPPackage.TP_SETTING__ELEMENTS:
            return elements != null && !elements.isEmpty();
        case TPPackage.TP_SETTING__PATTERNS:
            return patterns != null && !patterns.isEmpty();
        case TPPackage.TP_SETTING__PATTERN_ELEMENTS:
            return patternElements != null && !patternElements.isEmpty();
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
        result.append(", header: ");
        result.append(header);
        result.append(", title: ");
        result.append(title);
        result.append(')');
        return result.toString();
    }

} // TPSettingImpl
