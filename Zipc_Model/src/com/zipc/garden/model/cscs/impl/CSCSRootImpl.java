/**
 */
package com.zipc.garden.model.cscs.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.impl.AbstractRootElementImpl;

import com.zipc.garden.model.cscs.CSCSPackage;
import com.zipc.garden.model.cscs.CSCSPattern;
import com.zipc.garden.model.cscs.CSCSRoot;

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
 * <li>{@link com.zipc.garden.model.cscs.impl.CSCSRootImpl#getAll <em>All</em>}</li>
 * <li>{@link com.zipc.garden.model.cscs.impl.CSCSRootImpl#getBegin <em>Begin</em>}</li>
 * <li>{@link com.zipc.garden.model.cscs.impl.CSCSRootImpl#getEnd <em>End</em>}</li>
 * <li>{@link com.zipc.garden.model.cscs.impl.CSCSRootImpl#getStatus <em>Status</em>}</li>
 * <li>{@link com.zipc.garden.model.cscs.impl.CSCSRootImpl#getMessage <em>Message</em>}</li>
 * <li>{@link com.zipc.garden.model.cscs.impl.CSCSRootImpl#getPatterns <em>Patterns</em>}</li>
 * </ul>
 * @generated
 */
public class CSCSRootImpl extends AbstractRootElementImpl implements CSCSRoot {
    /**
     * The default value of the '{@link #getAll() <em>All</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getAll()
     * @generated
     * @ordered
     */
    protected static final double ALL_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getAll() <em>All</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getAll()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected double all = ALL_EDEFAULT;

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
     * The cached value of the '{@link #getPatterns() <em>Patterns</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPatterns()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<CSCSPattern> patterns;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected CSCSRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CSCSPackage.Literals.CSCS_ROOT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public double getAll() {
        return all;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAll(double newAll) {
        double oldAll = all;
        all = newAll;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CSCSPackage.CSCS_ROOT__ALL, oldAll, all));
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
            eNotify(new ENotificationImpl(this, Notification.SET, CSCSPackage.CSCS_ROOT__BEGIN, oldBegin, begin));
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
            eNotify(new ENotificationImpl(this, Notification.SET, CSCSPackage.CSCS_ROOT__END, oldEnd, end));
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
            eNotify(new ENotificationImpl(this, Notification.SET, CSCSPackage.CSCS_ROOT__STATUS, oldStatus, status));
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
            eNotify(new ENotificationImpl(this, Notification.SET, CSCSPackage.CSCS_ROOT__MESSAGE, oldMessage, message));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<CSCSPattern> getPatterns() {
        if (patterns == null) {
            patterns = new EObjectContainmentEList<CSCSPattern>(CSCSPattern.class, this, CSCSPackage.CSCS_ROOT__PATTERNS);
        }
        return patterns;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case CSCSPackage.CSCS_ROOT__PATTERNS:
            return ((InternalEList<?>) getPatterns()).basicRemove(otherEnd, msgs);
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
        case CSCSPackage.CSCS_ROOT__ALL:
            return getAll();
        case CSCSPackage.CSCS_ROOT__BEGIN:
            return getBegin();
        case CSCSPackage.CSCS_ROOT__END:
            return getEnd();
        case CSCSPackage.CSCS_ROOT__STATUS:
            return getStatus();
        case CSCSPackage.CSCS_ROOT__MESSAGE:
            return getMessage();
        case CSCSPackage.CSCS_ROOT__PATTERNS:
            return getPatterns();
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
        case CSCSPackage.CSCS_ROOT__ALL:
            setAll((Double) newValue);
            return;
        case CSCSPackage.CSCS_ROOT__BEGIN:
            setBegin((Integer) newValue);
            return;
        case CSCSPackage.CSCS_ROOT__END:
            setEnd((Integer) newValue);
            return;
        case CSCSPackage.CSCS_ROOT__STATUS:
            setStatus((Integer) newValue);
            return;
        case CSCSPackage.CSCS_ROOT__MESSAGE:
            setMessage((String) newValue);
            return;
        case CSCSPackage.CSCS_ROOT__PATTERNS:
            getPatterns().clear();
            getPatterns().addAll((Collection<? extends CSCSPattern>) newValue);
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
        case CSCSPackage.CSCS_ROOT__ALL:
            setAll(ALL_EDEFAULT);
            return;
        case CSCSPackage.CSCS_ROOT__BEGIN:
            setBegin(BEGIN_EDEFAULT);
            return;
        case CSCSPackage.CSCS_ROOT__END:
            setEnd(END_EDEFAULT);
            return;
        case CSCSPackage.CSCS_ROOT__STATUS:
            setStatus(STATUS_EDEFAULT);
            return;
        case CSCSPackage.CSCS_ROOT__MESSAGE:
            setMessage(MESSAGE_EDEFAULT);
            return;
        case CSCSPackage.CSCS_ROOT__PATTERNS:
            getPatterns().clear();
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
        case CSCSPackage.CSCS_ROOT__ALL:
            return all != ALL_EDEFAULT;
        case CSCSPackage.CSCS_ROOT__BEGIN:
            return begin != BEGIN_EDEFAULT;
        case CSCSPackage.CSCS_ROOT__END:
            return end != END_EDEFAULT;
        case CSCSPackage.CSCS_ROOT__STATUS:
            return status != STATUS_EDEFAULT;
        case CSCSPackage.CSCS_ROOT__MESSAGE:
            return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
        case CSCSPackage.CSCS_ROOT__PATTERNS:
            return patterns != null && !patterns.isEmpty();
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
        result.append(" (all: ");
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

} // CSCSRootImpl
