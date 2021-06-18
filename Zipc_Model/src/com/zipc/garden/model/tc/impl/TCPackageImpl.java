/**
 */
package com.zipc.garden.model.tc.impl;

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

import com.zipc.garden.model.scs.SCSPackage;
import com.zipc.garden.model.scs.impl.SCSPackageImpl;
import com.zipc.garden.model.spql.SPQLPackage;
import com.zipc.garden.model.spql.impl.SPQLPackageImpl;
import com.zipc.garden.model.tc.TCFactory;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCNodeState;
import com.zipc.garden.model.tc.TCPackage;
import com.zipc.garden.model.tc.TCProperty;
import com.zipc.garden.model.tc.TCRoot;

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
public class TCPackageImpl extends EPackageImpl implements TCPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass tcRootEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass tcNodeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass tcPropertyEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum tcNodeStateEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package, if one already exists. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see com.zipc.garden.model.tc.TCPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TCPackageImpl() {
        super(eNS_URI, TCFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * <p>
     * This method is used to initialize {@link TCPackage#eINSTANCE} when that field is accessed. Clients should not invoke it
     * directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static TCPackage init() {
        if (isInited)
            return (TCPackage) EPackage.Registry.INSTANCE.getEPackage(TCPackage.eNS_URI);

        initializeRegistryHelpers();

        // Obtain or create and register package
        Object registeredTCPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        TCPackageImpl theTCPackage = registeredTCPackage instanceof TCPackageImpl ? (TCPackageImpl) registeredTCPackage : new TCPackageImpl();

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
        theTCPackage.createPackageContents();
        theCOREPackage.createPackageContents();
        theSCDPackage.createPackageContents();
        theFMPackage.createPackageContents();
        theFMCPackage.createPackageContents();
        theFMCSPackage.createPackageContents();
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
        theTCPackage.initializePackageContents();
        theCOREPackage.initializePackageContents();
        theSCDPackage.initializePackageContents();
        theFMPackage.initializePackageContents();
        theFMCPackage.initializePackageContents();
        theFMCSPackage.initializePackageContents();
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
        theTCPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(TCPackage.eNS_URI, theTCPackage);
        return theTCPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static void initializeRegistryHelpers() {
        Reflect.register(TCRoot.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof TCRoot;
            }

            public Object newArrayInstance(int size) {
                return new TCRoot[size];
            }
        });
        Reflect.register(TCNode.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof TCNode;
            }

            public Object newArrayInstance(int size) {
                return new TCNode[size];
            }
        });
        Reflect.register(TCProperty.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof TCProperty;
            }

            public Object newArrayInstance(int size) {
                return new TCProperty[size];
            }
        });
        Reflect.register(TCNodeState.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof TCNodeState;
            }

            public Object newArrayInstance(int size) {
                return new TCNodeState[size];
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
        protected TCRoot tcRoot;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected TCNode tcNode;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected TCProperty tcProperty;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected TCNodeState tcNodeState;

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTCRoot() {
        return tcRootEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getTCRoot_RootNodes() {
        return (EReference) tcRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTCRoot_ShowingHierarchy() {
        return (EAttribute) tcRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTCRoot_Keywords() {
        return (EAttribute) tcRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTCNode() {
        return tcNodeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTCNode_Name() {
        return (EAttribute) tcNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTCNode_Checked() {
        return (EAttribute) tcNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getTCNode_Children() {
        return (EReference) tcNodeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTCNode_ChildType() {
        return (EAttribute) tcNodeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTCNode_State() {
        return (EAttribute) tcNodeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTCNode_Inherited() {
        return (EAttribute) tcNodeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTCNode_Optional() {
        return (EAttribute) tcNodeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTCNode_Min() {
        return (EAttribute) tcNodeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTCNode_Max() {
        return (EAttribute) tcNodeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTCNode_NWiseCombination() {
        return (EAttribute) tcNodeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTCNode_Temporary() {
        return (EAttribute) tcNodeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getTCNode_Properties() {
        return (EReference) tcNodeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTCProperty() {
        return tcPropertyEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTCProperty_Key() {
        return (EAttribute) tcPropertyEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTCProperty_Value() {
        return (EAttribute) tcPropertyEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getTCNodeState() {
        return tcNodeStateEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TCFactory getTCFactory() {
        return (TCFactory) getEFactoryInstance();
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
        tcRootEClass = createEClass(TC_ROOT);
        createEReference(tcRootEClass, TC_ROOT__ROOT_NODES);
        createEAttribute(tcRootEClass, TC_ROOT__SHOWING_HIERARCHY);
        createEAttribute(tcRootEClass, TC_ROOT__KEYWORDS);

        tcNodeEClass = createEClass(TC_NODE);
        createEAttribute(tcNodeEClass, TC_NODE__NAME);
        createEAttribute(tcNodeEClass, TC_NODE__CHECKED);
        createEReference(tcNodeEClass, TC_NODE__CHILDREN);
        createEAttribute(tcNodeEClass, TC_NODE__CHILD_TYPE);
        createEAttribute(tcNodeEClass, TC_NODE__STATE);
        createEAttribute(tcNodeEClass, TC_NODE__INHERITED);
        createEAttribute(tcNodeEClass, TC_NODE__OPTIONAL);
        createEAttribute(tcNodeEClass, TC_NODE__MIN);
        createEAttribute(tcNodeEClass, TC_NODE__MAX);
        createEAttribute(tcNodeEClass, TC_NODE__NWISE_COMBINATION);
        createEAttribute(tcNodeEClass, TC_NODE__TEMPORARY);
        createEReference(tcNodeEClass, TC_NODE__PROPERTIES);

        tcPropertyEClass = createEClass(TC_PROPERTY);
        createEAttribute(tcPropertyEClass, TC_PROPERTY__KEY);
        createEAttribute(tcPropertyEClass, TC_PROPERTY__VALUE);

        // Create enums
        tcNodeStateEEnum = createEEnum(TC_NODE_STATE);
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
        FMPackage theFMPackage = (FMPackage) EPackage.Registry.INSTANCE.getEPackage(FMPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        tcRootEClass.getESuperTypes().add(theCOREPackage.getAbstractRootElement());

        // Initialize classes, features, and operations; add parameters
        initEClass(tcRootEClass, TCRoot.class, "TCRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTCRoot_RootNodes(), this.getTCNode(), null, "rootNodes", null, 0, -1, TCRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTCRoot_ShowingHierarchy(), theXMLTypePackage.getInt(), "showingHierarchy", "-1", 0, 1, TCRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTCRoot_Keywords(), theXMLTypePackage.getString(), "keywords", null, 0, -1, TCRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tcNodeEClass, TCNode.class, "TCNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTCNode_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, TCNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTCNode_Checked(), theXMLTypePackage.getBoolean(), "checked", null, 0, 1, TCNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTCNode_Children(), this.getTCNode(), null, "children", null, 0, -1, TCNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTCNode_ChildType(), theFMPackage.getChildType(), "childType", null, 0, 1, TCNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTCNode_State(), this.getTCNodeState(), "state", "UNCHANGED", 0, 1, TCNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTCNode_Inherited(), ecorePackage.getEBoolean(), "inherited", null, 0, 1, TCNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTCNode_Optional(), ecorePackage.getEBoolean(), "optional", null, 0, 1, TCNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTCNode_Min(), ecorePackage.getEInt(), "min", "-1", 0, 1, TCNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTCNode_Max(), ecorePackage.getEInt(), "max", "-1", 0, 1, TCNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTCNode_NWiseCombination(), ecorePackage.getEInt(), "nWiseCombination", "-1", 0, 1, TCNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTCNode_Temporary(), ecorePackage.getEBoolean(), "temporary", null, 0, 1, TCNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTCNode_Properties(), this.getTCProperty(), null, "properties", null, 0, -1, TCNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tcPropertyEClass, TCProperty.class, "TCProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTCProperty_Key(), ecorePackage.getEString(), "key", null, 0, 1, TCProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTCProperty_Value(), ecorePackage.getEString(), "value", null, 0, 1, TCProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(tcNodeStateEEnum, TCNodeState.class, "TCNodeState");
        addEEnumLiteral(tcNodeStateEEnum, TCNodeState.NEW);
        addEEnumLiteral(tcNodeStateEEnum, TCNodeState.DELETED);
        addEEnumLiteral(tcNodeStateEEnum, TCNodeState.UNCHANGED);

        // Create resource
        createResource(eNS_URI);
    }

} // TCPackageImpl
