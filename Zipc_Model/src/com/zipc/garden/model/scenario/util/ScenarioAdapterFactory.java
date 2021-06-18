/**
 */
package com.zipc.garden.model.scenario.util;

import com.zipc.garden.model.core.AbstractRootElement;

import com.zipc.garden.model.scenario.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.zipc.garden.model.scenario.ScenarioPackage
 * @generated
 */
public class ScenarioAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ScenarioPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ScenarioAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = ScenarioPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ScenarioSwitch<Adapter> modelSwitch =
        new ScenarioSwitch<Adapter>() {
            @Override
            public Adapter caseScenarioRoot(ScenarioRoot object) {
                return createScenarioRootAdapter();
            }
            @Override
            public Adapter caseRoadSetting(RoadSetting object) {
                return createRoadSettingAdapter();
            }
            @Override
            public Adapter caseActorSetting(ActorSetting object) {
                return createActorSettingAdapter();
            }
            @Override
            public Adapter caseVehicle(Vehicle object) {
                return createVehicleAdapter();
            }
            @Override
            public Adapter caseEgoVehicle(EgoVehicle object) {
                return createEgoVehicleAdapter();
            }
            @Override
            public Adapter caseOtherVehicle(OtherVehicle object) {
                return createOtherVehicleAdapter();
            }
            @Override
            public Adapter casePhaseSetting(PhaseSetting object) {
                return createPhaseSettingAdapter();
            }
            @Override
            public Adapter casePhaseCondition(PhaseCondition object) {
                return createPhaseConditionAdapter();
            }
            @Override
            public Adapter casePhaseAction(PhaseAction object) {
                return createPhaseActionAdapter();
            }
            @Override
            public Adapter casePhase(Phase object) {
                return createPhaseAdapter();
            }
            @Override
            public Adapter caseAction(Action object) {
                return createActionAdapter();
            }
            @Override
            public Adapter caseVehicleAction(VehicleAction object) {
                return createVehicleActionAdapter();
            }
            @Override
            public Adapter caseActor(Actor object) {
                return createActorAdapter();
            }
            @Override
            public Adapter caseVehicleDetailAction(VehicleDetailAction object) {
                return createVehicleDetailActionAdapter();
            }
            @Override
            public Adapter caseLaneAction(LaneAction object) {
                return createLaneActionAdapter();
            }
            @Override
            public Adapter caseLaneSetting(LaneSetting object) {
                return createLaneSettingAdapter();
            }
            @Override
            public Adapter caseAbsoluteLane(AbsoluteLane object) {
                return createAbsoluteLaneAdapter();
            }
            @Override
            public Adapter caseRelativeLane(RelativeLane object) {
                return createRelativeLaneAdapter();
            }
            @Override
            public Adapter caseSpeedAction(SpeedAction object) {
                return createSpeedActionAdapter();
            }
            @Override
            public Adapter caseSpeedSetting(SpeedSetting object) {
                return createSpeedSettingAdapter();
            }
            @Override
            public Adapter caseAbsoluteSpeed(AbsoluteSpeed object) {
                return createAbsoluteSpeedAdapter();
            }
            @Override
            public Adapter caseRelativeSpeed(RelativeSpeed object) {
                return createRelativeSpeedAdapter();
            }
            @Override
            public Adapter caseDistanceAction(DistanceAction object) {
                return createDistanceActionAdapter();
            }
            @Override
            public Adapter caseDistanceSetting(DistanceSetting object) {
                return createDistanceSettingAdapter();
            }
            @Override
            public Adapter caseRelativeDistance(RelativeDistance object) {
                return createRelativeDistanceAdapter();
            }
            @Override
            public Adapter caseAccelAction(AccelAction object) {
                return createAccelActionAdapter();
            }
            @Override
            public Adapter caseAccelerationSetting(AccelerationSetting object) {
                return createAccelerationSettingAdapter();
            }
            @Override
            public Adapter caseAbsoluteAcceleration(AbsoluteAcceleration object) {
                return createAbsoluteAccelerationAdapter();
            }
            @Override
            public Adapter caseCondition(Condition object) {
                return createConditionAdapter();
            }
            @Override
            public Adapter caseVehicleCondition(VehicleCondition object) {
                return createVehicleConditionAdapter();
            }
            @Override
            public Adapter caseVehicleDetailCondition(VehicleDetailCondition object) {
                return createVehicleDetailConditionAdapter();
            }
            @Override
            public Adapter caseTimeCondition(TimeCondition object) {
                return createTimeConditionAdapter();
            }
            @Override
            public Adapter caseTimeEvenOnce(TimeEvenOnce object) {
                return createTimeEvenOnceAdapter();
            }
            @Override
            public Adapter caseTimeDuration(TimeDuration object) {
                return createTimeDurationAdapter();
            }
            @Override
            public Adapter caseValueCondition(ValueCondition object) {
                return createValueConditionAdapter();
            }
            @Override
            public Adapter caseSimpleValueCondition(SimpleValueCondition object) {
                return createSimpleValueConditionAdapter();
            }
            @Override
            public Adapter caseRangeValueCondition(RangeValueCondition object) {
                return createRangeValueConditionAdapter();
            }
            @Override
            public Adapter caseLaneCondition(LaneCondition object) {
                return createLaneConditionAdapter();
            }
            @Override
            public Adapter caseAbsoluteLaneCondition(AbsoluteLaneCondition object) {
                return createAbsoluteLaneConditionAdapter();
            }
            @Override
            public Adapter caseRelativeLaneCondition(RelativeLaneCondition object) {
                return createRelativeLaneConditionAdapter();
            }
            @Override
            public Adapter caseSpeedCondition(SpeedCondition object) {
                return createSpeedConditionAdapter();
            }
            @Override
            public Adapter caseAbsoluteSpeedCondition(AbsoluteSpeedCondition object) {
                return createAbsoluteSpeedConditionAdapter();
            }
            @Override
            public Adapter caseRelativeSpeedCondition(RelativeSpeedCondition object) {
                return createRelativeSpeedConditionAdapter();
            }
            @Override
            public Adapter caseDistanceCondition(DistanceCondition object) {
                return createDistanceConditionAdapter();
            }
            @Override
            public Adapter caseRelativeDistanceCondition(RelativeDistanceCondition object) {
                return createRelativeDistanceConditionAdapter();
            }
            @Override
            public Adapter caseAccelCondition(AccelCondition object) {
                return createAccelConditionAdapter();
            }
            @Override
            public Adapter caseAbsoluteAccelCondition(AbsoluteAccelCondition object) {
                return createAbsoluteAccelConditionAdapter();
            }
            @Override
            public Adapter caseAbstractRootElement(AbstractRootElement object) {
                return createAbstractRootElementAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.ScenarioRoot <em>Root</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.ScenarioRoot
     * @generated
     */
    public Adapter createScenarioRootAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.RoadSetting <em>Road Setting</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.RoadSetting
     * @generated
     */
    public Adapter createRoadSettingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.ActorSetting <em>Actor Setting</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.ActorSetting
     * @generated
     */
    public Adapter createActorSettingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.Vehicle <em>Vehicle</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.Vehicle
     * @generated
     */
    public Adapter createVehicleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.EgoVehicle <em>Ego Vehicle</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.EgoVehicle
     * @generated
     */
    public Adapter createEgoVehicleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.OtherVehicle <em>Other Vehicle</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.OtherVehicle
     * @generated
     */
    public Adapter createOtherVehicleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.PhaseSetting <em>Phase Setting</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.PhaseSetting
     * @generated
     */
    public Adapter createPhaseSettingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.PhaseCondition <em>Phase Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.PhaseCondition
     * @generated
     */
    public Adapter createPhaseConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.PhaseAction <em>Phase Action</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.PhaseAction
     * @generated
     */
    public Adapter createPhaseActionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.Phase <em>Phase</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.Phase
     * @generated
     */
    public Adapter createPhaseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.Action <em>Action</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.Action
     * @generated
     */
    public Adapter createActionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.VehicleAction <em>Vehicle Action</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.VehicleAction
     * @generated
     */
    public Adapter createVehicleActionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.Actor <em>Actor</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.Actor
     * @generated
     */
    public Adapter createActorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.VehicleDetailAction <em>Vehicle Detail Action</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.VehicleDetailAction
     * @generated
     */
    public Adapter createVehicleDetailActionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.LaneAction <em>Lane Action</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.LaneAction
     * @generated
     */
    public Adapter createLaneActionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.LaneSetting <em>Lane Setting</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.LaneSetting
     * @generated
     */
    public Adapter createLaneSettingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.AbsoluteLane <em>Absolute Lane</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.AbsoluteLane
     * @generated
     */
    public Adapter createAbsoluteLaneAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.RelativeLane <em>Relative Lane</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.RelativeLane
     * @generated
     */
    public Adapter createRelativeLaneAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.SpeedAction <em>Speed Action</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.SpeedAction
     * @generated
     */
    public Adapter createSpeedActionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.SpeedSetting <em>Speed Setting</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.SpeedSetting
     * @generated
     */
    public Adapter createSpeedSettingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.AbsoluteSpeed <em>Absolute Speed</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.AbsoluteSpeed
     * @generated
     */
    public Adapter createAbsoluteSpeedAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.RelativeSpeed <em>Relative Speed</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.RelativeSpeed
     * @generated
     */
    public Adapter createRelativeSpeedAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.DistanceAction <em>Distance Action</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.DistanceAction
     * @generated
     */
    public Adapter createDistanceActionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.DistanceSetting <em>Distance Setting</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.DistanceSetting
     * @generated
     */
    public Adapter createDistanceSettingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.RelativeDistance <em>Relative Distance</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.RelativeDistance
     * @generated
     */
    public Adapter createRelativeDistanceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.AccelAction <em>Accel Action</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.AccelAction
     * @generated
     */
    public Adapter createAccelActionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.AccelerationSetting <em>Acceleration Setting</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.AccelerationSetting
     * @generated
     */
    public Adapter createAccelerationSettingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.AbsoluteAcceleration <em>Absolute Acceleration</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.AbsoluteAcceleration
     * @generated
     */
    public Adapter createAbsoluteAccelerationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.Condition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.Condition
     * @generated
     */
    public Adapter createConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.VehicleCondition <em>Vehicle Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.VehicleCondition
     * @generated
     */
    public Adapter createVehicleConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.VehicleDetailCondition <em>Vehicle Detail Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.VehicleDetailCondition
     * @generated
     */
    public Adapter createVehicleDetailConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.TimeCondition <em>Time Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.TimeCondition
     * @generated
     */
    public Adapter createTimeConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.TimeEvenOnce <em>Time Even Once</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.TimeEvenOnce
     * @generated
     */
    public Adapter createTimeEvenOnceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.TimeDuration <em>Time Duration</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.TimeDuration
     * @generated
     */
    public Adapter createTimeDurationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.ValueCondition <em>Value Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.ValueCondition
     * @generated
     */
    public Adapter createValueConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.SimpleValueCondition <em>Simple Value Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.SimpleValueCondition
     * @generated
     */
    public Adapter createSimpleValueConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.RangeValueCondition <em>Range Value Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.RangeValueCondition
     * @generated
     */
    public Adapter createRangeValueConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.LaneCondition <em>Lane Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.LaneCondition
     * @generated
     */
    public Adapter createLaneConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.AbsoluteLaneCondition <em>Absolute Lane Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.AbsoluteLaneCondition
     * @generated
     */
    public Adapter createAbsoluteLaneConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.RelativeLaneCondition <em>Relative Lane Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.RelativeLaneCondition
     * @generated
     */
    public Adapter createRelativeLaneConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.SpeedCondition <em>Speed Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.SpeedCondition
     * @generated
     */
    public Adapter createSpeedConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.AbsoluteSpeedCondition <em>Absolute Speed Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.AbsoluteSpeedCondition
     * @generated
     */
    public Adapter createAbsoluteSpeedConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.RelativeSpeedCondition <em>Relative Speed Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.RelativeSpeedCondition
     * @generated
     */
    public Adapter createRelativeSpeedConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.DistanceCondition <em>Distance Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.DistanceCondition
     * @generated
     */
    public Adapter createDistanceConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.RelativeDistanceCondition <em>Relative Distance Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.RelativeDistanceCondition
     * @generated
     */
    public Adapter createRelativeDistanceConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.AccelCondition <em>Accel Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.AccelCondition
     * @generated
     */
    public Adapter createAccelConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.scenario.AbsoluteAccelCondition <em>Absolute Accel Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.scenario.AbsoluteAccelCondition
     * @generated
     */
    public Adapter createAbsoluteAccelConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.core.AbstractRootElement <em>Abstract Root Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.core.AbstractRootElement
     * @generated
     */
    public Adapter createAbstractRootElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //ScenarioAdapterFactory
