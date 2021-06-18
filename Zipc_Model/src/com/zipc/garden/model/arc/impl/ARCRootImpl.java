/**
 */
package com.zipc.garden.model.arc.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.arc.ARCLine;
import com.zipc.garden.model.arc.ARCPackage;
import com.zipc.garden.model.arc.ARCRoot;
import com.zipc.garden.model.arc.ARCState;

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractNode;
import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.core.impl.AbstractRootElementImpl;

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
 * <li>{@link com.zipc.garden.model.arc.impl.ARCRootImpl#getScrollX <em>Scroll X</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCRootImpl#getScrollY <em>Scroll Y</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCRootImpl#getPositionX <em>Position X</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCRootImpl#getPositionY <em>Position Y</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCRootImpl#getGridSize <em>Grid Size</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCRootImpl#getNodes <em>Nodes</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCRootImpl#getLineMode <em>Line Mode</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCRootImpl#getLines <em>Lines</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.impl.ARCRootImpl#getStates <em>States</em>}</li>
 * </ul>
 * @generated
 */
public class ARCRootImpl extends AbstractRootElementImpl implements ARCRoot {
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
     * The cached value of the '{@link #getLines() <em>Lines</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getLines()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<ARCLine> lines;

    /**
     * The cached value of the '{@link #getStates() <em>States</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getStates()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<ARCState> states;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected ARCRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ARCPackage.Literals.ARC_ROOT;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_ROOT__SCROLL_X, oldScrollX, scrollX));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_ROOT__SCROLL_Y, oldScrollY, scrollY));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_ROOT__POSITION_X, oldPositionX, positionX));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_ROOT__POSITION_Y, oldPositionY, positionY));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_ROOT__GRID_SIZE, oldGridSize, gridSize));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<AbstractNode> getNodes() {
        if (nodes == null) {
            nodes = new EObjectContainmentEList<AbstractNode>(AbstractNode.class, this, ARCPackage.ARC_ROOT__NODES);
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
            eNotify(new ENotificationImpl(this, Notification.SET, ARCPackage.ARC_ROOT__LINE_MODE, oldLineMode, lineMode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<ARCLine> getLines() {
        if (lines == null) {
            lines = new EObjectContainmentEList<ARCLine>(ARCLine.class, this, ARCPackage.ARC_ROOT__LINES);
        }
        return lines;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<ARCState> getStates() {
        if (states == null) {
            states = new EObjectContainmentEList<ARCState>(ARCState.class, this, ARCPackage.ARC_ROOT__STATES);
        }
        return states;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ARCPackage.ARC_ROOT__NODES:
            return ((InternalEList<?>) getNodes()).basicRemove(otherEnd, msgs);
        case ARCPackage.ARC_ROOT__LINES:
            return ((InternalEList<?>) getLines()).basicRemove(otherEnd, msgs);
        case ARCPackage.ARC_ROOT__STATES:
            return ((InternalEList<?>) getStates()).basicRemove(otherEnd, msgs);
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
        case ARCPackage.ARC_ROOT__SCROLL_X:
            return getScrollX();
        case ARCPackage.ARC_ROOT__SCROLL_Y:
            return getScrollY();
        case ARCPackage.ARC_ROOT__POSITION_X:
            return getPositionX();
        case ARCPackage.ARC_ROOT__POSITION_Y:
            return getPositionY();
        case ARCPackage.ARC_ROOT__GRID_SIZE:
            return getGridSize();
        case ARCPackage.ARC_ROOT__NODES:
            return getNodes();
        case ARCPackage.ARC_ROOT__LINE_MODE:
            return getLineMode();
        case ARCPackage.ARC_ROOT__LINES:
            return getLines();
        case ARCPackage.ARC_ROOT__STATES:
            return getStates();
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
        case ARCPackage.ARC_ROOT__SCROLL_X:
            setScrollX((Integer) newValue);
            return;
        case ARCPackage.ARC_ROOT__SCROLL_Y:
            setScrollY((Integer) newValue);
            return;
        case ARCPackage.ARC_ROOT__POSITION_X:
            setPositionX((Integer) newValue);
            return;
        case ARCPackage.ARC_ROOT__POSITION_Y:
            setPositionY((Integer) newValue);
            return;
        case ARCPackage.ARC_ROOT__GRID_SIZE:
            setGridSize((Integer) newValue);
            return;
        case ARCPackage.ARC_ROOT__NODES:
            getNodes().clear();
            getNodes().addAll((Collection<? extends AbstractNode>) newValue);
            return;
        case ARCPackage.ARC_ROOT__LINE_MODE:
            setLineMode((Integer) newValue);
            return;
        case ARCPackage.ARC_ROOT__LINES:
            getLines().clear();
            getLines().addAll((Collection<? extends ARCLine>) newValue);
            return;
        case ARCPackage.ARC_ROOT__STATES:
            getStates().clear();
            getStates().addAll((Collection<? extends ARCState>) newValue);
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
        case ARCPackage.ARC_ROOT__SCROLL_X:
            setScrollX(SCROLL_X_EDEFAULT);
            return;
        case ARCPackage.ARC_ROOT__SCROLL_Y:
            setScrollY(SCROLL_Y_EDEFAULT);
            return;
        case ARCPackage.ARC_ROOT__POSITION_X:
            setPositionX(POSITION_X_EDEFAULT);
            return;
        case ARCPackage.ARC_ROOT__POSITION_Y:
            setPositionY(POSITION_Y_EDEFAULT);
            return;
        case ARCPackage.ARC_ROOT__GRID_SIZE:
            setGridSize(GRID_SIZE_EDEFAULT);
            return;
        case ARCPackage.ARC_ROOT__NODES:
            getNodes().clear();
            return;
        case ARCPackage.ARC_ROOT__LINE_MODE:
            setLineMode(LINE_MODE_EDEFAULT);
            return;
        case ARCPackage.ARC_ROOT__LINES:
            getLines().clear();
            return;
        case ARCPackage.ARC_ROOT__STATES:
            getStates().clear();
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
        case ARCPackage.ARC_ROOT__SCROLL_X:
            return scrollX != SCROLL_X_EDEFAULT;
        case ARCPackage.ARC_ROOT__SCROLL_Y:
            return scrollY != SCROLL_Y_EDEFAULT;
        case ARCPackage.ARC_ROOT__POSITION_X:
            return positionX != POSITION_X_EDEFAULT;
        case ARCPackage.ARC_ROOT__POSITION_Y:
            return positionY != POSITION_Y_EDEFAULT;
        case ARCPackage.ARC_ROOT__GRID_SIZE:
            return gridSize != GRID_SIZE_EDEFAULT;
        case ARCPackage.ARC_ROOT__NODES:
            return nodes != null && !nodes.isEmpty();
        case ARCPackage.ARC_ROOT__LINE_MODE:
            return lineMode != LINE_MODE_EDEFAULT;
        case ARCPackage.ARC_ROOT__LINES:
            return lines != null && !lines.isEmpty();
        case ARCPackage.ARC_ROOT__STATES:
            return states != null && !states.isEmpty();
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
            case ARCPackage.ARC_ROOT__SCROLL_X:
                return COREPackage.ABSTRACT_DIAGRAM__SCROLL_X;
            case ARCPackage.ARC_ROOT__SCROLL_Y:
                return COREPackage.ABSTRACT_DIAGRAM__SCROLL_Y;
            case ARCPackage.ARC_ROOT__POSITION_X:
                return COREPackage.ABSTRACT_DIAGRAM__POSITION_X;
            case ARCPackage.ARC_ROOT__POSITION_Y:
                return COREPackage.ABSTRACT_DIAGRAM__POSITION_Y;
            case ARCPackage.ARC_ROOT__GRID_SIZE:
                return COREPackage.ABSTRACT_DIAGRAM__GRID_SIZE;
            case ARCPackage.ARC_ROOT__NODES:
                return COREPackage.ABSTRACT_DIAGRAM__NODES;
            case ARCPackage.ARC_ROOT__LINE_MODE:
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
                return ARCPackage.ARC_ROOT__SCROLL_X;
            case COREPackage.ABSTRACT_DIAGRAM__SCROLL_Y:
                return ARCPackage.ARC_ROOT__SCROLL_Y;
            case COREPackage.ABSTRACT_DIAGRAM__POSITION_X:
                return ARCPackage.ARC_ROOT__POSITION_X;
            case COREPackage.ABSTRACT_DIAGRAM__POSITION_Y:
                return ARCPackage.ARC_ROOT__POSITION_Y;
            case COREPackage.ABSTRACT_DIAGRAM__GRID_SIZE:
                return ARCPackage.ARC_ROOT__GRID_SIZE;
            case COREPackage.ABSTRACT_DIAGRAM__NODES:
                return ARCPackage.ARC_ROOT__NODES;
            case COREPackage.ABSTRACT_DIAGRAM__LINE_MODE:
                return ARCPackage.ARC_ROOT__LINE_MODE;
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
        result.append(')');
        return result.toString();
    }

} // ARCRootImpl
