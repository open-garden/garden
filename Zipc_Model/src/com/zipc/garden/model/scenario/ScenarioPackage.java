/**
 */
package com.zipc.garden.model.scenario;

import com.zipc.garden.model.core.COREPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.zipc.garden.model.scenario.ScenarioFactory
 * @model kind="package"
 * @generated
 */
public interface ScenarioPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "scenario";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.scenario";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "SCENARIO";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ScenarioPackage eINSTANCE = com.zipc.garden.model.scenario.impl.ScenarioPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.ScenarioRootImpl <em>Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.ScenarioRootImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getScenarioRoot()
     * @generated
     */
    int SCENARIO_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>Road Setting</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO_ROOT__ROAD_SETTING = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Actor Setting</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO_ROOT__ACTOR_SETTING = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Phase Setting</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO_ROOT__PHASE_SETTING = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Root</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Root</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.RoadSettingImpl <em>Road Setting</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.RoadSettingImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRoadSetting()
     * @generated
     */
    int ROAD_SETTING = 1;

    /**
     * The feature id for the '<em><b>Lane Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROAD_SETTING__LANE_COUNT = 0;

    /**
     * The number of structural features of the '<em>Road Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROAD_SETTING_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Road Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ROAD_SETTING_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.ActorSettingImpl <em>Actor Setting</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.ActorSettingImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getActorSetting()
     * @generated
     */
    int ACTOR_SETTING = 2;

    /**
     * The feature id for the '<em><b>Ego</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_SETTING__EGO = 0;

    /**
     * The feature id for the '<em><b>Others</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_SETTING__OTHERS = 1;

    /**
     * The number of structural features of the '<em>Actor Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_SETTING_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Actor Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_SETTING_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.ActorImpl <em>Actor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.ActorImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getActor()
     * @generated
     */
    int ACTOR = 12;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR__NAME = 0;

    /**
     * The number of structural features of the '<em>Actor</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Actor</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.VehicleImpl <em>Vehicle</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.VehicleImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getVehicle()
     * @generated
     */
    int VEHICLE = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE__NAME = ACTOR__NAME;

    /**
     * The number of structural features of the '<em>Vehicle</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_FEATURE_COUNT = ACTOR_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Vehicle</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_OPERATION_COUNT = ACTOR_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.EgoVehicleImpl <em>Ego Vehicle</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.EgoVehicleImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getEgoVehicle()
     * @generated
     */
    int EGO_VEHICLE = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EGO_VEHICLE__NAME = VEHICLE__NAME;

    /**
     * The number of structural features of the '<em>Ego Vehicle</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EGO_VEHICLE_FEATURE_COUNT = VEHICLE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Ego Vehicle</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EGO_VEHICLE_OPERATION_COUNT = VEHICLE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.OtherVehicleImpl <em>Other Vehicle</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.OtherVehicleImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getOtherVehicle()
     * @generated
     */
    int OTHER_VEHICLE = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OTHER_VEHICLE__NAME = VEHICLE__NAME;

    /**
     * The number of structural features of the '<em>Other Vehicle</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OTHER_VEHICLE_FEATURE_COUNT = VEHICLE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Other Vehicle</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OTHER_VEHICLE_OPERATION_COUNT = VEHICLE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.PhaseSettingImpl <em>Phase Setting</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.PhaseSettingImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getPhaseSetting()
     * @generated
     */
    int PHASE_SETTING = 6;

    /**
     * The feature id for the '<em><b>Initial Phase</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE_SETTING__INITIAL_PHASE = 0;

    /**
     * The number of structural features of the '<em>Phase Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE_SETTING_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Phase Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE_SETTING_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.PhaseConditionImpl <em>Phase Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.PhaseConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getPhaseCondition()
     * @generated
     */
    int PHASE_CONDITION = 7;

    /**
     * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE_CONDITION__CONDITIONS = 0;

    /**
     * The number of structural features of the '<em>Phase Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE_CONDITION_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Phase Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE_CONDITION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.PhaseActionImpl <em>Phase Action</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.PhaseActionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getPhaseAction()
     * @generated
     */
    int PHASE_ACTION = 8;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE_ACTION__ACTIONS = 0;

    /**
     * The number of structural features of the '<em>Phase Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE_ACTION_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Phase Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE_ACTION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.PhaseImpl <em>Phase</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.PhaseImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getPhase()
     * @generated
     */
    int PHASE = 9;

    /**
     * The feature id for the '<em><b>Action</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE__ACTION = 0;

    /**
     * The feature id for the '<em><b>Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE__CONDITION = 1;

    /**
     * The feature id for the '<em><b>Next Phases</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE__NEXT_PHASES = 2;

    /**
     * The feature id for the '<em><b>Prev Phase</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE__PREV_PHASE = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE__NAME = 4;

    /**
     * The number of structural features of the '<em>Phase</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE_FEATURE_COUNT = 5;

    /**
     * The number of operations of the '<em>Phase</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PHASE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.ActionImpl <em>Action</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.ActionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAction()
     * @generated
     */
    int ACTION = 10;

    /**
     * The number of structural features of the '<em>Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTION_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.VehicleActionImpl <em>Vehicle Action</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.VehicleActionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getVehicleAction()
     * @generated
     */
    int VEHICLE_ACTION = 11;

    /**
     * The feature id for the '<em><b>Vehicle</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_ACTION__VEHICLE = ACTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Appear</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_ACTION__APPEAR = ACTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_ACTION__ACTIONS = ACTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Vehicle Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Vehicle Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_ACTION_OPERATION_COUNT = ACTION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.VehicleDetailActionImpl <em>Vehicle Detail Action</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.VehicleDetailActionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getVehicleDetailAction()
     * @generated
     */
    int VEHICLE_DETAIL_ACTION = 13;

    /**
     * The number of structural features of the '<em>Vehicle Detail Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_DETAIL_ACTION_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Vehicle Detail Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_DETAIL_ACTION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.LaneActionImpl <em>Lane Action</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.LaneActionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getLaneAction()
     * @generated
     */
    int LANE_ACTION = 14;

    /**
     * The feature id for the '<em><b>Lane</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_ACTION__LANE = VEHICLE_DETAIL_ACTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Lane Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_ACTION_FEATURE_COUNT = VEHICLE_DETAIL_ACTION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Lane Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_ACTION_OPERATION_COUNT = VEHICLE_DETAIL_ACTION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.LaneSettingImpl <em>Lane Setting</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.LaneSettingImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getLaneSetting()
     * @generated
     */
    int LANE_SETTING = 15;

    /**
     * The number of structural features of the '<em>Lane Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_SETTING_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Lane Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_SETTING_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.AbsoluteLaneImpl <em>Absolute Lane</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.AbsoluteLaneImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAbsoluteLane()
     * @generated
     */
    int ABSOLUTE_LANE = 16;

    /**
     * The feature id for the '<em><b>Lane Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_LANE__LANE_ID = LANE_SETTING_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Absolute Lane</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_LANE_FEATURE_COUNT = LANE_SETTING_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Absolute Lane</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_LANE_OPERATION_COUNT = LANE_SETTING_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.RelativeLaneImpl <em>Relative Lane</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.RelativeLaneImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRelativeLane()
     * @generated
     */
    int RELATIVE_LANE = 17;

    /**
     * The feature id for the '<em><b>Vehicle</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_LANE__VEHICLE = LANE_SETTING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Offset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_LANE__OFFSET = LANE_SETTING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Direction</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_LANE__DIRECTION = LANE_SETTING_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Relative Lane</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_LANE_FEATURE_COUNT = LANE_SETTING_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Relative Lane</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_LANE_OPERATION_COUNT = LANE_SETTING_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.SpeedActionImpl <em>Speed Action</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.SpeedActionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getSpeedAction()
     * @generated
     */
    int SPEED_ACTION = 18;

    /**
     * The feature id for the '<em><b>Speed</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPEED_ACTION__SPEED = VEHICLE_DETAIL_ACTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Speed Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPEED_ACTION_FEATURE_COUNT = VEHICLE_DETAIL_ACTION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Speed Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPEED_ACTION_OPERATION_COUNT = VEHICLE_DETAIL_ACTION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.SpeedSettingImpl <em>Speed Setting</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.SpeedSettingImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getSpeedSetting()
     * @generated
     */
    int SPEED_SETTING = 19;

    /**
     * The number of structural features of the '<em>Speed Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPEED_SETTING_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Speed Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPEED_SETTING_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.AbsoluteSpeedImpl <em>Absolute Speed</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.AbsoluteSpeedImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAbsoluteSpeed()
     * @generated
     */
    int ABSOLUTE_SPEED = 20;

    /**
     * The feature id for the '<em><b>Kph</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_SPEED__KPH = SPEED_SETTING_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Absolute Speed</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_SPEED_FEATURE_COUNT = SPEED_SETTING_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Absolute Speed</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_SPEED_OPERATION_COUNT = SPEED_SETTING_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.RelativeSpeedImpl <em>Relative Speed</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.RelativeSpeedImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRelativeSpeed()
     * @generated
     */
    int RELATIVE_SPEED = 21;

    /**
     * The feature id for the '<em><b>Vehicle</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_SPEED__VEHICLE = SPEED_SETTING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Kph</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_SPEED__KPH = SPEED_SETTING_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Relative Speed</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_SPEED_FEATURE_COUNT = SPEED_SETTING_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Relative Speed</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_SPEED_OPERATION_COUNT = SPEED_SETTING_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.DistanceActionImpl <em>Distance Action</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.DistanceActionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getDistanceAction()
     * @generated
     */
    int DISTANCE_ACTION = 22;

    /**
     * The feature id for the '<em><b>Distance</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DISTANCE_ACTION__DISTANCE = VEHICLE_DETAIL_ACTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Distance Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DISTANCE_ACTION_FEATURE_COUNT = VEHICLE_DETAIL_ACTION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Distance Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DISTANCE_ACTION_OPERATION_COUNT = VEHICLE_DETAIL_ACTION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.DistanceSettingImpl <em>Distance Setting</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.DistanceSettingImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getDistanceSetting()
     * @generated
     */
    int DISTANCE_SETTING = 23;

    /**
     * The number of structural features of the '<em>Distance Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DISTANCE_SETTING_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Distance Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DISTANCE_SETTING_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.RelativeDistanceImpl <em>Relative Distance</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.RelativeDistanceImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRelativeDistance()
     * @generated
     */
    int RELATIVE_DISTANCE = 24;

    /**
     * The feature id for the '<em><b>Vehicle</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_DISTANCE__VEHICLE = DISTANCE_SETTING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Direction</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_DISTANCE__DIRECTION = DISTANCE_SETTING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Meter</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_DISTANCE__METER = DISTANCE_SETTING_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Relative Distance</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_DISTANCE_FEATURE_COUNT = DISTANCE_SETTING_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Relative Distance</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_DISTANCE_OPERATION_COUNT = DISTANCE_SETTING_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.AccelActionImpl <em>Accel Action</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.AccelActionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAccelAction()
     * @generated
     */
    int ACCEL_ACTION = 25;

    /**
     * The feature id for the '<em><b>Accel</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACCEL_ACTION__ACCEL = VEHICLE_DETAIL_ACTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Accel Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACCEL_ACTION_FEATURE_COUNT = VEHICLE_DETAIL_ACTION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Accel Action</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACCEL_ACTION_OPERATION_COUNT = VEHICLE_DETAIL_ACTION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.AccelerationSettingImpl <em>Acceleration Setting</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.AccelerationSettingImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAccelerationSetting()
     * @generated
     */
    int ACCELERATION_SETTING = 26;

    /**
     * The number of structural features of the '<em>Acceleration Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACCELERATION_SETTING_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Acceleration Setting</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACCELERATION_SETTING_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.AbsoluteAccelerationImpl <em>Absolute Acceleration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.AbsoluteAccelerationImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAbsoluteAcceleration()
     * @generated
     */
    int ABSOLUTE_ACCELERATION = 27;

    /**
     * The feature id for the '<em><b>Mpss</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_ACCELERATION__MPSS = ACCELERATION_SETTING_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Absolute Acceleration</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_ACCELERATION_FEATURE_COUNT = ACCELERATION_SETTING_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Absolute Acceleration</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_ACCELERATION_OPERATION_COUNT = ACCELERATION_SETTING_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.ConditionImpl <em>Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.ConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getCondition()
     * @generated
     */
    int CONDITION = 28;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__ENABLED = 0;

    /**
     * The number of structural features of the '<em>Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.VehicleConditionImpl <em>Vehicle Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.VehicleConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getVehicleCondition()
     * @generated
     */
    int VEHICLE_CONDITION = 29;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_CONDITION__ENABLED = CONDITION__ENABLED;

    /**
     * The feature id for the '<em><b>Vehicle</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_CONDITION__VEHICLE = CONDITION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_CONDITION__CONDITIONS = CONDITION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Vehicle Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_CONDITION_FEATURE_COUNT = CONDITION_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Vehicle Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_CONDITION_OPERATION_COUNT = CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.VehicleDetailConditionImpl <em>Vehicle Detail Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.VehicleDetailConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getVehicleDetailCondition()
     * @generated
     */
    int VEHICLE_DETAIL_CONDITION = 30;

    /**
     * The feature id for the '<em><b>Time</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_DETAIL_CONDITION__TIME = 0;

    /**
     * The number of structural features of the '<em>Vehicle Detail Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_DETAIL_CONDITION_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Vehicle Detail Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VEHICLE_DETAIL_CONDITION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.TimeConditionImpl <em>Time Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.TimeConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getTimeCondition()
     * @generated
     */
    int TIME_CONDITION = 31;

    /**
     * The number of structural features of the '<em>Time Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIME_CONDITION_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Time Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIME_CONDITION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.TimeEvenOnceImpl <em>Time Even Once</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.TimeEvenOnceImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getTimeEvenOnce()
     * @generated
     */
    int TIME_EVEN_ONCE = 32;

    /**
     * The number of structural features of the '<em>Time Even Once</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIME_EVEN_ONCE_FEATURE_COUNT = TIME_CONDITION_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Time Even Once</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIME_EVEN_ONCE_OPERATION_COUNT = TIME_CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.TimeDurationImpl <em>Time Duration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.TimeDurationImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getTimeDuration()
     * @generated
     */
    int TIME_DURATION = 33;

    /**
     * The feature id for the '<em><b>Msec</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIME_DURATION__MSEC = TIME_CONDITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Time Duration</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIME_DURATION_FEATURE_COUNT = TIME_CONDITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Time Duration</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIME_DURATION_OPERATION_COUNT = TIME_CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.ValueConditionImpl <em>Value Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.ValueConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getValueCondition()
     * @generated
     */
    int VALUE_CONDITION = 34;

    /**
     * The number of structural features of the '<em>Value Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_CONDITION_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Value Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_CONDITION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.SimpleValueConditionImpl <em>Simple Value Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.SimpleValueConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getSimpleValueCondition()
     * @generated
     */
    int SIMPLE_VALUE_CONDITION = 35;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_VALUE_CONDITION__VALUE = VALUE_CONDITION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_VALUE_CONDITION__OPERATOR = VALUE_CONDITION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Simple Value Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_VALUE_CONDITION_FEATURE_COUNT = VALUE_CONDITION_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Simple Value Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMPLE_VALUE_CONDITION_OPERATION_COUNT = VALUE_CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.RangeValueConditionImpl <em>Range Value Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.RangeValueConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRangeValueCondition()
     * @generated
     */
    int RANGE_VALUE_CONDITION = 36;

    /**
     * The feature id for the '<em><b>Upper Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_VALUE_CONDITION__UPPER_VALUE = VALUE_CONDITION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Lower Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_VALUE_CONDITION__LOWER_VALUE = VALUE_CONDITION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Range Value Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_VALUE_CONDITION_FEATURE_COUNT = VALUE_CONDITION_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Range Value Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RANGE_VALUE_CONDITION_OPERATION_COUNT = VALUE_CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.LaneConditionImpl <em>Lane Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.LaneConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getLaneCondition()
     * @generated
     */
    int LANE_CONDITION = 37;

    /**
     * The feature id for the '<em><b>Time</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_CONDITION__TIME = VEHICLE_DETAIL_CONDITION__TIME;

    /**
     * The number of structural features of the '<em>Lane Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_CONDITION_FEATURE_COUNT = VEHICLE_DETAIL_CONDITION_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Lane Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LANE_CONDITION_OPERATION_COUNT = VEHICLE_DETAIL_CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.AbsoluteLaneConditionImpl <em>Absolute Lane Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.AbsoluteLaneConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAbsoluteLaneCondition()
     * @generated
     */
    int ABSOLUTE_LANE_CONDITION = 38;

    /**
     * The feature id for the '<em><b>Time</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_LANE_CONDITION__TIME = LANE_CONDITION__TIME;

    /**
     * The feature id for the '<em><b>Lane Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_LANE_CONDITION__LANE_ID = LANE_CONDITION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_LANE_CONDITION__OPERATOR = LANE_CONDITION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Absolute Lane Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_LANE_CONDITION_FEATURE_COUNT = LANE_CONDITION_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Absolute Lane Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_LANE_CONDITION_OPERATION_COUNT = LANE_CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.RelativeLaneConditionImpl <em>Relative Lane Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.RelativeLaneConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRelativeLaneCondition()
     * @generated
     */
    int RELATIVE_LANE_CONDITION = 39;

    /**
     * The feature id for the '<em><b>Time</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_LANE_CONDITION__TIME = LANE_CONDITION__TIME;

    /**
     * The feature id for the '<em><b>Vehicle</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_LANE_CONDITION__VEHICLE = LANE_CONDITION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Direction</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_LANE_CONDITION__DIRECTION = LANE_CONDITION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Offset</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_LANE_CONDITION__OFFSET = LANE_CONDITION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Relative Lane Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_LANE_CONDITION_FEATURE_COUNT = LANE_CONDITION_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Relative Lane Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_LANE_CONDITION_OPERATION_COUNT = LANE_CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.SpeedConditionImpl <em>Speed Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.SpeedConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getSpeedCondition()
     * @generated
     */
    int SPEED_CONDITION = 40;

    /**
     * The feature id for the '<em><b>Time</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPEED_CONDITION__TIME = VEHICLE_DETAIL_CONDITION__TIME;

    /**
     * The number of structural features of the '<em>Speed Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPEED_CONDITION_FEATURE_COUNT = VEHICLE_DETAIL_CONDITION_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Speed Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPEED_CONDITION_OPERATION_COUNT = VEHICLE_DETAIL_CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.AbsoluteSpeedConditionImpl <em>Absolute Speed Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.AbsoluteSpeedConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAbsoluteSpeedCondition()
     * @generated
     */
    int ABSOLUTE_SPEED_CONDITION = 41;

    /**
     * The feature id for the '<em><b>Time</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_SPEED_CONDITION__TIME = SPEED_CONDITION__TIME;

    /**
     * The feature id for the '<em><b>Kph</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_SPEED_CONDITION__KPH = SPEED_CONDITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Absolute Speed Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_SPEED_CONDITION_FEATURE_COUNT = SPEED_CONDITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Absolute Speed Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_SPEED_CONDITION_OPERATION_COUNT = SPEED_CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.RelativeSpeedConditionImpl <em>Relative Speed Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.RelativeSpeedConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRelativeSpeedCondition()
     * @generated
     */
    int RELATIVE_SPEED_CONDITION = 42;

    /**
     * The feature id for the '<em><b>Time</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_SPEED_CONDITION__TIME = SPEED_CONDITION__TIME;

    /**
     * The feature id for the '<em><b>Vehicle</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_SPEED_CONDITION__VEHICLE = SPEED_CONDITION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Kph</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_SPEED_CONDITION__KPH = SPEED_CONDITION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Relative Speed Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_SPEED_CONDITION_FEATURE_COUNT = SPEED_CONDITION_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Relative Speed Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_SPEED_CONDITION_OPERATION_COUNT = SPEED_CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.DistanceConditionImpl <em>Distance Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.DistanceConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getDistanceCondition()
     * @generated
     */
    int DISTANCE_CONDITION = 43;

    /**
     * The feature id for the '<em><b>Time</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DISTANCE_CONDITION__TIME = VEHICLE_DETAIL_CONDITION__TIME;

    /**
     * The number of structural features of the '<em>Distance Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DISTANCE_CONDITION_FEATURE_COUNT = VEHICLE_DETAIL_CONDITION_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Distance Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DISTANCE_CONDITION_OPERATION_COUNT = VEHICLE_DETAIL_CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.RelativeDistanceConditionImpl <em>Relative Distance Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.RelativeDistanceConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRelativeDistanceCondition()
     * @generated
     */
    int RELATIVE_DISTANCE_CONDITION = 44;

    /**
     * The feature id for the '<em><b>Time</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_DISTANCE_CONDITION__TIME = DISTANCE_CONDITION__TIME;

    /**
     * The feature id for the '<em><b>Vehicle</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_DISTANCE_CONDITION__VEHICLE = DISTANCE_CONDITION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Direction</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_DISTANCE_CONDITION__DIRECTION = DISTANCE_CONDITION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Meter</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_DISTANCE_CONDITION__METER = DISTANCE_CONDITION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Relative Distance Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_DISTANCE_CONDITION_FEATURE_COUNT = DISTANCE_CONDITION_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Relative Distance Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATIVE_DISTANCE_CONDITION_OPERATION_COUNT = DISTANCE_CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.AccelConditionImpl <em>Accel Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.AccelConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAccelCondition()
     * @generated
     */
    int ACCEL_CONDITION = 45;

    /**
     * The feature id for the '<em><b>Time</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACCEL_CONDITION__TIME = VEHICLE_DETAIL_CONDITION__TIME;

    /**
     * The number of structural features of the '<em>Accel Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACCEL_CONDITION_FEATURE_COUNT = VEHICLE_DETAIL_CONDITION_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Accel Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACCEL_CONDITION_OPERATION_COUNT = VEHICLE_DETAIL_CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.impl.AbsoluteAccelConditionImpl <em>Absolute Accel Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.impl.AbsoluteAccelConditionImpl
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAbsoluteAccelCondition()
     * @generated
     */
    int ABSOLUTE_ACCEL_CONDITION = 46;

    /**
     * The feature id for the '<em><b>Time</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_ACCEL_CONDITION__TIME = ACCEL_CONDITION__TIME;

    /**
     * The feature id for the '<em><b>Mpss</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_ACCEL_CONDITION__MPSS = ACCEL_CONDITION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Absolute Accel Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_ACCEL_CONDITION_FEATURE_COUNT = ACCEL_CONDITION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Absolute Accel Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSOLUTE_ACCEL_CONDITION_OPERATION_COUNT = ACCEL_CONDITION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.LaneDirection <em>Lane Direction</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.LaneDirection
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getLaneDirection()
     * @generated
     */
    int LANE_DIRECTION = 47;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.DistanceDirection <em>Distance Direction</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.DistanceDirection
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getDistanceDirection()
     * @generated
     */
    int DISTANCE_DIRECTION = 48;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scenario.ComparisonOperator <em>Comparison Operator</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.scenario.ComparisonOperator
     * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getComparisonOperator()
     * @generated
     */
    int COMPARISON_OPERATOR = 49;


    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.ScenarioRoot <em>Root</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.scenario.ScenarioRoot
     * @generated
     */
    EClass getScenarioRoot();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.ScenarioRoot#getRoadSetting <em>Road Setting</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Road Setting</em>'.
     * @see com.zipc.garden.model.scenario.ScenarioRoot#getRoadSetting()
     * @see #getScenarioRoot()
     * @generated
     */
    EReference getScenarioRoot_RoadSetting();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.ScenarioRoot#getActorSetting <em>Actor Setting</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Actor Setting</em>'.
     * @see com.zipc.garden.model.scenario.ScenarioRoot#getActorSetting()
     * @see #getScenarioRoot()
     * @generated
     */
    EReference getScenarioRoot_ActorSetting();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.ScenarioRoot#getPhaseSetting <em>Phase Setting</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Phase Setting</em>'.
     * @see com.zipc.garden.model.scenario.ScenarioRoot#getPhaseSetting()
     * @see #getScenarioRoot()
     * @generated
     */
    EReference getScenarioRoot_PhaseSetting();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.RoadSetting <em>Road Setting</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Road Setting</em>'.
     * @see com.zipc.garden.model.scenario.RoadSetting
     * @generated
     */
    EClass getRoadSetting();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.RoadSetting#getLaneCount <em>Lane Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Lane Count</em>'.
     * @see com.zipc.garden.model.scenario.RoadSetting#getLaneCount()
     * @see #getRoadSetting()
     * @generated
     */
    EAttribute getRoadSetting_LaneCount();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.ActorSetting <em>Actor Setting</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Actor Setting</em>'.
     * @see com.zipc.garden.model.scenario.ActorSetting
     * @generated
     */
    EClass getActorSetting();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.ActorSetting#getEgo <em>Ego</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Ego</em>'.
     * @see com.zipc.garden.model.scenario.ActorSetting#getEgo()
     * @see #getActorSetting()
     * @generated
     */
    EReference getActorSetting_Ego();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.scenario.ActorSetting#getOthers <em>Others</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Others</em>'.
     * @see com.zipc.garden.model.scenario.ActorSetting#getOthers()
     * @see #getActorSetting()
     * @generated
     */
    EReference getActorSetting_Others();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.Vehicle <em>Vehicle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Vehicle</em>'.
     * @see com.zipc.garden.model.scenario.Vehicle
     * @generated
     */
    EClass getVehicle();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.EgoVehicle <em>Ego Vehicle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Ego Vehicle</em>'.
     * @see com.zipc.garden.model.scenario.EgoVehicle
     * @generated
     */
    EClass getEgoVehicle();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.OtherVehicle <em>Other Vehicle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Other Vehicle</em>'.
     * @see com.zipc.garden.model.scenario.OtherVehicle
     * @generated
     */
    EClass getOtherVehicle();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.PhaseSetting <em>Phase Setting</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Phase Setting</em>'.
     * @see com.zipc.garden.model.scenario.PhaseSetting
     * @generated
     */
    EClass getPhaseSetting();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.PhaseSetting#getInitialPhase <em>Initial Phase</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Initial Phase</em>'.
     * @see com.zipc.garden.model.scenario.PhaseSetting#getInitialPhase()
     * @see #getPhaseSetting()
     * @generated
     */
    EReference getPhaseSetting_InitialPhase();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.PhaseCondition <em>Phase Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Phase Condition</em>'.
     * @see com.zipc.garden.model.scenario.PhaseCondition
     * @generated
     */
    EClass getPhaseCondition();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.scenario.PhaseCondition#getConditions <em>Conditions</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Conditions</em>'.
     * @see com.zipc.garden.model.scenario.PhaseCondition#getConditions()
     * @see #getPhaseCondition()
     * @generated
     */
    EReference getPhaseCondition_Conditions();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.PhaseAction <em>Phase Action</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Phase Action</em>'.
     * @see com.zipc.garden.model.scenario.PhaseAction
     * @generated
     */
    EClass getPhaseAction();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.scenario.PhaseAction#getActions <em>Actions</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Actions</em>'.
     * @see com.zipc.garden.model.scenario.PhaseAction#getActions()
     * @see #getPhaseAction()
     * @generated
     */
    EReference getPhaseAction_Actions();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.Phase <em>Phase</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Phase</em>'.
     * @see com.zipc.garden.model.scenario.Phase
     * @generated
     */
    EClass getPhase();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.Phase#getAction <em>Action</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Action</em>'.
     * @see com.zipc.garden.model.scenario.Phase#getAction()
     * @see #getPhase()
     * @generated
     */
    EReference getPhase_Action();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.Phase#getCondition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Condition</em>'.
     * @see com.zipc.garden.model.scenario.Phase#getCondition()
     * @see #getPhase()
     * @generated
     */
    EReference getPhase_Condition();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.scenario.Phase#getNextPhases <em>Next Phases</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Next Phases</em>'.
     * @see com.zipc.garden.model.scenario.Phase#getNextPhases()
     * @see #getPhase()
     * @generated
     */
    EReference getPhase_NextPhases();

    /**
     * Returns the meta object for the container reference '{@link com.zipc.garden.model.scenario.Phase#getPrevPhase <em>Prev Phase</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Prev Phase</em>'.
     * @see com.zipc.garden.model.scenario.Phase#getPrevPhase()
     * @see #getPhase()
     * @generated
     */
    EReference getPhase_PrevPhase();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.Phase#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.scenario.Phase#getName()
     * @see #getPhase()
     * @generated
     */
    EAttribute getPhase_Name();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.Action <em>Action</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Action</em>'.
     * @see com.zipc.garden.model.scenario.Action
     * @generated
     */
    EClass getAction();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.VehicleAction <em>Vehicle Action</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Vehicle Action</em>'.
     * @see com.zipc.garden.model.scenario.VehicleAction
     * @generated
     */
    EClass getVehicleAction();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.scenario.VehicleAction#getVehicle <em>Vehicle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Vehicle</em>'.
     * @see com.zipc.garden.model.scenario.VehicleAction#getVehicle()
     * @see #getVehicleAction()
     * @generated
     */
    EReference getVehicleAction_Vehicle();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.VehicleAction#isAppear <em>Appear</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Appear</em>'.
     * @see com.zipc.garden.model.scenario.VehicleAction#isAppear()
     * @see #getVehicleAction()
     * @generated
     */
    EAttribute getVehicleAction_Appear();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.scenario.VehicleAction#getActions <em>Actions</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Actions</em>'.
     * @see com.zipc.garden.model.scenario.VehicleAction#getActions()
     * @see #getVehicleAction()
     * @generated
     */
    EReference getVehicleAction_Actions();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.Actor <em>Actor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Actor</em>'.
     * @see com.zipc.garden.model.scenario.Actor
     * @generated
     */
    EClass getActor();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.Actor#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.scenario.Actor#getName()
     * @see #getActor()
     * @generated
     */
    EAttribute getActor_Name();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.VehicleDetailAction <em>Vehicle Detail Action</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Vehicle Detail Action</em>'.
     * @see com.zipc.garden.model.scenario.VehicleDetailAction
     * @generated
     */
    EClass getVehicleDetailAction();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.LaneAction <em>Lane Action</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Lane Action</em>'.
     * @see com.zipc.garden.model.scenario.LaneAction
     * @generated
     */
    EClass getLaneAction();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.LaneAction#getLane <em>Lane</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Lane</em>'.
     * @see com.zipc.garden.model.scenario.LaneAction#getLane()
     * @see #getLaneAction()
     * @generated
     */
    EReference getLaneAction_Lane();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.LaneSetting <em>Lane Setting</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Lane Setting</em>'.
     * @see com.zipc.garden.model.scenario.LaneSetting
     * @generated
     */
    EClass getLaneSetting();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.AbsoluteLane <em>Absolute Lane</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Absolute Lane</em>'.
     * @see com.zipc.garden.model.scenario.AbsoluteLane
     * @generated
     */
    EClass getAbsoluteLane();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.AbsoluteLane#getLaneId <em>Lane Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Lane Id</em>'.
     * @see com.zipc.garden.model.scenario.AbsoluteLane#getLaneId()
     * @see #getAbsoluteLane()
     * @generated
     */
    EAttribute getAbsoluteLane_LaneId();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.RelativeLane <em>Relative Lane</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Relative Lane</em>'.
     * @see com.zipc.garden.model.scenario.RelativeLane
     * @generated
     */
    EClass getRelativeLane();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.scenario.RelativeLane#getVehicle <em>Vehicle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Vehicle</em>'.
     * @see com.zipc.garden.model.scenario.RelativeLane#getVehicle()
     * @see #getRelativeLane()
     * @generated
     */
    EReference getRelativeLane_Vehicle();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.RelativeLane#getOffset <em>Offset</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Offset</em>'.
     * @see com.zipc.garden.model.scenario.RelativeLane#getOffset()
     * @see #getRelativeLane()
     * @generated
     */
    EAttribute getRelativeLane_Offset();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.RelativeLane#getDirection <em>Direction</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Direction</em>'.
     * @see com.zipc.garden.model.scenario.RelativeLane#getDirection()
     * @see #getRelativeLane()
     * @generated
     */
    EAttribute getRelativeLane_Direction();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.SpeedAction <em>Speed Action</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Speed Action</em>'.
     * @see com.zipc.garden.model.scenario.SpeedAction
     * @generated
     */
    EClass getSpeedAction();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.SpeedAction#getSpeed <em>Speed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Speed</em>'.
     * @see com.zipc.garden.model.scenario.SpeedAction#getSpeed()
     * @see #getSpeedAction()
     * @generated
     */
    EReference getSpeedAction_Speed();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.SpeedSetting <em>Speed Setting</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Speed Setting</em>'.
     * @see com.zipc.garden.model.scenario.SpeedSetting
     * @generated
     */
    EClass getSpeedSetting();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.AbsoluteSpeed <em>Absolute Speed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Absolute Speed</em>'.
     * @see com.zipc.garden.model.scenario.AbsoluteSpeed
     * @generated
     */
    EClass getAbsoluteSpeed();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.AbsoluteSpeed#getKph <em>Kph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Kph</em>'.
     * @see com.zipc.garden.model.scenario.AbsoluteSpeed#getKph()
     * @see #getAbsoluteSpeed()
     * @generated
     */
    EAttribute getAbsoluteSpeed_Kph();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.RelativeSpeed <em>Relative Speed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Relative Speed</em>'.
     * @see com.zipc.garden.model.scenario.RelativeSpeed
     * @generated
     */
    EClass getRelativeSpeed();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.scenario.RelativeSpeed#getVehicle <em>Vehicle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Vehicle</em>'.
     * @see com.zipc.garden.model.scenario.RelativeSpeed#getVehicle()
     * @see #getRelativeSpeed()
     * @generated
     */
    EReference getRelativeSpeed_Vehicle();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.RelativeSpeed#getKph <em>Kph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Kph</em>'.
     * @see com.zipc.garden.model.scenario.RelativeSpeed#getKph()
     * @see #getRelativeSpeed()
     * @generated
     */
    EAttribute getRelativeSpeed_Kph();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.DistanceAction <em>Distance Action</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Distance Action</em>'.
     * @see com.zipc.garden.model.scenario.DistanceAction
     * @generated
     */
    EClass getDistanceAction();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.DistanceAction#getDistance <em>Distance</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Distance</em>'.
     * @see com.zipc.garden.model.scenario.DistanceAction#getDistance()
     * @see #getDistanceAction()
     * @generated
     */
    EReference getDistanceAction_Distance();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.DistanceSetting <em>Distance Setting</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Distance Setting</em>'.
     * @see com.zipc.garden.model.scenario.DistanceSetting
     * @generated
     */
    EClass getDistanceSetting();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.RelativeDistance <em>Relative Distance</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Relative Distance</em>'.
     * @see com.zipc.garden.model.scenario.RelativeDistance
     * @generated
     */
    EClass getRelativeDistance();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.scenario.RelativeDistance#getVehicle <em>Vehicle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Vehicle</em>'.
     * @see com.zipc.garden.model.scenario.RelativeDistance#getVehicle()
     * @see #getRelativeDistance()
     * @generated
     */
    EReference getRelativeDistance_Vehicle();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.RelativeDistance#getDirection <em>Direction</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Direction</em>'.
     * @see com.zipc.garden.model.scenario.RelativeDistance#getDirection()
     * @see #getRelativeDistance()
     * @generated
     */
    EAttribute getRelativeDistance_Direction();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.RelativeDistance#getMeter <em>Meter</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Meter</em>'.
     * @see com.zipc.garden.model.scenario.RelativeDistance#getMeter()
     * @see #getRelativeDistance()
     * @generated
     */
    EAttribute getRelativeDistance_Meter();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.AccelAction <em>Accel Action</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Accel Action</em>'.
     * @see com.zipc.garden.model.scenario.AccelAction
     * @generated
     */
    EClass getAccelAction();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.AccelAction#getAccel <em>Accel</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Accel</em>'.
     * @see com.zipc.garden.model.scenario.AccelAction#getAccel()
     * @see #getAccelAction()
     * @generated
     */
    EReference getAccelAction_Accel();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.AccelerationSetting <em>Acceleration Setting</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Acceleration Setting</em>'.
     * @see com.zipc.garden.model.scenario.AccelerationSetting
     * @generated
     */
    EClass getAccelerationSetting();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.AbsoluteAcceleration <em>Absolute Acceleration</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Absolute Acceleration</em>'.
     * @see com.zipc.garden.model.scenario.AbsoluteAcceleration
     * @generated
     */
    EClass getAbsoluteAcceleration();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.AbsoluteAcceleration#getMpss <em>Mpss</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Mpss</em>'.
     * @see com.zipc.garden.model.scenario.AbsoluteAcceleration#getMpss()
     * @see #getAbsoluteAcceleration()
     * @generated
     */
    EAttribute getAbsoluteAcceleration_Mpss();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.Condition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Condition</em>'.
     * @see com.zipc.garden.model.scenario.Condition
     * @generated
     */
    EClass getCondition();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.Condition#isEnabled <em>Enabled</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Enabled</em>'.
     * @see com.zipc.garden.model.scenario.Condition#isEnabled()
     * @see #getCondition()
     * @generated
     */
    EAttribute getCondition_Enabled();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.VehicleCondition <em>Vehicle Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Vehicle Condition</em>'.
     * @see com.zipc.garden.model.scenario.VehicleCondition
     * @generated
     */
    EClass getVehicleCondition();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.scenario.VehicleCondition#getVehicle <em>Vehicle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Vehicle</em>'.
     * @see com.zipc.garden.model.scenario.VehicleCondition#getVehicle()
     * @see #getVehicleCondition()
     * @generated
     */
    EReference getVehicleCondition_Vehicle();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.scenario.VehicleCondition#getConditions <em>Conditions</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Conditions</em>'.
     * @see com.zipc.garden.model.scenario.VehicleCondition#getConditions()
     * @see #getVehicleCondition()
     * @generated
     */
    EReference getVehicleCondition_Conditions();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.VehicleDetailCondition <em>Vehicle Detail Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Vehicle Detail Condition</em>'.
     * @see com.zipc.garden.model.scenario.VehicleDetailCondition
     * @generated
     */
    EClass getVehicleDetailCondition();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.VehicleDetailCondition#getTime <em>Time</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Time</em>'.
     * @see com.zipc.garden.model.scenario.VehicleDetailCondition#getTime()
     * @see #getVehicleDetailCondition()
     * @generated
     */
    EReference getVehicleDetailCondition_Time();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.TimeCondition <em>Time Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Time Condition</em>'.
     * @see com.zipc.garden.model.scenario.TimeCondition
     * @generated
     */
    EClass getTimeCondition();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.TimeEvenOnce <em>Time Even Once</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Time Even Once</em>'.
     * @see com.zipc.garden.model.scenario.TimeEvenOnce
     * @generated
     */
    EClass getTimeEvenOnce();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.TimeDuration <em>Time Duration</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Time Duration</em>'.
     * @see com.zipc.garden.model.scenario.TimeDuration
     * @generated
     */
    EClass getTimeDuration();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.TimeDuration#getMsec <em>Msec</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Msec</em>'.
     * @see com.zipc.garden.model.scenario.TimeDuration#getMsec()
     * @see #getTimeDuration()
     * @generated
     */
    EAttribute getTimeDuration_Msec();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.ValueCondition <em>Value Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Condition</em>'.
     * @see com.zipc.garden.model.scenario.ValueCondition
     * @generated
     */
    EClass getValueCondition();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.SimpleValueCondition <em>Simple Value Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Simple Value Condition</em>'.
     * @see com.zipc.garden.model.scenario.SimpleValueCondition
     * @generated
     */
    EClass getSimpleValueCondition();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.SimpleValueCondition#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see com.zipc.garden.model.scenario.SimpleValueCondition#getValue()
     * @see #getSimpleValueCondition()
     * @generated
     */
    EAttribute getSimpleValueCondition_Value();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.SimpleValueCondition#getOperator <em>Operator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Operator</em>'.
     * @see com.zipc.garden.model.scenario.SimpleValueCondition#getOperator()
     * @see #getSimpleValueCondition()
     * @generated
     */
    EAttribute getSimpleValueCondition_Operator();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.RangeValueCondition <em>Range Value Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Range Value Condition</em>'.
     * @see com.zipc.garden.model.scenario.RangeValueCondition
     * @generated
     */
    EClass getRangeValueCondition();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.RangeValueCondition#getUpperValue <em>Upper Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Upper Value</em>'.
     * @see com.zipc.garden.model.scenario.RangeValueCondition#getUpperValue()
     * @see #getRangeValueCondition()
     * @generated
     */
    EAttribute getRangeValueCondition_UpperValue();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.RangeValueCondition#getLowerValue <em>Lower Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Lower Value</em>'.
     * @see com.zipc.garden.model.scenario.RangeValueCondition#getLowerValue()
     * @see #getRangeValueCondition()
     * @generated
     */
    EAttribute getRangeValueCondition_LowerValue();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.LaneCondition <em>Lane Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Lane Condition</em>'.
     * @see com.zipc.garden.model.scenario.LaneCondition
     * @generated
     */
    EClass getLaneCondition();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.AbsoluteLaneCondition <em>Absolute Lane Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Absolute Lane Condition</em>'.
     * @see com.zipc.garden.model.scenario.AbsoluteLaneCondition
     * @generated
     */
    EClass getAbsoluteLaneCondition();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.AbsoluteLaneCondition#getLaneId <em>Lane Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Lane Id</em>'.
     * @see com.zipc.garden.model.scenario.AbsoluteLaneCondition#getLaneId()
     * @see #getAbsoluteLaneCondition()
     * @generated
     */
    EAttribute getAbsoluteLaneCondition_LaneId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.AbsoluteLaneCondition#getOperator <em>Operator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Operator</em>'.
     * @see com.zipc.garden.model.scenario.AbsoluteLaneCondition#getOperator()
     * @see #getAbsoluteLaneCondition()
     * @generated
     */
    EAttribute getAbsoluteLaneCondition_Operator();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.RelativeLaneCondition <em>Relative Lane Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Relative Lane Condition</em>'.
     * @see com.zipc.garden.model.scenario.RelativeLaneCondition
     * @generated
     */
    EClass getRelativeLaneCondition();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.scenario.RelativeLaneCondition#getVehicle <em>Vehicle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Vehicle</em>'.
     * @see com.zipc.garden.model.scenario.RelativeLaneCondition#getVehicle()
     * @see #getRelativeLaneCondition()
     * @generated
     */
    EReference getRelativeLaneCondition_Vehicle();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.RelativeLaneCondition#getDirection <em>Direction</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Direction</em>'.
     * @see com.zipc.garden.model.scenario.RelativeLaneCondition#getDirection()
     * @see #getRelativeLaneCondition()
     * @generated
     */
    EAttribute getRelativeLaneCondition_Direction();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.RelativeLaneCondition#getOffset <em>Offset</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Offset</em>'.
     * @see com.zipc.garden.model.scenario.RelativeLaneCondition#getOffset()
     * @see #getRelativeLaneCondition()
     * @generated
     */
    EAttribute getRelativeLaneCondition_Offset();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.SpeedCondition <em>Speed Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Speed Condition</em>'.
     * @see com.zipc.garden.model.scenario.SpeedCondition
     * @generated
     */
    EClass getSpeedCondition();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.AbsoluteSpeedCondition <em>Absolute Speed Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Absolute Speed Condition</em>'.
     * @see com.zipc.garden.model.scenario.AbsoluteSpeedCondition
     * @generated
     */
    EClass getAbsoluteSpeedCondition();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.AbsoluteSpeedCondition#getKph <em>Kph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Kph</em>'.
     * @see com.zipc.garden.model.scenario.AbsoluteSpeedCondition#getKph()
     * @see #getAbsoluteSpeedCondition()
     * @generated
     */
    EReference getAbsoluteSpeedCondition_Kph();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.RelativeSpeedCondition <em>Relative Speed Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Relative Speed Condition</em>'.
     * @see com.zipc.garden.model.scenario.RelativeSpeedCondition
     * @generated
     */
    EClass getRelativeSpeedCondition();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.scenario.RelativeSpeedCondition#getVehicle <em>Vehicle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Vehicle</em>'.
     * @see com.zipc.garden.model.scenario.RelativeSpeedCondition#getVehicle()
     * @see #getRelativeSpeedCondition()
     * @generated
     */
    EReference getRelativeSpeedCondition_Vehicle();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.RelativeSpeedCondition#getKph <em>Kph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Kph</em>'.
     * @see com.zipc.garden.model.scenario.RelativeSpeedCondition#getKph()
     * @see #getRelativeSpeedCondition()
     * @generated
     */
    EReference getRelativeSpeedCondition_Kph();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.DistanceCondition <em>Distance Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Distance Condition</em>'.
     * @see com.zipc.garden.model.scenario.DistanceCondition
     * @generated
     */
    EClass getDistanceCondition();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.RelativeDistanceCondition <em>Relative Distance Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Relative Distance Condition</em>'.
     * @see com.zipc.garden.model.scenario.RelativeDistanceCondition
     * @generated
     */
    EClass getRelativeDistanceCondition();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.scenario.RelativeDistanceCondition#getVehicle <em>Vehicle</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Vehicle</em>'.
     * @see com.zipc.garden.model.scenario.RelativeDistanceCondition#getVehicle()
     * @see #getRelativeDistanceCondition()
     * @generated
     */
    EReference getRelativeDistanceCondition_Vehicle();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scenario.RelativeDistanceCondition#getDirection <em>Direction</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Direction</em>'.
     * @see com.zipc.garden.model.scenario.RelativeDistanceCondition#getDirection()
     * @see #getRelativeDistanceCondition()
     * @generated
     */
    EAttribute getRelativeDistanceCondition_Direction();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.RelativeDistanceCondition#getMeter <em>Meter</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Meter</em>'.
     * @see com.zipc.garden.model.scenario.RelativeDistanceCondition#getMeter()
     * @see #getRelativeDistanceCondition()
     * @generated
     */
    EReference getRelativeDistanceCondition_Meter();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.AccelCondition <em>Accel Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Accel Condition</em>'.
     * @see com.zipc.garden.model.scenario.AccelCondition
     * @generated
     */
    EClass getAccelCondition();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scenario.AbsoluteAccelCondition <em>Absolute Accel Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Absolute Accel Condition</em>'.
     * @see com.zipc.garden.model.scenario.AbsoluteAccelCondition
     * @generated
     */
    EClass getAbsoluteAccelCondition();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.scenario.AbsoluteAccelCondition#getMpss <em>Mpss</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Mpss</em>'.
     * @see com.zipc.garden.model.scenario.AbsoluteAccelCondition#getMpss()
     * @see #getAbsoluteAccelCondition()
     * @generated
     */
    EReference getAbsoluteAccelCondition_Mpss();

    /**
     * Returns the meta object for enum '{@link com.zipc.garden.model.scenario.LaneDirection <em>Lane Direction</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Lane Direction</em>'.
     * @see com.zipc.garden.model.scenario.LaneDirection
     * @generated
     */
    EEnum getLaneDirection();

    /**
     * Returns the meta object for enum '{@link com.zipc.garden.model.scenario.DistanceDirection <em>Distance Direction</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Distance Direction</em>'.
     * @see com.zipc.garden.model.scenario.DistanceDirection
     * @generated
     */
    EEnum getDistanceDirection();

    /**
     * Returns the meta object for enum '{@link com.zipc.garden.model.scenario.ComparisonOperator <em>Comparison Operator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Comparison Operator</em>'.
     * @see com.zipc.garden.model.scenario.ComparisonOperator
     * @generated
     */
    EEnum getComparisonOperator();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ScenarioFactory getScenarioFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each operation of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.ScenarioRootImpl <em>Root</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.ScenarioRootImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getScenarioRoot()
         * @generated
         */
        EClass SCENARIO_ROOT = eINSTANCE.getScenarioRoot();

        /**
         * The meta object literal for the '<em><b>Road Setting</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SCENARIO_ROOT__ROAD_SETTING = eINSTANCE.getScenarioRoot_RoadSetting();

        /**
         * The meta object literal for the '<em><b>Actor Setting</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SCENARIO_ROOT__ACTOR_SETTING = eINSTANCE.getScenarioRoot_ActorSetting();

        /**
         * The meta object literal for the '<em><b>Phase Setting</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SCENARIO_ROOT__PHASE_SETTING = eINSTANCE.getScenarioRoot_PhaseSetting();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.RoadSettingImpl <em>Road Setting</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.RoadSettingImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRoadSetting()
         * @generated
         */
        EClass ROAD_SETTING = eINSTANCE.getRoadSetting();

        /**
         * The meta object literal for the '<em><b>Lane Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ROAD_SETTING__LANE_COUNT = eINSTANCE.getRoadSetting_LaneCount();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.ActorSettingImpl <em>Actor Setting</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.ActorSettingImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getActorSetting()
         * @generated
         */
        EClass ACTOR_SETTING = eINSTANCE.getActorSetting();

        /**
         * The meta object literal for the '<em><b>Ego</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ACTOR_SETTING__EGO = eINSTANCE.getActorSetting_Ego();

        /**
         * The meta object literal for the '<em><b>Others</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ACTOR_SETTING__OTHERS = eINSTANCE.getActorSetting_Others();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.VehicleImpl <em>Vehicle</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.VehicleImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getVehicle()
         * @generated
         */
        EClass VEHICLE = eINSTANCE.getVehicle();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.EgoVehicleImpl <em>Ego Vehicle</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.EgoVehicleImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getEgoVehicle()
         * @generated
         */
        EClass EGO_VEHICLE = eINSTANCE.getEgoVehicle();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.OtherVehicleImpl <em>Other Vehicle</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.OtherVehicleImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getOtherVehicle()
         * @generated
         */
        EClass OTHER_VEHICLE = eINSTANCE.getOtherVehicle();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.PhaseSettingImpl <em>Phase Setting</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.PhaseSettingImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getPhaseSetting()
         * @generated
         */
        EClass PHASE_SETTING = eINSTANCE.getPhaseSetting();

        /**
         * The meta object literal for the '<em><b>Initial Phase</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PHASE_SETTING__INITIAL_PHASE = eINSTANCE.getPhaseSetting_InitialPhase();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.PhaseConditionImpl <em>Phase Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.PhaseConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getPhaseCondition()
         * @generated
         */
        EClass PHASE_CONDITION = eINSTANCE.getPhaseCondition();

        /**
         * The meta object literal for the '<em><b>Conditions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PHASE_CONDITION__CONDITIONS = eINSTANCE.getPhaseCondition_Conditions();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.PhaseActionImpl <em>Phase Action</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.PhaseActionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getPhaseAction()
         * @generated
         */
        EClass PHASE_ACTION = eINSTANCE.getPhaseAction();

        /**
         * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PHASE_ACTION__ACTIONS = eINSTANCE.getPhaseAction_Actions();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.PhaseImpl <em>Phase</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.PhaseImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getPhase()
         * @generated
         */
        EClass PHASE = eINSTANCE.getPhase();

        /**
         * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PHASE__ACTION = eINSTANCE.getPhase_Action();

        /**
         * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PHASE__CONDITION = eINSTANCE.getPhase_Condition();

        /**
         * The meta object literal for the '<em><b>Next Phases</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PHASE__NEXT_PHASES = eINSTANCE.getPhase_NextPhases();

        /**
         * The meta object literal for the '<em><b>Prev Phase</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PHASE__PREV_PHASE = eINSTANCE.getPhase_PrevPhase();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PHASE__NAME = eINSTANCE.getPhase_Name();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.ActionImpl <em>Action</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.ActionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAction()
         * @generated
         */
        EClass ACTION = eINSTANCE.getAction();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.VehicleActionImpl <em>Vehicle Action</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.VehicleActionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getVehicleAction()
         * @generated
         */
        EClass VEHICLE_ACTION = eINSTANCE.getVehicleAction();

        /**
         * The meta object literal for the '<em><b>Vehicle</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VEHICLE_ACTION__VEHICLE = eINSTANCE.getVehicleAction_Vehicle();

        /**
         * The meta object literal for the '<em><b>Appear</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute VEHICLE_ACTION__APPEAR = eINSTANCE.getVehicleAction_Appear();

        /**
         * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VEHICLE_ACTION__ACTIONS = eINSTANCE.getVehicleAction_Actions();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.ActorImpl <em>Actor</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.ActorImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getActor()
         * @generated
         */
        EClass ACTOR = eINSTANCE.getActor();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ACTOR__NAME = eINSTANCE.getActor_Name();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.VehicleDetailActionImpl <em>Vehicle Detail Action</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.VehicleDetailActionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getVehicleDetailAction()
         * @generated
         */
        EClass VEHICLE_DETAIL_ACTION = eINSTANCE.getVehicleDetailAction();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.LaneActionImpl <em>Lane Action</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.LaneActionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getLaneAction()
         * @generated
         */
        EClass LANE_ACTION = eINSTANCE.getLaneAction();

        /**
         * The meta object literal for the '<em><b>Lane</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LANE_ACTION__LANE = eINSTANCE.getLaneAction_Lane();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.LaneSettingImpl <em>Lane Setting</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.LaneSettingImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getLaneSetting()
         * @generated
         */
        EClass LANE_SETTING = eINSTANCE.getLaneSetting();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.AbsoluteLaneImpl <em>Absolute Lane</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.AbsoluteLaneImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAbsoluteLane()
         * @generated
         */
        EClass ABSOLUTE_LANE = eINSTANCE.getAbsoluteLane();

        /**
         * The meta object literal for the '<em><b>Lane Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSOLUTE_LANE__LANE_ID = eINSTANCE.getAbsoluteLane_LaneId();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.RelativeLaneImpl <em>Relative Lane</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.RelativeLaneImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRelativeLane()
         * @generated
         */
        EClass RELATIVE_LANE = eINSTANCE.getRelativeLane();

        /**
         * The meta object literal for the '<em><b>Vehicle</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RELATIVE_LANE__VEHICLE = eINSTANCE.getRelativeLane_Vehicle();

        /**
         * The meta object literal for the '<em><b>Offset</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RELATIVE_LANE__OFFSET = eINSTANCE.getRelativeLane_Offset();

        /**
         * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RELATIVE_LANE__DIRECTION = eINSTANCE.getRelativeLane_Direction();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.SpeedActionImpl <em>Speed Action</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.SpeedActionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getSpeedAction()
         * @generated
         */
        EClass SPEED_ACTION = eINSTANCE.getSpeedAction();

        /**
         * The meta object literal for the '<em><b>Speed</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SPEED_ACTION__SPEED = eINSTANCE.getSpeedAction_Speed();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.SpeedSettingImpl <em>Speed Setting</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.SpeedSettingImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getSpeedSetting()
         * @generated
         */
        EClass SPEED_SETTING = eINSTANCE.getSpeedSetting();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.AbsoluteSpeedImpl <em>Absolute Speed</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.AbsoluteSpeedImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAbsoluteSpeed()
         * @generated
         */
        EClass ABSOLUTE_SPEED = eINSTANCE.getAbsoluteSpeed();

        /**
         * The meta object literal for the '<em><b>Kph</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSOLUTE_SPEED__KPH = eINSTANCE.getAbsoluteSpeed_Kph();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.RelativeSpeedImpl <em>Relative Speed</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.RelativeSpeedImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRelativeSpeed()
         * @generated
         */
        EClass RELATIVE_SPEED = eINSTANCE.getRelativeSpeed();

        /**
         * The meta object literal for the '<em><b>Vehicle</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RELATIVE_SPEED__VEHICLE = eINSTANCE.getRelativeSpeed_Vehicle();

        /**
         * The meta object literal for the '<em><b>Kph</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RELATIVE_SPEED__KPH = eINSTANCE.getRelativeSpeed_Kph();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.DistanceActionImpl <em>Distance Action</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.DistanceActionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getDistanceAction()
         * @generated
         */
        EClass DISTANCE_ACTION = eINSTANCE.getDistanceAction();

        /**
         * The meta object literal for the '<em><b>Distance</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DISTANCE_ACTION__DISTANCE = eINSTANCE.getDistanceAction_Distance();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.DistanceSettingImpl <em>Distance Setting</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.DistanceSettingImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getDistanceSetting()
         * @generated
         */
        EClass DISTANCE_SETTING = eINSTANCE.getDistanceSetting();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.RelativeDistanceImpl <em>Relative Distance</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.RelativeDistanceImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRelativeDistance()
         * @generated
         */
        EClass RELATIVE_DISTANCE = eINSTANCE.getRelativeDistance();

        /**
         * The meta object literal for the '<em><b>Vehicle</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RELATIVE_DISTANCE__VEHICLE = eINSTANCE.getRelativeDistance_Vehicle();

        /**
         * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RELATIVE_DISTANCE__DIRECTION = eINSTANCE.getRelativeDistance_Direction();

        /**
         * The meta object literal for the '<em><b>Meter</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RELATIVE_DISTANCE__METER = eINSTANCE.getRelativeDistance_Meter();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.AccelActionImpl <em>Accel Action</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.AccelActionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAccelAction()
         * @generated
         */
        EClass ACCEL_ACTION = eINSTANCE.getAccelAction();

        /**
         * The meta object literal for the '<em><b>Accel</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ACCEL_ACTION__ACCEL = eINSTANCE.getAccelAction_Accel();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.AccelerationSettingImpl <em>Acceleration Setting</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.AccelerationSettingImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAccelerationSetting()
         * @generated
         */
        EClass ACCELERATION_SETTING = eINSTANCE.getAccelerationSetting();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.AbsoluteAccelerationImpl <em>Absolute Acceleration</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.AbsoluteAccelerationImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAbsoluteAcceleration()
         * @generated
         */
        EClass ABSOLUTE_ACCELERATION = eINSTANCE.getAbsoluteAcceleration();

        /**
         * The meta object literal for the '<em><b>Mpss</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSOLUTE_ACCELERATION__MPSS = eINSTANCE.getAbsoluteAcceleration_Mpss();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.ConditionImpl <em>Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.ConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getCondition()
         * @generated
         */
        EClass CONDITION = eINSTANCE.getCondition();

        /**
         * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CONDITION__ENABLED = eINSTANCE.getCondition_Enabled();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.VehicleConditionImpl <em>Vehicle Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.VehicleConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getVehicleCondition()
         * @generated
         */
        EClass VEHICLE_CONDITION = eINSTANCE.getVehicleCondition();

        /**
         * The meta object literal for the '<em><b>Vehicle</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VEHICLE_CONDITION__VEHICLE = eINSTANCE.getVehicleCondition_Vehicle();

        /**
         * The meta object literal for the '<em><b>Conditions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VEHICLE_CONDITION__CONDITIONS = eINSTANCE.getVehicleCondition_Conditions();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.VehicleDetailConditionImpl <em>Vehicle Detail Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.VehicleDetailConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getVehicleDetailCondition()
         * @generated
         */
        EClass VEHICLE_DETAIL_CONDITION = eINSTANCE.getVehicleDetailCondition();

        /**
         * The meta object literal for the '<em><b>Time</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VEHICLE_DETAIL_CONDITION__TIME = eINSTANCE.getVehicleDetailCondition_Time();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.TimeConditionImpl <em>Time Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.TimeConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getTimeCondition()
         * @generated
         */
        EClass TIME_CONDITION = eINSTANCE.getTimeCondition();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.TimeEvenOnceImpl <em>Time Even Once</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.TimeEvenOnceImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getTimeEvenOnce()
         * @generated
         */
        EClass TIME_EVEN_ONCE = eINSTANCE.getTimeEvenOnce();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.TimeDurationImpl <em>Time Duration</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.TimeDurationImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getTimeDuration()
         * @generated
         */
        EClass TIME_DURATION = eINSTANCE.getTimeDuration();

        /**
         * The meta object literal for the '<em><b>Msec</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TIME_DURATION__MSEC = eINSTANCE.getTimeDuration_Msec();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.ValueConditionImpl <em>Value Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.ValueConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getValueCondition()
         * @generated
         */
        EClass VALUE_CONDITION = eINSTANCE.getValueCondition();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.SimpleValueConditionImpl <em>Simple Value Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.SimpleValueConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getSimpleValueCondition()
         * @generated
         */
        EClass SIMPLE_VALUE_CONDITION = eINSTANCE.getSimpleValueCondition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMPLE_VALUE_CONDITION__VALUE = eINSTANCE.getSimpleValueCondition_Value();

        /**
         * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMPLE_VALUE_CONDITION__OPERATOR = eINSTANCE.getSimpleValueCondition_Operator();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.RangeValueConditionImpl <em>Range Value Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.RangeValueConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRangeValueCondition()
         * @generated
         */
        EClass RANGE_VALUE_CONDITION = eINSTANCE.getRangeValueCondition();

        /**
         * The meta object literal for the '<em><b>Upper Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RANGE_VALUE_CONDITION__UPPER_VALUE = eINSTANCE.getRangeValueCondition_UpperValue();

        /**
         * The meta object literal for the '<em><b>Lower Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RANGE_VALUE_CONDITION__LOWER_VALUE = eINSTANCE.getRangeValueCondition_LowerValue();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.LaneConditionImpl <em>Lane Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.LaneConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getLaneCondition()
         * @generated
         */
        EClass LANE_CONDITION = eINSTANCE.getLaneCondition();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.AbsoluteLaneConditionImpl <em>Absolute Lane Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.AbsoluteLaneConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAbsoluteLaneCondition()
         * @generated
         */
        EClass ABSOLUTE_LANE_CONDITION = eINSTANCE.getAbsoluteLaneCondition();

        /**
         * The meta object literal for the '<em><b>Lane Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSOLUTE_LANE_CONDITION__LANE_ID = eINSTANCE.getAbsoluteLaneCondition_LaneId();

        /**
         * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSOLUTE_LANE_CONDITION__OPERATOR = eINSTANCE.getAbsoluteLaneCondition_Operator();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.RelativeLaneConditionImpl <em>Relative Lane Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.RelativeLaneConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRelativeLaneCondition()
         * @generated
         */
        EClass RELATIVE_LANE_CONDITION = eINSTANCE.getRelativeLaneCondition();

        /**
         * The meta object literal for the '<em><b>Vehicle</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RELATIVE_LANE_CONDITION__VEHICLE = eINSTANCE.getRelativeLaneCondition_Vehicle();

        /**
         * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RELATIVE_LANE_CONDITION__DIRECTION = eINSTANCE.getRelativeLaneCondition_Direction();

        /**
         * The meta object literal for the '<em><b>Offset</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RELATIVE_LANE_CONDITION__OFFSET = eINSTANCE.getRelativeLaneCondition_Offset();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.SpeedConditionImpl <em>Speed Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.SpeedConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getSpeedCondition()
         * @generated
         */
        EClass SPEED_CONDITION = eINSTANCE.getSpeedCondition();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.AbsoluteSpeedConditionImpl <em>Absolute Speed Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.AbsoluteSpeedConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAbsoluteSpeedCondition()
         * @generated
         */
        EClass ABSOLUTE_SPEED_CONDITION = eINSTANCE.getAbsoluteSpeedCondition();

        /**
         * The meta object literal for the '<em><b>Kph</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ABSOLUTE_SPEED_CONDITION__KPH = eINSTANCE.getAbsoluteSpeedCondition_Kph();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.RelativeSpeedConditionImpl <em>Relative Speed Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.RelativeSpeedConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRelativeSpeedCondition()
         * @generated
         */
        EClass RELATIVE_SPEED_CONDITION = eINSTANCE.getRelativeSpeedCondition();

        /**
         * The meta object literal for the '<em><b>Vehicle</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RELATIVE_SPEED_CONDITION__VEHICLE = eINSTANCE.getRelativeSpeedCondition_Vehicle();

        /**
         * The meta object literal for the '<em><b>Kph</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RELATIVE_SPEED_CONDITION__KPH = eINSTANCE.getRelativeSpeedCondition_Kph();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.DistanceConditionImpl <em>Distance Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.DistanceConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getDistanceCondition()
         * @generated
         */
        EClass DISTANCE_CONDITION = eINSTANCE.getDistanceCondition();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.RelativeDistanceConditionImpl <em>Relative Distance Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.RelativeDistanceConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getRelativeDistanceCondition()
         * @generated
         */
        EClass RELATIVE_DISTANCE_CONDITION = eINSTANCE.getRelativeDistanceCondition();

        /**
         * The meta object literal for the '<em><b>Vehicle</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RELATIVE_DISTANCE_CONDITION__VEHICLE = eINSTANCE.getRelativeDistanceCondition_Vehicle();

        /**
         * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RELATIVE_DISTANCE_CONDITION__DIRECTION = eINSTANCE.getRelativeDistanceCondition_Direction();

        /**
         * The meta object literal for the '<em><b>Meter</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RELATIVE_DISTANCE_CONDITION__METER = eINSTANCE.getRelativeDistanceCondition_Meter();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.AccelConditionImpl <em>Accel Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.AccelConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAccelCondition()
         * @generated
         */
        EClass ACCEL_CONDITION = eINSTANCE.getAccelCondition();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.impl.AbsoluteAccelConditionImpl <em>Absolute Accel Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.impl.AbsoluteAccelConditionImpl
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getAbsoluteAccelCondition()
         * @generated
         */
        EClass ABSOLUTE_ACCEL_CONDITION = eINSTANCE.getAbsoluteAccelCondition();

        /**
         * The meta object literal for the '<em><b>Mpss</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ABSOLUTE_ACCEL_CONDITION__MPSS = eINSTANCE.getAbsoluteAccelCondition_Mpss();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.LaneDirection <em>Lane Direction</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.LaneDirection
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getLaneDirection()
         * @generated
         */
        EEnum LANE_DIRECTION = eINSTANCE.getLaneDirection();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.DistanceDirection <em>Distance Direction</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.DistanceDirection
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getDistanceDirection()
         * @generated
         */
        EEnum DISTANCE_DIRECTION = eINSTANCE.getDistanceDirection();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scenario.ComparisonOperator <em>Comparison Operator</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.zipc.garden.model.scenario.ComparisonOperator
         * @see com.zipc.garden.model.scenario.impl.ScenarioPackageImpl#getComparisonOperator()
         * @generated
         */
        EEnum COMPARISON_OPERATOR = eINSTANCE.getComparisonOperator();

    }

} //ScenarioPackage
