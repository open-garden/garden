/**
 */
package com.zipc.garden.model.fsm.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractNode;
import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.core.impl.AbstractRootElementImpl;

import com.zipc.garden.model.fsm.FSMDAction;
import com.zipc.garden.model.fsm.FSMDEvent;
import com.zipc.garden.model.fsm.FSMDLine;
import com.zipc.garden.model.fsm.FSMDMemo;
import com.zipc.garden.model.fsm.FSMDProperty;
import com.zipc.garden.model.fsm.FSMDRegion;
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.model.fsm.FSMDTransition;
import com.zipc.garden.model.fsm.FSMDVariable;
import com.zipc.garden.model.fsm.FSMPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>DState Machine</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getScrollX <em>Scroll X</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getScrollY <em>Scroll Y</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getPositionX <em>Position X</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getPositionY <em>Position Y</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getGridSize <em>Grid Size</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getNodes <em>Nodes</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getLineMode <em>Line Mode</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getProperties <em>Properties</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getTransitions <em>Transitions</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getMemos <em>Memos</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getRegions <em>Regions</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getFmsdevent <em>Fmsdevent</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getActions <em>Actions</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getLines <em>Lines</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getVariables <em>Variables</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl#getManhattanMode <em>Manhattan Mode</em>}</li>
 * </ul>
 * @generated
 */
public class FSMDStateMachineImpl extends AbstractRootElementImpl implements FSMDStateMachine {
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
     * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getProperties()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FSMDProperty> properties;

    /**
     * The cached value of the '{@link #getTransitions() <em>Transitions</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getTransitions()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FSMDTransition> transitions;

    /**
     * The cached value of the '{@link #getMemos() <em>Memos</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getMemos()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FSMDMemo> memos;

    /**
     * The cached value of the '{@link #getRegions() <em>Regions</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getRegions()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FSMDRegion> regions;

    /**
     * The cached value of the '{@link #getFmsdevent() <em>Fmsdevent</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFmsdevent()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FSMDEvent> fmsdevent;

    /**
     * The cached value of the '{@link #getActions() <em>Actions</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getActions()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FSMDAction> actions;

    /**
     * The cached value of the '{@link #getLines() <em>Lines</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getLines()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FSMDLine> lines;

    /**
     * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariables()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FSMDVariable> variables;

    /**
     * The default value of the '{@link #getManhattanMode() <em>Manhattan Mode</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getManhattanMode()
     * @generated
     * @ordered
     */
    protected static final int MANHATTAN_MODE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getManhattanMode() <em>Manhattan Mode</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getManhattanMode()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int manhattanMode = MANHATTAN_MODE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FSMDStateMachineImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FSMPackage.Literals.FSM_DSTATE_MACHINE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE_MACHINE__SCROLL_X, oldScrollX, scrollX));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE_MACHINE__SCROLL_Y, oldScrollY, scrollY));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE_MACHINE__POSITION_X, oldPositionX, positionX));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE_MACHINE__POSITION_Y, oldPositionY, positionY));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FSMDProperty> getProperties() {
        if (properties == null) {
            properties = new EObjectContainmentEList<FSMDProperty>(FSMDProperty.class, this, FSMPackage.FSM_DSTATE_MACHINE__PROPERTIES);
        }
        return properties;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FSMDTransition> getTransitions() {
        if (transitions == null) {
            transitions = new EObjectContainmentEList<FSMDTransition>(FSMDTransition.class, this, FSMPackage.FSM_DSTATE_MACHINE__TRANSITIONS);
        }
        return transitions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FSMDMemo> getMemos() {
        if (memos == null) {
            memos = new EObjectContainmentEList<FSMDMemo>(FSMDMemo.class, this, FSMPackage.FSM_DSTATE_MACHINE__MEMOS);
        }
        return memos;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FSMDRegion> getRegions() {
        if (regions == null) {
            regions = new EObjectContainmentEList<FSMDRegion>(FSMDRegion.class, this, FSMPackage.FSM_DSTATE_MACHINE__REGIONS);
        }
        return regions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FSMDEvent> getFmsdevent() {
        if (fmsdevent == null) {
            fmsdevent = new EObjectContainmentEList<FSMDEvent>(FSMDEvent.class, this, FSMPackage.FSM_DSTATE_MACHINE__FMSDEVENT);
        }
        return fmsdevent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FSMDAction> getActions() {
        if (actions == null) {
            actions = new EObjectResolvingEList<FSMDAction>(FSMDAction.class, this, FSMPackage.FSM_DSTATE_MACHINE__ACTIONS);
        }
        return actions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FSMDLine> getLines() {
        if (lines == null) {
            lines = new EObjectContainmentEList<FSMDLine>(FSMDLine.class, this, FSMPackage.FSM_DSTATE_MACHINE__LINES);
        }
        return lines;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE_MACHINE__GRID_SIZE, oldGridSize, gridSize));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<AbstractNode> getNodes() {
        if (nodes == null) {
            nodes = new EObjectContainmentEList<AbstractNode>(AbstractNode.class, this, FSMPackage.FSM_DSTATE_MACHINE__NODES);
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
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE_MACHINE__LINE_MODE, oldLineMode, lineMode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FSMDVariable> getVariables() {
        if (variables == null) {
            variables = new EObjectContainmentEList<FSMDVariable>(FSMDVariable.class, this, FSMPackage.FSM_DSTATE_MACHINE__VARIABLES);
        }
        return variables;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getManhattanMode() {
        return manhattanMode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setManhattanMode(int newManhattanMode) {
        int oldManhattanMode = manhattanMode;
        manhattanMode = newManhattanMode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.FSM_DSTATE_MACHINE__MANHATTAN_MODE, oldManhattanMode, manhattanMode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FSMPackage.FSM_DSTATE_MACHINE__NODES:
            return ((InternalEList<?>) getNodes()).basicRemove(otherEnd, msgs);
        case FSMPackage.FSM_DSTATE_MACHINE__PROPERTIES:
            return ((InternalEList<?>) getProperties()).basicRemove(otherEnd, msgs);
        case FSMPackage.FSM_DSTATE_MACHINE__TRANSITIONS:
            return ((InternalEList<?>) getTransitions()).basicRemove(otherEnd, msgs);
        case FSMPackage.FSM_DSTATE_MACHINE__MEMOS:
            return ((InternalEList<?>) getMemos()).basicRemove(otherEnd, msgs);
        case FSMPackage.FSM_DSTATE_MACHINE__REGIONS:
            return ((InternalEList<?>) getRegions()).basicRemove(otherEnd, msgs);
        case FSMPackage.FSM_DSTATE_MACHINE__FMSDEVENT:
            return ((InternalEList<?>) getFmsdevent()).basicRemove(otherEnd, msgs);
        case FSMPackage.FSM_DSTATE_MACHINE__LINES:
            return ((InternalEList<?>) getLines()).basicRemove(otherEnd, msgs);
        case FSMPackage.FSM_DSTATE_MACHINE__VARIABLES:
            return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
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
        case FSMPackage.FSM_DSTATE_MACHINE__SCROLL_X:
            return getScrollX();
        case FSMPackage.FSM_DSTATE_MACHINE__SCROLL_Y:
            return getScrollY();
        case FSMPackage.FSM_DSTATE_MACHINE__POSITION_X:
            return getPositionX();
        case FSMPackage.FSM_DSTATE_MACHINE__POSITION_Y:
            return getPositionY();
        case FSMPackage.FSM_DSTATE_MACHINE__GRID_SIZE:
            return getGridSize();
        case FSMPackage.FSM_DSTATE_MACHINE__NODES:
            return getNodes();
        case FSMPackage.FSM_DSTATE_MACHINE__LINE_MODE:
            return getLineMode();
        case FSMPackage.FSM_DSTATE_MACHINE__PROPERTIES:
            return getProperties();
        case FSMPackage.FSM_DSTATE_MACHINE__TRANSITIONS:
            return getTransitions();
        case FSMPackage.FSM_DSTATE_MACHINE__MEMOS:
            return getMemos();
        case FSMPackage.FSM_DSTATE_MACHINE__REGIONS:
            return getRegions();
        case FSMPackage.FSM_DSTATE_MACHINE__FMSDEVENT:
            return getFmsdevent();
        case FSMPackage.FSM_DSTATE_MACHINE__ACTIONS:
            return getActions();
        case FSMPackage.FSM_DSTATE_MACHINE__LINES:
            return getLines();
        case FSMPackage.FSM_DSTATE_MACHINE__VARIABLES:
            return getVariables();
        case FSMPackage.FSM_DSTATE_MACHINE__MANHATTAN_MODE:
            return getManhattanMode();
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
        case FSMPackage.FSM_DSTATE_MACHINE__SCROLL_X:
            setScrollX((Integer) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__SCROLL_Y:
            setScrollY((Integer) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__POSITION_X:
            setPositionX((Integer) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__POSITION_Y:
            setPositionY((Integer) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__GRID_SIZE:
            setGridSize((Integer) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__NODES:
            getNodes().clear();
            getNodes().addAll((Collection<? extends AbstractNode>) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__LINE_MODE:
            setLineMode((Integer) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__PROPERTIES:
            getProperties().clear();
            getProperties().addAll((Collection<? extends FSMDProperty>) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__TRANSITIONS:
            getTransitions().clear();
            getTransitions().addAll((Collection<? extends FSMDTransition>) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__MEMOS:
            getMemos().clear();
            getMemos().addAll((Collection<? extends FSMDMemo>) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__REGIONS:
            getRegions().clear();
            getRegions().addAll((Collection<? extends FSMDRegion>) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__FMSDEVENT:
            getFmsdevent().clear();
            getFmsdevent().addAll((Collection<? extends FSMDEvent>) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__ACTIONS:
            getActions().clear();
            getActions().addAll((Collection<? extends FSMDAction>) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__LINES:
            getLines().clear();
            getLines().addAll((Collection<? extends FSMDLine>) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__VARIABLES:
            getVariables().clear();
            getVariables().addAll((Collection<? extends FSMDVariable>) newValue);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__MANHATTAN_MODE:
            setManhattanMode((Integer) newValue);
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
        case FSMPackage.FSM_DSTATE_MACHINE__SCROLL_X:
            setScrollX(SCROLL_X_EDEFAULT);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__SCROLL_Y:
            setScrollY(SCROLL_Y_EDEFAULT);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__POSITION_X:
            setPositionX(POSITION_X_EDEFAULT);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__POSITION_Y:
            setPositionY(POSITION_Y_EDEFAULT);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__GRID_SIZE:
            setGridSize(GRID_SIZE_EDEFAULT);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__NODES:
            getNodes().clear();
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__LINE_MODE:
            setLineMode(LINE_MODE_EDEFAULT);
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__PROPERTIES:
            getProperties().clear();
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__TRANSITIONS:
            getTransitions().clear();
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__MEMOS:
            getMemos().clear();
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__REGIONS:
            getRegions().clear();
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__FMSDEVENT:
            getFmsdevent().clear();
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__ACTIONS:
            getActions().clear();
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__LINES:
            getLines().clear();
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__VARIABLES:
            getVariables().clear();
            return;
        case FSMPackage.FSM_DSTATE_MACHINE__MANHATTAN_MODE:
            setManhattanMode(MANHATTAN_MODE_EDEFAULT);
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
        case FSMPackage.FSM_DSTATE_MACHINE__SCROLL_X:
            return scrollX != SCROLL_X_EDEFAULT;
        case FSMPackage.FSM_DSTATE_MACHINE__SCROLL_Y:
            return scrollY != SCROLL_Y_EDEFAULT;
        case FSMPackage.FSM_DSTATE_MACHINE__POSITION_X:
            return positionX != POSITION_X_EDEFAULT;
        case FSMPackage.FSM_DSTATE_MACHINE__POSITION_Y:
            return positionY != POSITION_Y_EDEFAULT;
        case FSMPackage.FSM_DSTATE_MACHINE__GRID_SIZE:
            return gridSize != GRID_SIZE_EDEFAULT;
        case FSMPackage.FSM_DSTATE_MACHINE__NODES:
            return nodes != null && !nodes.isEmpty();
        case FSMPackage.FSM_DSTATE_MACHINE__LINE_MODE:
            return lineMode != LINE_MODE_EDEFAULT;
        case FSMPackage.FSM_DSTATE_MACHINE__PROPERTIES:
            return properties != null && !properties.isEmpty();
        case FSMPackage.FSM_DSTATE_MACHINE__TRANSITIONS:
            return transitions != null && !transitions.isEmpty();
        case FSMPackage.FSM_DSTATE_MACHINE__MEMOS:
            return memos != null && !memos.isEmpty();
        case FSMPackage.FSM_DSTATE_MACHINE__REGIONS:
            return regions != null && !regions.isEmpty();
        case FSMPackage.FSM_DSTATE_MACHINE__FMSDEVENT:
            return fmsdevent != null && !fmsdevent.isEmpty();
        case FSMPackage.FSM_DSTATE_MACHINE__ACTIONS:
            return actions != null && !actions.isEmpty();
        case FSMPackage.FSM_DSTATE_MACHINE__LINES:
            return lines != null && !lines.isEmpty();
        case FSMPackage.FSM_DSTATE_MACHINE__VARIABLES:
            return variables != null && !variables.isEmpty();
        case FSMPackage.FSM_DSTATE_MACHINE__MANHATTAN_MODE:
            return manhattanMode != MANHATTAN_MODE_EDEFAULT;
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
            case FSMPackage.FSM_DSTATE_MACHINE__SCROLL_X:
                return COREPackage.ABSTRACT_DIAGRAM__SCROLL_X;
            case FSMPackage.FSM_DSTATE_MACHINE__SCROLL_Y:
                return COREPackage.ABSTRACT_DIAGRAM__SCROLL_Y;
            case FSMPackage.FSM_DSTATE_MACHINE__POSITION_X:
                return COREPackage.ABSTRACT_DIAGRAM__POSITION_X;
            case FSMPackage.FSM_DSTATE_MACHINE__POSITION_Y:
                return COREPackage.ABSTRACT_DIAGRAM__POSITION_Y;
            case FSMPackage.FSM_DSTATE_MACHINE__GRID_SIZE:
                return COREPackage.ABSTRACT_DIAGRAM__GRID_SIZE;
            case FSMPackage.FSM_DSTATE_MACHINE__NODES:
                return COREPackage.ABSTRACT_DIAGRAM__NODES;
            case FSMPackage.FSM_DSTATE_MACHINE__LINE_MODE:
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
                return FSMPackage.FSM_DSTATE_MACHINE__SCROLL_X;
            case COREPackage.ABSTRACT_DIAGRAM__SCROLL_Y:
                return FSMPackage.FSM_DSTATE_MACHINE__SCROLL_Y;
            case COREPackage.ABSTRACT_DIAGRAM__POSITION_X:
                return FSMPackage.FSM_DSTATE_MACHINE__POSITION_X;
            case COREPackage.ABSTRACT_DIAGRAM__POSITION_Y:
                return FSMPackage.FSM_DSTATE_MACHINE__POSITION_Y;
            case COREPackage.ABSTRACT_DIAGRAM__GRID_SIZE:
                return FSMPackage.FSM_DSTATE_MACHINE__GRID_SIZE;
            case COREPackage.ABSTRACT_DIAGRAM__NODES:
                return FSMPackage.FSM_DSTATE_MACHINE__NODES;
            case COREPackage.ABSTRACT_DIAGRAM__LINE_MODE:
                return FSMPackage.FSM_DSTATE_MACHINE__LINE_MODE;
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
        result.append(", manhattanMode: ");
        result.append(manhattanMode);
        result.append(')');
        return result.toString();
    }

} // FSMDStateMachineImpl
