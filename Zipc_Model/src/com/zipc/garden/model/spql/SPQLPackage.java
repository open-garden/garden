/**
 */
package com.zipc.garden.model.spql;

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
 * @see com.zipc.garden.model.spql.SPQLFactory
 * @model kind="package"
 * @generated
 */
public interface SPQLPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "spql";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.spql";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "SPQL";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    SPQLPackage eINSTANCE = com.zipc.garden.model.spql.impl.SPQLPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.spql.impl.SPQLRootImpl <em>Root</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.spql.impl.SPQLRootImpl
     * @see com.zipc.garden.model.spql.impl.SPQLPackageImpl#getSPQLRoot()
     * @generated
     */
    int SPQL_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPQL_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPQL_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPQL_ROOT__UUID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Settings</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int SPQL_ROOT__SETTINGS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPQL_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPQL_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.spql.impl.SPQLSettingImpl <em>Setting</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.spql.impl.SPQLSettingImpl
     * @see com.zipc.garden.model.spql.impl.SPQLPackageImpl#getSPQLSetting()
     * @generated
     */
    int SPQL_SETTING = 1;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPQL_SETTING__UUID = 0;

    /**
     * The feature id for the '<em><b>Query</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPQL_SETTING__QUERY = 1;

    /**
     * The feature id for the '<em><b>Result</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPQL_SETTING__RESULT = 2;

    /**
     * The number of structural features of the '<em>Setting</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPQL_SETTING_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Setting</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPQL_SETTING_OPERATION_COUNT = 0;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.spql.SPQLRoot <em>Root</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.spql.SPQLRoot
     * @generated
     */
    EClass getSPQLRoot();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.spql.SPQLRoot#getUuid <em>Uuid</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Uuid</em>'.
     * @see com.zipc.garden.model.spql.SPQLRoot#getUuid()
     * @see #getSPQLRoot()
     * @generated
     */
    EAttribute getSPQLRoot_Uuid();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.spql.SPQLRoot#getSettings
     * <em>Settings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Settings</em>'.
     * @see com.zipc.garden.model.spql.SPQLRoot#getSettings()
     * @see #getSPQLRoot()
     * @generated
     */
    EReference getSPQLRoot_Settings();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.spql.SPQLSetting <em>Setting</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Setting</em>'.
     * @see com.zipc.garden.model.spql.SPQLSetting
     * @generated
     */
    EClass getSPQLSetting();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.spql.SPQLSetting#getUuid <em>Uuid</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Uuid</em>'.
     * @see com.zipc.garden.model.spql.SPQLSetting#getUuid()
     * @see #getSPQLSetting()
     * @generated
     */
    EAttribute getSPQLSetting_Uuid();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.spql.SPQLSetting#getQuery <em>Query</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Query</em>'.
     * @see com.zipc.garden.model.spql.SPQLSetting#getQuery()
     * @see #getSPQLSetting()
     * @generated
     */
    EAttribute getSPQLSetting_Query();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.spql.SPQLSetting#getResult <em>Result</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Result</em>'.
     * @see com.zipc.garden.model.spql.SPQLSetting#getResult()
     * @see #getSPQLSetting()
     * @generated
     */
    EAttribute getSPQLSetting_Result();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    SPQLFactory getSPQLFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.spql.impl.SPQLRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.spql.impl.SPQLRootImpl
         * @see com.zipc.garden.model.spql.impl.SPQLPackageImpl#getSPQLRoot()
         * @generated
         */
        EClass SPQL_ROOT = eINSTANCE.getSPQLRoot();

        /**
         * The meta object literal for the '<em><b>Uuid</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SPQL_ROOT__UUID = eINSTANCE.getSPQLRoot_Uuid();

        /**
         * The meta object literal for the '<em><b>Settings</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference SPQL_ROOT__SETTINGS = eINSTANCE.getSPQLRoot_Settings();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.spql.impl.SPQLSettingImpl <em>Setting</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.spql.impl.SPQLSettingImpl
         * @see com.zipc.garden.model.spql.impl.SPQLPackageImpl#getSPQLSetting()
         * @generated
         */
        EClass SPQL_SETTING = eINSTANCE.getSPQLSetting();

        /**
         * The meta object literal for the '<em><b>Uuid</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SPQL_SETTING__UUID = eINSTANCE.getSPQLSetting_Uuid();

        /**
         * The meta object literal for the '<em><b>Query</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SPQL_SETTING__QUERY = eINSTANCE.getSPQLSetting_Query();

        /**
         * The meta object literal for the '<em><b>Result</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SPQL_SETTING__RESULT = eINSTANCE.getSPQLSetting_Result();

    }

} // SPQLPackage
