/**
 */
package com.zipc.garden.model.tc.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.google.gwt.user.client.rpc.GwtTransient;
import com.zipc.garden.model.core.impl.AbstractRootElementImpl;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCNodeState;
import com.zipc.garden.model.tc.TCPackage;
import com.zipc.garden.model.tc.TCRoot;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tc.impl.TCRootImpl#getRootNodes <em>Root Nodes</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.impl.TCRootImpl#getShowingHierarchy <em>Showing Hierarchy</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.impl.TCRootImpl#getKeywords <em>Keywords</em>}</li>
 * </ul>
 * @generated
 */
public class TCRootImpl extends AbstractRootElementImpl implements TCRoot {
    /**
     * The cached value of the '{@link #getRootNodes() <em>Root Nodes</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRootNodes()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<TCNode> rootNodes;

    /**
     * The default value of the '{@link #getShowingHierarchy() <em>Showing Hierarchy</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getShowingHierarchy()
     * @generated
     * @ordered
     */
    protected static final int SHOWING_HIERARCHY_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getShowingHierarchy() <em>Showing Hierarchy</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getShowingHierarchy()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int showingHierarchy = SHOWING_HIERARCHY_EDEFAULT;

    /**
     * The cached value of the '{@link #getKeywords() <em>Keywords</em>}' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getKeywords()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<String> keywords;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected TCRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TCPackage.Literals.TC_ROOT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TCNode> getRootNodes() {
        if (rootNodes == null) {
            rootNodes = new EObjectContainmentEList<TCNode>(TCNode.class, this, TCPackage.TC_ROOT__ROOT_NODES);
        }
        return rootNodes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getShowingHierarchy() {
        return showingHierarchy;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setShowingHierarchy(int newShowingHierarchy) {
        int oldShowingHierarchy = showingHierarchy;
        showingHierarchy = newShowingHierarchy;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TCPackage.TC_ROOT__SHOWING_HIERARCHY, oldShowingHierarchy, showingHierarchy));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<String> getKeywords() {
        if (keywords == null) {
            keywords = new EDataTypeUniqueEList<String>(String.class, this, TCPackage.TC_ROOT__KEYWORDS);
        }
        return keywords;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TCPackage.TC_ROOT__ROOT_NODES:
            return ((InternalEList<?>) getRootNodes()).basicRemove(otherEnd, msgs);
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
        case TCPackage.TC_ROOT__ROOT_NODES:
            return getRootNodes();
        case TCPackage.TC_ROOT__SHOWING_HIERARCHY:
            return getShowingHierarchy();
        case TCPackage.TC_ROOT__KEYWORDS:
            return getKeywords();
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
        case TCPackage.TC_ROOT__ROOT_NODES:
            getRootNodes().clear();
            getRootNodes().addAll((Collection<? extends TCNode>) newValue);
            return;
        case TCPackage.TC_ROOT__SHOWING_HIERARCHY:
            setShowingHierarchy((Integer) newValue);
            return;
        case TCPackage.TC_ROOT__KEYWORDS:
            getKeywords().clear();
            getKeywords().addAll((Collection<? extends String>) newValue);
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
        case TCPackage.TC_ROOT__ROOT_NODES:
            getRootNodes().clear();
            return;
        case TCPackage.TC_ROOT__SHOWING_HIERARCHY:
            setShowingHierarchy(SHOWING_HIERARCHY_EDEFAULT);
            return;
        case TCPackage.TC_ROOT__KEYWORDS:
            getKeywords().clear();
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
        case TCPackage.TC_ROOT__ROOT_NODES:
            return rootNodes != null && !rootNodes.isEmpty();
        case TCPackage.TC_ROOT__SHOWING_HIERARCHY:
            return showingHierarchy != SHOWING_HIERARCHY_EDEFAULT;
        case TCPackage.TC_ROOT__KEYWORDS:
            return keywords != null && !keywords.isEmpty();
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
        result.append(" (showingHierarchy: ");
        result.append(showingHierarchy);
        result.append(", keywords: ");
        result.append(keywords);
        result.append(')');
        return result.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public TCNode getActiveRootNode() {
        for (TCNode node : getRootNodes()) {
            if (node.getState() != TCNodeState.DELETED) {
                return node;
            }
        }
        return null;
    }

} // TCRootImpl
