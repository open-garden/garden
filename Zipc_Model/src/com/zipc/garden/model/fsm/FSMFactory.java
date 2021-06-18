/**
 */
package com.zipc.garden.model.fsm;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.fsm.FSMPackage
 * @generated
 */
public interface FSMFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    FSMFactory eINSTANCE = com.zipc.garden.model.fsm.impl.FSMFactoryImpl.init();

    /**
     * Returns a new object of class '<em>DState Machine</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>DState Machine</em>'.
     * @generated
     */
    FSMDStateMachine createFSMDStateMachine();

    /**
     * Returns a new object of class '<em>DProperty</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>DProperty</em>'.
     * @generated
     */
    FSMDProperty createFSMDProperty();

    /**
     * Returns a new object of class '<em>DState</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>DState</em>'.
     * @generated
     */
    FSMDState createFSMDState();

    /**
     * Returns a new object of class '<em>DTransition</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>DTransition</em>'.
     * @generated
     */
    FSMDTransition createFSMDTransition();

    /**
     * Returns a new object of class '<em>DMemo</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>DMemo</em>'.
     * @generated
     */
    FSMDMemo createFSMDMemo();

    /**
     * Returns a new object of class '<em>DRegion</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>DRegion</em>'.
     * @generated
     */
    FSMDRegion createFSMDRegion();

    /**
     * Returns a new object of class '<em>DLine</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>DLine</em>'.
     * @generated
     */
    FSMDLine createFSMDLine();

    /**
     * Returns a new object of class '<em>DEvent</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>DEvent</em>'.
     * @generated
     */
    FSMDEvent createFSMDEvent();

    /**
     * Returns a new object of class '<em>DAction</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>DAction</em>'.
     * @generated
     */
    FSMDAction createFSMDAction();

    /**
     * Returns a new object of class '<em>DPoint</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>DPoint</em>'.
     * @generated
     */
    FSMDPoint createFSMDPoint();

    /**
     * Returns a new object of class '<em>DVariable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>DVariable</em>'.
     * @generated
     */
    FSMDVariable createFSMDVariable();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    FSMPackage getFSMPackage();

} // FSMFactory
