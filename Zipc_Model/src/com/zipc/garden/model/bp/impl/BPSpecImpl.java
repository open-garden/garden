/**
 */
package com.zipc.garden.model.bp.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.google.gwt.user.client.rpc.GwtTransient;
import com.zipc.garden.model.bp.BPPackage;
import com.zipc.garden.model.bp.BPSpec;
import com.zipc.garden.model.bp.BPSpecElement;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Spec</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.impl.BPSpecImpl#getPaths <em>Paths</em>}</li>
 * </ul>
 * @generated
 */
public class BPSpecImpl extends MinimalEObjectImpl.Container implements BPSpec {
    /**
     * The cached value of the '{@link #getPaths() <em>Paths</em>}' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getPaths()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<BPSpecElement> paths;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected BPSpecImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BPPackage.Literals.BP_SPEC;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<BPSpecElement> getPaths() {
        if (paths == null) {
            paths = new EObjectContainmentEList<BPSpecElement>(BPSpecElement.class, this, BPPackage.BP_SPEC__PATHS);
        }
        return paths;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BPPackage.BP_SPEC__PATHS:
            return ((InternalEList<?>) getPaths()).basicRemove(otherEnd, msgs);
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
        case BPPackage.BP_SPEC__PATHS:
            return getPaths();
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
        case BPPackage.BP_SPEC__PATHS:
            getPaths().clear();
            getPaths().addAll((Collection<? extends BPSpecElement>) newValue);
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
        case BPPackage.BP_SPEC__PATHS:
            getPaths().clear();
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
        case BPPackage.BP_SPEC__PATHS:
            return paths != null && !paths.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }

        if (!(obj instanceof BPSpec)) {
            return false;
        }
        BPSpec other = (BPSpec) obj;
        List<BPSpecElement> paths = getPaths();
        List<BPSpecElement> otherPaths = other.getPaths();
        if (paths.size() != otherPaths.size()) {
            return false;
        }

        if (IntStream.range(0, paths.size()).allMatch(i -> paths.get(i).equals(otherPaths.get(i)))) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getPaths().toString();
    }
} // BPSpecImpl
