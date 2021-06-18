/**
 */
package com.zipc.garden.model.arc.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.arc.ARCLine;
import com.zipc.garden.model.arc.ARCPackage;
import com.zipc.garden.model.arc.ARCState;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>State</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCStateImpl#getFileId <em>File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCStateImpl#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCStateImpl#getIncomingLine <em>Incoming Line</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCStateImpl#getOutgoingLine <em>Outgoing Line</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCStateImpl#getEvalPriority <em>Eval Priority</em>}</li>
 * </ul>
 * @generated
 */
public class ARCStateImpl extends ARCVertexImpl implements ARCState {
    /**
     * The default value of the '{@link #getFileId() <em>File Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFileId()
     * @generated
     * @ordered
     */
    protected static final String FILE_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFileId() <em>File Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String fileId = FILE_ID_EDEFAULT;

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
     * The cached value of the '{@link #getIncomingLine() <em>Incoming Line</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getIncomingLine()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<ARCLine> incomingLine;

    /**
     * The cached value of the '{@link #getOutgoingLine() <em>Outgoing Line</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getOutgoingLine()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<ARCLine> outgoingLine;

    /**
     * The default value of the '{@link #getEvalPriority() <em>Eval Priority</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getEvalPriority()
     * @generated
     * @ordered
     */
    protected static final int EVAL_PRIORITY_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getEvalPriority() <em>Eval Priority</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getEvalPriority()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int evalPriority = EVAL_PRIORITY_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected ARCStateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ARCPackage.Literals.ARC_STATE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getFileId() {
        return fileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFileId(String newFileId) {
        String oldFileId = fileId;
        fileId = newFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_STATE__FILE_ID, oldFileId, fileId));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_STATE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<ARCLine> getIncomingLine() {
        if (incomingLine == null) {
            incomingLine = new EObjectWithInverseResolvingEList<ARCLine>(ARCLine.class, this, ARCPackage.ARC_STATE__INCOMING_LINE, ARCPackage.ARC_LINE__TARGET);
        }
        return incomingLine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<ARCLine> getOutgoingLine() {
        if (outgoingLine == null) {
            outgoingLine = new EObjectWithInverseResolvingEList<ARCLine>(ARCLine.class, this, ARCPackage.ARC_STATE__OUTGOING_LINE, ARCPackage.ARC_LINE__SOURCE);
        }
        return outgoingLine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getEvalPriority() {
        return evalPriority;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setEvalPriority(int newEvalPriority) {
        int oldEvalPriority = evalPriority;
        evalPriority = newEvalPriority;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_STATE__EVAL_PRIORITY, oldEvalPriority, evalPriority));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ARCPackage.ARC_STATE__INCOMING_LINE:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getIncomingLine()).basicAdd(otherEnd, msgs);
        case ARCPackage.ARC_STATE__OUTGOING_LINE:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getOutgoingLine()).basicAdd(otherEnd, msgs);
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
        case ARCPackage.ARC_STATE__INCOMING_LINE:
            return ((InternalEList<?>) getIncomingLine()).basicRemove(otherEnd, msgs);
        case ARCPackage.ARC_STATE__OUTGOING_LINE:
            return ((InternalEList<?>) getOutgoingLine()).basicRemove(otherEnd, msgs);
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
        case ARCPackage.ARC_STATE__FILE_ID:
            return getFileId();
        case ARCPackage.ARC_STATE__NAME:
            return getName();
        case ARCPackage.ARC_STATE__INCOMING_LINE:
            return getIncomingLine();
        case ARCPackage.ARC_STATE__OUTGOING_LINE:
            return getOutgoingLine();
        case ARCPackage.ARC_STATE__EVAL_PRIORITY:
            return getEvalPriority();
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
        case ARCPackage.ARC_STATE__FILE_ID:
            setFileId((String) newValue);
            return;
        case ARCPackage.ARC_STATE__NAME:
            setName((String) newValue);
            return;
        case ARCPackage.ARC_STATE__INCOMING_LINE:
            getIncomingLine().clear();
            getIncomingLine().addAll((Collection<? extends ARCLine>) newValue);
            return;
        case ARCPackage.ARC_STATE__OUTGOING_LINE:
            getOutgoingLine().clear();
            getOutgoingLine().addAll((Collection<? extends ARCLine>) newValue);
            return;
        case ARCPackage.ARC_STATE__EVAL_PRIORITY:
            setEvalPriority((Integer) newValue);
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
        case ARCPackage.ARC_STATE__FILE_ID:
            setFileId(FILE_ID_EDEFAULT);
            return;
        case ARCPackage.ARC_STATE__NAME:
            setName(NAME_EDEFAULT);
            return;
        case ARCPackage.ARC_STATE__INCOMING_LINE:
            getIncomingLine().clear();
            return;
        case ARCPackage.ARC_STATE__OUTGOING_LINE:
            getOutgoingLine().clear();
            return;
        case ARCPackage.ARC_STATE__EVAL_PRIORITY:
            setEvalPriority(EVAL_PRIORITY_EDEFAULT);
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
        case ARCPackage.ARC_STATE__FILE_ID:
            return FILE_ID_EDEFAULT == null ? fileId != null : !FILE_ID_EDEFAULT.equals(fileId);
        case ARCPackage.ARC_STATE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case ARCPackage.ARC_STATE__INCOMING_LINE:
            return incomingLine != null && !incomingLine.isEmpty();
        case ARCPackage.ARC_STATE__OUTGOING_LINE:
            return outgoingLine != null && !outgoingLine.isEmpty();
        case ARCPackage.ARC_STATE__EVAL_PRIORITY:
            return evalPriority != EVAL_PRIORITY_EDEFAULT;
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
        result.append(" (fileId: ");
        result.append(fileId);
        result.append(", name: ");
        result.append(name);
        result.append(", evalPriority: ");
        result.append(evalPriority);
        result.append(')');
        return result.toString();
    }

} // ARCStateImpl
