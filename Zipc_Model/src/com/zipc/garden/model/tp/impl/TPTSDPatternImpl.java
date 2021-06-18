/**
 */
package com.zipc.garden.model.tp.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.tp.TPElement;
import com.zipc.garden.model.tp.TPPackage;
import com.zipc.garden.model.tp.TPPatternElement;
import com.zipc.garden.model.tp.TPTSDPattern;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>TSD Pattern</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tp.impl.TPTSDPatternImpl#getElements <em>Elements</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPTSDPatternImpl#getId <em>Id</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.impl.TPTSDPatternImpl#getPatternElements <em>Pattern Elements</em>}</li>
 * </ul>
 * @generated
 */
public class TPTSDPatternImpl extends MinimalEObjectImpl.Container implements TPTSDPattern {
    /**
     * The cached value of the '{@link #getElements() <em>Elements</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getElements()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<TPElement> elements;

    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String id = ID_EDEFAULT;

    /**
     * The cached value of the '{@link #getPatternElements() <em>Pattern Elements</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPatternElements()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<TPPatternElement> patternElements;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected TPTSDPatternImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TPPackage.Literals.TP_TSD_PATTERN;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TPElement> getElements() {
        if (elements == null) {
            elements = new EObjectResolvingEList<TPElement>(TPElement.class, this, TPPackage.TP_TSD_PATTERN__ELEMENTS);
        }
        return elements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TPPackage.TP_TSD_PATTERN__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TPPatternElement> getPatternElements() {
        if (patternElements == null) {
            patternElements = new EObjectResolvingEList<TPPatternElement>(TPPatternElement.class, this, TPPackage.TP_TSD_PATTERN__PATTERN_ELEMENTS);
        }
        return patternElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case TPPackage.TP_TSD_PATTERN__ELEMENTS:
            return getElements();
        case TPPackage.TP_TSD_PATTERN__ID:
            return getId();
        case TPPackage.TP_TSD_PATTERN__PATTERN_ELEMENTS:
            return getPatternElements();
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
        case TPPackage.TP_TSD_PATTERN__ELEMENTS:
            getElements().clear();
            getElements().addAll((Collection<? extends TPElement>) newValue);
            return;
        case TPPackage.TP_TSD_PATTERN__ID:
            setId((String) newValue);
            return;
        case TPPackage.TP_TSD_PATTERN__PATTERN_ELEMENTS:
            getPatternElements().clear();
            getPatternElements().addAll((Collection<? extends TPPatternElement>) newValue);
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
        case TPPackage.TP_TSD_PATTERN__ELEMENTS:
            getElements().clear();
            return;
        case TPPackage.TP_TSD_PATTERN__ID:
            setId(ID_EDEFAULT);
            return;
        case TPPackage.TP_TSD_PATTERN__PATTERN_ELEMENTS:
            getPatternElements().clear();
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
        case TPPackage.TP_TSD_PATTERN__ELEMENTS:
            return elements != null && !elements.isEmpty();
        case TPPackage.TP_TSD_PATTERN__ID:
            return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
        case TPPackage.TP_TSD_PATTERN__PATTERN_ELEMENTS:
            return patternElements != null && !patternElements.isEmpty();
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
        result.append(')');
        return result.toString();
    }

} // TPTSDPatternImpl
