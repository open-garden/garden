/**
 */
package com.zipc.garden.model.fmcs;

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
 * @see com.zipc.garden.model.fmcs.FMCSFactory
 * @model kind="package"
 * @generated
 */
public interface FMCSPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "fmcs";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.fmcs";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "FMCS";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    FMCSPackage eINSTANCE = com.zipc.garden.model.fmcs.impl.FMCSPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fmcs.impl.FMCSRootImpl <em>Root</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fmcs.impl.FMCSRootImpl
     * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSRoot()
     * @generated
     */
    int FMCS_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_ROOT__CONSTRAINTS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fmcs.impl.FMCSConstraintImpl <em>Constraint</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fmcs.impl.FMCSConstraintImpl
     * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSConstraint()
     * @generated
     */
    int FMCS_CONSTRAINT = 1;

    /**
     * The feature id for the '<em><b>Expression</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_CONSTRAINT__EXPRESSION = 0;

    /**
     * The feature id for the '<em><b>Ref</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_CONSTRAINT__REF = 1;

    /**
     * The number of structural features of the '<em>Constraint</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_CONSTRAINT_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Constraint</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_CONSTRAINT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fmcs.impl.FMCSExpressionImpl <em>Expression</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fmcs.impl.FMCSExpressionImpl
     * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSExpression()
     * @generated
     */
    int FMCS_EXPRESSION = 2;

    /**
     * The number of structural features of the '<em>Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_EXPRESSION_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_EXPRESSION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fmcs.impl.FMCSImpliesExpressionImpl <em>Implies
     * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fmcs.impl.FMCSImpliesExpressionImpl
     * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSImpliesExpression()
     * @generated
     */
    int FMCS_IMPLIES_EXPRESSION = 6;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fmcs.impl.FMCSOrExpressionImpl <em>Or Expression</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fmcs.impl.FMCSOrExpressionImpl
     * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSOrExpression()
     * @generated
     */
    int FMCS_OR_EXPRESSION = 5;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fmcs.impl.FMCSAndExpressionImpl <em>And Expression</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fmcs.impl.FMCSAndExpressionImpl
     * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSAndExpression()
     * @generated
     */
    int FMCS_AND_EXPRESSION = 4;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fmcs.impl.FMCSNotExpressionImpl <em>Not Expression</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fmcs.impl.FMCSNotExpressionImpl
     * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSNotExpression()
     * @generated
     */
    int FMCS_NOT_EXPRESSION = 3;

    /**
     * The feature id for the '<em><b>Expression</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_NOT_EXPRESSION__EXPRESSION = FMCS_EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Not Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_NOT_EXPRESSION_FEATURE_COUNT = FMCS_EXPRESSION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Not Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_NOT_EXPRESSION_OPERATION_COUNT = FMCS_EXPRESSION_OPERATION_COUNT + 0;

    /**
     * The feature id for the '<em><b>Expressions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_AND_EXPRESSION__EXPRESSIONS = FMCS_EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>And Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_AND_EXPRESSION_FEATURE_COUNT = FMCS_EXPRESSION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>And Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_AND_EXPRESSION_OPERATION_COUNT = FMCS_EXPRESSION_OPERATION_COUNT + 0;

    /**
     * The feature id for the '<em><b>Expressions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_OR_EXPRESSION__EXPRESSIONS = FMCS_EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Or Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_OR_EXPRESSION_FEATURE_COUNT = FMCS_EXPRESSION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Or Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_OR_EXPRESSION_OPERATION_COUNT = FMCS_EXPRESSION_OPERATION_COUNT + 0;

    /**
     * The feature id for the '<em><b>Left Expression</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FMCS_IMPLIES_EXPRESSION__LEFT_EXPRESSION = FMCS_EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Right Expression</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_IMPLIES_EXPRESSION__RIGHT_EXPRESSION = FMCS_EXPRESSION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Implies Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FMCS_IMPLIES_EXPRESSION_FEATURE_COUNT = FMCS_EXPRESSION_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Implies Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_IMPLIES_EXPRESSION_OPERATION_COUNT = FMCS_EXPRESSION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fmcs.impl.FMCSSelectExpressionImpl <em>Select Expression</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fmcs.impl.FMCSSelectExpressionImpl
     * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSSelectExpression()
     * @generated
     */
    int FMCS_SELECT_EXPRESSION = 7;

    /**
     * The feature id for the '<em><b>Od Element</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_SELECT_EXPRESSION__OD_ELEMENT = FMCS_EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Select Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FMCS_SELECT_EXPRESSION_FEATURE_COUNT = FMCS_EXPRESSION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Select Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_SELECT_EXPRESSION_OPERATION_COUNT = FMCS_EXPRESSION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fmcs.impl.FMCSODElementImpl <em>OD Element</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fmcs.impl.FMCSODElementImpl
     * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSODElement()
     * @generated
     */
    int FMCS_OD_ELEMENT = 8;

    /**
     * The feature id for the '<em><b>Full Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_OD_ELEMENT__FULL_NAME = 0;

    /**
     * The feature id for the '<em><b>Node</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_OD_ELEMENT__NODE = 1;

    /**
     * The number of structural features of the '<em>OD Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_OD_ELEMENT_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>OD Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_OD_ELEMENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fmcs.impl.FMCSMutexExpressionImpl <em>Mutex Expression</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fmcs.impl.FMCSMutexExpressionImpl
     * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSMutexExpression()
     * @generated
     */
    int FMCS_MUTEX_EXPRESSION = 10;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fmcs.impl.FMCSRemovesExpressionImpl <em>Removes
     * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fmcs.impl.FMCSRemovesExpressionImpl
     * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSRemovesExpression()
     * @generated
     */
    int FMCS_REMOVES_EXPRESSION = 9;

    /**
     * The feature id for the '<em><b>Expression</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_REMOVES_EXPRESSION__EXPRESSION = FMCS_EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Od Element</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_REMOVES_EXPRESSION__OD_ELEMENT = FMCS_EXPRESSION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Removes Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FMCS_REMOVES_EXPRESSION_FEATURE_COUNT = FMCS_EXPRESSION_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Removes Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_REMOVES_EXPRESSION_OPERATION_COUNT = FMCS_EXPRESSION_OPERATION_COUNT + 0;

    /**
     * The feature id for the '<em><b>Od Elements</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_MUTEX_EXPRESSION__OD_ELEMENTS = FMCS_EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Mutex Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_MUTEX_EXPRESSION_FEATURE_COUNT = FMCS_EXPRESSION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Mutex Expression</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMCS_MUTEX_EXPRESSION_OPERATION_COUNT = FMCS_EXPRESSION_OPERATION_COUNT + 0;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fmcs.FMCSRoot <em>Root</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSRoot
     * @generated
     */
    EClass getFMCSRoot();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.fmcs.FMCSRoot#getConstraints
     * <em>Constraints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Constraints</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSRoot#getConstraints()
     * @see #getFMCSRoot()
     * @generated
     */
    EReference getFMCSRoot_Constraints();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fmcs.FMCSConstraint <em>Constraint</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Constraint</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSConstraint
     * @generated
     */
    EClass getFMCSConstraint();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.fmcs.FMCSConstraint#getExpression
     * <em>Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Expression</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSConstraint#getExpression()
     * @see #getFMCSConstraint()
     * @generated
     */
    EReference getFMCSConstraint_Expression();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.fmcs.FMCSConstraint#getRef <em>Ref</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Ref</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSConstraint#getRef()
     * @see #getFMCSConstraint()
     * @generated
     */
    EReference getFMCSConstraint_Ref();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fmcs.FMCSExpression <em>Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Expression</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSExpression
     * @generated
     */
    EClass getFMCSExpression();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fmcs.FMCSImpliesExpression <em>Implies Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Implies Expression</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSImpliesExpression
     * @generated
     */
    EClass getFMCSImpliesExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link com.zipc.garden.model.fmcs.FMCSImpliesExpression#getLeftExpression <em>Left Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Left Expression</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSImpliesExpression#getLeftExpression()
     * @see #getFMCSImpliesExpression()
     * @generated
     */
    EReference getFMCSImpliesExpression_LeftExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link com.zipc.garden.model.fmcs.FMCSImpliesExpression#getRightExpression <em>Right Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Right Expression</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSImpliesExpression#getRightExpression()
     * @see #getFMCSImpliesExpression()
     * @generated
     */
    EReference getFMCSImpliesExpression_RightExpression();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fmcs.FMCSOrExpression <em>Or Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Or Expression</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSOrExpression
     * @generated
     */
    EClass getFMCSOrExpression();

    /**
     * Returns the meta object for the containment reference list
     * '{@link com.zipc.garden.model.fmcs.FMCSOrExpression#getExpressions <em>Expressions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>Expressions</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSOrExpression#getExpressions()
     * @see #getFMCSOrExpression()
     * @generated
     */
    EReference getFMCSOrExpression_Expressions();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fmcs.FMCSAndExpression <em>And Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>And Expression</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSAndExpression
     * @generated
     */
    EClass getFMCSAndExpression();

    /**
     * Returns the meta object for the containment reference list
     * '{@link com.zipc.garden.model.fmcs.FMCSAndExpression#getExpressions <em>Expressions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>Expressions</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSAndExpression#getExpressions()
     * @see #getFMCSAndExpression()
     * @generated
     */
    EReference getFMCSAndExpression_Expressions();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fmcs.FMCSNotExpression <em>Not Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Not Expression</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSNotExpression
     * @generated
     */
    EClass getFMCSNotExpression();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.fmcs.FMCSNotExpression#getExpression
     * <em>Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Expression</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSNotExpression#getExpression()
     * @see #getFMCSNotExpression()
     * @generated
     */
    EReference getFMCSNotExpression_Expression();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fmcs.FMCSSelectExpression <em>Select Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Select Expression</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSSelectExpression
     * @generated
     */
    EClass getFMCSSelectExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link com.zipc.garden.model.fmcs.FMCSSelectExpression#getOdElement <em>Od Element</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference '<em>Od Element</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSSelectExpression#getOdElement()
     * @see #getFMCSSelectExpression()
     * @generated
     */
    EReference getFMCSSelectExpression_OdElement();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fmcs.FMCSODElement <em>OD Element</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>OD Element</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSODElement
     * @generated
     */
    EClass getFMCSODElement();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.fmcs.FMCSODElement#getNode <em>Node</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Node</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSODElement#getNode()
     * @see #getFMCSODElement()
     * @generated
     */
    EReference getFMCSODElement_Node();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fmcs.FMCSODElement#getFullName <em>Full
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Full Name</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSODElement#getFullName()
     * @see #getFMCSODElement()
     * @generated
     */
    EAttribute getFMCSODElement_FullName();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fmcs.FMCSMutexExpression <em>Mutex Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Mutex Expression</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSMutexExpression
     * @generated
     */
    EClass getFMCSMutexExpression();

    /**
     * Returns the meta object for the containment reference list
     * '{@link com.zipc.garden.model.fmcs.FMCSMutexExpression#getOdElements <em>Od Elements</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference list '<em>Od Elements</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSMutexExpression#getOdElements()
     * @see #getFMCSMutexExpression()
     * @generated
     */
    EReference getFMCSMutexExpression_OdElements();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fmcs.FMCSRemovesExpression <em>Removes Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Removes Expression</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSRemovesExpression
     * @generated
     */
    EClass getFMCSRemovesExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link com.zipc.garden.model.fmcs.FMCSRemovesExpression#getOdElement <em>Od Element</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for the containment reference '<em>Od Element</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSRemovesExpression#getOdElement()
     * @see #getFMCSRemovesExpression()
     * @generated
     */
    EReference getFMCSRemovesExpression_OdElement();

    /**
     * Returns the meta object for the containment reference
     * '{@link com.zipc.garden.model.fmcs.FMCSRemovesExpression#getExpression <em>Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Expression</em>'.
     * @see com.zipc.garden.model.fmcs.FMCSRemovesExpression#getExpression()
     * @see #getFMCSRemovesExpression()
     * @generated
     */
    EReference getFMCSRemovesExpression_Expression();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    FMCSFactory getFMCSFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.fmcs.impl.FMCSRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fmcs.impl.FMCSRootImpl
         * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSRoot()
         * @generated
         */
        EClass FMCS_ROOT = eINSTANCE.getFMCSRoot();

        /**
         * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FMCS_ROOT__CONSTRAINTS = eINSTANCE.getFMCSRoot_Constraints();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fmcs.impl.FMCSConstraintImpl <em>Constraint</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fmcs.impl.FMCSConstraintImpl
         * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSConstraint()
         * @generated
         */
        EClass FMCS_CONSTRAINT = eINSTANCE.getFMCSConstraint();

        /**
         * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FMCS_CONSTRAINT__EXPRESSION = eINSTANCE.getFMCSConstraint_Expression();

        /**
         * The meta object literal for the '<em><b>Ref</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference FMCS_CONSTRAINT__REF = eINSTANCE.getFMCSConstraint_Ref();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fmcs.impl.FMCSExpressionImpl <em>Expression</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fmcs.impl.FMCSExpressionImpl
         * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSExpression()
         * @generated
         */
        EClass FMCS_EXPRESSION = eINSTANCE.getFMCSExpression();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fmcs.impl.FMCSImpliesExpressionImpl <em>Implies
         * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fmcs.impl.FMCSImpliesExpressionImpl
         * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSImpliesExpression()
         * @generated
         */
        EClass FMCS_IMPLIES_EXPRESSION = eINSTANCE.getFMCSImpliesExpression();

        /**
         * The meta object literal for the '<em><b>Left Expression</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FMCS_IMPLIES_EXPRESSION__LEFT_EXPRESSION = eINSTANCE.getFMCSImpliesExpression_LeftExpression();

        /**
         * The meta object literal for the '<em><b>Right Expression</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FMCS_IMPLIES_EXPRESSION__RIGHT_EXPRESSION = eINSTANCE.getFMCSImpliesExpression_RightExpression();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fmcs.impl.FMCSOrExpressionImpl <em>Or Expression</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fmcs.impl.FMCSOrExpressionImpl
         * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSOrExpression()
         * @generated
         */
        EClass FMCS_OR_EXPRESSION = eINSTANCE.getFMCSOrExpression();

        /**
         * The meta object literal for the '<em><b>Expressions</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FMCS_OR_EXPRESSION__EXPRESSIONS = eINSTANCE.getFMCSOrExpression_Expressions();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fmcs.impl.FMCSAndExpressionImpl <em>And
         * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fmcs.impl.FMCSAndExpressionImpl
         * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSAndExpression()
         * @generated
         */
        EClass FMCS_AND_EXPRESSION = eINSTANCE.getFMCSAndExpression();

        /**
         * The meta object literal for the '<em><b>Expressions</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FMCS_AND_EXPRESSION__EXPRESSIONS = eINSTANCE.getFMCSAndExpression_Expressions();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fmcs.impl.FMCSNotExpressionImpl <em>Not
         * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fmcs.impl.FMCSNotExpressionImpl
         * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSNotExpression()
         * @generated
         */
        EClass FMCS_NOT_EXPRESSION = eINSTANCE.getFMCSNotExpression();

        /**
         * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FMCS_NOT_EXPRESSION__EXPRESSION = eINSTANCE.getFMCSNotExpression_Expression();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fmcs.impl.FMCSSelectExpressionImpl <em>Select
         * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fmcs.impl.FMCSSelectExpressionImpl
         * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSSelectExpression()
         * @generated
         */
        EClass FMCS_SELECT_EXPRESSION = eINSTANCE.getFMCSSelectExpression();

        /**
         * The meta object literal for the '<em><b>Od Element</b></em>' containment reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FMCS_SELECT_EXPRESSION__OD_ELEMENT = eINSTANCE.getFMCSSelectExpression_OdElement();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fmcs.impl.FMCSODElementImpl <em>OD Element</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fmcs.impl.FMCSODElementImpl
         * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSODElement()
         * @generated
         */
        EClass FMCS_OD_ELEMENT = eINSTANCE.getFMCSODElement();

        /**
         * The meta object literal for the '<em><b>Node</b></em>' reference feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EReference FMCS_OD_ELEMENT__NODE = eINSTANCE.getFMCSODElement_Node();

        /**
         * The meta object literal for the '<em><b>Full Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FMCS_OD_ELEMENT__FULL_NAME = eINSTANCE.getFMCSODElement_FullName();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fmcs.impl.FMCSMutexExpressionImpl <em>Mutex
         * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fmcs.impl.FMCSMutexExpressionImpl
         * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSMutexExpression()
         * @generated
         */
        EClass FMCS_MUTEX_EXPRESSION = eINSTANCE.getFMCSMutexExpression();

        /**
         * The meta object literal for the '<em><b>Od Elements</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FMCS_MUTEX_EXPRESSION__OD_ELEMENTS = eINSTANCE.getFMCSMutexExpression_OdElements();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fmcs.impl.FMCSRemovesExpressionImpl <em>Removes
         * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fmcs.impl.FMCSRemovesExpressionImpl
         * @see com.zipc.garden.model.fmcs.impl.FMCSPackageImpl#getFMCSRemovesExpression()
         * @generated
         */
        EClass FMCS_REMOVES_EXPRESSION = eINSTANCE.getFMCSRemovesExpression();

        /**
         * The meta object literal for the '<em><b>Od Element</b></em>' containment reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FMCS_REMOVES_EXPRESSION__OD_ELEMENT = eINSTANCE.getFMCSRemovesExpression_OdElement();

        /**
         * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FMCS_REMOVES_EXPRESSION__EXPRESSION = eINSTANCE.getFMCSRemovesExpression_Expression();

    }

} // FMCSPackage
