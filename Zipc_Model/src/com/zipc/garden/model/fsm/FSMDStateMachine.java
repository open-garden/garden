/**
 */
package com.zipc.garden.model.fsm;

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractRootElement;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>DState Machine</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.FSMDStateMachine#getProperties <em>Properties</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDStateMachine#getTransitions <em>Transitions</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDStateMachine#getMemos <em>Memos</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDStateMachine#getRegions <em>Regions</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDStateMachine#getFmsdevent <em>Fmsdevent</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDStateMachine#getActions <em>Actions</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDStateMachine#getLines <em>Lines</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDStateMachine#getVariables <em>Variables</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDStateMachine#getManhattanMode <em>Manhattan Mode</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDStateMachine()
 * @model
 * @generated
 */
public interface FSMDStateMachine extends AbstractRootElement, AbstractDiagram {
    /**
     * Returns the value of the '<em><b>Properties</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fsm.FSMDProperty}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Properties</em>' containment reference list.
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDStateMachine_Properties()
     * @model containment="true"
     * @generated
     */
    EList<FSMDProperty> getProperties();

    /**
     * Returns the value of the '<em><b>Transitions</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fsm.FSMDTransition}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Transitions</em>' containment reference list.
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDStateMachine_Transitions()
     * @model containment="true"
     * @generated
     */
    EList<FSMDTransition> getTransitions();

    /**
     * Returns the value of the '<em><b>Memos</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fsm.FSMDMemo}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Memos</em>' containment reference list.
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDStateMachine_Memos()
     * @model containment="true"
     * @generated
     */
    EList<FSMDMemo> getMemos();

    /**
     * Returns the value of the '<em><b>Regions</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fsm.FSMDRegion}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Regions</em>' containment reference list.
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDStateMachine_Regions()
     * @model containment="true"
     * @generated
     */
    EList<FSMDRegion> getRegions();

    /**
     * Returns the value of the '<em><b>Fmsdevent</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fsm.FSMDEvent}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Fmsdevent</em>' containment reference list.
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDStateMachine_Fmsdevent()
     * @model containment="true"
     * @generated
     */
    EList<FSMDEvent> getFmsdevent();

    /**
     * Returns the value of the '<em><b>Actions</b></em>' reference list. The list contents are of type
     * {@link com.zipc.garden.model.fsm.FSMDAction}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Actions</em>' reference list.
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDStateMachine_Actions()
     * @model
     * @generated
     */
    EList<FSMDAction> getActions();

    /**
     * Returns the value of the '<em><b>Lines</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fsm.FSMDLine}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Lines</em>' containment reference list.
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDStateMachine_Lines()
     * @model containment="true"
     * @generated
     */
    EList<FSMDLine> getLines();

    /**
     * Returns the value of the '<em><b>Variables</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fsm.FSMDVariable}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Variables</em>' containment reference list.
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDStateMachine_Variables()
     * @model containment="true"
     * @generated
     */
    EList<FSMDVariable> getVariables();

    /**
     * Returns the value of the '<em><b>Manhattan Mode</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Manhattan Mode</em>' attribute.
     * @see #setManhattanMode(int)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDStateMachine_ManhattanMode()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getManhattanMode();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDStateMachine#getManhattanMode <em>Manhattan Mode</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Manhattan Mode</em>' attribute.
     * @see #getManhattanMode()
     * @generated
     */
    void setManhattanMode(int value);

} // FSMDStateMachine
