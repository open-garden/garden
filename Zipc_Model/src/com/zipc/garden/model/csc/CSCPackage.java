/**
 */
package com.zipc.garden.model.csc;

import com.zipc.garden.model.core.COREPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see com.zipc.garden.model.csc.CSCFactory
 * @model kind="package"
 * @generated
 */
public interface CSCPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "csc";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.csc";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "CSC";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    CSCPackage eINSTANCE = com.zipc.garden.model.csc.impl.CSCPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.csc.impl.CSCRootImpl <em>Root</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.csc.impl.CSCRootImpl
     * @see com.zipc.garden.model.csc.impl.CSCPackageImpl#getCSCRoot()
     * @generated
     */
    int CSC_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSC_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSC_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSC_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CSC_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.csc.CSCRoot <em>Root</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.csc.CSCRoot
     * @generated
     */
    EClass getCSCRoot();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    CSCFactory getCSCFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.csc.impl.CSCRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.csc.impl.CSCRootImpl
         * @see com.zipc.garden.model.csc.impl.CSCPackageImpl#getCSCRoot()
         * @generated
         */
        EClass CSC_ROOT = eINSTANCE.getCSCRoot();

    }

} // CSCPackage
