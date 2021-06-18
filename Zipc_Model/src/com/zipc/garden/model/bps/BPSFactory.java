/**
 */
package com.zipc.garden.model.bps;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.bps.BPSPackage
 * @generated
 */
public interface BPSFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    BPSFactory eINSTANCE = com.zipc.garden.model.bps.impl.BPSFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    BPSRoot createBPSRoot();

    /**
     * Returns a new object of class '<em>State Combination Option</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>State Combination Option</em>'.
     * @generated
     */
    BPSStateCombinationOption createBPSStateCombinationOption();

    /**
     * Returns a new object of class '<em>NSwitch Option</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>NSwitch Option</em>'.
     * @generated
     */
    BPSNSwitchOption createBPSNSwitchOption();

    /**
     * Returns a new object of class '<em>Path Combination Option</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Path Combination Option</em>'.
     * @generated
     */
    BPSPathCombinationOption createBPSPathCombinationOption();

    /**
     * Returns a new object of class '<em>Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Variable</em>'.
     * @generated
     */
    BPSVariable createBPSVariable();

    /**
     * Returns a new object of class '<em>Dataflow</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Dataflow</em>'.
     * @generated
     */
    BPSDataflow createBPSDataflow();

    /**
     * Returns a new object of class '<em>Instance Arc</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Instance Arc</em>'.
     * @generated
     */
    BPSInstanceArc createBPSInstanceArc();

    /**
     * Returns a new object of class '<em>Instance State Machine</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Instance State Machine</em>'.
     * @generated
     */
    BPSInstanceStateMachine createBPSInstanceStateMachine();

    /**
     * Returns a new object of class '<em>Instance State</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Instance State</em>'.
     * @generated
     */
    BPSInstanceState createBPSInstanceState();

    /**
     * Returns a new object of class '<em>Instance Transition</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Instance Transition</em>'.
     * @generated
     */
    BPSInstanceTransition createBPSInstanceTransition();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    BPSPackage getBPSPackage();

} // BPSFactory
