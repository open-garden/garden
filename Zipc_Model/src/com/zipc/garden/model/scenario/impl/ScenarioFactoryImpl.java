/**
 */
package com.zipc.garden.model.scenario.impl;

import com.zipc.garden.model.scenario.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScenarioFactoryImpl extends EFactoryImpl implements ScenarioFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ScenarioFactory init() {
        try {
            ScenarioFactory theScenarioFactory = (ScenarioFactory)EPackage.Registry.INSTANCE.getEFactory(ScenarioPackage.eNS_URI);
            if (theScenarioFactory != null) {
                return theScenarioFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ScenarioFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ScenarioFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case ScenarioPackage.SCENARIO_ROOT: return createScenarioRoot();
            case ScenarioPackage.ROAD_SETTING: return createRoadSetting();
            case ScenarioPackage.ACTOR_SETTING: return createActorSetting();
            case ScenarioPackage.EGO_VEHICLE: return createEgoVehicle();
            case ScenarioPackage.OTHER_VEHICLE: return createOtherVehicle();
            case ScenarioPackage.PHASE_SETTING: return createPhaseSetting();
            case ScenarioPackage.PHASE_CONDITION: return createPhaseCondition();
            case ScenarioPackage.PHASE_ACTION: return createPhaseAction();
            case ScenarioPackage.PHASE: return createPhase();
            case ScenarioPackage.VEHICLE_ACTION: return createVehicleAction();
            case ScenarioPackage.LANE_ACTION: return createLaneAction();
            case ScenarioPackage.ABSOLUTE_LANE: return createAbsoluteLane();
            case ScenarioPackage.RELATIVE_LANE: return createRelativeLane();
            case ScenarioPackage.SPEED_ACTION: return createSpeedAction();
            case ScenarioPackage.ABSOLUTE_SPEED: return createAbsoluteSpeed();
            case ScenarioPackage.RELATIVE_SPEED: return createRelativeSpeed();
            case ScenarioPackage.DISTANCE_ACTION: return createDistanceAction();
            case ScenarioPackage.RELATIVE_DISTANCE: return createRelativeDistance();
            case ScenarioPackage.ACCEL_ACTION: return createAccelAction();
            case ScenarioPackage.ABSOLUTE_ACCELERATION: return createAbsoluteAcceleration();
            case ScenarioPackage.VEHICLE_CONDITION: return createVehicleCondition();
            case ScenarioPackage.TIME_EVEN_ONCE: return createTimeEvenOnce();
            case ScenarioPackage.TIME_DURATION: return createTimeDuration();
            case ScenarioPackage.SIMPLE_VALUE_CONDITION: return createSimpleValueCondition();
            case ScenarioPackage.RANGE_VALUE_CONDITION: return createRangeValueCondition();
            case ScenarioPackage.ABSOLUTE_LANE_CONDITION: return createAbsoluteLaneCondition();
            case ScenarioPackage.RELATIVE_LANE_CONDITION: return createRelativeLaneCondition();
            case ScenarioPackage.ABSOLUTE_SPEED_CONDITION: return createAbsoluteSpeedCondition();
            case ScenarioPackage.RELATIVE_SPEED_CONDITION: return createRelativeSpeedCondition();
            case ScenarioPackage.RELATIVE_DISTANCE_CONDITION: return createRelativeDistanceCondition();
            case ScenarioPackage.ABSOLUTE_ACCEL_CONDITION: return createAbsoluteAccelCondition();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case ScenarioPackage.LANE_DIRECTION:
                return createLaneDirectionFromString(eDataType, initialValue);
            case ScenarioPackage.DISTANCE_DIRECTION:
                return createDistanceDirectionFromString(eDataType, initialValue);
            case ScenarioPackage.COMPARISON_OPERATOR:
                return createComparisonOperatorFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case ScenarioPackage.LANE_DIRECTION:
                return convertLaneDirectionToString(eDataType, instanceValue);
            case ScenarioPackage.DISTANCE_DIRECTION:
                return convertDistanceDirectionToString(eDataType, instanceValue);
            case ScenarioPackage.COMPARISON_OPERATOR:
                return convertComparisonOperatorToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ScenarioRoot createScenarioRoot() {
        ScenarioRootImpl scenarioRoot = new ScenarioRootImpl();
        return scenarioRoot;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public RoadSetting createRoadSetting() {
        RoadSettingImpl roadSetting = new RoadSettingImpl();
        return roadSetting;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ActorSetting createActorSetting() {
        ActorSettingImpl actorSetting = new ActorSettingImpl();
        return actorSetting;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EgoVehicle createEgoVehicle() {
        EgoVehicleImpl egoVehicle = new EgoVehicleImpl();
        return egoVehicle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public OtherVehicle createOtherVehicle() {
        OtherVehicleImpl otherVehicle = new OtherVehicleImpl();
        return otherVehicle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public PhaseSetting createPhaseSetting() {
        PhaseSettingImpl phaseSetting = new PhaseSettingImpl();
        return phaseSetting;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public PhaseCondition createPhaseCondition() {
        PhaseConditionImpl phaseCondition = new PhaseConditionImpl();
        return phaseCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public PhaseAction createPhaseAction() {
        PhaseActionImpl phaseAction = new PhaseActionImpl();
        return phaseAction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Phase createPhase() {
        PhaseImpl phase = new PhaseImpl();
        return phase;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public VehicleAction createVehicleAction() {
        VehicleActionImpl vehicleAction = new VehicleActionImpl();
        return vehicleAction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public LaneAction createLaneAction() {
        LaneActionImpl laneAction = new LaneActionImpl();
        return laneAction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AbsoluteLane createAbsoluteLane() {
        AbsoluteLaneImpl absoluteLane = new AbsoluteLaneImpl();
        return absoluteLane;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public RelativeLane createRelativeLane() {
        RelativeLaneImpl relativeLane = new RelativeLaneImpl();
        return relativeLane;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SpeedAction createSpeedAction() {
        SpeedActionImpl speedAction = new SpeedActionImpl();
        return speedAction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AbsoluteSpeed createAbsoluteSpeed() {
        AbsoluteSpeedImpl absoluteSpeed = new AbsoluteSpeedImpl();
        return absoluteSpeed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public RelativeSpeed createRelativeSpeed() {
        RelativeSpeedImpl relativeSpeed = new RelativeSpeedImpl();
        return relativeSpeed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public DistanceAction createDistanceAction() {
        DistanceActionImpl distanceAction = new DistanceActionImpl();
        return distanceAction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public RelativeDistance createRelativeDistance() {
        RelativeDistanceImpl relativeDistance = new RelativeDistanceImpl();
        return relativeDistance;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AccelAction createAccelAction() {
        AccelActionImpl accelAction = new AccelActionImpl();
        return accelAction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AbsoluteAcceleration createAbsoluteAcceleration() {
        AbsoluteAccelerationImpl absoluteAcceleration = new AbsoluteAccelerationImpl();
        return absoluteAcceleration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public VehicleCondition createVehicleCondition() {
        VehicleConditionImpl vehicleCondition = new VehicleConditionImpl();
        return vehicleCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TimeEvenOnce createTimeEvenOnce() {
        TimeEvenOnceImpl timeEvenOnce = new TimeEvenOnceImpl();
        return timeEvenOnce;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TimeDuration createTimeDuration() {
        TimeDurationImpl timeDuration = new TimeDurationImpl();
        return timeDuration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SimpleValueCondition createSimpleValueCondition() {
        SimpleValueConditionImpl simpleValueCondition = new SimpleValueConditionImpl();
        return simpleValueCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public RangeValueCondition createRangeValueCondition() {
        RangeValueConditionImpl rangeValueCondition = new RangeValueConditionImpl();
        return rangeValueCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AbsoluteLaneCondition createAbsoluteLaneCondition() {
        AbsoluteLaneConditionImpl absoluteLaneCondition = new AbsoluteLaneConditionImpl();
        return absoluteLaneCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public RelativeLaneCondition createRelativeLaneCondition() {
        RelativeLaneConditionImpl relativeLaneCondition = new RelativeLaneConditionImpl();
        return relativeLaneCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AbsoluteSpeedCondition createAbsoluteSpeedCondition() {
        AbsoluteSpeedConditionImpl absoluteSpeedCondition = new AbsoluteSpeedConditionImpl();
        return absoluteSpeedCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public RelativeSpeedCondition createRelativeSpeedCondition() {
        RelativeSpeedConditionImpl relativeSpeedCondition = new RelativeSpeedConditionImpl();
        return relativeSpeedCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public RelativeDistanceCondition createRelativeDistanceCondition() {
        RelativeDistanceConditionImpl relativeDistanceCondition = new RelativeDistanceConditionImpl();
        return relativeDistanceCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AbsoluteAccelCondition createAbsoluteAccelCondition() {
        AbsoluteAccelConditionImpl absoluteAccelCondition = new AbsoluteAccelConditionImpl();
        return absoluteAccelCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LaneDirection createLaneDirectionFromString(EDataType eDataType, String initialValue) {
        LaneDirection result = LaneDirection.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertLaneDirectionToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DistanceDirection createDistanceDirectionFromString(EDataType eDataType, String initialValue) {
        DistanceDirection result = DistanceDirection.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertDistanceDirectionToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComparisonOperator createComparisonOperatorFromString(EDataType eDataType, String initialValue) {
        ComparisonOperator result = ComparisonOperator.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertComparisonOperatorToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ScenarioPackage getScenarioPackage() {
        return (ScenarioPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ScenarioPackage getPackage() {
        return ScenarioPackage.eINSTANCE;
    }

} //ScenarioFactoryImpl
