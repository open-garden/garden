/**
 */
package com.zipc.garden.model.scenario;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.zipc.garden.model.scenario.ScenarioPackage
 * @generated
 */
public interface ScenarioFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ScenarioFactory eINSTANCE = com.zipc.garden.model.scenario.impl.ScenarioFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Root</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    ScenarioRoot createScenarioRoot();

    /**
     * Returns a new object of class '<em>Road Setting</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Road Setting</em>'.
     * @generated
     */
    RoadSetting createRoadSetting();

    /**
     * Returns a new object of class '<em>Actor Setting</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Actor Setting</em>'.
     * @generated
     */
    ActorSetting createActorSetting();

    /**
     * Returns a new object of class '<em>Ego Vehicle</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Ego Vehicle</em>'.
     * @generated
     */
    EgoVehicle createEgoVehicle();

    /**
     * Returns a new object of class '<em>Other Vehicle</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Other Vehicle</em>'.
     * @generated
     */
    OtherVehicle createOtherVehicle();

    /**
     * Returns a new object of class '<em>Phase Setting</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Phase Setting</em>'.
     * @generated
     */
    PhaseSetting createPhaseSetting();

    /**
     * Returns a new object of class '<em>Phase Condition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Phase Condition</em>'.
     * @generated
     */
    PhaseCondition createPhaseCondition();

    /**
     * Returns a new object of class '<em>Phase Action</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Phase Action</em>'.
     * @generated
     */
    PhaseAction createPhaseAction();

    /**
     * Returns a new object of class '<em>Phase</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Phase</em>'.
     * @generated
     */
    Phase createPhase();

    /**
     * Returns a new object of class '<em>Vehicle Action</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Vehicle Action</em>'.
     * @generated
     */
    VehicleAction createVehicleAction();

    /**
     * Returns a new object of class '<em>Lane Action</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Lane Action</em>'.
     * @generated
     */
    LaneAction createLaneAction();

    /**
     * Returns a new object of class '<em>Absolute Lane</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Absolute Lane</em>'.
     * @generated
     */
    AbsoluteLane createAbsoluteLane();

    /**
     * Returns a new object of class '<em>Relative Lane</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Relative Lane</em>'.
     * @generated
     */
    RelativeLane createRelativeLane();

    /**
     * Returns a new object of class '<em>Speed Action</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Speed Action</em>'.
     * @generated
     */
    SpeedAction createSpeedAction();

    /**
     * Returns a new object of class '<em>Absolute Speed</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Absolute Speed</em>'.
     * @generated
     */
    AbsoluteSpeed createAbsoluteSpeed();

    /**
     * Returns a new object of class '<em>Relative Speed</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Relative Speed</em>'.
     * @generated
     */
    RelativeSpeed createRelativeSpeed();

    /**
     * Returns a new object of class '<em>Distance Action</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Distance Action</em>'.
     * @generated
     */
    DistanceAction createDistanceAction();

    /**
     * Returns a new object of class '<em>Relative Distance</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Relative Distance</em>'.
     * @generated
     */
    RelativeDistance createRelativeDistance();

    /**
     * Returns a new object of class '<em>Accel Action</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Accel Action</em>'.
     * @generated
     */
    AccelAction createAccelAction();

    /**
     * Returns a new object of class '<em>Absolute Acceleration</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Absolute Acceleration</em>'.
     * @generated
     */
    AbsoluteAcceleration createAbsoluteAcceleration();

    /**
     * Returns a new object of class '<em>Vehicle Condition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Vehicle Condition</em>'.
     * @generated
     */
    VehicleCondition createVehicleCondition();

    /**
     * Returns a new object of class '<em>Time Even Once</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Time Even Once</em>'.
     * @generated
     */
    TimeEvenOnce createTimeEvenOnce();

    /**
     * Returns a new object of class '<em>Time Duration</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Time Duration</em>'.
     * @generated
     */
    TimeDuration createTimeDuration();

    /**
     * Returns a new object of class '<em>Simple Value Condition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Simple Value Condition</em>'.
     * @generated
     */
    SimpleValueCondition createSimpleValueCondition();

    /**
     * Returns a new object of class '<em>Range Value Condition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Range Value Condition</em>'.
     * @generated
     */
    RangeValueCondition createRangeValueCondition();

    /**
     * Returns a new object of class '<em>Absolute Lane Condition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Absolute Lane Condition</em>'.
     * @generated
     */
    AbsoluteLaneCondition createAbsoluteLaneCondition();

    /**
     * Returns a new object of class '<em>Relative Lane Condition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Relative Lane Condition</em>'.
     * @generated
     */
    RelativeLaneCondition createRelativeLaneCondition();

    /**
     * Returns a new object of class '<em>Absolute Speed Condition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Absolute Speed Condition</em>'.
     * @generated
     */
    AbsoluteSpeedCondition createAbsoluteSpeedCondition();

    /**
     * Returns a new object of class '<em>Relative Speed Condition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Relative Speed Condition</em>'.
     * @generated
     */
    RelativeSpeedCondition createRelativeSpeedCondition();

    /**
     * Returns a new object of class '<em>Relative Distance Condition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Relative Distance Condition</em>'.
     * @generated
     */
    RelativeDistanceCondition createRelativeDistanceCondition();

    /**
     * Returns a new object of class '<em>Absolute Accel Condition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Absolute Accel Condition</em>'.
     * @generated
     */
    AbsoluteAccelCondition createAbsoluteAccelCondition();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    ScenarioPackage getScenarioPackage();

} //ScenarioFactory
