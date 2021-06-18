/**
 */
package com.zipc.garden.model.fmc.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.impl.AbstractRootElementImpl;

import com.zipc.garden.model.fmc.FMCConstraint;
import com.zipc.garden.model.fmc.FMCNodePath;
import com.zipc.garden.model.fmc.FMCPackage;
import com.zipc.garden.model.fmc.FMCRoot;

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
 * <li>{@link com.zipc.garden.model.fmc.impl.FMCRootImpl#getConstraints <em>Constraints</em>}</li>
 * <li>{@link com.zipc.garden.model.fmc.impl.FMCRootImpl#getNodepaths <em>Nodepaths</em>}</li>
 * <li>{@link com.zipc.garden.model.fmc.impl.FMCRootImpl#getDocument <em>Document</em>}</li>
 * </ul>
 * @generated
 */
public class FMCRootImpl extends AbstractRootElementImpl implements FMCRoot {
    /**
     * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getConstraints()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FMCConstraint> constraints;

    /**
     * The cached value of the '{@link #getNodepaths() <em>Nodepaths</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNodepaths()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FMCNodePath> nodepaths;

    /**
     * The default value of the '{@link #getDocument() <em>Document</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getDocument()
     * @generated
     * @ordered
     */
    protected static final String DOCUMENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDocument() <em>Document</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getDocument()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String document = DOCUMENT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FMCRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FMCPackage.Literals.FMC_ROOT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FMCConstraint> getConstraints() {
        if (constraints == null) {
            constraints = new EObjectContainmentEList<FMCConstraint>(FMCConstraint.class, this, FMCPackage.FMC_ROOT__CONSTRAINTS);
        }
        return constraints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FMCNodePath> getNodepaths() {
        if (nodepaths == null) {
            nodepaths = new EObjectContainmentEList<FMCNodePath>(FMCNodePath.class, this, FMCPackage.FMC_ROOT__NODEPATHS);
        }
        return nodepaths;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getDocument() {
        return document;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDocument(String newDocument) {
        String oldDocument = document;
        document = newDocument;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMCPackage.FMC_ROOT__DOCUMENT, oldDocument, document));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FMCPackage.FMC_ROOT__CONSTRAINTS:
            return ((InternalEList<?>) getConstraints()).basicRemove(otherEnd, msgs);
        case FMCPackage.FMC_ROOT__NODEPATHS:
            return ((InternalEList<?>) getNodepaths()).basicRemove(otherEnd, msgs);
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
        case FMCPackage.FMC_ROOT__CONSTRAINTS:
            return getConstraints();
        case FMCPackage.FMC_ROOT__NODEPATHS:
            return getNodepaths();
        case FMCPackage.FMC_ROOT__DOCUMENT:
            return getDocument();
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
        case FMCPackage.FMC_ROOT__CONSTRAINTS:
            getConstraints().clear();
            getConstraints().addAll((Collection<? extends FMCConstraint>) newValue);
            return;
        case FMCPackage.FMC_ROOT__NODEPATHS:
            getNodepaths().clear();
            getNodepaths().addAll((Collection<? extends FMCNodePath>) newValue);
            return;
        case FMCPackage.FMC_ROOT__DOCUMENT:
            setDocument((String) newValue);
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
        case FMCPackage.FMC_ROOT__CONSTRAINTS:
            getConstraints().clear();
            return;
        case FMCPackage.FMC_ROOT__NODEPATHS:
            getNodepaths().clear();
            return;
        case FMCPackage.FMC_ROOT__DOCUMENT:
            setDocument(DOCUMENT_EDEFAULT);
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
        case FMCPackage.FMC_ROOT__CONSTRAINTS:
            return constraints != null && !constraints.isEmpty();
        case FMCPackage.FMC_ROOT__NODEPATHS:
            return nodepaths != null && !nodepaths.isEmpty();
        case FMCPackage.FMC_ROOT__DOCUMENT:
            return DOCUMENT_EDEFAULT == null ? document != null : !DOCUMENT_EDEFAULT.equals(document);
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
        result.append(" (document: ");
        result.append(document);
        result.append(')');
        return result.toString();
    }

} // FMCRootImpl
