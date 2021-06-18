/**
 */
package com.zipc.garden.model.lsc;

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
 * @see com.zipc.garden.model.lsc.LSCFactory
 * @model kind="package"
 * @generated
 */
public interface LSCPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "lsc";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.lsc";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "LSC";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    LSCPackage eINSTANCE = com.zipc.garden.model.lsc.impl.LSCPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.lsc.impl.LSCRootImpl <em>Root</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.lsc.impl.LSCRootImpl
     * @see com.zipc.garden.model.lsc.impl.LSCPackageImpl#getLSCRoot()
     * @generated
     */
    int LSC_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LSC_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LSC_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>Scenarios</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int LSC_ROOT__SCENARIOS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LSC_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LSC_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.lsc.impl.LSCScenarioImpl <em>Scenario</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.lsc.impl.LSCScenarioImpl
     * @see com.zipc.garden.model.lsc.impl.LSCPackageImpl#getLSCScenario()
     * @generated
     */
    int LSC_SCENARIO = 1;

    /**
     * The feature id for the '<em><b>Tp Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LSC_SCENARIO__TP_ID = 0;

    /**
     * The feature id for the '<em><b>Bp Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LSC_SCENARIO__BP_ID = 1;

    /**
     * The number of structural features of the '<em>Scenario</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LSC_SCENARIO_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Scenario</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LSC_SCENARIO_OPERATION_COUNT = 0;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.lsc.LSCRoot <em>Root</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.lsc.LSCRoot
     * @generated
     */
    EClass getLSCRoot();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.lsc.LSCRoot#getScenarios
     * <em>Scenarios</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Scenarios</em>'.
     * @see com.zipc.garden.model.lsc.LSCRoot#getScenarios()
     * @see #getLSCRoot()
     * @generated
     */
    EReference getLSCRoot_Scenarios();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.lsc.LSCScenario <em>Scenario</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Scenario</em>'.
     * @see com.zipc.garden.model.lsc.LSCScenario
     * @generated
     */
    EClass getLSCScenario();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.lsc.LSCScenario#getTpId <em>Tp Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Tp Id</em>'.
     * @see com.zipc.garden.model.lsc.LSCScenario#getTpId()
     * @see #getLSCScenario()
     * @generated
     */
    EAttribute getLSCScenario_TpId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.lsc.LSCScenario#getBpId <em>Bp Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bp Id</em>'.
     * @see com.zipc.garden.model.lsc.LSCScenario#getBpId()
     * @see #getLSCScenario()
     * @generated
     */
    EAttribute getLSCScenario_BpId();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    LSCFactory getLSCFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.lsc.impl.LSCRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.lsc.impl.LSCRootImpl
         * @see com.zipc.garden.model.lsc.impl.LSCPackageImpl#getLSCRoot()
         * @generated
         */
        EClass LSC_ROOT = eINSTANCE.getLSCRoot();

        /**
         * The meta object literal for the '<em><b>Scenarios</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference LSC_ROOT__SCENARIOS = eINSTANCE.getLSCRoot_Scenarios();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.lsc.impl.LSCScenarioImpl <em>Scenario</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.lsc.impl.LSCScenarioImpl
         * @see com.zipc.garden.model.lsc.impl.LSCPackageImpl#getLSCScenario()
         * @generated
         */
        EClass LSC_SCENARIO = eINSTANCE.getLSCScenario();

        /**
         * The meta object literal for the '<em><b>Tp Id</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute LSC_SCENARIO__TP_ID = eINSTANCE.getLSCScenario_TpId();

        /**
         * The meta object literal for the '<em><b>Bp Id</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute LSC_SCENARIO__BP_ID = eINSTANCE.getLSCScenario_BpId();

    }

} // LSCPackage
