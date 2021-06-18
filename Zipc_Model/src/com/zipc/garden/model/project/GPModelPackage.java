/**
 */
package com.zipc.garden.model.project;

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
 * @see com.zipc.garden.model.project.GPModelFactory
 * @model kind="package"
 * @generated
 */
public interface GPModelPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "project";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.model.project";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "GP";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    GPModelPackage eINSTANCE = com.zipc.garden.model.project.impl.GPModelPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.project.impl.GPResourceImpl <em>GP Resource</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.project.impl.GPResourceImpl
     * @see com.zipc.garden.model.project.impl.GPModelPackageImpl#getGPResource()
     * @generated
     */
    int GP_RESOURCE = 0;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GP_RESOURCE__PARENT = 0;

    /**
     * The feature id for the '<em><b>Children</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GP_RESOURCE__CHILDREN = 1;

    /**
     * The feature id for the '<em><b>Directory</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GP_RESOURCE__DIRECTORY = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GP_RESOURCE__NAME = 3;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GP_RESOURCE__ID = 4;

    /**
     * The number of structural features of the '<em>GP Resource</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GP_RESOURCE_FEATURE_COUNT = 5;

    /**
     * The number of operations of the '<em>GP Resource</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GP_RESOURCE_OPERATION_COUNT = 0;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.project.GPResource <em>GP Resource</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>GP Resource</em>'.
     * @see com.zipc.garden.model.project.GPResource
     * @generated
     */
    EClass getGPResource();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.project.GPResource#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Parent</em>'.
     * @see com.zipc.garden.model.project.GPResource#getParent()
     * @see #getGPResource()
     * @generated
     */
    EReference getGPResource_Parent();

    /**
     * Returns the meta object for the reference list '{@link com.zipc.garden.model.project.GPResource#getChildren
     * <em>Children</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Children</em>'.
     * @see com.zipc.garden.model.project.GPResource#getChildren()
     * @see #getGPResource()
     * @generated
     */
    EReference getGPResource_Children();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.project.GPResource#isDirectory
     * <em>Directory</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Directory</em>'.
     * @see com.zipc.garden.model.project.GPResource#isDirectory()
     * @see #getGPResource()
     * @generated
     */
    EAttribute getGPResource_Directory();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.project.GPResource#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.project.GPResource#getName()
     * @see #getGPResource()
     * @generated
     */
    EAttribute getGPResource_Name();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.project.GPResource#getId <em>Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see com.zipc.garden.model.project.GPResource#getId()
     * @see #getGPResource()
     * @generated
     */
    EAttribute getGPResource_Id();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    GPModelFactory getGPModelFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.project.impl.GPResourceImpl <em>GP Resource</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.project.impl.GPResourceImpl
         * @see com.zipc.garden.model.project.impl.GPModelPackageImpl#getGPResource()
         * @generated
         */
        EClass GP_RESOURCE = eINSTANCE.getGPResource();

        /**
         * The meta object literal for the '<em><b>Parent</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference GP_RESOURCE__PARENT = eINSTANCE.getGPResource_Parent();

        /**
         * The meta object literal for the '<em><b>Children</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference GP_RESOURCE__CHILDREN = eINSTANCE.getGPResource_Children();

        /**
         * The meta object literal for the '<em><b>Directory</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute GP_RESOURCE__DIRECTORY = eINSTANCE.getGPResource_Directory();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute GP_RESOURCE__NAME = eINSTANCE.getGPResource_Name();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute GP_RESOURCE__ID = eINSTANCE.getGPResource_Id();

    }

} // GPModelPackage
