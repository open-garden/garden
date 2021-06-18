/**
 */
package com.zipc.garden.model.bps.impl;

import com.google.gwt.user.client.rpc.IsSerializable;

import com.zipc.garden.model.arc.ARCPackage;

import com.zipc.garden.model.arc.impl.ARCPackageImpl;

import com.zipc.garden.model.bp.BPPackage;

import com.zipc.garden.model.bp.impl.BPPackageImpl;

import com.zipc.garden.model.bps.BPSDataflow;
import com.zipc.garden.model.bps.BPSEnablement;
import com.zipc.garden.model.bps.BPSFactory;
import com.zipc.garden.model.bps.BPSInstanceArc;
import com.zipc.garden.model.bps.BPSInstanceElement;
import com.zipc.garden.model.bps.BPSInstancePseudoStateType;
import com.zipc.garden.model.bps.BPSInstanceState;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;
import com.zipc.garden.model.bps.BPSInstanceTransition;
import com.zipc.garden.model.bps.BPSNSwitchOption;
import com.zipc.garden.model.bps.BPSOption;
import com.zipc.garden.model.bps.BPSPackage;
import com.zipc.garden.model.bps.BPSPathCombinationOption;
import com.zipc.garden.model.bps.BPSRoot;

import com.zipc.garden.model.bps.BPSStateCombinationOption;
import com.zipc.garden.model.bps.BPSVariable;
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
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class BPSPackageImpl extends EPackageImpl implements BPSPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bpsRootEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bpsOptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bpsStateCombinationOptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bpsnSwitchOptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bpsPathCombinationOptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bpsEnablementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bpsVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bpsDataflowEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bpsInstanceElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bpsInstanceArcEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bpsInstanceStateMachineEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bpsInstanceStateEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass bpsInstanceTransitionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum bpsInstancePseudoStateTypeEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package, if one already exists. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see com.zipc.garden.model.bps.BPSPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private BPSPackageImpl() {
        super(eNS_URI, BPSFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * <p>
     * This method is used to initialize {@link BPSPackage#eINSTANCE} when that field is accessed. Clients should not invoke it
     * directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static BPSPackage init() {
        if (isInited)
            return (BPSPackage) EPackage.Registry.INSTANCE.getEPackage(BPSPackage.eNS_URI);

        initializeRegistryHelpers();

        // Obtain or create and register package
        Object registeredBPSPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        BPSPackageImpl theBPSPackage = registeredBPSPackage instanceof BPSPackageImpl ? (BPSPackageImpl) registeredBPSPackage : new BPSPackageImpl();

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(COREPackage.eNS_URI);
        COREPackageImpl theCOREPackage = (COREPackageImpl) (registeredPackage instanceof COREPackageImpl ? registeredPackage : COREPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SCDPackage.eNS_URI);
        SCDPackageImpl theSCDPackage = (SCDPackageImpl) (registeredPackage instanceof SCDPackageImpl ? registeredPackage : SCDPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FMPackage.eNS_URI);
        FMPackageImpl theFMPackage = (FMPackageImpl) (registeredPackage instanceof FMPackageImpl ? registeredPackage : FMPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FMCPackage.eNS_URI);
        FMCPackageImpl theFMCPackage = (FMCPackageImpl) (registeredPackage instanceof FMCPackageImpl ? registeredPackage : FMCPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FMCSPackage.eNS_URI);
        FMCSPackageImpl theFMCSPackage = (FMCSPackageImpl) (registeredPackage instanceof FMCSPackageImpl ? registeredPackage : FMCSPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(TCPackage.eNS_URI);
        TCPackageImpl theTCPackage = (TCPackageImpl) (registeredPackage instanceof TCPackageImpl ? registeredPackage : TCPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ARCPackage.eNS_URI);
        ARCPackageImpl theARCPackage = (ARCPackageImpl) (registeredPackage instanceof ARCPackageImpl ? registeredPackage : ARCPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FSMPackage.eNS_URI);
        FSMPackageImpl theFSMPackage = (FSMPackageImpl) (registeredPackage instanceof FSMPackageImpl ? registeredPackage : FSMPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(TPSPackage.eNS_URI);
        TPSPackageImpl theTPSPackage = (TPSPackageImpl) (registeredPackage instanceof TPSPackageImpl ? registeredPackage : TPSPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(TPPackage.eNS_URI);
        TPPackageImpl theTPPackage = (TPPackageImpl) (registeredPackage instanceof TPPackageImpl ? registeredPackage : TPPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(BPPackage.eNS_URI);
        BPPackageImpl theBPPackage = (BPPackageImpl) (registeredPackage instanceof BPPackageImpl ? registeredPackage : BPPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(LGENPackage.eNS_URI);
        LGENPackageImpl theLGENPackage = (LGENPackageImpl) (registeredPackage instanceof LGENPackageImpl ? registeredPackage : LGENPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(LSCPackage.eNS_URI);
        LSCPackageImpl theLSCPackage = (LSCPackageImpl) (registeredPackage instanceof LSCPackageImpl ? registeredPackage : LSCPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CGENPackage.eNS_URI);
        CGENPackageImpl theCGENPackage = (CGENPackageImpl) (registeredPackage instanceof CGENPackageImpl ? registeredPackage : CGENPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CSCPackage.eNS_URI);
        CSCPackageImpl theCSCPackage = (CSCPackageImpl) (registeredPackage instanceof CSCPackageImpl ? registeredPackage : CSCPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CBPackage.eNS_URI);
        CBPackageImpl theCBPackage = (CBPackageImpl) (registeredPackage instanceof CBPackageImpl ? registeredPackage : CBPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SCSPackage.eNS_URI);
        SCSPackageImpl theSCSPackage = (SCSPackageImpl) (registeredPackage instanceof SCSPackageImpl ? registeredPackage : SCSPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SPQLPackage.eNS_URI);
        SPQLPackageImpl theSPQLPackage = (SPQLPackageImpl) (registeredPackage instanceof SPQLPackageImpl ? registeredPackage : SPQLPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CSCSPackage.eNS_URI);
        CSCSPackageImpl theCSCSPackage = (CSCSPackageImpl) (registeredPackage instanceof CSCSPackageImpl ? registeredPackage : CSCSPackage.eINSTANCE);

        // Create package meta-data objects
        theBPSPackage.createPackageContents();
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
        theBPSPackage.initializePackageContents();
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
        theBPSPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(BPSPackage.eNS_URI, theBPSPackage);
        return theBPSPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static void initializeRegistryHelpers() {
        Reflect.register(BPSRoot.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof BPSRoot;
            }

            public Object newArrayInstance(int size) {
                return new BPSRoot[size];
            }
        });
        Reflect.register(BPSOption.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof BPSOption;
            }

            public Object newArrayInstance(int size) {
                return new BPSOption[size];
            }
        });
        Reflect.register(BPSStateCombinationOption.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof BPSStateCombinationOption;
            }

            public Object newArrayInstance(int size) {
                return new BPSStateCombinationOption[size];
            }
        });
        Reflect.register(BPSNSwitchOption.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof BPSNSwitchOption;
            }

            public Object newArrayInstance(int size) {
                return new BPSNSwitchOption[size];
            }
        });
        Reflect.register(BPSPathCombinationOption.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof BPSPathCombinationOption;
            }

            public Object newArrayInstance(int size) {
                return new BPSPathCombinationOption[size];
            }
        });
        Reflect.register(BPSEnablement.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof BPSEnablement;
            }

            public Object newArrayInstance(int size) {
                return new BPSEnablement[size];
            }
        });
        Reflect.register(BPSVariable.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof BPSVariable;
            }

            public Object newArrayInstance(int size) {
                return new BPSVariable[size];
            }
        });
        Reflect.register(BPSDataflow.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof BPSDataflow;
            }

            public Object newArrayInstance(int size) {
                return new BPSDataflow[size];
            }
        });
        Reflect.register(BPSInstanceElement.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof BPSInstanceElement;
            }

            public Object newArrayInstance(int size) {
                return new BPSInstanceElement[size];
            }
        });
        Reflect.register(BPSInstanceArc.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof BPSInstanceArc;
            }

            public Object newArrayInstance(int size) {
                return new BPSInstanceArc[size];
            }
        });
        Reflect.register(BPSInstanceStateMachine.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof BPSInstanceStateMachine;
            }

            public Object newArrayInstance(int size) {
                return new BPSInstanceStateMachine[size];
            }
        });
        Reflect.register(BPSInstanceState.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof BPSInstanceState;
            }

            public Object newArrayInstance(int size) {
                return new BPSInstanceState[size];
            }
        });
        Reflect.register(BPSInstanceTransition.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof BPSInstanceTransition;
            }

            public Object newArrayInstance(int size) {
                return new BPSInstanceTransition[size];
            }
        });
        Reflect.register(BPSInstancePseudoStateType.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof BPSInstancePseudoStateType;
            }

            public Object newArrayInstance(int size) {
                return new BPSInstancePseudoStateType[size];
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static class WhiteList implements IsSerializable, EBasicWhiteList {
        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected BPSRoot bpsRoot;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected BPSOption bpsOption;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected BPSStateCombinationOption bpsStateCombinationOption;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected BPSNSwitchOption bpsnSwitchOption;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected BPSPathCombinationOption bpsPathCombinationOption;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected BPSEnablement bpsEnablement;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected BPSVariable bpsVariable;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected BPSDataflow bpsDataflow;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected BPSInstanceElement bpsInstanceElement;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected BPSInstanceArc bpsInstanceArc;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected BPSInstanceStateMachine bpsInstanceStateMachine;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected BPSInstanceState bpsInstanceState;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected BPSInstanceTransition bpsInstanceTransition;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected BPSInstancePseudoStateType bpsInstancePseudoStateType;

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPSRoot() {
        return bpsRootEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getBPSRoot__GetActiveOption() {
        return bpsRootEClass.getEOperations().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSRoot_ActiveOptionIndex() {
        return (EAttribute) bpsRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPSRoot_Options() {
        return (EReference) bpsRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPSRoot_InstanceArc() {
        return (EReference) bpsRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPSOption() {
        return bpsOptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSOption_TargetStateMachines() {
        return (EAttribute) bpsOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPSStateCombinationOption() {
        return bpsStateCombinationOptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPSNSwitchOption() {
        return bpsnSwitchOptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSNSwitchOption_TSMFileId() {
        return (EAttribute) bpsnSwitchOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSNSwitchOption_NSwitch() {
        return (EAttribute) bpsnSwitchOptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPSPathCombinationOption() {
        return bpsPathCombinationOptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSPathCombinationOption_PathLength() {
        return (EAttribute) bpsPathCombinationOptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPSEnablement() {
        return bpsEnablementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSEnablement_Enabled() {
        return (EAttribute) bpsEnablementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPSVariable() {
        return bpsVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSVariable_Name() {
        return (EAttribute) bpsVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSVariable_Type() {
        return (EAttribute) bpsVariableEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPSDataflow() {
        return bpsDataflowEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPSDataflow_Source() {
        return (EReference) bpsDataflowEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPSDataflow_Target() {
        return (EReference) bpsDataflowEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPSInstanceElement() {
        return bpsInstanceElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPSInstanceArc() {
        return bpsInstanceArcEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSInstanceArc_OriginalUuid() {
        return (EAttribute) bpsInstanceArcEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPSInstanceArc_StateMachines() {
        return (EReference) bpsInstanceArcEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPSInstanceArc_Dataflows() {
        return (EReference) bpsInstanceArcEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPSInstanceStateMachine() {
        return bpsInstanceStateMachineEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSInstanceStateMachine_OriginalName() {
        return (EAttribute) bpsInstanceStateMachineEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSInstanceStateMachine_OriginalUuid() {
        return (EAttribute) bpsInstanceStateMachineEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSInstanceStateMachine_EvalPriority() {
        return (EAttribute) bpsInstanceStateMachineEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPSInstanceStateMachine_InitialState() {
        return (EReference) bpsInstanceStateMachineEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPSInstanceStateMachine_States() {
        return (EReference) bpsInstanceStateMachineEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPSInstanceStateMachine_Variables() {
        return (EReference) bpsInstanceStateMachineEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPSInstanceState() {
        return bpsInstanceStateEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSInstanceState_OriginalName() {
        return (EAttribute) bpsInstanceStateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSInstanceState_Type() {
        return (EAttribute) bpsInstanceStateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPSInstanceState_Transitions() {
        return (EReference) bpsInstanceStateEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPSInstanceTransition() {
        return bpsInstanceTransitionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSInstanceTransition_Priority() {
        return (EAttribute) bpsInstanceTransitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSInstanceTransition_Trigger() {
        return (EAttribute) bpsInstanceTransitionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSInstanceTransition_Event() {
        return (EAttribute) bpsInstanceTransitionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSInstanceTransition_Condition() {
        return (EAttribute) bpsInstanceTransitionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPSInstanceTransition_Action() {
        return (EAttribute) bpsInstanceTransitionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPSInstanceTransition_Source() {
        return (EReference) bpsInstanceTransitionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPSInstanceTransition_Target() {
        return (EReference) bpsInstanceTransitionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getBPSInstancePseudoStateType() {
        return bpsInstancePseudoStateTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPSFactory getBPSFactory() {
        return (BPSFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        bpsRootEClass = createEClass(BPS_ROOT);
        createEAttribute(bpsRootEClass, BPS_ROOT__ACTIVE_OPTION_INDEX);
        createEReference(bpsRootEClass, BPS_ROOT__OPTIONS);
        createEReference(bpsRootEClass, BPS_ROOT__INSTANCE_ARC);
        createEOperation(bpsRootEClass, BPS_ROOT___GET_ACTIVE_OPTION);

        bpsOptionEClass = createEClass(BPS_OPTION);
        createEAttribute(bpsOptionEClass, BPS_OPTION__TARGET_STATE_MACHINES);

        bpsStateCombinationOptionEClass = createEClass(BPS_STATE_COMBINATION_OPTION);

        bpsnSwitchOptionEClass = createEClass(BPS_NSWITCH_OPTION);
        createEAttribute(bpsnSwitchOptionEClass, BPS_NSWITCH_OPTION__TSM_FILE_ID);
        createEAttribute(bpsnSwitchOptionEClass, BPS_NSWITCH_OPTION__NSWITCH);

        bpsPathCombinationOptionEClass = createEClass(BPS_PATH_COMBINATION_OPTION);
        createEAttribute(bpsPathCombinationOptionEClass, BPS_PATH_COMBINATION_OPTION__PATH_LENGTH);

        bpsEnablementEClass = createEClass(BPS_ENABLEMENT);
        createEAttribute(bpsEnablementEClass, BPS_ENABLEMENT__ENABLED);

        bpsVariableEClass = createEClass(BPS_VARIABLE);
        createEAttribute(bpsVariableEClass, BPS_VARIABLE__NAME);
        createEAttribute(bpsVariableEClass, BPS_VARIABLE__TYPE);

        bpsDataflowEClass = createEClass(BPS_DATAFLOW);
        createEReference(bpsDataflowEClass, BPS_DATAFLOW__SOURCE);
        createEReference(bpsDataflowEClass, BPS_DATAFLOW__TARGET);

        bpsInstanceElementEClass = createEClass(BPS_INSTANCE_ELEMENT);

        bpsInstanceArcEClass = createEClass(BPS_INSTANCE_ARC);
        createEAttribute(bpsInstanceArcEClass, BPS_INSTANCE_ARC__ORIGINAL_UUID);
        createEReference(bpsInstanceArcEClass, BPS_INSTANCE_ARC__STATE_MACHINES);
        createEReference(bpsInstanceArcEClass, BPS_INSTANCE_ARC__DATAFLOWS);

        bpsInstanceStateMachineEClass = createEClass(BPS_INSTANCE_STATE_MACHINE);
        createEAttribute(bpsInstanceStateMachineEClass, BPS_INSTANCE_STATE_MACHINE__ORIGINAL_NAME);
        createEAttribute(bpsInstanceStateMachineEClass, BPS_INSTANCE_STATE_MACHINE__ORIGINAL_UUID);
        createEAttribute(bpsInstanceStateMachineEClass, BPS_INSTANCE_STATE_MACHINE__EVAL_PRIORITY);
        createEReference(bpsInstanceStateMachineEClass, BPS_INSTANCE_STATE_MACHINE__INITIAL_STATE);
        createEReference(bpsInstanceStateMachineEClass, BPS_INSTANCE_STATE_MACHINE__STATES);
        createEReference(bpsInstanceStateMachineEClass, BPS_INSTANCE_STATE_MACHINE__VARIABLES);

        bpsInstanceStateEClass = createEClass(BPS_INSTANCE_STATE);
        createEAttribute(bpsInstanceStateEClass, BPS_INSTANCE_STATE__ORIGINAL_NAME);
        createEAttribute(bpsInstanceStateEClass, BPS_INSTANCE_STATE__TYPE);
        createEReference(bpsInstanceStateEClass, BPS_INSTANCE_STATE__TRANSITIONS);

        bpsInstanceTransitionEClass = createEClass(BPS_INSTANCE_TRANSITION);
        createEAttribute(bpsInstanceTransitionEClass, BPS_INSTANCE_TRANSITION__PRIORITY);
        createEAttribute(bpsInstanceTransitionEClass, BPS_INSTANCE_TRANSITION__TRIGGER);
        createEAttribute(bpsInstanceTransitionEClass, BPS_INSTANCE_TRANSITION__EVENT);
        createEAttribute(bpsInstanceTransitionEClass, BPS_INSTANCE_TRANSITION__CONDITION);
        createEAttribute(bpsInstanceTransitionEClass, BPS_INSTANCE_TRANSITION__ACTION);
        createEReference(bpsInstanceTransitionEClass, BPS_INSTANCE_TRANSITION__SOURCE);
        createEReference(bpsInstanceTransitionEClass, BPS_INSTANCE_TRANSITION__TARGET);

        // Create enums
        bpsInstancePseudoStateTypeEEnum = createEEnum(BPS_INSTANCE_PSEUDO_STATE_TYPE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any invocation
     * but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        COREPackage theCOREPackage = (COREPackage) EPackage.Registry.INSTANCE.getEPackage(COREPackage.eNS_URI);
        XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        bpsRootEClass.getESuperTypes().add(theCOREPackage.getAbstractRootElement());
        bpsStateCombinationOptionEClass.getESuperTypes().add(this.getBPSOption());
        bpsnSwitchOptionEClass.getESuperTypes().add(this.getBPSOption());
        bpsPathCombinationOptionEClass.getESuperTypes().add(this.getBPSOption());
        bpsInstanceArcEClass.getESuperTypes().add(this.getBPSInstanceElement());
        bpsInstanceStateMachineEClass.getESuperTypes().add(this.getBPSInstanceElement());
        bpsInstanceStateMachineEClass.getESuperTypes().add(this.getBPSEnablement());
        bpsInstanceStateEClass.getESuperTypes().add(this.getBPSInstanceElement());
        bpsInstanceStateEClass.getESuperTypes().add(this.getBPSEnablement());
        bpsInstanceTransitionEClass.getESuperTypes().add(this.getBPSInstanceElement());
        bpsInstanceTransitionEClass.getESuperTypes().add(this.getBPSEnablement());

        // Initialize classes, features, and operations; add parameters
        initEClass(bpsRootEClass, BPSRoot.class, "BPSRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBPSRoot_ActiveOptionIndex(), ecorePackage.getEInt(), "activeOptionIndex", null, 1, 1, BPSRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBPSRoot_Options(), this.getBPSOption(), null, "options", null, 1, -1, BPSRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBPSRoot_InstanceArc(), this.getBPSInstanceArc(), null, "instanceArc", null, 0, 1, BPSRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEOperation(getBPSRoot__GetActiveOption(), this.getBPSOption(), "getActiveOption", 1, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(bpsOptionEClass, BPSOption.class, "BPSOption", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBPSOption_TargetStateMachines(), ecorePackage.getEString(), "targetStateMachines", null, 1, -1, BPSOption.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bpsStateCombinationOptionEClass, BPSStateCombinationOption.class, "BPSStateCombinationOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(bpsnSwitchOptionEClass, BPSNSwitchOption.class, "BPSNSwitchOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBPSNSwitchOption_TSMFileId(), ecorePackage.getEString(), "TSMFileId", "", 1, 1, BPSNSwitchOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPSNSwitchOption_NSwitch(), ecorePackage.getEInt(), "nSwitch", null, 1, 1, BPSNSwitchOption.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bpsPathCombinationOptionEClass, BPSPathCombinationOption.class, "BPSPathCombinationOption", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBPSPathCombinationOption_PathLength(), ecorePackage.getEInt(), "pathLength", null, 1, 1, BPSPathCombinationOption.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bpsEnablementEClass, BPSEnablement.class, "BPSEnablement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBPSEnablement_Enabled(), ecorePackage.getEBoolean(), "enabled", "true", 0, 1, BPSEnablement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bpsVariableEClass, BPSVariable.class, "BPSVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBPSVariable_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, BPSVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPSVariable_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, BPSVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bpsDataflowEClass, BPSDataflow.class, "BPSDataflow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBPSDataflow_Source(), this.getBPSInstanceStateMachine(), null, "source", null, 0, 1, BPSDataflow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBPSDataflow_Target(), this.getBPSVariable(), null, "target", null, 0, 1, BPSDataflow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bpsInstanceElementEClass, BPSInstanceElement.class, "BPSInstanceElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(bpsInstanceArcEClass, BPSInstanceArc.class, "BPSInstanceArc", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBPSInstanceArc_OriginalUuid(), ecorePackage.getEString(), "originalUuid", null, 0, 1, BPSInstanceArc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBPSInstanceArc_StateMachines(), this.getBPSInstanceStateMachine(), null, "stateMachines", null, 0, -1, BPSInstanceArc.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBPSInstanceArc_Dataflows(), this.getBPSDataflow(), null, "dataflows", null, 0, -1, BPSInstanceArc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bpsInstanceStateMachineEClass, BPSInstanceStateMachine.class, "BPSInstanceStateMachine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBPSInstanceStateMachine_OriginalName(), ecorePackage.getEString(), "originalName", null, 0, 1, BPSInstanceStateMachine.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPSInstanceStateMachine_OriginalUuid(), ecorePackage.getEString(), "originalUuid", null, 0, 1, BPSInstanceStateMachine.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPSInstanceStateMachine_EvalPriority(), ecorePackage.getEInt(), "evalPriority", null, 1, 1, BPSInstanceStateMachine.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBPSInstanceStateMachine_InitialState(), this.getBPSInstanceState(), null, "initialState", null, 0, 1, BPSInstanceStateMachine.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBPSInstanceStateMachine_States(), this.getBPSInstanceState(), null, "states", null, 0, -1, BPSInstanceStateMachine.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBPSInstanceStateMachine_Variables(), this.getBPSVariable(), null, "variables", null, 0, -1, BPSInstanceStateMachine.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bpsInstanceStateEClass, BPSInstanceState.class, "BPSInstanceState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBPSInstanceState_OriginalName(), ecorePackage.getEString(), "originalName", null, 0, 1, BPSInstanceState.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPSInstanceState_Type(), this.getBPSInstancePseudoStateType(), "type", "NONE", 0, 1, BPSInstanceState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBPSInstanceState_Transitions(), this.getBPSInstanceTransition(), null, "transitions", null, 0, -1, BPSInstanceState.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bpsInstanceTransitionEClass, BPSInstanceTransition.class, "BPSInstanceTransition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBPSInstanceTransition_Priority(), ecorePackage.getEInt(), "priority", null, 0, 1, BPSInstanceTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPSInstanceTransition_Trigger(), theXMLTypePackage.getString(), "trigger", null, 0, 1, BPSInstanceTransition.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPSInstanceTransition_Event(), theXMLTypePackage.getString(), "event", null, 0, 1, BPSInstanceTransition.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPSInstanceTransition_Condition(), theXMLTypePackage.getString(), "condition", null, 0, 1, BPSInstanceTransition.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPSInstanceTransition_Action(), theXMLTypePackage.getString(), "action", null, 0, 1, BPSInstanceTransition.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBPSInstanceTransition_Source(), this.getBPSInstanceState(), null, "source", null, 0, 1, BPSInstanceTransition.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBPSInstanceTransition_Target(), this.getBPSInstanceState(), null, "target", null, 0, 1, BPSInstanceTransition.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(bpsInstancePseudoStateTypeEEnum, BPSInstancePseudoStateType.class, "BPSInstancePseudoStateType");
        addEEnumLiteral(bpsInstancePseudoStateTypeEEnum, BPSInstancePseudoStateType.JOIN);
        addEEnumLiteral(bpsInstancePseudoStateTypeEEnum, BPSInstancePseudoStateType.FORK);
        addEEnumLiteral(bpsInstancePseudoStateTypeEEnum, BPSInstancePseudoStateType.JUNCTION);
        addEEnumLiteral(bpsInstancePseudoStateTypeEEnum, BPSInstancePseudoStateType.CHOICE);
        addEEnumLiteral(bpsInstancePseudoStateTypeEEnum, BPSInstancePseudoStateType.ENTRY_POINT);
        addEEnumLiteral(bpsInstancePseudoStateTypeEEnum, BPSInstancePseudoStateType.EXIT_POINT);
        addEEnumLiteral(bpsInstancePseudoStateTypeEEnum, BPSInstancePseudoStateType.TERMINATE);
        addEEnumLiteral(bpsInstancePseudoStateTypeEEnum, BPSInstancePseudoStateType.INITIAL);
        addEEnumLiteral(bpsInstancePseudoStateTypeEEnum, BPSInstancePseudoStateType.FINAL);
        addEEnumLiteral(bpsInstancePseudoStateTypeEEnum, BPSInstancePseudoStateType.DEEP_HISTORY);
        addEEnumLiteral(bpsInstancePseudoStateTypeEEnum, BPSInstancePseudoStateType.SHALLOW_HISTORY);
        addEEnumLiteral(bpsInstancePseudoStateTypeEEnum, BPSInstancePseudoStateType.NONE);

        // Create resource
        createResource(eNS_URI);
    }

} // BPSPackageImpl
