/**
 */
package com.zipc.garden.model.bp;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.bp.BPPackage
 * @generated
 */
public interface BPFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    BPFactory eINSTANCE = com.zipc.garden.model.bp.impl.BPFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    BPRoot createBPRoot();

    /**
     * Returns a new object of class '<em>State Machine</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>State Machine</em>'.
     * @generated
     */
    BPStateMachine createBPStateMachine();

    /**
     * Returns a new object of class '<em>Behavior Pattern</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Behavior Pattern</em>'.
     * @generated
     */
    BPBehaviorPattern createBPBehaviorPattern();

    /**
     * Returns a new object of class '<em>Behavior</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Behavior</em>'.
     * @generated
     */
    BPBehavior createBPBehavior();

    /**
     * Returns a new object of class '<em>Step</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Step</em>'.
     * @generated
     */
    BPStep createBPStep();

    /**
     * Returns a new object of class '<em>State</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>State</em>'.
     * @generated
     */
    BPState createBPState();

    /**
     * Returns a new object of class '<em>Event</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Event</em>'.
     * @generated
     */
    BPEvent createBPEvent();

    /**
     * Returns a new object of class '<em>Spec</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Spec</em>'.
     * @generated
     */
    BPSpec createBPSpec();

    /**
     * Returns a new object of class '<em>Spec Element</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Spec Element</em>'.
     * @generated
     */
    BPSpecElement createBPSpecElement();

    /**
     * Returns a new object of class '<em>Setting</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Setting</em>'.
     * @generated
     */
    BPSetting createBPSetting();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    BPPackage getBPPackage();

} // BPFactory
