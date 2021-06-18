/**
 */
package com.zipc.garden.model.tps;

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
 * @see com.zipc.garden.model.tps.TPSFactory
 * @model kind="package"
 * @generated
 */
public interface TPSPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "tps";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.tps";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "TPS";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    TPSPackage eINSTANCE = com.zipc.garden.model.tps.impl.TPSPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.tps.impl.TPSRootImpl <em>Root</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.tps.impl.TPSRootImpl
     * @see com.zipc.garden.model.tps.impl.TPSPackageImpl#getTPSRoot()
     * @generated
     */
    int TPS_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TPS_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TPS_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>NWise Combination</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TPS_ROOT__NWISE_COMBINATION = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Root Nodes</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     * @ordered
     */
    int TPS_ROOT__ROOT_NODES = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Fmc Root</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TPS_ROOT__FMC_ROOT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Pattern Elements</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int TPS_ROOT__PATTERN_ELEMENTS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Node Variables</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     * @ordered
     */
    int TPS_ROOT__NODE_VARIABLES = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TPS_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TPS_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.tps.impl.TPSPatternElementImpl <em>Pattern Element</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.tps.impl.TPSPatternElementImpl
     * @see com.zipc.garden.model.tps.impl.TPSPackageImpl#getTPSPatternElement()
     * @generated
     */
    int TPS_PATTERN_ELEMENT = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TPS_PATTERN_ELEMENT__NAME = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TPS_PATTERN_ELEMENT__VALUE = 1;

    /**
     * The number of structural features of the '<em>Pattern Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TPS_PATTERN_ELEMENT_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Pattern Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TPS_PATTERN_ELEMENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link com.zipc.garden.model.tps.impl.NodeVariableImpl <em>Node Variable</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.tps.impl.NodeVariableImpl
     * @see com.zipc.garden.model.tps.impl.TPSPackageImpl#getNodeVariable()
     * @generated
     */
    int NODE_VARIABLE = 2;

    /**
     * The feature id for the '<em><b>Tc Node</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_VARIABLE__TC_NODE = 0;

    /**
     * The feature id for the '<em><b>Variable Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_VARIABLE__VARIABLE_NAME = 1;

    /**
     * The number of structural features of the '<em>Node Variable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_VARIABLE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Node Variable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_VARIABLE_OPERATION_COUNT = 0;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.tps.TPSRoot <em>Root</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.tps.TPSRoot
     * @generated
     */
    EClass getTPSRoot();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tps.TPSRoot#getNWiseCombination <em>NWise
     * Combination</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>NWise Combination</em>'.
     * @see com.zipc.garden.model.tps.TPSRoot#getNWiseCombination()
     * @see #getTPSRoot()
     * @generated
     */
    EAttribute getTPSRoot_NWiseCombination();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.tps.TPSRoot#getRootNodes
     * <em>Root Nodes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Root Nodes</em>'.
     * @see com.zipc.garden.model.tps.TPSRoot#getRootNodes()
     * @see #getTPSRoot()
     * @generated
     */
    EReference getTPSRoot_RootNodes();

    /**
     * Returns the meta object for the containment reference '{@link com.zipc.garden.model.tps.TPSRoot#getFmcRoot <em>Fmc
     * Root</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Fmc Root</em>'.
     * @see com.zipc.garden.model.tps.TPSRoot#getFmcRoot()
     * @see #getTPSRoot()
     * @generated
     */
    EReference getTPSRoot_FmcRoot();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.tps.TPSRoot#getPatternElements
     * <em>Pattern Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Pattern Elements</em>'.
     * @see com.zipc.garden.model.tps.TPSRoot#getPatternElements()
     * @see #getTPSRoot()
     * @generated
     */
    EReference getTPSRoot_PatternElements();

    /**
     * Returns the meta object for the containment reference list '{@link com.zipc.garden.model.tps.TPSRoot#getNodeVariables
     * <em>Node Variables</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Node Variables</em>'.
     * @see com.zipc.garden.model.tps.TPSRoot#getNodeVariables()
     * @see #getTPSRoot()
     * @generated
     */
    EReference getTPSRoot_NodeVariables();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.tps.TPSPatternElement <em>Pattern Element</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Pattern Element</em>'.
     * @see com.zipc.garden.model.tps.TPSPatternElement
     * @generated
     */
    EClass getTPSPatternElement();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tps.TPSPatternElement#getName <em>Name</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see com.zipc.garden.model.tps.TPSPatternElement#getName()
     * @see #getTPSPatternElement()
     * @generated
     */
    EAttribute getTPSPatternElement_Name();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tps.TPSPatternElement#getValue <em>Value</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see com.zipc.garden.model.tps.TPSPatternElement#getValue()
     * @see #getTPSPatternElement()
     * @generated
     */
    EAttribute getTPSPatternElement_Value();

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.tps.NodeVariable <em>Node Variable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for class '<em>Node Variable</em>'.
     * @see com.zipc.garden.model.tps.NodeVariable
     * @generated
     */
    EClass getNodeVariable();

    /**
     * Returns the meta object for the reference '{@link com.zipc.garden.model.tps.NodeVariable#getTcNode <em>Tc Node</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Tc Node</em>'.
     * @see com.zipc.garden.model.tps.NodeVariable#getTcNode()
     * @see #getNodeVariable()
     * @generated
     */
    EReference getNodeVariable_TcNode();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.tps.NodeVariable#getVariableName <em>Variable
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Variable Name</em>'.
     * @see com.zipc.garden.model.tps.NodeVariable#getVariableName()
     * @see #getNodeVariable()
     * @generated
     */
    EAttribute getNodeVariable_VariableName();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    TPSFactory getTPSFactory();

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
         * The meta object literal for the '{@link com.zipc.garden.model.tps.impl.TPSRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.tps.impl.TPSRootImpl
         * @see com.zipc.garden.model.tps.impl.TPSPackageImpl#getTPSRoot()
         * @generated
         */
        EClass TPS_ROOT = eINSTANCE.getTPSRoot();

        /**
         * The meta object literal for the '<em><b>NWise Combination</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute TPS_ROOT__NWISE_COMBINATION = eINSTANCE.getTPSRoot_NWiseCombination();

        /**
         * The meta object literal for the '<em><b>Root Nodes</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * @generated
         */
        EReference TPS_ROOT__ROOT_NODES = eINSTANCE.getTPSRoot_RootNodes();

        /**
         * The meta object literal for the '<em><b>Fmc Root</b></em>' containment reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TPS_ROOT__FMC_ROOT = eINSTANCE.getTPSRoot_FmcRoot();

        /**
         * The meta object literal for the '<em><b>Pattern Elements</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference TPS_ROOT__PATTERN_ELEMENTS = eINSTANCE.getTPSRoot_PatternElements();

        /**
         * The meta object literal for the '<em><b>Node Variables</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EReference TPS_ROOT__NODE_VARIABLES = eINSTANCE.getTPSRoot_NodeVariables();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.tps.impl.TPSPatternElementImpl <em>Pattern
         * Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.tps.impl.TPSPatternElementImpl
         * @see com.zipc.garden.model.tps.impl.TPSPackageImpl#getTPSPatternElement()
         * @generated
         */
        EClass TPS_PATTERN_ELEMENT = eINSTANCE.getTPSPatternElement();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TPS_PATTERN_ELEMENT__NAME = eINSTANCE.getTPSPatternElement_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute TPS_PATTERN_ELEMENT__VALUE = eINSTANCE.getTPSPatternElement_Value();

        /**
         * The meta object literal for the '{@link com.zipc.garden.model.tps.impl.NodeVariableImpl <em>Node Variable</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.tps.impl.NodeVariableImpl
         * @see com.zipc.garden.model.tps.impl.TPSPackageImpl#getNodeVariable()
         * @generated
         */
        EClass NODE_VARIABLE = eINSTANCE.getNodeVariable();

        /**
         * The meta object literal for the '<em><b>Tc Node</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EReference NODE_VARIABLE__TC_NODE = eINSTANCE.getNodeVariable_TcNode();

        /**
         * The meta object literal for the '<em><b>Variable Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute NODE_VARIABLE__VARIABLE_NAME = eINSTANCE.getNodeVariable_VariableName();

    }

} // TPSPackage
