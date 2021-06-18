/**
 */
package com.zipc.garden.model.fmc;

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
 * @see com.zipc.garden.model.fmc.FMCFactory
 * @model kind="package"
 * @generated
 */
public interface FMCPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "fmc";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.fmc";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "FMC";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    FMCPackage eINSTANCE = com.zipc.garden.model.fmc.impl.FMCPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fmc.impl.FMCRootImpl <em>Root</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fmc.impl.FMCRootImpl
     * @see com.zipc.garden.model.fmc.impl.FMCPackageImpl#getFMCRoot()
     * @generated
     */
    int FMC_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_ROOT__CONSTRAINTS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Nodepaths</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int FMC_ROOT__NODEPATHS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Document</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_ROOT__DOCUMENT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fmc.impl.FMCConstraintImpl <em>Constraint</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fmc.impl.FMCConstraintImpl
     * @see com.zipc.garden.model.fmc.impl.FMCPackageImpl#getFMCConstraint()
     * @generated
     */
    int FMC_CONSTRAINT = 1;

    /**
     * The feature id for the '<em><b>Constraint</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_CONSTRAINT__CONSTRAINT = 0;

    /**
     * The feature id for the '<em><b>Comment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_CONSTRAINT__COMMENT = 1;

    /**
     * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_CONSTRAINT__ENABLED = 2;

    /**
     * The feature id for the '<em><b>Related Nodes</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_CONSTRAINT__RELATED_NODES = 3;

    /**
     * The number of structural features of the '<em>Constraint</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_CONSTRAINT_FEATURE_COUNT = 4;

    /**
     * The number of operations of the '<em>Constraint</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_CONSTRAINT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.fmc.impl.FMCNodePathImpl <em>Node Path</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.fmc.impl.FMCNodePathImpl
     * @see com.zipc.garden.model.fmc.impl.FMCPackageImpl#getFMCNodePath()
     * @generated
     */
    int FMC_NODE_PATH = 2;

    /**
     * The feature id for the '<em><b>Fullpath</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_NODE_PATH__FULLPATH = 0;

    /**
     * The feature id for the '<em><b>Simple Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_NODE_PATH__SIMPLE_PATH = 1;

    /**
     * The feature id for the '<em><b>Node Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_NODE_PATH__NODE_NAME = 2;

    /**
     * The number of structural features of the '<em>Node Path</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_NODE_PATH_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Node Path</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FMC_NODE_PATH_OPERATION_COUNT = 0;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fmc.FMCRoot <em>Root</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.fmc.FMCRoot
     * @generated
     */
    EClass getFMCRoot();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.fmc.FMCRoot#getConstraints
     * <em>Constraints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Constraints</em>'.
     * @see com.zipc.garden.model.fmc.FMCRoot#getConstraints()
     * @see #getFMCRoot()
     * @generated
     */
    EReference getFMCRoot_Constraints();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.fmc.FMCRoot#getNodepaths
     * <em>Nodepaths</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Nodepaths</em>'.
     * @see com.zipc.garden.model.fmc.FMCRoot#getNodepaths()
     * @see #getFMCRoot()
     * @generated
     */
    EReference getFMCRoot_Nodepaths();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fmc.FMCRoot#getDocument <em>Document</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Document</em>'.
     * @see com.zipc.garden.model.fmc.FMCRoot#getDocument()
     * @see #getFMCRoot()
     * @generated
     */
    EAttribute getFMCRoot_Document();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fmc.FMCConstraint <em>Constraint</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Constraint</em>'.
     * @see com.zipc.garden.model.fmc.FMCConstraint
     * @generated
     */
    EClass getFMCConstraint();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fmc.FMCConstraint#getConstraint
     * <em>Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Constraint</em>'.
     * @see com.zipc.garden.model.fmc.FMCConstraint#getConstraint()
     * @see #getFMCConstraint()
     * @generated
     */
    EAttribute getFMCConstraint_Constraint();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fmc.FMCConstraint#getComment <em>Comment</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Comment</em>'.
     * @see com.zipc.garden.model.fmc.FMCConstraint#getComment()
     * @see #getFMCConstraint()
     * @generated
     */
    EAttribute getFMCConstraint_Comment();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fmc.FMCConstraint#isEnabled <em>Enabled</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Enabled</em>'.
     * @see com.zipc.garden.model.fmc.FMCConstraint#isEnabled()
     * @see #getFMCConstraint()
     * @generated
     */
    EAttribute getFMCConstraint_Enabled();

    /**
     * Returns the meta object for the reference list '{@link com.zipc.garden.model.fmc.FMCConstraint#getRelatedNodes
     * <em>Related Nodes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Related Nodes</em>'.
     * @see com.zipc.garden.model.fmc.FMCConstraint#getRelatedNodes()
     * @see #getFMCConstraint()
     * @generated
     */
    EReference getFMCConstraint_RelatedNodes();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.fmc.FMCNodePath <em>Node Path</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Node Path</em>'.
     * @see com.zipc.garden.model.fmc.FMCNodePath
     * @generated
     */
    EClass getFMCNodePath();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fmc.FMCNodePath#getFullpath <em>Fullpath</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Fullpath</em>'.
     * @see com.zipc.garden.model.fmc.FMCNodePath#getFullpath()
     * @see #getFMCNodePath()
     * @generated
     */
    EAttribute getFMCNodePath_Fullpath();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fmc.FMCNodePath#getSimplePath <em>Simple
     * Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Simple Path</em>'.
     * @see com.zipc.garden.model.fmc.FMCNodePath#getSimplePath()
     * @see #getFMCNodePath()
     * @generated
     */
    EAttribute getFMCNodePath_SimplePath();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.fmc.FMCNodePath#getNodeName <em>Node Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Node Name</em>'.
     * @see com.zipc.garden.model.fmc.FMCNodePath#getNodeName()
     * @see #getFMCNodePath()
     * @generated
     */
    EAttribute getFMCNodePath_NodeName();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    FMCFactory getFMCFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.fmc.impl.FMCRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fmc.impl.FMCRootImpl
         * @see com.zipc.garden.model.fmc.impl.FMCPackageImpl#getFMCRoot()
         * @generated
         */
        EClass FMC_ROOT = eINSTANCE.getFMCRoot();

        /**
         * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FMC_ROOT__CONSTRAINTS = eINSTANCE.getFMCRoot_Constraints();

        /**
         * The meta object literal for the '<em><b>Nodepaths</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference FMC_ROOT__NODEPATHS = eINSTANCE.getFMCRoot_Nodepaths();

        /**
         * The meta object literal for the '<em><b>Document</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FMC_ROOT__DOCUMENT = eINSTANCE.getFMCRoot_Document();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fmc.impl.FMCConstraintImpl <em>Constraint</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fmc.impl.FMCConstraintImpl
         * @see com.zipc.garden.model.fmc.impl.FMCPackageImpl#getFMCConstraint()
         * @generated
         */
        EClass FMC_CONSTRAINT = eINSTANCE.getFMCConstraint();

        /**
         * The meta object literal for the '<em><b>Constraint</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FMC_CONSTRAINT__CONSTRAINT = eINSTANCE.getFMCConstraint_Constraint();

        /**
         * The meta object literal for the '<em><b>Comment</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FMC_CONSTRAINT__COMMENT = eINSTANCE.getFMCConstraint_Comment();

        /**
         * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FMC_CONSTRAINT__ENABLED = eINSTANCE.getFMCConstraint_Enabled();

        /**
         * The meta object literal for the '<em><b>Related Nodes</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference FMC_CONSTRAINT__RELATED_NODES = eINSTANCE.getFMCConstraint_RelatedNodes();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.fmc.impl.FMCNodePathImpl <em>Node Path</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.fmc.impl.FMCNodePathImpl
         * @see com.zipc.garden.model.fmc.impl.FMCPackageImpl#getFMCNodePath()
         * @generated
         */
        EClass FMC_NODE_PATH = eINSTANCE.getFMCNodePath();

        /**
         * The meta object literal for the '<em><b>Fullpath</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FMC_NODE_PATH__FULLPATH = eINSTANCE.getFMCNodePath_Fullpath();

        /**
         * The meta object literal for the '<em><b>Simple Path</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FMC_NODE_PATH__SIMPLE_PATH = eINSTANCE.getFMCNodePath_SimplePath();

        /**
         * The meta object literal for the '<em><b>Node Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute FMC_NODE_PATH__NODE_NAME = eINSTANCE.getFMCNodePath_NodeName();

    }

} // FMCPackage
