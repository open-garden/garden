/**
 */
package com.zipc.garden.model.bp.impl;

import com.google.gwt.user.client.rpc.GwtTransient;
import com.zipc.garden.model.bp.BPPackage;
import com.zipc.garden.model.bp.BPRoot;
import com.zipc.garden.model.core.AbstractSearchCondition;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.core.SettingInterface;
import com.zipc.garden.model.core.impl.AbstractRootElementImpl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.impl.BPRootImpl#getMax <em>Max</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPRootImpl#getRowIds <em>Row Ids</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPRootImpl#getSettings <em>Settings</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPRootImpl#getAll <em>All</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPRootImpl#getBegin <em>Begin</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPRootImpl#getEnd <em>End</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPRootImpl#getStatus <em>Status</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.impl.BPRootImpl#getMessage <em>Message</em>}</li>
 * </ul>
 * @generated
 */
public class BPRootImpl extends AbstractRootElementImpl implements BPRoot {
    /**
     * The default value of the '{@link #getMax() <em>Max</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMax()
     * @generated
     * @ordered
     */
    protected static final int MAX_EDEFAULT = 100;

    /**
     * The cached value of the '{@link #getMax() <em>Max</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMax()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int max = MAX_EDEFAULT;

    /**
     * The default value of the '{@link #getRowIds() <em>Row Ids</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRowIds()
     * @generated
     * @ordered
     */
    protected static final String ROW_IDS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRowIds() <em>Row Ids</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRowIds()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String rowIds = ROW_IDS_EDEFAULT;

    /**
     * The cached value of the '{@link #getSettings() <em>Settings</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSettings()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<AbstractSetting> settings;

    /**
     * The default value of the '{@link #getAll() <em>All</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getAll()
     * @generated
     * @ordered
     */
    protected static final int ALL_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getAll() <em>All</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getAll()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int all = ALL_EDEFAULT;

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
     * The default value of the '{@link #getStatus() <em>Status</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getStatus()
     * @generated
     * @ordered
     */
    protected static final int STATUS_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getStatus()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int status = STATUS_EDEFAULT;

    /**
     * The default value of the '{@link #getMessage() <em>Message</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getMessage()
     * @generated
     * @ordered
     */
    protected static final String MESSAGE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMessage() <em>Message</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMessage()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String message = MESSAGE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPPackage.Literals.BP_ROOT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getAll() {
        return all;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAll(int newAll) {
        int oldAll = all;
        all = newAll;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_ROOT__ALL, oldAll, all));
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
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_ROOT__BEGIN, oldBegin, begin));
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
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_ROOT__END, oldEnd, end));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getStatus() {
        return status;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setStatus(int newStatus) {
        int oldStatus = status;
        status = newStatus;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_ROOT__STATUS, oldStatus, status));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMessage(String newMessage) {
        String oldMessage = message;
        message = newMessage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_ROOT__MESSAGE, oldMessage, message));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<AbstractSetting> getSettings() {
        if (settings == null) {
            settings = new EObjectContainmentEList<AbstractSetting>(AbstractSetting.class, this, BPPackage.BP_ROOT__SETTINGS);
        }
        return settings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getMax() {
        return max;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMax(int newMax) {
        int oldMax = max;
        max = newMax;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_ROOT__MAX, oldMax, max));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getRowIds() {
        return rowIds;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRowIds(String newRowIds) {
        String oldRowIds = rowIds;
        rowIds = newRowIds;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPPackage.BP_ROOT__ROW_IDS, oldRowIds, rowIds));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BPPackage.BP_ROOT__SETTINGS:
            return ((InternalEList<?>) getSettings()).basicRemove(otherEnd, msgs);
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
        case BPPackage.BP_ROOT__MAX:
            return getMax();
        case BPPackage.BP_ROOT__ROW_IDS:
            return getRowIds();
        case BPPackage.BP_ROOT__SETTINGS:
            return getSettings();
        case BPPackage.BP_ROOT__ALL:
            return getAll();
        case BPPackage.BP_ROOT__BEGIN:
            return getBegin();
        case BPPackage.BP_ROOT__END:
            return getEnd();
        case BPPackage.BP_ROOT__STATUS:
            return getStatus();
        case BPPackage.BP_ROOT__MESSAGE:
            return getMessage();
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
        case BPPackage.BP_ROOT__MAX:
            setMax((Integer) newValue);
            return;
        case BPPackage.BP_ROOT__ROW_IDS:
            setRowIds((String) newValue);
            return;
        case BPPackage.BP_ROOT__SETTINGS:
            getSettings().clear();
            getSettings().addAll((Collection<? extends AbstractSetting>) newValue);
            return;
        case BPPackage.BP_ROOT__ALL:
            setAll((Integer) newValue);
            return;
        case BPPackage.BP_ROOT__BEGIN:
            setBegin((Integer) newValue);
            return;
        case BPPackage.BP_ROOT__END:
            setEnd((Integer) newValue);
            return;
        case BPPackage.BP_ROOT__STATUS:
            setStatus((Integer) newValue);
            return;
        case BPPackage.BP_ROOT__MESSAGE:
            setMessage((String) newValue);
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
        case BPPackage.BP_ROOT__MAX:
            setMax(MAX_EDEFAULT);
            return;
        case BPPackage.BP_ROOT__ROW_IDS:
            setRowIds(ROW_IDS_EDEFAULT);
            return;
        case BPPackage.BP_ROOT__SETTINGS:
            getSettings().clear();
            return;
        case BPPackage.BP_ROOT__ALL:
            setAll(ALL_EDEFAULT);
            return;
        case BPPackage.BP_ROOT__BEGIN:
            setBegin(BEGIN_EDEFAULT);
            return;
        case BPPackage.BP_ROOT__END:
            setEnd(END_EDEFAULT);
            return;
        case BPPackage.BP_ROOT__STATUS:
            setStatus(STATUS_EDEFAULT);
            return;
        case BPPackage.BP_ROOT__MESSAGE:
            setMessage(MESSAGE_EDEFAULT);
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
        case BPPackage.BP_ROOT__MAX:
            return max != MAX_EDEFAULT;
        case BPPackage.BP_ROOT__ROW_IDS:
            return ROW_IDS_EDEFAULT == null ? rowIds != null : !ROW_IDS_EDEFAULT.equals(rowIds);
        case BPPackage.BP_ROOT__SETTINGS:
            return settings != null && !settings.isEmpty();
        case BPPackage.BP_ROOT__ALL:
            return all != ALL_EDEFAULT;
        case BPPackage.BP_ROOT__BEGIN:
            return begin != BEGIN_EDEFAULT;
        case BPPackage.BP_ROOT__END:
            return end != END_EDEFAULT;
        case BPPackage.BP_ROOT__STATUS:
            return status != STATUS_EDEFAULT;
        case BPPackage.BP_ROOT__MESSAGE:
            return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == AbstractSearchCondition.class) {
            switch (derivedFeatureID) {
            case BPPackage.BP_ROOT__MAX:
                return COREPackage.ABSTRACT_SEARCH_CONDITION__MAX;
            case BPPackage.BP_ROOT__ROW_IDS:
                return COREPackage.ABSTRACT_SEARCH_CONDITION__ROW_IDS;
            default:
                return -1;
            }
        }
        if (baseClass == SettingInterface.class) {
            switch (derivedFeatureID) {
            case BPPackage.BP_ROOT__SETTINGS:
                return COREPackage.SETTING_INTERFACE__SETTINGS;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == AbstractSearchCondition.class) {
            switch (baseFeatureID) {
            case COREPackage.ABSTRACT_SEARCH_CONDITION__MAX:
                return BPPackage.BP_ROOT__MAX;
            case COREPackage.ABSTRACT_SEARCH_CONDITION__ROW_IDS:
                return BPPackage.BP_ROOT__ROW_IDS;
            default:
                return -1;
            }
        }
        if (baseClass == SettingInterface.class) {
            switch (baseFeatureID) {
            case COREPackage.SETTING_INTERFACE__SETTINGS:
                return BPPackage.BP_ROOT__SETTINGS;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (max: ");
        result.append(max);
        result.append(", rowIds: ");
        result.append(rowIds);
        result.append(", all: ");
        result.append(all);
        result.append(", begin: ");
        result.append(begin);
        result.append(", end: ");
        result.append(end);
        result.append(", status: ");
        result.append(status);
        result.append(", message: ");
        result.append(message);
        result.append(')');
        return result.toString();
    }

} // BPRootImpl
