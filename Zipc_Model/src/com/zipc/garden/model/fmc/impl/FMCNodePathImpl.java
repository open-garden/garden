/**
 */
package com.zipc.garden.model.fmc.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.fmc.FMCNodePath;
import com.zipc.garden.model.fmc.FMCPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Node Path</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmc.impl.FMCNodePathImpl#getFullpath <em>Fullpath</em>}</li>
 * <li>{@link com.zipc.garden.model.fmc.impl.FMCNodePathImpl#getSimplePath <em>Simple Path</em>}</li>
 * <li>{@link com.zipc.garden.model.fmc.impl.FMCNodePathImpl#getNodeName <em>Node Name</em>}</li>
 * </ul>
 * @generated
 */
public class FMCNodePathImpl extends MinimalEObjectImpl.Container implements FMCNodePath {
    /**
     * The default value of the '{@link #getFullpath() <em>Fullpath</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getFullpath()
     * @generated
     * @ordered
     */
    protected static final String FULLPATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFullpath() <em>Fullpath</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getFullpath()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String fullpath = FULLPATH_EDEFAULT;

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
     * The default value of the '{@link #getNodeName() <em>Node Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getNodeName()
     * @generated
     * @ordered
     */
    protected static final String NODE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getNodeName() <em>Node Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getNodeName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String nodeName = NODE_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FMCNodePathImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FMCPackage.Literals.FMC_NODE_PATH;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getFullpath() {
        return fullpath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFullpath(String newFullpath) {
        String oldFullpath = fullpath;
        fullpath = newFullpath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMCPackage.FMC_NODE_PATH__FULLPATH, oldFullpath, fullpath));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FMCPackage.FMC_NODE_PATH__SIMPLE_PATH, oldSimplePath, simplePath));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getNodeName() {
        return nodeName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setNodeName(String newNodeName) {
        String oldNodeName = nodeName;
        nodeName = newNodeName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMCPackage.FMC_NODE_PATH__NODE_NAME, oldNodeName, nodeName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case FMCPackage.FMC_NODE_PATH__FULLPATH:
            return getFullpath();
        case FMCPackage.FMC_NODE_PATH__SIMPLE_PATH:
            return getSimplePath();
        case FMCPackage.FMC_NODE_PATH__NODE_NAME:
            return getNodeName();
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
        case FMCPackage.FMC_NODE_PATH__FULLPATH:
            setFullpath((String) newValue);
            return;
        case FMCPackage.FMC_NODE_PATH__SIMPLE_PATH:
            setSimplePath((String) newValue);
            return;
        case FMCPackage.FMC_NODE_PATH__NODE_NAME:
            setNodeName((String) newValue);
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
        case FMCPackage.FMC_NODE_PATH__FULLPATH:
            setFullpath(FULLPATH_EDEFAULT);
            return;
        case FMCPackage.FMC_NODE_PATH__SIMPLE_PATH:
            setSimplePath(SIMPLE_PATH_EDEFAULT);
            return;
        case FMCPackage.FMC_NODE_PATH__NODE_NAME:
            setNodeName(NODE_NAME_EDEFAULT);
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
        case FMCPackage.FMC_NODE_PATH__FULLPATH:
            return FULLPATH_EDEFAULT == null ? fullpath != null : !FULLPATH_EDEFAULT.equals(fullpath);
        case FMCPackage.FMC_NODE_PATH__SIMPLE_PATH:
            return SIMPLE_PATH_EDEFAULT == null ? simplePath != null : !SIMPLE_PATH_EDEFAULT.equals(simplePath);
        case FMCPackage.FMC_NODE_PATH__NODE_NAME:
            return NODE_NAME_EDEFAULT == null ? nodeName != null : !NODE_NAME_EDEFAULT.equals(nodeName);
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
        result.append(" (fullpath: ");
        result.append(fullpath);
        result.append(", simplePath: ");
        result.append(simplePath);
        result.append(", nodeName: ");
        result.append(nodeName);
        result.append(')');
        return result.toString();
    }

} // FMCNodePathImpl
