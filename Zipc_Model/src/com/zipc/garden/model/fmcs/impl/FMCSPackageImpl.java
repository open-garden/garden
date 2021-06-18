/**
 */
package com.zipc.garden.model.fmcs.impl;

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
import com.zipc.garden.model.fmcs.FMCSAndExpression;
import com.zipc.garden.model.fmcs.FMCSConstraint;
import com.zipc.garden.model.fmcs.FMCSExpression;
import com.zipc.garden.model.fmcs.FMCSFactory;
import com.zipc.garden.model.fmcs.FMCSImpliesExpression;
import com.zipc.garden.model.fmcs.FMCSMutexExpression;
import com.zipc.garden.model.fmcs.FMCSNotExpression;
import com.zipc.garden.model.fmcs.FMCSODElement;
import com.zipc.garden.model.fmcs.FMCSOrExpression;
import com.zipc.garden.model.fmcs.FMCSPackage;
import com.zipc.garden.model.fmcs.FMCSRemovesExpression;
import com.zipc.garden.model.fmcs.FMCSRoot;
import com.zipc.garden.model.fmcs.FMCSSelectExpression;

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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class FMCSPackageImpl extends EPackageImpl implements FMCSPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmcsRootEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmcsConstraintEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmcsExpressionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmcsImpliesExpressionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmcsOrExpressionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmcsAndExpressionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmcsNotExpressionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmcsSelectExpressionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmcsodElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmcsMutexExpressionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmcsRemovesExpressionEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package, if one already exists. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see com.zipc.garden.model.fmcs.FMCSPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private FMCSPackageImpl() {
        super(eNS_URI, FMCSFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * <p>
     * This method is used to initialize {@link FMCSPackage#eINSTANCE} when that field is accessed. Clients should not invoke it
     * directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static FMCSPackage init() {
        if (isInited)
            return (FMCSPackage) EPackage.Registry.INSTANCE.getEPackage(FMCSPackage.eNS_URI);

        initializeRegistryHelpers();

        // Obtain or create and register package
        Object registeredFMCSPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        FMCSPackageImpl theFMCSPackage = registeredFMCSPackage instanceof FMCSPackageImpl ? (FMCSPackageImpl) registeredFMCSPackage : new FMCSPackageImpl();

        isInited = true;

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(COREPackage.eNS_URI);
        COREPackageImpl theCOREPackage = (COREPackageImpl) (registeredPackage instanceof COREPackageImpl ? registeredPackage : COREPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SCDPackage.eNS_URI);
        SCDPackageImpl theSCDPackage = (SCDPackageImpl) (registeredPackage instanceof SCDPackageImpl ? registeredPackage : SCDPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FMPackage.eNS_URI);
        FMPackageImpl theFMPackage = (FMPackageImpl) (registeredPackage instanceof FMPackageImpl ? registeredPackage : FMPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FMCPackage.eNS_URI);
        FMCPackageImpl theFMCPackage = (FMCPackageImpl) (registeredPackage instanceof FMCPackageImpl ? registeredPackage : FMCPackage.eINSTANCE);
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
        theFMCSPackage.createPackageContents();
        theCOREPackage.createPackageContents();
        theSCDPackage.createPackageContents();
        theFMPackage.createPackageContents();
        theFMCPackage.createPackageContents();
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
        theFMCSPackage.initializePackageContents();
        theCOREPackage.initializePackageContents();
        theSCDPackage.initializePackageContents();
        theFMPackage.initializePackageContents();
        theFMCPackage.initializePackageContents();
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
        theFMCSPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(FMCSPackage.eNS_URI, theFMCSPackage);
        return theFMCSPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static void initializeRegistryHelpers() {
        Reflect.register(FMCSRoot.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMCSRoot;
            }

            public Object newArrayInstance(int size) {
                return new FMCSRoot[size];
            }
        });
        Reflect.register(FMCSConstraint.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMCSConstraint;
            }

            public Object newArrayInstance(int size) {
                return new FMCSConstraint[size];
            }
        });
        Reflect.register(FMCSExpression.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMCSExpression;
            }

            public Object newArrayInstance(int size) {
                return new FMCSExpression[size];
            }
        });
        Reflect.register(FMCSNotExpression.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMCSNotExpression;
            }

            public Object newArrayInstance(int size) {
                return new FMCSNotExpression[size];
            }
        });
        Reflect.register(FMCSAndExpression.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMCSAndExpression;
            }

            public Object newArrayInstance(int size) {
                return new FMCSAndExpression[size];
            }
        });
        Reflect.register(FMCSOrExpression.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMCSOrExpression;
            }

            public Object newArrayInstance(int size) {
                return new FMCSOrExpression[size];
            }
        });
        Reflect.register(FMCSImpliesExpression.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMCSImpliesExpression;
            }

            public Object newArrayInstance(int size) {
                return new FMCSImpliesExpression[size];
            }
        });
        Reflect.register(FMCSSelectExpression.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMCSSelectExpression;
            }

            public Object newArrayInstance(int size) {
                return new FMCSSelectExpression[size];
            }
        });
        Reflect.register(FMCSODElement.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMCSODElement;
            }

            public Object newArrayInstance(int size) {
                return new FMCSODElement[size];
            }
        });
        Reflect.register(FMCSRemovesExpression.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMCSRemovesExpression;
            }

            public Object newArrayInstance(int size) {
                return new FMCSRemovesExpression[size];
            }
        });
        Reflect.register(FMCSMutexExpression.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMCSMutexExpression;
            }

            public Object newArrayInstance(int size) {
                return new FMCSMutexExpression[size];
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
        protected FMCSRoot fmcsRoot;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMCSConstraint fmcsConstraint;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMCSExpression fmcsExpression;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMCSImpliesExpression fmcsImpliesExpression;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMCSOrExpression fmcsOrExpression;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMCSAndExpression fmcsAndExpression;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMCSNotExpression fmcsNotExpression;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMCSSelectExpression fmcsSelectExpression;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMCSODElement fmcsodElement;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMCSMutexExpression fmcsMutexExpression;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMCSRemovesExpression fmcsRemovesExpression;

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMCSRoot() {
        return fmcsRootEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCSRoot_Constraints() {
        return (EReference) fmcsRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMCSConstraint() {
        return fmcsConstraintEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCSConstraint_Expression() {
        return (EReference) fmcsConstraintEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCSConstraint_Ref() {
        return (EReference) fmcsConstraintEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMCSExpression() {
        return fmcsExpressionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMCSImpliesExpression() {
        return fmcsImpliesExpressionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCSImpliesExpression_LeftExpression() {
        return (EReference) fmcsImpliesExpressionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCSImpliesExpression_RightExpression() {
        return (EReference) fmcsImpliesExpressionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMCSOrExpression() {
        return fmcsOrExpressionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCSOrExpression_Expressions() {
        return (EReference) fmcsOrExpressionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMCSAndExpression() {
        return fmcsAndExpressionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCSAndExpression_Expressions() {
        return (EReference) fmcsAndExpressionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMCSNotExpression() {
        return fmcsNotExpressionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCSNotExpression_Expression() {
        return (EReference) fmcsNotExpressionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMCSSelectExpression() {
        return fmcsSelectExpressionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCSSelectExpression_OdElement() {
        return (EReference) fmcsSelectExpressionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMCSODElement() {
        return fmcsodElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCSODElement_Node() {
        return (EReference) fmcsodElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMCSODElement_FullName() {
        return (EAttribute) fmcsodElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMCSMutexExpression() {
        return fmcsMutexExpressionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCSMutexExpression_OdElements() {
        return (EReference) fmcsMutexExpressionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMCSRemovesExpression() {
        return fmcsRemovesExpressionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCSRemovesExpression_OdElement() {
        return (EReference) fmcsRemovesExpressionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCSRemovesExpression_Expression() {
        return (EReference) fmcsRemovesExpressionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCSFactory getFMCSFactory() {
        return (FMCSFactory) getEFactoryInstance();
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
        fmcsRootEClass = createEClass(FMCS_ROOT);
        createEReference(fmcsRootEClass, FMCS_ROOT__CONSTRAINTS);

        fmcsConstraintEClass = createEClass(FMCS_CONSTRAINT);
        createEReference(fmcsConstraintEClass, FMCS_CONSTRAINT__EXPRESSION);
        createEReference(fmcsConstraintEClass, FMCS_CONSTRAINT__REF);

        fmcsExpressionEClass = createEClass(FMCS_EXPRESSION);

        fmcsNotExpressionEClass = createEClass(FMCS_NOT_EXPRESSION);
        createEReference(fmcsNotExpressionEClass, FMCS_NOT_EXPRESSION__EXPRESSION);

        fmcsAndExpressionEClass = createEClass(FMCS_AND_EXPRESSION);
        createEReference(fmcsAndExpressionEClass, FMCS_AND_EXPRESSION__EXPRESSIONS);

        fmcsOrExpressionEClass = createEClass(FMCS_OR_EXPRESSION);
        createEReference(fmcsOrExpressionEClass, FMCS_OR_EXPRESSION__EXPRESSIONS);

        fmcsImpliesExpressionEClass = createEClass(FMCS_IMPLIES_EXPRESSION);
        createEReference(fmcsImpliesExpressionEClass, FMCS_IMPLIES_EXPRESSION__LEFT_EXPRESSION);
        createEReference(fmcsImpliesExpressionEClass, FMCS_IMPLIES_EXPRESSION__RIGHT_EXPRESSION);

        fmcsSelectExpressionEClass = createEClass(FMCS_SELECT_EXPRESSION);
        createEReference(fmcsSelectExpressionEClass, FMCS_SELECT_EXPRESSION__OD_ELEMENT);

        fmcsodElementEClass = createEClass(FMCS_OD_ELEMENT);
        createEAttribute(fmcsodElementEClass, FMCS_OD_ELEMENT__FULL_NAME);
        createEReference(fmcsodElementEClass, FMCS_OD_ELEMENT__NODE);

        fmcsRemovesExpressionEClass = createEClass(FMCS_REMOVES_EXPRESSION);
        createEReference(fmcsRemovesExpressionEClass, FMCS_REMOVES_EXPRESSION__EXPRESSION);
        createEReference(fmcsRemovesExpressionEClass, FMCS_REMOVES_EXPRESSION__OD_ELEMENT);

        fmcsMutexExpressionEClass = createEClass(FMCS_MUTEX_EXPRESSION);
        createEReference(fmcsMutexExpressionEClass, FMCS_MUTEX_EXPRESSION__OD_ELEMENTS);
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
        FMCPackage theFMCPackage = (FMCPackage) EPackage.Registry.INSTANCE.getEPackage(FMCPackage.eNS_URI);
        FMPackage theFMPackage = (FMPackage) EPackage.Registry.INSTANCE.getEPackage(FMPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        fmcsRootEClass.getESuperTypes().add(theCOREPackage.getAbstractRootElement());
        fmcsNotExpressionEClass.getESuperTypes().add(this.getFMCSExpression());
        fmcsAndExpressionEClass.getESuperTypes().add(this.getFMCSExpression());
        fmcsOrExpressionEClass.getESuperTypes().add(this.getFMCSExpression());
        fmcsImpliesExpressionEClass.getESuperTypes().add(this.getFMCSExpression());
        fmcsSelectExpressionEClass.getESuperTypes().add(this.getFMCSExpression());
        fmcsRemovesExpressionEClass.getESuperTypes().add(this.getFMCSExpression());
        fmcsMutexExpressionEClass.getESuperTypes().add(this.getFMCSExpression());

        // Initialize classes, features, and operations; add parameters
        initEClass(fmcsRootEClass, FMCSRoot.class, "FMCSRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFMCSRoot_Constraints(), this.getFMCSConstraint(), null, "constraints", null, 0, -1, FMCSRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fmcsConstraintEClass, FMCSConstraint.class, "FMCSConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFMCSConstraint_Expression(), this.getFMCSExpression(), null, "expression", null, 1, 1, FMCSConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFMCSConstraint_Ref(), theFMCPackage.getFMCConstraint(), null, "ref", null, 0, 1, FMCSConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fmcsExpressionEClass, FMCSExpression.class, "FMCSExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(fmcsNotExpressionEClass, FMCSNotExpression.class, "FMCSNotExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFMCSNotExpression_Expression(), this.getFMCSExpression(), null, "expression", null, 1, 1, FMCSNotExpression.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fmcsAndExpressionEClass, FMCSAndExpression.class, "FMCSAndExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFMCSAndExpression_Expressions(), this.getFMCSExpression(), null, "expressions", null, 2, -1, FMCSAndExpression.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fmcsOrExpressionEClass, FMCSOrExpression.class, "FMCSOrExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFMCSOrExpression_Expressions(), this.getFMCSExpression(), null, "expressions", null, 2, -1, FMCSOrExpression.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fmcsImpliesExpressionEClass, FMCSImpliesExpression.class, "FMCSImpliesExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFMCSImpliesExpression_LeftExpression(), this.getFMCSExpression(), null, "leftExpression", null, 1, 1, FMCSImpliesExpression.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFMCSImpliesExpression_RightExpression(), this.getFMCSExpression(), null, "rightExpression", null, 1, 1, FMCSImpliesExpression.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fmcsSelectExpressionEClass, FMCSSelectExpression.class, "FMCSSelectExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFMCSSelectExpression_OdElement(), this.getFMCSODElement(), null, "odElement", null, 1, 1, FMCSSelectExpression.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fmcsodElementEClass, FMCSODElement.class, "FMCSODElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFMCSODElement_FullName(), ecorePackage.getEString(), "fullName", null, 0, 1, FMCSODElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFMCSODElement_Node(), theFMPackage.getFMNode(), null, "node", null, 1, 1, FMCSODElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fmcsRemovesExpressionEClass, FMCSRemovesExpression.class, "FMCSRemovesExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFMCSRemovesExpression_Expression(), this.getFMCSExpression(), null, "expression", null, 1, 1, FMCSRemovesExpression.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFMCSRemovesExpression_OdElement(), this.getFMCSODElement(), null, "odElement", null, 1, 1, FMCSRemovesExpression.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fmcsMutexExpressionEClass, FMCSMutexExpression.class, "FMCSMutexExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFMCSMutexExpression_OdElements(), this.getFMCSODElement(), null, "odElements", null, 1, -1, FMCSMutexExpression.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // FMCSPackageImpl
