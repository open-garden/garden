/**
 */
package com.zipc.garden.model.cb;

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
 * @see com.zipc.garden.model.cb.CBFactory
 * @model kind="package"
 * @generated
 */
public interface CBPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "cb";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.cb";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "CB";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    CBPackage eINSTANCE = com.zipc.garden.model.cb.impl.CBPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.cb.impl.CBRootImpl <em>Root</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.cb.impl.CBRootImpl
     * @see com.zipc.garden.model.cb.impl.CBPackageImpl#getCBRoot()
     * @generated
     */
    int CB_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>Scroll X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ROOT__SCROLL_X = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Scroll Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ROOT__SCROLL_Y = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Position X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ROOT__POSITION_X = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Position Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ROOT__POSITION_Y = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Grid Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ROOT__GRID_SIZE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ROOT__NODES = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Line Mode</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ROOT__LINE_MODE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Logic</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ROOT__LOGIC = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Layout Mode</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ROOT__LAYOUT_MODE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.cb.impl.CBLogicImpl <em>Logic</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.cb.impl.CBLogicImpl
     * @see com.zipc.garden.model.cb.impl.CBPackageImpl#getCBLogic()
     * @generated
     */
    int CB_LOGIC = 1;

    /**
     * The feature id for the '<em><b>Top</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_LOGIC__TOP = COREPackage.ABSTRACT_NODE__TOP;

    /**
     * The feature id for the '<em><b>Left</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_LOGIC__LEFT = COREPackage.ABSTRACT_NODE__LEFT;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_LOGIC__HEIGHT = COREPackage.ABSTRACT_NODE__HEIGHT;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_LOGIC__WIDTH = COREPackage.ABSTRACT_NODE__WIDTH;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int CB_LOGIC__CHILDREN = COREPackage.ABSTRACT_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>File</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_LOGIC__FILE = COREPackage.ABSTRACT_NODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Type</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_LOGIC__TYPE = COREPackage.ABSTRACT_NODE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Logic</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_LOGIC_FEATURE_COUNT = COREPackage.ABSTRACT_NODE_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Logic</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_LOGIC_OPERATION_COUNT = COREPackage.ABSTRACT_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.cb.impl.CBFileImpl <em>File</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.cb.impl.CBFileImpl
     * @see com.zipc.garden.model.cb.impl.CBPackageImpl#getCBFile()
     * @generated
     */
    int CB_FILE = 2;

    /**
     * The feature id for the '<em><b>Top</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_FILE__TOP = COREPackage.ABSTRACT_NODE__TOP;

    /**
     * The feature id for the '<em><b>Left</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_FILE__LEFT = COREPackage.ABSTRACT_NODE__LEFT;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_FILE__HEIGHT = COREPackage.ABSTRACT_NODE__HEIGHT;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_FILE__WIDTH = COREPackage.ABSTRACT_NODE__WIDTH;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_FILE__UUID = COREPackage.ABSTRACT_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_FILE__PATTERN = COREPackage.ABSTRACT_NODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Abstract Root</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int CB_FILE__ABSTRACT_ROOT = COREPackage.ABSTRACT_NODE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Hash</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_FILE__HASH = COREPackage.ABSTRACT_NODE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>File</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_FILE_FEATURE_COUNT = COREPackage.ABSTRACT_NODE_FEATURE_COUNT + 4;

    /**
     * The number of operations of the '<em>File</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_FILE_OPERATION_COUNT = COREPackage.ABSTRACT_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.cb.impl.CBLogicTypeImpl <em>Logic Type</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.cb.impl.CBLogicTypeImpl
     * @see com.zipc.garden.model.cb.impl.CBPackageImpl#getCBLogicType()
     * @generated
     */
    int CB_LOGIC_TYPE = 5;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.cb.impl.CBAllCombinationImpl <em>All Combination</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.cb.impl.CBAllCombinationImpl
     * @see com.zipc.garden.model.cb.impl.CBPackageImpl#getCBAllCombination()
     * @generated
     */
    int CB_ALL_COMBINATION = 3;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.cb.impl.CBPairWiseImpl <em>Pair Wise</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.cb.impl.CBPairWiseImpl
     * @see com.zipc.garden.model.cb.impl.CBPackageImpl#getCBPairWise()
     * @generated
     */
    int CB_PAIR_WISE = 4;

    /**
     * The number of structural features of the '<em>Logic Type</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_LOGIC_TYPE_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Logic Type</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_LOGIC_TYPE_OPERATION_COUNT = 0;

    /**
     * The number of structural features of the '<em>All Combination</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ALL_COMBINATION_FEATURE_COUNT = CB_LOGIC_TYPE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>All Combination</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_ALL_COMBINATION_OPERATION_COUNT = CB_LOGIC_TYPE_OPERATION_COUNT + 0;

    /**
     * The number of structural features of the '<em>Pair Wise</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_PAIR_WISE_FEATURE_COUNT = CB_LOGIC_TYPE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Pair Wise</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CB_PAIR_WISE_OPERATION_COUNT = CB_LOGIC_TYPE_OPERATION_COUNT + 0;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.cb.CBRoot <em>Root</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.cb.CBRoot
     * @generated
     */
    EClass getCBRoot();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.cb.CBRoot#getLogic <em>Logic</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Logic</em>'.
     * @see com.zipc.garden.model.cb.CBRoot#getLogic()
     * @see #getCBRoot()
     * @generated
     */
    EReference getCBRoot_Logic();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.cb.CBRoot#getLayoutMode <em>Layout Mode</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Layout Mode</em>'.
     * @see com.zipc.garden.model.cb.CBRoot#getLayoutMode()
     * @see #getCBRoot()
     * @generated
     */
    EAttribute getCBRoot_LayoutMode();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.cb.CBLogic <em>Logic</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Logic</em>'.
     * @see com.zipc.garden.model.cb.CBLogic
     * @generated
     */
    EClass getCBLogic();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.cb.CBLogic#getChildren
     * <em>Children</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Children</em>'.
     * @see com.zipc.garden.model.cb.CBLogic#getChildren()
     * @see #getCBLogic()
     * @generated
     */
    EReference getCBLogic_Children();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.cb.CBLogic#getFile
     * <em>File</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>File</em>'.
     * @see com.zipc.garden.model.cb.CBLogic#getFile()
     * @see #getCBLogic()
     * @generated
     */
    EReference getCBLogic_File();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.cb.CBLogic#getType <em>Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Type</em>'.
     * @see com.zipc.garden.model.cb.CBLogic#getType()
     * @see #getCBLogic()
     * @generated
     */
    EReference getCBLogic_Type();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.cb.CBFile <em>File</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>File</em>'.
     * @see com.zipc.garden.model.cb.CBFile
     * @generated
     */
    EClass getCBFile();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.cb.CBFile#getUuid <em>Uuid</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Uuid</em>'.
     * @see com.zipc.garden.model.cb.CBFile#getUuid()
     * @see #getCBFile()
     * @generated
     */
    EAttribute getCBFile_Uuid();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.cb.CBFile#getPattern <em>Pattern</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Pattern</em>'.
     * @see com.zipc.garden.model.cb.CBFile#getPattern()
     * @see #getCBFile()
     * @generated
     */
    EAttribute getCBFile_Pattern();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.cb.CBFile#getAbstractRoot
     * <em>Abstract Root</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Abstract Root</em>'.
     * @see com.zipc.garden.model.cb.CBFile#getAbstractRoot()
     * @see #getCBFile()
     * @generated
     */
    EReference getCBFile_AbstractRoot();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.cb.CBFile#getHash <em>Hash</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Hash</em>'.
     * @see com.zipc.garden.model.cb.CBFile#getHash()
     * @see #getCBFile()
     * @generated
     */
    EAttribute getCBFile_Hash();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.cb.CBAllCombination <em>All Combination</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>All Combination</em>'.
     * @see com.zipc.garden.model.cb.CBAllCombination
     * @generated
     */
    EClass getCBAllCombination();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.cb.CBPairWise <em>Pair Wise</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Pair Wise</em>'.
     * @see com.zipc.garden.model.cb.CBPairWise
     * @generated
     */
    EClass getCBPairWise();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.cb.CBLogicType <em>Logic Type</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Logic Type</em>'.
     * @see com.zipc.garden.model.cb.CBLogicType
     * @generated
     */
    EClass getCBLogicType();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    CBFactory getCBFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.cb.impl.CBRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.cb.impl.CBRootImpl
         * @see com.zipc.garden.model.cb.impl.CBPackageImpl#getCBRoot()
         * @generated
         */
        EClass CB_ROOT = eINSTANCE.getCBRoot();

        /**
         * The meta object literal for the '<em><b>Logic</b></em>' containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference CB_ROOT__LOGIC = eINSTANCE.getCBRoot_Logic();

        /**
         * The meta object literal for the '<em><b>Layout Mode</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute CB_ROOT__LAYOUT_MODE = eINSTANCE.getCBRoot_LayoutMode();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.cb.impl.CBLogicImpl <em>Logic</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.cb.impl.CBLogicImpl
         * @see com.zipc.garden.model.cb.impl.CBPackageImpl#getCBLogic()
         * @generated
         */
        EClass CB_LOGIC = eINSTANCE.getCBLogic();

        /**
         * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference CB_LOGIC__CHILDREN = eINSTANCE.getCBLogic_Children();

        /**
         * The meta object literal for the '<em><b>File</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CB_LOGIC__FILE = eINSTANCE.getCBLogic_File();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference CB_LOGIC__TYPE = eINSTANCE.getCBLogic_Type();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.cb.impl.CBFileImpl <em>File</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.cb.impl.CBFileImpl
         * @see com.zipc.garden.model.cb.impl.CBPackageImpl#getCBFile()
         * @generated
         */
        EClass CB_FILE = eINSTANCE.getCBFile();

        /**
         * The meta object literal for the '<em><b>Uuid</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute CB_FILE__UUID = eINSTANCE.getCBFile_Uuid();

        /**
         * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute CB_FILE__PATTERN = eINSTANCE.getCBFile_Pattern();

        /**
         * The meta object literal for the '<em><b>Abstract Root</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference CB_FILE__ABSTRACT_ROOT = eINSTANCE.getCBFile_AbstractRoot();

        /**
         * The meta object literal for the '<em><b>Hash</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute CB_FILE__HASH = eINSTANCE.getCBFile_Hash();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.cb.impl.CBAllCombinationImpl <em>All Combination</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.cb.impl.CBAllCombinationImpl
         * @see com.zipc.garden.model.cb.impl.CBPackageImpl#getCBAllCombination()
         * @generated
         */
        EClass CB_ALL_COMBINATION = eINSTANCE.getCBAllCombination();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.cb.impl.CBPairWiseImpl <em>Pair Wise</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.cb.impl.CBPairWiseImpl
         * @see com.zipc.garden.model.cb.impl.CBPackageImpl#getCBPairWise()
         * @generated
         */
        EClass CB_PAIR_WISE = eINSTANCE.getCBPairWise();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.cb.impl.CBLogicTypeImpl <em>Logic Type</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.cb.impl.CBLogicTypeImpl
         * @see com.zipc.garden.model.cb.impl.CBPackageImpl#getCBLogicType()
         * @generated
         */
        EClass CB_LOGIC_TYPE = eINSTANCE.getCBLogicType();

    }

} // CBPackage
