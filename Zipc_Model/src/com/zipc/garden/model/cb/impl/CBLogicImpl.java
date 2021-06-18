/**
 */
package com.zipc.garden.model.cb.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.cb.CBFile;
import com.zipc.garden.model.cb.CBLogic;
import com.zipc.garden.model.cb.CBLogicType;
import com.zipc.garden.model.cb.CBPackage;

import com.zipc.garden.model.core.impl.AbstractNodeImpl;
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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Logic</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.cb.impl.CBLogicImpl#getChildren <em>Children</em>}</li>
 * <li>{@link com.zipc.garden.model.cb.impl.CBLogicImpl#getFile <em>File</em>}</li>
 * <li>{@link com.zipc.garden.model.cb.impl.CBLogicImpl#getType <em>Type</em>}</li>
 * </ul>
 * @generated
 */
public class CBLogicImpl extends AbstractNodeImpl implements CBLogic {
    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildren()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<CBLogic> children;

    /**
     * The cached value of the '{@link #getFile() <em>File</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getFile()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<CBFile> file;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected CBLogicType type;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected CBLogicImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CBPackage.Literals.CB_LOGIC;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<CBLogic> getChildren() {
        if (children == null) {
            children = new EObjectContainmentEList<CBLogic>(CBLogic.class, this, CBPackage.CB_LOGIC__CHILDREN);
        }
        return children;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<CBFile> getFile() {
        if (file == null) {
            file = new EObjectContainmentEList<CBFile>(CBFile.class, this, CBPackage.CB_LOGIC__FILE);
        }
        return file;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CBLogicType getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetType(CBLogicType newType, NotificationChain msgs) {
        CBLogicType oldType = type;
        type = newType;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CBPackage.CB_LOGIC__TYPE, oldType, newType);
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
    public void setType(CBLogicType newType) {
        if (newType != type) {
            NotificationChain msgs = null;
            if (type != null)
                msgs = ((InternalEObject) type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CBPackage.CB_LOGIC__TYPE, null, msgs);
            if (newType != null)
                msgs = ((InternalEObject) newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CBPackage.CB_LOGIC__TYPE, null, msgs);
            msgs = basicSetType(newType, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CBPackage.CB_LOGIC__TYPE, newType, newType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case CBPackage.CB_LOGIC__CHILDREN:
            return ((InternalEList<?>) getChildren()).basicRemove(otherEnd, msgs);
        case CBPackage.CB_LOGIC__FILE:
            return ((InternalEList<?>) getFile()).basicRemove(otherEnd, msgs);
        case CBPackage.CB_LOGIC__TYPE:
            return basicSetType(null, msgs);
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
        case CBPackage.CB_LOGIC__CHILDREN:
            return getChildren();
        case CBPackage.CB_LOGIC__FILE:
            return getFile();
        case CBPackage.CB_LOGIC__TYPE:
            return getType();
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
        case CBPackage.CB_LOGIC__CHILDREN:
            getChildren().clear();
            getChildren().addAll((Collection<? extends CBLogic>) newValue);
            return;
        case CBPackage.CB_LOGIC__FILE:
            getFile().clear();
            getFile().addAll((Collection<? extends CBFile>) newValue);
            return;
        case CBPackage.CB_LOGIC__TYPE:
            setType((CBLogicType) newValue);
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
        case CBPackage.CB_LOGIC__CHILDREN:
            getChildren().clear();
            return;
        case CBPackage.CB_LOGIC__FILE:
            getFile().clear();
            return;
        case CBPackage.CB_LOGIC__TYPE:
            setType((CBLogicType) null);
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
        case CBPackage.CB_LOGIC__CHILDREN:
            return children != null && !children.isEmpty();
        case CBPackage.CB_LOGIC__FILE:
            return file != null && !file.isEmpty();
        case CBPackage.CB_LOGIC__TYPE:
            return type != null;
        }
        return super.eIsSet(featureID);
    }

} // CBLogicImpl
