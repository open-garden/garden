/**
 */
package com.zipc.garden.model.core;

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
 * @see com.zipc.garden.model.core.COREFactory
 * @model kind="package"
 * @generated
 */
public interface COREPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "core";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.core";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "CORE";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    COREPackage eINSTANCE = com.zipc.garden.model.core.impl.COREPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.core.impl.AbstractRootElementImpl <em>Abstract Root
     * Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.core.impl.AbstractRootElementImpl
     * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractRootElement()
     * @generated
     */
    int ABSTRACT_ROOT_ELEMENT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_ROOT_ELEMENT__ID = 0;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_ROOT_ELEMENT__REFS = 1;

    /**
     * The number of structural features of the '<em>Abstract Root Element</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Abstract Root Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.core.impl.ReferenceImpl <em>Reference</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.core.impl.ReferenceImpl
     * @see com.zipc.garden.model.core.impl.COREPackageImpl#getReference()
     * @generated
     */
    int REFERENCE = 1;

    /**
     * The feature id for the '<em><b>Refid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE__REFID = 0;

    /**
     * The feature id for the '<em><b>Hash</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE__HASH = 1;

    /**
     * The feature id for the '<em><b>Ref Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE__REF_NAME = 2;

    /**
     * The feature id for the '<em><b>Ref Extension</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE__REF_EXTENSION = 3;

    /**
     * The number of structural features of the '<em>Reference</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE_FEATURE_COUNT = 4;

    /**
     * The number of operations of the '<em>Reference</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.core.impl.AbstractDiagramImpl <em>Abstract Diagram</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.core.impl.AbstractDiagramImpl
     * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractDiagram()
     * @generated
     */
    int ABSTRACT_DIAGRAM = 2;

    /**
     * The feature id for the '<em><b>Scroll X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_DIAGRAM__SCROLL_X = 0;

    /**
     * The feature id for the '<em><b>Scroll Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_DIAGRAM__SCROLL_Y = 1;

    /**
     * The feature id for the '<em><b>Position X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_DIAGRAM__POSITION_X = 2;

    /**
     * The feature id for the '<em><b>Position Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_DIAGRAM__POSITION_Y = 3;

    /**
     * The feature id for the '<em><b>Grid Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_DIAGRAM__GRID_SIZE = 4;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_DIAGRAM__NODES = 5;

    /**
     * The feature id for the '<em><b>Line Mode</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_DIAGRAM__LINE_MODE = 6;

    /**
     * The number of structural features of the '<em>Abstract Diagram</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_DIAGRAM_FEATURE_COUNT = 7;

    /**
     * The number of operations of the '<em>Abstract Diagram</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_DIAGRAM_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.core.impl.AbstractNodeImpl <em>Abstract Node</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.core.impl.AbstractNodeImpl
     * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractNode()
     * @generated
     */
    int ABSTRACT_NODE = 3;

    /**
     * The feature id for the '<em><b>Top</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__TOP = 0;

    /**
     * The feature id for the '<em><b>Left</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__LEFT = 1;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__HEIGHT = 2;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__WIDTH = 3;

    /**
     * The number of structural features of the '<em>Abstract Node</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_FEATURE_COUNT = 4;

    /**
     * The number of operations of the '<em>Abstract Node</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.core.impl.MemoImpl <em>Memo</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.core.impl.MemoImpl
     * @see com.zipc.garden.model.core.impl.COREPackageImpl#getMemo()
     * @generated
     */
    int MEMO = 4;

    /**
     * The feature id for the '<em><b>Top</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEMO__TOP = ABSTRACT_NODE__TOP;

    /**
     * The feature id for the '<em><b>Left</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEMO__LEFT = ABSTRACT_NODE__LEFT;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEMO__HEIGHT = ABSTRACT_NODE__HEIGHT;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEMO__WIDTH = ABSTRACT_NODE__WIDTH;

    /**
     * The feature id for the '<em><b>Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEMO__TEXT = ABSTRACT_NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Memo</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEMO_FEATURE_COUNT = ABSTRACT_NODE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Memo</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MEMO_OPERATION_COUNT = ABSTRACT_NODE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.core.impl.AbstractPointImpl <em>Abstract Point</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.core.impl.AbstractPointImpl
     * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractPoint()
     * @generated
     */
    int ABSTRACT_POINT = 5;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_POINT__X = 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_POINT__Y = 1;

    /**
     * The number of structural features of the '<em>Abstract Point</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_POINT_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Abstract Point</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_POINT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.core.impl.AbstractLineImpl <em>Abstract Line</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.core.impl.AbstractLineImpl
     * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractLine()
     * @generated
     */
    int ABSTRACT_LINE = 6;

    /**
     * The feature id for the '<em><b>Source Anchor X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_LINE__SOURCE_ANCHOR_X = 0;

    /**
     * The feature id for the '<em><b>Source Anchor Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_LINE__SOURCE_ANCHOR_Y = 1;

    /**
     * The feature id for the '<em><b>Target Anchor X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_LINE__TARGET_ANCHOR_X = 2;

    /**
     * The feature id for the '<em><b>Target Anchor Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_LINE__TARGET_ANCHOR_Y = 3;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_LINE__TYPE = 4;

    /**
     * The feature id for the '<em><b>Connection Point</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_LINE__CONNECTION_POINT = 5;

    /**
     * The number of structural features of the '<em>Abstract Line</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_LINE_FEATURE_COUNT = 6;

    /**
     * The number of operations of the '<em>Abstract Line</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_LINE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.core.impl.AbstractStyleImpl <em>Abstract Style</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.core.impl.AbstractStyleImpl
     * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractStyle()
     * @generated
     */
    int ABSTRACT_STYLE = 7;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_STYLE__FONT_SIZE = 0;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_STYLE__FILL_COLOR = 1;

    /**
     * The feature id for the '<em><b>Font Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_STYLE__FONT_COLOR = 2;

    /**
     * The number of structural features of the '<em>Abstract Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_STYLE_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Abstract Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_STYLE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.core.impl.AbstractSearchConditionImpl <em>Abstract Search
     * Condition</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.core.impl.AbstractSearchConditionImpl
     * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractSearchCondition()
     * @generated
     */
    int ABSTRACT_SEARCH_CONDITION = 8;

    /**
     * The feature id for the '<em><b>Max</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_SEARCH_CONDITION__MAX = 0;

    /**
     * The feature id for the '<em><b>Row Ids</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_SEARCH_CONDITION__ROW_IDS = 1;

    /**
     * The number of structural features of the '<em>Abstract Search Condition</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_SEARCH_CONDITION_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Abstract Search Condition</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_SEARCH_CONDITION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.core.AbstractSetting <em>Abstract Setting</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.core.AbstractSetting
     * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractSetting()
     * @generated
     */
    int ABSTRACT_SETTING = 9;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_SETTING__UUID = 0;

    /**
     * The feature id for the '<em><b>Setting Hash</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_SETTING__SETTING_HASH = 1;

    /**
     * The feature id for the '<em><b>Pattern Nos</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_SETTING__PATTERN_NOS = 2;

    /**
     * The feature id for the '<em><b>Begin</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_SETTING__BEGIN = 3;

    /**
     * The feature id for the '<em><b>End</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_SETTING__END = 4;

    /**
     * The feature id for the '<em><b>Count</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_SETTING__COUNT = 5;

    /**
     * The feature id for the '<em><b>Abstract Root</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int ABSTRACT_SETTING__ABSTRACT_ROOT = 6;

    /**
     * The number of structural features of the '<em>Abstract Setting</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_SETTING_FEATURE_COUNT = 7;

    /**
     * The number of operations of the '<em>Abstract Setting</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_SETTING_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.core.SettingInterface <em>Setting Interface</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.core.SettingInterface
     * @see com.zipc.garden.model.core.impl.COREPackageImpl#getSettingInterface()
     * @generated
     */
    int SETTING_INTERFACE = 10;

    /**
     * The feature id for the '<em><b>Settings</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int SETTING_INTERFACE__SETTINGS = 0;

    /**
     * The number of structural features of the '<em>Setting Interface</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int SETTING_INTERFACE_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Setting Interface</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SETTING_INTERFACE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.core.LineType <em>Line Type</em>}' enum. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.zipc.garden.model.core.LineType
     * @see com.zipc.garden.model.core.impl.COREPackageImpl#getLineType()
     * @generated
     */
    int LINE_TYPE = 11;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.core.AbstractRootElement <em>Abstract Root
     * Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Root Element</em>'.
     * @see com.zipc.garden.model.core.AbstractRootElement
     * @generated
     */
    EClass getAbstractRootElement();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractRootElement#getId <em>Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see com.zipc.garden.model.core.AbstractRootElement#getId()
     * @see #getAbstractRootElement()
     * @generated
     */
    EAttribute getAbstractRootElement_Id();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.core.AbstractRootElement#getRefs
     * <em>Refs</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Refs</em>'.
     * @see com.zipc.garden.model.core.AbstractRootElement#getRefs()
     * @see #getAbstractRootElement()
     * @generated
     */
    EReference getAbstractRootElement_Refs();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.core.Reference <em>Reference</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Reference</em>'.
     * @see com.zipc.garden.model.core.Reference
     * @generated
     */
    EClass getReference();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.Reference#getRefid <em>Refid</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Refid</em>'.
     * @see com.zipc.garden.model.core.Reference#getRefid()
     * @see #getReference()
     * @generated
     */
    EAttribute getReference_Refid();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.Reference#getHash <em>Hash</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Hash</em>'.
     * @see com.zipc.garden.model.core.Reference#getHash()
     * @see #getReference()
     * @generated
     */
    EAttribute getReference_Hash();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.Reference#getRefName <em>Ref Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ref Name</em>'.
     * @see com.zipc.garden.model.core.Reference#getRefName()
     * @see #getReference()
     * @generated
     */
    EAttribute getReference_RefName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.Reference#getRefExtension <em>Ref
     * Extension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ref Extension</em>'.
     * @see com.zipc.garden.model.core.Reference#getRefExtension()
     * @see #getReference()
     * @generated
     */
    EAttribute getReference_RefExtension();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.core.AbstractDiagram <em>Abstract Diagram</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Diagram</em>'.
     * @see com.zipc.garden.model.core.AbstractDiagram
     * @generated
     */
    EClass getAbstractDiagram();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractDiagram#getScrollX <em>Scroll
     * X</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Scroll X</em>'.
     * @see com.zipc.garden.model.core.AbstractDiagram#getScrollX()
     * @see #getAbstractDiagram()
     * @generated
     */
    EAttribute getAbstractDiagram_ScrollX();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractDiagram#getScrollY <em>Scroll
     * Y</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Scroll Y</em>'.
     * @see com.zipc.garden.model.core.AbstractDiagram#getScrollY()
     * @see #getAbstractDiagram()
     * @generated
     */
    EAttribute getAbstractDiagram_ScrollY();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractDiagram#getPositionX <em>Position
     * X</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Position X</em>'.
     * @see com.zipc.garden.model.core.AbstractDiagram#getPositionX()
     * @see #getAbstractDiagram()
     * @generated
     */
    EAttribute getAbstractDiagram_PositionX();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractDiagram#getPositionY <em>Position
     * Y</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Position Y</em>'.
     * @see com.zipc.garden.model.core.AbstractDiagram#getPositionY()
     * @see #getAbstractDiagram()
     * @generated
     */
    EAttribute getAbstractDiagram_PositionY();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractDiagram#getGridSize <em>Grid
     * Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Grid Size</em>'.
     * @see com.zipc.garden.model.core.AbstractDiagram#getGridSize()
     * @see #getAbstractDiagram()
     * @generated
     */
    EAttribute getAbstractDiagram_GridSize();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.core.AbstractDiagram#getNodes
     * <em>Nodes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Nodes</em>'.
     * @see com.zipc.garden.model.core.AbstractDiagram#getNodes()
     * @see #getAbstractDiagram()
     * @generated
     */
    EReference getAbstractDiagram_Nodes();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractDiagram#getLineMode <em>Line
     * Mode</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Line Mode</em>'.
     * @see com.zipc.garden.model.core.AbstractDiagram#getLineMode()
     * @see #getAbstractDiagram()
     * @generated
     */
    EAttribute getAbstractDiagram_LineMode();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.core.AbstractNode <em>Abstract Node</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Node</em>'.
     * @see com.zipc.garden.model.core.AbstractNode
     * @generated
     */
    EClass getAbstractNode();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractNode#getTop <em>Top</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Top</em>'.
     * @see com.zipc.garden.model.core.AbstractNode#getTop()
     * @see #getAbstractNode()
     * @generated
     */
    EAttribute getAbstractNode_Top();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractNode#getLeft <em>Left</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Left</em>'.
     * @see com.zipc.garden.model.core.AbstractNode#getLeft()
     * @see #getAbstractNode()
     * @generated
     */
    EAttribute getAbstractNode_Left();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractNode#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see com.zipc.garden.model.core.AbstractNode#getHeight()
     * @see #getAbstractNode()
     * @generated
     */
    EAttribute getAbstractNode_Height();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractNode#getWidth <em>Width</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see com.zipc.garden.model.core.AbstractNode#getWidth()
     * @see #getAbstractNode()
     * @generated
     */
    EAttribute getAbstractNode_Width();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.core.Memo <em>Memo</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Memo</em>'.
     * @see com.zipc.garden.model.core.Memo
     * @generated
     */
    EClass getMemo();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.Memo#getText <em>Text</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see com.zipc.garden.model.core.Memo#getText()
     * @see #getMemo()
     * @generated
     */
    EAttribute getMemo_Text();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.core.AbstractPoint <em>Abstract Point</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Point</em>'.
     * @see com.zipc.garden.model.core.AbstractPoint
     * @generated
     */
    EClass getAbstractPoint();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractPoint#getX <em>X</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see com.zipc.garden.model.core.AbstractPoint#getX()
     * @see #getAbstractPoint()
     * @generated
     */
    EAttribute getAbstractPoint_X();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractPoint#getY <em>Y</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see com.zipc.garden.model.core.AbstractPoint#getY()
     * @see #getAbstractPoint()
     * @generated
     */
    EAttribute getAbstractPoint_Y();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.core.AbstractLine <em>Abstract Line</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Line</em>'.
     * @see com.zipc.garden.model.core.AbstractLine
     * @generated
     */
    EClass getAbstractLine();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractLine#getSourceAnchorX <em>Source
     * Anchor X</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Source Anchor X</em>'.
     * @see com.zipc.garden.model.core.AbstractLine#getSourceAnchorX()
     * @see #getAbstractLine()
     * @generated
     */
    EAttribute getAbstractLine_SourceAnchorX();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractLine#getSourceAnchorY <em>Source
     * Anchor Y</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Source Anchor Y</em>'.
     * @see com.zipc.garden.model.core.AbstractLine#getSourceAnchorY()
     * @see #getAbstractLine()
     * @generated
     */
    EAttribute getAbstractLine_SourceAnchorY();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractLine#getTargetAnchorX <em>Target
     * Anchor X</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Target Anchor X</em>'.
     * @see com.zipc.garden.model.core.AbstractLine#getTargetAnchorX()
     * @see #getAbstractLine()
     * @generated
     */
    EAttribute getAbstractLine_TargetAnchorX();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractLine#getTargetAnchorY <em>Target
     * Anchor Y</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Target Anchor Y</em>'.
     * @see com.zipc.garden.model.core.AbstractLine#getTargetAnchorY()
     * @see #getAbstractLine()
     * @generated
     */
    EAttribute getAbstractLine_TargetAnchorY();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractLine#getType <em>Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see com.zipc.garden.model.core.AbstractLine#getType()
     * @see #getAbstractLine()
     * @generated
     */
    EAttribute getAbstractLine_Type();

    /**
     * Returns the meta object for the reference list '{@link com.zipc.garden.model.core.AbstractLine#getConnectionPoint
     * <em>Connection Point</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Connection Point</em>'.
     * @see com.zipc.garden.model.core.AbstractLine#getConnectionPoint()
     * @see #getAbstractLine()
     * @generated
     */
    EReference getAbstractLine_ConnectionPoint();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.core.AbstractStyle <em>Abstract Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Style</em>'.
     * @see com.zipc.garden.model.core.AbstractStyle
     * @generated
     */
    EClass getAbstractStyle();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractStyle#getFontSize <em>Font
     * Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Font Size</em>'.
     * @see com.zipc.garden.model.core.AbstractStyle#getFontSize()
     * @see #getAbstractStyle()
     * @generated
     */
    EAttribute getAbstractStyle_FontSize();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractStyle#getFillColor <em>Fill
     * Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Fill Color</em>'.
     * @see com.zipc.garden.model.core.AbstractStyle#getFillColor()
     * @see #getAbstractStyle()
     * @generated
     */
    EAttribute getAbstractStyle_FillColor();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractStyle#getFontColor <em>Font
     * Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Font Color</em>'.
     * @see com.zipc.garden.model.core.AbstractStyle#getFontColor()
     * @see #getAbstractStyle()
     * @generated
     */
    EAttribute getAbstractStyle_FontColor();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.core.AbstractSearchCondition <em>Abstract Search
     * Condition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Search Condition</em>'.
     * @see com.zipc.garden.model.core.AbstractSearchCondition
     * @generated
     */
    EClass getAbstractSearchCondition();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractSearchCondition#getMax
     * <em>Max</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Max</em>'.
     * @see com.zipc.garden.model.core.AbstractSearchCondition#getMax()
     * @see #getAbstractSearchCondition()
     * @generated
     */
    EAttribute getAbstractSearchCondition_Max();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractSearchCondition#getRowIds <em>Row
     * Ids</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Row Ids</em>'.
     * @see com.zipc.garden.model.core.AbstractSearchCondition#getRowIds()
     * @see #getAbstractSearchCondition()
     * @generated
     */
    EAttribute getAbstractSearchCondition_RowIds();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.core.AbstractSetting <em>Abstract Setting</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Setting</em>'.
     * @see com.zipc.garden.model.core.AbstractSetting
     * @generated
     */
    EClass getAbstractSetting();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractSetting#getUuid <em>Uuid</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Uuid</em>'.
     * @see com.zipc.garden.model.core.AbstractSetting#getUuid()
     * @see #getAbstractSetting()
     * @generated
     */
    EAttribute getAbstractSetting_Uuid();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractSetting#getSettingHash <em>Setting
     * Hash</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Setting Hash</em>'.
     * @see com.zipc.garden.model.core.AbstractSetting#getSettingHash()
     * @see #getAbstractSetting()
     * @generated
     */
    EAttribute getAbstractSetting_SettingHash();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractSetting#getPatternNos <em>Pattern
     * Nos</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Pattern Nos</em>'.
     * @see com.zipc.garden.model.core.AbstractSetting#getPatternNos()
     * @see #getAbstractSetting()
     * @generated
     */
    EAttribute getAbstractSetting_PatternNos();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractSetting#getBegin <em>Begin</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Begin</em>'.
     * @see com.zipc.garden.model.core.AbstractSetting#getBegin()
     * @see #getAbstractSetting()
     * @generated
     */
    EAttribute getAbstractSetting_Begin();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractSetting#getEnd <em>End</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>End</em>'.
     * @see com.zipc.garden.model.core.AbstractSetting#getEnd()
     * @see #getAbstractSetting()
     * @generated
     */
    EAttribute getAbstractSetting_End();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.core.AbstractSetting#getCount <em>Count</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Count</em>'.
     * @see com.zipc.garden.model.core.AbstractSetting#getCount()
     * @see #getAbstractSetting()
     * @generated
     */
    EAttribute getAbstractSetting_Count();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.core.AbstractSetting#getAbstractRoot
     * <em>Abstract Root</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Abstract Root</em>'.
     * @see com.zipc.garden.model.core.AbstractSetting#getAbstractRoot()
     * @see #getAbstractSetting()
     * @generated
     */
    EReference getAbstractSetting_AbstractRoot();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.core.SettingInterface <em>Setting Interface</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Setting Interface</em>'.
     * @see com.zipc.garden.model.core.SettingInterface
     * @generated
     */
    EClass getSettingInterface();

    /**
     * Returns the meta object for the containment reference list
     * '{@link com.zipc.garden.model.core.SettingInterface#getSettings <em>Settings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>Settings</em>'.
     * @see com.zipc.garden.model.core.SettingInterface#getSettings()
     * @see #getSettingInterface()
     * @generated
     */
    EReference getSettingInterface_Settings();

    /**
     * Returns the meta object for enum '{@link com.zipc.garden.model.core.LineType <em>Line Type</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Line Type</em>'.
     * @see com.zipc.garden.model.core.LineType
     * @generated
     */
    EEnum getLineType();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    COREFactory getCOREFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.core.impl.AbstractRootElementImpl <em>Abstract Root
         * Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.core.impl.AbstractRootElementImpl
         * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractRootElement()
         * @generated
         */
        EClass ABSTRACT_ROOT_ELEMENT = eINSTANCE.getAbstractRootElement();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_ROOT_ELEMENT__ID = eINSTANCE.getAbstractRootElement_Id();

        /**
         * The meta object literal for the '<em><b>Refs</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_ROOT_ELEMENT__REFS = eINSTANCE.getAbstractRootElement_Refs();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.core.impl.ReferenceImpl <em>Reference</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.core.impl.ReferenceImpl
         * @see com.zipc.garden.model.core.impl.COREPackageImpl#getReference()
         * @generated
         */
        EClass REFERENCE = eINSTANCE.getReference();

        /**
         * The meta object literal for the '<em><b>Refid</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute REFERENCE__REFID = eINSTANCE.getReference_Refid();

        /**
         * The meta object literal for the '<em><b>Hash</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute REFERENCE__HASH = eINSTANCE.getReference_Hash();

        /**
         * The meta object literal for the '<em><b>Ref Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute REFERENCE__REF_NAME = eINSTANCE.getReference_RefName();

        /**
         * The meta object literal for the '<em><b>Ref Extension</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute REFERENCE__REF_EXTENSION = eINSTANCE.getReference_RefExtension();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.core.impl.AbstractDiagramImpl <em>Abstract
         * Diagram</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.core.impl.AbstractDiagramImpl
         * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractDiagram()
         * @generated
         */
        EClass ABSTRACT_DIAGRAM = eINSTANCE.getAbstractDiagram();

        /**
         * The meta object literal for the '<em><b>Scroll X</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_DIAGRAM__SCROLL_X = eINSTANCE.getAbstractDiagram_ScrollX();

        /**
         * The meta object literal for the '<em><b>Scroll Y</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_DIAGRAM__SCROLL_Y = eINSTANCE.getAbstractDiagram_ScrollY();

        /**
         * The meta object literal for the '<em><b>Position X</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_DIAGRAM__POSITION_X = eINSTANCE.getAbstractDiagram_PositionX();

        /**
         * The meta object literal for the '<em><b>Position Y</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_DIAGRAM__POSITION_Y = eINSTANCE.getAbstractDiagram_PositionY();

        /**
         * The meta object literal for the '<em><b>Grid Size</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_DIAGRAM__GRID_SIZE = eINSTANCE.getAbstractDiagram_GridSize();

        /**
         * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_DIAGRAM__NODES = eINSTANCE.getAbstractDiagram_Nodes();

        /**
         * The meta object literal for the '<em><b>Line Mode</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_DIAGRAM__LINE_MODE = eINSTANCE.getAbstractDiagram_LineMode();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.core.impl.AbstractNodeImpl <em>Abstract Node</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.core.impl.AbstractNodeImpl
         * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractNode()
         * @generated
         */
        EClass ABSTRACT_NODE = eINSTANCE.getAbstractNode();

        /**
         * The meta object literal for the '<em><b>Top</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ABSTRACT_NODE__TOP = eINSTANCE.getAbstractNode_Top();

        /**
         * The meta object literal for the '<em><b>Left</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ABSTRACT_NODE__LEFT = eINSTANCE.getAbstractNode_Left();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ABSTRACT_NODE__HEIGHT = eINSTANCE.getAbstractNode_Height();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ABSTRACT_NODE__WIDTH = eINSTANCE.getAbstractNode_Width();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.core.impl.MemoImpl <em>Memo</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.core.impl.MemoImpl
         * @see com.zipc.garden.model.core.impl.COREPackageImpl#getMemo()
         * @generated
         */
        EClass MEMO = eINSTANCE.getMemo();

        /**
         * The meta object literal for the '<em><b>Text</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute MEMO__TEXT = eINSTANCE.getMemo_Text();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.core.impl.AbstractPointImpl <em>Abstract Point</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.core.impl.AbstractPointImpl
         * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractPoint()
         * @generated
         */
        EClass ABSTRACT_POINT = eINSTANCE.getAbstractPoint();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_POINT__X = eINSTANCE.getAbstractPoint_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_POINT__Y = eINSTANCE.getAbstractPoint_Y();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.core.impl.AbstractLineImpl <em>Abstract Line</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.core.impl.AbstractLineImpl
         * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractLine()
         * @generated
         */
        EClass ABSTRACT_LINE = eINSTANCE.getAbstractLine();

        /**
         * The meta object literal for the '<em><b>Source Anchor X</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_LINE__SOURCE_ANCHOR_X = eINSTANCE.getAbstractLine_SourceAnchorX();

        /**
         * The meta object literal for the '<em><b>Source Anchor Y</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_LINE__SOURCE_ANCHOR_Y = eINSTANCE.getAbstractLine_SourceAnchorY();

        /**
         * The meta object literal for the '<em><b>Target Anchor X</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_LINE__TARGET_ANCHOR_X = eINSTANCE.getAbstractLine_TargetAnchorX();

        /**
         * The meta object literal for the '<em><b>Target Anchor Y</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_LINE__TARGET_ANCHOR_Y = eINSTANCE.getAbstractLine_TargetAnchorY();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ABSTRACT_LINE__TYPE = eINSTANCE.getAbstractLine_Type();

        /**
         * The meta object literal for the '<em><b>Connection Point</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_LINE__CONNECTION_POINT = eINSTANCE.getAbstractLine_ConnectionPoint();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.core.impl.AbstractStyleImpl <em>Abstract Style</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.core.impl.AbstractStyleImpl
         * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractStyle()
         * @generated
         */
        EClass ABSTRACT_STYLE = eINSTANCE.getAbstractStyle();

        /**
         * The meta object literal for the '<em><b>Font Size</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_STYLE__FONT_SIZE = eINSTANCE.getAbstractStyle_FontSize();

        /**
         * The meta object literal for the '<em><b>Fill Color</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_STYLE__FILL_COLOR = eINSTANCE.getAbstractStyle_FillColor();

        /**
         * The meta object literal for the '<em><b>Font Color</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_STYLE__FONT_COLOR = eINSTANCE.getAbstractStyle_FontColor();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.core.impl.AbstractSearchConditionImpl <em>Abstract
         * Search Condition</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.core.impl.AbstractSearchConditionImpl
         * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractSearchCondition()
         * @generated
         */
        EClass ABSTRACT_SEARCH_CONDITION = eINSTANCE.getAbstractSearchCondition();

        /**
         * The meta object literal for the '<em><b>Max</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ABSTRACT_SEARCH_CONDITION__MAX = eINSTANCE.getAbstractSearchCondition_Max();

        /**
         * The meta object literal for the '<em><b>Row Ids</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_SEARCH_CONDITION__ROW_IDS = eINSTANCE.getAbstractSearchCondition_RowIds();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.core.AbstractSetting <em>Abstract Setting</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.core.AbstractSetting
         * @see com.zipc.garden.model.core.impl.COREPackageImpl#getAbstractSetting()
         * @generated
         */
        EClass ABSTRACT_SETTING = eINSTANCE.getAbstractSetting();

        /**
         * The meta object literal for the '<em><b>Uuid</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ABSTRACT_SETTING__UUID = eINSTANCE.getAbstractSetting_Uuid();

        /**
         * The meta object literal for the '<em><b>Setting Hash</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_SETTING__SETTING_HASH = eINSTANCE.getAbstractSetting_SettingHash();

        /**
         * The meta object literal for the '<em><b>Pattern Nos</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_SETTING__PATTERN_NOS = eINSTANCE.getAbstractSetting_PatternNos();

        /**
         * The meta object literal for the '<em><b>Begin</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ABSTRACT_SETTING__BEGIN = eINSTANCE.getAbstractSetting_Begin();

        /**
         * The meta object literal for the '<em><b>End</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ABSTRACT_SETTING__END = eINSTANCE.getAbstractSetting_End();

        /**
         * The meta object literal for the '<em><b>Count</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ABSTRACT_SETTING__COUNT = eINSTANCE.getAbstractSetting_Count();

        /**
         * The meta object literal for the '<em><b>Abstract Root</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_SETTING__ABSTRACT_ROOT = eINSTANCE.getAbstractSetting_AbstractRoot();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.core.SettingInterface <em>Setting Interface</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.core.SettingInterface
         * @see com.zipc.garden.model.core.impl.COREPackageImpl#getSettingInterface()
         * @generated
         */
        EClass SETTING_INTERFACE = eINSTANCE.getSettingInterface();

        /**
         * The meta object literal for the '<em><b>Settings</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference SETTING_INTERFACE__SETTINGS = eINSTANCE.getSettingInterface_Settings();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.core.LineType <em>Line Type</em>}' enum. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.core.LineType
         * @see com.zipc.garden.model.core.impl.COREPackageImpl#getLineType()
         * @generated
         */
        EEnum LINE_TYPE = eINSTANCE.getLineType();

    }

} // COREPackage
