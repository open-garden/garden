/**
 */
package com.zipc.garden.model.fsm.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.fsm.FSMDReferenceable;
import com.zipc.garden.model.fsm.FSMPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>DReferenceable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDReferenceableImpl#getId <em>Id</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDReferenceableImpl#getUuid <em>Uuid</em>}</li>
 * </ul>
 * @generated
 */
public abstract class FSMDReferenceableImpl extends MinimalEObjectImpl.Container implements FSMDReferenceable {
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FSMDReferenceableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FSMPackage.Literals.FSM_DREFERENCEABLE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DREFERENCEABLE__ID, oldId, id));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DREFERENCEABLE__UUID, oldUuid, uuid));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case FSMPackage.FSM_DREFERENCEABLE__ID:
            return getId();
        case FSMPackage.FSM_DREFERENCEABLE__UUID:
            return getUuid();
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
        case FSMPackage.FSM_DREFERENCEABLE__ID:
            setId((Long) newValue);
            return;
        case FSMPackage.FSM_DREFERENCEABLE__UUID:
            setUuid((String) newValue);
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
        case FSMPackage.FSM_DREFERENCEABLE__ID:
            setId(ID_EDEFAULT);
            return;
        case FSMPackage.FSM_DREFERENCEABLE__UUID:
            setUuid(UUID_EDEFAULT);
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
        case FSMPackage.FSM_DREFERENCEABLE__ID:
            return id != ID_EDEFAULT;
        case FSMPackage.FSM_DREFERENCEABLE__UUID:
            return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
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
        result.append(" (id: ");
        result.append(id);
        result.append(", uuid: ");
        result.append(uuid);
        result.append(')');
        return result.toString();
    }

} // FSMDReferenceableImpl
