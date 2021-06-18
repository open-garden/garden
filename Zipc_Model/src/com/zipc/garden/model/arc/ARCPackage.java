/**
 */
package com.zipc.garden.model.arc;

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
 * @see com.zipc.garden.model.arc.ARCFactory
 * @model kind="package"
 * @generated
 */
public interface ARCPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "arc";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.arc";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "ARC";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    ARCPackage eINSTANCE = com.zipc.garden.model.arc.impl.ARCPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.arc.impl.ARCReferenceableImpl <em>Referenceable</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.arc.impl.ARCReferenceableImpl
     * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCReferenceable()
     * @generated
     */
    int ARC_REFERENCEABLE = 6;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.arc.impl.ARCVertexImpl <em>Vertex</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.arc.impl.ARCVertexImpl
     * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCVertex()
     * @generated
     */
    int ARC_VERTEX = 1;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.arc.impl.ARCStateImpl <em>State</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.arc.impl.ARCStateImpl
     * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCState()
     * @generated
     */
    int ARC_STATE = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.arc.impl.ARCPointImpl <em>Point</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.arc.impl.ARCPointImpl
     * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCPoint()
     * @generated
     */
    int ARC_POINT = 2;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.arc.impl.ARCLineImpl <em>Line</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.arc.impl.ARCLineImpl
     * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCLine()
     * @generated
     */
    int ARC_LINE = 3;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.arc.impl.ARCAbstractLineImpl <em>Abstract Line</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.arc.impl.ARCAbstractLineImpl
     * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCAbstractLine()
     * @generated
     */
    int ARC_ABSTRACT_LINE = 4;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.arc.impl.ARCRootImpl <em>Root</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.arc.impl.ARCRootImpl
     * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCRoot()
     * @generated
     */
    int ARC_ROOT = 5;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_REFERENCEABLE__ID = 0;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_REFERENCEABLE__UUID = 1;

    /**
     * The number of structural features of the '<em>Referenceable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_REFERENCEABLE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Referenceable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_REFERENCEABLE_OPERATION_COUNT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_VERTEX__ID = ARC_REFERENCEABLE__ID;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_VERTEX__UUID = ARC_REFERENCEABLE__UUID;

    /**
     * The feature id for the '<em><b>Top</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_VERTEX__TOP = ARC_REFERENCEABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Left</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_VERTEX__LEFT = ARC_REFERENCEABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_VERTEX__HEIGHT = ARC_REFERENCEABLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_VERTEX__WIDTH = ARC_REFERENCEABLE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Vertex</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_VERTEX_FEATURE_COUNT = ARC_REFERENCEABLE_FEATURE_COUNT + 4;

    /**
     * The number of operations of the '<em>Vertex</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_VERTEX_OPERATION_COUNT = ARC_REFERENCEABLE_OPERATION_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_STATE__ID = ARC_VERTEX__ID;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_STATE__UUID = ARC_VERTEX__UUID;

    /**
     * The feature id for the '<em><b>Top</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_STATE__TOP = ARC_VERTEX__TOP;

    /**
     * The feature id for the '<em><b>Left</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_STATE__LEFT = ARC_VERTEX__LEFT;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_STATE__HEIGHT = ARC_VERTEX__HEIGHT;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_STATE__WIDTH = ARC_VERTEX__WIDTH;

    /**
     * The feature id for the '<em><b>File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_STATE__FILE_ID = ARC_VERTEX_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_STATE__NAME = ARC_VERTEX_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Incoming Line</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_STATE__INCOMING_LINE = ARC_VERTEX_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Outgoing Line</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_STATE__OUTGOING_LINE = ARC_VERTEX_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Eval Priority</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_STATE__EVAL_PRIORITY = ARC_VERTEX_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>State</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_STATE_FEATURE_COUNT = ARC_VERTEX_FEATURE_COUNT + 5;

    /**
     * The number of operations of the '<em>State</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_STATE_OPERATION_COUNT = ARC_VERTEX_OPERATION_COUNT + 0;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_POINT__X = COREPackage.ABSTRACT_POINT__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_POINT__Y = COREPackage.ABSTRACT_POINT__Y;

    /**
     * The number of structural features of the '<em>Point</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_POINT_FEATURE_COUNT = COREPackage.ABSTRACT_POINT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Point</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_POINT_OPERATION_COUNT = COREPackage.ABSTRACT_POINT_OPERATION_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE__ID = ARC_REFERENCEABLE__ID;

    /**
     * The feature id for the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE__UUID = ARC_REFERENCEABLE__UUID;

    /**
     * The feature id for the '<em><b>Source Anchor X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE__SOURCE_ANCHOR_X = ARC_REFERENCEABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Anchor Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE__SOURCE_ANCHOR_Y = ARC_REFERENCEABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target Anchor X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE__TARGET_ANCHOR_X = ARC_REFERENCEABLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Target Anchor Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE__TARGET_ANCHOR_Y = ARC_REFERENCEABLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE__TYPE = ARC_REFERENCEABLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Connection Point</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE__CONNECTION_POINT = ARC_REFERENCEABLE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Variable Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE__VARIABLE_NAME = ARC_REFERENCEABLE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Variable Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE__VARIABLE_TYPE = ARC_REFERENCEABLE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE__X = ARC_REFERENCEABLE_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE__TARGET = ARC_REFERENCEABLE_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE__SOURCE = ARC_REFERENCEABLE_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE__Y = ARC_REFERENCEABLE_FEATURE_COUNT + 11;

    /**
     * The number of structural features of the '<em>Line</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE_FEATURE_COUNT = ARC_REFERENCEABLE_FEATURE_COUNT + 12;

    /**
     * The number of operations of the '<em>Line</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_LINE_OPERATION_COUNT = ARC_REFERENCEABLE_OPERATION_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Anchor X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ABSTRACT_LINE__SOURCE_ANCHOR_X = COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_X;

    /**
     * The feature id for the '<em><b>Source Anchor Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ABSTRACT_LINE__SOURCE_ANCHOR_Y = COREPackage.ABSTRACT_LINE__SOURCE_ANCHOR_Y;

    /**
     * The feature id for the '<em><b>Target Anchor X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ABSTRACT_LINE__TARGET_ANCHOR_X = COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_X;

    /**
     * The feature id for the '<em><b>Target Anchor Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ABSTRACT_LINE__TARGET_ANCHOR_Y = COREPackage.ABSTRACT_LINE__TARGET_ANCHOR_Y;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ABSTRACT_LINE__TYPE = COREPackage.ABSTRACT_LINE__TYPE;

    /**
     * The feature id for the '<em><b>Connection Point</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ABSTRACT_LINE__CONNECTION_POINT = COREPackage.ABSTRACT_LINE__CONNECTION_POINT;

    /**
     * The number of structural features of the '<em>Abstract Line</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ABSTRACT_LINE_FEATURE_COUNT = COREPackage.ABSTRACT_LINE_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Abstract Line</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ABSTRACT_LINE_OPERATION_COUNT = COREPackage.ABSTRACT_LINE_OPERATION_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>Scroll X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ROOT__SCROLL_X = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Scroll Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ROOT__SCROLL_Y = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Position X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ROOT__POSITION_X = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Position Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ROOT__POSITION_Y = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Grid Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ROOT__GRID_SIZE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ROOT__NODES = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Line Mode</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ROOT__LINE_MODE = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Lines</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ROOT__LINES = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>States</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ROOT__STATES = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ARC_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.arc.ARCLineType <em>Line Type</em>}' enum. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.arc.ARCLineType
     * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCLineType()
     * @generated
     */
    int ARC_LINE_TYPE = 7;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.arc.ARCState <em>State</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>State</em>'.
     * @see com.zipc.garden.model.arc.ARCState
     * @generated
     */
    EClass getARCState();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.arc.ARCState#getFileId <em>File Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>File Id</em>'.
     * @see com.zipc.garden.model.arc.ARCState#getFileId()
     * @see #getARCState()
     * @generated
     */
    EAttribute getARCState_FileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.arc.ARCState#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.arc.ARCState#getName()
     * @see #getARCState()
     * @generated
     */
    EAttribute getARCState_Name();

    /**
     * Returns the meta object for the reference list '{@link com.zipc.garden.model.arc.ARCState#getIncomingLine <em>Incoming
     * Line</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Incoming Line</em>'.
     * @see com.zipc.garden.model.arc.ARCState#getIncomingLine()
     * @see #getARCState()
     * @generated
     */
    EReference getARCState_IncomingLine();

    /**
     * Returns the meta object for the reference list '{@link com.zipc.garden.model.arc.ARCState#getOutgoingLine <em>Outgoing
     * Line</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Outgoing Line</em>'.
     * @see com.zipc.garden.model.arc.ARCState#getOutgoingLine()
     * @see #getARCState()
     * @generated
     */
    EReference getARCState_OutgoingLine();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.arc.ARCState#getEvalPriority <em>Eval
     * Priority</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Eval Priority</em>'.
     * @see com.zipc.garden.model.arc.ARCState#getEvalPriority()
     * @see #getARCState()
     * @generated
     */
    EAttribute getARCState_EvalPriority();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.arc.ARCVertex <em>Vertex</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Vertex</em>'.
     * @see com.zipc.garden.model.arc.ARCVertex
     * @generated
     */
    EClass getARCVertex();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.arc.ARCVertex#getTop <em>Top</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Top</em>'.
     * @see com.zipc.garden.model.arc.ARCVertex#getTop()
     * @see #getARCVertex()
     * @generated
     */
    EAttribute getARCVertex_Top();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.arc.ARCVertex#getLeft <em>Left</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Left</em>'.
     * @see com.zipc.garden.model.arc.ARCVertex#getLeft()
     * @see #getARCVertex()
     * @generated
     */
    EAttribute getARCVertex_Left();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.arc.ARCVertex#getHeight <em>Height</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see com.zipc.garden.model.arc.ARCVertex#getHeight()
     * @see #getARCVertex()
     * @generated
     */
    EAttribute getARCVertex_Height();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.arc.ARCVertex#getWidth <em>Width</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see com.zipc.garden.model.arc.ARCVertex#getWidth()
     * @see #getARCVertex()
     * @generated
     */
    EAttribute getARCVertex_Width();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.arc.ARCPoint <em>Point</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Point</em>'.
     * @see com.zipc.garden.model.arc.ARCPoint
     * @generated
     */
    EClass getARCPoint();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.arc.ARCLine <em>Line</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Line</em>'.
     * @see com.zipc.garden.model.arc.ARCLine
     * @generated
     */
    EClass getARCLine();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.arc.ARCLine#getVariableName <em>Variable
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Variable Name</em>'.
     * @see com.zipc.garden.model.arc.ARCLine#getVariableName()
     * @see #getARCLine()
     * @generated
     */
    EAttribute getARCLine_VariableName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.arc.ARCLine#getVariableType <em>Variable
     * Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Variable Type</em>'.
     * @see com.zipc.garden.model.arc.ARCLine#getVariableType()
     * @see #getARCLine()
     * @generated
     */
    EAttribute getARCLine_VariableType();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.arc.ARCLine#getX <em>X</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see com.zipc.garden.model.arc.ARCLine#getX()
     * @see #getARCLine()
     * @generated
     */
    EAttribute getARCLine_X();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.arc.ARCLine#getTarget <em>Target</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see com.zipc.garden.model.arc.ARCLine#getTarget()
     * @see #getARCLine()
     * @generated
     */
    EReference getARCLine_Target();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.arc.ARCLine#getSource <em>Source</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see com.zipc.garden.model.arc.ARCLine#getSource()
     * @see #getARCLine()
     * @generated
     */
    EReference getARCLine_Source();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.arc.ARCLine#getY <em>Y</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see com.zipc.garden.model.arc.ARCLine#getY()
     * @see #getARCLine()
     * @generated
     */
    EAttribute getARCLine_Y();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.arc.ARCAbstractLine <em>Abstract Line</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Line</em>'.
     * @see com.zipc.garden.model.arc.ARCAbstractLine
     * @generated
     */
    EClass getARCAbstractLine();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.arc.ARCRoot <em>Root</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.arc.ARCRoot
     * @generated
     */
    EClass getARCRoot();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.arc.ARCRoot#getLines
     * <em>Lines</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Lines</em>'.
     * @see com.zipc.garden.model.arc.ARCRoot#getLines()
     * @see #getARCRoot()
     * @generated
     */
    EReference getARCRoot_Lines();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.arc.ARCRoot#getStates
     * <em>States</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>States</em>'.
     * @see com.zipc.garden.model.arc.ARCRoot#getStates()
     * @see #getARCRoot()
     * @generated
     */
    EReference getARCRoot_States();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.arc.ARCReferenceable <em>Referenceable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Referenceable</em>'.
     * @see com.zipc.garden.model.arc.ARCReferenceable
     * @generated
     */
    EClass getARCReferenceable();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.arc.ARCReferenceable#getId <em>Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see com.zipc.garden.model.arc.ARCReferenceable#getId()
     * @see #getARCReferenceable()
     * @generated
     */
    EAttribute getARCReferenceable_Id();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.arc.ARCReferenceable#getUuid <em>Uuid</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Uuid</em>'.
     * @see com.zipc.garden.model.arc.ARCReferenceable#getUuid()
     * @see #getARCReferenceable()
     * @generated
     */
    EAttribute getARCReferenceable_Uuid();

    /**
     * Returns the meta object for enum '{@link com.zipc.garden.model.arc.ARCLineType <em>Line Type</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for enum '<em>Line Type</em>'.
     * @see com.zipc.garden.model.arc.ARCLineType
     * @generated
     */
    EEnum getARCLineType();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ARCFactory getARCFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.arc.impl.ARCStateImpl <em>State</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.arc.impl.ARCStateImpl
         * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCState()
         * @generated
         */
        EClass ARC_STATE = eINSTANCE.getARCState();

        /**
         * The meta object literal for the '<em><b>File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ARC_STATE__FILE_ID = eINSTANCE.getARCState_FileId();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ARC_STATE__NAME = eINSTANCE.getARCState_Name();

        /**
         * The meta object literal for the '<em><b>Incoming Line</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference ARC_STATE__INCOMING_LINE = eINSTANCE.getARCState_IncomingLine();

        /**
         * The meta object literal for the '<em><b>Outgoing Line</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference ARC_STATE__OUTGOING_LINE = eINSTANCE.getARCState_OutgoingLine();

        /**
         * The meta object literal for the '<em><b>Eval Priority</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ARC_STATE__EVAL_PRIORITY = eINSTANCE.getARCState_EvalPriority();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.arc.impl.ARCVertexImpl <em>Vertex</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.arc.impl.ARCVertexImpl
         * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCVertex()
         * @generated
         */
        EClass ARC_VERTEX = eINSTANCE.getARCVertex();

        /**
         * The meta object literal for the '<em><b>Top</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ARC_VERTEX__TOP = eINSTANCE.getARCVertex_Top();

        /**
         * The meta object literal for the '<em><b>Left</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ARC_VERTEX__LEFT = eINSTANCE.getARCVertex_Left();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ARC_VERTEX__HEIGHT = eINSTANCE.getARCVertex_Height();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ARC_VERTEX__WIDTH = eINSTANCE.getARCVertex_Width();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.arc.impl.ARCPointImpl <em>Point</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.arc.impl.ARCPointImpl
         * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCPoint()
         * @generated
         */
        EClass ARC_POINT = eINSTANCE.getARCPoint();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.arc.impl.ARCLineImpl <em>Line</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.arc.impl.ARCLineImpl
         * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCLine()
         * @generated
         */
        EClass ARC_LINE = eINSTANCE.getARCLine();

        /**
         * The meta object literal for the '<em><b>Variable Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ARC_LINE__VARIABLE_NAME = eINSTANCE.getARCLine_VariableName();

        /**
         * The meta object literal for the '<em><b>Variable Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute ARC_LINE__VARIABLE_TYPE = eINSTANCE.getARCLine_VariableType();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ARC_LINE__X = eINSTANCE.getARCLine_X();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference ARC_LINE__TARGET = eINSTANCE.getARCLine_Target();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference ARC_LINE__SOURCE = eINSTANCE.getARCLine_Source();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ARC_LINE__Y = eINSTANCE.getARCLine_Y();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.arc.impl.ARCAbstractLineImpl <em>Abstract Line</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.arc.impl.ARCAbstractLineImpl
         * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCAbstractLine()
         * @generated
         */
        EClass ARC_ABSTRACT_LINE = eINSTANCE.getARCAbstractLine();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.arc.impl.ARCRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.arc.impl.ARCRootImpl
         * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCRoot()
         * @generated
         */
        EClass ARC_ROOT = eINSTANCE.getARCRoot();

        /**
         * The meta object literal for the '<em><b>Lines</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ARC_ROOT__LINES = eINSTANCE.getARCRoot_Lines();

        /**
         * The meta object literal for the '<em><b>States</b></em>' containment reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ARC_ROOT__STATES = eINSTANCE.getARCRoot_States();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.arc.impl.ARCReferenceableImpl <em>Referenceable</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.arc.impl.ARCReferenceableImpl
         * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCReferenceable()
         * @generated
         */
        EClass ARC_REFERENCEABLE = eINSTANCE.getARCReferenceable();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute ARC_REFERENCEABLE__ID = eINSTANCE.getARCReferenceable_Id();

        /**
         * The meta object literal for the '<em><b>Uuid</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute ARC_REFERENCEABLE__UUID = eINSTANCE.getARCReferenceable_Uuid();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.arc.ARCLineType <em>Line Type</em>}' enum. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.arc.ARCLineType
         * @see com.zipc.garden.model.arc.impl.ARCPackageImpl#getARCLineType()
         * @generated
         */
        EEnum ARC_LINE_TYPE = eINSTANCE.getARCLineType();

    }

} // ARCPackage
