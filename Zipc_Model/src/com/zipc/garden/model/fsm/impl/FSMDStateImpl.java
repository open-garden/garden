/**
 */
package com.zipc.garden.model.fsm.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.fsm.FSMDPseudoStateType;
import com.zipc.garden.model.fsm.FSMDRegion;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMDTransition;
import com.zipc.garden.model.fsm.FSMPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>DState</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateImpl#getRef <em>Ref</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateImpl#getRefName <em>Ref Name</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateImpl#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateImpl#getEntry <em>Entry</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateImpl#getExit <em>Exit</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateImpl#getDo <em>Do</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateImpl#getRegions <em>Regions</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateImpl#getType <em>Type</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateImpl#getIncomingTransition <em>Incoming Transition</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateImpl#getOutgoingTransition <em>Outgoing Transition</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateImpl#getRefuuid <em>Refuuid</em>}</li>
 * </ul>
 * @generated
 */
public class FSMDStateImpl extends FSMDVertexImpl implements FSMDState {
    /**
     * The default value of the '{@link #getRef() <em>Ref</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRef()
     * @generated
     * @ordered
     */
    protected static final long REF_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getRef() <em>Ref</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRef()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long ref = REF_EDEFAULT;

    /**
     * The default value of the '{@link #getRefName() <em>Ref Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getRefName()
     * @generated
     * @ordered
     */
    protected static final String REF_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRefName() <em>Ref Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getRefName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String refName = REF_NAME_EDEFAULT;

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
     * The default value of the '{@link #getEntry() <em>Entry</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getEntry()
     * @generated
     * @ordered
     */
    protected static final String ENTRY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEntry() <em>Entry</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getEntry()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String entry = ENTRY_EDEFAULT;

    /**
     * The default value of the '{@link #getExit() <em>Exit</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getExit()
     * @generated
     * @ordered
     */
    protected static final String EXIT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExit() <em>Exit</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getExit()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String exit = EXIT_EDEFAULT;

    /**
     * The default value of the '{@link #getDo() <em>Do</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getDo()
     * @generated
     * @ordered
     */
    protected static final String DO_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDo() <em>Do</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getDo()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String do_ = DO_EDEFAULT;

    /**
     * The cached value of the '{@link #getRegions() <em>Regions</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getRegions()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FSMDRegion> regions;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final FSMDPseudoStateType TYPE_EDEFAULT = FSMDPseudoStateType.NONE;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected FSMDPseudoStateType type = TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getIncomingTransition() <em>Incoming Transition</em>}' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see #getIncomingTransition()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FSMDTransition> incomingTransition;

    /**
     * The cached value of the '{@link #getOutgoingTransition() <em>Outgoing Transition</em>}' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see #getOutgoingTransition()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FSMDTransition> outgoingTransition;

    /**
     * The default value of the '{@link #getRefuuid() <em>Refuuid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getRefuuid()
     * @generated
     * @ordered
     */
    protected static final String REFUUID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRefuuid() <em>Refuuid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRefuuid()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String refuuid = REFUUID_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FSMDStateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FSMPackage.Literals.FSM_DSTATE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getRef() {
        return ref;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRef(long newRef) {
        long oldRef = ref;
        ref = newRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE__REF, oldRef, ref));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getRefName() {
        return refName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRefName(String newRefName) {
        String oldRefName = refName;
        refName = newRefName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE__REF_NAME, oldRefName, refName));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getEntry() {
        return entry;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setEntry(String newEntry) {
        String oldEntry = entry;
        entry = newEntry;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE__ENTRY, oldEntry, entry));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getExit() {
        return exit;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setExit(String newExit) {
        String oldExit = exit;
        exit = newExit;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE__EXIT, oldExit, exit));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getDo() {
        return do_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDo(String newDo) {
        String oldDo = do_;
        do_ = newDo;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE__DO, oldDo, do_));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FSMDRegion> getRegions() {
        if (regions == null) {
            regions = new EObjectContainmentEList<FSMDRegion>(FSMDRegion.class, this, FSMPackage.FSM_DSTATE__REGIONS);
        }
        return regions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDPseudoStateType getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setType(FSMDPseudoStateType newType) {
        FSMDPseudoStateType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FSMDTransition> getIncomingTransition() {
        if (incomingTransition == null) {
            incomingTransition = new EObjectWithInverseResolvingEList<FSMDTransition>(FSMDTransition.class, this, FSMPackage.FSM_DSTATE__INCOMING_TRANSITION,
                    FSMPackage.FSM_DTRANSITION__TARGET);
        }
        return incomingTransition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FSMDTransition> getOutgoingTransition() {
        if (outgoingTransition == null) {
            outgoingTransition = new EObjectWithInverseResolvingEList<FSMDTransition>(FSMDTransition.class, this, FSMPackage.FSM_DSTATE__OUTGOING_TRANSITION,
                    FSMPackage.FSM_DTRANSITION__SOURCE);
        }
        return outgoingTransition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getRefuuid() {
        return refuuid;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRefuuid(String newRefuuid) {
        String oldRefuuid = refuuid;
        refuuid = newRefuuid;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE__REFUUID, oldRefuuid, refuuid));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FSMPackage.FSM_DSTATE__INCOMING_TRANSITION:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getIncomingTransition()).basicAdd(otherEnd, msgs);
        case FSMPackage.FSM_DSTATE__OUTGOING_TRANSITION:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getOutgoingTransition()).basicAdd(otherEnd, msgs);
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
        case FSMPackage.FSM_DSTATE__REGIONS:
            return ((InternalEList<?>) getRegions()).basicRemove(otherEnd, msgs);
        case FSMPackage.FSM_DSTATE__INCOMING_TRANSITION:
            return ((InternalEList<?>) getIncomingTransition()).basicRemove(otherEnd, msgs);
        case FSMPackage.FSM_DSTATE__OUTGOING_TRANSITION:
            return ((InternalEList<?>) getOutgoingTransition()).basicRemove(otherEnd, msgs);
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
        case FSMPackage.FSM_DSTATE__REF:
            return getRef();
        case FSMPackage.FSM_DSTATE__REF_NAME:
            return getRefName();
        case FSMPackage.FSM_DSTATE__NAME:
            return getName();
        case FSMPackage.FSM_DSTATE__ENTRY:
            return getEntry();
        case FSMPackage.FSM_DSTATE__EXIT:
            return getExit();
        case FSMPackage.FSM_DSTATE__DO:
            return getDo();
        case FSMPackage.FSM_DSTATE__REGIONS:
            return getRegions();
        case FSMPackage.FSM_DSTATE__TYPE:
            return getType();
        case FSMPackage.FSM_DSTATE__INCOMING_TRANSITION:
            return getIncomingTransition();
        case FSMPackage.FSM_DSTATE__OUTGOING_TRANSITION:
            return getOutgoingTransition();
        case FSMPackage.FSM_DSTATE__REFUUID:
            return getRefuuid();
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
        case FSMPackage.FSM_DSTATE__REF:
            setRef((Long) newValue);
            return;
        case FSMPackage.FSM_DSTATE__REF_NAME:
            setRefName((String) newValue);
            return;
        case FSMPackage.FSM_DSTATE__NAME:
            setName((String) newValue);
            return;
        case FSMPackage.FSM_DSTATE__ENTRY:
            setEntry((String) newValue);
            return;
        case FSMPackage.FSM_DSTATE__EXIT:
            setExit((String) newValue);
            return;
        case FSMPackage.FSM_DSTATE__DO:
            setDo((String) newValue);
            return;
        case FSMPackage.FSM_DSTATE__REGIONS:
            getRegions().clear();
            getRegions().addAll((Collection<? extends FSMDRegion>) newValue);
            return;
        case FSMPackage.FSM_DSTATE__TYPE:
            setType((FSMDPseudoStateType) newValue);
            return;
        case FSMPackage.FSM_DSTATE__INCOMING_TRANSITION:
            getIncomingTransition().clear();
            getIncomingTransition().addAll((Collection<? extends FSMDTransition>) newValue);
            return;
        case FSMPackage.FSM_DSTATE__OUTGOING_TRANSITION:
            getOutgoingTransition().clear();
            getOutgoingTransition().addAll((Collection<? extends FSMDTransition>) newValue);
            return;
        case FSMPackage.FSM_DSTATE__REFUUID:
            setRefuuid((String) newValue);
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
        case FSMPackage.FSM_DSTATE__REF:
            setRef(REF_EDEFAULT);
            return;
        case FSMPackage.FSM_DSTATE__REF_NAME:
            setRefName(REF_NAME_EDEFAULT);
            return;
        case FSMPackage.FSM_DSTATE__NAME:
            setName(NAME_EDEFAULT);
            return;
        case FSMPackage.FSM_DSTATE__ENTRY:
            setEntry(ENTRY_EDEFAULT);
            return;
        case FSMPackage.FSM_DSTATE__EXIT:
            setExit(EXIT_EDEFAULT);
            return;
        case FSMPackage.FSM_DSTATE__DO:
            setDo(DO_EDEFAULT);
            return;
        case FSMPackage.FSM_DSTATE__REGIONS:
            getRegions().clear();
            return;
        case FSMPackage.FSM_DSTATE__TYPE:
            setType(TYPE_EDEFAULT);
            return;
        case FSMPackage.FSM_DSTATE__INCOMING_TRANSITION:
            getIncomingTransition().clear();
            return;
        case FSMPackage.FSM_DSTATE__OUTGOING_TRANSITION:
            getOutgoingTransition().clear();
            return;
        case FSMPackage.FSM_DSTATE__REFUUID:
            setRefuuid(REFUUID_EDEFAULT);
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
        case FSMPackage.FSM_DSTATE__REF:
            return ref != REF_EDEFAULT;
        case FSMPackage.FSM_DSTATE__REF_NAME:
            return REF_NAME_EDEFAULT == null ? refName != null : !REF_NAME_EDEFAULT.equals(refName);
        case FSMPackage.FSM_DSTATE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case FSMPackage.FSM_DSTATE__ENTRY:
            return ENTRY_EDEFAULT == null ? entry != null : !ENTRY_EDEFAULT.equals(entry);
        case FSMPackage.FSM_DSTATE__EXIT:
            return EXIT_EDEFAULT == null ? exit != null : !EXIT_EDEFAULT.equals(exit);
        case FSMPackage.FSM_DSTATE__DO:
            return DO_EDEFAULT == null ? do_ != null : !DO_EDEFAULT.equals(do_);
        case FSMPackage.FSM_DSTATE__REGIONS:
            return regions != null && !regions.isEmpty();
        case FSMPackage.FSM_DSTATE__TYPE:
            return type != TYPE_EDEFAULT;
        case FSMPackage.FSM_DSTATE__INCOMING_TRANSITION:
            return incomingTransition != null && !incomingTransition.isEmpty();
        case FSMPackage.FSM_DSTATE__OUTGOING_TRANSITION:
            return outgoingTransition != null && !outgoingTransition.isEmpty();
        case FSMPackage.FSM_DSTATE__REFUUID:
            return REFUUID_EDEFAULT == null ? refuuid != null : !REFUUID_EDEFAULT.equals(refuuid);
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
        result.append(" (ref: ");
        result.append(ref);
        result.append(", refName: ");
        result.append(refName);
        result.append(", name: ");
        result.append(name);
        result.append(", entry: ");
        result.append(entry);
        result.append(", exit: ");
        result.append(exit);
        result.append(", do: ");
        result.append(do_);
        result.append(", type: ");
        result.append(type);
        result.append(", refuuid: ");
        result.append(refuuid);
        result.append(')');
        return result.toString();
    }

} // FSMDStateImpl
