/**
 */
package com.zipc.garden.model.fm.impl;

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
import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.fm.FMConstraint;
import com.zipc.garden.model.fm.FMFactory;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMPackage;
import com.zipc.garden.model.fm.FMProperty;
import com.zipc.garden.model.fm.FMRoot;

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
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class FMPackageImpl extends EPackageImpl implements FMPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmRootEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmNodeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmPropertyEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmConstraintEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum childTypeEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package, if one already exists. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see com.zipc.garden.model.fm.FMPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private FMPackageImpl() {
        super(eNS_URI, FMFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * <p>
     * This method is used to initialize {@link FMPackage#eINSTANCE} when that field is accessed. Clients should not invoke it
     * directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static FMPackage init() {
        if (isInited)
            return (FMPackage) EPackage.Registry.INSTANCE.getEPackage(FMPackage.eNS_URI);

        initializeRegistryHelpers();

        // Obtain or create and register package
        Object registeredFMPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        FMPackageImpl theFMPackage = registeredFMPackage instanceof FMPackageImpl ? (FMPackageImpl) registeredFMPackage : new FMPackageImpl();

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(COREPackage.eNS_URI);
        COREPackageImpl theCOREPackage = (COREPackageImpl) (registeredPackage instanceof COREPackageImpl ? registeredPackage : COREPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SCDPackage.eNS_URI);
        SCDPackageImpl theSCDPackage = (SCDPackageImpl) (registeredPackage instanceof SCDPackageImpl ? registeredPackage : SCDPackage.eINSTANCE);
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
        theFMPackage.createPackageContents();
        theCOREPackage.createPackageContents();
        theSCDPackage.createPackageContents();
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
        theFMPackage.initializePackageContents();
        theCOREPackage.initializePackageContents();
        theSCDPackage.initializePackageContents();
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
        theFMPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(FMPackage.eNS_URI, theFMPackage);
        return theFMPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static void initializeRegistryHelpers() {
        Reflect.register(FMRoot.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMRoot;
            }

            public Object newArrayInstance(int size) {
                return new FMRoot[size];
            }
        });
        Reflect.register(FMNode.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMNode;
            }

            public Object newArrayInstance(int size) {
                return new FMNode[size];
            }
        });
        Reflect.register(FMProperty.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMProperty;
            }

            public Object newArrayInstance(int size) {
                return new FMProperty[size];
            }
        });
        Reflect.register(FMConstraint.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMConstraint;
            }

            public Object newArrayInstance(int size) {
                return new FMConstraint[size];
            }
        });
        Reflect.register(ChildType.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof ChildType;
            }

            public Object newArrayInstance(int size) {
                return new ChildType[size];
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
        protected FMRoot fmRoot;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMNode fmNode;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMProperty fmProperty;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMConstraint fmConstraint;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected ChildType childType;

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMRoot() {
        return fmRootEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMRoot_Node() {
        return (EReference) fmRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMRoot_Properties() {
        return (EReference) fmRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMRoot_Constraints() {
        return (EReference) fmRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMRoot_AutoLayout() {
        return (EAttribute) fmRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMNode() {
        return fmNodeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMNode_Children() {
        return (EReference) fmNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_Top() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_Left() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_Height() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_Width() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_Name() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_Ref() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_RefName() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_RefInfo() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_ChildType() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMNode_Properties() {
        return (EReference) fmNodeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_Min() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_Max() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_X() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_Y() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_Refuuid() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMNode_Optional() {
        return (EAttribute) fmNodeEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMProperty() {
        return fmPropertyEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMProperty_Key() {
        return (EAttribute) fmPropertyEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMProperty_Value() {
        return (EAttribute) fmPropertyEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMConstraint() {
        return fmConstraintEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMConstraint_Constraint() {
        return (EAttribute) fmConstraintEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMConstraint_Comment() {
        return (EAttribute) fmConstraintEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMConstraint_Enabled() {
        return (EAttribute) fmConstraintEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMConstraint_RelatedNodes() {
        return (EReference) fmConstraintEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getChildType() {
        return childTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMFactory getFMFactory() {
        return (FMFactory) getEFactoryInstance();
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
        fmRootEClass = createEClass(FM_ROOT);
        createEReference(fmRootEClass, FM_ROOT__NODE);
        createEReference(fmRootEClass, FM_ROOT__PROPERTIES);
        createEReference(fmRootEClass, FM_ROOT__CONSTRAINTS);
        createEAttribute(fmRootEClass, FM_ROOT__AUTO_LAYOUT);

        fmNodeEClass = createEClass(FM_NODE);
        createEReference(fmNodeEClass, FM_NODE__CHILDREN);
        createEAttribute(fmNodeEClass, FM_NODE__TOP);
        createEAttribute(fmNodeEClass, FM_NODE__LEFT);
        createEAttribute(fmNodeEClass, FM_NODE__HEIGHT);
        createEAttribute(fmNodeEClass, FM_NODE__WIDTH);
        createEAttribute(fmNodeEClass, FM_NODE__NAME);
        createEAttribute(fmNodeEClass, FM_NODE__REF);
        createEAttribute(fmNodeEClass, FM_NODE__REF_NAME);
        createEAttribute(fmNodeEClass, FM_NODE__REF_INFO);
        createEAttribute(fmNodeEClass, FM_NODE__CHILD_TYPE);
        createEReference(fmNodeEClass, FM_NODE__PROPERTIES);
        createEAttribute(fmNodeEClass, FM_NODE__MIN);
        createEAttribute(fmNodeEClass, FM_NODE__MAX);
        createEAttribute(fmNodeEClass, FM_NODE__X);
        createEAttribute(fmNodeEClass, FM_NODE__Y);
        createEAttribute(fmNodeEClass, FM_NODE__REFUUID);
        createEAttribute(fmNodeEClass, FM_NODE__OPTIONAL);

        fmPropertyEClass = createEClass(FM_PROPERTY);
        createEAttribute(fmPropertyEClass, FM_PROPERTY__KEY);
        createEAttribute(fmPropertyEClass, FM_PROPERTY__VALUE);

        fmConstraintEClass = createEClass(FM_CONSTRAINT);
        createEAttribute(fmConstraintEClass, FM_CONSTRAINT__CONSTRAINT);
        createEAttribute(fmConstraintEClass, FM_CONSTRAINT__COMMENT);
        createEAttribute(fmConstraintEClass, FM_CONSTRAINT__ENABLED);
        createEReference(fmConstraintEClass, FM_CONSTRAINT__RELATED_NODES);

        // Create enums
        childTypeEEnum = createEEnum(CHILD_TYPE);
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
        fmRootEClass.getESuperTypes().add(theCOREPackage.getAbstractRootElement());
        fmRootEClass.getESuperTypes().add(theCOREPackage.getAbstractDiagram());
        fmNodeEClass.getESuperTypes().add(theCOREPackage.getAbstractStyle());

        // Initialize classes, features, and operations; add parameters
        initEClass(fmRootEClass, FMRoot.class, "FMRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFMRoot_Node(), this.getFMNode(), null, "node", null, 0, 1, FMRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFMRoot_Properties(), this.getFMProperty(), null, "properties", null, 0, -1, FMRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFMRoot_Constraints(), this.getFMConstraint(), null, "constraints", null, 0, -1, FMRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMRoot_AutoLayout(), theXMLTypePackage.getInt(), "autoLayout", null, 0, 1, FMRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fmNodeEClass, FMNode.class, "FMNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFMNode_Children(), this.getFMNode(), null, "children", null, 0, -1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_Top(), ecorePackage.getEInt(), "top", null, 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_Left(), ecorePackage.getEInt(), "left", null, 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_Height(), ecorePackage.getEInt(), "height", null, 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_Width(), ecorePackage.getEInt(), "width", null, 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_Name(), ecorePackage.getEString(), "name", null, 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_Ref(), ecorePackage.getELong(), "ref", null, 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_RefName(), ecorePackage.getEString(), "refName", null, 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_RefInfo(), ecorePackage.getEString(), "refInfo", null, 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_ChildType(), this.getChildType(), "childType", null, 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFMNode_Properties(), this.getFMProperty(), null, "properties", null, 0, -1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_Min(), ecorePackage.getEInt(), "min", "-1", 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_Max(), ecorePackage.getEInt(), "max", "-1", 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_X(), ecorePackage.getEInt(), "x", null, 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_Y(), ecorePackage.getEInt(), "y", null, 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_Refuuid(), theXMLTypePackage.getString(), "refuuid", null, 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMNode_Optional(), ecorePackage.getEBoolean(), "optional", null, 0, 1, FMNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fmPropertyEClass, FMProperty.class, "FMProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFMProperty_Key(), ecorePackage.getEString(), "key", null, 0, 1, FMProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMProperty_Value(), ecorePackage.getEString(), "value", null, 0, 1, FMProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fmConstraintEClass, FMConstraint.class, "FMConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFMConstraint_Constraint(), ecorePackage.getEString(), "constraint", null, 0, 1, FMConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMConstraint_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, FMConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMConstraint_Enabled(), theXMLTypePackage.getBoolean(), "enabled", "true", 0, 1, FMConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFMConstraint_RelatedNodes(), this.getFMNode(), null, "relatedNodes", null, 0, -1, FMConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(childTypeEEnum, ChildType.class, "ChildType");
        addEEnumLiteral(childTypeEEnum, ChildType.AND);
        addEEnumLiteral(childTypeEEnum, ChildType.XOR);

        // Create resource
        createResource(eNS_URI);
    }

} // FMPackageImpl
