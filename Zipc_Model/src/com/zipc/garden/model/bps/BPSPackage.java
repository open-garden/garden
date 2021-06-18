/**
 */
package com.zipc.garden.model.bps;

import com.zipc.garden.model.core.COREPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
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
 * @see com.zipc.garden.model.bps.BPSFactory
 * @model kind="package"
 * @generated
 */
public interface BPSPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "bps";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.bps";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "BPS";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    BPSPackage eINSTANCE = com.zipc.garden.model.bps.impl.BPSPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bps.impl.BPSRootImpl <em>Root</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bps.impl.BPSRootImpl
     * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSRoot()
     * @generated
     */
    int BPS_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>Active Option Index</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_ROOT__ACTIVE_OPTION_INDEX = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Options</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int BPS_ROOT__OPTIONS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Instance Arc</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int BPS_ROOT__INSTANCE_ARC = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The operation id for the '<em>Get Active Option</em>' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_ROOT___GET_ACTIVE_OPTION = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 1;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bps.impl.BPSOptionImpl <em>Option</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bps.impl.BPSOptionImpl
     * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSOption()
     * @generated
     */
    int BPS_OPTION = 1;

    /**
     * The feature id for the '<em><b>Target State Machines</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int BPS_OPTION__TARGET_STATE_MACHINES = 0;

    /**
     * The number of structural features of the '<em>Option</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_OPTION_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Option</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_OPTION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bps.impl.BPSStateCombinationOptionImpl <em>State Combination
     * Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bps.impl.BPSStateCombinationOptionImpl
     * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSStateCombinationOption()
     * @generated
     */
    int BPS_STATE_COMBINATION_OPTION = 2;

    /**
     * The feature id for the '<em><b>Target State Machines</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int BPS_STATE_COMBINATION_OPTION__TARGET_STATE_MACHINES = BPS_OPTION__TARGET_STATE_MACHINES;

    /**
     * The number of structural features of the '<em>State Combination Option</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_STATE_COMBINATION_OPTION_FEATURE_COUNT = BPS_OPTION_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>State Combination Option</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_STATE_COMBINATION_OPTION_OPERATION_COUNT = BPS_OPTION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bps.impl.BPSNSwitchOptionImpl <em>NSwitch Option</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bps.impl.BPSNSwitchOptionImpl
     * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSNSwitchOption()
     * @generated
     */
    int BPS_NSWITCH_OPTION = 3;

    /**
     * The feature id for the '<em><b>Target State Machines</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int BPS_NSWITCH_OPTION__TARGET_STATE_MACHINES = BPS_OPTION__TARGET_STATE_MACHINES;

    /**
     * The feature id for the '<em><b>TSM File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_NSWITCH_OPTION__TSM_FILE_ID = BPS_OPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>NSwitch</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_NSWITCH_OPTION__NSWITCH = BPS_OPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>NSwitch Option</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_NSWITCH_OPTION_FEATURE_COUNT = BPS_OPTION_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>NSwitch Option</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_NSWITCH_OPTION_OPERATION_COUNT = BPS_OPTION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bps.impl.BPSPathCombinationOptionImpl <em>Path Combination
     * Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bps.impl.BPSPathCombinationOptionImpl
     * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSPathCombinationOption()
     * @generated
     */
    int BPS_PATH_COMBINATION_OPTION = 4;

    /**
     * The feature id for the '<em><b>Target State Machines</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int BPS_PATH_COMBINATION_OPTION__TARGET_STATE_MACHINES = BPS_OPTION__TARGET_STATE_MACHINES;

    /**
     * The feature id for the '<em><b>Path Length</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_PATH_COMBINATION_OPTION__PATH_LENGTH = BPS_OPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Path Combination Option</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_PATH_COMBINATION_OPTION_FEATURE_COUNT = BPS_OPTION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Path Combination Option</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_PATH_COMBINATION_OPTION_OPERATION_COUNT = BPS_OPTION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bps.BPSEnablement <em>Enablement</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bps.BPSEnablement
     * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSEnablement()
     * @generated
     */
    int BPS_ENABLEMENT = 5;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_ENABLEMENT__ENABLED = 0;

    /**
     * The number of structural features of the '<em>Enablement</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_ENABLEMENT_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Enablement</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_ENABLEMENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bps.impl.BPSVariableImpl <em>Variable</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bps.impl.BPSVariableImpl
     * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSVariable()
     * @generated
     */
    int BPS_VARIABLE = 6;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_VARIABLE__NAME = 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_VARIABLE__TYPE = 1;

    /**
     * The number of structural features of the '<em>Variable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_VARIABLE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Variable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_VARIABLE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bps.impl.BPSDataflowImpl <em>Dataflow</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bps.impl.BPSDataflowImpl
     * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSDataflow()
     * @generated
     */
    int BPS_DATAFLOW = 7;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_DATAFLOW__SOURCE = 0;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_DATAFLOW__TARGET = 1;

    /**
     * The number of structural features of the '<em>Dataflow</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_DATAFLOW_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Dataflow</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_DATAFLOW_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bps.BPSInstanceElement <em>Instance Element</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bps.BPSInstanceElement
     * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSInstanceElement()
     * @generated
     */
    int BPS_INSTANCE_ELEMENT = 8;

    /**
     * The number of structural features of the '<em>Instance Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_ELEMENT_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Instance Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_ELEMENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bps.impl.BPSInstanceArcImpl <em>Instance Arc</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bps.impl.BPSInstanceArcImpl
     * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSInstanceArc()
     * @generated
     */
    int BPS_INSTANCE_ARC = 9;

    /**
     * The feature id for the '<em><b>Original Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_ARC__ORIGINAL_UUID = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>State Machines</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_ARC__STATE_MACHINES = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Dataflows</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_ARC__DATAFLOWS = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Instance Arc</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_ARC_FEATURE_COUNT = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Instance Arc</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_ARC_OPERATION_COUNT = BPS_INSTANCE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bps.impl.BPSInstanceStateMachineImpl <em>Instance State
     * Machine</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bps.impl.BPSInstanceStateMachineImpl
     * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSInstanceStateMachine()
     * @generated
     */
    int BPS_INSTANCE_STATE_MACHINE = 10;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE_MACHINE__ENABLED = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Original Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE_MACHINE__ORIGINAL_NAME = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Original Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE_MACHINE__ORIGINAL_UUID = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Eval Priority</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE_MACHINE__EVAL_PRIORITY = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Initial State</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE_MACHINE__INITIAL_STATE = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>States</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE_MACHINE__STATES = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Variables</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE_MACHINE__VARIABLES = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>Instance State Machine</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE_MACHINE_FEATURE_COUNT = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The number of operations of the '<em>Instance State Machine</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE_MACHINE_OPERATION_COUNT = BPS_INSTANCE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bps.impl.BPSInstanceStateImpl <em>Instance State</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bps.impl.BPSInstanceStateImpl
     * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSInstanceState()
     * @generated
     */
    int BPS_INSTANCE_STATE = 11;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE__ENABLED = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Original Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE__ORIGINAL_NAME = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE__TYPE = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Transitions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE__TRANSITIONS = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Instance State</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE_FEATURE_COUNT = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The number of operations of the '<em>Instance State</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_STATE_OPERATION_COUNT = BPS_INSTANCE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bps.impl.BPSInstanceTransitionImpl <em>Instance
     * Transition</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bps.impl.BPSInstanceTransitionImpl
     * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSInstanceTransition()
     * @generated
     */
    int BPS_INSTANCE_TRANSITION = 12;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_TRANSITION__ENABLED = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Priority</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_TRANSITION__PRIORITY = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Trigger</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_TRANSITION__TRIGGER = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Event</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_TRANSITION__EVENT = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Condition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_TRANSITION__CONDITION = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Action</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_TRANSITION__ACTION = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_TRANSITION__SOURCE = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_TRANSITION__TARGET = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Instance Transition</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_TRANSITION_FEATURE_COUNT = BPS_INSTANCE_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The number of operations of the '<em>Instance Transition</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BPS_INSTANCE_TRANSITION_OPERATION_COUNT = BPS_INSTANCE_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.bps.BPSInstancePseudoStateType <em>Instance Pseudo State
     * Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.bps.BPSInstancePseudoStateType
     * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSInstancePseudoStateType()
     * @generated
     */
    int BPS_INSTANCE_PSEUDO_STATE_TYPE = 13;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bps.BPSRoot <em>Root</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.bps.BPSRoot
     * @generated
     */
    EClass getBPSRoot();

    /**
     * Returns the meta object for the '{@link com.zipc.garden.model.bps.BPSRoot#getActiveOption() <em>Get Active Option</em>}'
     * operation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Active Option</em>' operation.
     * @see com.zipc.garden.model.bps.BPSRoot#getActiveOption()
     * @generated
     */
    EOperation getBPSRoot__GetActiveOption();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSRoot#getActiveOptionIndex <em>Active
     * Option Index</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Active Option Index</em>'.
     * @see com.zipc.garden.model.bps.BPSRoot#getActiveOptionIndex()
     * @see #getBPSRoot()
     * @generated
     */
    EAttribute getBPSRoot_ActiveOptionIndex();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.bps.BPSRoot#getOptions
     * <em>Options</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Options</em>'.
     * @see com.zipc.garden.model.bps.BPSRoot#getOptions()
     * @see #getBPSRoot()
     * @generated
     */
    EReference getBPSRoot_Options();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.bps.BPSRoot#getInstanceArc
     * <em>Instance Arc</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Instance Arc</em>'.
     * @see com.zipc.garden.model.bps.BPSRoot#getInstanceArc()
     * @see #getBPSRoot()
     * @generated
     */
    EReference getBPSRoot_InstanceArc();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bps.BPSOption <em>Option</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Option</em>'.
     * @see com.zipc.garden.model.bps.BPSOption
     * @generated
     */
    EClass getBPSOption();

    /**
     * Returns the meta object for the attribute list '{@link com.zipc.garden.model.bps.BPSOption#getTargetStateMachines
     * <em>Target State Machines</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Target State Machines</em>'.
     * @see com.zipc.garden.model.bps.BPSOption#getTargetStateMachines()
     * @see #getBPSOption()
     * @generated
     */
    EAttribute getBPSOption_TargetStateMachines();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bps.BPSStateCombinationOption <em>State Combination
     * Option</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>State Combination Option</em>'.
     * @see com.zipc.garden.model.bps.BPSStateCombinationOption
     * @generated
     */
    EClass getBPSStateCombinationOption();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bps.BPSNSwitchOption <em>NSwitch Option</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>NSwitch Option</em>'.
     * @see com.zipc.garden.model.bps.BPSNSwitchOption
     * @generated
     */
    EClass getBPSNSwitchOption();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSNSwitchOption#getTSMFileId <em>TSM File
     * Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>TSM File Id</em>'.
     * @see com.zipc.garden.model.bps.BPSNSwitchOption#getTSMFileId()
     * @see #getBPSNSwitchOption()
     * @generated
     */
    EAttribute getBPSNSwitchOption_TSMFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSNSwitchOption#getNSwitch
     * <em>NSwitch</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>NSwitch</em>'.
     * @see com.zipc.garden.model.bps.BPSNSwitchOption#getNSwitch()
     * @see #getBPSNSwitchOption()
     * @generated
     */
    EAttribute getBPSNSwitchOption_NSwitch();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bps.BPSPathCombinationOption <em>Path Combination
     * Option</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Path Combination Option</em>'.
     * @see com.zipc.garden.model.bps.BPSPathCombinationOption
     * @generated
     */
    EClass getBPSPathCombinationOption();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSPathCombinationOption#getPathLength
     * <em>Path Length</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Path Length</em>'.
     * @see com.zipc.garden.model.bps.BPSPathCombinationOption#getPathLength()
     * @see #getBPSPathCombinationOption()
     * @generated
     */
    EAttribute getBPSPathCombinationOption_PathLength();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bps.BPSEnablement <em>Enablement</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Enablement</em>'.
     * @see com.zipc.garden.model.bps.BPSEnablement
     * @generated
     */
    EClass getBPSEnablement();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSEnablement#isEnabled <em>Enabled</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Enabled</em>'.
     * @see com.zipc.garden.model.bps.BPSEnablement#isEnabled()
     * @see #getBPSEnablement()
     * @generated
     */
    EAttribute getBPSEnablement_Enabled();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bps.BPSVariable <em>Variable</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Variable</em>'.
     * @see com.zipc.garden.model.bps.BPSVariable
     * @generated
     */
    EClass getBPSVariable();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSVariable#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.bps.BPSVariable#getName()
     * @see #getBPSVariable()
     * @generated
     */
    EAttribute getBPSVariable_Name();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSVariable#getType <em>Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see com.zipc.garden.model.bps.BPSVariable#getType()
     * @see #getBPSVariable()
     * @generated
     */
    EAttribute getBPSVariable_Type();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bps.BPSDataflow <em>Dataflow</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Dataflow</em>'.
     * @see com.zipc.garden.model.bps.BPSDataflow
     * @generated
     */
    EClass getBPSDataflow();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.bps.BPSDataflow#getSource <em>Source</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see com.zipc.garden.model.bps.BPSDataflow#getSource()
     * @see #getBPSDataflow()
     * @generated
     */
    EReference getBPSDataflow_Source();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.bps.BPSDataflow#getTarget <em>Target</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see com.zipc.garden.model.bps.BPSDataflow#getTarget()
     * @see #getBPSDataflow()
     * @generated
     */
    EReference getBPSDataflow_Target();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bps.BPSInstanceElement <em>Instance Element</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Instance Element</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceElement
     * @generated
     */
    EClass getBPSInstanceElement();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bps.BPSInstanceArc <em>Instance Arc</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Instance Arc</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceArc
     * @generated
     */
    EClass getBPSInstanceArc();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSInstanceArc#getOriginalUuid <em>Original
     * Uuid</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Original Uuid</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceArc#getOriginalUuid()
     * @see #getBPSInstanceArc()
     * @generated
     */
    EAttribute getBPSInstanceArc_OriginalUuid();

    /**
     * Returns the meta object for the containment reference list
     * '{@link com.zipc.garden.model.bps.BPSInstanceArc#getStateMachines <em>State Machines</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>State Machines</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceArc#getStateMachines()
     * @see #getBPSInstanceArc()
     * @generated
     */
    EReference getBPSInstanceArc_StateMachines();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.bps.BPSInstanceArc#getDataflows
     * <em>Dataflows</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Dataflows</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceArc#getDataflows()
     * @see #getBPSInstanceArc()
     * @generated
     */
    EReference getBPSInstanceArc_Dataflows();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bps.BPSInstanceStateMachine <em>Instance State
     * Machine</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Instance State Machine</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceStateMachine
     * @generated
     */
    EClass getBPSInstanceStateMachine();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getOriginalName
     * <em>Original Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Original Name</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceStateMachine#getOriginalName()
     * @see #getBPSInstanceStateMachine()
     * @generated
     */
    EAttribute getBPSInstanceStateMachine_OriginalName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getOriginalUuid
     * <em>Original Uuid</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Original Uuid</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceStateMachine#getOriginalUuid()
     * @see #getBPSInstanceStateMachine()
     * @generated
     */
    EAttribute getBPSInstanceStateMachine_OriginalUuid();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getEvalPriority
     * <em>Eval Priority</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Eval Priority</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceStateMachine#getEvalPriority()
     * @see #getBPSInstanceStateMachine()
     * @generated
     */
    EAttribute getBPSInstanceStateMachine_EvalPriority();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getInitialState
     * <em>Initial State</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Initial State</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceStateMachine#getInitialState()
     * @see #getBPSInstanceStateMachine()
     * @generated
     */
    EReference getBPSInstanceStateMachine_InitialState();

    /**
     * Returns the meta object for the containment reference list
     * '{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getStates <em>States</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>States</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceStateMachine#getStates()
     * @see #getBPSInstanceStateMachine()
     * @generated
     */
    EReference getBPSInstanceStateMachine_States();

    /**
     * Returns the meta object for the containment reference list
     * '{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getVariables <em>Variables</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>Variables</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceStateMachine#getVariables()
     * @see #getBPSInstanceStateMachine()
     * @generated
     */
    EReference getBPSInstanceStateMachine_Variables();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bps.BPSInstanceState <em>Instance State</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Instance State</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceState
     * @generated
     */
    EClass getBPSInstanceState();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSInstanceState#getOriginalName <em>Original
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Original Name</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceState#getOriginalName()
     * @see #getBPSInstanceState()
     * @generated
     */
    EAttribute getBPSInstanceState_OriginalName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSInstanceState#getType <em>Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceState#getType()
     * @see #getBPSInstanceState()
     * @generated
     */
    EAttribute getBPSInstanceState_Type();

    /**
     * Returns the meta object for the containment reference list
     * '{@link com.zipc.garden.model.bps.BPSInstanceState#getTransitions <em>Transitions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>Transitions</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceState#getTransitions()
     * @see #getBPSInstanceState()
     * @generated
     */
    EReference getBPSInstanceState_Transitions();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.bps.BPSInstanceTransition <em>Instance Transition</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Instance Transition</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceTransition
     * @generated
     */
    EClass getBPSInstanceTransition();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSInstanceTransition#getPriority
     * <em>Priority</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Priority</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceTransition#getPriority()
     * @see #getBPSInstanceTransition()
     * @generated
     */
    EAttribute getBPSInstanceTransition_Priority();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSInstanceTransition#getTrigger
     * <em>Trigger</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Trigger</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceTransition#getTrigger()
     * @see #getBPSInstanceTransition()
     * @generated
     */
    EAttribute getBPSInstanceTransition_Trigger();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSInstanceTransition#getEvent
     * <em>Event</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Event</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceTransition#getEvent()
     * @see #getBPSInstanceTransition()
     * @generated
     */
    EAttribute getBPSInstanceTransition_Event();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSInstanceTransition#getCondition
     * <em>Condition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Condition</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceTransition#getCondition()
     * @see #getBPSInstanceTransition()
     * @generated
     */
    EAttribute getBPSInstanceTransition_Condition();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.bps.BPSInstanceTransition#getAction
     * <em>Action</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Action</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceTransition#getAction()
     * @see #getBPSInstanceTransition()
     * @generated
     */
    EAttribute getBPSInstanceTransition_Action();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.bps.BPSInstanceTransition#getSource
     * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceTransition#getSource()
     * @see #getBPSInstanceTransition()
     * @generated
     */
    EReference getBPSInstanceTransition_Source();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.bps.BPSInstanceTransition#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see com.zipc.garden.model.bps.BPSInstanceTransition#getTarget()
     * @see #getBPSInstanceTransition()
     * @generated
     */
    EReference getBPSInstanceTransition_Target();

    /**
     * Returns the meta object for enum '{@link com.zipc.garden.model.bps.BPSInstancePseudoStateType <em>Instance Pseudo State
     * Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Instance Pseudo State Type</em>'.
     * @see com.zipc.garden.model.bps.BPSInstancePseudoStateType
     * @generated
     */
    EEnum getBPSInstancePseudoStateType();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    BPSFactory getBPSFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.bps.impl.BPSRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bps.impl.BPSRootImpl
         * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSRoot()
         * @generated
         */
        EClass BPS_ROOT = eINSTANCE.getBPSRoot();

        /**
         * The meta object literal for the '<em><b>Get Active Option</b></em>' operation. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EOperation BPS_ROOT___GET_ACTIVE_OPTION = eINSTANCE.getBPSRoot__GetActiveOption();

        /**
         * The meta object literal for the '<em><b>Active Option Index</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute BPS_ROOT__ACTIVE_OPTION_INDEX = eINSTANCE.getBPSRoot_ActiveOptionIndex();

        /**
         * The meta object literal for the '<em><b>Options</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BPS_ROOT__OPTIONS = eINSTANCE.getBPSRoot_Options();

        /**
         * The meta object literal for the '<em><b>Instance Arc</b></em>' containment reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BPS_ROOT__INSTANCE_ARC = eINSTANCE.getBPSRoot_InstanceArc();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bps.impl.BPSOptionImpl <em>Option</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bps.impl.BPSOptionImpl
         * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSOption()
         * @generated
         */
        EClass BPS_OPTION = eINSTANCE.getBPSOption();

        /**
         * The meta object literal for the '<em><b>Target State Machines</b></em>' attribute list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute BPS_OPTION__TARGET_STATE_MACHINES = eINSTANCE.getBPSOption_TargetStateMachines();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bps.impl.BPSStateCombinationOptionImpl <em>State
         * Combination Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bps.impl.BPSStateCombinationOptionImpl
         * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSStateCombinationOption()
         * @generated
         */
        EClass BPS_STATE_COMBINATION_OPTION = eINSTANCE.getBPSStateCombinationOption();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bps.impl.BPSNSwitchOptionImpl <em>NSwitch Option</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bps.impl.BPSNSwitchOptionImpl
         * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSNSwitchOption()
         * @generated
         */
        EClass BPS_NSWITCH_OPTION = eINSTANCE.getBPSNSwitchOption();

        /**
         * The meta object literal for the '<em><b>TSM File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute BPS_NSWITCH_OPTION__TSM_FILE_ID = eINSTANCE.getBPSNSwitchOption_TSMFileId();

        /**
         * The meta object literal for the '<em><b>NSwitch</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute BPS_NSWITCH_OPTION__NSWITCH = eINSTANCE.getBPSNSwitchOption_NSwitch();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bps.impl.BPSPathCombinationOptionImpl <em>Path
         * Combination Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bps.impl.BPSPathCombinationOptionImpl
         * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSPathCombinationOption()
         * @generated
         */
        EClass BPS_PATH_COMBINATION_OPTION = eINSTANCE.getBPSPathCombinationOption();

        /**
         * The meta object literal for the '<em><b>Path Length</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute BPS_PATH_COMBINATION_OPTION__PATH_LENGTH = eINSTANCE.getBPSPathCombinationOption_PathLength();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bps.BPSEnablement <em>Enablement</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bps.BPSEnablement
         * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSEnablement()
         * @generated
         */
        EClass BPS_ENABLEMENT = eINSTANCE.getBPSEnablement();

        /**
         * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute BPS_ENABLEMENT__ENABLED = eINSTANCE.getBPSEnablement_Enabled();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bps.impl.BPSVariableImpl <em>Variable</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bps.impl.BPSVariableImpl
         * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSVariable()
         * @generated
         */
        EClass BPS_VARIABLE = eINSTANCE.getBPSVariable();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BPS_VARIABLE__NAME = eINSTANCE.getBPSVariable_Name();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BPS_VARIABLE__TYPE = eINSTANCE.getBPSVariable_Type();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bps.impl.BPSDataflowImpl <em>Dataflow</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bps.impl.BPSDataflowImpl
         * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSDataflow()
         * @generated
         */
        EClass BPS_DATAFLOW = eINSTANCE.getBPSDataflow();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference BPS_DATAFLOW__SOURCE = eINSTANCE.getBPSDataflow_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference BPS_DATAFLOW__TARGET = eINSTANCE.getBPSDataflow_Target();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bps.BPSInstanceElement <em>Instance Element</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bps.BPSInstanceElement
         * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSInstanceElement()
         * @generated
         */
        EClass BPS_INSTANCE_ELEMENT = eINSTANCE.getBPSInstanceElement();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bps.impl.BPSInstanceArcImpl <em>Instance Arc</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bps.impl.BPSInstanceArcImpl
         * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSInstanceArc()
         * @generated
         */
        EClass BPS_INSTANCE_ARC = eINSTANCE.getBPSInstanceArc();

        /**
         * The meta object literal for the '<em><b>Original Uuid</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute BPS_INSTANCE_ARC__ORIGINAL_UUID = eINSTANCE.getBPSInstanceArc_OriginalUuid();

        /**
         * The meta object literal for the '<em><b>State Machines</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference BPS_INSTANCE_ARC__STATE_MACHINES = eINSTANCE.getBPSInstanceArc_StateMachines();

        /**
         * The meta object literal for the '<em><b>Dataflows</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference BPS_INSTANCE_ARC__DATAFLOWS = eINSTANCE.getBPSInstanceArc_Dataflows();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bps.impl.BPSInstanceStateMachineImpl <em>Instance State
         * Machine</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bps.impl.BPSInstanceStateMachineImpl
         * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSInstanceStateMachine()
         * @generated
         */
        EClass BPS_INSTANCE_STATE_MACHINE = eINSTANCE.getBPSInstanceStateMachine();

        /**
         * The meta object literal for the '<em><b>Original Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute BPS_INSTANCE_STATE_MACHINE__ORIGINAL_NAME = eINSTANCE.getBPSInstanceStateMachine_OriginalName();

        /**
         * The meta object literal for the '<em><b>Original Uuid</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute BPS_INSTANCE_STATE_MACHINE__ORIGINAL_UUID = eINSTANCE.getBPSInstanceStateMachine_OriginalUuid();

        /**
         * The meta object literal for the '<em><b>Eval Priority</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute BPS_INSTANCE_STATE_MACHINE__EVAL_PRIORITY = eINSTANCE.getBPSInstanceStateMachine_EvalPriority();

        /**
         * The meta object literal for the '<em><b>Initial State</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference BPS_INSTANCE_STATE_MACHINE__INITIAL_STATE = eINSTANCE.getBPSInstanceStateMachine_InitialState();

        /**
         * The meta object literal for the '<em><b>States</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference BPS_INSTANCE_STATE_MACHINE__STATES = eINSTANCE.getBPSInstanceStateMachine_States();

        /**
         * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference BPS_INSTANCE_STATE_MACHINE__VARIABLES = eINSTANCE.getBPSInstanceStateMachine_Variables();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bps.impl.BPSInstanceStateImpl <em>Instance State</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bps.impl.BPSInstanceStateImpl
         * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSInstanceState()
         * @generated
         */
        EClass BPS_INSTANCE_STATE = eINSTANCE.getBPSInstanceState();

        /**
         * The meta object literal for the '<em><b>Original Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute BPS_INSTANCE_STATE__ORIGINAL_NAME = eINSTANCE.getBPSInstanceState_OriginalName();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BPS_INSTANCE_STATE__TYPE = eINSTANCE.getBPSInstanceState_Type();

        /**
         * The meta object literal for the '<em><b>Transitions</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference BPS_INSTANCE_STATE__TRANSITIONS = eINSTANCE.getBPSInstanceState_Transitions();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bps.impl.BPSInstanceTransitionImpl <em>Instance
         * Transition</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bps.impl.BPSInstanceTransitionImpl
         * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSInstanceTransition()
         * @generated
         */
        EClass BPS_INSTANCE_TRANSITION = eINSTANCE.getBPSInstanceTransition();

        /**
         * The meta object literal for the '<em><b>Priority</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute BPS_INSTANCE_TRANSITION__PRIORITY = eINSTANCE.getBPSInstanceTransition_Priority();

        /**
         * The meta object literal for the '<em><b>Trigger</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute BPS_INSTANCE_TRANSITION__TRIGGER = eINSTANCE.getBPSInstanceTransition_Trigger();

        /**
         * The meta object literal for the '<em><b>Event</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BPS_INSTANCE_TRANSITION__EVENT = eINSTANCE.getBPSInstanceTransition_Event();

        /**
         * The meta object literal for the '<em><b>Condition</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute BPS_INSTANCE_TRANSITION__CONDITION = eINSTANCE.getBPSInstanceTransition_Condition();

        /**
         * The meta object literal for the '<em><b>Action</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute BPS_INSTANCE_TRANSITION__ACTION = eINSTANCE.getBPSInstanceTransition_Action();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference BPS_INSTANCE_TRANSITION__SOURCE = eINSTANCE.getBPSInstanceTransition_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference BPS_INSTANCE_TRANSITION__TARGET = eINSTANCE.getBPSInstanceTransition_Target();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.bps.BPSInstancePseudoStateType <em>Instance Pseudo
         * State Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.bps.BPSInstancePseudoStateType
         * @see com.zipc.garden.model.bps.impl.BPSPackageImpl#getBPSInstancePseudoStateType()
         * @generated
         */
        EEnum BPS_INSTANCE_PSEUDO_STATE_TYPE = eINSTANCE.getBPSInstancePseudoStateType();

    }

} // BPSPackage
