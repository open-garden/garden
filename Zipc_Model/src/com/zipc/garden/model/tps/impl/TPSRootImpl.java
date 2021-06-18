/**
 */
package com.zipc.garden.model.tps.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.impl.AbstractRootElementImpl;

import com.zipc.garden.model.fmc.FMCRoot;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tps.NodeVariable;
import com.zipc.garden.model.tps.TPSPackage;
import com.zipc.garden.model.tps.TPSPatternElement;
import com.zipc.garden.model.tps.TPSRoot;

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
 * <li>{@link com.zipc.garden.model.tps.impl.TPSRootImpl#getNWiseCombination <em>NWise Combination</em>}</li>
 * <li>{@link com.zipc.garden.model.tps.impl.TPSRootImpl#getRootNodes <em>Root Nodes</em>}</li>
 * <li>{@link com.zipc.garden.model.tps.impl.TPSRootImpl#getFmcRoot <em>Fmc Root</em>}</li>
 * <li>{@link com.zipc.garden.model.tps.impl.TPSRootImpl#getPatternElements <em>Pattern Elements</em>}</li>
 * <li>{@link com.zipc.garden.model.tps.impl.TPSRootImpl#getNodeVariables <em>Node Variables</em>}</li>
 * </ul>
 * @generated
 */
public class TPSRootImpl extends AbstractRootElementImpl implements TPSRoot {
    /**
     * The default value of the '{@link #getNWiseCombination() <em>NWise Combination</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNWiseCombination()
     * @generated
     * @ordered
     */
    protected static final int NWISE_COMBINATION_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getNWiseCombination() <em>NWise Combination</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNWiseCombination()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int nWiseCombination = NWISE_COMBINATION_EDEFAULT;

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
     * The cached value of the '{@link #getFmcRoot() <em>Fmc Root</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getFmcRoot()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected FMCRoot fmcRoot;

    /**
     * The cached value of the '{@link #getPatternElements() <em>Pattern Elements</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see #getPatternElements()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<TPSPatternElement> patternElements;

    /**
     * The cached value of the '{@link #getNodeVariables() <em>Node Variables</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see #getNodeVariables()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<NodeVariable> nodeVariables;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected TPSRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TPSPackage.Literals.TPS_ROOT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getNWiseCombination() {
        return nWiseCombination;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setNWiseCombination(int newNWiseCombination) {
        int oldNWiseCombination = nWiseCombination;
        nWiseCombination = newNWiseCombination;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TPSPackage.TPS_ROOT__NWISE_COMBINATION, oldNWiseCombination, nWiseCombination));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TCNode> getRootNodes() {
        if (rootNodes == null) {
            rootNodes = new EObjectContainmentEList<TCNode>(TCNode.class, this, TPSPackage.TPS_ROOT__ROOT_NODES);
        }
        return rootNodes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCRoot getFmcRoot() {
        return fmcRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFmcRoot(FMCRoot newFmcRoot, NotificationChain msgs) {
        FMCRoot oldFmcRoot = fmcRoot;
        fmcRoot = newFmcRoot;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TPSPackage.TPS_ROOT__FMC_ROOT, oldFmcRoot, newFmcRoot);
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
    public void setFmcRoot(FMCRoot newFmcRoot) {
        if (newFmcRoot != fmcRoot) {
            NotificationChain msgs = null;
            if (fmcRoot != null)
                msgs = ((InternalEObject) fmcRoot).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TPSPackage.TPS_ROOT__FMC_ROOT, null, msgs);
            if (newFmcRoot != null)
                msgs = ((InternalEObject) newFmcRoot).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TPSPackage.TPS_ROOT__FMC_ROOT, null, msgs);
            msgs = basicSetFmcRoot(newFmcRoot, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TPSPackage.TPS_ROOT__FMC_ROOT, newFmcRoot, newFmcRoot));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TPSPatternElement> getPatternElements() {
        if (patternElements == null) {
            patternElements = new EObjectContainmentEList<TPSPatternElement>(TPSPatternElement.class, this, TPSPackage.TPS_ROOT__PATTERN_ELEMENTS);
        }
        return patternElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<NodeVariable> getNodeVariables() {
        if (nodeVariables == null) {
            nodeVariables = new EObjectContainmentEList<NodeVariable>(NodeVariable.class, this, TPSPackage.TPS_ROOT__NODE_VARIABLES);
        }
        return nodeVariables;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TPSPackage.TPS_ROOT__ROOT_NODES:
            return ((InternalEList<?>) getRootNodes()).basicRemove(otherEnd, msgs);
        case TPSPackage.TPS_ROOT__FMC_ROOT:
            return basicSetFmcRoot(null, msgs);
        case TPSPackage.TPS_ROOT__PATTERN_ELEMENTS:
            return ((InternalEList<?>) getPatternElements()).basicRemove(otherEnd, msgs);
        case TPSPackage.TPS_ROOT__NODE_VARIABLES:
            return ((InternalEList<?>) getNodeVariables()).basicRemove(otherEnd, msgs);
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
        case TPSPackage.TPS_ROOT__NWISE_COMBINATION:
            return getNWiseCombination();
        case TPSPackage.TPS_ROOT__ROOT_NODES:
            return getRootNodes();
        case TPSPackage.TPS_ROOT__FMC_ROOT:
            return getFmcRoot();
        case TPSPackage.TPS_ROOT__PATTERN_ELEMENTS:
            return getPatternElements();
        case TPSPackage.TPS_ROOT__NODE_VARIABLES:
            return getNodeVariables();
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
        case TPSPackage.TPS_ROOT__NWISE_COMBINATION:
            setNWiseCombination((Integer) newValue);
            return;
        case TPSPackage.TPS_ROOT__ROOT_NODES:
            getRootNodes().clear();
            getRootNodes().addAll((Collection<? extends TCNode>) newValue);
            return;
        case TPSPackage.TPS_ROOT__FMC_ROOT:
            setFmcRoot((FMCRoot) newValue);
            return;
        case TPSPackage.TPS_ROOT__PATTERN_ELEMENTS:
            getPatternElements().clear();
            getPatternElements().addAll((Collection<? extends TPSPatternElement>) newValue);
            return;
        case TPSPackage.TPS_ROOT__NODE_VARIABLES:
            getNodeVariables().clear();
            getNodeVariables().addAll((Collection<? extends NodeVariable>) newValue);
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
        case TPSPackage.TPS_ROOT__NWISE_COMBINATION:
            setNWiseCombination(NWISE_COMBINATION_EDEFAULT);
            return;
        case TPSPackage.TPS_ROOT__ROOT_NODES:
            getRootNodes().clear();
            return;
        case TPSPackage.TPS_ROOT__FMC_ROOT:
            setFmcRoot((FMCRoot) null);
            return;
        case TPSPackage.TPS_ROOT__PATTERN_ELEMENTS:
            getPatternElements().clear();
            return;
        case TPSPackage.TPS_ROOT__NODE_VARIABLES:
            getNodeVariables().clear();
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
        case TPSPackage.TPS_ROOT__NWISE_COMBINATION:
            return nWiseCombination != NWISE_COMBINATION_EDEFAULT;
        case TPSPackage.TPS_ROOT__ROOT_NODES:
            return rootNodes != null && !rootNodes.isEmpty();
        case TPSPackage.TPS_ROOT__FMC_ROOT:
            return fmcRoot != null;
        case TPSPackage.TPS_ROOT__PATTERN_ELEMENTS:
            return patternElements != null && !patternElements.isEmpty();
        case TPSPackage.TPS_ROOT__NODE_VARIABLES:
            return nodeVariables != null && !nodeVariables.isEmpty();
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
        result.append(" (nWiseCombination: ");
        result.append(nWiseCombination);
        result.append(')');
        return result.toString();
    }

} // TPSRootImpl
