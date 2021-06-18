/**
 */
package com.zipc.garden.model.bp;

import com.zipc.garden.model.core.COREPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.zipc.garden.model.bp.BPFactory
 * @model kind="package"
 * @generated
 */
public interface BPPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "bp";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.bp";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "BP";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    BPPackage eINSTANCE = com.zipc.garden.model.bp.impl.BPPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bp.impl.BPRootImpl <em>Root</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bp.impl.BPRootImpl
     * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPRoot()
     * @generated
     */
    int BP_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>Max</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_ROOT__MAX = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Row Ids</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_ROOT__ROW_IDS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Settings</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int BP_ROOT__SETTINGS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>All</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_ROOT__ALL = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Begin</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_ROOT__BEGIN = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>End</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_ROOT__END = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Status</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_ROOT__STATUS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_ROOT__MESSAGE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bp.impl.BPStateMachineImpl <em>State Machine</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bp.impl.BPStateMachineImpl
     * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPStateMachine()
     * @generated
     */
    int BP_STATE_MACHINE = 1;

    /**
     * The feature id for the '<em><b>Fsm Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_STATE_MACHINE__FSM_ID = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_STATE_MACHINE__NAME = 1;

    /**
     * The feature id for the '<em><b>States</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_STATE_MACHINE__STATES = 2;

    /**
     * The feature id for the '<em><b>Events</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_STATE_MACHINE__EVENTS = 3;

    /**
     * The number of structural features of the '<em>State Machine</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_STATE_MACHINE_FEATURE_COUNT = 4;

    /**
     * The number of operations of the '<em>State Machine</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_STATE_MACHINE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bp.impl.BPBehaviorPatternImpl <em>Behavior Pattern</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bp.impl.BPBehaviorPatternImpl
     * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPBehaviorPattern()
     * @generated
     */
    int BP_BEHAVIOR_PATTERN = 2;

    /**
     * The feature id for the '<em><b>Behaviors</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int BP_BEHAVIOR_PATTERN__BEHAVIORS = 0;

    /**
     * The feature id for the '<em><b>Specification</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_BEHAVIOR_PATTERN__SPECIFICATION = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_BEHAVIOR_PATTERN__ID = 2;

    /**
     * The number of structural features of the '<em>Behavior Pattern</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_BEHAVIOR_PATTERN_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Behavior Pattern</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_BEHAVIOR_PATTERN_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bp.impl.BPBehaviorImpl <em>Behavior</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bp.impl.BPBehaviorImpl
     * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPBehavior()
     * @generated
     */
    int BP_BEHAVIOR = 3;

    /**
     * The feature id for the '<em><b>Steps</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_BEHAVIOR__STEPS = 0;

    /**
     * The feature id for the '<em><b>State Machine Ref</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_BEHAVIOR__STATE_MACHINE_REF = 1;

    /**
     * The number of structural features of the '<em>Behavior</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_BEHAVIOR_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Behavior</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_BEHAVIOR_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bp.impl.BPStepImpl <em>Step</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bp.impl.BPStepImpl
     * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPStep()
     * @generated
     */
    int BP_STEP = 4;

    /**
     * The feature id for the '<em><b>State</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_STEP__STATE = 0;

    /**
     * The feature id for the '<em><b>Event</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_STEP__EVENT = 1;

    /**
     * The number of structural features of the '<em>Step</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_STEP_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Step</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_STEP_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bp.impl.BPSpecElementImpl <em>Spec Element</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bp.impl.BPSpecElementImpl
     * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPSpecElement()
     * @generated
     */
    int BP_SPEC_ELEMENT = 8;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bp.impl.BPStateImpl <em>State</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bp.impl.BPStateImpl
     * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPState()
     * @generated
     */
    int BP_STATE = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_STATE__NAME = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_STATE__VALUE = 1;

    /**
     * The number of structural features of the '<em>State</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_STATE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>State</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_STATE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bp.impl.BPEventImpl <em>Event</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bp.impl.BPEventImpl
     * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPEvent()
     * @generated
     */
    int BP_EVENT = 6;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_EVENT__NAME = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_EVENT__VALUE = 1;

    /**
     * The number of structural features of the '<em>Event</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_EVENT_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Event</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_EVENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bp.impl.BPSpecImpl <em>Spec</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bp.impl.BPSpecImpl
     * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPSpec()
     * @generated
     */
    int BP_SPEC = 7;

    /**
     * The feature id for the '<em><b>Paths</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SPEC__PATHS = 0;

    /**
     * The number of structural features of the '<em>Spec</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SPEC_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Spec</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SPEC_OPERATION_COUNT = 0;

    /**
     * The feature id for the '<em><b>State</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SPEC_ELEMENT__STATE = 0;

    /**
     * The feature id for the '<em><b>Event</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SPEC_ELEMENT__EVENT = 1;

    /**
     * The number of structural features of the '<em>Spec Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SPEC_ELEMENT_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Spec Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SPEC_ELEMENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bp.impl.BPSettingImpl <em>Setting</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bp.impl.BPSettingImpl
     * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPSetting()
     * @generated
     */
    int BP_SETTING = 9;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SETTING__UUID = COREPackage.ABSTRACT_SETTING__UUID;

    /**
     * The feature id for the '<em><b>Setting Hash</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SETTING__SETTING_HASH = COREPackage.ABSTRACT_SETTING__SETTING_HASH;

    /**
     * The feature id for the '<em><b>Pattern Nos</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SETTING__PATTERN_NOS = COREPackage.ABSTRACT_SETTING__PATTERN_NOS;

    /**
     * The feature id for the '<em><b>Begin</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SETTING__BEGIN = COREPackage.ABSTRACT_SETTING__BEGIN;

    /**
     * The feature id for the '<em><b>End</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SETTING__END = COREPackage.ABSTRACT_SETTING__END;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SETTING__COUNT = COREPackage.ABSTRACT_SETTING__COUNT;

    /**
     * The feature id for the '<em><b>Abstract Root</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int BP_SETTING__ABSTRACT_ROOT = COREPackage.ABSTRACT_SETTING__ABSTRACT_ROOT;

    /**
     * The feature id for the '<em><b>State Machines</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SETTING__STATE_MACHINES = COREPackage.ABSTRACT_SETTING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Pattern</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int BP_SETTING__PATTERN = COREPackage.ABSTRACT_SETTING_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Setting</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SETTING_FEATURE_COUNT = COREPackage.ABSTRACT_SETTING_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Setting</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BP_SETTING_OPERATION_COUNT = COREPackage.ABSTRACT_SETTING_OPERATION_COUNT + 0;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bp.BPRoot <em>Root</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.bp.BPRoot
     * @generated
     */
    EClass getBPRoot();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bp.BPRoot#getAll <em>All</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>All</em>'.
     * @see com.zipc.garden.model.bp.BPRoot#getAll()
     * @see #getBPRoot()
     * @generated
     */
    EAttribute getBPRoot_All();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bp.BPRoot#getBegin <em>Begin</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Begin</em>'.
     * @see com.zipc.garden.model.bp.BPRoot#getBegin()
     * @see #getBPRoot()
     * @generated
     */
    EAttribute getBPRoot_Begin();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bp.BPRoot#getEnd <em>End</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>End</em>'.
     * @see com.zipc.garden.model.bp.BPRoot#getEnd()
     * @see #getBPRoot()
     * @generated
     */
    EAttribute getBPRoot_End();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bp.BPRoot#getStatus <em>Status</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Status</em>'.
     * @see com.zipc.garden.model.bp.BPRoot#getStatus()
     * @see #getBPRoot()
     * @generated
     */
    EAttribute getBPRoot_Status();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bp.BPRoot#getMessage <em>Message</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Message</em>'.
     * @see com.zipc.garden.model.bp.BPRoot#getMessage()
     * @see #getBPRoot()
     * @generated
     */
    EAttribute getBPRoot_Message();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bp.BPStateMachine <em>State Machine</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>State Machine</em>'.
     * @see com.zipc.garden.model.bp.BPStateMachine
     * @generated
     */
    EClass getBPStateMachine();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bp.BPStateMachine#getFsmId <em>Fsm Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Fsm Id</em>'.
     * @see com.zipc.garden.model.bp.BPStateMachine#getFsmId()
     * @see #getBPStateMachine()
     * @generated
     */
    EAttribute getBPStateMachine_FsmId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bp.BPStateMachine#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.bp.BPStateMachine#getName()
     * @see #getBPStateMachine()
     * @generated
     */
    EAttribute getBPStateMachine_Name();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.bp.BPStateMachine#getStates
     * <em>States</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>States</em>'.
     * @see com.zipc.garden.model.bp.BPStateMachine#getStates()
     * @see #getBPStateMachine()
     * @generated
     */
    EReference getBPStateMachine_States();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.bp.BPStateMachine#getEvents
     * <em>Events</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Events</em>'.
     * @see com.zipc.garden.model.bp.BPStateMachine#getEvents()
     * @see #getBPStateMachine()
     * @generated
     */
    EReference getBPStateMachine_Events();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bp.BPBehaviorPattern <em>Behavior Pattern</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Behavior Pattern</em>'.
     * @see com.zipc.garden.model.bp.BPBehaviorPattern
     * @generated
     */
    EClass getBPBehaviorPattern();

    /**
     * Returns the meta object for the containment reference list
     * '{@link com.zipc.garden.model.bp.BPBehaviorPattern#getBehaviors <em>Behaviors</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>Behaviors</em>'.
     * @see com.zipc.garden.model.bp.BPBehaviorPattern#getBehaviors()
     * @see #getBPBehaviorPattern()
     * @generated
     */
    EReference getBPBehaviorPattern_Behaviors();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.bp.BPBehaviorPattern#getSpecification
     * <em>Specification</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Specification</em>'.
     * @see com.zipc.garden.model.bp.BPBehaviorPattern#getSpecification()
     * @see #getBPBehaviorPattern()
     * @generated
     */
    EReference getBPBehaviorPattern_Specification();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bp.BPBehaviorPattern#getId <em>Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see com.zipc.garden.model.bp.BPBehaviorPattern#getId()
     * @see #getBPBehaviorPattern()
     * @generated
     */
    EAttribute getBPBehaviorPattern_Id();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bp.BPBehavior <em>Behavior</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Behavior</em>'.
     * @see com.zipc.garden.model.bp.BPBehavior
     * @generated
     */
    EClass getBPBehavior();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.bp.BPBehavior#getSteps
     * <em>Steps</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Steps</em>'.
     * @see com.zipc.garden.model.bp.BPBehavior#getSteps()
     * @see #getBPBehavior()
     * @generated
     */
    EReference getBPBehavior_Steps();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.bp.BPBehavior#getStateMachineRef <em>State
     * Machine Ref</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>State Machine Ref</em>'.
     * @see com.zipc.garden.model.bp.BPBehavior#getStateMachineRef()
     * @see #getBPBehavior()
     * @generated
     */
    EReference getBPBehavior_StateMachineRef();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bp.BPStep <em>Step</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Step</em>'.
     * @see com.zipc.garden.model.bp.BPStep
     * @generated
     */
    EClass getBPStep();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.bp.BPStep#getState <em>State</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>State</em>'.
     * @see com.zipc.garden.model.bp.BPStep#getState()
     * @see #getBPStep()
     * @generated
     */
    EReference getBPStep_State();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.bp.BPStep#getEvent <em>Event</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Event</em>'.
     * @see com.zipc.garden.model.bp.BPStep#getEvent()
     * @see #getBPStep()
     * @generated
     */
    EReference getBPStep_Event();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bp.BPState <em>State</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>State</em>'.
     * @see com.zipc.garden.model.bp.BPState
     * @generated
     */
    EClass getBPState();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bp.BPState#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.bp.BPState#getName()
     * @see #getBPState()
     * @generated
     */
    EAttribute getBPState_Name();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bp.BPState#getValue <em>Value</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see com.zipc.garden.model.bp.BPState#getValue()
     * @see #getBPState()
     * @generated
     */
    EAttribute getBPState_Value();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bp.BPEvent <em>Event</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Event</em>'.
     * @see com.zipc.garden.model.bp.BPEvent
     * @generated
     */
    EClass getBPEvent();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bp.BPEvent#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.bp.BPEvent#getName()
     * @see #getBPEvent()
     * @generated
     */
    EAttribute getBPEvent_Name();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bp.BPEvent#getValue <em>Value</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see com.zipc.garden.model.bp.BPEvent#getValue()
     * @see #getBPEvent()
     * @generated
     */
    EAttribute getBPEvent_Value();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bp.BPSpec <em>Spec</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Spec</em>'.
     * @see com.zipc.garden.model.bp.BPSpec
     * @generated
     */
    EClass getBPSpec();

    /**
     * Returns the meta object for the reference list '{@link com.zipc.garden.model.bp.BPSpec#getPaths <em>Paths</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Paths</em>'.
     * @see com.zipc.garden.model.bp.BPSpec#getPaths()
     * @see #getBPSpec()
     * @generated
     */
    EReference getBPSpec_Paths();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bp.BPSpecElement <em>Spec Element</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Spec Element</em>'.
     * @see com.zipc.garden.model.bp.BPSpecElement
     * @generated
     */
    EClass getBPSpecElement();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.bp.BPSpecElement#getState <em>State</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>State</em>'.
     * @see com.zipc.garden.model.bp.BPSpecElement#getState()
     * @see #getBPSpecElement()
     * @generated
     */
    EReference getBPSpecElement_State();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.bp.BPSpecElement#getEvent <em>Event</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Event</em>'.
     * @see com.zipc.garden.model.bp.BPSpecElement#getEvent()
     * @see #getBPSpecElement()
     * @generated
     */
    EReference getBPSpecElement_Event();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bp.BPSetting <em>Setting</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Setting</em>'.
     * @see com.zipc.garden.model.bp.BPSetting
     * @generated
     */
    EClass getBPSetting();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.bp.BPSetting#getStateMachines
     * <em>State Machines</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>State Machines</em>'.
     * @see com.zipc.garden.model.bp.BPSetting#getStateMachines()
     * @see #getBPSetting()
     * @generated
     */
    EReference getBPSetting_StateMachines();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.bp.BPSetting#getPattern
     * <em>Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Pattern</em>'.
     * @see com.zipc.garden.model.bp.BPSetting#getPattern()
     * @see #getBPSetting()
     * @generated
     */
    EReference getBPSetting_Pattern();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    BPFactory getBPFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bp.impl.BPRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bp.impl.BPRootImpl
         * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPRoot()
         * @generated
         */
        EClass BP_ROOT = eINSTANCE.getBPRoot();

        /**
         * The meta object literal for the '<em><b>All</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BP_ROOT__ALL = eINSTANCE.getBPRoot_All();

        /**
         * The meta object literal for the '<em><b>Begin</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BP_ROOT__BEGIN = eINSTANCE.getBPRoot_Begin();

        /**
         * The meta object literal for the '<em><b>End</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BP_ROOT__END = eINSTANCE.getBPRoot_End();

        /**
         * The meta object literal for the '<em><b>Status</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BP_ROOT__STATUS = eINSTANCE.getBPRoot_Status();

        /**
         * The meta object literal for the '<em><b>Message</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute BP_ROOT__MESSAGE = eINSTANCE.getBPRoot_Message();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bp.impl.BPStateMachineImpl <em>State Machine</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bp.impl.BPStateMachineImpl
         * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPStateMachine()
         * @generated
         */
        EClass BP_STATE_MACHINE = eINSTANCE.getBPStateMachine();

        /**
         * The meta object literal for the '<em><b>Fsm Id</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BP_STATE_MACHINE__FSM_ID = eINSTANCE.getBPStateMachine_FsmId();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BP_STATE_MACHINE__NAME = eINSTANCE.getBPStateMachine_Name();

        /**
         * The meta object literal for the '<em><b>States</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BP_STATE_MACHINE__STATES = eINSTANCE.getBPStateMachine_States();

        /**
         * The meta object literal for the '<em><b>Events</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BP_STATE_MACHINE__EVENTS = eINSTANCE.getBPStateMachine_Events();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bp.impl.BPBehaviorPatternImpl <em>Behavior
         * Pattern</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bp.impl.BPBehaviorPatternImpl
         * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPBehaviorPattern()
         * @generated
         */
        EClass BP_BEHAVIOR_PATTERN = eINSTANCE.getBPBehaviorPattern();

        /**
         * The meta object literal for the '<em><b>Behaviors</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference BP_BEHAVIOR_PATTERN__BEHAVIORS = eINSTANCE.getBPBehaviorPattern_Behaviors();

        /**
         * The meta object literal for the '<em><b>Specification</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference BP_BEHAVIOR_PATTERN__SPECIFICATION = eINSTANCE.getBPBehaviorPattern_Specification();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute BP_BEHAVIOR_PATTERN__ID = eINSTANCE.getBPBehaviorPattern_Id();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bp.impl.BPBehaviorImpl <em>Behavior</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bp.impl.BPBehaviorImpl
         * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPBehavior()
         * @generated
         */
        EClass BP_BEHAVIOR = eINSTANCE.getBPBehavior();

        /**
         * The meta object literal for the '<em><b>Steps</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BP_BEHAVIOR__STEPS = eINSTANCE.getBPBehavior_Steps();

        /**
         * The meta object literal for the '<em><b>State Machine Ref</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference BP_BEHAVIOR__STATE_MACHINE_REF = eINSTANCE.getBPBehavior_StateMachineRef();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bp.impl.BPStepImpl <em>Step</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bp.impl.BPStepImpl
         * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPStep()
         * @generated
         */
        EClass BP_STEP = eINSTANCE.getBPStep();

        /**
         * The meta object literal for the '<em><b>State</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference BP_STEP__STATE = eINSTANCE.getBPStep_State();

        /**
         * The meta object literal for the '<em><b>Event</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference BP_STEP__EVENT = eINSTANCE.getBPStep_Event();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bp.impl.BPStateImpl <em>State</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bp.impl.BPStateImpl
         * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPState()
         * @generated
         */
        EClass BP_STATE = eINSTANCE.getBPState();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BP_STATE__NAME = eINSTANCE.getBPState_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BP_STATE__VALUE = eINSTANCE.getBPState_Value();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bp.impl.BPEventImpl <em>Event</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bp.impl.BPEventImpl
         * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPEvent()
         * @generated
         */
        EClass BP_EVENT = eINSTANCE.getBPEvent();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BP_EVENT__NAME = eINSTANCE.getBPEvent_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BP_EVENT__VALUE = eINSTANCE.getBPEvent_Value();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bp.impl.BPSpecImpl <em>Spec</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bp.impl.BPSpecImpl
         * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPSpec()
         * @generated
         */
        EClass BP_SPEC = eINSTANCE.getBPSpec();

        /**
         * The meta object literal for the '<em><b>Paths</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BP_SPEC__PATHS = eINSTANCE.getBPSpec_Paths();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bp.impl.BPSpecElementImpl <em>Spec Element</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bp.impl.BPSpecElementImpl
         * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPSpecElement()
         * @generated
         */
        EClass BP_SPEC_ELEMENT = eINSTANCE.getBPSpecElement();

        /**
         * The meta object literal for the '<em><b>State</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference BP_SPEC_ELEMENT__STATE = eINSTANCE.getBPSpecElement_State();

        /**
         * The meta object literal for the '<em><b>Event</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference BP_SPEC_ELEMENT__EVENT = eINSTANCE.getBPSpecElement_Event();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bp.impl.BPSettingImpl <em>Setting</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bp.impl.BPSettingImpl
         * @see com.zipc.garden.model.bp.impl.BPPackageImpl#getBPSetting()
         * @generated
         */
        EClass BP_SETTING = eINSTANCE.getBPSetting();

        /**
         * The meta object literal for the '<em><b>State Machines</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference BP_SETTING__STATE_MACHINES = eINSTANCE.getBPSetting_StateMachines();

        /**
         * The meta object literal for the '<em><b>Pattern</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BP_SETTING__PATTERN = eINSTANCE.getBPSetting_Pattern();

    }

} // BPPackage
