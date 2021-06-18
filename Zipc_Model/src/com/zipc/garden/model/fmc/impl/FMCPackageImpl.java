/**
 */
package com.zipc.garden.model.fmc.impl;

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

import com.zipc.garden.model.fmc.FMCConstraint;
import com.zipc.garden.model.fmc.FMCFactory;
import com.zipc.garden.model.fmc.FMCNodePath;
import com.zipc.garden.model.fmc.FMCPackage;
import com.zipc.garden.model.fmc.FMCRoot;

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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class FMCPackageImpl extends EPackageImpl implements FMCPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmcRootEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmcConstraintEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass fmcNodePathEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package, if one already exists. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see com.zipc.garden.model.fmc.FMCPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private FMCPackageImpl() {
        super(eNS_URI, FMCFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * <p>
     * This method is used to initialize {@link FMCPackage#eINSTANCE} when that field is accessed. Clients should not invoke it
     * directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static FMCPackage init() {
        if (isInited)
            return (FMCPackage) EPackage.Registry.INSTANCE.getEPackage(FMCPackage.eNS_URI);

        initializeRegistryHelpers();

        // Obtain or create and register package
        Object registeredFMCPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        FMCPackageImpl theFMCPackage = registeredFMCPackage instanceof FMCPackageImpl ? (FMCPackageImpl) registeredFMCPackage : new FMCPackageImpl();

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
        theFMCPackage.createPackageContents();
        theCOREPackage.createPackageContents();
        theSCDPackage.createPackageContents();
        theFMPackage.createPackageContents();
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
        theFMCPackage.initializePackageContents();
        theCOREPackage.initializePackageContents();
        theSCDPackage.initializePackageContents();
        theFMPackage.initializePackageContents();
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
        theFMCPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(FMCPackage.eNS_URI, theFMCPackage);
        return theFMCPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static void initializeRegistryHelpers() {
        Reflect.register(FMCRoot.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMCRoot;
            }

            public Object newArrayInstance(int size) {
                return new FMCRoot[size];
            }
        });
        Reflect.register(FMCConstraint.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMCConstraint;
            }

            public Object newArrayInstance(int size) {
                return new FMCConstraint[size];
            }
        });
        Reflect.register(FMCNodePath.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof FMCNodePath;
            }

            public Object newArrayInstance(int size) {
                return new FMCNodePath[size];
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
        protected FMCRoot fmcRoot;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMCConstraint fmcConstraint;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected FMCNodePath fmcNodePath;

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMCRoot() {
        return fmcRootEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCRoot_Constraints() {
        return (EReference) fmcRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCRoot_Nodepaths() {
        return (EReference) fmcRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMCRoot_Document() {
        return (EAttribute) fmcRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMCConstraint() {
        return fmcConstraintEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMCConstraint_Constraint() {
        return (EAttribute) fmcConstraintEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMCConstraint_Comment() {
        return (EAttribute) fmcConstraintEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMCConstraint_Enabled() {
        return (EAttribute) fmcConstraintEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFMCConstraint_RelatedNodes() {
        return (EReference) fmcConstraintEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFMCNodePath() {
        return fmcNodePathEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMCNodePath_Fullpath() {
        return (EAttribute) fmcNodePathEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMCNodePath_SimplePath() {
        return (EAttribute) fmcNodePathEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFMCNodePath_NodeName() {
        return (EAttribute) fmcNodePathEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FMCFactory getFMCFactory() {
        return (FMCFactory) getEFactoryInstance();
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
        fmcRootEClass = createEClass(FMC_ROOT);
        createEReference(fmcRootEClass, FMC_ROOT__CONSTRAINTS);
        createEReference(fmcRootEClass, FMC_ROOT__NODEPATHS);
        createEAttribute(fmcRootEClass, FMC_ROOT__DOCUMENT);

        fmcConstraintEClass = createEClass(FMC_CONSTRAINT);
        createEAttribute(fmcConstraintEClass, FMC_CONSTRAINT__CONSTRAINT);
        createEAttribute(fmcConstraintEClass, FMC_CONSTRAINT__COMMENT);
        createEAttribute(fmcConstraintEClass, FMC_CONSTRAINT__ENABLED);
        createEReference(fmcConstraintEClass, FMC_CONSTRAINT__RELATED_NODES);

        fmcNodePathEClass = createEClass(FMC_NODE_PATH);
        createEAttribute(fmcNodePathEClass, FMC_NODE_PATH__FULLPATH);
        createEAttribute(fmcNodePathEClass, FMC_NODE_PATH__SIMPLE_PATH);
        createEAttribute(fmcNodePathEClass, FMC_NODE_PATH__NODE_NAME);
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
        fmcRootEClass.getESuperTypes().add(theCOREPackage.getAbstractRootElement());

        // Initialize classes, features, and operations; add parameters
        initEClass(fmcRootEClass, FMCRoot.class, "FMCRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFMCRoot_Constraints(), this.getFMCConstraint(), null, "constraints", null, 0, -1, FMCRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFMCRoot_Nodepaths(), this.getFMCNodePath(), null, "nodepaths", null, 0, -1, FMCRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMCRoot_Document(), ecorePackage.getEString(), "document", null, 0, 1, FMCRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fmcConstraintEClass, FMCConstraint.class, "FMCConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFMCConstraint_Constraint(), ecorePackage.getEString(), "constraint", null, 0, 1, FMCConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMCConstraint_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, FMCConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMCConstraint_Enabled(), theXMLTypePackage.getBoolean(), "enabled", "true", 0, 1, FMCConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFMCConstraint_RelatedNodes(), this.getFMCNodePath(), null, "relatedNodes", null, 0, -1, FMCConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fmcNodePathEClass, FMCNodePath.class, "FMCNodePath", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFMCNodePath_Fullpath(), theXMLTypePackage.getString(), "fullpath", null, 0, 1, FMCNodePath.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMCNodePath_SimplePath(), theXMLTypePackage.getString(), "simplePath", null, 0, 1, FMCNodePath.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFMCNodePath_NodeName(), ecorePackage.getEString(), "nodeName", null, 0, 1, FMCNodePath.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // FMCPackageImpl
