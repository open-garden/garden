/**
 */
package com.zipc.garden.model.scs;

import com.zipc.garden.model.core.COREPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.zipc.garden.model.scs.SCSFactory
 * @model kind="package"
 * @generated
 */
public interface SCSPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "scs";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.scs";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "SCS";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    SCSPackage eINSTANCE = com.zipc.garden.model.scs.impl.SCSPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scs.impl.SCSRootImpl <em>Root</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.scs.impl.SCSRootImpl
     * @see com.zipc.garden.model.scs.impl.SCSPackageImpl#getSCSRoot()
     * @generated
     */
    int SCS_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>Max</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_ROOT__MAX = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Row Ids</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_ROOT__ROW_IDS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Settings</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int SCS_ROOT__SETTINGS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>All</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_ROOT__ALL = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Begin</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_ROOT__BEGIN = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>End</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_ROOT__END = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Status</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_ROOT__STATUS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_ROOT__MESSAGE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scs.impl.SCSPatternImpl <em>Pattern</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.scs.impl.SCSPatternImpl
     * @see com.zipc.garden.model.scs.impl.SCSPackageImpl#getSCSPattern()
     * @generated
     */
    int SCS_PATTERN = 1;

    /**
     * The feature id for the '<em><b>Lsc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_PATTERN__LSC = 0;

    /**
     * The feature id for the '<em><b>Patternreferences</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_PATTERN__PATTERNREFERENCES = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_PATTERN__ID = 2;

    /**
     * The feature id for the '<em><b>Pattern Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_PATTERN__PATTERN_VALUE = 3;

    /**
     * The feature id for the '<em><b>Csc Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_PATTERN__CSC_UUID = 4;

    /**
     * The feature id for the '<em><b>Csc File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_PATTERN__CSC_FILE_NAME = 5;

    /**
     * The number of structural features of the '<em>Pattern</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_PATTERN_FEATURE_COUNT = 6;

    /**
     * The number of operations of the '<em>Pattern</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_PATTERN_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scs.impl.SCSPatternReferenceImpl <em>Pattern Reference</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.scs.impl.SCSPatternReferenceImpl
     * @see com.zipc.garden.model.scs.impl.SCSPackageImpl#getSCSPatternReference()
     * @generated
     */
    int SCS_PATTERN_REFERENCE = 2;

    /**
     * The feature id for the '<em><b>File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_PATTERN_REFERENCE__FILE_ID = 0;

    /**
     * The feature id for the '<em><b>Pattern Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_PATTERN_REFERENCE__PATTERN_ID = 1;

    /**
     * The number of structural features of the '<em>Pattern Reference</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int SCS_PATTERN_REFERENCE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Pattern Reference</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_PATTERN_REFERENCE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scs.impl.SCSSettingImpl <em>Setting</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.scs.impl.SCSSettingImpl
     * @see com.zipc.garden.model.scs.impl.SCSPackageImpl#getSCSSetting()
     * @generated
     */
    int SCS_SETTING = 3;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_SETTING__UUID = COREPackage.ABSTRACT_SETTING__UUID;

    /**
     * The feature id for the '<em><b>Setting Hash</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_SETTING__SETTING_HASH = COREPackage.ABSTRACT_SETTING__SETTING_HASH;

    /**
     * The feature id for the '<em><b>Pattern Nos</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_SETTING__PATTERN_NOS = COREPackage.ABSTRACT_SETTING__PATTERN_NOS;

    /**
     * The feature id for the '<em><b>Begin</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_SETTING__BEGIN = COREPackage.ABSTRACT_SETTING__BEGIN;

    /**
     * The feature id for the '<em><b>End</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_SETTING__END = COREPackage.ABSTRACT_SETTING__END;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_SETTING__COUNT = COREPackage.ABSTRACT_SETTING__COUNT;

    /**
     * The feature id for the '<em><b>Abstract Root</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int SCS_SETTING__ABSTRACT_ROOT = COREPackage.ABSTRACT_SETTING__ABSTRACT_ROOT;

    /**
     * The feature id for the '<em><b>Patterns</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int SCS_SETTING__PATTERNS = COREPackage.ABSTRACT_SETTING_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Setting</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_SETTING_FEATURE_COUNT = COREPackage.ABSTRACT_SETTING_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Setting</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCS_SETTING_OPERATION_COUNT = COREPackage.ABSTRACT_SETTING_OPERATION_COUNT + 0;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scs.SCSRoot <em>Root</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.scs.SCSRoot
     * @generated
     */
    EClass getSCSRoot();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scs.SCSRoot#getAll <em>All</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>All</em>'.
     * @see com.zipc.garden.model.scs.SCSRoot#getAll()
     * @see #getSCSRoot()
     * @generated
     */
    EAttribute getSCSRoot_All();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scs.SCSRoot#getBegin <em>Begin</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Begin</em>'.
     * @see com.zipc.garden.model.scs.SCSRoot#getBegin()
     * @see #getSCSRoot()
     * @generated
     */
    EAttribute getSCSRoot_Begin();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scs.SCSRoot#getEnd <em>End</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>End</em>'.
     * @see com.zipc.garden.model.scs.SCSRoot#getEnd()
     * @see #getSCSRoot()
     * @generated
     */
    EAttribute getSCSRoot_End();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scs.SCSRoot#getStatus <em>Status</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Status</em>'.
     * @see com.zipc.garden.model.scs.SCSRoot#getStatus()
     * @see #getSCSRoot()
     * @generated
     */
    EAttribute getSCSRoot_Status();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scs.SCSRoot#getMessage <em>Message</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Message</em>'.
     * @see com.zipc.garden.model.scs.SCSRoot#getMessage()
     * @see #getSCSRoot()
     * @generated
     */
    EAttribute getSCSRoot_Message();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scs.SCSPattern <em>Pattern</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Pattern</em>'.
     * @see com.zipc.garden.model.scs.SCSPattern
     * @generated
     */
    EClass getSCSPattern();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scs.SCSPattern#getLsc <em>Lsc</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Lsc</em>'.
     * @see com.zipc.garden.model.scs.SCSPattern#getLsc()
     * @see #getSCSPattern()
     * @generated
     */
    EAttribute getSCSPattern_Lsc();

    /**
     * Returns the meta object for the containment reference list
     * '{@link com.zipc.garden.model.scs.SCSPattern#getPatternreferences <em>Patternreferences</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Patternreferences</em>'.
     * @see com.zipc.garden.model.scs.SCSPattern#getPatternreferences()
     * @see #getSCSPattern()
     * @generated
     */
    EReference getSCSPattern_Patternreferences();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scs.SCSPattern#getId <em>Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see com.zipc.garden.model.scs.SCSPattern#getId()
     * @see #getSCSPattern()
     * @generated
     */
    EAttribute getSCSPattern_Id();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scs.SCSPattern#getPatternValue <em>Pattern
     * Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Pattern Value</em>'.
     * @see com.zipc.garden.model.scs.SCSPattern#getPatternValue()
     * @see #getSCSPattern()
     * @generated
     */
    EAttribute getSCSPattern_PatternValue();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scs.SCSPattern#getCscUuid <em>Csc Uuid</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Csc Uuid</em>'.
     * @see com.zipc.garden.model.scs.SCSPattern#getCscUuid()
     * @see #getSCSPattern()
     * @generated
     */
    EAttribute getSCSPattern_CscUuid();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scs.SCSPattern#getCscFileName <em>Csc File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Csc File Name</em>'.
     * @see com.zipc.garden.model.scs.SCSPattern#getCscFileName()
     * @see #getSCSPattern()
     * @generated
     */
    EAttribute getSCSPattern_CscFileName();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scs.SCSPatternReference <em>Pattern Reference</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Pattern Reference</em>'.
     * @see com.zipc.garden.model.scs.SCSPatternReference
     * @generated
     */
    EClass getSCSPatternReference();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scs.SCSPatternReference#getFileId <em>File
     * Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>File Id</em>'.
     * @see com.zipc.garden.model.scs.SCSPatternReference#getFileId()
     * @see #getSCSPatternReference()
     * @generated
     */
    EAttribute getSCSPatternReference_FileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scs.SCSPatternReference#getPatternId <em>Pattern
     * Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Pattern Id</em>'.
     * @see com.zipc.garden.model.scs.SCSPatternReference#getPatternId()
     * @see #getSCSPatternReference()
     * @generated
     */
    EAttribute getSCSPatternReference_PatternId();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scs.SCSSetting <em>Setting</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Setting</em>'.
     * @see com.zipc.garden.model.scs.SCSSetting
     * @generated
     */
    EClass getSCSSetting();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.scs.SCSSetting#getPatterns
     * <em>Patterns</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Patterns</em>'.
     * @see com.zipc.garden.model.scs.SCSSetting#getPatterns()
     * @see #getSCSSetting()
     * @generated
     */
    EReference getSCSSetting_Patterns();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    SCSFactory getSCSFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scs.impl.SCSRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.scs.impl.SCSRootImpl
         * @see com.zipc.garden.model.scs.impl.SCSPackageImpl#getSCSRoot()
         * @generated
         */
        EClass SCS_ROOT = eINSTANCE.getSCSRoot();

        /**
         * The meta object literal for the '<em><b>All</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SCS_ROOT__ALL = eINSTANCE.getSCSRoot_All();

        /**
         * The meta object literal for the '<em><b>Begin</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SCS_ROOT__BEGIN = eINSTANCE.getSCSRoot_Begin();

        /**
         * The meta object literal for the '<em><b>End</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SCS_ROOT__END = eINSTANCE.getSCSRoot_End();

        /**
         * The meta object literal for the '<em><b>Status</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SCS_ROOT__STATUS = eINSTANCE.getSCSRoot_Status();

        /**
         * The meta object literal for the '<em><b>Message</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCS_ROOT__MESSAGE = eINSTANCE.getSCSRoot_Message();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scs.impl.SCSPatternImpl <em>Pattern</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.scs.impl.SCSPatternImpl
         * @see com.zipc.garden.model.scs.impl.SCSPackageImpl#getSCSPattern()
         * @generated
         */
        EClass SCS_PATTERN = eINSTANCE.getSCSPattern();

        /**
         * The meta object literal for the '<em><b>Lsc</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SCS_PATTERN__LSC = eINSTANCE.getSCSPattern_Lsc();

        /**
         * The meta object literal for the '<em><b>Patternreferences</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference SCS_PATTERN__PATTERNREFERENCES = eINSTANCE.getSCSPattern_Patternreferences();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCS_PATTERN__ID = eINSTANCE.getSCSPattern_Id();

        /**
         * The meta object literal for the '<em><b>Pattern Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCS_PATTERN__PATTERN_VALUE = eINSTANCE.getSCSPattern_PatternValue();

        /**
         * The meta object literal for the '<em><b>Csc Uuid</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCS_PATTERN__CSC_UUID = eINSTANCE.getSCSPattern_CscUuid();

        /**
         * The meta object literal for the '<em><b>Csc File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCS_PATTERN__CSC_FILE_NAME = eINSTANCE.getSCSPattern_CscFileName();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scs.impl.SCSPatternReferenceImpl <em>Pattern
         * Reference</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.scs.impl.SCSPatternReferenceImpl
         * @see com.zipc.garden.model.scs.impl.SCSPackageImpl#getSCSPatternReference()
         * @generated
         */
        EClass SCS_PATTERN_REFERENCE = eINSTANCE.getSCSPatternReference();

        /**
         * The meta object literal for the '<em><b>File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCS_PATTERN_REFERENCE__FILE_ID = eINSTANCE.getSCSPatternReference_FileId();

        /**
         * The meta object literal for the '<em><b>Pattern Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCS_PATTERN_REFERENCE__PATTERN_ID = eINSTANCE.getSCSPatternReference_PatternId();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scs.impl.SCSSettingImpl <em>Setting</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.scs.impl.SCSSettingImpl
         * @see com.zipc.garden.model.scs.impl.SCSPackageImpl#getSCSSetting()
         * @generated
         */
        EClass SCS_SETTING = eINSTANCE.getSCSSetting();

        /**
         * The meta object literal for the '<em><b>Patterns</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference SCS_SETTING__PATTERNS = eINSTANCE.getSCSSetting_Patterns();

    }

} // SCSPackage
