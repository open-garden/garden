/**
 */
package com.zipc.garden.model.core.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractNode;
import com.zipc.garden.model.core.COREPackage;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Diagram</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractDiagramImpl#getScrollX <em>Scroll X</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractDiagramImpl#getScrollY <em>Scroll Y</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractDiagramImpl#getPositionX <em>Position X</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractDiagramImpl#getPositionY <em>Position Y</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractDiagramImpl#getGridSize <em>Grid Size</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractDiagramImpl#getNodes <em>Nodes</em>}</li>
 * <li>{@link com.zipc.garden.model.core.impl.AbstractDiagramImpl#getLineMode <em>Line Mode</em>}</li>
 * </ul>
 * @generated
 */
public abstract class AbstractDiagramImpl extends MinimalEObjectImpl.Container implements AbstractDiagram {
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected AbstractDiagramImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return COREPackage.Literals.ABSTRACT_DIAGRAM;
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
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_DIAGRAM__SCROLL_X, oldScrollX, scrollX));
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
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_DIAGRAM__SCROLL_Y, oldScrollY, scrollY));
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
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_DIAGRAM__POSITION_X, oldPositionX, positionX));
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
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_DIAGRAM__POSITION_Y, oldPositionY, positionY));
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
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_DIAGRAM__GRID_SIZE, oldGridSize, gridSize));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<AbstractNode> getNodes() {
        if (nodes == null) {
            nodes = new EObjectContainmentEList<AbstractNode>(AbstractNode.class, this, COREPackage.ABSTRACT_DIAGRAM__NODES);
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
            eNotify(new ENotificationImpl(this, Notification.SET, COREPackage.ABSTRACT_DIAGRAM__LINE_MODE, oldLineMode, lineMode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case COREPackage.ABSTRACT_DIAGRAM__NODES:
            return ((InternalEList<?>) getNodes()).basicRemove(otherEnd, msgs);
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
        case COREPackage.ABSTRACT_DIAGRAM__SCROLL_X:
            return getScrollX();
        case COREPackage.ABSTRACT_DIAGRAM__SCROLL_Y:
            return getScrollY();
        case COREPackage.ABSTRACT_DIAGRAM__POSITION_X:
            return getPositionX();
        case COREPackage.ABSTRACT_DIAGRAM__POSITION_Y:
            return getPositionY();
        case COREPackage.ABSTRACT_DIAGRAM__GRID_SIZE:
            return getGridSize();
        case COREPackage.ABSTRACT_DIAGRAM__NODES:
            return getNodes();
        case COREPackage.ABSTRACT_DIAGRAM__LINE_MODE:
            return getLineMode();
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
        case COREPackage.ABSTRACT_DIAGRAM__SCROLL_X:
            setScrollX((Integer) newValue);
            return;
        case COREPackage.ABSTRACT_DIAGRAM__SCROLL_Y:
            setScrollY((Integer) newValue);
            return;
        case COREPackage.ABSTRACT_DIAGRAM__POSITION_X:
            setPositionX((Integer) newValue);
            return;
        case COREPackage.ABSTRACT_DIAGRAM__POSITION_Y:
            setPositionY((Integer) newValue);
            return;
        case COREPackage.ABSTRACT_DIAGRAM__GRID_SIZE:
            setGridSize((Integer) newValue);
            return;
        case COREPackage.ABSTRACT_DIAGRAM__NODES:
            getNodes().clear();
            getNodes().addAll((Collection<? extends AbstractNode>) newValue);
            return;
        case COREPackage.ABSTRACT_DIAGRAM__LINE_MODE:
            setLineMode((Integer) newValue);
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
        case COREPackage.ABSTRACT_DIAGRAM__SCROLL_X:
            setScrollX(SCROLL_X_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_DIAGRAM__SCROLL_Y:
            setScrollY(SCROLL_Y_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_DIAGRAM__POSITION_X:
            setPositionX(POSITION_X_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_DIAGRAM__POSITION_Y:
            setPositionY(POSITION_Y_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_DIAGRAM__GRID_SIZE:
            setGridSize(GRID_SIZE_EDEFAULT);
            return;
        case COREPackage.ABSTRACT_DIAGRAM__NODES:
            getNodes().clear();
            return;
        case COREPackage.ABSTRACT_DIAGRAM__LINE_MODE:
            setLineMode(LINE_MODE_EDEFAULT);
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
        case COREPackage.ABSTRACT_DIAGRAM__SCROLL_X:
            return scrollX != SCROLL_X_EDEFAULT;
        case COREPackage.ABSTRACT_DIAGRAM__SCROLL_Y:
            return scrollY != SCROLL_Y_EDEFAULT;
        case COREPackage.ABSTRACT_DIAGRAM__POSITION_X:
            return positionX != POSITION_X_EDEFAULT;
        case COREPackage.ABSTRACT_DIAGRAM__POSITION_Y:
            return positionY != POSITION_Y_EDEFAULT;
        case COREPackage.ABSTRACT_DIAGRAM__GRID_SIZE:
            return gridSize != GRID_SIZE_EDEFAULT;
        case COREPackage.ABSTRACT_DIAGRAM__NODES:
            return nodes != null && !nodes.isEmpty();
        case COREPackage.ABSTRACT_DIAGRAM__LINE_MODE:
            return lineMode != LINE_MODE_EDEFAULT;
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

} // AbstractDiagramImpl
