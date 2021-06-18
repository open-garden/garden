/**
 */
package com.zipc.garden.model.core.impl;

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

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractLine;
import com.zipc.garden.model.core.AbstractNode;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.AbstractSearchCondition;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.core.AbstractStyle;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.core.LineType;
import com.zipc.garden.model.core.Memo;
import com.zipc.garden.model.core.Reference;

import com.zipc.garden.model.core.SettingInterface;
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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class COREPackageImpl extends EPackageImpl implements COREPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractRootElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass referenceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractDiagramEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractNodeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass memoEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractPointEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractLineEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractSearchConditionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractSettingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass settingInterfaceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum lineTypeEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package, if one already exists. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see com.zipc.garden.model.core.COREPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private COREPackageImpl() {
        super(eNS_URI, COREFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * <p>
     * This method is used to initialize {@link COREPackage#eINSTANCE} when that field is accessed. Clients should not invoke it
     * directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static COREPackage init() {
        if (isInited)
            return (COREPackage) EPackage.Registry.INSTANCE.getEPackage(COREPackage.eNS_URI);

        initializeRegistryHelpers();

        // Obtain or create and register package
        Object registeredCOREPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        COREPackageImpl theCOREPackage = registeredCOREPackage instanceof COREPackageImpl ? (COREPackageImpl) registeredCOREPackage : new COREPackageImpl();

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SCDPackage.eNS_URI);
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
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SCSPackage.eNS_URI);
        SCSPackageImpl theSCSPackage = (SCSPackageImpl) (registeredPackage instanceof SCSPackageImpl ? registeredPackage : SCSPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SPQLPackage.eNS_URI);
        SPQLPackageImpl theSPQLPackage = (SPQLPackageImpl) (registeredPackage instanceof SPQLPackageImpl ? registeredPackage : SPQLPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CSCSPackage.eNS_URI);
        CSCSPackageImpl theCSCSPackage = (CSCSPackageImpl) (registeredPackage instanceof CSCSPackageImpl ? registeredPackage : CSCSPackage.eINSTANCE);

        // Create package meta-data objects
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
        theCOREPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(COREPackage.eNS_URI, theCOREPackage);
        return theCOREPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static void initializeRegistryHelpers() {
        Reflect.register(AbstractRootElement.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof AbstractRootElement;
            }

            public Object newArrayInstance(int size) {
                return new AbstractRootElement[size];
            }
        });
        Reflect.register(Reference.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof Reference;
            }

            public Object newArrayInstance(int size) {
                return new Reference[size];
            }
        });
        Reflect.register(AbstractDiagram.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof AbstractDiagram;
            }

            public Object newArrayInstance(int size) {
                return new AbstractDiagram[size];
            }
        });
        Reflect.register(AbstractNode.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof AbstractNode;
            }

            public Object newArrayInstance(int size) {
                return new AbstractNode[size];
            }
        });
        Reflect.register(Memo.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof Memo;
            }

            public Object newArrayInstance(int size) {
                return new Memo[size];
            }
        });
        Reflect.register(AbstractPoint.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof AbstractPoint;
            }

            public Object newArrayInstance(int size) {
                return new AbstractPoint[size];
            }
        });
        Reflect.register(AbstractLine.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof AbstractLine;
            }

            public Object newArrayInstance(int size) {
                return new AbstractLine[size];
            }
        });
        Reflect.register(AbstractStyle.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof AbstractStyle;
            }

            public Object newArrayInstance(int size) {
                return new AbstractStyle[size];
            }
        });
        Reflect.register(AbstractSearchCondition.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof AbstractSearchCondition;
            }

            public Object newArrayInstance(int size) {
                return new AbstractSearchCondition[size];
            }
        });
        Reflect.register(AbstractSetting.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof AbstractSetting;
            }

            public Object newArrayInstance(int size) {
                return new AbstractSetting[size];
            }
        });
        Reflect.register(SettingInterface.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof SettingInterface;
            }

            public Object newArrayInstance(int size) {
                return new SettingInterface[size];
            }
        });
        Reflect.register(LineType.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof LineType;
            }

            public Object newArrayInstance(int size) {
                return new LineType[size];
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
        protected AbstractRootElement abstractRootElement;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected Reference reference;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected AbstractDiagram abstractDiagram;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected AbstractNode abstractNode;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected Memo memo;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected AbstractPoint abstractPoint;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected AbstractLine abstractLine;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected AbstractStyle abstractStyle;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected AbstractSearchCondition abstractSearchCondition;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected AbstractSetting abstractSetting;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected SettingInterface settingInterface;

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        protected LineType lineType;

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbstractRootElement() {
        return abstractRootElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractRootElement_Id() {
        return (EAttribute) abstractRootElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getAbstractRootElement_Refs() {
        return (EReference) abstractRootElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getReference() {
        return referenceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getReference_Refid() {
        return (EAttribute) referenceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getReference_Hash() {
        return (EAttribute) referenceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getReference_RefName() {
        return (EAttribute) referenceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getReference_RefExtension() {
        return (EAttribute) referenceEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbstractDiagram() {
        return abstractDiagramEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractDiagram_ScrollX() {
        return (EAttribute) abstractDiagramEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractDiagram_ScrollY() {
        return (EAttribute) abstractDiagramEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractDiagram_PositionX() {
        return (EAttribute) abstractDiagramEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractDiagram_PositionY() {
        return (EAttribute) abstractDiagramEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractDiagram_GridSize() {
        return (EAttribute) abstractDiagramEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getAbstractDiagram_Nodes() {
        return (EReference) abstractDiagramEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractDiagram_LineMode() {
        return (EAttribute) abstractDiagramEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbstractNode() {
        return abstractNodeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractNode_Top() {
        return (EAttribute) abstractNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractNode_Left() {
        return (EAttribute) abstractNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractNode_Height() {
        return (EAttribute) abstractNodeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractNode_Width() {
        return (EAttribute) abstractNodeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getMemo() {
        return memoEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getMemo_Text() {
        return (EAttribute) memoEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbstractPoint() {
        return abstractPointEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractPoint_X() {
        return (EAttribute) abstractPointEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractPoint_Y() {
        return (EAttribute) abstractPointEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbstractLine() {
        return abstractLineEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractLine_SourceAnchorX() {
        return (EAttribute) abstractLineEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractLine_SourceAnchorY() {
        return (EAttribute) abstractLineEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractLine_TargetAnchorX() {
        return (EAttribute) abstractLineEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractLine_TargetAnchorY() {
        return (EAttribute) abstractLineEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractLine_Type() {
        return (EAttribute) abstractLineEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getAbstractLine_ConnectionPoint() {
        return (EReference) abstractLineEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbstractStyle() {
        return abstractStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractStyle_FontSize() {
        return (EAttribute) abstractStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractStyle_FillColor() {
        return (EAttribute) abstractStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractStyle_FontColor() {
        return (EAttribute) abstractStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbstractSearchCondition() {
        return abstractSearchConditionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractSearchCondition_Max() {
        return (EAttribute) abstractSearchConditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractSearchCondition_RowIds() {
        return (EAttribute) abstractSearchConditionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAbstractSetting() {
        return abstractSettingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractSetting_Uuid() {
        return (EAttribute) abstractSettingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractSetting_SettingHash() {
        return (EAttribute) abstractSettingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractSetting_PatternNos() {
        return (EAttribute) abstractSettingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractSetting_Begin() {
        return (EAttribute) abstractSettingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractSetting_End() {
        return (EAttribute) abstractSettingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getAbstractSetting_Count() {
        return (EAttribute) abstractSettingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getAbstractSetting_AbstractRoot() {
        return (EReference) abstractSettingEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getSettingInterface() {
        return settingInterfaceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getSettingInterface_Settings() {
        return (EReference) settingInterfaceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getLineType() {
        return lineTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public COREFactory getCOREFactory() {
        return (COREFactory) getEFactoryInstance();
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
        abstractRootElementEClass = createEClass(ABSTRACT_ROOT_ELEMENT);
        createEAttribute(abstractRootElementEClass, ABSTRACT_ROOT_ELEMENT__ID);
        createEReference(abstractRootElementEClass, ABSTRACT_ROOT_ELEMENT__REFS);

        referenceEClass = createEClass(REFERENCE);
        createEAttribute(referenceEClass, REFERENCE__REFID);
        createEAttribute(referenceEClass, REFERENCE__HASH);
        createEAttribute(referenceEClass, REFERENCE__REF_NAME);
        createEAttribute(referenceEClass, REFERENCE__REF_EXTENSION);

        abstractDiagramEClass = createEClass(ABSTRACT_DIAGRAM);
        createEAttribute(abstractDiagramEClass, ABSTRACT_DIAGRAM__SCROLL_X);
        createEAttribute(abstractDiagramEClass, ABSTRACT_DIAGRAM__SCROLL_Y);
        createEAttribute(abstractDiagramEClass, ABSTRACT_DIAGRAM__POSITION_X);
        createEAttribute(abstractDiagramEClass, ABSTRACT_DIAGRAM__POSITION_Y);
        createEAttribute(abstractDiagramEClass, ABSTRACT_DIAGRAM__GRID_SIZE);
        createEReference(abstractDiagramEClass, ABSTRACT_DIAGRAM__NODES);
        createEAttribute(abstractDiagramEClass, ABSTRACT_DIAGRAM__LINE_MODE);

        abstractNodeEClass = createEClass(ABSTRACT_NODE);
        createEAttribute(abstractNodeEClass, ABSTRACT_NODE__TOP);
        createEAttribute(abstractNodeEClass, ABSTRACT_NODE__LEFT);
        createEAttribute(abstractNodeEClass, ABSTRACT_NODE__HEIGHT);
        createEAttribute(abstractNodeEClass, ABSTRACT_NODE__WIDTH);

        memoEClass = createEClass(MEMO);
        createEAttribute(memoEClass, MEMO__TEXT);

        abstractPointEClass = createEClass(ABSTRACT_POINT);
        createEAttribute(abstractPointEClass, ABSTRACT_POINT__X);
        createEAttribute(abstractPointEClass, ABSTRACT_POINT__Y);

        abstractLineEClass = createEClass(ABSTRACT_LINE);
        createEAttribute(abstractLineEClass, ABSTRACT_LINE__SOURCE_ANCHOR_X);
        createEAttribute(abstractLineEClass, ABSTRACT_LINE__SOURCE_ANCHOR_Y);
        createEAttribute(abstractLineEClass, ABSTRACT_LINE__TARGET_ANCHOR_X);
        createEAttribute(abstractLineEClass, ABSTRACT_LINE__TARGET_ANCHOR_Y);
        createEAttribute(abstractLineEClass, ABSTRACT_LINE__TYPE);
        createEReference(abstractLineEClass, ABSTRACT_LINE__CONNECTION_POINT);

        abstractStyleEClass = createEClass(ABSTRACT_STYLE);
        createEAttribute(abstractStyleEClass, ABSTRACT_STYLE__FONT_SIZE);
        createEAttribute(abstractStyleEClass, ABSTRACT_STYLE__FILL_COLOR);
        createEAttribute(abstractStyleEClass, ABSTRACT_STYLE__FONT_COLOR);

        abstractSearchConditionEClass = createEClass(ABSTRACT_SEARCH_CONDITION);
        createEAttribute(abstractSearchConditionEClass, ABSTRACT_SEARCH_CONDITION__MAX);
        createEAttribute(abstractSearchConditionEClass, ABSTRACT_SEARCH_CONDITION__ROW_IDS);

        abstractSettingEClass = createEClass(ABSTRACT_SETTING);
        createEAttribute(abstractSettingEClass, ABSTRACT_SETTING__UUID);
        createEAttribute(abstractSettingEClass, ABSTRACT_SETTING__SETTING_HASH);
        createEAttribute(abstractSettingEClass, ABSTRACT_SETTING__PATTERN_NOS);
        createEAttribute(abstractSettingEClass, ABSTRACT_SETTING__BEGIN);
        createEAttribute(abstractSettingEClass, ABSTRACT_SETTING__END);
        createEAttribute(abstractSettingEClass, ABSTRACT_SETTING__COUNT);
        createEReference(abstractSettingEClass, ABSTRACT_SETTING__ABSTRACT_ROOT);

        settingInterfaceEClass = createEClass(SETTING_INTERFACE);
        createEReference(settingInterfaceEClass, SETTING_INTERFACE__SETTINGS);

        // Create enums
        lineTypeEEnum = createEEnum(LINE_TYPE);
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
        XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        memoEClass.getESuperTypes().add(this.getAbstractNode());

        // Initialize classes, features, and operations; add parameters
        initEClass(abstractRootElementEClass, AbstractRootElement.class, "AbstractRootElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractRootElement_Id(), ecorePackage.getEString(), "id", null, 0, 1, AbstractRootElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractRootElement_Refs(), this.getReference(), null, "refs", null, 0, -1, AbstractRootElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(referenceEClass, Reference.class, "Reference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getReference_Refid(), ecorePackage.getEString(), "refid", null, 0, 1, Reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getReference_Hash(), ecorePackage.getEString(), "hash", null, 0, 1, Reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getReference_RefName(), ecorePackage.getEString(), "refName", null, 0, 1, Reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getReference_RefExtension(), ecorePackage.getEString(), "refExtension", null, 0, 1, Reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractDiagramEClass, AbstractDiagram.class, "AbstractDiagram", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractDiagram_ScrollX(), ecorePackage.getEInt(), "scrollX", "1000", 0, 1, AbstractDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractDiagram_ScrollY(), ecorePackage.getEInt(), "scrollY", "700", 0, 1, AbstractDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractDiagram_PositionX(), ecorePackage.getEInt(), "positionX", null, 0, 1, AbstractDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractDiagram_PositionY(), ecorePackage.getEInt(), "positionY", null, 0, 1, AbstractDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractDiagram_GridSize(), ecorePackage.getEInt(), "gridSize", "20", 0, 1, AbstractDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractDiagram_Nodes(), this.getAbstractNode(), null, "nodes", null, 0, -1, AbstractDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractDiagram_LineMode(), theXMLTypePackage.getInt(), "lineMode", "0", 0, 1, AbstractDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractNodeEClass, AbstractNode.class, "AbstractNode", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractNode_Top(), ecorePackage.getEInt(), "top", null, 0, 1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractNode_Left(), ecorePackage.getEInt(), "left", null, 0, 1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractNode_Height(), ecorePackage.getEInt(), "height", null, 0, 1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractNode_Width(), ecorePackage.getEInt(), "width", null, 0, 1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(memoEClass, Memo.class, "Memo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMemo_Text(), ecorePackage.getEString(), "text", null, 0, 1, Memo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(abstractPointEClass, AbstractPoint.class, "AbstractPoint", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractPoint_X(), ecorePackage.getEInt(), "x", null, 0, 1, AbstractPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractPoint_Y(), ecorePackage.getEInt(), "y", null, 0, 1, AbstractPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractLineEClass, AbstractLine.class, "AbstractLine", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractLine_SourceAnchorX(), theXMLTypePackage.getDouble(), "sourceAnchorX", "0.5", 0, 1, AbstractLine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractLine_SourceAnchorY(), theXMLTypePackage.getDouble(), "sourceAnchorY", "0.5", 0, 1, AbstractLine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractLine_TargetAnchorX(), theXMLTypePackage.getDouble(), "targetAnchorX", "0.5", 0, 1, AbstractLine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractLine_TargetAnchorY(), theXMLTypePackage.getDouble(), "targetAnchorY", "0.5", 0, 1, AbstractLine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractLine_Type(), this.getLineType(), "type", "SIMPLE", 0, 1, AbstractLine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractLine_ConnectionPoint(), this.getAbstractPoint(), null, "connectionPoint", null, 0, -1, AbstractLine.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractStyleEClass, AbstractStyle.class, "AbstractStyle", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractStyle_FontSize(), ecorePackage.getEInt(), "fontSize", "11", 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractStyle_FillColor(), ecorePackage.getEString(), "fillColor", "#FFFFFF", 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractStyle_FontColor(), ecorePackage.getEString(), "fontColor", "#707070", 0, 1, AbstractStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractSearchConditionEClass, AbstractSearchCondition.class, "AbstractSearchCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractSearchCondition_Max(), theXMLTypePackage.getInt(), "max", "100", 0, 1, AbstractSearchCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractSearchCondition_RowIds(), theXMLTypePackage.getString(), "rowIds", null, 0, 1, AbstractSearchCondition.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractSettingEClass, AbstractSetting.class, "AbstractSetting", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractSetting_Uuid(), ecorePackage.getEString(), "uuid", null, 0, 1, AbstractSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractSetting_SettingHash(), ecorePackage.getEString(), "settingHash", null, 0, 1, AbstractSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractSetting_PatternNos(), ecorePackage.getEString(), "patternNos", null, 1, 1, AbstractSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractSetting_Begin(), theXMLTypePackage.getInt(), "begin", null, 0, 1, AbstractSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractSetting_End(), theXMLTypePackage.getInt(), "end", null, 0, 1, AbstractSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractSetting_Count(), theXMLTypePackage.getInt(), "count", null, 0, 1, AbstractSetting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractSetting_AbstractRoot(), this.getAbstractRootElement(), null, "abstractRoot", null, 1, 1, AbstractSetting.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(settingInterfaceEClass, SettingInterface.class, "SettingInterface", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSettingInterface_Settings(), this.getAbstractSetting(), null, "settings", null, 0, -1, SettingInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(lineTypeEEnum, LineType.class, "LineType");
        addEEnumLiteral(lineTypeEEnum, LineType.SIMPLE);
        addEEnumLiteral(lineTypeEEnum, LineType.MANHATTAN);

        // Create resource
        createResource(eNS_URI);
    }

} // COREPackageImpl
