/**
 */
package com.zipc.garden.model.arc.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.arc.ARCAbstractLine;
import com.zipc.garden.model.arc.ARCLine;
import com.zipc.garden.model.arc.ARCPackage;
import com.zipc.garden.model.arc.ARCState;

import com.zipc.garden.model.core.AbstractLine;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.core.LineType;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Line</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCLineImpl#getSourceAnchorX <em>Source Anchor X</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCLineImpl#getSourceAnchorY <em>Source Anchor Y</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCLineImpl#getTargetAnchorX <em>Target Anchor X</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCLineImpl#getTargetAnchorY <em>Target Anchor Y</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCLineImpl#getType <em>Type</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCLineImpl#getConnectionPoint <em>Connection Point</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCLineImpl#getVariableName <em>Variable Name</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCLineImpl#getVariableType <em>Variable Type</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCLineImpl#getX <em>X</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCLineImpl#getTarget <em>Target</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCLineImpl#getSource <em>Source</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCLineImpl#getY <em>Y</em>}</li>
 * </ul>
 * @generated
 */
public class ARCLineImpl extends ARCReferenceableImpl implements ARCLine {
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
     * The cached value of the '{@link #getConnectionPoint() <em>Connection Point</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see #getConnectionPoint()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<AbstractPoint> connectionPoint;

    /**
     * The default value of the '{@link #getVariableName() <em>Variable Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getVariableName()
     * @generated
     * @ordered
     */
    protected static final String VARIABLE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVariableName() <em>Variable Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getVariableName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String variableName = VARIABLE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getVariableType() <em>Variable Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getVariableType()
     * @generated
     * @ordered
     */
    protected static final String VARIABLE_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVariableType() <em>Variable Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getVariableType()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String variableType = VARIABLE_TYPE_EDEFAULT;

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
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected ARCState target;

    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected ARCState source;

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
    protected ARCLineImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ARCPackage.Literals.ARC_LINE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_LINE__SOURCE_ANCHOR_X, oldSourceAnchorX, sourceAnchorX));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_LINE__SOURCE_ANCHOR_Y, oldSourceAnchorY, sourceAnchorY));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_LINE__TARGET_ANCHOR_X, oldTargetAnchorX, targetAnchorX));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_LINE__TARGET_ANCHOR_Y, oldTargetAnchorY, targetAnchorY));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<AbstractPoint> getConnectionPoint() {
        if (connectionPoint == null) {
            connectionPoint = new EObjectResolvingEList<AbstractPoint>(AbstractPoint.class, this, ARCPackage.ARC_LINE__CONNECTION_POINT);
        }
        return connectionPoint;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_LINE__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getVariableName() {
        return variableName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setVariableName(String newVariableName) {
        String oldVariableName = variableName;
        variableName = newVariableName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_LINE__VARIABLE_NAME, oldVariableName, variableName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getVariableType() {
        return variableType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setVariableType(String newVariableType) {
        String oldVariableType = variableType;
        variableType = newVariableType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_LINE__VARIABLE_TYPE, oldVariableType, variableType));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_LINE__X, oldX, x));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ARCState getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject) target;
            target = (ARCState) eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ARCPackage.ARC_LINE__TARGET, oldTarget, target));
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ARCState basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTarget(ARCState newTarget, NotificationChain msgs) {
        ARCState oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_LINE__TARGET, oldTarget, newTarget);
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
    public void setTarget(ARCState newTarget) {
        if (newTarget != target) {
            NotificationChain msgs = null;
            if (target != null)
                msgs = ((InternalEObject) target).eInverseRemove(this, ARCPackage.ARC_STATE__INCOMING_LINE, ARCState.class, msgs);
            if (newTarget != null)
                msgs = ((InternalEObject) newTarget).eInverseAdd(this, ARCPackage.ARC_STATE__INCOMING_LINE, ARCState.class, msgs);
            msgs = basicSetTarget(newTarget, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_LINE__TARGET, newTarget, newTarget));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ARCState getSource() {
        if (source != null && source.eIsProxy()) {
            InternalEObject oldSource = (InternalEObject) source;
            source = (ARCState) eResolveProxy(oldSource);
            if (source != oldSource) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ARCPackage.ARC_LINE__SOURCE, oldSource, source));
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ARCState basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSource(ARCState newSource, NotificationChain msgs) {
        ARCState oldSource = source;
        source = newSource;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_LINE__SOURCE, oldSource, newSource);
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
    public void setSource(ARCState newSource) {
        if (newSource != source) {
            NotificationChain msgs = null;
            if (source != null)
                msgs = ((InternalEObject) source).eInverseRemove(this, ARCPackage.ARC_STATE__OUTGOING_LINE, ARCState.class, msgs);
            if (newSource != null)
                msgs = ((InternalEObject) newSource).eInverseAdd(this, ARCPackage.ARC_STATE__OUTGOING_LINE, ARCState.class, msgs);
            msgs = basicSetSource(newSource, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_LINE__SOURCE, newSource, newSource));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_LINE__Y, oldY, y));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ARCPackage.ARC_LINE__TARGET:
            if (target != null)
                msgs = ((InternalEObject) target).eInverseRemove(this, ARCPackage.ARC_STATE__INCOMING_LINE, ARCState.class, msgs);
            return basicSetTarget((ARCState) otherEnd, msgs);
        case ARCPackage.ARC_LINE__SOURCE:
            if (source != null)
                msgs = ((InternalEObject) source).eInverseRemove(this, ARCPackage.ARC_STATE__OUTGOING_LINE, ARCState.class, msgs);
            return basicSetSource((ARCState) otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ARCPackage.ARC_LINE__TARGET:
            return basicSetTarget(null, msgs);
        case ARCPackage.ARC_LINE__SOURCE:
            return basicSetSource(null, msgs);
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
        case ARCPackage.ARC_LINE__SOURCE_ANCHOR_X:
            return getSourceAnchorX();
        case ARCPackage.ARC_LINE__SOURCE_ANCHOR_Y:
            return getSourceAnchorY();
        case ARCPackage.ARC_LINE__TARGET_ANCHOR_X:
            return getTargetAnchorX();
        case ARCPackage.ARC_LINE__TARGET_ANCHOR_Y:
            return getTargetAnchorY();
        case ARCPackage.ARC_LINE__TYPE:
            return getType();
        case ARCPackage.ARC_LINE__CONNECTION_POINT:
            return getConnectionPoint();
        case ARCPackage.ARC_LINE__VARIABLE_NAME:
            return getVariableName();
        case ARCPackage.ARC_LINE__VARIABLE_TYPE:
            return getVariableType();
        case ARCPackage.ARC_LINE__X:
            return getX();
        case ARCPackage.ARC_LINE__TARGET:
            if (resolve)
                return getTarget();
            return basicGetTarget();
        case ARCPackage.ARC_LINE__SOURCE:
            if (resolve)
                return getSource();
            return basicGetSource();
        case ARCPackage.ARC_LINE__Y:
            return getY();
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
        case ARCPackage.ARC_LINE__SOURCE_ANCHOR_X:
            setSourceAnchorX((Double) newValue);
            return;
        case ARCPackage.ARC_LINE__SOURCE_ANCHOR_Y:
            setSourceAnchorY((Double) newValue);
            return;
        case ARCPackage.ARC_LINE__TARGET_ANCHOR_X:
            setTargetAnchorX((Double) newValue);
            return;
        case ARCPackage.ARC_LINE__TARGET_ANCHOR_Y:
            setTargetAnchorY((Double) newValue);
            return;
        case ARCPackage.ARC_LINE__TYPE:
            setType((LineType) newValue);
            return;
        case ARCPackage.ARC_LINE__CONNECTION_POINT:
            getConnectionPoint().clear();
            getConnectionPoint().addAll((Collection<? extends AbstractPoint>) newValue);
            return;
        case ARCPackage.ARC_LINE__VARIABLE_NAME:
            setVariableName((String) newValue);
            return;
        case ARCPackage.ARC_LINE__VARIABLE_TYPE:
            setVariableType((String) newValue);
            return;
        case ARCPackage.ARC_LINE__X:
            setX((Integer) newValue);
            return;
        case ARCPackage.ARC_LINE__TARGET:
            setTarget((ARCState) newValue);
            return;
        case ARCPackage.ARC_LINE__SOURCE:
            setSource((ARCState) newValue);
            return;
        case ARCPackage.ARC_LINE__Y:
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
        case ARCPackage.ARC_LINE__SOURCE_ANCHOR_X:
            setSourceAnchorX(SOURCE_ANCHOR_X_EDEFAULT);
            return;
        case ARCPackage.ARC_LINE__SOURCE_ANCHOR_Y:
            setSourceAnchorY(SOURCE_ANCHOR_Y_EDEFAULT);
            return;
        case ARCPackage.ARC_LINE__TARGET_ANCHOR_X:
            setTargetAnchorX(TARGET_ANCHOR_X_EDEFAULT);
            return;
        case ARCPackage.ARC_LINE__TARGET_ANCHOR_Y:
            setTargetAnchorY(TARGET_ANCHOR_Y_EDEFAULT);
            return;
        case ARCPackage.ARC_LINE__TYPE:
            setType(TYPE_EDEFAULT);
            return;
        case ARCPackage.ARC_LINE__CONNECTION_POINT:
            getConnectionPoint().clear();
            return;
        case ARCPackage.ARC_LINE__VARIABLE_NAME:
            setVariableName(VARIABLE_NAME_EDEFAULT);
            return;
        case ARCPackage.ARC_LINE__VARIABLE_TYPE:
            setVariableType(VARIABLE_TYPE_EDEFAULT);
            return;
        case ARCPackage.ARC_LINE__X:
            setX(X_EDEFAULT);
            return;
        case ARCPackage.ARC_LINE__TARGET:
            setTarget((ARCState) null);
            return;
        case ARCPackage.ARC_LINE__SOURCE:
            setSource((ARCState) null);
            return;
        case ARCPackage.ARC_LINE__Y:
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
        case ARCPackage.ARC_LINE__SOURCE_ANCHOR_X:
            return sourceAnchorX != SOURCE_ANCHOR_X_EDEFAULT;
        case ARCPackage.ARC_LINE__SOURCE_ANCHOR_Y:
            return sourceAnchorY != SOURCE_ANCHOR_Y_EDEFAULT;
        case ARCPackage.ARC_LINE__TARGET_ANCHOR_X:
            return targetAnchorX != TARGET_ANCHOR_X_EDEFAULT;
        case ARCPackage.ARC_LINE__TARGET_ANCHOR_Y:
            return targetAnchorY != TARGET_ANCHOR_Y_EDEFAULT;
        case ARCPackage.ARC_LINE__TYPE:
            return type != TYPE_EDEFAULT;
        case ARCPackage.ARC_LINE__CONNECTION_POINT:
            return connectionPoint != null && !connectionPoint.isEmpty();
        case ARCPackage.ARC_LINE__VARIABLE_NAME:
            return VARIABLE_NAME_EDEFAULT == null ? variableName != null : !VARIABLE_NAME_EDEFAULT.equals(variableName);
        case ARCPackage.ARC_LINE__VARIABLE_TYPE:
            return VARIABLE_TYPE_EDEFAULT == null ? variableType != null : !VARIABLE_TYPE_EDEFAULT.equals(variableType);
        case ARCPackage.ARC_LINE__X:
            return x != X_EDEFAULT;
        case ARCPackage.ARC_LINE__TARGET:
            return target != null;
        case ARCPackage.ARC_LINE__SOURCE:
            return source != null;
        case ARCPackage.ARC_LINE__Y:
            return y != Y_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == AbstractLine.class) {
            switch (derivedFeatureID) {
            case ARCPackage.ARC_LINE__SOURCE_ANCHOR_X:
                return COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_X;
            case ARCPackage.ARC_LINE__SOURCE_ANCHOR_Y:
                return COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_Y;
            case ARCPackage.ARC_LINE__TARGET_ANCHOR_X:
                return COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_X;
            case ARCPackage.ARC_LINE__TARGET_ANCHOR_Y:
                return COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_Y;
            case ARCPackage.ARC_LINE__TYPE:
                return COREPackage.ABSTRACT_LINE__TYPE;
            case ARCPackage.ARC_LINE__CONNECTION_POINT:
                return COREPackage.ABSTRACT_LINE__CONNECTION_POINT;
            default:
                return -1;
            }
        }
        if (baseClass == ARCAbstractLine.class) {
            switch (derivedFeatureID) {
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == AbstractLine.class) {
            switch (baseFeatureID) {
            case COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_X:
                return ARCPackage.ARC_LINE__SOURCE_ANCHOR_X;
            case COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_Y:
                return ARCPackage.ARC_LINE__SOURCE_ANCHOR_Y;
            case COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_X:
                return ARCPackage.ARC_LINE__TARGET_ANCHOR_X;
            case COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_Y:
                return ARCPackage.ARC_LINE__TARGET_ANCHOR_Y;
            case COREPackage.ABSTRACT_LINE__TYPE:
                return ARCPackage.ARC_LINE__TYPE;
            case COREPackage.ABSTRACT_LINE__CONNECTION_POINT:
                return ARCPackage.ARC_LINE__CONNECTION_POINT;
            default:
                return -1;
            }
        }
        if (baseClass == ARCAbstractLine.class) {
            switch (baseFeatureID) {
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(", variableName: ");
        result.append(variableName);
        result.append(", variableType: ");
        result.append(variableType);
        result.append(", x: ");
        result.append(x);
        result.append(", y: ");
        result.append(y);
        result.append(')');
        return result.toString();
    }

} // ARCLineImpl
