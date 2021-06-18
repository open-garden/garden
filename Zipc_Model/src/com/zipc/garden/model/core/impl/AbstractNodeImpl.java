/**
 */
package com.zipc.garden.model.core.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.AbstractNode;
import com.zipc.garden.model.core.COREPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractNodeImpl#getTop <em>Top</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractNodeImpl#getLeft <em>Left</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractNodeImpl#getHeight <em>Height</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractNodeImpl#getWidth <em>Width</em>}</li>
 * </ul>
 * @generated
 */
public abstract class AbstractNodeImpl extends MinimalEObjectImpl.Container implements AbstractNode {
    /**
     * The default value of the '{@link #getTop() <em>Top</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTop()
     * @generated
     * @ordered
     */
    protected static final int TOP_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getTop() <em>Top</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTop()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int top = TOP_EDEFAULT;

    /**
     * The default value of the '{@link #getLeft() <em>Left</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLeft()
     * @generated
     * @ordered
     */
    protected static final int LEFT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getLeft() <em>Left</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLeft()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int left = LEFT_EDEFAULT;

    /**
     * The default value of the '{@link #getHeight() <em>Height</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected static final int HEIGHT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getHeight()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int height = HEIGHT_EDEFAULT;

    /**
     * The default value of the '{@link #getWidth() <em>Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected static final int WIDTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getWidth()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int width = WIDTH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected AbstractNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return COREPackage.Literals.ABSTRACT_NODE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getTop() {
        return top;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTop(int newTop) {
        int oldTop = top;
        top = newTop;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_NODE__TOP, oldTop, top));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getLeft() {
        return left;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLeft(int newLeft) {
        int oldLeft = left;
        left = newLeft;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_NODE__LEFT, oldLeft, left));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setHeight(int newHeight) {
        int oldHeight = height;
        height = newHeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_NODE__HEIGHT, oldHeight, height));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setWidth(int newWidth) {
        int oldWidth = width;
        width = newWidth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_NODE__WIDTH, oldWidth, width));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case COREPackage.ABSTRACT_NODE__TOP:
            return getTop();
        case COREPackage.ABSTRACT_NODE__LEFT:
            return getLeft();
        case COREPackage.ABSTRACT_NODE__HEIGHT:
            return getHeight();
        case COREPackage.ABSTRACT_NODE__WIDTH:
            return getWidth();
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
        case COREPackage.ABSTRACT_NODE__TOP:
            setTop((Integer) newValue);
            return;
        case COREPackage.ABSTRACT_NODE__LEFT:
            setLeft((Integer) newValue);
            return;
        case COREPackage.ABSTRACT_NODE__HEIGHT:
            setHeight((Integer) newValue);
            return;
        case COREPackage.ABSTRACT_NODE__WIDTH:
            setWidth((Integer) newValue);
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
        case COREPackage.ABSTRACT_NODE__TOP:
            setTop(TOP_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_NODE__LEFT:
            setLeft(LEFT_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_NODE__HEIGHT:
            setHeight(HEIGHT_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_NODE__WIDTH:
            setWidth(WIDTH_EDEFAULT);
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
        case COREPackage.ABSTRACT_NODE__TOP:
            return top != TOP_EDEFAULT;
        case COREPackage.ABSTRACT_NODE__LEFT:
            return left != LEFT_EDEFAULT;
        case COREPackage.ABSTRACT_NODE__HEIGHT:
            return height != HEIGHT_EDEFAULT;
        case COREPackage.ABSTRACT_NODE__WIDTH:
            return width != WIDTH_EDEFAULT;
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
        result.append(" (top: ");
        result.append(top);
        result.append(", left: ");
        result.append(left);
        result.append(", height: ");
        result.append(height);
        result.append(", width: ");
        result.append(width);
        result.append(')');
        return result.toString();
    }

} // AbstractNodeImpl
