/**
 */
package com.zipc.garden.model.tps.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.tc.TCNode;

import com.zipc.garden.model.tps.NodeVariable;
import com.zipc.garden.model.tps.TPSPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Node Variable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tps.impl.NodeVariableImpl#getTcNode <em>Tc Node</em>}</li>
 * <li>{@link com.zipc.garden.model.tps.impl.NodeVariableImpl#getVariableName <em>Variable Name</em>}</li>
 * </ul>
 * @generated
 */
public class NodeVariableImpl extends MinimalEObjectImpl.Container implements NodeVariable {
    /**
     * The cached value of the '{@link #getTcNode() <em>Tc Node</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTcNode()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected TCNode tcNode;

    /**
     * The default value of the '{@link #getVariableName() <em>Variable Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getVariableName()
     * @generated
     * @ordered
     */
    protected static final String VARIABLE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVariableName() <em>Variable Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getVariableName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String variableName = VARIABLE_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected NodeVariableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TPSPackage.Literals.NODE_VARIABLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TCNode getTcNode() {
        if (tcNode != null && tcNode.eIsProxy()) {
            InternalEObject oldTcNode = (InternalEObject) tcNode;
            tcNode = (TCNode) eResolveProxy(oldTcNode);
            if (tcNode != oldTcNode) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TPSPackage.NODE_VARIABLE__TC_NODE, oldTcNode, tcNode));
            }
        }
        return tcNode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public TCNode basicGetTcNode() {
        return tcNode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTcNode(TCNode newTcNode) {
        TCNode oldTcNode = tcNode;
        tcNode = newTcNode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TPSPackage.NODE_VARIABLE__TC_NODE, oldTcNode, tcNode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getVariableName() {
        return variableName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setVariableName(String newVariableName) {
        String oldVariableName = variableName;
        variableName = newVariableName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TPSPackage.NODE_VARIABLE__VARIABLE_NAME, oldVariableName, variableName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case TPSPackage.NODE_VARIABLE__TC_NODE:
            if (resolve)
                return getTcNode();
            return basicGetTcNode();
        case TPSPackage.NODE_VARIABLE__VARIABLE_NAME:
            return getVariableName();
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
        case TPSPackage.NODE_VARIABLE__TC_NODE:
            setTcNode((TCNode) newValue);
            return;
        case TPSPackage.NODE_VARIABLE__VARIABLE_NAME:
            setVariableName((String) newValue);
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
        case TPSPackage.NODE_VARIABLE__TC_NODE:
            setTcNode((TCNode) null);
            return;
        case TPSPackage.NODE_VARIABLE__VARIABLE_NAME:
            setVariableName(VARIABLE_NAME_EDEFAULT);
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
        case TPSPackage.NODE_VARIABLE__TC_NODE:
            return tcNode != null;
        case TPSPackage.NODE_VARIABLE__VARIABLE_NAME:
            return VARIABLE_NAME_EDEFAULT == null ? variableName != null : !VARIABLE_NAME_EDEFAULT.equals(variableName);
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
        result.append(" (variableName: ");
        result.append(variableName);
        result.append(')');
        return result.toString();
    }

} // NodeVariableImpl
