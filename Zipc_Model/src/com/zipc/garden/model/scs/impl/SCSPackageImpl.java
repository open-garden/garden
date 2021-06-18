/**
 */
package com.zipc.garden.model.scs.impl;

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

import com.zipc.garden.model.scs.SCSFactory;
import com.zipc.garden.model.scs.SCSPackage;
import com.zipc.garden.model.scs.SCSPattern;
import com.zipc.garden.model.scs.SCSPatternReference;
import com.zipc.garden.model.scs.SCSRoot;

import com.zipc.garden.model.scs.SCSSetting;
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

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class SCSPackageImpl extends EPackageImpl implements SCSPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass scsRootEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass scsPatternEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass scsPatternReferenceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass scsSettingEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package, if one already exists. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see com.zipc.garden.model.scs.SCSPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private SCSPackageImpl() {
        super(eNS_URI, SCSFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * <p>
     * This method is used to initialize {@link SCSPackage#eINSTANCE} when that field is accessed. Clients should not invoke it
     * directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static SCSPackage init() {
        if (isInited)
            return (SCSPackage) EPackage.Registry.INSTANCE.getEPackage(SCSPackage.eNS_URI);

        initializeRegistryHelpers();

        // Obtain or create and register package
        Object registeredSCSPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        SCSPackageImpl theSCSPackage = registeredSCSPackage instanceof SCSPackageImpl ? (SCSPackageImpl) registeredSCSPackage : new SCSPackageImpl();

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
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SPQLPackage.eNS_URI);
        SPQLPackageImpl theSPQLPackage = (SPQLPackageImpl) (registeredPackage instanceof SPQLPackageImpl ? registeredPackage : SPQLPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CSCSPackage.eNS_URI);
        CSCSPackageImpl theCSCSPackage = (CSCSPackageImpl) (registeredPackage instanceof CSCSPackageImpl ? registeredPackage : CSCSPackage.eINSTANCE);

        // Create package meta-data objects
        theSCSPackage.createPackageContents();
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
        theSPQLPackage.createPackageContents();
        theCSCSPackage.createPackageContents();

        // Initialize created meta-data
        theSCSPackage.initializePackageContents();
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
        theSPQLPackage.initializePackageContents();
        theCSCSPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theSCSPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(SCSPackage.eNS_URI, theSCSPackage);
        return theSCSPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static void initializeRegistryHelpers() {
        Reflect.register(SCSRoot.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof SCSRoot;
            }

            public Object newArrayInstance(int size) {
                return new SCSRoot[size];
            }
        });
        Reflect.register(SCSPattern.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof SCSPattern;
            }

            public Object newArrayInstance(int size) {
                return new SCSPattern[size];
            }
        });
        Reflect.register(SCSPatternReference.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof SCSPatternReference;
            }

            public Object newArrayInstance(int size) {
                return new SCSPatternReference[size];
            }
        });
        Reflect.register(SCSSetting.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof SCSSetting;
            }

            public Object newArrayInstance(int size) {
                return new SCSSetting[size];
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
        protected SCSRoot scsRoot;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected SCSPattern scsPattern;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected SCSPatternReference scsPatternReference;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected SCSSetting scsSetting;

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getSCSRoot() {
        return scsRootEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSCSRoot_All() {
        return (EAttribute) scsRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSCSRoot_Begin() {
        return (EAttribute) scsRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSCSRoot_End() {
        return (EAttribute) scsRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSCSRoot_Status() {
        return (EAttribute) scsRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSCSRoot_Message() {
        return (EAttribute) scsRootEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getSCSPattern() {
        return scsPatternEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSCSPattern_Lsc() {
        return (EAttribute) scsPatternEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getSCSPattern_Patternreferences() {
        return (EReference) scsPatternEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSCSPattern_Id() {
        return (EAttribute) scsPatternEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSCSPattern_PatternValue() {
        return (EAttribute) scsPatternEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSCSPattern_CscUuid() {
        return (EAttribute) scsPatternEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSCSPattern_CscFileName() {
        return (EAttribute) scsPatternEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getSCSPatternReference() {
        return scsPatternReferenceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSCSPatternReference_FileId() {
        return (EAttribute) scsPatternReferenceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSCSPatternReference_PatternId() {
        return (EAttribute) scsPatternReferenceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getSCSSetting() {
        return scsSettingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getSCSSetting_Patterns() {
        return (EReference) scsSettingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SCSFactory getSCSFactory() {
        return (SCSFactory) getEFactoryInstance();
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
        scsRootEClass = createEClass(SCS_ROOT);
        createEAttribute(scsRootEClass, SCS_ROOT__ALL);
        createEAttribute(scsRootEClass, SCS_ROOT__BEGIN);
        createEAttribute(scsRootEClass, SCS_ROOT__END);
        createEAttribute(scsRootEClass, SCS_ROOT__STATUS);
        createEAttribute(scsRootEClass, SCS_ROOT__MESSAGE);

        scsPatternEClass = createEClass(SCS_PATTERN);
        createEAttribute(scsPatternEClass, SCS_PATTERN__LSC);
        createEReference(scsPatternEClass, SCS_PATTERN__PATTERNREFERENCES);
        createEAttribute(scsPatternEClass, SCS_PATTERN__ID);
        createEAttribute(scsPatternEClass, SCS_PATTERN__PATTERN_VALUE);
        createEAttribute(scsPatternEClass, SCS_PATTERN__CSC_UUID);
        createEAttribute(scsPatternEClass, SCS_PATTERN__CSC_FILE_NAME);

        scsPatternReferenceEClass = createEClass(SCS_PATTERN_REFERENCE);
        createEAttribute(scsPatternReferenceEClass, SCS_PATTERN_REFERENCE__FILE_ID);
        createEAttribute(scsPatternReferenceEClass, SCS_PATTERN_REFERENCE__PATTERN_ID);

        scsSettingEClass = createEClass(SCS_SETTING);
        createEReference(scsSettingEClass, SCS_SETTING__PATTERNS);
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
        scsRootEClass.getESuperTypes().add(theCOREPackage.getAbstractRootElement());
        scsRootEClass.getESuperTypes().add(theCOREPackage.getAbstractSearchCondition());
        scsRootEClass.getESuperTypes().add(theCOREPackage.getSettingInterface());
        scsSettingEClass.getESuperTypes().add(theCOREPackage.getAbstractSetting());

        // Initialize classes, features, and operations; add parameters
        initEClass(scsRootEClass, SCSRoot.class, "SCSRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSCSRoot_All(), ecorePackage.getEDouble(), "all", null, 0, 1, SCSRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSCSRoot_Begin(), theXMLTypePackage.getInt(), "begin", null, 0, 1, SCSRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSCSRoot_End(), theXMLTypePackage.getInt(), "end", null, 0, 1, SCSRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSCSRoot_Status(), theXMLTypePackage.getInt(), "status", "-1", 0, 1, SCSRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSCSRoot_Message(), theXMLTypePackage.getString(), "message", null, 0, 1, SCSRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scsPatternEClass, SCSPattern.class, "SCSPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSCSPattern_Lsc(), ecorePackage.getEString(), "lsc", null, 0, 1, SCSPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSCSPattern_Patternreferences(), this.getSCSPatternReference(), null, "patternreferences", null, 0, -1, SCSPattern.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSCSPattern_Id(), ecorePackage.getEString(), "id", null, 0, 1, SCSPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSCSPattern_PatternValue(), ecorePackage.getEString(), "patternValue", null, 0, 1, SCSPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSCSPattern_CscUuid(), ecorePackage.getEString(), "cscUuid", null, 0, 1, SCSPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSCSPattern_CscFileName(), ecorePackage.getEString(), "cscFileName", null, 0, 1, SCSPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scsPatternReferenceEClass, SCSPatternReference.class, "SCSPatternReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSCSPatternReference_FileId(), ecorePackage.getELong(), "fileId", null, 0, 1, SCSPatternReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSCSPatternReference_PatternId(), ecorePackage.getELong(), "patternId", null, 0, 1, SCSPatternReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scsSettingEClass, SCSSetting.class, "SCSSetting", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSCSSetting_Patterns(), this.getSCSPattern(), null, "patterns", null, 0, -1, SCSSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // SCSPackageImpl
