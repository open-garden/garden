/**
 */
package com.zipc.garden.model.project.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.project.GPModelPackage;
import com.zipc.garden.model.project.GPResource;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>GP Resource</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.project.impl.GPResourceImpl#getParent <em>Parent</em>}</li>
 * <li>{@link com.zipc.garden.model.project.impl.GPResourceImpl#getChildren <em>Children</em>}</li>
 * <li>{@link com.zipc.garden.model.project.impl.GPResourceImpl#isDirectory <em>Directory</em>}</li>
 * <li>{@link com.zipc.garden.model.project.impl.GPResourceImpl#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.project.impl.GPResourceImpl#getId <em>Id</em>}</li>
 * </ul>
 * @generated
 */
public class GPResourceImpl extends MinimalEObjectImpl.Container implements GPResource {
    /**
     * The cached value of the '{@link #getParent() <em>Parent</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getParent()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected GPResource parent;

    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getChildren()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<GPResource> children;

    /**
     * The default value of the '{@link #isDirectory() <em>Directory</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #isDirectory()
     * @generated
     * @ordered
     */
    protected static final boolean DIRECTORY_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isDirectory() <em>Directory</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #isDirectory()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected boolean directory = DIRECTORY_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final long ID_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long id = ID_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected GPResourceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return GPModelPackage.Literals.GP_RESOURCE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public GPResource getParent() {
        if (parent != null && parent.eIsProxy()) {
            InternalEObject oldParent = (InternalEObject) parent;
            parent = (GPResource) eResolveProxy(oldParent);
            if (parent != oldParent) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GPModelPackage.GP_RESOURCE__PARENT, oldParent, parent));
            }
        }
        return parent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public GPResource basicGetParent() {
        return parent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParent(GPResource newParent, NotificationChain msgs) {
        GPResource oldParent = parent;
        parent = newParent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GPModelPackage.GP_RESOURCE__PARENT, oldParent, newParent);
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
    public void setParent(GPResource newParent) {
        if (newParent != parent) {
            NotificationChain msgs = null;
            if (parent != null)
                msgs = ((InternalEObject) parent).eInverseRemove(this, GPModelPackage.GP_RESOURCE__CHILDREN, GPResource.class, msgs);
            if (newParent != null)
                msgs = ((InternalEObject) newParent).eInverseAdd(this, GPModelPackage.GP_RESOURCE__CHILDREN, GPResource.class, msgs);
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GPModelPackage.GP_RESOURCE__PARENT, newParent, newParent));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<GPResource> getChildren() {
        if (children == null) {
            children = new EObjectWithInverseResolvingEList<GPResource>(GPResource.class, this, GPModelPackage.GP_RESOURCE__CHILDREN, GPModelPackage.GP_RESOURCE__PARENT);
        }
        return children;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isDirectory() {
        return directory;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDirectory(boolean newDirectory) {
        boolean oldDirectory = directory;
        directory = newDirectory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GPModelPackage.GP_RESOURCE__DIRECTORY, oldDirectory, directory));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GPModelPackage.GP_RESOURCE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setId(long newId) {
        long oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GPModelPackage.GP_RESOURCE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case GPModelPackage.GP_RESOURCE__PARENT:
            if (parent != null)
                msgs = ((InternalEObject) parent).eInverseRemove(this, GPModelPackage.GP_RESOURCE__CHILDREN, GPResource.class, msgs);
            return basicSetParent((GPResource) otherEnd, msgs);
        case GPModelPackage.GP_RESOURCE__CHILDREN:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getChildren()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case GPModelPackage.GP_RESOURCE__PARENT:
            return basicSetParent(null, msgs);
        case GPModelPackage.GP_RESOURCE__CHILDREN:
            return ((InternalEList<?>) getChildren()).basicRemove(otherEnd, msgs);
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
        case GPModelPackage.GP_RESOURCE__PARENT:
            if (resolve)
                return getParent();
            return basicGetParent();
        case GPModelPackage.GP_RESOURCE__CHILDREN:
            return getChildren();
        case GPModelPackage.GP_RESOURCE__DIRECTORY:
            return isDirectory();
        case GPModelPackage.GP_RESOURCE__NAME:
            return getName();
        case GPModelPackage.GP_RESOURCE__ID:
            return getId();
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
        case GPModelPackage.GP_RESOURCE__PARENT:
            setParent((GPResource) newValue);
            return;
        case GPModelPackage.GP_RESOURCE__CHILDREN:
            getChildren().clear();
            getChildren().addAll((Collection<? extends GPResource>) newValue);
            return;
        case GPModelPackage.GP_RESOURCE__DIRECTORY:
            setDirectory((Boolean) newValue);
            return;
        case GPModelPackage.GP_RESOURCE__NAME:
            setName((String) newValue);
            return;
        case GPModelPackage.GP_RESOURCE__ID:
            setId((Long) newValue);
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
        case GPModelPackage.GP_RESOURCE__PARENT:
            setParent((GPResource) null);
            return;
        case GPModelPackage.GP_RESOURCE__CHILDREN:
            getChildren().clear();
            return;
        case GPModelPackage.GP_RESOURCE__DIRECTORY:
            setDirectory(DIRECTORY_EDEFAULT);
            return;
        case GPModelPackage.GP_RESOURCE__NAME:
            setName(NAME_EDEFAULT);
            return;
        case GPModelPackage.GP_RESOURCE__ID:
            setId(ID_EDEFAULT);
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
        case GPModelPackage.GP_RESOURCE__PARENT:
            return parent != null;
        case GPModelPackage.GP_RESOURCE__CHILDREN:
            return children != null && !children.isEmpty();
        case GPModelPackage.GP_RESOURCE__DIRECTORY:
            return directory != DIRECTORY_EDEFAULT;
        case GPModelPackage.GP_RESOURCE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case GPModelPackage.GP_RESOURCE__ID:
            return id != ID_EDEFAULT;
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
        result.append(" (directory: ");
        result.append(directory);
        result.append(", name: ");
        result.append(name);
        result.append(", id: ");
        result.append(id);
        result.append(')');
        return result.toString();
    }

} // GPResourceImpl
