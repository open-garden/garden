/**
 */
package com.zipc.garden.model.tp;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import com.zipc.garden.model.core.COREPackage;

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
 * @see com.zipc.garden.model.tp.TPFactory
 * @model kind="package"
 * @generated
 */
public interface TPPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "tp";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.tp";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "TP";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    TPPackage eINSTANCE = com.zipc.garden.model.tp.impl.TPPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.tp.impl.TPRootImpl <em>Root</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.tp.impl.TPRootImpl
     * @see com.zipc.garden.model.tp.impl.TPPackageImpl#getTPRoot()
     * @generated
     */
    int TP_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>Max</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ROOT__MAX = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Row Ids</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ROOT__ROW_IDS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Settings</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int TP_ROOT__SETTINGS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>All</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ROOT__ALL = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ROOT__MESSAGE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Status</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ROOT__STATUS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>End</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ROOT__END = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Begin</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ROOT__BEGIN = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.tp.impl.TPTSDPatternImpl <em>TSD Pattern</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.tp.impl.TPTSDPatternImpl
     * @see com.zipc.garden.model.tp.impl.TPPackageImpl#getTPTSDPattern()
     * @generated
     */
    int TP_TSD_PATTERN = 1;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_TSD_PATTERN__ELEMENTS = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_TSD_PATTERN__ID = 1;

    /**
     * The feature id for the '<em><b>Pattern Elements</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_TSD_PATTERN__PATTERN_ELEMENTS = 2;

    /**
     * The number of structural features of the '<em>TSD Pattern</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_TSD_PATTERN_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>TSD Pattern</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_TSD_PATTERN_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.tp.impl.TPElementImpl <em>Element</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.tp.impl.TPElementImpl
     * @see com.zipc.garden.model.tp.impl.TPPackageImpl#getTPElement()
     * @generated
     */
    int TP_ELEMENT = 2;

    /**
     * The feature id for the '<em><b>Full Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ELEMENT__FULL_PATH = 0;

    /**
     * The feature id for the '<em><b>Simple Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ELEMENT__SIMPLE_PATH = 1;

    /**
     * The number of structural features of the '<em>Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ELEMENT_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_ELEMENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.tp.impl.TPSettingImpl <em>Setting</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.tp.impl.TPSettingImpl
     * @see com.zipc.garden.model.tp.impl.TPPackageImpl#getTPSetting()
     * @generated
     */
    int TP_SETTING = 3;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_SETTING__UUID = COREPackage.ABSTRACT_SETTING__UUID;

    /**
     * The feature id for the '<em><b>Setting Hash</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_SETTING__SETTING_HASH = COREPackage.ABSTRACT_SETTING__SETTING_HASH;

    /**
     * The feature id for the '<em><b>Pattern Nos</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_SETTING__PATTERN_NOS = COREPackage.ABSTRACT_SETTING__PATTERN_NOS;

    /**
     * The feature id for the '<em><b>Begin</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_SETTING__BEGIN = COREPackage.ABSTRACT_SETTING__BEGIN;

    /**
     * The feature id for the '<em><b>End</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_SETTING__END = COREPackage.ABSTRACT_SETTING__END;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_SETTING__COUNT = COREPackage.ABSTRACT_SETTING__COUNT;

    /**
     * The feature id for the '<em><b>Abstract Root</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int TP_SETTING__ABSTRACT_ROOT = COREPackage.ABSTRACT_SETTING__ABSTRACT_ROOT;

    /**
     * The feature id for the '<em><b>Header</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_SETTING__HEADER = COREPackage.ABSTRACT_SETTING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Title</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_SETTING__TITLE = COREPackage.ABSTRACT_SETTING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int TP_SETTING__ELEMENTS = COREPackage.ABSTRACT_SETTING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Patterns</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int TP_SETTING__PATTERNS = COREPackage.ABSTRACT_SETTING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Pattern Elements</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_SETTING__PATTERN_ELEMENTS = COREPackage.ABSTRACT_SETTING_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Setting</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_SETTING_FEATURE_COUNT = COREPackage.ABSTRACT_SETTING_FEATURE_COUNT + 5;

    /**
     * The number of operations of the '<em>Setting</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_SETTING_OPERATION_COUNT = COREPackage.ABSTRACT_SETTING_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.tp.impl.TPPatternElementImpl <em>Pattern Element</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.tp.impl.TPPatternElementImpl
     * @see com.zipc.garden.model.tp.impl.TPPackageImpl#getTPPatternElement()
     * @generated
     */
    int TP_PATTERN_ELEMENT = 4;

    /**
     * The feature id for the '<em><b>Checked</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_PATTERN_ELEMENT__CHECKED = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_PATTERN_ELEMENT__NAME = 1;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_PATTERN_ELEMENT__VALUE = 2;

    /**
     * The number of structural features of the '<em>Pattern Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_PATTERN_ELEMENT_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Pattern Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TP_PATTERN_ELEMENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.tp.PathType <em>Path Type</em>}' enum. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.tp.PathType
     * @see com.zipc.garden.model.tp.impl.TPPackageImpl#getPathType()
     * @generated
     */
    int PATH_TYPE = 5;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.tp.TPRoot <em>Root</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.tp.TPRoot
     * @generated
     */
    EClass getTPRoot();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tp.TPRoot#getAll <em>All</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>All</em>'.
     * @see com.zipc.garden.model.tp.TPRoot#getAll()
     * @see #getTPRoot()
     * @generated
     */
    EAttribute getTPRoot_All();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tp.TPRoot#getMessage <em>Message</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Message</em>'.
     * @see com.zipc.garden.model.tp.TPRoot#getMessage()
     * @see #getTPRoot()
     * @generated
     */
    EAttribute getTPRoot_Message();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tp.TPRoot#getStatus <em>Status</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Status</em>'.
     * @see com.zipc.garden.model.tp.TPRoot#getStatus()
     * @see #getTPRoot()
     * @generated
     */
    EAttribute getTPRoot_Status();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tp.TPRoot#getEnd <em>End</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>End</em>'.
     * @see com.zipc.garden.model.tp.TPRoot#getEnd()
     * @see #getTPRoot()
     * @generated
     */
    EAttribute getTPRoot_End();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tp.TPRoot#getBegin <em>Begin</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Begin</em>'.
     * @see com.zipc.garden.model.tp.TPRoot#getBegin()
     * @see #getTPRoot()
     * @generated
     */
    EAttribute getTPRoot_Begin();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.tp.TPTSDPattern <em>TSD Pattern</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>TSD Pattern</em>'.
     * @see com.zipc.garden.model.tp.TPTSDPattern
     * @generated
     */
    EClass getTPTSDPattern();

    /**
     * Returns the meta object for the reference list '{@link com.zipc.garden.model.tp.TPTSDPattern#getElements
     * <em>Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Elements</em>'.
     * @see com.zipc.garden.model.tp.TPTSDPattern#getElements()
     * @see #getTPTSDPattern()
     * @generated
     */
    EReference getTPTSDPattern_Elements();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tp.TPTSDPattern#getId <em>Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see com.zipc.garden.model.tp.TPTSDPattern#getId()
     * @see #getTPTSDPattern()
     * @generated
     */
    EAttribute getTPTSDPattern_Id();

    /**
     * Returns the meta object for the reference list '{@link com.zipc.garden.model.tp.TPTSDPattern#getPatternElements
     * <em>Pattern Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Pattern Elements</em>'.
     * @see com.zipc.garden.model.tp.TPTSDPattern#getPatternElements()
     * @see #getTPTSDPattern()
     * @generated
     */
    EReference getTPTSDPattern_PatternElements();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.tp.TPElement <em>Element</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Element</em>'.
     * @see com.zipc.garden.model.tp.TPElement
     * @generated
     */
    EClass getTPElement();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tp.TPElement#getFullPath <em>Full Path</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Full Path</em>'.
     * @see com.zipc.garden.model.tp.TPElement#getFullPath()
     * @see #getTPElement()
     * @generated
     */
    EAttribute getTPElement_FullPath();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tp.TPElement#getSimplePath <em>Simple
     * Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Simple Path</em>'.
     * @see com.zipc.garden.model.tp.TPElement#getSimplePath()
     * @see #getTPElement()
     * @generated
     */
    EAttribute getTPElement_SimplePath();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.tp.TPSetting <em>Setting</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Setting</em>'.
     * @see com.zipc.garden.model.tp.TPSetting
     * @generated
     */
    EClass getTPSetting();

    /**
     * Returns the meta object for the attribute list '{@link com.zipc.garden.model.tp.TPSetting#getHeader <em>Header</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Header</em>'.
     * @see com.zipc.garden.model.tp.TPSetting#getHeader()
     * @see #getTPSetting()
     * @generated
     */
    EAttribute getTPSetting_Header();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tp.TPSetting#getTitle <em>Title</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Title</em>'.
     * @see com.zipc.garden.model.tp.TPSetting#getTitle()
     * @see #getTPSetting()
     * @generated
     */
    EAttribute getTPSetting_Title();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.tp.TPSetting#getElements
     * <em>Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Elements</em>'.
     * @see com.zipc.garden.model.tp.TPSetting#getElements()
     * @see #getTPSetting()
     * @generated
     */
    EReference getTPSetting_Elements();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.tp.TPSetting#getPatterns
     * <em>Patterns</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Patterns</em>'.
     * @see com.zipc.garden.model.tp.TPSetting#getPatterns()
     * @see #getTPSetting()
     * @generated
     */
    EReference getTPSetting_Patterns();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.tp.TPSetting#getPatternElements
     * <em>Pattern Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Pattern Elements</em>'.
     * @see com.zipc.garden.model.tp.TPSetting#getPatternElements()
     * @see #getTPSetting()
     * @generated
     */
    EReference getTPSetting_PatternElements();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.tp.TPPatternElement <em>Pattern Element</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Pattern Element</em>'.
     * @see com.zipc.garden.model.tp.TPPatternElement
     * @generated
     */
    EClass getTPPatternElement();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tp.TPPatternElement#isChecked <em>Checked</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Checked</em>'.
     * @see com.zipc.garden.model.tp.TPPatternElement#isChecked()
     * @see #getTPPatternElement()
     * @generated
     */
    EAttribute getTPPatternElement_Checked();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tp.TPPatternElement#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.tp.TPPatternElement#getName()
     * @see #getTPPatternElement()
     * @generated
     */
    EAttribute getTPPatternElement_Name();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tp.TPPatternElement#getValue <em>Value</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see com.zipc.garden.model.tp.TPPatternElement#getValue()
     * @see #getTPPatternElement()
     * @generated
     */
    EAttribute getTPPatternElement_Value();

    /**
     * Returns the meta object for enum '{@link com.zipc.garden.model.tp.PathType <em>Path Type</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Path Type</em>'.
     * @see com.zipc.garden.model.tp.PathType
     * @generated
     */
    EEnum getPathType();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    TPFactory getTPFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.tp.impl.TPRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.tp.impl.TPRootImpl
         * @see com.zipc.garden.model.tp.impl.TPPackageImpl#getTPRoot()
         * @generated
         */
        EClass TP_ROOT = eINSTANCE.getTPRoot();

        /**
         * The meta object literal for the '<em><b>All</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TP_ROOT__ALL = eINSTANCE.getTPRoot_All();

        /**
         * The meta object literal for the '<em><b>Message</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute TP_ROOT__MESSAGE = eINSTANCE.getTPRoot_Message();

        /**
         * The meta object literal for the '<em><b>Status</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TP_ROOT__STATUS = eINSTANCE.getTPRoot_Status();

        /**
         * The meta object literal for the '<em><b>End</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TP_ROOT__END = eINSTANCE.getTPRoot_End();

        /**
         * The meta object literal for the '<em><b>Begin</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TP_ROOT__BEGIN = eINSTANCE.getTPRoot_Begin();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.tp.impl.TPTSDPatternImpl <em>TSD Pattern</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.tp.impl.TPTSDPatternImpl
         * @see com.zipc.garden.model.tp.impl.TPPackageImpl#getTPTSDPattern()
         * @generated
         */
        EClass TP_TSD_PATTERN = eINSTANCE.getTPTSDPattern();

        /**
         * The meta object literal for the '<em><b>Elements</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference TP_TSD_PATTERN__ELEMENTS = eINSTANCE.getTPTSDPattern_Elements();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute TP_TSD_PATTERN__ID = eINSTANCE.getTPTSDPattern_Id();

        /**
         * The meta object literal for the '<em><b>Pattern Elements</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TP_TSD_PATTERN__PATTERN_ELEMENTS = eINSTANCE.getTPTSDPattern_PatternElements();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.tp.impl.TPElementImpl <em>Element</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.tp.impl.TPElementImpl
         * @see com.zipc.garden.model.tp.impl.TPPackageImpl#getTPElement()
         * @generated
         */
        EClass TP_ELEMENT = eINSTANCE.getTPElement();

        /**
         * The meta object literal for the '<em><b>Full Path</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute TP_ELEMENT__FULL_PATH = eINSTANCE.getTPElement_FullPath();

        /**
         * The meta object literal for the '<em><b>Simple Path</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute TP_ELEMENT__SIMPLE_PATH = eINSTANCE.getTPElement_SimplePath();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.tp.impl.TPSettingImpl <em>Setting</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.tp.impl.TPSettingImpl
         * @see com.zipc.garden.model.tp.impl.TPPackageImpl#getTPSetting()
         * @generated
         */
        EClass TP_SETTING = eINSTANCE.getTPSetting();

        /**
         * The meta object literal for the '<em><b>Header</b></em>' attribute list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute TP_SETTING__HEADER = eINSTANCE.getTPSetting_Header();

        /**
         * The meta object literal for the '<em><b>Title</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TP_SETTING__TITLE = eINSTANCE.getTPSetting_Title();

        /**
         * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference TP_SETTING__ELEMENTS = eINSTANCE.getTPSetting_Elements();

        /**
         * The meta object literal for the '<em><b>Patterns</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference TP_SETTING__PATTERNS = eINSTANCE.getTPSetting_Patterns();

        /**
         * The meta object literal for the '<em><b>Pattern Elements</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference TP_SETTING__PATTERN_ELEMENTS = eINSTANCE.getTPSetting_PatternElements();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.tp.impl.TPPatternElementImpl <em>Pattern Element</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.tp.impl.TPPatternElementImpl
         * @see com.zipc.garden.model.tp.impl.TPPackageImpl#getTPPatternElement()
         * @generated
         */
        EClass TP_PATTERN_ELEMENT = eINSTANCE.getTPPatternElement();

        /**
         * The meta object literal for the '<em><b>Checked</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute TP_PATTERN_ELEMENT__CHECKED = eINSTANCE.getTPPatternElement_Checked();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TP_PATTERN_ELEMENT__NAME = eINSTANCE.getTPPatternElement_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TP_PATTERN_ELEMENT__VALUE = eINSTANCE.getTPPatternElement_Value();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.tp.PathType <em>Path Type</em>}' enum. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.tp.PathType
         * @see com.zipc.garden.model.tp.impl.TPPackageImpl#getPathType()
         * @generated
         */
        EEnum PATH_TYPE = eINSTANCE.getPathType();

    }

} // TPPackage
