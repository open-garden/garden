/**
 */
package com.zipc.garden.model.arc.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.arc.ARCPackage;
import com.zipc.garden.model.arc.ARCVertex;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Vertex</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCVertexImpl#getTop <em>Top</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCVertexImpl#getLeft <em>Left</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCVertexImpl#getHeight <em>Height</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCVertexImpl#getWidth <em>Width</em>}</li>
 * </ul>
 * @generated
 */
public abstract class ARCVertexImpl extends ARCReferenceableImpl implements ARCVertex {
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
    protected ARCVertexImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ARCPackage.Literals.ARC_VERTEX;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_VERTEX__TOP, oldTop, top));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_VERTEX__LEFT, oldLeft, left));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_VERTEX__HEIGHT, oldHeight, height));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_VERTEX__WIDTH, oldWidth, width));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ARCPackage.ARC_VERTEX__TOP:
            return getTop();
        case ARCPackage.ARC_VERTEX__LEFT:
            return getLeft();
        case ARCPackage.ARC_VERTEX__HEIGHT:
            return getHeight();
        case ARCPackage.ARC_VERTEX__WIDTH:
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
        case ARCPackage.ARC_VERTEX__TOP:
            setTop((Integer) newValue);
            return;
        case ARCPackage.ARC_VERTEX__LEFT:
            setLeft((Integer) newValue);
            return;
        case ARCPackage.ARC_VERTEX__HEIGHT:
            setHeight((Integer) newValue);
            return;
        case ARCPackage.ARC_VERTEX__WIDTH:
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
        case ARCPackage.ARC_VERTEX__TOP:
            setTop(TOP_EDEFAULT);
            return;
        case ARCPackage.ARC_VERTEX__LEFT:
            setLeft(LEFT_EDEFAULT);
            return;
        case ARCPackage.ARC_VERTEX__HEIGHT:
            setHeight(HEIGHT_EDEFAULT);
            return;
        case ARCPackage.ARC_VERTEX__WIDTH:
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
        case ARCPackage.ARC_VERTEX__TOP:
            return top != TOP_EDEFAULT;
        case ARCPackage.ARC_VERTEX__LEFT:
            return left != LEFT_EDEFAULT;
        case ARCPackage.ARC_VERTEX__HEIGHT:
            return height != HEIGHT_EDEFAULT;
        case ARCPackage.ARC_VERTEX__WIDTH:
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

} // ARCVertexImpl
