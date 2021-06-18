/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.IsSerializable;

import com.zipc.garden.model.arc.ARCPackage;

import com.zipc.garden.model.arc.impl.ARCPackageImpl;

import com.zipc.garden.model.bp.BPPackage;

import com.zipc.garden.model.bp.impl.BPPackageImpl;

import com.zipc.garden.model.bps.BPSPackage;

import com.zipc.garden.model.bps.impl.BPSPackageImpl;

import com.zipc.garden.model.cb.CBPackage;

import com.zipc.garden.model.cb.impl.CBPackageImpl;

import com.zipc.garden.model.cgen.CGENPackage;

import com.zipc.garden.model.cgen.impl.CGENPackageImpl;

import com.zipc.garden.model.core.COREPackage;

import com.zipc.garden.model.core.impl.COREPackageImpl;

import com.zipc.garden.model.csc.CSCPackage;

import com.zipc.garden.model.csc.impl.CSCPackageImpl;

import com.zipc.garden.model.cscs.CSCSPackage;

import com.zipc.garden.model.cscs.impl.CSCSPackageImpl;

import com.zipc.garden.model.fm.FMPackage;

import com.zipc.garden.model.fm.impl.FMPackageImpl;

import com.zipc.garden.model.fmc.FMCPackage;

import com.zipc.garden.model.fmc.impl.FMCPackageImpl;

import com.zipc.garden.model.fmcs.FMCSPackage;

import com.zipc.garden.model.fmcs.impl.FMCSPackageImpl;

import com.zipc.garden.model.fsm.FSMPackage;

import com.zipc.garden.model.fsm.impl.FSMPackageImpl;

import com.zipc.garden.model.lgen.LGENPackage;

import com.zipc.garden.model.lgen.impl.LGENPackageImpl;

import com.zipc.garden.model.lsc.LSCPackage;

import com.zipc.garden.model.lsc.impl.LSCPackageImpl;

import com.zipc.garden.model.scd.SCDPackage;

import com.zipc.garden.model.scd.impl.SCDPackageImpl;

import com.zipc.garden.model.scenario.AbsoluteAccelCondition;
import com.zipc.garden.model.scenario.AbsoluteAcceleration;
import com.zipc.garden.model.scenario.AbsoluteLane;
import com.zipc.garden.model.scenario.AbsoluteLaneCondition;
import com.zipc.garden.model.scenario.AbsoluteSpeed;
import com.zipc.garden.model.scenario.AbsoluteSpeedCondition;
import com.zipc.garden.model.scenario.AccelAction;
import com.zipc.garden.model.scenario.AccelCondition;
import com.zipc.garden.model.scenario.AccelerationSetting;
import com.zipc.garden.model.scenario.Action;
import com.zipc.garden.model.scenario.Actor;
import com.zipc.garden.model.scenario.ActorSetting;
import com.zipc.garden.model.scenario.ComparisonOperator;
import com.zipc.garden.model.scenario.Condition;
import com.zipc.garden.model.scenario.DistanceAction;
import com.zipc.garden.model.scenario.DistanceCondition;
import com.zipc.garden.model.scenario.DistanceDirection;
import com.zipc.garden.model.scenario.DistanceSetting;
import com.zipc.garden.model.scenario.EgoVehicle;
import com.zipc.garden.model.scenario.LaneAction;
import com.zipc.garden.model.scenario.LaneCondition;
import com.zipc.garden.model.scenario.LaneDirection;
import com.zipc.garden.model.scenario.LaneSetting;
import com.zipc.garden.model.scenario.OtherVehicle;
import com.zipc.garden.model.scenario.Phase;
import com.zipc.garden.model.scenario.PhaseAction;
import com.zipc.garden.model.scenario.PhaseCondition;
import com.zipc.garden.model.scenario.PhaseSetting;
import com.zipc.garden.model.scenario.RangeValueCondition;
import com.zipc.garden.model.scenario.RelativeDistance;
import com.zipc.garden.model.scenario.RelativeDistanceCondition;
import com.zipc.garden.model.scenario.RelativeLane;
import com.zipc.garden.model.scenario.RelativeLaneCondition;
import com.zipc.garden.model.scenario.RelativeSpeed;
import com.zipc.garden.model.scenario.RelativeSpeedCondition;
import com.zipc.garden.model.scenario.RoadSetting;
import com.zipc.garden.model.scenario.ScenarioFactory;
import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scenario.ScenarioRoot;
import com.zipc.garden.model.scenario.SimpleValueCondition;
import com.zipc.garden.model.scenario.SpeedAction;
import com.zipc.garden.model.scenario.SpeedCondition;
import com.zipc.garden.model.scenario.SpeedSetting;
import com.zipc.garden.model.scenario.TimeCondition;
import com.zipc.garden.model.scenario.TimeDuration;
import com.zipc.garden.model.scenario.TimeEvenOnce;
import com.zipc.garden.model.scenario.ValueCondition;
import com.zipc.garden.model.scenario.Vehicle;
import com.zipc.garden.model.scenario.VehicleAction;
import com.zipc.garden.model.scenario.VehicleCondition;
import com.zipc.garden.model.scenario.VehicleDetailAction;

import com.zipc.garden.model.scenario.VehicleDetailCondition;
import com.zipc.garden.model.scs.SCSPackage;

import com.zipc.garden.model.scs.impl.SCSPackageImpl;

import com.zipc.garden.model.spql.SPQLPackage;

import com.zipc.garden.model.spql.impl.SPQLPackageImpl;

import com.zipc.garden.model.tc.TCPackage;

import com.zipc.garden.model.tc.impl.TCPackageImpl;

import com.zipc.garden.model.tp.TPPackage;

import com.zipc.garden.model.tp.impl.TPPackageImpl;

import com.zipc.garden.model.tps.TPSPackage;

import com.zipc.garden.model.tps.impl.TPSPackageImpl;

import org.eclipse.emf.common.util.Reflect;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScenarioPackageImpl extends EPackageImpl implements ScenarioPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scenarioRootEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass roadSettingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass actorSettingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass vehicleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass egoVehicleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass otherVehicleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass phaseSettingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass phaseConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass phaseActionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass phaseEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass actionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass vehicleActionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass actorEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass vehicleDetailActionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass laneActionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass laneSettingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass absoluteLaneEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass relativeLaneEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass speedActionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass speedSettingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass absoluteSpeedEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass relativeSpeedEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass distanceActionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass distanceSettingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass relativeDistanceEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass accelActionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass accelerationSettingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass absoluteAccelerationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass conditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass vehicleConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass vehicleDetailConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass timeConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass timeEvenOnceEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass timeDurationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass valueConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass simpleValueConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass rangeValueConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass laneConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass absoluteLaneConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass relativeLaneConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass speedConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass absoluteSpeedConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass relativeSpeedConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass distanceConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass relativeDistanceConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass accelConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass absoluteAccelConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum laneDirectionEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum distanceDirectionEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum comparisonOperatorEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see com.zipc.garden.model.scenario.ScenarioPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ScenarioPackageImpl() {
        super(eNS_URI, ScenarioFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>This method is used to initialize {@link ScenarioPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ScenarioPackage init() {
        if (isInited) return (ScenarioPackage)EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI);

        initializeRegistryHelpers();

        // Obtain or create and register package
        Object registeredScenarioPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        ScenarioPackageImpl theScenarioPackage = registeredScenarioPackage instanceof ScenarioPackageImpl ? (ScenarioPackageImpl)registeredScenarioPackage : new ScenarioPackageImpl();

        isInited = true;

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(COREPackage.eNS_URI);
        COREPackageImpl theCOREPackage = (COREPackageImpl)(registeredPackage instanceof COREPackageImpl ? registeredPackage : COREPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SCDPackage.eNS_URI);
        SCDPackageImpl theSCDPackage = (SCDPackageImpl)(registeredPackage instanceof SCDPackageImpl ? registeredPackage : SCDPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FMPackage.eNS_URI);
        FMPackageImpl theFMPackage = (FMPackageImpl)(registeredPackage instanceof FMPackageImpl ? registeredPackage : FMPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FMCPackage.eNS_URI);
        FMCPackageImpl theFMCPackage = (FMCPackageImpl)(registeredPackage instanceof FMCPackageImpl ? registeredPackage : FMCPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FMCSPackage.eNS_URI);
        FMCSPackageImpl theFMCSPackage = (FMCSPackageImpl)(registeredPackage instanceof FMCSPackageImpl ? registeredPackage : FMCSPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(TCPackage.eNS_URI);
        TCPackageImpl theTCPackage = (TCPackageImpl)(registeredPackage instanceof TCPackageImpl ? registeredPackage : TCPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ARCPackage.eNS_URI);
        ARCPackageImpl theARCPackage = (ARCPackageImpl)(registeredPackage instanceof ARCPackageImpl ? registeredPackage : ARCPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FSMPackage.eNS_URI);
        FSMPackageImpl theFSMPackage = (FSMPackageImpl)(registeredPackage instanceof FSMPackageImpl ? registeredPackage : FSMPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(TPSPackage.eNS_URI);
        TPSPackageImpl theTPSPackage = (TPSPackageImpl)(registeredPackage instanceof TPSPackageImpl ? registeredPackage : TPSPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(TPPackage.eNS_URI);
        TPPackageImpl theTPPackage = (TPPackageImpl)(registeredPackage instanceof TPPackageImpl ? registeredPackage : TPPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(BPSPackage.eNS_URI);
        BPSPackageImpl theBPSPackage = (BPSPackageImpl)(registeredPackage instanceof BPSPackageImpl ? registeredPackage : BPSPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(BPPackage.eNS_URI);
        BPPackageImpl theBPPackage = (BPPackageImpl)(registeredPackage instanceof BPPackageImpl ? registeredPackage : BPPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(LGENPackage.eNS_URI);
        LGENPackageImpl theLGENPackage = (LGENPackageImpl)(registeredPackage instanceof LGENPackageImpl ? registeredPackage : LGENPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(LSCPackage.eNS_URI);
        LSCPackageImpl theLSCPackage = (LSCPackageImpl)(registeredPackage instanceof LSCPackageImpl ? registeredPackage : LSCPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CGENPackage.eNS_URI);
        CGENPackageImpl theCGENPackage = (CGENPackageImpl)(registeredPackage instanceof CGENPackageImpl ? registeredPackage : CGENPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CSCPackage.eNS_URI);
        CSCPackageImpl theCSCPackage = (CSCPackageImpl)(registeredPackage instanceof CSCPackageImpl ? registeredPackage : CSCPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CBPackage.eNS_URI);
        CBPackageImpl theCBPackage = (CBPackageImpl)(registeredPackage instanceof CBPackageImpl ? registeredPackage : CBPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SCSPackage.eNS_URI);
        SCSPackageImpl theSCSPackage = (SCSPackageImpl)(registeredPackage instanceof SCSPackageImpl ? registeredPackage : SCSPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SPQLPackage.eNS_URI);
        SPQLPackageImpl theSPQLPackage = (SPQLPackageImpl)(registeredPackage instanceof SPQLPackageImpl ? registeredPackage : SPQLPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CSCSPackage.eNS_URI);
        CSCSPackageImpl theCSCSPackage = (CSCSPackageImpl)(registeredPackage instanceof CSCSPackageImpl ? registeredPackage : CSCSPackage.eINSTANCE);

        // Create package meta-data objects
        theScenarioPackage.createPackageContents();
        theCOREPackage.createPackageContents();
        theSCDPackage.createPackageContents();
        theFMPackage.createPackageContents();
        theFMCPackage.createPackageContents();
        theFMCSPackage.createPackageContents();
        theTCPackage.createPackageContents();
        theARCPackage.createPackageContents();
        theFSMPackage.createPackageContents();
        theTPSPackage.createPackageContents();
        theTPPackage.createPackageContents();
        theBPSPackage.createPackageContents();
        theBPPackage.createPackageContents();
        theLGENPackage.createPackageContents();
        theLSCPackage.createPackageContents();
        theCGENPackage.createPackageContents();
        theCSCPackage.createPackageContents();
        theCBPackage.createPackageContents();
        theSCSPackage.createPackageContents();
        theSPQLPackage.createPackageContents();
        theCSCSPackage.createPackageContents();

        // Initialize created meta-data
        theScenarioPackage.initializePackageContents();
        theCOREPackage.initializePackageContents();
        theSCDPackage.initializePackageContents();
        theFMPackage.initializePackageContents();
        theFMCPackage.initializePackageContents();
        theFMCSPackage.initializePackageContents();
        theTCPackage.initializePackageContents();
        theARCPackage.initializePackageContents();
        theFSMPackage.initializePackageContents();
        theTPSPackage.initializePackageContents();
        theTPPackage.initializePackageContents();
        theBPSPackage.initializePackageContents();
        theBPPackage.initializePackageContents();
        theLGENPackage.initializePackageContents();
        theLSCPackage.initializePackageContents();
        theCGENPackage.initializePackageContents();
        theCSCPackage.initializePackageContents();
        theCBPackage.initializePackageContents();
        theSCSPackage.initializePackageContents();
        theSPQLPackage.initializePackageContents();
        theCSCSPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theScenarioPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ScenarioPackage.eNS_URI, theScenarioPackage);
        return theScenarioPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void initializeRegistryHelpers() {
        Reflect.register
            (ScenarioRoot.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof ScenarioRoot;
                 }

                 public Object newArrayInstance(int size) {
                     return new ScenarioRoot[size];
                 }
             });
        Reflect.register
            (RoadSetting.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof RoadSetting;
                 }

                 public Object newArrayInstance(int size) {
                     return new RoadSetting[size];
                 }
             });
        Reflect.register
            (ActorSetting.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof ActorSetting;
                 }

                 public Object newArrayInstance(int size) {
                     return new ActorSetting[size];
                 }
             });
        Reflect.register
            (Vehicle.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof Vehicle;
                 }

                 public Object newArrayInstance(int size) {
                     return new Vehicle[size];
                 }
             });
        Reflect.register
            (EgoVehicle.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof EgoVehicle;
                 }

                 public Object newArrayInstance(int size) {
                     return new EgoVehicle[size];
                 }
             });
        Reflect.register
            (OtherVehicle.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof OtherVehicle;
                 }

                 public Object newArrayInstance(int size) {
                     return new OtherVehicle[size];
                 }
             });
        Reflect.register
            (PhaseSetting.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof PhaseSetting;
                 }

                 public Object newArrayInstance(int size) {
                     return new PhaseSetting[size];
                 }
             });
        Reflect.register
            (PhaseCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof PhaseCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new PhaseCondition[size];
                 }
             });
        Reflect.register
            (PhaseAction.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof PhaseAction;
                 }

                 public Object newArrayInstance(int size) {
                     return new PhaseAction[size];
                 }
             });
        Reflect.register
            (Phase.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof Phase;
                 }

                 public Object newArrayInstance(int size) {
                     return new Phase[size];
                 }
             });
        Reflect.register
            (Action.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof Action;
                 }

                 public Object newArrayInstance(int size) {
                     return new Action[size];
                 }
             });
        Reflect.register
            (VehicleAction.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof VehicleAction;
                 }

                 public Object newArrayInstance(int size) {
                     return new VehicleAction[size];
                 }
             });
        Reflect.register
            (Actor.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof Actor;
                 }

                 public Object newArrayInstance(int size) {
                     return new Actor[size];
                 }
             });
        Reflect.register
            (VehicleDetailAction.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof VehicleDetailAction;
                 }

                 public Object newArrayInstance(int size) {
                     return new VehicleDetailAction[size];
                 }
             });
        Reflect.register
            (LaneAction.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof LaneAction;
                 }

                 public Object newArrayInstance(int size) {
                     return new LaneAction[size];
                 }
             });
        Reflect.register
            (LaneSetting.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof LaneSetting;
                 }

                 public Object newArrayInstance(int size) {
                     return new LaneSetting[size];
                 }
             });
        Reflect.register
            (AbsoluteLane.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof AbsoluteLane;
                 }

                 public Object newArrayInstance(int size) {
                     return new AbsoluteLane[size];
                 }
             });
        Reflect.register
            (RelativeLane.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof RelativeLane;
                 }

                 public Object newArrayInstance(int size) {
                     return new RelativeLane[size];
                 }
             });
        Reflect.register
            (SpeedAction.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof SpeedAction;
                 }

                 public Object newArrayInstance(int size) {
                     return new SpeedAction[size];
                 }
             });
        Reflect.register
            (SpeedSetting.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof SpeedSetting;
                 }

                 public Object newArrayInstance(int size) {
                     return new SpeedSetting[size];
                 }
             });
        Reflect.register
            (AbsoluteSpeed.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof AbsoluteSpeed;
                 }

                 public Object newArrayInstance(int size) {
                     return new AbsoluteSpeed[size];
                 }
             });
        Reflect.register
            (RelativeSpeed.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof RelativeSpeed;
                 }

                 public Object newArrayInstance(int size) {
                     return new RelativeSpeed[size];
                 }
             });
        Reflect.register
            (DistanceAction.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof DistanceAction;
                 }

                 public Object newArrayInstance(int size) {
                     return new DistanceAction[size];
                 }
             });
        Reflect.register
            (DistanceSetting.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof DistanceSetting;
                 }

                 public Object newArrayInstance(int size) {
                     return new DistanceSetting[size];
                 }
             });
        Reflect.register
            (RelativeDistance.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof RelativeDistance;
                 }

                 public Object newArrayInstance(int size) {
                     return new RelativeDistance[size];
                 }
             });
        Reflect.register
            (AccelAction.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof AccelAction;
                 }

                 public Object newArrayInstance(int size) {
                     return new AccelAction[size];
                 }
             });
        Reflect.register
            (AccelerationSetting.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof AccelerationSetting;
                 }

                 public Object newArrayInstance(int size) {
                     return new AccelerationSetting[size];
                 }
             });
        Reflect.register
            (AbsoluteAcceleration.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof AbsoluteAcceleration;
                 }

                 public Object newArrayInstance(int size) {
                     return new AbsoluteAcceleration[size];
                 }
             });
        Reflect.register
            (Condition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof Condition;
                 }

                 public Object newArrayInstance(int size) {
                     return new Condition[size];
                 }
             });
        Reflect.register
            (VehicleCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof VehicleCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new VehicleCondition[size];
                 }
             });
        Reflect.register
            (VehicleDetailCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof VehicleDetailCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new VehicleDetailCondition[size];
                 }
             });
        Reflect.register
            (TimeCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof TimeCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new TimeCondition[size];
                 }
             });
        Reflect.register
            (TimeEvenOnce.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof TimeEvenOnce;
                 }

                 public Object newArrayInstance(int size) {
                     return new TimeEvenOnce[size];
                 }
             });
        Reflect.register
            (TimeDuration.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof TimeDuration;
                 }

                 public Object newArrayInstance(int size) {
                     return new TimeDuration[size];
                 }
             });
        Reflect.register
            (ValueCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof ValueCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new ValueCondition[size];
                 }
             });
        Reflect.register
            (SimpleValueCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof SimpleValueCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new SimpleValueCondition[size];
                 }
             });
        Reflect.register
            (RangeValueCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof RangeValueCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new RangeValueCondition[size];
                 }
             });
        Reflect.register
            (LaneCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof LaneCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new LaneCondition[size];
                 }
             });
        Reflect.register
            (AbsoluteLaneCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof AbsoluteLaneCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new AbsoluteLaneCondition[size];
                 }
             });
        Reflect.register
            (RelativeLaneCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof RelativeLaneCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new RelativeLaneCondition[size];
                 }
             });
        Reflect.register
            (SpeedCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof SpeedCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new SpeedCondition[size];
                 }
             });
        Reflect.register
            (AbsoluteSpeedCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof AbsoluteSpeedCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new AbsoluteSpeedCondition[size];
                 }
             });
        Reflect.register
            (RelativeSpeedCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof RelativeSpeedCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new RelativeSpeedCondition[size];
                 }
             });
        Reflect.register
            (DistanceCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof DistanceCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new DistanceCondition[size];
                 }
             });
        Reflect.register
            (RelativeDistanceCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof RelativeDistanceCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new RelativeDistanceCondition[size];
                 }
             });
        Reflect.register
            (AccelCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof AccelCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new AccelCondition[size];
                 }
             });
        Reflect.register
            (AbsoluteAccelCondition.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof AbsoluteAccelCondition;
                 }

                 public Object newArrayInstance(int size) {
                     return new AbsoluteAccelCondition[size];
                 }
             });
        Reflect.register
            (LaneDirection.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof LaneDirection;
                 }

                 public Object newArrayInstance(int size) {
                     return new LaneDirection[size];
                 }
        });
        Reflect.register
            (DistanceDirection.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof DistanceDirection;
                 }

                 public Object newArrayInstance(int size) {
                     return new DistanceDirection[size];
                 }
        });
        Reflect.register
            (ComparisonOperator.class,
             new Reflect.Helper() {
                 public boolean isInstance(Object instance) {
                     return instance instanceof ComparisonOperator;
                 }

                 public Object newArrayInstance(int size) {
                     return new ComparisonOperator[size];
                 }
        });
    }


    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static class WhiteList implements IsSerializable, EBasicWhiteList {
        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected ScenarioRoot scenarioRoot;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected RoadSetting roadSetting;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected ActorSetting actorSetting;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected Vehicle vehicle;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected EgoVehicle egoVehicle;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected OtherVehicle otherVehicle;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected PhaseSetting phaseSetting;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected PhaseCondition phaseCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected PhaseAction phaseAction;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected Phase phase;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected Action action;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected VehicleAction vehicleAction;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected Actor actor;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected VehicleDetailAction vehicleDetailAction;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected LaneAction laneAction;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected LaneSetting laneSetting;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected AbsoluteLane absoluteLane;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected RelativeLane relativeLane;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected SpeedAction speedAction;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected SpeedSetting speedSetting;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected AbsoluteSpeed absoluteSpeed;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected RelativeSpeed relativeSpeed;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected DistanceAction distanceAction;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected DistanceSetting distanceSetting;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected RelativeDistance relativeDistance;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected AccelAction accelAction;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected AccelerationSetting accelerationSetting;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected AbsoluteAcceleration absoluteAcceleration;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected Condition condition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected VehicleCondition vehicleCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected VehicleDetailCondition vehicleDetailCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected TimeCondition timeCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected TimeEvenOnce timeEvenOnce;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected TimeDuration timeDuration;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected ValueCondition valueCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected SimpleValueCondition simpleValueCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected RangeValueCondition rangeValueCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected LaneCondition laneCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected AbsoluteLaneCondition absoluteLaneCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected RelativeLaneCondition relativeLaneCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected SpeedCondition speedCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected AbsoluteSpeedCondition absoluteSpeedCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected RelativeSpeedCondition relativeSpeedCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected DistanceCondition distanceCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected RelativeDistanceCondition relativeDistanceCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected AccelCondition accelCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected AbsoluteAccelCondition absoluteAccelCondition;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected LaneDirection laneDirection;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected DistanceDirection distanceDirection;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected ComparisonOperator comparisonOperator;

    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getScenarioRoot() {
        return scenarioRootEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getScenarioRoot_RoadSetting() {
        return (EReference)scenarioRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getScenarioRoot_ActorSetting() {
        return (EReference)scenarioRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getScenarioRoot_PhaseSetting() {
        return (EReference)scenarioRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getRoadSetting() {
        return roadSettingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getRoadSetting_LaneCount() {
        return (EAttribute)roadSettingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getActorSetting() {
        return actorSettingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getActorSetting_Ego() {
        return (EReference)actorSettingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getActorSetting_Others() {
        return (EReference)actorSettingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getVehicle() {
        return vehicleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getEgoVehicle() {
        return egoVehicleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getOtherVehicle() {
        return otherVehicleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getPhaseSetting() {
        return phaseSettingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getPhaseSetting_InitialPhase() {
        return (EReference)phaseSettingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getPhaseCondition() {
        return phaseConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getPhaseCondition_Conditions() {
        return (EReference)phaseConditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getPhaseAction() {
        return phaseActionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getPhaseAction_Actions() {
        return (EReference)phaseActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getPhase() {
        return phaseEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getPhase_Action() {
        return (EReference)phaseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getPhase_Condition() {
        return (EReference)phaseEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getPhase_NextPhases() {
        return (EReference)phaseEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getPhase_PrevPhase() {
        return (EReference)phaseEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getPhase_Name() {
        return (EAttribute)phaseEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAction() {
        return actionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getVehicleAction() {
        return vehicleActionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getVehicleAction_Vehicle() {
        return (EReference)vehicleActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getVehicleAction_Appear() {
        return (EAttribute)vehicleActionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getVehicleAction_Actions() {
        return (EReference)vehicleActionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getActor() {
        return actorEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getActor_Name() {
        return (EAttribute)actorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getVehicleDetailAction() {
        return vehicleDetailActionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getLaneAction() {
        return laneActionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getLaneAction_Lane() {
        return (EReference)laneActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getLaneSetting() {
        return laneSettingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbsoluteLane() {
        return absoluteLaneEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbsoluteLane_LaneId() {
        return (EAttribute)absoluteLaneEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getRelativeLane() {
        return relativeLaneEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getRelativeLane_Vehicle() {
        return (EReference)relativeLaneEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getRelativeLane_Offset() {
        return (EAttribute)relativeLaneEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getRelativeLane_Direction() {
        return (EAttribute)relativeLaneEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getSpeedAction() {
        return speedActionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getSpeedAction_Speed() {
        return (EReference)speedActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getSpeedSetting() {
        return speedSettingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbsoluteSpeed() {
        return absoluteSpeedEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbsoluteSpeed_Kph() {
        return (EAttribute)absoluteSpeedEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getRelativeSpeed() {
        return relativeSpeedEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getRelativeSpeed_Vehicle() {
        return (EReference)relativeSpeedEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getRelativeSpeed_Kph() {
        return (EAttribute)relativeSpeedEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getDistanceAction() {
        return distanceActionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDistanceAction_Distance() {
        return (EReference)distanceActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getDistanceSetting() {
        return distanceSettingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getRelativeDistance() {
        return relativeDistanceEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getRelativeDistance_Vehicle() {
        return (EReference)relativeDistanceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getRelativeDistance_Direction() {
        return (EAttribute)relativeDistanceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getRelativeDistance_Meter() {
        return (EAttribute)relativeDistanceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAccelAction() {
        return accelActionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getAccelAction_Accel() {
        return (EReference)accelActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAccelerationSetting() {
        return accelerationSettingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbsoluteAcceleration() {
        return absoluteAccelerationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbsoluteAcceleration_Mpss() {
        return (EAttribute)absoluteAccelerationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getCondition() {
        return conditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getCondition_Enabled() {
        return (EAttribute)conditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getVehicleCondition() {
        return vehicleConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getVehicleCondition_Vehicle() {
        return (EReference)vehicleConditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getVehicleCondition_Conditions() {
        return (EReference)vehicleConditionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getVehicleDetailCondition() {
        return vehicleDetailConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getVehicleDetailCondition_Time() {
        return (EReference)vehicleDetailConditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTimeCondition() {
        return timeConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTimeEvenOnce() {
        return timeEvenOnceEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTimeDuration() {
        return timeDurationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTimeDuration_Msec() {
        return (EAttribute)timeDurationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getValueCondition() {
        return valueConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getSimpleValueCondition() {
        return simpleValueConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSimpleValueCondition_Value() {
        return (EAttribute)simpleValueConditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSimpleValueCondition_Operator() {
        return (EAttribute)simpleValueConditionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getRangeValueCondition() {
        return rangeValueConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getRangeValueCondition_UpperValue() {
        return (EAttribute)rangeValueConditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getRangeValueCondition_LowerValue() {
        return (EAttribute)rangeValueConditionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getLaneCondition() {
        return laneConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbsoluteLaneCondition() {
        return absoluteLaneConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbsoluteLaneCondition_LaneId() {
        return (EAttribute)absoluteLaneConditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbsoluteLaneCondition_Operator() {
        return (EAttribute)absoluteLaneConditionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getRelativeLaneCondition() {
        return relativeLaneConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getRelativeLaneCondition_Vehicle() {
        return (EReference)relativeLaneConditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getRelativeLaneCondition_Direction() {
        return (EAttribute)relativeLaneConditionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getRelativeLaneCondition_Offset() {
        return (EAttribute)relativeLaneConditionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getSpeedCondition() {
        return speedConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbsoluteSpeedCondition() {
        return absoluteSpeedConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getAbsoluteSpeedCondition_Kph() {
        return (EReference)absoluteSpeedConditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getRelativeSpeedCondition() {
        return relativeSpeedConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getRelativeSpeedCondition_Vehicle() {
        return (EReference)relativeSpeedConditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getRelativeSpeedCondition_Kph() {
        return (EReference)relativeSpeedConditionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getDistanceCondition() {
        return distanceConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getRelativeDistanceCondition() {
        return relativeDistanceConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getRelativeDistanceCondition_Vehicle() {
        return (EReference)relativeDistanceConditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getRelativeDistanceCondition_Direction() {
        return (EAttribute)relativeDistanceConditionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getRelativeDistanceCondition_Meter() {
        return (EReference)relativeDistanceConditionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAccelCondition() {
        return accelConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbsoluteAccelCondition() {
        return absoluteAccelConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getAbsoluteAccelCondition_Mpss() {
        return (EReference)absoluteAccelConditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getLaneDirection() {
        return laneDirectionEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getDistanceDirection() {
        return distanceDirectionEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getComparisonOperator() {
        return comparisonOperatorEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ScenarioFactory getScenarioFactory() {
        return (ScenarioFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        scenarioRootEClass = createEClass(SCENARIO_ROOT);
        createEReference(scenarioRootEClass, SCENARIO_ROOT__ROAD_SETTING);
        createEReference(scenarioRootEClass, SCENARIO_ROOT__ACTOR_SETTING);
        createEReference(scenarioRootEClass, SCENARIO_ROOT__PHASE_SETTING);

        roadSettingEClass = createEClass(ROAD_SETTING);
        createEAttribute(roadSettingEClass, ROAD_SETTING__LANE_COUNT);

        actorSettingEClass = createEClass(ACTOR_SETTING);
        createEReference(actorSettingEClass, ACTOR_SETTING__EGO);
        createEReference(actorSettingEClass, ACTOR_SETTING__OTHERS);

        vehicleEClass = createEClass(VEHICLE);

        egoVehicleEClass = createEClass(EGO_VEHICLE);

        otherVehicleEClass = createEClass(OTHER_VEHICLE);

        phaseSettingEClass = createEClass(PHASE_SETTING);
        createEReference(phaseSettingEClass, PHASE_SETTING__INITIAL_PHASE);

        phaseConditionEClass = createEClass(PHASE_CONDITION);
        createEReference(phaseConditionEClass, PHASE_CONDITION__CONDITIONS);

        phaseActionEClass = createEClass(PHASE_ACTION);
        createEReference(phaseActionEClass, PHASE_ACTION__ACTIONS);

        phaseEClass = createEClass(PHASE);
        createEReference(phaseEClass, PHASE__ACTION);
        createEReference(phaseEClass, PHASE__CONDITION);
        createEReference(phaseEClass, PHASE__NEXT_PHASES);
        createEReference(phaseEClass, PHASE__PREV_PHASE);
        createEAttribute(phaseEClass, PHASE__NAME);

        actionEClass = createEClass(ACTION);

        vehicleActionEClass = createEClass(VEHICLE_ACTION);
        createEReference(vehicleActionEClass, VEHICLE_ACTION__VEHICLE);
        createEAttribute(vehicleActionEClass, VEHICLE_ACTION__APPEAR);
        createEReference(vehicleActionEClass, VEHICLE_ACTION__ACTIONS);

        actorEClass = createEClass(ACTOR);
        createEAttribute(actorEClass, ACTOR__NAME);

        vehicleDetailActionEClass = createEClass(VEHICLE_DETAIL_ACTION);

        laneActionEClass = createEClass(LANE_ACTION);
        createEReference(laneActionEClass, LANE_ACTION__LANE);

        laneSettingEClass = createEClass(LANE_SETTING);

        absoluteLaneEClass = createEClass(ABSOLUTE_LANE);
        createEAttribute(absoluteLaneEClass, ABSOLUTE_LANE__LANE_ID);

        relativeLaneEClass = createEClass(RELATIVE_LANE);
        createEReference(relativeLaneEClass, RELATIVE_LANE__VEHICLE);
        createEAttribute(relativeLaneEClass, RELATIVE_LANE__OFFSET);
        createEAttribute(relativeLaneEClass, RELATIVE_LANE__DIRECTION);

        speedActionEClass = createEClass(SPEED_ACTION);
        createEReference(speedActionEClass, SPEED_ACTION__SPEED);

        speedSettingEClass = createEClass(SPEED_SETTING);

        absoluteSpeedEClass = createEClass(ABSOLUTE_SPEED);
        createEAttribute(absoluteSpeedEClass, ABSOLUTE_SPEED__KPH);

        relativeSpeedEClass = createEClass(RELATIVE_SPEED);
        createEReference(relativeSpeedEClass, RELATIVE_SPEED__VEHICLE);
        createEAttribute(relativeSpeedEClass, RELATIVE_SPEED__KPH);

        distanceActionEClass = createEClass(DISTANCE_ACTION);
        createEReference(distanceActionEClass, DISTANCE_ACTION__DISTANCE);

        distanceSettingEClass = createEClass(DISTANCE_SETTING);

        relativeDistanceEClass = createEClass(RELATIVE_DISTANCE);
        createEReference(relativeDistanceEClass, RELATIVE_DISTANCE__VEHICLE);
        createEAttribute(relativeDistanceEClass, RELATIVE_DISTANCE__DIRECTION);
        createEAttribute(relativeDistanceEClass, RELATIVE_DISTANCE__METER);

        accelActionEClass = createEClass(ACCEL_ACTION);
        createEReference(accelActionEClass, ACCEL_ACTION__ACCEL);

        accelerationSettingEClass = createEClass(ACCELERATION_SETTING);

        absoluteAccelerationEClass = createEClass(ABSOLUTE_ACCELERATION);
        createEAttribute(absoluteAccelerationEClass, ABSOLUTE_ACCELERATION__MPSS);

        conditionEClass = createEClass(CONDITION);
        createEAttribute(conditionEClass, CONDITION__ENABLED);

        vehicleConditionEClass = createEClass(VEHICLE_CONDITION);
        createEReference(vehicleConditionEClass, VEHICLE_CONDITION__VEHICLE);
        createEReference(vehicleConditionEClass, VEHICLE_CONDITION__CONDITIONS);

        vehicleDetailConditionEClass = createEClass(VEHICLE_DETAIL_CONDITION);
        createEReference(vehicleDetailConditionEClass, VEHICLE_DETAIL_CONDITION__TIME);

        timeConditionEClass = createEClass(TIME_CONDITION);

        timeEvenOnceEClass = createEClass(TIME_EVEN_ONCE);

        timeDurationEClass = createEClass(TIME_DURATION);
        createEAttribute(timeDurationEClass, TIME_DURATION__MSEC);

        valueConditionEClass = createEClass(VALUE_CONDITION);

        simpleValueConditionEClass = createEClass(SIMPLE_VALUE_CONDITION);
        createEAttribute(simpleValueConditionEClass, SIMPLE_VALUE_CONDITION__VALUE);
        createEAttribute(simpleValueConditionEClass, SIMPLE_VALUE_CONDITION__OPERATOR);

        rangeValueConditionEClass = createEClass(RANGE_VALUE_CONDITION);
        createEAttribute(rangeValueConditionEClass, RANGE_VALUE_CONDITION__UPPER_VALUE);
        createEAttribute(rangeValueConditionEClass, RANGE_VALUE_CONDITION__LOWER_VALUE);

        laneConditionEClass = createEClass(LANE_CONDITION);

        absoluteLaneConditionEClass = createEClass(ABSOLUTE_LANE_CONDITION);
        createEAttribute(absoluteLaneConditionEClass, ABSOLUTE_LANE_CONDITION__LANE_ID);
        createEAttribute(absoluteLaneConditionEClass, ABSOLUTE_LANE_CONDITION__OPERATOR);

        relativeLaneConditionEClass = createEClass(RELATIVE_LANE_CONDITION);
        createEReference(relativeLaneConditionEClass, RELATIVE_LANE_CONDITION__VEHICLE);
        createEAttribute(relativeLaneConditionEClass, RELATIVE_LANE_CONDITION__DIRECTION);
        createEAttribute(relativeLaneConditionEClass, RELATIVE_LANE_CONDITION__OFFSET);

        speedConditionEClass = createEClass(SPEED_CONDITION);

        absoluteSpeedConditionEClass = createEClass(ABSOLUTE_SPEED_CONDITION);
        createEReference(absoluteSpeedConditionEClass, ABSOLUTE_SPEED_CONDITION__KPH);

        relativeSpeedConditionEClass = createEClass(RELATIVE_SPEED_CONDITION);
        createEReference(relativeSpeedConditionEClass, RELATIVE_SPEED_CONDITION__VEHICLE);
        createEReference(relativeSpeedConditionEClass, RELATIVE_SPEED_CONDITION__KPH);

        distanceConditionEClass = createEClass(DISTANCE_CONDITION);

        relativeDistanceConditionEClass = createEClass(RELATIVE_DISTANCE_CONDITION);
        createEReference(relativeDistanceConditionEClass, RELATIVE_DISTANCE_CONDITION__VEHICLE);
        createEAttribute(relativeDistanceConditionEClass, RELATIVE_DISTANCE_CONDITION__DIRECTION);
        createEReference(relativeDistanceConditionEClass, RELATIVE_DISTANCE_CONDITION__METER);

        accelConditionEClass = createEClass(ACCEL_CONDITION);

        absoluteAccelConditionEClass = createEClass(ABSOLUTE_ACCEL_CONDITION);
        createEReference(absoluteAccelConditionEClass, ABSOLUTE_ACCEL_CONDITION__MPSS);

        // Create enums
        laneDirectionEEnum = createEEnum(LANE_DIRECTION);
        distanceDirectionEEnum = createEEnum(DISTANCE_DIRECTION);
        comparisonOperatorEEnum = createEEnum(COMPARISON_OPERATOR);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        COREPackage theCOREPackage = (COREPackage)EPackage.Registry.INSTANCE.getEPackage(COREPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        scenarioRootEClass.getESuperTypes().add(theCOREPackage.getAbstractRootElement());
        vehicleEClass.getESuperTypes().add(this.getActor());
        egoVehicleEClass.getESuperTypes().add(this.getVehicle());
        otherVehicleEClass.getESuperTypes().add(this.getVehicle());
        vehicleActionEClass.getESuperTypes().add(this.getAction());
        laneActionEClass.getESuperTypes().add(this.getVehicleDetailAction());
        absoluteLaneEClass.getESuperTypes().add(this.getLaneSetting());
        relativeLaneEClass.getESuperTypes().add(this.getLaneSetting());
        speedActionEClass.getESuperTypes().add(this.getVehicleDetailAction());
        absoluteSpeedEClass.getESuperTypes().add(this.getSpeedSetting());
        relativeSpeedEClass.getESuperTypes().add(this.getSpeedSetting());
        distanceActionEClass.getESuperTypes().add(this.getVehicleDetailAction());
        relativeDistanceEClass.getESuperTypes().add(this.getDistanceSetting());
        accelActionEClass.getESuperTypes().add(this.getVehicleDetailAction());
        absoluteAccelerationEClass.getESuperTypes().add(this.getAccelerationSetting());
        vehicleConditionEClass.getESuperTypes().add(this.getCondition());
        timeEvenOnceEClass.getESuperTypes().add(this.getTimeCondition());
        timeDurationEClass.getESuperTypes().add(this.getTimeCondition());
        simpleValueConditionEClass.getESuperTypes().add(this.getValueCondition());
        rangeValueConditionEClass.getESuperTypes().add(this.getValueCondition());
        laneConditionEClass.getESuperTypes().add(this.getVehicleDetailCondition());
        absoluteLaneConditionEClass.getESuperTypes().add(this.getLaneCondition());
        relativeLaneConditionEClass.getESuperTypes().add(this.getLaneCondition());
        speedConditionEClass.getESuperTypes().add(this.getVehicleDetailCondition());
        absoluteSpeedConditionEClass.getESuperTypes().add(this.getSpeedCondition());
        relativeSpeedConditionEClass.getESuperTypes().add(this.getSpeedCondition());
        distanceConditionEClass.getESuperTypes().add(this.getVehicleDetailCondition());
        relativeDistanceConditionEClass.getESuperTypes().add(this.getDistanceCondition());
        accelConditionEClass.getESuperTypes().add(this.getVehicleDetailCondition());
        absoluteAccelConditionEClass.getESuperTypes().add(this.getAccelCondition());

        // Initialize classes, features, and operations; add parameters
        initEClass(scenarioRootEClass, ScenarioRoot.class, "ScenarioRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getScenarioRoot_RoadSetting(), this.getRoadSetting(), null, "roadSetting", null, 1, 1, ScenarioRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getScenarioRoot_ActorSetting(), this.getActorSetting(), null, "actorSetting", null, 1, 1, ScenarioRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getScenarioRoot_PhaseSetting(), this.getPhaseSetting(), null, "phaseSetting", null, 1, 1, ScenarioRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(roadSettingEClass, RoadSetting.class, "RoadSetting", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRoadSetting_LaneCount(), ecorePackage.getEInt(), "laneCount", "3", 1, 1, RoadSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(actorSettingEClass, ActorSetting.class, "ActorSetting", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getActorSetting_Ego(), this.getEgoVehicle(), null, "ego", null, 1, 1, ActorSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getActorSetting_Others(), this.getActor(), null, "others", null, 0, -1, ActorSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(vehicleEClass, Vehicle.class, "Vehicle", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(egoVehicleEClass, EgoVehicle.class, "EgoVehicle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(otherVehicleEClass, OtherVehicle.class, "OtherVehicle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(phaseSettingEClass, PhaseSetting.class, "PhaseSetting", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPhaseSetting_InitialPhase(), this.getPhase(), null, "initialPhase", null, 1, 1, PhaseSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(phaseConditionEClass, PhaseCondition.class, "PhaseCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPhaseCondition_Conditions(), this.getCondition(), null, "conditions", null, 0, -1, PhaseCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(phaseActionEClass, PhaseAction.class, "PhaseAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPhaseAction_Actions(), this.getAction(), null, "actions", null, 0, -1, PhaseAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(phaseEClass, Phase.class, "Phase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPhase_Action(), this.getPhaseAction(), null, "action", null, 1, 1, Phase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPhase_Condition(), this.getPhaseCondition(), null, "condition", null, 1, 1, Phase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPhase_NextPhases(), this.getPhase(), this.getPhase_PrevPhase(), "nextPhases", null, 0, -1, Phase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPhase_PrevPhase(), this.getPhase(), this.getPhase_NextPhases(), "prevPhase", null, 0, 1, Phase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPhase_Name(), ecorePackage.getEString(), "name", null, 0, 1, Phase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(actionEClass, Action.class, "Action", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(vehicleActionEClass, VehicleAction.class, "VehicleAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVehicleAction_Vehicle(), this.getVehicle(), null, "vehicle", null, 1, 1, VehicleAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVehicleAction_Appear(), ecorePackage.getEBoolean(), "appear", null, 0, 1, VehicleAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVehicleAction_Actions(), this.getVehicleDetailAction(), null, "actions", null, 1, -1, VehicleAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(actorEClass, Actor.class, "Actor", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getActor_Name(), ecorePackage.getEString(), "name", null, 0, 1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(vehicleDetailActionEClass, VehicleDetailAction.class, "VehicleDetailAction", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(laneActionEClass, LaneAction.class, "LaneAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLaneAction_Lane(), this.getLaneSetting(), null, "lane", null, 1, 1, LaneAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(laneSettingEClass, LaneSetting.class, "LaneSetting", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(absoluteLaneEClass, AbsoluteLane.class, "AbsoluteLane", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbsoluteLane_LaneId(), ecorePackage.getEInt(), "laneId", null, 1, 1, AbsoluteLane.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(relativeLaneEClass, RelativeLane.class, "RelativeLane", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRelativeLane_Vehicle(), this.getVehicle(), null, "vehicle", null, 1, 1, RelativeLane.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRelativeLane_Offset(), ecorePackage.getEInt(), "offset", null, 1, 1, RelativeLane.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRelativeLane_Direction(), this.getLaneDirection(), "direction", null, 1, 1, RelativeLane.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(speedActionEClass, SpeedAction.class, "SpeedAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSpeedAction_Speed(), this.getSpeedSetting(), null, "speed", null, 1, 1, SpeedAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(speedSettingEClass, SpeedSetting.class, "SpeedSetting", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(absoluteSpeedEClass, AbsoluteSpeed.class, "AbsoluteSpeed", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbsoluteSpeed_Kph(), ecorePackage.getEDouble(), "kph", null, 1, 1, AbsoluteSpeed.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(relativeSpeedEClass, RelativeSpeed.class, "RelativeSpeed", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRelativeSpeed_Vehicle(), this.getVehicle(), null, "vehicle", null, 1, 1, RelativeSpeed.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRelativeSpeed_Kph(), ecorePackage.getEDouble(), "kph", null, 1, 1, RelativeSpeed.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(distanceActionEClass, DistanceAction.class, "DistanceAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDistanceAction_Distance(), this.getDistanceSetting(), null, "distance", null, 1, 1, DistanceAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(distanceSettingEClass, DistanceSetting.class, "DistanceSetting", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(relativeDistanceEClass, RelativeDistance.class, "RelativeDistance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRelativeDistance_Vehicle(), this.getVehicle(), null, "vehicle", null, 1, 1, RelativeDistance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRelativeDistance_Direction(), this.getDistanceDirection(), "direction", null, 0, 1, RelativeDistance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRelativeDistance_Meter(), ecorePackage.getEDouble(), "meter", null, 1, 1, RelativeDistance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(accelActionEClass, AccelAction.class, "AccelAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAccelAction_Accel(), this.getAccelerationSetting(), null, "accel", null, 1, 1, AccelAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(accelerationSettingEClass, AccelerationSetting.class, "AccelerationSetting", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(absoluteAccelerationEClass, AbsoluteAcceleration.class, "AbsoluteAcceleration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbsoluteAcceleration_Mpss(), ecorePackage.getEDouble(), "mpss", null, 1, 1, AbsoluteAcceleration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(conditionEClass, Condition.class, "Condition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCondition_Enabled(), ecorePackage.getEBoolean(), "enabled", null, 0, 1, Condition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(vehicleConditionEClass, VehicleCondition.class, "VehicleCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVehicleCondition_Vehicle(), this.getVehicle(), null, "vehicle", null, 1, 1, VehicleCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVehicleCondition_Conditions(), this.getVehicleDetailCondition(), null, "conditions", null, 1, -1, VehicleCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(vehicleDetailConditionEClass, VehicleDetailCondition.class, "VehicleDetailCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVehicleDetailCondition_Time(), this.getTimeCondition(), null, "time", null, 1, 1, VehicleDetailCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(timeConditionEClass, TimeCondition.class, "TimeCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(timeEvenOnceEClass, TimeEvenOnce.class, "TimeEvenOnce", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(timeDurationEClass, TimeDuration.class, "TimeDuration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTimeDuration_Msec(), ecorePackage.getEDouble(), "msec", null, 1, 1, TimeDuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueConditionEClass, ValueCondition.class, "ValueCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(simpleValueConditionEClass, SimpleValueCondition.class, "SimpleValueCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSimpleValueCondition_Value(), ecorePackage.getEDouble(), "value", null, 1, 1, SimpleValueCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSimpleValueCondition_Operator(), this.getComparisonOperator(), "operator", null, 1, 1, SimpleValueCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(rangeValueConditionEClass, RangeValueCondition.class, "RangeValueCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRangeValueCondition_UpperValue(), ecorePackage.getEDouble(), "upperValue", null, 1, 1, RangeValueCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRangeValueCondition_LowerValue(), ecorePackage.getEDouble(), "lowerValue", null, 1, 1, RangeValueCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(laneConditionEClass, LaneCondition.class, "LaneCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(absoluteLaneConditionEClass, AbsoluteLaneCondition.class, "AbsoluteLaneCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbsoluteLaneCondition_LaneId(), ecorePackage.getEInt(), "laneId", null, 1, 1, AbsoluteLaneCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbsoluteLaneCondition_Operator(), this.getComparisonOperator(), "operator", null, 1, 1, AbsoluteLaneCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(relativeLaneConditionEClass, RelativeLaneCondition.class, "RelativeLaneCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRelativeLaneCondition_Vehicle(), this.getVehicle(), null, "vehicle", null, 1, 1, RelativeLaneCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRelativeLaneCondition_Direction(), this.getLaneDirection(), "direction", null, 1, 1, RelativeLaneCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRelativeLaneCondition_Offset(), ecorePackage.getEInt(), "offset", "0", 1, 1, RelativeLaneCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(speedConditionEClass, SpeedCondition.class, "SpeedCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(absoluteSpeedConditionEClass, AbsoluteSpeedCondition.class, "AbsoluteSpeedCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAbsoluteSpeedCondition_Kph(), this.getValueCondition(), null, "kph", null, 1, 1, AbsoluteSpeedCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(relativeSpeedConditionEClass, RelativeSpeedCondition.class, "RelativeSpeedCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRelativeSpeedCondition_Vehicle(), this.getVehicle(), null, "vehicle", null, 1, 1, RelativeSpeedCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRelativeSpeedCondition_Kph(), this.getValueCondition(), null, "kph", null, 1, 1, RelativeSpeedCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(distanceConditionEClass, DistanceCondition.class, "DistanceCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(relativeDistanceConditionEClass, RelativeDistanceCondition.class, "RelativeDistanceCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRelativeDistanceCondition_Vehicle(), this.getVehicle(), null, "vehicle", null, 1, 1, RelativeDistanceCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRelativeDistanceCondition_Direction(), this.getDistanceDirection(), "direction", null, 1, 1, RelativeDistanceCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRelativeDistanceCondition_Meter(), this.getValueCondition(), null, "meter", null, 1, 1, RelativeDistanceCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(accelConditionEClass, AccelCondition.class, "AccelCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(absoluteAccelConditionEClass, AbsoluteAccelCondition.class, "AbsoluteAccelCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAbsoluteAccelCondition_Mpss(), this.getValueCondition(), null, "mpss", null, 1, 1, AbsoluteAccelCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(laneDirectionEEnum, LaneDirection.class, "LaneDirection");
        addEEnumLiteral(laneDirectionEEnum, LaneDirection.EQUAL);
        addEEnumLiteral(laneDirectionEEnum, LaneDirection.LEFT);
        addEEnumLiteral(laneDirectionEEnum, LaneDirection.RIGHT);

        initEEnum(distanceDirectionEEnum, DistanceDirection.class, "DistanceDirection");
        addEEnumLiteral(distanceDirectionEEnum, DistanceDirection.FORWARD);
        addEEnumLiteral(distanceDirectionEEnum, DistanceDirection.BACKWARD);

        initEEnum(comparisonOperatorEEnum, ComparisonOperator.class, "ComparisonOperator");
        addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.EQUAL);
        addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.NOT_EQUAL);
        addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.GREATER_THAN);
        addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.EQUAL_GREATER_THAN);
        addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.EQUAL_LESSER_THAN);
        addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.LESSER_THAN);
        addEEnumLiteral(comparisonOperatorEEnum, ComparisonOperator.RANGE);

        // Create resource
        createResource(eNS_URI);
    }

} //ScenarioPackageImpl
