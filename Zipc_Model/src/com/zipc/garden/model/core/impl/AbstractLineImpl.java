/**
 */
package com.zipc.garden.model.core.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.AbstractLine;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.COREPackage;

import com.zipc.garden.model.core.LineType;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Line</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractLineImpl#getSourceAnchorX <em>Source Anchor X</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractLineImpl#getSourceAnchorY <em>Source Anchor Y</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractLineImpl#getTargetAnchorX <em>Target Anchor X</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractLineImpl#getTargetAnchorY <em>Target Anchor Y</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractLineImpl#getType <em>Type</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractLineImpl#getConnectionPoint <em>Connection Point</em>}</li>
 * </ul>
 * @generated
 */
public abstract class AbstractLineImpl extends MinimalEObjectImpl.Container implements AbstractLine {
    /**
     * The default value of the '{@link #getSourceAnchorX() <em>Source Anchor X</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSourceAnchorX()
     * @generated
     * @ordered
     */
    protected static final double SOURCE_ANCHOR_X_EDEFAULT = 0.5;

    /**
     * The cached value of the '{@link #getSourceAnchorX() <em>Source Anchor X</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSourceAnchorX()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected double sourceAnchorX = SOURCE_ANCHOR_X_EDEFAULT;

    /**
     * The default value of the '{@link #getSourceAnchorY() <em>Source Anchor Y</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSourceAnchorY()
     * @generated
     * @ordered
     */
    protected static final double SOURCE_ANCHOR_Y_EDEFAULT = 0.5;

    /**
     * The cached value of the '{@link #getSourceAnchorY() <em>Source Anchor Y</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSourceAnchorY()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected double sourceAnchorY = SOURCE_ANCHOR_Y_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetAnchorX() <em>Target Anchor X</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTargetAnchorX()
     * @generated
     * @ordered
     */
    protected static final double TARGET_ANCHOR_X_EDEFAULT = 0.5;

    /**
     * The cached value of the '{@link #getTargetAnchorX() <em>Target Anchor X</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTargetAnchorX()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected double targetAnchorX = TARGET_ANCHOR_X_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetAnchorY() <em>Target Anchor Y</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTargetAnchorY()
     * @generated
     * @ordered
     */
    protected static final double TARGET_ANCHOR_Y_EDEFAULT = 0.5;

    /**
     * The cached value of the '{@link #getTargetAnchorY() <em>Target Anchor Y</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTargetAnchorY()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected double targetAnchorY = TARGET_ANCHOR_Y_EDEFAULT;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final LineType TYPE_EDEFAULT = LineType.SIMPLE;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected LineType type = TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getConnectionPoint() <em>Connection Point</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConnectionPoint()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<AbstractPoint> connectionPoint;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected AbstractLineImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return COREPackage.Literals.ABSTRACT_LINE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public double getSourceAnchorX() {
        return sourceAnchorX;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSourceAnchorX(double newSourceAnchorX) {
        double oldSourceAnchorX = sourceAnchorX;
        sourceAnchorX = newSourceAnchorX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_X, oldSourceAnchorX, sourceAnchorX));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public double getSourceAnchorY() {
        return sourceAnchorY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSourceAnchorY(double newSourceAnchorY) {
        double oldSourceAnchorY = sourceAnchorY;
        sourceAnchorY = newSourceAnchorY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_Y, oldSourceAnchorY, sourceAnchorY));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public double getTargetAnchorX() {
        return targetAnchorX;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTargetAnchorX(double newTargetAnchorX) {
        double oldTargetAnchorX = targetAnchorX;
        targetAnchorX = newTargetAnchorX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_X, oldTargetAnchorX, targetAnchorX));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public double getTargetAnchorY() {
        return targetAnchorY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTargetAnchorY(double newTargetAnchorY) {
        double oldTargetAnchorY = targetAnchorY;
        targetAnchorY = newTargetAnchorY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_Y, oldTargetAnchorY, targetAnchorY));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public LineType getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setType(LineType newType) {
        LineType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_LINE__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<AbstractPoint> getConnectionPoint() {
        if (connectionPoint == null) {
            connectionPoint = new EObjectResolvingEList<AbstractPoint>(AbstractPoint.class, this, COREPackage.ABSTRACT_LINE__CONNECTION_POINT);
        }
        return connectionPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_X:
            return getSourceAnchorX();
        case COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_Y:
            return getSourceAnchorY();
        case COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_X:
            return getTargetAnchorX();
        case COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_Y:
            return getTargetAnchorY();
        case COREPackage.ABSTRACT_LINE__TYPE:
            return getType();
        case COREPackage.ABSTRACT_LINE__CONNECTION_POINT:
            return getConnectionPoint();
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
        case COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_X:
            setSourceAnchorX((Double) newValue);
            return;
        case COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_Y:
            setSourceAnchorY((Double) newValue);
            return;
        case COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_X:
            setTargetAnchorX((Double) newValue);
            return;
        case COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_Y:
            setTargetAnchorY((Double) newValue);
            return;
        case COREPackage.ABSTRACT_LINE__TYPE:
            setType((LineType) newValue);
            return;
        case COREPackage.ABSTRACT_LINE__CONNECTION_POINT:
            getConnectionPoint().clear();
            getConnectionPoint().addAll((Collection<? extends AbstractPoint>) newValue);
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
        case COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_X:
            setSourceAnchorX(SOURCE_ANCHOR_X_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_Y:
            setSourceAnchorY(SOURCE_ANCHOR_Y_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_X:
            setTargetAnchorX(TARGET_ANCHOR_X_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_Y:
            setTargetAnchorY(TARGET_ANCHOR_Y_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_LINE__TYPE:
            setType(TYPE_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_LINE__CONNECTION_POINT:
            getConnectionPoint().clear();
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
        case COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_X:
            return sourceAnchorX != SOURCE_ANCHOR_X_EDEFAULT;
        case COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_Y:
            return sourceAnchorY != SOURCE_ANCHOR_Y_EDEFAULT;
        case COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_X:
            return targetAnchorX != TARGET_ANCHOR_X_EDEFAULT;
        case COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_Y:
            return targetAnchorY != TARGET_ANCHOR_Y_EDEFAULT;
        case COREPackage.ABSTRACT_LINE__TYPE:
            return type != TYPE_EDEFAULT;
        case COREPackage.ABSTRACT_LINE__CONNECTION_POINT:
            return connectionPoint != null && !connectionPoint.isEmpty();
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
        result.append(" (sourceAnchorX: ");
        result.append(sourceAnchorX);
        result.append(", sourceAnchorY: ");
        result.append(sourceAnchorY);
        result.append(", targetAnchorX: ");
        result.append(targetAnchorX);
        result.append(", targetAnchorY: ");
        result.append(targetAnchorY);
        result.append(", type: ");
        result.append(type);
        result.append(')');
        return result.toString();
    }

} // AbstractLineImpl
