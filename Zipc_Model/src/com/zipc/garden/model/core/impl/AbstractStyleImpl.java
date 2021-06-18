/**
 */
package com.zipc.garden.model.core.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.AbstractStyle;
import com.zipc.garden.model.core.COREPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractStyleImpl#getFontSize <em>Font Size</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractStyleImpl#getFillColor <em>Fill Color</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractStyleImpl#getFontColor <em>Font Color</em>}</li>
 * </ul>
 * @generated
 */
public abstract class AbstractStyleImpl extends MinimalEObjectImpl.Container implements AbstractStyle {
    /**
     * The default value of the '{@link #getFontSize() <em>Font Size</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getFontSize()
     * @generated
     * @ordered
     */
    protected static final int FONT_SIZE_EDEFAULT = 11;

    /**
     * The cached value of the '{@link #getFontSize() <em>Font Size</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getFontSize()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int fontSize = FONT_SIZE_EDEFAULT;

    /**
     * The default value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getFillColor()
     * @generated
     * @ordered
     */
    protected static final String FILL_COLOR_EDEFAULT = "#FFFFFF";

    /**
     * The cached value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getFillColor()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String fillColor = FILL_COLOR_EDEFAULT;

    /**
     * The default value of the '{@link #getFontColor() <em>Font Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getFontColor()
     * @generated
     * @ordered
     */
    protected static final String FONT_COLOR_EDEFAULT = "#707070";

    /**
     * The cached value of the '{@link #getFontColor() <em>Font Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getFontColor()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String fontColor = FONT_COLOR_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected AbstractStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return COREPackage.Literals.ABSTRACT_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getFontSize() {
        return fontSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFontSize(int newFontSize) {
        int oldFontSize = fontSize;
        fontSize = newFontSize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_STYLE__FONT_SIZE, oldFontSize, fontSize));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getFillColor() {
        return fillColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFillColor(String newFillColor) {
        String oldFillColor = fillColor;
        fillColor = newFillColor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_STYLE__FILL_COLOR, oldFillColor, fillColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getFontColor() {
        return fontColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFontColor(String newFontColor) {
        String oldFontColor = fontColor;
        fontColor = newFontColor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_STYLE__FONT_COLOR, oldFontColor, fontColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case COREPackage.ABSTRACT_STYLE__FONT_SIZE:
            return getFontSize();
        case COREPackage.ABSTRACT_STYLE__FILL_COLOR:
            return getFillColor();
        case COREPackage.ABSTRACT_STYLE__FONT_COLOR:
            return getFontColor();
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
        case COREPackage.ABSTRACT_STYLE__FONT_SIZE:
            setFontSize((Integer) newValue);
            return;
        case COREPackage.ABSTRACT_STYLE__FILL_COLOR:
            setFillColor((String) newValue);
            return;
        case COREPackage.ABSTRACT_STYLE__FONT_COLOR:
            setFontColor((String) newValue);
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
        case COREPackage.ABSTRACT_STYLE__FONT_SIZE:
            setFontSize(FONT_SIZE_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_STYLE__FILL_COLOR:
            setFillColor(FILL_COLOR_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_STYLE__FONT_COLOR:
            setFontColor(FONT_COLOR_EDEFAULT);
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
        case COREPackage.ABSTRACT_STYLE__FONT_SIZE:
            return fontSize != FONT_SIZE_EDEFAULT;
        case COREPackage.ABSTRACT_STYLE__FILL_COLOR:
            return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
        case COREPackage.ABSTRACT_STYLE__FONT_COLOR:
            return FONT_COLOR_EDEFAULT == null ? fontColor != null : !FONT_COLOR_EDEFAULT.equals(fontColor);
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
        result.append(" (fontSize: ");
        result.append(fontSize);
        result.append(", fillColor: ");
        result.append(fillColor);
        result.append(", fontColor: ");
        result.append(fontColor);
        result.append(')');
        return result.toString();
    }

} // AbstractStyleImpl
