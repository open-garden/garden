/**
 */
package com.zipc.garden.model.fsm.impl;

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

import com.zipc.garden.model.fsm.FSMDAbstractLine;
import com.zipc.garden.model.fsm.FSMDAction;
import com.zipc.garden.model.fsm.FSMDEvent;
import com.zipc.garden.model.fsm.FSMDLine;
import com.zipc.garden.model.fsm.FSMDLineType;
import com.zipc.garden.model.fsm.FSMDMemo;
import com.zipc.garden.model.fsm.FSMDPoint;
import com.zipc.garden.model.fsm.FSMDProperty;
import com.zipc.garden.model.fsm.FSMDPseudoStateType;
import com.zipc.garden.model.fsm.FSMDReferenceable;
import com.zipc.garden.model.fsm.FSMDRegion;
import com.zipc.garden.model.fsm.FSMDState;
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.model.fsm.FSMDTransition;
import com.zipc.garden.model.fsm.FSMDVariable;
import com.zipc.garden.model.fsm.FSMDVertex;
import com.zipc.garden.model.fsm.FSMFactory;
import com.zipc.garden.model.fsm.FSMPackage;

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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class FSMPackageImpl extends EPackageImpl implements FSMPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fsmdStateMachineEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fsmdVertexEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fsmdPropertyEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fsmdStateEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fsmdTransitionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fsmdReferenceableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fsmdMemoEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fsmdRegionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fsmdLineEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fsmdEventEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fsmdActionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fsmdAbstractLineEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fsmdPointEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fsmdVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum fsmdPseudoStateTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum fsmdLineTypeEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package, if one already exists. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see com.zipc.garden.model.fsm.FSMPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private FSMPackageImpl() {
        super(eNS_URI, FSMFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * <p>
     * This method is used to initialize {@link FSMPackage#eINSTANCE} when that field is accessed. Clients should not invoke it
     * directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static FSMPackage init() {
        if (isInited)
            return (FSMPackage) EPackage.Registry.INSTANCE.getEPackage(FSMPackage.eNS_URI);

        initializeRegistryHelpers();

        // Obtain or create and register package
        Object registeredFSMPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        FSMPackageImpl theFSMPackage = registeredFSMPackage instanceof FSMPackageImpl ? (FSMPackageImpl) registeredFSMPackage : new FSMPackageImpl();

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
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(TPSPackage.eNS_URI);
        TPSPackageImpl theTPSPackage = (TPSPackageImpl) (registeredPackage instanceof TPSPackageImpl ? registeredPackage : TPSPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(TPPackage.eNS_URI);
        TPPackageImpl theTPPackage = (TPPackageImpl) (registeredPackage instanceof TPPackageImpl ? registeredPackage : TPPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(BPSPackage.eNS_URI);
        BPSPackageImpl theBPSPackage = (BPSPackageImpl) (registeredPackage instanceof BPSPackageImpl ? registeredPackage : BPSPackage.eINSTANCE);
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
        theFSMPackage.createPackageContents();
        theCOREPackage.createPackageContents();
        theSCDPackage.createPackageContents();
        theFMPackage.createPackageContents();
        theFMCPackage.createPackageContents();
        theFMCSPackage.createPackageContents();
        theTCPackage.createPackageContents();
        theARCPackage.createPackageContents();
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
        theFSMPackage.initializePackageContents();
        theCOREPackage.initializePackageContents();
        theSCDPackage.initializePackageContents();
        theFMPackage.initializePackageContents();
        theFMCPackage.initializePackageContents();
        theFMCSPackage.initializePackageContents();
        theTCPackage.initializePackageContents();
        theARCPackage.initializePackageContents();
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
        theFSMPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(FSMPackage.eNS_URI, theFSMPackage);
        return theFSMPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static void initializeRegistryHelpers() {
        Reflect.register(FSMDStateMachine.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDStateMachine;
            }

            public Object newArrayInstance(int size) {
                return new FSMDStateMachine[size];
            }
        });
        Reflect.register(FSMDVertex.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDVertex;
            }

            public Object newArrayInstance(int size) {
                return new FSMDVertex[size];
            }
        });
        Reflect.register(FSMDProperty.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDProperty;
            }

            public Object newArrayInstance(int size) {
                return new FSMDProperty[size];
            }
        });
        Reflect.register(FSMDState.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDState;
            }

            public Object newArrayInstance(int size) {
                return new FSMDState[size];
            }
        });
        Reflect.register(FSMDTransition.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDTransition;
            }

            public Object newArrayInstance(int size) {
                return new FSMDTransition[size];
            }
        });
        Reflect.register(FSMDReferenceable.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDReferenceable;
            }

            public Object newArrayInstance(int size) {
                return new FSMDReferenceable[size];
            }
        });
        Reflect.register(FSMDMemo.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDMemo;
            }

            public Object newArrayInstance(int size) {
                return new FSMDMemo[size];
            }
        });
        Reflect.register(FSMDRegion.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDRegion;
            }

            public Object newArrayInstance(int size) {
                return new FSMDRegion[size];
            }
        });
        Reflect.register(FSMDLine.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDLine;
            }

            public Object newArrayInstance(int size) {
                return new FSMDLine[size];
            }
        });
        Reflect.register(FSMDEvent.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDEvent;
            }

            public Object newArrayInstance(int size) {
                return new FSMDEvent[size];
            }
        });
        Reflect.register(FSMDAction.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDAction;
            }

            public Object newArrayInstance(int size) {
                return new FSMDAction[size];
            }
        });
        Reflect.register(FSMDAbstractLine.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDAbstractLine;
            }

            public Object newArrayInstance(int size) {
                return new FSMDAbstractLine[size];
            }
        });
        Reflect.register(FSMDPoint.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDPoint;
            }

            public Object newArrayInstance(int size) {
                return new FSMDPoint[size];
            }
        });
        Reflect.register(FSMDVariable.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDVariable;
            }

            public Object newArrayInstance(int size) {
                return new FSMDVariable[size];
            }
        });
        Reflect.register(FSMDPseudoStateType.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDPseudoStateType;
            }

            public Object newArrayInstance(int size) {
                return new FSMDPseudoStateType[size];
            }
        });
        Reflect.register(FSMDLineType.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FSMDLineType;
            }

            public Object newArrayInstance(int size) {
                return new FSMDLineType[size];
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
        protected FSMDStateMachine fsmdStateMachine;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDVertex fsmdVertex;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDProperty fsmdProperty;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDState fsmdState;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDTransition fsmdTransition;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDReferenceable fsmdReferenceable;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDMemo fsmdMemo;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDRegion fsmdRegion;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDLine fsmdLine;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDEvent fsmdEvent;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDAction fsmdAction;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDAbstractLine fsmdAbstractLine;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDPoint fsmdPoint;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDVariable fsmdVariable;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDPseudoStateType fsmdPseudoStateType;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FSMDLineType fsmdLineType;

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFSMDStateMachine() {
        return fsmdStateMachineEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDStateMachine_Properties() {
        return (EReference) fsmdStateMachineEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDStateMachine_Transitions() {
        return (EReference) fsmdStateMachineEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDStateMachine_Memos() {
        return (EReference) fsmdStateMachineEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDStateMachine_Regions() {
        return (EReference) fsmdStateMachineEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDStateMachine_Fmsdevent() {
        return (EReference) fsmdStateMachineEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDStateMachine_Actions() {
        return (EReference) fsmdStateMachineEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDStateMachine_Lines() {
        return (EReference) fsmdStateMachineEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDStateMachine_Variables() {
        return (EReference) fsmdStateMachineEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDStateMachine_ManhattanMode() {
        return (EAttribute) fsmdStateMachineEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFSMDVertex() {
        return fsmdVertexEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDVertex_Top() {
        return (EAttribute) fsmdVertexEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDVertex_Left() {
        return (EAttribute) fsmdVertexEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDVertex_Height() {
        return (EAttribute) fsmdVertexEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDVertex_Width() {
        return (EAttribute) fsmdVertexEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDVertex_Properties() {
        return (EReference) fsmdVertexEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDVertex_Background() {
        return (EAttribute) fsmdVertexEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDVertex_Foreground() {
        return (EAttribute) fsmdVertexEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDVertex_IncomingLine() {
        return (EReference) fsmdVertexEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDVertex_OutgoingLine() {
        return (EReference) fsmdVertexEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFSMDProperty() {
        return fsmdPropertyEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDProperty_Key() {
        return (EAttribute) fsmdPropertyEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDProperty_Value() {
        return (EAttribute) fsmdPropertyEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFSMDState() {
        return fsmdStateEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDState_Ref() {
        return (EAttribute) fsmdStateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDState_RefName() {
        return (EAttribute) fsmdStateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDState_Name() {
        return (EAttribute) fsmdStateEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDState_Entry() {
        return (EAttribute) fsmdStateEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDState_Exit() {
        return (EAttribute) fsmdStateEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDState_Do() {
        return (EAttribute) fsmdStateEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDState_Regions() {
        return (EReference) fsmdStateEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDState_Type() {
        return (EAttribute) fsmdStateEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDState_IncomingTransition() {
        return (EReference) fsmdStateEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDState_OutgoingTransition() {
        return (EReference) fsmdStateEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDState_Refuuid() {
        return (EAttribute) fsmdStateEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFSMDTransition() {
        return fsmdTransitionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDTransition_Trigger() {
        return (EAttribute) fsmdTransitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDTransition_Event() {
        return (EAttribute) fsmdTransitionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDTransition_Condition() {
        return (EAttribute) fsmdTransitionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDTransition_Action() {
        return (EAttribute) fsmdTransitionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDTransition_X() {
        return (EAttribute) fsmdTransitionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDTransition_Y() {
        return (EAttribute) fsmdTransitionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDTransition_Target() {
        return (EReference) fsmdTransitionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDTransition_Source() {
        return (EReference) fsmdTransitionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDTransition_Priority() {
        return (EAttribute) fsmdTransitionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFSMDReferenceable() {
        return fsmdReferenceableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDReferenceable_Id() {
        return (EAttribute) fsmdReferenceableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDReferenceable_Uuid() {
        return (EAttribute) fsmdReferenceableEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFSMDMemo() {
        return fsmdMemoEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDMemo_Text() {
        return (EAttribute) fsmdMemoEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFSMDRegion() {
        return fsmdRegionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDRegion_States() {
        return (EReference) fsmdRegionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFSMDLine() {
        return fsmdLineEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDLine_Source() {
        return (EReference) fsmdLineEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFSMDLine_Target() {
        return (EReference) fsmdLineEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFSMDEvent() {
        return fsmdEventEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDEvent_Name() {
        return (EAttribute) fsmdEventEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFSMDAction() {
        return fsmdActionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDAction_Text() {
        return (EAttribute) fsmdActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFSMDAbstractLine() {
        return fsmdAbstractLineEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFSMDPoint() {
        return fsmdPointEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFSMDVariable() {
        return fsmdVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDVariable_Name() {
        return (EAttribute) fsmdVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFSMDVariable_Type() {
        return (EAttribute) fsmdVariableEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getFSMDPseudoStateType() {
        return fsmdPseudoStateTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getFSMDLineType() {
        return fsmdLineTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FSMFactory getFSMFactory() {
        return (FSMFactory) getEFactoryInstance();
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
        fsmdStateMachineEClass = createEClass(FSM_DSTATE_MACHINE);
        createEReference(fsmdStateMachineEClass, FSM_DSTATE_MACHINE__PROPERTIES);
        createEReference(fsmdStateMachineEClass, FSM_DSTATE_MACHINE__TRANSITIONS);
        createEReference(fsmdStateMachineEClass, FSM_DSTATE_MACHINE__MEMOS);
        createEReference(fsmdStateMachineEClass, FSM_DSTATE_MACHINE__REGIONS);
        createEReference(fsmdStateMachineEClass, FSM_DSTATE_MACHINE__FMSDEVENT);
        createEReference(fsmdStateMachineEClass, FSM_DSTATE_MACHINE__ACTIONS);
        createEReference(fsmdStateMachineEClass, FSM_DSTATE_MACHINE__LINES);
        createEReference(fsmdStateMachineEClass, FSM_DSTATE_MACHINE__VARIABLES);
        createEAttribute(fsmdStateMachineEClass, FSM_DSTATE_MACHINE__MANHATTAN_MODE);

        fsmdVertexEClass = createEClass(FSM_DVERTEX);
        createEAttribute(fsmdVertexEClass, FSM_DVERTEX__TOP);
        createEAttribute(fsmdVertexEClass, FSM_DVERTEX__LEFT);
        createEAttribute(fsmdVertexEClass, FSM_DVERTEX__HEIGHT);
        createEAttribute(fsmdVertexEClass, FSM_DVERTEX__WIDTH);
        createEReference(fsmdVertexEClass, FSM_DVERTEX__PROPERTIES);
        createEAttribute(fsmdVertexEClass, FSM_DVERTEX__BACKGROUND);
        createEAttribute(fsmdVertexEClass, FSM_DVERTEX__FOREGROUND);
        createEReference(fsmdVertexEClass, FSM_DVERTEX__INCOMING_LINE);
        createEReference(fsmdVertexEClass, FSM_DVERTEX__OUTGOING_LINE);

        fsmdPropertyEClass = createEClass(FSM_DPROPERTY);
        createEAttribute(fsmdPropertyEClass, FSM_DPROPERTY__KEY);
        createEAttribute(fsmdPropertyEClass, FSM_DPROPERTY__VALUE);

        fsmdStateEClass = createEClass(FSM_DSTATE);
        createEAttribute(fsmdStateEClass, FSM_DSTATE__REF);
        createEAttribute(fsmdStateEClass, FSM_DSTATE__REF_NAME);
        createEAttribute(fsmdStateEClass, FSM_DSTATE__NAME);
        createEAttribute(fsmdStateEClass, FSM_DSTATE__ENTRY);
        createEAttribute(fsmdStateEClass, FSM_DSTATE__EXIT);
        createEAttribute(fsmdStateEClass, FSM_DSTATE__DO);
        createEReference(fsmdStateEClass, FSM_DSTATE__REGIONS);
        createEAttribute(fsmdStateEClass, FSM_DSTATE__TYPE);
        createEReference(fsmdStateEClass, FSM_DSTATE__INCOMING_TRANSITION);
        createEReference(fsmdStateEClass, FSM_DSTATE__OUTGOING_TRANSITION);
        createEAttribute(fsmdStateEClass, FSM_DSTATE__REFUUID);

        fsmdTransitionEClass = createEClass(FSM_DTRANSITION);
        createEAttribute(fsmdTransitionEClass, FSM_DTRANSITION__TRIGGER);
        createEAttribute(fsmdTransitionEClass, FSM_DTRANSITION__EVENT);
        createEAttribute(fsmdTransitionEClass, FSM_DTRANSITION__CONDITION);
        createEAttribute(fsmdTransitionEClass, FSM_DTRANSITION__ACTION);
        createEAttribute(fsmdTransitionEClass, FSM_DTRANSITION__X);
        createEAttribute(fsmdTransitionEClass, FSM_DTRANSITION__Y);
        createEReference(fsmdTransitionEClass, FSM_DTRANSITION__TARGET);
        createEReference(fsmdTransitionEClass, FSM_DTRANSITION__SOURCE);
        createEAttribute(fsmdTransitionEClass, FSM_DTRANSITION__PRIORITY);

        fsmdReferenceableEClass = createEClass(FSM_DREFERENCEABLE);
        createEAttribute(fsmdReferenceableEClass, FSM_DREFERENCEABLE__ID);
        createEAttribute(fsmdReferenceableEClass, FSM_DREFERENCEABLE__UUID);

        fsmdMemoEClass = createEClass(FSM_DMEMO);
        createEAttribute(fsmdMemoEClass, FSM_DMEMO__TEXT);

        fsmdRegionEClass = createEClass(FSM_DREGION);
        createEReference(fsmdRegionEClass, FSM_DREGION__STATES);

        fsmdLineEClass = createEClass(FSM_DLINE);
        createEReference(fsmdLineEClass, FSM_DLINE__SOURCE);
        createEReference(fsmdLineEClass, FSM_DLINE__TARGET);

        fsmdEventEClass = createEClass(FSM_DEVENT);
        createEAttribute(fsmdEventEClass, FSM_DEVENT__NAME);

        fsmdActionEClass = createEClass(FSM_DACTION);
        createEAttribute(fsmdActionEClass, FSM_DACTION__TEXT);

        fsmdAbstractLineEClass = createEClass(FSM_DABSTRACT_LINE);

        fsmdPointEClass = createEClass(FSM_DPOINT);

        fsmdVariableEClass = createEClass(FSM_DVARIABLE);
        createEAttribute(fsmdVariableEClass, FSM_DVARIABLE__NAME);
        createEAttribute(fsmdVariableEClass, FSM_DVARIABLE__TYPE);

        // Create enums
        fsmdPseudoStateTypeEEnum = createEEnum(FSM_DPSEUDO_STATE_TYPE);
        fsmdLineTypeEEnum = createEEnum(FSM_DLINE_TYPE);
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
        fsmdStateMachineEClass.getESuperTypes().add(theCOREPackage.getAbstractRootElement());
        fsmdStateMachineEClass.getESuperTypes().add(theCOREPackage.getAbstractDiagram());
        fsmdVertexEClass.getESuperTypes().add(this.getFSMDReferenceable());
        fsmdStateEClass.getESuperTypes().add(this.getFSMDVertex());
        fsmdTransitionEClass.getESuperTypes().add(this.getFSMDReferenceable());
        fsmdTransitionEClass.getESuperTypes().add(this.getFSMDAbstractLine());
        fsmdMemoEClass.getESuperTypes().add(this.getFSMDVertex());
        fsmdLineEClass.getESuperTypes().add(this.getFSMDAbstractLine());
        fsmdAbstractLineEClass.getESuperTypes().add(theCOREPackage.getAbstractLine());
        fsmdPointEClass.getESuperTypes().add(theCOREPackage.getAbstractPoint());

        // Initialize classes, features, and operations; add parameters
        initEClass(fsmdStateMachineEClass, FSMDStateMachine.class, "FSMDStateMachine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFSMDStateMachine_Properties(), this.getFSMDProperty(), null, "properties", null, 0, -1, FSMDStateMachine.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDStateMachine_Transitions(), this.getFSMDTransition(), null, "transitions", null, 0, -1, FSMDStateMachine.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDStateMachine_Memos(), this.getFSMDMemo(), null, "memos", null, 0, -1, FSMDStateMachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDStateMachine_Regions(), this.getFSMDRegion(), null, "regions", null, 0, -1, FSMDStateMachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDStateMachine_Fmsdevent(), this.getFSMDEvent(), null, "fmsdevent", null, 0, -1, FSMDStateMachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDStateMachine_Actions(), this.getFSMDAction(), null, "actions", null, 0, -1, FSMDStateMachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDStateMachine_Lines(), this.getFSMDLine(), null, "lines", null, 0, -1, FSMDStateMachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDStateMachine_Variables(), this.getFSMDVariable(), null, "variables", null, 0, -1, FSMDStateMachine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDStateMachine_ManhattanMode(), theXMLTypePackage.getInt(), "manhattanMode", null, 0, 1, FSMDStateMachine.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fsmdVertexEClass, FSMDVertex.class, "FSMDVertex", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFSMDVertex_Top(), ecorePackage.getEInt(), "top", null, 0, 1, FSMDVertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDVertex_Left(), ecorePackage.getEInt(), "left", null, 0, 1, FSMDVertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDVertex_Height(), ecorePackage.getEInt(), "height", null, 0, 1, FSMDVertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDVertex_Width(), ecorePackage.getEInt(), "width", null, 0, 1, FSMDVertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDVertex_Properties(), this.getFSMDProperty(), null, "properties", null, 0, -1, FSMDVertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDVertex_Background(), theXMLTypePackage.getString(), "background", null, 0, 1, FSMDVertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDVertex_Foreground(), theXMLTypePackage.getString(), "foreground", null, 0, 1, FSMDVertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDVertex_IncomingLine(), this.getFSMDLine(), this.getFSMDLine_Target(), "incomingLine", null, 0, -1, FSMDVertex.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDVertex_OutgoingLine(), this.getFSMDLine(), this.getFSMDLine_Source(), "outgoingLine", null, 0, 1, FSMDVertex.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fsmdPropertyEClass, FSMDProperty.class, "FSMDProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFSMDProperty_Key(), ecorePackage.getEString(), "key", null, 0, 1, FSMDProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDProperty_Value(), ecorePackage.getEString(), "value", null, 0, 1, FSMDProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fsmdStateEClass, FSMDState.class, "FSMDState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFSMDState_Ref(), ecorePackage.getELong(), "ref", null, 0, 1, FSMDState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDState_RefName(), ecorePackage.getEString(), "refName", null, 0, 1, FSMDState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDState_Name(), ecorePackage.getEString(), "name", null, 0, 1, FSMDState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDState_Entry(), theXMLTypePackage.getString(), "entry", null, 0, 1, FSMDState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDState_Exit(), theXMLTypePackage.getString(), "exit", null, 0, 1, FSMDState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDState_Do(), theXMLTypePackage.getString(), "do", null, 0, 1, FSMDState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDState_Regions(), this.getFSMDRegion(), null, "regions", null, 0, -1, FSMDState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDState_Type(), this.getFSMDPseudoStateType(), "type", "NONE", 0, 1, FSMDState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDState_IncomingTransition(), this.getFSMDTransition(), this.getFSMDTransition_Target(), "incomingTransition", null, 0, -1, FSMDState.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDState_OutgoingTransition(), this.getFSMDTransition(), this.getFSMDTransition_Source(), "outgoingTransition", null, 0, -1, FSMDState.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDState_Refuuid(), theXMLTypePackage.getString(), "refuuid", null, 0, 1, FSMDState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fsmdTransitionEClass, FSMDTransition.class, "FSMDTransition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFSMDTransition_Trigger(), theXMLTypePackage.getString(), "trigger", null, 0, 1, FSMDTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDTransition_Event(), theXMLTypePackage.getString(), "event", null, 0, 1, FSMDTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDTransition_Condition(), theXMLTypePackage.getString(), "condition", null, 0, 1, FSMDTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDTransition_Action(), theXMLTypePackage.getString(), "action", null, 0, 1, FSMDTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDTransition_X(), ecorePackage.getEInt(), "x", null, 0, 1, FSMDTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDTransition_Y(), ecorePackage.getEInt(), "y", null, 0, 1, FSMDTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDTransition_Target(), this.getFSMDState(), this.getFSMDState_IncomingTransition(), "target", null, 0, 1, FSMDTransition.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDTransition_Source(), this.getFSMDState(), this.getFSMDState_OutgoingTransition(), "source", null, 0, 1, FSMDTransition.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDTransition_Priority(), ecorePackage.getEInt(), "priority", null, 0, 1, FSMDTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fsmdReferenceableEClass, FSMDReferenceable.class, "FSMDReferenceable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFSMDReferenceable_Id(), ecorePackage.getELong(), "id", null, 0, 1, FSMDReferenceable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDReferenceable_Uuid(), theXMLTypePackage.getString(), "uuid", null, 0, 1, FSMDReferenceable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fsmdMemoEClass, FSMDMemo.class, "FSMDMemo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFSMDMemo_Text(), ecorePackage.getEString(), "text", null, 0, 1, FSMDMemo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fsmdRegionEClass, FSMDRegion.class, "FSMDRegion", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFSMDRegion_States(), this.getFSMDState(), null, "states", null, 0, -1, FSMDRegion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fsmdLineEClass, FSMDLine.class, "FSMDLine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFSMDLine_Source(), this.getFSMDVertex(), this.getFSMDVertex_OutgoingLine(), "source", null, 0, 1, FSMDLine.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFSMDLine_Target(), this.getFSMDVertex(), this.getFSMDVertex_IncomingLine(), "target", null, 0, 1, FSMDLine.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fsmdEventEClass, FSMDEvent.class, "FSMDEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFSMDEvent_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, FSMDEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fsmdActionEClass, FSMDAction.class, "FSMDAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFSMDAction_Text(), theXMLTypePackage.getString(), "text", null, 0, 1, FSMDAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fsmdAbstractLineEClass, FSMDAbstractLine.class, "FSMDAbstractLine", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(fsmdPointEClass, FSMDPoint.class, "FSMDPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(fsmdVariableEClass, FSMDVariable.class, "FSMDVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFSMDVariable_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, FSMDVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFSMDVariable_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, FSMDVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(fsmdPseudoStateTypeEEnum, FSMDPseudoStateType.class, "FSMDPseudoStateType");
        addEEnumLiteral(fsmdPseudoStateTypeEEnum, FSMDPseudoStateType.JOIN);
        addEEnumLiteral(fsmdPseudoStateTypeEEnum, FSMDPseudoStateType.FORK);
        addEEnumLiteral(fsmdPseudoStateTypeEEnum, FSMDPseudoStateType.JUNCTION);
        addEEnumLiteral(fsmdPseudoStateTypeEEnum, FSMDPseudoStateType.CHOICE);
        addEEnumLiteral(fsmdPseudoStateTypeEEnum, FSMDPseudoStateType.ENTRY_POINT);
        addEEnumLiteral(fsmdPseudoStateTypeEEnum, FSMDPseudoStateType.EXIT_POINT);
        addEEnumLiteral(fsmdPseudoStateTypeEEnum, FSMDPseudoStateType.TERMINATE);
        addEEnumLiteral(fsmdPseudoStateTypeEEnum, FSMDPseudoStateType.INITIAL);
        addEEnumLiteral(fsmdPseudoStateTypeEEnum, FSMDPseudoStateType.FINAL);
        addEEnumLiteral(fsmdPseudoStateTypeEEnum, FSMDPseudoStateType.DEEP_HISTORY);
        addEEnumLiteral(fsmdPseudoStateTypeEEnum, FSMDPseudoStateType.SHALLOW_HISTORY);
        addEEnumLiteral(fsmdPseudoStateTypeEEnum, FSMDPseudoStateType.NONE);

        initEEnum(fsmdLineTypeEEnum, FSMDLineType.class, "FSMDLineType");
        addEEnumLiteral(fsmdLineTypeEEnum, FSMDLineType.SIMPLE);
        addEEnumLiteral(fsmdLineTypeEEnum, FSMDLineType.MANHATTAN);

        // Create resource
        createResource(eNS_URI);
    }

} // FSMPackageImpl
