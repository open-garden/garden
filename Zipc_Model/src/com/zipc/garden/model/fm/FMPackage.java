/**
 */
package com.zipc.garden.model.fm;

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
 * @see com.zipc.garden.model.fm.FMFactory
 * @model kind="package"
 * @generated
 */
public interface FMPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "fm";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.fm";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "FM";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    FMPackage eINSTANCE = com.zipc.garden.model.fm.impl.FMPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fm.impl.FMRootImpl <em>Root</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fm.impl.FMRootImpl
     * @see com.zipc.garden.model.fm.impl.FMPackageImpl#getFMRoot()
     * @generated
     */
    int FM_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>Scroll X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_ROOT__SCROLL_X = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Scroll Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_ROOT__SCROLL_Y = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Position X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_ROOT__POSITION_X = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Position Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_ROOT__POSITION_Y = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Grid Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_ROOT__GRID_SIZE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_ROOT__NODES = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Line Mode</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_ROOT__LINE_MODE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Node</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_ROOT__NODE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Properties</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FM_ROOT__PROPERTIES = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_ROOT__CONSTRAINTS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Auto Layout</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_ROOT__AUTO_LAYOUT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 11;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fm.impl.FMNodeImpl <em>Node</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fm.impl.FMNodeImpl
     * @see com.zipc.garden.model.fm.impl.FMPackageImpl#getFMNode()
     * @generated
     */
    int FM_NODE = 1;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__FONT_SIZE = COREPackage.ABSTRACT_STYLE__FONT_SIZE;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__FILL_COLOR = COREPackage.ABSTRACT_STYLE__FILL_COLOR;

    /**
     * The feature id for the '<em><b>Font Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__FONT_COLOR = COREPackage.ABSTRACT_STYLE__FONT_COLOR;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FM_NODE__CHILDREN = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Top</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__TOP = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Left</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__LEFT = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__HEIGHT = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__WIDTH = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__NAME = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Ref</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__REF = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Ref Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__REF_NAME = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Ref Info</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__REF_INFO = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Child Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__CHILD_TYPE = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Properties</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FM_NODE__PROPERTIES = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Min</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__MIN = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Max</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__MAX = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__X = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__Y = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>Refuuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__REFUUID = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 15;

    /**
     * The feature id for the '<em><b>Optional</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE__OPTIONAL = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 16;

    /**
     * The number of structural features of the '<em>Node</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE_FEATURE_COUNT = COREPackage.ABSTRACT_STYLE_FEATURE_COUNT + 17;

    /**
     * The number of operations of the '<em>Node</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_NODE_OPERATION_COUNT = COREPackage.ABSTRACT_STYLE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fm.impl.FMPropertyImpl <em>Property</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fm.impl.FMPropertyImpl
     * @see com.zipc.garden.model.fm.impl.FMPackageImpl#getFMProperty()
     * @generated
     */
    int FM_PROPERTY = 2;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_PROPERTY__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_PROPERTY__VALUE = 1;

    /**
     * The number of structural features of the '<em>Property</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_PROPERTY_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Property</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_PROPERTY_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fm.impl.FMConstraintImpl <em>Constraint</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fm.impl.FMConstraintImpl
     * @see com.zipc.garden.model.fm.impl.FMPackageImpl#getFMConstraint()
     * @generated
     */
    int FM_CONSTRAINT = 3;

    /**
     * The feature id for the '<em><b>Constraint</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_CONSTRAINT__CONSTRAINT = 0;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_CONSTRAINT__COMMENT = 1;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_CONSTRAINT__ENABLED = 2;

    /**
     * The feature id for the '<em><b>Related Nodes</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_CONSTRAINT__RELATED_NODES = 3;

    /**
     * The number of structural features of the '<em>Constraint</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_CONSTRAINT_FEATURE_COUNT = 4;

    /**
     * The number of operations of the '<em>Constraint</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FM_CONSTRAINT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fm.ChildType <em>Child Type</em>}' enum. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.fm.ChildType
     * @see com.zipc.garden.model.fm.impl.FMPackageImpl#getChildType()
     * @generated
     */
    int CHILD_TYPE = 4;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fm.FMRoot <em>Root</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.fm.FMRoot
     * @generated
     */
    EClass getFMRoot();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.fm.FMRoot#getNode <em>Node</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Node</em>'.
     * @see com.zipc.garden.model.fm.FMRoot#getNode()
     * @see #getFMRoot()
     * @generated
     */
    EReference getFMRoot_Node();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.fm.FMRoot#getProperties
     * <em>Properties</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Properties</em>'.
     * @see com.zipc.garden.model.fm.FMRoot#getProperties()
     * @see #getFMRoot()
     * @generated
     */
    EReference getFMRoot_Properties();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.fm.FMRoot#getConstraints
     * <em>Constraints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Constraints</em>'.
     * @see com.zipc.garden.model.fm.FMRoot#getConstraints()
     * @see #getFMRoot()
     * @generated
     */
    EReference getFMRoot_Constraints();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMRoot#getAutoLayout <em>Auto Layout</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Auto Layout</em>'.
     * @see com.zipc.garden.model.fm.FMRoot#getAutoLayout()
     * @see #getFMRoot()
     * @generated
     */
    EAttribute getFMRoot_AutoLayout();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fm.FMNode <em>Node</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Node</em>'.
     * @see com.zipc.garden.model.fm.FMNode
     * @generated
     */
    EClass getFMNode();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.fm.FMNode#getChildren
     * <em>Children</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Children</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getChildren()
     * @see #getFMNode()
     * @generated
     */
    EReference getFMNode_Children();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#getTop <em>Top</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Top</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getTop()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_Top();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#getLeft <em>Left</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Left</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getLeft()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_Left();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#getHeight <em>Height</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getHeight()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_Height();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#getWidth <em>Width</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getWidth()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_Width();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getName()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_Name();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#getRef <em>Ref</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ref</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getRef()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_Ref();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#getRefName <em>Ref Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ref Name</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getRefName()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_RefName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#getRefInfo <em>Ref Info</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ref Info</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getRefInfo()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_RefInfo();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#getChildType <em>Child Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Child Type</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getChildType()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_ChildType();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.fm.FMNode#getProperties
     * <em>Properties</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Properties</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getProperties()
     * @see #getFMNode()
     * @generated
     */
    EReference getFMNode_Properties();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#getMin <em>Min</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Min</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getMin()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_Min();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#getMax <em>Max</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Max</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getMax()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_Max();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#getX <em>X</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getX()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_X();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#getY <em>Y</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getY()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_Y();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#getRefuuid <em>Refuuid</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Refuuid</em>'.
     * @see com.zipc.garden.model.fm.FMNode#getRefuuid()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_Refuuid();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMNode#isOptional <em>Optional</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Optional</em>'.
     * @see com.zipc.garden.model.fm.FMNode#isOptional()
     * @see #getFMNode()
     * @generated
     */
    EAttribute getFMNode_Optional();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fm.FMProperty <em>Property</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Property</em>'.
     * @see com.zipc.garden.model.fm.FMProperty
     * @generated
     */
    EClass getFMProperty();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMProperty#getKey <em>Key</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see com.zipc.garden.model.fm.FMProperty#getKey()
     * @see #getFMProperty()
     * @generated
     */
    EAttribute getFMProperty_Key();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMProperty#getValue <em>Value</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see com.zipc.garden.model.fm.FMProperty#getValue()
     * @see #getFMProperty()
     * @generated
     */
    EAttribute getFMProperty_Value();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fm.FMConstraint <em>Constraint</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Constraint</em>'.
     * @see com.zipc.garden.model.fm.FMConstraint
     * @generated
     */
    EClass getFMConstraint();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMConstraint#getConstraint
     * <em>Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Constraint</em>'.
     * @see com.zipc.garden.model.fm.FMConstraint#getConstraint()
     * @see #getFMConstraint()
     * @generated
     */
    EAttribute getFMConstraint_Constraint();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMConstraint#getComment <em>Comment</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Comment</em>'.
     * @see com.zipc.garden.model.fm.FMConstraint#getComment()
     * @see #getFMConstraint()
     * @generated
     */
    EAttribute getFMConstraint_Comment();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fm.FMConstraint#isEnabled <em>Enabled</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Enabled</em>'.
     * @see com.zipc.garden.model.fm.FMConstraint#isEnabled()
     * @see #getFMConstraint()
     * @generated
     */
    EAttribute getFMConstraint_Enabled();

    /**
     * Returns the meta object for the reference list '{@link com.zipc.garden.model.fm.FMConstraint#getRelatedNodes <em>Related
     * Nodes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Related Nodes</em>'.
     * @see com.zipc.garden.model.fm.FMConstraint#getRelatedNodes()
     * @see #getFMConstraint()
     * @generated
     */
    EReference getFMConstraint_RelatedNodes();

    /**
     * Returns the meta object for enum '{@link com.zipc.garden.model.fm.ChildType <em>Child Type</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Child Type</em>'.
     * @see com.zipc.garden.model.fm.ChildType
     * @generated
     */
    EEnum getChildType();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    FMFactory getFMFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.fm.impl.FMRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fm.impl.FMRootImpl
         * @see com.zipc.garden.model.fm.impl.FMPackageImpl#getFMRoot()
         * @generated
         */
        EClass FM_ROOT = eINSTANCE.getFMRoot();

        /**
         * The meta object literal for the '<em><b>Node</b></em>' containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference FM_ROOT__NODE = eINSTANCE.getFMRoot_Node();

        /**
         * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FM_ROOT__PROPERTIES = eINSTANCE.getFMRoot_Properties();

        /**
         * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FM_ROOT__CONSTRAINTS = eINSTANCE.getFMRoot_Constraints();

        /**
         * The meta object literal for the '<em><b>Auto Layout</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FM_ROOT__AUTO_LAYOUT = eINSTANCE.getFMRoot_AutoLayout();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fm.impl.FMNodeImpl <em>Node</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fm.impl.FMNodeImpl
         * @see com.zipc.garden.model.fm.impl.FMPackageImpl#getFMNode()
         * @generated
         */
        EClass FM_NODE = eINSTANCE.getFMNode();

        /**
         * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FM_NODE__CHILDREN = eINSTANCE.getFMNode_Children();

        /**
         * The meta object literal for the '<em><b>Top</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FM_NODE__TOP = eINSTANCE.getFMNode_Top();

        /**
         * The meta object literal for the '<em><b>Left</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FM_NODE__LEFT = eINSTANCE.getFMNode_Left();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FM_NODE__HEIGHT = eINSTANCE.getFMNode_Height();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FM_NODE__WIDTH = eINSTANCE.getFMNode_Width();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FM_NODE__NAME = eINSTANCE.getFMNode_Name();

        /**
         * The meta object literal for the '<em><b>Ref</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FM_NODE__REF = eINSTANCE.getFMNode_Ref();

        /**
         * The meta object literal for the '<em><b>Ref Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FM_NODE__REF_NAME = eINSTANCE.getFMNode_RefName();

        /**
         * The meta object literal for the '<em><b>Ref Info</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FM_NODE__REF_INFO = eINSTANCE.getFMNode_RefInfo();

        /**
         * The meta object literal for the '<em><b>Child Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FM_NODE__CHILD_TYPE = eINSTANCE.getFMNode_ChildType();

        /**
         * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FM_NODE__PROPERTIES = eINSTANCE.getFMNode_Properties();

        /**
         * The meta object literal for the '<em><b>Min</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FM_NODE__MIN = eINSTANCE.getFMNode_Min();

        /**
         * The meta object literal for the '<em><b>Max</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FM_NODE__MAX = eINSTANCE.getFMNode_Max();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute FM_NODE__X = eINSTANCE.getFMNode_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute FM_NODE__Y = eINSTANCE.getFMNode_Y();

        /**
         * The meta object literal for the '<em><b>Refuuid</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FM_NODE__REFUUID = eINSTANCE.getFMNode_Refuuid();

        /**
         * The meta object literal for the '<em><b>Optional</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FM_NODE__OPTIONAL = eINSTANCE.getFMNode_Optional();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fm.impl.FMPropertyImpl <em>Property</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fm.impl.FMPropertyImpl
         * @see com.zipc.garden.model.fm.impl.FMPackageImpl#getFMProperty()
         * @generated
         */
        EClass FM_PROPERTY = eINSTANCE.getFMProperty();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FM_PROPERTY__KEY = eINSTANCE.getFMProperty_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute FM_PROPERTY__VALUE = eINSTANCE.getFMProperty_Value();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fm.impl.FMConstraintImpl <em>Constraint</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fm.impl.FMConstraintImpl
         * @see com.zipc.garden.model.fm.impl.FMPackageImpl#getFMConstraint()
         * @generated
         */
        EClass FM_CONSTRAINT = eINSTANCE.getFMConstraint();

        /**
         * The meta object literal for the '<em><b>Constraint</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FM_CONSTRAINT__CONSTRAINT = eINSTANCE.getFMConstraint_Constraint();

        /**
         * The meta object literal for the '<em><b>Comment</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FM_CONSTRAINT__COMMENT = eINSTANCE.getFMConstraint_Comment();

        /**
         * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FM_CONSTRAINT__ENABLED = eINSTANCE.getFMConstraint_Enabled();

        /**
         * The meta object literal for the '<em><b>Related Nodes</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference FM_CONSTRAINT__RELATED_NODES = eINSTANCE.getFMConstraint_RelatedNodes();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fm.ChildType <em>Child Type</em>}' enum. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fm.ChildType
         * @see com.zipc.garden.model.fm.impl.FMPackageImpl#getChildType()
         * @generated
         */
        EEnum CHILD_TYPE = eINSTANCE.getChildType();

    }

} // FMPackage
