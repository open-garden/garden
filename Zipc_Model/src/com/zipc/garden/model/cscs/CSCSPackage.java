/**
 */
package com.zipc.garden.model.cscs;

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
 * @see com.zipc.garden.model.cscs.CSCSFactory
 * @model kind="package"
 * @generated
 */
public interface CSCSPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "cscs";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.cscs";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "CSCS";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    CSCSPackage eINSTANCE = com.zipc.garden.model.cscs.impl.CSCSPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.cscs.impl.CSCSRootImpl <em>Root</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.cscs.impl.CSCSRootImpl
     * @see com.zipc.garden.model.cscs.impl.CSCSPackageImpl#getCSCSRoot()
     * @generated
     */
    int CSCS_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSCS_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSCS_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>All</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSCS_ROOT__ALL = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Begin</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSCS_ROOT__BEGIN = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>End</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSCS_ROOT__END = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Status</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSCS_ROOT__STATUS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSCS_ROOT__MESSAGE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Patterns</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int CSCS_ROOT__PATTERNS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSCS_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSCS_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.cscs.impl.CSCSPatternImpl <em>Pattern</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.cscs.impl.CSCSPatternImpl
     * @see com.zipc.garden.model.cscs.impl.CSCSPackageImpl#getCSCSPattern()
     * @generated
     */
    int CSCS_PATTERN = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSCS_PATTERN__ID = 0;

    /**
     * The feature id for the '<em><b>Pattern Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSCS_PATTERN__PATTERN_VALUE = 1;

    /**
     * The feature id for the '<em><b>Csc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSCS_PATTERN__CSC = 2;

    /**
     * The number of structural features of the '<em>Pattern</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSCS_PATTERN_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Pattern</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSCS_PATTERN_OPERATION_COUNT = 0;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.cscs.CSCSRoot <em>Root</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.cscs.CSCSRoot
     * @generated
     */
    EClass getCSCSRoot();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.cscs.CSCSRoot#getAll <em>All</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>All</em>'.
     * @see com.zipc.garden.model.cscs.CSCSRoot#getAll()
     * @see #getCSCSRoot()
     * @generated
     */
    EAttribute getCSCSRoot_All();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.cscs.CSCSRoot#getBegin <em>Begin</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Begin</em>'.
     * @see com.zipc.garden.model.cscs.CSCSRoot#getBegin()
     * @see #getCSCSRoot()
     * @generated
     */
    EAttribute getCSCSRoot_Begin();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.cscs.CSCSRoot#getEnd <em>End</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>End</em>'.
     * @see com.zipc.garden.model.cscs.CSCSRoot#getEnd()
     * @see #getCSCSRoot()
     * @generated
     */
    EAttribute getCSCSRoot_End();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.cscs.CSCSRoot#getStatus <em>Status</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Status</em>'.
     * @see com.zipc.garden.model.cscs.CSCSRoot#getStatus()
     * @see #getCSCSRoot()
     * @generated
     */
    EAttribute getCSCSRoot_Status();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.cscs.CSCSRoot#getMessage <em>Message</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Message</em>'.
     * @see com.zipc.garden.model.cscs.CSCSRoot#getMessage()
     * @see #getCSCSRoot()
     * @generated
     */
    EAttribute getCSCSRoot_Message();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.cscs.CSCSRoot#getPatterns
     * <em>Patterns</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Patterns</em>'.
     * @see com.zipc.garden.model.cscs.CSCSRoot#getPatterns()
     * @see #getCSCSRoot()
     * @generated
     */
    EReference getCSCSRoot_Patterns();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.cscs.CSCSPattern <em>Pattern</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Pattern</em>'.
     * @see com.zipc.garden.model.cscs.CSCSPattern
     * @generated
     */
    EClass getCSCSPattern();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.cscs.CSCSPattern#getId <em>Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see com.zipc.garden.model.cscs.CSCSPattern#getId()
     * @see #getCSCSPattern()
     * @generated
     */
    EAttribute getCSCSPattern_Id();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.cscs.CSCSPattern#getPatternValue <em>Pattern
     * Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Pattern Value</em>'.
     * @see com.zipc.garden.model.cscs.CSCSPattern#getPatternValue()
     * @see #getCSCSPattern()
     * @generated
     */
    EAttribute getCSCSPattern_PatternValue();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.cscs.CSCSPattern#getCsc <em>Csc</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Csc</em>'.
     * @see com.zipc.garden.model.cscs.CSCSPattern#getCsc()
     * @see #getCSCSPattern()
     * @generated
     */
    EAttribute getCSCSPattern_Csc();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    CSCSFactory getCSCSFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.cscs.impl.CSCSRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.cscs.impl.CSCSRootImpl
         * @see com.zipc.garden.model.cscs.impl.CSCSPackageImpl#getCSCSRoot()
         * @generated
         */
        EClass CSCS_ROOT = eINSTANCE.getCSCSRoot();

        /**
         * The meta object literal for the '<em><b>All</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute CSCS_ROOT__ALL = eINSTANCE.getCSCSRoot_All();

        /**
         * The meta object literal for the '<em><b>Begin</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute CSCS_ROOT__BEGIN = eINSTANCE.getCSCSRoot_Begin();

        /**
         * The meta object literal for the '<em><b>End</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute CSCS_ROOT__END = eINSTANCE.getCSCSRoot_End();

        /**
         * The meta object literal for the '<em><b>Status</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute CSCS_ROOT__STATUS = eINSTANCE.getCSCSRoot_Status();

        /**
         * The meta object literal for the '<em><b>Message</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute CSCS_ROOT__MESSAGE = eINSTANCE.getCSCSRoot_Message();

        /**
         * The meta object literal for the '<em><b>Patterns</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference CSCS_ROOT__PATTERNS = eINSTANCE.getCSCSRoot_Patterns();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.cscs.impl.CSCSPatternImpl <em>Pattern</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.cscs.impl.CSCSPatternImpl
         * @see com.zipc.garden.model.cscs.impl.CSCSPackageImpl#getCSCSPattern()
         * @generated
         */
        EClass CSCS_PATTERN = eINSTANCE.getCSCSPattern();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute CSCS_PATTERN__ID = eINSTANCE.getCSCSPattern_Id();

        /**
         * The meta object literal for the '<em><b>Pattern Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute CSCS_PATTERN__PATTERN_VALUE = eINSTANCE.getCSCSPattern_PatternValue();

        /**
         * The meta object literal for the '<em><b>Csc</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute CSCS_PATTERN__CSC = eINSTANCE.getCSCSPattern_Csc();

    }

} // CSCSPackage
