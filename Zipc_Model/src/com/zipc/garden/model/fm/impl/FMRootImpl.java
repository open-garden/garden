/**
 */
package com.zipc.garden.model.fm.impl;

import com.google.gwt.user.client.rpc.GwtTransient;
import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractNode;
import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.core.impl.AbstractRootElementImpl;

import com.zipc.garden.model.fm.FMConstraint;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMPackage;
import com.zipc.garden.model.fm.FMProperty;
import com.zipc.garden.model.fm.FMRoot;

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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fm.impl.FMRootImpl#getScrollX <em>Scroll X</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMRootImpl#getScrollY <em>Scroll Y</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMRootImpl#getPositionX <em>Position X</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMRootImpl#getPositionY <em>Position Y</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMRootImpl#getGridSize <em>Grid Size</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMRootImpl#getNodes <em>Nodes</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMRootImpl#getLineMode <em>Line Mode</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMRootImpl#getNode <em>Node</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMRootImpl#getProperties <em>Properties</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMRootImpl#getConstraints <em>Constraints</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMRootImpl#getAutoLayout <em>Auto Layout</em>}</li>
 * </ul>
 * @generated
 */
public class FMRootImpl extends AbstractRootElementImpl implements FMRoot {
    /**
     * The default value of the '{@link #getScrollX() <em>Scroll X</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getScrollX()
     * @generated
     * @ordered
     */
    protected static final int SCROLL_X_EDEFAULT = 1000;

    /**
     * The cached value of the '{@link #getScrollX() <em>Scroll X</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getScrollX()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int scrollX = SCROLL_X_EDEFAULT;

    /**
     * The default value of the '{@link #getScrollY() <em>Scroll Y</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getScrollY()
     * @generated
     * @ordered
     */
    protected static final int SCROLL_Y_EDEFAULT = 700;

    /**
     * The cached value of the '{@link #getScrollY() <em>Scroll Y</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getScrollY()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int scrollY = SCROLL_Y_EDEFAULT;

    /**
     * The default value of the '{@link #getPositionX() <em>Position X</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getPositionX()
     * @generated
     * @ordered
     */
    protected static final int POSITION_X_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getPositionX() <em>Position X</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getPositionX()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int positionX = POSITION_X_EDEFAULT;

    /**
     * The default value of the '{@link #getPositionY() <em>Position Y</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getPositionY()
     * @generated
     * @ordered
     */
    protected static final int POSITION_Y_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getPositionY() <em>Position Y</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getPositionY()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int positionY = POSITION_Y_EDEFAULT;

    /**
     * The default value of the '{@link #getGridSize() <em>Grid Size</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getGridSize()
     * @generated
     * @ordered
     */
    protected static final int GRID_SIZE_EDEFAULT = 20;

    /**
     * The cached value of the '{@link #getGridSize() <em>Grid Size</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getGridSize()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int gridSize = GRID_SIZE_EDEFAULT;

    /**
     * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getNodes()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<AbstractNode> nodes;

    /**
     * The default value of the '{@link #getLineMode() <em>Line Mode</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getLineMode()
     * @generated
     * @ordered
     */
    protected static final int LINE_MODE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getLineMode() <em>Line Mode</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getLineMode()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int lineMode = LINE_MODE_EDEFAULT;

    /**
     * The cached value of the '{@link #getNode() <em>Node</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getNode()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected FMNode node;

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
     * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getConstraints()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FMConstraint> constraints;

    /**
     * The default value of the '{@link #getAutoLayout() <em>Auto Layout</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getAutoLayout()
     * @generated
     * @ordered
     */
    protected static final int AUTO_LAYOUT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getAutoLayout() <em>Auto Layout</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getAutoLayout()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int autoLayout = AUTO_LAYOUT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FMRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FMPackage.Literals.FM_ROOT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getScrollX() {
        return scrollX;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setScrollX(int newScrollX) {
        int oldScrollX = scrollX;
        scrollX = newScrollX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_ROOT__SCROLL_X, oldScrollX, scrollX));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getScrollY() {
        return scrollY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setScrollY(int newScrollY) {
        int oldScrollY = scrollY;
        scrollY = newScrollY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_ROOT__SCROLL_Y, oldScrollY, scrollY));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getPositionX() {
        return positionX;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setPositionX(int newPositionX) {
        int oldPositionX = positionX;
        positionX = newPositionX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_ROOT__POSITION_X, oldPositionX, positionX));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getPositionY() {
        return positionY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setPositionY(int newPositionY) {
        int oldPositionY = positionY;
        positionY = newPositionY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_ROOT__POSITION_Y, oldPositionY, positionY));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMNode getNode() {
        return node;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNode(FMNode newNode, NotificationChain msgs) {
        FMNode oldNode = node;
        node = newNode;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FMPackage.FM_ROOT__NODE, oldNode, newNode);
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
    public void setNode(FMNode newNode) {
        if (newNode != node) {
            NotificationChain msgs = null;
            if (node != null)
                msgs = ((InternalEObject) node).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FMPackage.FM_ROOT__NODE, null, msgs);
            if (newNode != null)
                msgs = ((InternalEObject) newNode).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FMPackage.FM_ROOT__NODE, null, msgs);
            msgs = basicSetNode(newNode, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_ROOT__NODE, newNode, newNode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FMProperty> getProperties() {
        if (properties == null) {
            properties = new EObjectContainmentEList<FMProperty>(FMProperty.class, this, FMPackage.FM_ROOT__PROPERTIES);
        }
        return properties;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FMConstraint> getConstraints() {
        if (constraints == null) {
            constraints = new EObjectContainmentEList<FMConstraint>(FMConstraint.class, this, FMPackage.FM_ROOT__CONSTRAINTS);
        }
        return constraints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getAutoLayout() {
        return autoLayout;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAutoLayout(int newAutoLayout) {
        int oldAutoLayout = autoLayout;
        autoLayout = newAutoLayout;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_ROOT__AUTO_LAYOUT, oldAutoLayout, autoLayout));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getGridSize() {
        return gridSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setGridSize(int newGridSize) {
        int oldGridSize = gridSize;
        gridSize = newGridSize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_ROOT__GRID_SIZE, oldGridSize, gridSize));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<AbstractNode> getNodes() {
        if (nodes == null) {
            nodes = new EObjectContainmentEList<AbstractNode>(AbstractNode.class, this, FMPackage.FM_ROOT__NODES);
        }
        return nodes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getLineMode() {
        return lineMode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLineMode(int newLineMode) {
        int oldLineMode = lineMode;
        lineMode = newLineMode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_ROOT__LINE_MODE, oldLineMode, lineMode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FMPackage.FM_ROOT__NODES:
            return ((InternalEList<?>) getNodes()).basicRemove(otherEnd, msgs);
        case FMPackage.FM_ROOT__NODE:
            return basicSetNode(null, msgs);
        case FMPackage.FM_ROOT__PROPERTIES:
            return ((InternalEList<?>) getProperties()).basicRemove(otherEnd, msgs);
        case FMPackage.FM_ROOT__CONSTRAINTS:
            return ((InternalEList<?>) getConstraints()).basicRemove(otherEnd, msgs);
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
        case FMPackage.FM_ROOT__SCROLL_X:
            return getScrollX();
        case FMPackage.FM_ROOT__SCROLL_Y:
            return getScrollY();
        case FMPackage.FM_ROOT__POSITION_X:
            return getPositionX();
        case FMPackage.FM_ROOT__POSITION_Y:
            return getPositionY();
        case FMPackage.FM_ROOT__GRID_SIZE:
            return getGridSize();
        case FMPackage.FM_ROOT__NODES:
            return getNodes();
        case FMPackage.FM_ROOT__LINE_MODE:
            return getLineMode();
        case FMPackage.FM_ROOT__NODE:
            return getNode();
        case FMPackage.FM_ROOT__PROPERTIES:
            return getProperties();
        case FMPackage.FM_ROOT__CONSTRAINTS:
            return getConstraints();
        case FMPackage.FM_ROOT__AUTO_LAYOUT:
            return getAutoLayout();
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
        case FMPackage.FM_ROOT__SCROLL_X:
            setScrollX((Integer) newValue);
            return;
        case FMPackage.FM_ROOT__SCROLL_Y:
            setScrollY((Integer) newValue);
            return;
        case FMPackage.FM_ROOT__POSITION_X:
            setPositionX((Integer) newValue);
            return;
        case FMPackage.FM_ROOT__POSITION_Y:
            setPositionY((Integer) newValue);
            return;
        case FMPackage.FM_ROOT__GRID_SIZE:
            setGridSize((Integer) newValue);
            return;
        case FMPackage.FM_ROOT__NODES:
            getNodes().clear();
            getNodes().addAll((Collection<? extends AbstractNode>) newValue);
            return;
        case FMPackage.FM_ROOT__LINE_MODE:
            setLineMode((Integer) newValue);
            return;
        case FMPackage.FM_ROOT__NODE:
            setNode((FMNode) newValue);
            return;
        case FMPackage.FM_ROOT__PROPERTIES:
            getProperties().clear();
            getProperties().addAll((Collection<? extends FMProperty>) newValue);
            return;
        case FMPackage.FM_ROOT__CONSTRAINTS:
            getConstraints().clear();
            getConstraints().addAll((Collection<? extends FMConstraint>) newValue);
            return;
        case FMPackage.FM_ROOT__AUTO_LAYOUT:
            setAutoLayout((Integer) newValue);
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
        case FMPackage.FM_ROOT__SCROLL_X:
            setScrollX(SCROLL_X_EDEFAULT);
            return;
        case FMPackage.FM_ROOT__SCROLL_Y:
            setScrollY(SCROLL_Y_EDEFAULT);
            return;
        case FMPackage.FM_ROOT__POSITION_X:
            setPositionX(POSITION_X_EDEFAULT);
            return;
        case FMPackage.FM_ROOT__POSITION_Y:
            setPositionY(POSITION_Y_EDEFAULT);
            return;
        case FMPackage.FM_ROOT__GRID_SIZE:
            setGridSize(GRID_SIZE_EDEFAULT);
            return;
        case FMPackage.FM_ROOT__NODES:
            getNodes().clear();
            return;
        case FMPackage.FM_ROOT__LINE_MODE:
            setLineMode(LINE_MODE_EDEFAULT);
            return;
        case FMPackage.FM_ROOT__NODE:
            setNode((FMNode) null);
            return;
        case FMPackage.FM_ROOT__PROPERTIES:
            getProperties().clear();
            return;
        case FMPackage.FM_ROOT__CONSTRAINTS:
            getConstraints().clear();
            return;
        case FMPackage.FM_ROOT__AUTO_LAYOUT:
            setAutoLayout(AUTO_LAYOUT_EDEFAULT);
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
        case FMPackage.FM_ROOT__SCROLL_X:
            return scrollX != SCROLL_X_EDEFAULT;
        case FMPackage.FM_ROOT__SCROLL_Y:
            return scrollY != SCROLL_Y_EDEFAULT;
        case FMPackage.FM_ROOT__POSITION_X:
            return positionX != POSITION_X_EDEFAULT;
        case FMPackage.FM_ROOT__POSITION_Y:
            return positionY != POSITION_Y_EDEFAULT;
        case FMPackage.FM_ROOT__GRID_SIZE:
            return gridSize != GRID_SIZE_EDEFAULT;
        case FMPackage.FM_ROOT__NODES:
            return nodes != null && !nodes.isEmpty();
        case FMPackage.FM_ROOT__LINE_MODE:
            return lineMode != LINE_MODE_EDEFAULT;
        case FMPackage.FM_ROOT__NODE:
            return node != null;
        case FMPackage.FM_ROOT__PROPERTIES:
            return properties != null && !properties.isEmpty();
        case FMPackage.FM_ROOT__CONSTRAINTS:
            return constraints != null && !constraints.isEmpty();
        case FMPackage.FM_ROOT__AUTO_LAYOUT:
            return autoLayout != AUTO_LAYOUT_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == AbstractDiagram.class) {
            switch (derivedFeatureID) {
            case FMPackage.FM_ROOT__SCROLL_X:
                return COREPackage.ABSTRACT_DIAGRAM__SCROLL_X;
            case FMPackage.FM_ROOT__SCROLL_Y:
                return COREPackage.ABSTRACT_DIAGRAM__SCROLL_Y;
            case FMPackage.FM_ROOT__POSITION_X:
                return COREPackage.ABSTRACT_DIAGRAM__POSITION_X;
            case FMPackage.FM_ROOT__POSITION_Y:
                return COREPackage.ABSTRACT_DIAGRAM__POSITION_Y;
            case FMPackage.FM_ROOT__GRID_SIZE:
                return COREPackage.ABSTRACT_DIAGRAM__GRID_SIZE;
            case FMPackage.FM_ROOT__NODES:
                return COREPackage.ABSTRACT_DIAGRAM__NODES;
            case FMPackage.FM_ROOT__LINE_MODE:
                return COREPackage.ABSTRACT_DIAGRAM__LINE_MODE;
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
        if (baseClass == AbstractDiagram.class) {
            switch (baseFeatureID) {
            case COREPackage.ABSTRACT_DIAGRAM__SCROLL_X:
                return FMPackage.FM_ROOT__SCROLL_X;
            case COREPackage.ABSTRACT_DIAGRAM__SCROLL_Y:
                return FMPackage.FM_ROOT__SCROLL_Y;
            case COREPackage.ABSTRACT_DIAGRAM__POSITION_X:
                return FMPackage.FM_ROOT__POSITION_X;
            case COREPackage.ABSTRACT_DIAGRAM__POSITION_Y:
                return FMPackage.FM_ROOT__POSITION_Y;
            case COREPackage.ABSTRACT_DIAGRAM__GRID_SIZE:
                return FMPackage.FM_ROOT__GRID_SIZE;
            case COREPackage.ABSTRACT_DIAGRAM__NODES:
                return FMPackage.FM_ROOT__NODES;
            case COREPackage.ABSTRACT_DIAGRAM__LINE_MODE:
                return FMPackage.FM_ROOT__LINE_MODE;
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
        result.append(" (scrollX: ");
        result.append(scrollX);
        result.append(", scrollY: ");
        result.append(scrollY);
        result.append(", positionX: ");
        result.append(positionX);
        result.append(", positionY: ");
        result.append(positionY);
        result.append(", gridSize: ");
        result.append(gridSize);
        result.append(", lineMode: ");
        result.append(lineMode);
        result.append(", autoLayout: ");
        result.append(autoLayout);
        result.append(')');
        return result.toString();
    }

} // FMRootImpl
