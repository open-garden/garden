/**
 */
package com.zipc.garden.model.tp.impl;

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
import com.zipc.garden.model.tc.TCPackage;

import com.zipc.garden.model.tc.impl.TCPackageImpl;

import com.zipc.garden.model.tp.PathType;
import com.zipc.garden.model.tp.TPElement;
import com.zipc.garden.model.tp.TPFactory;
import com.zipc.garden.model.tp.TPPackage;
import com.zipc.garden.model.tp.TPPatternElement;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tp.TPTSDPattern;

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
public class TPPackageImpl extends EPackageImpl implements TPPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass tpRootEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass tptsdPatternEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass tpElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass tpSettingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass tpPatternElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum pathTypeEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package, if one already exists. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see com.zipc.garden.model.tp.TPPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TPPackageImpl() {
        super(eNS_URI, TPFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * <p>
     * This method is used to initialize {@link TPPackage#eINSTANCE} when that field is accessed. Clients should not invoke it
     * directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static TPPackage init() {
        if (isInited)
            return (TPPackage) EPackage.Registry.INSTANCE.getEPackage(TPPackage.eNS_URI);

        initializeRegistryHelpers();

        // Obtain or create and register package
        Object registeredTPPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        TPPackageImpl theTPPackage = registeredTPPackage instanceof TPPackageImpl ? (TPPackageImpl) registeredTPPackage : new TPPackageImpl();

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
        theTPPackage.createPackageContents();
        theCOREPackage.createPackageContents();
        theSCDPackage.createPackageContents();
        theFMPackage.createPackageContents();
        theFMCPackage.createPackageContents();
        theFMCSPackage.createPackageContents();
        theTCPackage.createPackageContents();
        theARCPackage.createPackageContents();
        theFSMPackage.createPackageContents();
        theTPSPackage.createPackageContents();
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
        theTPPackage.initializePackageContents();
        theCOREPackage.initializePackageContents();
        theSCDPackage.initializePackageContents();
        theFMPackage.initializePackageContents();
        theFMCPackage.initializePackageContents();
        theFMCSPackage.initializePackageContents();
        theTCPackage.initializePackageContents();
        theARCPackage.initializePackageContents();
        theFSMPackage.initializePackageContents();
        theTPSPackage.initializePackageContents();
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
        theTPPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(TPPackage.eNS_URI, theTPPackage);
        return theTPPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static void initializeRegistryHelpers() {
        Reflect.register(TPRoot.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof TPRoot;
            }

            public Object newArrayInstance(int size) {
                return new TPRoot[size];
            }
        });
        Reflect.register(TPTSDPattern.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof TPTSDPattern;
            }

            public Object newArrayInstance(int size) {
                return new TPTSDPattern[size];
            }
        });
        Reflect.register(TPElement.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof TPElement;
            }

            public Object newArrayInstance(int size) {
                return new TPElement[size];
            }
        });
        Reflect.register(TPSetting.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof TPSetting;
            }

            public Object newArrayInstance(int size) {
                return new TPSetting[size];
            }
        });
        Reflect.register(TPPatternElement.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof TPPatternElement;
            }

            public Object newArrayInstance(int size) {
                return new TPPatternElement[size];
            }
        });
        Reflect.register(PathType.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof PathType;
            }

            public Object newArrayInstance(int size) {
                return new PathType[size];
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
        protected TPRoot tpRoot;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected TPTSDPattern tptsdPattern;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected TPElement tpElement;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected TPSetting tpSetting;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected TPPatternElement tpPatternElement;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected PathType pathType;

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTPRoot() {
        return tpRootEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTPRoot_All() {
        return (EAttribute) tpRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTPRoot_Message() {
        return (EAttribute) tpRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTPRoot_Status() {
        return (EAttribute) tpRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTPRoot_End() {
        return (EAttribute) tpRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTPRoot_Begin() {
        return (EAttribute) tpRootEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTPTSDPattern() {
        return tptsdPatternEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getTPTSDPattern_Elements() {
        return (EReference) tptsdPatternEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTPTSDPattern_Id() {
        return (EAttribute) tptsdPatternEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getTPTSDPattern_PatternElements() {
        return (EReference) tptsdPatternEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTPElement() {
        return tpElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTPElement_FullPath() {
        return (EAttribute) tpElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTPElement_SimplePath() {
        return (EAttribute) tpElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTPSetting() {
        return tpSettingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTPSetting_Header() {
        return (EAttribute) tpSettingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTPSetting_Title() {
        return (EAttribute) tpSettingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getTPSetting_Elements() {
        return (EReference) tpSettingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getTPSetting_Patterns() {
        return (EReference) tpSettingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getTPSetting_PatternElements() {
        return (EReference) tpSettingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTPPatternElement() {
        return tpPatternElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTPPatternElement_Checked() {
        return (EAttribute) tpPatternElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTPPatternElement_Name() {
        return (EAttribute) tpPatternElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTPPatternElement_Value() {
        return (EAttribute) tpPatternElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getPathType() {
        return pathTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TPFactory getTPFactory() {
        return (TPFactory) getEFactoryInstance();
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
        tpRootEClass = createEClass(TP_ROOT);
        createEAttribute(tpRootEClass, TP_ROOT__ALL);
        createEAttribute(tpRootEClass, TP_ROOT__MESSAGE);
        createEAttribute(tpRootEClass, TP_ROOT__STATUS);
        createEAttribute(tpRootEClass, TP_ROOT__END);
        createEAttribute(tpRootEClass, TP_ROOT__BEGIN);

        tptsdPatternEClass = createEClass(TP_TSD_PATTERN);
        createEReference(tptsdPatternEClass, TP_TSD_PATTERN__ELEMENTS);
        createEAttribute(tptsdPatternEClass, TP_TSD_PATTERN__ID);
        createEReference(tptsdPatternEClass, TP_TSD_PATTERN__PATTERN_ELEMENTS);

        tpElementEClass = createEClass(TP_ELEMENT);
        createEAttribute(tpElementEClass, TP_ELEMENT__FULL_PATH);
        createEAttribute(tpElementEClass, TP_ELEMENT__SIMPLE_PATH);

        tpSettingEClass = createEClass(TP_SETTING);
        createEAttribute(tpSettingEClass, TP_SETTING__HEADER);
        createEAttribute(tpSettingEClass, TP_SETTING__TITLE);
        createEReference(tpSettingEClass, TP_SETTING__ELEMENTS);
        createEReference(tpSettingEClass, TP_SETTING__PATTERNS);
        createEReference(tpSettingEClass, TP_SETTING__PATTERN_ELEMENTS);

        tpPatternElementEClass = createEClass(TP_PATTERN_ELEMENT);
        createEAttribute(tpPatternElementEClass, TP_PATTERN_ELEMENT__CHECKED);
        createEAttribute(tpPatternElementEClass, TP_PATTERN_ELEMENT__NAME);
        createEAttribute(tpPatternElementEClass, TP_PATTERN_ELEMENT__VALUE);

        // Create enums
        pathTypeEEnum = createEEnum(PATH_TYPE);
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
        tpRootEClass.getESuperTypes().add(theCOREPackage.getAbstractRootElement());
        tpRootEClass.getESuperTypes().add(theCOREPackage.getAbstractSearchCondition());
        tpRootEClass.getESuperTypes().add(theCOREPackage.getSettingInterface());
        tpSettingEClass.getESuperTypes().add(theCOREPackage.getAbstractSetting());

        // Initialize classes, features, and operations; add parameters
        initEClass(tpRootEClass, TPRoot.class, "TPRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTPRoot_All(), ecorePackage.getEDouble(), "all", null, 0, 1, TPRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTPRoot_Message(), theXMLTypePackage.getString(), "message", null, 0, 1, TPRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTPRoot_Status(), theXMLTypePackage.getInt(), "status", "-1", 0, 1, TPRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTPRoot_End(), theXMLTypePackage.getInt(), "end", null, 0, 1, TPRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTPRoot_Begin(), theXMLTypePackage.getInt(), "begin", null, 0, 1, TPRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tptsdPatternEClass, TPTSDPattern.class, "TPTSDPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTPTSDPattern_Elements(), this.getTPElement(), null, "elements", null, 0, -1, TPTSDPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTPTSDPattern_Id(), ecorePackage.getEString(), "id", null, 1, 1, TPTSDPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTPTSDPattern_PatternElements(), this.getTPPatternElement(), null, "patternElements", null, 0, -1, TPTSDPattern.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tpElementEClass, TPElement.class, "TPElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTPElement_FullPath(), ecorePackage.getEString(), "fullPath", null, 1, 1, TPElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTPElement_SimplePath(), theXMLTypePackage.getString(), "simplePath", null, 0, 1, TPElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tpSettingEClass, TPSetting.class, "TPSetting", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTPSetting_Header(), theXMLTypePackage.getString(), "header", null, 0, -1, TPSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTPSetting_Title(), this.getPathType(), "title", null, 0, 1, TPSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTPSetting_Elements(), this.getTPElement(), null, "elements", null, 0, -1, TPSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTPSetting_Patterns(), this.getTPTSDPattern(), null, "patterns", null, 0, -1, TPSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTPSetting_PatternElements(), this.getTPPatternElement(), null, "patternElements", null, 0, -1, TPSetting.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tpPatternElementEClass, TPPatternElement.class, "TPPatternElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTPPatternElement_Checked(), theXMLTypePackage.getBoolean(), "checked", null, 0, 1, TPPatternElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTPPatternElement_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, TPPatternElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTPPatternElement_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, TPPatternElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(pathTypeEEnum, PathType.class, "PathType");
        addEEnumLiteral(pathTypeEEnum, PathType.SIMPLE_PATH);
        addEEnumLiteral(pathTypeEEnum, PathType.FULL_PATH);

        // Create resource
        createResource(eNS_URI);
    }

} // TPPackageImpl
