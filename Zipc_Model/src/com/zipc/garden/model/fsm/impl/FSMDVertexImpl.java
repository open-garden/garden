/**
 */
package com.zipc.garden.model.fsm.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.fsm.FSMDLine;
import com.zipc.garden.model.fsm.FSMDProperty;
import com.zipc.garden.model.fsm.FSMDVertex;
import com.zipc.garden.model.fsm.FSMPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>DVertex</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDVertexImpl#getTop <em>Top</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDVertexImpl#getLeft <em>Left</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDVertexImpl#getHeight <em>Height</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDVertexImpl#getWidth <em>Width</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDVertexImpl#getProperties <em>Properties</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDVertexImpl#getBackground <em>Background</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDVertexImpl#getForeground <em>Foreground</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDVertexImpl#getIncomingLine <em>Incoming Line</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDVertexImpl#getOutgoingLine <em>Outgoing Line</em>}</li>
 * </ul>
 * @generated
 */
public abstract class FSMDVertexImpl extends FSMDReferenceableImpl implements FSMDVertex {
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
     * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getProperties()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FSMDProperty> properties;

    /**
     * The default value of the '{@link #getBackground() <em>Background</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getBackground()
     * @generated
     * @ordered
     */
    protected static final String BACKGROUND_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBackground() <em>Background</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getBackground()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String background = BACKGROUND_EDEFAULT;

    /**
     * The default value of the '{@link #getForeground() <em>Foreground</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getForeground()
     * @generated
     * @ordered
     */
    protected static final String FOREGROUND_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getForeground() <em>Foreground</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getForeground()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String foreground = FOREGROUND_EDEFAULT;

    /**
     * The cached value of the '{@link #getIncomingLine() <em>Incoming Line</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getIncomingLine()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FSMDLine> incomingLine;

    /**
     * The cached value of the '{@link #getOutgoingLine() <em>Outgoing Line</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getOutgoingLine()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected FSMDLine outgoingLine;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FSMDVertexImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FSMPackage.Literals.FSM_DVERTEX;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DVERTEX__TOP, oldTop, top));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DVERTEX__LEFT, oldLeft, left));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DVERTEX__HEIGHT, oldHeight, height));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DVERTEX__WIDTH, oldWidth, width));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FSMDProperty> getProperties() {
        if (properties == null) {
            properties = new EObjectContainmentEList<FSMDProperty>(FSMDProperty.class, this, FSMPackage.FSM_DVERTEX__PROPERTIES);
        }
        return properties;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getBackground() {
        return background;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBackground(String newBackground) {
        String oldBackground = background;
        background = newBackground;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DVERTEX__BACKGROUND, oldBackground, background));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getForeground() {
        return foreground;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setForeground(String newForeground) {
        String oldForeground = foreground;
        foreground = newForeground;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DVERTEX__FOREGROUND, oldForeground, foreground));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FSMDLine> getIncomingLine() {
        if (incomingLine == null) {
            incomingLine = new EObjectWithInverseResolvingEList<FSMDLine>(FSMDLine.class, this, FSMPackage.FSM_DVERTEX__INCOMING_LINE, FSMPackage.FSM_DLINE__TARGET);
        }
        return incomingLine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMDLine getOutgoingLine() {
        if (outgoingLine != null && outgoingLine.eIsProxy()) {
            InternalEObject oldOutgoingLine = (InternalEObject) outgoingLine;
            outgoingLine = (FSMDLine) eResolveProxy(oldOutgoingLine);
            if (outgoingLine != oldOutgoingLine) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FSMPackage.FSM_DVERTEX__OUTGOING_LINE, oldOutgoingLine, outgoingLine));
            }
        }
        return outgoingLine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FSMDLine basicGetOutgoingLine() {
        return outgoingLine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOutgoingLine(FSMDLine newOutgoingLine, NotificationChain msgs) {
        FSMDLine oldOutgoingLine = outgoingLine;
        outgoingLine = newOutgoingLine;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DVERTEX__OUTGOING_LINE, oldOutgoingLine, newOutgoingLine);
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
    public void setOutgoingLine(FSMDLine newOutgoingLine) {
        if (newOutgoingLine != outgoingLine) {
            NotificationChain msgs = null;
            if (outgoingLine != null)
                msgs = ((InternalEObject) outgoingLine).eInverseRemove(this, FSMPackage.FSM_DLINE__SOURCE, FSMDLine.class, msgs);
            if (newOutgoingLine != null)
                msgs = ((InternalEObject) newOutgoingLine).eInverseAdd(this, FSMPackage.FSM_DLINE__SOURCE, FSMDLine.class, msgs);
            msgs = basicSetOutgoingLine(newOutgoingLine, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DVERTEX__OUTGOING_LINE, newOutgoingLine, newOutgoingLine));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FSMPackage.FSM_DVERTEX__INCOMING_LINE:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getIncomingLine()).basicAdd(otherEnd, msgs);
        case FSMPackage.FSM_DVERTEX__OUTGOING_LINE:
            if (outgoingLine != null)
                msgs = ((InternalEObject) outgoingLine).eInverseRemove(this, FSMPackage.FSM_DLINE__SOURCE, FSMDLine.class, msgs);
            return basicSetOutgoingLine((FSMDLine) otherEnd, msgs);
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
        case FSMPackage.FSM_DVERTEX__PROPERTIES:
            return ((InternalEList<?>) getProperties()).basicRemove(otherEnd, msgs);
        case FSMPackage.FSM_DVERTEX__INCOMING_LINE:
            return ((InternalEList<?>) getIncomingLine()).basicRemove(otherEnd, msgs);
        case FSMPackage.FSM_DVERTEX__OUTGOING_LINE:
            return basicSetOutgoingLine(null, msgs);
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
        case FSMPackage.FSM_DVERTEX__TOP:
            return getTop();
        case FSMPackage.FSM_DVERTEX__LEFT:
            return getLeft();
        case FSMPackage.FSM_DVERTEX__HEIGHT:
            return getHeight();
        case FSMPackage.FSM_DVERTEX__WIDTH:
            return getWidth();
        case FSMPackage.FSM_DVERTEX__PROPERTIES:
            return getProperties();
        case FSMPackage.FSM_DVERTEX__BACKGROUND:
            return getBackground();
        case FSMPackage.FSM_DVERTEX__FOREGROUND:
            return getForeground();
        case FSMPackage.FSM_DVERTEX__INCOMING_LINE:
            return getIncomingLine();
        case FSMPackage.FSM_DVERTEX__OUTGOING_LINE:
            if (resolve)
                return getOutgoingLine();
            return basicGetOutgoingLine();
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
        case FSMPackage.FSM_DVERTEX__TOP:
            setTop((Integer) newValue);
            return;
        case FSMPackage.FSM_DVERTEX__LEFT:
            setLeft((Integer) newValue);
            return;
        case FSMPackage.FSM_DVERTEX__HEIGHT:
            setHeight((Integer) newValue);
            return;
        case FSMPackage.FSM_DVERTEX__WIDTH:
            setWidth((Integer) newValue);
            return;
        case FSMPackage.FSM_DVERTEX__PROPERTIES:
            getProperties().clear();
            getProperties().addAll((Collection<? extends FSMDProperty>) newValue);
            return;
        case FSMPackage.FSM_DVERTEX__BACKGROUND:
            setBackground((String) newValue);
            return;
        case FSMPackage.FSM_DVERTEX__FOREGROUND:
            setForeground((String) newValue);
            return;
        case FSMPackage.FSM_DVERTEX__INCOMING_LINE:
            getIncomingLine().clear();
            getIncomingLine().addAll((Collection<? extends FSMDLine>) newValue);
            return;
        case FSMPackage.FSM_DVERTEX__OUTGOING_LINE:
            setOutgoingLine((FSMDLine) newValue);
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
        case FSMPackage.FSM_DVERTEX__TOP:
            setTop(TOP_EDEFAULT);
            return;
        case FSMPackage.FSM_DVERTEX__LEFT:
            setLeft(LEFT_EDEFAULT);
            return;
        case FSMPackage.FSM_DVERTEX__HEIGHT:
            setHeight(HEIGHT_EDEFAULT);
            return;
        case FSMPackage.FSM_DVERTEX__WIDTH:
            setWidth(WIDTH_EDEFAULT);
            return;
        case FSMPackage.FSM_DVERTEX__PROPERTIES:
            getProperties().clear();
            return;
        case FSMPackage.FSM_DVERTEX__BACKGROUND:
            setBackground(BACKGROUND_EDEFAULT);
            return;
        case FSMPackage.FSM_DVERTEX__FOREGROUND:
            setForeground(FOREGROUND_EDEFAULT);
            return;
        case FSMPackage.FSM_DVERTEX__INCOMING_LINE:
            getIncomingLine().clear();
            return;
        case FSMPackage.FSM_DVERTEX__OUTGOING_LINE:
            setOutgoingLine((FSMDLine) null);
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
        case FSMPackage.FSM_DVERTEX__TOP:
            return top != TOP_EDEFAULT;
        case FSMPackage.FSM_DVERTEX__LEFT:
            return left != LEFT_EDEFAULT;
        case FSMPackage.FSM_DVERTEX__HEIGHT:
            return height != HEIGHT_EDEFAULT;
        case FSMPackage.FSM_DVERTEX__WIDTH:
            return width != WIDTH_EDEFAULT;
        case FSMPackage.FSM_DVERTEX__PROPERTIES:
            return properties != null && !properties.isEmpty();
        case FSMPackage.FSM_DVERTEX__BACKGROUND:
            return BACKGROUND_EDEFAULT == null ? background != null : !BACKGROUND_EDEFAULT.equals(background);
        case FSMPackage.FSM_DVERTEX__FOREGROUND:
            return FOREGROUND_EDEFAULT == null ? foreground != null : !FOREGROUND_EDEFAULT.equals(foreground);
        case FSMPackage.FSM_DVERTEX__INCOMING_LINE:
            return incomingLine != null && !incomingLine.isEmpty();
        case FSMPackage.FSM_DVERTEX__OUTGOING_LINE:
            return outgoingLine != null;
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
        result.append(", background: ");
        result.append(background);
        result.append(", foreground: ");
        result.append(foreground);
        result.append(')');
        return result.toString();
    }

} // FSMDVertexImpl
