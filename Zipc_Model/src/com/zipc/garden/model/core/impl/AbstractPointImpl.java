/**
 */
package com.zipc.garden.model.core.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.COREPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Point</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractPointImpl#getX <em>X</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractPointImpl#getY <em>Y</em>}</li>
 * </ul>
 * @generated
 */
public abstract class AbstractPointImpl extends MinimalEObjectImpl.Container implements AbstractPoint {
    /**
     * The default value of the '{@link #getX() <em>X</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getX()
     * @generated
     * @ordered
     */
    protected static final int X_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getX() <em>X</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getX()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int x = X_EDEFAULT;

    /**
     * The default value of the '{@link #getY() <em>Y</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getY()
     * @generated
     * @ordered
     */
    protected static final int Y_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getY() <em>Y</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getY()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int y = Y_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected AbstractPointImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return COREPackage.Literals.ABSTRACT_POINT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setX(int newX) {
        int oldX = x;
        x = newX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_POINT__X, oldX, x));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setY(int newY) {
        int oldY = y;
        y = newY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_POINT__Y, oldY, y));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case COREPackage.ABSTRACT_POINT__X:
            return getX();
        case COREPackage.ABSTRACT_POINT__Y:
            return getY();
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
        case COREPackage.ABSTRACT_POINT__X:
            setX((Integer) newValue);
            return;
        case COREPackage.ABSTRACT_POINT__Y:
            setY((Integer) newValue);
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
        case COREPackage.ABSTRACT_POINT__X:
            setX(X_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_POINT__Y:
            setY(Y_EDEFAULT);
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
        case COREPackage.ABSTRACT_POINT__X:
            return x != X_EDEFAULT;
        case COREPackage.ABSTRACT_POINT__Y:
            return y != Y_EDEFAULT;
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
        result.append(" (x: ");
        result.append(x);
        result.append(", y: ");
        result.append(y);
        result.append(')');
        return result.toString();
    }

} // AbstractPointImpl