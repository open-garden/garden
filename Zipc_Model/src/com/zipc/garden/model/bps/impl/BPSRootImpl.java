/**
 */
package com.zipc.garden.model.bps.impl;

import com.google.gwt.user.client.rpc.GwtTransient;
import com.zipc.garden.model.bps.BPSInstanceArc;
import com.zipc.garden.model.bps.BPSOption;
import com.zipc.garden.model.bps.BPSPackage;
import com.zipc.garden.model.bps.BPSRoot;

import com.zipc.garden.model.core.impl.AbstractRootElementImpl;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.InvocationTargetException;
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
 * <li>{@link com.zipc.garden.model.bps.impl.BPSRootImpl#getActiveOptionIndex <em>Active Option Index</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSRootImpl#getOptions <em>Options</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.impl.BPSRootImpl#getInstanceArc <em>Instance Arc</em>}</li>
 * </ul>
 * @generated
 */
public class BPSRootImpl extends AbstractRootElementImpl implements BPSRoot {
    /**
     * The default value of the '{@link #getActiveOptionIndex() <em>Active Option Index</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getActiveOptionIndex()
     * @generated
     * @ordered
     */
    protected static final int ACTIVE_OPTION_INDEX_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getActiveOptionIndex() <em>Active Option Index</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActiveOptionIndex()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int activeOptionIndex = ACTIVE_OPTION_INDEX_EDEFAULT;

    /**
     * The cached value of the '{@link #getOptions() <em>Options</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getOptions()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<BPSOption> options;

    /**
     * The cached value of the '{@link #getInstanceArc() <em>Instance Arc</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInstanceArc()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected BPSInstanceArc instanceArc;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPSRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPSPackage.Literals.BPS_ROOT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSOption getActiveOption() {
        return getOptions().get(getActiveOptionIndex());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getActiveOptionIndex() {
        return activeOptionIndex;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setActiveOptionIndex(int newActiveOptionIndex) {
        int oldActiveOptionIndex = activeOptionIndex;
        activeOptionIndex = newActiveOptionIndex;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_ROOT__ACTIVE_OPTION_INDEX, oldActiveOptionIndex, activeOptionIndex));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<BPSOption> getOptions() {
        if (options == null) {
            options = new EObjectContainmentEList<BPSOption>(BPSOption.class, this, BPSPackage.BPS_ROOT__OPTIONS);
        }
        return options;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSInstanceArc getInstanceArc() {
        return instanceArc;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInstanceArc(BPSInstanceArc newInstanceArc, NotificationChain msgs) {
        BPSInstanceArc oldInstanceArc = instanceArc;
        instanceArc = newInstanceArc;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_ROOT__INSTANCE_ARC, oldInstanceArc, newInstanceArc);
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
    public void setInstanceArc(BPSInstanceArc newInstanceArc) {
        if (newInstanceArc != instanceArc) {
            NotificationChain msgs = null;
            if (instanceArc != null)
                msgs = ((InternalEObject) instanceArc).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BPSPackage.BPS_ROOT__INSTANCE_ARC, null, msgs);
            if (newInstanceArc != null)
                msgs = ((InternalEObject) newInstanceArc).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BPSPackage.BPS_ROOT__INSTANCE_ARC, null, msgs);
            msgs = basicSetInstanceArc(newInstanceArc, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BPSPackage.BPS_ROOT__INSTANCE_ARC, newInstanceArc, newInstanceArc));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BPSPackage.BPS_ROOT__OPTIONS:
            return ((InternalEList<?>) getOptions()).basicRemove(otherEnd, msgs);
        case BPSPackage.BPS_ROOT__INSTANCE_ARC:
            return basicSetInstanceArc(null, msgs);
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
        case BPSPackage.BPS_ROOT__ACTIVE_OPTION_INDEX:
            return getActiveOptionIndex();
        case BPSPackage.BPS_ROOT__OPTIONS:
            return getOptions();
        case BPSPackage.BPS_ROOT__INSTANCE_ARC:
            return getInstanceArc();
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
        case BPSPackage.BPS_ROOT__ACTIVE_OPTION_INDEX:
            setActiveOptionIndex((Integer) newValue);
            return;
        case BPSPackage.BPS_ROOT__OPTIONS:
            getOptions().clear();
            getOptions().addAll((Collection<? extends BPSOption>) newValue);
            return;
        case BPSPackage.BPS_ROOT__INSTANCE_ARC:
            setInstanceArc((BPSInstanceArc) newValue);
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
        case BPSPackage.BPS_ROOT__ACTIVE_OPTION_INDEX:
            setActiveOptionIndex(ACTIVE_OPTION_INDEX_EDEFAULT);
            return;
        case BPSPackage.BPS_ROOT__OPTIONS:
            getOptions().clear();
            return;
        case BPSPackage.BPS_ROOT__INSTANCE_ARC:
            setInstanceArc((BPSInstanceArc) null);
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
        case BPSPackage.BPS_ROOT__ACTIVE_OPTION_INDEX:
            return activeOptionIndex != ACTIVE_OPTION_INDEX_EDEFAULT;
        case BPSPackage.BPS_ROOT__OPTIONS:
            return options != null && !options.isEmpty();
        case BPSPackage.BPS_ROOT__INSTANCE_ARC:
            return instanceArc != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
        switch (operationID) {
        case BPSPackage.BPS_ROOT___GET_ACTIVE_OPTION:
            return getActiveOption();
        }
        return super.eInvoke(operationID, arguments);
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
        result.append(" (activeOptionIndex: ");
        result.append(activeOptionIndex);
        result.append(')');
        return result.toString();
    }

} // BPSRootImpl
