/**
 */
package com.zipc.garden.model.fm.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.impl.AbstractStyleImpl;
import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMPackage;
import com.zipc.garden.model.fm.FMProperty;

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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getChildren <em>Children</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getTop <em>Top</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getLeft <em>Left</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getHeight <em>Height</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getWidth <em>Width</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getRef <em>Ref</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getRefName <em>Ref Name</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getRefInfo <em>Ref Info</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getChildType <em>Child Type</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getProperties <em>Properties</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getMin <em>Min</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getMax <em>Max</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getX <em>X</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getY <em>Y</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#getRefuuid <em>Refuuid</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMNodeImpl#isOptional <em>Optional</em>}</li>
 * </ul>
 * @generated
 */
public class FMNodeImpl extends AbstractStyleImpl implements FMNode {
    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildren()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FMNode> children;

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
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getRef() <em>Ref</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRef()
     * @generated
     * @ordered
     */
    protected static final long REF_EDEFAULT = 0L;

    /**
     * The cached value of the '{@link #getRef() <em>Ref</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRef()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long ref = REF_EDEFAULT;

    /**
     * The default value of the '{@link #getRefName() <em>Ref Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getRefName()
     * @generated
     * @ordered
     */
    protected static final String REF_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRefName() <em>Ref Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getRefName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String refName = REF_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getRefInfo() <em>Ref Info</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getRefInfo()
     * @generated
     * @ordered
     */
    protected static final String REF_INFO_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRefInfo() <em>Ref Info</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getRefInfo()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String refInfo = REF_INFO_EDEFAULT;

    /**
     * The default value of the '{@link #getChildType() <em>Child Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getChildType()
     * @generated
     * @ordered
     */
    protected static final ChildType CHILD_TYPE_EDEFAULT = ChildType.AND;

    /**
     * The cached value of the '{@link #getChildType() <em>Child Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getChildType()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected ChildType childType = CHILD_TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getProperties()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FMProperty> properties;

    /**
     * The default value of the '{@link #getMin() <em>Min</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMin()
     * @generated
     * @ordered
     */
    protected static final int MIN_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getMin() <em>Min</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMin()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int min = MIN_EDEFAULT;

    /**
     * The default value of the '{@link #getMax() <em>Max</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMax()
     * @generated
     * @ordered
     */
    protected static final int MAX_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getMax() <em>Max</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMax()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int max = MAX_EDEFAULT;

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
     * The default value of the '{@link #getRefuuid() <em>Refuuid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getRefuuid()
     * @generated
     * @ordered
     */
    protected static final String REFUUID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRefuuid() <em>Refuuid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getRefuuid()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String refuuid = REFUUID_EDEFAULT;

    /**
     * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #isOptional()
     * @generated
     * @ordered
     */
    protected static final boolean OPTIONAL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #isOptional()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected boolean optional = OPTIONAL_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FMNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FMPackage.Literals.FM_NODE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FMNode> getChildren() {
        if (children == null) {
            children = new EObjectContainmentEList<FMNode>(FMNode.class, this, FMPackage.FM_NODE__CHILDREN);
        }
        return children;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__TOP, oldTop, top));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__LEFT, oldLeft, left));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__HEIGHT, oldHeight, height));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__WIDTH, oldWidth, width));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getRef() {
        return ref;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRef(long newRef) {
        long oldRef = ref;
        ref = newRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__REF, oldRef, ref));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getRefName() {
        return refName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRefName(String newRefName) {
        String oldRefName = refName;
        refName = newRefName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__REF_NAME, oldRefName, refName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getRefInfo() {
        return refInfo;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRefInfo(String newRefInfo) {
        String oldRefInfo = refInfo;
        refInfo = newRefInfo;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__REF_INFO, oldRefInfo, refInfo));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ChildType getChildType() {
        return childType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setChildType(ChildType newChildType) {
        ChildType oldChildType = childType;
        childType = newChildType == null ? CHILD_TYPE_EDEFAULT : newChildType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__CHILD_TYPE, oldChildType, childType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FMProperty> getProperties() {
        if (properties == null) {
            properties = new EObjectContainmentEList<FMProperty>(FMProperty.class, this, FMPackage.FM_NODE__PROPERTIES);
        }
        return properties;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getMin() {
        return min;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMin(int newMin) {
        int oldMin = min;
        min = newMin;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__MIN, oldMin, min));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getMax() {
        return max;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMax(int newMax) {
        int oldMax = max;
        max = newMax;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__MAX, oldMax, max));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__X, oldX, x));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__Y, oldY, y));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getRefuuid() {
        return refuuid;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRefuuid(String newRefuuid) {
        String oldRefuuid = refuuid;
        refuuid = newRefuuid;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__REFUUID, oldRefuuid, refuuid));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isOptional() {
        return optional;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setOptional(boolean newOptional) {
        boolean oldOptional = optional;
        optional = newOptional;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_NODE__OPTIONAL, oldOptional, optional));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FMPackage.FM_NODE__CHILDREN:
            return ((InternalEList<?>) getChildren()).basicRemove(otherEnd, msgs);
        case FMPackage.FM_NODE__PROPERTIES:
            return ((InternalEList<?>) getProperties()).basicRemove(otherEnd, msgs);
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
        case FMPackage.FM_NODE__CHILDREN:
            return getChildren();
        case FMPackage.FM_NODE__TOP:
            return getTop();
        case FMPackage.FM_NODE__LEFT:
            return getLeft();
        case FMPackage.FM_NODE__HEIGHT:
            return getHeight();
        case FMPackage.FM_NODE__WIDTH:
            return getWidth();
        case FMPackage.FM_NODE__NAME:
            return getName();
        case FMPackage.FM_NODE__REF:
            return getRef();
        case FMPackage.FM_NODE__REF_NAME:
            return getRefName();
        case FMPackage.FM_NODE__REF_INFO:
            return getRefInfo();
        case FMPackage.FM_NODE__CHILD_TYPE:
            return getChildType();
        case FMPackage.FM_NODE__PROPERTIES:
            return getProperties();
        case FMPackage.FM_NODE__MIN:
            return getMin();
        case FMPackage.FM_NODE__MAX:
            return getMax();
        case FMPackage.FM_NODE__X:
            return getX();
        case FMPackage.FM_NODE__Y:
            return getY();
        case FMPackage.FM_NODE__REFUUID:
            return getRefuuid();
        case FMPackage.FM_NODE__OPTIONAL:
            return isOptional();
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
        case FMPackage.FM_NODE__CHILDREN:
            getChildren().clear();
            getChildren().addAll((Collection<? extends FMNode>) newValue);
            return;
        case FMPackage.FM_NODE__TOP:
            setTop((Integer) newValue);
            return;
        case FMPackage.FM_NODE__LEFT:
            setLeft((Integer) newValue);
            return;
        case FMPackage.FM_NODE__HEIGHT:
            setHeight((Integer) newValue);
            return;
        case FMPackage.FM_NODE__WIDTH:
            setWidth((Integer) newValue);
            return;
        case FMPackage.FM_NODE__NAME:
            setName((String) newValue);
            return;
        case FMPackage.FM_NODE__REF:
            setRef((Long) newValue);
            return;
        case FMPackage.FM_NODE__REF_NAME:
            setRefName((String) newValue);
            return;
        case FMPackage.FM_NODE__REF_INFO:
            setRefInfo((String) newValue);
            return;
        case FMPackage.FM_NODE__CHILD_TYPE:
            setChildType((ChildType) newValue);
            return;
        case FMPackage.FM_NODE__PROPERTIES:
            getProperties().clear();
            getProperties().addAll((Collection<? extends FMProperty>) newValue);
            return;
        case FMPackage.FM_NODE__MIN:
            setMin((Integer) newValue);
            return;
        case FMPackage.FM_NODE__MAX:
            setMax((Integer) newValue);
            return;
        case FMPackage.FM_NODE__X:
            setX((Integer) newValue);
            return;
        case FMPackage.FM_NODE__Y:
            setY((Integer) newValue);
            return;
        case FMPackage.FM_NODE__REFUUID:
            setRefuuid((String) newValue);
            return;
        case FMPackage.FM_NODE__OPTIONAL:
            setOptional((Boolean) newValue);
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
        case FMPackage.FM_NODE__CHILDREN:
            getChildren().clear();
            return;
        case FMPackage.FM_NODE__TOP:
            setTop(TOP_EDEFAULT);
            return;
        case FMPackage.FM_NODE__LEFT:
            setLeft(LEFT_EDEFAULT);
            return;
        case FMPackage.FM_NODE__HEIGHT:
            setHeight(HEIGHT_EDEFAULT);
            return;
        case FMPackage.FM_NODE__WIDTH:
            setWidth(WIDTH_EDEFAULT);
            return;
        case FMPackage.FM_NODE__NAME:
            setName(NAME_EDEFAULT);
            return;
        case FMPackage.FM_NODE__REF:
            setRef(REF_EDEFAULT);
            return;
        case FMPackage.FM_NODE__REF_NAME:
            setRefName(REF_NAME_EDEFAULT);
            return;
        case FMPackage.FM_NODE__REF_INFO:
            setRefInfo(REF_INFO_EDEFAULT);
            return;
        case FMPackage.FM_NODE__CHILD_TYPE:
            setChildType(CHILD_TYPE_EDEFAULT);
            return;
        case FMPackage.FM_NODE__PROPERTIES:
            getProperties().clear();
            return;
        case FMPackage.FM_NODE__MIN:
            setMin(MIN_EDEFAULT);
            return;
        case FMPackage.FM_NODE__MAX:
            setMax(MAX_EDEFAULT);
            return;
        case FMPackage.FM_NODE__X:
            setX(X_EDEFAULT);
            return;
        case FMPackage.FM_NODE__Y:
            setY(Y_EDEFAULT);
            return;
        case FMPackage.FM_NODE__REFUUID:
            setRefuuid(REFUUID_EDEFAULT);
            return;
        case FMPackage.FM_NODE__OPTIONAL:
            setOptional(OPTIONAL_EDEFAULT);
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
        case FMPackage.FM_NODE__CHILDREN:
            return children != null && !children.isEmpty();
        case FMPackage.FM_NODE__TOP:
            return top != TOP_EDEFAULT;
        case FMPackage.FM_NODE__LEFT:
            return left != LEFT_EDEFAULT;
        case FMPackage.FM_NODE__HEIGHT:
            return height != HEIGHT_EDEFAULT;
        case FMPackage.FM_NODE__WIDTH:
            return width != WIDTH_EDEFAULT;
        case FMPackage.FM_NODE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case FMPackage.FM_NODE__REF:
            return ref != REF_EDEFAULT;
        case FMPackage.FM_NODE__REF_NAME:
            return REF_NAME_EDEFAULT == null ? refName != null : !REF_NAME_EDEFAULT.equals(refName);
        case FMPackage.FM_NODE__REF_INFO:
            return REF_INFO_EDEFAULT == null ? refInfo != null : !REF_INFO_EDEFAULT.equals(refInfo);
        case FMPackage.FM_NODE__CHILD_TYPE:
            return childType != CHILD_TYPE_EDEFAULT;
        case FMPackage.FM_NODE__PROPERTIES:
            return properties != null && !properties.isEmpty();
        case FMPackage.FM_NODE__MIN:
            return min != MIN_EDEFAULT;
        case FMPackage.FM_NODE__MAX:
            return max != MAX_EDEFAULT;
        case FMPackage.FM_NODE__X:
            return x != X_EDEFAULT;
        case FMPackage.FM_NODE__Y:
            return y != Y_EDEFAULT;
        case FMPackage.FM_NODE__REFUUID:
            return REFUUID_EDEFAULT == null ? refuuid != null : !REFUUID_EDEFAULT.equals(refuuid);
        case FMPackage.FM_NODE__OPTIONAL:
            return optional != OPTIONAL_EDEFAULT;
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
        result.append(", name: ");
        result.append(name);
        result.append(", ref: ");
        result.append(ref);
        result.append(", refName: ");
        result.append(refName);
        result.append(", refInfo: ");
        result.append(refInfo);
        result.append(", childType: ");
        result.append(childType);
        result.append(", min: ");
        result.append(min);
        result.append(", max: ");
        result.append(max);
        result.append(", x: ");
        result.append(x);
        result.append(", y: ");
        result.append(y);
        result.append(", refuuid: ");
        result.append(refuuid);
        result.append(", optional: ");
        result.append(optional);
        result.append(')');
        return result.toString();
    }

} // FMNodeImpl
