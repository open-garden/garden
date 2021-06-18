/**
 */
package com.zipc.garden.model.tp.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.tp.TPElement;
import com.zipc.garden.model.tp.TPPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tp.impl.TPElementImpl#getFullPath <em>Full Path</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPElementImpl#getSimplePath <em>Simple Path</em>}</li>
 * </ul>
 * @generated
 */
public class TPElementImpl extends MinimalEObjectImpl.Container implements TPElement {
    /**
     * The default value of the '{@link #getFullPath() <em>Full Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getFullPath()
     * @generated
     * @ordered
     */
    protected static final String FULL_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFullPath() <em>Full Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getFullPath()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String fullPath = FULL_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getSimplePath() <em>Simple Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSimplePath()
     * @generated
     * @ordered
     */
    protected static final String SIMPLE_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSimplePath() <em>Simple Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSimplePath()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String simplePath = SIMPLE_PATH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected TPElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TPPackage.Literals.TP_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getFullPath() {
        return fullPath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFullPath(String newFullPath) {
        String oldFullPath = fullPath;
        fullPath = newFullPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TPPackage.TP_ELEMENT__FULL_PATH, oldFullPath, fullPath));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getSimplePath() {
        return simplePath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSimplePath(String newSimplePath) {
        String oldSimplePath = simplePath;
        simplePath = newSimplePath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TPPackage.TP_ELEMENT__SIMPLE_PATH, oldSimplePath, simplePath));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case TPPackage.TP_ELEMENT__FULL_PATH:
            return getFullPath();
        case TPPackage.TP_ELEMENT__SIMPLE_PATH:
            return getSimplePath();
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
        case TPPackage.TP_ELEMENT__FULL_PATH:
            setFullPath((String) newValue);
            return;
        case TPPackage.TP_ELEMENT__SIMPLE_PATH:
            setSimplePath((String) newValue);
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
        case TPPackage.TP_ELEMENT__FULL_PATH:
            setFullPath(FULL_PATH_EDEFAULT);
            return;
        case TPPackage.TP_ELEMENT__SIMPLE_PATH:
            setSimplePath(SIMPLE_PATH_EDEFAULT);
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
        case TPPackage.TP_ELEMENT__FULL_PATH:
            return FULL_PATH_EDEFAULT == null ? fullPath != null : !FULL_PATH_EDEFAULT.equals(fullPath);
        case TPPackage.TP_ELEMENT__SIMPLE_PATH:
            return SIMPLE_PATH_EDEFAULT == null ? simplePath != null : !SIMPLE_PATH_EDEFAULT.equals(simplePath);
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
        result.append(" (fullPath: ");
        result.append(fullPath);
        result.append(", simplePath: ");
        result.append(simplePath);
        result.append(')');
        return result.toString();
    }

} // TPElementImpl
