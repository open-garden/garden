/**
 */
package com.zipc.garden.model.fmcs.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.fm.FMNode;

import com.zipc.garden.model.fmcs.FMCSODElement;
import com.zipc.garden.model.fmcs.FMCSPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>OD Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.impl.FMCSODElementImpl#getFullName <em>Full Name</em>}</li>
 * <li>{@link com.zipc.garden.model.fmcs.impl.FMCSODElementImpl#getNode <em>Node</em>}</li>
 * </ul>
 * @generated
 */
public class FMCSODElementImpl extends MinimalEObjectImpl.Container implements FMCSODElement {
    /**
     * The default value of the '{@link #getFullName() <em>Full Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getFullName()
     * @generated
     * @ordered
     */
    protected static final String FULL_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFullName() <em>Full Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getFullName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String fullName = FULL_NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getNode() <em>Node</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getNode()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected FMNode node;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FMCSODElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FMCSPackage.Literals.FMCS_OD_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMNode getNode() {
        if (node != null && node.eIsProxy()) {
            InternalEObject oldNode = (InternalEObject) node;
            node = (FMNode) eResolveProxy(oldNode);
            if (node != oldNode) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FMCSPackage.FMCS_OD_ELEMENT__NODE, oldNode, node));
            }
        }
        return node;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FMNode basicGetNode() {
        return node;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setNode(FMNode newNode) {
        FMNode oldNode = node;
        node = newNode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_OD_ELEMENT__NODE, oldNode, node));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getFullName() {
        return fullName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFullName(String newFullName) {
        String oldFullName = fullName;
        fullName = newFullName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMCSPackage.FMCS_OD_ELEMENT__FULL_NAME, oldFullName, fullName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case FMCSPackage.FMCS_OD_ELEMENT__FULL_NAME:
            return getFullName();
        case FMCSPackage.FMCS_OD_ELEMENT__NODE:
            if (resolve)
                return getNode();
            return basicGetNode();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case FMCSPackage.FMCS_OD_ELEMENT__FULL_NAME:
            setFullName((String) newValue);
            return;
        case FMCSPackage.FMCS_OD_ELEMENT__NODE:
            setNode((FMNode) newValue);
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
        case FMCSPackage.FMCS_OD_ELEMENT__FULL_NAME:
            setFullName(FULL_NAME_EDEFAULT);
            return;
        case FMCSPackage.FMCS_OD_ELEMENT__NODE:
            setNode((FMNode) null);
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
        case FMCSPackage.FMCS_OD_ELEMENT__FULL_NAME:
            return FULL_NAME_EDEFAULT == null ? fullName != null : !FULL_NAME_EDEFAULT.equals(fullName);
        case FMCSPackage.FMCS_OD_ELEMENT__NODE:
            return node != null;
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
        result.append(" (fullName: ");
        result.append(fullName);
        result.append(')');
        return result.toString();
    }

} // FMCSODElementImpl
