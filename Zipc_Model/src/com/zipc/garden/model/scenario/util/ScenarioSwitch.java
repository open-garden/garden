/**
 */
package com.zipc.garden.model.scenario.util;

import com.zipc.garden.model.core.AbstractRootElement;

import com.zipc.garden.model.scenario.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.zipc.garden.model.scenario.ScenarioPackage
 * @generated
 */
public class ScenarioSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ScenarioPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ScenarioSwitch() {
        if (modelPackage == null) {
            modelPackage = ScenarioPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case ScenarioPackage.SCENARIO_ROOT: {
                ScenarioRoot scenarioRoot = (ScenarioRoot)theEObject;
                T result = caseScenarioRoot(scenarioRoot);
                if (result == null) result = caseAbstractRootElement(scenarioRoot);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.ROAD_SETTING: {
                RoadSetting roadSetting = (RoadSetting)theEObject;
                T result = caseRoadSetting(roadSetting);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.ACTOR_SETTING: {
                ActorSetting actorSetting = (ActorSetting)theEObject;
                T result = caseActorSetting(actorSetting);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.VEHICLE: {
                Vehicle vehicle = (Vehicle)theEObject;
                T result = caseVehicle(vehicle);
                if (result == null) result = caseActor(vehicle);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.EGO_VEHICLE: {
                EgoVehicle egoVehicle = (EgoVehicle)theEObject;
                T result = caseEgoVehicle(egoVehicle);
                if (result == null) result = caseVehicle(egoVehicle);
                if (result == null) result = caseActor(egoVehicle);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.OTHER_VEHICLE: {
                OtherVehicle otherVehicle = (OtherVehicle)theEObject;
                T result = caseOtherVehicle(otherVehicle);
                if (result == null) result = caseVehicle(otherVehicle);
                if (result == null) result = caseActor(otherVehicle);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.PHASE_SETTING: {
                PhaseSetting phaseSetting = (PhaseSetting)theEObject;
                T result = casePhaseSetting(phaseSetting);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.PHASE_CONDITION: {
                PhaseCondition phaseCondition = (PhaseCondition)theEObject;
                T result = casePhaseCondition(phaseCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.PHASE_ACTION: {
                PhaseAction phaseAction = (PhaseAction)theEObject;
                T result = casePhaseAction(phaseAction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.PHASE: {
                Phase phase = (Phase)theEObject;
                T result = casePhase(phase);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.ACTION: {
                Action action = (Action)theEObject;
                T result = caseAction(action);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.VEHICLE_ACTION: {
                VehicleAction vehicleAction = (VehicleAction)theEObject;
                T result = caseVehicleAction(vehicleAction);
                if (result == null) result = caseAction(vehicleAction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.ACTOR: {
                Actor actor = (Actor)theEObject;
                T result = caseActor(actor);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.VEHICLE_DETAIL_ACTION: {
                VehicleDetailAction vehicleDetailAction = (VehicleDetailAction)theEObject;
                T result = caseVehicleDetailAction(vehicleDetailAction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.LANE_ACTION: {
                LaneAction laneAction = (LaneAction)theEObject;
                T result = caseLaneAction(laneAction);
                if (result == null) result = caseVehicleDetailAction(laneAction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.LANE_SETTING: {
                LaneSetting laneSetting = (LaneSetting)theEObject;
                T result = caseLaneSetting(laneSetting);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.ABSOLUTE_LANE: {
                AbsoluteLane absoluteLane = (AbsoluteLane)theEObject;
                T result = caseAbsoluteLane(absoluteLane);
                if (result == null) result = caseLaneSetting(absoluteLane);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.RELATIVE_LANE: {
                RelativeLane relativeLane = (RelativeLane)theEObject;
                T result = caseRelativeLane(relativeLane);
                if (result == null) result = caseLaneSetting(relativeLane);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.SPEED_ACTION: {
                SpeedAction speedAction = (SpeedAction)theEObject;
                T result = caseSpeedAction(speedAction);
                if (result == null) result = caseVehicleDetailAction(speedAction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.SPEED_SETTING: {
                SpeedSetting speedSetting = (SpeedSetting)theEObject;
                T result = caseSpeedSetting(speedSetting);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.ABSOLUTE_SPEED: {
                AbsoluteSpeed absoluteSpeed = (AbsoluteSpeed)theEObject;
                T result = caseAbsoluteSpeed(absoluteSpeed);
                if (result == null) result = caseSpeedSetting(absoluteSpeed);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.RELATIVE_SPEED: {
                RelativeSpeed relativeSpeed = (RelativeSpeed)theEObject;
                T result = caseRelativeSpeed(relativeSpeed);
                if (result == null) result = caseSpeedSetting(relativeSpeed);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.DISTANCE_ACTION: {
                DistanceAction distanceAction = (DistanceAction)theEObject;
                T result = caseDistanceAction(distanceAction);
                if (result == null) result = caseVehicleDetailAction(distanceAction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.DISTANCE_SETTING: {
                DistanceSetting distanceSetting = (DistanceSetting)theEObject;
                T result = caseDistanceSetting(distanceSetting);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.RELATIVE_DISTANCE: {
                RelativeDistance relativeDistance = (RelativeDistance)theEObject;
                T result = caseRelativeDistance(relativeDistance);
                if (result == null) result = caseDistanceSetting(relativeDistance);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.ACCEL_ACTION: {
                AccelAction accelAction = (AccelAction)theEObject;
                T result = caseAccelAction(accelAction);
                if (result == null) result = caseVehicleDetailAction(accelAction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.ACCELERATION_SETTING: {
                AccelerationSetting accelerationSetting = (AccelerationSetting)theEObject;
                T result = caseAccelerationSetting(accelerationSetting);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.ABSOLUTE_ACCELERATION: {
                AbsoluteAcceleration absoluteAcceleration = (AbsoluteAcceleration)theEObject;
                T result = caseAbsoluteAcceleration(absoluteAcceleration);
                if (result == null) result = caseAccelerationSetting(absoluteAcceleration);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.CONDITION: {
                Condition condition = (Condition)theEObject;
                T result = caseCondition(condition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.VEHICLE_CONDITION: {
                VehicleCondition vehicleCondition = (VehicleCondition)theEObject;
                T result = caseVehicleCondition(vehicleCondition);
                if (result == null) result = caseCondition(vehicleCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.VEHICLE_DETAIL_CONDITION: {
                VehicleDetailCondition vehicleDetailCondition = (VehicleDetailCondition)theEObject;
                T result = caseVehicleDetailCondition(vehicleDetailCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.TIME_CONDITION: {
                TimeCondition timeCondition = (TimeCondition)theEObject;
                T result = caseTimeCondition(timeCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.TIME_EVEN_ONCE: {
                TimeEvenOnce timeEvenOnce = (TimeEvenOnce)theEObject;
                T result = caseTimeEvenOnce(timeEvenOnce);
                if (result == null) result = caseTimeCondition(timeEvenOnce);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.TIME_DURATION: {
                TimeDuration timeDuration = (TimeDuration)theEObject;
                T result = caseTimeDuration(timeDuration);
                if (result == null) result = caseTimeCondition(timeDuration);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.VALUE_CONDITION: {
                ValueCondition valueCondition = (ValueCondition)theEObject;
                T result = caseValueCondition(valueCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.SIMPLE_VALUE_CONDITION: {
                SimpleValueCondition simpleValueCondition = (SimpleValueCondition)theEObject;
                T result = caseSimpleValueCondition(simpleValueCondition);
                if (result == null) result = caseValueCondition(simpleValueCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.RANGE_VALUE_CONDITION: {
                RangeValueCondition rangeValueCondition = (RangeValueCondition)theEObject;
                T result = caseRangeValueCondition(rangeValueCondition);
                if (result == null) result = caseValueCondition(rangeValueCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.LANE_CONDITION: {
                LaneCondition laneCondition = (LaneCondition)theEObject;
                T result = caseLaneCondition(laneCondition);
                if (result == null) result = caseVehicleDetailCondition(laneCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.ABSOLUTE_LANE_CONDITION: {
                AbsoluteLaneCondition absoluteLaneCondition = (AbsoluteLaneCondition)theEObject;
                T result = caseAbsoluteLaneCondition(absoluteLaneCondition);
                if (result == null) result = caseLaneCondition(absoluteLaneCondition);
                if (result == null) result = caseVehicleDetailCondition(absoluteLaneCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.RELATIVE_LANE_CONDITION: {
                RelativeLaneCondition relativeLaneCondition = (RelativeLaneCondition)theEObject;
                T result = caseRelativeLaneCondition(relativeLaneCondition);
                if (result == null) result = caseLaneCondition(relativeLaneCondition);
                if (result == null) result = caseVehicleDetailCondition(relativeLaneCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.SPEED_CONDITION: {
                SpeedCondition speedCondition = (SpeedCondition)theEObject;
                T result = caseSpeedCondition(speedCondition);
                if (result == null) result = caseVehicleDetailCondition(speedCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.ABSOLUTE_SPEED_CONDITION: {
                AbsoluteSpeedCondition absoluteSpeedCondition = (AbsoluteSpeedCondition)theEObject;
                T result = caseAbsoluteSpeedCondition(absoluteSpeedCondition);
                if (result == null) result = caseSpeedCondition(absoluteSpeedCondition);
                if (result == null) result = caseVehicleDetailCondition(absoluteSpeedCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.RELATIVE_SPEED_CONDITION: {
                RelativeSpeedCondition relativeSpeedCondition = (RelativeSpeedCondition)theEObject;
                T result = caseRelativeSpeedCondition(relativeSpeedCondition);
                if (result == null) result = caseSpeedCondition(relativeSpeedCondition);
                if (result == null) result = caseVehicleDetailCondition(relativeSpeedCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.DISTANCE_CONDITION: {
                DistanceCondition distanceCondition = (DistanceCondition)theEObject;
                T result = caseDistanceCondition(distanceCondition);
                if (result == null) result = caseVehicleDetailCondition(distanceCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.RELATIVE_DISTANCE_CONDITION: {
                RelativeDistanceCondition relativeDistanceCondition = (RelativeDistanceCondition)theEObject;
                T result = caseRelativeDistanceCondition(relativeDistanceCondition);
                if (result == null) result = caseDistanceCondition(relativeDistanceCondition);
                if (result == null) result = caseVehicleDetailCondition(relativeDistanceCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.ACCEL_CONDITION: {
                AccelCondition accelCondition = (AccelCondition)theEObject;
                T result = caseAccelCondition(accelCondition);
                if (result == null) result = caseVehicleDetailCondition(accelCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ScenarioPackage.ABSOLUTE_ACCEL_CONDITION: {
                AbsoluteAccelCondition absoluteAccelCondition = (AbsoluteAccelCondition)theEObject;
                T result = caseAbsoluteAccelCondition(absoluteAccelCondition);
                if (result == null) result = caseAccelCondition(absoluteAccelCondition);
                if (result == null) result = caseVehicleDetailCondition(absoluteAccelCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Root</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScenarioRoot(ScenarioRoot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Road Setting</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Road Setting</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRoadSetting(RoadSetting object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Actor Setting</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Actor Setting</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseActorSetting(ActorSetting object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Vehicle</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Vehicle</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVehicle(Vehicle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ego Vehicle</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ego Vehicle</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEgoVehicle(EgoVehicle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Other Vehicle</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Other Vehicle</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOtherVehicle(OtherVehicle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Phase Setting</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Phase Setting</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePhaseSetting(PhaseSetting object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Phase Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Phase Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePhaseCondition(PhaseCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Phase Action</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Phase Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePhaseAction(PhaseAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Phase</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Phase</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePhase(Phase object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Action</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAction(Action object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Vehicle Action</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Vehicle Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVehicleAction(VehicleAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Actor</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Actor</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseActor(Actor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Vehicle Detail Action</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Vehicle Detail Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVehicleDetailAction(VehicleDetailAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Lane Action</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Lane Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLaneAction(LaneAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Lane Setting</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Lane Setting</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLaneSetting(LaneSetting object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Absolute Lane</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Absolute Lane</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbsoluteLane(AbsoluteLane object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Relative Lane</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Relative Lane</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRelativeLane(RelativeLane object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Speed Action</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Speed Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSpeedAction(SpeedAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Speed Setting</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Speed Setting</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSpeedSetting(SpeedSetting object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Absolute Speed</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Absolute Speed</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbsoluteSpeed(AbsoluteSpeed object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Relative Speed</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Relative Speed</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRelativeSpeed(RelativeSpeed object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Distance Action</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Distance Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDistanceAction(DistanceAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Distance Setting</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Distance Setting</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDistanceSetting(DistanceSetting object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Relative Distance</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Relative Distance</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRelativeDistance(RelativeDistance object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Accel Action</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Accel Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAccelAction(AccelAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Acceleration Setting</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Acceleration Setting</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAccelerationSetting(AccelerationSetting object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Absolute Acceleration</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Absolute Acceleration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbsoluteAcceleration(AbsoluteAcceleration object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCondition(Condition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Vehicle Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Vehicle Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVehicleCondition(VehicleCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Vehicle Detail Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Vehicle Detail Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVehicleDetailCondition(VehicleDetailCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Time Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Time Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTimeCondition(TimeCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Time Even Once</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Time Even Once</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTimeEvenOnce(TimeEvenOnce object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Time Duration</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Time Duration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTimeDuration(TimeDuration object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Value Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseValueCondition(ValueCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Simple Value Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Simple Value Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSimpleValueCondition(SimpleValueCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Range Value Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Range Value Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRangeValueCondition(RangeValueCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Lane Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Lane Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLaneCondition(LaneCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Absolute Lane Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Absolute Lane Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbsoluteLaneCondition(AbsoluteLaneCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Relative Lane Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Relative Lane Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRelativeLaneCondition(RelativeLaneCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Speed Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Speed Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSpeedCondition(SpeedCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Absolute Speed Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Absolute Speed Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbsoluteSpeedCondition(AbsoluteSpeedCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Relative Speed Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Relative Speed Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRelativeSpeedCondition(RelativeSpeedCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Distance Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Distance Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDistanceCondition(DistanceCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Relative Distance Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Relative Distance Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRelativeDistanceCondition(RelativeDistanceCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Accel Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Accel Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAccelCondition(AccelCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Absolute Accel Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Absolute Accel Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbsoluteAccelCondition(AbsoluteAccelCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Root Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Root Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractRootElement(AbstractRootElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} //ScenarioSwitch
