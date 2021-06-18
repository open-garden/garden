/**
 */
package com.zipc.garden.model.tc;

import com.zipc.garden.model.core.COREPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see com.zipc.garden.model.tc.TCFactory
 * @model kind="package"
 * @generated
 */
public interface TCPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "tc";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.tc";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "TC";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    TCPackage eINSTANCE = com.zipc.garden.model.tc.impl.TCPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.tc.impl.TCRootImpl <em>Root</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.tc.impl.TCRootImpl
     * @see com.zipc.garden.model.tc.impl.TCPackageImpl#getTCRoot()
     * @generated
     */
    int TC_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>Root Nodes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int TC_ROOT__ROOT_NODES = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Showing Hierarchy</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_ROOT__SHOWING_HIERARCHY = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Keywords</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_ROOT__KEYWORDS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.tc.impl.TCNodeImpl <em>Node</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.tc.impl.TCNodeImpl
     * @see com.zipc.garden.model.tc.impl.TCPackageImpl#getTCNode()
     * @generated
     */
    int TC_NODE = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_NODE__NAME = 0;

    /**
     * The feature id for the '<em><b>Checked</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_NODE__CHECKED = 1;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int TC_NODE__CHILDREN = 2;

    /**
     * The feature id for the '<em><b>Child Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_NODE__CHILD_TYPE = 3;

    /**
     * The feature id for the '<em><b>State</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_NODE__STATE = 4;

    /**
     * The feature id for the '<em><b>Inherited</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_NODE__INHERITED = 5;

    /**
     * The feature id for the '<em><b>Optional</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_NODE__OPTIONAL = 6;

    /**
     * The feature id for the '<em><b>Min</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_NODE__MIN = 7;

    /**
     * The feature id for the '<em><b>Max</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_NODE__MAX = 8;

    /**
     * The feature id for the '<em><b>NWise Combination</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_NODE__NWISE_COMBINATION = 9;

    /**
     * The feature id for the '<em><b>Temporary</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_NODE__TEMPORARY = 10;

    /**
     * The feature id for the '<em><b>Properties</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int TC_NODE__PROPERTIES = 11;

    /**
     * The number of structural features of the '<em>Node</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_NODE_FEATURE_COUNT = 12;

    /**
     * The number of operations of the '<em>Node</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_NODE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.tc.impl.TCPropertyImpl <em>Property</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.tc.impl.TCPropertyImpl
     * @see com.zipc.garden.model.tc.impl.TCPackageImpl#getTCProperty()
     * @generated
     */
    int TC_PROPERTY = 2;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_PROPERTY__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_PROPERTY__VALUE = 1;

    /**
     * The number of structural features of the '<em>Property</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_PROPERTY_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Property</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TC_PROPERTY_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.tc.TCNodeState <em>Node State</em>}' enum. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.tc.TCNodeState
     * @see com.zipc.garden.model.tc.impl.TCPackageImpl#getTCNodeState()
     * @generated
     */
    int TC_NODE_STATE = 3;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.tc.TCRoot <em>Root</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.tc.TCRoot
     * @generated
     */
    EClass getTCRoot();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.tc.TCRoot#getRootNodes <em>Root
     * Nodes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Root Nodes</em>'.
     * @see com.zipc.garden.model.tc.TCRoot#getRootNodes()
     * @see #getTCRoot()
     * @generated
     */
    EReference getTCRoot_RootNodes();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tc.TCRoot#getShowingHierarchy <em>Showing
     * Hierarchy</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Showing Hierarchy</em>'.
     * @see com.zipc.garden.model.tc.TCRoot#getShowingHierarchy()
     * @see #getTCRoot()
     * @generated
     */
    EAttribute getTCRoot_ShowingHierarchy();

    /**
     * Returns the meta object for the attribute list '{@link com.zipc.garden.model.tc.TCRoot#getKeywords <em>Keywords</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Keywords</em>'.
     * @see com.zipc.garden.model.tc.TCRoot#getKeywords()
     * @see #getTCRoot()
     * @generated
     */
    EAttribute getTCRoot_Keywords();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.tc.TCNode <em>Node</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Node</em>'.
     * @see com.zipc.garden.model.tc.TCNode
     * @generated
     */
    EClass getTCNode();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tc.TCNode#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.tc.TCNode#getName()
     * @see #getTCNode()
     * @generated
     */
    EAttribute getTCNode_Name();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tc.TCNode#isChecked <em>Checked</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Checked</em>'.
     * @see com.zipc.garden.model.tc.TCNode#isChecked()
     * @see #getTCNode()
     * @generated
     */
    EAttribute getTCNode_Checked();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.tc.TCNode#getChildren
     * <em>Children</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Children</em>'.
     * @see com.zipc.garden.model.tc.TCNode#getChildren()
     * @see #getTCNode()
     * @generated
     */
    EReference getTCNode_Children();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tc.TCNode#getChildType <em>Child Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Child Type</em>'.
     * @see com.zipc.garden.model.tc.TCNode#getChildType()
     * @see #getTCNode()
     * @generated
     */
    EAttribute getTCNode_ChildType();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tc.TCNode#getState <em>State</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>State</em>'.
     * @see com.zipc.garden.model.tc.TCNode#getState()
     * @see #getTCNode()
     * @generated
     */
    EAttribute getTCNode_State();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tc.TCNode#isInherited <em>Inherited</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Inherited</em>'.
     * @see com.zipc.garden.model.tc.TCNode#isInherited()
     * @see #getTCNode()
     * @generated
     */
    EAttribute getTCNode_Inherited();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tc.TCNode#isOptional <em>Optional</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Optional</em>'.
     * @see com.zipc.garden.model.tc.TCNode#isOptional()
     * @see #getTCNode()
     * @generated
     */
    EAttribute getTCNode_Optional();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tc.TCNode#getMin <em>Min</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Min</em>'.
     * @see com.zipc.garden.model.tc.TCNode#getMin()
     * @see #getTCNode()
     * @generated
     */
    EAttribute getTCNode_Min();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tc.TCNode#getMax <em>Max</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Max</em>'.
     * @see com.zipc.garden.model.tc.TCNode#getMax()
     * @see #getTCNode()
     * @generated
     */
    EAttribute getTCNode_Max();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tc.TCNode#getNWiseCombination <em>NWise
     * Combination</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>NWise Combination</em>'.
     * @see com.zipc.garden.model.tc.TCNode#getNWiseCombination()
     * @see #getTCNode()
     * @generated
     */
    EAttribute getTCNode_NWiseCombination();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tc.TCNode#isTemporary <em>Temporary</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Temporary</em>'.
     * @see com.zipc.garden.model.tc.TCNode#isTemporary()
     * @see #getTCNode()
     * @generated
     */
    EAttribute getTCNode_Temporary();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.tc.TCNode#getProperties
     * <em>Properties</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Properties</em>'.
     * @see com.zipc.garden.model.tc.TCNode#getProperties()
     * @see #getTCNode()
     * @generated
     */
    EReference getTCNode_Properties();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.tc.TCProperty <em>Property</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Property</em>'.
     * @see com.zipc.garden.model.tc.TCProperty
     * @generated
     */
    EClass getTCProperty();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tc.TCProperty#getKey <em>Key</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see com.zipc.garden.model.tc.TCProperty#getKey()
     * @see #getTCProperty()
     * @generated
     */
    EAttribute getTCProperty_Key();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tc.TCProperty#getValue <em>Value</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see com.zipc.garden.model.tc.TCProperty#getValue()
     * @see #getTCProperty()
     * @generated
     */
    EAttribute getTCProperty_Value();

    /**
     * Returns the meta object for enum '{@link com.zipc.garden.model.tc.TCNodeState <em>Node State</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Node State</em>'.
     * @see com.zipc.garden.model.tc.TCNodeState
     * @generated
     */
    EEnum getTCNodeState();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    TCFactory getTCFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.tc.impl.TCRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.tc.impl.TCRootImpl
         * @see com.zipc.garden.model.tc.impl.TCPackageImpl#getTCRoot()
         * @generated
         */
        EClass TC_ROOT = eINSTANCE.getTCRoot();

        /**
         * The meta object literal for the '<em><b>Root Nodes</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference TC_ROOT__ROOT_NODES = eINSTANCE.getTCRoot_RootNodes();

        /**
         * The meta object literal for the '<em><b>Showing Hierarchy</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute TC_ROOT__SHOWING_HIERARCHY = eINSTANCE.getTCRoot_ShowingHierarchy();

        /**
         * The meta object literal for the '<em><b>Keywords</b></em>' attribute list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute TC_ROOT__KEYWORDS = eINSTANCE.getTCRoot_Keywords();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.tc.impl.TCNodeImpl <em>Node</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.tc.impl.TCNodeImpl
         * @see com.zipc.garden.model.tc.impl.TCPackageImpl#getTCNode()
         * @generated
         */
        EClass TC_NODE = eINSTANCE.getTCNode();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TC_NODE__NAME = eINSTANCE.getTCNode_Name();

        /**
         * The meta object literal for the '<em><b>Checked</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute TC_NODE__CHECKED = eINSTANCE.getTCNode_Checked();

        /**
         * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference TC_NODE__CHILDREN = eINSTANCE.getTCNode_Children();

        /**
         * The meta object literal for the '<em><b>Child Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute TC_NODE__CHILD_TYPE = eINSTANCE.getTCNode_ChildType();

        /**
         * The meta object literal for the '<em><b>State</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TC_NODE__STATE = eINSTANCE.getTCNode_State();

        /**
         * The meta object literal for the '<em><b>Inherited</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute TC_NODE__INHERITED = eINSTANCE.getTCNode_Inherited();

        /**
         * The meta object literal for the '<em><b>Optional</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute TC_NODE__OPTIONAL = eINSTANCE.getTCNode_Optional();

        /**
         * The meta object literal for the '<em><b>Min</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TC_NODE__MIN = eINSTANCE.getTCNode_Min();

        /**
         * The meta object literal for the '<em><b>Max</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TC_NODE__MAX = eINSTANCE.getTCNode_Max();

        /**
         * The meta object literal for the '<em><b>NWise Combination</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute TC_NODE__NWISE_COMBINATION = eINSTANCE.getTCNode_NWiseCombination();

        /**
         * The meta object literal for the '<em><b>Temporary</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute TC_NODE__TEMPORARY = eINSTANCE.getTCNode_Temporary();

        /**
         * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference TC_NODE__PROPERTIES = eINSTANCE.getTCNode_Properties();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.tc.impl.TCPropertyImpl <em>Property</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.tc.impl.TCPropertyImpl
         * @see com.zipc.garden.model.tc.impl.TCPackageImpl#getTCProperty()
         * @generated
         */
        EClass TC_PROPERTY = eINSTANCE.getTCProperty();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TC_PROPERTY__KEY = eINSTANCE.getTCProperty_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TC_PROPERTY__VALUE = eINSTANCE.getTCProperty_Value();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.tc.TCNodeState <em>Node State</em>}' enum. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.tc.TCNodeState
         * @see com.zipc.garden.model.tc.impl.TCPackageImpl#getTCNodeState()
         * @generated
         */
        EEnum TC_NODE_STATE = eINSTANCE.getTCNodeState();

    }

} // TCPackage
