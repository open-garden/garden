/**
 */
package com.zipc.garden.model.fsm;

import com.zipc.garden.model.core.COREPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see com.zipc.garden.model.fsm.FSMFactory
 * @model kind="package"
 * @generated
 */
public interface FSMPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "fsm";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.fsm";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "FSM";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    FSMPackage eINSTANCE = com.zipc.garden.model.fsm.impl.FSMPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl <em>DState Machine</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDStateMachine()
     * @generated
     */
    int FSM_DSTATE_MACHINE = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>Scroll X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__SCROLL_X = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Scroll Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__SCROLL_Y = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Position X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__POSITION_X = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Position Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__POSITION_Y = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Grid Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__GRID_SIZE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__NODES = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Line Mode</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__LINE_MODE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Properties</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__PROPERTIES = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Transitions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__TRANSITIONS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Memos</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__MEMOS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Regions</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__REGIONS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Fmsdevent</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__FMSDEVENT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Actions</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__ACTIONS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Lines</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__LINES = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Variables</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__VARIABLES = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>Manhattan Mode</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE__MANHATTAN_MODE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 15;

    /**
     * The number of structural features of the '<em>DState Machine</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 16;

    /**
     * The number of operations of the '<em>DState Machine</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_MACHINE_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.impl.FSMDReferenceableImpl <em>DReferenceable</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.impl.FSMDReferenceableImpl
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDReferenceable()
     * @generated
     */
    int FSM_DREFERENCEABLE = 5;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.impl.FSMDVertexImpl <em>DVertex</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.impl.FSMDVertexImpl
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDVertex()
     * @generated
     */
    int FSM_DVERTEX = 1;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.impl.FSMDPropertyImpl <em>DProperty</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.impl.FSMDPropertyImpl
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDProperty()
     * @generated
     */
    int FSM_DPROPERTY = 2;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.impl.FSMDStateImpl <em>DState</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.impl.FSMDStateImpl
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDState()
     * @generated
     */
    int FSM_DSTATE = 3;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl <em>DTransition</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.impl.FSMDTransitionImpl
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDTransition()
     * @generated
     */
    int FSM_DTRANSITION = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DREFERENCEABLE__ID = 0;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DREFERENCEABLE__UUID = 1;

    /**
     * The number of structural features of the '<em>DReferenceable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DREFERENCEABLE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>DReferenceable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DREFERENCEABLE_OPERATION_COUNT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVERTEX__ID = FSM_DREFERENCEABLE__ID;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVERTEX__UUID = FSM_DREFERENCEABLE__UUID;

    /**
     * The feature id for the '<em><b>Top</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVERTEX__TOP = FSM_DREFERENCEABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Left</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVERTEX__LEFT = FSM_DREFERENCEABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVERTEX__HEIGHT = FSM_DREFERENCEABLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVERTEX__WIDTH = FSM_DREFERENCEABLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Properties</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FSM_DVERTEX__PROPERTIES = FSM_DREFERENCEABLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Background</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVERTEX__BACKGROUND = FSM_DREFERENCEABLE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Foreground</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVERTEX__FOREGROUND = FSM_DREFERENCEABLE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Incoming Line</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVERTEX__INCOMING_LINE = FSM_DREFERENCEABLE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Outgoing Line</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVERTEX__OUTGOING_LINE = FSM_DREFERENCEABLE_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>DVertex</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVERTEX_FEATURE_COUNT = FSM_DREFERENCEABLE_FEATURE_COUNT + 9;

    /**
     * The number of operations of the '<em>DVertex</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVERTEX_OPERATION_COUNT = FSM_DREFERENCEABLE_OPERATION_COUNT + 0;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DPROPERTY__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DPROPERTY__VALUE = 1;

    /**
     * The number of structural features of the '<em>DProperty</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DPROPERTY_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>DProperty</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DPROPERTY_OPERATION_COUNT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__ID = FSM_DVERTEX__ID;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__UUID = FSM_DVERTEX__UUID;

    /**
     * The feature id for the '<em><b>Top</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__TOP = FSM_DVERTEX__TOP;

    /**
     * The feature id for the '<em><b>Left</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__LEFT = FSM_DVERTEX__LEFT;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__HEIGHT = FSM_DVERTEX__HEIGHT;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__WIDTH = FSM_DVERTEX__WIDTH;

    /**
     * The feature id for the '<em><b>Properties</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__PROPERTIES = FSM_DVERTEX__PROPERTIES;

    /**
     * The feature id for the '<em><b>Background</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__BACKGROUND = FSM_DVERTEX__BACKGROUND;

    /**
     * The feature id for the '<em><b>Foreground</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__FOREGROUND = FSM_DVERTEX__FOREGROUND;

    /**
     * The feature id for the '<em><b>Incoming Line</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__INCOMING_LINE = FSM_DVERTEX__INCOMING_LINE;

    /**
     * The feature id for the '<em><b>Outgoing Line</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__OUTGOING_LINE = FSM_DVERTEX__OUTGOING_LINE;

    /**
     * The feature id for the '<em><b>Ref</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__REF = FSM_DVERTEX_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Ref Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__REF_NAME = FSM_DVERTEX_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__NAME = FSM_DVERTEX_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Entry</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__ENTRY = FSM_DVERTEX_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Exit</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__EXIT = FSM_DVERTEX_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Do</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__DO = FSM_DVERTEX_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Regions</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__REGIONS = FSM_DVERTEX_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__TYPE = FSM_DVERTEX_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Incoming Transition</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__INCOMING_TRANSITION = FSM_DVERTEX_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Outgoing Transition</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__OUTGOING_TRANSITION = FSM_DVERTEX_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Refuuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE__REFUUID = FSM_DVERTEX_FEATURE_COUNT + 10;

    /**
     * The number of structural features of the '<em>DState</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_FEATURE_COUNT = FSM_DVERTEX_FEATURE_COUNT + 11;

    /**
     * The number of operations of the '<em>DState</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DSTATE_OPERATION_COUNT = FSM_DVERTEX_OPERATION_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__ID = FSM_DREFERENCEABLE__ID;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__UUID = FSM_DREFERENCEABLE__UUID;

    /**
     * The feature id for the '<em><b>Source Anchor X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__SOURCE_ANCHOR_X = FSM_DREFERENCEABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Anchor Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__SOURCE_ANCHOR_Y = FSM_DREFERENCEABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target Anchor X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__TARGET_ANCHOR_X = FSM_DREFERENCEABLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Target Anchor Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__TARGET_ANCHOR_Y = FSM_DREFERENCEABLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__TYPE = FSM_DREFERENCEABLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Connection Point</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__CONNECTION_POINT = FSM_DREFERENCEABLE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Trigger</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__TRIGGER = FSM_DREFERENCEABLE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Event</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__EVENT = FSM_DREFERENCEABLE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Condition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__CONDITION = FSM_DREFERENCEABLE_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Action</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__ACTION = FSM_DREFERENCEABLE_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__X = FSM_DREFERENCEABLE_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__Y = FSM_DREFERENCEABLE_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__TARGET = FSM_DREFERENCEABLE_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__SOURCE = FSM_DREFERENCEABLE_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Priority</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION__PRIORITY = FSM_DREFERENCEABLE_FEATURE_COUNT + 14;

    /**
     * The number of structural features of the '<em>DTransition</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION_FEATURE_COUNT = FSM_DREFERENCEABLE_FEATURE_COUNT + 15;

    /**
     * The number of operations of the '<em>DTransition</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DTRANSITION_OPERATION_COUNT = FSM_DREFERENCEABLE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.impl.FSMDMemoImpl <em>DMemo</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.impl.FSMDMemoImpl
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDMemo()
     * @generated
     */
    int FSM_DMEMO = 6;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DMEMO__ID = FSM_DVERTEX__ID;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DMEMO__UUID = FSM_DVERTEX__UUID;

    /**
     * The feature id for the '<em><b>Top</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DMEMO__TOP = FSM_DVERTEX__TOP;

    /**
     * The feature id for the '<em><b>Left</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DMEMO__LEFT = FSM_DVERTEX__LEFT;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DMEMO__HEIGHT = FSM_DVERTEX__HEIGHT;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DMEMO__WIDTH = FSM_DVERTEX__WIDTH;

    /**
     * The feature id for the '<em><b>Properties</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FSM_DMEMO__PROPERTIES = FSM_DVERTEX__PROPERTIES;

    /**
     * The feature id for the '<em><b>Background</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DMEMO__BACKGROUND = FSM_DVERTEX__BACKGROUND;

    /**
     * The feature id for the '<em><b>Foreground</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DMEMO__FOREGROUND = FSM_DVERTEX__FOREGROUND;

    /**
     * The feature id for the '<em><b>Incoming Line</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DMEMO__INCOMING_LINE = FSM_DVERTEX__INCOMING_LINE;

    /**
     * The feature id for the '<em><b>Outgoing Line</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DMEMO__OUTGOING_LINE = FSM_DVERTEX__OUTGOING_LINE;

    /**
     * The feature id for the '<em><b>Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DMEMO__TEXT = FSM_DVERTEX_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>DMemo</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DMEMO_FEATURE_COUNT = FSM_DVERTEX_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>DMemo</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DMEMO_OPERATION_COUNT = FSM_DVERTEX_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.impl.FSMDRegionImpl <em>DRegion</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.impl.FSMDRegionImpl
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDRegion()
     * @generated
     */
    int FSM_DREGION = 7;

    /**
     * The feature id for the '<em><b>States</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DREGION__STATES = 0;

    /**
     * The number of structural features of the '<em>DRegion</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DREGION_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>DRegion</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DREGION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.impl.FSMDAbstractLineImpl <em>DAbstract Line</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.impl.FSMDAbstractLineImpl
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDAbstractLine()
     * @generated
     */
    int FSM_DABSTRACT_LINE = 11;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.impl.FSMDLineImpl <em>DLine</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.impl.FSMDLineImpl
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDLine()
     * @generated
     */
    int FSM_DLINE = 8;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.impl.FSMDEventImpl <em>DEvent</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.impl.FSMDEventImpl
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDEvent()
     * @generated
     */
    int FSM_DEVENT = 9;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.impl.FSMDActionImpl <em>DAction</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.impl.FSMDActionImpl
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDAction()
     * @generated
     */
    int FSM_DACTION = 10;

    /**
     * The feature id for the '<em><b>Source Anchor X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DABSTRACT_LINE__SOURCE_ANCHOR_X = COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_X;

    /**
     * The feature id for the '<em><b>Source Anchor Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DABSTRACT_LINE__SOURCE_ANCHOR_Y = COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_Y;

    /**
     * The feature id for the '<em><b>Target Anchor X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DABSTRACT_LINE__TARGET_ANCHOR_X = COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_X;

    /**
     * The feature id for the '<em><b>Target Anchor Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DABSTRACT_LINE__TARGET_ANCHOR_Y = COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_Y;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DABSTRACT_LINE__TYPE = COREPackage.ABSTRACT_LINE__TYPE;

    /**
     * The feature id for the '<em><b>Connection Point</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DABSTRACT_LINE__CONNECTION_POINT = COREPackage.ABSTRACT_LINE__CONNECTION_POINT;

    /**
     * The number of structural features of the '<em>DAbstract Line</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DABSTRACT_LINE_FEATURE_COUNT = COREPackage.ABSTRACT_LINE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>DAbstract Line</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DABSTRACT_LINE_OPERATION_COUNT = COREPackage.ABSTRACT_LINE_OPERATION_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Anchor X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DLINE__SOURCE_ANCHOR_X = FSM_DABSTRACT_LINE__SOURCE_ANCHOR_X;

    /**
     * The feature id for the '<em><b>Source Anchor Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DLINE__SOURCE_ANCHOR_Y = FSM_DABSTRACT_LINE__SOURCE_ANCHOR_Y;

    /**
     * The feature id for the '<em><b>Target Anchor X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DLINE__TARGET_ANCHOR_X = FSM_DABSTRACT_LINE__TARGET_ANCHOR_X;

    /**
     * The feature id for the '<em><b>Target Anchor Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DLINE__TARGET_ANCHOR_Y = FSM_DABSTRACT_LINE__TARGET_ANCHOR_Y;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DLINE__TYPE = FSM_DABSTRACT_LINE__TYPE;

    /**
     * The feature id for the '<em><b>Connection Point</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DLINE__CONNECTION_POINT = FSM_DABSTRACT_LINE__CONNECTION_POINT;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DLINE__SOURCE = FSM_DABSTRACT_LINE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DLINE__TARGET = FSM_DABSTRACT_LINE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>DLine</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DLINE_FEATURE_COUNT = FSM_DABSTRACT_LINE_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>DLine</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DLINE_OPERATION_COUNT = FSM_DABSTRACT_LINE_OPERATION_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DEVENT__NAME = 0;

    /**
     * The number of structural features of the '<em>DEvent</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DEVENT_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>DEvent</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DEVENT_OPERATION_COUNT = 0;

    /**
     * The feature id for the '<em><b>Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DACTION__TEXT = 0;

    /**
     * The number of structural features of the '<em>DAction</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DACTION_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>DAction</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DACTION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.impl.FSMDPointImpl <em>DPoint</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.impl.FSMDPointImpl
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDPoint()
     * @generated
     */
    int FSM_DPOINT = 12;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DPOINT__X = COREPackage.ABSTRACT_POINT__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DPOINT__Y = COREPackage.ABSTRACT_POINT__Y;

    /**
     * The number of structural features of the '<em>DPoint</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DPOINT_FEATURE_COUNT = COREPackage.ABSTRACT_POINT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>DPoint</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DPOINT_OPERATION_COUNT = COREPackage.ABSTRACT_POINT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.impl.FSMDVariableImpl <em>DVariable</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.impl.FSMDVariableImpl
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDVariable()
     * @generated
     */
    int FSM_DVARIABLE = 13;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVARIABLE__NAME = 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVARIABLE__TYPE = 1;

    /**
     * The number of structural features of the '<em>DVariable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVARIABLE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>DVariable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FSM_DVARIABLE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.FSMDPseudoStateType <em>DPseudo State Type</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.FSMDPseudoStateType
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDPseudoStateType()
     * @generated
     */
    int FSM_DPSEUDO_STATE_TYPE = 14;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fsm.FSMDLineType <em>DLine Type</em>}' enum. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fsm.FSMDLineType
     * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDLineType()
     * @generated
     */
    int FSM_DLINE_TYPE = 15;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fsm.FSMDStateMachine <em>DState Machine</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>DState Machine</em>'.
     * @see com.zipc.garden.model.fsm.FSMDStateMachine
     * @generated
     */
    EClass getFSMDStateMachine();

    /**
     * Returns the meta object for the containment reference list
     * '{@link com.zipc.garden.model.fsm.FSMDStateMachine#getProperties <em>Properties</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>Properties</em>'.
     * @see com.zipc.garden.model.fsm.FSMDStateMachine#getProperties()
     * @see #getFSMDStateMachine()
     * @generated
     */
    EReference getFSMDStateMachine_Properties();

    /**
     * Returns the meta object for the containment reference list
     * '{@link com.zipc.garden.model.fsm.FSMDStateMachine#getTransitions <em>Transitions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>Transitions</em>'.
     * @see com.zipc.garden.model.fsm.FSMDStateMachine#getTransitions()
     * @see #getFSMDStateMachine()
     * @generated
     */
    EReference getFSMDStateMachine_Transitions();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.fsm.FSMDStateMachine#getMemos
     * <em>Memos</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Memos</em>'.
     * @see com.zipc.garden.model.fsm.FSMDStateMachine#getMemos()
     * @see #getFSMDStateMachine()
     * @generated
     */
    EReference getFSMDStateMachine_Memos();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.fsm.FSMDStateMachine#getRegions
     * <em>Regions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Regions</em>'.
     * @see com.zipc.garden.model.fsm.FSMDStateMachine#getRegions()
     * @see #getFSMDStateMachine()
     * @generated
     */
    EReference getFSMDStateMachine_Regions();

    /**
     * Returns the meta object for the containment reference list
     * '{@link com.zipc.garden.model.fsm.FSMDStateMachine#getFmsdevent <em>Fmsdevent</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>Fmsdevent</em>'.
     * @see com.zipc.garden.model.fsm.FSMDStateMachine#getFmsdevent()
     * @see #getFSMDStateMachine()
     * @generated
     */
    EReference getFSMDStateMachine_Fmsdevent();

    /**
     * Returns the meta object for the reference list '{@link com.zipc.garden.model.fsm.FSMDStateMachine#getActions
     * <em>Actions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Actions</em>'.
     * @see com.zipc.garden.model.fsm.FSMDStateMachine#getActions()
     * @see #getFSMDStateMachine()
     * @generated
     */
    EReference getFSMDStateMachine_Actions();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.fsm.FSMDStateMachine#getLines
     * <em>Lines</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Lines</em>'.
     * @see com.zipc.garden.model.fsm.FSMDStateMachine#getLines()
     * @see #getFSMDStateMachine()
     * @generated
     */
    EReference getFSMDStateMachine_Lines();

    /**
     * Returns the meta object for the containment reference list
     * '{@link com.zipc.garden.model.fsm.FSMDStateMachine#getVariables <em>Variables</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>Variables</em>'.
     * @see com.zipc.garden.model.fsm.FSMDStateMachine#getVariables()
     * @see #getFSMDStateMachine()
     * @generated
     */
    EReference getFSMDStateMachine_Variables();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDStateMachine#getManhattanMode
     * <em>Manhattan Mode</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Manhattan Mode</em>'.
     * @see com.zipc.garden.model.fsm.FSMDStateMachine#getManhattanMode()
     * @see #getFSMDStateMachine()
     * @generated
     */
    EAttribute getFSMDStateMachine_ManhattanMode();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fsm.FSMDVertex <em>DVertex</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>DVertex</em>'.
     * @see com.zipc.garden.model.fsm.FSMDVertex
     * @generated
     */
    EClass getFSMDVertex();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDVertex#getTop <em>Top</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Top</em>'.
     * @see com.zipc.garden.model.fsm.FSMDVertex#getTop()
     * @see #getFSMDVertex()
     * @generated
     */
    EAttribute getFSMDVertex_Top();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDVertex#getLeft <em>Left</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Left</em>'.
     * @see com.zipc.garden.model.fsm.FSMDVertex#getLeft()
     * @see #getFSMDVertex()
     * @generated
     */
    EAttribute getFSMDVertex_Left();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDVertex#getHeight <em>Height</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see com.zipc.garden.model.fsm.FSMDVertex#getHeight()
     * @see #getFSMDVertex()
     * @generated
     */
    EAttribute getFSMDVertex_Height();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDVertex#getWidth <em>Width</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see com.zipc.garden.model.fsm.FSMDVertex#getWidth()
     * @see #getFSMDVertex()
     * @generated
     */
    EAttribute getFSMDVertex_Width();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.fsm.FSMDVertex#getProperties
     * <em>Properties</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Properties</em>'.
     * @see com.zipc.garden.model.fsm.FSMDVertex#getProperties()
     * @see #getFSMDVertex()
     * @generated
     */
    EReference getFSMDVertex_Properties();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDVertex#getBackground
     * <em>Background</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Background</em>'.
     * @see com.zipc.garden.model.fsm.FSMDVertex#getBackground()
     * @see #getFSMDVertex()
     * @generated
     */
    EAttribute getFSMDVertex_Background();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDVertex#getForeground
     * <em>Foreground</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Foreground</em>'.
     * @see com.zipc.garden.model.fsm.FSMDVertex#getForeground()
     * @see #getFSMDVertex()
     * @generated
     */
    EAttribute getFSMDVertex_Foreground();

    /**
     * Returns the meta object for the reference list '{@link com.zipc.garden.model.fsm.FSMDVertex#getIncomingLine <em>Incoming
     * Line</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Incoming Line</em>'.
     * @see com.zipc.garden.model.fsm.FSMDVertex#getIncomingLine()
     * @see #getFSMDVertex()
     * @generated
     */
    EReference getFSMDVertex_IncomingLine();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.fsm.FSMDVertex#getOutgoingLine <em>Outgoing
     * Line</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Outgoing Line</em>'.
     * @see com.zipc.garden.model.fsm.FSMDVertex#getOutgoingLine()
     * @see #getFSMDVertex()
     * @generated
     */
    EReference getFSMDVertex_OutgoingLine();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fsm.FSMDProperty <em>DProperty</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>DProperty</em>'.
     * @see com.zipc.garden.model.fsm.FSMDProperty
     * @generated
     */
    EClass getFSMDProperty();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDProperty#getKey <em>Key</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see com.zipc.garden.model.fsm.FSMDProperty#getKey()
     * @see #getFSMDProperty()
     * @generated
     */
    EAttribute getFSMDProperty_Key();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDProperty#getValue <em>Value</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see com.zipc.garden.model.fsm.FSMDProperty#getValue()
     * @see #getFSMDProperty()
     * @generated
     */
    EAttribute getFSMDProperty_Value();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fsm.FSMDState <em>DState</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>DState</em>'.
     * @see com.zipc.garden.model.fsm.FSMDState
     * @generated
     */
    EClass getFSMDState();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDState#getRef <em>Ref</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ref</em>'.
     * @see com.zipc.garden.model.fsm.FSMDState#getRef()
     * @see #getFSMDState()
     * @generated
     */
    EAttribute getFSMDState_Ref();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDState#getRefName <em>Ref Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ref Name</em>'.
     * @see com.zipc.garden.model.fsm.FSMDState#getRefName()
     * @see #getFSMDState()
     * @generated
     */
    EAttribute getFSMDState_RefName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDState#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.fsm.FSMDState#getName()
     * @see #getFSMDState()
     * @generated
     */
    EAttribute getFSMDState_Name();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDState#getEntry <em>Entry</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Entry</em>'.
     * @see com.zipc.garden.model.fsm.FSMDState#getEntry()
     * @see #getFSMDState()
     * @generated
     */
    EAttribute getFSMDState_Entry();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDState#getExit <em>Exit</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Exit</em>'.
     * @see com.zipc.garden.model.fsm.FSMDState#getExit()
     * @see #getFSMDState()
     * @generated
     */
    EAttribute getFSMDState_Exit();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDState#getDo <em>Do</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Do</em>'.
     * @see com.zipc.garden.model.fsm.FSMDState#getDo()
     * @see #getFSMDState()
     * @generated
     */
    EAttribute getFSMDState_Do();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.fsm.FSMDState#getRegions
     * <em>Regions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Regions</em>'.
     * @see com.zipc.garden.model.fsm.FSMDState#getRegions()
     * @see #getFSMDState()
     * @generated
     */
    EReference getFSMDState_Regions();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDState#getType <em>Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see com.zipc.garden.model.fsm.FSMDState#getType()
     * @see #getFSMDState()
     * @generated
     */
    EAttribute getFSMDState_Type();

    /**
     * Returns the meta object for the reference list '{@link com.zipc.garden.model.fsm.FSMDState#getIncomingTransition
     * <em>Incoming Transition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Incoming Transition</em>'.
     * @see com.zipc.garden.model.fsm.FSMDState#getIncomingTransition()
     * @see #getFSMDState()
     * @generated
     */
    EReference getFSMDState_IncomingTransition();

    /**
     * Returns the meta object for the reference list '{@link com.zipc.garden.model.fsm.FSMDState#getOutgoingTransition
     * <em>Outgoing Transition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Outgoing Transition</em>'.
     * @see com.zipc.garden.model.fsm.FSMDState#getOutgoingTransition()
     * @see #getFSMDState()
     * @generated
     */
    EReference getFSMDState_OutgoingTransition();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDState#getRefuuid <em>Refuuid</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Refuuid</em>'.
     * @see com.zipc.garden.model.fsm.FSMDState#getRefuuid()
     * @see #getFSMDState()
     * @generated
     */
    EAttribute getFSMDState_Refuuid();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fsm.FSMDTransition <em>DTransition</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>DTransition</em>'.
     * @see com.zipc.garden.model.fsm.FSMDTransition
     * @generated
     */
    EClass getFSMDTransition();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDTransition#getTrigger <em>Trigger</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Trigger</em>'.
     * @see com.zipc.garden.model.fsm.FSMDTransition#getTrigger()
     * @see #getFSMDTransition()
     * @generated
     */
    EAttribute getFSMDTransition_Trigger();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDTransition#getEvent <em>Event</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Event</em>'.
     * @see com.zipc.garden.model.fsm.FSMDTransition#getEvent()
     * @see #getFSMDTransition()
     * @generated
     */
    EAttribute getFSMDTransition_Event();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDTransition#getCondition
     * <em>Condition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Condition</em>'.
     * @see com.zipc.garden.model.fsm.FSMDTransition#getCondition()
     * @see #getFSMDTransition()
     * @generated
     */
    EAttribute getFSMDTransition_Condition();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDTransition#getAction <em>Action</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Action</em>'.
     * @see com.zipc.garden.model.fsm.FSMDTransition#getAction()
     * @see #getFSMDTransition()
     * @generated
     */
    EAttribute getFSMDTransition_Action();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDTransition#getX <em>X</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see com.zipc.garden.model.fsm.FSMDTransition#getX()
     * @see #getFSMDTransition()
     * @generated
     */
    EAttribute getFSMDTransition_X();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDTransition#getY <em>Y</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see com.zipc.garden.model.fsm.FSMDTransition#getY()
     * @see #getFSMDTransition()
     * @generated
     */
    EAttribute getFSMDTransition_Y();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.fsm.FSMDTransition#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see com.zipc.garden.model.fsm.FSMDTransition#getTarget()
     * @see #getFSMDTransition()
     * @generated
     */
    EReference getFSMDTransition_Target();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.fsm.FSMDTransition#getSource <em>Source</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see com.zipc.garden.model.fsm.FSMDTransition#getSource()
     * @see #getFSMDTransition()
     * @generated
     */
    EReference getFSMDTransition_Source();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDTransition#getPriority
     * <em>Priority</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Priority</em>'.
     * @see com.zipc.garden.model.fsm.FSMDTransition#getPriority()
     * @see #getFSMDTransition()
     * @generated
     */
    EAttribute getFSMDTransition_Priority();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fsm.FSMDReferenceable <em>DReferenceable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>DReferenceable</em>'.
     * @see com.zipc.garden.model.fsm.FSMDReferenceable
     * @generated
     */
    EClass getFSMDReferenceable();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDReferenceable#getId <em>Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see com.zipc.garden.model.fsm.FSMDReferenceable#getId()
     * @see #getFSMDReferenceable()
     * @generated
     */
    EAttribute getFSMDReferenceable_Id();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDReferenceable#getUuid <em>Uuid</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Uuid</em>'.
     * @see com.zipc.garden.model.fsm.FSMDReferenceable#getUuid()
     * @see #getFSMDReferenceable()
     * @generated
     */
    EAttribute getFSMDReferenceable_Uuid();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fsm.FSMDMemo <em>DMemo</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>DMemo</em>'.
     * @see com.zipc.garden.model.fsm.FSMDMemo
     * @generated
     */
    EClass getFSMDMemo();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDMemo#getText <em>Text</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see com.zipc.garden.model.fsm.FSMDMemo#getText()
     * @see #getFSMDMemo()
     * @generated
     */
    EAttribute getFSMDMemo_Text();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fsm.FSMDRegion <em>DRegion</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>DRegion</em>'.
     * @see com.zipc.garden.model.fsm.FSMDRegion
     * @generated
     */
    EClass getFSMDRegion();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.fsm.FSMDRegion#getStates
     * <em>States</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>States</em>'.
     * @see com.zipc.garden.model.fsm.FSMDRegion#getStates()
     * @see #getFSMDRegion()
     * @generated
     */
    EReference getFSMDRegion_States();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fsm.FSMDLine <em>DLine</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>DLine</em>'.
     * @see com.zipc.garden.model.fsm.FSMDLine
     * @generated
     */
    EClass getFSMDLine();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.fsm.FSMDLine#getSource <em>Source</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see com.zipc.garden.model.fsm.FSMDLine#getSource()
     * @see #getFSMDLine()
     * @generated
     */
    EReference getFSMDLine_Source();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.fsm.FSMDLine#getTarget <em>Target</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see com.zipc.garden.model.fsm.FSMDLine#getTarget()
     * @see #getFSMDLine()
     * @generated
     */
    EReference getFSMDLine_Target();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fsm.FSMDEvent <em>DEvent</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>DEvent</em>'.
     * @see com.zipc.garden.model.fsm.FSMDEvent
     * @generated
     */
    EClass getFSMDEvent();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDEvent#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.fsm.FSMDEvent#getName()
     * @see #getFSMDEvent()
     * @generated
     */
    EAttribute getFSMDEvent_Name();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fsm.FSMDAction <em>DAction</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>DAction</em>'.
     * @see com.zipc.garden.model.fsm.FSMDAction
     * @generated
     */
    EClass getFSMDAction();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDAction#getText <em>Text</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see com.zipc.garden.model.fsm.FSMDAction#getText()
     * @see #getFSMDAction()
     * @generated
     */
    EAttribute getFSMDAction_Text();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fsm.FSMDAbstractLine <em>DAbstract Line</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>DAbstract Line</em>'.
     * @see com.zipc.garden.model.fsm.FSMDAbstractLine
     * @generated
     */
    EClass getFSMDAbstractLine();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fsm.FSMDPoint <em>DPoint</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>DPoint</em>'.
     * @see com.zipc.garden.model.fsm.FSMDPoint
     * @generated
     */
    EClass getFSMDPoint();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fsm.FSMDVariable <em>DVariable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>DVariable</em>'.
     * @see com.zipc.garden.model.fsm.FSMDVariable
     * @generated
     */
    EClass getFSMDVariable();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDVariable#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.fsm.FSMDVariable#getName()
     * @see #getFSMDVariable()
     * @generated
     */
    EAttribute getFSMDVariable_Name();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fsm.FSMDVariable#getType <em>Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see com.zipc.garden.model.fsm.FSMDVariable#getType()
     * @see #getFSMDVariable()
     * @generated
     */
    EAttribute getFSMDVariable_Type();

    /**
     * Returns the meta object for enum '{@link com.zipc.garden.model.fsm.FSMDPseudoStateType <em>DPseudo State Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>DPseudo State Type</em>'.
     * @see com.zipc.garden.model.fsm.FSMDPseudoStateType
     * @generated
     */
    EEnum getFSMDPseudoStateType();

    /**
     * Returns the meta object for enum '{@link com.zipc.garden.model.fsm.FSMDLineType <em>DLine Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>DLine Type</em>'.
     * @see com.zipc.garden.model.fsm.FSMDLineType
     * @generated
     */
    EEnum getFSMDLineType();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    FSMFactory getFSMFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl <em>DState Machine</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.impl.FSMDStateMachineImpl
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDStateMachine()
         * @generated
         */
        EClass FSM_DSTATE_MACHINE = eINSTANCE.getFSMDStateMachine();

        /**
         * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FSM_DSTATE_MACHINE__PROPERTIES = eINSTANCE.getFSMDStateMachine_Properties();

        /**
         * The meta object literal for the '<em><b>Transitions</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FSM_DSTATE_MACHINE__TRANSITIONS = eINSTANCE.getFSMDStateMachine_Transitions();

        /**
         * The meta object literal for the '<em><b>Memos</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FSM_DSTATE_MACHINE__MEMOS = eINSTANCE.getFSMDStateMachine_Memos();

        /**
         * The meta object literal for the '<em><b>Regions</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FSM_DSTATE_MACHINE__REGIONS = eINSTANCE.getFSMDStateMachine_Regions();

        /**
         * The meta object literal for the '<em><b>Fmsdevent</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FSM_DSTATE_MACHINE__FMSDEVENT = eINSTANCE.getFSMDStateMachine_Fmsdevent();

        /**
         * The meta object literal for the '<em><b>Actions</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference FSM_DSTATE_MACHINE__ACTIONS = eINSTANCE.getFSMDStateMachine_Actions();

        /**
         * The meta object literal for the '<em><b>Lines</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FSM_DSTATE_MACHINE__LINES = eINSTANCE.getFSMDStateMachine_Lines();

        /**
         * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FSM_DSTATE_MACHINE__VARIABLES = eINSTANCE.getFSMDStateMachine_Variables();

        /**
         * The meta object literal for the '<em><b>Manhattan Mode</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FSM_DSTATE_MACHINE__MANHATTAN_MODE = eINSTANCE.getFSMDStateMachine_ManhattanMode();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.impl.FSMDVertexImpl <em>DVertex</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.impl.FSMDVertexImpl
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDVertex()
         * @generated
         */
        EClass FSM_DVERTEX = eINSTANCE.getFSMDVertex();

        /**
         * The meta object literal for the '<em><b>Top</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DVERTEX__TOP = eINSTANCE.getFSMDVertex_Top();

        /**
         * The meta object literal for the '<em><b>Left</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DVERTEX__LEFT = eINSTANCE.getFSMDVertex_Left();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DVERTEX__HEIGHT = eINSTANCE.getFSMDVertex_Height();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DVERTEX__WIDTH = eINSTANCE.getFSMDVertex_Width();

        /**
         * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FSM_DVERTEX__PROPERTIES = eINSTANCE.getFSMDVertex_Properties();

        /**
         * The meta object literal for the '<em><b>Background</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FSM_DVERTEX__BACKGROUND = eINSTANCE.getFSMDVertex_Background();

        /**
         * The meta object literal for the '<em><b>Foreground</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FSM_DVERTEX__FOREGROUND = eINSTANCE.getFSMDVertex_Foreground();

        /**
         * The meta object literal for the '<em><b>Incoming Line</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference FSM_DVERTEX__INCOMING_LINE = eINSTANCE.getFSMDVertex_IncomingLine();

        /**
         * The meta object literal for the '<em><b>Outgoing Line</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference FSM_DVERTEX__OUTGOING_LINE = eINSTANCE.getFSMDVertex_OutgoingLine();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.impl.FSMDPropertyImpl <em>DProperty</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.impl.FSMDPropertyImpl
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDProperty()
         * @generated
         */
        EClass FSM_DPROPERTY = eINSTANCE.getFSMDProperty();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DPROPERTY__KEY = eINSTANCE.getFSMDProperty_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DPROPERTY__VALUE = eINSTANCE.getFSMDProperty_Value();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.impl.FSMDStateImpl <em>DState</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.impl.FSMDStateImpl
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDState()
         * @generated
         */
        EClass FSM_DSTATE = eINSTANCE.getFSMDState();

        /**
         * The meta object literal for the '<em><b>Ref</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DSTATE__REF = eINSTANCE.getFSMDState_Ref();

        /**
         * The meta object literal for the '<em><b>Ref Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FSM_DSTATE__REF_NAME = eINSTANCE.getFSMDState_RefName();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DSTATE__NAME = eINSTANCE.getFSMDState_Name();

        /**
         * The meta object literal for the '<em><b>Entry</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DSTATE__ENTRY = eINSTANCE.getFSMDState_Entry();

        /**
         * The meta object literal for the '<em><b>Exit</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DSTATE__EXIT = eINSTANCE.getFSMDState_Exit();

        /**
         * The meta object literal for the '<em><b>Do</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute FSM_DSTATE__DO = eINSTANCE.getFSMDState_Do();

        /**
         * The meta object literal for the '<em><b>Regions</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FSM_DSTATE__REGIONS = eINSTANCE.getFSMDState_Regions();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DSTATE__TYPE = eINSTANCE.getFSMDState_Type();

        /**
         * The meta object literal for the '<em><b>Incoming Transition</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FSM_DSTATE__INCOMING_TRANSITION = eINSTANCE.getFSMDState_IncomingTransition();

        /**
         * The meta object literal for the '<em><b>Outgoing Transition</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FSM_DSTATE__OUTGOING_TRANSITION = eINSTANCE.getFSMDState_OutgoingTransition();

        /**
         * The meta object literal for the '<em><b>Refuuid</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FSM_DSTATE__REFUUID = eINSTANCE.getFSMDState_Refuuid();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.impl.FSMDTransitionImpl <em>DTransition</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.impl.FSMDTransitionImpl
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDTransition()
         * @generated
         */
        EClass FSM_DTRANSITION = eINSTANCE.getFSMDTransition();

        /**
         * The meta object literal for the '<em><b>Trigger</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FSM_DTRANSITION__TRIGGER = eINSTANCE.getFSMDTransition_Trigger();

        /**
         * The meta object literal for the '<em><b>Event</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DTRANSITION__EVENT = eINSTANCE.getFSMDTransition_Event();

        /**
         * The meta object literal for the '<em><b>Condition</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FSM_DTRANSITION__CONDITION = eINSTANCE.getFSMDTransition_Condition();

        /**
         * The meta object literal for the '<em><b>Action</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DTRANSITION__ACTION = eINSTANCE.getFSMDTransition_Action();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute FSM_DTRANSITION__X = eINSTANCE.getFSMDTransition_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute FSM_DTRANSITION__Y = eINSTANCE.getFSMDTransition_Y();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference FSM_DTRANSITION__TARGET = eINSTANCE.getFSMDTransition_Target();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference FSM_DTRANSITION__SOURCE = eINSTANCE.getFSMDTransition_Source();

        /**
         * The meta object literal for the '<em><b>Priority</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FSM_DTRANSITION__PRIORITY = eINSTANCE.getFSMDTransition_Priority();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.impl.FSMDReferenceableImpl
         * <em>DReferenceable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.impl.FSMDReferenceableImpl
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDReferenceable()
         * @generated
         */
        EClass FSM_DREFERENCEABLE = eINSTANCE.getFSMDReferenceable();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute FSM_DREFERENCEABLE__ID = eINSTANCE.getFSMDReferenceable_Id();

        /**
         * The meta object literal for the '<em><b>Uuid</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DREFERENCEABLE__UUID = eINSTANCE.getFSMDReferenceable_Uuid();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.impl.FSMDMemoImpl <em>DMemo</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.impl.FSMDMemoImpl
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDMemo()
         * @generated
         */
        EClass FSM_DMEMO = eINSTANCE.getFSMDMemo();

        /**
         * The meta object literal for the '<em><b>Text</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DMEMO__TEXT = eINSTANCE.getFSMDMemo_Text();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.impl.FSMDRegionImpl <em>DRegion</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.impl.FSMDRegionImpl
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDRegion()
         * @generated
         */
        EClass FSM_DREGION = eINSTANCE.getFSMDRegion();

        /**
         * The meta object literal for the '<em><b>States</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FSM_DREGION__STATES = eINSTANCE.getFSMDRegion_States();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.impl.FSMDLineImpl <em>DLine</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.impl.FSMDLineImpl
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDLine()
         * @generated
         */
        EClass FSM_DLINE = eINSTANCE.getFSMDLine();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference FSM_DLINE__SOURCE = eINSTANCE.getFSMDLine_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference FSM_DLINE__TARGET = eINSTANCE.getFSMDLine_Target();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.impl.FSMDEventImpl <em>DEvent</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.impl.FSMDEventImpl
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDEvent()
         * @generated
         */
        EClass FSM_DEVENT = eINSTANCE.getFSMDEvent();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DEVENT__NAME = eINSTANCE.getFSMDEvent_Name();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.impl.FSMDActionImpl <em>DAction</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.impl.FSMDActionImpl
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDAction()
         * @generated
         */
        EClass FSM_DACTION = eINSTANCE.getFSMDAction();

        /**
         * The meta object literal for the '<em><b>Text</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DACTION__TEXT = eINSTANCE.getFSMDAction_Text();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.impl.FSMDAbstractLineImpl <em>DAbstract Line</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.impl.FSMDAbstractLineImpl
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDAbstractLine()
         * @generated
         */
        EClass FSM_DABSTRACT_LINE = eINSTANCE.getFSMDAbstractLine();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.impl.FSMDPointImpl <em>DPoint</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.impl.FSMDPointImpl
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDPoint()
         * @generated
         */
        EClass FSM_DPOINT = eINSTANCE.getFSMDPoint();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.impl.FSMDVariableImpl <em>DVariable</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.impl.FSMDVariableImpl
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDVariable()
         * @generated
         */
        EClass FSM_DVARIABLE = eINSTANCE.getFSMDVariable();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DVARIABLE__NAME = eINSTANCE.getFSMDVariable_Name();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FSM_DVARIABLE__TYPE = eINSTANCE.getFSMDVariable_Type();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.FSMDPseudoStateType <em>DPseudo State Type</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.FSMDPseudoStateType
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDPseudoStateType()
         * @generated
         */
        EEnum FSM_DPSEUDO_STATE_TYPE = eINSTANCE.getFSMDPseudoStateType();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fsm.FSMDLineType <em>DLine Type</em>}' enum. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fsm.FSMDLineType
         * @see com.zipc.garden.model.fsm.impl.FSMPackageImpl#getFSMDLineType()
         * @generated
         */
        EEnum FSM_DLINE_TYPE = eINSTANCE.getFSMDLineType();

    }

} // FSMPackage
