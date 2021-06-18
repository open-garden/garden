/**
 */
package com.zipc.garden.model.project.impl;

import com.google.gwt.user.client.rpc.IsSerializable;

import com.zipc.garden.model.project.GPModelFactory;
import com.zipc.garden.model.project.GPModelPackage;
import com.zipc.garden.model.project.GPResource;

import org.eclipse.emf.common.util.Reflect;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class GPModelPackageImpl extends EPackageImpl implements GPModelPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass gpResourceEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package, if one already exists. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see com.zipc.garden.model.project.GPModelPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private GPModelPackageImpl() {
        super(eNS_URI, GPModelFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * <p>
     * This method is used to initialize {@link GPModelPackage#eINSTANCE} when that field is accessed. Clients should not invoke
     * it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static GPModelPackage init() {
        if (isInited)
            return (GPModelPackage) EPackage.Registry.INSTANCE.getEPackage(GPModelPackage.eNS_URI);

        initializeRegistryHelpers();

        // Obtain or create and register package
        Object registeredGPModelPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        GPModelPackageImpl theGPModelPackage = registeredGPModelPackage instanceof GPModelPackageImpl ? (GPModelPackageImpl) registeredGPModelPackage : new GPModelPackageImpl();

        isInited = true;

        // Create package meta-data objects
        theGPModelPackage.createPackageContents();

        // Initialize created meta-data
        theGPModelPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theGPModelPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(GPModelPackage.eNS_URI, theGPModelPackage);
        return theGPModelPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static void initializeRegistryHelpers() {
        Reflect.register(GPResource.class, new Reflect.Helper() {
            public boolean isInstance(Object instance) {
                return instance instanceof GPResource;
            }

            public Object newArrayInstance(int size) {
                return new GPResource[size];
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
        protected GPResource gpResource;

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getGPResource() {
        return gpResourceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getGPResource_Parent() {
        return (EReference) gpResourceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getGPResource_Children() {
        return (EReference) gpResourceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getGPResource_Directory() {
        return (EAttribute) gpResourceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getGPResource_Name() {
        return (EAttribute) gpResourceEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getGPResource_Id() {
        return (EAttribute) gpResourceEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public GPModelFactory getGPModelFactory() {
        return (GPModelFactory) getEFactoryInstance();
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
        gpResourceEClass = createEClass(GP_RESOURCE);
        createEReference(gpResourceEClass, GP_RESOURCE__PARENT);
        createEReference(gpResourceEClass, GP_RESOURCE__CHILDREN);
        createEAttribute(gpResourceEClass, GP_RESOURCE__DIRECTORY);
        createEAttribute(gpResourceEClass, GP_RESOURCE__NAME);
        createEAttribute(gpResourceEClass, GP_RESOURCE__ID);
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

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes, features, and operations; add parameters
        initEClass(gpResourceEClass, GPResource.class, "GPResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGPResource_Parent(), this.getGPResource(), this.getGPResource_Children(), "parent", null, 0, 1, GPResource.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGPResource_Children(), this.getGPResource(), this.getGPResource_Parent(), "children", null, 0, -1, GPResource.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGPResource_Directory(), ecorePackage.getEBoolean(), "directory", null, 0, 1, GPResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGPResource_Name(), ecorePackage.getEString(), "name", null, 0, 1, GPResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGPResource_Id(), ecorePackage.getELong(), "id", null, 0, 1, GPResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // GPModelPackageImpl
